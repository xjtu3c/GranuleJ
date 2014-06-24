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
public class Tree extends java.lang.Object {

	public TreeNode root;



    
	public Tree()
	{
	 GranuleNode g1=new GranuleNode("","");
	    root =new TreeNode("Object",g1);

	}



	public TreeNode find(String classname){
	
		return findTreeNode(root,classname);

	}



    


    public TreeNode findTreeNode(TreeNode tn,String classname)
	{
		if(tn.classname.equals(classname))
			return tn;
		Iterator iter = tn.children.iterator();
		while(iter.hasNext()){
			TreeNode temp = (TreeNode)iter.next();
			TreeNode treenode = findTreeNode(temp,classname);
			if(treenode!=null)
				return treenode;
		}
		return null;
	}



    public void traverse(){
       traverseNode(root);
    }


    public void traverseNode(TreeNode tn)
    {
       System.out.println(tn.classname);
       Iterator iter = tn.children.iterator();
		while(iter.hasNext()){
			TreeNode temp = (TreeNode)iter.next();
			traverseNode(temp);
		}
       
    }


    
    public void generatefile(String fileName) {
    	
      File dest = new File(fileName);
      File parentFile = dest.getParentFile();
      if(parentFile != null)
        parentFile.mkdirs();
      try{
		FileWriter fw=new FileWriter(fileName);
		writeNode(root,0,fw);
      	fw.close();
      }catch(Exception e){
       System.out.println("File open error");
      }
    }


    
    
    public void writeNode(TreeNode tn, int i,FileWriter out){
    	if(out==null || tn==null)
			return;
		try{
			for(int j=0; j<i; j++)
				out.write('\t');
			String source = "<class name=\"" 
				+ tn.classname 
				+ "\">\n"; 
			char buffer[] = new char[source.length()]; 
			source.getChars(0, source.length(), buffer, 0); 
			out.write(buffer);

			for(int j=0; j<=i; j++)
				out.write('\t');
			
                         String s2="" ;
                        Iterator iter0 =tn.granulenodename.iterator();
                         GranuleNode gn3=new GranuleNode("","");
                         ArrayList<String> cl=new ArrayList<String>();
                         
		       while(iter0.hasNext()){
		       gn3=(GranuleNode)iter0.next();
		       int p=0;
		       for(int q=0; q<cl.size();q++)
		       {
		       if(cl.get(q).equals(gn3.getContextname()))
		      {
		       p=1;
		       break;
		      }
		       }
		       if(p==1)
		       continue;
		       cl.add(gn3.getContextname());
		        if(s2.equals(""))
		         s2=s2+"("+gn3.getGranulename()+","+gn3.getContextname()+")";
		        else
                 s2=s2+","+"("+gn3.getGranulename()+","+gn3.getContextname()+")";
		       }
		       int l=3;
		       if(s2.length()>3)
		       l++;
		        String s3=s2.substring(l,s2.length());
                      String source1 = "<granule name=\"" 
				+ s3 
				+ "\"/>\n"; 

			char buffer1[] = new char[source1.length()]; 
			source1.getChars(0, source1.length(), buffer1, 0); 
			out.write(buffer1);

			Iterator iter = tn.children.iterator();
			while(iter.hasNext()){				
				TreeNode temp = (TreeNode)iter.next();				
				writeNode(temp,i+1,out);
			}
			
			for(int j=0; j<i; j++)
				out.write('\t');
			
			String source3 = "</class>\n";
			char buffer3[] = new char[source3.length()]; 
			source3.getChars(0, source3.length(), buffer3, 0); 
			out.write(buffer3);
		}catch (Exception e){
		  System.out.println("File write error");
		}
	}


}
