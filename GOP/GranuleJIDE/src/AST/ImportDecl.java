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
 * @declaredat java.ast:7
 */
public abstract class ImportDecl extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    importedTypes_String_values = null;
    importedFields_String_values = null;
    importedMethods_String_values = null;
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
  public ImportDecl clone() throws CloneNotSupportedException {
    ImportDecl node = (ImportDecl)super.clone();
    node.importedTypes_String_values = null;
    node.importedFields_String_values = null;
    node.importedMethods_String_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ImportDecl() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public ImportDecl(Access p0) {
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
   * Setter for Access
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setAccess(Access node) {
    setChild(node, 0);
  }
  /**
   * Getter for Access
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getAccess() {
    return (Access)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getAccessNoTransform() {
    return (Access)getChildNoTransform(0);
  }
  protected java.util.Map importedTypes_String_values;
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:273
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet importedTypes(String name) {
    Object _parameters = name;
    if(importedTypes_String_values == null) importedTypes_String_values = new java.util.HashMap(4);
    if(importedTypes_String_values.containsKey(_parameters)) {
      return (SimpleSet)importedTypes_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet importedTypes_String_value = importedTypes_compute(name);
if(isFinal && num == state().boundariesCrossed) importedTypes_String_values.put(_parameters, importedTypes_String_value);
    return importedTypes_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet importedTypes_compute(String name) {  return SimpleSet.emptySet;  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:302
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isOnDemand() {
      ASTNode$State state = state();
    boolean isOnDemand_value = isOnDemand_compute();
    return isOnDemand_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isOnDemand_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\QualifiedNames.jrag:53
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String typeName() {
      ASTNode$State state = state();
    String typeName_value = typeName_compute();
    return typeName_value;
  }
  /**
   * @apilvl internal
   */
  private String typeName_compute() {
    Access a = getAccess().lastAccess();
    String name = a.isTypeAccess() ? ((TypeAccess)a).nameWithPackage() : "";
    while(a.hasPrevExpr() && a.prevExpr() instanceof Access) {
      Access pred = (Access)a.prevExpr();
      if(pred.isTypeAccess())
        name = ((TypeAccess)pred).nameWithPackage() + "." + name;
      a = pred;
    }
    return name;
  }
  protected java.util.Map importedFields_String_values;
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:30
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet importedFields(String name) {
    Object _parameters = name;
    if(importedFields_String_values == null) importedFields_String_values = new java.util.HashMap(4);
    if(importedFields_String_values.containsKey(_parameters)) {
      return (SimpleSet)importedFields_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet importedFields_String_value = importedFields_compute(name);
if(isFinal && num == state().boundariesCrossed) importedFields_String_values.put(_parameters, importedFields_String_value);
    return importedFields_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet importedFields_compute(String name) {  return SimpleSet.emptySet;  }
  protected java.util.Map importedMethods_String_values;
  /**
   * @attribute syn
   * @aspect StaticImports
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\StaticImports.jrag:41
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection importedMethods(String name) {
    Object _parameters = name;
    if(importedMethods_String_values == null) importedMethods_String_values = new java.util.HashMap(4);
    if(importedMethods_String_values.containsKey(_parameters)) {
      return (Collection)importedMethods_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    Collection importedMethods_String_value = importedMethods_compute(name);
if(isFinal && num == state().boundariesCrossed) importedMethods_String_values.put(_parameters, importedMethods_String_value);
    return importedMethods_String_value;
  }
  /**
   * @apilvl internal
   */
  private Collection importedMethods_compute(String name) {  return Collections.EMPTY_LIST;  }
  /**
   * @attribute inh
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:300
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String packageName() {
      ASTNode$State state = state();
    String packageName_value = getParent().Define_String_packageName(this, null);
    return packageName_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:23
   * @apilvl internal
   */
  public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
    if(caller == getAccessNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_isDest(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:32
   * @apilvl internal
   */
  public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
    if(caller == getAccessNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_isSource(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
