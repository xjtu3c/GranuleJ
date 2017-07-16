package granulej.gui.action;

import granulej.gui.Console;
import granulej.gui.IndividualView;
import granulej.gui.MainFrame;
import granulej.gui.ProgramView;
import granulej.gui.datastructure.NodeData;
import granulej.gui.datastructure.NodeType;
import granulej.lang.FileSystemClassLoader;
import granulej.util.OpertionSystem;
import granulej.util.xmlUtil;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import config.GUIConfig;

public class RunAction implements ActionListener {
	private GUIConfig config;
	private Console console;
	private LineNumberReader input;
	private LineNumberReader inputerr;
	private boolean isStop1 = false;
	private boolean isStop2 = false;		
	private Process process;
	private MainFrame main;
	private boolean flag=false;
	private static String commandline;
	
	public static boolean isStandard=false;
	
	public RunAction(MainFrame m, GUIConfig c, Console con) {
		config = c;
		console = con;
		main = m;	
	}
	

	public static void main(String[] args) {
		
	}

	class Dumpoutput implements Runnable{

		public void run() {
			String line;
			try {
				while ((line = input.readLine()) != null) {
//					console.setOutput(console.getText() + line + "\n");
					Console.getInstance().append(line + "\n");
					//process.getOutputStream().flush();
				}
				isStop1 = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class Dumperr implements Runnable{

		public void run() {
			String line = "";
			String result = "";
			try {
				while ((line = inputerr.readLine()) != null) {
					result = result + line + "\n";
				}
				
				Console.getInstance().append(result);
				isStop2 = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class CheckStatus implements Runnable{
		public void run() {
				while (isStop1 == false || isStop2 == false)
					;
                updateJTree();
				//JOptionPane.showMessageDialog(null, "Application execution is completed !","Message",1);
				main.stop();
			return;
		}
		
	}
	
	public void runShell(String shStr) {
		try { 
			if(OpertionSystem.getSystemState().equals("windows")){
            process = Runtime.getRuntime().exec(
					new String[] { "cmd.exe ","/c",shStr}, null, null);
			}
			else{
			process = Runtime.getRuntime().exec(
				    new String[] { "/bin/sh","-c",shStr}, null, null);	
				
			}
			InputStreamReader ierr = new InputStreamReader(
					process.getErrorStream());
			inputerr = new LineNumberReader(ierr);

			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			
			input = new LineNumberReader(ir);
			Dumpoutput d1 = new Dumpoutput();
			Thread t1 = new Thread(d1);
			t1.start();
			
			Dumperr d2 = new Dumperr();
			Thread t2 = new Thread(d2);
			t2.start();

			CheckStatus ck = new CheckStatus();
			Thread t3 = new Thread(ck);
			t3.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getTreeXML(File dirFile) {
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (files[i].getName().endsWith("tree.xml"))
					return files[i].getAbsolutePath();
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				return getTreeXML(files[i]);
			}
		}
		return null;
	}
	public void run() {
		// Execute a command with an argument that contains a space
		String main = config.getMainFile();		
		if (main == null || main.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Please open main class tab before the application is exectued !","Message",1);
			return;
		}

		try {
			String mainAbs = config.fileAndPath.get(main);
			String mainarg = mainAbs
					.substring(config.getProjectSrcPath().length() + 1);
			mainarg = mainarg.substring(0, mainarg.length() - 5);
			mainarg = mainarg.replace('\\', '.');
			String path=null,jpath=null,syspath=null;   
           if(!flag){
			 StringBuffer sb=new StringBuffer();
			 String curProj=config.getProjectBinPath();
			 String curDir=System.getProperty("java.class.path");
			 path=System.getProperty("user.dir")+File.separator;
			 if(OpertionSystem.getSystemState().equals("windows")){			 
			 String disk=diskChanged(curDir,curProj);
			 if(disk!=null){
			 sb.append(disk);
			 sb.append(" & ");
			 }
			 sb.append("cd "+ curProj);
			 sb.append(" & ");			
			 jpath=".;"+path+"javassist.jar";
			 syspath=";"+curDir;
			 if(!isStandard){
				 sb.append(" java -agentlib:"+path+"GVM "+" -cp "+jpath+syspath+config.getClasspath()+" "+mainarg);
				 System.out.println("11 if(! isStandard)  :"+path);
			 }
			 else
				 sb.append(" java -cp "+jpath+syspath+config.getClasspath()+" "+mainarg);
			 }
			 else{
				 //the path is not changed which is already absolute
				 jpath=".:"+path+"javassist.jar";
				 syspath=":"+curDir;
				 if(!isStandard){
					 sb.append(" java -agentlib:"+path+"GVM "+" -cp "+jpath+syspath+config.getClasspath()+" "+mainarg);
					 System.out.println(path);
				 }
				 else
					 sb.append(" java -cp "+jpath+syspath+config.getClasspath()+" "+mainarg);
			 }	
			 runShell(sb.toString());	
			 commandline=sb.toString();
			 flag=true;
			 }
			else 
			{
			  runShell(commandline);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    public String diskChanged(String curDir,String curProj)
    {
    	String disk=null;
    	String sysdisk=curDir.substring(0,curDir.indexOf(":")+1);
    	disk=curProj.substring(0,curDir.indexOf(":")+1);
    	if(sysdisk!=null && disk!=null && disk.equals(sysdisk))
    	return null;
    	else
    	return disk;
    } 
    
    
    public void actionPerformed(ActionEvent arg0) {
		/*
		 * if (config.getGvmAddr().trim().length() == 0 ||
		 * config.getIndAddr().trim().length() == 0 ||
		 * config.getConAddr().trim().length() == 0) {
		 * JOptionPane.showMessageDialog(null, "请先配置网络运行环境！"); return; }
		 * LocalGranuleManager lm =
		 * LocalGranuleManager.getLm(config.getIndAddr()); Thread t = new
		 * Thread(lm); t.start(); String os = System.getProperty("os.name"); if
		 * (!os.contains("Linux")) System.out
		 * .println("OS is not Linux, the program will run on server " +
		 * config.getGvmAddr());
		 */
		run();
	}
     public void updateJTree()
     {
    	 String b_path = config.getProjectBinPath() + File.separator;
    	 if(!new File(b_path+"TestGranuleTree1.xml").exists())
    	 {
            return ;				
         }
    	 else 
    	 {
    		HashMap<String, ArrayList<String>> map=update(b_path);    		
    		IndividualView view=ProgramView.getNewContentPane();
    		view.clear();  
    		Iterator iter = map.entrySet().iterator();
    		while (iter.hasNext()) {
    			Map.Entry entry = (Map.Entry) iter.next();
    			String key = (String) entry.getKey();
    			int idx=key.indexOf(")");
    			DefaultMutableTreeNode parent=view.addObject(null,key.substring(0,idx+1));
    			view.addObject(parent,new NodeData(NodeType.FIELDPRI,key.substring(idx+1)),true);
    			view.addObject(parent,"fitness()");
    			view.addObject(parent,"fitnessAction()");
    			view.addObject(parent,"checkFitness()");
    			ArrayList<String> value = (ArrayList<String>)(entry.getValue());
    			String son="";
    			String filePath=config.getProjectBinPath()+File.separator;
    			for (int i = 0,len=value.size(); i < len; i++) {
                  son=(String)value.get(i);
                  DefaultMutableTreeNode sonNode=view.addObject(parent, son.substring(son.lastIndexOf("_")+1));
                  if(!new File(filePath+son+".class").exists())
                  return ;
                  Class s_class=getClassFromBinPath(filePath,son);
                  if(s_class!=null){
                	Field[] fs=s_class.getDeclaredFields();
                    
                	for (Field field : fs) {
                		NodeData nd=null;
            			if (Modifier.isPublic(field.getModifiers())){
            			nd=new NodeData(NodeType.FIELDPUB,field.getName()+":"+field.getType());
            			}
            			else if(Modifier.isProtected(field.getModifiers())){
            			nd=new NodeData(NodeType.FIELDPRO,field.getName()+":"+field.getType());
            			}
            			else{
            			nd=new NodeData(NodeType.FIELDPRI,field.getName()+":"+field.getType());
            			}
            			view.addObject(sonNode,nd,true);
                  }                   
                  Method[] ms=s_class.getDeclaredMethods();
                  for(Method method:ms){
                	 NodeData nda=null;
          			if (Modifier.isPublic(method.getModifiers())){
          			nda=new NodeData(NodeType.METHPUB,method.getName()+getMethodSignature(method));
          			}
          			else if(Modifier.isProtected(method.getModifiers())){
          			nda=new NodeData(NodeType.METHPRO,method.getName()+getMethodSignature(method));
          			}
          			else{
          			nda=new NodeData(NodeType.METHPRI,method.getName()+getMethodSignature(method));
          			}
          			view.addObject(sonNode,nda,true);  
                     }
                    }           	
                  }
    			} 			
    		} 
    	 } 
     
 	public HashMap<String, ArrayList<String>> update(String path){
	      HashMap<String, ArrayList<String>> tree=new HashMap<String, ArrayList<String>>();
	      int num=findMaxNumberFileName(path);
		  Document doc=xmlUtil.load(path+"TestGranuleTree"+num+".xml");
		  Element root=doc.getDocumentElement();
		  traversalGranuleTree(root.getFirstChild(),tree);		  
		  return tree;
		}
		
		public void traversalGranuleTree(Node node,HashMap<String, ArrayList<String>> tree) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
						String name = node.getNodeName();
						if (name.equals("granule")) {
							NamedNodeMap nodeMap = node.getAttributes();
							String gName = nodeMap.getNamedItem("name").getNodeValue();
							String cName="";
							String conName="";
							String key="";
							if (!gName.equals("g0")) {
							  cName=nodeMap.getNamedItem("class").getNodeValue();
							  conName=nodeMap.getNamedItem("context").getNodeValue();
							  key=gName+"("+ cName+ ")"+conName;
							  ArrayList<String> list=new ArrayList<String>();			
							  int length = nodeMap.getLength();
							  for (int i = 0; i < length; i++) {
									String nodeName = nodeMap.item(i).getNodeName();
									if (nodeName.startsWith("shadow_class")){
										 list.add(nodeMap.item(i).getNodeValue());
							     }
	                           }
							   tree.put(key, list);						
							 }
						  }
					}				
				for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
					traversalGranuleTree(child,tree);
				}
			}
		
	      private int findMaxNumberFileName(String path){
			int num=0;
			for(int i=1;i<Integer.MAX_VALUE;i++){
			  if(new File(path+File.separator+"TestGranuleTree"+i+".xml").exists()){
				  num++;
			  }else{
				  break;
			  }
			}
			 return num;	
			}    
     
     public Class<?> getClassFromBinPath(String path, String name)
     {
    	 Class<?> class1 = null;
   		 try {
   		  FileSystemClassLoader newloader = new FileSystemClassLoader(path);
   		  class1 = newloader.loadClass(name);
   		  return class1;
            } catch (ClassNotFoundException e) {
   		  System.err.println("The class from far side is loaded failurely !");
   		 }
    	  return class1;   	 
     } 
     public String getMethodSignature(Method md)
     {
        String temp="(";
        for(int i=0;i<md.getParameterTypes().length; i++)
        {
         temp=temp+md.getParameterTypes()[i].getName(); 
        }
        temp=temp+")";
        return temp;
     }
 
}