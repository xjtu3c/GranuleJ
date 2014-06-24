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
 * @declaredat java.ast:160
 */
public abstract class Binary extends Expr implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    isConstant_visited = -1;
    isConstant_computed = false;
    isConstant_initialized = false;
    isDAafterTrue_Variable_values = null;
    isDAafterFalse_Variable_values = null;
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    isDUbefore_Variable_values = null;
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
  public Binary clone() throws CloneNotSupportedException {
    Binary node = (Binary)super.clone();
    node.isConstant_visited = -1;
    node.isConstant_computed = false;
    node.isConstant_initialized = false;
    node.isDAafterTrue_Variable_values = null;
    node.isDAafterFalse_Variable_values = null;
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.isDUbefore_Variable_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:521
   */
  public void toString(StringBuffer s) {
    getLeftOperand().toString(s);
    s.append(printOp());
    getRightOperand().toString(s);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1463
   */
  public void createBCode(CodeGeneration gen) {   
    getLeftOperand().createBCode(gen);
    getLeftOperand().type().emitCastTo(gen, type()); // Binary numeric promotion
    getRightOperand().createBCode(gen);
    getRightOperand().type().emitCastTo(gen, type()); // Binary numeric promotion
    emitOperation(gen);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1471
   */
  public void emitShiftExpr(CodeGeneration gen) {
    getLeftOperand().createBCode(gen);
    getLeftOperand().type().emitCastTo(gen, type()); // Binary numeric promotion
    getRightOperand().createBCode(gen);
    getRightOperand().type().emitCastTo(gen, typeInt());
    emitOperation(gen);
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public Binary() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public Binary(Expr p0, Expr p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:14
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:20
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for LeftOperand
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setLeftOperand(Expr node) {
    setChild(node, 0);
  }
  /**
   * Getter for LeftOperand
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getLeftOperand() {
    return (Expr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getLeftOperandNoTransform() {
    return (Expr)getChildNoTransform(0);
  }
  /**
   * Setter for RightOperand
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setRightOperand(Expr node) {
    setChild(node, 1);
  }
  /**
   * Getter for RightOperand
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getRightOperand() {
    return (Expr)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getRightOperandNoTransform() {
    return (Expr)getChildNoTransform(1);
  }
  /**
   * @ast method 
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:516
   */
  private TypeDecl refined_ConstantExpression_Binary_binaryNumericPromotedType()
{
    TypeDecl leftType = left().type();
    TypeDecl rightType = right().type();
    if(leftType.isString())
      return leftType;
    if(rightType.isString())
      return rightType;
    if(leftType.isNumericType() && rightType.isNumericType())
      return leftType.binaryNumericPromotion(rightType);
    if(leftType.isBoolean() && rightType.isBoolean())
      return leftType;
    return unknownType();
  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:527
   */
  @SuppressWarnings({"unchecked", "cast"})
  public abstract String printOp();
  /**
   * @apilvl internal
   */
  protected int isConstant_visited = -1;
  /**
   * @apilvl internal
   */
  protected boolean isConstant_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isConstant_initialized = false;
  /**
   * @apilvl internal
   */
  protected boolean isConstant_value;
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:491
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstant() {
    if(isConstant_computed) {
      return isConstant_value;
    }
    ASTNode$State state = state();
    if (!isConstant_initialized) {
      isConstant_initialized = true;
      isConstant_value = false;
    }
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
    int num = state.boundariesCrossed;
    boolean isFinal = this.is$Final();
      do {
        isConstant_visited = state.CIRCLE_INDEX;
        state.CHANGE = false;
        boolean new_isConstant_value = isConstant_compute();
        if (new_isConstant_value!=isConstant_value)
          state.CHANGE = true;
        isConstant_value = new_isConstant_value; 
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
      isConstant_computed = true;
      }
      else {
      state.RESET_CYCLE = true;
      isConstant_compute();
      state.RESET_CYCLE = false;
        isConstant_computed = false;
        isConstant_initialized = false;
      }
      state.IN_CIRCLE = false; 
      return isConstant_value;
    }
    if(isConstant_visited != state.CIRCLE_INDEX) {
      isConstant_visited = state.CIRCLE_INDEX;
      if (state.RESET_CYCLE) {
        isConstant_computed = false;
        isConstant_initialized = false;
        isConstant_visited = -1;
        return isConstant_value;
      }
      boolean new_isConstant_value = isConstant_compute();
      if (new_isConstant_value!=isConstant_value)
        state.CHANGE = true;
      isConstant_value = new_isConstant_value; 
      return isConstant_value;
    }
    return isConstant_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstant_compute() {  return getLeftOperand().isConstant() && getRightOperand().isConstant();  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:514
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr left() {
      ASTNode$State state = state();
    Expr left_value = left_compute();
    return left_value;
  }
  /**
   * @apilvl internal
   */
  private Expr left_compute() {  return getLeftOperand();  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:515
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr right() {
      ASTNode$State state = state();
    Expr right_value = right_compute();
    return right_value;
  }
  /**
   * @apilvl internal
   */
  private Expr right_compute() {  return getRightOperand();  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:205
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl binaryNumericPromotedType() {
      ASTNode$State state = state();
    TypeDecl binaryNumericPromotedType_value = binaryNumericPromotedType_compute();
    return binaryNumericPromotedType_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl binaryNumericPromotedType_compute() {
    TypeDecl leftType = left().type();
    TypeDecl rightType = right().type();
    if(leftType.isBoolean() && rightType.isBoolean()) {
      return leftType.isReferenceType() ? leftType.unboxed() : leftType;
    }
    return refined_ConstantExpression_Binary_binaryNumericPromotedType();
  }
  protected java.util.Map isDAafterTrue_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:405
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafterTrue(Variable v) {
    Object _parameters = v;
    if(isDAafterTrue_Variable_values == null) isDAafterTrue_Variable_values = new java.util.HashMap(4);
    if(isDAafterTrue_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDAafterTrue_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDAafterTrue_Variable_value = isDAafterTrue_compute(v);
if(isFinal && num == state().boundariesCrossed) isDAafterTrue_Variable_values.put(_parameters, Boolean.valueOf(isDAafterTrue_Variable_value));
    return isDAafterTrue_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafterTrue_compute(Variable v) {  return getRightOperand().isDAafter(v) || isFalse();  }
  protected java.util.Map isDAafterFalse_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:406
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafterFalse(Variable v) {
    Object _parameters = v;
    if(isDAafterFalse_Variable_values == null) isDAafterFalse_Variable_values = new java.util.HashMap(4);
    if(isDAafterFalse_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDAafterFalse_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDAafterFalse_Variable_value = isDAafterFalse_compute(v);
if(isFinal && num == state().boundariesCrossed) isDAafterFalse_Variable_values.put(_parameters, Boolean.valueOf(isDAafterFalse_Variable_value));
    return isDAafterFalse_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafterFalse_compute(Variable v) {  return getRightOperand().isDAafter(v) || isTrue();  }
  protected java.util.Map isDAafter_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:408
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
  private boolean isDAafter_compute(Variable v) {  return getRightOperand().isDAafter(v);  }
  protected java.util.Map isDUafter_Variable_values;
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:849
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
  private boolean isDUafter_compute(Variable v) {  return getRightOperand().isDUafter(v);  }
  protected java.util.Map isDUbefore_Variable_values;
  /**
   * @attribute inh
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:697
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUbefore(Variable v) {
    Object _parameters = v;
    if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
    if(isDUbefore_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDUbefore_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
if(isFinal && num == state().boundariesCrossed) isDUbefore_Variable_values.put(_parameters, Boolean.valueOf(isDUbefore_Variable_value));
    return isDUbefore_Variable_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:409
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getRightOperandNoTransform()) {
      return getLeftOperand().isDAafter(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:850
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getRightOperandNoTransform()) {
      return getLeftOperand().isDUafter(v);
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
