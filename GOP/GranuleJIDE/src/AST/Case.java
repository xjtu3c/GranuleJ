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
 * @declaredat java.ast:214
 */
public abstract class Case extends Stmt implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    isDAbefore_Variable_values = null;
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    label_CodeGeneration_values = null;
    bind_Case_values = null;
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
  public Case clone() throws CloneNotSupportedException {
    Case node = (Case)super.clone();
    node.isDAbefore_Variable_values = null;
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.label_CodeGeneration_values = null;
    node.bind_Case_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1900
   */
  public void createBCode(CodeGeneration gen) {
    gen.addLabel(label(gen));
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public Case() {
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
   * @attribute syn
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:535
   */
  @SuppressWarnings({"unchecked", "cast"})
  public abstract boolean constValue(Case c);
  protected java.util.Map isDAbefore_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:571
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAbefore(Variable v) {
    Object _parameters = v;
    if(isDAbefore_Variable_values == null) isDAbefore_Variable_values = new java.util.HashMap(4);
    if(isDAbefore_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDAbefore_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDAbefore_Variable_value = isDAbefore_compute(v);
if(isFinal && num == state().boundariesCrossed) isDAbefore_Variable_values.put(_parameters, Boolean.valueOf(isDAbefore_Variable_value));
    return isDAbefore_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAbefore_compute(Variable v) {  return getParent().getParent() instanceof Block && ((Block)getParent().getParent()).isDAbefore(v)
    && super.isDAbefore(v);  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:575
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
  private boolean isDAafter_compute(Variable v) {  return isDAbefore(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1029
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUbefore(Variable v) {
      ASTNode$State state = state();
    boolean isDUbefore_Variable_value = isDUbefore_compute(v);
    return isDUbefore_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUbefore_compute(Variable v) {  return getParent().getParent() instanceof Block && ((Block)getParent().getParent()).isDUbefore(v)
    && super.isDUbefore(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1033
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
  private boolean isDUafter_compute(Variable v) {  return isDUbefore(v);  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:86
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean reachable() {
      ASTNode$State state = state();
    boolean reachable_value = reachable_compute();
    return reachable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean reachable_compute() {  return getParent().getParent() instanceof Block && ((Block)getParent().getParent()).reachable();  }
  protected java.util.Map label_CodeGeneration_values;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1898
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label(CodeGeneration gen) {
    Object _parameters = gen;
    if(label_CodeGeneration_values == null) label_CodeGeneration_values = new java.util.HashMap(4);
    if(label_CodeGeneration_values.containsKey(_parameters)) {
      return ((Integer)label_CodeGeneration_values.get(_parameters)).intValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    int label_CodeGeneration_value = label_compute(gen);
if(isFinal && num == state().boundariesCrossed) label_CodeGeneration_values.put(_parameters, Integer.valueOf(label_CodeGeneration_value));
    return label_CodeGeneration_value;
  }
  /**
   * @apilvl internal
   */
  private int label_compute(CodeGeneration gen) {  return hostType().constantPool().newLabel();  }
  protected java.util.Map bind_Case_values;
  /**
   * @attribute inh
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:521
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Case bind(Case c) {
    Object _parameters = c;
    if(bind_Case_values == null) bind_Case_values = new java.util.HashMap(4);
    if(bind_Case_values.containsKey(_parameters)) {
      return (Case)bind_Case_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    Case bind_Case_value = getParent().Define_Case_bind(this, null, c);
if(isFinal && num == state().boundariesCrossed) bind_Case_values.put(_parameters, bind_Case_value);
    return bind_Case_value;
  }
  /**
   * @attribute inh
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:356
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl switchType() {
      ASTNode$State state = state();
    TypeDecl switchType_value = getParent().Define_TypeDecl_switchType(this, null);
    return switchType_value;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
