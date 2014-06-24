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
 * @declaredat java.ast:108
 */
public class AssignSimpleExpr extends AssignExpr implements Cloneable {
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
  public AssignSimpleExpr clone() throws CloneNotSupportedException {
    AssignSimpleExpr node = (AssignSimpleExpr)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public AssignSimpleExpr copy() {
      try {
        AssignSimpleExpr node = (AssignSimpleExpr)clone();
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
  public AssignSimpleExpr fullCopy() {
    AssignSimpleExpr res = (AssignSimpleExpr)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:43
   */
  public void typeCheck() {
    if(!getDest().isVariable())
      error("left hand side is not a variable");
    else if(!sourceType().assignConversionTo(getDest().type(), getSource()) && !sourceType().isUnknown())
      error("can not assign " + getDest() + " of type " + getDest().type().typeName() +
            " a value of type " + sourceType().typeName());  
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:510
   */
  public void createBCode(CodeGeneration gen) {
    getDest().createAssignSimpleLoadDest(gen);
    getSource().createBCode(gen);
    getSource().type().emitAssignConvTo(gen, getDest().type()); // AssignConversion
    if(needsPush()) {
      getDest().createPushAssignmentResult(gen);
    }
    getDest().emitStore(gen);
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public AssignSimpleExpr() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public AssignSimpleExpr(Expr p0, Expr p1) {
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
   * Setter for Dest
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setDest(Expr node) {
    setChild(node, 0);
  }
  /**
   * Getter for Dest
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getDest() {
    return (Expr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getDestNoTransform() {
    return (Expr)getChildNoTransform(0);
  }
  /**
   * Setter for Source
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setSource(Expr node) {
    setChild(node, 1);
  }
  /**
   * Getter for Source
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getSource() {
    return (Expr)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getSourceNoTransform() {
    return (Expr)getChildNoTransform(1);
  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:355
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String printOp() {
      ASTNode$State state = state();
    String printOp_value = printOp_compute();
    return printOp_value;
  }
  /**
   * @apilvl internal
   */
  private String printOp_compute() {  return " = ";  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:120
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl sourceType() {
      ASTNode$State state = state();
    TypeDecl sourceType_value = sourceType_compute();
    return sourceType_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl sourceType_compute() {  return getSource().type();  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:17
   * @apilvl internal
   */
  public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
    if(caller == getDestNoTransform()) {
      return true;
    }
    return super.Define_boolean_isDest(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:27
   * @apilvl internal
   */
  public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
    if(caller == getDestNoTransform()) {
      return false;
    }
    return super.Define_boolean_isSource(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericMethodsInference.jrag:36
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
    if(caller == getSourceNoTransform()) {
      return getDest().type();
    }
    return getParent().Define_TypeDecl_assignConvertedType(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 1518
    if(hostType().isClassDecl()&&(getDest().varDecl() instanceof ContextVarDeclaration)) {
      state().duringGOP++;
      ASTNode result = rewriteRule0();
      state().duringGOP--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1518
   * @apilvl internal
   */  private AbstractDot rewriteRule0() {
{
					   String methodname=new String("modifyContext");
					   TypeAccess ta= new TypeAccess("GopContext");
				       List<Expr> l =new List<Expr>();
				       StringLiteral para1=new StringLiteral(getDest().toString());
				       l.add(para1);
				       String typename=sourceType().typeName();				     
				       if(typename.equals("java.lang.String")){
				       l.add(getSource());
				       }
				       else {
				       String method=new String("valueOf");
				       List<Expr> ll=new List<Expr>();
				       ll.add(getSource());				      
				       TypeAccess tta=new TypeAccess("String");
				       ParseName ppn= new ParseName("valueOf");
				       MethodAccess mm=new MethodAccess(method,ll);		    	  
				       AbstractDot ddd=new AbstractDot(tta,ppn);
				       ddd.replaceLast(mm);
				       l.add(ddd);
				       }
				       ContextVarDeclaration convar=(ContextVarDeclaration)(getDest().varDecl());
				       /*if(convar.getModifiers().toString().equals("private")){
					   String way=new String("getLocalIP");
					   ParseName ipname= new ParseName("getLocalIP");
					   List<Expr> lst=new List<Expr>();
					   MethodAccess m=new MethodAccess(way,lst);		    	  
					   AbstractDot d=new AbstractDot(ta,ipname);
					   d.replaceLast(m);
					   l.add(d);
					   }
					   else{*/
					   StringLiteral para3=new StringLiteral(convar.getModifiers().toString());
					   l.add(para3);
					   //}		     
				       ParseName pn= new ParseName("modifyContext");
				       AbstractDot d = new AbstractDot(ta,pn);
				       MethodAccess m = new MethodAccess(methodname,l);
				       d.replaceLast(m);					   
				       return d;  
				}  }
}
