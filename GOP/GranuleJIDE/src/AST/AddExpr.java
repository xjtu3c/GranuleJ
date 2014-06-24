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
 * @declaredat java.ast:168
 */
public class AddExpr extends AdditiveExpr implements Cloneable {
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
  public AddExpr clone() throws CloneNotSupportedException {
    AddExpr node = (AddExpr)super.clone();
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
  public AddExpr copy() {
      try {
        AddExpr node = (AddExpr)clone();
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
  public AddExpr fullCopy() {
    AddExpr res = (AddExpr)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:170
   */
  public void typeCheck() {
    TypeDecl left = getLeftOperand().type();
    TypeDecl right = getRightOperand().type();
    if(!left.isString() && !right.isString())
      super.typeCheck();
    else if(left.isVoid())
      error("The type void of the left hand side is not numeric");
    else if(right.isVoid())
      error("The type void of the right hand side is not numeric");
  }
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1136
   */
  void emitOperation(CodeGeneration gen) { type().add(gen); }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1482
   */
  public void createBCode(CodeGeneration gen) {
    if(!type().isString())
      super.createBCode(gen);
    else if(isConstant()) {
      StringLiteral.push(gen, constant().stringValue());
    }
    else {
      TypeDecl stringBuffer = lookupType("java.lang", "StringBuffer");
      String classname = stringBuffer.constantPoolName();
      String desc;
      int index;
      TypeDecl argumentType;
      if(firstStringAddPart()) {
        stringBuffer.emitNew(gen); // new StringBuffer
        gen.emitDup();             // dup
        desc = "()V";
        index = gen.constantPool().addMethodref(classname, "<init>", desc);
        gen.emit(Bytecode.INVOKESPECIAL, -1).add2(index); // invokespecial StringBuffer()
        getLeftOperand().createBCode(gen); // left
        argumentType = getLeftOperand().type().stringPromotion();
        desc = "(" + argumentType.typeDescriptor() + ")" + stringBuffer.typeDescriptor();
        index = gen.constantPool().addMethodref(classname, "append", desc);
        gen.emit(Bytecode.INVOKEVIRTUAL, -argumentType.variableSize()).add2(index); // StringBuffer.append
      }
      else {
        getLeftOperand().createBCode(gen);
      }
      getRightOperand().createBCode(gen); // right
      argumentType = getRightOperand().type().stringPromotion();
      desc = "(" + argumentType.typeDescriptor() + ")" + stringBuffer.typeDescriptor();
      index = gen.constantPool().addMethodref(classname, "append", desc);
      gen.emit(Bytecode.INVOKEVIRTUAL, -argumentType.variableSize()).add2(index); // StringBuffer.append
      if(lastStringAddPart()) {
        desc = "()" + type().typeDescriptor();
        index = gen.constantPool().addMethodref(classname, "toString", desc);
        gen.emit(Bytecode.INVOKEVIRTUAL, 0).add2(index); // StringBuffer.toString
      }
    }
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public AddExpr() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public AddExpr(Expr p0, Expr p1) {
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
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:121
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant constant() {
      ASTNode$State state = state();
    Constant constant_value = constant_compute();
    return constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant constant_compute() {  return type().add(getLeftOperand().constant(), getRightOperand().constant());  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:531
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String printOp() {
      ASTNode$State state = state();
    String printOp_value = printOp_compute();
    return printOp_value;
  }
  /**
   * @apilvl internal
   */
  private String printOp_compute() {  return " + ";  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:429
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
    TypeDecl left = getLeftOperand().type();
    TypeDecl right = getRightOperand().type();
    if(!left.isString() && !right.isString())
      return super.type();
    else {
      if(left.isVoid() || right.isVoid())
        return unknownType();
      // pick the string type
      return left.isString() ? left : right;
    }
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:165
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isStringAdd() {
      ASTNode$State state = state();
    boolean isStringAdd_value = isStringAdd_compute();
    return isStringAdd_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isStringAdd_compute() {  return type().isString() && !isConstant();  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:167
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean firstStringAddPart() {
      ASTNode$State state = state();
    boolean firstStringAddPart_value = firstStringAddPart_compute();
    return firstStringAddPart_value;
  }
  /**
   * @apilvl internal
   */
  private boolean firstStringAddPart_compute() {  return type().isString() && !getLeftOperand().isStringAdd();  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:168
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean lastStringAddPart() {
      ASTNode$State state = state();
    boolean lastStringAddPart_value = lastStringAddPart_compute();
    return lastStringAddPart_value;
  }
  /**
   * @apilvl internal
   */
  private boolean lastStringAddPart_compute() {  return !getParent().isStringAdd();  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
