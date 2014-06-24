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
public class ClassNode extends java.lang.Object {
 String classname;

 ClassDecl classdecl;

 ArrayList<ClassNode> children;

 ClassNode(String classname,ClassDecl classdecl){  
        this.classname=classname;  
        this.classdecl=classdecl;
        children=new ArrayList<ClassNode>();  
     }

public  String getClassname() {  
    return classname;  
  }

public  void setClassname(String classname) {  
    this.classname=classname;  
   }

public  ClassDecl getClassdecl()
    {
    	return classdecl;
    }

public  void setClassdecl(ClassDecl classdecl)
    {
    	this.classdecl=classdecl;
    }

public  ArrayList<ClassNode> getChildren(){
        if (this.children == null) {
            return new ArrayList<ClassNode>();
        }
        return this.children;
    }

public  void setChildren(ArrayList<ClassNode> children) {
        this.children = children;
    }

public  ArrayList<ClassNode> getOrderChildren()
    {
        ArrayList<ClassNode> childrens=getChildren();
        if(getNumberOfChildren()>1)
        childrens=Sort(childrens);
        return childrens;    	
    }

public  int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }

public  void addChild(ClassNode child) {
        if (children == null) {
            children = new ArrayList<ClassNode>();
        }
        children.add(child);
    }

public  void insertChildAt(int index, ClassNode child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            addChild(child);
            return;
        } else {
            children.get(index); 
            children.add(index, child);
        }
    }

public  void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

public  ArrayList<ClassNode> Sort(ArrayList<ClassNode> list)
    {
      int len=list.size(); 
      int flag, size=len;
      ClassNode c1=null,c2=null;
      for(int i=0;i<len;i++){       
       for(int j=0;j<len-1;j++){
    	c1=(ClassNode)list.get(j);
    	c2=(ClassNode)list.get(j+1);
    	flag=c1.getClassname().compareToIgnoreCase(c2.getClassname());
    	if(flag>=0){
          list.set(j,c2);
          list.set(j+1,c1);
    	}
       }
       size=size-1;  
      }
      return list;
    }


}
