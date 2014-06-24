package AST;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.io.File;
import java.util.*;
import beaver.*;
import java.util.ArrayList;
import java.util.zip.*;
import java.io.*;
import java.util.Stack;
import java.util.regex.Pattern;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Collection;

/**
 * @ast class
 * @declaredat :0
 */
public class Frontend extends java.lang.Object {

    protected Program program;



    protected Frontend() {
      program = new Program();
      program.state().reset();
    }



    public boolean process(String[] args, BytecodeReader reader, JavaParser parser) {
      program.initBytecodeReader(reader);
      program.initJavaParser(parser);

      initOptions();
      processArgs(args);

      Collection files = program.options().files();
      if(program.options().hasOption("-version")) {
        printVersion();
        return false;
      }
      if(program.options().hasOption("-help") || files.isEmpty()) {
        printUsage();
        return false;
      }

      try {
        for(Iterator iter = files.iterator(); iter.hasNext(); ) {
          String name = (String)iter.next();
          if(!new File(name).exists())
            System.out.println("WARNING: file \"" + name + "\" does not exist");
          program.addSourceFile(name);
        }

        for(Iterator iter = program.compilationUnitIterator(); iter.hasNext(); ) {
          CompilationUnit unit = (CompilationUnit)iter.next();
          if(unit.fromSource()) {
            Collection errors = unit.parseErrors();
            Collection warnings = new LinkedList();
            // compute static semantic errors when there are no parse errors or 
            // the recover from parse errors option is specified
            if(errors.isEmpty() || program.options().hasOption("-recover"))
              unit.errorCheck(errors, warnings);
            if(!errors.isEmpty()) {
              processErrors(errors, unit);
              return false;
            }
            else {
              processWarnings(warnings, unit);
              processNoErrors(unit);
            }
          }
        }
      } catch (Exception e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
      }
      return true;
    }



    protected void initOptions() {
      Options options = program.options();
      options.initOptions();
      options.addKeyOption("-version");
      options.addKeyOption("-print");
      options.addKeyOption("-g");
      options.addKeyOption("-g:none");
      options.addKeyOption("-g:lines,vars,source");
      options.addKeyOption("-nowarn");
      options.addKeyOption("-verbose");
      options.addKeyOption("-deprecation");
      options.addKeyValueOption("-classpath");
      options.addKeyValueOption("-sourcepath");
      options.addKeyValueOption("-bootclasspath");
      options.addKeyValueOption("-extdirs");
      options.addKeyValueOption("-d");
      options.addKeyValueOption("-encoding");
      options.addKeyValueOption("-source");
      options.addKeyValueOption("-target");
      options.addKeyOption("-help");
      options.addKeyOption("-O");
      options.addKeyOption("-J-Xmx128M");
      options.addKeyOption("-recover");
    }


    protected void processArgs(String[] args) {
      program.options().addOptions(args);
    }



    protected void processErrors(Collection errors, CompilationUnit unit) {
      System.out.println("Errors:");
      for(Iterator iter2 = errors.iterator(); iter2.hasNext(); ) {
        System.out.println(iter2.next());
      }
    }


    protected void processWarnings(Collection warnings, CompilationUnit unit) {
      for(Iterator iter2 = warnings.iterator(); iter2.hasNext(); ) {
        System.out.println(iter2.next());
      }
    }


    protected void processNoErrors(CompilationUnit unit) {
    }



    protected void printUsage() {
      printVersion();
      System.out.println(
          "\n" + name() + "\n\n" +
          "Usage: java " + name() + " <options> <source files>\n" +
          "  -verbose                  Output messages about what the compiler is doing\n" +
          "  -classpath <path>         Specify where to find user class files\n" +
          "  -sourcepath <path>        Specify where to find input source files\n" + 
          "  -bootclasspath <path>     Override location of bootstrap class files\n" + 
          "  -extdirs <dirs>           Override location of installed extensions\n" +
          "  -d <directory>            Specify where to place generated class files\n" +
          "  -help                     Print a synopsis of standard options\n" +
          "  -version                  Print version information\n"
          );
    }



    protected void printVersion() {
      System.out.println(name() + " " + url() + " Version " + version());
    }



    protected String name() {
      return "Java1.4Frontend";
    }


    protected String url() {
      return "(http://jastadd.cs.lth.se)";
    }



    protected String version() {
      return "R20070504";
    }


}
