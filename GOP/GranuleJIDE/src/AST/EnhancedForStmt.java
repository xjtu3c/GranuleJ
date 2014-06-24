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
 * @declaredat EnhancedFor.ast:1
 */
public class EnhancedForStmt extends BranchTargetStmt implements Cloneable, VariableScope {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    targetOf_ContinueStmt_values = null;
    targetOf_BreakStmt_values = null;
    canCompleteNormally_computed = false;
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    cond_label_computed = false;
    update_label_computed = false;
    end_label_computed = false;
    extraLocalIndex_computed = false;
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
  public EnhancedForStmt clone() throws CloneNotSupportedException {
    EnhancedForStmt node = (EnhancedForStmt)super.clone();
    node.targetOf_ContinueStmt_values = null;
    node.targetOf_BreakStmt_values = null;
    node.canCompleteNormally_computed = false;
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.cond_label_computed = false;
    node.update_label_computed = false;
    node.end_label_computed = false;
    node.extraLocalIndex_computed = false;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public EnhancedForStmt copy() {
      try {
        EnhancedForStmt node = (EnhancedForStmt)clone();
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
  public EnhancedForStmt fullCopy() {
    EnhancedForStmt res = (EnhancedForStmt)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:15
   */
  public void typeCheck() {
		if (!getExpr().type().isArrayDecl() && !getExpr().type().isIterable()) {
			error("type " + getExpr().type().name() + 
			      " of expression in foreach is neither array type nor java.lang.Iterable");
		}	
    else if(getExpr().type().isArrayDecl() && !getExpr().type().componentType().assignConversionTo(getVariableDeclaration().type(), null))
      error("parameter of type " + getVariableDeclaration().type().typeName() + " can not be assigned an element of type " + getExpr().type().componentType().typeName()); 
    else if(getExpr().type().isIterable() && !getExpr().type().isUnknown()) {
      MethodDecl iterator = (MethodDecl)getExpr().type().memberMethods("iterator").iterator().next();
      MethodDecl next = (MethodDecl)iterator.type().memberMethods("next").iterator().next();
      TypeDecl componentType = next.type();
      if(!componentType.assignConversionTo(getVariableDeclaration().type(), null))
        error("parameter of type " + getVariableDeclaration().type().typeName() + " can not be assigned an element of type " + componentType.typeName()); 
    }
	}
  /**
   * @ast method 
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:58
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("for (");
    getVariableDeclaration().getModifiers().toString(s);
    getVariableDeclaration().getTypeAccess().toString(s);
    s.append(" " + getVariableDeclaration().name());
    s.append(" : ");
    getExpr().toString(s);
    s.append(") ");
    getStmt().toString(s);
  }
  /**
   * @ast method 
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:24
   */
  public void createBCode(CodeGeneration gen) {
    if(getExpr().type().isArrayDecl()) {
      getExpr().createBCode(gen);
      gen.emitStoreReference(extraLocalIndex());
      IntegerLiteral.push(gen, 0);
      gen.emit(Bytecode.ISTORE).add(extraLocalIndex()+1);
      gen.addLabel(cond_label());
      gen.emit(Bytecode.ILOAD).add(extraLocalIndex()+1);
      gen.emitLoadReference(extraLocalIndex());
      gen.emit(Bytecode.ARRAYLENGTH);
      gen.emitCompare(Bytecode.IF_ICMPGE, end_label());
      gen.emitLoadReference(extraLocalIndex());
      gen.emit(Bytecode.ILOAD).add(extraLocalIndex()+1);
      gen.emit(getExpr().type().componentType().arrayLoad());
      getExpr().type().componentType().emitCastTo(gen, getVariableDeclaration().type());
      getVariableDeclaration().type().emitStoreLocal(gen, getVariableDeclaration().localNum());
      getStmt().createBCode(gen);
      gen.addLabel(update_label());
      gen.emit(Bytecode.IINC).add(extraLocalIndex()+1).add(1);
      gen.emitGoto(cond_label());
      gen.addLabel(end_label());
    }
    else {
      getExpr().createBCode(gen);
      iteratorMethod().emitInvokeMethod(gen, lookupType("java.lang", "Iterable"));
      gen.emitStoreReference(extraLocalIndex());
      gen.addLabel(cond_label());
      gen.emitLoadReference(extraLocalIndex());
      hasNextMethod().emitInvokeMethod(gen, lookupType("java.util", "Iterator"));
      gen.emitCompare(Bytecode.IFEQ, end_label());
      gen.emitLoadReference(extraLocalIndex());
      nextMethod().emitInvokeMethod(gen, lookupType("java.util", "Iterator"));
      VariableDeclaration obj = getVariableDeclaration();
      if (obj.type().isPrimitive()) {
        gen.emitCheckCast(obj.type().boxed());
        obj.type().boxed().emitCastTo(gen, obj.type());
        obj.type().emitStoreLocal(gen, obj.localNum());
      } else {
        gen.emitCheckCast(obj.type());
        gen.emitStoreReference(obj.localNum());
      }
      getStmt().createBCode(gen);
      gen.addLabel(update_label());	
      gen.emitGoto(cond_label());
      gen.addLabel(end_label());
    }
  }
  /**
   * @ast method 
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:72
   */
  private MethodDecl iteratorMethod() {
    TypeDecl typeDecl = lookupType("java.lang", "Iterable");
		for (Iterator iter = typeDecl.memberMethods("iterator").iterator(); iter.hasNext();) {
			MethodDecl m = (MethodDecl)iter.next();
			if (m.getNumParameter() == 0) {
				return m;
      }
    }
    throw new Error("Could not find java.lang.Iterable.iterator()");
  }
  /**
   * @ast method 
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:82
   */
  private MethodDecl hasNextMethod() {
    TypeDecl typeDecl = lookupType("java.util", "Iterator");
		for (Iterator iter = typeDecl.memberMethods("hasNext").iterator(); iter.hasNext();) {
			MethodDecl m = (MethodDecl)iter.next();
			if (m.getNumParameter() == 0) {
				return m;
      }
    }
    throw new Error("Could not find java.util.Collection.hasNext()");
  }
  /**
   * @ast method 
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:92
   */
  private MethodDecl nextMethod() {
    TypeDecl typeDecl = lookupType("java.util", "Iterator");
		for (Iterator iter = typeDecl.memberMethods("next").iterator(); iter.hasNext();) {
			MethodDecl m = (MethodDecl)iter.next();
			if (m.getNumParameter() == 0) {
				return m;
      }
    }
    throw new Error("Could not find java.util.Collection.next()");
  }
  /**
   * @ast method 
   * @declaredat EnhancedFor.ast:1
   */
  public EnhancedForStmt() {
    super();


  }
  /**
   * @ast method 
   * @declaredat EnhancedFor.ast:7
   */
  public EnhancedForStmt(VariableDeclaration p0, Expr p1, Stmt p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat EnhancedFor.ast:15
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat EnhancedFor.ast:21
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for VariableDeclaration
   * @apilvl high-level
   * @ast method 
   * @declaredat EnhancedFor.ast:5
   */
  public void setVariableDeclaration(VariableDeclaration node) {
    setChild(node, 0);
  }
  /**
   * Getter for VariableDeclaration
   * @apilvl high-level
   * @ast method 
   * @declaredat EnhancedFor.ast:12
   */
  public VariableDeclaration getVariableDeclaration() {
    return (VariableDeclaration)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat EnhancedFor.ast:18
   */
  public VariableDeclaration getVariableDeclarationNoTransform() {
    return (VariableDeclaration)getChildNoTransform(0);
  }
  /**
   * Setter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat EnhancedFor.ast:5
   */
  public void setExpr(Expr node) {
    setChild(node, 1);
  }
  /**
   * Getter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat EnhancedFor.ast:12
   */
  public Expr getExpr() {
    return (Expr)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat EnhancedFor.ast:18
   */
  public Expr getExprNoTransform() {
    return (Expr)getChildNoTransform(1);
  }
  /**
   * Setter for Stmt
   * @apilvl high-level
   * @ast method 
   * @declaredat EnhancedFor.ast:5
   */
  public void setStmt(Stmt node) {
    setChild(node, 2);
  }
  /**
   * Getter for Stmt
   * @apilvl high-level
   * @ast method 
   * @declaredat EnhancedFor.ast:12
   */
  public Stmt getStmt() {
    return (Stmt)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat EnhancedFor.ast:18
   */
  public Stmt getStmtNoTransform() {
    return (Stmt)getChildNoTransform(2);
  }
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:50
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet localLookupVariable(String name) {
      ASTNode$State state = state();
    SimpleSet localLookupVariable_String_value = localLookupVariable_compute(name);
    return localLookupVariable_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet localLookupVariable_compute(String name) {
		if(getVariableDeclaration().name().equals(name)) {
      return SimpleSet.emptySet.add(getVariableDeclaration());
    }
 	  return lookupVariable(name);
	}
  protected java.util.Map targetOf_ContinueStmt_values;
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:75
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean targetOf(ContinueStmt stmt) {
    Object _parameters = stmt;
    if(targetOf_ContinueStmt_values == null) targetOf_ContinueStmt_values = new java.util.HashMap(4);
    if(targetOf_ContinueStmt_values.containsKey(_parameters)) {
      return ((Boolean)targetOf_ContinueStmt_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean targetOf_ContinueStmt_value = targetOf_compute(stmt);
if(isFinal && num == state().boundariesCrossed) targetOf_ContinueStmt_values.put(_parameters, Boolean.valueOf(targetOf_ContinueStmt_value));
    return targetOf_ContinueStmt_value;
  }
  /**
   * @apilvl internal
   */
  private boolean targetOf_compute(ContinueStmt stmt) {  return !stmt.hasLabel();  }
  protected java.util.Map targetOf_BreakStmt_values;
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:76
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean targetOf(BreakStmt stmt) {
    Object _parameters = stmt;
    if(targetOf_BreakStmt_values == null) targetOf_BreakStmt_values = new java.util.HashMap(4);
    if(targetOf_BreakStmt_values.containsKey(_parameters)) {
      return ((Boolean)targetOf_BreakStmt_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean targetOf_BreakStmt_value = targetOf_compute(stmt);
if(isFinal && num == state().boundariesCrossed) targetOf_BreakStmt_values.put(_parameters, Boolean.valueOf(targetOf_BreakStmt_value));
    return targetOf_BreakStmt_value;
  }
  /**
   * @apilvl internal
   */
  private boolean targetOf_compute(BreakStmt stmt) {  return !stmt.hasLabel();  }
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:79
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean canCompleteNormally() {
    if(canCompleteNormally_computed) {
      return canCompleteNormally_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    canCompleteNormally_value = canCompleteNormally_compute();
if(isFinal && num == state().boundariesCrossed) canCompleteNormally_computed = true;
    return canCompleteNormally_value;
  }
  /**
   * @apilvl internal
   */
  private boolean canCompleteNormally_compute() {  return reachable();  }
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:83
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafter(Variable v) {
    Object _parameters = v;
    if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
    if(isDAafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDAafter_Variable_value = isDAafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
    return isDAafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafter_compute(Variable v) {
    if(!getExpr().isDAafter(v))
      return false;
    /*
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    */
    return true;
  }
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:99
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafter(Variable v) {
    Object _parameters = v;
    if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
    if(isDUafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDUafter_Variable_value = isDUafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
    return isDUafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafter_compute(Variable v) {
    if(!getExpr().isDUafter(v))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDUafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:114
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean continueLabel() {
      ASTNode$State state = state();
    boolean continueLabel_value = continueLabel_compute();
    return continueLabel_value;
  }
  /**
   * @apilvl internal
   */
  private boolean continueLabel_compute() {  return true;  }
  /**
   * @apilvl internal
   */
  protected boolean cond_label_computed = false;
  /**
   * @apilvl internal
   */
  protected int cond_label_value;
  /**
   * @attribute syn
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:12
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int cond_label() {
    if(cond_label_computed) {
      return cond_label_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    cond_label_value = cond_label_compute();
if(isFinal && num == state().boundariesCrossed) cond_label_computed = true;
    return cond_label_value;
  }
  /**
   * @apilvl internal
   */
  private int cond_label_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean update_label_computed = false;
  /**
   * @apilvl internal
   */
  protected int update_label_value;
  /**
   * @attribute syn
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:13
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int update_label() {
    if(update_label_computed) {
      return update_label_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    update_label_value = update_label_compute();
if(isFinal && num == state().boundariesCrossed) update_label_computed = true;
    return update_label_value;
  }
  /**
   * @apilvl internal
   */
  private int update_label_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean end_label_computed = false;
  /**
   * @apilvl internal
   */
  protected int end_label_value;
  /**
   * @attribute syn
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:14
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int end_label() {
    if(end_label_computed) {
      return end_label_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    end_label_value = end_label_compute();
if(isFinal && num == state().boundariesCrossed) end_label_computed = true;
    return end_label_value;
  }
  /**
   * @apilvl internal
   */
  private int end_label_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean extraLocalIndex_computed = false;
  /**
   * @apilvl internal
   */
  protected int extraLocalIndex_value;
  /**
   * @attribute syn
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:16
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int extraLocalIndex() {
    if(extraLocalIndex_computed) {
      return extraLocalIndex_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    extraLocalIndex_value = extraLocalIndex_compute();
if(isFinal && num == state().boundariesCrossed) extraLocalIndex_computed = true;
    return extraLocalIndex_value;
  }
  /**
   * @apilvl internal
   */
  private int extraLocalIndex_compute() {  return localNum();  }
  /**
   * @attribute syn
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:21
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int break_label() {
      ASTNode$State state = state();
    int break_label_value = break_label_compute();
    return break_label_value;
  }
  /**
   * @apilvl internal
   */
  private int break_label_compute() {  return end_label();  }
  /**
   * @attribute syn
   * @aspect EnhancedForToBytecode
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:22
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int continue_label() {
      ASTNode$State state = state();
    int continue_label_value = continue_label_compute();
    return continue_label_value;
  }
  /**
   * @apilvl internal
   */
  private int continue_label_compute() {  return update_label();  }
  /**
   * @attribute inh
   * @aspect EnhancedFor
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:38
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupVariable(String name) {
      ASTNode$State state = state();
    SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
    return lookupVariable_String_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:41
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getStmtNoTransform()) {
      return localLookupVariable(name);
    }
    if(caller == getExprNoTransform()) {
      return localLookupVariable(name);
    }
    if(caller == getVariableDeclarationNoTransform()) {
      return localLookupVariable(name);
    }
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:43
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getVariableDeclarationNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:48
   * @apilvl internal
   */
  public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
    if(caller == getStmtNoTransform()) {
      return this;
    }
    if(caller == getExprNoTransform()) {
      return this;
    }
    if(caller == getVariableDeclarationNoTransform()) {
      return this;
    }
    return getParent().Define_VariableScope_outerScope(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:71
   * @apilvl internal
   */
  public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
    if(caller == getVariableDeclarationNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_isMethodParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:72
   * @apilvl internal
   */
  public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
    if(caller == getVariableDeclarationNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_isConstructorParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:73
   * @apilvl internal
   */
  public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
    if(caller == getVariableDeclarationNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:80
   * @apilvl internal
   */
  public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
    if(caller == getStmtNoTransform()) {
      return reachable();
    }
    return getParent().Define_boolean_reachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:97
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getStmtNoTransform()) {
      return getExpr().isDAafter(v);
    }
    if(caller == getExprNoTransform()) {
      return v == getVariableDeclaration() || isDAbefore(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:111
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getStmtNoTransform()) {
      return getExpr().isDUafter(v);
    }
    if(caller == getExprNoTransform()) {
      return v != getVariableDeclaration() && isDUbefore(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\EnhancedFor.jrag:113
   * @apilvl internal
   */
  public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
    if(caller == getStmtNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_insideLoop(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnhancedForCodegen.jrag:18
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(caller == getStmtNoTransform()) {
      return getVariableDeclaration().localNum() + getVariableDeclaration().type().size();
    }
    if(caller == getVariableDeclarationNoTransform()) {
      return localNum() + (getExpr().type().isArrayDecl() ? 2 : 1);
    }
    return getParent().Define_int_localNum(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
