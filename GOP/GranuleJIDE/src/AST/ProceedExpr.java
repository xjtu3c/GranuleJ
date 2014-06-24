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
 * @declaredat Granule.ast:23
 */
public class ProceedExpr extends Access implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    primarySignature_MethodDecl_String_values = null;
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
  public ProceedExpr clone() throws CloneNotSupportedException {
    ProceedExpr node = (ProceedExpr)super.clone();
    node.primarySignature_MethodDecl_String_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ProceedExpr copy() {
      try {
        ProceedExpr node = (ProceedExpr)clone();
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
  public ProceedExpr fullCopy() {
    ProceedExpr res = (ProceedExpr)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:336
   */
  public void toString(StringBuffer s)
    {
        s.append("proceed (");
        if(getNumArg() > 0){
         getArg(0).toString(s);
         for(int i = 1; i < getNumArg(); i++) {
         s.append(", ");
         getArg(i).toString(s);
      }
    }
        s.append(")");
    }
  /**
   * @ast method 
   * @declaredat Granule.ast:1
   */
  public ProceedExpr() {
    super();

    setChild(new List(), 0);

  }
  /**
   * @ast method 
   * @declaredat Granule.ast:8
   */
  public ProceedExpr(List<Expr> p0) {
    setChild(p0, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:14
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Granule.ast:20
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setArgList(List<Expr> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public int getNumArg() {
    return getArgList().getNumChild();
  }
  /**
   * Getter for child in list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr getArg(int i) {
    return (Expr)getArgList().getChild(i);
  }
  /**
   * Add element to list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:27
   */
  public void addArg(Expr node) {
    List<Expr> list = (parent == null || state == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:34
   */
  public void addArgNoTransform(Expr node) {
    List<Expr> list = getArgListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:42
   */
  public void setArg(Expr node, int i) {
    List<Expr> list = getArgList();
    list.setChild(node, i);
  }
  /**
   * Getter for Arg list.
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:50
   */
  public List<Expr> getArgs() {
    return getArgList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:56
   */
  public List<Expr> getArgsNoTransform() {
    return getArgListNoTransform();
  }
  /**
   * Getter for list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expr> getArgList() {
    List<Expr> list = (List<Expr>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expr> getArgListNoTransform() {
    return (List<Expr>)getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:30
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl type() {
      ASTNode$State state = state();
    TypeDecl type_value = type_compute();
    return type_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl type_compute() {  return ((MethodDecl)enclosingBodyDecl()).type();  }
  protected java.util.Map primarySignature_MethodDecl_String_values;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:190
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String primarySignature(MethodDecl md, String name) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(md);
    _parameters.add(name);
    if(primarySignature_MethodDecl_String_values == null) primarySignature_MethodDecl_String_values = new java.util.HashMap(4);
    if(primarySignature_MethodDecl_String_values.containsKey(_parameters)) {
      return (String)primarySignature_MethodDecl_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    String primarySignature_MethodDecl_String_value = primarySignature_compute(md, name);
if(isFinal && num == state().boundariesCrossed) primarySignature_MethodDecl_String_values.put(_parameters, primarySignature_MethodDecl_String_value);
    return primarySignature_MethodDecl_String_value;
  }
  /**
   * @apilvl internal
   */
  private String primarySignature_compute(MethodDecl md, String name) {
    	    StringBuffer s = new StringBuffer();
    	    s.append(name + "(");
    	    for(int i = 0; i < md.getNumParameter(); i++) {
    	      if(i != 0) s.append(", ");
    	      s.append(md.getParameter(i).type().typeName());
    	    }
    	    s.append(")");
    	    return s.toString();
    	  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag at line 153
    state().duringMethodTransform++;
    ASTNode result = rewriteRule0();
    state().duringMethodTransform--;
    return result;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:153
   * @apilvl internal
   */  private MethodAccess rewriteRule0() {
{
	  MethodDecl enclosingMethod=(MethodDecl)enclosingBodyDecl();
	  List proceedArgs=getArgList();
	  String type=enclosingMethod.name();
	  if(type.startsWith("before$")||type.startsWith("around$")||type.startsWith("after$"))
	  type=type.substring(type.indexOf("$"));	
	  MethodDecl m=enclosingMethod.primaryMethod(primarySignature(enclosingMethod,type));
	  LinkedList namelist=m.methodName;
	  String typename=enclosingMethod.name();
	  String methodname="";
	  int index=namelist.indexOf(typename);
	  if(index-1>=0&&(typename.startsWith("before$")||typename.startsWith("around$"))){
	   methodname=(String)namelist.get(index-1);
	  }	
	  if(typename.equals(type)&&proceedArgs.getNumChild()==enclosingMethod.getParameterList().getNumChild())
	  {
		  methodname=(String)namelist.get(index-1);
	  }
	  if(typename.equals(type)&&proceedArgs.getNumChild()>enclosingMethod.getParameterList().getNumChild()){
		  methodname=(String)namelist.get(index+1);
		  proceedArgs.removeChild(0);
	  }
	  if(typename.startsWith("after$")&&(namelist.size()-index-1)>1)
	  methodname=(String)namelist.get(index+1);
	  return new MethodAccess(methodname,proceedArgs);
  }  }
}
