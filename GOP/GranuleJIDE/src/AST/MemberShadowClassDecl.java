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
 * @declaredat java.ast:99
 */
public class MemberShadowClassDecl extends MemberTypeDecl implements Cloneable {
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
  public MemberShadowClassDecl clone() throws CloneNotSupportedException {
    MemberShadowClassDecl node = (MemberShadowClassDecl)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MemberShadowClassDecl copy() {
      try {
        MemberShadowClassDecl node = (MemberShadowClassDecl)clone();
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
  public MemberShadowClassDecl fullCopy() {
    MemberShadowClassDecl res = (MemberShadowClassDecl)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:298
   */
  public void toString(StringBuffer s)
  {
  s.append(indent());
  getShadowClassDecl().toString(s);
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public MemberShadowClassDecl() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public MemberShadowClassDecl(ShadowClassDecl p0) {
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
   * Setter for ShadowClassDecl
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setShadowClassDecl(ShadowClassDecl node) {
    setChild(node, 0);
  }
  /**
   * Getter for ShadowClassDecl
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public ShadowClassDecl getShadowClassDecl() {
    return (ShadowClassDecl)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public ShadowClassDecl getShadowClassDeclNoTransform() {
    return (ShadowClassDecl)getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:436
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeDecl() {
      ASTNode$State state = state();
    TypeDecl typeDecl_value = typeDecl_compute();
    return typeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl typeDecl_compute() {  return getShadowClassDecl();  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:664
   * @apilvl internal
   */
  public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
    if(caller == getShadowClassDeclNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_isMemberType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:185
   * @apilvl internal
   */
  public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
    if(caller == getShadowClassDeclNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_inStaticContext(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
