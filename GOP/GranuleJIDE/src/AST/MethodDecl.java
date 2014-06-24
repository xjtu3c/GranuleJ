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
 * @declaredat java.ast:94
 */
public class MethodDecl extends MemberDecl implements Cloneable, SimpleSet, Iterator, ExceptionHolder {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    accessibleFrom_TypeDecl_values = null;
    throwsException_TypeDecl_values = null;
    isFixedExpr_computed = false;
    signature_computed = false;
    signature_value = null;
    moreSpecificThan_MethodDecl_values = null;
    overrides_MethodDecl_values = null;
    hides_MethodDecl_values = null;
    parameterDeclaration_String_values = null;
    primaryMethod_String_values = null;
    isOverridingMethod_computed = false;
    isOverriding_computed = false;
    getGranuleName_computed = false;
    getGranuleName_value = null;
    overrides_computed = false;
    overrides_value = null;
    isFromSourceFilesTo_computed = false;
    isOverridden_computed = false;
    type_computed = false;
    type_value = null;
    attributes_computed = false;
    attributes_value = null;
    descName_computed = false;
    descName_value = null;
    bytecodes_ConstantPool_values = null;
    flags_computed = false;
    offsetBeforeParameters_computed = false;
    offsetAfterParameters_computed = false;
    resultOffset_computed = false;
    usesTypeVariable_computed = false;
    sourceMethodDecl_computed = false;
    sourceMethodDecl_value = null;
    handlesException_TypeDecl_values = null;
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
  public MethodDecl clone() throws CloneNotSupportedException {
    MethodDecl node = (MethodDecl)super.clone();
    node.accessibleFrom_TypeDecl_values = null;
    node.throwsException_TypeDecl_values = null;
    node.isFixedExpr_computed = false;
    node.signature_computed = false;
    node.signature_value = null;
    node.moreSpecificThan_MethodDecl_values = null;
    node.overrides_MethodDecl_values = null;
    node.hides_MethodDecl_values = null;
    node.parameterDeclaration_String_values = null;
    node.primaryMethod_String_values = null;
    node.isOverridingMethod_computed = false;
    node.isOverriding_computed = false;
    node.getGranuleName_computed = false;
    node.getGranuleName_value = null;
    node.overrides_computed = false;
    node.overrides_value = null;
    node.isFromSourceFilesTo_computed = false;
    node.isOverridden_computed = false;
    node.type_computed = false;
    node.type_value = null;
    node.attributes_computed = false;
    node.attributes_value = null;
    node.descName_computed = false;
    node.descName_value = null;
    node.bytecodes_ConstantPool_values = null;
    node.flags_computed = false;
    node.offsetBeforeParameters_computed = false;
    node.offsetAfterParameters_computed = false;
    node.resultOffset_computed = false;
    node.usesTypeVariable_computed = false;
    node.sourceMethodDecl_computed = false;
    node.sourceMethodDecl_value = null;
    node.handlesException_TypeDecl_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl copy() {
      try {
        MethodDecl node = (MethodDecl)clone();
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
  public MethodDecl fullCopy() {
    MethodDecl res = (MethodDecl)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:204
   */
  
     protected LinkedList auxiliarymethodes=new LinkedList();
  /**
   * @ast method 
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:205
   */
  public Collection auxiliarymethodes() {
   		Program root=getProgram();
   		if(!root.isRegisterMethodDecl) {
   		   root.registerMethodDecl();
   		   root.isRegisterMethodDecl=true;
   		}
   		return auxiliarymethodes;
   	}
  /**
   * @ast method 
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:230
   */
  boolean hasAuxiliaryMethodDecl()
      {
    	  if(!auxiliarymethodes().isEmpty())
    		  return true;
    	      return false;    	
   	 }
  /**
   * @ast method 
   * @aspect BoundNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BoundNames.jrag:91
   */
  public Access createBoundAccess(List args) {
    if(isStatic()) {
      return hostType().createQualifiedAccess().qualifiesAccess(
        new BoundMethodAccess(name(), args, this)
      );
    }
    return new BoundMethodAccess(name(), args, this);
  }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:173
   */
  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:177
   */
  public boolean isSingleton() { return true; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:178
   */
  public boolean isSingleton(Object o) { return contains(o); }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:185
   */
  
  private MethodDecl iterElem;
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:186
   */
  public Iterator iterator() { iterElem = this; return this; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:187
   */
  public boolean hasNext() { return iterElem != null; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:188
   */
  public Object next() { Object o = iterElem; iterElem = null; return o; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:189
   */
  public void remove() { throw new UnsupportedOperationException(); }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:82
   */
  public static Block getBlockForBaseMethod(MethodDecl baseMethodDecl)
  {
      Block block = baseMethodDecl.getBlock().fullCopy();
      List args =baseMethodDecl.getParameterList();
      block.getStmtList().insertChild(new ExprStmt(new ProceedExpr(args)), 0);
      return block;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:89
   */
    
  private static int num[]=new int[3];
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:90
   */
  
  public static LinkedList methodName=new LinkedList();
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:91
   */
  public void methodNames()
  {
	  LinkedList list=AuxiliaryMethodList;
	  for(Iterator iter=list.iterator();iter.hasNext();)
	  {
		  AuxiliaryMethodDecl auximethod=(AuxiliaryMethodDecl)iter.next();
		  methodName.add(auximethod.name());
	  }
	  methodName.add(num[0]+num[1],getID());
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:101
   */
  public void AuxiliaryMethodsNum()
  {
	  LinkedList list=AuxiliaryMethodList;
	  int b=0,ar=0,af=0;
	  for(Iterator iter=list.iterator();iter.hasNext();)
	  {
	    AuxiliaryMethodDecl auximethod=(AuxiliaryMethodDecl)iter.next();
	    if(auximethod.getAuxiliarySpec().kind().equals("before"))
	    b++;
	    if(auximethod.getAuxiliarySpec().kind().equals("around"))
	    ar++;
	    if(auximethod.getAuxiliarySpec().kind().equals("after"))
		af++;
	  }
	  num[0]=b;
	  num[1]=ar;
	  num[2]=af;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:119
   */
  public boolean hasBeforeOrAroundMethods()
  {
	  if(num[0]>0||num[1]>0)
	  return true;
	  return false;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:125
   */
  public boolean hasAfterMethods()
  {
	  if(num[2]>0)
	  return true;
	  return false;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:131
   */
  public boolean hasPrecursor(AuxiliaryMethodDecl amd)
  {
	  LinkedList list=AuxiliaryMethodList;
	  if(list.indexOf(amd)==0)
	  return false;
	  return true;	  
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:138
   */
  public boolean hasSuccessor(AuxiliaryMethodDecl amd)
  {
	  LinkedList list=AuxiliaryMethodList;
	  if(list.indexOf(amd)==list.size()-1)
	  return false;
	  return true;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:145
   */
  public void addProceedExpr()
  {
	   List args=getParameterList();
	   if(hasBeforeOrAroundMethods())
		   getBlock().getStmtList().insertChild(new ExprStmt(new ProceedExpr(args)),0);
	   if(hasAfterMethods())
		   getBlock().addStmt(new ExprStmt(new ProceedExpr(args)));	 
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:200
   */
  
   public static LinkedList AuxiliaryMethodList=new LinkedList();
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:201
   */
  public static LinkedList lookupAuxiliaryMethods(MethodDecl md)
    {
    	LinkedList list=new LinkedList();
    	if(md.hostType().isClassDecl()){
    	ClassDecl host=(ClassDecl)md.hostType();
    	String primaryMethodSignature=md.getID();
    	 for(int i = 0; i <host.getNumBodyDecl(); i++) {
    		if (host.getBodyDecl(i) instanceof AuxiliaryMethodDecl) {    			
    			String signature = ((AuxiliaryMethodDecl)host.getBodyDecl(i)).getID();
    			if(signature.equals(primaryMethodSignature)){
    			   AuxiliaryMethodDecl auximethod=(AuxiliaryMethodDecl)host.getBodyDecl(i);
    			  if(!list.contains(auximethod))
    			  list.add(auximethod);
    		}
    	  }
        }
     }
    	return list;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:220
   */
  public void SortAuxiliaryMethods(LinkedList list)
    {
    	for(Iterator iter=list.iterator();iter.hasNext();)
    	{
    		AuxiliaryMethodDecl partialmethod=(AuxiliaryMethodDecl)iter.next();
    		String order=partialmethod.getAuxiliarySpec().kind();
    		if(order.equals("before"))
    		AuxiliaryMethodList.addFirst(partialmethod);
    		else if(order.equals("after"))
    		AuxiliaryMethodList.addLast(partialmethod);
    		else if(order.equals("around")){
    		 if(AuxiliaryMethodList.size()!=0&&AuxiliaryMethodList.getFirst()!=null){
    			AuxiliaryMethodDecl firstmethod=(AuxiliaryMethodDecl)(AuxiliaryMethodList.getFirst());
    			if(firstmethod.getAuxiliarySpec().kind().equals("before"))
    				AuxiliaryMethodList.add(1,partialmethod);
    		 }
    		 else 
    			 AuxiliaryMethodList.addFirst(partialmethod);
    		}
    	}
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:242
   */
  
    
    private boolean hasBeforeProceedExpr=false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:243
   */
  
    private boolean hasAfterProceedExpr=false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:244
   */
  public void setBeforeProceedExpr(boolean value){
		hasBeforeProceedExpr=value;		
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:247
   */
  public boolean hasBeforeProceedExpr(){
		return hasBeforeProceedExpr;
     }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:250
   */
  public void setAfterProceedExpr(boolean value)
    {
    	hasAfterProceedExpr=value;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:254
   */
  public boolean hasAfterProceedExpr(){
    	return hasAfterProceedExpr;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:872
   */
  public List transformParamsToArgs(List params)
  {
      List args = new List();	  
      for(int y = 0; y < params.getNumChild(); y++)
          args.add(new VarAccess(((ParameterDeclaration)params.getChild(y)).getID()));			  
      return args;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:964
   */
    
	  
	 private boolean m_done = false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:965
   */
  private boolean m_done(){
       if(m_done) return true;	    
	     m_done = true;
	    return false; 
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:1032
   */
  
	
	public static char separator='_';
  /**
   * @ast method 
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:128
   */
  public void checkModifiers() {
    super.checkModifiers();
    if(hostType().isClassDecl()) {
      // 8.4.3.1
      if(isAbstract() && !hostType().isAbstract())
        error("class must be abstract to include abstract methods");
      // 8.4.3.1
      if(isAbstract() && isPrivate())
        error("method may not be abstract and private");
      // 8.4.3.1
      // 8.4.3.2
      if(isAbstract() && isStatic())
        error("method may not be abstract and static");
      if(isAbstract() && isSynchronized())
        error("method may not be abstract and synchronized");
      // 8.4.3.4
      if(isAbstract() && isNative())
        error("method may not be abstract and native");
      if(isAbstract() && isStrictfp())
        error("method may not be abstract and strictfp");
      if(isNative() && isStrictfp())
        error("method may not be native and strictfp");
      if(isBase())
        error("method may not be base");
    }
    if(hostType().isShadowClassDecl()){
    	if(isAbstract())
        error("method may not be abstract");
    	if(isNative()&&isStrictfp())
    	error("method may not be native and strictfp");
    }
    if(hostType().isInterfaceDecl()) {
      // 9.4
      if(isStatic())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be static");
      if(isStrictfp())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be strictfp");
      if(isNative())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be native");
      if(isSynchronized())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be synchronized");
      if(isProtected())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be protected");
      if(isPrivate())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be private");
      else if(isFinal())
        error("interface method " + signature() + " in " +
            hostType().typeName() +  " may not be final");
      else if(isBase())
      error("interface method "+signature() +" in "+ hostType().typeName() + "may not be base");
    }
  }
  /**
   * @ast method 
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:96
   */
  public void nameCheck() {
    // 8.4
    // 8.4.2
    if(!hostType().methodsSignature(signature()).contains(this))
      error("method with signature " + signature() + " is multiply declared in type " + hostType().typeName());
    // 8.4.3.4
    if(isNative() && hasBlock())
      error("native methods must have an empty semicolon body");
    // 8.4.5
    if(isAbstract() && hasBlock())
      error("abstract methods must have an empty semicolon body");
    // 8.4.5
    if(!hasBlock() && !(isNative() || isAbstract()))
      error("only abstract and native methods may have an empty semicolon body");
  }
  /**
   * @ast method 
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:3
   */
  public Collection overriders() {
		Program root = getProgram();
		if (!root.overridesCrossRefsFilledIn) {
			root.overridesCrossRefs();
			root.overridesCrossRefsFilledIn = true;
		}
		return privateOverriders;
	}
  /**
   * @ast method 
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:12
   */
   

	private LinkedList privateOverriders = new LinkedList();
  /**
   * @ast method 
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:21
   */
  void overridesCrossRefs() {
		for (Iterator iter = overrides().iterator(); iter.hasNext();) {
			MethodDecl m = (MethodDecl) iter.next();
			m.privateOverriders.add(this);
		}
		super.overridesCrossRefs();		
	}
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:231
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name() + "(");
    if(getNumParameter() > 0) {
      getParameter(0).toString(s);
      for(int i = 1; i < getNumParameter(); i++) {
        s.append(", ");
        getParameter(i).toString(s);
      }
    }
    s.append(")");
    if(getNumException() > 0) {
      s.append(" throws ");
      getException(0).toString(s);
      for(int i = 1; i < getNumException(); i++) {
        s.append(", ");
        getException(i).toString(s);
      }
    }
    if(hasBlock()) {
      s.append(" ");
      getBlock().toString(s);
    }
    else {
      s.append(";");
    }
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:961
   */
  public void emitInvokeMethod(CodeGeneration gen, TypeDecl hostType) {
    if(hostType.isInterfaceDecl()) {
      int size = type().variableSize() - 1;
      for(int i = 0; i < getNumParameter(); i++)
        size -= getParameter(i).type().variableSize();
      String classname = hostType.constantPoolName();
      String      desc = descName();
      String      name = name();
      int index = gen.constantPool().addInterfaceMethodref(classname, name, desc);
      int numArg = 1; // instance
      for(int i = 0; i < getNumParameter(); i++)
        numArg += getParameter(i).type().variableSize();
      gen.emit(Bytecode.INVOKEINTERFACE, size).add2(index).add(numArg).add(0);
    }
    else {  	
      String classname = hostType.constantPoolName();        
      String      desc = descName();
      String      name = name();
      int index = gen.constantPool().addMethodref(classname, name, desc);
      if(isStatic()) {
        int size = type().variableSize();
        for(int i = 0; i < getNumParameter(); i++)
          size -= getParameter(i).type().variableSize();
        gen.emit(Bytecode.INVOKESTATIC, size).add2(index);
      }
      else {
        int size = type().variableSize() - 1;
        for(int i = 0; i < getNumParameter(); i++)
          size -= getParameter(i).type().variableSize();
        gen.emit(Bytecode.INVOKEVIRTUAL, size).add2(index);
      }
    }
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:995
   */
  public void emitInvokeSpecialMethod(CodeGeneration gen, TypeDecl hostType, Boolean previous) {
    //String classname=null;
    /*if(previous)
    classname ="previous%"+hostType.constantPoolName();
    else 
    classname="seed%"+hostType.constantPoolName();*/
    String      classname=hostType.constantPoolName();
    String      desc = descName();
    String      name=name();

    int index = gen.constantPool().addMethodref(classname, name, desc);
    int size = type().variableSize() - 1;
    for(int i = 0; i < getNumParameter(); i++)
      size -= getParameter(i).type().variableSize();
    gen.emit(Bytecode.INVOKESPECIAL, size).add2(index);
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1013
   */
  public void emitInvokeSpecialMethod(CodeGeneration gen, TypeDecl hostType) { 
    String classname = hostType.constantPoolName();
    String      desc = descName();
    String      name = name();
    int index = gen.constantPool().addMethodref(classname, name, desc);
    int size = type().variableSize() - 1;
    for(int i = 0; i < getNumParameter(); i++)
      size -= getParameter(i).type().variableSize();
    gen.emit(Bytecode.INVOKESPECIAL, size).add2(index);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:245
   */
  public LinkedList sortingAuxiliaryMethod()
		 { 
			 LinkedList list=new LinkedList();
			 if(hasAuxiliaryMethodDecl()){
				 LinkedList auxiliarymethod=new LinkedList(auxiliarymethodes());
				 String beforename="before"+"$"+name();
				 String aroundname="around"+"$"+name();
				 String aftername="after"+"$"+name();
				 boolean aroundexist=false;
				 for(Iterator iter=auxiliarymethod.iterator();iter.hasNext();)
				 {
					 AuxiliaryMethodDecl auxiliary=(AuxiliaryMethodDecl)iter.next();					
					 if(auxiliary.name().equals(beforename))
					 list.addFirst(auxiliary);
					 else if(auxiliary.name().equals(aroundname)){				    
				     if(list!=null){
				    	 AuxiliaryMethodDecl afterauxiliary=(AuxiliaryMethodDecl)list.getLast();
				    	 if(afterauxiliary.name().equals(aftername)){
				         int i=list.indexOf(afterauxiliary);
				    	 list.add(i,auxiliary);
				    	 }
				    	 else 
				         list.add(auxiliary);
				        }
					else {
					 list.add(auxiliary);
					 }	
				     aroundexist=true;	
				 }
				 else
				 {
				   list.add(auxiliary);
				 }
				 }
				AuxiliaryMethodDecl aftermethod=(AuxiliaryMethodDecl)list.getLast();
				if(!aroundexist){
			    if(aftermethod.name().equals(aftername))
			    {
			      int j=list.indexOf(aftermethod);
			      list.add(j,this);
			    }
			   else
				   list.add(this);
			    }
				return list;
			 }
			 return list;
		 }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:308
   */
  private void generateBytecodes(CodeGeneration gen) {
    int label = gen.variableScopeLabel();
    if(!isStatic())
      gen.addLocalVariableEntryAtCurrentPC("this", hostType().typeDescriptor(), 0, label);
    for(int i = 0; i < getNumParameter(); i++) {
      ParameterDeclaration p = (ParameterDeclaration)getParameter(i);
      gen.addLocalVariableEntryAtCurrentPC(
        p.name(), p.type().typeDescriptor(), p.localNum(), label
      );
    }
    createBCode(gen);
    if(type() instanceof VoidType) // TODO: canCompleteNormally check as well
      gen.emitReturn();
    gen.addVariableScopeLabel(label);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:360
   */
  public void createBCode(CodeGeneration gen) {
	    try { 
	    if(hasBlock()){
	      gen.maxLocals = Math.max(gen.maxLocals, getBlock().localNum());
	      getBlock().createBCode(gen);
	    }
	    } catch (Error e) {
	      System.err.println(hostType().typeName() + ": " + this);
	      throw e;
	    }
	  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:300
   */
  public void generateMethod(DataOutputStream out, ConstantPool cp) throws IOException {
	out.writeChar(flags());
    out.writeChar(cp.addUtf8(name()));
    out.writeChar(cp.addUtf8(descName()));
    out.writeChar(attributes().size());
    for(Iterator itera = attributes().iterator(); itera.hasNext();)
      ((Attribute)itera.next()).emit(out);
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:319
   */
  public void touchMethod(ConstantPool cp) {	
    cp.addUtf8(name());
    cp.addUtf8(descName());
    attributes();
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:453
   */
  public boolean clear() {
    if(hasBlock()) {
      getBlock().clear();
      setBlock(new Block(new List()));
    }
    bytecodes_ConstantPool_values = null;
    return false;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:298
   */
  public MethodDecl createAccessor(TypeDecl methodQualifier) {
    MethodDecl m = (MethodDecl)methodQualifier.getAccessor(this, "method");     
    if(m != null) return m;    
    int accessorIndex = methodQualifier.accessorCounter++;
     
     List parameterList = new List();
    for(int i = 0; i < getNumParameter(); i++)
      parameterList.add(new ParameterDeclaration(getParameter(i).type(), getParameter(i).name()));
    List exceptionList = new List();
    for(int i = 0; i < getNumException(); i++)
      exceptionList.add(getException(i).type().createQualifiedAccess());
    // add synthetic flag to modifiers
    Modifiers modifiers = new Modifiers(new List());
    if(getModifiers().isStatic())
      modifiers.addModifier(new Modifier("static"));
    modifiers.addModifier(new Modifier("synthetic"));
    modifiers.addModifier(new Modifier("public"));
    // build accessor declaration
    m = new MethodDecl(
      modifiers,
      type().createQualifiedAccess(),
      name() + "$access$" + accessorIndex,
      parameterList,
      exceptionList,
      new Opt(
        new Block(
          new List().add(
            createAccessorStmt()
          )
        )
      )
    );
    m = methodQualifier.addMemberMethod(m);
    methodQualifier.addAccessor(this, "method", m);
    return m;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:335
   */
  private Stmt createAccessorStmt() {
    List argumentList = new List();
    for(int i = 0; i < getNumParameter(); i++)
      argumentList.add(new VarAccess(getParameter(i).name()));
    Access access = new BoundMethodAccess(name(), argumentList, this);
    if(!isStatic())
      access = new ThisAccess("this").qualifiesAccess(access);
    return isVoid() ? (Stmt) new ExprStmt(access) : new ReturnStmt(new Opt(access));
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:345
   */
  public MethodDecl createSeedAccessor(TypeDecl methodQualifier) {	
	    MethodDecl m = (MethodDecl)methodQualifier.getAccessor(this, "method_seed");
	    if(m != null) return m;
	    int accessorIndex=methodQualifier.accessorCounter++;
	    List parameters = new List();
	    List args = new List();
	    for(int i = 0; i < getNumParameter(); i++) {
	      parameters.add(getParameter(i).fullCopy());
	      args.add(new VarAccess(getParameter(i).name()));
	    }
	    Stmt stmt;
	    if(type().isVoid())
	      stmt = new ExprStmt(new SeedAccess("seed").qualifiesAccess(new MethodAccess(name(), args)));
	    else 
	      stmt = new ReturnStmt(new Opt(new SeedAccess("seed").qualifiesAccess(new MethodAccess(name(), args))));
	    m = new MethodDecl(
	      new Modifiers(new List().add(new Modifier("synthetic"))),
	      type().createQualifiedAccess(),
	      name() + "$access$" + accessorIndex,
	      parameters,
	      new List(),
	      new Opt(
	        new Block(
	          new List().add(stmt)
	        )
	      )
	    );
	    m = methodQualifier.addMemberMethod(m);
	    methodQualifier.addAccessor(this, "method_seed", m);
	    return m;
	  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:376
   */
  public MethodDecl createPreviousAccessor(TypeDecl methodQualifier) {	
	    MethodDecl m = (MethodDecl)methodQualifier.getAccessor(this, "method_previous");
	    if(m != null) return m;
	    int accessorIndex=methodQualifier.accessorCounter++;
	    List parameters = new List();
	    List args = new List();
	    for(int i = 0; i < getNumParameter(); i++) {
	      parameters.add(getParameter(i).fullCopy());
	      args.add(new VarAccess(getParameter(i).name()));
	    }
	    Stmt stmt;
	    if(type().isVoid())
	      stmt = new ExprStmt(new PreviousAccess("previous").qualifiesAccess(new MethodAccess(name(), args)));
	    else 
	      stmt = new ReturnStmt(new Opt(new PreviousAccess("previous").qualifiesAccess(new MethodAccess(name(), args))));
	    m = new MethodDecl(
	      new Modifiers(new List().add(new Modifier("synthetic"))),
	      type().createQualifiedAccess(),
	      name() + "$access$" + accessorIndex,
	      parameters,
	      new List(),
	      new Opt(
	        new Block(
	          new List().add(stmt)
	        )
	      )
	    );
	    m = methodQualifier.addMemberMethod(m);
	    methodQualifier.addAccessor(this, "method_previous", m);
	    return m;
	  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:408
   */
  public MethodDecl createSuperAccessor(TypeDecl methodQualifier) {	
    MethodDecl m = (MethodDecl)methodQualifier.getAccessor(this, "method_super");
    if(m != null) return m;
    int accessorIndex = methodQualifier.accessorCounter++;
    List parameters = new List();
    List args = new List();
    for(int i = 0; i < getNumParameter(); i++) {
       parameters.add(new ParameterDeclaration(getParameter(i).type(), getParameter(i).name()));
      args.add(new VarAccess(getParameter(i).name()));
    }
    Stmt stmt;
    if(type().isVoid())
      stmt = new ExprStmt(new SuperAccess("super").qualifiesAccess(new MethodAccess(name(), args)));
    else 
      stmt = new ReturnStmt(new Opt(new SuperAccess("super").qualifiesAccess(new MethodAccess(name(), args))));
    m = new MethodDecl(
      new Modifiers(new List().add(new Modifier("synthetic"))),
      type().createQualifiedAccess(),
      name() + "$access$" + accessorIndex,
      parameters,
      new List(),
      new Opt(
        new Block(
          new List().add(stmt)
        )
      )
    );
    m = methodQualifier.addMemberMethod(m);
    methodQualifier.addAccessor(this, "method_super", m);
    return m;
  }
  /**
   * @ast method 
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1005
   */
  public BodyDecl p(Parameterization parTypeDecl) {
    //System.out.println("Begin substituting " + signature() + " in " + hostType().typeName() + " with " + parTypeDecl.typeSignature());
    MethodDecl m = new MethodDeclSubstituted(
      (Modifiers)getModifiers().fullCopy(),
      getTypeAccess().type().substituteReturnType(parTypeDecl),
      getID(),
      getParameterList().substitute(parTypeDecl),
      getExceptionList().substitute(parTypeDecl),
      substituteBody(parTypeDecl),
      this
    );
    //System.out.println("End substituting " + signature());
    return m;
  }
  /**
   * @ast method 
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1020
   */
  public Opt substituteBody(Parameterization parTypeDecl) {
    return new Opt();
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:84
   */
  public void addRuntimeVisibleParameterAnnotationsAttribute(Collection c) {
    boolean foundVisibleAnnotations = false;
    Collection annotations = new ArrayList(getNumParameter());
    for(int i = 0; i < getNumParameter(); i++) {
      Collection a = getParameter(i).getModifiers().runtimeVisibleAnnotations();
      if(!a.isEmpty()) foundVisibleAnnotations = true;
      annotations.add(a);
    }
    if(foundVisibleAnnotations)
      c.add(new ParameterAnnotationsAttribute(hostType().constantPool(), annotations, "RuntimeVisibleParameterAnnotations"));
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:105
   */
  public void addRuntimeInvisibleParameterAnnotationsAttribute(Collection c) {
    boolean foundInvisibleAnnotations = false;
    Collection annotations = new ArrayList(getNumParameter());
    for(int i = 0; i < getNumParameter(); i++) {
      Collection a = getParameter(i).getModifiers().runtimeInvisibleAnnotations();
      if(!a.isEmpty()) foundInvisibleAnnotations = true;
      annotations.add(a);
    }
    if(foundInvisibleAnnotations)
      c.add(new ParameterAnnotationsAttribute(hostType().constantPool(), annotations, "RuntimeInvisibleParameterAnnotations"));
  }
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:277
   */
  public void transformation() {
    super.transformation();
    HashSet processed = new HashSet();
    for(Iterator iter = hostType().bridgeCandidates(signature()).iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(this.overrides(m)) {
        MethodDecl erased = m.erasedMethod();
        if(!erased.signature().equals(signature()) || erased.type().erasure() != type().erasure()) {
          StringBuffer keyBuffer = new StringBuffer();
          for(int i = 0; i < getNumParameter(); i++) {
            keyBuffer.append(erased.getParameter(i).type().erasure().fullName());
          }
          keyBuffer.append(erased.type().erasure().fullName());
          String key = keyBuffer.toString();
          if(!processed.contains(key)) {
            processed.add(key);

            List args = new List();
            List parameters = new List();
            for(int i = 0; i < getNumParameter(); i++) {
              args.add(new CastExpr(getParameter(i).type().erasure().createBoundAccess(), new VarAccess("p" + i)));
              parameters.add(new ParameterDeclaration(erased.getParameter(i).type().erasure(), "p" + i));
            }
            Stmt stmt;
            if(type().isVoid()) {
              stmt = new ExprStmt(
                  createBoundAccess(
                    args
                    )
                  );
            }
            else {
              stmt = new ReturnStmt(
                  createBoundAccess(
                    args
                    )
                  );
            }
            List modifiersList = new List();
            if(isPublic())
              modifiersList.add(new Modifier("public"));
            else if(isProtected())
              modifiersList.add(new Modifier("protected"));
            else if(isPrivate())
              modifiersList.add(new Modifier("private"));
            MethodDecl bridge = new BridgeMethodDecl(
                new Modifiers(modifiersList),
                erased.type().erasure().createBoundAccess(),
                erased.name(),
                parameters,
                (List)getExceptionList().fullCopy(),
                new Opt(
                  new Block(
                    new List().add(stmt)
                    )
                  )
                );
            hostType().addBodyDecl(bridge);
          }
        }
      }
    }
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public MethodDecl() {
    super();

    setChild(new List(), 2);
    setChild(new List(), 3);
    setChild(new Opt(), 4);

  }
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  public MethodDecl(Modifiers p0, Access p1, String p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /**
   * @ast method 
   * @declaredat java.ast:18
   */
  public MethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:29
   */
  protected int numChildren() {
    return 5;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:35
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat java.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_ID;
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  
  public int IDstart;
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  
  public int IDend;
  /**
   * @ast method 
   * @declaredat java.ast:11
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
   * @declaredat java.ast:22
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 2);
  }
  /**
   * @return number of children in ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumParameter() {
    return getParameterList().getNumChild();
  }
  /**
   * Getter for child in list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ParameterDeclaration getParameter(int i) {
    return (ParameterDeclaration)getParameterList().getChild(i);
  }
  /**
   * Add element to list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list = (parent == null || state == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addParameterNoTransform(ParameterDeclaration node) {
    List<ParameterDeclaration> list = getParameterListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setParameter(ParameterDeclaration node, int i) {
    List<ParameterDeclaration> list = getParameterList();
    list.setChild(node, i);
  }
  /**
   * Getter for Parameter list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<ParameterDeclaration> getParameters() {
    return getParameterList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<ParameterDeclaration> getParametersNoTransform() {
    return getParameterListNoTransform();
  }
  /**
   * Getter for list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ParameterDeclaration> getParameterList() {
    List<ParameterDeclaration> list = (List<ParameterDeclaration>)getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>)getChildNoTransform(2);
  }
  /**
   * Setter for ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setExceptionList(List<Access> list) {
    setChild(list, 3);
  }
  /**
   * @return number of children in ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumException() {
    return getExceptionList().getNumChild();
  }
  /**
   * Getter for child in list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getException(int i) {
    return (Access)getExceptionList().getChild(i);
  }
  /**
   * Add element to list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addException(Access node) {
    List<Access> list = (parent == null || state == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addExceptionNoTransform(Access node) {
    List<Access> list = getExceptionListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setException(Access node, int i) {
    List<Access> list = getExceptionList();
    list.setChild(node, i);
  }
  /**
   * Getter for Exception list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<Access> getExceptions() {
    return getExceptionList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<Access> getExceptionsNoTransform() {
    return getExceptionListNoTransform();
  }
  /**
   * Getter for list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getExceptionList() {
    List<Access> list = (List<Access>)getChild(3);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>)getChildNoTransform(3);
  }
  /**
   * Setter for BlockOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBlockOpt(Opt<Block> opt) {
    setChild(opt, 4);
  }
  /**
   * Does this node have a Block child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasBlock() {
    return getBlockOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Block getBlock() {
    return (Block)getBlockOpt().getChild(0);
  }
  /**
   * Setter for optional child Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setBlock(Block node) {
    getBlockOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getBlockOpt() {
    return (Opt<Block>)getChild(4);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getBlockOptNoTransform() {
    return (Opt<Block>)getChildNoTransform(4);
  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1159
   */
    public void typeCheck() {
    // Thrown vs super class method see MethodDecl.nameCheck
    // 8.4.4
    TypeDecl exceptionType = typeThrowable();
    for(int i = 0; i < getNumException(); i++) {
      TypeDecl typeDecl = getException(i).type();
      if(!typeDecl.instanceOf(exceptionType))
        error(signature() + " throws non throwable type " + typeDecl.fullName());
    }

    // check returns
    if(!hostType().isGranuleDecl()&& !isVoid() && hasBlock() && getBlock().canCompleteNormally())
     error("the body of a non void method may not complete normally");
    
    //if(hostType().isGranuleDecl() && getBlock().getNumStmt()==0)
      //error("the body fitness method in granule may not be empty");
    
    //if(hostType().isGranuleDecl() && !isVoid() && hasBlock() && getBlock().canCompleteNormally())
    //error("the body fitness method in granule must return boolean");
  }
  /**
   * @ast method 
   * @aspect MethodDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:241
   */
  private boolean refined_MethodDecl_MethodDecl_moreSpecificThan_MethodDecl(MethodDecl m)
{
    if(getNumParameter() == 0)
      return false;
    for(int i = 0; i < getNumParameter(); i++) {
    	if(!getParameter(i).type().instanceOf(m.getParameter(i).type()))
    	{
    	  //if(!getParameter(i).type().methodInvocationConversionTo(m.getParameter(i).type()))
           return false;
    	}
    }
    return true;
  }
  /**
   * @ast method 
   * @aspect Attributes
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Attributes.jrag:247
   */
  private Collection refined_Attributes_MethodDecl_attributes()
{
    ArrayList l = new ArrayList();
    l.add(new ExceptionsAttribute(bytecodes(hostType().constantPool()), this));
    if(isAbstract() || isNative()) return l;
    l.add(new CodeAttribute(bytecodes(hostType().constantPool()), this));
    if(getModifiers().isSynthetic())
      l.add(new SyntheticAttribute(hostType().constantPool()));
    return l;
  }
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:45
   */
  private int refined_Flags_MethodDecl_flags()
{
    int res = 0;
    if(isPublic()) res |= Modifiers.ACC_PUBLIC;
    if(isPrivate()) res |= Modifiers.ACC_PRIVATE;
    if(isProtected()) res |= Modifiers.ACC_PROTECTED;
    if(isStatic()) res |= Modifiers.ACC_STATIC;
    if(isFinal()) res |= Modifiers.ACC_FINAL;
    if(isSynchronized()) res |= Modifiers.ACC_SYNCHRONIZED;
    if(isNative()) res |= Modifiers.ACC_NATIVE;
    if(isAbstract()) res |= Modifiers.ACC_ABSTRACT;
    if(isStrictfp() || (hostType().isStrictfp() && !hostType().isInterfaceDecl())) res |= Modifiers.ACC_STRICT;
//==============GOP========================================
    if(isBase()) res|=Modifiers.ACC_BASE;
//==============END========================================
    return res;
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:23
   */
  private Collection refined_AnnotationsCodegen_MethodDecl_attributes()
{
    Collection c = refined_Attributes_MethodDecl_attributes();
    getModifiers().addRuntimeVisibleAnnotationsAttribute(c);
    getModifiers().addRuntimeInvisibleAnnotationsAttribute(c);
    addRuntimeVisibleParameterAnnotationsAttribute(c);
    addRuntimeInvisibleParameterAnnotationsAttribute(c);
    return c;
  }
  protected java.util.Map accessibleFrom_TypeDecl_values;
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AccessControl.jrag:77
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean accessibleFrom(TypeDecl type) {
    Object _parameters = type;
    if(accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
    if(accessibleFrom_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)accessibleFrom_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
if(isFinal && num == state().boundariesCrossed) accessibleFrom_TypeDecl_values.put(_parameters, Boolean.valueOf(accessibleFrom_TypeDecl_value));
    return accessibleFrom_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean accessibleFrom_compute(TypeDecl type) {
    if(isPublic()) {
      return true;
    }
    else if(isProtected()) {
      if(hostPackage().equals(type.hostPackage()))
        return true;
      if(type.withinBodyThatSubclasses(hostType()) != null)
        return true;
      return false;
    }
    else if(isPrivate())
      return hostType().topLevelType() == type.topLevelType();
    else
      return hostPackage().equals(type.hostPackage());
  }
  /**
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:171
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:172
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
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:176
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
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:31
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int lineNumber() {
      ASTNode$State state = state();
    int lineNumber_value = lineNumber_compute();
    return lineNumber_value;
  }
  /**
   * @apilvl internal
   */
  private int lineNumber_compute() {  return getLine(IDstart);  }
  protected java.util.Map throwsException_TypeDecl_values;
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:131
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean throwsException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if(throwsException_TypeDecl_values == null) throwsException_TypeDecl_values = new java.util.HashMap(4);
    if(throwsException_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)throwsException_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean throwsException_TypeDecl_value = throwsException_compute(exceptionType);
if(isFinal && num == state().boundariesCrossed) throwsException_TypeDecl_values.put(_parameters, Boolean.valueOf(throwsException_TypeDecl_value));
    return throwsException_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean throwsException_compute(TypeDecl exceptionType) {
    for(int i = 0; i < getNumException(); i++)
      if(exceptionType.instanceOf(getException(i).type()))
        return true;
    return false;
  }
  /**
   * @apilvl internal
   */
  protected boolean isFixedExpr_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isFixedExpr_value;
  /**
   * @attribute syn
   * @aspect ExpressionAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExpressionAnalysis.jrag:6
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFixedExpr() {
    if(isFixedExpr_computed) {
      return isFixedExpr_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isFixedExpr_value = isFixedExpr_compute();
if(isFinal && num == state().boundariesCrossed) isFixedExpr_computed = true;
    return isFixedExpr_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFixedExpr_compute() {
	  if(!hostType().isGranuleDecl())
	   return false;
	  if(hasBlock()){
		 for(int i=0;i<getBlock().getNumStmt();i++)
		 {
			 Stmt st=getBlock().getStmt(i);
			 if(st instanceof ExprStmt){
				ExprStmt exprst=(ExprStmt)st;
			 }
		 }
	  }
     return true;
  }
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:224
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
  protected boolean signature_computed = false;
  /**
   * @apilvl internal
   */
  protected String signature_value;
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:347
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String signature() {
    if(signature_computed) {
      return signature_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    signature_value = signature_compute();
if(isFinal && num == state().boundariesCrossed) signature_computed = true;
    return signature_value;
  }
  /**
   * @apilvl internal
   */
  private String signature_compute() {
    StringBuffer s = new StringBuffer();
    s.append(name() + "(");
    for(int i = 0; i < getNumParameter(); i++) {
      if(i != 0) s.append(", ");
      s.append(getParameter(i).type().erasure().typeName());
    }
    s.append(")");
    return s.toString();

  }
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:239
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean sameSignature(MethodDecl m) {
      ASTNode$State state = state();
    boolean sameSignature_MethodDecl_value = sameSignature_compute(m);
    return sameSignature_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean sameSignature_compute(MethodDecl m) {  return signature().equals(m.signature());  }
  protected java.util.Map moreSpecificThan_MethodDecl_values;
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:155
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean moreSpecificThan(MethodDecl m) {
    Object _parameters = m;
    if(moreSpecificThan_MethodDecl_values == null) moreSpecificThan_MethodDecl_values = new java.util.HashMap(4);
    if(moreSpecificThan_MethodDecl_values.containsKey(_parameters)) {
      return ((Boolean)moreSpecificThan_MethodDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean moreSpecificThan_MethodDecl_value = moreSpecificThan_compute(m);
if(isFinal && num == state().boundariesCrossed) moreSpecificThan_MethodDecl_values.put(_parameters, Boolean.valueOf(moreSpecificThan_MethodDecl_value));
    return moreSpecificThan_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean moreSpecificThan_compute(MethodDecl m) {
    if(!isVariableArity() && !m.isVariableArity())
      return refined_MethodDecl_MethodDecl_moreSpecificThan_MethodDecl(m);
    int num = Math.max(getNumParameter(), m.getNumParameter());
    for(int i = 0; i < num; i++) {
      TypeDecl t1 = i < getNumParameter() - 1 ? getParameter(i).type() : getParameter(getNumParameter()-1).type().componentType();
      TypeDecl t2 = i < m.getNumParameter() - 1 ? m.getParameter(i).type() : m.getParameter(m.getNumParameter()-1).type().componentType();
      if(!t1.instanceOf(t2))
        return false;
    }
    return true;
  }
  protected java.util.Map overrides_MethodDecl_values;
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:286
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean overrides(MethodDecl m) {
    Object _parameters = m;
    if(overrides_MethodDecl_values == null) overrides_MethodDecl_values = new java.util.HashMap(4);
    if(overrides_MethodDecl_values.containsKey(_parameters)) {
      return ((Boolean)overrides_MethodDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean overrides_MethodDecl_value = overrides_compute(m);
if(isFinal && num == state().boundariesCrossed) overrides_MethodDecl_values.put(_parameters, Boolean.valueOf(overrides_MethodDecl_value));
    return overrides_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean overrides_compute(MethodDecl m) {  return !isStatic() && !m.isPrivate() && m.accessibleFrom(hostType()) && 
     hostType().instanceOf(m.hostType()) && m.signature().equals(signature());  }
  protected java.util.Map hides_MethodDecl_values;
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:290
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hides(MethodDecl m) {
    Object _parameters = m;
    if(hides_MethodDecl_values == null) hides_MethodDecl_values = new java.util.HashMap(4);
    if(hides_MethodDecl_values.containsKey(_parameters)) {
      return ((Boolean)hides_MethodDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean hides_MethodDecl_value = hides_compute(m);
if(isFinal && num == state().boundariesCrossed) hides_MethodDecl_values.put(_parameters, Boolean.valueOf(hides_MethodDecl_value));
    return hides_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hides_compute(MethodDecl m) {  return isStatic() && !m.isPrivate() && m.accessibleFrom(hostType()) && 
     hostType().instanceOf(m.hostType()) && m.signature().equals(signature());  }
  protected java.util.Map parameterDeclaration_String_values;
  /**
   * @attribute syn
   * @aspect VariableScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:154
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet parameterDeclaration(String name) {
    Object _parameters = name;
    if(parameterDeclaration_String_values == null) parameterDeclaration_String_values = new java.util.HashMap(4);
    if(parameterDeclaration_String_values.containsKey(_parameters)) {
      return (SimpleSet)parameterDeclaration_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet parameterDeclaration_String_value = parameterDeclaration_compute(name);
if(isFinal && num == state().boundariesCrossed) parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
    return parameterDeclaration_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet parameterDeclaration_compute(String name) {
    for(int i = 0; i < getNumParameter(); i++)
      if(getParameter(i).name().equals(name))
        return (ParameterDeclaration)getParameter(i);
    return SimpleSet.emptySet;
  }
  protected java.util.Map primaryMethod_String_values;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:182
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl primaryMethod(String signature) {
    Object _parameters = signature;
    if(primaryMethod_String_values == null) primaryMethod_String_values = new java.util.HashMap(4);
    if(primaryMethod_String_values.containsKey(_parameters)) {
      return (MethodDecl)primaryMethod_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    MethodDecl primaryMethod_String_value = primaryMethod_compute(signature);
if(isFinal && num == state().boundariesCrossed) primaryMethod_String_values.put(_parameters, primaryMethod_String_value);
    return primaryMethod_String_value;
  }
  /**
   * @apilvl internal
   */
  private MethodDecl primaryMethod_compute(String signature) {    	  
		SimpleSet set=(SimpleSet)(hostType().methodsSignature(signature));	
		if(set.size() == 1){
		MethodDecl n=(MethodDecl)set.iterator().next();    
        return n;
		}
		return null;
	  }
  /**
   * @apilvl internal
   */
  protected boolean isOverridingMethod_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isOverridingMethod_value;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:971
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isOverridingMethod() {
    if(isOverridingMethod_computed) {
      return isOverridingMethod_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isOverridingMethod_value = isOverridingMethod_compute();
if(isFinal && num == state().boundariesCrossed) isOverridingMethod_computed = true;
    return isOverridingMethod_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isOverridingMethod_compute() {
	     if(hostType().isShadowClassDecl())
		 return isOverriding();
		 /*else if(hostType().isClassDecl()&& isFromSourceFilesTo()){
		     Collection col=((ClassDecl)hostType()).getShadowSet();
              for(Iterator iter=col.iterator();iter.hasNext();){
			   ShadowClassDecl scd=(ShadowClassDecl)iter.next();
			   for (BodyDecl sBD : scd.getBodyDeclListNoTransform()){
			    if(sBD instanceof MethodDecl){
				   if(((MethodDecl)sBD).overrides(this)) 
				   return true;
				  }
			    }
		   }
	    }*/
	  return false;
	}
  /**
   * @apilvl internal
   */
  protected boolean isOverriding_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isOverriding_value;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:989
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isOverriding() {
    if(isOverriding_computed) {
      return isOverriding_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isOverriding_value = isOverriding_compute();
if(isFinal && num == state().boundariesCrossed) isOverriding_computed = true;
    return isOverriding_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isOverriding_compute() {			  
	      for(Iterator iter=overrides().iterator();iter.hasNext();){
		     MethodDecl decl=(MethodDecl)iter.next();
		     if(decl.hostType().isClassDecl()&& hostType().instanceOf(decl.hostType())){
			 //gname=getGranuleName();
		     return true;
			 }
		   } 
	  return false;
	}
  /**
   * @apilvl internal
   */
  protected boolean getGranuleName_computed = false;
  /**
   * @apilvl internal
   */
  protected String getGranuleName_value;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:1033
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String getGranuleName() {
    if(getGranuleName_computed) {
      return getGranuleName_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    getGranuleName_value = getGranuleName_compute();
if(isFinal && num == state().boundariesCrossed) getGranuleName_computed = true;
    return getGranuleName_value;
  }
  /**
   * @apilvl internal
   */
  private String getGranuleName_compute() {
	   String gname="";
	   if(hostType()!=null&&!hostType().isClassDecl()){
	     gname=hostType().typeName();
		 int index=gname.indexOf(separator);
		 if(index>0)
         gname=gname.substring(0,index);
	    }
	  return gname;
	}
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:224
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
  private boolean isSynthetic_compute() {  return getModifiers().isSynthetic();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:240
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
  private boolean isPublic_compute() {  return getModifiers().isPublic() || hostType().isInterfaceDecl();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:241
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:242
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:243
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isAbstract() {
      ASTNode$State state = state();
    boolean isAbstract_value = isAbstract_compute();
    return isAbstract_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isAbstract_compute() {  return getModifiers().isAbstract() || hostType().isInterfaceDecl();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:244
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
  private boolean isStatic_compute() {  return getModifiers().isStatic();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:246
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
  private boolean isFinal_compute() {  return getModifiers().isFinal() || hostType().isFinal() || isPrivate();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:247
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSynchronized() {
      ASTNode$State state = state();
    boolean isSynchronized_value = isSynchronized_compute();
    return isSynchronized_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSynchronized_compute() {  return getModifiers().isSynchronized();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:248
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isNative() {
      ASTNode$State state = state();
    boolean isNative_value = isNative_compute();
    return isNative_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isNative_compute() {  return getModifiers().isNative();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:249
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isStrictfp() {
      ASTNode$State state = state();
    boolean isStrictfp_value = isStrictfp_compute();
    return isStrictfp_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isStrictfp_compute() {  return getModifiers().isStrictfp();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:250
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isBase() {
      ASTNode$State state = state();
    boolean isBase_value = isBase_compute();
    return isBase_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isBase_compute() {  return getModifiers().isBase();  }
  /**
   * @apilvl internal
   */
  protected boolean overrides_computed = false;
  /**
   * @apilvl internal
   */
  protected HashSet overrides_value;
  /**
   * @attribute syn
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:29
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashSet overrides() {
    if(overrides_computed) {
      return overrides_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    overrides_value = overrides_compute();
if(isFinal && num == state().boundariesCrossed) overrides_computed = true;
    return overrides_value;
  }
  /**
   * @apilvl internal
   */
  private HashSet overrides_compute() {
    HashSet set = new HashSet();
    for(Iterator iter = hostType().ancestorMethods(signature()).iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(overrides(m)) {
        set.add(m);
        set.addAll(m.overrides());
      }
    }
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean isFromSourceFilesTo_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isFromSourceFilesTo_value;
  /**
   * @attribute syn
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:50
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFromSourceFilesTo() {
    if(isFromSourceFilesTo_computed) {
      return isFromSourceFilesTo_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isFromSourceFilesTo_value = isFromSourceFilesTo_compute();
if(isFinal && num == state().boundariesCrossed) isFromSourceFilesTo_computed = true;
    return isFromSourceFilesTo_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFromSourceFilesTo_compute() {
	  CompilationUnit cu=hostType().compilationUnit();
	  if(cu.fromSource()) return true;
	  else return false;
	}
  /**
   * @apilvl internal
   */
  protected boolean isOverridden_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isOverridden_value;
  /*coll HashSet MethodDecl.overriders() [new HashSet()] with add root CompilationUnit;
	MethodDecl contributes this when isSourceFile() to MethodDecl.overriders() for each overrides();

    inh lazy CompilationUnit MethodDecl.enclosingCompilationUnit();
	eq CompilationUnit.getChild(int i).enclosingCompilationUnit() = this;
	eq Program.getCompilationUnit().enclosingCompilationUnit()=null;
	syn lazy boolean MethodDecl.isSourceFile()=(enclosingCompilationUnit()!=null && enclosingCompilationUnit().fromSource());
	
	syn lazy SimpleSet MethodDecl.overrides() {
		SimpleSet anc = SimpleSet.emptySet;
		for(Iterator i=hostType().ancestorMethods(signature()).iterator();i.hasNext();) {
			MethodDecl md = (MethodDecl)i.next();
			if(overrides(md))
				anc = anc.add(md);
		}
		return anc;
	}* @attribute syn
   * @aspect OverridesCrossRefs
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\OverridesCrossRefs.jrag:74
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isOverridden() {
    if(isOverridden_computed) {
      return isOverridden_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isOverridden_value = isOverridden_compute();
if(isFinal && num == state().boundariesCrossed) isOverridden_computed = true;
    return isOverridden_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isOverridden_compute() { 
      if(hostType().isUnknown())
	  return false;
	
	  if(hostType().isShadowClassDecl()){			  
	      for(Iterator iter=overrides().iterator();iter.hasNext();){
		     MethodDecl decl=(MethodDecl)iter.next();
		     if(decl.hostType().isClassDecl()&& hostType().instanceOf(decl.hostType()))
		     return true;
		   } 
	  } 
      else if(hostType().isClassDecl()){
	  for(Iterator iter=overriders().iterator();iter.hasNext();){
			 MethodDecl decl=(MethodDecl)iter.next();			 
			 if(decl.hostType().isShadowClassDecl() && decl.hostType().instanceOf(hostType())){
			 return true;
			}
		 }  
	   } 
	 return false;
    }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:944
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
  private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }
  /**
   * @apilvl internal
   */
  protected boolean type_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl type_value;
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:271
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
  private TypeDecl type_compute() {  return getTypeAccess().type();  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:274
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVoid() {
      ASTNode$State state = state();
    boolean isVoid_value = isVoid_compute();
    return isVoid_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVoid_compute() {  return type().isVoid();  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericMethods.jrag:90
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayOverrideReturn(MethodDecl m) {
      ASTNode$State state = state();
    boolean mayOverrideReturn_MethodDecl_value = mayOverrideReturn_compute(m);
    return mayOverrideReturn_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean mayOverrideReturn_compute(MethodDecl m) {
    return type().instanceOf(m.type());
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:358
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
    Collection c = refined_AnnotationsCodegen_MethodDecl_attributes();
    if(needsSignatureAttribute())
      c.add(new SignatureAttribute(hostType().constantPool(), methodTypeSignature()));
    return c;
  }
  /**
   * @apilvl internal
   */
  protected boolean descName_computed = false;
  /**
   * @apilvl internal
   */
  protected String descName_value;
  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:34
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String descName() {
    if(descName_computed) {
      return descName_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    descName_value = descName_compute();
if(isFinal && num == state().boundariesCrossed) descName_computed = true;
    return descName_value;
  }
  /**
   * @apilvl internal
   */
  private String descName_compute() {
    StringBuffer b = new StringBuffer();
    b.append("(");
    for (int i=0; i<getNumParameter(); i++)
      b.append(getParameter(i).type().typeDescriptor());
    b.append(")");
    if(type().elementType().isUnknown()) {
      System.out.println(getTypeAccess().dumpTree());
      throw new Error("Error generating descName for " + signature() + ", did not expect unknown return type");
    }
    b.append(type().typeDescriptor());
    return b.toString();
  }
  protected java.util.Map bytecodes_ConstantPool_values;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:295
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CodeGeneration bytecodes(ConstantPool constantPool) {
    Object _parameters = constantPool;
    if(bytecodes_ConstantPool_values == null) bytecodes_ConstantPool_values = new java.util.HashMap(4);
    if(bytecodes_ConstantPool_values.containsKey(_parameters)) {
      return (CodeGeneration)bytecodes_ConstantPool_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    CodeGeneration bytecodes_ConstantPool_value = bytecodes_compute(constantPool);
if(isFinal && num == state().boundariesCrossed) bytecodes_ConstantPool_values.put(_parameters, bytecodes_ConstantPool_value);
    return bytecodes_ConstantPool_value;
  }
  /**
   * @apilvl internal
   */
  private CodeGeneration bytecodes_compute(ConstantPool constantPool) {
    //if(options().verbose())
    //  System.out.println("Generating bytecodes for " + signature() + " in " + hostType().fullName());
    CodeGeneration gen = new CodeGeneration(constantPool);
    generateBytecodes(gen);
    if(!gen.numberFormatError())
      return gen;
    gen = new CodeGeneration(constantPool, true);
    generateBytecodes(gen);
    if(!gen.numberFormatError())
      return gen;
    throw new Error("Could not generate code for " + signature() + " in " + hostType().typeName());
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
   * @aspect VariableArityParametersCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\VariableArityParametersCodegen.jrag:81
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
    int res = refined_Flags_MethodDecl_flags();
    if(isVariableArity())
      res |= Modifiers.ACC_VARARGS;
    return res;
  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:381
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isBytecodeMethod() {
      ASTNode$State state = state();
    boolean isBytecodeMethod_value = isBytecodeMethod_compute();
    return isBytecodeMethod_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isBytecodeMethod_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:421
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean flush() {
      ASTNode$State state = state();
    boolean flush_value = flush_compute();
    return flush_value;
  }
  /**
   * @apilvl internal
   */
  private boolean flush_compute() {  return false;  }
  /**
   * @apilvl internal
   */
  protected boolean offsetBeforeParameters_computed = false;
  /**
   * @apilvl internal
   */
  protected int offsetBeforeParameters_value;
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:38
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int offsetBeforeParameters() {
    if(offsetBeforeParameters_computed) {
      return offsetBeforeParameters_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    offsetBeforeParameters_value = offsetBeforeParameters_compute();
if(isFinal && num == state().boundariesCrossed) offsetBeforeParameters_computed = true;
    return offsetBeforeParameters_value;
  }
  /**
   * @apilvl internal
   */
  private int offsetBeforeParameters_compute() {  return isStatic() ? 0 : 1;  }
  /**
   * @apilvl internal
   */
  protected boolean offsetAfterParameters_computed = false;
  /**
   * @apilvl internal
   */
  protected int offsetAfterParameters_value;
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:40
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int offsetAfterParameters() {
    if(offsetAfterParameters_computed) {
      return offsetAfterParameters_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    offsetAfterParameters_value = offsetAfterParameters_compute();
if(isFinal && num == state().boundariesCrossed) offsetAfterParameters_computed = true;
    return offsetAfterParameters_value;
  }
  /**
   * @apilvl internal
   */
  private int offsetAfterParameters_compute() {
    if(getNumParameter() == 0)
      return offsetBeforeParameters();
    return getParameter(getNumParameter()-1).localNum() + 
           getParameter(getNumParameter()-1).type().variableSize();
  }
  /**
   * @apilvl internal
   */
  protected boolean resultOffset_computed = false;
  /**
   * @apilvl internal
   */
  protected int resultOffset_value;
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:71
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int resultOffset() {
    if(resultOffset_computed) {
      return resultOffset_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    resultOffset_value = resultOffset_compute();
if(isFinal && num == state().boundariesCrossed) resultOffset_computed = true;
    return resultOffset_value;
  }
  /**
   * @apilvl internal
   */
  private int resultOffset_compute() {  return type().isVoid() ? 0 : type().variableSize();  }
  /* It is also a compile-time error if any method declared in an annotation type has a
  signature that is override-equivalent to that of any public or protected method
  declared in class Object or in the interface annotation.Annotation* @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:139
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean annotationMethodOverride() {
      ASTNode$State state = state();
    boolean annotationMethodOverride_value = annotationMethodOverride_compute();
    return annotationMethodOverride_value;
  }
  /**
   * @apilvl internal
   */
  private boolean annotationMethodOverride_compute() {  return !hostType().ancestorMethods(signature()).isEmpty();  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:285
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasAnnotationSuppressWarnings(String s) {
      ASTNode$State state = state();
    boolean hasAnnotationSuppressWarnings_String_value = hasAnnotationSuppressWarnings_compute(s);
    return hasAnnotationSuppressWarnings_String_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasAnnotationSuppressWarnings_compute(String s) {  return getModifiers().hasAnnotationSuppressWarnings(s);  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:323
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDeprecated() {
      ASTNode$State state = state();
    boolean isDeprecated_value = isDeprecated_compute();
    return isDeprecated_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDeprecated_compute() {  return getModifiers().hasDeprecatedAnnotation();  }
  /**
   * @apilvl internal
   */
  protected boolean usesTypeVariable_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean usesTypeVariable_value;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:907
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean usesTypeVariable() {
    if(usesTypeVariable_computed) {
      return usesTypeVariable_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    usesTypeVariable_value = usesTypeVariable_compute();
if(isFinal && num == state().boundariesCrossed) usesTypeVariable_computed = true;
    return usesTypeVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean usesTypeVariable_compute() {  return getModifiers().usesTypeVariable() || getTypeAccess().usesTypeVariable() ||
    getParameterList().usesTypeVariable() || getExceptionList().usesTypeVariable();  }
  /**
   * @apilvl internal
   */
  protected boolean sourceMethodDecl_computed = false;
  /**
   * @apilvl internal
   */
  protected MethodDecl sourceMethodDecl_value;
  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1275
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl sourceMethodDecl() {
    if(sourceMethodDecl_computed) {
      return sourceMethodDecl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    sourceMethodDecl_value = sourceMethodDecl_compute();
if(isFinal && num == state().boundariesCrossed) sourceMethodDecl_computed = true;
    return sourceMethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private MethodDecl sourceMethodDecl_compute() {  return this;  }
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsParTypeDecl.jrag:66
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean visibleTypeParameters() {
      ASTNode$State state = state();
    boolean visibleTypeParameters_value = visibleTypeParameters_compute();
    return visibleTypeParameters_value;
  }
  /**
   * @apilvl internal
   */
  private boolean visibleTypeParameters_compute() {  return !isStatic();  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:284
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int arity() {
      ASTNode$State state = state();
    int arity_value = arity_compute();
    return arity_value;
  }
  /**
   * @apilvl internal
   */
  private int arity_compute() {  return getNumParameter();  }
  /* The method is then a variable arity method. Otherwise, it is a fixed arity method.* @attribute syn
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:33
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVariableArity() {
      ASTNode$State state = state();
    boolean isVariableArity_value = isVariableArity_compute();
    return isVariableArity_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVariableArity_compute() {  return getNumParameter() == 0 ? false : getParameter(getNumParameter()-1).isVariableArity();  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:38
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ParameterDeclaration lastParameter() {
      ASTNode$State state = state();
    ParameterDeclaration lastParameter_value = lastParameter_compute();
    return lastParameter_value;
  }
  /**
   * @apilvl internal
   */
  private ParameterDeclaration lastParameter_compute() {  return getParameter(getNumParameter() - 1);  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:56
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl erasedMethod() {
      ASTNode$State state = state();
    MethodDecl erasedMethod_value = erasedMethod_compute();
    return erasedMethod_value;
  }
  /**
   * @apilvl internal
   */
  private MethodDecl erasedMethod_compute() {  return this;  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:404
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean needsSignatureAttribute() {
      ASTNode$State state = state();
    boolean needsSignatureAttribute_value = needsSignatureAttribute_compute();
    return needsSignatureAttribute_value;
  }
  /**
   * @apilvl internal
   */
  private boolean needsSignatureAttribute_compute() {
    if(type().needsSignatureAttribute())
      return true;
    for(int i = 0; i < getNumParameter(); i++)
      if(getParameter(i).type().needsSignatureAttribute())
        return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:523
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String methodTypeSignature() {
      ASTNode$State state = state();
    String methodTypeSignature_value = methodTypeSignature_compute();
    return methodTypeSignature_value;
  }
  /**
   * @apilvl internal
   */
  private String methodTypeSignature_compute() {
    StringBuffer buf = new StringBuffer();
    buf.append("(");
    for(int i = 0; i < getNumParameter(); i++)
      buf.append(getParameter(i).type().classTypeSignature());
    buf.append(")");
    buf.append(type().classTypeSignature());
    for(int i = 0; i < getNumException(); i++)
      buf.append("^" + getException(i).type().classTypeSignature());
    return buf.toString();
  }
  protected java.util.Map handlesException_TypeDecl_values;
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean handlesException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if(handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
    if(handlesException_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)handlesException_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
if(isFinal && num == state().boundariesCrossed) handlesException_TypeDecl_values.put(_parameters, Boolean.valueOf(handlesException_TypeDecl_value));
    return handlesException_TypeDecl_value;
  }
  /**
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:14
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl unknownMethod() {
      ASTNode$State state = state();
    MethodDecl unknownMethod_value = getParent().Define_MethodDecl_unknownMethod(this, null);
    return unknownMethod_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:438
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getBlockOptNoTransform()) {
      return v.isFinal() && (v.isClassVariable() || v.isInstanceVariable()) ? true : isDAbefore(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:872
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getBlockOptNoTransform()) {
      return v.isFinal() && (v.isClassVariable() || v.isInstanceVariable()) ? false : true;
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:128
   * @apilvl internal
   */
  public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(caller == getBlockOptNoTransform()) {
      return throwsException(exceptionType) || handlesException(exceptionType);
    }
    return getParent().Define_boolean_handlesException(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:101
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return parameterDeclaration(name);
    }
    if(caller == getBlockOptNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    // A declaration of a method parameter name shadows any other variable declarations
    if(!set.isEmpty()) return set;
    // Delegate to other declarations in scope
    return lookupVariable(name);
  }
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:323
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePublic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:324
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeProtected(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:325
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePrivate(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:326
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeAbstract(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:327
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeStatic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:328
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeFinal(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:329
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeSynchronized(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeSynchronized(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:330
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeNative(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeNative(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:331
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeStrictfp(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:332
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeBase(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeBase(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:242
   * @apilvl internal
   */
  public ASTNode Define_ASTNode_enclosingBlock(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return this;
    }
    return getParent().Define_ASTNode_enclosingBlock(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:82
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getExceptionListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.TYPE_NAME;
    }
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.TYPE_NAME;
    }
    if(caller == getTypeAccessNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:403
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return type();
    }
    return getParent().Define_TypeDecl_returnType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:180
   * @apilvl internal
   */
  public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return isStatic();
    }
    return getParent().Define_boolean_inStaticContext(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:33
   * @apilvl internal
   */
  public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_reachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:86
   * @apilvl internal
   */
  public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return true;
    }
    return getParent().Define_boolean_isMethodParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:87
   * @apilvl internal
   */
  public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return getParent().Define_boolean_isConstructorParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:88
   * @apilvl internal
   */
  public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:73
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return offsetAfterParameters() + 
      resultOffset();
    }
    if(caller == getParameterListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
    if(index == 0)
      return offsetBeforeParameters();
    return getParameter(index-1).localNum() + getParameter(index-1).type().variableSize();
  }
}
    return getParent().Define_int_localNum(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:66
   * @apilvl internal
   */
  public int Define_int_resultSaveLocalNum(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return offsetAfterParameters();
    }
    return getParent().Define_int_resultSaveLocalNum(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:86
   * @apilvl internal
   */
  public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
    if(caller == getModifiersNoTransform()) {
      return name.equals("METHOD");
    }
    return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:22
   * @apilvl internal
   */
  public boolean Define_boolean_variableArityValid(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
      return i == getNumParameter() - 1;
    }
    return getParent().Define_boolean_variableArityValid(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag at line 881
    if(!m_done() &&!isAbstract()&& isOverridingMethod()) {
      state().duringMethodTransform++;
      ASTNode result = rewriteRule0();
      state().duringMethodTransform--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:881
   * @apilvl internal
   */  private MethodDecl rewriteRule0() {
{	    
            String mfit=new String("doFitness");
			List<Expr> list=new List<Expr>();
			ThisAccess tis=new ThisAccess("this");
			Expr tisexpr=new CastExpr(new TypeAccess("Object"),(Expr)tis);			
			//list.add(tisexpr);						
			StringLiteral para1=new StringLiteral(name());
            //list.add(para1);             
			//StringLiteral para2=new StringLiteral(signature());
		    //list.add(para2);

            String gname=getGranuleName(); 
            StringLiteral arg=new StringLiteral(gname);				
            list.add(arg);			
		    MethodAccess m=new MethodAccess(mfit,list);			
			TypeAccess ta= new TypeAccess("InvokeAgent");			
			LogNotExpr lne=new LogNotExpr(ta.qualifiesAccess(m));		
			
			String returnTypeName="";
			TypeDecl td=getTypeAccess().type();
	        if(td.isPrimitive()||td.isBoolean()) 
            returnTypeName=td.boxed().typeName();
            else    	
	        returnTypeName=td.typeName();
			
			String mrep=isVoid()? new String("replaceVoidMethod"):new String("replaceMethod");			
			List<Expr> l=new List<Expr>();
            l.add(tisexpr);
			l.add(arg);
			l.add(para1);				
			
			List params=getParameterList().fullCopy();
			int len=params.getNumChild();
			
		    List<Expr> cls=new List<Expr>();		
			AbstractDot cclass=null;
			for(int x = 0; x < len; x++) {
			cclass=new AbstractDot(new TypeAccess(getParameter(x).type().typeName()),new ClassAccess());
			cls.add(cclass);
            }			
            Access classAccess=new TypeAccess("Class");			
			classAccess=new ArrayTypeAccess(classAccess);			
		    if(len==0){		
             l.add(new ArrayCreationExpr(classAccess,new Opt(new ArrayInit(cls))));			 
		    }
			else{		 	     
		     l.add(new ArrayCreationExpr(classAccess,new Opt(new ArrayInit(cls))));
            }	
		   
		    List args=new List();
            for(int y = 0; y < len; y++)
              args.add(new VarAccess(((ParameterDeclaration)params.getChild(y)).getID()));			
						
			Access typeAccess=new TypeAccess("Object");  		 
		    typeAccess=new ArrayTypeAccess(typeAccess);			
		    if(len==0){		
             l.add(new ArrayCreationExpr(typeAccess,new Opt(new ArrayInit(args))));			 
		    }
			else{		 	     
		     l.add(new ArrayCreationExpr(typeAccess,new Opt(new ArrayInit(args))));
            }  		
			 
			MethodAccess mr=new MethodAccess(mrep,l); 
			Access dr=ta.qualifiesAccess(mr);	
            		
			Stmt t=null;
		    if(!isVoid()){
			   t=new ReturnStmt(new CastExpr(new TypeAccess(returnTypeName),dr));
            }
            else{
			   t=new ExprStmt(dr);
			}
			
		    IfStmt ist=new IfStmt(lne, t, new Opt(getBlock().fullCopy()));
            List<Stmt> ls=new List<Stmt>();
            ls.add(ist);
           	setBlock(new Block(ls));
            //System.out.println("good! in body "+hostType().typeName() + "method name is :"+name());		
            return this;					
		 }  }
}
