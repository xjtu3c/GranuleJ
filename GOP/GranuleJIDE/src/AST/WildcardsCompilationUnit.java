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
 * @declaredat Generics.ast:41
 */
public class WildcardsCompilationUnit extends CompilationUnit implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    typeWildcard_computed = false;
    typeWildcard_value = null;
    lookupWildcardExtends_TypeDecl_values = null;
    lookupWildcardSuper_TypeDecl_values = null;
    lookupLUBType_Collection_values = null;
    lookupGLBType_ArrayList_values = null;
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
  public WildcardsCompilationUnit clone() throws CloneNotSupportedException {
    WildcardsCompilationUnit node = (WildcardsCompilationUnit)super.clone();
    node.typeWildcard_computed = false;
    node.typeWildcard_value = null;
    node.lookupWildcardExtends_TypeDecl_values = null;
    node.lookupWildcardSuper_TypeDecl_values = null;
    node.lookupLUBType_Collection_values = null;
    node.lookupGLBType_ArrayList_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public WildcardsCompilationUnit copy() {
      try {
        WildcardsCompilationUnit node = (WildcardsCompilationUnit)clone();
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
  public WildcardsCompilationUnit fullCopy() {
    WildcardsCompilationUnit res = (WildcardsCompilationUnit)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1178
   */
  public static LUBType createLUBType(Collection bounds) {
    List boundList = new List();
    StringBuffer name = new StringBuffer();
    for(Iterator iter = bounds.iterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      boundList.add(typeDecl.createBoundAccess());
      name.append("& " + typeDecl.typeName());
    }
    LUBType decl = new LUBType(
      new Modifiers(new List().add(new Modifier("public"))),
      name.toString(),
      new List(),
      boundList
    );
    return decl;
  }
  /**
   * @ast method 
   * @declaredat Generics.ast:1
   */
  public WildcardsCompilationUnit() {
    super();

    setChild(new List(), 0);
    setChild(new List(), 1);
    setChild(new List(), 2);

  }
  /**
   * @ast method 
   * @declaredat Generics.ast:10
   */
  public WildcardsCompilationUnit(java.lang.String p0, List<ImportDecl> p1, List<ContextVar> p2, List<TypeDecl> p3) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @ast method 
   * @declaredat Generics.ast:16
   */
  public WildcardsCompilationUnit(beaver.Symbol p0, List<ImportDecl> p1, List<ContextVar> p2, List<TypeDecl> p3) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Generics.ast:25
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Generics.ast:31
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for lexeme PackageDecl
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setPackageDecl(java.lang.String value) {
    tokenjava_lang_String_PackageDecl = value;
  }
  /**
   * @ast method 
   * @declaredat java.ast:8
   */
  public void setPackageDecl(beaver.Symbol symbol) {
    if(symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setPackageDecl is only valid for String lexemes");
    tokenjava_lang_String_PackageDecl = (String)symbol.value;
    PackageDeclstart = symbol.getStart();
    PackageDeclend = symbol.getEnd();
  }
  /**
   * Getter for lexeme PackageDecl
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  public java.lang.String getPackageDecl() {
    return tokenjava_lang_String_PackageDecl != null ? tokenjava_lang_String_PackageDecl : "";
  }
  /**
   * Setter for ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setImportDeclList(List<ImportDecl> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumImportDecl() {
    return getImportDeclList().getNumChild();
  }
  /**
   * Getter for child in list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ImportDecl getImportDecl(int i) {
    return (ImportDecl)getImportDeclList().getChild(i);
  }
  /**
   * Add element to list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addImportDecl(ImportDecl node) {
    List<ImportDecl> list = (parent == null || state == null) ? getImportDeclListNoTransform() : getImportDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addImportDeclNoTransform(ImportDecl node) {
    List<ImportDecl> list = getImportDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setImportDecl(ImportDecl node, int i) {
    List<ImportDecl> list = getImportDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for ImportDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<ImportDecl> getImportDecls() {
    return getImportDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<ImportDecl> getImportDeclsNoTransform() {
    return getImportDeclListNoTransform();
  }
  /**
   * Getter for list ImportDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ImportDecl> getImportDeclList() {
    List<ImportDecl> list = (List<ImportDecl>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ImportDecl> getImportDeclListNoTransform() {
    return (List<ImportDecl>)getChildNoTransform(0);
  }
  /**
   * Setter for ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setContextVarList(List<ContextVar> list) {
    setChild(list, 1);
  }
  /**
   * @return number of children in ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumContextVar() {
    return getContextVarList().getNumChild();
  }
  /**
   * Getter for child in list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ContextVar getContextVar(int i) {
    return (ContextVar)getContextVarList().getChild(i);
  }
  /**
   * Add element to list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addContextVar(ContextVar node) {
    List<ContextVar> list = (parent == null || state == null) ? getContextVarListNoTransform() : getContextVarList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addContextVarNoTransform(ContextVar node) {
    List<ContextVar> list = getContextVarListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setContextVar(ContextVar node, int i) {
    List<ContextVar> list = getContextVarList();
    list.setChild(node, i);
  }
  /**
   * Getter for ContextVar list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<ContextVar> getContextVars() {
    return getContextVarList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<ContextVar> getContextVarsNoTransform() {
    return getContextVarListNoTransform();
  }
  /**
   * Getter for list ContextVarList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ContextVar> getContextVarList() {
    List<ContextVar> list = (List<ContextVar>)getChild(1);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ContextVar> getContextVarListNoTransform() {
    return (List<ContextVar>)getChildNoTransform(1);
  }
  /**
   * Setter for TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setTypeDeclList(List<TypeDecl> list) {
    setChild(list, 2);
  }
  /**
   * @return number of children in TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumTypeDecl() {
    return getTypeDeclList().getNumChild();
  }
  /**
   * Getter for child in list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl getTypeDecl(int i) {
    return (TypeDecl)getTypeDeclList().getChild(i);
  }
  /**
   * Add element to list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addTypeDecl(TypeDecl node) {
    List<TypeDecl> list = (parent == null || state == null) ? getTypeDeclListNoTransform() : getTypeDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addTypeDeclNoTransform(TypeDecl node) {
    List<TypeDecl> list = getTypeDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setTypeDecl(TypeDecl node, int i) {
    List<TypeDecl> list = getTypeDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for TypeDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<TypeDecl> getTypeDecls() {
    return getTypeDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<TypeDecl> getTypeDeclsNoTransform() {
    return getTypeDeclListNoTransform();
  }
  /**
   * Getter for list TypeDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<TypeDecl> getTypeDeclList() {
    List<TypeDecl> list = (List<TypeDecl>)getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<TypeDecl> getTypeDeclListNoTransform() {
    return (List<TypeDecl>)getChildNoTransform(2);
  }
  /**
   * @apilvl internal
   */
  protected boolean typeWildcard_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeWildcard_value;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1140
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeWildcard() {
    if(typeWildcard_computed) {
      return typeWildcard_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeWildcard_value = typeWildcard_compute();
    typeWildcard_value.setParent(this);
    typeWildcard_value.is$Final = true;
if(true) typeWildcard_computed = true;
    return typeWildcard_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeWildcard_compute() {
    TypeDecl decl = new WildcardType(
      new Modifiers(new List().add(new Modifier("public"))),
      "?",
      new List()
    );
    return decl;
  }
  /**
   * @apilvl internal
   */
  protected java.util.Map lookupWildcardExtends_TypeDecl_values;
  protected List lookupWildcardExtends_TypeDecl_list;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1151
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl lookupWildcardExtends(TypeDecl bound) {
    Object _parameters = bound;
    if(lookupWildcardExtends_TypeDecl_values == null) lookupWildcardExtends_TypeDecl_values = new java.util.HashMap(4);
    if(lookupWildcardExtends_TypeDecl_values.containsKey(_parameters)) {
      return (TypeDecl)lookupWildcardExtends_TypeDecl_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    TypeDecl lookupWildcardExtends_TypeDecl_value = lookupWildcardExtends_compute(bound);
    if(lookupWildcardExtends_TypeDecl_list == null) {
      lookupWildcardExtends_TypeDecl_list = new List();
      lookupWildcardExtends_TypeDecl_list.is$Final = true;
      lookupWildcardExtends_TypeDecl_list.setParent(this);
    }
    lookupWildcardExtends_TypeDecl_list.add(lookupWildcardExtends_TypeDecl_value);
    lookupWildcardExtends_TypeDecl_value.is$Final = true;
if(true) lookupWildcardExtends_TypeDecl_values.put(_parameters, lookupWildcardExtends_TypeDecl_value);
    return lookupWildcardExtends_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl lookupWildcardExtends_compute(TypeDecl bound) {
    TypeDecl decl = new WildcardExtendsType(
      new Modifiers(new List().add(new Modifier("public"))),
      "? extends " + bound.fullName(),
      new List(),
      bound.createBoundAccess()
    );
    return decl;
  }
  /**
   * @apilvl internal
   */
  protected java.util.Map lookupWildcardSuper_TypeDecl_values;
  protected List lookupWildcardSuper_TypeDecl_list;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1164
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl lookupWildcardSuper(TypeDecl bound) {
    Object _parameters = bound;
    if(lookupWildcardSuper_TypeDecl_values == null) lookupWildcardSuper_TypeDecl_values = new java.util.HashMap(4);
    if(lookupWildcardSuper_TypeDecl_values.containsKey(_parameters)) {
      return (TypeDecl)lookupWildcardSuper_TypeDecl_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    TypeDecl lookupWildcardSuper_TypeDecl_value = lookupWildcardSuper_compute(bound);
    if(lookupWildcardSuper_TypeDecl_list == null) {
      lookupWildcardSuper_TypeDecl_list = new List();
      lookupWildcardSuper_TypeDecl_list.is$Final = true;
      lookupWildcardSuper_TypeDecl_list.setParent(this);
    }
    lookupWildcardSuper_TypeDecl_list.add(lookupWildcardSuper_TypeDecl_value);
    lookupWildcardSuper_TypeDecl_value.is$Final = true;
if(true) lookupWildcardSuper_TypeDecl_values.put(_parameters, lookupWildcardSuper_TypeDecl_value);
    return lookupWildcardSuper_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl lookupWildcardSuper_compute(TypeDecl bound) {
    TypeDecl decl = new WildcardSuperType(
      new Modifiers(new List().add(new Modifier("public"))),
      "? super " + bound.fullName(),
      new List(),
      bound.createBoundAccess()
    );
    return decl;
  }
  /**
   * @apilvl internal
   */
  protected java.util.Map lookupLUBType_Collection_values;
  protected List lookupLUBType_Collection_list;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1177
   */
  @SuppressWarnings({"unchecked", "cast"})
  public LUBType lookupLUBType(Collection bounds) {
    Object _parameters = bounds;
    if(lookupLUBType_Collection_values == null) lookupLUBType_Collection_values = new java.util.HashMap(4);
    if(lookupLUBType_Collection_values.containsKey(_parameters)) {
      return (LUBType)lookupLUBType_Collection_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    LUBType lookupLUBType_Collection_value = lookupLUBType_compute(bounds);
    if(lookupLUBType_Collection_list == null) {
      lookupLUBType_Collection_list = new List();
      lookupLUBType_Collection_list.is$Final = true;
      lookupLUBType_Collection_list.setParent(this);
    }
    lookupLUBType_Collection_list.add(lookupLUBType_Collection_value);
    lookupLUBType_Collection_value.is$Final = true;
if(true) lookupLUBType_Collection_values.put(_parameters, lookupLUBType_Collection_value);
    return lookupLUBType_Collection_value;
  }
  /**
   * @apilvl internal
   */
  private LUBType lookupLUBType_compute(Collection bounds) {  return createLUBType(bounds);  }
  /**
   * @apilvl internal
   */
  protected java.util.Map lookupGLBType_ArrayList_values;
  protected List lookupGLBType_ArrayList_list;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1216
   */
  @SuppressWarnings({"unchecked", "cast"})
  public GLBType lookupGLBType(ArrayList bounds) {
    Object _parameters = bounds;
    if(lookupGLBType_ArrayList_values == null) lookupGLBType_ArrayList_values = new java.util.HashMap(4);
    if(lookupGLBType_ArrayList_values.containsKey(_parameters)) {
      return (GLBType)lookupGLBType_ArrayList_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    GLBType lookupGLBType_ArrayList_value = lookupGLBType_compute(bounds);
    if(lookupGLBType_ArrayList_list == null) {
      lookupGLBType_ArrayList_list = new List();
      lookupGLBType_ArrayList_list.is$Final = true;
      lookupGLBType_ArrayList_list.setParent(this);
    }
    lookupGLBType_ArrayList_list.add(lookupGLBType_ArrayList_value);
    lookupGLBType_ArrayList_value.is$Final = true;
if(true) lookupGLBType_ArrayList_values.put(_parameters, lookupGLBType_ArrayList_value);
    return lookupGLBType_ArrayList_value;
  }
  /**
   * @apilvl internal
   */
  private GLBType lookupGLBType_compute(ArrayList bounds) {
    List boundList = new List();
    StringBuffer name = new StringBuffer();
    for(Iterator iter = bounds.iterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)iter.next();
      boundList.add(typeDecl.createBoundAccess());
      name.append("& " + typeDecl.typeName());
    }
    GLBType decl = new GLBType(
      new Modifiers(new List().add(new Modifier("public"))),
      name.toString(),
      new List(),
      boundList
    );
    return decl;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
