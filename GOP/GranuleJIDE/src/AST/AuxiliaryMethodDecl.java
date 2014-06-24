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
 * @declaredat Granule.ast:17
 */
public class AuxiliaryMethodDecl extends MemberDecl implements Cloneable, SimpleSet, Iterator, ExceptionHolder {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    name_computed = false;
    name_value = null;
    methodSignature_computed = false;
    methodSignature_value = null;
    signature_computed = false;
    signature_value = null;
    accessibleFrom_TypeDecl_values = null;
    type_computed = false;
    type_value = null;
    throwsException_TypeDecl_values = null;
    attributes_computed = false;
    attributes_value = null;
    descName_computed = false;
    descName_value = null;
    bytecodes_ConstantPool_values = null;
    flags_computed = false;
    offsetBeforeParameters_computed = false;
    offsetAfterParameters_computed = false;
    resultOffset_computed = false;
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
  public AuxiliaryMethodDecl clone() throws CloneNotSupportedException {
    AuxiliaryMethodDecl node = (AuxiliaryMethodDecl)super.clone();
    node.name_computed = false;
    node.name_value = null;
    node.methodSignature_computed = false;
    node.methodSignature_value = null;
    node.signature_computed = false;
    node.signature_value = null;
    node.accessibleFrom_TypeDecl_values = null;
    node.type_computed = false;
    node.type_value = null;
    node.throwsException_TypeDecl_values = null;
    node.attributes_computed = false;
    node.attributes_value = null;
    node.descName_computed = false;
    node.descName_value = null;
    node.bytecodes_ConstantPool_values = null;
    node.flags_computed = false;
    node.offsetBeforeParameters_computed = false;
    node.offsetAfterParameters_computed = false;
    node.resultOffset_computed = false;
    node.handlesException_TypeDecl_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public AuxiliaryMethodDecl copy() {
      try {
        AuxiliaryMethodDecl node = (AuxiliaryMethodDecl)clone();
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
  public AuxiliaryMethodDecl fullCopy() {
    AuxiliaryMethodDecl res = (AuxiliaryMethodDecl)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:47
   */
  public boolean hasPrimaryMethod() {
	     return primaryMethod(methodSignature())!=null;
     }
  /**
   * @ast method 
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:72
   */
  public void typeCheck()
	  {
		  AuxiliarySpec spec=getAuxiliarySpec();
	    	 if(hasPrimaryMethod())
	    	 {
	    	   MethodDecl primarymethod=primaryMethod(methodSignature());
	    	   primarymethod.typeCheck();
	    	   if(getTypeAccess().type()!=primarymethod.getTypeAccess().type())
	    	   error("the type of auxiliary method is "+type().typeName()+" not consistent with the type of primary method "+primarymethod.type().typeName());
	    	   if(spec.getNumParameter()!=primarymethod.getNumParameter())
	    	   error("the number of auxiliary method is  "+spec.getNumParameter()+" not different from the number of primary method " +primarymethod.getNumParameter());
	    	   else {
	    	   for(int i = 0; i < spec.getNumParameter(); i++)	{
	    		  if(!spec.getParameter(i).type().equals(primarymethod.getParameter(i).type()))
	    			  error("the number of auxiliary method is  "+spec.getParameter(i).type().typeName()+" not different from the number of primary method " +primarymethod.getParameter(i).type().typeName());
	     	      if(!spec.getParameter(i).name().equals(primarymethod.getParameter(i).name()))
	     	    	 error("the number of auxiliary method is  "+spec.getParameter(i).name()+" not different from the number of primary method " +primarymethod.getParameter(i).name());
	    	   }
	    	   }
	    	 }
	   }
  /**
   * @ast method 
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:93
   */
  public void nameCheck()
     {
    	 MethodDecl primarymethod=primaryMethod(methodSignature());
    	 if(primarymethod==null)
    		 error("No MethodDecl signature "+methodSignature()+" exist as the primary method of auxiliary method,make sure primarymethod is declared");
    	 AuxiliarySpec spec=getAuxiliarySpec();
    	 if(spec instanceof AroundSpec)
    	 {
    		 if(hasBlock()){
    		 int found=0;
    		 for(int i=0;i<getBlock().getNumStmt();i++){
    		  if(getBlock().getStmt(i) instanceof ExprStmt){
    		  ExprStmt smt=(ExprStmt)getBlock().getStmt(i);
    		  if(smt.getExpr() instanceof MethodAccess){
    		  MethodAccess method=(MethodAccess)smt.getExpr();
    		  if(method.name().equals(getID())){
    	     	if(spec.getNumParameter()!=method.getNumArg())
       	    	   error("the number of auxiliary method is  "+spec.getNumParameter()+" not different from the number of primary method " +method.getNumArg());
       	         else {
       	    	   for(int j=0; j<spec.getNumParameter();j++){
       	    		  if(!spec.getParameter(j).type().equals(method.getArg(j).type()))
       	    			  error("the type of auxiliary method is  "+spec.getParameter(j).type().typeName()+" not different from the type of primary method " +method.getArg(j).type().typeName());
       	     	     }
       	    	 }
    	      found++;
    		   }
    		  }
    		 }
    	   }
    	   if(found==0)
    	   error("No proceed method exists!");    	    
    	   }
    	 }
       }
  /**
   * @ast method 
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:127
   */
  public void checkModifiers()
     {
    	 super.checkModifiers();
    	 if(hasPrimaryMethod())
    	 {
    	   MethodDecl primarymethod=primaryMethod(methodSignature());
    	   primarymethod.checkModifiers();    	  
    	 if(getModifiers().isPublic()&&!primarymethod.getModifiers().isPublic())
    		   error("the modifer of auxiliary method is "+getModifiers()+" not consistent with the modifer of primary method "+primarymethod.getModifiers());     
    	 if(getModifiers().isPrivate()&&!primarymethod.getModifiers().isPrivate())
  		   error("the modifer of auxiliary method is "+getModifiers()+" not consistent with the modifer of primary method "+primarymethod.getModifiers());  	   	 
         if(getModifiers().isProtected()&&!primarymethod.getModifiers().isProtected())
          error("the modifer of auxiliary method is "+getModifiers()+" not consistent with the modifer of primary method "+primarymethod.getModifiers());      }
     }
  /**
   * @ast method 
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:218
   */
  public void registerMethodDecl(){	
   		 if(hasPrimaryMethod()){
   		 MethodDecl primarymethod=primaryMethod(methodSignature());	
   		 if(primarymethod instanceof MethodDecl)
   		 {
            if(!((MethodDecl)primarymethod).auxiliarymethodes.contains(this))			     
   		     ((MethodDecl)primarymethod).auxiliarymethodes.add(this);
   		 }			 
   		 }		
   		
   	}
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:181
   */
  public boolean isSingleton() { return true; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:182
   */
  public boolean isSingleton(Object o) { return contains(o); }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:195
   */
  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:199
   */
  
  private AuxiliaryMethodDecl iterElem;
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:200
   */
  public Iterator iterator() { iterElem = this; return this; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:201
   */
  public boolean hasNext() { return iterElem != null; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:202
   */
  public Object next() { Object o = iterElem; iterElem = null; return o; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:203
   */
  public void remove() { throw new UnsupportedOperationException(); }
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1950
   */
  public int hasRunAccess()
	{
		if(hasBlock())
		{
			for(int i=0;i<getBlock().getNumStmt();i++)
			{
				if(getBlock().getStmt(i) instanceof ExprStmt){
					ExprStmt smt=(ExprStmt)getBlock().getStmt(i);
					if(smt.getExpr() instanceof MethodAccess){
					MethodAccess method=(MethodAccess)smt.getExpr();
					if(method.name().equals("proceed"))
					return i;
					}
			 }			 
			}		
		}
		return -1;
}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:61
   */
  
  private boolean done = false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:62
   */
  private boolean done() {
    if(done) return true;
    done = true;
    return false;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:67
   */
  public static List copyParams(List params)
  {
	  List copyargs=new List();
	  for(int i=0;i<params.getNumChild();i++)
	  copyargs.add(params.getChild(i));
	  return copyargs;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:74
   */
  public static List transformParamsToArgs(List params)
  {
      List args = new List();
      for(int y = 0; y < params.getNumChild(); y++)
          args.add(new VarAccess(((ParameterDeclaration)params.getChild(y)).getID()));

      return args;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:258
   */
  public static Block getBlockForBeforeMethod(AuxiliaryMethodDecl partialMethodDecl)
  {
      Block block = partialMethodDecl.getBlock().fullCopy();
      List args =partialMethodDecl.getAuxiliarySpec().getParameterList();      
      block.getStmtList().insertChild(new ExprStmt(new ProceedExpr(args)), 0);
      return block;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:265
   */
  public static Block getBlockForAfterMethod(AuxiliaryMethodDecl partialMethodDecl)
  {
      Block block = partialMethodDecl.getBlock().fullCopy();
      List args =partialMethodDecl.getAuxiliarySpec().getParameterList();      
      block.getStmtList().addChild(new ExprStmt(new ProceedExpr(args)));
      return block;
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:261
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + getID()+" "+":"+" ");
    getAuxiliarySpec().toString(s);
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:915
   */
  public void emitInvokeMethod(CodeGeneration gen, TypeDecl hostType) {
	    AuxiliarySpec spec=getAuxiliarySpec();
	    if(hostType.isInterfaceDecl()){
	      int size = type().variableSize() - 1;	     
	      for(int i = 0; i < spec.getNumParameter(); i++)
	      size -= spec.getParameter(i).type().variableSize();
	      String classname = hostType.constantPoolName();
	      String      desc = descName();
	      String      name = name();
	      int index = gen.constantPool().addInterfaceMethodref(classname, name, desc);
	      int numArg = 1; // instance
	      for(int i = 0; i < spec.getNumParameter(); i++)
	        numArg +=spec.getParameter(i).type().variableSize();
	      gen.emit(Bytecode.INVOKEINTERFACE, size).add2(index).add(numArg).add(0);
	    }
	    else {
	      String classname = hostType.constantPoolName();
	      String      desc = descName();
	      String      name = name();
	      int index = gen.constantPool().addMethodref(classname, name, desc);
	      if(isStatic()) {
	        int size = type().variableSize();
	        for(int i = 0; i < spec.getNumParameter(); i++)
	          size -=spec.getParameter(i).type().variableSize();
	        gen.emit(Bytecode.INVOKESTATIC, size).add2(index);
	      }
	      else {
	        int size = type().variableSize() - 1;
	        for(int i = 0; i < spec.getNumParameter(); i++)
	          size -=spec.getParameter(i).type().variableSize();
	        gen.emit(Bytecode.INVOKEVIRTUAL, size).add2(index);
	      }
	    }
	  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:949
   */
  public void emitInvokeSpecialMethod(CodeGeneration gen, TypeDecl hostType) {
		 AuxiliarySpec spec=getAuxiliarySpec();
		String classname = hostType.constantPoolName();
	    String      desc = descName();
	    String      name = name();
	    int index = gen.constantPool().addMethodref(classname, name, desc);
	    int size = type().variableSize() - 1;
	    for(int i = 0; i < spec.getNumParameter(); i++)
	      size -=spec.getParameter(i).type().variableSize();
	    gen.emit(Bytecode.INVOKESPECIAL, size).add2(index);
	  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:216
   */
  private void generateBytecodes(CodeGeneration gen) {
		    int label = gen.variableScopeLabel();
		    if(!isStatic())
		      gen.addLocalVariableEntryAtCurrentPC("this", hostType().typeDescriptor(), 0, label);
		    AuxiliarySpec spec=getAuxiliarySpec();
		    for(int i = 0; i < spec.getNumParameter(); i++) {
		      ParameterDeclaration p = (ParameterDeclaration)spec.getParameter(i);
		      gen.addLocalVariableEntryAtCurrentPC(
		        p.name(), p.type().typeDescriptor(), p.localNum(), label
		      );
		    }
		    createBCode(gen);
		    if(type() instanceof VoidType) 
		      gen.emitReturn();
		    gen.addVariableScopeLabel(label);
		  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:233
   */
  public void createBCode(CodeGeneration gen) {
			    try {
			    if(hasBlock()) {
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:274
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:283
   */
  public void touchMethod(ConstantPool cp) {
	    cp.addUtf8(name());
	    cp.addUtf8(descName());
	    attributes();
   }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:291
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
   * @declaredat Granule.ast:1
   */
  public AuxiliaryMethodDecl() {
    super();

    setChild(new List(), 3);
    setChild(new Opt(), 4);

  }
  /**
   * @ast method 
   * @declaredat Granule.ast:9
   */
  public AuxiliaryMethodDecl(Modifiers p0, Access p1, String p2, AuxiliarySpec p3, List<Access> p4, Opt<Block> p5) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /**
   * @ast method 
   * @declaredat Granule.ast:17
   */
  public AuxiliaryMethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, AuxiliarySpec p3, List<Access> p4, Opt<Block> p5) {
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
   * @declaredat Granule.ast:28
   */
  protected int numChildren() {
    return 5;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Granule.ast:34
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat Granule.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_ID;
  /**
   * @ast method 
   * @declaredat Granule.ast:9
   */
  
  public int IDstart;
  /**
   * @ast method 
   * @declaredat Granule.ast:10
   */
  
  public int IDend;
  /**
   * @ast method 
   * @declaredat Granule.ast:11
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
   * @declaredat Granule.ast:22
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for AuxiliarySpec
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setAuxiliarySpec(AuxiliarySpec node) {
    setChild(node, 2);
  }
  /**
   * Getter for AuxiliarySpec
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public AuxiliarySpec getAuxiliarySpec() {
    return (AuxiliarySpec)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:18
   */
  public AuxiliarySpec getAuxiliarySpecNoTransform() {
    return (AuxiliarySpec)getChildNoTransform(2);
  }
  /**
   * Setter for ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setExceptionList(List<Access> list) {
    setChild(list, 3);
  }
  /**
   * @return number of children in ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public int getNumException() {
    return getExceptionList().getNumChild();
  }
  /**
   * Getter for child in list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getException(int i) {
    return (Access)getExceptionList().getChild(i);
  }
  /**
   * Add element to list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:27
   */
  public void addException(Access node) {
    List<Access> list = (parent == null || state == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:34
   */
  public void addExceptionNoTransform(Access node) {
    List<Access> list = getExceptionListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:42
   */
  public void setException(Access node, int i) {
    List<Access> list = getExceptionList();
    list.setChild(node, i);
  }
  /**
   * Getter for Exception list.
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:50
   */
  public List<Access> getExceptions() {
    return getExceptionList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:56
   */
  public List<Access> getExceptionsNoTransform() {
    return getExceptionListNoTransform();
  }
  /**
   * Getter for list ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:63
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
   * @declaredat Granule.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>)getChildNoTransform(3);
  }
  /**
   * Setter for BlockOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setBlockOpt(Opt<Block> opt) {
    setChild(opt, 4);
  }
  /**
   * Does this node have a Block child?
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public boolean hasBlock() {
    return getBlockOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child Block
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Block getBlock() {
    return (Block)getBlockOpt().getChild(0);
  }
  /**
   * Setter for optional child Block
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:27
   */
  public void setBlock(Block node) {
    getBlockOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getBlockOpt() {
    return (Opt<Block>)getChild(4);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getBlockOptNoTransform() {
    return (Opt<Block>)getChildNoTransform(4);
  }
  /**
   * @apilvl internal
   */
  protected boolean name_computed = false;
  /**
   * @apilvl internal
   */
  protected String name_value;
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:7
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String name() {
    if(name_computed) {
      return name_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    name_value = name_compute();
if(isFinal && num == state().boundariesCrossed) name_computed = true;
    return name_value;
  }
  /**
   * @apilvl internal
   */
  private String name_compute() {  return getAuxiliarySpec().kind()+"$"+getID();  }
  /**
   * @apilvl internal
   */
  protected boolean methodSignature_computed = false;
  /**
   * @apilvl internal
   */
  protected String methodSignature_value;
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:8
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String methodSignature() {
    if(methodSignature_computed) {
      return methodSignature_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    methodSignature_value = methodSignature_compute();
if(isFinal && num == state().boundariesCrossed) methodSignature_computed = true;
    return methodSignature_value;
  }
  /**
   * @apilvl internal
   */
  private String methodSignature_compute() {
		    AuxiliarySpec spec=getAuxiliarySpec();
		    StringBuffer s = new StringBuffer();		    
		    s.append(getID() + "(");
		    for(int i = 0; i <spec.getNumParameter(); i++) {
		      if(i != 0) s.append(", ");
		      s.append(spec.getParameter(i).type().typeName());
		    }
		    s.append(")");
		    return s.toString();
		  }
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
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:19
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
    	 AuxiliarySpec spec=getAuxiliarySpec();
    	 StringBuffer s=new StringBuffer();    	 
    	 s.append(name()+"(");
    	 for(int i = 0; i < spec.getNumParameter(); i++) {
  		 if(i != 0) s.append(", ");
  		 s.append(spec.getParameter(i).type().typeName());
  		}
  		    s.append(")");
  		    return s.toString();
     }
  protected java.util.Map accessibleFrom_TypeDecl_values;
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:31
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
		    else if(isProtected()){
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
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:50
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl primaryMethod(String signature) {
      ASTNode$State state = state();
    MethodDecl primaryMethod_String_value = primaryMethod_compute(signature);
    return primaryMethod_String_value;
  }
  /**
   * @apilvl internal
   */
  private MethodDecl primaryMethod_compute(String signature) {		
		SimpleSet set=(SimpleSet)(hostType().methodsSignature(methodSignature()));	
		if(set.size() == 1){
		MethodDecl n=(MethodDecl)set.iterator().next();    
        return n;
		}
		return null;
	  }
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
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:59
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
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:60
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
  protected java.util.Map throwsException_TypeDecl_values;
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:190
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
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:180
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:193
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:194
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
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:34
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
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:252
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
  private boolean isPublic_compute() {  return getModifiers().isPublic()||hostType().isInterfaceDecl();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:253
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:254
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:255
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
  private boolean isAbstract_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:256
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:257
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
  private boolean isFinal_compute() {  return getModifiers().isFinal()||hostType().isFinal()||isPrivate();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:258
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:259
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:260
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
   * @aspect Attributes
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Attributes.jrag:237
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
	    l.add(new ExceptionsAttribute(bytecodes(hostType().constantPool()), this));
	    if(isAbstract() || isNative()) return l;
	    l.add(new CodeAttribute(bytecodes(hostType().constantPool()), this,true));
	    if(getModifiers().isSynthetic())
	      l.add(new SyntheticAttribute(hostType().constantPool()));
	    return l; 
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:48
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
	    AuxiliarySpec spec=getAuxiliarySpec();
	    StringBuffer b=new StringBuffer();
	    b.append("(");
	    for(int i=0; i<spec.getNumParameter(); i++)
	      b.append(spec.getParameter(i).type().typeDescriptor());
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:203
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
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:63
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
   return res;
 }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:288
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:289
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:18
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
  private int offsetBeforeParameters_compute() {  return isStatic()?0:1;  }
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:19
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
	  AuxiliarySpec spec=getAuxiliarySpec();
	  if(spec.getNumParameter()==0)
		  return offsetBeforeParameters();
	  return spec.getParameter(spec.getNumParameter()-1).localNum()+
	         spec.getParameter(spec.getNumParameter()-1).type().variableSize();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:33
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
  /**
   * @attribute inh
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:158
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupVariable(String name) {
      ASTNode$State state = state();
    SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
    return lookupVariable_String_value;
  }
  protected java.util.Map handlesException_TypeDecl_values;
  /**
   * @attribute inh
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:184
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:61
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return type();
    }
    return getParent().Define_TypeDecl_returnType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:167
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getBlockOptNoTransform()){
    	    SimpleSet v = getAuxiliarySpec().localLookupVariable(name);
    	    if(!v.isEmpty()) return v;
    	    v = getAuxiliarySpec().lookupAfterVariable(name);
    	    if(!v.isEmpty()) return v;
    	    return lookupVariable(name);
    	  }
    if(caller == getAuxiliarySpecNoTransform()){
    	    SimpleSet v = getAuxiliarySpec().localLookupVariable(name);
    	    if(!v.isEmpty()) return v;
    	    v = getAuxiliarySpec().lookupAfterVariable(name);
    	    if(!v.isEmpty()) return v;
    	    return lookupVariable(name);
    	  }
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:185
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getExceptionListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:187
   * @apilvl internal
   */
  public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(caller == getBlockOptNoTransform()) {
      return throwsException(exceptionType) || handlesException(exceptionType);
    }
    return getParent().Define_boolean_handlesException(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:198
   * @apilvl internal
   */
  public ASTNode Define_ASTNode_enclosingBlock(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return this;
    }
    return getParent().Define_ASTNode_enclosingBlock(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:199
   * @apilvl internal
   */
  public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return isStatic();
    }
    return getParent().Define_boolean_inStaticContext(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:334
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePublic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:335
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeProtected(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:336
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePrivate(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:337
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeAbstract(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:338
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeStatic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:339
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeFinal(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:340
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeSynchronized(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeSynchronized(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:341
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeNative(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeNative(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:342
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeStrictfp(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:35
   * @apilvl internal
   */
  public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_reachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:35
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(caller == getBlockOptNoTransform()) {
      return offsetAfterParameters() + 
   resultOffset();
    }
    return getParent().Define_int_localNum(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 1970
    if(getAuxiliarySpec().kind().equals("around")&&hasRunAccess()!=-1) {
      state().duringGOP++;
      ASTNode result = rewriteRule0();
      state().duringGOP--;
      return result;
    }

    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag at line 4
    if(hostType().isGranuleDecl()&& (getAuxiliarySpec() instanceof BeforeSpec)) {
      state().duringMethodTransform++;
      ASTNode result = rewriteRule1();
      state().duringMethodTransform--;
      return result;
    }

    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag at line 32
    if(!done()&&hostType().isClassDecl()) {
      state().duringMethodTransform++;
      ASTNode result = rewriteRule2();
      state().duringMethodTransform--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1970
   * @apilvl internal
   */  private AuxiliaryMethodDecl rewriteRule0() {
{
		int acount=hasRunAccess();
        Stmt smt=getBlock().getStmt(acount);
		if(smt instanceof ExprStmt){
		ExprStmt stmt=(ExprStmt)smt;
		MethodAccess method=(MethodAccess)stmt.getExpr();
		method.setID(getID());
		}
		return this;
	}  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:4
   * @apilvl internal
   */  private MethodDecl rewriteRule1() {
{
		MethodDecl m=primaryMethod(signature());
		String mname=m.getID();
		m.setID(mname+"_base");
		List<Expr> list=new List<Expr>();
		for(int i=0; i<getAuxiliarySpec().getNumParameter();i++)
		{
			TypeAccess access=new TypeAccess(getAuxiliarySpec().getParameter(i).getID());
			list.add(access);
		}
		MethodAccess maccess=new MethodAccess(m.getID(),list);
		ExprStmt stmt=new ExprStmt(maccess);
	    Block bl=(Block)getBlock().fullCopy();
	    bl.addStmt(stmt);
		MethodDecl auxim=new MethodDecl(
		  (Modifiers)getModifiersNoTransform().fullCopy(),
		  (Access)getTypeAccessNoTransform().fullCopy(),
		  mname,
		  getAuxiliarySpec().getParametersNoTransform().fullCopy(),
		  getExceptionsNoTransform().fullCopy(),
		  new Opt(bl));
		return auxim;  
	  }  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:32
   * @apilvl internal
   */  private MethodDecl rewriteRule2() {
{
		 List args=getAuxiliarySpec().getParameterList();
		 String order=getAuxiliarySpec().kind();
		 Block block=getBlock().fullCopy();
		 MethodDecl m=primaryMethod(signature());
		 if(!m.hasBeforeProceedExpr()){
		  m.SortAuxiliaryMethods(m.lookupAuxiliaryMethods(m));
		  m.AuxiliaryMethodsNum();
		  m.methodNames();		
		  if(m.hasBeforeOrAroundMethods()){
		  List transfromargs=transformParamsToArgs(args);
		  m.getBlock().getStmtList().insertChild(new ExprStmt(new ProceedExpr(transfromargs)),0);
		  }
		  if(m.hasAfterMethods()){
		  List newargs=copyParams(args);
		  newargs.insertChild(new ParameterDeclaration(new TypeAccess("int"),"@p"),0);
		  List transformnewargs=transformParamsToArgs(newargs);
		  m.getBlock().getStmtList().addChild(new ExprStmt(new ProceedExpr(transformnewargs)));
		  }
		  m.setBeforeProceedExpr(true);
		 }
		 if((order.equals("before")||order.equals("around"))&&m.hasPrecursor(this))
	     block=getBlockForBeforeMethod(this);
		 if(order.equals("after")&&m.hasSuccessor(this))
		 block=getBlockForAfterMethod(this);
		 return new MethodDecl(getModifiers().fullCopy(), (Access)getTypeAccess().fullCopy(), name(), getAuxiliarySpec().getParameterList().fullCopy(), getExceptionList().fullCopy(), new Opt(block));   
	 }  }
}
