package granulej.lang;

import granulej.gui.datastructure.ExecuteUnit;
import granulej.gui.datastructure.GranuleUnit;
import gui.constant.GranuleConstant;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class InvokeAgent {
	
	private static ArrayList<String> lastGranules = null;
	private static HashMap<String,Class> loadedGranules=new HashMap<String,Class>();
	private static HashMap<String,Method> fitnessMethods = new HashMap<String,Method>();
	
	//做适合性检查
	public static <IN> boolean doFitness(String gName)
	{
//		System.out.println("in doFitness");

        //记录下当前可能要处理的粒
		lastGranules = null;
		return fitnessChecking(gName);
		
/*
		 * 这里代码有用
		 * 这是遍历演化记录，来查找之前到现在是否有合理的粒，也就是本地先查找是否有合适的粒可使用
		for(String gName:gNameList)
		{
			printLog("granuleName: "+gName);
			if(fitnessChecking(className,gName,methodName,methodSignature))
				return true;
		}
*/

	}
	
	//替换返回类型不是void的方法
	public static <IN,OUT> OUT replaceMethod(IN obj,String gName,String methodName,Class[] argsTypes,Object [] args)
	{
//		printLog("in replaceMethod");
		
		GranuleUnit granuleUnit = searchForSimilarGranule(lastGranules);
		if(granuleUnit==null)
		{
			System.out.println("can't find similar granule!!!");
			return null;
		}
		
		ExecuteUnit exeUnit = analyseExecuteUnit(obj,gName,granuleUnit);
		if(exeUnit==null)
		{
			System.out.println("can't find new execute method!!!");
			return null;
		}
		
		//执行新对象的函数,并将新的对象更新到对象列表中
		Object result = executeShadowClassMethod(exeUnit.newInstance,exeUnit.newClass,methodName,argsTypes,args);
		ObjectAgent.updateObject(obj.hashCode(), exeUnit.newInstance);
		new Thread(new xmlFileUpdate(IndividualInfo.getInstance().getConfigfile(),"cTree")).start();
		
		return (OUT)result;
	}
	
	//替换返回类型为void的方法
	public static <IN> void replaceVoidMethod(IN obj,String gName,String methodName,Class[] argsTypes,Object [] args)
	{
//		printLog("in replaceVoidMethod");
		
		GranuleUnit granuleUnit = searchForSimilarGranule(lastGranules);
		if(granuleUnit==null)
		{
			System.out.println("can't find similar granule!!!");
			return;
		}

		ExecuteUnit exeUnit = analyseExecuteUnit(obj,gName,granuleUnit);

		if(exeUnit==null)
		{
			System.out.println("can't find new execute method!!!");
			return;
		}
//		test();		
		
//		//执行新对象的函数，并将新的对象更新到对象列表中
		executeShadowClassMethod(exeUnit.newInstance,exeUnit.newClass,methodName,argsTypes,args);
		ObjectAgent.updateObject(obj.hashCode(), exeUnit.newInstance);
		new Thread(new xmlFileUpdate(IndividualInfo.getInstance().getConfigfile(),"cTree")).start();
	}	
	
	private static GranuleUnit searchForSimilarGranule(ArrayList<String> gNames)
	{
		//如果当前粒为空，那就没法替换
		if(gNames == null || gNames.size()==0)
			return null;
		
		//查找并执行相似粒，直到找到合适的粒为止
		Class simiGranule=null;
		String simiFileName=null;
		Class currentGranule = null;

		while(simiGranule==null||simiFileName==null||currentGranule==null)
		{

			
			boolean isFit = true;
			for(int j=gNames.size()-1;j>=0&&isFit;--j)
			{
				 String gName = gNames.get(j);
				 //TODO 这里如果在服务器上实在找不到粒，应该跳出去，但现在的服务器没有处理这种情况，找不到就一直找
//				 System.out.println("gName: "+gName);
	             Object [] similarGra=GranulePolling.getInstance().getPollingResult(gName,0);
//	             System.out.println(similarGra[1]);
	             //此处用于测试排除网络部分的执行效率
//				 Object [] similarGra = new Object[2];
//				 similarGra[0] = loadedGranules.get(gName+"2");
//				 if(similarGra[0]==null)
//					 System.out.println("granule is null");
//				 similarGra[1] = "C:/Users/Administrator/Downloads/GranuleJIDE/example/Test_1/bin/TestGranuleTree.xml";
	             if(similarGra[1].equals(GranuleConstant.GRANULE_NOT_EXIST))
	             {
	            	 System.out.println("no similar granule on granule server!!!");
	            	 return null;
	             }
	
	             //TODO 这里还需要对相似粒的子粒做适合性检测，只有当对应的子粒也符合适合性条件的时候才能算是相似粒
	             if(executeGranule((Class)similarGra[0]))
	             {
	            	 if(j==0)
	            	 {
	            		 simiGranule = (Class)similarGra[0];
	            	 }
	            	 if(j==gNames.size()-1)
	            	 {
	            		 currentGranule = (Class)similarGra[0];
		                 simiFileName = (String)similarGra[1]; 
	            	 }
	             }
	             else
	             {
	            	 isFit = false;
	             }
			}
			

		}
		
//		printLog("find similar granule: "+simiGranule);
		
		//如果服务器上找不到合适的粒
		if(simiGranule==null||simiFileName==null)
		{
			System.out.println("Similar granule not found!");
			return null;
		}
		
		GranuleUnit unit = new GranuleUnit(simiGranule,currentGranule,simiFileName);
		return unit;
	}
	
	private static ExecuteUnit analyseExecuteUnit(Object obj,String gName,GranuleUnit unit)
	{
//		printLog("in ExecuteUnit");
		//是否在安全点
		String className = obj.getClass().getName();
		if(isSafePoint(className))
		{
			String shadowClassName = "";
			try{
//				System.out.println("lastGranules.get(0): "+lastGranules.get(0));
//				System.out.println("simiGranule.getName(): "+unit.simiGranule.getName());
				XmlParser.replaceGranuleTree(lastGranules.get(0), unit.simiFileName, unit.simiGranule.getName());
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			shadowClassName = unit.currentGranule.getName()+"_"+className.split("_")[1];
			
			//当粒替换发生的位置在父粒上的时候，需要用匹配的新结果来组装影子类的名字
//			shadowClassName = gName+"_"+className.split("_")[1];

//			System.out.println(shadowClassName);

//			Object curInstance = instances.get(obj.hashCode());

			ClassLoader classLoader = obj.getClass().getClassLoader();
			Class newClass = null;
			try{
				newClass = classLoader.loadClass(shadowClassName);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if(newClass==null)
			{
				System.out.println("load new class failed");
				return null;
			}
			
			Object newInstance = transferClassAndObjectState(newClass,obj);

			if(newInstance==null)
			{
				printLog("create new object failed");
				return null;
			}
			
			ExecuteUnit result = new ExecuteUnit(newInstance,newClass);
//			System.out.println("class is on the safePoint!");
			return result;

		}
		
//		System.out.println("class is not on the safePoint!");
		return null;
	}
	
	//执行影子类的方法
	private static Object executeShadowClassMethod(Object obj,Class cls,String methodName,Class [] argsTypes,Object[] args)
	{
		//获取方法
//		printLog("class:"+cls.getName());
		Method method = null;
		try{
			method=cls.getDeclaredMethod(methodName,argsTypes);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			printLog(methodName+"get method failed"+"  ! in method of executeShadowClassMethod");
			return null;
		}
		
		Object result=null;
		//执行方法
		try{
			method.setAccessible(true);
			result=method.invoke(obj, args);
		}
		catch(Exception e)
		{
			printLog(methodName+"execute method failed"+"  ! in method of executeShadowClassMethod");
			printLog(e.toString());
		}
		
		return result;
	}
	
	//装换类的状态和对象的状态
	private static Object transferClassAndObjectState(Class newClass,Object oldObject)
	{
		//接下来该转换类的状态和对象，对应vs中的translateClassState()
		Class oldClass=oldObject.getClass();
		
//		System.out.println("start transfer class and object!");
		
		//生成新的对象
		Object resObj=null;
		try{
			Constructor constructor = newClass.getDeclaredConstructor();
			constructor.setAccessible(true);

			resObj=constructor.newInstance();
		}
		catch(Exception e)
		{
			printLog(e.toString());
			printLog(newClass.getName()+"create new instance failed"+"  ! in method of transferClassAndObjectState");
			return resObj;
		}
		
		int fieldCount=0;
		//获取新类的属性，如果刚好旧类中的静态属性存在，那么就转移过来，如果不存在，则初始化为null
		Field [] newFields = newClass.getDeclaredFields();
		for(Field field:newFields)
		{
			//如果属性是静态的，则转换到类的属性上
			if(Modifier.isStatic(field.getModifiers()))
			{
				//获取该属性的值
				Object fieldValue = null;
				try{
					Field oldField = oldClass.getDeclaredField(field.getName());
					if(oldField!=null&&Modifier.isStatic(oldField.getModifiers()))
					{
						oldField.setAccessible(true);
						fieldValue=oldField.get(null);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					printLog(field.getName()+"get static field failed"+"  ! in method of transferClassAndObjectState");
				}
				
				
				//将属性设置到新类中
				try{
					field.setAccessible(true);
					field.set(null, fieldValue);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					printLog(field.getName()+"set static filed failed"+"  ! in method of transferClassAndObjectState");				
				}
			}
			else  //不是静态属性，则将属性的值转换到新的对象上
			{
				//获取该属性的值
				Object fieldValue = null;
				try{
					Field oldField = oldClass.getDeclaredField(field.getName());
					if(oldField!=null&&!Modifier.isStatic(oldField.getModifiers()))
					{
						oldField.setAccessible(true);
						fieldValue=oldField.get(null);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					printLog(field.getName()+"get object filed failed"+"  ! in method of transferClassAndObjectState");
				}
				
				//将属性设置到新类中
				try{
					field.setAccessible(true);
					field.set(resObj, fieldValue);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					printLog(field.getName()+"set object field failed"+"  ! in method of transferClassAndObjectState");				
				}
			}
		}
		
		return resObj;
		
	}
	
	//TODO 这里遇到一个问题，适合性检查如果是检查当前粒的所有父粒是不是都是true的话，那么如果遇到中间粒为false，将导致粒树需要替换这个粒所有的子粒，
	//     使得替换对象时，需要更新所有粒对应的对象，这里存在不她合理的问题,所以当前情况下，我只检查了当前粒的状态
	//检查一个粒的适合性
	public static boolean fitnessChecking(String gName)
	{
//		printLog("in fitnessChecking");
		//得到粒的父粒，挨个判断是否满足适合性条件
		ArrayList<String> gParents = GranuleTree.getGranuleParents(gName, "root");
//		ArrayList<String> gParents = new ArrayList<String>();

		//将当前粒也添加到列表中，如果父粒都执行成功，则检测当前粒是否符合要求
		gParents.add(gName);
	
		//依次遍历粒树，挨个执行fitness函数
		for(int i=0;i<gParents.size();++i)
		{
			//将粒load进来

			String graName = gParents.get(i);
/*
			Class granule=loadedGranules.get(graName);
			if(granule==null){
				granule = GranuleLoader.loadGranule(graName);
				loadedGranules.put(graName, granule);
			}
//			printLog("granule name:"+granule.getName());
			
			if(!executeGranule(granule))
			{
				lastGranules = new ArrayList<String>(gParents.subList(i, gParents.size()));
				
//				System.out.println("final result of "+gName+": false");
				return false;
			}
*/
			Method fitMethod = fitnessMethods.get(graName);
			if(fitMethod==null)
			{
				Class granule = GranuleLoader.loadGranule(graName);
				loadedGranules.put(graName, granule);
				
				try{
					fitMethod=granule.getDeclaredMethod("fitness");
				}
				catch(Exception e)
				{
					printLog("get fitness method failed！！");
					e.printStackTrace();
				}
				try{
					//先将fitness方法的访问权限去掉，然后再执行
					fitMethod.setAccessible(true);
					fitnessMethods.put(graName, fitMethod);
//					printLog("fitness result of "+granule.getName()+": "+result);
				}
				catch(Exception e)
				{
					printLog("execute fitness method failed!");
					e.printStackTrace();
				}
			}
			if(!executeFitness(fitMethod))
			{
				lastGranules = new ArrayList<String>(gParents.subList(i, gParents.size()));
				
//				System.out.println("final result of "+gName+": false");
				return false;
			}
		}		
		
//		System.out.println("final result of "+gName+": true");
		return true;
	}
	
	public static boolean executeGranule(Class granule)
	{
		
////		printLog("granule name is: "+granule.getName());
		if(granule==null)
		{
			return false;
		}
		
		Boolean result=false;
		//获取fitness方法
		Method fitMethod = null;
		try{
			fitMethod=granule.getDeclaredMethod("fitness");
		}
		catch(Exception e)
		{
			printLog("get fitness method failed！！");
			e.printStackTrace();
			return false;
		}
		try{
			//先将fitness方法的访问权限去掉，然后再执行
			fitMethod.setAccessible(true);
			result=(Boolean)fitMethod.invoke(null, null);
//			printLog("fitness result of "+granule.getName()+": "+result);
		}
		catch(Exception e)
		{
			printLog("execute fitness method failed!");
			e.printStackTrace();
			return false;
		}
		
//		System.out.println(granule.getName()+": "+result);
		
		return result;
	}
	
	public static void loadAllGranules(ArrayList<String> granules)
	{

		for(String graName:granules)
		{
			if(graName.equals("g0"))
				continue;
			Class granule=loadedGranules.get(graName);
			if(granule==null){
				System.out.println("已经加载："+graName);
				granule = GranuleLoader.loadGranule(graName);
				loadedGranules.put(graName, granule);
				
				//获取fitness方法
				Method fitMethod = null;
				try{
					fitMethod=granule.getDeclaredMethod("fitness");
				}
				catch(Exception e)
				{
					printLog("get fitness method failed！！");
					e.printStackTrace();
				}
				try{
					//先将fitness方法的访问权限去掉，然后再执行
					fitMethod.setAccessible(true);
					fitnessMethods.put(graName, fitMethod);
//					printLog("fitness result of "+granule.getName()+": "+result);
				}
				catch(Exception e)
				{
					printLog("execute fitness method failed!");
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static boolean executeFitness(Method fitMethod)
	{
		try{
			return (Boolean)fitMethod.invoke(null, null);
//			printLog("fitness result of "+granule.getName()+": "+result);
		}
		catch(Exception e)
		{
			printLog("execute fitness method failed!");
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isSafePoint(String className)
	{
		return true;
	}
	
	//根据类名获取粒的名字
	public static String getGranuleName(String className)
	{
		String gName = "";
//		printLog("className: "+className);
		
		//TODO 这里以的标记可能会有问题，因为下划线在函数中是常用的符号，可能会混淆
		int start = className.lastIndexOf("/\\");
		int end = className.lastIndexOf("_");
		if(start>=end)
		{
//			printLog("className： "+className+" get granules failed!!");
			gName=null;
		}
		else
			gName=className.substring(start+1, end-1);
		 
		return gName;
	} 
	
	public static void test()
	{
		Math.exp(10);
	}
	
	//打印log
	public static void printLog(String info)
	{
		System.out.println(info);
	}
	
	public static void main(String args[])
	{
		ArrayList array = new ArrayList();
		array.add("3");
		//Array.get(index);
	}

}
