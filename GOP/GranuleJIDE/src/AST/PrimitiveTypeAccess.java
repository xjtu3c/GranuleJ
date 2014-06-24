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
 * @declaredat java.ast:21
 */
public class PrimitiveTypeAccess extends TypeAccess implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    decls_computed = false;
    decls_value = null;
    getPackage_computed = false;
    getPackage_value = null;
    getID_computed = false;
    getID_value = null;
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
  public PrimitiveTypeAccess clone() throws CloneNotSupportedException {
    PrimitiveTypeAccess node = (PrimitiveTypeAccess)super.clone();
    node.decls_computed = false;
    node.decls_value = null;
    node.getPackage_computed = false;
    node.getPackage_value = null;
    node.getID_computed = false;
    node.getID_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public PrimitiveTypeAccess copy() {
      try {
        PrimitiveTypeAccess node = (PrimitiveTypeAccess)clone();
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
  public PrimitiveTypeAccess fullCopy() {
    PrimitiveTypeAccess res = (PrimitiveTypeAccess)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public PrimitiveTypeAccess() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public PrimitiveTypeAccess(String p0) {
    setName(p0);
  }
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  public PrimitiveTypeAccess(beaver.Symbol p0) {
    setName(p0);
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
    return false;
  }
  /**
   * Setter for lexeme Name
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setName(String value) {
    tokenString_Name = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat java.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_Name;
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  
  public int Namestart;
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  
  public int Nameend;
  /**
   * @ast method 
   * @declaredat java.ast:11
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
   * @declaredat java.ast:22
   */
  public String getName() {
    return tokenString_Name != null ? tokenString_Name : "";
  }
  /**
   * Setter for lexeme Package
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setPackage(String value) {
    tokenString_Package = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat java.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_Package;
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
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:149
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
  private SimpleSet decls_compute() {  return lookupType(PRIMITIVE_PACKAGE_NAME, name());  }
  /**
   * @apilvl internal
   */
  protected boolean getPackage_computed = false;
  /**
   * @apilvl internal
   */
  protected String getPackage_value;
  /**
   * @attribute syn nta
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:151
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String getPackage() {
    if(getPackage_computed) {
      return getPackage_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    getPackage_value = getPackage_compute();
      setPackage(getPackage_value);
if(isFinal && num == state().boundariesCrossed) getPackage_computed = true;
    return getPackage_value;
  }
  /**
   * @apilvl internal
   */
  private String getPackage_compute() {  return PRIMITIVE_PACKAGE_NAME;  }
  /**
   * @apilvl internal
   */
  protected boolean getID_computed = false;
  /**
   * @apilvl internal
   */
  protected String getID_value;
  /**
   * @attribute syn nta
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:152
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String getID() {
    if(getID_computed) {
      return getID_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    getID_value = getID_compute();
      setID(getID_value);
if(isFinal && num == state().boundariesCrossed) getID_computed = true;
    return getID_value;
  }
  /**
   * @apilvl internal
   */
  private String getID_compute() {  return getName();  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
