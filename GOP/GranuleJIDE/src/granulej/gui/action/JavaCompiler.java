package granulej.gui.action;
import granulej.error.CompilerException;
import granulej.gui.Console;
import AST.BytecodeParser;
import AST.CompilationUnit;
import AST.Frontend;
import AST.JavaParser;


public class JavaCompiler extends Frontend {
  public static void main(String args[]) {
	  try{
		  if(!compile(args))
		     System.exit(1);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public static boolean compile(String args[]) throws CompilerException{    
	  return new JavaCompiler().process(
        args,
        new BytecodeParser(),
        new JavaParser() {
          public CompilationUnit parse(java.io.InputStream is, String fileName) throws java.io.IOException, beaver.Parser.Exception {
            return new parser.JavaParser().parse(is, fileName);
          }
        }
    );
	  
  }  
  protected void processNoErrors(CompilationUnit unit) {
	     unit.transformation();
	     unit.generateClassfile();
	    if(program.options().hasOption("-print"))
	    	Console.getInstance().append(unit+"\n");
	    if(!program.hasProduced()){
	    program.generateAuxiliaryClassFile();
	    program.generateClassNameXmlfile();
	    //program.MethodMapShadowClassesXmlfile();
	    program.generateGranuleTreeXmlfile();
	    program.generateGranulePathXmlfile();
	   }
  }


  protected String name() { return "Java1.4Frontend + Backend"; }
  protected String version() { return "R20071015"; }
}