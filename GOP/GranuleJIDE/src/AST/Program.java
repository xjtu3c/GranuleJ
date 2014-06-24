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
 * @declaredat java.ast:1
 */
public class Program extends ASTNode<ASTNode> implements Cloneable, VariableScope {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    contextVariable_String_values = null;
    contextVariableMap_computed = false;
    contextVariableMap_value = null;
    FitAccessCounts_computed = false;
    searchMainFunction_computed = false;
    searchMainFunction_value = null;
    searchMainType_computed = false;
    searchMainType_value = null;
    lookupTypeDecl_String_String_values = null;
    getAuxiliaryClassDeclList_computed = false;
    getAuxiliaryClassDeclList_value = null;
    CreateClassTree_computed = false;
    CreateClassTree_value = null;
    CreateGranuleTree_computed = false;
    CreateGranuleTree_value = null;
    typeObject_computed = false;
    typeObject_value = null;
    typeCloneable_computed = false;
    typeCloneable_value = null;
    typeSerializable_computed = false;
    typeSerializable_value = null;
    typeBoolean_computed = false;
    typeBoolean_value = null;
    typeByte_computed = false;
    typeByte_value = null;
    typeShort_computed = false;
    typeShort_value = null;
    typeChar_computed = false;
    typeChar_value = null;
    typeInt_computed = false;
    typeInt_value = null;
    typeLong_computed = false;
    typeLong_value = null;
    typeFloat_computed = false;
    typeFloat_value = null;
    typeDouble_computed = false;
    typeDouble_value = null;
    typeString_computed = false;
    typeString_value = null;
    typeVoid_computed = false;
    typeVoid_value = null;
    typeNull_computed = false;
    typeNull_value = null;
    unknownType_computed = false;
    unknownType_value = null;
    hasPackage_String_values = null;
    lookupType_String_String_values = null;
    unknownConstructor_computed = false;
    unknownConstructor_value = null;
    searchMainCuPath_computed = false;
    searchMainCuPath_value = null;
    destinationPath_computed = false;
    destinationPath_value = null;
    wildcards_computed = false;
    wildcards_value = null;
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
  public Program clone() throws CloneNotSupportedException {
    Program node = (Program)super.clone();
    node.contextVariable_String_values = null;
    node.contextVariableMap_computed = false;
    node.contextVariableMap_value = null;
    node.FitAccessCounts_computed = false;
    node.searchMainFunction_computed = false;
    node.searchMainFunction_value = null;
    node.searchMainType_computed = false;
    node.searchMainType_value = null;
    node.lookupTypeDecl_String_String_values = null;
    node.getAuxiliaryClassDeclList_computed = false;
    node.getAuxiliaryClassDeclList_value = null;
    node.CreateClassTree_computed = false;
    node.CreateClassTree_value = null;
    node.CreateGranuleTree_computed = false;
    node.CreateGranuleTree_value = null;
    node.typeObject_computed = false;
    node.typeObject_value = null;
    node.typeCloneable_computed = false;
    node.typeCloneable_value = null;
    node.typeSerializable_computed = false;
    node.typeSerializable_value = null;
    node.typeBoolean_computed = false;
    node.typeBoolean_value = null;
    node.typeByte_computed = false;
    node.typeByte_value = null;
    node.typeShort_computed = false;
    node.typeShort_value = null;
    node.typeChar_computed = false;
    node.typeChar_value = null;
    node.typeInt_computed = false;
    node.typeInt_value = null;
    node.typeLong_computed = false;
    node.typeLong_value = null;
    node.typeFloat_computed = false;
    node.typeFloat_value = null;
    node.typeDouble_computed = false;
    node.typeDouble_value = null;
    node.typeString_computed = false;
    node.typeString_value = null;
    node.typeVoid_computed = false;
    node.typeVoid_value = null;
    node.typeNull_computed = false;
    node.typeNull_value = null;
    node.unknownType_computed = false;
    node.unknownType_value = null;
    node.hasPackage_String_values = null;
    node.lookupType_String_String_values = null;
    node.unknownConstructor_computed = false;
    node.unknownConstructor_value = null;
    node.searchMainCuPath_computed = false;
    node.searchMainCuPath_value = null;
    node.destinationPath_computed = false;
    node.destinationPath_value = null;
    node.wildcards_computed = false;
    node.wildcards_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Program copy() {
      try {
        Program node = (Program)clone();
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
  public Program fullCopy() {
    Program res = (Program)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:203
   */
  
     //=========================================GOP dispatch message to primary method============
     //syn Program ASTNode.getProgram()=getParent().getProgram();	
     //eq Program.getProgram()=this; 
     boolean isRegisterMethodDecl=false;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:22
   */
  

  protected BytecodeReader bytecodeReader;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:23
   */
  public void initBytecodeReader(BytecodeReader r) { bytecodeReader = r; }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:24
   */
  
  protected JavaParser javaParser;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:25
   */
  public void initJavaParser(JavaParser p) { javaParser = p; }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:35
   */
  public void addSourceFile(String name) {
    sourceFiles.addSourceFile(name);

  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:41
   */
  public Iterator compilationUnitIterator() {
    initPaths();
    return new Iterator() {
      int index = 0;
      public boolean hasNext() {
        return index < getNumCompilationUnit() || !sourceFiles.isEmpty();
      }
      public Object next() {
        if(getNumCompilationUnit() == index) {
          String typename = (String)sourceFiles.keySet().iterator().next();
          CompilationUnit u = getCompilationUnit(typename);
          if(u != null) {
            addCompilationUnit(u);
            getCompilationUnit(getNumCompilationUnit()-1);
          }
          else
            throw new Error("File " + typename + " not found");
        }
        return getCompilationUnit(index++);
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:70
   */
  public InputStream getInputStream(String name) {
    initPaths();
    try {
      for(Iterator iter = classPath.iterator(); iter.hasNext(); ) {
        PathPart part = (PathPart)iter.next();
        if(part.selectCompilationUnit(name))
          return part.is;
      }
    }
    catch(IOException e) {
    }
    throw new Error("Could not find nested type " + name);
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:88
   */
  public CompilationUnit getCompilationUnit(String name) {
    initPaths();
    try {
      if(sourceFiles.selectCompilationUnit(name))
        return sourceFiles.getCompilationUnit();
      PathPart sourcePart = null;
      PathPart classPart = null;
      for(Iterator iter = sourcePath.iterator(); iter.hasNext() && sourcePart == null; ) {
        PathPart part = (PathPart)iter.next();
        if(part.selectCompilationUnit(name))
          sourcePart = part;
      }
      for(Iterator iter = classPath.iterator(); iter.hasNext() && classPart == null; ) {
        PathPart part = (PathPart)iter.next(); 
        if(part.selectCompilationUnit(name))
          classPart = part;
      }
      if(sourcePart != null && (classPart == null || classPart.age <= sourcePart.age)) {
        CompilationUnit unit = sourcePart.getCompilationUnit();
        int index = name.lastIndexOf('.');
        if(index == -1)
          return unit;
        String pkgName = name.substring(0, index);       
        if(pkgName.equals(unit.getPackageDecl()))
          return unit;
      }
      if(classPart != null) {
        CompilationUnit unit = classPart.getCompilationUnit();
        int index = name.lastIndexOf('.');
        if(index == -1)
          return unit;
        String pkgName = name.substring(0, index);
        if(pkgName.equals(unit.getPackageDecl()))
          return unit;
      }
      return null;
    }
    catch(IOException e) {
    }
    return null;
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:131
   */
  public boolean isPackage(String name) {
    if(sourceFiles.hasPackage(name))
      return true;
    for(Iterator iter = classPath.iterator(); iter.hasNext(); ) {
      PathPart part = (PathPart)iter.next();
      if(part.hasPackage(name))
        return true;
    }
    for(Iterator iter = sourcePath.iterator(); iter.hasNext(); ) {
      PathPart part = (PathPart)iter.next();
      if(part.hasPackage(name))
        return true;
    }
    return false;
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:161
   */
  

  private boolean pathsInitialized = false;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:162
   */
  
  private java.util.ArrayList classPath;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:163
   */
  
  private java.util.ArrayList sourcePath;
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:164
   */
  
  private FileNamesPart sourceFiles = new FileNamesPart(this);
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:166
   */
  public void pushClassPath(String name) {
    PathPart part = PathPart.createSourcePath(name, this);
    if(part != null) {
      sourcePath.add(part);
      System.out.println("Pushing source path " + name);
    }
    else
      throw new Error("Could not push source path " + name);
    part = PathPart.createClassPath(name, this);
    if(part != null) {
      classPath.add(part);
      System.out.println("Pushing class path " + name);
    }
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:180
   */
  public void popClassPath() {
    if(sourcePath.size() > 0)
      sourcePath.remove(sourcePath.size()-1);
    if(classPath.size() > 0)
      classPath.remove(classPath.size()-1);
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:186
   */
  public void initPaths() {
    if(!pathsInitialized) {
      pathsInitialized = true;

      //System.err.println("Initializing class paths");
      
      ArrayList classPaths = new ArrayList();
      ArrayList sourcePaths = new ArrayList();
      
      String[] bootclasspaths;
      if(options().hasValueForOption("-bootclasspath"))
        bootclasspaths = options().getValueForOption("-bootclasspath").split(File.pathSeparator);
      else
        bootclasspaths = System.getProperty("sun.boot.class.path").split(File.pathSeparator);
      for(int i = 0; i < bootclasspaths.length; i++) {
        classPaths.add(bootclasspaths[i]);
        //System.err.println("Adding classpath " + bootclasspaths[i]);
      }
      
      String[] extdirs;
      if(options().hasValueForOption("-extdirs"))
        extdirs = options().getValueForOption("-extdirs").split(File.pathSeparator);
      else
        extdirs = System.getProperty("java.ext.dirs").split(File.pathSeparator);
      for(int i = 0; i < extdirs.length; i++) {
        classPaths.add(extdirs[i]);
        //System.err.println("Adding classpath " + extdirs[i]);
      }

      String[] userClasses = null;
      if(options().hasValueForOption("-classpath"))
        userClasses = options().getValueForOption("-classpath").split(File.pathSeparator);
      else {
        String s = System.getProperty("java.class.path");
        if(s != null && s.length() > 0) {
          s = s + File.pathSeparator + "."; // TODO; This should not be necessary
          //s=s+"granule"+"."+"util"+".";
          userClasses = s.split(File.pathSeparator);
        }
        else
          userClasses = ".".split(File.pathSeparator);
      }
      if(!options().hasValueForOption("-sourcepath")) {
        for(int i = 0; i < userClasses.length; i++) {
          classPaths.add(userClasses[i]);
          sourcePaths.add(userClasses[i]);
          //System.err.println("Adding classpath/sourcepath " + userClasses[i]);
        }
      }
      else {
        for(int i = 0; i < userClasses.length; i++) {
          classPaths.add(userClasses[i]);
          //System.err.println("Adding classpath " + userClasses[i]);
        }
        userClasses = options().getValueForOption("-sourcepath").split(File.pathSeparator);
        for(int i = 0; i < userClasses.length; i++) {
          sourcePaths.add(userClasses[i]);
          //System.err.println("Adding sourcepath " + userClasses[i]);
        }
      }
        
      classPath = new ArrayList();
      sourcePath = new ArrayList();
      
      for(Iterator iter = classPaths.iterator(); iter.hasNext(); ) {
        String s = (String)iter.next();
        PathPart part = PathPart.createClassPath(s, this);
        if(part != null) {
          classPath.add(part);
          //System.out.println("Adding classpath " + s);
        }
        else if(options().verbose())
          System.out.println("Warning: Could not use " + s + " as class path");
      }
      for(Iterator iter = sourcePaths.iterator(); iter.hasNext(); ) {
        String s = (String)iter.next();
        PathPart part = PathPart.createSourcePath(s, this);
        if(part != null) {
          sourcePath.add(part);
          //System.out.println("Adding sourcepath " + s);
        }
        else if(options().verbose())
          System.out.println("Warning: Could not use " + s + " as source path");
      }
    }
  }
  /**
   * @ast method 
   * @aspect ClassPath
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ClassPath.jrag:540
   */
  public void simpleReset() {
    lookupType_String_String_values = new HashMap();
    hasPackage_String_values = new HashMap();
    List list = new List();
    for(int i = 0; i < getNumCompilationUnit(); i++) {
      CompilationUnit unit = getCompilationUnit(i);
      if(!unit.fromSource()) {
        list.add(unit);
      }
    }
    setCompilationUnitList(list);
  }
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:211
   */
  public void errorCheck(Collection collection) {
    for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit)iter.next();
      if(cu.fromSource()) {
        cu.collectErrors();
        collection.addAll(cu.errors);
      }
    }
  }
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:220
   */
  public void errorCheck(Collection collection, Collection warn) {
    for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit)iter.next();
      if(cu.fromSource()) {
        cu.collectErrors();
        collection.addAll(cu.errors);
        warn.addAll(cu.warnings);
      }
    }
  }
  /**
   * @ast method 
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:241
   */
  public boolean errorCheck() {
    Collection collection = new LinkedList();
    errorCheck(collection);
    if(collection.isEmpty())
      return false;
    System.out.println("Errors:");
    for(Iterator iter = collection.iterator(); iter.hasNext(); ) {
      String s = (String)iter.next();
      System.out.println(s);
    }
    return true;
  }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:210
   */
  public MethodDecl createContextVarMethodDecl()
  {  
	  Block bl=new Block();	 
	  int w=0;
	  Modifier ms=new Modifier("protected");
	  List<Modifier> lll= new List<Modifier>();
	  lll.add(ms);
	  Modifiers mss=new Modifiers(lll);
	  String s2="void";
	  Access access=new TypeAccess(s2);
	  List<ParameterDeclaration> l3=new List<ParameterDeclaration>();
	  List<Access> l4=new List<Access>();   
	  List<Stmt> ll=new List<Stmt>();
	  for(Iterator iterc= compilationUnitIterator(); iterc.hasNext(); ) {
          CompilationUnit cu=(CompilationUnit)iterc.next(); 
        if(cu.fromSource())
        {
    	  if(cu.getNumContextVar()>0){
    		    w+=cu.getNumContextVar();    		     		    
    		    for(int j=0; j<cu.getNumContextVar();j++){
    		     ASTNode node=cu.getContextVar(j);
    		     if(node instanceof ContextVarDeclaration){  
    		     ContextVarDeclaration decl=(ContextVarDeclaration)node; 
    		     ll.add(decl.createContextVarBlockStmt());
    		     }
    		     else if (node instanceof List)
    		     {
    		        List listvar = (List)node;
    		        for(Iterator iter=listvar.iterator(); iter.hasNext(); ) {
    		        VariableDeclaration decl=(VariableDeclaration)iter.next();
    		        ll.add(decl.createContextVarBlockStmt());
    		      }
    		     }
    		    }    				 
    		  }    		  
        }
	 }
	  if(w==0)
	  {
	  bl=new Block();
	  }
	  else {
      bl=new Block(ll);
	  }
	  Opt<Block> opt=new Opt<Block>(bl);
	  MethodDecl method=new MethodDecl(mss,access,"gg",l3,l4,opt);	
	  return method;
  }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:258
   */
  public CompilationUnit createContextVarClassDecl(){	 
        Modifier ms=new Modifier("public");
	    List<Modifier> lll= new List<Modifier>();
		lll.add(ms);
		Modifiers mss=new Modifiers(lll);
		//String classname=searchMainFunction();
                String classname="";
		String packname="";
		ClassDecl hostclass=searchMainType();
		if(hostclass!=null){
		classname=hostclass.name();
		packname=hostclass.packageName();
		}
		String s=classname+"$publish$";	
		List<BodyDecl> b=new List<BodyDecl>();
		b.add(createContextVarMethodDecl());
		Opt<Access> oppt=new Opt<Access>();
	        List<Access> l4=new List<Access>();
		ClassDecl sc=new ClassDecl(mss,s,oppt,l4,b);
		List<TypeDecl> list=new List<TypeDecl>();
	        list.add(sc);
		CompilationUnit unit=new CompilationUnit(packname,new List(),new List(),list);
                if(!classname.equals(""))
		unit.setFromAuxiliary(true);
		return unit;		
  }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:284
   */
  public CompilationUnit createExecuteModeClass(){	
	 List<Modifier> lll=new List<Modifier>();
	 lll.add(new Modifier("public"));
	 Modifiers mss=new Modifiers(lll);
	 String s="fit$fit";
	 List<BodyDecl> b=new List<BodyDecl>();
	 b.add(createExecuteModeState());
	 Opt<Access> oppt=new Opt<Access>();
	 List<Access> l4=new List<Access>();
	 ClassDecl sc=new ClassDecl(mss,s,oppt,l4,b);
	 List<TypeDecl> list=new List<TypeDecl>();
	 list.add(sc);
	 String packname="";
         	 String classname="";
	 ClassDecl hostclass=searchMainType();
	 if(hostclass!=null){
	 classname=hostclass.name();
	 packname=hostclass.packageName();
	 }
	 CompilationUnit unit=new CompilationUnit(packname,new List(),new List(),list);
         //String classname=searchMainFunction();
	 if(!classname.equals(""))
	 unit.setFromAuxiliary(true);
	 return unit;	
 }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:332
   */
  public FieldDeclaration createExecuteModeState()
 {
	 List<Modifier> lst=new List<Modifier>();
	 lst.add(new Modifier("protected"));
	 lst.add(new Modifier("static"));
	 Modifiers mss=new Modifiers(lst);
	 Access sa=new TypeAccess("int");
	 String fieldname="fit";
	 Expr value=new IntegerLiteral("0");
	 if(FitAccessCounts()>=1)
	 value=new IntegerLiteral("1");
	 Opt<Expr> opt=new Opt<Expr>(value);	 
	 FieldDeclaration ff=new FieldDeclaration(mss,sa,fieldname,opt);
	 return ff;
 }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:35
   */
  

   boolean isRegisterToShadowClass=false;
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:69
   */
   
//===================register shadowclass ================================

 protected boolean isRegisterShadowClass=false;
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1731
   */
  		 
		/*rewrite AssignSimpleExpr{
				when(hostType().isShadowClassDecl()&&(getDest().varDecl() instanceof ContextVarDeclaration))
				to AbstractDot {					   
					   String methodname=new String("modifyContext");
				       List<Expr> l =new List<Expr>();
				       StringLiteral para1=new StringLiteral(getDest().toString());
				       l.add(para1);
				       String typename=sourceType().typeName();				     
				       if(typename.equals("java.lang.String")){
				       l.add(getSource());
				       }
				       else {
				       String method=new String("valueOf");
				       List<Expr> ll=new List<Expr>();
				       ll.add(getSource());				      
				       TypeAccess tta=new TypeAccess("String");
				       ParseName ppn= new ParseName("valueOf");
				       MethodAccess mm=new MethodAccess(method,ll);		    	  
				       AbstractDot ddd=new AbstractDot(tta,ppn);
				       ddd.replaceLast(mm);
				       l.add(ddd);
				       }
				       TypeAccess ta= new TypeAccess("GopContext");
				       ParseName pn= new ParseName("modifyContext");
				       AbstractDot d = new AbstractDot(ta,pn);
				       MethodAccess m = new MethodAccess(methodname,l);
				       d.replaceLast(m);				   
				       return d;  
				}
			}*/
	/*rewrite VarAccess { 
		  when(hostType().isGranuleDecl()&&(decl() instanceof ContextVarDeclaration))
		  to AbstractDot
		  {		  
			   String methodname = new String("getContext");
		       List<Expr> l =new List<Expr>();
		       StringLiteral para1 = new StringLiteral(this.getID());
		       l.add(para1);  
		       TypeAccess ta= new TypeAccess("GopContext");
		       List<Expr> p1=new List<Expr>();
		       Opt<TypeDecl> p2=new Opt<TypeDecl>();
		       ClassInstanceExpr taa=new ClassInstanceExpr(ta,p1,p2);
		       ParseName pn = new ParseName("getContext");
		       AbstractDot d = new AbstractDot(taa,pn);
		       MethodAccess m = new MethodAccess(methodname,l);
		       d.replaceLast(m);
		       String typename=decl().type().typeName();	      
		 	   char typechar=Character.toUpperCase(typename.charAt(0));
		       String descname=typechar+typename.substring(1);
		       String conversion="parse"+descname;
		       String classname=decl().type().boxed().typeName();
		       TypeAccess tta=new TypeAccess(classname);
		       ParseName ppn=new ParseName(conversion);
		       List<Expr> ll=new List<Expr>();
		       ll.add(d);
		       MethodAccess mm= new MethodAccess(conversion,ll);
		       AbstractDot dd=new AbstractDot(tta,ppn);
		       dd.replaceLast(mm);
		       return dd;
		  }
		 }	
		 rewrite AssignSimpleExpr{
				when(hostType().isClassDecl()&&(getDest().varDecl() instanceof ContextVarDeclaration))
				to AbstractDot {
					   String methodname=new String("modifyContext");
				       List<Expr> l =new List<Expr>();
				       StringLiteral para1=new StringLiteral(getDest().toString());
				       l.add(para1);
				       String method=new String("valueOf");
				       List<Expr> ll=new List<Expr>();
				       ll.add(getSource());
				       TypeAccess tta=new TypeAccess("String");
				       ParseName ppn= new ParseName("valueOf");
				       MethodAccess mm=new MethodAccess(method,ll);		    	  
				       AbstractDot ddd=new AbstractDot(tta,ppn);
				       ddd.replaceLast(mm);
				       l.add(ddd);
				       TypeAccess ta= new TypeAccess("GopContext");
				       List<Expr> p1=new List<Expr>();
				       Opt<TypeDecl> p2=new Opt<TypeDecl>();
				       ClassInstanceExpr taa=new ClassInstanceExpr(ta,p1,p2);				      
				       ParseName pn= new ParseName("modifyContext");
				       AbstractDot d=new AbstractDot(taa,pn);
				       MethodAccess m = new MethodAccess(methodname,l);
				       d.replaceLast(m);
				       return d;  
				}
		 }*/
/*rewrite FieldDeclaration{
	when(hostType().isShadowClassDecl()&&hasSameField())
	to FieldDeclaration {
	   if(!variableRef.isEmpty()){
		   FieldDeclaration ff=(FieldDeclaration)variableRef.get(0);
		   String typename=getID()+"%"+getTypeAccess();
		   for(Iterator iter=fieldToVarMap().iterator();iter.hasNext();)
			{
				VarAccess var=(VarAccess)iter.next();			
				var.setID(typename);
			}
		   setID(typename);		
	   }
	   return this;
	}
}*/
		 
 /*
  * 
  * rewrite VarAccess {
   	when(!hasUpdate&&sameVariables().size()>0)
   	to VarAccess
   	{
   	    FieldDeclaration f=(FieldDeclaration)decl();    	    
   	    if(!f.variableRef.isEmpty()&&!f.isUpdateName){
   	     FieldDeclaration ff=(FieldDeclaration)f.variableRef.get(0);
  		 String typename=f.getID()+"%"+ff.getTypeAccess();
  		 f.setID(typename);
  		 f.isUpdateName=true;	
   	    }
        //if(!isUpdateName)
   	   	//updateName(f);
   		String typename="";   		
   		for(Iterator iter1=samevariables.iterator();iter1.hasNext();){
   		VarAccess var=(VarAccess)iter1.next();   		
   		typename=var.getID()+"%int";
   		var.setID(typename);
   		var.hasUpdate=true;
   		}	  		
        return this;
     }
}
 public static boolean VarAccess.hasUpdate=false;
 public static boolean FieldDeclaration.isUpdateName=false;
 public static boolean ASTNode.isUpdateName=false;
 public static FieldDeclaration ASTNode.updateName(FieldDeclaration decl)
 {
	 if(!decl.variableRef.isEmpty())
	 {
		 FieldDeclaration ff=(FieldDeclaration)decl.variableRef.get(0);
   		 String typename=decl.getID()+"%"+ff.getTypeAccess();
   		 decl.setID(typename);
   		 isUpdateName=true;
	 }
	 return decl;
 }

  protected boolean Program.isSameVariable=false;
  protected LinkedList VarAccess.samevariables=new LinkedList();
  syn LinkedList VarAccess.sameVariables() circular [new LinkedList()]{
		Program root=getProgram();
		if(!root.isSameVariable) {
		   root.registerVariables();
		  root.isSameVariable=true;
	}
		return samevariables;
	}    
   public void ASTNode.registerVariables(){
		for(int i=0; i<getNumChild(); i++) {
			getChild(i).registerVariables();
		}
   }
  public void VarAccess.registerVariables(){	
	   if(hostType().isShadowClassDecl()&&(decl() instanceof FieldDeclaration)&&((FieldDeclaration)decl()).hasSameField()){
		   if(!samevariables.contains(this))
			 samevariables.add(this);
	   }
  }
  *
  *
  */
  
  protected boolean isMapSet=false;
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:2055
   */
  public boolean hasGopAuxiliaryFile(){
for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
    CompilationUnit cu = (CompilationUnit)iter.next();
    if(cu.hasGopContextUsed)
    return true;
  }
   return false;
}
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:2064
   */
  public boolean hasGopRemoteFile(){
	for(Iterator iter=compilationUnitIterator();iter.hasNext();)
	{
		CompilationUnit cu=(CompilationUnit)iter.next();
		if(cu.hasGopRemoteUsed)
		return true;
	}
	return false;
}
  /**
   * @ast method 
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:165
   */
  public ArrayList SortContextVariable()
	{
		ArrayList contextv = new ArrayList();
	    ClassTree classtree = CreateClassTree();
	    if (classtree == null)
	    return null;
	    ArrayList<ClassNode> list = new ArrayList(classtree.toList());
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				ClassNode classnode = (ClassNode) iter.next();
				if (classnode.getClassname().equals("Object"))
					continue;
				TypeDecl decl = lookupType(classnode.getClassdecl().packageName(),classnode.getClassname());

				if (decl == null)
					return null;
				if (decl.isClassDecl() && ((ClassDecl) decl).hasGranuleClass()) {
					LinkedList granules = new LinkedList(
							((ClassDecl) decl).granuleclasses);
					LinkedList contextsort = new LinkedList();
					if (granules.size() > 1) {
						for (Iterator iter0 = granules.iterator(); iter0.hasNext();) {
							GranuleDecl granuledecl = (GranuleDecl) iter0.next();
							String contextname = "";
							for (Iterator iter1 = granuledecl.fieldsIterator(); iter1
									.hasNext();) {
								Object decll = iter1.next();
								if (decll instanceof ContextVarDeclaration) {
									ContextVarDeclaration deccl = (ContextVarDeclaration) decll;
									contextname = deccl.name();
									break;
								}
								continue;
							}
							if (!granuledecl.hasSuperGranuleSpec()) {
								if (!contextsort.contains(contextname))
									contextsort.add(contextname);
							} else {
								if (!contextsort.contains(contextname))
									contextsort.add(contextname);
								while (granuledecl.hasSuperGranuleSpec()) {
									TypeAccess typeaccess = (TypeAccess) (granuledecl
											.getSuperGranuleSpec()
											.getSuperGranuleAccess());
									TypeDecl supgranule = granuledecl.superGranule();
									if (supgranule == null)
										break;
									if (supgranule != null) {
										GranuleDecl supergranule = (GranuleDecl) supgranule;
										String granulename1 = supergranule.name();
										String contextname1 = "";
										for (Iterator iter2 = supergranule
												.fieldsIterator(); iter2.hasNext();) {
											Object decll = iter2.next();
											if (decll instanceof ContextVarDeclaration) {
												ContextVarDeclaration deccl = (ContextVarDeclaration) decll;
												contextname1 = deccl.name();
												break;
											}
											continue;
										}
										if (!contextname1.equals(contextname)) {
											if (contextsort.contains(contextname1))
												contextsort.remove(contextname1);
											int m = contextsort
													.indexOf(contextname);
											contextsort.add(m + 1, contextname);
											contextsort.set(m, contextname1);
										}
										granuledecl = supergranule;
									}
								}
							}
						}
					} else {
						for (Iterator iter0 = granules.iterator(); iter0.hasNext();) {
							GranuleDecl granuledecl = (GranuleDecl) iter0.next();
							String granulename = granuledecl.name();
							String contextname = "";
							for (Iterator iter1 = granuledecl.fieldsIterator(); iter1
									.hasNext();) {
								Object decll = iter1.next();
								if (decll instanceof ContextVarDeclaration) {
									ContextVarDeclaration deccl = (ContextVarDeclaration) decll;
									contextname = deccl.name();
									break;
								}
								continue;
							}
							if (!contextsort.contains(contextname))
								contextsort.add(contextname);
						}
					}
					for (Iterator iter7 = contextsort.iterator(); iter7.hasNext();) {
						String contextnode = (String) iter7.next();
						if (!contextv.contains(contextnode))
							contextv.add(contextnode);
					}
				}
			}
			return contextv;
	}
  /**
   * @ast method 
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:281
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
	 			  		 System.out.println("super granule name is "+supgranule.name());
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
	 						   if(supgranule!=null){
	 							   GranuleDecl supergranule=(GranuleDecl)supgranule;
	 							   System.out.println("super granule name is a "+supgranule.name());
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:453
   */
  public void GatherGranule()
	   {
	   		for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
	          CompilationUnit unit = (CompilationUnit)iter.next();
	          if(unit.fromSource()) {
	          	 unit.GatherGranule();
	          }
	       }
	   }
  /**
   * @ast method 
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:473
   */
  public ArrayList getCVsFromGranuleT(GranuleTree tree)
	    {
	    	ArrayList list=new ArrayList();
	    	ArrayList<GranuleTreeNode> listGNode=tree.toList();
	    	for(int i=1;i<listGNode.size();i++){
	        //System.out.println("get CVs for Granule Tree : "+listGNode.get(i).getContextname());
	        String contextname=listGNode.get(i).getContextname();
	        if(!list.contains(contextname))
	        list.add(contextname);
	    	}
            return list;
      }
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:139
   */
  
	
	boolean subclassFilled = false;
  /**
   * @ast method 
   * @aspect ImplicitConstructor
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:471
   */
     
  
boolean isRegisterToClassInstance=false;
  /**
   * @ast method 
   * @aspect LookupFullyQualifiedTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:107
   */
  


  public int classFileReadTime;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:340
   */
  public boolean addSourceFileIfNew(String name) {
        for (CompilationUnit cu : getCompilationUnitListNoTransform()) {
            if (cu.fromSource() && cu.relativeName().equals(name))
                return false;
        }
        addSourceFile(name);
        return true;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:774
   */
  
	
	boolean subclassCrossRefsFilledIn = false;
  /**
   * @ast method 
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:13
   */
  	
	boolean overridesCrossRefsFilledIn = false;
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:23
   */
  public void toString(StringBuffer s) {
    for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit)iter.next();
      if(cu.fromSource()) { 
        cu.toString(s);
      }
    }
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:950
   */
  public String dumpTree() {
    StringBuffer s = new StringBuffer();
    for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit)iter.next();
      if(cu.fromSource()) { 
        s.append(cu.dumpTree());
      }
    }
    return s.toString();
  }
  /**
   * @ast method 
   * @aspect PrimitiveTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrimitiveTypes.jrag:13
   */
  

  private boolean initPrimTypes = false;
  /**
   * @ast method 
   * @aspect PrimitiveTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrimitiveTypes.jrag:15
   */
  public void addPrimitiveTypes() {
    if(!initPrimTypes) {
      initPrimTypes = true;	
    CompilationUnit u = new CompilationUnit();
    u.setPackageDecl(PRIMITIVE_PACKAGE_NAME);
    addCompilationUnit(u);

    TypeDecl classDecl = generateUnknownType();
    u.addTypeDecl(classDecl);
    TypeDecl unknown = classDecl;

    classDecl = generatePrimitiveType(new BooleanType(), "boolean", unknown);
    u.addTypeDecl(classDecl);
    
    classDecl = generatePrimitiveType(new DoubleType(), "double", unknown);
    u.addTypeDecl(classDecl);
    
    classDecl = generatePrimitiveType(new FloatType(), "float", classDecl);
    u.addTypeDecl(classDecl);
    
    classDecl = generatePrimitiveType(new LongType(), "long", classDecl);
    u.addTypeDecl(classDecl);
    
    classDecl = generatePrimitiveType(new IntType(), "int", classDecl);
    u.addTypeDecl(classDecl);
    TypeDecl intDecl = classDecl;
    
    classDecl = generatePrimitiveType(new ShortType(), "short", classDecl);
    u.addTypeDecl(classDecl);
    
    classDecl = generatePrimitiveType(new ByteType(), "byte", classDecl);
    u.addTypeDecl(classDecl);
    
    classDecl = generatePrimitiveType(new CharType(), "char", intDecl);
    u.addTypeDecl(classDecl);
    
    classDecl = new NullType();
    classDecl.setModifiers(new Modifiers(new List().add(new Modifier("public"))));
    classDecl.setID("null");
    u.addTypeDecl(classDecl);

    classDecl = new VoidType();
    classDecl.setModifiers(new Modifiers(new List().add(new Modifier("public"))));
    classDecl.setID("void");
    u.addTypeDecl(classDecl);

    }
  }
  /**
   * @ast method 
   * @aspect PrimitiveTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrimitiveTypes.jrag:64
   */
  public TypeDecl generatePrimitiveType(PrimitiveType type, String name, TypeDecl superType) {
    type.setModifiers(new Modifiers(new List().add(new Modifier("public"))));
    type.setID(name);
    if(superType != null)
      type.setSuperClassAccess(superType.createQualifiedAccess());
    return type;
  }
  /**
   * @ast method 
   * @aspect PrimitiveTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrimitiveTypes.jrag:72
   */
  private TypeDecl generateUnknownType() {   
    ClassDecl classDecl = new UnknownType();
    classDecl.setModifiers(new Modifiers(new List().add(new Modifier("public"))));
    classDecl.setID("Unknown");
    MethodDecl methodDecl = new MethodDecl(
        new Modifiers(new List().add(
          new Modifier("public")
        )),
        new PrimitiveTypeAccess("Unknown"),
        "unknown",
        new List(),
        new List(),
        new Opt()
    );
    classDecl.addBodyDecl(methodDecl);
    FieldDeclaration fieldDecl = new FieldDeclaration(
        new Modifiers(new List().add(
          new Modifier("public")
        )),
        new PrimitiveTypeAccess("Unknown"),
        "unknown",
        new Opt()
    );
    classDecl.addBodyDecl(fieldDecl);   
    ConstructorDecl constrDecl = new ConstructorDecl(
      new Modifiers(new List().add(new Modifier("public"))),
      "Unknown",
      new List(),
      new List(),
      new Opt(),
      new Block()
    );
    classDecl.addBodyDecl(constrDecl);
      
    return classDecl;
  }
  /**
   * @ast method 
   * @aspect BaseClassMapping
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\BaseClassMapping.jrag:19
   */
  public boolean containsBaseClass(ClassDecl cd){
	    return baseMapShadows.containsKey(cd);	
	}
  /**
   * @ast method 
   * @aspect BaseClassMapping
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\BaseClassMapping.jrag:23
   */
  public Collection getSpecificShadowSet(ClassDecl cd){
	    if(containsBaseClass(cd)){
		 return (ArrayList<ShadowClassDecl>)baseMapShadows.get(cd);
		}
        return new ArrayList<ShadowClassDecl>();	
	}
  /**
   * @ast method 
   * @aspect BaseClassMapping
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\BaseClassMapping.jrag:30
   */
    
   
    private HashMap<ClassDecl, ArrayList<ShadowClassDecl>> baseMapShadows= new HashMap<ClassDecl, ArrayList<ShadowClassDecl>>();
  /**
   * @ast method 
   * @aspect BaseClassMapping
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\BaseClassMapping.jrag:32
   */
  
	
	private boolean firstVisit=false;
  /**
   * @ast method 
   * @aspect BaseClassMapping
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\BaseClassMapping.jrag:34
   */
  public ShadowClassDecl getRedirectType(ClassDecl cd){
	   if(baseMapShadows.containsKey(cd)){
	     ArrayList<ShadowClassDecl> alist=(ArrayList<ShadowClassDecl>)baseMapShadows.get(cd);
	     return (ShadowClassDecl)alist.get(alist.size()-1);
	   }
	   return null;
	}
  /**
   * @ast method 
   * @aspect BaseClassMapping
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\BaseClassMapping.jrag:42
   */
  public void sortClassMapping(){
	     Iterator iter=baseMapShadows.entrySet().iterator();
	     while(iter.hasNext()){
		  Map.Entry entry=(Map.Entry)iter.next(); 
		  ArrayList<ShadowClassDecl> val=(ArrayList<ShadowClassDecl>)entry.getValue();
		  if(val.isEmpty()) iter.remove();
		  else if(val.size()>1){
		    Collections.sort(val, (new Comparator<ShadowClassDecl>() {
            public int compare(ShadowClassDecl s1, ShadowClassDecl s2) {
                 if(s2.instanceOf(s1))
				 return 1;
                 else 
                 return 0;
            }
          }));
          //for(ShadowClassDecl u : val){
            // System.out.println(u.name());	
            //}			 
		  }
		 }     
   }
  /**
   * @ast method 
   * @aspect BaseClassMapping
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\BaseClassMapping.jrag:63
   */
  public void collectClassMapping(){
	    if(!firstVisit){
	    Iterator<CompilationUnit> cuIter = compilationUnitIterator();
        while (cuIter.hasNext()) {
            CompilationUnit cu = cuIter.next();
            if (cu.fromSource()) {
	         for(TypeDecl td:cu.getTypeDeclListNoTransform()){
			    if(td instanceof ClassDecl){
				   if(!baseMapShadows.containsKey((ClassDecl)td)){
				      baseMapShadows.put((ClassDecl)td, new ArrayList<ShadowClassDecl>());
				   }				
				} 
				else if(td instanceof ShadowClassDecl){
				   ShadowClassDecl scd=(ShadowClassDecl)td;
				   ClassDecl cd=scd.seedClass();
				   if(baseMapShadows.containsKey(cd)){
				    ArrayList<ShadowClassDecl> list=baseMapShadows.get(cd);
					if(!list.contains(scd)){
					  list.add(scd);
                      baseMapShadows.put(cd,list);					  
                    }					  
				   }
				   else{
				   ArrayList<ShadowClassDecl> new_list=new ArrayList<ShadowClassDecl>();
				   new_list.add(scd);
				   baseMapShadows.put(cd,new_list);				   
				   }			   
				}		 
			 }	
	        }
	    }
		sortClassMapping();
		firstVisit=true;
	}
   }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:11
   */
  public void generateClassfile() {
    for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit)iter.next();
      cu.generateClassfile();
    }
  /*for(int i=0;i<getNumCompilationUnit();i++)
    {
    	CompilationUnit cuu=get(i);
    	cuu.generateClassfile();
    }*/
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:22
   */
  public boolean hasProduced(){
	  return hasProduced;
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:25
   */
  
  private boolean hasProduced=false;
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:26
   */
  public void setHasProduced(boolean value) {
	    hasProduced=value;
	  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:29
   */
  public void generateAuxiliaryClassFile()
  {
	  for(int i=0;i<getNumAuxiliaryClassDecl();i++)
	    {
	    	if(!searchMainFunction().equals("")){
	    	CompilationUnit cuu=getAuxiliaryClassDecl(i);
	    	cuu.generateClassfile();
	    	}
	    }
  	  setHasProduced(true);
  }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:25
   */
  protected boolean isAuxiliaryGopClass(CompilationUnit unit) {
		return unit.getPackageDecl().equals("granulej.lang");		
	 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:28
   */
  public void generateShadowClassXmlfile(){
		 try{
	    String gfileName="";
	    String individualName=getIndividualName();
	    if(individualName.equals("")){	  	
	    Collection files = options().files();
	    for(Iterator iter = files.iterator(); iter.hasNext(); ) {
	          gfileName = (String)iter.next();
	          break;
	     }
	    gfileName=gfileName.substring(0,gfileName.lastIndexOf(java.io.File.separator)+1)+"UserClasses"+".xml";
	    }
	    else 
	    gfileName=individualName+"UserClasses"+".xml";
		ClassTree classtree=CreateClassTree();
		ClassNode cn0=(ClassNode)classtree.getRootElement();	
		Document Doc=getXmlDocument();
		Element root=Doc.createElement("userclass");
		Doc.appendChild(root);
		GatherGranule();
	    shadowclassTravels(root,cn0,Doc);
	    writeXmlFile(Doc,gfileName);
		 }
	     catch(Exception e)
		 {
		   e.printStackTrace();
		 } 
 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:56
   */
  public void writeXmlFile(Document doc,String fileName)
	 {
		    try{
		    Transformer transFormer=TransformerFactory.newInstance().newTransformer();
			DOMSource domSource = new DOMSource(doc);
			File file=new File(fileName);
			if(!file.exists())
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			StreamResult xmlResult = new StreamResult(out);
			transFormer.transform(domSource, xmlResult);
			}catch(TransformerException e) { 
	    	   e.printStackTrace(); 
	        }catch(IOException e) { 
	    	   e.printStackTrace(); 
	    	}		 
	 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:73
   */
  public String getXmlFileName(String suffix){
		 String gfileName="";
		 String individualName=getIndividualName();
		 if(individualName.equals("")){	  
		   gfileName=destinationPath()+java.io.File.separator+suffix+".xml";	
		 }
		 else
	       gfileName=individualName+suffix+".xml";
		return gfileName;
	}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:84
   */
  public Document getXmlDocument()
   {    
	    Document Doc=null;
	    try{
	    Doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();		    
	    Doc.setXmlVersion("1.0");	  
	    }catch(ParserConfigurationException e)
	    {
	      e.printStackTrace();
	    }
	    return Doc;
   }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:96
   */
  public void shadowclassTravels(Element parentElement,ClassNode cn,Document doc)
	 {		 
		 try{
	     if(!cn.getClassname().equals("Object")){
		   Element nowNode=doc.createElement("class");
		   nowNode.setAttribute("name",cn.getClassname());
 		 int i=0;
 		 if(cn.getClassdecl().hasShadowClass())
  		 {
  		    for(Iterator iter1=cn.getClassdecl().shadowclasses().iterator();iter1.hasNext();){
  			ShadowClassDecl shadowdecl=(ShadowClassDecl)iter1.next();
  			Element shadowclass=doc.createElement("shadowclass");
  			shadowclass.setAttribute("name",shadowdecl.name());
  			nowNode.appendChild(shadowclass);
  			i++;
  			}  	
  		}
 		 else
 		 {
 	  	     Element shadowclass=doc.createElement("shadowclass");
 	  		 shadowclass.setAttribute("name","");
 	  		 nowNode.appendChild(shadowclass);
 		 }
		 parentElement.appendChild(nowNode);
	     }
		 for(Iterator iter=cn.getChildren().iterator();iter.hasNext();)
		 {
			 ClassNode gn=(ClassNode)iter.next();
			 shadowclassTravels(parentElement,gn,doc);
		 }	 		      
	   }    
	 catch(Exception e){
   System.out.println("gFile open error");
  }
}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:131
   */
  public void generateClassNameXmlfile(){
       ClassTree classtree=CreateClassTree();
       String gfileName=getXmlFileName("class_tree");
       ClassNode cn0=(ClassNode)classtree.getRootElement();
       Document Doc=getXmlDocument();
       Element root=Doc.createElement("class");
       root.setAttribute("name",cn0.getClassname());
       root.setAttribute("granule0","g0");
       Doc.appendChild(root);
   	   LinkedHashMap c_g_map=getGranuleList();
   	   classItemTravels(root,cn0,Doc,c_g_map);
   	   writeXmlFile(Doc,gfileName);   	   
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:145
   */
  private LinkedHashMap<String,ArrayList<GranuleDecl>> getGranuleList()
    {
        LinkedHashMap<String,ArrayList<GranuleDecl>> cg_map=new LinkedHashMap<String,ArrayList<GranuleDecl>>();      
        GranuleTree granuletree=CreateGranuleTree();
        
        for(Iterator iter=granuletree.toList().iterator();iter.hasNext();)
    	   {
              GranuleTreeNode gtn=(GranuleTreeNode)iter.next();
         	  if(gtn==granuletree.getRootElement())
              continue;
    		  String base_c=getClassNameFromGranule(gtn);    		
    		  GranuleDecl gn=gtn.getGranuledecl();    		 
    		  if(!cg_map.containsKey(base_c)){
    		  ArrayList<GranuleDecl> list=new ArrayList<GranuleDecl>();
    		  list.add(gn);
    		  cg_map.put(base_c,list);   			  
    		  }   
    		  else{
    		  ArrayList<GranuleDecl> list=(ArrayList<GranuleDecl>)cg_map.get(base_c);
    		  if(!list.contains(gn))
    		  list.add(gn);  			  
    		  }    		   
    	 }
       return cg_map;
     }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:170
   */
  private String getClassNameFromGranule(GranuleTreeNode gtn)
     {
    	 
    	 GranuleDecl gd=(GranuleDecl)gtn.getGranuledecl();     	
    	 ClassDecl cl=(ClassDecl)gd.rootClass();
    	 return cl.name();
     }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:179
   */
  public void classItemTravels(Element parentElement,ClassNode cn, Document doc, LinkedHashMap list)
	{		 
		try {
				if (!cn.getClassname().equals("Object")&&!cn.getClassname().equals("GopContext")) {
					Element nowNode=doc.createElement("class");
					nowNode.setAttribute("name", cn.getClassname());
					int n = 0, i=0;
					if(cn.getClassdecl().hasGranuleClass()) {
				    ArrayList<GranuleDecl> glist=(ArrayList<GranuleDecl>)list.get(cn.getClassname());
				    for (Iterator iter = glist.iterator(); iter.hasNext();) {
					GranuleDecl granuledecl = (GranuleDecl) iter.next();
					nowNode.setAttribute("granule" + n,granuledecl.name());
					n++;
					for (Iterator iter1 = granuledecl.shadowclasses().iterator(); iter1.hasNext();) {
					nowNode.setAttribute("shadow_class" + i,((ShadowClassDecl) iter1.next()).name());
					i++;
					}
                   }
			      }
					parentElement.appendChild(nowNode);
					parentElement = nowNode;
				}
				ArrayList<ClassNode> childs=cn.getChildren();
				if(cn.getNumberOfChildren()>1)
			    childs=cn.Sort(childs);
				for (Iterator iter = childs.iterator(); iter.hasNext();) {
					ClassNode gn = (ClassNode) iter.next();
					classTravels(parentElement, gn,doc);
				}
			} catch (Exception e) {
				System.out.println("gFile open error");
			}
		}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:213
   */
  public void classTravels(Element parentElement,ClassNode cn, Document doc)
	{		 
		try {
				if (!cn.getClassname().equals("Object")&&!cn.getClassname().equals("GopContext")) {
					Element nowNode=doc.createElement("class");
					nowNode.setAttribute("name", cn.getClassname());
					int n = 0;
					if(cn.getClassdecl().hasGranuleClass()) {
				    for (Iterator iter = cn.getClassdecl().granuleclasses.iterator(); iter.hasNext();) {
					GranuleDecl granuledecl = (GranuleDecl) iter.next();
					nowNode.setAttribute("granule" + n,granuledecl.name());
					n++;
						}
					}
					int i = 0;
					if (cn.getClassdecl().hasShadowClass()) {
						for (Iterator iter1 = cn.getClassdecl().shadowclasses().iterator(); iter1.hasNext();) {
							ShadowClassDecl shadowdecl = (ShadowClassDecl) iter1.next();
							nowNode.setAttribute("shadow_class" + i,shadowdecl.name());
							i++;
						}
					}
					parentElement.appendChild(nowNode);
					parentElement = nowNode;
				}
				ArrayList<ClassNode> childs=cn.getChildren();
				if(cn.getNumberOfChildren()>1)
			    childs=cn.Sort(childs);
				for (Iterator iter = childs.iterator(); iter.hasNext();) {
					ClassNode gn = (ClassNode) iter.next();
					classTravels(parentElement, gn,doc);
				}
			} catch (Exception e) {
				System.out.println("gFile open error");
			}
		}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:249
   */
  public void generateGranuleSeedXmlfile()
	{
	    String gfileName=getXmlFileName("TestGranule");
		GranuleTree granuletree=CreateGranuleTree();
		GranuleTreeNode gtn0=granuletree.getRootElement();	
		Document Doc=getXmlDocument();
		Element root=Doc.createElement("granule"); 
		root.setAttribute("name",gtn0.getGranulename());
		root.setAttribute("context",gtn0.getContextname());
		ArrayList list=getCVsFromGranuleT(granuletree);
		String contextSeq="";
		for(int i = 0;i < list.size(); i ++){
	    contextSeq+=list.get(i);
	    if(i!=list.size()-1)
	    contextSeq+=",";	     
	    }
		root.setAttribute("conSeq",contextSeq);		
		if(gtn0.getGranuledecl()==null)
	    root.setAttribute("class","Object");
		Doc.appendChild(root);
		treeTravels(root,gtn0,Doc);
		writeXmlFile(Doc,gfileName);  
	}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:273
   */
  public void treeTravels(Element parentElement,GranuleTreeNode gtn,Document doc)
		 {		 
		   try {
				if (gtn.getGranuledecl() != null) {
					Element nowNode =doc.createElement("granule");
					nowNode.setAttribute("name", gtn.getGranulename());
					nowNode.setAttribute("context", gtn.getContextname());
					GranuleDecl gd = (GranuleDecl) gtn.getGranuledecl();
					ClassDecl classdecl = (ClassDecl) ((TypeAccess) gd.getRootClassAccess()).decl();
					nowNode.setAttribute("class", classdecl.name());
					int i = 0;
					for (Iterator iter1 = gd.shadowclasses().iterator(); iter1.hasNext();) {
						ShadowClassDecl shadow = (ShadowClassDecl) iter1.next();
						nowNode.setAttribute("shadow_class" + i, shadow.name());
						i++;
					}
					if (gd.hasSuperGranuleSpec()) {
						String expandString = "";
						boolean existsuper = false;
						if (gd.getSuperGranuleSpec() instanceof ExpandsGranuleSpec) {
							expandString = gd.name();
							existsuper = true;
						}
						if (existsuper) {
							while (gd.hasSuperGranuleSpec()) {
								if (gd.getSuperGranuleSpec() instanceof ExpandsGranuleSpec) {
									ExpandsGranuleSpec expands=(ExpandsGranuleSpec)gd.getSuperGranuleSpec();
									TypeDecl typedecl = expands.getSuperGranuleAccess().type();
									GranuleDecl supergranule = (GranuleDecl) typedecl;
									expandString = supergranule.name() + ","+ expandString;
									gd = supergranule;
								} else {
									break;
								}
							}
							nowNode.setAttribute("expands", expandString);
						}
					}
					parentElement.appendChild(nowNode);
					parentElement = nowNode;
				}
				for (Iterator iter = gtn.getChildren().iterator(); iter.hasNext();) {
					GranuleTreeNode gn = (GranuleTreeNode) iter.next();
					treeTravels(parentElement, gn,doc);
				}
			} catch (Exception e) {
				System.out.println("gFile open error");
			}
	 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:322
   */
  
   private String individualName="";
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:323
   */
  public void setIndividualName(String value) 
   {
	   individualName=value;
   }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:327
   */
  public String getIndividualName()
   {
	   return individualName;
   }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:332
   */
  public void generateClassFingerXmlfile(){
		ClassTree classtree=CreateClassTree();
	    String gfileName=getXmlFileName("class_finger");
	    ClassNode cn0=(ClassNode)classtree.getRootElement();
	    Document Doc=getXmlDocument();
		Element rootelement=Doc.createElement("class"); 
		rootelement.setAttribute("name",cn0.getClassname());
		rootelement.setAttribute("Sha512","");	     
		Doc.appendChild(rootelement); 
		GatherGranule();
		classFingerTravels(rootelement,cn0,Doc); 
		writeXmlFile(Doc,gfileName);
	 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:345
   */
  public void classFingerTravels(Element parentElement,ClassNode cn,Document doc)
	{		 
		try {
				if (!cn.getClassname().equals("Object")) {
					Element nowNode =doc.createElement("class");
					nowNode.setAttribute("name", cn.getClassname());
					Collection files = options().files();
					String fileFName="";
				    for(Iterator iter = files.iterator(); iter.hasNext(); ) {
				        fileFName=(String)iter.next();
				        String fileTemp=fileFName.substring(fileFName.lastIndexOf(java.io.File.separator)+1,fileFName.length()-5);
				        if(fileTemp.equals(cn.getClassname())){
				        fileFName=fileFName.substring(0,fileFName.length()-5)+".class";
                        break;
                        
				        }
				     }
					File file=new File(fileFName);
					nowNode.setAttribute("Sha512",FingerprintUtil.FileFingerprintWithSHA512(file));
					fileFName=fileFName.substring(fileFName.indexOf("src")-1,fileFName.lastIndexOf(java.io.File.separator)+1);					
					nowNode.setAttribute("path",fileFName);
					parentElement.appendChild(nowNode);
					parentElement = nowNode;
				}
				for (Iterator iter = cn.getChildren().iterator(); iter.hasNext();) {
					ClassNode gn = (ClassNode) iter.next();
					classFingerTravels(parentElement, gn,doc);
				}
			} catch (Exception e) {
				System.out.println("gFile open error");
			}
		}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:377
   */
  public void ClassMapShadowXmlfile(){
		ClassTree classtree=CreateClassTree();
	    String gfileName=getXmlFileName("ClassMapShadow");
	    ClassNode cn0=(ClassNode)classtree.getRootElement();	   
	    Document Doc=getXmlDocument();
		Element rootelement=Doc.createElement("classMapShadow");     
		Doc.appendChild(rootelement); 
		GatherGranule();
		shadowclassSort(rootelement,cn0,Doc);
		writeXmlFile(Doc,gfileName);	  
	 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:388
   */
  public void shadowclassSort(Element parentElement,ClassNode cn,Document doc)
	 {		 
       try{
	     if(!cn.getClassname().equals("Object")&&!cn.getClassname().equals("GopContext")){
		   Element nowNode=doc.createElement("class");
		   nowNode.setAttribute("name",cn.getClassname());
		 if(cn.getClassdecl().hasShadowClass())
 		 {
			LinkedList<String> list=new LinkedList<String>();
			for(Iterator iter1=cn.getClassdecl().shadowclasses().iterator();iter1.hasNext();){
 			ShadowClassDecl shadowdecl=(ShadowClassDecl)iter1.next();
 			if(shadowdecl.SuperShadowClass() instanceof ClassDecl)
 			list.addFirst(shadowdecl.name());
 			else
 			{
 				Stack<String> stack=new Stack();
 				stack.push(shadowdecl.name());
 				while(!(shadowdecl.SuperShadowClass() instanceof ClassDecl)&&!list.contains(((ShadowClassDecl)(shadowdecl.SuperShadowClass())).name())){ 				
 				ShadowClassDecl sshadow=(ShadowClassDecl)(shadowdecl.SuperShadowClass());
 				stack.push(sshadow.name());
 				shadowdecl=sshadow;
 				}
 				for(String s:stack){
 				 if(!list.contains(s))
 				 list.add(s);
 				}
 			}
			}
 			for(int i=0;i<list.size();i++){
 			Element shadowclass=doc.createElement("shadowclass");
 			String sname=list.get(i);
 			shadowclass.setAttribute("name",sname);
 			sname=sname.substring(0,sname.indexOf("%"));
 			shadowclass.setAttribute("attached",sname);
 			nowNode.appendChild(shadowclass);
 			}  	
 		}
		 else
		 {
	  	     Element shadowclass=doc.createElement("shadowclass");
	  		 shadowclass.setAttribute("name","");
	  		 shadowclass.setAttribute("attached","");
	  		 nowNode.appendChild(shadowclass);
		 }
		 parentElement.appendChild(nowNode);
	     }
		 for(Iterator iter=cn.getChildren().iterator();iter.hasNext();)
		 {
			 ClassNode gn=(ClassNode)iter.next();
			 shadowclassSort(parentElement,gn,doc);
		 }	 		      
	   }    
	 catch(Exception e){
  System.out.println("gFile open error");
 }
}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:444
   */
  public void MethodMapClassXmlfile(){
		ClassTree classtree=CreateClassTree();
	    String gfileName=getXmlFileName("MethodMapClass");
	    ClassNode cn0=(ClassNode)classtree.getRootElement();	   
	    Document Doc=getXmlDocument();
		Element rootelement=Doc.createElement("MethodMapClass");     
		Doc.appendChild(rootelement); 
		GatherGranule();
		classSort(rootelement,cn0,Doc);
		writeXmlFile(Doc,gfileName);  
	 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:455
   */
  public void classSort(Element parentElement,ClassNode cn,Document doc)
	 {		 
		try{
	     if(!cn.getClassname().equals("Object")&&!cn.getClassname().equals("GopContext")){
		   Element nowNode=doc.createElement("class");
		   nowNode.setAttribute("name",cn.getClassname());	
		   if(cn.getClassdecl().hasShadowClass())
 		   { 
 		    /*cn.getClassdecl().methodMapShadowes();
 		    LinkedList<String> list=cn.getClassdecl().shadowSet();
            for(int i=1;i<list.size();i++){
            Element shadowclass=doc.createElement("shadowclass");
 			String sname=list.get(i);
 			shadowclass.setAttribute("name",sname);
 			sname=sname.substring(0,sname.indexOf("%"));
 			shadowclass.setAttribute("attached",sname);
 			nowNode.appendChild(shadowclass);
 			}*/
 			HashMap<String,ArrayList<String>> hs=cn.getClassdecl().methodMapShadowes(); 			
 			Iterator iterator=hs.entrySet().iterator();   
            while(iterator.hasNext()){   
            Entry entry = (Entry)iterator.next(); 
            Element mname=doc.createElement("method");
 			//String sname=list.get(i);
 			mname.setAttribute("name",(String)entry.getKey());
 		    ArrayList<String> ls=(ArrayList<String>)entry.getValue();
 			for(int n=0;n<ls.size();n++)
 			mname.setAttribute("shadow"+n,ls.get(n));
 			nowNode.appendChild(mname);
 			}
           }
		 else
		 {
	  	     Element mname=doc.createElement("method");
	  		 mname.setAttribute("name","");
	  		 mname.setAttribute("shadow0","");
	  		 nowNode.appendChild(mname);
		 }
		 parentElement.appendChild(nowNode);
	     }
		 for(Iterator iter=cn.getChildren().iterator();iter.hasNext();)
		 {
			 ClassNode gn=(ClassNode)iter.next();
			 classSort(parentElement,gn,doc);
		 }	 		      
	   }catch(Exception e){
         System.err.println("gFile open error");
      }
}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:590
   */
  public void testClass()
	{		 
		ClassTree classtree=CreateClassTree();
		ClassNode cn0=(ClassNode)classtree.getRootElement();
		if(!cn0.getClassname().equals("Object"))
		cn0.getClassdecl().classList();
		 for(Iterator iter=cn0.getChildren().iterator();iter.hasNext();)
		 {
			 ClassNode gn=(ClassNode)iter.next();
			 gn.getClassdecl().classList();
		 }	 		   	
     }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:644
   */
  public void MethodMapShadowClassesXmlfile(){
		ClassTree classtree=CreateClassTree();
	    String gfileName=getXmlFileName("MethodMapShadowClass");
	    ClassNode cn0=(ClassNode)classtree.getRootElement();	   
	    Document Doc=getXmlDocument();
		Element rootelement=Doc.createElement("ShadowClasses");     
		Doc.appendChild(rootelement); 
		GatherGranule();
		classCol(rootelement,cn0,Doc);
		writeXmlFile(Doc,gfileName);	   
	 }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:655
   */
  public void classCol(Element parentElement,ClassNode cn,Document doc)
	 {		 
		try{
	     if(!cn.getClassname().equals("Object")&&!cn.getClassname().equals("GopContext")){
		   //System.out.println("cn is :"+cn.getClassname());	
		   if(cn.getClassdecl().hasShadowClass())
		   { 
			HashMap<String,ArrayList<String>> hs=cn.getClassdecl().shadowesRewrite();  			
		    Iterator iterator=hs.entrySet().iterator();   
            while(iterator.hasNext()){   
            Entry entry = (Entry)iterator.next(); 
            Element nowNode=doc.createElement("method");
			nowNode.setAttribute("name",(String)entry.getKey());
		    ArrayList<String> ls=(ArrayList<String>)entry.getValue();
			for(int n=0;n<ls.size();n++)
			nowNode.setAttribute("shadow"+n,ls.get(n));
			parentElement.appendChild(nowNode);
			}
          }
	     }
		 for(Iterator iter=cn.getChildren().iterator();iter.hasNext();)
		 {
			 ClassNode gn=(ClassNode)iter.next();
			 classCol(parentElement,gn,doc);
		 }	 		      
	   }catch(Exception e){
        System.err.println("gFile open error");
     }	   
}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:719
   */
  public void generateGranuleTreeXmlfile()
	{
	    String gfileName=getXmlFileName("TestGranuleTree");
		GranuleTree granuletree=CreateGranuleTree();
		GranuleTreeNode gtn0=granuletree.getRootElement();	
		Document Doc=getXmlDocument();
		Element root=Doc.createElement("g-tree");		
		String bSeq=getBaseClasses();
        root.setAttribute("raceId",FingerprintUtil.StringFingerprintWithSHA512(bSeq));
        Doc.appendChild(root);
        Element sonNode=Doc.createElement("granule");
        if(gtn0.getGranuledecl()==null){       
        sonNode.setAttribute("name",gtn0.getGranulename());
        sonNode.setAttribute("context",gtn0.getContextname());
        sonNode.setAttribute("class","Object");
        }
        root.appendChild(sonNode);
		treeTravelsToAll(sonNode,gtn0,Doc);	
		String mdToString=FingerprintUtil.StringFingerprintWithSHA512(NodetoString(sonNode));
		root.setAttribute("md5",mdToString);
		setMd5(mdToString);
		root.setAttribute("poset",poset.toString());
		if(individualName.equals(""))
		root.setAttribute("location",destinationPath()+File.separator);
		else
		root.setAttribute("location",individualName+File.separator);
		root.setAttribute("file","TestGranuleTree.xml");
		//root.setAttribute("mmap","MethodMapShadowClass.xml");
                Element methNode=Doc.createElement("ShadowClasses");
	        ClassTree classtree=CreateClassTree();
		ClassNode cn0=(ClassNode)classtree.getRootElement();	       
		GatherGranule();
		classCollect(methNode,cn0,Doc);	    
	        root.appendChild(methNode);	
		writeXmlFile(Doc,gfileName);	  
}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:756
   */
  public void classCollect(Element parentElement,ClassNode cn, Document doc)
	 {		 
		try{
	     if(!cn.getClassname().equals("Object")&&!cn.getClassname().equals("GopContext")){
		   System.out.println("cn is :"+cn.getClassname());	
		   if(cn.getClassdecl().hasShadowClass())
		   { 
			HashMap<String,ArrayList<String>> hs=cn.getClassdecl().shadowesRewrite();  			
		    Iterator iterator=hs.entrySet().iterator();   
           while(iterator.hasNext()){   
           Entry entry = (Entry)iterator.next(); 
           Element nowNode=doc.createElement("method");
			nowNode.setAttribute("name",(String)entry.getKey());
		    ArrayList<String> ls=(ArrayList<String>)entry.getValue();
			for(int n=0;n<ls.size();n++)
			nowNode.setAttribute("shadow"+n,ls.get(n));
			parentElement.appendChild(nowNode);
			}
         }
	     }
		 for(Iterator iter=cn.getChildren().iterator();iter.hasNext();)
		 {
			 ClassNode gn=(ClassNode)iter.next();
			 classCol(parentElement,gn,doc);
		 }	 		      
	   }catch(Exception e){
       System.err.println("gFile open error");
    }	
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:785
   */
  
    private StringBuffer poset=new StringBuffer();
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:787
   */
  public void treeTravelsToAll(Element parentElement,GranuleTreeNode gtn,Document doc)
	 {		 
	   try {
                if (gtn.getGranuledecl() != null) {
				Element nowNode =doc.createElement("granule");
				nowNode.setAttribute("name", gtn.getGranulename());
				nowNode.setAttribute("context", gtn.getContextname());
				poset.append("<"+parentElement.getAttribute("context")+","+gtn.getContextname()+">");
				GranuleDecl gd = (GranuleDecl) gtn.getGranuledecl();
				ClassDecl classdecl = (ClassDecl) ((TypeAccess) gd.getRootClassAccess()).decl();
				nowNode.setAttribute("class", classdecl.name());
				int i = 0;
				for (Iterator iter1 = gd.shadowclasses().iterator(); iter1.hasNext();) {
					ShadowClassDecl shadow = (ShadowClassDecl) iter1.next();
					nowNode.setAttribute("shadow_class" + i, shadow.name());
					i++;
				}
				if (gd.hasSuperGranuleSpec()) {
					String expandString = "";
					boolean existsuper = false;
					if (gd.getSuperGranuleSpec() instanceof ExpandsGranuleSpec) {
						expandString = gd.name();
						existsuper = true;
					}
					if (existsuper) {
						while (gd.hasSuperGranuleSpec()) {
							if (gd.getSuperGranuleSpec() instanceof ExpandsGranuleSpec) {
								ExpandsGranuleSpec expands=(ExpandsGranuleSpec)gd.getSuperGranuleSpec();
								TypeDecl typedecl = expands.getSuperGranuleAccess().type();
								GranuleDecl supergranule = (GranuleDecl) typedecl;
								expandString = supergranule.name() + ","+ expandString;
								gd = supergranule;
							} else {
								break;
							}
						}
						nowNode.setAttribute("expands", expandString);
					}
				}
				parentElement.appendChild(nowNode);
				parentElement = nowNode;
			}
			for (Iterator iter = gtn.getChildren().iterator(); iter.hasNext();) {
				GranuleTreeNode gn = (GranuleTreeNode) iter.next();
				treeTravelsToAll(parentElement,gn,doc);
			}
		} catch (Exception e) {
			System.out.println("gFile open error");
		}
}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:839
   */
  
    
    
    private String mdToString;
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:841
   */
  public void setMd5(String md)
    {
    	mdToString=md;
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:845
   */
  public String getMd5()
    {
    	return mdToString;
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:850
   */
  public String NodetoString(Element node) {
      Transformer transformer=null;
      String result=null;
      if (node == null)
         throw new IllegalArgumentException();
      try{
    	transformer = TransformerFactory.newInstance().newTransformer();
       }catch( Exception e){
    	 throw new RuntimeException(e.getMessage());
       }
      if(transformer != null) {
    	try{
    	StringWriter sw = new StringWriter();
    	transformer.transform(new DOMSource(node), new StreamResult(sw));
    	result=sw.toString();
    	return result;
    	}catch (TransformerException te) {
    	throw new RuntimeException(te.getMessage());
    	}
    	}
    	return result;
    	}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:872
   */
  public void testTree(){
      System.out.println("base class sequence is :"+getBaseClasses());    	
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:887
   */
  public String getBaseClasses(){
    	ClassTree classtree=CreateClassTree();
    	return classtree.getListSequence();   	
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:891
   */
  public String generateIndividualIdentity(){ 
    return null;
   }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:894
   */
  public void generateGranulePathXmlfile()
	{
	    String gfileName=getXmlFileName("GranuleSimiPath");
		GranuleTree granuletree=CreateGranuleTree();
		GranuleTreeNode gtn0=granuletree.getRootElement();	
		Document Doc=getXmlDocument();
		Element root=Doc.createElement("g-path"); 
		Doc.appendChild(root);
		String path="";
		treeTravelsPath(root,gtn0,Doc,path);
		root.setAttribute("md5",getMd5());
		writeXmlFile(Doc,gfileName);	  
	}
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:907
   */
  public void treeTravelsPath(Element pe,GranuleTreeNode gtn, Document doc,String path)
     {
    	try{
    	 if(!gtn.getGranulename().equals("g0")){
    	 Element nowNode=doc.createElement("path");
    	 nowNode.setAttribute("name",gtn.getGranulename());
    	 GranuleDecl gd=(GranuleDecl)gtn.getGranuledecl();
         ClassDecl classdecl = (ClassDecl) ((TypeAccess) gd.getRootClassAccess()).decl();
         String p_app=classdecl.name()+":"+gtn.getContextname();
    	 if(!path.equals("")){
    	 path=path+","+p_app;
    	 }
    	 else{
    	 path=path+p_app;
    	 }
    	 nowNode.setAttribute("value",path);
    	 pe.appendChild(nowNode);
        }
    	for(Iterator iter = gtn.getChildren().iterator(); iter.hasNext();){
			GranuleTreeNode gn=(GranuleTreeNode)iter.next();
			treeTravelsPath(pe,gn,doc,path);
		}
		}catch (Exception e) {
			System.out.println("gFile open error");
			e.printStackTrace();
		}
     }
  /**
   * @ast method 
   * @aspect Caching
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\caching.jrag:28
   */
  public void resetCache() {       
    	  typeObject_computed = false;          
          typeCloneable_computed = false;          
          typeSerializable_computed = false;          
          typeBoolean_computed = false;          
          typeByte_computed = false;          
          typeShort_computed = false;          
          typeChar_computed = false;          
          typeInt_computed = false;          
          typeLong_computed = false;          
          typeFloat_computed = false;          
          typeDouble_computed = false;          
          typeString_computed = false;          
          typeVoid_computed = false;         
          typeNull_computed = false;          
          unknownType_computed = false;         
          unknownConstructor_computed = false;          
          wildcards_computed = false;             
     }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public Program() {
    super();

    setChild(new List(), 0);
    setChild(new List(), 1);
    is$Final(true);

  }
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  public Program(List<CompilationUnit> p0) {
    setChild(p0, 0);
    setChild(new List(), 1);
    is$Final(true);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:24
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for CompilationUnitList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setCompilationUnitList(List<CompilationUnit> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in CompilationUnitList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumCompilationUnit() {
    return getCompilationUnitList().getNumChild();
  }
  /**
   * Getter for child in list CompilationUnitList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CompilationUnit getCompilationUnit(int i) {
    return (CompilationUnit)getCompilationUnitList().getChild(i);
  }
  /**
   * Add element to list CompilationUnitList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addCompilationUnit(CompilationUnit node) {
    List<CompilationUnit> list = (parent == null || state == null) ? getCompilationUnitListNoTransform() : getCompilationUnitList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addCompilationUnitNoTransform(CompilationUnit node) {
    List<CompilationUnit> list = getCompilationUnitListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list CompilationUnitList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setCompilationUnit(CompilationUnit node, int i) {
    List<CompilationUnit> list = getCompilationUnitList();
    list.setChild(node, i);
  }
  /**
   * Getter for CompilationUnit list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<CompilationUnit> getCompilationUnits() {
    return getCompilationUnitList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<CompilationUnit> getCompilationUnitsNoTransform() {
    return getCompilationUnitListNoTransform();
  }
  /**
   * Getter for list CompilationUnitList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<CompilationUnit> getCompilationUnitList() {
    List<CompilationUnit> list = (List<CompilationUnit>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<CompilationUnit> getCompilationUnitListNoTransform() {
    return (List<CompilationUnit>)getChildNoTransform(0);
  }
  /**
   * Setter for AuxiliaryClassDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setAuxiliaryClassDeclList(List<CompilationUnit> list) {
    setChild(list, 1);
  }
  /**
   * @return number of children in AuxiliaryClassDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumAuxiliaryClassDecl() {
    return getAuxiliaryClassDeclList().getNumChild();
  }
  /**
   * Getter for child in list AuxiliaryClassDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CompilationUnit getAuxiliaryClassDecl(int i) {
    return (CompilationUnit)getAuxiliaryClassDeclList().getChild(i);
  }
  /**
   * Add element to list AuxiliaryClassDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addAuxiliaryClassDecl(CompilationUnit node) {
    List<CompilationUnit> list = (parent == null || state == null) ? getAuxiliaryClassDeclListNoTransform() : getAuxiliaryClassDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addAuxiliaryClassDeclNoTransform(CompilationUnit node) {
    List<CompilationUnit> list = getAuxiliaryClassDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list AuxiliaryClassDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setAuxiliaryClassDecl(CompilationUnit node, int i) {
    List<CompilationUnit> list = getAuxiliaryClassDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for AuxiliaryClassDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<CompilationUnit> getAuxiliaryClassDecls() {
    return getAuxiliaryClassDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<CompilationUnit> getAuxiliaryClassDeclsNoTransform() {
    return getAuxiliaryClassDeclListNoTransform();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:62
   */
  public List<CompilationUnit> getAuxiliaryClassDeclListNoTransform() {
    return (List<CompilationUnit>)getChildNoTransform(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:68
   */
  protected int getAuxiliaryClassDeclListChildPosition() {
    return 1;
  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:4
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupVariable(String name) {
      ASTNode$State state = state();
    SimpleSet lookupVariable_String_value = lookupVariable_compute(name);
    return lookupVariable_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet lookupVariable_compute(String name) {  return contextVariable(name);  }
  protected java.util.Map contextVariable_String_values;
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:8
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet contextVariable(String name) {
    Object _parameters = name;
    if(contextVariable_String_values == null) contextVariable_String_values = new java.util.HashMap(4);
    if(contextVariable_String_values.containsKey(_parameters)) {
      return (SimpleSet)contextVariable_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet contextVariable_String_value = contextVariable_compute(name);
if(isFinal && num == state().boundariesCrossed) contextVariable_String_values.put(_parameters, contextVariable_String_value);
    return contextVariable_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet contextVariable_compute(String name) {  return contextVariableMap().containsKey(name) ? (SimpleSet)contextVariableMap().get(name) : SimpleSet.emptySet;  }
  /**
   * @apilvl internal
   */
  protected boolean contextVariableMap_computed = false;
  /**
   * @apilvl internal
   */
  protected HashMap contextVariableMap_value;
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:30
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap contextVariableMap() {
    if(contextVariableMap_computed) {
      return contextVariableMap_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    contextVariableMap_value = contextVariableMap_compute();
if(isFinal && num == state().boundariesCrossed) contextVariableMap_computed = true;
    return contextVariableMap_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap contextVariableMap_compute() {
    HashMap map = new HashMap();
    for(int i = 0; i < getNumCompilationUnit(); i++) {
    	CompilationUnit cu = getCompilationUnit(i);
      for(int j=0; j< cu.getNumContextVar();j++){
    	  ASTNode node = cu.getContextVar(j);
        if(node instanceof ContextVarDeclaration){  
        	ContextVarDeclaration decl = (ContextVarDeclaration)node;        	
	        SimpleSet fields = (SimpleSet)map.get(decl.name());
	        if(fields == null) fields = SimpleSet.emptySet;
	        fields = fields.add(decl);
	        map.put(decl.name(), fields);
	     }
	     else if (node instanceof List)
	     {
	        List listvar = (List)node;
	        for(Iterator iter = listvar.iterator(); iter.hasNext(); ) {
	            VariableDeclaration decl = (VariableDeclaration)iter.next();
	            SimpleSet fields = (SimpleSet)map.get(decl.name());
		        if(fields == null) fields = SimpleSet.emptySet;
		        fields = fields.add(decl);
		        map.put(decl.name(), fields);
		     }
		  }  
       }
    }
    return map;  
  }
  /**
   * @apilvl internal
   */
  protected boolean FitAccessCounts_computed = false;
  /**
   * @apilvl internal
   */
  protected int FitAccessCounts_value;
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:309
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int FitAccessCounts() {
    if(FitAccessCounts_computed) {
      return FitAccessCounts_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    FitAccessCounts_value = FitAccessCounts_compute();
if(isFinal && num == state().boundariesCrossed) FitAccessCounts_computed = true;
    return FitAccessCounts_value;
  }
  /**
   * @apilvl internal
   */
  private int FitAccessCounts_compute() {
	 int count=0;
	 for(Iterator iter = compilationUnitIterator(); iter.hasNext();){
	 CompilationUnit cu = (CompilationUnit)iter.next();
	 if(cu.fromSource())
	 count+=cu.FitAccessCount();
	 }
	 return count;
 }
  /**
   * @apilvl internal
   */
  protected boolean searchMainFunction_computed = false;
  /**
   * @apilvl internal
   */
  protected String searchMainFunction_value;
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:347
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String searchMainFunction() {
    if(searchMainFunction_computed) {
      return searchMainFunction_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    searchMainFunction_value = searchMainFunction_compute();
if(isFinal && num == state().boundariesCrossed) searchMainFunction_computed = true;
    return searchMainFunction_value;
  }
  /**
   * @apilvl internal
   */
  private String searchMainFunction_compute() {
	 String classname="";
	 boolean found=false;
	 for(Iterator iterc= compilationUnitIterator(); iterc.hasNext();){
		 CompilationUnit cu=(CompilationUnit)iterc.next();
	 if(cu.fromSource()){	
	   for(int i=0; i<cu.getNumTypeDecl();i++){
		 if(cu.getTypeDecl(i) instanceof ClassDecl)
		 {
		   ClassDecl type=(ClassDecl)cu.getTypeDecl(i);		
		   for(Iterator iter=type.localMethodsSignatureMap().values().iterator();iter.hasNext()&&!found;)
		   {
			   MethodDecl method=(MethodDecl)iter.next();
			   if(method.signature().equals("main(java.lang.String[])"))
			   found=true;
			   classname=type.name();
		   }
		 }
	   }
	 }
	 }
	 return classname;
 }
  /**
   * @apilvl internal
   */
  protected boolean searchMainType_computed = false;
  /**
   * @apilvl internal
   */
  protected ClassDecl searchMainType_value;
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:377
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ClassDecl searchMainType() {
    if(searchMainType_computed) {
      return searchMainType_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    searchMainType_value = searchMainType_compute();
if(isFinal && num == state().boundariesCrossed) searchMainType_computed = true;
    return searchMainType_value;
  }
  /**
   * @apilvl internal
   */
  private ClassDecl searchMainType_compute() {
	 ClassDecl c=null;
	 boolean found=false;
	 for(Iterator iterc= compilationUnitIterator(); iterc.hasNext();){
	   CompilationUnit cu=(CompilationUnit)iterc.next();
	 if(cu.fromSource()){	
	   for(int i=0; i<cu.getNumTypeDecl();i++){
		 if(cu.getTypeDecl(i) instanceof ClassDecl)
		 {
		   ClassDecl type=(ClassDecl)cu.getTypeDecl(i);		
		   for(Iterator iter=type.localMethodsSignatureMap().values().iterator();iter.hasNext()&&!found;)
		   {
			   MethodDecl method=(MethodDecl)iter.next();
			   if(method.signature().equals("main(java.lang.String[])"))
			   found=true;
			   c=type;
		   }
		 }
	   }
	 }
	 }
	 return c;
 }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:33
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Program getProgram() {
      ASTNode$State state = state();
    Program getProgram_value = getProgram_compute();
    return getProgram_value;
  }
  /**
   * @apilvl internal
   */
  private Program getProgram_compute() {  return this;  }
  protected java.util.Map lookupTypeDecl_String_String_values;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1017
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl lookupTypeDecl(String packageName, String typeName) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(packageName);
    _parameters.add(typeName);
    if(lookupTypeDecl_String_String_values == null) lookupTypeDecl_String_String_values = new java.util.HashMap(4);
    if(lookupTypeDecl_String_String_values.containsKey(_parameters)) {
      return (TypeDecl)lookupTypeDecl_String_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    TypeDecl lookupTypeDecl_String_String_value = lookupTypeDecl_compute(packageName, typeName);
if(isFinal && num == state().boundariesCrossed) lookupTypeDecl_String_String_values.put(_parameters, lookupTypeDecl_String_String_value);
    return lookupTypeDecl_String_String_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl lookupTypeDecl_compute(String packageName, String typeName) {
	    String fullName = packageName.equals("") ? typeName : packageName + "." + typeName; 
        for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
	    CompilationUnit cu = (CompilationUnit)iter.next();
	    if(cu.fromSource())
	      {
	         for(Iterator iterator=cu.getTypeDeclList().iterator();iterator.hasNext();)
	         {
	         TypeDecl typeDecl=(TypeDecl)iterator.next();
	         if(typeDecl.getID().equals(fullName))
	         return typeDecl;
	         }
	      }	   
	    }
        return null;
	  }
  /**
   * @apilvl internal
   */
  protected boolean getAuxiliaryClassDeclList_computed = false;
  /**
   * @apilvl internal
   */
  protected List<CompilationUnit> getAuxiliaryClassDeclList_value;
  /**
   * @attribute syn nta
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1808
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<CompilationUnit> getAuxiliaryClassDeclList() {
    if(getAuxiliaryClassDeclList_computed) {
      return (List<CompilationUnit>)ASTNode.getChild(this, getAuxiliaryClassDeclListChildPosition());
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    getAuxiliaryClassDeclList_value = getAuxiliaryClassDeclList_compute();
    setAuxiliaryClassDeclList(getAuxiliaryClassDeclList_value);
if(isFinal && num == state().boundariesCrossed) getAuxiliaryClassDeclList_computed = true;
    return (List<CompilationUnit>)ASTNode.getChild(this, getAuxiliaryClassDeclListChildPosition());
  }
  /**
   * @apilvl internal
   */
  private List<CompilationUnit> getAuxiliaryClassDeclList_compute() {  return new List<CompilationUnit>().add(createContextVarClassDecl()).add(createExecuteModeClass());  }
  /**
   * @apilvl internal
   */
  protected boolean CreateClassTree_computed = false;
  /**
   * @apilvl internal
   */
  protected ClassTree CreateClassTree_value;
  /* public LinkedList Block.updateExprSet=new LinkedList();
	 public boolean Block.isUpdateExpr()
	  {
		 if(hostType().isClassDecl()){
			 for(int j=0;j<getNumStmt();j++){
			 if(getStmt(j).isUpdateExpr()){
			 if(!updateExprSet.contains(getStmt(j)))
			 updateExprSet.add(getStmt(j));
			 return true;
			 }
			 }
		 }		 
		 return false;
	  }
	 public boolean Stmt.isUpdateExpr(){
		  if(this instanceof ExprStmt){
			  ExprStmt expr=(ExprStmt)this;
			 if(expr.getExpr() instanceof UpdateExpr)
			 return true;
		  }
		     return false;
	 }* @attribute syn
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:126
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
		  ClassTree cn=new ClassTree();
		  int w=0;
		  for(Iterator iter = compilationUnitIterator(); iter.hasNext(); ) {
	          CompilationUnit unit = (CompilationUnit)iter.next();
	          w++;
	          if(unit.fromSource()){
	           for(int i=0; i<unit.getNumTypeDecl();i++)
	           {
	        	  if(unit.getTypeDecl(i) instanceof ClassDecl){
	        		  ClassDecl classdecl=(ClassDecl)unit.getTypeDecl(i);	
	        		  ClassNode classdecll=new ClassNode(classdecl.name(),classdecl);
	        		  if(classdecl.hasSuperclass()){
	        		  String superclass=classdecl.superclass().getID();
	        		  ClassNode cl=cn.findClass(superclass);
	        		  if(cl!=null){
	        			  if(cn.findClass(classdecl.name())==null)
	        				 cl.addChild(classdecll);
	        		  }
	        		  else{
	        			  while(classdecl.hasSuperclass()&& cn.findClass(superclass) == null) {
	  						ClassNode superdecll = new ClassNode(superclass,classdecl.superclass());	  						
	  						superdecll.addChild(classdecll);
	  						classdecl = classdecl.superclass();
	  						classdecll = superdecll;	  						
	  						superclass = classdecl.superclass().getID();
	  					}
	  					if (classdecl.hasSuperclass()&& cn.findClass(superclass)!=null)
	  						cn.findClass(superclass).addChild(classdecll);
	  				    }  
	        	  }
	           }
	          }
	        }
		  }	      
		  return cn;
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
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:268
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
		        return gtree;
			}
			return 	null;
		}
  /**
   * @apilvl internal
   */
  protected boolean typeObject_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeObject_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:15
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeObject() {
    if(typeObject_computed) {
      return typeObject_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeObject_value = typeObject_compute();
if(isFinal && num == state().boundariesCrossed) typeObject_computed = true;
    return typeObject_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeObject_compute() {  return lookupType("java.lang", "Object");  }
  /**
   * @apilvl internal
   */
  protected boolean typeCloneable_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeCloneable_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:16
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeCloneable() {
    if(typeCloneable_computed) {
      return typeCloneable_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeCloneable_value = typeCloneable_compute();
if(isFinal && num == state().boundariesCrossed) typeCloneable_computed = true;
    return typeCloneable_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeCloneable_compute() {  return lookupType("java.lang", "Cloneable");  }
  /**
   * @apilvl internal
   */
  protected boolean typeSerializable_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeSerializable_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:17
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeSerializable() {
    if(typeSerializable_computed) {
      return typeSerializable_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeSerializable_value = typeSerializable_compute();
if(isFinal && num == state().boundariesCrossed) typeSerializable_computed = true;
    return typeSerializable_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeSerializable_compute() {  return lookupType("java.io", "Serializable");  }
  /**
   * @apilvl internal
   */
  protected boolean typeBoolean_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeBoolean_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:22
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeBoolean() {
    if(typeBoolean_computed) {
      return typeBoolean_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeBoolean_value = typeBoolean_compute();
if(isFinal && num == state().boundariesCrossed) typeBoolean_computed = true;
    return typeBoolean_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeBoolean_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME, "boolean");  }
  /**
   * @apilvl internal
   */
  protected boolean typeByte_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeByte_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:23
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeByte() {
    if(typeByte_computed) {
      return typeByte_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeByte_value = typeByte_compute();
if(isFinal && num == state().boundariesCrossed) typeByte_computed = true;
    return typeByte_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeByte_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME , "byte");  }
  /**
   * @apilvl internal
   */
  protected boolean typeShort_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeShort_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:24
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeShort() {
    if(typeShort_computed) {
      return typeShort_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeShort_value = typeShort_compute();
if(isFinal && num == state().boundariesCrossed) typeShort_computed = true;
    return typeShort_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeShort_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME , "short");  }
  /**
   * @apilvl internal
   */
  protected boolean typeChar_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeChar_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:25
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeChar() {
    if(typeChar_computed) {
      return typeChar_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeChar_value = typeChar_compute();
if(isFinal && num == state().boundariesCrossed) typeChar_computed = true;
    return typeChar_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeChar_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME , "char");  }
  /**
   * @apilvl internal
   */
  protected boolean typeInt_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeInt_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:26
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeInt() {
    if(typeInt_computed) {
      return typeInt_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeInt_value = typeInt_compute();
if(isFinal && num == state().boundariesCrossed) typeInt_computed = true;
    return typeInt_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeInt_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME , "int");  }
  /**
   * @apilvl internal
   */
  protected boolean typeLong_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeLong_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:27
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeLong() {
    if(typeLong_computed) {
      return typeLong_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeLong_value = typeLong_compute();
if(isFinal && num == state().boundariesCrossed) typeLong_computed = true;
    return typeLong_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeLong_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME , "long");  }
  /**
   * @apilvl internal
   */
  protected boolean typeFloat_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeFloat_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:28
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeFloat() {
    if(typeFloat_computed) {
      return typeFloat_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeFloat_value = typeFloat_compute();
if(isFinal && num == state().boundariesCrossed) typeFloat_computed = true;
    return typeFloat_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeFloat_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME , "float");  }
  /**
   * @apilvl internal
   */
  protected boolean typeDouble_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeDouble_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:29
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeDouble() {
    if(typeDouble_computed) {
      return typeDouble_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeDouble_value = typeDouble_compute();
if(isFinal && num == state().boundariesCrossed) typeDouble_computed = true;
    return typeDouble_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeDouble_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME , "double");  }
  /**
   * @apilvl internal
   */
  protected boolean typeString_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeString_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:30
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeString() {
    if(typeString_computed) {
      return typeString_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeString_value = typeString_compute();
if(isFinal && num == state().boundariesCrossed) typeString_computed = true;
    return typeString_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeString_compute() {  return lookupType("java.lang", "String");  }
  /**
   * @apilvl internal
   */
  protected boolean typeVoid_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeVoid_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:41
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeVoid() {
    if(typeVoid_computed) {
      return typeVoid_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeVoid_value = typeVoid_compute();
if(isFinal && num == state().boundariesCrossed) typeVoid_computed = true;
    return typeVoid_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeVoid_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME, "void");  }
  /**
   * @apilvl internal
   */
  protected boolean typeNull_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeNull_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:43
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeNull() {
    if(typeNull_computed) {
      return typeNull_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeNull_value = typeNull_compute();
if(isFinal && num == state().boundariesCrossed) typeNull_computed = true;
    return typeNull_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeNull_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME, "null");  }
  /**
   * @apilvl internal
   */
  protected boolean unknownType_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl unknownType_value;
  /**
   * @attribute syn
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:46
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl unknownType() {
    if(unknownType_computed) {
      return unknownType_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    unknownType_value = unknownType_compute();
if(isFinal && num == state().boundariesCrossed) unknownType_computed = true;
    return unknownType_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl unknownType_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME, "Unknown");  }
  protected java.util.Map hasPackage_String_values;
  /**
   * @attribute syn
   * @aspect LookupFullyQualifiedTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:78
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasPackage(String packageName) {
    Object _parameters = packageName;
    if(hasPackage_String_values == null) hasPackage_String_values = new java.util.HashMap(4);
    if(hasPackage_String_values.containsKey(_parameters)) {
      return ((Boolean)hasPackage_String_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean hasPackage_String_value = hasPackage_compute(packageName);
if(isFinal && num == state().boundariesCrossed) hasPackage_String_values.put(_parameters, Boolean.valueOf(hasPackage_String_value));
    return hasPackage_String_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasPackage_compute(String packageName) {
  return isPackage(packageName);
  }
  protected java.util.Map lookupType_String_String_values;
  /**
   * @attribute syn
   * @aspect LookupFullyQualifiedTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:109
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl lookupType(String packageName, String typeName) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(packageName);
    _parameters.add(typeName);
    if(lookupType_String_String_values == null) lookupType_String_String_values = new java.util.HashMap(4);
    if(lookupType_String_String_values.containsKey(_parameters)) {
      return (TypeDecl)lookupType_String_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    TypeDecl lookupType_String_String_value = lookupType_compute(packageName, typeName);
if(isFinal && num == state().boundariesCrossed) lookupType_String_String_values.put(_parameters, lookupType_String_String_value);
    return lookupType_String_String_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl lookupType_compute(String packageName, String typeName) {
    addPrimitiveTypes();
    String fullName = packageName.equals("") ? typeName : packageName + "." + typeName; 
    for(int i = 0; i < getNumCompilationUnit(); i++) { 
      for(int j = 0; j < getCompilationUnit(i).getNumTypeDecl(); j++) {
        TypeDecl type = getCompilationUnit(i).getTypeDecl(j); 
        if(type.fullName().equals(fullName)) {        
          return type;
        }
      }
    } 
    CompilationUnit u = getCompilationUnit(fullName);  
    if(u != null) {
      addCompilationUnit(u);
      getCompilationUnit(getNumCompilationUnit()-1);
      for(int j = 0; j < u.getNumTypeDecl(); j++) {
        if(u.getTypeDecl(j).name().equals(typeName)) {
          return u.getTypeDecl(j);
        }
      }
      //throw new Error("No type named " + typeName + " in file " + fullName + ", " + u.pathName() + ", " + u.relativeName());
    }
    return null;
  }
  /**
   * @apilvl internal
   */
  protected boolean unknownConstructor_computed = false;
  /**
   * @apilvl internal
   */
  protected ConstructorDecl unknownConstructor_value;
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:247
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ConstructorDecl unknownConstructor() {
    if(unknownConstructor_computed) {
      return unknownConstructor_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    unknownConstructor_value = unknownConstructor_compute();
if(isFinal && num == state().boundariesCrossed) unknownConstructor_computed = true;
    return unknownConstructor_value;
  }
  /**
   * @apilvl internal
   */
  private ConstructorDecl unknownConstructor_compute() {
    return (ConstructorDecl)unknownType().constructors().iterator().next();
  }
  /**
   * @apilvl internal
   */
  protected boolean searchMainCuPath_computed = false;
  /**
   * @apilvl internal
   */
  protected String searchMainCuPath_value;
  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:161
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String searchMainCuPath() {
    if(searchMainCuPath_computed) {
      return searchMainCuPath_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    searchMainCuPath_value = searchMainCuPath_compute();
if(isFinal && num == state().boundariesCrossed) searchMainCuPath_computed = true;
    return searchMainCuPath_value;
  }
  /**
   * @apilvl internal
   */
  private String searchMainCuPath_compute() {
	 String sourcepath="";
	 boolean found=false;
 	 for(Iterator iterc= compilationUnitIterator(); iterc.hasNext();){
 		 CompilationUnit cu=(CompilationUnit)iterc.next();
 	 if(cu.fromSource()){	
 	   for(int i=0; i<cu.getNumTypeDecl();i++){
 		 if(cu.getTypeDecl(i) instanceof ClassDecl)
 		 {
 		   ClassDecl type=(ClassDecl)cu.getTypeDecl(i);		
 		   for(Iterator iter=type.localMethodsSignatureMap().values().iterator();iter.hasNext()&&!found;)
 		   {
 			   MethodDecl method=(MethodDecl)iter.next();
 			   if(method.signature().equals("main(java.lang.String[])")){
 			   found=true;
 			   sourcepath=cu.destinationPath();
 			   }
 		   }
 		 }
 	   }
 	 }
 	}
 	 return sourcepath;
  }
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
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:935
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
  private String destinationPath_compute() {  return getCompilationUnit(0).destinationPath();  }
  /**
   * @apilvl internal
   */
  protected boolean wildcards_computed = false;
  /**
   * @apilvl internal
   */
  protected WildcardsCompilationUnit wildcards_value;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1121
   */
  @SuppressWarnings({"unchecked", "cast"})
  public WildcardsCompilationUnit wildcards() {
    if(wildcards_computed) {
      return wildcards_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    wildcards_value = wildcards_compute();
    wildcards_value.setParent(this);
    wildcards_value.is$Final = true;
if(true) wildcards_computed = true;
    return wildcards_value;
  }
  /**
   * @apilvl internal
   */
  private WildcardsCompilationUnit wildcards_compute() {
    return new WildcardsCompilationUnit(
      "wildcards",
      new List(),
      new List(),
      new List()
    );
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AnonymousClasses.jrag:16
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_superType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_TypeDecl_superType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AnonymousClasses.jrag:26
   * @apilvl internal
   */
  public ConstructorDecl Define_ConstructorDecl_constructorDecl(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_ConstructorDecl_constructorDecl(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Arrays.jrag:19
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_componentType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return unknownType();
    }
    return getParent().Define_TypeDecl_componentType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:173
   * @apilvl internal
   */
  public LabeledStmt Define_LabeledStmt_lookupLabel(ASTNode caller, ASTNode child, String name) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_LabeledStmt_lookupLabel(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:16
   * @apilvl internal
   */
  public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isDest(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:26
   * @apilvl internal
   */
  public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return true;
    }
    return getParent().Define_boolean_isSource(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:50
   * @apilvl internal
   */
  public boolean Define_boolean_isIncOrDec(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isIncOrDec(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:326
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return true;
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:711
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return true;
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:13
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeException(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return lookupType("java.lang", "Exception");
    }
    return getParent().Define_TypeDecl_typeException(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:15
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeRuntimeException(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return lookupType("java.lang", "RuntimeException");
    }
    return getParent().Define_TypeDecl_typeRuntimeException(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:17
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeError(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return lookupType("java.lang", "Error");
    }
    return getParent().Define_TypeDecl_typeError(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:19
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeNullPointerException(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return lookupType("java.lang", "NullPointerException");
    }
    return getParent().Define_TypeDecl_typeNullPointerException(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:21
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeThrowable(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return lookupType("java.lang", "Throwable");
    }
    return getParent().Define_TypeDecl_typeThrowable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:122
   * @apilvl internal
   */
  public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    throw new Error("Operation handlesException not supported");
  }
}
    return getParent().Define_boolean_handlesException(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:6
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return lookupVariable(name);
    }
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:15
   * @apilvl internal
   */
  public Collection Define_Collection_lookupConstructor(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return Collections.EMPTY_LIST;
    }
    return getParent().Define_Collection_lookupConstructor(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:24
   * @apilvl internal
   */
  public Collection Define_Collection_lookupSuperConstructor(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return Collections.EMPTY_LIST;
    }
    return getParent().Define_Collection_lookupSuperConstructor(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:23
   * @apilvl internal
   */
  public Expr Define_Expr_nestedScope(ASTNode caller, ASTNode child) {
    if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{ throw new UnsupportedOperationException(); }
}
    return getParent().Define_Expr_nestedScope(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:36
   * @apilvl internal
   */
  public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return Collections.EMPTY_LIST;
    }
    return getParent().Define_Collection_lookupMethod(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:18
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeObject(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeObject();
    }
    return getParent().Define_TypeDecl_typeObject(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:19
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeCloneable(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeCloneable();
    }
    return getParent().Define_TypeDecl_typeCloneable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:20
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeSerializable(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeSerializable();
    }
    return getParent().Define_TypeDecl_typeSerializable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:31
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeBoolean(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeBoolean();
    }
    return getParent().Define_TypeDecl_typeBoolean(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:32
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeByte(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeByte();
    }
    return getParent().Define_TypeDecl_typeByte(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:33
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeShort(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeShort();
    }
    return getParent().Define_TypeDecl_typeShort(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:34
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeChar(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeChar();
    }
    return getParent().Define_TypeDecl_typeChar(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:35
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeInt(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeInt();
    }
    return getParent().Define_TypeDecl_typeInt(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:36
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeLong(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeLong();
    }
    return getParent().Define_TypeDecl_typeLong(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:37
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeFloat(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeFloat();
    }
    return getParent().Define_TypeDecl_typeFloat(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:38
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeDouble(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeDouble();
    }
    return getParent().Define_TypeDecl_typeDouble(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:39
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeString(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeString();
    }
    return getParent().Define_TypeDecl_typeString(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:42
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeVoid(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeVoid();
    }
    return getParent().Define_TypeDecl_typeVoid(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:44
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeNull(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeNull();
    }
    return getParent().Define_TypeDecl_typeNull(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:48
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_unknownType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return unknownType();
    }
    return getParent().Define_TypeDecl_unknownType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:82
   * @apilvl internal
   */
  public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return hasPackage(packageName);
    }
    return getParent().Define_boolean_hasPackage(this, caller, packageName);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:104
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_lookupType(ASTNode caller, ASTNode child, String packageName, String typeName) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return lookupType(packageName, typeName);
    }
    return getParent().Define_TypeDecl_lookupType(this, caller, packageName, typeName);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:191
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return SimpleSet.emptySet;
    }
    return getParent().Define_SimpleSet_lookupType(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:298
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeCompile(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeCompile(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:299
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeLoad(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeLoad(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:300
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeRuntime(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeRuntime(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:301
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeBase(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeBase(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:353
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBePublic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:354
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeProtected(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:355
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBePrivate(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:356
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeStatic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:357
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeFinal(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:358
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeAbstract(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:359
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeVolatile(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeVolatile(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:360
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeTransient(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeTransient(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:361
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeStrictfp(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:362
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeSynchronized(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeSynchronized(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:363
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeNative(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayBeNative(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:245
   * @apilvl internal
   */
  public ASTNode Define_ASTNode_enclosingBlock(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_ASTNode_enclosingBlock(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:404
   * @apilvl internal
   */
  public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
    if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    throw new UnsupportedOperationException("outerScope() not defined");
  }
}
    return getParent().Define_VariableScope_outerScope(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:472
   * @apilvl internal
   */
  public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_insideLoop(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:479
   * @apilvl internal
   */
  public boolean Define_boolean_insideSwitch(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_insideSwitch(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:529
   * @apilvl internal
   */
  public Case Define_Case_bind(ASTNode caller, ASTNode child, Case c) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_Case_bind(this, caller, c);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:461
   * @apilvl internal
   */
  public String Define_String_typeDeclIndent(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return "";
    }
    return getParent().Define_String_typeDeclIndent(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:64
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return NameType.NO_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:219
   * @apilvl internal
   */
  public boolean Define_boolean_isAnonymous(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isAnonymous(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:235
   * @apilvl internal
   */
  public Variable Define_Variable_unknownField(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return unknownType().findSingleVariable("unknown");
    }
    return getParent().Define_Variable_unknownField(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:239
   * @apilvl internal
   */
  public MethodDecl Define_MethodDecl_unknownMethod(ASTNode caller, ASTNode child) {
    if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    for(Iterator iter = unknownType().memberMethods("unknown").iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      return m;
    }
    throw new Error("Could not find method unknown in type Unknown");
  }
}
    return getParent().Define_MethodDecl_unknownMethod(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:246
   * @apilvl internal
   */
  public ConstructorDecl Define_ConstructorDecl_unknownConstructor(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return unknownConstructor();
    }
    return getParent().Define_ConstructorDecl_unknownConstructor(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:258
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
    if(true) {
      int i = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_TypeDecl_declType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:646
   * @apilvl internal
   */
  public BodyDecl Define_BodyDecl_enclosingBodyDecl(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_BodyDecl_enclosingBodyDecl(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:668
   * @apilvl internal
   */
  public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isMemberType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:722
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_TypeDecl_hostType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:358
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_switchType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return unknownType();
    }
    return getParent().Define_TypeDecl_switchType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:404
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeVoid();
    }
    return getParent().Define_TypeDecl_returnType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:504
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_TypeDecl_enclosingInstance(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:14
   * @apilvl internal
   */
  public String Define_String_methodHost(ASTNode caller, ASTNode child) {
    if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    throw new Error("Needs extra equation for methodHost()");
  }
}
    return getParent().Define_String_methodHost(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:164
   * @apilvl internal
   */
  public boolean Define_boolean_inExplicitConstructorInvocation(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_inExplicitConstructorInvocation(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:173
   * @apilvl internal
   */
  public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_inStaticContext(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:161
   * @apilvl internal
   */
  public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
    if(caller == getCompilationUnitListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return true;
    }
    return getParent().Define_boolean_reportUnreachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:94
   * @apilvl internal
   */
  public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isMethodParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:95
   * @apilvl internal
   */
  public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isConstructorParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:96
   * @apilvl internal
   */
  public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:47
   * @apilvl internal
   */
  public int Define_int_variableScopeEndLabel(ASTNode caller, ASTNode child, CodeGeneration gen) {
    if(true) { 
   int i = this.getIndexOfChild(caller);
{
    throw new Error("variableScopeEndLabel not valid from here");
  }
}
    return getParent().Define_int_variableScopeEndLabel(this, caller, gen);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1556
   * @apilvl internal
   */
  public int Define_int_condition_false_label(ASTNode caller, ASTNode child) {
    if(true) { 
   int i = this.getIndexOfChild(caller);
{
    throw new Error("condition_false_label not implemented");
  }
}
    return getParent().Define_int_condition_false_label(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1560
   * @apilvl internal
   */
  public int Define_int_condition_true_label(ASTNode caller, ASTNode child) {
    if(true) { 
   int i = this.getIndexOfChild(caller);
{
    throw new Error("condition_true_label not implemented");
  }
}
    return getParent().Define_int_condition_true_label(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:140
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_expectedType(ASTNode caller, ASTNode child) {
    if(caller == getAuxiliaryClassDeclListNoTransform()) {
      int i = caller.getIndexOfChild(child);
      return null;
    }
    if(caller == getCompilationUnitListNoTransform()) {
      int i = caller.getIndexOfChild(child);
      return null;
    }
    return getParent().Define_TypeDecl_expectedType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:16
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(true) {
      int index = this.getIndexOfChild(caller);
      return 0;
    }
    return getParent().Define_int_localNum(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:67
   * @apilvl internal
   */
  public int Define_int_resultSaveLocalNum(ASTNode caller, ASTNode child) {
    if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    throw new Error("Unsupported operation resultSaveLocalNum");
  }
}
    return getParent().Define_int_resultSaveLocalNum(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:94
   * @apilvl internal
   */
  public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:180
   * @apilvl internal
   */
  public ElementValue Define_ElementValue_lookupElementTypeValue(ASTNode caller, ASTNode child, String name) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_ElementValue_lookupElementTypeValue(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:269
   * @apilvl internal
   */
  public boolean Define_boolean_withinSuppressWarnings(ASTNode caller, ASTNode child, String s) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_withinSuppressWarnings(this, caller, s);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:371
   * @apilvl internal
   */
  public boolean Define_boolean_withinDeprecatedAnnotation(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_withinDeprecatedAnnotation(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:430
   * @apilvl internal
   */
  public Annotation Define_Annotation_lookupAnnotation(ASTNode caller, ASTNode child, TypeDecl typeDecl) {
    if(true) {
      int i = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_Annotation_lookupAnnotation(this, caller, typeDecl);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:461
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_enclosingAnnotationDecl(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return unknownType();
    }
    return getParent().Define_TypeDecl_enclosingAnnotationDecl(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericMethodsInference.jrag:39
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return typeNull();
    }
    return getParent().Define_TypeDecl_assignConvertedType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1149
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_typeWildcard(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return wildcards().typeWildcard();
    }
    return getParent().Define_TypeDecl_typeWildcard(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1160
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_lookupWildcardExtends(ASTNode caller, ASTNode child, TypeDecl typeDecl) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return wildcards().lookupWildcardExtends(typeDecl);
    }
    return getParent().Define_TypeDecl_lookupWildcardExtends(this, caller, typeDecl);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1173
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_lookupWildcardSuper(ASTNode caller, ASTNode child, TypeDecl typeDecl) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return wildcards().lookupWildcardSuper(typeDecl);
    }
    return getParent().Define_TypeDecl_lookupWildcardSuper(this, caller, typeDecl);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1195
   * @apilvl internal
   */
  public LUBType Define_LUBType_lookupLUBType(ASTNode caller, ASTNode child, Collection bounds) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return wildcards().lookupLUBType(bounds);
    }
    return getParent().Define_LUBType_lookupLUBType(this, caller, bounds);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1233
   * @apilvl internal
   */
  public GLBType Define_GLBType_lookupGLBType(ASTNode caller, ASTNode child, ArrayList bounds) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return wildcards().lookupGLBType(bounds);
    }
    return getParent().Define_GLBType_lookupGLBType(this, caller, bounds);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsParTypeDecl.jrag:47
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_genericDecl(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return null;
    }
    return getParent().Define_TypeDecl_genericDecl(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:24
   * @apilvl internal
   */
  public boolean Define_boolean_variableArityValid(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return false;
    }
    return getParent().Define_boolean_variableArityValid(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
