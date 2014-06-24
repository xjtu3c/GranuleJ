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
 * @declaredat java.ast:16
 */
public class VarAccess extends Access implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    isConstant_visited = -1;
    isConstant_computed = false;
    isConstant_initialized = false;
    isDAafter_Variable_values = null;
    decls_computed = false;
    decls_value = null;
    decl_computed = false;
    decl_value = null;
    isFieldAccess_computed = false;
    type_computed = false;
    type_value = null;
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
  public VarAccess clone() throws CloneNotSupportedException {
    VarAccess node = (VarAccess)super.clone();
    node.isConstant_visited = -1;
    node.isConstant_computed = false;
    node.isConstant_initialized = false;
    node.isDAafter_Variable_values = null;
    node.decls_computed = false;
    node.decls_value = null;
    node.decl_computed = false;
    node.decl_value = null;
    node.isFieldAccess_computed = false;
    node.type_computed = false;
    node.type_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public VarAccess copy() {
      try {
        VarAccess node = (VarAccess)clone();
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
  public VarAccess fullCopy() {
    VarAccess res = (VarAccess)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect DefiniteAssignment
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:94
   */
  public void definiteAssignment() {
    if(isSource()) {
      if(decl() instanceof VariableDeclaration) {
        VariableDeclaration v = (VariableDeclaration)decl();
        //System.err.println("Is " + v + " final? " + v.isFinal() + ", DAbefore: " + isDAbefore(v));
        if(v.isValue()) {
        }
        else if(v.isBlankFinal()) {
          //if(!isDAbefore(v) && !v.hasInit() && !v.getInit().isConstant())
          if(!isDAbefore(v))
            error("Final variable " + v.name() + " is not assigned before used");
        }
        else {
          //if(!v.hasInit() && !isDAbefore(v)) {
          if(!isDAbefore(v))
          error("Local variable " + v.name() + " in not assigned before used");
        }
      }
      
      else if(decl() instanceof FieldDeclaration && !isQualified()) {
        FieldDeclaration f = (FieldDeclaration)decl();
        //if(f.isFinal() && f.isInstanceVariable() && !isDAbefore(f)) {
        //if(f.isFinal() && !isDAbefore(f) && (!f.hasInit() || !f.getInit().isConstant())) {
        //if(f.isFinal() && (!f.hasInit() || !f.getInit().isConstant()) && !isDAbefore(f)) {
        if(f.isFinal() && !f.hasInit() && !isDAbefore(f)) {
          error("Final field " + f + " is not assigned before used");
        }
      }
      
    }
    if(isDest()) {
      Variable v = decl();
      // Blank final field
      if(v.isFinal() && v.isBlank() && !hostType().instanceOf(v.hostType()))
        error("The final variable is not a blank final in this context, so it may not be assigned.");
      else if(v.isFinal() && isQualified() && (!qualifier().isThisAccess() || ((Access)qualifier()).isQualified()))
        error("the blank final field " + v.name() + " may only be assigned by simple name");
      
      // local variable or parameter
      else if(v instanceof VariableDeclaration) {
        VariableDeclaration var = (VariableDeclaration)v;
        //System.out.println("### is variable");
        if(!var.isValue() && var.getParent().getParent().getParent() instanceof SwitchStmt && var.isFinal()) {
          if(!isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        else if(var.isValue()) {
          if(var.hasInit() || !isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        else if(var.isBlankFinal()) {
          if(var.hasInit() || !isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        if(var.isFinal() && (var.hasInit() || !isDUbefore(var))) {
        //if(var.isFinal() && ((var.hasInit() && var.getInit().isConstant()) || !isDUbefore(var))) {
        }
      }
      // field
      else if(v instanceof FieldDeclaration) {
        FieldDeclaration f = (FieldDeclaration)v;
        if(f.isFinal()) {
          if(f.hasInit())
            error("initialized field " + f.name() + " can not be assigned");
          else {
            BodyDecl bodyDecl = enclosingBodyDecl();
            if(!(bodyDecl instanceof ConstructorDecl) && !(bodyDecl instanceof InstanceInitializer) && !(bodyDecl instanceof StaticInitializer) && !(bodyDecl instanceof FieldDeclaration))
              error("final field " + f.name() + " may only be assigned in constructors and initializers");
            else if(!isDUbefore(f))
              error("Final field " + f.name() + " may only be assigned once");
          }
        }
      }
      else if(v instanceof ParameterDeclaration) {
        ParameterDeclaration p = (ParameterDeclaration)v;

        // 8.4.1
        if(p.isFinal()) {
          error("Final parameter " + p.name() + " may not be assigned");
        }
      }
      
    }
  }
  /**
   * @ast method 
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:458
   */
  protected boolean checkDUeverywhere(Variable v) {
    if(isDest() && decl() == v)
      return false;
    return super.checkDUeverywhere(v);
  }
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1747
   */
  public void registerVariables(){
	  if(hostType().isShadowClassDecl()&&(decl() instanceof FieldDeclaration)&&((FieldDeclaration)decl()).hasSameField()){
		  FieldDeclaration f=(FieldDeclaration)decl();		
		  if(!f.fieldToVar.contains(this))
		  f.fieldToVar.add(this);
	  }
  }
  /**
   * @ast method 
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:212
   */
  public BodyDecl closestBodyDecl(TypeDecl t) {
    ASTNode node = this;
    while(!(node.getParent().getParent() instanceof Program) && node.getParent().getParent() != t) {
      node = node.getParent();
    }
    if(node instanceof BodyDecl)
      return (BodyDecl)node;
    return null;
  }
  /**
   * @ast method 
   * @aspect NodeConstructors
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NodeConstructors.jrag:38
   */
  public VarAccess(String name, int start, int end) {
    this(name);
    this.start = start;
    this.end = end;
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:580
   */
  public void toString(StringBuffer s) {
    s.append(name());
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:717
   */
  public void refined_CodeGeneration_VarAccess_emitStore(CodeGeneration gen) {
    Variable v = decl(); 
    if(v instanceof VariableDeclaration) {
      VariableDeclaration decl = (VariableDeclaration)v;
      if(isDUbefore(v))
         gen.addLocalVariableEntryAtCurrentPC(decl.name(), decl.type().typeDescriptor(), decl.localNum(), decl.variableScopeEndLabel(gen));
      decl.type().emitStoreLocal(gen, decl.localNum());
    }
    else if(v instanceof ParameterDeclaration) {
      ParameterDeclaration decl = (ParameterDeclaration)v;
      decl.type().emitStoreLocal(gen, decl.localNum());
    }
    else if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      if(f.isPrivate() && !hostType().hasField(v.name()))
        f.createAccessorWrite(fieldQualifierType()).emitInvokeMethod(gen, fieldQualifierType());
      else
        f.emitStoreField(gen, fieldQualifierType());
    }
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:617
   */
  public void createAssignSimpleLoadDest(CodeGeneration gen) {
    createLoadQualifier(gen);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:631
   */
  public void createPushAssignmentResult(CodeGeneration gen) {
    if(hostType().needsAccessorFor(decl()))
      return;
    if(decl().isInstanceVariable())
      type().emitDup_x1(gen);
    else
      type().emitDup(gen);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:649
   */
  public void refined_CreateBCode_VarAccess_createAssignLoadDest(CodeGeneration gen) {
    createLoadQualifier(gen);
    Variable v = decl();
    if(v.isInstanceVariable())
      gen.emitDup();
    if(v instanceof VariableDeclaration) {
      VariableDeclaration decl = (VariableDeclaration)v;
      decl.type().emitLoadLocal(gen, decl.localNum());
    }
    else if(v instanceof ParameterDeclaration) {
      ParameterDeclaration decl = (ParameterDeclaration)v;
      decl.type().emitLoadLocal(gen, decl.localNum());
    }
    else if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      if(requiresAccessor())
        f.createAccessor(fieldQualifierType()).emitInvokeMethod(gen, fieldQualifierType());
      else
        f.emitLoadField(gen, fieldQualifierType());      
    }
//  ==================GOP==================================
    else if(v instanceof ContextVarDeclaration)
    {
     ContextVarDeclaration c=(ContextVarDeclaration)v;
     c.emitLoadField(gen,fieldQualifierType());
    }
//================END=====================================
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:706
   */
  public void refined_CreateBCode_VarAccess_createBCode(CodeGeneration gen) {	
    Variable v = decl();
    if(v instanceof VariableDeclaration) {
      VariableDeclaration decl = (VariableDeclaration)v;
      if(decl.hostType() == hostType())    	
        decl.type().emitLoadLocal(gen, decl.localNum());      
      else
        emitLoadLocalInNestedClass(gen, decl);
    }
    else if(v instanceof ParameterDeclaration) {
      ParameterDeclaration decl = (ParameterDeclaration)v;
      if(decl.hostType() == hostType())
        decl.type().emitLoadLocal(gen, decl.localNum());
      else
        emitLoadLocalInNestedClass(gen, decl);
    }
    else if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      createLoadQualifier(gen);
      if(f.isConstant() && (f.type().isPrimitive() || f.type().isString())) {
        if(!f.isStatic())
          fieldQualifierType().emitPop(gen);
        f.constant().createBCode(gen);
      }
      else if(requiresAccessor())
        f.createAccessor(fieldQualifierType()).emitInvokeMethod(gen, fieldQualifierType());
      else
        f.emitLoadField(gen, fieldQualifierType());
    }    
//  ===================GOP=========================================

    else if(v instanceof ContextVarDeclaration) {
        ContextVarDeclaration f=(ContextVarDeclaration)v;
        createLoadQualifier(gen);     
        //if(requiresAccessor())
        // f.createAccessor(fieldQualifierType()).emitInvokeMethod(gen, fieldQualifierType());
        //else
          f.emitLoadField(gen, fieldQualifierType());
      }
//==================END===========================================
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:773
   */
  protected void createLoadQualifier(CodeGeneration gen) {
    Variable v = decl();  
    if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      if(hasPrevExpr()) {    	  
        // load explicit qualifier
        prevExpr().createBCode(gen);
        // pop qualifier stack element for class variables
        // this qualifier must be computed to ensure side effects
        if(!prevExpr().isTypeAccess() && f.isClassVariable())
          prevExpr().type().emitPop(gen);
      }
      else if(f.isInstanceVariable()) { 
        emitThis(gen, fieldQualifierType());
      }
    }
//  ==============================GOP===============================
    if(v instanceof ContextVarDeclaration){
        ContextVarDeclaration f=(ContextVarDeclaration)v;
          emitThis(gen, fieldQualifierType());
        }
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:54
   */
  private TypeDecl refined_InnerClasses_VarAccess_fieldQualifierType() {
    if(hasPrevExpr())
      return prevExpr().type();
    TypeDecl typeDecl = hostType();
    while(typeDecl != null && !typeDecl.hasField(name()))
      typeDecl = typeDecl.enclosingType();
    if(typeDecl != null)
      return typeDecl;
    return decl().hostType();
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:261
   */
  public void collectEnclosingVariables(HashSet set, TypeDecl typeDecl) {
    Variable v = decl();
    if(!v.isInstanceVariable() && !v.isClassVariable() && v.hostType() == typeDecl)
      set.add(v);
    super.collectEnclosingVariables(set, typeDecl);
  }
  /**
   * @ast method 
   * @aspect Transformations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Transformations.jrag:110
   */
  public void transformation() {
    Variable v = decl();
    if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      if(requiresAccessor()) {
        TypeDecl typeDecl = fieldQualifierType();
        if(isSource())
          f.createAccessor(typeDecl);
        if(isDest())
          f.createAccessorWrite(typeDecl);
      }
    }
    super.transformation();
  }
  /**
   * @ast method 
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:344
   */
  public void checkModifiers() {
    if(decl() instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)decl();
      if(f.isDeprecated() &&
        !withinDeprecatedAnnotation() &&
        hostType().topLevelType() != f.hostType().topLevelType() &&
        !withinSuppressWarnings("deprecation"))
          warning(f.name() + " in " + f.hostType().typeName() + " has been deprecated");
    }
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:466
   */
  protected void checkEnum(EnumDecl enumDecl) {
    super.checkEnum(enumDecl);
    if(decl().isStatic() && decl().hostType() == enumDecl && !isConstant())
      error("may not reference a static field of an enum type from here");
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public VarAccess() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public VarAccess(String p0) {
    setID(p0);
  }
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  public VarAccess(beaver.Symbol p0) {
    setID(p0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:16
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:22
   */
  public boolean mayHaveRewrite() {
    return true;
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
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1320
   */
     public void nameCheck() {
    if(decls().isEmpty() && (!isQualified() || !qualifier().type().isUnknown() || qualifier().isPackageAccess()))
      error("no field named " + name());
    if(decls().size() > 1) {
      StringBuffer s = new StringBuffer();
      s.append("several fields named " + name());
      for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
        Variable v = (Variable)iter.next();

        if(v.hostType()!=null)
        s.append("\n    " + v.type().typeName() + "." + v.name() + " declared in " + v.hostType().typeName());
        else
        { 
        s.append("\n    " + v.type().typeName() + "." + v.name() + " declared in program publish " );        	
        }
      }
      error(s.toString());
    }
      
    // 8.8.5.1
    if(inExplicitConstructorInvocation() && !isQualified() && decl().isInstanceVariable() && hostType() == decl().hostType())
      error("instance variable " + name() + " may not be accessed in an explicit constructor invocation");

    Variable v = decl();
    
    if(!v.isFinal() && !v.isClassVariable() && !v.isInstanceVariable() && v.hostType()!=null && v.hostType() != hostType()  && !v.hostType().name().equals(hostType().name()) && v.hostType().isGranuleDecl() )
      error("A parameter/variable used but not declared in an inner class must be declared final");

    // 8.3.2.3
    if((decl().isInstanceVariable() || decl().isClassVariable()) && !isQualified()) {
      if(hostType() != null && !hostType().declaredBeforeUse(decl(), this)) {
        if(inSameInitializer() && !simpleAssignment() && inDeclaringClass()) {
          BodyDecl b = closestBodyDecl(hostType());
          error("variable " + decl().name() + " is used in " + b + " before it is declared");
        }
      }
    }

 }
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:60
   */
    public void emitStore(CodeGeneration gen) {
    Variable v = decl();
    if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      f = f.erasedField();
      if(requiresAccessor())
        f.createAccessorWrite(fieldQualifierType()).emitInvokeMethod(gen, fieldQualifierType());
      else
        f.emitStoreField(gen, fieldQualifierType());
    }
    else
      refined_CodeGeneration_VarAccess_emitStore(gen);
  }
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:73
   */
    public void createAssignLoadDest(CodeGeneration gen) {
    Variable v = decl();
    if(v instanceof FieldDeclaration) {
      createLoadQualifier(gen);
      if(v.isInstanceVariable())
        gen.emitDup();
      FieldDeclaration f = (FieldDeclaration)v;
      f = f.erasedField();
      if(requiresAccessor())
        f.createAccessor(fieldQualifierType()).emitInvokeMethod(gen, fieldQualifierType());
      else
        f.emitLoadField(gen, fieldQualifierType());
    }
    else
      refined_CreateBCode_VarAccess_createAssignLoadDest(gen);
  }
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:90
   */
    public void createBCode(CodeGeneration gen) {
    Variable v = decl();
    if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      f = f.erasedField();
      createLoadQualifier(gen);
      if(f.isConstant() && (f.type().isPrimitive() || f.type().isString())) {
        if(!f.isStatic())
          fieldQualifierType().emitPop(gen);
        f.constant().createBCode(gen);
      }
      else if(requiresAccessor())
        f.createAccessor(fieldQualifierType()).emitInvokeMethod(gen, fieldQualifierType());
      else
        f.emitLoadField(gen, fieldQualifierType());
      if(f.type() != decl().type())
        gen.emitCheckCast(decl().type());
    }
    else
      refined_CreateBCode_VarAccess_createBCode(gen);
  }
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:189
   */
    protected TypeDecl refined_GenericsCodegen_VarAccess_fieldQualifierType() {
    TypeDecl typeDecl = refined_InnerClasses_VarAccess_fieldQualifierType();
    return typeDecl == null ? null : typeDecl.erasure();
  }
  /**
   * @ast method 
   * @aspect StaticImportsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\StaticImportsCodegen.jrag:11
   */
    protected TypeDecl fieldQualifierType() {
    TypeDecl typeDecl = refined_GenericsCodegen_VarAccess_fieldQualifierType();
    if(typeDecl != null)
      return typeDecl;
    return decl().hostType();
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:108
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
  private Constant constant_compute() {  return type().cast(decl().getInit().constant());  }
  /**
   * @apilvl internal
   */
  protected int isConstant_visited = -1;
  /**
   * @apilvl internal
   */
  protected boolean isConstant_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isConstant_initialized = false;
  /**
   * @apilvl internal
   */
  protected boolean isConstant_value;
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:500
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstant() {
    if(isConstant_computed) {
      return isConstant_value;
    }
    ASTNode$State state = state();
    if (!isConstant_initialized) {
      isConstant_initialized = true;
      isConstant_value = false;
    }
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
    int num = state.boundariesCrossed;
    boolean isFinal = this.is$Final();
      do {
        isConstant_visited = state.CIRCLE_INDEX;
        state.CHANGE = false;
        boolean new_isConstant_value = isConstant_compute();
        if (new_isConstant_value!=isConstant_value)
          state.CHANGE = true;
        isConstant_value = new_isConstant_value; 
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
      isConstant_computed = true;
      }
      else {
      state.RESET_CYCLE = true;
      isConstant_compute();
      state.RESET_CYCLE = false;
        isConstant_computed = false;
        isConstant_initialized = false;
      }
      state.IN_CIRCLE = false; 
      return isConstant_value;
    }
    if(isConstant_visited != state.CIRCLE_INDEX) {
      isConstant_visited = state.CIRCLE_INDEX;
      if (state.RESET_CYCLE) {
        isConstant_computed = false;
        isConstant_initialized = false;
        isConstant_visited = -1;
        return isConstant_value;
      }
      boolean new_isConstant_value = isConstant_compute();
      if (new_isConstant_value!=isConstant_value)
        state.CHANGE = true;
      isConstant_value = new_isConstant_value; 
      return isConstant_value;
    }
    return isConstant_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstant_compute() {
    Variable v = decl();
    if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      return f.isConstant() && (!isQualified() || (isQualified() && qualifier().isTypeAccess()));
    }
    boolean result = v.isFinal() && v.hasInit() && v.getInit().isConstant() && (v.type().isPrimitive() || v.type().isString());
    return result && (!isQualified() || (isQualified() && qualifier().isTypeAccess()));
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:60
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
  private Variable varDecl_compute() {  return decl();  }
  protected java.util.Map isDAafter_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:353
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
  private boolean isDAafter_compute(Variable v) {
    return (isDest() && decl() == v) || isDAbefore(v);
  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:833
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafter(Variable v) {
      ASTNode$State state = state();
    boolean isDUafter_Variable_value = isDUafter_compute(v);
    return isDUafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafter_compute(Variable v) {
    if(isDest() && decl() == v)
      return false;
    return isDUbefore(v);
  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1208
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean unassignedEverywhere(Variable v, TryStmt stmt) {
      ASTNode$State state = state();
    boolean unassignedEverywhere_Variable_TryStmt_value = unassignedEverywhere_compute(v, stmt);
    return unassignedEverywhere_Variable_TryStmt_value;
  }
  /**
   * @apilvl internal
   */
  private boolean unassignedEverywhere_compute(Variable v, TryStmt stmt) {
    if(isDest() && decl() == v && enclosingStmt().reachable()) {
      return false;
    }
    return super.unassignedEverywhere(v, stmt);
  }
  /**
   * @apilvl internal
   */
  protected boolean decls_computed = false;
  /**
   * @apilvl internal
   */
  protected SimpleSet decls_value;
  /**
   * @attribute syn
   * @aspect VariableScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:305
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet decls() {
    if(decls_computed) {
      return decls_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    decls_value = decls_compute();
if(isFinal && num == state().boundariesCrossed) decls_computed = true;
    return decls_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet decls_compute() {
    SimpleSet set=lookupVariable(name());   		
    if(set.size() == 1) {
      Variable v = (Variable)set.iterator().next();
      if(!isQualified() && inStaticContext()) {
        if(v.isInstanceVariable() && !hostType().memberFields(v.name()).isEmpty())
          return SimpleSet.emptySet;
      }
      else if(isQualified() && qualifier().staticContextQualifier()) {
        if(v.isInstanceVariable())
          return SimpleSet.emptySet;
      }
    }  
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean decl_computed = false;
  /**
   * @apilvl internal
   */
  protected Variable decl_value;
  /**
   * @attribute syn
   * @aspect VariableScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:320
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Variable decl() {
    if(decl_computed) {
      return decl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    decl_value = decl_compute();
if(isFinal && num == state().boundariesCrossed) decl_computed = true;
    return decl_value;
  }
  /**
   * @apilvl internal
   */
  private Variable decl_compute() {
    SimpleSet decls = decls();
    if(decls.size() == 1)
      return (Variable)decls.iterator().next();
    return unknownField();
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:222
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean inSameInitializer() {
      ASTNode$State state = state();
    boolean inSameInitializer_value = inSameInitializer_compute();
    return inSameInitializer_value;
  }
  /**
   * @apilvl internal
   */
  private boolean inSameInitializer_compute() {
    BodyDecl b = closestBodyDecl(decl().hostType());
    if(b == null) return false;
    if(b instanceof FieldDeclaration && ((FieldDeclaration)b).isStatic() == decl().isStatic())
      return true;
    if(b instanceof InstanceInitializer && !decl().isStatic())
      return true;
    if(b instanceof StaticInitializer && decl().isStatic())
      return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:234
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean simpleAssignment() {
      ASTNode$State state = state();
    boolean simpleAssignment_value = simpleAssignment_compute();
    return simpleAssignment_value;
  }
  /**
   * @apilvl internal
   */
  private boolean simpleAssignment_compute() {  return isDest() && getParent() instanceof AssignSimpleExpr;  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:236
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean inDeclaringClass() {
      ASTNode$State state = state();
    boolean inDeclaringClass_value = inDeclaringClass_compute();
    return inDeclaringClass_value;
  }
  /**
   * @apilvl internal
   */
  private boolean inDeclaringClass_compute() {  return hostType() == decl().hostType();  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:932
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
   * @attribute syn
   * @aspect Names
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\QualifiedNames.jrag:17
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
  protected boolean isFieldAccess_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isFieldAccess_value;
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:24
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFieldAccess() {
    if(isFieldAccess_computed) {
      return isFieldAccess_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isFieldAccess_value = isFieldAccess_compute();
if(isFinal && num == state().boundariesCrossed) isFieldAccess_computed = true;
    return isFieldAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFieldAccess_compute() {  return decl().isClassVariable() || decl().isInstanceVariable();  }
  /**
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:114
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
  private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:291
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
  private TypeDecl type_compute() {  return decl().type();  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:17
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
  private boolean isVariable_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:496
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
  private boolean isVarAccessWithAccessor_compute() {  return decl() instanceof FieldDeclaration && 
        decl().isInstanceVariable() && requiresAccessor();  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:523
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean requiresAccessor() {
      ASTNode$State state = state();
    boolean requiresAccessor_value = requiresAccessor_compute();
    return requiresAccessor_value;
  }
  /**
   * @apilvl internal
   */
  private boolean requiresAccessor_compute() {
    Variable v = decl();
    if(!(v instanceof FieldDeclaration))
      return false;
    FieldDeclaration f = (FieldDeclaration)v;
    if(f.isPrivate() && !hostType().hasField(v.name()))
      return true;
    if(f.isProtected() && !f.hostPackage().equals(hostPackage()) && !hostType().hasField(v.name()))
      return true;
    return false;
  }
  /**
   * @attribute inh
   * @aspect TypeHierarchyCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:158
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean inExplicitConstructorInvocation() {
      ASTNode$State state = state();
    boolean inExplicitConstructorInvocation_value = getParent().Define_boolean_inExplicitConstructorInvocation(this, null);
    return inExplicitConstructorInvocation_value;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 1460
    if(hostType().isGranuleDecl()&&(decl() instanceof ContextVarDeclaration)||hostType().isShadowClassDecl()&&(decl() instanceof ContextVarDeclaration)) {
      state().duringGOP++;
      ASTNode result = rewriteRule0();
      state().duringGOP--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1460
   * @apilvl internal
   */  private AbstractDot rewriteRule0() {
{		 
			   String methodname = new String("getContext");
			   TypeAccess ta = new TypeAccess("GopContext");
		       List<Expr> l =new List<Expr>();
		       StringLiteral para1 = new StringLiteral(this.getID());
		       l.add(para1); 
		       ContextVarDeclaration convar=(ContextVarDeclaration)decl();
		       Modifiers modifiers=convar.getModifiersNoTransform().fullCopy();
		       /*List lis=modifiers.getModifierList();
		       boolean isPrivate=false;				       
		       for(int j = 0; j < lis.getNumChild(); j++)
		       {
		        Modifier modifier=(Modifier)lis.getChild(j);				      
		        if(modifier.getID().equals("private"))
		        isPrivate=true;
		       }	
		       if(isPrivate){
			   String way=new String("getLocalIP");
			   ParseName ipname= new ParseName("getLocalIP");
			   List<Expr> lst=new List<Expr>();
			   MethodAccess m=new MethodAccess(way,lst);		    	  
			   AbstractDot d=new AbstractDot(ta,ipname);
			   d.replaceLast(m);
			   l.add(d);
				}
		       else
		       {*/
		    	StringLiteral para3=new StringLiteral(convar.getModifiers().toString());
			l.add(para3);	
		       //}

		       ParseName pn = new ParseName("getContext");
		       AbstractDot d = new AbstractDot(ta,pn);
		       MethodAccess m = new MethodAccess(methodname,l);
		       d.replaceLast(m);
		       String typename=decl().type().typeName();
		       if(typename.equals("java.lang.String"))
		       return d;
		 	   char typechar=Character.toUpperCase(typename.charAt(0));
		       String descname=typechar+typename.substring(1);
		       String conversion="parse"+descname;
		       String classname=decl().type().boxed().typeName();
		       int n=classname.lastIndexOf(".");
		       String realpackage=classname.substring(0,n);
		       String realclass=classname.substring(n+1);
		       TypeAccess tta=new TypeAccess(realpackage,realclass);
		       ParseName ppn=new ParseName(conversion);
		       List<Expr> ll=new List<Expr>();
		       ll.add(d);
		       MethodAccess mm= new MethodAccess(conversion,ll);
		       AbstractDot dd=new AbstractDot(tta,ppn);
		       dd.replaceLast(mm);
		       return dd;
		  }  }
}
