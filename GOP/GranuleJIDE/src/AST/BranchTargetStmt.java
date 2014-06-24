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
 * @declaredat java.ast:206
 */
public abstract class BranchTargetStmt extends Stmt implements Cloneable, BranchPropagation {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    reachableBreak_computed = false;
    reachableContinue_computed = false;
    targetBranches_computed = false;
    targetBranches_value = null;
    escapedBranches_computed = false;
    escapedBranches_value = null;
    branches_computed = false;
    branches_value = null;
    targetContinues_computed = false;
    targetContinues_value = null;
    targetBreaks_computed = false;
    targetBreaks_value = null;
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
  public BranchTargetStmt clone() throws CloneNotSupportedException {
    BranchTargetStmt node = (BranchTargetStmt)super.clone();
    node.reachableBreak_computed = false;
    node.reachableContinue_computed = false;
    node.targetBranches_computed = false;
    node.targetBranches_value = null;
    node.escapedBranches_computed = false;
    node.escapedBranches_value = null;
    node.branches_computed = false;
    node.branches_value = null;
    node.targetContinues_computed = false;
    node.targetContinues_value = null;
    node.targetBreaks_computed = false;
    node.targetBreaks_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public BranchTargetStmt() {
    super();


  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:10
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:16
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * @ast method 
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:58
   */
  public void collectBranches(Collection c) {
    c.addAll(escapedBranches());
  }
  /**
   * @ast method 
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:157
   */
  public Stmt branchTarget(Stmt branchStmt) {
    if(targetBranches().contains(branchStmt))
      return this;
    return super.branchTarget(branchStmt);
  }
  /**
   * @ast method 
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:195
   */
  public void collectFinally(Stmt branchStmt, ArrayList list) {
    if(targetBranches().contains(branchStmt))
      return;
    super.collectFinally(branchStmt, list);
  }
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:40
   */
  @SuppressWarnings({"unchecked", "cast"})
  public abstract boolean targetOf(ContinueStmt stmt);
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:41
   */
  @SuppressWarnings({"unchecked", "cast"})
  public abstract boolean targetOf(BreakStmt stmt);
  /**
   * @apilvl internal
   */
  protected boolean reachableBreak_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean reachableBreak_value;
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:52
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean reachableBreak() {
    if(reachableBreak_computed) {
      return reachableBreak_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    reachableBreak_value = reachableBreak_compute();
if(isFinal && num == state().boundariesCrossed) reachableBreak_computed = true;
    return reachableBreak_value;
  }
  /**
   * @apilvl internal
   */
  private boolean reachableBreak_compute() {
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(stmt.reachable())
        return true;
    }
    return false;
  }
  /**
   * @apilvl internal
   */
  protected boolean reachableContinue_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean reachableContinue_value;
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:94
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean reachableContinue() {
    if(reachableContinue_computed) {
      return reachableContinue_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    reachableContinue_value = reachableContinue_compute();
if(isFinal && num == state().boundariesCrossed) reachableContinue_computed = true;
    return reachableContinue_value;
  }
  /**
   * @apilvl internal
   */
  private boolean reachableContinue_compute() {
    for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
      Stmt stmt = (Stmt)iter.next();
      if(stmt.reachable())
        return true;
    }
    return false;
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:83
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
    for(Iterator iter = branches().iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof ContinueStmt && targetOf((ContinueStmt)o))
        set.add(o);
      if(o instanceof BreakStmt && targetOf((BreakStmt)o))
        set.add(o);
    }
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:95
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
    for(Iterator iter = branches().iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof ContinueStmt && !targetOf((ContinueStmt)o))
        set.add(o);
      if(o instanceof BreakStmt && !targetOf((BreakStmt)o))
        set.add(o);
      if(o instanceof ReturnStmt)
        set.add(o);
    }
    return set;
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:109
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
    super.collectBranches(set);
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean targetContinues_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection targetContinues_value;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:216
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection targetContinues() {
    if(targetContinues_computed) {
      return targetContinues_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    targetContinues_value = targetContinues_compute();
if(isFinal && num == state().boundariesCrossed) targetContinues_computed = true;
    return targetContinues_value;
  }
  /**
   * @apilvl internal
   */
  private Collection targetContinues_compute() {
    HashSet set = new HashSet();
    for(Iterator iter = targetBranches().iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof ContinueStmt)
        set.add(o);
    }
    if(getParent() instanceof LabeledStmt) {
      for(Iterator iter = ((LabeledStmt)getParent()).targetBranches().iterator(); iter.hasNext(); ) {
        Object o = iter.next();
        if(o instanceof ContinueStmt)
          set.add(o);
      }
    }
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean targetBreaks_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection targetBreaks_value;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:233
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection targetBreaks() {
    if(targetBreaks_computed) {
      return targetBreaks_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    targetBreaks_value = targetBreaks_compute();
if(isFinal && num == state().boundariesCrossed) targetBreaks_computed = true;
    return targetBreaks_value;
  }
  /**
   * @apilvl internal
   */
  private Collection targetBreaks_compute() {
    HashSet set = new HashSet();
    for(Iterator iter = targetBranches().iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof BreakStmt)
        set.add(o);
    }
    return set;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
