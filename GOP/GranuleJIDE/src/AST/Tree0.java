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
public class Tree0 extends java.lang.Object {

	public TreeNode0 root;



    
	public Tree0()
	{
	    GranuleNode0 g1=new GranuleNode0("","");
	    root =new TreeNode0("Object",g1);

	}



	public TreeNode0 find(String classname){
	
		return findTreeNode0(root,classname);

	}



    


    public TreeNode0 findTreeNode0(TreeNode0 tn,String classname)
	{
		if(tn.classname.equals(classname))
			return tn;
		Iterator iter = tn.children.iterator();
		while(iter.hasNext()){
			TreeNode0 temp = (TreeNode0)iter.next();
			TreeNode0 treenode = findTreeNode0(temp,classname);
			if(treenode!=null)
				return treenode;
		}
		return null;
	}



    public void traverse0(){
       traverseNode0(root);
    }


    public void traverseNode0(TreeNode0 tn)
    {
       System.out.println(tn.classname);
       Iterator iter = tn.children.iterator();
		while(iter.hasNext()){
			TreeNode0 temp = (TreeNode0)iter.next();
			traverseNode0(temp);
		}
       
    }


    
 
    
   public void g_generatefile(String gfileName) {
    	
      File dest1 = new File(gfileName);
      File parentFile1 = dest1.getParentFile();
      if(parentFile1 != null)
        parentFile1.mkdirs();
      try{
		FileWriter fw1=new FileWriter(gfileName);
		g_writeNode(root,0,fw1);
      	fw1.close();
      }catch(Exception e){
       System.out.println("gFile open error");
      }
      
    }

 
    
    public void g_writeNode(TreeNode0 tn1, int i1,FileWriter out1){
    	if(out1==null || tn1==null)
			return;
		
		try{			
             if(tn1.classname.equals("Object"))
                {
			       for(int j=0; j<=i1; j++)
				       out1.write('\t');
				
                   String source15= "<granule name=\"" 
				                    + "(g0,c0)"
				                    + "\">\n"; 
				                    
			       char buffer15[] = new char[source15.length()]; 
			       source15.getChars(0, source15.length(), buffer15, 0); 
			       out1.write(buffer15);
			
	               for(int j=0; j<=i1; j++)
				       out1.write('\t');
				
                   String source20 = "<class name=\"" 
				                     + tn1.classname 
				                     + "\"/>\n"; 
				                     
			       char buffer20[] = new char[source20.length()]; 
			       source20.getChars(0, source20.length(), buffer20, 0); 
			       out1.write(buffer20);		
						
                }
                
                int s=0;    	
                Iterator iter10 =tn1.granulenodename.iterator();
                GranuleNode0 gn3=new GranuleNode0("","");
                ArrayList<String> cl=new ArrayList<String>();
                
		        while(iter10.hasNext())
		           {
		             gn3=(GranuleNode0)iter10.next();
		             int p=0;
		             for(int q=0; q<cl.size();q++)
		                {
		                   if(cl.get(q).equals(gn3.getContextname()))
		                      {
		                        p=1;
		                        break;
		                       }
		                }
		                
		             if(p==1||gn3.getGranulename().equals("")) continue;		       
		             cl.add(gn3.getContextname());
		             s++;
               	     i1++;	
               	     
			         for(int j=0; j<=i1; j++)
				         out1.write('\t');

		             String source10 = "<granule name=\"" 
				                       +"("+gn3.getGranulename()+","+gn3.getContextname()+")"			
				                       + "\">\n"; 	
				
				     char buffer10[] = new char[source10.length()]; 
		 	         source10.getChars(0, source10.length(), buffer10, 0); 
			         out1.write(buffer10);

			         for(int j=0; j<=i1; j++)
				        out1.write('\t');
				
                     String source11 = "<class name=\"" 
				                     + tn1.classname 
				                     + "\"/>\n"; 
				                     
			         char buffer11[] = new char[source11.length()]; 
			         source11.getChars(0, source11.length(), buffer11, 0); 
			         out1.write(buffer11);
			
		         }

                 Iterator iter11 = tn1.children.iterator();
			     while(iter11.hasNext())
			         {				
				        TreeNode0 temp11 = (TreeNode0)iter11.next();				
				        g_writeNode(temp11,i1+1,out1);				
				     }
				                           
		         for(int k=0;k<s;k++)
			       {			       
			    	   for(int j=0; j<=i1; j++)
				           out1.write('\t');
					
			           String source12 = "</granule>\n";
			           
			           char buffer12[] = new char[source12.length()]; 
			           source12.getChars(0, source12.length(), buffer12, 0); 
			           out1.write(buffer12);	
			           i1--;
		           }
			
	            if(tn1.classname.equals("Object"))
			      {	
                     int i2=0;
                     
				     for(int j=0; j<=i2; j++)
				          out1.write('\t');
			
			         String source16 = "</granule>\n";
			         
			         char buffer16[] = new char[source16.length()]; 
			         source16.getChars(0, source16.length(), buffer16, 0); 
			         out1.write(buffer16);	
			      }
	

		}catch (Exception e){
		  System.out.println("gFile write error");
		}
	}


}
