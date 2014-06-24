package granulej.lang;
import javassist.*;
import javassist.expr.ExprEditor;
import java.util.*;
public class UpdateShortlivedObject {
    private String oldClassName;
    private String newClassName;
    
	public void updateCaller(CtClass caller)
	{
	  Collection col=caller.getRefClasses();
	  Iterator iter=col.iterator();
	  while(iter.hasNext()){
	  CtClass callee=(CtClass)iter.next();
	  if(callee.getName().compareTo(oldClassName)==0){
	  
		 ClassMap classMap=new ClassMap();
		 classMap.put(oldClassName,newClassName);
		 callee.replaceClassName(classMap);		  
	  }
	  } 
	  try{
	  CtMethod[] methods=caller.getDeclaredMethods();
	  ExprEditor expr=new ExprEditor();
	  for(int i=0;i<methods.length; i++)
	  {
         methods[i].instrument(expr);		  
	  }	
	  } 
	  catch(CannotCompileException e)
	  {
		  
	  }
	  //targetVm.redefineClasses(callerClass);
	}	
}
