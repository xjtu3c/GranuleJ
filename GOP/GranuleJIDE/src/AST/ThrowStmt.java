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
 * @declaredat java.ast:227
 */
public class ThrowStmt extends Stmt implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    canCompleteNormally_computed = false;
    typeNullPointerException_computed = false;
    typeNullPointerException_value = null;
    handlesException_TypeDecl_values = null;
    typeThrowable_computed = false;
    typeThrowable_value = null;
    typeNull_computed = false;
    typeNull_value = null;
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
  public ThrowStmt clone() throws CloneNotSupportedException {
    ThrowStmt node = (ThrowStmt)super.clone();
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.canCompleteNormally_computed = false;
    node.typeNullPointerException_computed = false;
    node.typeNullPointerException_value = null;
    node.handlesException_TypeDecl_values = null;
    node.typeThrowable_computed = false;
    node.typeThrowable_value = null;
    node.typeNull_computed = false;
    node.typeNull_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ThrowStmt copy() {
      try {
        ThrowStmt node = (ThrowStmt)clone();
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
  public ThrowStmt fullCopy() {
    ThrowStmt res = (ThrowStmt)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect AnonymousClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AnonymousClasses.jrag:140
   */
  protected void collectExceptions(Collection c, ASTNode target) {
    super.collectExceptions(c, target);
    TypeDecl exceptionType = getExpr().type();
    if(exceptionType == typeNull())
      exceptionType = typeNullPointerException();
    c.add(exceptionType);
  }
  /**
   * @ast method 
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:113
   */
  public void exceptionHandling() {
    TypeDecl exceptionType = getExpr().type();
    if(exceptionType == typeNull())
      exceptionType = typeNullPointerException();
    // 8.4.4
    if(!handlesException(exceptionType))
      error("" + this + " throws uncaught exception " + exceptionType.fullName());
  }
  /**
   * @ast method 
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:250
   */
  protected boolean reachedException(TypeDecl catchType) {
    TypeDecl exceptionType = getExpr().type();
    if(exceptionType == typeNull())
      exceptionType = typeNullPointerException();
    if(catchType.mayCatch(exceptionType))
      return true;
    return super.reachedException(catchType);
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:822
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("throw ");
    getExpr().toString(s);
    s.append(";");
  }
  /**
   * @ast method 
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:371
   */
  public void typeCheck() {
    if(!getExpr().type().instanceOf(typeThrowable()))
      error("*** The thrown expression must extend Throwable");
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2056
   */
  public void createBCode(CodeGeneration gen) {
    super.createBCode(gen);
    getExpr().createBCode(gen);
    gen.emitThrow();
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ThrowStmt() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public ThrowStmt(Expr p0) {
    setChild(p0, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:13
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:19
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setExpr(Expr node) {
    setChild(node, 0);
  }
  /**
   * Getter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getExpr() {
    return (Expr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getExprNoTransform() {
    return (Expr)getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:651
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafter(Variable v) {
    Object _parameters = v;
    if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
    if(isDAafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDAafter_Variable_value = isDAafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
    return isDAafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafter_compute(Variable v) {  return true;  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1177
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafter(Variable v) {
    Object _parameters = v;
    if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
    if(isDUafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDUafter_Variable_value = isDUafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
    return isDUafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafter_compute(Variable v) {  return true;  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:111
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean canCompleteNormally() {
    if(canCompleteNormally_computed) {
      return canCompleteNormally_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    canCompleteNormally_value = canCompleteNormally_compute();
if(isFinal && num == state().boundariesCrossed) canCompleteNormally_computed = true;
    return canCompleteNormally_value;
  }
  /**
   * @apilvl internal
   */
  private boolean canCompleteNormally_compute() {  return false;  }
  /**
   * @apilvl internal
   */
  protected boolean typeNullPointerException_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeNullPointerException_value;
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:20
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeNullPointerException() {
    if(typeNullPointerException_computed) {
      return typeNullPointerException_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeNullPointerException_value = getParent().Define_TypeDecl_typeNullPointerException(this, null);
if(isFinal && num == state().boundariesCrossed) typeNullPointerException_computed = true;
    return typeNullPointerException_value;
  }
  protected java.util.Map handlesException_TypeDecl_values;
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:31
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean handlesException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if(handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
    if(handlesException_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)handlesException_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
if(isFinal && num == state().boundariesCrossed) handlesException_TypeDecl_values.put(_parameters, Boolean.valueOf(handlesException_TypeDecl_value));
    return handlesException_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  protected boolean typeThrowable_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeThrowable_value;
  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:68
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeThrowable() {
    if(typeThrowable_computed) {
      return typeThrowable_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeThrowable_value = getParent().Define_TypeDecl_typeThrowable(this, null);
if(isFinal && num == state().boundariesCrossed) typeThrowable_computed = true;
    return typeThrowable_value;
  }
  /**
   * @apilvl internal
   */
  protected boolean typeNull_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeNull_value;
  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:71
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeNull() {
    if(typeNull_computed) {
      return typeNull_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeNull_value = getParent().Define_TypeDecl_typeNull(this, null);
if(isFinal && num == state().boundariesCrossed) typeNull_computed = true;
    return typeNull_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:654
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getExprNoTransform()) {
      return isDAbefore(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1180
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getExprNoTransform()) {
      return isDUbefore(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
