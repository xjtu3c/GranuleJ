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
 * @declaredat java.ast:13
 */
public class AbstractDot extends Access implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    isSystemClass_computed = false;
    isFilterClass_VariableDeclaration_values = null;
    isInceptClass_Variable_values = null;
    isInceptMethod_computed = false;
    type_computed = false;
    type_value = null;
    isDUbefore_Variable_values = null;
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
  public AbstractDot clone() throws CloneNotSupportedException {
    AbstractDot node = (AbstractDot)super.clone();
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.isSystemClass_computed = false;
    node.isFilterClass_VariableDeclaration_values = null;
    node.isInceptClass_Variable_values = null;
    node.isInceptMethod_computed = false;
    node.type_computed = false;
    node.type_value = null;
    node.isDUbefore_Variable_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public AbstractDot copy() {
      try {
        AbstractDot node = (AbstractDot)clone();
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
  public AbstractDot fullCopy() {
    AbstractDot res = (AbstractDot)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:315
   */
  
	public boolean isStaticShadowMethod=false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:316
   */
  public boolean isStaticShadowMethod()
	{
		if(hostType()!=null&&hostType().isGranuleDecl()&&!isStaticShadowMethod){
		GranuleDecl granule=(GranuleDecl)hostType();
	    if(getRight() instanceof AbstractDot){
	     AbstractDot dot=(AbstractDot)getRight();
	     dot.isStaticShadowMethod();
	    }
	    else if(isMethodAccess()){
		MethodAccess m=(MethodAccess)getRight();
			if(m.qualifier().staticContextQualifier()){		    
			if(getLeft() instanceof TypeAccess){			
			    TypeAccess type=(TypeAccess)getLeft();			    
			     if(type.name().equals(granule.rootClass().name())){
			     isStaticShadowMethod=true;
			     return true;
			     }
			}
		  }
	  }
	}
	  return false;
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:601
   */
  public String getMethodAccessSig(){        
       return ((MethodAccess)lastAccess()).decl().signature();   	
     }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:605
   */
  public String getReturnType(){
	   MethodDecl md=(MethodDecl)((MethodAccess)lastAccess()).decl();
	   TypeDecl td=md.getTypeAccess().type();
	   if(td.isPrimitive()||td.isBoolean()) 
       return td.boxed().typeName();
       else    	
	   return td.typeName();
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:613
   */
  public String getLeftType(){
	    VariableDeclaration vd=(VariableDeclaration)(((VarAccess)getLeft()).decl());  
        TypeDecl td=vd.getTypeAccess().type();
        if(td.isPrimitive()||td.isBoolean()) 
        return td.boxed().typeName();
        else    	
	    return td.typeName();
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:622
   */
  public String getLeftType(TypeDecl td){
	//TypeDecl td=va.type();
    if(td.isPrimitive()||td.isBoolean()) 
    return td.boxed().typeName();
    else    	
	return td.typeName(); 	
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:629
   */
    
    public String methodAccessSig="";
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:631
   */
  
	
	public String methodReturnType;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:633
   */
  
	
	public String qualifierType;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:635
   */
  public void setMethodAccessSig(){
    	methodAccessSig=getMethodAccessSig();
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:638
   */
  public void setMethodReturnType(){
	    methodReturnType=getReturnType();
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:642
   */
  public void setQualifierType(){
	    qualifierType=getLeftType();
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:645
   */
  public void setQualifierType(TypeDecl va){
	 qualifierType=getLeftType(va);	
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:683
   */
  public String getMethodAccessSig(MethodDecl md){        
       return md.signature();   	
     }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:687
   */
  public String getReturnType(MethodDecl md){
	   TypeDecl td=md.getTypeAccess().type();
	   if(td.isPrimitive()||td.isBoolean()) 
       return td.boxed().typeName();
       else    	
	   return td.typeName();
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:701
   */
  public MethodAccess getMethodAccess()
	{
	    MethodAccess ma=null;
	    if(isMethodAccess()&&!isSystemClass()){ 		   
            if(getLeft() instanceof VarAccess){
			   if(getRight() instanceof AbstractDot){
		          AbstractDot dot=(AbstractDot)getRight();
			       if(dot.getLeft() instanceof MethodAccess){
				     ma=(MethodAccess)dot.getLeft();
				   }
				}
				else{
				  ma=(MethodAccess)lastAccess();
				}
           }
	  }
       return ma;	
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:720
   */
  public boolean isMethodInvoke(){
  		if(lastAccess().isMethodAccess() && !isSystemClass()&&isInceptMethod()){  		
 			  if((getLeft() instanceof VarAccess)){				   				
					setMethodAccessSig();
				    setMethodReturnType();					
 					return true;						
				 }	      
 		 	  }		  		  
  		 return false;
  	}
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:573
   */
  public void toString(StringBuffer s) {
    getLeft().toString(s);
    if(!nextAccess().isArrayAccess())
      s.append(".");
    getRight().toString(s);
  }
  /**
   * @ast method 
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:152
   */
  public Access extractLast() {
    return getRightNoTransform();
 }
  /**
   * @ast method 
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:155
   */
  public void replaceLast(Access access) {
    setRight(access);
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:715
   */
  public void emitStore(CodeGeneration gen) { lastAccess().emitStore(gen); }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:614
   */
  public void createAssignSimpleLoadDest(CodeGeneration gen) {
    lastAccess().createAssignSimpleLoadDest(gen);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:628
   */
  public void createPushAssignmentResult(CodeGeneration gen) {
    lastAccess().createPushAssignmentResult(gen);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:646
   */
  public void createAssignLoadDest(CodeGeneration gen) {
    lastAccess().createAssignLoadDest(gen);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:702
   */
  public void createBCode(CodeGeneration gen) {
    lastAccess().createBCode(gen);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1665
   */
  public void emitEvalBranch(CodeGeneration gen) { lastAccess().emitEvalBranch(gen); }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public AbstractDot() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public AbstractDot(Expr p0, Access p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:14
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:20
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for Left
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setLeft(Expr node) {
    setChild(node, 0);
  }
  /**
   * Getter for Left
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getLeft() {
    return (Expr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getLeftNoTransform() {
    return (Expr)getChildNoTransform(0);
  }
  /**
   * Setter for Right
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setRight(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for Right
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getRight() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getRightNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:109
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant constant() {
      ASTNode$State state = state();
    Constant constant_value = constant_compute();
    return constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant constant_compute() {  return lastAccess().constant();  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:495
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstant() {
      ASTNode$State state = state();
    boolean isConstant_value = isConstant_compute();
    return isConstant_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstant_compute() {  return lastAccess().isConstant();  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:59
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Variable varDecl() {
      ASTNode$State state = state();
    Variable varDecl_value = varDecl_compute();
    return varDecl_value;
  }
  /**
   * @apilvl internal
   */
  private Variable varDecl_compute() {  return lastAccess().varDecl();  }
  /*eq Stmt.isDAafter(Variable v) {
    //System.out.println("### isDAafter reached in " + getClass().getName());
    //throw new NullPointerException();
    throw new Error("Can not compute isDAafter for " + getClass().getName() + " at " + errorPrefix());
  }* @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:337
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafterTrue(Variable v) {
      ASTNode$State state = state();
    boolean isDAafterTrue_Variable_value = isDAafterTrue_compute(v);
    return isDAafterTrue_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafterTrue_compute(Variable v) {  return isDAafter(v);  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:338
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafterFalse(Variable v) {
      ASTNode$State state = state();
    boolean isDAafterFalse_Variable_value = isDAafterFalse_compute(v);
    return isDAafterFalse_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafterFalse_compute(Variable v) {  return isDAafter(v);  }
  protected java.util.Map isDAafter_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:357
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafter(Variable v) {
    Object _parameters = v;
    if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
    if(isDAafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDAafter_Variable_value = isDAafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
    return isDAafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafter_compute(Variable v) {  return lastAccess().isDAafter(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:794
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafterTrue(Variable v) {
      ASTNode$State state = state();
    boolean isDUafterTrue_Variable_value = isDUafterTrue_compute(v);
    return isDUafterTrue_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafterTrue_compute(Variable v) {  return isDUafter(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:795
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafterFalse(Variable v) {
      ASTNode$State state = state();
    boolean isDUafterFalse_Variable_value = isDUafterFalse_compute(v);
    return isDUafterFalse_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafterFalse_compute(Variable v) {  return isDUafter(v);  }
  protected java.util.Map isDUafter_Variable_values;
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:839
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafter(Variable v) {
    Object _parameters = v;
    if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
    if(isDUafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDUafter_Variable_value = isDUafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
    return isDUafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafter_compute(Variable v) {  return lastAccess().isDUafter(v);  }
  /**
   * @apilvl internal
   */
  protected boolean isSystemClass_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isSystemClass_value;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:649
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSystemClass() {
    if(isSystemClass_computed) {
      return isSystemClass_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isSystemClass_value = isSystemClass_compute();
if(isFinal && num == state().boundariesCrossed) isSystemClass_computed = true;
    return isSystemClass_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSystemClass_compute() {
    	String pname=hostType().packageName();
    	if(pname!=null && !"".equals(pname)){
    		if(pname.startsWith("granulej.")|| pname.startsWith("java."))
    		return true;
    	}
    	return false;    	
    }
  protected java.util.Map isFilterClass_VariableDeclaration_values;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:657
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFilterClass(VariableDeclaration vd) {
    Object _parameters = vd;
    if(isFilterClass_VariableDeclaration_values == null) isFilterClass_VariableDeclaration_values = new java.util.HashMap(4);
    if(isFilterClass_VariableDeclaration_values.containsKey(_parameters)) {
      return ((Boolean)isFilterClass_VariableDeclaration_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isFilterClass_VariableDeclaration_value = isFilterClass_compute(vd);
if(isFinal && num == state().boundariesCrossed) isFilterClass_VariableDeclaration_values.put(_parameters, Boolean.valueOf(isFilterClass_VariableDeclaration_value));
    return isFilterClass_VariableDeclaration_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFilterClass_compute(VariableDeclaration vd) {
	  String qname=vd.type().typeName();
	  if(qname!=null && !"".equals(qname)){
    	if(qname.startsWith("granulej.")|| qname.startsWith("java.") || qname.startsWith("org.") || qname.startsWith("javax."))
    	return true;
       }
	   return false;
	}
  protected java.util.Map isInceptClass_Variable_values;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:666
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isInceptClass(Variable va) {
    Object _parameters = va;
    if(isInceptClass_Variable_values == null) isInceptClass_Variable_values = new java.util.HashMap(4);
    if(isInceptClass_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isInceptClass_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isInceptClass_Variable_value = isInceptClass_compute(va);
if(isFinal && num == state().boundariesCrossed) isInceptClass_Variable_values.put(_parameters, Boolean.valueOf(isInceptClass_Variable_value));
    return isInceptClass_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isInceptClass_compute(Variable va) {
      TypeDecl td=va.type();
	  String qname=td.typeName();
	  if(qname==null || "".equals(qname)){
	  return false;
	  }
      if(qname.startsWith("granulej.")|| qname.startsWith("java.") || qname.startsWith("org.") || qname.startsWith("javax.")){
    	return false;
      }  
	  if(td.isClassDecl() &&((ClassDecl)td).hasShadowClass())
	  return true;
	  else if(td.isInterfaceDecl() &&((InterfaceDecl)td).hasShadowClass())
	  return true;
	  else
	  return false;	
   }
  /**
   * @apilvl internal
   */
  protected boolean isInceptMethod_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isInceptMethod_value;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:695
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isInceptMethod() {
    if(isInceptMethod_computed) {
      return isInceptMethod_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isInceptMethod_value = isInceptMethod_compute();
if(isFinal && num == state().boundariesCrossed) isInceptMethod_computed = true;
    return isInceptMethod_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isInceptMethod_compute() {
	   MethodAccess ma=(MethodAccess)lastAccess();
	   MethodDecl md=ma.decl();
       return md.isOverridden();
    }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\QualifiedNames.jrag:65
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String typeName() {
      ASTNode$State state = state();
    String typeName_value = typeName_compute();
    return typeName_value;
  }
  /**
   * @apilvl internal
   */
  private String typeName_compute() {  return lastAccess().typeName();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:15
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isTypeAccess() {
      ASTNode$State state = state();
    boolean isTypeAccess_value = isTypeAccess_compute();
    return isTypeAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isTypeAccess_compute() {  return getRight().isTypeAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:18
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isMethodAccess() {
      ASTNode$State state = state();
    boolean isMethodAccess_value = isMethodAccess_compute();
    return isMethodAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isMethodAccess_compute() {  return getRight().isMethodAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:22
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFieldAccess() {
      ASTNode$State state = state();
    boolean isFieldAccess_value = isFieldAccess_compute();
    return isFieldAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFieldAccess_compute() {  return getRight().isFieldAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:27
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSuperAccess() {
      ASTNode$State state = state();
    boolean isSuperAccess_value = isSuperAccess_compute();
    return isSuperAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSuperAccess_compute() {  return getRight().isSuperAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:34
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSeedAccess() {
      ASTNode$State state = state();
    boolean isSeedAccess_value = isSeedAccess_compute();
    return isSeedAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSeedAccess_compute() {  return getRight().isSeedAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:40
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPreviousAccess() {
      ASTNode$State state = state();
    boolean isPreviousAccess_value = isPreviousAccess_compute();
    return isPreviousAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPreviousAccess_compute() {  return getRight().isPreviousAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:46
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isThisAccess() {
      ASTNode$State state = state();
    boolean isThisAccess_value = isThisAccess_compute();
    return isThisAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isThisAccess_compute() {  return getRight().isThisAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:52
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPackageAccess() {
      ASTNode$State state = state();
    boolean isPackageAccess_value = isPackageAccess_compute();
    return isPackageAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPackageAccess_compute() {  return getRight().isPackageAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:57
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isArrayAccess() {
      ASTNode$State state = state();
    boolean isArrayAccess_value = isArrayAccess_compute();
    return isArrayAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isArrayAccess_compute() {  return getRight().isArrayAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:61
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isClassAccess() {
      ASTNode$State state = state();
    boolean isClassAccess_value = isClassAccess_compute();
    return isClassAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isClassAccess_compute() {  return getRight().isClassAccess();  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:65
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSuperConstructorAccess() {
      ASTNode$State state = state();
    boolean isSuperConstructorAccess_value = isSuperConstructorAccess_compute();
    return isSuperConstructorAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSuperConstructorAccess_compute() {  return getRight().isSuperConstructorAccess();  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:77
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isQualified() {
      ASTNode$State state = state();
    boolean isQualified_value = isQualified_compute();
    return isQualified_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isQualified_compute() {  return hasParentDot();  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:80
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr leftSide() {
      ASTNode$State state = state();
    Expr leftSide_value = leftSide_compute();
    return leftSide_value;
  }
  /**
   * @apilvl internal
   */
  private Expr leftSide_compute() {  return getLeft();  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:82
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access rightSide() {
      ASTNode$State state = state();
    Access rightSide_value = rightSide_compute();
    return rightSide_value;
  }
  /**
   * @apilvl internal
   */
  private Access rightSide_compute() {	  
	 return getRight/*NoTransform*/() instanceof AbstractDot ? (Access)((AbstractDot)getRight/*NoTransform*/()).getLeft() : (Access)getRight();
  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:88
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access lastAccess() {
      ASTNode$State state = state();
    Access lastAccess_value = lastAccess_compute();
    return lastAccess_value;
  }
  /**
   * @apilvl internal
   */
  private Access lastAccess_compute() {  return getRight().lastAccess();  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:96
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access nextAccess() {
      ASTNode$State state = state();
    Access nextAccess_value = nextAccess_compute();
    return nextAccess_value;
  }
  /**
   * @apilvl internal
   */
  private Access nextAccess_compute() {  return rightSide();  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:98
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr prevExpr() {
      ASTNode$State state = state();
    Expr prevExpr_value = prevExpr_compute();
    return prevExpr_value;
  }
  /**
   * @apilvl internal
   */
  private Expr prevExpr_compute() {  return leftSide();  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:109
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasPrevExpr() {
      ASTNode$State state = state();
    boolean hasPrevExpr_value = hasPrevExpr_compute();
    return hasPrevExpr_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasPrevExpr_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:60
   */
  @SuppressWarnings({"unchecked", "cast"})
  public NameType predNameType() {
      ASTNode$State state = state();
    NameType predNameType_value = predNameType_compute();
    return predNameType_value;
  }
  /**
   * @apilvl internal
   */
  private NameType predNameType_compute() {  return getLeft() instanceof Access ? ((Access)getLeft()).predNameType() : NameType.NO_NAME;  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:251
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl type() {
    if(type_computed) {
      return type_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    type_value = type_compute();
if(isFinal && num == state().boundariesCrossed) type_computed = true;
    return type_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl type_compute() {  return lastAccess().type();  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:16
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVariable() {
      ASTNode$State state = state();
    boolean isVariable_value = isVariable_compute();
    return isVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVariable_compute() {  return lastAccess().isVariable();  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:194
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean staticContextQualifier() {
      ASTNode$State state = state();
    boolean staticContextQualifier_value = staticContextQualifier_compute();
    return staticContextQualifier_value;
  }
  /**
   * @apilvl internal
   */
  private boolean staticContextQualifier_compute() {  return lastAccess().staticContextQualifier();  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:482
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean needsPop() {
      ASTNode$State state = state();
    boolean needsPop_value = needsPop_compute();
    return needsPop_value;
  }
  /**
   * @apilvl internal
   */
  private boolean needsPop_compute() {  return lastAccess().needsPop();  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:495
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVarAccessWithAccessor() {
      ASTNode$State state = state();
    boolean isVarAccessWithAccessor_value = isVarAccessWithAccessor_compute();
    return isVarAccessWithAccessor_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVarAccessWithAccessor_compute() {  return lastAccess().isVarAccessWithAccessor();  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1531
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean definesLabel() {
      ASTNode$State state = state();
    boolean definesLabel_value = definesLabel_compute();
    return definesLabel_value;
  }
  /**
   * @apilvl internal
   */
  private boolean definesLabel_compute() {  return getParent().definesLabel();  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1591
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean canBeTrue() {
      ASTNode$State state = state();
    boolean canBeTrue_value = canBeTrue_compute();
    return canBeTrue_value;
  }
  /**
   * @apilvl internal
   */
  private boolean canBeTrue_compute() {  return lastAccess().canBeTrue();  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1601
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean canBeFalse() {
      ASTNode$State state = state();
    boolean canBeFalse_value = canBeFalse_compute();
    return canBeFalse_value;
  }
  /**
   * @apilvl internal
   */
  private boolean canBeFalse_compute() {  return lastAccess().canBeFalse();  }
  protected java.util.Map isDUbefore_Variable_values;
  /**
   * @attribute inh
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:698
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUbefore(Variable v) {
    Object _parameters = v;
    if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
    if(isDUbefore_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDUbefore_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
if(isFinal && num == state().boundariesCrossed) isDUbefore_Variable_values.put(_parameters, Boolean.valueOf(isDUbefore_Variable_value));
    return isDUbefore_Variable_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:21
   * @apilvl internal
   */
  public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
    if(caller == getLeftNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_isDest(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:31
   * @apilvl internal
   */
  public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
    if(caller == getLeftNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_isSource(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:356
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getRightNoTransform()) {
      return getLeft().isDAafter(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:838
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getRightNoTransform()) {
      return getLeft().isDUafter(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:17
   * @apilvl internal
   */
  public Collection Define_Collection_lookupConstructor(ASTNode caller, ASTNode child) {
    if(caller == getRightNoTransform()) {
      return getLeft().type().constructors();
    }
    return getParent().Define_Collection_lookupConstructor(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:25
   * @apilvl internal
   */
  public Collection Define_Collection_lookupSuperConstructor(ASTNode caller, ASTNode child) {
    if(caller == getRightNoTransform()) {
      return getLeft().type().lookupSuperConstructor();
    }
    return getParent().Define_Collection_lookupSuperConstructor(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:21
   * @apilvl internal
   */
  public Expr Define_Expr_nestedScope(ASTNode caller, ASTNode child) {
    if(caller == getLeftNoTransform()) {
      return isQualified() ? nestedScope() : this;
    }
    if(caller == getRightNoTransform()) {
      return isQualified() ? nestedScope() : this;
    }
    return getParent().Define_Expr_nestedScope(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:104
   * @apilvl internal
   */
  public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
    if(caller == getRightNoTransform()){
		TypeDecl type=getLeft().type();	
		if(hostType().isShadowClassDecl()&&type.isClassDecl())
		{
			while(((ClassDecl)type).hasSuperclass())
			{
				Collection c=((ClassDecl)type).UnionMemberMethods(name);
				if(!c.isEmpty()) return c;
				type=((ClassDecl)type).superclass();
			}
		}
		else
		 return getLeft().type().memberMethods(name);
}
    return getParent().Define_Collection_lookupMethod(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:84
   * @apilvl internal
   */
  public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
    if(caller == getRightNoTransform()) {
      return getLeft().hasQualifiedPackage(packageName);
    }
    return getParent().Define_boolean_hasPackage(this, caller, packageName);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:380
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
    if(caller == getRightNoTransform()){
    return getLeft().qualifiedLookupType(name);
  }
    return getParent().Define_SimpleSet_lookupType(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:195
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getRightNoTransform()) {
      return getLeft().qualifiedLookupVariable(name);
    }
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:58
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getLeftNoTransform()) {
      return getRight().predNameType();
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:514
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
    if(caller == getRightNoTransform()) {
      return getLeft().type();
    }
    return getParent().Define_TypeDecl_enclosingInstance(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:13
   * @apilvl internal
   */
  public String Define_String_methodHost(ASTNode caller, ASTNode child) {
    if(caller == getRightNoTransform()) {
      return getLeft().type().typeName();
    }
    return getParent().Define_String_methodHost(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag at line 305
    if(isStaticShadowMethod()) {
      state().duringMethodTransform++;
      ASTNode result = rewriteRule0();
      state().duringMethodTransform--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:305
   * @apilvl internal
   */  private AbstractDot rewriteRule0() {
{
		  TypeAccess type=(TypeAccess)getLeft();
		  String granulename=hostType().name();
		  String nname=granulename+"%"+type.name();
		  TypeAccess ntype=new TypeAccess(type.getPackage(),nname);
		  setLeft(ntype);
		  return this;		
		}  }
}
