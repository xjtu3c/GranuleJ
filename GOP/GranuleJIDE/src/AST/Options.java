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
public class Options extends java.lang.Object {
 class Option {
    public String name;
    public boolean hasValue;
    public boolean isCollection;
    public Option(String name, boolean hasValue, boolean isCollection) {
      this.name = name;
      this.hasValue = hasValue;
      this.isCollection = isCollection;
    }
  }

 Pattern SOURCE_FILE_EXT_PAT = Pattern.compile(".*\\.java$", Pattern.CASE_INSENSITIVE);

 Map options = new HashMap();

 Map optionDescriptions = new HashMap();

 HashSet files = new HashSet();

public  Collection files() {
    return files;
  }

public  void initOptions() {
    options = new HashMap();
    optionDescriptions = new HashMap();
    files = new HashSet();
  }

public  void addKeyOption(String name) {
    if(optionDescriptions.containsKey(name))
      throw new Error("Command line definition error: option description for " + name + " is multiply declared");
    optionDescriptions.put(name, new Option(name, false, false));
  }

public  void addKeyValueOption(String name) {
    if(optionDescriptions.containsKey(name))
      throw new Error("Command line definition error: option description for " + name + " is multiply declared");
    optionDescriptions.put(name, new Option(name, true, false));
  }

public  void addKeyCollectionOption(String name) {
    if(optionDescriptions.containsKey(name))
      throw new Error("Command line definition error: option description for " + name + " is multiply declared");
    optionDescriptions.put(name, new Option(name, true, true));
  }

public  void addOptionDescription(String name, boolean value) {
    if(optionDescriptions.containsKey(name))
      throw new Error("Command line definition error: option description for " + name + " is multiply declared");
    optionDescriptions.put(name, new Option(name, value, false));
  }

public  void addOptionDescription(String name, boolean value, boolean isCollection) {
    if(optionDescriptions.containsKey(name))
      throw new Error("Command line definition error: option description for " + name + " is multiply declared");
    optionDescriptions.put(name, new Option(name, value, isCollection));
  }

public  void addOptions(String[] args) {
    for(int i = 0; i < args.length; i++) {
      String arg = args[i];
      if(arg.startsWith("@")) {
        try {
          String fileName = arg.substring(1,arg.length());
          java.io.StreamTokenizer tokenizer = new java.io.StreamTokenizer(new java.io.FileReader(fileName));
          tokenizer.resetSyntax();
          tokenizer.whitespaceChars(' ',' ');
          tokenizer.whitespaceChars('\t','\t');
          tokenizer.whitespaceChars('\f','\f');
          tokenizer.whitespaceChars('\n','\n');
          tokenizer.whitespaceChars('\r','\r');
          tokenizer.wordChars(33,255);
          ArrayList list = new ArrayList();
          int next = tokenizer.nextToken();
          while(next != java.io.StreamTokenizer.TT_EOF) {
            if(next == java.io.StreamTokenizer.TT_WORD) {
              list.add(tokenizer.sval);
            }
            next = tokenizer.nextToken();
          }
          String[] newArgs = new String[list.size()];
          int index = 0;
          for(Iterator iter = list.iterator(); iter.hasNext(); index++) {
            newArgs[index] = (String)iter.next();
          }
          addOptions(newArgs);
        } catch (java.io.IOException e) {
        }
      }
      else if(arg.startsWith("-")) {
        if(!optionDescriptions.containsKey(arg))
          throw new Error("Command line argument error: option " + arg + " is not defined");
        Option o = (Option)optionDescriptions.get(arg);
        
        if(!o.isCollection && options.containsKey(arg))
          throw new Error("Command line argument error: option " + arg + " is multiply defined");
        
        if(o.hasValue && !o.isCollection) {
          String value = null;
          if(i + 1 > args.length - 1)
            throw new Error("Command line argument error: value missing for key " + arg);
          value = args[i+1];
          if(value.startsWith("-"))
            throw new Error("Command line argument error: value missing for key " + arg);
          i++;
          options.put(arg, value);
        }
        else if(o.hasValue && o.isCollection) {
          String value = null;
          if(i + 1 > args.length - 1)
            throw new Error("Command line argument error: value missing for key " + arg);
          value = args[i+1];
          if(value.startsWith("-"))
            throw new Error("Command line argument error: value missing for key " + arg);
          i++;
          Collection c = (Collection)options.get(arg);
          if(c == null)
            c = new ArrayList();
          c.add(value);
          options.put(arg, c);
        }
        else {
          options.put(arg, null);
        }
      }
      else {
//======================GOP=================================
      File fname=new File(arg);
      if(fname.isDirectory()){
      Stack<File> dirs = new Stack<File>();  
      dirs.push(fname);
      while (dirs.size() > 0) {
	    for (File file : dirs.pop().listFiles()) {
	       if (file.isDirectory()) {
	       dirs.push(file);
	        } else if (SOURCE_FILE_EXT_PAT.matcher(file.getName()).matches()) {
	               String filePathname = file.getPath();
                   files.add(filePathname);
	        }  
	      } 
         }
       }
       else 
//======================END=================================
        files.add(arg);
      }
    }
  }

public  boolean hasOption(String name) {
    return options.containsKey(name);
  }

public  void setOption(String name) {
    options.put(name, null);
  }

public  boolean hasValueForOption(String name) {
    return options.containsKey(name) && options.get(name) != null;
  }

public  String getValueForOption(String name) {
    if(!hasValueForOption(name))
      throw new Error("Command line argument error: key " + name + " does not have a value");
    return (String)options.get(name);
  }

public  void setValueForOption(String value, String option) {
    options.put(option, value);
  }

public  Collection getValueCollectionForOption(String name) {
    if(!hasValueForOption(name))
      throw new Error("Command line argument error: key " + name + " does not have a value");
    return (Collection)options.get(name);
  }

public  boolean verbose() {
    return hasOption("-verbose");
  }


}
