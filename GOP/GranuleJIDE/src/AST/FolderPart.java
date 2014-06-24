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
public class FolderPart extends PathPart {

    private HashMap map = new HashMap();


    private File folder;



    public FolderPart(File folder) {
      this.folder = folder;
    }



    public boolean hasPackage(String name) {
      return filesInPackage(name) != null;
    }



    public boolean hasCompilationUnit(String canonicalName) {
      int index = canonicalName.lastIndexOf('.');
      String packageName = index == -1 ? "" : canonicalName.substring(0, index);
      String typeName = canonicalName.substring(index + 1, canonicalName.length());
      Collection c = filesInPackage(packageName);
      boolean result = c != null && c.contains(typeName + fileSuffix());
      return result;
    }


    
    private Collection filesInPackage(String packageName) {
      if(!map.containsKey(packageName)) {
        File f = new File(folder, packageName.replace('.', File.separatorChar));
        Collection c = Collections.EMPTY_LIST;
        if(f.exists() && f.isDirectory()) {
          String[] files = f.list();
          if(files.length > 0) {
            c = new HashSet();
            for(int i = 0; i < files.length; i++)
              c.add(files[i]);
          }
        }
        else
          c = null;
        map.put(packageName, c);
      }
      return (Collection)map.get(packageName);
    }


    
    public boolean selectCompilationUnit(String canonicalName) throws IOException {
      if(hasCompilationUnit(canonicalName)) {
        String fileName = canonicalName.replace('.', File.separatorChar);
        File classFile = new File(folder, fileName + fileSuffix());
        if(classFile.isFile()) {
          is = new FileInputStream(classFile);
          age = classFile.lastModified();
          pathName = classFile.getAbsolutePath();
          relativeName = fileName + fileSuffix();
          fullName = canonicalName;
          return true;
        }
      }
      return false;
    }


}
