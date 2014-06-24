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
 * @declaredat VariableArityParameters.ast:1
 */
public class VariableArityParameterDeclaration extends ParameterDeclaration implements Cloneable {
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
  public VariableArityParameterDeclaration clone() throws CloneNotSupportedException {
    VariableArityParameterDeclaration node = (VariableArityParameterDeclaration)super.clone();
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
  public VariableArityParameterDeclaration copy() {
      try {
        VariableArityParameterDeclaration node = (VariableArityParameterDeclaration)clone();
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
  public VariableArityParameterDeclaration fullCopy() {
    VariableArityParameterDeclaration res = (VariableArityParameterDeclaration)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:15
   */
  public void nameCheck() {
    super.nameCheck();
    if(!variableArityValid())
      error("only the last formal paramater may be of variable arity");
  }
  /**
   * @ast method 
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:101
   */
  public void toString(StringBuffer s) {
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" ... " + name());
  }
  /**
   * @ast method 
   * @declaredat VariableArityParameters.ast:1
   */
  public VariableArityParameterDeclaration() {
    super();


  }
  /**
   * @ast method 
   * @declaredat VariableArityParameters.ast:7
   */
  public VariableArityParameterDeclaration(Modifiers p0, Access p1, String p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
  }
  /**
   * @ast method 
   * @declaredat VariableArityParameters.ast:12
   */
  public VariableArityParameterDeclaration(Modifiers p0, Access p1, beaver.Symbol p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat VariableArityParameters.ast:20
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat VariableArityParameters.ast:26
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
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(1);
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
  /* If the last formal parameter is a variable arity parameter of type T, it is
  considered to define a formal parameter of type T[].* @attribute syn
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:30
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
  private TypeDecl type_compute() {  return super.type().arrayType();  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:36
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVariableArity() {
      ASTNode$State state = state();
    boolean isVariableArity_value = isVariableArity_compute();
    return isVariableArity_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVariableArity_compute() {  return true;  }
  /**
   * @attribute inh
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:26
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean variableArityValid() {
      ASTNode$State state = state();
    boolean variableArityValid_value = getParent().Define_boolean_variableArityValid(this, null);
    return variableArityValid_value;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
