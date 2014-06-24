package granulej.lang;
import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;


public class FieldContainerGenerator {
  
   public void addContainerClass(){
	try{
	  ClassPool cp=ClassPool.getDefault();
	  CtClass cc=cp.makeClass("Container");
	  CtClass[] iters=new CtClass[1];
	  iters[0]=cp.get("IFieldContainer");
	  cc.setInterfaces(iters);
	  cc.writeFile();
	  cc.detach();
	  }catch(CannotCompileException ex){
		  ex.printStackTrace();
	  }catch(NotFoundException ex){
		  ex.printStackTrace();
	  }catch(IOException ex){
		 ex.printStackTrace();
	  }  
	}
	
}
