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
 * @declaredat java.ast:231
 */
public class TryStmt extends Stmt implements Cloneable, FinallyHost {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    branches_computed = false;
    branches_value = null;
    branchesFromFinally_computed = false;
    branchesFromFinally_value = null;
    targetBranches_computed = false;
    targetBranches_value = null;
    escapedBranches_computed = false;
    escapedBranches_value = null;
    isDAafter_Variable_values = null;
    isDUbefore_Variable_values = null;
    isDUafter_Variable_values = null;
    catchableException_TypeDecl_values = null;
    canCompleteNormally_computed = false;
    label_begin_computed = false;
    label_block_end_computed = false;
    label_end_computed = false;
    label_finally_computed = false;
    label_finally_block_computed = false;
    label_exception_handler_computed = false;
    label_catch_end_computed = false;
    handlesException_TypeDecl_values = null;
    typeError_computed = false;
    typeError_value = null;
    typeRuntimeException_computed = false;
    typeRuntimeException_value = null;
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
  public TryStmt clone() throws CloneNotSupportedException {
    TryStmt node = (TryStmt)super.clone();
    node.branches_computed = false;
    node.branches_value = null;
    node.branchesFromFinally_computed = false;
    node.branchesFromFinally_value = null;
    node.targetBranches_computed = false;
    node.targetBranches_value = null;
    node.escapedBranches_computed = false;
    node.escapedBranches_value = null;
    node.isDAafter_Variable_values = null;
    node.isDUbefore_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.catchableException_TypeDecl_values = null;
    node.canCompleteNormally_computed = false;
    node.label_begin_computed = false;
    node.label_block_end_computed = false;
    node.label_end_computed = false;
    node.label_finally_computed = false;
    node.label_finally_block_computed = false;
    node.label_exception_handler_computed = false;
    node.label_catch_end_computed = false;
    node.handlesException_TypeDecl_values = null;
    node.typeError_computed = false;
    node.typeError_value = null;
    node.typeRuntimeException_computed = false;
    node.typeRuntimeException_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TryStmt copy() {
      try {
        TryStmt node = (TryStmt)clone();
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
  public TryStmt fullCopy() {
    TryStmt res = (TryStmt)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:61
   */
  public void collectBranches(Collection c) {
    c.addAll(escapedBranches());
  }
  /**
   * @ast method 
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:162
   */
  public Stmt branchTarget(Stmt branchStmt) {
    if(targetBranches().contains(branchStmt))
      return this;
    return super.branchTarget(branchStmt);
  }
  /**
   * @ast method 
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:200
   */
  public void collectFinally(Stmt branchStmt, ArrayList list) {
    if(hasFinally() && !branchesFromFinally().contains(branchStmt))
      list.add(this);
    if(targetBranches().contains(branchStmt))
      return;
    super.collectFinally(branchStmt, list);
  }
  /**
   * @ast method 
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:219
   */
  protected boolean reachedException(TypeDecl type) {
    boolean found = false;
    // found is true if the exception type is caught by a catch clause
    for(int i = 0; i < getNumCatchClause() && !found; i++)
      if(getCatchClause(i).handles(type))
        found = true;
    // if an exception is thrown in the block and the exception is not caught and
    // either there is no finally block or the finally block can complete normally
    if(!found && (!hasFinally() || getFinally().canCompleteNormally()) )
      if(getBlock().reachedException(type))
        return true;
    // even if the exception is caught by the catch clauses they may 
    // throw new exceptions
    for(int i = 0; i < getNumCatchClause() && found; i++)
      if(getCatchClause(i).reachedException(type))
        return true;
    return hasFinally() && getFinally().reachedException(type);
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:837
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("try ");
    getBlock().toString(s);
    for(int i = 0; i < getNumCatchClause(); i++) {
      s.append(indent());
      getCatchClause(i).toString(s);
    }
    if(hasFinally()) {
      s.append(indent());
      s.append("finally ");
      getFinally().toString(s);
    }
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:842
   */
  public void emitExceptionHandler(CodeGeneration gen) {
    // add 1 to stack depth
    gen.changeStackDepth(1);
    int num = localNum();
    gen.emitStoreReference(num);
    gen.emitJsr(label_finally_block());
    gen.emitLoadReference(num);
    gen.emit(Bytecode.ATHROW);
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:852
   */
  public void emitFinallyBlock(CodeGeneration gen) {
    // add 1 to stack depth
    gen.changeStackDepth(1);
    int num = localNum()+1;
    gen.emitStoreReference(num);
    getFinally().createBCode(gen);
    if(num < 256)
      gen.emit(Bytecode.RET).add(num);
    else
      gen.emit(Bytecode.WIDE).emit(Bytecode.RET).add2(num);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2071
   */
  public void createBCode(CodeGeneration gen) {
    super.createBCode(gen);
    gen.addLabel(label_begin());
    getBlock().createBCode(gen);
    if(getBlock().canCompleteNormally())
      gen.emitGoto(label_finally());
    gen.addLabel(label_block_end());
    for(int i = 0; i < getNumCatchClause(); i++) {
      getCatchClause(i).createBCode(gen);
      if(getCatchClause(i).getBlock().canCompleteNormally()) {
        if(!hasFinally())
          gen.emitGoto(label_finally());
        else
          gen.emitGoto(label_catch_end());
      }
    }
    
    gen.addLabel(label_catch_end());
    if(hasFinally() && getNumCatchClause() > 0) {
      gen.emitJsr(label_finally_block());
      if(canCompleteNormally())
        gen.emitGoto(label_end());
    }
    
    gen.addLabel(label_finally());
    if(hasFinally()) {
      if(getBlock().canCompleteNormally()) {
        gen.emitJsr(label_finally_block());
        if(canCompleteNormally())
          gen.emitGoto(label_end());
      }
      gen.addLabel(label_exception_handler());
      emitExceptionHandler(gen);
      gen.addLabel(label_finally_block());
      emitFinallyBlock(gen);
    }
    gen.addLabel(label_end());
    gen.createExceptionTable(this);
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public TryStmt() {
    super();

    setChild(new List(), 1);
    setChild(new Opt(), 2);

  }
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  public TryStmt(Block p0, List<CatchClause> p1, Opt<Block> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:17
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:23
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBlock(Block node) {
    setChild(node, 0);
  }
  /**
   * Getter for Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Block getBlock() {
    return (Block)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Block getBlockNoTransform() {
    return (Block)getChildNoTransform(0);
  }
  /**
   * Setter for CatchClauseList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setCatchClauseList(List<CatchClause> list) {
    setChild(list, 1);
  }
  /**
   * @return number of children in CatchClauseList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumCatchClause() {
    return getCatchClauseList().getNumChild();
  }
  /**
   * Getter for child in list CatchClauseList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public CatchClause getCatchClause(int i) {
    return (CatchClause)getCatchClauseList().getChild(i);
  }
  /**
   * Add element to list CatchClauseList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addCatchClause(CatchClause node) {
    List<CatchClause> list = (parent == null || state == null) ? getCatchClauseListNoTransform() : getCatchClauseList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addCatchClauseNoTransform(CatchClause node) {
    List<CatchClause> list = getCatchClauseListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list CatchClauseList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setCatchClause(CatchClause node, int i) {
    List<CatchClause> list = getCatchClauseList();
    list.setChild(node, i);
  }
  /**
   * Getter for CatchClause list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<CatchClause> getCatchClauses() {
    return getCatchClauseList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<CatchClause> getCatchClausesNoTransform() {
    return getCatchClauseListNoTransform();
  }
  /**
   * Getter for list CatchClauseList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<CatchClause> getCatchClauseList() {
    List<CatchClause> list = (List<CatchClause>)getChild(1);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<CatchClause> getCatchClauseListNoTransform() {
    return (List<CatchClause>)getChildNoTransform(1);
  }
  /**
   * Setter for FinallyOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setFinallyOpt(Opt<Block> opt) {
    setChild(opt, 2);
  }
  /**
   * Does this node have a Finally child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasFinally() {
    return getFinallyOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child Finally
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Block getFinally() {
    return (Block)getFinallyOpt().getChild(0);
  }
  /**
   * Setter for optional child Finally
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setFinally(Block node) {
    getFinallyOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getFinallyOpt() {
    return (Opt<Block>)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getFinallyOptNoTransform() {
    return (Opt<Block>)getChildNoTransform(2);
  }
  /**
   * @apilvl internal
   */
  protected boolean branches_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection branches_value;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:116
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection branches() {
    if(branches_computed) {
      return branches_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    branches_value = branches_compute();
if(isFinal && num == state().boundariesCrossed) branches_computed = true;
    return branches_value;
  }
  /**
   * @apilvl internal
   */
  private Collection branches_compute() {
    HashSet set = new HashSet();
    getBlock().collectBranches(set);
    for(int i = 0; i < getNumCatchClause(); i++)
      getCatchClause(i).collectBranches(set);
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean branchesFromFinally_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection branchesFromFinally_value;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:124
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection branchesFromFinally() {
    if(branchesFromFinally_computed) {
      return branchesFromFinally_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    branchesFromFinally_value = branchesFromFinally_compute();
if(isFinal && num == state().boundariesCrossed) branchesFromFinally_computed = true;
    return branchesFromFinally_value;
  }
  /**
   * @apilvl internal
   */
  private Collection branchesFromFinally_compute() {
    HashSet set = new HashSet();
    if(hasFinally())
      getFinally().collectBranches(set);
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean targetBranches_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection targetBranches_value;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:132
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection targetBranches() {
    if(targetBranches_computed) {
      return targetBranches_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    targetBranches_value = targetBranches_compute();
if(isFinal && num == state().boundariesCrossed) targetBranches_computed = true;
    return targetBranches_value;
  }
  /**
   * @apilvl internal
   */
  private Collection targetBranches_compute() {
    HashSet set = new HashSet();
    if(hasFinally() && !getFinally().canCompleteNormally())
      set.addAll(branches());
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean escapedBranches_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection escapedBranches_value;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:140
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection escapedBranches() {
    if(escapedBranches_computed) {
      return escapedBranches_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    escapedBranches_value = escapedBranches_compute();
if(isFinal && num == state().boundariesCrossed) escapedBranches_computed = true;
    return escapedBranches_value;
  }
  /**
   * @apilvl internal
   */
  private Collection escapedBranches_compute() {
    HashSet set = new HashSet();
    if(hasFinally())
      set.addAll(branchesFromFinally());
    if(!hasFinally() || getFinally().canCompleteNormally())
      set.addAll(branches());
    return set;
  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:667
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
    // 16.2.15 4th bullet
    if(!hasFinally()) {
      if(!getBlock().isDAafter(v))
        return false;
      for(int i = 0; i < getNumCatchClause(); i++)
        if(!getCatchClause(i).getBlock().isDAafter(v))
          return false;
      return true;
    }
    else {
      // 16.2.15 5th bullet
      if(getFinally().isDAafter(v))
        return true;
      if(!getBlock().isDAafter(v))
        return false;
      for(int i = 0; i < getNumCatchClause(); i++)
        if(!getCatchClause(i).getBlock().isDAafter(v))
          return false;
      return true;
    }
  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:918
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafterFinally(Variable v) {
      ASTNode$State state = state();
    boolean isDUafterFinally_Variable_value = isDUafterFinally_compute(v);
    return isDUafterFinally_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafterFinally_compute(Variable v) {  return getFinally().isDUafter(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:921
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafterFinally(Variable v) {
      ASTNode$State state = state();
    boolean isDAafterFinally_Variable_value = isDAafterFinally_compute(v);
    return isDAafterFinally_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafterFinally_compute(Variable v) {  return getFinally().isDAafter(v);  }
  protected java.util.Map isDUbefore_Variable_values;
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1189
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUbefore(Variable v) {
    Object _parameters = v;
    if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if(isDUbefore_Variable_values.containsKey(_parameters)) {
      Object _o = isDUbefore_Variable_values.get(_parameters);
      if(!(_o instanceof ASTNode$State.CircularValue)) {
        return ((Boolean)_o).booleanValue();
      }
      else
        _value = (ASTNode$State.CircularValue)_o;
    }
    else {
      _value = new ASTNode$State.CircularValue();
      isDUbefore_Variable_values.put(_parameters, _value);
      _value.value = Boolean.valueOf(true);
    }
    ASTNode$State state = state();
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
      int num = state.boundariesCrossed;
      boolean isFinal = this.is$Final();
      boolean new_isDUbefore_Variable_value;
      do {
        _value.visited = new Integer(state.CIRCLE_INDEX);
        state.CHANGE = false;
        new_isDUbefore_Variable_value = isDUbefore_compute(v);
        if (new_isDUbefore_Variable_value!=((Boolean)_value.value).booleanValue()) {
          state.CHANGE = true;
          _value.value = Boolean.valueOf(new_isDUbefore_Variable_value);
        }
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
        isDUbefore_Variable_values.put(_parameters, new_isDUbefore_Variable_value);
      }
      else {
        isDUbefore_Variable_values.remove(_parameters);
      state.RESET_CYCLE = true;
      isDUbefore_compute(v);
      state.RESET_CYCLE = false;
      }
      state.IN_CIRCLE = false; 
      return new_isDUbefore_Variable_value;
    }
    if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
      _value.visited = new Integer(state.CIRCLE_INDEX);
      boolean new_isDUbefore_Variable_value = isDUbefore_compute(v);
      if (state.RESET_CYCLE) {
        isDUbefore_Variable_values.remove(_parameters);
      }
      else if (new_isDUbefore_Variable_value!=((Boolean)_value.value).booleanValue()) {
        state.CHANGE = true;
        _value.value = new_isDUbefore_Variable_value;
      }
      return new_isDUbefore_Variable_value;
    }
    return ((Boolean)_value.value).booleanValue();
  }
  /**
   * @apilvl internal
   */
  private boolean isDUbefore_compute(Variable v) {  return super.isDUbefore(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1225
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
    // 16.2.14 4th bullet
    if(!hasFinally()) {
      if(!getBlock().isDUafter(v))
        return false;
      for(int i = 0; i < getNumCatchClause(); i++)
        if(!getCatchClause(i).getBlock().isDUafter(v))
          return false;
      return true;
    }
    else
      return getFinally().isDUafter(v);
  }
  protected java.util.Map catchableException_TypeDecl_values;
  /**
   * The block of the try statement can throw an exception of
   * a type assignable to the given type.
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:208
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean catchableException(TypeDecl type) {
    Object _parameters = type;
    if(catchableException_TypeDecl_values == null) catchableException_TypeDecl_values = new java.util.HashMap(4);
    if(catchableException_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)catchableException_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean catchableException_TypeDecl_value = catchableException_compute(type);
if(isFinal && num == state().boundariesCrossed) catchableException_TypeDecl_values.put(_parameters, Boolean.valueOf(catchableException_TypeDecl_value));
    return catchableException_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean catchableException_compute(TypeDecl type) {  return getBlock().reachedException(type);  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:116
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
  private boolean canCompleteNormally_compute() {
     boolean anyCatchClauseCompleteNormally = false;
     for(int i = 0; i < getNumCatchClause() && !anyCatchClauseCompleteNormally; i++)
       anyCatchClauseCompleteNormally = getCatchClause(i).getBlock().canCompleteNormally();
     return (getBlock().canCompleteNormally() || anyCatchClauseCompleteNormally) &&
       (!hasFinally() || getFinally().canCompleteNormally());
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1988
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
  private int break_label_compute() {  return label_finally();  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2006
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
  private int continue_label_compute() {  return label_finally();  }
  /**
   * @apilvl internal
   */
  protected boolean label_begin_computed = false;
  /**
   * @apilvl internal
   */
  protected int label_begin_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2063
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label_begin() {
    if(label_begin_computed) {
      return label_begin_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    label_begin_value = label_begin_compute();
if(isFinal && num == state().boundariesCrossed) label_begin_computed = true;
    return label_begin_value;
  }
  /**
   * @apilvl internal
   */
  private int label_begin_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean label_block_end_computed = false;
  /**
   * @apilvl internal
   */
  protected int label_block_end_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2064
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label_block_end() {
    if(label_block_end_computed) {
      return label_block_end_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    label_block_end_value = label_block_end_compute();
if(isFinal && num == state().boundariesCrossed) label_block_end_computed = true;
    return label_block_end_value;
  }
  /**
   * @apilvl internal
   */
  private int label_block_end_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean label_end_computed = false;
  /**
   * @apilvl internal
   */
  protected int label_end_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2065
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label_end() {
    if(label_end_computed) {
      return label_end_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    label_end_value = label_end_compute();
if(isFinal && num == state().boundariesCrossed) label_end_computed = true;
    return label_end_value;
  }
  /**
   * @apilvl internal
   */
  private int label_end_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean label_finally_computed = false;
  /**
   * @apilvl internal
   */
  protected int label_finally_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2066
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label_finally() {
    if(label_finally_computed) {
      return label_finally_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    label_finally_value = label_finally_compute();
if(isFinal && num == state().boundariesCrossed) label_finally_computed = true;
    return label_finally_value;
  }
  /**
   * @apilvl internal
   */
  private int label_finally_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean label_finally_block_computed = false;
  /**
   * @apilvl internal
   */
  protected int label_finally_block_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2067
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label_finally_block() {
    if(label_finally_block_computed) {
      return label_finally_block_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    label_finally_block_value = label_finally_block_compute();
if(isFinal && num == state().boundariesCrossed) label_finally_block_computed = true;
    return label_finally_block_value;
  }
  /**
   * @apilvl internal
   */
  private int label_finally_block_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean label_exception_handler_computed = false;
  /**
   * @apilvl internal
   */
  protected int label_exception_handler_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2068
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label_exception_handler() {
    if(label_exception_handler_computed) {
      return label_exception_handler_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    label_exception_handler_value = label_exception_handler_compute();
if(isFinal && num == state().boundariesCrossed) label_exception_handler_computed = true;
    return label_exception_handler_value;
  }
  /**
   * @apilvl internal
   */
  private int label_exception_handler_compute() {  return hostType().constantPool().newLabel();  }
  /**
   * @apilvl internal
   */
  protected boolean label_catch_end_computed = false;
  /**
   * @apilvl internal
   */
  protected int label_catch_end_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:2069
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int label_catch_end() {
    if(label_catch_end_computed) {
      return label_catch_end_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    label_catch_end_value = label_catch_end_compute();
if(isFinal && num == state().boundariesCrossed) label_catch_end_computed = true;
    return label_catch_end_value;
  }
  /**
   * @apilvl internal
   */
  private int label_catch_end_compute() {  return hostType().constantPool().newLabel();  }
  protected java.util.Map handlesException_TypeDecl_values;
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:35
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean handlesException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if(handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
    if(handlesException_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)handlesException_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
if(isFinal && num == state().boundariesCrossed) handlesException_TypeDecl_values.put(_parameters, Boolean.valueOf(handlesException_TypeDecl_value));
    return handlesException_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  protected boolean typeError_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeError_value;
  /**
   * @attribute inh
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:139
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeError() {
    if(typeError_computed) {
      return typeError_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeError_value = getParent().Define_TypeDecl_typeError(this, null);
if(isFinal && num == state().boundariesCrossed) typeError_computed = true;
    return typeError_value;
  }
  /**
   * @apilvl internal
   */
  protected boolean typeRuntimeException_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeRuntimeException_value;
  /**
   * @attribute inh
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:140
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeRuntimeException() {
    if(typeRuntimeException_computed) {
      return typeRuntimeException_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeRuntimeException_value = getParent().Define_TypeDecl_typeRuntimeException(this, null);
if(isFinal && num == state().boundariesCrossed) typeRuntimeException_computed = true;
    return typeRuntimeException_value;
  }
  /**
   * @attribute inh
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:840
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeThrowable() {
      ASTNode$State state = state();
    TypeDecl typeThrowable_value = getParent().Define_TypeDecl_typeThrowable(this, null);
    return typeThrowable_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:666
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getFinallyOptNoTransform()) {
      return isDAbefore(v);
    }
    if(caller == getCatchClauseListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return getBlock().isDAbefore(v);
    }
    if(caller == getBlockNoTransform()) {
      return isDAbefore(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1216
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getFinallyOptNoTransform()){
    if(!getBlock().isDUeverywhere(v))
      return false;
    for(int i = 0; i < getNumCatchClause(); i++)
      if(!getCatchClause(i).getBlock().unassignedEverywhere(v, this))
        return false;
    return true;
  }
    if(caller == getCatchClauseListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    if(!getBlock().isDUafter(v))
      return false;
    if(!getBlock().isDUeverywhere(v))
      return false;
    return true;
  }
}
    if(caller == getBlockNoTransform()) {
      return isDUbefore(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:188
   * @apilvl internal
   */
  public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(caller == getBlockNoTransform()){
    for(int i = 0; i < getNumCatchClause(); i++)
      if(getCatchClause(i).handles(exceptionType))
        return true;
    if(hasFinally() && !getFinally().canCompleteNormally())
      return true;
    return handlesException(exceptionType);
  }
    if(caller == getCatchClauseListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    if(hasFinally() && !getFinally().canCompleteNormally())
      return true;
    return handlesException(exceptionType);
  }
}
    return getParent().Define_boolean_handlesException(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:124
   * @apilvl internal
   */
  public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
    if(caller == getFinallyOptNoTransform()) {
      return reachable();
    }
    if(caller == getBlockNoTransform()) {
      return reachable();
    }
    return getParent().Define_boolean_reachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:128
   * @apilvl internal
   */
  public boolean Define_boolean_reachableCatchClause(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(caller == getCatchClauseListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    for(int i = 0; i < childIndex; i++)
      if(getCatchClause(i).handles(exceptionType))
        return false;
    if(catchableException(exceptionType))
      return true;
    if(exceptionType.mayCatch(typeError()) || exceptionType.mayCatch(typeRuntimeException()))
      return true;
    return false;
  }
}
    return getParent().Define_boolean_reachableCatchClause(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:157
   * @apilvl internal
   */
  public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
    if(caller == getFinallyOptNoTransform()) {
      return reachable();
    }
    if(caller == getCatchClauseListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return reachable();
    }
    if(caller == getBlockNoTransform()) {
      return reachable();
    }
    return getParent().Define_boolean_reportUnreachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:139
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(caller == getFinallyOptNoTransform()) {
      return localNum() + 2;
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
