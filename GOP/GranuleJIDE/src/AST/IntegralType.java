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
 * @declaredat java.ast:53
 */
public abstract class IntegralType extends NumericType implements Cloneable {
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
  public IntegralType clone() throws CloneNotSupportedException {
    IntegralType node = (IntegralType)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @aspect Attributes
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Attributes.jrag:77
   */
  public int addConstant(ConstantPool p, Constant c) { return p.addConstant(c.intValue()); }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:544
   */
  public void emitPushConstant(CodeGeneration gen, int value) { IntegerLiteral.push(gen, value); }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1102
   */
  void byteToThis(CodeGeneration gen) { }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1109
   */
  void charToThis(CodeGeneration gen) { }
  /**
   * @ast method 
   * @aspect CodeGenerationConversions
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1117
   */
  void shortToThis(CodeGeneration gen) { }
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1150
   */
  void neg(CodeGeneration gen) { gen.emit(Bytecode.INEG); }
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1156
   */
  void bitNot(CodeGeneration gen) { gen.emit(Bytecode.ICONST_M1).emit(Bytecode.IXOR); }
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1166
   */
  void add(CodeGeneration gen) {gen.emit(Bytecode.IADD);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1172
   */
  void sub(CodeGeneration gen) {gen.emit(Bytecode.ISUB);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1178
   */
  void mul(CodeGeneration gen) {gen.emit(Bytecode.IMUL);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1184
   */
  void div(CodeGeneration gen) {gen.emit(Bytecode.IDIV);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1190
   */
  void rem(CodeGeneration gen) {gen.emit(Bytecode.IREM);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1194
   */
  void shl(CodeGeneration gen) {gen.emit(Bytecode.ISHL);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1198
   */
  void shr(CodeGeneration gen) {gen.emit(Bytecode.ISHR);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1202
   */
  void ushr(CodeGeneration gen) {gen.emit(Bytecode.IUSHR);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1206
   */
  void bitand(CodeGeneration gen) {gen.emit(Bytecode.IAND);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1211
   */
  void bitor(CodeGeneration gen) {gen.emit(Bytecode.IOR);}
  /**
   * @ast method 
   * @aspect CodeGenerationBinaryOperations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1216
   */
  void bitxor(CodeGeneration gen) {gen.emit(Bytecode.IXOR);}
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1225
   */
  public void branchLT(CodeGeneration gen, int label) { gen.emitCompare(Bytecode.IF_ICMPLT, label); }
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1231
   */
  public void branchLE(CodeGeneration gen, int label) { gen.emitCompare(Bytecode.IF_ICMPLE, label); }
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1237
   */
  public void branchGE(CodeGeneration gen, int label) { gen.emitCompare(Bytecode.IF_ICMPGE, label); }
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1243
   */
  public void branchGT(CodeGeneration gen, int label) { gen.emitCompare(Bytecode.IF_ICMPGT, label); }
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1249
   */
  public void branchEQ(CodeGeneration gen, int label)  { gen.emitCompare(Bytecode.IF_ICMPEQ, label); }
  /**
   * @ast method 
   * @aspect CodeGenerationBranch
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:1258
   */
  public void branchNE(CodeGeneration gen, int label)  { gen.emitCompare(Bytecode.IF_ICMPNE, label); }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:180
   */
  public int addAnnotConstant(ConstantPool p, Constant c) {
    return addConstant(p, c);
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public IntegralType() {
    super();

    setChild(new Opt(), 1);
    setChild(new List(), 2);

  }
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  public IntegralType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @ast method 
   * @declaredat java.ast:15
   */
  public IntegralType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:24
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:30
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
   * Setter for SuperClassAccessOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setSuperClassAccessOpt(Opt<Access> opt) {
    setChild(opt, 1);
  }
  /**
   * Does this node have a SuperClassAccess child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasSuperClassAccess() {
    return getSuperClassAccessOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child SuperClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getSuperClassAccess() {
    return (Access)getSuperClassAccessOpt().getChild(0);
  }
  /**
   * Setter for optional child SuperClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setSuperClassAccess(Access node) {
    getSuperClassAccessOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Access> getSuperClassAccessOpt() {
    return (Opt<Access>)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Access> getSuperClassAccessOptNoTransform() {
    return (Opt<Access>)getChildNoTransform(1);
  }
  /**
   * Setter for BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 2);
  }
  /**
   * @return number of children in BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Getter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl)getBodyDeclList().getChild(i);
  }
  /**
   * Add element to list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null || state == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for BodyDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * Getter for list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclList() {
    List<BodyDecl> list = (List<BodyDecl>)getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>)getChildNoTransform(2);
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:310
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant cast(Constant c) {
      ASTNode$State state = state();
    Constant cast_Constant_value = cast_compute(c);
    return cast_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant cast_compute(Constant c) {  return Constant.create(c.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:324
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant plus(Constant c) {
      ASTNode$State state = state();
    Constant plus_Constant_value = plus_compute(c);
    return plus_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant plus_compute(Constant c) {  return c;  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:333
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant minus(Constant c) {
      ASTNode$State state = state();
    Constant minus_Constant_value = minus_compute(c);
    return minus_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant minus_compute(Constant c) {  return Constant.create(-c.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:342
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant bitNot(Constant c) {
      ASTNode$State state = state();
    Constant bitNot_Constant_value = bitNot_compute(c);
    return bitNot_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant bitNot_compute(Constant c) {  return Constant.create(~c.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:349
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant mul(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant mul_Constant_Constant_value = mul_compute(c1, c2);
    return mul_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant mul_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() * c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:358
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant div(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant div_Constant_Constant_value = div_compute(c1, c2);
    return div_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant div_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() / c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:367
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant mod(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant mod_Constant_Constant_value = mod_compute(c1, c2);
    return mod_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant mod_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() % c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:376
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant add(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant add_Constant_Constant_value = add_compute(c1, c2);
    return add_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant add_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() + c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:386
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant sub(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant sub_Constant_Constant_value = sub_compute(c1, c2);
    return sub_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant sub_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() - c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:395
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant lshift(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant lshift_Constant_Constant_value = lshift_compute(c1, c2);
    return lshift_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant lshift_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() << c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:402
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant rshift(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant rshift_Constant_Constant_value = rshift_compute(c1, c2);
    return rshift_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant rshift_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() >> c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:409
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant urshift(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant urshift_Constant_Constant_value = urshift_compute(c1, c2);
    return urshift_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant urshift_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() >>> c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:416
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant andBitwise(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant andBitwise_Constant_Constant_value = andBitwise_compute(c1, c2);
    return andBitwise_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant andBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() & c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:424
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant xorBitwise(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant xorBitwise_Constant_Constant_value = xorBitwise_compute(c1, c2);
    return xorBitwise_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant xorBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() ^ c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:432
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant orBitwise(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant orBitwise_Constant_Constant_value = orBitwise_compute(c1, c2);
    return orBitwise_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant orBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.intValue() | c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:440
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant questionColon(Constant cond, Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant questionColon_Constant_Constant_Constant_value = questionColon_compute(cond, c1, c2);
    return questionColon_Constant_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant questionColon_compute(Constant cond, Constant c1, Constant c2) {  return Constant.create(cond.booleanValue() ? c1.intValue() : c2.intValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:544
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean eqIsTrue(Expr left, Expr right) {
      ASTNode$State state = state();
    boolean eqIsTrue_Expr_Expr_value = eqIsTrue_compute(left, right);
    return eqIsTrue_Expr_Expr_value;
  }
  /**
   * @apilvl internal
   */
  private boolean eqIsTrue_compute(Expr left, Expr right) {  return left.constant().intValue() == right.constant().intValue();  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:552
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean ltIsTrue(Expr left, Expr right) {
      ASTNode$State state = state();
    boolean ltIsTrue_Expr_Expr_value = ltIsTrue_compute(left, right);
    return ltIsTrue_Expr_Expr_value;
  }
  /**
   * @apilvl internal
   */
  private boolean ltIsTrue_compute(Expr left, Expr right) {  return left.constant().intValue() < right.constant().intValue();  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:558
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean leIsTrue(Expr left, Expr right) {
      ASTNode$State state = state();
    boolean leIsTrue_Expr_Expr_value = leIsTrue_compute(left, right);
    return leIsTrue_Expr_Expr_value;
  }
  /**
   * @apilvl internal
   */
  private boolean leIsTrue_compute(Expr left, Expr right) {  return left.constant().intValue() <= right.constant().intValue();  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:532
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean assignableToInt() {
      ASTNode$State state = state();
    boolean assignableToInt_value = assignableToInt_compute();
    return assignableToInt_value;
  }
  /**
   * @apilvl internal
   */
  private boolean assignableToInt_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:178
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isIntegralType() {
      ASTNode$State state = state();
    boolean isIntegralType_value = isIntegralType_compute();
    return isIntegralType_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isIntegralType_compute() {  return true;  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
