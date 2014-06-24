package granulej.lang;
import javassist.*;
import java.io.IOException;
public class IFieldContainerGenerator {
  private final String inter_name="IFieldContainer";
  /*public static void main(String args[])
  {
 	      IFieldContainerGenerator icg=new IFieldContainerGenerator();
	      icg.addContainerInterface();
	      CtClass clas=null;
	      try{
	    	  clas=ClassPool.getDefault().get(args[0]);	
              clas.getDeclaredField("cont"); 
              System.out.println("The field cont is already defined in the class!");
	    	}catch(NotFoundException ex){
	    	 try{
	          CtField f=CtField.make("public IFieldContainer cont;",clas);
		      clas.addField(f);
		      clas.writeFile();
		      clas.detach(); 		     
	    	}catch(NotFoundException e){
	    	 e.printStackTrace();
	    	}catch(CannotCompileException e){
	    	  e.printStackTrace();
	    	}catch(IOException e)
	    	{
	    	  e.printStackTrace();
	    	}
	    	}
  }*/
  public void addContainerFields(){
	  try{
		  CtClass clas=ClassPool.getDefault().get("Test");
		  clas.writeFile();
		  
	  }catch(CannotCompileException ex){
		  ex.printStackTrace();
	  }catch(NotFoundException ex){
		  ex.printStackTrace();
	  }catch(IOException ex){
		 ex.printStackTrace();
	  }  
  }
 
  public void addContainerInterface(){
	  try{
	  ClassPool pool=ClassPool.getDefault();
	  CtClass excc=pool.getCtClass(inter_name);
	  if(excc==null){
	  CtClass cc=pool.makeInterface(inter_name);
	  cc.writeFile();
	  }
	  else{
	   System.out.println("Interface is already added !");
	  }
	  }catch(CannotCompileException ex){
		  ex.printStackTrace();
	  }catch(NotFoundException ex){
		  ex.printStackTrace();
	  }catch(IOException ex){
		 ex.printStackTrace();
	  }	  
  }	
}
