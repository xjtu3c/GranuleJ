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
 * @declaredat Annotations.ast:11
 */
public class ElementConstantValue extends ElementValue implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
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
  public ElementConstantValue clone() throws CloneNotSupportedException {
    ElementConstantValue node = (ElementConstantValue)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ElementConstantValue copy() {
      try {
        ElementConstantValue node = (ElementConstantValue)clone();
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
  public ElementConstantValue fullCopy() {
    ElementConstantValue res = (ElementConstantValue)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:169
   */
  public void nameCheck() {
    if(enclosingAnnotationDecl().fullName().equals("java.lang.annotation.Target")) {
      Variable v = getExpr().varDecl();
      if(v != null && v.hostType().fullName().equals("java.lang.annotation.ElementType"))
        if(lookupElementTypeValue(v.name()) != this)
          error("repeated annotation target");
    }
  }
  /**
   * @ast method 
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:593
   */
  public void toString(StringBuffer s) {
    getExpr().toString(s);
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:197
   */
  public void appendAsAttributeTo(Attribute buf) {
    if(getExpr().isConstant() && !getExpr().type().isEnumDecl()) {
      char tag = getExpr().type().isString() ? 's' : getExpr().type().typeDescriptor().charAt(0);
      int const_value_index = getExpr().type().addAnnotConstant(hostType().constantPool(), getExpr().constant());
      buf.u1(tag);
      buf.u2(const_value_index);
    }
    else if(getExpr().isClassAccess()) {
      int const_class_index = hostType().constantPool().addUtf8(getExpr().type().typeDescriptor());
      buf.u1('c');
      buf.u2(const_class_index);
    }
    else {
      Variable v = getExpr().varDecl();
      if(v == null) throw new Error("Expected Enumeration constant");

      int type_name_index = hostType().constantPool().addUtf8(v.type().typeDescriptor());
      int const_name_index = hostType().constantPool().addUtf8(v.name());
      buf.u1('e');
      buf.u2(type_name_index);
      buf.u2(const_name_index);
    }
  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:1
   */
  public ElementConstantValue() {
    super();


  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:7
   */
  public ElementConstantValue(Expr p0) {
    setChild(p0, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:13
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Annotations.ast:19
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:5
   */
  public void setExpr(Expr node) {
    setChild(node, 0);
  }
  /**
   * Getter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:12
   */
  public Expr getExpr() {
    return (Expr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:18
   */
  public Expr getExprNoTransform() {
    return (Expr)getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:58
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean validTarget(Annotation a) {
      ASTNode$State state = state();
    boolean validTarget_Annotation_value = validTarget_compute(a);
    return validTarget_Annotation_value;
  }
  /**
   * @apilvl internal
   */
  private boolean validTarget_compute(Annotation a) {
    Variable v = getExpr().varDecl();
    if(v == null) return true;
    return v.hostType().fullName().equals("java.lang.annotation.ElementType") && a.mayUseAnnotationTarget(v.name());
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:182
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ElementValue definesElementTypeValue(String name) {
      ASTNode$State state = state();
    ElementValue definesElementTypeValue_String_value = definesElementTypeValue_compute(name);
    return definesElementTypeValue_String_value;
  }
  /**
   * @apilvl internal
   */
  private ElementValue definesElementTypeValue_compute(String name) {
    Variable v = getExpr().varDecl();
    if(v != null && v.hostType().fullName().equals("java.lang.annotation.ElementType") && v.name().equals(name))
      return this;
    return null;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:296
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasValue(String s) {
      ASTNode$State state = state();
    boolean hasValue_String_value = hasValue_compute(s);
    return hasValue_String_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasValue_compute(String s) {  return getExpr().type().isString() &&
    getExpr().isConstant() && 
    getExpr().constant().stringValue().equals(s);  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:474
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean commensurateWithTypeDecl(TypeDecl type) {
      ASTNode$State state = state();
    boolean commensurateWithTypeDecl_TypeDecl_value = commensurateWithTypeDecl_compute(type);
    return commensurateWithTypeDecl_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean commensurateWithTypeDecl_compute(TypeDecl type) {
    Expr v = getExpr();
    if(!v.type().assignConversionTo(type, v))
      return false;
    if((type.isPrimitive() || type.isString()) && !v.isConstant())
      return false;
    if(v.type().isNull())
      return false;
    if(type.fullName().equals("java.lang.Class") && !v.isClassAccess())
      return false;
    if(type.isEnumDecl() && (v.varDecl() == null || !(v.varDecl() instanceof EnumConstant)))
      return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:507
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
  private TypeDecl type_compute() {  return getExpr().type();  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:177
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ElementValue lookupElementTypeValue(String name) {
      ASTNode$State state = state();
    ElementValue lookupElementTypeValue_String_value = getParent().Define_ElementValue_lookupElementTypeValue(this, null, name);
    return lookupElementTypeValue_String_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:546
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getExprNoTransform()) {
      return NameType.AMBIGUOUS_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:551
   * @apilvl internal
   */
  public String Define_String_methodHost(ASTNode caller, ASTNode child) {
    if(caller == getExprNoTransform()) {
      return enclosingAnnotationDecl().typeName();
    }
    return getParent().Define_String_methodHost(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
