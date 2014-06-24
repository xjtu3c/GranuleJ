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
 * @declaredat java.ast:37
 */
public class ClassAccess extends Access implements Cloneable {
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
  public ClassAccess clone() throws CloneNotSupportedException {
    ClassAccess node = (ClassAccess)super.clone();
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
  public ClassAccess copy() {
      try {
        ClassAccess node = (ClassAccess)clone();
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
  public ClassAccess fullCopy() {
    ClassAccess res = (ClassAccess)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:172
   */
  public void nameCheck() {
    if(isQualified() && !qualifier().isTypeAccess())
      error("class literal may only contain type names");
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:650
   */
  public void toString(StringBuffer s) {
    s.append("class");
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2152
   */
  public void refined_CreateBCode_ClassAccess_createBCode(CodeGeneration gen) {
    if(prevExpr().type().isPrimitiveType() || prevExpr().type().isVoid()) {
      TypeDecl typeDecl = lookupType("java.lang", prevExpr().type().primitiveClassName());
      SimpleSet c = typeDecl.memberFields("TYPE");
      FieldDeclaration f = (FieldDeclaration)c.iterator().next();
      f.emitLoadField(gen, typeDecl);
    }
    else {
      FieldDeclaration f = hostType().topLevelType().createStaticClassField(prevExpr().type().referenceClassFieldName());
      // add method to perform lookup as a side-effect
      MethodDecl m = hostType().topLevelType().createStaticClassMethod();

      int next_label = hostType().constantPool().newLabel();
      int end_label = hostType().constantPool().newLabel();
      f.emitLoadField(gen, hostType());
      gen.emitBranchNonNull(next_label);
      
      // emit string literal
      
      StringLiteral.push(gen, prevExpr().type().jvmName());
      m.emitInvokeMethod(gen, hostType());
      gen.emitDup();
      f.emitStoreField(gen, hostType());
      gen.emitGoto(end_label);
      gen.addLabel(next_label);
      gen.changeStackDepth(-1);
      f.emitLoadField(gen, hostType());
      gen.addLabel(end_label);
    }
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ClassAccess() {
    super();


  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:10
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:16
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * @ast method 
   * @aspect Version
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\Version.jrag:19
   */
    public void createBCode(CodeGeneration gen) {
    if(prevExpr().type().isPrimitiveType() || prevExpr().type().isVoid())
      refined_CreateBCode_ClassAccess_createBCode(gen);
    else {
      int index = gen.constantPool().addClass(prevExpr().type().jvmName());
      if(index < 256)
        gen.emit(Bytecode.LDC).add(index);
      else 
        gen.emit(Bytecode.LDC_W).add2(index);
    }
  }
  /**
   * @ast method 
   * @aspect Version
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\Version.jrag:16
   */
    public void transformation() {
    super.transformation();
  }
  /**
   * @ast method 
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:503
   */
  private TypeDecl refined_TypeAnalysis_ClassAccess_type()
{ return lookupType("java.lang", "Class"); }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:62
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isClassAccess() {
      ASTNode$State state = state();
    boolean isClassAccess_value = isClassAccess_compute();
    return isClassAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isClassAccess_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:91
   */
  @SuppressWarnings({"unchecked", "cast"})
  public NameType predNameType() {
      ASTNode$State state = state();
    NameType predNameType_value = predNameType_compute();
    return predNameType_value;
  }
  /**
   * @apilvl internal
   */
  private NameType predNameType_compute() {  return NameType.TYPE_NAME;  }
  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:99
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
    GenericClassDecl d = (GenericClassDecl)refined_TypeAnalysis_ClassAccess_type();
    TypeDecl type = qualifier().type();
    if(type.isPrimitiveType())
      type = type.boxed();
    ArrayList list = new ArrayList();
    list.add(type);
    return d.lookupParTypeDecl(list);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
