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
public class ConstantPool extends java.lang.Object {

    public TypeDecl typeDecl;


    public ConstantPool(TypeDecl typeDecl) {
      this.typeDecl = typeDecl;
      //if(!typeDecl.isFinal)
      //  System.out.println("Warning: trying to create constant pool for non final type decl " + typeDecl.fullName());
    }


    
    public static final byte CONSTANT_Class              = 7;


    public static final byte CONSTANT_Fieldref           = 9;


    public static final byte CONSTANT_Methodref          = 10;


    public static final byte CONSTANT_InterfaceMethodref = 11;


    public static final byte CONSTANT_String             = 8;


    public static final byte CONSTANT_Integer            = 3;


    public static final byte CONSTANT_Float              = 4;


    public static final byte CONSTANT_Long               = 5;


    public static final byte CONSTANT_Double             = 6;


    public static final byte CONSTANT_NameAndType        = 12;


    public static final byte CONSTANT_Utf8               = 1;


 
    private int posCounter = 1;


 
    private ArrayList list = new ArrayList();


    private void addCPInfo(CPInfo info) {
      list.add(info);
    }


 
    // for debugging purposes
    public int numElements() {
      return list.size();
    }


    public String toString() {
      StringBuffer s = new StringBuffer();
      for(Iterator iter = list.iterator(); iter.hasNext(); ) {
        s.append(iter.next().toString());
        s.append("\n");
      }
      return s.toString();
    }


 
    public void emit(DataOutputStream out) throws IOException {
      out.writeChar(posCounter);
      for(Iterator iter = list.iterator(); iter.hasNext(); ) {
        CPInfo info = (CPInfo)iter.next();
        info.emit(out);
      }
    }



    private int labelCounter = 1;


    public int newLabel() {
      return labelCounter++;
    }


 
    private HashMap classConstants = new HashMap();


    public int addClass(String name) {
      Map map = classConstants;
      Object key = name;
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantClass(addUtf8(name.replace('.', '/')));
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap fieldrefConstants = new HashMap();


    public int addFieldref(String classname, String name, String type) {
      Map map = fieldrefConstants;
      Object key = classname + name + type;     
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantFieldref(addClass(classname), addNameAndType(name, type));
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap methodrefConstants = new HashMap();


    public int addMethodref(String classname, String name, String desc) {
      Map map = methodrefConstants;
      Object key = classname + name + desc;
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantMethodref(addClass(classname), addNameAndType(name, desc));
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap interfaceMethodrefConstants = new HashMap();


    public int addInterfaceMethodref(String classname, String name, String desc) {
      Map map = interfaceMethodrefConstants;
      Object key = classname + name + desc;
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantInterfaceMethodref(addClass(classname), addNameAndType(name, desc));
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap nameAndTypeConstants = new HashMap();


    public int addNameAndType(String name, String type) {
      Map map = nameAndTypeConstants;
      Object key = name + type;
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantNameAndType(addUtf8(name), addUtf8(type));
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap utf8Constants = new HashMap();


    public int addUtf8(String name) {
      Map map = utf8Constants;
      Object key = name;
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantUtf8(name);
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap stringConstants = new HashMap();


    public int addConstant(String val) {
      Map map = stringConstants;
      Object key = val;
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantString(addUtf8(val));
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap intConstants = new HashMap();


    public int addConstant(int val) {
      Map map = intConstants;
      Object key = new Integer(val);
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantInteger(val);
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap floatConstants = new HashMap();


    public int addConstant(float val) {
      Map map = floatConstants;
      Object key = new Float(val);
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantFloat(val);
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap longConstants = new HashMap();


    public int addConstant(long val) {
      Map map = longConstants;
      Object key = new Long(val);
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantLong(val);
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


 
    private HashMap doubleConstants = new HashMap();


    public int addConstant(double val) {
      Map map = doubleConstants;
      Object key = new Double(val);
      if(!map.containsKey(key)) {
        CPInfo info = new ConstantDouble(val);
        info.pos = posCounter; posCounter += info.size();
        addCPInfo(info);
        map.put(key, info);
        return info.pos;
      }
      CPInfo info = (CPInfo)map.get(key);
      return info.pos;
    }


}
