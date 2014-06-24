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
 * @declaredat BoundNames.ast:6
 */
public class BoundFieldAccess extends VarAccess implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    decl_computed = false;
    decl_value = null;
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
  public BoundFieldAccess clone() throws CloneNotSupportedException {
    BoundFieldAccess node = (BoundFieldAccess)super.clone();
    node.decl_computed = false;
    node.decl_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public BoundFieldAccess copy() {
      try {
        BoundFieldAccess node = (BoundFieldAccess)clone();
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
  public BoundFieldAccess fullCopy() {
    BoundFieldAccess res = (BoundFieldAccess)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect BoundNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BoundNames.jrag:82
   */
  public BoundFieldAccess(FieldDeclaration f) {
    this(f.name(), f);
  }
  /**
   * @ast method 
   * @aspect BoundNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BoundNames.jrag:87
   */
  public boolean isExactVarAccess() {
    return false;
  }
  /**
   * @ast method 
   * @declaredat BoundNames.ast:1
   */
  public BoundFieldAccess() {
    super();


  }
  /**
   * @ast method 
   * @declaredat BoundNames.ast:7
   */
  public BoundFieldAccess(String p0, FieldDeclaration p1) {
    setID(p0);
    setFieldDeclaration(p1);
  }
  /**
   * @ast method 
   * @declaredat BoundNames.ast:11
   */
  public BoundFieldAccess(beaver.Symbol p0, FieldDeclaration p1) {
    setID(p0);
    setFieldDeclaration(p1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat BoundNames.ast:18
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat BoundNames.ast:24
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
   * Setter for lexeme FieldDeclaration
   * @apilvl high-level
   * @ast method 
   * @declaredat BoundNames.ast:5
   */
  public void setFieldDeclaration(FieldDeclaration value) {
    tokenFieldDeclaration_FieldDeclaration = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat BoundNames.ast:8
   */
  
  /**   * @apilvl internal   */  protected FieldDeclaration tokenFieldDeclaration_FieldDeclaration;
  /**
   * Getter for lexeme FieldDeclaration
   * @apilvl high-level
   * @ast method 
   * @declaredat BoundNames.ast:13
   */
  public FieldDeclaration getFieldDeclaration() {
    return tokenFieldDeclaration_FieldDeclaration;
  }
  /**
   * @attribute syn
   * @aspect BoundNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BoundNames.jrag:86
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
  private Variable decl_compute() {  return getFieldDeclaration();  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
