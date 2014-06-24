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
 * @declaredat java.ast:42
 */
public abstract class ReferenceType extends TypeDecl implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    narrowingConversionTo_TypeDecl_values = null;
    bcFields_computed = false;
    bcFields_value = null;
    jvmName_computed = false;
    jvmName_value = null;
    unboxed_computed = false;
    unboxed_value = null;
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
  public ReferenceType clone() throws CloneNotSupportedException {
    ReferenceType node = (ReferenceType)super.clone();
    node.narrowingConversionTo_TypeDecl_values = null;
    node.bcFields_computed = false;
    node.bcFields_value = null;
    node.jvmName_computed = false;
    node.jvmName_value = null;
    node.unboxed_computed = false;
    node.unboxed_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:619
   */
  public void emitReturn(CodeGeneration gen) { gen.emit(Bytecode.ARETURN);}
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:672
   */
  public void emitLoadLocal(CodeGeneration gen, int pos) {
    gen.emitLoadReference(pos);
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:831
   */
  public void emitStoreLocal(CodeGeneration gen, int pos) {
    gen.emitStoreReference(pos);
  }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1054
   */
  void refined_CodeGenerationConversions_ReferenceType_emitCastTo(CodeGeneration gen, TypeDecl type) { if(!instanceOf(type) && !type.isNull()) gen.emitCheckCast(type); }
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1251
   */
  public void branchEQ(CodeGeneration gen, int label) { gen.emitCompare(Bytecode.IF_ACMPEQ, label); }
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1260
   */
  public void branchNE(CodeGeneration gen, int label) { gen.emitCompare(Bytecode.IF_ACMPNE, label); }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:16
   */
  void byteToThis(CodeGeneration gen) {
    if(isUnknown()) throw new Error("Trying to cast to Unknown");
    if(!isNumericType())
      typeByte().boxed().byteToThis(gen);
    else {
      unboxed().byteToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:25
   */
  void charToThis(CodeGeneration gen) {
    if(isUnknown()) throw new Error("Trying to cast to Unknown");
    if(!isNumericType())
      typeChar().boxed().charToThis(gen);
    else {
      unboxed().charToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:34
   */
  void shortToThis(CodeGeneration gen) {
    if(isUnknown()) throw new Error("Trying to cast to Unknown");
    if(!isNumericType())
      typeShort().boxed().shortToThis(gen);
    else {
      unboxed().shortToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:43
   */
  void intToThis(CodeGeneration gen) {
    if(isUnknown()) throw new Error("Trying to cast to Unknown");
    if(!isNumericType())
      typeInt().boxed().intToThis(gen);
    else {
      unboxed().intToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:52
   */
  void longToThis(CodeGeneration gen) {
    if(isUnknown()) throw new Error("Trying to cast to Unknown");
    if(!isNumericType())
      typeLong().boxed().longToThis(gen);
    else {
      unboxed().longToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:61
   */
  void floatToThis(CodeGeneration gen) {
    if(isUnknown()) throw new Error("Trying to cast to Unknown");
    if(!isNumericType())
      typeFloat().boxed().floatToThis(gen);
    else {
      unboxed().floatToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:70
   */
  void doubleToThis(CodeGeneration gen) {
    if(isUnknown()) throw new Error("Trying to cast to Unknown");
    if(!isNumericType())
      typeDouble().boxed().doubleToThis(gen);
    else {
      unboxed().doubleToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ReferenceType() {
    super();

    setChild(new List(), 1);

  }
  /**
   * @ast method 
   * @declaredat java.ast:8
   */
  public ReferenceType(Modifiers p0, String p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @ast method 
   * @declaredat java.ast:13
   */
  public ReferenceType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:21
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:27
   */
  public boolean mayHaveRewrite() {
    return false;
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
  /**
   * @ast method 
   * @declaredat java.ast:8
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
   * @declaredat java.ast:19
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 1);
  }
  /**
   * @return number of children in BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Getter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl)getBodyDeclList().getChild(i);
  }
  /**
   * Add element to list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null || state == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for BodyDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * Getter for list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclList() {
    List<BodyDecl> list = (List<BodyDecl>)getChild(1);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>)getChildNoTransform(1);
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:89
   */
    public void emitCastTo(CodeGeneration gen, TypeDecl type) {
    if(type instanceof PrimitiveType) {
      emitUnboxingOperation(gen);
      unboxed().emitCastTo(gen, type);
    }
    else 
      refined_CodeGenerationConversions_ReferenceType_emitCastTo(gen, type);
  }
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:33
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean wideningConversionTo(TypeDecl type) {
      ASTNode$State state = state();
    boolean wideningConversionTo_TypeDecl_value = wideningConversionTo_compute(type);
    return wideningConversionTo_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean wideningConversionTo_compute(TypeDecl type) {  return instanceOf(type);  }
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:36
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean narrowingConversionTo(TypeDecl type) {
    Object _parameters = type;
    if(narrowingConversionTo_TypeDecl_values == null) narrowingConversionTo_TypeDecl_values = new java.util.HashMap(4);
    if(narrowingConversionTo_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)narrowingConversionTo_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean narrowingConversionTo_TypeDecl_value = narrowingConversionTo_compute(type);
if(isFinal && num == state().boundariesCrossed) narrowingConversionTo_TypeDecl_values.put(_parameters, Boolean.valueOf(narrowingConversionTo_TypeDecl_value));
    return narrowingConversionTo_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean narrowingConversionTo_compute(TypeDecl type) {
    if(type.instanceOf(this))
      return true;
    if(isClassDecl() && !getModifiers().isFinal() && type.isInterfaceDecl())
      return true;
    if(isInterfaceDecl() && type.isClassDecl() && !type.getModifiers().isFinal())
      return true;
    if(isInterfaceDecl() && type.instanceOf(this))
      return true;
    if(fullName().equals("java.lang.Object") && type.isInterfaceDecl())
      return true;
    // Dragons
    // TODO: Check if both are interfaces with compatible methods
    if(isArrayDecl() && type.isArrayDecl() && elementType().instanceOf(type.elementType()))
      return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:166
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isReferenceType() {
      ASTNode$State state = state();
    boolean isReferenceType_value = isReferenceType_compute();
    return isReferenceType_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isReferenceType_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:615
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSupertypeOfNullType(NullType type) {
      ASTNode$State state = state();
    boolean isSupertypeOfNullType_NullType_value = isSupertypeOfNullType_compute(type);
    return isSupertypeOfNullType_NullType_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSupertypeOfNullType_compute(NullType type) {  return true;  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:625
   */
  @SuppressWarnings({"unchecked", "cast"})
  public byte arrayLoad() {
      ASTNode$State state = state();
    byte arrayLoad_value = arrayLoad_compute();
    return arrayLoad_value;
  }
  /**
   * @apilvl internal
   */
  private byte arrayLoad_compute() {  return Bytecode.AALOAD;  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:744
   */
  @SuppressWarnings({"unchecked", "cast"})
  public byte arrayStore() {
      ASTNode$State state = state();
    byte arrayStore_value = arrayStore_compute();
    return arrayStore_value;
  }
  /**
   * @apilvl internal
   */
  private byte arrayStore_compute() {  return Bytecode.AASTORE;  }
  /*syn lazy Collection TypeDecl.bcFields() = new ArrayList();
  eq ReferenceType.bcFields() {
    ArrayList l = new ArrayList();
    for(int i = 0; i < getNumBodyDecl(); i++) 
      if(getBodyDecl(i).isBytecodeField() && getBodyDecl(i).generate())
        l.add(getBodyDecl(i));
    return l;
  }* @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:340
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection bcFields() {
    if(bcFields_computed) {
      return bcFields_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    bcFields_value = bcFields_compute();
if(isFinal && num == state().boundariesCrossed) bcFields_computed = true;
    return bcFields_value;
  }
  /**
   * @apilvl internal
   */
  private Collection bcFields_compute() {
    ArrayList l = new ArrayList();
    for(int i = 0; i < getNumBodyDecl(); i++) 
    {
     if(getBodyDecl(i) instanceof MemberContextVarDecl)
    {
    	if(getBodyDecl(i).isBytecodeField() && getBodyDecl(i).generate()){
    		MemberContextVarDecl valuedecl=(MemberContextVarDecl)getBodyDecl(i);
    		l.add(valuedecl.getContextVar());
    		/*if(valuedecl.getNumMemberDecl()>0)
    		{
    			for(int m=0;m<valuedecl.getNumMemberDecl();m++)
    			{
    				l.add(valuedecl.getMemberDecl(m));
    			}
    		}*/
    	}
    }

    else {
         if(getBodyDecl(i).isBytecodeField() && getBodyDecl(i).generate()){
         l.add(getBodyDecl(i));
         }
        }
      }
    return l;
  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:368
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection bcMethods() {
      ASTNode$State state = state();
    Collection bcMethods_value = bcMethods_compute();
    return bcMethods_value;
  }
  /**
   * @apilvl internal
   */
  private Collection bcMethods_compute() {
    ArrayList l = new ArrayList();
    constructors();
    for(int i = 0; i < getNumBodyDecl(); i++)
      if(getBodyDecl(i).isBytecodeMethod() && getBodyDecl(i).generate())
        l.add(getBodyDecl(i));
    return l;
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:159
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl stringPromotion() {
      ASTNode$State state = state();
    TypeDecl stringPromotion_value = stringPromotion_compute();
    return stringPromotion_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl stringPromotion_compute() {  return typeObject();  }
  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Java2Rewrites.jrag:18
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String jvmName() {
    if(jvmName_computed) {
      return jvmName_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    jvmName_value = jvmName_compute();
if(isFinal && num == state().boundariesCrossed) jvmName_computed = true;
    return jvmName_value;
  }
  /**
   * @apilvl internal
   */
  private String jvmName_compute() {
    if(!isNestedType())
      return fullName();
    else if(isAnonymous() || isLocalClass())
       return enclosingType().jvmName() + "$" + uniqueIndex() + name();
     
    else
    return enclosingType().jvmName() + "$" + name();

  }
  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Java2Rewrites.jrag:62
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String referenceClassFieldName() {
      ASTNode$State state = state();
    String referenceClassFieldName_value = referenceClassFieldName_compute();
    return referenceClassFieldName_value;
  }
  /**
   * @apilvl internal
   */
  private String referenceClassFieldName_compute() {  return "class$" + jvmName().replace('[', '$').replace('.', '$').replace(';', ' ').trim();  }
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:146
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int variableSize() {
      ASTNode$State state = state();
    int variableSize_value = variableSize_compute();
    return variableSize_value;
  }
  /**
   * @apilvl internal
   */
  private int variableSize_compute() {  return 1;  }
  /* It is a compile-time error if the return type of a method declared in an
  annotation type is any type other than one of the following: one of the
  primitive types, String, Class and any invocation of Class, an enum type
  (\ufffd.9), an annotation type, or an array (\ufffd0) of one of the preceding types.* @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:123
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isValidAnnotationMethodReturnType() {
      ASTNode$State state = state();
    boolean isValidAnnotationMethodReturnType_value = isValidAnnotationMethodReturnType_compute();
    return isValidAnnotationMethodReturnType_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isValidAnnotationMethodReturnType_compute() {
    if(isString()) return true;
    if(fullName().equals("java.lang.Class"))
      return true;
    // include generic versions of Class
    if(erasure().fullName().equals("java.lang.Class"))
      return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:48
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean unboxingConversionTo(TypeDecl typeDecl) {
      ASTNode$State state = state();
    boolean unboxingConversionTo_TypeDecl_value = unboxingConversionTo_compute(typeDecl);
    return unboxingConversionTo_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean unboxingConversionTo_compute(TypeDecl typeDecl) {  return unboxed() == typeDecl;  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:52
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl unboxed() {
    if(unboxed_computed) {
      return unboxed_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    unboxed_value = unboxed_compute();
if(isFinal && num == state().boundariesCrossed) unboxed_computed = true;
    return unboxed_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl unboxed_compute() {
    if(packageName().equals("java.lang") && isTopLevelType()) {
      String n = name();
      if(n.equals("Boolean")) return typeBoolean();
      if(n.equals("Byte")) return typeByte();
      if(n.equals("Character")) return typeChar();
      if(n.equals("Short")) return typeShort();
      if(n.equals("Integer")) return typeInt();
      if(n.equals("Long")) return typeLong();
      if(n.equals("Float")) return typeFloat();
      if(n.equals("Double")) return typeDouble();
    }
    return unknownType();
  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:170
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl unaryNumericPromotion() {
      ASTNode$State state = state();
    TypeDecl unaryNumericPromotion_value = unaryNumericPromotion_compute();
    return unaryNumericPromotion_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl unaryNumericPromotion_compute() {  return isNumericType() && !isUnknown() ? unboxed().unaryNumericPromotion() : this;  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:174
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl binaryNumericPromotion(TypeDecl type) {
      ASTNode$State state = state();
    TypeDecl binaryNumericPromotion_TypeDecl_value = binaryNumericPromotion_compute(type);
    return binaryNumericPromotion_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl binaryNumericPromotion_compute(TypeDecl type) {  return unboxed().binaryNumericPromotion(type);  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:196
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isNumericType() {
      ASTNode$State state = state();
    boolean isNumericType_value = isNumericType_compute();
    return isNumericType_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isNumericType_compute() {  return !unboxed().isUnknown() && unboxed().isNumericType();  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:199
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isIntegralType() {
      ASTNode$State state = state();
    boolean isIntegralType_value = isIntegralType_compute();
    return isIntegralType_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isIntegralType_compute() {  return !unboxed().isUnknown() && unboxed().isIntegralType();  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:202
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPrimitive() {
      ASTNode$State state = state();
    boolean isPrimitive_value = isPrimitive_compute();
    return isPrimitive_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPrimitive_compute() {  return !unboxed().isUnknown() && unboxed().isPrimitive();  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:215
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isBoolean() {
      ASTNode$State state = state();
    boolean isBoolean_value = isBoolean_compute();
    return isBoolean_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isBoolean_compute() {  return fullName().equals("java.lang.Boolean") && unboxed().isBoolean();  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsSubtype.jrag:480
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean supertypeNullType(NullType type) {
      ASTNode$State state = state();
    boolean supertypeNullType_NullType_value = supertypeNullType_compute(type);
    return supertypeNullType_NullType_value;
  }
  /**
   * @apilvl internal
   */
  private boolean supertypeNullType_compute(NullType type) {  return true;  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:66
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeBoolean() {
      ASTNode$State state = state();
    TypeDecl typeBoolean_value = getParent().Define_TypeDecl_typeBoolean(this, null);
    return typeBoolean_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:67
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeByte() {
      ASTNode$State state = state();
    TypeDecl typeByte_value = getParent().Define_TypeDecl_typeByte(this, null);
    return typeByte_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:68
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeChar() {
      ASTNode$State state = state();
    TypeDecl typeChar_value = getParent().Define_TypeDecl_typeChar(this, null);
    return typeChar_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:69
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeShort() {
      ASTNode$State state = state();
    TypeDecl typeShort_value = getParent().Define_TypeDecl_typeShort(this, null);
    return typeShort_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:70
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeInt() {
      ASTNode$State state = state();
    TypeDecl typeInt_value = getParent().Define_TypeDecl_typeInt(this, null);
    return typeInt_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:71
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeLong() {
      ASTNode$State state = state();
    TypeDecl typeLong_value = getParent().Define_TypeDecl_typeLong(this, null);
    return typeLong_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeFloat() {
      ASTNode$State state = state();
    TypeDecl typeFloat_value = getParent().Define_TypeDecl_typeFloat(this, null);
    return typeFloat_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:73
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeDouble() {
      ASTNode$State state = state();
    TypeDecl typeDouble_value = getParent().Define_TypeDecl_typeDouble(this, null);
    return typeDouble_value;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
