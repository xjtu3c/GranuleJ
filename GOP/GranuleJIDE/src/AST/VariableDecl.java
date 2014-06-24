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
 * @declaredat java.ast:83
 */
public class VariableDecl extends ASTNode<ASTNode> implements Cloneable {
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
  public VariableDecl clone() throws CloneNotSupportedException {
    VariableDecl node = (VariableDecl)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public VariableDecl copy() {
      try {
        VariableDecl node = (VariableDecl)clone();
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
  public VariableDecl fullCopy() {
    VariableDecl res = (VariableDecl)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:117
   */
  public ContextVarDeclaration createContextVarDeclarationFrom(Modifiers modifiers, Access type) {
    ContextVarDeclaration decl = new ContextVarDeclaration(
      modifiers,
      type.addArrayDims(getDimsList()),
      getID(),
      getInitOpt()
    );
    decl.setStart(start); // copy location information
    decl.setEnd(end); // copy location information
    decl.IDstart = IDstart;
    decl.IDend = IDend;
    return decl;
  }
  /**
   * @ast method 
   * @aspect VariableDeclarationTransformation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:230
   */
  public VariableDeclaration createVariableDeclarationFrom(Modifiers modifiers, Access type) {
    VariableDeclaration decl = new VariableDeclaration(
      modifiers,
      type.addArrayDims(getDimsList()),
      getID(),
      getInitOpt()
    );
    decl.setStart(start); // copy location information
    decl.setEnd(end); // copy location information
    decl.IDstart = IDstart;
    decl.IDend = IDend;
    return decl;
  }
  /**
   * @ast method 
   * @aspect VariableDeclarationTransformation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:244
   */
  public FieldDeclaration createFieldDeclarationFrom(Modifiers modifiers, Access type) {
    FieldDeclaration decl = new FieldDeclaration(
      modifiers,
      type.addArrayDims(getDimsList()),
      getID(),
      getInitOpt()
    );
    decl.setStart(start); // copy location information
    decl.setEnd(end); // copy location information
    decl.IDstart = IDstart;
    decl.IDend = IDend;
    return decl;
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public VariableDecl() {
    super();

    setChild(new List(), 0);
    setChild(new Opt(), 1);

  }
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  public VariableDecl(String p0, List<Dims> p1, Opt<Expr> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @ast method 
   * @declaredat java.ast:14
   */
  public VariableDecl(beaver.Symbol p0, List<Dims> p1, Opt<Expr> p2) {
    setID(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:22
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:28
   */
  public boolean mayHaveRewrite() {
    return false;
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
   * Setter for DimsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setDimsList(List<Dims> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in DimsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumDims() {
    return getDimsList().getNumChild();
  }
  /**
   * Getter for child in list DimsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Dims getDims(int i) {
    return (Dims)getDimsList().getChild(i);
  }
  /**
   * Add element to list DimsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addDims(Dims node) {
    List<Dims> list = (parent == null || state == null) ? getDimsListNoTransform() : getDimsList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addDimsNoTransform(Dims node) {
    List<Dims> list = getDimsListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list DimsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setDims(Dims node, int i) {
    List<Dims> list = getDimsList();
    list.setChild(node, i);
  }
  /**
   * Getter for Dims list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<Dims> getDimss() {
    return getDimsList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<Dims> getDimssNoTransform() {
    return getDimsListNoTransform();
  }
  /**
   * Getter for list DimsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Dims> getDimsList() {
    List<Dims> list = (List<Dims>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Dims> getDimsListNoTransform() {
    return (List<Dims>)getChildNoTransform(0);
  }
  /**
   * Setter for InitOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setInitOpt(Opt<Expr> opt) {
    setChild(opt, 1);
  }
  /**
   * Does this node have a Init child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasInit() {
    return getInitOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr getInit() {
    return (Expr)getInitOpt().getChild(0);
  }
  /**
   * Setter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setInit(Expr node) {
    getInitOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOpt() {
    return (Opt<Expr>)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOptNoTransform() {
    return (Opt<Expr>)getChildNoTransform(1);
  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:110
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:41
   * @apilvl internal
   */
  public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
    if(caller == getInitOptNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_isSource(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:145
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_expectedType(ASTNode caller, ASTNode child) {
    if(caller == getInitOptNoTransform()) {
      return null;
    }
    return getParent().Define_TypeDecl_expectedType(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
