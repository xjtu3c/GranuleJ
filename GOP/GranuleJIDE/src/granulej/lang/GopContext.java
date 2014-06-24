package granulej.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GopContext {
	private static HashMap<String, Context> contexts=new HashMap<String, Context>();
	private static ArrayList<String> con_list=new ArrayList<String>();
	private static String p_str="";
	
	public static synchronized HashMap<String, Context> getContexts() {
		return contexts;
	}

	public static void setContexts(HashMap<String, Context> contexts) {
		GopContext.contexts = contexts;
	}

	public static String getContextSequence() {
		addContexts();
		return p_str;
	}
	public static void setContextSequence(String p_str) {
		GopContext.p_str = p_str;
	}
	public static synchronized String getContext(String name,String modifiers) {      	
		   if(contexts.containsKey(name)){
		     Context cn=contexts.get(name);
		     if(cn!=null && cn.getValue()!=null){
		    	 return cn.getValue().trim();
		     }
		     return "";			   
		   }
		   else 
			 return "";
	}
	
	public static synchronized void addContexts(){
		 StringBuffer pstr=new StringBuffer();
         int len=con_list.size();
		 Iterator<String> iter = con_list.iterator();
		 int i=0;
		 while(iter.hasNext()){
		  i++;
		  String str = (String) iter.next();
		  pstr.append(str);
		  if(i!=len)
		  pstr.append(";");
		 }
		 p_str=pstr.toString();
		 System.out.println("Context variable is published !");
	}
	public static synchronized void addContext(String name,String value)
	{
	   if(!contexts.containsKey(name))
	   {  
		  Context cn=new Context(name,value);
		  contexts.put(name,cn);		  
	   }		
	}
	
	public static synchronized void addContext(String name, String value, String modifiers) {
		    String p_str=name+":"+value+":"+modifiers;
		    if(!con_list.contains(p_str))
		    con_list.add(p_str);
		    Context cn=new Context(name,value,modifiers);
		    if(!contexts.containsKey(name))
		    contexts.put(name,cn);
		    //System.out.println("context variable is added!");		
	}
	
	public static synchronized void modifyContext(String name,String newValue)
	{ 
	    if(contexts.containsKey(name)){
		 Context ct=(Context)contexts.get(name);
		 if("".equals(newValue))
         newValue=null;
		 ct.setValue(newValue);
		}		
	}
	
	public static synchronized void modifyContext(String name, String newValue,String modifiers) {
          if(contexts.containsKey(name))
          {
        	 Context ct=(Context)contexts.get(name);        	 
        	 if(modifiers!=null && !modifiers.equals("") && !modifiers.equals(ct.getModifiers()))
             ct.setModifiers(modifiers);
        	 //修改值
        	 if(newValue==null && newValue==ct.getValue())
        	 return ;       		 
        	 if(newValue!=null && ct.getValue()!=null && newValue.equals(ct.getValue()))
        	 return ;
        	 if(newValue==null && ct.getValue()!=null || newValue!=null && ct.getValue()==null || newValue!=null && ct.getValue()!=null && newValue.equals(ct.getValue())){
                if("".equals(newValue))
                  newValue=null;        		 
                ct.setValue(newValue);
               //new Thread(new ContextModify(name+":"+newValue)).start();
                new ContextModify(name+":"+newValue).run();
        	 }        	       	
          }
	}
}