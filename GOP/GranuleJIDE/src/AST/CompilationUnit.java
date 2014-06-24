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
 * @ast node
 * @declaredat java.ast:5
 */
public class CompilationUnit extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    CreateClassTree_computed = false;
    CreateClassTree_value = null;
    CreateGranuleTree_computed = false;
    CreateGranuleTree_value = null;
    GranuleCounts_computed = false;
    packageName_computed = false;
    packageName_value = null;
    destinationPath_computed = false;
    destinationPath_value = null;
    lookupType_String_values = null;
  }
  /**
   * @apilvl internal
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CompilationUnit clone() throws CloneNotSupportedException {
    CompilationUnit node = (CompilationUnit)super.clone();
    node.CreateClassTree_computed = false;
    node.CreateClassTree_value = null;
    node.CreateGranuleTree_computed = false;
    node.CreateGranuleTree_value = null;
    node.GranuleCounts_computed = false;
    node.packageName_computed = false;
    node.packageName_value = null;
    node.destinationPath_computed = false;
    node.destinationPath_value = null;
    node.lookupType_String_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CompilationUnit copy() {
      try {
        CompilationUnit node = (CompilationUnit)clone();
        if(children != null) node.children = (ASTNode[])children.clone();
        return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
  }
  /**
   * @apilvl low-level
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CompilationUnit fullCopy() {
    CompilationUnit res = (CompilationUnit)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:147
   */
  

  private String relativeName;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:148
   */
  
  private String pathName;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:149
   */
  
  private boolean fromSource;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:151
   */
  public void setRelativeName(String name) {
    relativeName = name;
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:154
   */
  public void setPathName(String name) {
    pathName = name;
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:157
   */
  public void setFromSource(boolean value) {
    fromSource = value;
  }
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:68
   */
  

  protected java.util.ArrayList errors = new java.util.ArrayList();
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:69
   */
  
  protected java.util.ArrayList warnings = new java.util.ArrayList();
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:71
   */
  public Collection parseErrors() { return parseErrors; }
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:72
   */
  public void addParseError(Problem msg) { parseErrors.add(msg); }
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:73
   */
  
  protected Collection parseErrors = new ArrayList();
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:231
   */
  public void errorCheck(Collection collection) {
    collectErrors();
    collection.addAll(errors);
  }
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:235
   */
  public void errorCheck(Collection err, Collection warn) {
    collectErrors();
    err.addAll(errors);
    warn.addAll(warnings);
  }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:372
   */
  public void setFromAuxiliary(boolean value) {
	    fromAuxiliary=value;
	  }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:375
   */
  
 private boolean fromAuxiliary;
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:121
   */
  public void GatherGranule()
   {
	   if(fromSource())
	   {
		 //if(!s_computed){
		 for(int i=0;i<getNumTypeDecl();i++){
	      if(getTypeDecl(i) instanceof GranuleDecl){
	        GranuleDecl granuledecl=(GranuleDecl)getTypeDecl(i);
		    if(granuledecl.hasRootClass()){
			 TypeDecl rootclass=granuledecl.getRootClassAccess().type();
		     if(rootclass instanceof ClassDecl)
		     {
		    	if(!((ClassDecl)rootclass).granuleclasses.contains(granuledecl))
		    	((ClassDecl)rootclass).granuleclasses.add(granuledecl);		    	
		     }
		 }
	   }
	}
   }
 }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:182
   */
  public ArrayList SortContextVariable()
{
	ArrayList contextv=new ArrayList();
	ClassTree classtree=CreateClassTree();
	if(classtree!=null){   
	ArrayList<ClassNode> list=new ArrayList(classtree.toList());
	for(Iterator iter=list.iterator();iter.hasNext();){
	   ClassNode classnode=(ClassNode)iter.next();
	   if(classnode.getClassname().equals("Object"))
	    continue;
	    TypeDecl decl=lookupType(packageName(),classnode.getClassname());
	   if(decl!=null){
       if(decl.isClassDecl()&&((ClassDecl)decl).hasGranuleClass())
       {
    	  LinkedList granules=new LinkedList(((ClassDecl)decl).granuleclasses);    	 
    	  LinkedList contextsort=new LinkedList();
    	  if(granules.size()>1){
    	   for(Iterator iter0=granules.iterator();iter0.hasNext();) 
    	   {
    		 GranuleDecl granuledecl=(GranuleDecl)iter0.next();
    		 String contextname="";
    		 for(Iterator iter1=granuledecl.fieldsIterator(); iter1.hasNext();){
       	      Object decll=iter1.next();
   			  if(decll instanceof ContextVarDeclaration){
   			  ContextVarDeclaration deccl=(ContextVarDeclaration)decll;
   			  contextname=deccl.name();
   			   break;
   			   }		   
   			   continue;
   			} 
    	   if(!granuledecl.hasSuperGranuleSpec())
    		{
    		 if(!contextsort.contains(contextname))
    		 contextsort.add(contextname); 
    		 for(Iterator iter00=contextsort.iterator();iter00.hasNext();){
    		  String context=(String)iter00.next(); 
	    	  }  
    	    }
    		else
    		{    		
    		 if(!contextsort.contains(contextname))
       		 contextsort.add(contextname);  
    		 while(granuledecl.hasSuperGranuleSpec()){
    		  TypeAccess typeaccess=(TypeAccess)(granuledecl.getSuperGranuleSpec().getSuperGranuleAccess());
    		  TypeDecl supgranule=granuledecl.superGranule();
			  if(supgranule==null) break;
			  if(supgranule!=null){
			  GranuleDecl supergranule=(GranuleDecl)supgranule;	 
			  String granulename1=supergranule.name();
	    	  String contextname1="";
	    	  for(Iterator iter2=supergranule.fieldsIterator(); iter2.hasNext();){
	       	      Object decll=iter2.next();
	   			  if(decll instanceof ContextVarDeclaration){
	   			  ContextVarDeclaration deccl=(ContextVarDeclaration)decll;
	   			  contextname1=deccl.name();
	   			   break;
	   			  }		   
	   			   continue;
	   			}	
	    	 if(!contextname1.equals(contextname)){	    	 
             if(contextsort.contains(contextname1))
	    	  contextsort.remove(contextname1);          
	    	  int m=contextsort.indexOf(contextname);
			  contextsort.add(m+1,contextname);
			  contextsort.set(m,contextname1);
	    	 }
		      granuledecl=supergranule;
    		}
    	   }
    	 }
       }
    }
    	 else 
    	   {
    		   for(Iterator iter0=granules.iterator();iter0.hasNext();) 
        	   {
        		 GranuleDecl granuledecl=(GranuleDecl)iter0.next();        		 
        		 String granulename=granuledecl.name();        		
        		 String contextname="";
        		 for(Iterator iter1=granuledecl.fieldsIterator(); iter1.hasNext();){
           	      Object decll=iter1.next();
       			  if(decll instanceof ContextVarDeclaration){
       			  ContextVarDeclaration deccl=(ContextVarDeclaration)decll;
       			  contextname=deccl.name();
       			   break;
       			   }		   
       			   continue;
       			}
        		 if(!contextsort.contains(contextname))
        		 contextsort.add(contextname);
         	   }               
    	   } 
    	   for(Iterator iter7=contextsort.iterator();iter7.hasNext();)
    	   {
    		   String contextnode=(String)iter7.next();
    		   if(!contextv.contains(contextnode))
    		   contextv.add(contextnode);
    	   }
    	  }
	   }
	  } 
	}
	/*for(Iterator iter3=contextv.iterator();iter3.hasNext();){
	     System.out.println("red leaf ti shi second:"+(String)iter3.next());   
	   }*/
	return contextv;
}
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:289
   */
  public void CreateGranuleNode(ClassNode cn,GranuleTreeNode gtn0)
{
	   boolean start=false;
	   ClassNode classnode=(ClassNode)cn;
	   String classname=classnode.getClassname();
		  if(classname.equals("Object")) start=true;
		  if(!start){		 
		  ClassDecl decl=classnode.getClassdecl();	
		  if(((ClassDecl)decl).hasGranuleClass()){
		  LinkedList granulesetsort=new LinkedList();
		  if((((ClassDecl)decl).granuleclasses).size()>1){		
		  LinkedList granuleclasses=new	LinkedList(((ClassDecl)decl).granuleclasses);
		  HashMap samelayer=new HashMap();
		  for(Iterator iter0=granuleclasses.iterator();iter0.hasNext();){
		  GranuleDecl granuledecl=(GranuleDecl)iter0.next();
		  String granulename=granuledecl.name();        		
 		  String contextname="";
		  for(Iterator iter1=granuledecl.fieldsIterator(); iter1.hasNext();){
       	      Object decll=iter1.next();
   			  if(decll instanceof ContextVarDeclaration){
   			  ContextVarDeclaration deccl=(ContextVarDeclaration)decll;
   			  contextname=deccl.name();
   			   break;
   			   }		   
   			   continue;
   			}
		 if(contextname!=null)
		 {
			if(!samelayer.containsKey(contextname)){
			ArrayList list=new ArrayList();
		    list.add(granuledecl);
			samelayer.put(contextname,list);
			}
			else 
		   {
			ArrayList list=(ArrayList)(samelayer.get(contextname));
			list.add(granuledecl);
		   }
		 }
	 }
		 int i=0,j=0;
		 ArrayList context=(ArrayList)SortContextVariable();
		 LinkedList granulesort=new LinkedList();		 
		 for(Iterator iter2=samelayer.keySet().iterator();iter2.hasNext();)
		 {
			 String contextname=(String)iter2.next();	
			 i=context.indexOf(contextname);		
			 if(i!=-1&i!=0){
			   if(i>j){			
			   ArrayList list=(ArrayList)(samelayer.get(contextname));
			   granulesort.add(list);
			   }
			   else if(i<j){
			   ArrayList list=(ArrayList)(samelayer.get(contextname));
			   ArrayList previouslist=(ArrayList)(granulesort.getLast());
			   int m=granulesort.indexOf(previouslist);
			   granulesort.add(m+1,previouslist);
			   granulesort.set(m,list);
			   }				 
			 }	
			 else
			 {
			   ArrayList list=(ArrayList)(samelayer.get(contextname));
			   granulesort.add(list);
			 }
			 j=i;
		 }	
		for(Iterator iter3=granulesort.iterator();iter3.hasNext();)
		{
			ArrayList array=(ArrayList)iter3.next();
			if(array.size()>1){
			for(Iterator iter4=array.iterator();iter4.hasNext();)
			{
				GranuleDecl granuledecl=(GranuleDecl)iter4.next();
			 if(!granuledecl.hasSuperGranuleSpec()){
			    if(!granulesetsort.contains(granuledecl))
			  	granulesetsort.add(granuledecl);
				}
			 else {
			     if(!granulesetsort.contains(granuledecl))
			  	    granulesetsort.add(granuledecl);
			     while(granuledecl.hasSuperGranuleSpec())
			  	{
			  	     TypeDecl supgranule=granuledecl.superGranule();
					  if(supgranule==null) break;
				          if(supgranule!=null){
			  		 //System.out.println("super granule name is "+supgranule.name());
			  		 GranuleDecl supergranule=(GranuleDecl)supgranule;	
			  		if(array.contains(supergranule)){
			  		if(granulesetsort.contains(supergranule))
			  		 granulesetsort.remove(supergranule);
			  		 int n=granulesetsort.indexOf(granuledecl);
			  		 granulesetsort.add(n+1,granuledecl);
			  		 granulesetsort.set(n,supergranule);	
			  		}				
			  	      granuledecl=supergranule;	
			  	 }
			  	}
			  }
			}		
		}
			else
			{
				for(Iterator iter5=array.iterator();iter5.hasNext();)
				{
					GranuleDecl granuledecl=(GranuleDecl)iter5.next();
					if(!granuledecl.hasSuperGranuleSpec()){
					if(!granulesetsort.contains(granuledecl))
					granulesetsort.add(granuledecl);
					}
					else{
				      if(!granulesetsort.contains(granuledecl))
				      granulesetsort.add(granuledecl);
				      while(granuledecl.hasSuperGranuleSpec())
				      {
				    	   TypeDecl supgranule=granuledecl.superGranule();
				    	   if(supgranule==null) break;
				           else {
							   GranuleDecl supergranule=(GranuleDecl)supgranule;
						          if(granuledecl.rootClass()!=supergranule.rootClass())
							  	break;
							   if(granulesetsort.contains(supergranule)){
								 granulesetsort.remove(supergranule);
								 int n=granulesetsort.indexOf(granuledecl);
								 granulesetsort.add(n+1,granuledecl);
								 granulesetsort.set(n,supergranule);	
							    }
							   else{								 
								   int m=granulesetsort.indexOf(granuledecl);
								   granulesetsort.add(m+1,granuledecl);
								   granulesetsort.set(m,supergranule);								   
							   }								 
							   granuledecl=supergranule;
					      }								  
						}
				      }					
			  }
			}
		}
	}
			else
			{
					 granulesetsort=((ClassDecl)decl).granuleclasses;	  		
			} 
		
		  for(Iterator iter1=granulesetsort.iterator();iter1.hasNext();){		  
			  GranuleDecl granuledecl=(GranuleDecl)iter1.next();
			  String granulename=granuledecl.name();		  
			  String contextname="";
			  for(Iterator iter2=granuledecl.fieldsIterator(); iter2.hasNext();){
			  Object decll=iter2.next();
			  if(decll instanceof ContextVarDeclaration){
			  ContextVarDeclaration deccl=(ContextVarDeclaration)decll;
			  contextname=deccl.name();
			   break;
			   }		   
			   continue;
			}
		  GranuleTreeNode gtn=new GranuleTreeNode(granulename,contextname,granuledecl);		
		  gtn0.addChild(gtn);
		  gtn0=gtn;		 
		  }
	   }
    }
	for(Iterator iter1=classnode.getChildren().iterator();iter1.hasNext();)
	{
		ClassNode temp=(ClassNode)iter1.next();
		CreateGranuleNode(temp,gtn0);
	}	  	  
}
  /**
   * @ast method 
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:102
   */
  
      public boolean hasTrueUpdateExpr=false;
  /**
   * @ast method 
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:103
   */
  
      public boolean hasFalseUpdateExpr=false;
  /**
   * @ast method 
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:463
   */
  public String classQName() {
	        if (relativeName == null)
	            return null;
	        String qName = getPackageDecl();
	        int idx = relativeName.lastIndexOf(File.separatorChar);	     
	        String id = relativeName.substring(idx+1);
	        idx = id.indexOf(".");
	        id = id.substring(0, idx);
	        return qName.isEmpty() ? id : qName + "." + id;
	    }
  /**
   * @ast method 
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:195
   */
  
 
  // The scope of a type import declaration is all the class and interface type declarations in
  // the compilation unit in which the import declaration appears.
  protected boolean hasGopContextUsed=false;
  /**
   * @ast method 
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:196
   */
  
  protected boolean hasGopRemoteUsed=false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:827
   */
  public LinkedList getSubTypes(InterfaceDecl id){
	   LinkedList s=new LinkedList();
	   if(fromSource()) { 
         for(int i = 0; i < getNumTypeDecl(); i++) {
          if(getTypeDecl(i) instanceof ClassDecl){
        	ClassDecl classdecl = (ClassDecl)getTypeDecl(i);
       	     for(Iterator iter = classdecl.interfacesIterator(); iter.hasNext();){
			  InterfaceDecl idd=(InterfaceDecl)iter.next();
              if(idd == id)
			  s.add(classdecl);
			  s.addAll(classdecl.getSubclasses());
           }     		
	     }
	   }
	 }	 
     return s;	 
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:856
   */
  public LinkedList getSubclasses(ClassDecl cd){
	   LinkedList s=new LinkedList();
	   if(fromSource()) { 
         for(int i = 0; i < getNumTypeDecl(); i++) {
          if(getTypeDecl(i) instanceof ClassDecl){
        	ClassDecl classdecl = (ClassDecl)getTypeDecl(i);
			if(classdecl!=cd && classdecl.instanceOf(cd))
			s.add(classdecl);
           }     		
	     }
	   } 
     return s;	 
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:995
   */
  public Collection getShadowclasses(ClassDecl cd){
	   Collection s=new LinkedList();
	   if(fromSource()) { 
         for(int i = 0; i < getNumTypeDecl(); i++) {
          if(getTypeDecl(i) instanceof ShadowClassDecl){
        	ShadowClassDecl sdecl = (ShadowClassDecl)getTypeDecl(i);
			if(sdecl.instanceOf(cd))
			s.add(sdecl);
           }     		
	     }
	   } 
     return s;	 
	}
  /**
   * @ast method 
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:35
   */
  public void refined_NameCheck_CompilationUnit_nameCheck() {
    for(int i = 0; i < getNumImportDecl(); i++) {
      ImportDecl decl = getImportDecl(i);
      if(decl instanceof SingleTypeImportDecl) {
        if(localLookupType(decl.getAccess().type().name()).contains(decl.getAccess().type()))
          error("" + decl + " is conflicting with visible type");
      }
    }
  }
  /**
   * @ast method 
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:42
   */
    

  
    private boolean isFromGranule = false;
  /**
   * @ast method 
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:43
   */
  public void setFromGranule(boolean b) {
        isFromGranule = b;
    }
  /**
   * @ast method 
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:46
   */
  public boolean fromGranule() {
        return isFromGranule;
   }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:32
   */
  public void toString(StringBuffer s) {
    try {
      if(!getPackageDecl().equals("")) {
        s.append("package " + getPackageDecl() + ";\n");
      }
      for(int i = 0; i < getNumImportDecl(); i++) {
        getImportDecl(i).toString(s);
      }
      for(int i = 0; i < getNumTypeDecl(); i++) {
        getTypeDecl(i).toString(s);
        s.append("\n");
      }
    } catch (NullPointerException e) {
      System.out.print("Error in compilation unit hosting " + getTypeDecl(0).typeName());
      throw e;
    }
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:41
   */
  public void generateClassfile() {
    if(fromSource()) {
      for(int i = 0; i < getNumTypeDecl(); i++) {
        getTypeDecl(i).generateClassfile();
        getTypeDecl(i).clear();
      }
     }
//================Gop========================
  if(fromAuxiliary())
     {
    	    for(int j = 0; j < getNumTypeDecl(); j++){
    	        getTypeDecl(j).generateClassfile();
    	        getTypeDecl(j).clear();
    	      }
     }
  }
  /**
   * @ast method 
   * @aspect GranuleSeedStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeed.jrag:2
   */
  public void generateGranuleSeedfile() {
    if(fromSource()) {
      Tree0 tree0 = new Tree0();
      GranuleNode0 g00=new GranuleNode0("","");
      for(int i = 0; i < getNumTypeDecl(); i++) {
         if(getTypeDecl(i) instanceof ClassDecl){
        	ClassDecl classdecl0 = (ClassDecl)getTypeDecl(i);        	
       		String classname0 = getTypeDecl(i).name();
       		TreeNode0 tn0= new TreeNode0(classname0,g00);
       		if(classdecl0.hasSuperclass()){
       			String supername0 = classdecl0.superclass().getID();
       			TreeNode0 tparent0 =tree0.find(supername0);
       		    if(tparent0!=null){
       		      if(tree0.find(classname0)==null)
       		      	tparent0.children.add(tn0);
       		    }
       		    else{
       		     while(classdecl0.hasSuperclass() && tree0.find(supername0)==null){
       		     	TreeNode0 tp0 = new TreeNode0(supername0,g00);
       		     	tp0.children.add(tn0);
       		     	classdecl0 = classdecl0.superclass();
       		     	tn0 = tp0;
       		        supername0 = classdecl0.superclass().getID();
       		      }//while
       		      if(classdecl0.hasSuperclass() && tree0.find(supername0)!=null)
       		         tree0.find(supername0).children.add(tn0);
       		    }//else
       		 }//ifhassuperclass
         }
         else if(getTypeDecl(i) instanceof GranuleDecl){
          GranuleDecl granuledecl0 = (GranuleDecl)getTypeDecl(i);
          ClassDecl root0 =(ClassDecl) ((TypeAccess)granuledecl0.getRootClassAccess()).decl();
         
          GranuleNode0 g=new GranuleNode0(granuledecl0.name(),"");
	      String ss0="";
	            
        for(Iterator iter0 = granuledecl0.fieldsIterator(); iter0.hasNext(); ) {
        	Object decl0=iter0.next();
       //FieldDeclaration decl0 = (FieldDeclaration)iter0.next();
          if(decl0 instanceof ContextVarDeclaration){
         ContextVarDeclaration deccl=(ContextVarDeclaration)decl0;
         ss0=deccl.name();        
     // if(decl0.isExternal())
        //{
         // ss0=decl0.name();
          g.setContextname(ss0);
           break;
  }
	  continue;
  }
         
          TreeNode0 noderootclass0 =tree0.find(root0.name());
          if(noderootclass0 != null)
                 noderootclass0.addGranulenodename(g);
          else{

              TreeNode0 tn0 = new TreeNode0(root0.name(),g);
              ClassDecl classdecl0 = root0;
              if(classdecl0.hasSuperclass()){
       			String supername0 = classdecl0.superclass().getID();
       			TreeNode0 tparent0 =tree0.find(supername0);
       		    if(tparent0!=null){
       		      if(tree0.find(tn0.classname)==null)
       		      	tparent0.children.add(tn0);
       		    }
       		    else{
       		     while(classdecl0.hasSuperclass() && tree0.find(supername0)==null){
       		     	TreeNode0 tp0 = new TreeNode0(supername0,g00);
       		     	tp0.children.add(tn0);
       		     	classdecl0 = classdecl0.superclass();
       		     	tn0 = tp0;
       		        supername0 = classdecl0.superclass().getID();
       		      }
       		      if(classdecl0.hasSuperclass() && tree0.find(supername0)!=null)
       		         tree0.find(supername0).children.add(tn0);
       		    }
       		 }
       } 
    }

   }
   

   String gfileName = relativeName.substring(0,relativeName.length()-6)+"$" + ".xml";
   tree0.g_generatefile(gfileName);
  }
}
  /**
   * @ast method 
   * @aspect SeedStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Seed.jrag:4
   */
  public void generateSeedfile() {
    if(fromSource()) {
      Tree tree = new Tree();
       GranuleNode g0=new GranuleNode("","");
      for(int i = 0; i < getNumTypeDecl(); i++) {
         if(getTypeDecl(i) instanceof ClassDecl){
        	ClassDecl classdecl = (ClassDecl)getTypeDecl(i);
       		String classname= getTypeDecl(i).name();       		
       		TreeNode tn= new TreeNode(classname,g0);
       		if(classdecl.hasSuperclass()){
       			String supername = classdecl.superclass().getID();
       			TreeNode tparent =tree.find(supername);
       		    if(tparent!=null){
       		      if(tree.find(classname)==null)
       		      	tparent.children.add(tn);
       		    }
       		    else{
       		     while(classdecl.hasSuperclass() && tree.find(supername)==null){
       		     	TreeNode tp = new TreeNode(supername,g0);
       		     	tp.children.add(tn);
       		     	classdecl = classdecl.superclass();
       		     	tn=tp;
       		        supername = classdecl.superclass().getID();
       		      }//while
       		      if(classdecl.hasSuperclass() && tree.find(supername)!=null)
       		         tree.find(supername).children.add(tn);
       		    }//else
       		 }//ifhassuperclass
         }
         else if(getTypeDecl(i) instanceof GranuleDecl){
          GranuleDecl granuledecl = (GranuleDecl)getTypeDecl(i);
          ClassDecl root =(ClassDecl) ((TypeAccess)granuledecl.getRootClassAccess()).decl();
         GranuleNode g=new GranuleNode(granuledecl.name(),"");
	      String ss="";
	            
        for(Iterator iter = granuledecl.fieldsIterator(); iter.hasNext(); ) {
      // FieldDeclaration decl = (FieldDeclaration)iter.next();
         Object decl=iter.next();
         if(decl instanceof ContextVarDeclaration) {  
          ContextVarDeclaration decll=(ContextVarDeclaration)decl;
         ss=decll.name();          
      //if(decl.isExternal())
       // {
        //  ss=decl.name();
          g.setContextname(ss);
           break;
  }
	  continue;
  }

         
          TreeNode noderootclass =tree.find(root.name());
          if(noderootclass != null)
                 noderootclass.addGranulenodename(g);
          else{

              TreeNode tn = new TreeNode(root.name(),g);
              ClassDecl classdecl = root;
              if(classdecl.hasSuperclass()){
       			String supername = classdecl.superclass().getID();
       			TreeNode tparent =tree.find(supername);
       		    if(tparent!=null){
       		      if(tree.find(tn.classname)==null)
       		      	tparent.children.add(tn);
       		    }
       		    else{
       		     while(classdecl.hasSuperclass() && tree.find(supername)==null){
       		     	TreeNode tp = new TreeNode(supername,g0);
       		     	tp.children.add(tn);
       		     	classdecl = classdecl.superclass();
       		     	tn = tp;
       		        supername = classdecl.superclass().getID();
       		      }
       		      if(classdecl.hasSuperclass() && tree.find(supername)!=null)
       		         tree.find(supername).children.add(tn);
       		    }
       		 }
       } 
    }

   }
   
   String fileName = relativeName.substring(0,relativeName.length()-5) + "xml";
   tree.generatefile(fileName);
  }
}
  /**
   * @ast method 
   * @aspect Transformations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Transformations.jrag:18
   */
  public void transformation() {
    if(fromSource()) {
      for(int i = 0; i < getNumTypeDecl(); i++) {
        getTypeDecl(i).transformation();
      }
    }
  }
  /**
   * @ast method 
   * @aspect Caching
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\caching.jrag:6
   */
  public void clearOnExit() {
	  if(fromSource()) {
	      for(int i = 0; i < getNumTypeDecl(); i++) {
	    	  getTypeDecl(i).clearOnExit();
	      }
	  }
	}
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public CompilationUnit() {
    super();

    setChild(new List(), 0);
    setChild(new List(), 1);
    setChild(new List(), 2);

  }
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  public CompilationUnit(java.lang.String p0, List<ImportDecl> p1, List<ContextVar> p2, List<TypeDecl> p3) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @ast method 
   * @declaredat java.ast:16
   */
  public CompilationUnit(beaver.Symbol p0, List<ImportDecl> p1, List<ContextVar> p2, List<TypeDecl> p3) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:25
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:31
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for lexeme PackageDecl
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setPackageDecl(java.lang.String value) {
    tokenjava_lang_String_PackageDecl = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat java.ast:8
   */
  
  /**   * @apilvl internal   */  protected java.lang.String tokenjava_lang_String_PackageDecl;
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  
  public int PackageDeclstart;
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  
  public int PackageDeclend;
  /**
   * @ast method 
   * @declaredat java.ast:11
   */
  public void setPackageDecl(beaver.Symbol symbol) {
    if(symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setPackageDecl is only valid for String lexemes");
    tokenjava_lang_String_PackageDecl = (String)symbol.value;
    PackageDeclstart = symbol.getStart();
    PackageDeclend = symbol.getEnd();
  }
  /**
   * Getter for lexeme PackageDecl
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:22
   */
  public java.lang.String getPackageDecl() {
    return tokenjava_lang_String_PackageDecl != null ? tokenjava_lang_String_PackageDecl : "";
  }
  /**
   * Setter for ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setImportDeclList(List<ImportDecl> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumImportDecl() {
    return getImportDeclList().getNumChild();
  }
  /**
   * Getter for child in list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ImportDecl getImportDecl(int i) {
    return (ImportDecl)getImportDeclList().getChild(i);
  }
  /**
   * Add element to list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addImportDecl(ImportDecl node) {
    List<ImportDecl> list = (parent == null || state == null) ? getImportDeclListNoTransform() : getImportDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addImportDeclNoTransform(ImportDecl node) {
    List<ImportDecl> list = getImportDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setImportDecl(ImportDecl node, int i) {
    List<ImportDecl> list = getImportDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for ImportDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<ImportDecl> getImportDecls() {
    return getImportDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<ImportDecl> getImportDeclsNoTransform() {
    return getImportDeclListNoTransform();
  }
  /**
   * Getter for list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ImportDecl> getImportDeclList() {
    List<ImportDecl> list = (List<ImportDecl>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ImportDecl> getImportDeclListNoTransform() {
    return (List<ImportDecl>)getChildNoTransform(0);
  }
  /**
   * Setter for ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setContextVarList(List<ContextVar> list) {
    setChild(list, 1);
  }
  /**
   * @return number of children in ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumContextVar() {
    return getContextVarList().getNumChild();
  }
  /**
   * Getter for child in list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ContextVar getContextVar(int i) {
    return (ContextVar)getContextVarList().getChild(i);
  }
  /**
   * Add element to list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addContextVar(ContextVar node) {
    List<ContextVar> list = (parent == null || state == null) ? getContextVarListNoTransform() : getContextVarList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addContextVarNoTransform(ContextVar node) {
    List<ContextVar> list = getContextVarListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setContextVar(ContextVar node, int i) {
    List<ContextVar> list = getContextVarList();
    list.setChild(node, i);
  }
  /**
   * Getter for ContextVar list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<ContextVar> getContextVars() {
    return getContextVarList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<ContextVar> getContextVarsNoTransform() {
    return getContextVarListNoTransform();
  }
  /**
   * Getter for list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ContextVar> getContextVarList() {
    List<ContextVar> list = (List<ContextVar>)getChild(1);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ContextVar> getContextVarListNoTransform() {
    return (List<ContextVar>)getChildNoTransform(1);
  }
  /**
   * Setter for TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setTypeDeclList(List<TypeDecl> list) {
    setChild(list, 2);
  }
  /**
   * @return number of children in TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumTypeDecl() {
    return getTypeDeclList().getNumChild();
  }
  /**
   * Getter for child in list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl getTypeDecl(int i) {
    return (TypeDecl)getTypeDeclList().getChild(i);
  }
  /**
   * Add element to list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addTypeDecl(TypeDecl node) {
    List<TypeDecl> list = (parent == null || state == null) ? getTypeDeclListNoTransform() : getTypeDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addTypeDeclNoTransform(TypeDecl node) {
    List<TypeDecl> list = getTypeDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setTypeDecl(TypeDecl node, int i) {
    List<TypeDecl> list = getTypeDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for TypeDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<TypeDecl> getTypeDecls() {
    return getTypeDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<TypeDecl> getTypeDeclsNoTransform() {
    return getTypeDeclListNoTransform();
  }
  /**
   * Getter for list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<TypeDecl> getTypeDeclList() {
    List<TypeDecl> list = (List<TypeDecl>)getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<TypeDecl> getTypeDeclListNoTransform() {
    return (List<TypeDecl>)getChildNoTransform(2);
  }
  /**
   * @ast method 
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:181
   */
    public void nameCheck() {
    refined_NameCheck_CompilationUnit_nameCheck();
    for(int i = 0; i < getNumImportDecl(); i++) {
      if(getImportDecl(i) instanceof SingleStaticImportDecl) {
        SingleStaticImportDecl decl = (SingleStaticImportDecl)getImportDecl(i);
        String name = decl.name();
        if(!decl.importedTypes(name).isEmpty()) {
          TypeDecl type = (TypeDecl)decl.importedTypes(name).iterator().next();
          if(localLookupType(name).contains(type))
            decl.error(packageName() + "." + name + " is already defined in this compilation unit");
        }
      }
    }
  }
  /**
   * @ast method 
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:197
   */
  private SimpleSet refined_TypeScopePropagation_CompilationUnit_Child_lookupType_String(String name)
{
    //locally declared types in compilation unit
    SimpleSet set = localLookupType(name);
    if(!set.isEmpty()) return set;    
    // imported types
    set = importedTypes(name);
    if(!set.isEmpty()) return set;

    // types in the same package
    TypeDecl result = lookupType(packageName(), name);    
    if(result != null && result.accessibleFromPackage(packageName())) 
      return SimpleSet.emptySet.add(result);
    
    // types imported on demand
    set=importedTypesOnDemand(name);
    if(!set.isEmpty()) return set;
    
    // include primitive types
    result = lookupType(PRIMITIVE_PACKAGE_NAME, name);
    if(result != null) return SimpleSet.emptySet.add(result);
    
    // 7.5.5 Automatic Imports    
    result=lookupType("java.lang", name);     
    if(result != null && result.accessibleFromPackage(packageName())) 
      return SimpleSet.emptySet.add(result);
     // Automatic granulej.lang.GopContext;    
    result=lookupType("granulej.lang",name);    
    if(result!=null && result.accessibleFromPackage(packageName()))
    return SimpleSet.emptySet.add(result); 
    return lookupType(name);
      }
  /**
   * @attribute syn
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:27
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String relativeName() {
      ASTNode$State state = state();
    String relativeName_value = relativeName_compute();
    return relativeName_value;
  }
  /**
   * @apilvl internal
   */
  private String relativeName_compute() {  return relativeName;  }
  /**
   * @attribute syn
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:28
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String pathName() {
      ASTNode$State state = state();
    String pathName_value = pathName_compute();
    return pathName_value;
  }
  /**
   * @apilvl internal
   */
  private String pathName_compute() {  return pathName;  }
  /**
   * @attribute syn
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:29
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean fromSource() {
      ASTNode$State state = state();
    boolean fromSource_value = fromSource_compute();
    return fromSource_value;
  }
  /**
   * @apilvl internal
   */
  private boolean fromSource_compute() {  return fromSource;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:371
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean fromAuxiliary() {
      ASTNode$State state = state();
    boolean fromAuxiliary_value = fromAuxiliary_compute();
    return fromAuxiliary_value;
  }
  /**
   * @apilvl internal
   */
  private boolean fromAuxiliary_compute() {  return fromAuxiliary;  }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:143
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CompilationUnit getCompilationUnit() {
      ASTNode$State state = state();
    CompilationUnit getCompilationUnit_value = getCompilationUnit_compute();
    return getCompilationUnit_value;
  }
  /**
   * @apilvl internal
   */
  private CompilationUnit getCompilationUnit_compute() {  return this;  }
  /**
   * @apilvl internal
   */
  protected boolean CreateClassTree_computed = false;
  /**
   * @apilvl internal
   */
  protected ClassTree CreateClassTree_value;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:144
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ClassTree CreateClassTree() {
    if(CreateClassTree_computed) {
      return CreateClassTree_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    CreateClassTree_value = CreateClassTree_compute();
if(isFinal && num == state().boundariesCrossed) CreateClassTree_computed = true;
    return CreateClassTree_value;
  }
  /**
   * @apilvl internal
   */
  private ClassTree CreateClassTree_compute() {     
    	  if(fromSource()){     		
     	     ClassTree cn=new ClassTree();
     	      for(int i = 0; i < getNumTypeDecl(); i++){
     	         if(getTypeDecl(i) instanceof ClassDecl){
     	        	ClassDecl classdecl=(ClassDecl)getTypeDecl(i);	        	
     	       		String classname=classdecl.name();	
     	       		ClassNode classdecll=new ClassNode(classname,classdecl);	       		
     	       		if(classdecl.hasSuperclass()){
     	       			String superclass=classdecl.superclass().getID();
     	       			ClassNode cl=cn.findClass(superclass);	       			
     	       			if(cl!=null){
     	       			   if(cn.findClass(classname)==null)
     	       				cl.addChild(classdecll);
     	       			 }
     	       			else
     	       			{
     	       			  while(classdecl.hasSuperclass()&&cn.findClass(superclass)==null)
     	       			  {
     	       				  ClassNode superdecll=new ClassNode(superclass,classdecl.superclass());
     	       				  superdecll.addChild(classdecll);
     	       				  classdecl=classdecl.superclass();
     	       				  classdecll=superdecll;
     	       				  superclass=classdecl.superclass().getID();
     	       			  }
     	       			  if(classdecl.hasSuperclass()&&cn.findClass(superclass)!=null)
     	       			    cn.findClass(superclass).addChild(classdecll);
     	       		} 	  	       			
     	       	}
      }
      }     	 
          return cn;
   }
     	 return null;
      }
  /**
   * @apilvl internal
   */
  protected boolean CreateGranuleTree_computed = false;
  /**
   * @apilvl internal
   */
  protected GranuleTree CreateGranuleTree_value;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:461
   */
  @SuppressWarnings({"unchecked", "cast"})
  public GranuleTree CreateGranuleTree() {
    if(CreateGranuleTree_computed) {
      return CreateGranuleTree_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    CreateGranuleTree_value = CreateGranuleTree_compute();
if(isFinal && num == state().boundariesCrossed) CreateGranuleTree_computed = true;
    return CreateGranuleTree_value;
  }
  /**
   * @apilvl internal
   */
  private GranuleTree CreateGranuleTree_compute() {
	GranuleTree gtree=new GranuleTree();
	ClassTree classtree=CreateClassTree();
	GranuleTreeNode gtn0=gtree.getRootElement();
    if(classtree!=null){
    ClassNode classnode=(ClassNode)classtree.getRootElement();
    GatherGranule();
    CreateGranuleNode(classnode,gtn0); 
    for(Iterator iter3=gtree.toList().iterator();iter3.hasNext();){
       GranuleTreeNode granule=(GranuleTreeNode)iter3.next();
	   //System.out.println("red leaf ti shi second:"+(String)granule.getGranulename());   
	   }
    return gtree;
	}
	return gtree;
}
  /**
   * @apilvl internal
   */
  protected boolean GranuleCounts_computed = false;
  /**
   * @apilvl internal
   */
  protected int GranuleCounts_value;
  /**
   * @attribute syn
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1908
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int GranuleCounts() {
    if(GranuleCounts_computed) {
      return GranuleCounts_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    GranuleCounts_value = GranuleCounts_compute();
if(isFinal && num == state().boundariesCrossed) GranuleCounts_computed = true;
    return GranuleCounts_value;
  }
  /**
   * @apilvl internal
   */
  private int GranuleCounts_compute() {
	 int count=0;
	 if(fromSource())
	 count+=GranuleCount();
	 return count;
 }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:228
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet localLookupType(String name) {
      ASTNode$State state = state();
    SimpleSet localLookupType_String_value = localLookupType_compute(name);
    return localLookupType_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet localLookupType_compute(String name) {
        for(int i = 0; i < getNumTypeDecl(); i++){
//=========================GOP====================================
      if(getTypeDecl(i) instanceof GranuleDecl)
      {
    	  if(getTypeDecl(i).name().equals(name))
    	  return SimpleSet.emptySet.add(getTypeDecl(i));
    	  GranuleDecl granule=(GranuleDecl)getTypeDecl(i);
    	  for(int j=0;j<granule.getNumBodyDecl();j++){
    	   if(granule.getBodyDecl(j) instanceof MemberShadowClassDecl){
    		   MemberShadowClassDecl member=(MemberShadowClassDecl)granule.getBodyDecl(j);
    		   ShadowClassDecl shadow=(ShadowClassDecl)member.getShadowClassDecl();   		
    		   if(shadow.name().equals(name))
    			return SimpleSet.emptySet.add(shadow);
    	   }
    	  }		 
      }
    else {
//=========================END=====================================
      if(getTypeDecl(i).name().equals(name))
        return SimpleSet.emptySet.add(getTypeDecl(i));
     }
    }
    return SimpleSet.emptySet;
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:254
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet importedTypes(String name) {
      ASTNode$State state = state();
    SimpleSet importedTypes_String_value = importedTypes_compute(name);
    return importedTypes_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet importedTypes_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    for(int i = 0; i < getNumImportDecl(); i++){
      if(!getImportDecl(i).isOnDemand()){
        for(Iterator iter = getImportDecl(i).importedTypes(name).iterator(); iter.hasNext(); ){
          set = set.add(iter.next());
       }
      }
    }
    return set;
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:265
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet importedTypesOnDemand(String name) {
      ASTNode$State state = state();
    SimpleSet importedTypesOnDemand_String_value = importedTypesOnDemand_compute(name);
    return importedTypesOnDemand_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet importedTypesOnDemand_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    for(int i = 0; i < getNumImportDecl(); i++)
      if(getImportDecl(i).isOnDemand())
        for(Iterator iter = getImportDecl(i).importedTypes(name).iterator(); iter.hasNext(); )
          set = set.add(iter.next());
    return set;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:931
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String dumpString() {
      ASTNode$State state = state();
    String dumpString_value = dumpString_compute();
    return dumpString_value;
  }
  /**
   * @apilvl internal
   */
  private String dumpString_compute() {  return getClass().getName() + " [" + getPackageDecl() + "]";  }
  /**
   * @apilvl internal
   */
  protected boolean packageName_computed = false;
  /**
   * @apilvl internal
   */
  protected String packageName_value;
  /**
   * @attribute syn
   * @aspect TypeName
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\QualifiedNames.jrag:94
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String packageName() {
    if(packageName_computed) {
      return packageName_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    packageName_value = packageName_compute();
if(isFinal && num == state().boundariesCrossed) packageName_computed = true;
    return packageName_value;
  }
  /**
   * @apilvl internal
   */
  private String packageName_compute() {  return getPackageDecl();  }
  /**
   * @apilvl internal
   */
  protected boolean destinationPath_computed = false;
  /**
   * @apilvl internal
   */
  protected String destinationPath_value;
  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:87
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String destinationPath() {
    if(destinationPath_computed) {
      return destinationPath_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    destinationPath_value = destinationPath_compute();
if(isFinal && num == state().boundariesCrossed) destinationPath_computed = true;
    return destinationPath_value;
  }
  /**
   * @apilvl internal
   */
  private String destinationPath_compute() {
    if(options().hasValueForOption("-d")) {
      return options().getValueForOption("-d");
    }
    else {
      if(fromSource()) {
        String sourceName = pathName();         
        // extract source path including package directories
        String sourcePath = null;
        if(sourceName.lastIndexOf(java.io.File.separator) == -1)
          sourcePath = ".";
        else
          sourcePath = sourceName.substring(0, sourceName.lastIndexOf(java.io.File.separator));
        String sourcePathPattern = sourcePath.replace(java.io.File.separatorChar, '.');
        String[] parts = packageName().split("\\.");
        int num = parts.length;
        while(num > 3) {
          StringBuffer packagePattern = new StringBuffer();
          for(int i = 0; i < num; i++) {
            if(i != 0) packagePattern.append(".");
            packagePattern.append(parts[i]);
          }
          int index = sourcePathPattern.lastIndexOf(packagePattern.toString());
          if(index > 0) {
            return sourcePath.substring(0, index-1);
          }
          num--;
        }
          
        //System.err.println("SourcePath: " + sourcePath);
        //String[] parts = packageName().split("\\.");
        int k = parts.length - 1;
        while(k >= 0 && !sourcePath.endsWith(parts[k])) {
          //System.err.println(sourcePath + " does not end with " + parts[k]);
          k--;
        }
        if(k >= 0) {
          for(int i = k; i >= 0; i--) {
            sourcePath = sourcePath.substring(0, sourcePath.lastIndexOf(parts[i]));
            //System.err.println("new candidate is " + sourcePath);
          }
        }
        if(sourcePath.equals(""))
          sourcePath = ".";
        //System.err.println("SourcePath after: " + sourcePath);
        return sourcePath;
        /*
        // extract first part of package name
        String prefix;
        int pos = packageName().indexOf('.');       // AST
        if(pos != -1)
          prefix = packageName().substring(0, pos-1);
        else
          prefix = packageName();
        // add separator
        prefix = prefix + java.io.File.separator;
        // find last occurance
        pos = sourceName.lastIndexOf(prefix);
        if(pos > 0 && !packageName().equals(""))
          return sourceName.substring(0, pos-1);
        */
      }
//=============GOP=============================================
      if(fromAuxiliary()){
      Program root=getProgram();
      return root.searchMainCuPath();
      }
//=============END=============================================
      if(pathName != null)
        return pathName;
      else
        return ".";
    }
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:112
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet importedFields(String name) {
      ASTNode$State state = state();
    SimpleSet importedFields_String_value = importedFields_compute(name);
    return importedFields_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet importedFields_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    for(int i = 0; i < getNumImportDecl(); i++)
      if(!getImportDecl(i).isOnDemand())
        for(Iterator iter = getImportDecl(i).importedFields(name).iterator(); iter.hasNext(); )
          set = set.add(iter.next());
    return set;
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:120
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet importedFieldsOnDemand(String name) {
      ASTNode$State state = state();
    SimpleSet importedFieldsOnDemand_String_value = importedFieldsOnDemand_compute(name);
    return importedFieldsOnDemand_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet importedFieldsOnDemand_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    for(int i = 0; i < getNumImportDecl(); i++)
      if(getImportDecl(i).isOnDemand())
        for(Iterator iter = getImportDecl(i).importedFields(name).iterator(); iter.hasNext(); )
          set = set.add(iter.next());
    return set;
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:141
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection importedMethods(String name) {
      ASTNode$State state = state();
    Collection importedMethods_String_value = importedMethods_compute(name);
    return importedMethods_String_value;
  }
  /**
   * @apilvl internal
   */
  private Collection importedMethods_compute(String name) {
    Collection list = new ArrayList();
    for(int i = 0; i < getNumImportDecl(); i++)
      if(!getImportDecl(i).isOnDemand())
        list.addAll(getImportDecl(i).importedMethods(name));
    return list;
  }
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:148
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection importedMethodsOnDemand(String name) {
      ASTNode$State state = state();
    Collection importedMethodsOnDemand_String_value = importedMethodsOnDemand_compute(name);
    return importedMethodsOnDemand_String_value;
  }
  /**
   * @apilvl internal
   */
  private Collection importedMethodsOnDemand_compute(String name) {
    Collection list = new ArrayList();
    for(int i = 0; i < getNumImportDecl(); i++)
      if(getImportDecl(i).isOnDemand())
        list.addAll(getImportDecl(i).importedMethods(name));
    return list;
  }
  /**
   * @attribute inh
   * @aspect LookupFullyQualifiedTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:102
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl lookupType(String packageName, String typeName) {
      ASTNode$State state = state();
    TypeDecl lookupType_String_String_value = getParent().Define_TypeDecl_lookupType(this, null, packageName, typeName);
    return lookupType_String_String_value;
  }
  protected java.util.Map lookupType_String_values;
  /**
   * @attribute inh
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:184
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupType(String name) {
    Object _parameters = name;
    if(lookupType_String_values == null) lookupType_String_values = new java.util.HashMap(4);
    if(lookupType_String_values.containsKey(_parameters)) {
      return (SimpleSet)lookupType_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
if(isFinal && num == state().boundariesCrossed) lookupType_String_values.put(_parameters, lookupType_String_value);
    return lookupType_String_value;
  }
  /**
   * @attribute inh
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:111
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupVariable(String name) {
      ASTNode$State state = state();
    SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
    return lookupVariable_String_value;
  }
  /**
   * @attribute inh
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:140
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection lookupMethod(String name) {
      ASTNode$State state = state();
    Collection lookupMethod_String_value = getParent().Define_Collection_lookupMethod(this, null, name);
    return lookupMethod_String_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:32
   * @apilvl internal
   */
  public CompilationUnit Define_CompilationUnit_compilationUnit(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return this;
    }
    return getParent().Define_CompilationUnit_compilationUnit(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:51
   * @apilvl internal
   */
  public boolean Define_boolean_isIncOrDec(ASTNode caller, ASTNode child) {
    if(caller == getTypeDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return getParent().Define_boolean_isIncOrDec(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:198
   * @apilvl internal
   */
  public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(caller == getImportDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    return !exceptionType.isUncheckedException();
  }
}
    if(caller == getTypeDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return !exceptionType.isUncheckedException();
    }
    return getParent().Define_boolean_handlesException(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:306
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
    if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return lookupType(name);
    }
    if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    SimpleSet result = SimpleSet.emptySet;
    for(Iterator iter = refined_TypeScopePropagation_CompilationUnit_Child_lookupType_String(name).iterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      if(typeDecl instanceof ParTypeDecl)
        result = result.add(((ParTypeDecl)typeDecl).genericDecl());
      else
        result = result.add(typeDecl);
    }
    return result;
  }
}
    return getParent().Define_SimpleSet_lookupType(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:27
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_allImportedTypes(ASTNode caller, ASTNode child, String name) {
    if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return importedTypes(name);
    }
    return getParent().Define_SimpleSet_allImportedTypes(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\QualifiedNames.jrag:92
   * @apilvl internal
   */
  public String Define_String_packageName(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return packageName();
    }
    return getParent().Define_String_packageName(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:69
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.PACKAGE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:625
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_TypeDecl_enclosingType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:652
   * @apilvl internal
   */
  public boolean Define_boolean_isNestedType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isNestedType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:666
   * @apilvl internal
   */
  public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
    if(caller == getTypeDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return getParent().Define_boolean_isMemberType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:679
   * @apilvl internal
   */
  public boolean Define_boolean_isLocalClass(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isLocalClass(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:700
   * @apilvl internal
   */
  public String Define_String_hostPackage(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return packageName();
    }
    return getParent().Define_String_hostPackage(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:732
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
    if(caller == getContextVarListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return null;
    }
    if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return null;
    }
    return getParent().Define_TypeDecl_hostType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:82
   * @apilvl internal
   */
  public String Define_String_destinationPath(ASTNode caller, ASTNode child) {
    if(caller == getContextVarListNoTransform()) {
      int index = caller.getIndexOfChild(child);
      return destinationPath();
    }
    if(caller == getImportDeclListNoTransform()) {
      int index = caller.getIndexOfChild(child);
      return destinationPath();
    }
    if(caller == getTypeDeclListNoTransform()) {
      int index = caller.getIndexOfChild(child);
      return destinationPath();
    }
    return getParent().Define_String_destinationPath(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:104
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getTypeDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    SimpleSet set = importedFields(name);
    if(!set.isEmpty()) return set;
    set = importedFieldsOnDemand(name);
    if(!set.isEmpty()) return set;
    return lookupVariable(name);
  }
}
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:133
   * @apilvl internal
   */
  public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
    if(caller == getTypeDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    Collection list = importedMethods(name);
    if(!list.isEmpty()) return list;
    list = importedMethodsOnDemand(name);
    if(!list.isEmpty()) return list;
    return lookupMethod(name);
  }
}
    return getParent().Define_Collection_lookupMethod(this, caller, name);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
