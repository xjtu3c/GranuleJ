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
public class ClassTree extends java.lang.Object {
 ClassNode rootElement;

 ClassTree() {
        rootElement=new ClassNode("Object",null);
    }

public  ClassNode getRootElement() {
        return this.rootElement;
    }

public  void setRootElement(ClassNode rootElement) {
        this.rootElement = rootElement;
    }

public  ArrayList<ClassNode> toList() {
        ArrayList<ClassNode> list = new ArrayList<ClassNode>();
        traversal(getRootElement(), list);
        return list;
    }

public  String toString() {
        return toList().toString();
    }

public  void traversal(ClassNode element, ArrayList<ClassNode> list) {
        list.add(element);
        for(Iterator iter=element.getChildren().iterator();iter.hasNext();){
        	ClassNode cn=(ClassNode)iter.next();           
            traversal(cn, list);
        }
    }

public  ArrayList<ClassNode> orderToList(){
       ArrayList<ClassNode> list=new ArrayList<ClassNode>();
       OrderTraversal(getRootElement(),list);
       return list;    	
    }

public  void OrderTraversal(ClassNode element, ArrayList<ClassNode> list)
    {
       list.add(element);
       ArrayList<ClassNode> children=element.getChildren();
       if(element.getNumberOfChildren()>1)
       children=element.Sort(children);
       for(Iterator iter=children.iterator();iter.hasNext();)
       {
    	   ClassNode cn=(ClassNode)iter.next();
    	   OrderTraversal(cn,list);
       }
    }

public  ArrayList<ClassNode> getOrderChildren(ClassNode element)
    {
    	ArrayList<ClassNode> children=element.getChildren();
        if(element.getNumberOfChildren()>1)
        children=element.Sort(children);
        return children;    	
    }

public  ClassNode findClass(String classname)
    {
    	return find(rootElement,classname);
    }

public  ClassNode find(ClassNode element,String classname)
	{
		if(element.getClassname().equals(classname))
			return element;
		Iterator iter = element.getChildren().iterator();
		while(iter.hasNext()){
			ClassNode temp = (ClassNode)iter.next();
			ClassNode treenode=find(temp,classname);
			if(treenode!=null)
				return treenode;
		}
		return null;
	}

public String getListSequence(){
    	String c_seq="";
    	ArrayList<ClassNode> list=orderToList();
    	for(Iterator iter=list.iterator();iter.hasNext();){
    	  ClassNode cn=(ClassNode)iter.next();
		  String cname=cn.getClassname();
    	  if(!cname.equals("Object")&& !cname.equals("GopContext") && !cname.endsWith("View") && !(cname.startsWith("HK")||cname.startsWith("SH")||cname.startsWith("XA")))
    	  c_seq+=cn.getClassname();   		
    	}
       return c_seq;
    }


}
