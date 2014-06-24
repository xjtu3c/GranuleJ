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
 * @declaredat ExternalVar.ast:2
 */
public class ContextVarDecl extends ContextVar implements Cloneable {
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
  public ContextVarDecl clone() throws CloneNotSupportedException {
    ContextVarDecl node = (ContextVarDecl)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ContextVarDecl copy() {
      try {
        ContextVarDecl node = (ContextVarDecl)clone();
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
  public ContextVarDecl fullCopy() {
    ContextVarDecl res = (ContextVarDecl)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:1
   */
  public ContextVarDecl() {
    super();

    setChild(new List(), 2);

  }
  /**
   * @ast method 
   * @declaredat ExternalVar.ast:8
   */
  public ContextVarDecl(Modifiers p0, Access p1, List<VariableDecl> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:16
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat ExternalVar.ast:22
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * Setter for VariableDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:5
   */
  public void setVariableDeclList(List<VariableDecl> list) {
    setChild(list, 2);
  }
  /**
   * @return number of children in VariableDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:12
   */
  public int getNumVariableDecl() {
    return getVariableDeclList().getNumChild();
  }
  /**
   * Getter for child in list VariableDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public VariableDecl getVariableDecl(int i) {
    return (VariableDecl)getVariableDeclList().getChild(i);
  }
  /**
   * Add element to list VariableDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:27
   */
  public void addVariableDecl(VariableDecl node) {
    List<VariableDecl> list = (parent == null || state == null) ? getVariableDeclListNoTransform() : getVariableDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:34
   */
  public void addVariableDeclNoTransform(VariableDecl node) {
    List<VariableDecl> list = getVariableDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list VariableDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:42
   */
  public void setVariableDecl(VariableDecl node, int i) {
    List<VariableDecl> list = getVariableDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for VariableDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:50
   */
  public List<VariableDecl> getVariableDecls() {
    return getVariableDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:56
   */
  public List<VariableDecl> getVariableDeclsNoTransform() {
    return getVariableDeclListNoTransform();
  }
  /**
   * Getter for list VariableDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ExternalVar.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<VariableDecl> getVariableDeclList() {
    List<VariableDecl> list = (List<VariableDecl>)getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ExternalVar.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<VariableDecl> getVariableDeclListNoTransform() {
    return (List<VariableDecl>)getChildNoTransform(2);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:2
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getTypeAccessNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag at line 60
    if(getNumVariableDecl() == 1) {
      state().duringExternalVar++;
      ASTNode result = rewriteRule0();
      state().duringExternalVar--;
      return result;
    }

    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag at line 73
    if(getParent().getParent() instanceof CompilationUnit && 
      ((CompilationUnit)getParent().getParent()).getContextVarListNoTransform() == getParent() && getNumVariableDecl() > 1) {
    state().duringExternalVar++;
      List list = (List)getParent();
      int i = list.getIndexOfChild(this);
      List newList = rewriteCompilationUnit_getContextVar();
      for(int j = 1; j < newList.getNumChildNoTransform(); j++)
        list.insertChild(newList.getChildNoTransform(j), ++i);
        state().duringExternalVar--;
      return newList.getChildNoTransform(0);
    }
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag at line 96
    if(getParent().getParent() instanceof TypeDecl && 
      ((TypeDecl)getParent().getParent()).getBodyDeclListNoTransform() == getParent() && getNumVariableDecl() > 1) {
    state().duringExternalVar++;
      List list = (List)getParent();
      int i = list.getIndexOfChild(this);
      List newList = rewriteTypeDecl_getBodyDecl();
      for(int j = 1; j < newList.getNumChildNoTransform(); j++)
        list.insertChild(newList.getChildNoTransform(j), ++i);
        state().duringExternalVar--;
      return newList.getChildNoTransform(0);
    }
    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:60
   * @apilvl internal
   */  private ContextVarDeclaration rewriteRule0() {
{
      ContextVarDeclaration decl = getVariableDecl(0).createContextVarDeclarationFrom(getModifiers(), getTypeAccess());
      decl.setStart(start); // copy location information
      decl.setEnd(end); // copy location information
      return decl;
    }  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:73
   * @apilvl internal
   */  private List rewriteCompilationUnit_getContextVar() {
{
      List varList = new List();
      for(int j = 0; j < getNumVariableDecl(); j++) {
        ContextVarDeclaration e = 
          getVariableDecl(j).createContextVarDeclarationFrom(
            (Modifiers)getModifiers().fullCopy(),
            (Access)getTypeAccess().fullCopy()
          );       
        if(j == 0)
          e.setStart(start);
        else {
          e.getModifiersNoTransform().clearLocations();
          e.getTypeAccessNoTransform().clearLocations();
        }
        varList.add(e);
      }
      return varList;
    }  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:96
   * @apilvl internal
   */  private List rewriteTypeDecl_getBodyDecl() {
{
	      List varList = new List();
	      for(int j = 0; j < getNumVariableDecl(); j++) {
	        ContextVarDeclaration e = 
	          getVariableDecl(j).createContextVarDeclarationFrom(
	            (Modifiers)getModifiers().fullCopy(),
	            (Access)getTypeAccess().fullCopy()
	          );       
	        if(j == 0)
	          e.setStart(start);
	        else {
	          e.getModifiersNoTransform().clearLocations();
	          e.getTypeAccessNoTransform().clearLocations();
	        }
	        varList.add(e);
	      }
	      return varList;
	    }  }
}
