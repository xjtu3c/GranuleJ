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
public class GranuleTreeNode extends java.lang.Object {
 String granulename;

 String contextname;

 GranuleDecl granuledecl;

 ArrayList<GranuleTreeNode> children;

 GranuleTreeNode(String granulename,String contextname,GranuleDecl granuledecl){  
        this.granulename=granulename;
        this.contextname=contextname;
        this.granuledecl=granuledecl;
        this.children=new ArrayList<GranuleTreeNode>();
     }

public  String getGranulename() {  
    return granulename;  
  }

public  void setGranulename(String granulename) {  
    this.granulename=granulename;  
   }

public  String getContextname() {  
        return contextname;  
  }

public  void setContextname(String contextname) {  
        this.contextname=contextname; 
 }

public  void setGranuledecl(GranuleDecl granuledecl)
    {
    	this.granuledecl=granuledecl;
    }

public  GranuleDecl getGranuledecl()
    {
    	return granuledecl;
    }

public  ArrayList<GranuleTreeNode> getChildren() {
        if (this.children == null) {
            return new ArrayList<GranuleTreeNode>();
        }
        return this.children;
    }

public  void setChildren(ArrayList<GranuleTreeNode> children) {
        this.children = children;
    }

public  int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }

public  void addChild(GranuleTreeNode child) {
        if (children == null) {
            children = new ArrayList<GranuleTreeNode>();
        }
        children.add(child);
    }

public  void insertChildAt(int index, GranuleTreeNode child) throws IndexOutOfBoundsException {
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


}
