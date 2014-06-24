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
 * @declaredat Annotations.ast:13
 */
public class ElementArrayValue extends ElementValue implements Cloneable {
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
  public ElementArrayValue clone() throws CloneNotSupportedException {
    ElementArrayValue node = (ElementArrayValue)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ElementArrayValue copy() {
      try {
        ElementArrayValue node = (ElementArrayValue)clone();
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
  public ElementArrayValue fullCopy() {
    ElementArrayValue res = (ElementArrayValue)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:599
   */
  public void toString(StringBuffer s) {
    s.append("{");
    for(int i = 0; i < getNumElementValue(); i++) {
      getElementValue(i).toString(s);
      s.append(", ");
    }
    s.append("}");
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:224
   */
  public void appendAsAttributeTo(Attribute buf) {
    buf.u1('[');
    buf.u2(getNumElementValue());
    for(int i = 0; i < getNumElementValue(); i++)
      getElementValue(i).appendAsAttributeTo(buf);
  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:1
   */
  public ElementArrayValue() {
    super();

    setChild(new List(), 0);

  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:8
   */
  public ElementArrayValue(List<ElementValue> p0) {
    setChild(p0, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:14
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Annotations.ast:20
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for ElementValueList
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:5
   */
  public void setElementValueList(List<ElementValue> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ElementValueList
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:12
   */
  public int getNumElementValue() {
    return getElementValueList().getNumChild();
  }
  /**
   * Getter for child in list ElementValueList
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ElementValue getElementValue(int i) {
    return (ElementValue)getElementValueList().getChild(i);
  }
  /**
   * Add element to list ElementValueList
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:27
   */
  public void addElementValue(ElementValue node) {
    List<ElementValue> list = (parent == null || state == null) ? getElementValueListNoTransform() : getElementValueList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:34
   */
  public void addElementValueNoTransform(ElementValue node) {
    List<ElementValue> list = getElementValueListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ElementValueList
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:42
   */
  public void setElementValue(ElementValue node, int i) {
    List<ElementValue> list = getElementValueList();
    list.setChild(node, i);
  }
  /**
   * Getter for ElementValue list.
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:50
   */
  public List<ElementValue> getElementValues() {
    return getElementValueList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:56
   */
  public List<ElementValue> getElementValuesNoTransform() {
    return getElementValueListNoTransform();
  }
  /**
   * Getter for list ElementValueList
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ElementValue> getElementValueList() {
    List<ElementValue> list = (List<ElementValue>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ElementValue> getElementValueListNoTransform() {
    return (List<ElementValue>)getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:63
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
    for(int i = 0;  i < getNumElementValue(); i++)
      if(getElementValue(i).validTarget(a))
        return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:188
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
    for(int i = 0; i < getNumElementValue(); i++)
      if(getElementValue(i).definesElementTypeValue(name) != null)
        return getElementValue(i).definesElementTypeValue(name);
    return null;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:300
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
  private boolean hasValue_compute(String s) {
    for(int i = 0;  i < getNumElementValue(); i++)
      if(getElementValue(i).hasValue(s))
        return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:495
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
  private boolean commensurateWithArrayDecl_compute(ArrayDecl type) {
    for(int i = 0; i < getNumElementValue(); i++)
      if(!type.componentType().commensurateWith(getElementValue(i)))
        return false;
    return true;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:178
   * @apilvl internal
   */
  public ElementValue Define_ElementValue_lookupElementTypeValue(ASTNode caller, ASTNode child, String name) {
    if(caller == getElementValueListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return definesElementTypeValue(name);
    }
    return getParent().Define_ElementValue_lookupElementTypeValue(this, caller, name);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
