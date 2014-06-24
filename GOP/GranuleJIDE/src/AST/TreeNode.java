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
public class TreeNode extends java.lang.Object {

	public String classname = new String();

 
	public ArrayList<GranuleNode> granulenodename;


        public ArrayList<TreeNode> children;


	
	public TreeNode(String classname,GranuleNode granulenodename){ 
		this.classname = classname; 
                this.granulenodename = new ArrayList<GranuleNode>();

		this.granulenodename.add(granulenodename) ;  

		this.children = new ArrayList<TreeNode>(); 
	}


	
	public String getclassname(){ 
		return classname;
	}

 

	public void setClassname(String classname){ 

		this.classname = classname; 

	}

 

public ArrayList<GranuleNode> getGranulenodename(){ 

		return granulenodename; 

	}

 

	public void setGranulenodename(ArrayList<GranuleNode> link){ 

		this.granulenodename = link; 

	}

 

	public void addGranulenodename(GranuleNode gname){

		granulenodename.add(gname);

	}




	public ArrayList<TreeNode> getChildren(){ 

		return children; 

	}

 

	public void setChildren(ArrayList<TreeNode> link){ 

		this.children = link; 

	}

 

	public void addChildren(TreeNode tn){

		children.add(tn);

	}


}
