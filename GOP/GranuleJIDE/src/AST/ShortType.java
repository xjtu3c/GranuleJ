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
 * @declaredat java.ast:55
 */
public class ShortType extends IntegralType implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    narrowingConversionTo_TypeDecl_values = null;
    unaryNumericPromotion_computed = false;
    unaryNumericPromotion_value = null;
    typeDescriptor_computed = false;
    typeDescriptor_value = null;
    jvmName_computed = false;
    jvmName_value = null;
    boxed_computed = false;
    boxed_value = null;
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
  public ShortType clone() throws CloneNotSupportedException {
    ShortType node = (ShortType)super.clone();
    node.narrowingConversionTo_TypeDecl_values = null;
    node.unaryNumericPromotion_computed = false;
    node.unaryNumericPromotion_value = null;
    node.typeDescriptor_computed = false;
    node.typeDescriptor_value = null;
    node.jvmName_computed = false;
    node.jvmName_value = null;
    node.boxed_computed = false;
    node.boxed_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ShortType copy() {
      try {
        ShortType node = (ShortType)clone();
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
  public ShortType fullCopy() {
    ShortType res = (ShortType)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:968
   */
  public void toString(StringBuffer s) {
		s.append("short");
	}
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1060
   */
  void emitCastTo(CodeGeneration gen, TypeDecl type)    { type.shortToThis(gen); }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1072
   */
  void intToThis(CodeGeneration gen)  { gen.emit(Bytecode.I2S); }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1077
   */
  void floatToThis(CodeGeneration gen)  { gen.emit(Bytecode.F2I).emit(Bytecode.I2S); }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1086
   */
  void doubleToThis(CodeGeneration gen)  { gen.emit(Bytecode.D2I).emit(Bytecode.I2S); }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1095
   */
  void longToThis(CodeGeneration gen)  { gen.emit(Bytecode.L2I).emit(Bytecode.I2S); }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1111
   */
  void charToThis(CodeGeneration gen)    { gen.emit(Bytecode.I2S); }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ShortType() {
    super();

    setChild(new Opt(), 1);
    setChild(new List(), 2);

  }
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  public ShortType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @ast method 
   * @declaredat java.ast:15
   */
  public ShortType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:24
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:30
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
   * Setter for SuperClassAccessOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setSuperClassAccessOpt(Opt<Access> opt) {
    setChild(opt, 1);
  }
  /**
   * Does this node have a SuperClassAccess child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasSuperClassAccess() {
    return getSuperClassAccessOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child SuperClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getSuperClassAccess() {
    return (Access)getSuperClassAccessOpt().getChild(0);
  }
  /**
   * Setter for optional child SuperClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setSuperClassAccess(Access node) {
    getSuperClassAccessOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Access> getSuperClassAccessOpt() {
    return (Opt<Access>)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Access> getSuperClassAccessOptNoTransform() {
    return (Opt<Access>)getChildNoTransform(1);
  }
  /**
   * Setter for BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 2);
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
    List<BodyDecl> list = (List<BodyDecl>)getChild(2);
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
    return (List<BodyDecl>)getChildNoTransform(2);
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:311
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant cast(Constant c) {
      ASTNode$State state = state();
    Constant cast_Constant_value = cast_compute(c);
    return cast_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant cast_compute(Constant c) {  return Constant.create((short)c.intValue());  }
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:28
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
  private boolean narrowingConversionTo_compute(TypeDecl type) {  return type.isByte() || type.isChar();  }
  /**
   * @attribute syn
   * @aspect NumericPromotion
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:150
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl unaryNumericPromotion() {
    if(unaryNumericPromotion_computed) {
      return unaryNumericPromotion_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    unaryNumericPromotion_value = unaryNumericPromotion_compute();
if(isFinal && num == state().boundariesCrossed) unaryNumericPromotion_computed = true;
    return unaryNumericPromotion_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl unaryNumericPromotion_compute() {  return typeInt();  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:190
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isShort() {
      ASTNode$State state = state();
    boolean isShort_value = isShort_compute();
    return isShort_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isShort_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:632
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
  private byte arrayLoad_compute() {  return Bytecode.SALOAD;  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:751
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
  private byte arrayStore_compute() {  return Bytecode.SASTORE;  }
  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String typeDescriptor() {
    if(typeDescriptor_computed) {
      return typeDescriptor_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeDescriptor_value = typeDescriptor_compute();
if(isFinal && num == state().boundariesCrossed) typeDescriptor_computed = true;
    return typeDescriptor_value;
  }
  /**
   * @apilvl internal
   */
  private String typeDescriptor_compute() {  return "S";  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1413
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int arrayPrimitiveTypeDescriptor() {
      ASTNode$State state = state();
    int arrayPrimitiveTypeDescriptor_value = arrayPrimitiveTypeDescriptor_compute();
    return arrayPrimitiveTypeDescriptor_value;
  }
  /**
   * @apilvl internal
   */
  private int arrayPrimitiveTypeDescriptor_compute() {  return 9;  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:162
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
  private TypeDecl stringPromotion_compute() {  return typeInt();  }
  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Java2Rewrites.jrag:39
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
  private String jvmName_compute() {  return "S";  }
  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Java2Rewrites.jrag:51
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String primitiveClassName() {
      ASTNode$State state = state();
    String primitiveClassName_value = primitiveClassName_compute();
    return primitiveClassName_value;
  }
  /**
   * @apilvl internal
   */
  private String primitiveClassName_compute() {  return "Short";  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:39
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl boxed() {
    if(boxed_computed) {
      return boxed_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boxed_value = boxed_compute();
if(isFinal && num == state().boundariesCrossed) boxed_computed = true;
    return boxed_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl boxed_compute() {  return lookupType("java.lang", "Short");  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
