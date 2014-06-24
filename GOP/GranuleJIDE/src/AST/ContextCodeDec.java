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
 * @declaredat Granule.ast:6
 */
public class ContextCodeDec extends BodyDecl implements Cloneable {
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
  public ContextCodeDec clone() throws CloneNotSupportedException {
    ContextCodeDec node = (ContextCodeDec)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ContextCodeDec copy() {
      try {
        ContextCodeDec node = (ContextCodeDec)clone();
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
  public ContextCodeDec fullCopy() {
    ContextCodeDec res = (ContextCodeDec)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:162
   */
  public void toString(StringBuffer s)
   {
      if(getNumContextCode()>0)
      {
        getContextCode(0).toString(s);
        for(int i=1;i<getNumContextCode();i++)
        getContextCode(i).toString(s);
      }
   }
  /**
   * @ast method 
   * @declaredat Granule.ast:1
   */
  public ContextCodeDec() {
    super();

    setChild(new List(), 0);

  }
  /**
   * @ast method 
   * @declaredat Granule.ast:8
   */
  public ContextCodeDec(List<ContextCode> p0) {
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
   * Setter for ContextCodeList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setContextCodeList(List<ContextCode> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ContextCodeList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public int getNumContextCode() {
    return getContextCodeList().getNumChild();
  }
  /**
   * Getter for child in list ContextCodeList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ContextCode getContextCode(int i) {
    return (ContextCode)getContextCodeList().getChild(i);
  }
  /**
   * Add element to list ContextCodeList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:27
   */
  public void addContextCode(ContextCode node) {
    List<ContextCode> list = (parent == null || state == null) ? getContextCodeListNoTransform() : getContextCodeList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:34
   */
  public void addContextCodeNoTransform(ContextCode node) {
    List<ContextCode> list = getContextCodeListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ContextCodeList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:42
   */
  public void setContextCode(ContextCode node, int i) {
    List<ContextCode> list = getContextCodeList();
    list.setChild(node, i);
  }
  /**
   * Getter for ContextCode list.
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:50
   */
  public List<ContextCode> getContextCodes() {
    return getContextCodeList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:56
   */
  public List<ContextCode> getContextCodesNoTransform() {
    return getContextCodeListNoTransform();
  }
  /**
   * Getter for list ContextCodeList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ContextCode> getContextCodeList() {
    List<ContextCode> list = (List<ContextCode>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ContextCode> getContextCodeListNoTransform() {
    return (List<ContextCode>)getChildNoTransform(0);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag at line 415
    if(getParent().getParent() instanceof GranuleDecl && 
      ((GranuleDecl)getParent().getParent()).getBodyDeclListNoTransform() == getParent() && getNumContextCode()>0) {
    state().duringMethodTransform++;
      List list = (List)getParent();
      int i = list.getIndexOfChild(this);
      List newList = rewriteGranuleDecl_getBodyDecl();
      for(int j = 1; j < newList.getNumChildNoTransform(); j++)
        list.insertChild(newList.getChildNoTransform(j), ++i);
        state().duringMethodTransform--;
      return newList.getChildNoTransform(0);
    }
    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:415
   * @apilvl internal
   */  private List rewriteGranuleDecl_getBodyDecl() {
{
    	boolean flag=false,check=false;
   	    List<MemberDecl> methodlist=new List<MemberDecl>();
        Modifier m = new Modifier("protected");
   	    Modifier b=new Modifier("static");
   	    List<Modifier> l= new List<Modifier>();
   	    l.add(m);
   	    l.add(b);
   	    Modifiers ms = new Modifiers(l);
   	    Access access=new PrimitiveTypeAccess("boolean");
   	    List<ParameterDeclaration> l3=new List<ParameterDeclaration>();
   	    List<Access> l4 =new List<Access>();	
   	    Opt<Block> opt=new Opt(getContextCode(0).getBlock());	    
   	    MethodDecl m1=new MethodDecl(ms,access,"fitness",l3,l4,opt);
   	    methodlist.add(m1);
   	    if(getNumContextCode()==2){     	
     	if(getContextCode(1).getBlock().fullCopy().hasCheckFitness())
        flag=true;
     	 }
   	    Opt<Block> opt1=new Opt(new Block());
   	    if((!flag&&getNumContextCode()==2)||getNumContextCode()==3)
   	    opt1=new Opt(getContextCode(1).getBlock());
   	    String s2="void";
   	    Access access0=new TypeAccess(s2);	 
   	    MethodDecl m2=new MethodDecl(ms,access0,"fitnessAction",l3,l4,opt1);
   	    methodlist.add(m2);
        BooleanLiteral btrue=new BooleanLiteral("false");
      	ReturnStmt restmt=new ReturnStmt(btrue);
      	List<Stmt> lb=new List<Stmt>();
      	lb.add(restmt);
      	Block bl=new Block(lb);
   	    Opt<Block> opt2=new Opt(bl);
   	    if(flag)
   	    opt2=new Opt(getContextCode(1).getBlock());
   	    if(getNumContextCode()==3)
   	    opt2=new Opt(getContextCode(2).getBlock());
        MethodDecl m3=new MethodDecl(ms,access,"checkfitness",l3,l4,opt2);
        methodlist.add(m3);
        GranuleDecl gdel=(GranuleDecl)hostType();
        if(flag||getNumContextCode()==3)
        check=true;
        gdel.addFieldDecl(check);
   	    return methodlist;
   	    }  }
}
