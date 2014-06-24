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
 * @declaredat Annotations.ast:16
 */
public class AnnotatedCompilationUnit extends CompilationUnit implements Cloneable {
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
  public AnnotatedCompilationUnit clone() throws CloneNotSupportedException {
    AnnotatedCompilationUnit node = (AnnotatedCompilationUnit)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public AnnotatedCompilationUnit copy() {
      try {
        AnnotatedCompilationUnit node = (AnnotatedCompilationUnit)clone();
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
  public AnnotatedCompilationUnit fullCopy() {
    AnnotatedCompilationUnit res = (AnnotatedCompilationUnit)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:21
   */
  public void nameCheck() {
    super.nameCheck();
    if(!relativeName().endsWith("package-info.java"))
      error("package annotations should be in a file package-info.java");
  }
  /**
   * @ast method 
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:553
   */
  public void toString(StringBuffer s) {
      getModifiers().toString(s);
      super.toString(s);
  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:1
   */
  public AnnotatedCompilationUnit() {
    super();

    setChild(new List(), 0);
    setChild(new List(), 1);
    setChild(new List(), 2);

  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:10
   */
  public AnnotatedCompilationUnit(java.lang.String p0, List<ImportDecl> p1, List<ContextVar> p2, List<TypeDecl> p3, Modifiers p4) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /**
   * @ast method 
   * @declaredat Annotations.ast:17
   */
  public AnnotatedCompilationUnit(beaver.Symbol p0, List<ImportDecl> p1, List<ContextVar> p2, List<TypeDecl> p3, Modifiers p4) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:27
   */
  protected int numChildren() {
    return 4;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Annotations.ast:33
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
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 3);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Annotations.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Annotations.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(3);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:71
   * @apilvl internal
   */
  public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
    if(caller == getModifiersNoTransform()) {
      return name.equals("PACKAGE");
    }
    return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:548
   * @apilvl internal
   */
  public String Define_String_hostPackage(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return packageName();
    }
    return super.Define_String_hostPackage(caller, child);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
