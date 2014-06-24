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
 * @declaredat ExternalVar.ast:3
 */
public class ContextVarDeclaration extends ContextVar implements Cloneable, SimpleSet, Iterator, Variable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    constant_computed = false;
    constant_value = null;
    attributes_computed = false;
    attributes_value = null;
    flags_computed = false;
    sourceVariableDecl_computed = false;
    sourceVariableDecl_value = null;
    localNum_computed = false;
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
  public ContextVarDeclaration clone() throws CloneNotSupportedException {
    ContextVarDeclaration node = (ContextVarDeclaration)super.clone();
    node.constant_computed = false;
    node.constant_value = null;
    node.attributes_computed = false;
    node.attributes_value = null;
    node.flags_computed = false;
    node.sourceVariableDecl_computed = false;
    node.sourceVariableDecl_value = null;
    node.localNum_computed = false;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ContextVarDeclaration copy() {
      try {
        ContextVarDeclaration node = (ContextVarDeclaration)clone();
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
  public ContextVarDeclaration fullCopy() {
    ContextVarDeclaration res = (ContextVarDeclaration)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:89
   */
  public boolean isSingleton() { return true; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:90
   */
  public boolean isSingleton(Object o) { return contains(o); }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:103
   */
  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:108
   */
  
  private ContextVarDeclaration iterElem;
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:109
   */
  public Iterator iterator() { iterElem = this; return this; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:110
   */
  public boolean hasNext() { return iterElem != null; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:111
   */
  public Object next() { Object o = iterElem; iterElem = null; return o; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:112
   */
  public void remove() { throw new UnsupportedOperationException(); }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:131
   */
  public Stmt createContextVarBlockStmt(){		     
		 String methodname=new String("addContext");
		 TypeAccess ta= new TypeAccess("GopContext");
	     List<Expr> l =new List<Expr>();
	     StringLiteral para1=new StringLiteral(getID().toString());
	     l.add(para1); 
	     if(hasInit()){
	      if(!getTypeAccess().type().isString()){
	         String method=new String("valueOf");
	         TypeAccess tta=new TypeAccess("String");
	         List<Expr> ll=new List<Expr>();
	         ll.add(getInit());
	         MethodAccess mm=new MethodAccess(method,ll);        	 
	         l.add(tta.qualifiesAccess(new MethodAccess(method,ll)));
	      }
	      else{
	    	 l.add(getInit());
	      }
	     }
	     else 
	     {   if(getTypeAccess().type().isString()){
	    	 l.add(new StringLiteral(""));
	         }
	        else{
	    	l.add(new StringLiteral("0"));
	        }
	     }
	     /*if(getModifiers().toString().equals("private")){
	    	 String way=new String("getLocalIP");
	  		 ParseName ipname= new ParseName("getLocalIP");
	  		 List<Expr> lst=new List<Expr>();
	  	     MethodAccess m=new MethodAccess(way,lst);		    	  
	  	     AbstractDot d=new AbstractDot(ta,ipname);
	  	     d.replaceLast(m);
	         l.add(d);
		     }
		     else{*/
		     StringLiteral para3=new StringLiteral(getModifiers().toString());
		     l.add(para3);
		     //}	 
	     //ParseName pn=new ParseName("addContext");
	    // AbstractDot d=new AbstractDot(ta,pn);
	     //MethodAccess m=new MethodAccess(methodname,l);
	     //m.setStart(this.getStart()); 
	     //m.setEnd(this.getEnd()); 
	     //d.replaceLast(m);
	     Stmt st=new ExprStmt(ta.qualifiesAccess(new MethodAccess(methodname,l)));
	     return st;
  }
  /**
   * @ast method 
   * @aspect GOPModifier
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1364
   */
  public void checkModifiers()
  {
 	 super.checkModifiers();
	  if(hostType()==null){
		  if(isProtected())
		  error("a published context variable may not be protected");
		  if(isCompile())
		  error("a published context variable may not be compile");
		  if(isLoad())
		  error("a published context variable may not be load");
		  if(isRuntime())
		  error("a published context variable may not be runtime");
	  }	
	  if(hostType()!=null&&!hostType().isGranuleDecl())
	  error("context variable is declared can only exsit in granule body ! ");
 	// if(hostType()!=null&&!hostType().isGranuleDecl()&& isExternal())
 		// error("exteranl field can only exsit in Granule");
  }
  /**
   * @ast method 
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1416
   */
  public void typeCheck(){
	   if(hostType()==null&&hasInit()){
	   TypeDecl source = getInit().type();
       TypeDecl dest = type();
       if(!source.assignConversionTo(dest, getInit()))
        error("can not assign " + name() + " of type " + dest.typeName() +
              " a value of type " + source.typeName());
	   }	  
	   if(hasInit()&& hostType()!=null&&hostType().isGranuleDecl())		
	    error("External Variable in GranuleDecl must not be initiliazed"); 
	   if(!getTypeAccess().type().isPrimitiveType()&&!getTypeAccess().type().isString())
	   error("Context variable " +name()+ " is not be defined as "+ getTypeAccess());
}
  /**
   * @ast method 
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1430
   */
  

private boolean hasAreadyInit=false;
  /**
   * @ast method 
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1431
   */
  public boolean getHasAreadlyInit()
{
return hasAreadyInit;
}
  /**
   * @ast method 
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1435
   */
  public void setHasAreadlyInit(boolean hasAreadyInit)
{
	this.hasAreadyInit=hasAreadyInit;
}
  /**
   * @ast method 
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:284
   */
  public void nameCheck()
  {
	  super.nameCheck();
	  if(hostType()==null)
	  {
		Program root=getProgram();
	    	for(Iterator iter=root.contextVariable(name()).iterator();iter.hasNext();)
	    	{
	    		Variable v=(Variable)iter.next();    		
	    		if(v!=this&&v.name().equals(name()))
	            error("field named " +name()+" is multiply declared in program publish!");
	    	}
	  }
	  else if(hostType().isGranuleDecl())
	  {
		 for(Iterator iter=(hostType().memberFields(name())).iterator();iter.hasNext();)
		 {
			 Variable v=(Variable)iter.next();
			 if(v!=this &&v.hostType()==hostType())
			 error("field named "+ name()+" is multiply declared in type "+hostType().typeName());
		  } 
		   Program root=getProgram();
		   boolean isfound=false;
	       for(Iterator iter1=root.contextVariable(name()).iterator();iter1.hasNext()&&!isfound;)
	       {
	    		Object vv=iter1.next();    		
                if(vv instanceof ContextVarDeclaration){
                ContextVarDeclaration convar=(ContextVarDeclaration)vv;
                if(convar.name().equals(name()))
                isfound=true;              
                if(isfound&&!getModifiers().toString().equals("")){
                   boolean isPrivate=false;
                   if(convar.getModifiers().toString().equals("private")){	
                   Modifiers modifiers=getModifiersNoTransform().fullCopy();
                   List l=modifiers.getModifierList();
                    for(int j = 0; j < l.getNumChild(); j++)
                    {
                        Modifier modifier=(Modifier)l.getChild(j);
                        if(modifier.getID().equals("private"))
                        isPrivate=true;
                    }
                    if(!isPrivate)
                    error("the modifiers of field named "+name() +" in the granule "+hostType().typeName()+" is difference from that of the declared one in the publish!");
                  }
                   else if(!convar.getModifiers().toString().equals(getModifiers().toString()))
                   {
                	error("the modifiers of field named "+name() +" in the granule "+hostType().typeName()+" is difference from that of the declared one in the publish!");   
                   }                 
            }
          }
	   }
	       if(!isfound)
	       error("field named "+name()+ " is not declared  in the granule " +hostType().typeName()+", it must be declared when it is published");
	  }
	  else 
	  {
		  error("field named "+name()+"is defined illegal! ");
	  }
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:193
   */
  public void toString(StringBuffer s)
  {
    s.append(indent()); 
    s.append("external ");
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name());
    if(hasInit()) {
      s.append(" = ");
      getInit().toString(s);
    }
    s.append(";");
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:695
   */
  public void emitLoadField(CodeGeneration gen, TypeDecl typeDecl) {
	    if(typeDecl.isGranuleDecl())
	    {
	    String classname = typeDecl.constantPoolName();
	    String      desc = type().typeDescriptor();
	    String      name = name();
	    int index = gen.constantPool().addFieldref(classname, name, desc);
	    if(isStatic())
	      gen.emit(Bytecode.GETSTATIC, type().variableSize()).add2(index);
	    else
  	      gen.emit(Bytecode.GETFIELD, type().variableSize() - 1).add2(index);
	    }
	   else 
	     error("Context variable declaration is not correctness!");
	  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:782
   */
  public void emitStoreField(CodeGeneration gen, TypeDecl typeDecl) {
	    String classname = typeDecl.constantPoolName();
	    String      desc = type().typeDescriptor();
	    String      name = name();
	    int index=gen.constantPool().addFieldref(classname, name, desc);
	    //if(isStatic())
	     // gen.emit(Bytecode.PUTSTATIC, -type().variableSize()).add2(index);
	    //else
	      gen.emit(Bytecode.PUTFIELD, -type().variableSize() - 1).add2(index);
	  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:387
   */
  public boolean clear() {
	    return false;
	 }
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:1
   */
  public ContextVarDeclaration() {
    super();

    setChild(new Opt(), 2);

  }
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:8
   */
  public ContextVarDeclaration(Modifiers p0, Access p1, String p2, Opt<Expr> p3) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
  }
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:14
   */
  public ContextVarDeclaration(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:23
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat ExternalVar.ast:29
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat ExternalVar.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_ID;
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:9
   */
  
  public int IDstart;
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:10
   */
  
  public int IDend;
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:11
   */
  public void setID(beaver.Symbol symbol) {
    if(symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setID is only valid for String lexemes");
    tokenString_ID = (String)symbol.value;
    IDstart = symbol.getStart();
    IDend = symbol.getEnd();
  }
  /**
   * Getter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:22
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for InitOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:5
   */
  public void setInitOpt(Opt<Expr> opt) {
    setChild(opt, 2);
  }
  /**
   * Does this node have a Init child?
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:12
   */
  public boolean hasInit() {
    return getInitOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr getInit() {
    return (Expr)getInitOpt().getChild(0);
  }
  /**
   * Setter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:27
   */
  public void setInit(Expr node) {
    getInitOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOpt() {
    return (Opt<Expr>)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOptNoTransform() {
    return (Opt<Expr>)getChildNoTransform(2);
  }
  /**
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:88
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean contains(Object o) {
      ASTNode$State state = state();
    boolean contains_Object_value = contains_compute(o);
    return contains_Object_value;
  }
  /**
   * @apilvl internal
   */
  private boolean contains_compute(Object o) {  return this == o;  }
  /**
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:101
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int size() {
      ASTNode$State state = state();
    int size_value = size_compute();
    return size_value;
  }
  /**
   * @apilvl internal
   */
  private int size_compute() {  return 1;  }
  /**
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:102
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isEmpty() {
      ASTNode$State state = state();
    boolean isEmpty_value = isEmpty_compute();
    return isEmpty_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isEmpty_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:13
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isClassVariable() {
      ASTNode$State state = state();
    boolean isClassVariable_value = isClassVariable_compute();
    return isClassVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isClassVariable_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:14
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isInstanceVariable() {
      ASTNode$State state = state();
    boolean isInstanceVariable_value = isInstanceVariable_compute();
    return isInstanceVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isInstanceVariable_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:15
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isMethodParameter() {
      ASTNode$State state = state();
    boolean isMethodParameter_value = isMethodParameter_compute();
    return isMethodParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isMethodParameter_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:16
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstructorParameter() {
      ASTNode$State state = state();
    boolean isConstructorParameter_value = isConstructorParameter_compute();
    return isConstructorParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstructorParameter_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:17
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isExceptionHandlerParameter() {
      ASTNode$State state = state();
    boolean isExceptionHandlerParameter_value = isExceptionHandlerParameter_compute();
    return isExceptionHandlerParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isExceptionHandlerParameter_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:18
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isLocalVariable() {
      ASTNode$State state = state();
    boolean isLocalVariable_value = isLocalVariable_compute();
    return isLocalVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isLocalVariable_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFinal() {
      ASTNode$State state = state();
    boolean isFinal_value = isFinal_compute();
    return isFinal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFinal_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:20
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isBlank() {
      ASTNode$State state = state();
    boolean isBlank_value = isBlank_compute();
    return isBlank_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isBlank_compute() {  return !hasInit();  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:21
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isStatic() {
      ASTNode$State state = state();
    boolean isStatic_value = isStatic_compute();
    return isStatic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isStatic_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:23
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String name() {
      ASTNode$State state = state();
    String name_value = name_compute();
    return name_value;
  }
  /**
   * @apilvl internal
   */
  private String name_compute() {  return getID();  }
  /**
   * @apilvl internal
   */
  protected boolean constant_computed = false;
  /**
   * @apilvl internal
   */
  protected Constant constant_value;
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:24
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant constant() {
    if(constant_computed) {
      return constant_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    constant_value = constant_compute();
if(isFinal && num == state().boundariesCrossed) constant_computed = true;
    return constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant constant_compute() {  return type().cast(getInit().constant());  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:26
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl type() {
      ASTNode$State state = state();
    TypeDecl type_value = type_compute();
    return type_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl type_compute() {  return getTypeAccess().type();  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:27
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSynthetic() {
      ASTNode$State state = state();
    boolean isSynthetic_value = isSynthetic_compute();
    return isSynthetic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSynthetic_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect GOPModifier
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1363
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isExternal() {
      ASTNode$State state = state();
    boolean isExternal_value = isExternal_compute();
    return isExternal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isExternal_compute() {  return getModifiers().isExternal();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:291
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPublic() {
      ASTNode$State state = state();
    boolean isPublic_value = isPublic_compute();
    return isPublic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPublic_compute() {  return getModifiers().isPublic();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:292
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPrivate() {
      ASTNode$State state = state();
    boolean isPrivate_value = isPrivate_compute();
    return isPrivate_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPrivate_compute() {  return getModifiers().isPrivate();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:293
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isProtected() {
      ASTNode$State state = state();
    boolean isProtected_value = isProtected_compute();
    return isProtected_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isProtected_compute() {  return getModifiers().isProtected();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:294
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVolatile() {
      ASTNode$State state = state();
    boolean isVolatile_value = isVolatile_compute();
    return isVolatile_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVolatile_compute() {  return getModifiers().isVolatile();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:295
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isCompile() {
      ASTNode$State state = state();
    boolean isCompile_value = isCompile_compute();
    return isCompile_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isCompile_compute() {  return getModifiers().isCompile();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:296
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isLoad() {
      ASTNode$State state = state();
    boolean isLoad_value = isLoad_compute();
    return isLoad_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isLoad_compute() {  return getModifiers().isLoad();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:297
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isRuntime() {
      ASTNode$State state = state();
    boolean isRuntime_value = isRuntime_compute();
    return isRuntime_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isRuntime_compute() {  return getModifiers().isRuntime();  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:70
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isParameter() {
      ASTNode$State state = state();
    boolean isParameter_value = isParameter_compute();
    return isParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isParameter_compute() {  return false;  }
  /**
   * @apilvl internal
   */
  protected boolean attributes_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection attributes_value;
  /**
   * @attribute syn
   * @aspect Attributes
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Attributes.jrag:231
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection attributes() {
    if(attributes_computed) {
      return attributes_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    attributes_value = attributes_compute();
if(isFinal && num == state().boundariesCrossed) attributes_computed = true;
    return attributes_value;
  }
  /**
   * @apilvl internal
   */
  private Collection attributes_compute() {
	    ArrayList l = new ArrayList();
	    if(isStatic() && isFinal()&& (type().isPrimitive() || type().isString()))
	      l.add(new ConstantValueAttribute(hostType().constantPool(), this));
	    return l;
	  }
  /**
   * @apilvl internal
   */
  protected boolean flags_computed = false;
  /**
   * @apilvl internal
   */
  protected int flags_value;
  /**
   * @attribute syn
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:112
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int flags() {
    if(flags_computed) {
      return flags_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    flags_value = flags_compute();
if(isFinal && num == state().boundariesCrossed) flags_computed = true;
    return flags_value;
  }
  /**
   * @apilvl internal
   */
  private int flags_compute() {
	  int res=0;
	  if(isPublic()) res|=Modifiers.ACC_PUBLIC;
	  if(isPrivate()) res|=Modifiers.ACC_PRIVATE;
	  //if(isExternal()) res|=Modifiers.ACC_EXTERNAL;
	  if(isCompile())res|=Modifiers.ACC_COMPILE;
	  if(isLoad())  res|=Modifiers.ACC_LOAD;
	  if(isRuntime()) res|=Modifiers.ACC_RUN;
	  return res;
  }
  /**
   * @apilvl internal
   */
  protected boolean sourceVariableDecl_computed = false;
  /**
   * @apilvl internal
   */
  protected Variable sourceVariableDecl_value;
  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1289
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Variable sourceVariableDecl() {
    if(sourceVariableDecl_computed) {
      return sourceVariableDecl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    sourceVariableDecl_value = sourceVariableDecl_compute();
if(isFinal && num == state().boundariesCrossed) sourceVariableDecl_computed = true;
    return sourceVariableDecl_value;
  }
  /**
   * @apilvl internal
   */
  private Variable sourceVariableDecl_compute() {  return this;  }
  /**
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:731
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl hostType() {
      ASTNode$State state = state();
    TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
    return hostType_value;
  }
  /**
   * @apilvl internal
   */
  protected boolean localNum_computed = false;
  /**
   * @apilvl internal
   */
  protected int localNum_value;
  /**
   * @attribute inh
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:14
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int localNum() {
    if(localNum_computed) {
      return localNum_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    localNum_value = getParent().Define_int_localNum(this, null);
if(isFinal && num == state().boundariesCrossed) localNum_computed = true;
    return localNum_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:302
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeCompile(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeCompile(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:303
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeLoad(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeLoad(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:304
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeRuntime(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeRuntime(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:305
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePublic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:306
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_mayBeProtected(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:307
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePrivate(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:308
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_mayBeStatic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:309
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_mayBeFinal(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:310
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeTransient(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_mayBeTransient(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:311
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeVolatile(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_mayBeVolatile(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 2004
    if(hostType()==null&&getModifiers().toString().equals("")) {
      state().duringGOP++;
      ASTNode result = rewriteRule0();
      state().duringGOP--;
      return result;
    }

    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 2014
    if(hostType()!=null&&hostType().isGranuleDecl()&&(getModifiers().toString().equals("compile")||getModifiers().toString().equals("load")||getModifiers().toString().equals("runtime"))) {
      state().duringGOP++;
      ASTNode result = rewriteRule1();
      state().duringGOP--;
      return result;
    }

    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 2025
    if(hostType()!=null&&hostType().isGranuleDecl()&&getModifiers().toString().equals("")) {
      state().duringGOP++;
      ASTNode result = rewriteRule2();
      state().duringGOP--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:2004
   * @apilvl internal
   */  private ContextVarDeclaration rewriteRule0() {
{
	  Modifier m=new Modifier("private");
	  List<Modifier> ll=new List<Modifier>();
	  ll.add(m);
	  Modifiers ms=new Modifiers(ll);
	  setModifiers(ms);
	  return this;
   }  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:2014
   * @apilvl internal
   */  private ContextVarDeclaration rewriteRule1() {
{
	   Modifier m=new Modifier("private");
	   Modifiers modifiers=getModifiersNoTransform().fullCopy();
	   List l=modifiers.getModifierList();
	   l.add(m);
	   Modifiers ms=new Modifiers(l);
	   setModifiers(ms);
	   return this;
   }  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:2025
   * @apilvl internal
   */  private ContextVarDeclaration rewriteRule2() {
{
      Program root=getProgram();
      boolean isfound=false;
      for(Iterator iter=root.contextVariable(name()).iterator();iter.hasNext()&&!isfound;)
  	{
  	    
    	Object v=iter.next(); 
    	if(v instanceof ContextVarDeclaration){
    	ContextVarDeclaration vv=(ContextVarDeclaration)v;
  		if(vv.name().equals(name())){
  	     Modifiers modifiers=vv.getModifiersNoTransform().fullCopy();
  		 setModifiers(modifiers);
  	     isfound=true;  
       }
    }
  	}
    if(!isfound)
    {
		  List l=new List();
  		  Modifier ms=new Modifier("private");
  		  l.add(ms);
  		  Modifiers ns=new Modifiers(l);
  		  setModifiers(ns);
    }
    return this;
  	}  }
}
