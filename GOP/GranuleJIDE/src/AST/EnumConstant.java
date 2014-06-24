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
 * @declaredat Enums.ast:3
 */
public class EnumConstant extends FieldDeclaration implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    getTypeAccess_computed = false;
    getTypeAccess_value = null;
    flags_computed = false;
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
  public EnumConstant clone() throws CloneNotSupportedException {
    EnumConstant node = (EnumConstant)super.clone();
    node.getTypeAccess_computed = false;
    node.getTypeAccess_value = null;
    node.flags_computed = false;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public EnumConstant copy() {
      try {
        EnumConstant node = (EnumConstant)clone();
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
  public EnumConstant fullCopy() {
    EnumConstant res = (EnumConstant)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:197
   */
  public EnumConstant(Modifiers mods, String name, List<Expr> args, List<BodyDecl> bds) {
	  this(mods, name, args, new Opt<Expr>(new EnumInstanceExpr(createOptAnonymousDecl(bds))));
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:236
   */
  private static Opt<TypeDecl> createOptAnonymousDecl(List<BodyDecl> bds) {
    if(bds.getNumChildNoTransform() == 0)
      return new Opt<TypeDecl>();
    return new Opt<TypeDecl>(
      new AnonymousDecl(
        new Modifiers(),
        "Anonymous",
        bds
      )
    );
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:249
   */
  public int getNumBodyDecl() {
	  int cnt = 0;
	  ClassInstanceExpr init = (ClassInstanceExpr)getInit();
	  if(!init.hasTypeDecl())
		  return 0;
	  for(BodyDecl bd : init.getTypeDecl().getBodyDecls())
		  if(!(bd instanceof ConstructorDecl))
			  ++cnt;
	  return cnt;
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:260
   */
  public BodyDecl getBodyDecl(int i) {
	  ClassInstanceExpr init = (ClassInstanceExpr)getInit();
	  if(init.hasTypeDecl())
		  for(BodyDecl bd : init.getTypeDecl().getBodyDecls())
			  if(!(bd instanceof ConstructorDecl))
				  if(i-- == 0)
					  return bd;
	  throw new ArrayIndexOutOfBoundsException(i);
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:498
   */
  public int getNumChild() {
    return 5;
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:501
   */
  public ASTNode getChild(int i) {
    switch(i) {
      case 3: return getTypeAccess();
      case 4: return getInitOpt();
      default: return ASTNode.getChild(this, i);
    }
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:601
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    s.append(getID());
    s.append("(");
    if(getNumArg() > 0) {
      getArg(0).toString(s);
      for(int i = 1; i < getNumArg(); i++) {
        s.append(", ");
        getArg(i).toString(s);
      }
    }
    s.append(")");
    if(getNumBodyDecl() > 0) {
      s.append(" {");
      for(int i=0; i < getNumBodyDecl(); i++) {
        BodyDecl d = getBodyDecl(i);
        d.toString(s);
      }
      s.append(indent() + "}");
    }
    s.append(",\n");
  }
  /**
   * @ast method 
   * @declaredat Enums.ast:1
   */
  public EnumConstant() {
    super();

    setChild(new List(), 1);
    setChild(new Opt(), 2);

  }
  /**
   * @ast method 
   * @declaredat Enums.ast:9
   */
  public EnumConstant(Modifiers p0, String p1, List<Expr> p2, Opt<Expr> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(null, 3);
  }
  /**
   * @ast method 
   * @declaredat Enums.ast:16
   */
  public EnumConstant(Modifiers p0, beaver.Symbol p1, List<Expr> p2, Opt<Expr> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(null, 3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:26
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Enums.ast:32
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**
   * @ast method 
   * @declaredat Enums.ast:8
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
   * @declaredat Enums.ast:19
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:5
   */
  public void setArgList(List<Expr> list) {
    setChild(list, 1);
  }
  /**
   * @return number of children in ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:12
   */
  public int getNumArg() {
    return getArgList().getNumChild();
  }
  /**
   * Getter for child in list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr getArg(int i) {
    return (Expr)getArgList().getChild(i);
  }
  /**
   * Add element to list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:27
   */
  public void addArg(Expr node) {
    List<Expr> list = (parent == null || state == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:34
   */
  public void addArgNoTransform(Expr node) {
    List<Expr> list = getArgListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:42
   */
  public void setArg(Expr node, int i) {
    List<Expr> list = getArgList();
    list.setChild(node, i);
  }
  /**
   * Getter for Arg list.
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:50
   */
  public List<Expr> getArgs() {
    return getArgList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:56
   */
  public List<Expr> getArgsNoTransform() {
    return getArgListNoTransform();
  }
  /**
   * Getter for list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expr> getArgList() {
    List<Expr> list = (List<Expr>)getChild(1);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expr> getArgListNoTransform() {
    return (List<Expr>)getChildNoTransform(1);
  }
  /**
   * Setter for InitOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:5
   */
  public void setInitOpt(Opt<Expr> opt) {
    setChild(opt, 2);
  }
  /**
   * Does this node have a Init child?
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:12
   */
  public boolean hasInit() {
    return getInitOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr getInit() {
    return (Expr)getInitOpt().getChild(0);
  }
  /**
   * Setter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:27
   */
  public void setInit(Expr node) {
    getInitOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOpt() {
    return (Opt<Expr>)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOptNoTransform() {
    return (Opt<Expr>)getChildNoTransform(2);
  }
  /**
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat Enums.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:11
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Enums.ast:17
   */
  protected int getTypeAccessChildPosition() {
    return 3;
  }
  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:27
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isEnumConstant() {
      ASTNode$State state = state();
    boolean isEnumConstant_value = isEnumConstant_compute();
    return isEnumConstant_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isEnumConstant_compute() {  return true;  }
  /*
    11) In addition to the members it inherits from Enum<E>, for each declared
    enum constant with the name n the enum type has an implicitly declared
    public static final field named n of type E. These fields are considered to
    be declared in the same order as the corresponding enum constants, before
    any static fields explicitly declared in the enum type. Each such field is
    initialized to the enum constant that corresponds to it. Each such field is
    also considered to be annotated by the same annotations as the
    corresponding enum constant. The enum constant is said to be created when
    the corresponding field is initialized.
  * @attribute syn
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:189
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPublic() {
      ASTNode$State state = state();
    boolean isPublic_value = isPublic_compute();
    return isPublic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPublic_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:190
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isStatic() {
      ASTNode$State state = state();
    boolean isStatic_value = isStatic_compute();
    return isStatic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isStatic_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:191
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFinal() {
      ASTNode$State state = state();
    boolean isFinal_value = isFinal_compute();
    return isFinal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFinal_compute() {  return true;  }
  /**
   * @apilvl internal
   */
  protected boolean getTypeAccess_computed = false;
  /**
   * @apilvl internal
   */
  protected Access getTypeAccess_value;
  /**
   * @attribute syn nta
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:193
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getTypeAccess() {
    if(getTypeAccess_computed) {
      return (Access)ASTNode.getChild(this, getTypeAccessChildPosition());
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    getTypeAccess_value = getTypeAccess_compute();
      setTypeAccess(getTypeAccess_value);
if(isFinal && num == state().boundariesCrossed) getTypeAccess_computed = true;
    return (Access)ASTNode.getChild(this, getTypeAccessChildPosition());
  }
  /**
   * @apilvl internal
   */
  private Access getTypeAccess_compute() {
    return hostType().createQualifiedAccess();
  }
  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:518
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstant() {
      ASTNode$State state = state();
    boolean isConstant_value = isConstant_compute();
    return isConstant_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstant_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect EnumsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnumsCodegen.jrag:14
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int flags() {
    if(flags_computed) {
      return flags_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    flags_value = flags_compute();
if(isFinal && num == state().boundariesCrossed) flags_computed = true;
    return flags_value;
  }
  /**
   * @apilvl internal
   */
  private int flags_compute() {  return super.flags() | Modifiers.ACC_ENUM;  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:494
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getTypeAccessNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return super.Define_NameType_nameType(caller, child);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
