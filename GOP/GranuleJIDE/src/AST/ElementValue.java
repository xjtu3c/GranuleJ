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
 * @declaredat Annotations.ast:10
 */
public abstract class ElementValue extends ASTNode<ASTNode> implements Cloneable {
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
  public ElementValue clone() throws CloneNotSupportedException {
    ElementValue node = (ElementValue)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:194
   */
  public void appendAsAttributeTo(Attribute buf) {
    throw new Error(getClass().getName() + " does not support appendAsAttributeTo(Attribute buf)");
  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:1
   */
  public ElementValue() {
    super();


  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:10
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Annotations.ast:16
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:57
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
  private boolean validTarget_compute(Annotation a) {  return false;  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:181
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
  private ElementValue definesElementTypeValue_compute(String name) {  return null;  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:295
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
  private boolean hasValue_compute(String s) {  return false;  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:473
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
  private boolean commensurateWithTypeDecl_compute(TypeDecl type) {  return false;  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:493
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean commensurateWithArrayDecl(ArrayDecl type) {
      ASTNode$State state = state();
    boolean commensurateWithArrayDecl_ArrayDecl_value = commensurateWithArrayDecl_compute(type);
    return commensurateWithArrayDecl_ArrayDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean commensurateWithArrayDecl_compute(ArrayDecl type) {  return type.componentType().commensurateWith(this);  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:506
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
   * @attribute inh
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:459
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl enclosingAnnotationDecl() {
      ASTNode$State state = state();
    TypeDecl enclosingAnnotationDecl_value = getParent().Define_TypeDecl_enclosingAnnotationDecl(this, null);
    return enclosingAnnotationDecl_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:511
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl unknownType() {
      ASTNode$State state = state();
    TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
    return unknownType_value;
  }
  /**
   * @attribute inh
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:231
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
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
