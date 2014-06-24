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
 * @declaredat Annotations.ast:8
 */
public class ElementValuePair extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
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
  public ElementValuePair clone() throws CloneNotSupportedException {
    ElementValuePair node = (ElementValuePair)super.clone();
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
  public ElementValuePair copy() {
      try {
        ElementValuePair node = (ElementValuePair)clone();
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
  public ElementValuePair fullCopy() {
    ElementValuePair res = (ElementValuePair)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:502
   */
  public void typeCheck() {
    if(!type().commensurateWith(getElementValue()))
      error(type().typeName() + " is not commensurate with " + getElementValue().type().typeName());
  }
  /**
   * @ast method 
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:589
   */
  public void toString(StringBuffer s) {
    s.append(getName() + " = ");
    getElementValue().toString(s);
  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:1
   */
  public ElementValuePair() {
    super();


  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:7
   */
  public ElementValuePair(String p0, ElementValue p1) {
    setName(p0);
    setChild(p1, 0);
  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:11
   */
  public ElementValuePair(beaver.Symbol p0, ElementValue p1) {
    setName(p0);
    setChild(p1, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:18
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Annotations.ast:24
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for lexeme Name
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:5
   */
  public void setName(String value) {
    tokenString_Name = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat Annotations.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_Name;
  /**
   * @ast method 
   * @declaredat Annotations.ast:9
   */
  
  public int Namestart;
  /**
   * @ast method 
   * @declaredat Annotations.ast:10
   */
  
  public int Nameend;
  /**
   * @ast method 
   * @declaredat Annotations.ast:11
   */
  public void setName(beaver.Symbol symbol) {
    if(symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setName is only valid for String lexemes");
    tokenString_Name = (String)symbol.value;
    Namestart = symbol.getStart();
    Nameend = symbol.getEnd();
  }
  /**
   * Getter for lexeme Name
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:22
   */
  public String getName() {
    return tokenString_Name != null ? tokenString_Name : "";
  }
  /**
   * Setter for ElementValue
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:5
   */
  public void setElementValue(ElementValue node) {
    setChild(node, 0);
  }
  /**
   * Getter for ElementValue
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:12
   */
  public ElementValue getElementValue() {
    return (ElementValue)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:18
   */
  public ElementValue getElementValueNoTransform() {
    return (ElementValue)getChildNoTransform(0);
  }
  /**
   * @apilvl internal
   */
  protected boolean type_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl type_value;
  /* The annotation type named by an annotation must be accessible (\ufffd.6) at the
  point where the annotation is used, or a compile-time error occurs.
  Comment: This is done by the access control framework* @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:448
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
  private TypeDecl type_compute() {
    Iterator iter = enclosingAnnotationDecl().memberMethods(getName()).iterator();
    if(iter.hasNext()) {
      MethodDecl m = (MethodDecl)iter.next();
      return m.type();
    }
    return unknownType();
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:456
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl unknownType() {
      ASTNode$State state = state();
    TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
    return unknownType_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:458
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl enclosingAnnotationDecl() {
      ASTNode$State state = state();
    TypeDecl enclosingAnnotationDecl_value = getParent().Define_TypeDecl_enclosingAnnotationDecl(this, null);
    return enclosingAnnotationDecl_value;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag at line 523
    if(type().isArrayDecl() && getElementValue() instanceof ElementConstantValue) {
      state().duringAnnotations++;
      ASTNode result = rewriteRule0();
      state().duringAnnotations--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:523
   * @apilvl internal
   */  private ElementValuePair rewriteRule0() {
{
      setElementValue(new ElementArrayValue(new List().add(getElementValue())));
      return this;
    }  }
}
