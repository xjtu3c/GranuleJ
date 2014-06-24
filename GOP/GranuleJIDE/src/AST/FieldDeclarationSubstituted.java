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
 * @declaredat Generics.ast:29
 */
public class FieldDeclarationSubstituted extends FieldDeclaration implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    sourceVariableDecl_computed = false;
    sourceVariableDecl_value = null;
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
  public FieldDeclarationSubstituted clone() throws CloneNotSupportedException {
    FieldDeclarationSubstituted node = (FieldDeclarationSubstituted)super.clone();
    node.sourceVariableDecl_computed = false;
    node.sourceVariableDecl_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public FieldDeclarationSubstituted copy() {
      try {
        FieldDeclarationSubstituted node = (FieldDeclarationSubstituted)clone();
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
  public FieldDeclarationSubstituted fullCopy() {
    FieldDeclarationSubstituted res = (FieldDeclarationSubstituted)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @declaredat Generics.ast:1
   */
  public FieldDeclarationSubstituted() {
    super();

    setChild(new Opt(), 2);

  }
  /**
   * @ast method 
   * @declaredat Generics.ast:8
   */
  public FieldDeclarationSubstituted(Modifiers p0, Access p1, String p2, Opt<Expr> p3, FieldDeclaration p4) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setOriginal(p4);
  }
  /**
   * @ast method 
   * @declaredat Generics.ast:15
   */
  public FieldDeclarationSubstituted(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3, FieldDeclaration p4) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setOriginal(p4);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Generics.ast:25
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Generics.ast:31
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
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(1);
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
   * Setter for InitOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setInitOpt(Opt<Expr> opt) {
    setChild(opt, 2);
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
    return (Opt<Expr>)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOptNoTransform() {
    return (Opt<Expr>)getChildNoTransform(2);
  }
  /**
   * Setter for lexeme Original
   * @apilvl high-level
   * @ast method 
   * @declaredat Generics.ast:5
   */
  public void setOriginal(FieldDeclaration value) {
    tokenFieldDeclaration_Original = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat Generics.ast:8
   */
  
  /**   * @apilvl internal   */  protected FieldDeclaration tokenFieldDeclaration_Original;
  /**
   * Getter for lexeme Original
   * @apilvl high-level
   * @ast method 
   * @declaredat Generics.ast:13
   */
  public FieldDeclaration getOriginal() {
    return tokenFieldDeclaration_Original;
  }
  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1286
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Variable sourceVariableDecl() {
    if(sourceVariableDecl_computed) {
      return sourceVariableDecl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    sourceVariableDecl_value = sourceVariableDecl_compute();
if(isFinal && num == state().boundariesCrossed) sourceVariableDecl_computed = true;
    return sourceVariableDecl_value;
  }
  /**
   * @apilvl internal
   */
  private Variable sourceVariableDecl_compute() {  return getOriginal().sourceVariableDecl();  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:54
   */
  @SuppressWarnings({"unchecked", "cast"})
  public FieldDeclaration erasedField() {
      ASTNode$State state = state();
    FieldDeclaration erasedField_value = erasedField_compute();
    return erasedField_value;
  }
  /**
   * @apilvl internal
   */
  private FieldDeclaration erasedField_compute() {  return getOriginal().erasedField();  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
