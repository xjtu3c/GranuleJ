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
 * The abstract base class for all literals.
 * @ast node
 * @declaredat Literals.ast:4
 */
public abstract class Literal extends PrimaryExpr implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    constant_computed = false;
    constant_value = null;
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
  public Literal clone() throws CloneNotSupportedException {
    Literal node = (Literal)super.clone();
    node.constant_computed = false;
    node.constant_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @aspect BytecodeCONSTANT
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BytecodeCONSTANT.jrag:58
   */
  public static Literal buildDoubleLiteral(double value) {
    return new DoubleLiteral(Double.toString(value));
  }
  /**
   * @ast method 
   * @aspect BytecodeCONSTANT
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BytecodeCONSTANT.jrag:62
   */
  public static Literal buildFloatLiteral(float value) {
    return new FloatingPointLiteral(Double.toString(value));
  }
  /**
   * @ast method 
   * @aspect BytecodeCONSTANT
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BytecodeCONSTANT.jrag:66
   */
  public static Literal buildIntegerLiteral(int value) {
    return new IntegerLiteral("0x"+Integer.toHexString(value));
  }
  /**
   * @ast method 
   * @aspect BytecodeCONSTANT
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BytecodeCONSTANT.jrag:70
   */
  public static Literal buildLongLiteral(long value) {
    return new LongLiteral("0x"+Long.toHexString(value));
  }
  /**
   * @ast method 
   * @aspect BytecodeCONSTANT
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BytecodeCONSTANT.jrag:74
   */
  public static Literal buildBooleanLiteral(boolean value) {
    return new BooleanLiteral(value ? "true" : "false");
  }
  /**
   * @ast method 
   * @aspect BytecodeCONSTANT
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BytecodeCONSTANT.jrag:78
   */
  public static Literal buildStringLiteral(String value) {
    return new StringLiteral(value);
  }
  /**
   * @ast method 
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:160
   */
  static long parseLong(String s) {
    long x = 0L;
    s = s.toLowerCase();
    boolean neg = false;
    if(s.startsWith("-")) {
      s = s.substring(1);
      neg = true;
    }
    if(s.startsWith("0x")) {
      s = s.substring(2);
      if(s.length() > 16) {
        for(int i = 0; i < s.length()-16; i++)
          if(s.charAt(i) != '0')
            throw new NumberFormatException("");
      }
      for (int i = 0; i < s.length(); i++) {
        int c = s.charAt(i);
        if (c >= 'a' && c <= 'f')
          c = c - 'a' + 10;
        else if(c >= '0' && c <= '9')
          c = c - '0';
        else
          throw new NumberFormatException("");
        x = x * 16 + c;
      }
    }
    else if(s.startsWith("0")) {
      s = s.substring(1);
      // Octals larger than 01777777777777777777777L are not valid
      if(s.length() > 21) {
        for(int i = 0; i < s.length() - 21; i++)
          if(i == s.length() - 21 - 1) {
            if(s.charAt(i) != '0' && s.charAt(i) != '1')
              throw new NumberFormatException("");
          }
          else {
            if(s.charAt(i) != '0')
              throw new NumberFormatException("");
          }
      }
      for (int i = 0; i < s.length(); i++) {
        int c = s.charAt(i);
        if(c >= '0' && c <= '7')
          c = c - '0';
        else
          throw new NumberFormatException("");
        x = x * 8 + c;
      }
    }
    else {
      long oldx = 0;
      for (int i = 0; i < s.length(); i++) {
        int c = s.charAt(i);
        if(c >= '0' && c <= '9')
          c = c - '0';
        else
          throw new NumberFormatException("");
        x = x * 10 + c;
        if(x < oldx) {
          boolean negMinValue = i == (s.length()-1) && neg && x == Long.MIN_VALUE;
          if(!negMinValue)
            throw new NumberFormatException("");
        }
        oldx = x;
      }
      if(x == Long.MIN_VALUE)
        return x;
      if(x < 0)
        throw new NumberFormatException("");
    }
    return neg ? -x : x;
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:370
   */
  public void toString(StringBuffer s) {
    s.append(getLITERAL());
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:397
   */
  protected static String escape(String s) {
    StringBuffer result = new StringBuffer();
    for (int i=0; i < s.length(); i++) {
      switch(s.charAt(i)) {
        case '\b' : result.append("\\b"); break;
        case '\t' : result.append("\\t"); break;
        case '\n' : result.append("\\n"); break;
        case '\f' : result.append("\\f"); break;
        case '\r' : result.append("\\r"); break;
        case '\"' : result.append("\\\""); break;
        case '\'' : result.append("\\\'"); break;
        case '\\' : result.append("\\\\"); break;
        default:
          int value = (int)s.charAt(i);
          if(value < 0x20 || (value > 0x7e))
            result.append(asEscape(value));
          else
            result.append(s.charAt(i));
      }
    }
    return result.toString();
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:419
   */
  protected static String asEscape(int value) {
    StringBuffer s = new StringBuffer("\\u");
    String hex = Integer.toHexString(value);
    for(int i = 0; i < 4-hex.length(); i++)
      s.append("0");
    s.append(hex);
    return s.toString();
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:551
   */
  public void emitPushConstant(CodeGeneration gen) {
    System.out.println("ERROR: Tried to generate bytecode for: " + getClass().getName());	
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:469
   */
  public void createBCode(CodeGeneration gen) {      
    emitPushConstant(gen);
  }
  /**
   * @ast method 
   * @declaredat Literals.ast:1
   */
  public Literal() {
    super();


  }
  /**
   * @ast method 
   * @declaredat Literals.ast:7
   */
  public Literal(String p0) {
    setLITERAL(p0);
  }
  /**
   * @ast method 
   * @declaredat Literals.ast:10
   */
  public Literal(beaver.Symbol p0) {
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
  /**   * @apilvl internal   * @ast method 
   * @declaredat Literals.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_LITERAL;
  /**
   * @ast method 
   * @declaredat Literals.ast:9
   */
  
  public int LITERALstart;
  /**
   * @ast method 
   * @declaredat Literals.ast:10
   */
  
  public int LITERALend;
  /**
   * @ast method 
   * @declaredat Literals.ast:11
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
   * @declaredat Literals.ast:22
   */
  public String getLITERAL() {
    return tokenString_LITERAL != null ? tokenString_LITERAL : "";
  }
  /**
   * @apilvl internal
   */
  protected boolean constant_computed = false;
  /**
   * @apilvl internal
   */
  protected Constant constant_value;
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:103
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
    throw new UnsupportedOperationException("ConstantExpression operation constant" +
      " not supported for type " + getClass().getName()); 
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:483
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstant() {
      ASTNode$State state = state();
    boolean isConstant_value = isConstant_compute();
    return isConstant_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstant_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:946
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String dumpString() {
      ASTNode$State state = state();
    String dumpString_value = dumpString_compute();
    return dumpString_value;
  }
  /**
   * @apilvl internal
   */
  private String dumpString_compute() {  return getClass().getName() + " [" + getLITERAL() + "]";  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
