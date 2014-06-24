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
 * @declaredat java.ast:14
 */
public class Dot extends AbstractDot implements Cloneable {
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
  public Dot clone() throws CloneNotSupportedException {
    Dot node = (Dot)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Dot copy() {
      try {
        Dot node = (Dot)clone();
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
  public Dot fullCopy() {
    Dot res = (Dot)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:126
   */
  
  
  public Dot lastDot = null;
  /**
   * @ast method 
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:127
   */
  public Dot qualifiesAccess(Access access) {
    if(lastDot == null) {
      Dot node = this;
      while(node.getRightNoTransform() instanceof Dot)
        node = (Dot)node.getRightNoTransform();
      lastDot = node;
    }
    Dot dot = new Dot(lastDot.getRightNoTransform(), access);
    lastDot.setRight(dot);
    lastDot = dot;
    return this;
  }
  /**
   * @ast method 
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:141
   */
  private Access qualifyTailWith(Access expr) {
    if(getRight/*NoTransform*/() instanceof AbstractDot) {
      AbstractDot dot = (AbstractDot)getRight/*NoTransform*/();    
      return expr.qualifiesAccess(dot.getRight/*NoTransform*/());
    }
    return expr;
  }
  /**
   * @ast method 
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:158
   */
  public Access extractLast(){
	  return lastDot.getRightNoTransform();
  }
  /**
   * @ast method 
   * @aspect QualifiedNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:162
   */
  public void replaceLast(Access access) {
    lastDot.setRight(access);
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public Dot() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public Dot(Expr p0, Access p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:14
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:20
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for Left
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setLeft(Expr node) {
    setChild(node, 0);
  }
  /**
   * Getter for Left
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getLeft() {
    return (Expr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getLeftNoTransform() {
    return (Expr)getChildNoTransform(0);
  }
  /**
   * Setter for Right
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setRight(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for Right
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getRight() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getRightNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag at line 229
    if(!duringSyntacticClassification()&&leftSide().isPackageAccess() && rightSide().isPackageAccess()) {
      state().duringResolveAmbiguousNames++;
      ASTNode result = rewriteRule0();
      state().duringResolveAmbiguousNames--;
      return result;
    }

    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag at line 241
    if(!duringSyntacticClassification() && leftSide().isPackageAccess() && !((Access)leftSide()).hasPrevExpr() && rightSide() instanceof TypeAccess) {
      state().duringResolveAmbiguousNames++;
      ASTNode result = rewriteRule1();
      state().duringResolveAmbiguousNames--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:229
   * @apilvl internal
   */  private Access rewriteRule0() {
{
      PackageAccess left = (PackageAccess)leftSide();
      PackageAccess right = (PackageAccess)rightSide();
      left.setPackage(left.getPackage() + "." + right.getPackage());
      left.setEnd(right.end());
      return qualifyTailWith(left);
    }  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:241
   * @apilvl internal
   */  private Access rewriteRule1() {
{
      PackageAccess left = (PackageAccess)leftSide();
      TypeAccess right = (TypeAccess)rightSide();
      right.setPackage(left.getPackage());
      right.setStart(left.start());
      return qualifyTailWith(right);
    }  }
}
