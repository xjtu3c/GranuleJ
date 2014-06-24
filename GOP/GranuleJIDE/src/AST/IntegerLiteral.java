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
 * Default Java integer literal. Should only be used for numbers
 * that can be stored in 32 bits binary.
 * @ast node
 * @declaredat Literals.ast:34
 */
public class IntegerLiteral extends Literal implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    constant_computed = false;
    constant_value = null;
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
  public IntegerLiteral clone() throws CloneNotSupportedException {
    IntegerLiteral node = (IntegerLiteral)super.clone();
    node.constant_computed = false;
    node.constant_value = null;
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
  public IntegerLiteral copy() {
      try {
        IntegerLiteral node = (IntegerLiteral)clone();
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
  public IntegerLiteral fullCopy() {
    IntegerLiteral res = (IntegerLiteral)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect NodeConstructors
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NodeConstructors.jrag:48
   */
  public IntegerLiteral(int i) {
    this(Integer.toString(i));
  }
  /**
   * @ast method 
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:569
   */
  public void typeCheck() {
   if(constant().error)
     error("The value of an int literal must be a decimal value in the range -2147483648..2147483647 or a hexadecimal or octal literal that fits in 32 bits.");

 }
  /*************************************************************
   * Emit methods
   ************************************************************* @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:457
   */
  public static void push(CodeGeneration gen, int value) {
    switch(value) {
      case -1:
        gen.emit(Bytecode.ICONST_M1);
        break;
      case 0:
        gen.emit(Bytecode.ICONST_0);
        break;
      case 1:
        gen.emit(Bytecode.ICONST_1);
        break;
      case 2:
        gen.emit(Bytecode.ICONST_2);
        break;
      case 3:
        gen.emit(Bytecode.ICONST_3);
        break;
      case 4:
        gen.emit(Bytecode.ICONST_4);
        break;
      case 5:
        gen.emit(Bytecode.ICONST_5);
        break;
      default:
        if(value >= -128 && value <= 127) {
          gen.emit(Bytecode.BIPUSH).add(value);
        }
        else if(value >= -32768 && value <= 32767) {
          gen.emit(Bytecode.SIPUSH).add2(value);
        }
        else {
          int index = gen.constantPool().addConstant(value);
          if(index < 256)
            gen.emit(Bytecode.LDC).add(index);
          else 
            gen.emit(Bytecode.LDC_W).add2(index);
        }
    }
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:555
   */
  public void emitPushConstant(CodeGeneration gen) {
    type().emitPushConstant(gen, constant().intValue());
  }
  /**
   * @ast method 
   * @declaredat Literals.ast:1
   */
  public IntegerLiteral() {
    super();


  }
  /**
   * @ast method 
   * @declaredat Literals.ast:7
   */
  public IntegerLiteral(String p0) {
    setLITERAL(p0);
  }
  /**
   * @ast method 
   * @declaredat Literals.ast:10
   */
  public IntegerLiteral(beaver.Symbol p0) {
    setLITERAL(p0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Literals.ast:16
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Literals.ast:22
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for lexeme LITERAL
   * @apilvl high-level
   * @ast method 
   * @declaredat Literals.ast:5
   */
  public void setLITERAL(String value) {
    tokenString_LITERAL = value;
  }
  /**
   * @ast method 
   * @declaredat Literals.ast:8
   */
  public void setLITERAL(beaver.Symbol symbol) {
    if(symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setLITERAL is only valid for String lexemes");
    tokenString_LITERAL = (String)symbol.value;
    LITERALstart = symbol.getStart();
    LITERALend = symbol.getEnd();
  }
  /**
   * Getter for lexeme LITERAL
   * @apilvl high-level
   * @ast method 
   * @declaredat Literals.ast:19
   */
  public String getLITERAL() {
    return tokenString_LITERAL != null ? tokenString_LITERAL : "";
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:233
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isHex() {
      ASTNode$State state = state();
    boolean isHex_value = isHex_compute();
    return isHex_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isHex_compute() {  return getLITERAL().toLowerCase().startsWith("0x");  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:234
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isOctal() {
      ASTNode$State state = state();
    boolean isOctal_value = isOctal_compute();
    return isOctal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isOctal_compute() {  return getLITERAL().startsWith("0");  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:235
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDecimal() {
      ASTNode$State state = state();
    boolean isDecimal_value = isDecimal_compute();
    return isDecimal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDecimal_compute() {  return !isHex() && !isOctal();  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:242
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPositive() {
      ASTNode$State state = state();
    boolean isPositive_value = isPositive_compute();
    return isPositive_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPositive_compute() {  return !getLITERAL().startsWith("-");  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:255
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant constant() {
    if(constant_computed) {
      return constant_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    constant_value = constant_compute();
if(isFinal && num == state().boundariesCrossed) constant_computed = true;
    return constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant constant_compute() {
    long l = 0;
    try {
      l = Literal.parseLong(getLITERAL());
    } catch (NumberFormatException e) {
      Constant c = Constant.create(0L);
      c.error = true;
      return c;
    }
    Constant c = Constant.create((int)l);
    if(isDecimal() && l != (int)l)
      c.error = true;
    if(isOctal() && l > 037777777777L)
      c.error = true;
    if(isHex() && l > 0xffffffffL)
      c.error = true;
    return c;
  }
  /**
   * @apilvl internal
   */
  protected boolean type_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl type_value;
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:399
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
  private TypeDecl type_compute() {  return typeInt();  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
