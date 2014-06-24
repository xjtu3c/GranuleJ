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
 * @declaredat java.ast:73
 */
public class ConstructorDecl extends BodyDecl implements Cloneable, ExceptionHolder {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    accessibleFrom_TypeDecl_values = null;
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    throwsException_TypeDecl_values = null;
    name_computed = false;
    name_value = null;
    signature_computed = false;
    signature_value = null;
    sameSignature_ConstructorDecl_values = null;
    moreSpecificThan_ConstructorDecl_values = null;
    parameterDeclaration_String_values = null;
    circularThisInvocation_ConstructorDecl_values = null;
    attributes_computed = false;
    attributes_value = null;
    descName_computed = false;
    descName_value = null;
    bytecodes_ConstantPool_values = null;
    flags_computed = false;
    localNumOfFirstParameter_computed = false;
    offsetFirstEnclosingVariable_computed = false;
    sourceConstructorDecl_computed = false;
    sourceConstructorDecl_value = null;
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
  public ConstructorDecl clone() throws CloneNotSupportedException {
    ConstructorDecl node = (ConstructorDecl)super.clone();
    node.accessibleFrom_TypeDecl_values = null;
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.throwsException_TypeDecl_values = null;
    node.name_computed = false;
    node.name_value = null;
    node.signature_computed = false;
    node.signature_value = null;
    node.sameSignature_ConstructorDecl_values = null;
    node.moreSpecificThan_ConstructorDecl_values = null;
    node.parameterDeclaration_String_values = null;
    node.circularThisInvocation_ConstructorDecl_values = null;
    node.attributes_computed = false;
    node.attributes_value = null;
    node.descName_computed = false;
    node.descName_value = null;
    node.bytecodes_ConstantPool_values = null;
    node.flags_computed = false;
    node.localNumOfFirstParameter_computed = false;
    node.offsetFirstEnclosingVariable_computed = false;
    node.sourceConstructorDecl_computed = false;
    node.sourceConstructorDecl_value = null;
    node.handlesException_TypeDecl_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ConstructorDecl copy() {
      try {
        ConstructorDecl node = (ConstructorDecl)clone();
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
  public ConstructorDecl fullCopy() {
    ConstructorDecl res = (ConstructorDecl)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:156
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
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:164
   */
  public static List copyParams(List params)
   {
 	  List copyargs=new List();
 	  for(int i=0;i<params.getNumChild();i++){
      ParameterDeclaration pd=(ParameterDeclaration)params.getChild(i);      
      ParameterDeclaration npd=new ParameterDeclaration(new TypeAccess(pd.getTypeAccess().type().fullName()),pd.getID());
 	  copyargs.add(npd);
         }
 	  return copyargs;
   }
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:174
   */
  
   private static boolean isImplicitConstructor=false;
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:175
   */
  public void setImplicitConstructor(boolean value)
   {
	   isImplicitConstructor=value;
   }
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:179
   */
  public boolean hasImplicitConstructor()
   {
	   return isImplicitConstructor;
   }
  /**
   * @ast method 
   * @aspect ConstructorDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:402
   */
  public boolean applicable(List argList) {
    if(getNumParameter() != argList.getNumChild())
      return false;
    for(int i = 0; i < getNumParameter(); i++) {
      TypeDecl arg = ((Expr)argList.getChild(i)).type();
      TypeDecl parameter = getParameter(i).type();
      if(!arg.instanceOf(parameter)) {
        return false;
      }  
    }
    return true;
  }
  /**
   * @ast method 
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:108
   */
  public void checkModifiers() {
    super.checkModifiers();
  }
  /**
   * @ast method 
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:68
   */
  public void nameCheck() {
    super.nameCheck();
    // 8.8
    if(!hostType().name().equals(name()))
      error("constructor " + name() +" does not have the same name as the simple name of the host class " + hostType().name());
    
    // 8.8.2
    if(hostType().lookupConstructor(this) != this)
      error("constructor with signature " + signature() + " is multiply declared in type " + hostType().typeName());

    if(circularThisInvocation(this))
      error("The constructor " + signature() + " may not directly or indirectly invoke itself");
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:130
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    s.append(name() + "(");
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
    
    s.append(" {");
    if(hasConstructorInvocation()) {
      getConstructorInvocation().toString(s);
    }
    for(int i = 0; i < getBlock().getNumStmt(); i++) {
      getBlock().getStmt(i).toString(s);
    }
    s.append(indent());
    s.append("}");
  }
  /**
   * @ast method 
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:422
   */
  public void typeCheck() {
    // 8.8.4 (8.4.4)
    TypeDecl exceptionType = typeThrowable();
    for(int i = 0; i < getNumException(); i++) {
      TypeDecl typeDecl = getException(i).type();
      if(!typeDecl.instanceOf(exceptionType))
        error(signature() + " throws non throwable type " + typeDecl.fullName());
    }
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1024
   */
  public void emitInvokeConstructor(CodeGeneration gen) {
    int size = -1;
    for(int i = 0; i < getNumParameter(); i++)
      size -= getParameter(i).type().variableSize();
    if(hostType().needsEnclosing())
      size--;
    if(hostType().needsSuperEnclosing()) {
      size--;
    }
    String classname = hostType().constantPoolName();
    String      desc = descName();
    String      name = "<init>";
    int index = gen.constantPool().addMethodref(classname, name, desc); 
    gen.emit(Bytecode.INVOKESPECIAL, size).add2(index);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:335
   */
  private void generateBytecodes(CodeGeneration gen) {
    int label = gen.variableScopeLabel();
    gen.addLocalVariableEntryAtCurrentPC("this", hostType().typeDescriptor(), 0, label);
    for(int i = 0; i < getNumParameter(); i++) {
      ParameterDeclaration p = (ParameterDeclaration)getParameter(i);
      gen.addLocalVariableEntryAtCurrentPC(
        p.name(), p.type().typeDescriptor(), p.localNum(), label
      );
    }
    createBCode(gen);
    gen.emitReturn();
    gen.addVariableScopeLabel(label);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:372
   */
  public void createBCode(CodeGeneration gen) {
    try {	
    boolean needsInit = true;	
    if(hasConstructorInvocation()) {
      getConstructorInvocation().createBCode(gen);
      Stmt stmt = getConstructorInvocation();
      if(stmt instanceof ExprStmt) {
        ExprStmt exprStmt = (ExprStmt)stmt;
        Expr expr = exprStmt.getExpr();
        if(!expr.isSuperConstructorAccess())
          needsInit = false;
      }
    }  
	
	
    if(needsEnclosing()) {
      gen.emitLoadReference(0);
      gen.emitLoadReference(1);
      String classname = hostType().constantPoolName(); 
      String desc = enclosing().typeDescriptor();
      String name = "this$0";      
      int index = gen.constantPool().addFieldref(classname, name, desc);
      gen.emit(Bytecode.PUTFIELD, -2).add2(index);
    }
	
    int localIndex = offsetFirstEnclosingVariable();
    for(Iterator iter = hostType().enclosingVariables().iterator(); iter.hasNext(); ) {
        Variable v = (Variable)iter.next();
        gen.emitLoadReference(0);
        v.type().emitLoadLocal(gen, localIndex);
        String classname = hostType().constantPoolName();
        String desc = v.type().typeDescriptor();
        String name = "val$" + v.name();
        int index = gen.constantPool().addFieldref(classname, name, desc);
        gen.emit(Bytecode.PUTFIELD, -1 - v.type().variableSize()).add2(index);
        localIndex += v.type().variableSize();
    }  
 
    if(needsInit) {
      TypeDecl typeDecl = hostType();     
      for(int i = 0; i < typeDecl.getNumBodyDecl(); i++) {
        BodyDecl b = typeDecl.getBodyDecl(i); 
        if(b instanceof FieldDeclaration && b.isBytecodeField() && b.generate()) {
          FieldDeclaration f = (FieldDeclaration)b; 
          if(!f.isStatic() && f.hasInit()) { 
            gen.emit(Bytecode.ALOAD_0);
            f.getInit().createBCode(gen);
            f.getInit().type().emitAssignConvTo(gen, f.type()); // AssignConversion
            f.emitStoreField(gen, hostType());
          }
        }
        else if(b instanceof InstanceInitializer) {
          b.createBCode(gen);
        }
//      ==================================Gop===================================
  else if(b instanceof MemberContextVarDecl && b.isBytecodeField()&&b.generate())
          {
        	  MemberContextVarDecl valuedecl=(MemberContextVarDecl)b;
        	 // for(int j=0;j<valuedecl.getNumContextVar();j++)
        	  //{
        		  ContextVarDeclaration c=(ContextVarDeclaration)valuedecl.getContextVar();
        		  if(c.hasInit()){
        		   gen.emit(Bytecode.ALOAD_0);
        		   c.getInit().createBCode(gen);
        		   c.getInit().type().emitAssignConvTo(gen, c.type()); // AssignConversion
        		   c.emitStoreField(gen, valuedecl.hostType());
        		 //}    			
        	  }
        	/*  if(valuedecl.getNumMemberDecl()>0)
        	  {   
        		  for(int m=0;m<valuedecl.getNumMemberDecl();m++){
        			FieldDeclaration f=(FieldDeclaration)valuedecl.getMemberDecl(m);
        			if(!f.isStatic()&& f.hasInit()){
        	            gen.emit(Bytecode.ALOAD_0);           
        	            f.getInit().createBCode(gen); 
        	            f.getInit().type().emitAssignConvTo(gen, f.type()); // AssignConversion
        	            f.emitStoreField(gen, hostType());
        	          }
        		  }
        		 }*/
          }
//  ================================end=====================================
      }
    }
    gen.maxLocals = Math.max(gen.maxLocals, getBlock().localNum()); 	
    getBlock().createBCode(gen);	
    } catch (Error e) {
      System.err.println(hostType().typeName() + ": " + this);
      throw e;
    }
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:308
   */
  public void generateMethod(DataOutputStream out, ConstantPool cp) throws IOException {
    out.writeChar(flags());
    out.writeChar(cp.addUtf8("<init>")); 
    out.writeChar(cp.addUtf8(descName()));
    out.writeChar(attributes().size());
    for(Iterator itera = attributes().iterator(); itera.hasNext();)
      ((Attribute)itera.next()).emit(out);
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:324
   */
  public void touchMethod(ConstantPool cp) {
    cp.addUtf8("<init>");
    cp.addUtf8(descName());
    attributes();
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:466
   */
  public boolean clear() {
    getBlock().clear();
    setBlock(new Block(new List()));
    bytecodes_ConstantPool_values = null;
    return false;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:607
   */
  

  // add val$name as parameters to the constructor
  protected boolean addEnclosingVariables = true;
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:608
   */
  public void addEnclosingVariables() {
    if(!addEnclosingVariables) return;
    addEnclosingVariables = false;
    hostType().addEnclosingVariables();
    for(Iterator iter = hostType().enclosingVariables().iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      getParameterList().add(new ParameterDeclaration(v.type(), "val$" + v.name()));
    }
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:642
   */
  public ConstructorDecl createAccessor() {
    ConstructorDecl c = (ConstructorDecl)hostType().getAccessor(this, "constructor");
    if(c != null) return c;

    // make sure enclosing varibles are added as parameters prior to building accessor
    addEnclosingVariables();

    Modifiers modifiers = new Modifiers(new List());
    modifiers.addModifier(new Modifier("synthetic"));
    modifiers.addModifier(new Modifier("public"));

    List parameters = createAccessorParameters();

    List exceptionList = new List(); 
    for(int i = 0; i < getNumException(); i++)
    exceptionList.add(getException(i).type().createQualifiedAccess());
    
    // add all parameters as arguments except for the dummy parameter
    List args = new List();
    for(int i = 0; i < parameters.getNumChildNoTransform() - 1; i++)
      args.add(new VarAccess(((ParameterDeclaration)parameters.getChildNoTransform(i)).name()));
    ConstructorAccess access = new ConstructorAccess("this", args);
    access.addEnclosingVariables = false;

    c = new ConstructorDecl(
      modifiers,
      name(),
      parameters,
      exceptionList,
      new Opt(
        new ExprStmt(
          access
        )
      ),
      new Block(
        new List().add(new ReturnStmt(new Opt()))
      )
    );
    c = hostType().addConstructor(c);
    c.addEnclosingVariables = false;
    hostType().addAccessor(this, "constructor", c);
    return c;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:686
   */
  protected List createAccessorParameters() {
    List parameters = new List();
    for (int i=0; i<getNumParameter(); i++)
      parameters.add(new ParameterDeclaration(getParameter(i).type(), getParameter(i).name()));
    parameters.add(new ParameterDeclaration(createAnonymousJavaTypeDecl().createBoundAccess(), ("p" + getNumParameter())));
    return parameters;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:694
   */
  protected TypeDecl createAnonymousJavaTypeDecl() {
    ClassDecl classDecl =
      new ClassDecl(
          new Modifiers(new List().add(new Modifier("synthetic"))),
          "" + hostType().nextAnonymousIndex(),
          new Opt(),
          new List(),
          new List()
      );
    classDecl = hostType().addMemberClass(classDecl);
    hostType().addNestedType(classDecl);
    return classDecl;
  }
  /**
   * @ast method 
   * @aspect Transformations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Transformations.jrag:126
   */
  public void transformation() {
    // this$val as fields and constructor parameters
    addEnclosingVariables();
    super.transformation();
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:138
   */
  protected void transformEnumConstructors() {
    // make sure constructor is private
    Modifiers newModifiers = new Modifiers(new List());
    for (int i = 0; i < getModifiers().getNumModifier(); ++i) {
      String modifier = getModifiers().getModifier(i).getID();
      if (modifier.equals("public") || modifier.equals("private") ||
        modifier.equals("protected"))
          continue;
      newModifiers.addModifier(new Modifier(modifier));
    }
    newModifiers.addModifier(new Modifier("private"));
    setModifiers(newModifiers);

    // add implicit super constructor access since we are traversing
    // without doing rewrites
    if(!hasConstructorInvocation()) {
      setConstructorInvocation(
        new ExprStmt(
          new SuperConstructorAccess("super", new List())
        )
      );
    }
    super.transformEnumConstructors();
    getParameterList().insertChild(
      new ParameterDeclaration(new TypeAccess("java.lang", "String"), "@p0"),
      0
    );
    getParameterList().insertChild(
      new ParameterDeclaration(new TypeAccess("int"), "@p1"),
      1
    );
  }
  /**
   * @ast method 
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1046
   */
  public BodyDecl p(Parameterization parTypeDecl) {
    ConstructorDecl c = new ConstructorDeclSubstituted(
      (Modifiers)getModifiers().fullCopy(),
      getID(),
      getParameterList().substitute(parTypeDecl),
      getExceptionList().substitute(parTypeDecl),
      new Opt(),
      new Block(),
      this
    );
    return c;
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ConstructorDecl() {
    super();

    setChild(new List(), 1);
    setChild(new List(), 2);
    setChild(new Opt(), 3);

  }
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  public ConstructorDecl(Modifiers p0, String p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /**
   * @ast method 
   * @declaredat java.ast:18
   */
  public ConstructorDecl(Modifiers p0, beaver.Symbol p1, List<ParameterDeclaration> p2, List<Access> p3, Opt<Stmt> p4, Block p5) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
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
    setChild(list, 1);
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
    List<ParameterDeclaration> list = (List<ParameterDeclaration>)getChild(1);
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
    return (List<ParameterDeclaration>)getChildNoTransform(1);
  }
  /**
   * Setter for ExceptionList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setExceptionList(List<Access> list) {
    setChild(list, 2);
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
    List<Access> list = (List<Access>)getChild(2);
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
    return (List<Access>)getChildNoTransform(2);
  }
  /**
   * Setter for ConstructorInvocationOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setConstructorInvocationOpt(Opt<Stmt> opt) {
    setChild(opt, 3);
  }
  /**
   * Does this node have a ConstructorInvocation child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasConstructorInvocation() {
    return getConstructorInvocationOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child ConstructorInvocation
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Stmt getConstructorInvocation() {
    return (Stmt)getConstructorInvocationOpt().getChild(0);
  }
  /**
   * Setter for optional child ConstructorInvocation
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setConstructorInvocation(Stmt node) {
    getConstructorInvocationOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Stmt> getConstructorInvocationOpt() {
    return (Opt<Stmt>)getChild(3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Stmt> getConstructorInvocationOptNoTransform() {
    return (Opt<Stmt>)getChildNoTransform(3);
  }
  /**
   * Setter for Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBlock(Block node) {
    setChild(node, 4);
  }
  /**
   * Getter for Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Block getBlock() {
    return (Block)getChild(4);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Block getBlockNoTransform() {
    return (Block)getChildNoTransform(4);
  }
  /**
   * @ast method 
   * @aspect ConstructorDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:394
   */
  private boolean refined_ConstructorDecl_ConstructorDecl_moreSpecificThan_ConstructorDecl(ConstructorDecl m)
{
    for(int i = 0; i < getNumParameter(); i++) {
      if(!getParameter(i).type().instanceOf(m.getParameter(i).type()))
        return false;
    }
    return true;
  }
  /**
   * @ast method 
   * @aspect Attributes
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Attributes.jrag:256
   */
  private Collection refined_Attributes_ConstructorDecl_attributes()
{
    ArrayList l = new ArrayList();
    l.add(new CodeAttribute(bytecodes(hostType().constantPool()), null));
    l.add(new ExceptionsAttribute(bytecodes(hostType().constantPool()), this));
    if(getModifiers().isSynthetic())
      l.add(new SyntheticAttribute(hostType().constantPool()));
    return l;
  }
  protected java.util.Map accessibleFrom_TypeDecl_values;
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AccessControl.jrag:94
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
    if(!hostType().accessibleFrom(type))
      return false;
    else if(isPublic())
      return true;
    else if(isProtected()) {
      return true;
    }
    else if(isPrivate()) {
      return hostType().topLevelType() == type.topLevelType();
    }
    else
      return hostPackage().equals(type.hostPackage());
  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:297
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
  private boolean isDAafter_compute(Variable v) {  return getBlock().isDAafter(v) && getBlock().checkReturnDA(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:753
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
  private boolean isDUafter_compute(Variable v) {  return getBlock().isDUafter(v) && getBlock().checkReturnDU(v);  }
  protected java.util.Map throwsException_TypeDecl_values;
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:144
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
  protected boolean name_computed = false;
  /**
   * @apilvl internal
   */
  protected String name_value;
  /**
   * @attribute syn
   * @aspect ConstructorDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:368
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
   * @aspect ConstructorDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:370
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
      s.append(getParameter(i));
      if(i != getNumParameter() - 1)
        s.append(", ");
    }
    s.append(")");
    return s.toString();
  }
  protected java.util.Map sameSignature_ConstructorDecl_values;
  /**
   * @attribute syn
   * @aspect ConstructorDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:383
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean sameSignature(ConstructorDecl c) {
    Object _parameters = c;
    if(sameSignature_ConstructorDecl_values == null) sameSignature_ConstructorDecl_values = new java.util.HashMap(4);
    if(sameSignature_ConstructorDecl_values.containsKey(_parameters)) {
      return ((Boolean)sameSignature_ConstructorDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean sameSignature_ConstructorDecl_value = sameSignature_compute(c);
if(isFinal && num == state().boundariesCrossed) sameSignature_ConstructorDecl_values.put(_parameters, Boolean.valueOf(sameSignature_ConstructorDecl_value));
    return sameSignature_ConstructorDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean sameSignature_compute(ConstructorDecl c) {
    if(!name().equals(c.name()))
      return false;
    if(c.getNumParameter() != getNumParameter())
      return false;
    for(int i = 0; i < getNumParameter(); i++)
      if(!c.getParameter(i).type().equals(getParameter(i).type()))
        return false;
    return true;
  }
  protected java.util.Map moreSpecificThan_ConstructorDecl_values;
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:168
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean moreSpecificThan(ConstructorDecl m) {
    Object _parameters = m;
    if(moreSpecificThan_ConstructorDecl_values == null) moreSpecificThan_ConstructorDecl_values = new java.util.HashMap(4);
    if(moreSpecificThan_ConstructorDecl_values.containsKey(_parameters)) {
      return ((Boolean)moreSpecificThan_ConstructorDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean moreSpecificThan_ConstructorDecl_value = moreSpecificThan_compute(m);
if(isFinal && num == state().boundariesCrossed) moreSpecificThan_ConstructorDecl_values.put(_parameters, Boolean.valueOf(moreSpecificThan_ConstructorDecl_value));
    return moreSpecificThan_ConstructorDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean moreSpecificThan_compute(ConstructorDecl m) {
    if(!isVariableArity() && !m.isVariableArity())
      return refined_ConstructorDecl_ConstructorDecl_moreSpecificThan_ConstructorDecl(m);
    int num = Math.max(getNumParameter(), m.getNumParameter());
    for(int i = 0; i < num; i++) {
      TypeDecl t1 = i < getNumParameter() - 1 ? getParameter(i).type() : getParameter(getNumParameter()-1).type().componentType();
      TypeDecl t2 = i < m.getNumParameter() - 1 ? m.getParameter(i).type() : m.getParameter(m.getNumParameter()-1).type().componentType();
      if(!t1.instanceOf(t2))
        return false;
    }
    return true;
  }
  protected java.util.Map parameterDeclaration_String_values;
  /**
   * @attribute syn
   * @aspect VariableScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:160
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
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:226
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:263
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:264
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:265
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
  protected java.util.Map circularThisInvocation_ConstructorDecl_values;
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:83
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean circularThisInvocation(ConstructorDecl decl) {
    Object _parameters = decl;
    if(circularThisInvocation_ConstructorDecl_values == null) circularThisInvocation_ConstructorDecl_values = new java.util.HashMap(4);
    if(circularThisInvocation_ConstructorDecl_values.containsKey(_parameters)) {
      return ((Boolean)circularThisInvocation_ConstructorDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean circularThisInvocation_ConstructorDecl_value = circularThisInvocation_compute(decl);
if(isFinal && num == state().boundariesCrossed) circularThisInvocation_ConstructorDecl_values.put(_parameters, Boolean.valueOf(circularThisInvocation_ConstructorDecl_value));
    return circularThisInvocation_ConstructorDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean circularThisInvocation_compute(ConstructorDecl decl) {
    if(hasConstructorInvocation()) {
      Expr e = ((ExprStmt)getConstructorInvocation()).getExpr();
      if(e instanceof ConstructorAccess) {
        ConstructorDecl constructorDecl = ((ConstructorAccess)e).decl();
        if(constructorDecl == decl)
          return true;
        return constructorDecl.circularThisInvocation(decl);
      }
    }
    return false;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:270
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
  private TypeDecl type_compute() {  return unknownType();  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:276
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
  private boolean isVoid_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:31
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
    Collection c = refined_Attributes_ConstructorDecl_attributes();
    getModifiers().addRuntimeVisibleAnnotationsAttribute(c);
    getModifiers().addRuntimeInvisibleAnnotationsAttribute(c);
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:63
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
    // this$0
    if(needsEnclosing())
      b.append(enclosing().typeDescriptor());
    if(needsSuperEnclosing())
      b.append(superEnclosing().typeDescriptor());
    // args
    for (int i=0; i<getNumParameter(); i++)
      b.append(getParameter(i).type().typeDescriptor());
    b.append(")V");
    return b.toString();
  }
  protected java.util.Map bytecodes_ConstantPool_values;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:324
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:77
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
    //if(isSynchronized()) res |= Modifiers.ACC_SYNCHRONIZED;
    //if(isStrictfp()) res |= Modifiers.ACC_STRICT;
    return res;
  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:382
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:423
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
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:583
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean needsEnclosing() {
      ASTNode$State state = state();
    boolean needsEnclosing_value = needsEnclosing_compute();
    return needsEnclosing_value;
  }
  /**
   * @apilvl internal
   */
  private boolean needsEnclosing_compute() {  return hostType().needsEnclosing();  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:584
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean needsSuperEnclosing() {
      ASTNode$State state = state();
    boolean needsSuperEnclosing_value = needsSuperEnclosing_compute();
    return needsSuperEnclosing_value;
  }
  /**
   * @apilvl internal
   */
  private boolean needsSuperEnclosing_compute() {  return hostType().needsSuperEnclosing();  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:586
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl enclosing() {
      ASTNode$State state = state();
    TypeDecl enclosing_value = enclosing_compute();
    return enclosing_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl enclosing_compute() {  return hostType().enclosing();  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:587
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl superEnclosing() {
      ASTNode$State state = state();
    TypeDecl superEnclosing_value = superEnclosing_compute();
    return superEnclosing_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl superEnclosing_compute() {  return hostType().superEnclosing();  }
  /**
   * @apilvl internal
   */
  protected boolean localNumOfFirstParameter_computed = false;
  /**
   * @apilvl internal
   */
  protected int localNumOfFirstParameter_value;
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:76
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int localNumOfFirstParameter() {
    if(localNumOfFirstParameter_computed) {
      return localNumOfFirstParameter_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    localNumOfFirstParameter_value = localNumOfFirstParameter_compute();
if(isFinal && num == state().boundariesCrossed) localNumOfFirstParameter_computed = true;
    return localNumOfFirstParameter_value;
  }
  /**
   * @apilvl internal
   */
  private int localNumOfFirstParameter_compute() {
    int i = 1;
    if(hostType().needsEnclosing())
      i++;
    if(hostType().needsSuperEnclosing())
      i++;
    return i;
  }
  /**
   * @apilvl internal
   */
  protected boolean offsetFirstEnclosingVariable_computed = false;
  /**
   * @apilvl internal
   */
  protected int offsetFirstEnclosingVariable_value;
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:85
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int offsetFirstEnclosingVariable() {
    if(offsetFirstEnclosingVariable_computed) {
      return offsetFirstEnclosingVariable_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    offsetFirstEnclosingVariable_value = offsetFirstEnclosingVariable_compute();
if(isFinal && num == state().boundariesCrossed) offsetFirstEnclosingVariable_computed = true;
    return offsetFirstEnclosingVariable_value;
  }
  /**
   * @apilvl internal
   */
  private int offsetFirstEnclosingVariable_compute() {
    int localIndex = localNumOfFirstParameter();
    Collection vars = hostType().enclosingVariables();
    if(vars.isEmpty())
      return localIndex;
    String name = "val$" + ((Variable)vars.iterator().next()).name();
    for(int i = 0; !getParameter(i).name().equals(name); i++)
      localIndex += getParameter(i).type().variableSize();
    return localIndex;
  }
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:96
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int localIndexOfEnclosingVariable(Variable v) {
      ASTNode$State state = state();
    int localIndexOfEnclosingVariable_Variable_value = localIndexOfEnclosingVariable_compute(v);
    return localIndexOfEnclosingVariable_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private int localIndexOfEnclosingVariable_compute(Variable v) {
    int localIndex  = offsetFirstEnclosingVariable();
    Iterator iter = hostType().enclosingVariables().iterator();
    Variable varDecl = (Variable)iter.next();
    while(varDecl != v && iter.hasNext()) {
      localIndex += varDecl.type().variableSize();
      varDecl = (Variable)iter.next();
    }
    return localIndex;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:286
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
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:324
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
  protected boolean sourceConstructorDecl_computed = false;
  /**
   * @apilvl internal
   */
  protected ConstructorDecl sourceConstructorDecl_value;
  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1279
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ConstructorDecl sourceConstructorDecl() {
    if(sourceConstructorDecl_computed) {
      return sourceConstructorDecl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    sourceConstructorDecl_value = sourceConstructorDecl_compute();
if(isFinal && num == state().boundariesCrossed) sourceConstructorDecl_computed = true;
    return sourceConstructorDecl_value;
  }
  /**
   * @apilvl internal
   */
  private ConstructorDecl sourceConstructorDecl_compute() {  return this;  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:190
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean applicableBySubtyping(List argList) {
      ASTNode$State state = state();
    boolean applicableBySubtyping_List_value = applicableBySubtyping_compute(argList);
    return applicableBySubtyping_List_value;
  }
  /**
   * @apilvl internal
   */
  private boolean applicableBySubtyping_compute(List argList) {
    if(getNumParameter() != argList.getNumChild())
      return false;
    for(int i = 0; i < getNumParameter(); i++) {
      TypeDecl arg = ((Expr)argList.getChild(i)).type();
      if(!arg.instanceOf(getParameter(i).type()))
        return false;
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:210
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean applicableByMethodInvocationConversion(List argList) {
      ASTNode$State state = state();
    boolean applicableByMethodInvocationConversion_List_value = applicableByMethodInvocationConversion_compute(argList);
    return applicableByMethodInvocationConversion_List_value;
  }
  /**
   * @apilvl internal
   */
  private boolean applicableByMethodInvocationConversion_compute(List argList) {
    if(getNumParameter() != argList.getNumChild())
      return false;
    for(int i = 0; i < getNumParameter(); i++) {
      TypeDecl arg = ((Expr)argList.getChild(i)).type();
      if(!arg.methodInvocationConversionTo(getParameter(i).type()))
        return false;
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:231
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean applicableVariableArity(List argList) {
      ASTNode$State state = state();
    boolean applicableVariableArity_List_value = applicableVariableArity_compute(argList);
    return applicableVariableArity_List_value;
  }
  /**
   * @apilvl internal
   */
  private boolean applicableVariableArity_compute(List argList) {
    for(int i = 0; i < getNumParameter() - 1; i++) {
      TypeDecl arg = ((Expr)argList.getChild(i)).type();
      if(!arg.methodInvocationConversionTo(getParameter(i).type()))
        return false;
    }
    for(int i = getNumParameter() - 1; i < argList.getNumChild(); i++) {
      TypeDecl arg = ((Expr)argList.getChild(i)).type();
      if(!arg.methodInvocationConversionTo(lastParameter().type().componentType()))
        return false;
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:318
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean potentiallyApplicable(List argList) {
      ASTNode$State state = state();
    boolean potentiallyApplicable_List_value = potentiallyApplicable_compute(argList);
    return potentiallyApplicable_List_value;
  }
  /**
   * @apilvl internal
   */
  private boolean potentiallyApplicable_compute(List argList) {
    if(isVariableArity() && !(argList.getNumChild() >= arity()-1))
      return false;
    if(!isVariableArity() && !(arity() == argList.getNumChild()))
      return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:325
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
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:34
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
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:63
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
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:197
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ConstructorDecl erasedConstructor() {
      ASTNode$State state = state();
    ConstructorDecl erasedConstructor_value = erasedConstructor_compute();
    return erasedConstructor_value;
  }
  /**
   * @apilvl internal
   */
  private ConstructorDecl erasedConstructor_compute() {  return this;  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:402
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
    for(int i = 0; i < getNumParameter(); i++)
      if(getParameter(i).type().needsSignatureAttribute())
        return true;
    return false;
  }
  protected java.util.Map handlesException_TypeDecl_values;
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:36
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
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:269
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl unknownType() {
      ASTNode$State state = state();
    TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
    return unknownType_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:300
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getBlockNoTransform()) {
      return hasConstructorInvocation() ? getConstructorInvocation().isDAafter(v) : isDAbefore(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:756
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getBlockNoTransform()) {
      return hasConstructorInvocation() ? getConstructorInvocation().isDUafter(v) : isDUbefore(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:141
   * @apilvl internal
   */
  public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(caller == getConstructorInvocationOptNoTransform()) {
      return throwsException(exceptionType) || handlesException(exceptionType);
    }
    if(caller == getBlockNoTransform()) {
      return throwsException(exceptionType) || handlesException(exceptionType);
    }
    return getParent().Define_boolean_handlesException(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:83
   * @apilvl internal
   */
  public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
    if(caller == getConstructorInvocationOptNoTransform()){
    Collection c = new ArrayList();
    for(Iterator iter = lookupMethod(name).iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(!hostType().memberMethods(name).contains(m) || m.isStatic())
        c.add(m);
    }
    return c;
  }
    return getParent().Define_Collection_lookupMethod(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:119
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return parameterDeclaration(name);
    }
    if(caller == getConstructorInvocationOptNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    if(!set.isEmpty()) return set;
    for(Iterator iter=lookupVariable(name).iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      if(!hostType().memberFields(name).contains(v) || v.isStatic())
        set = set.add(v);
    }
    return set;
  }
    if(caller == getBlockNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    if(!set.isEmpty()) return set;
    return lookupVariable(name);
  }
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:345
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePublic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:346
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeProtected(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:347
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePrivate(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:243
   * @apilvl internal
   */
  public ASTNode Define_ASTNode_enclosingBlock(ASTNode caller, ASTNode child) {
    if(caller == getBlockNoTransform()) {
      return this;
    }
    return getParent().Define_ASTNode_enclosingBlock(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:121
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getConstructorInvocationOptNoTransform()) {
      return NameType.EXPRESSION_NAME;
    }
    if(caller == getExceptionListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.TYPE_NAME;
    }
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:516
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
    if(caller == getConstructorInvocationOptNoTransform()) {
      return unknownType();
    }
    return getParent().Define_TypeDecl_enclosingInstance(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:168
   * @apilvl internal
   */
  public boolean Define_boolean_inExplicitConstructorInvocation(ASTNode caller, ASTNode child) {
    if(caller == getConstructorInvocationOptNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_inExplicitConstructorInvocation(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:182
   * @apilvl internal
   */
  public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
    if(caller == getConstructorInvocationOptNoTransform()) {
      return false;
    }
    if(caller == getBlockNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_inStaticContext(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:32
   * @apilvl internal
   */
  public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
    if(caller == getBlockNoTransform()) {
      return !hasConstructorInvocation() ? true : getConstructorInvocation().canCompleteNormally();
    }
    if(caller == getConstructorInvocationOptNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_reachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:83
   * @apilvl internal
   */
  public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return getParent().Define_boolean_isMethodParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:84
   * @apilvl internal
   */
  public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return true;
    }
    return getParent().Define_boolean_isConstructorParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:85
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:111
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(caller == getBlockNoTransform()) {
      return getNumParameter() == 0 ? 
    localNumOfFirstParameter() :
    getParameter(getNumParameter()-1).localNum() + getParameter(getNumParameter()-1).type().variableSize();
    }
    if(caller == getParameterListNoTransform()) {
      int index = caller.getIndexOfChild(child);
      return index == 0 ?
    localNumOfFirstParameter() :
    getParameter(index-1).localNum() + getParameter(index-1).type().variableSize();
    }
    return getParent().Define_int_localNum(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:89
   * @apilvl internal
   */
  public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
    if(caller == getModifiersNoTransform()) {
      return name.equals("CONSTRUCTOR");
    }
    return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:21
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
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag at line 433
    if(!hasConstructorInvocation() && !hostType().isObject()) {
      state().duringLookupConstructor++;
      ASTNode result = rewriteRule0();
      state().duringLookupConstructor--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:433
   * @apilvl internal
   */  private ConstructorDecl rewriteRule0() {
{
//============================GOP=================================
  /* This is the way that can solve the constructor function of shadow class.
   if(hostType().isShadowClassDecl()&&((ShadowClassDecl)hostType()).SuperShadowClass().isClassDecl()){
   List l=new List();
   ShadowClassDecl typedecl=(ShadowClassDecl)hostType();
   ClassDecl classdecl=(ClassDecl)typedecl.SuperShadowClass();
   LinkedList instances=classdecl.classInstances();
   if(!instances.isEmpty()){
   ClassInstanceExpr classexpr=(ClassInstanceExpr)instances.get(0);
   for(int j=0;j<classexpr.getNumArg();j++){
   IntegerLiteral para=new IntegerLiteral(classexpr.getArg(j).toString());
   l.add(para);
   }
   }
    setConstructorInvocation(new ExprStmt(
    		new SuperConstructorAccess("super",l)));
    }
    else {*/
    if(hostType().isShadowClassDecl()&&hasImplicitConstructor())
   {
	   ConstructorDecl cl=(ConstructorDecl)this;
           List list=cl.transformParamsToArgs(cl.getParameterList());	   
	   setConstructorInvocation(new ExprStmt(new SuperConstructorAccess("super",list)));
   }
   else {
//=============================END=================================
      setConstructorInvocation(
        new ExprStmt(
          new SuperConstructorAccess("super", new List())
          )
        );
       }
      return this;
    }  }
}
