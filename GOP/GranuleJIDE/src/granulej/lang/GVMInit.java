package granulej.lang;

import java.lang.reflect.*;
import java.io.File;
import java.io.FilenameFilter;


public class GVMInit {

	private String curDir;

	private String g_tree = "TestGranuleTree.xml";

	private String g_path = "GranuleSimiPath.xml";
	
	private ContextUpdate con_up=null;
	
    private static GVMInit gvm=null;
	
    public static GVMInit getInstance(){
       if(gvm==null){
    	 synchronized(GVMInit.class){
    		if(gvm==null)
    		  gvm=new GVMInit();
    	 } 
       }
       return gvm;
    } 	

	private GVMInit() {
		curDir = IndividualInfo.getInstance().getWorkDirectory();	
	}

	public static boolean vmStart() {
	    GVMInit.getInstance();
	    boolean flag=false;
	    
	    //预加载publish类
		String className = gvm.lookupPublishClass();
		if (className != null) {
			className = className.substring(0, className.length() - 6);
			FileSystemClassLoader fscl1 = new FileSystemClassLoader(gvm.curDir);
			try {
				Class<?> class1 = fscl1.loadClass(className);
				Object obj1 = class1.newInstance();
				Method pubMethod = class1.getDeclaredMethod("gg");
				pubMethod.setAccessible(true);
				pubMethod.invoke(obj1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("publish class does not exist!");
		}
		
		//预加载模式类		
		String modeName = gvm.lookupFitnessModeClass();
		if (modeName != null) {
			modeName = modeName.substring(0, modeName.length() - 6);
			FileSystemClassLoader fscl2 = new FileSystemClassLoader(gvm.curDir);
			try {
				Class<?> class2 = fscl2.loadClass(modeName);
                Field proField = class2.getDeclaredField("fit");
                proField.setAccessible(true);
                int res=(Integer)proField.get(class2);
                flag=res > 0? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Mode class does not exist!");
		}
		
		try {			
			//将上下文发送服务器
			gvm.ContextInitialization();		
			
			//預加載各種表
			String xmlFile = gvm.getGranuleTreeFile();
			XmlParser xpar = XmlParser.getInstance();
		    String md5=xpar.readXML(xmlFile);
			GranuleTree.getInstance();
			IndividualInfo.getInstance().setConfigfile(xmlFile);
			IndividualInfo.getInstance().setMd5(md5);
			
			//初始化时加载所有粒
			InvokeAgent.loadAllGranules(GranuleTree.getGranuleList());
			
			//发起子线程进行相似粒的注册		
//			new Thread(new GranuleRegister(md5, gvm.curDir, gvm.g_tree, gvm.g_path)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	public String getGranuleTreeFile() {
		return curDir + g_tree;
	}
    
	public String lookupPublishClass() {
		String pubname = null;
		final String suffix="$publish$.class";
		File dir = new File(curDir);
		if(dir.exists()){
			File[] files=dir.listFiles(
		     new FilenameFilter(){
		    	 public boolean accept(File dir, String name){
		    		 return (name.endsWith(suffix));
		    	 }		    	 
		     });
		    if(files.length>=1){
		    	pubname=files[0].getName();
		    }
		    else{
		    	 System.err.println("we cant find the publish file !");		    	 
		    }
		}
		else 
		{
			System.err.println("we can't find the path !"+dir);
		}
		return pubname;	
	}	

    public String lookupFitnessModeClass(){
    	String modeclass=null;
    	File dir=new File(curDir);
    	String[] files=dir.list();
    	int i=files.length;
    	while (i > 0) {
			if (files[i - 1].equals("fit$fit.class")) {
				modeclass = files[i - 1];
				break;
			}
			i--;
		}
    	return modeclass;   	
    }
	
	public void ContextInitialization()
	{		
		ContextPublish cp=new ContextPublish(GopContext.getContextSequence());
		cp.sendThread();
		cp.run();
	}
	
	public void ContextTearDown()
	{
		con_up.shutDown();
		TaskExecutor.getInstance().close();
        new Thread(new xmlFileUpdate(IndividualInfo.getInstance().getConfigfile(),"cTree")).start();
	}

	public ContextUpdate getContextUp() {
		return con_up;
	}

	public void setContextUp(ContextUpdate con_up) {
		this.con_up = con_up;
	}
	
	public static void main(String[] args){		
		//vmStart();
		//GVMInit.getInstance().ContextTearDown();
		//GVMInit.getInstance().doWork();
	}
	

}