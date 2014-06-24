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
 * @declaredat Granule.ast:10
 */
public class UpdateExprStmt extends Stmt implements Cloneable {
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
  public UpdateExprStmt clone() throws CloneNotSupportedException {
    UpdateExprStmt node = (UpdateExprStmt)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public UpdateExprStmt copy() {
      try {
        UpdateExprStmt node = (UpdateExprStmt)clone();
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
  public UpdateExprStmt fullCopy() {
    UpdateExprStmt res = (UpdateExprStmt)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @declaredat Granule.ast:1
   */
  public UpdateExprStmt() {
    super();


  }
  /**
   * @ast method 
   * @declaredat Granule.ast:7
   */
  public UpdateExprStmt(UpdateExpr p0) {
    setChild(p0, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:13
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Granule.ast:19
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for UpdateExpr
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setUpdateExpr(UpdateExpr node) {
    setChild(node, 0);
  }
  /**
   * Getter for UpdateExpr
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public UpdateExpr getUpdateExpr() {
    return (UpdateExpr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:18
   */
  public UpdateExpr getUpdateExprNoTransform() {
    return (UpdateExpr)getChildNoTransform(0);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
