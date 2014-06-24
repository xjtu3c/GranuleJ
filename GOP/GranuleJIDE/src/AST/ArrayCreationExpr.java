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
 * @declaredat java.ast:143
 */
public class ArrayCreationExpr extends PrimaryExpr implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    type_computed = false;
    type_value = null;
    numArrays_computed = false;
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
  public ArrayCreationExpr clone() throws CloneNotSupportedException {
    ArrayCreationExpr node = (ArrayCreationExpr)super.clone();
    node.type_computed = false;
    node.type_value = null;
    node.numArrays_computed = false;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ArrayCreationExpr copy() {
      try {
        ArrayCreationExpr node = (ArrayCreationExpr)clone();
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
  public ArrayCreationExpr fullCopy() {
    ArrayCreationExpr res = (ArrayCreationExpr)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:463
   */
  public void toString(StringBuffer s) {
    s.append("new ");
    getTypeAccess().toString(s);
    if(hasArrayInit()) {
      getArrayInit().toString(s);
    }
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ArrayCreationExpr() {
    super();

    setChild(new Opt(), 1);

  }
  /**
   * @ast method 
   * @declaredat java.ast:8
   */
  public ArrayCreationExpr(Access p0, Opt<ArrayInit> p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:15
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:21
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 0);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(0);
  }
  /**
   * Setter for ArrayInitOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setArrayInitOpt(Opt<ArrayInit> opt) {
    setChild(opt, 1);
  }
  /**
   * Does this node have a ArrayInit child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasArrayInit() {
    return getArrayInitOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child ArrayInit
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ArrayInit getArrayInit() {
    return (ArrayInit)getArrayInitOpt().getChild(0);
  }
  /**
   * Setter for optional child ArrayInit
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setArrayInit(ArrayInit node) {
    getArrayInitOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<ArrayInit> getArrayInitOpt() {
    return (Opt<ArrayInit>)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<ArrayInit> getArrayInitOptNoTransform() {
    return (Opt<ArrayInit>)getChildNoTransform(1);
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:235
   */
    public void createBCode(CodeGeneration gen) {
    if(hasArrayInit()){
      getArrayInit().createBCode(gen);
    }
    else {
      getTypeAccess().createBCode(gen); // push array sizes
      if(type().componentType().isPrimitive() && !type().componentType().isReferenceType()) {
        gen.emit(Bytecode.NEWARRAY).add(type().componentType().arrayPrimitiveTypeDescriptor());
      }
      else {
        if(numArrays() == 1) {
          String n = type().componentType().arrayTypeDescriptor();
          int index = gen.constantPool().addClass(n);
          gen.emit(Bytecode.ANEWARRAY).add2(index);
        }
        else {
          String n = type().arrayTypeDescriptor();
          int index = gen.constantPool().addClass(n);
          gen.emit(Bytecode.MULTIANEWARRAY, 1 - numArrays()).add2(index).add(numArrays());
        }
      }
    }
  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:433
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafterCreation(Variable v) {
      ASTNode$State state = state();
    boolean isDAafterCreation_Variable_value = isDAafterCreation_compute(v);
    return isDAafterCreation_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafterCreation_compute(Variable v) {  return getTypeAccess().isDAafter(v);  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:434
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafter(Variable v) {
      ASTNode$State state = state();
    boolean isDAafter_Variable_value = isDAafter_compute(v);
    return isDAafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafter_compute(Variable v) {  return hasArrayInit() ? getArrayInit().isDAafter(v) : isDAafterCreation(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:864
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafterCreation(Variable v) {
      ASTNode$State state = state();
    boolean isDUafterCreation_Variable_value = isDUafterCreation_compute(v);
    return isDUafterCreation_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafterCreation_compute(Variable v) {  return getTypeAccess().isDUafter(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:865
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafter(Variable v) {
      ASTNode$State state = state();
    boolean isDUafter_Variable_value = isDUafter_compute(v);
    return isDUafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafter_compute(Variable v) {  return hasArrayInit() ? getArrayInit().isDUafter(v) : isDUafterCreation(v);  }
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:411
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
  private TypeDecl type_compute() {  return getTypeAccess().type();  }
  /**
   * @apilvl internal
   */
  protected boolean numArrays_computed = false;
  /**
   * @apilvl internal
   */
  protected int numArrays_value;
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:148
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int numArrays() {
    if(numArrays_computed) {
      return numArrays_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    numArrays_value = numArrays_compute();
if(isFinal && num == state().boundariesCrossed) numArrays_computed = true;
    return numArrays_value;
  }
  /**
   * @apilvl internal
   */
  private int numArrays_compute() {
    int i = type().dimension();
    Access a = getTypeAccess();
    while(a instanceof ArrayTypeAccess && !(a instanceof ArrayTypeWithSizeAccess)) {
      i--;
      a = ((ArrayTypeAccess)a).getAccess();
    }
    return i;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:435
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getArrayInitOptNoTransform()) {
      return isDAafterCreation(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:867
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getArrayInitOptNoTransform()) {
      return isDUafterCreation(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:87
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getTypeAccessNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:264
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
    if(caller == getArrayInitOptNoTransform()) {
      return type();
    }
    return getParent().Define_TypeDecl_declType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:142
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_expectedType(ASTNode caller, ASTNode child) {
    if(caller == getArrayInitOptNoTransform()) {
      return type().componentType();
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
