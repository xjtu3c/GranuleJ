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
 * @declaredat java.ast:213
 */
public class SwitchStmt extends BranchTargetStmt implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    targetOf_ContinueStmt_values = null;
    targetOf_BreakStmt_values = null;
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    canCompleteNormally_computed = false;
    defaultCase_computed = false;
    defaultCase_value = null;
    end_label_computed = false;
    typeInt_computed = false;
    typeInt_value = null;
    typeLong_computed = false;
    typeLong_value = null;
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
  public SwitchStmt clone() throws CloneNotSupportedException {
    SwitchStmt node = (SwitchStmt)super.clone();
    node.targetOf_ContinueStmt_values = null;
    node.targetOf_BreakStmt_values = null;
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.canCompleteNormally_computed = false;
    node.defaultCase_computed = false;
    node.defaultCase_value = null;
    node.end_label_computed = false;
    node.typeInt_computed = false;
    node.typeInt_value = null;
    node.typeLong_computed = false;
    node.typeLong_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SwitchStmt copy() {
      try {
        SwitchStmt node = (SwitchStmt)clone();
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
  public SwitchStmt fullCopy() {
    SwitchStmt res = (SwitchStmt)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:684
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("switch (");
    getExpr().toString(s);
    s.append(")");
    getBlock().toString(s);
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1880
   */
  private int emitPad(CodeGeneration gen) {
    int pad = (4 - (gen.pos() % 4)) % 4;
    for(int i = 0; i < pad; i++)
      gen.emit(Bytecode.NOP);
    if(gen.pos() % 4 != 0)
      throw new Error("Switch not at 4-byte boundary:" + gen.pos());
    return pad;
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1888
   */
  private int defaultOffset(CodeGeneration gen, int switch_label) {
    boolean hasDefault = defaultCase() != null;
    if(hasDefault) {
      int offset = gen.addressOf(defaultCase().label(gen))
        - gen.addressOf(switch_label);
      return offset;
    }
    return 0;
  }
  /**
   * @ast method 
   * @aspect EnumsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnumsCodegen.jrag:17
   */
  public void transformation() {
    if(getExpr().type().isEnumDecl()) {
      TypeDecl type = getExpr().type();
      hostType().createEnumArray(type);
      hostType().createEnumMethod(type);
      setExpr(
        hostType().createEnumMethod(type).createBoundAccess(new List()).qualifiesAccess(
        new ArrayAccess(
          ((Expr)getExpr().fullCopy()).qualifiesAccess(new MethodAccess("ordinal", new List()))
        ))
      );
    }
    super.transformation();
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public SwitchStmt() {
    super();


  }
  /**
   * @ast method 
   * @declaredat java.ast:7
   */
  public SwitchStmt(Expr p0, Block p1) {
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
    return false;
  }
  /**
   * Setter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setExpr(Expr node) {
    setChild(node, 0);
  }
  /**
   * Getter for Expr
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Expr getExpr() {
    return (Expr)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Expr getExprNoTransform() {
    return (Expr)getChildNoTransform(0);
  }
  /**
   * Setter for Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBlock(Block node) {
    setChild(node, 1);
  }
  /**
   * Getter for Block
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Block getBlock() {
    return (Block)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Block getBlockNoTransform() {
    return (Block)getChildNoTransform(1);
  }
  /**
   * @ast method 
   * @aspect Enums
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Enums.jrag:509
   */
    public void typeCheck() {
     TypeDecl type = getExpr().type();
    if((!type.isIntegralType() || type.isLong()) && !type.isEnumDecl())
      error("Switch expression must be of char, byte, short, int, or enum type");
  }
  /**
   * @ast method 
   * @aspect AutoBoxingCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AutoBoxingCodegen.jrag:125
   */
    public void createBCode(CodeGeneration gen) {
    super.createBCode(gen);
    int cond_label = hostType().constantPool().newLabel();
    int switch_label = hostType().constantPool().newLabel();

    gen.emitGoto(cond_label);
    getBlock().createBCode(gen);
    if(canCompleteNormally())
      gen.emitGoto(end_label());
    gen.addLabel(cond_label);
    getExpr().createBCode(gen);
    if(getExpr().type().isReferenceType())
      getExpr().type().emitUnboxingOperation(gen);

    TreeMap map = new TreeMap();
    for(int i = 0; i < getBlock().getNumStmt(); i++) {
      if(getBlock().getStmt(i) instanceof ConstCase) {
        ConstCase ca = (ConstCase)getBlock().getStmt(i);
        map.put(new Integer(ca.getValue().constant().intValue()), ca);
      }        
    }

    long low = map.isEmpty() ? 0 : ((Integer)map.firstKey()).intValue();
    long high = map.isEmpty() ? 0 : ((Integer)map.lastKey()).intValue();

    long tableSwitchSize = 8L + (high - low + 1L) * 4L;
    long lookupSwitchSize = 4L + map.size() * 8L;

    gen.addLabel(switch_label);
    if(tableSwitchSize < lookupSwitchSize) {
      gen.emit(Bytecode.TABLESWITCH);
      int pad = emitPad(gen);
      int defaultOffset = defaultOffset(gen, switch_label);
      if(defaultOffset == 0) {
        defaultOffset = 1 + pad + 4 + 4 + 4 + 4 * (int)(high - low + 1);
      }
      gen.add4(defaultOffset);
      gen.add4((int)low);
      gen.add4((int)high);
      for(long i = low; i <= high; i++) {
        ConstCase ca = (ConstCase)map.get(new Integer((int)i));
        if(ca != null) {
          int offset = gen.addressOf(ca.label(gen))
            - gen.addressOf(switch_label);
          gen.add4(offset);
        }
        else {
          gen.add4(defaultOffset);
        }
      }
    }
    else {
      gen.emit(Bytecode.LOOKUPSWITCH);
      int pad = emitPad(gen);
      int defaultOffset = defaultOffset(gen, switch_label);
      if(defaultOffset == 0) {
        defaultOffset = 1 + pad + 4 + 4 + 8 * numCase();
      }
      gen.add4(defaultOffset);
      gen.add4(map.size());
      for(Iterator iter = map.values().iterator(); iter.hasNext(); ) {
        ConstCase ca = (ConstCase)iter.next();
        gen.add4(ca.getValue().constant().intValue());
        int offset = gen.addressOf(ca.label(gen))
          - gen.addressOf(switch_label);
        gen.add4(offset);
      }
    }
    gen.addLabel(end_label());
  }
  protected java.util.Map targetOf_ContinueStmt_values;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:73
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
  private boolean targetOf_compute(ContinueStmt stmt) {  return false;  }
  protected java.util.Map targetOf_BreakStmt_values;
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:77
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
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:532
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
    if(!(!noDefaultLabel() || getExpr().isDAafter(v))) {
      return false;
    }
    if(!(!switchLabelEndsBlock() || getExpr().isDAafter(v))) {
      return false;
    }
    if(!assignedAfterLastStmt(v)) {
      return false;
    }
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:550
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean assignedAfterLastStmt(Variable v) {
      ASTNode$State state = state();
    boolean assignedAfterLastStmt_Variable_value = assignedAfterLastStmt_compute(v);
    return assignedAfterLastStmt_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean assignedAfterLastStmt_compute(Variable v) {  return getBlock().isDAafter(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1004
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
    if(!(!noDefaultLabel() || getExpr().isDUafter(v)))
      return false;
    if(!(!switchLabelEndsBlock() || getExpr().isDUafter(v)))
      return false;
    if(!unassignedAfterLastStmt(v))
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
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1019
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean unassignedAfterLastStmt(Variable v) {
      ASTNode$State state = state();
    boolean unassignedAfterLastStmt_Variable_value = unassignedAfterLastStmt_compute(v);
    return unassignedAfterLastStmt_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean unassignedAfterLastStmt_compute(Variable v) {  return getBlock().isDUafter(v);  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1022
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean switchLabelEndsBlock() {
      ASTNode$State state = state();
    boolean switchLabelEndsBlock_value = switchLabelEndsBlock_compute();
    return switchLabelEndsBlock_value;
  }
  /**
   * @apilvl internal
   */
  private boolean switchLabelEndsBlock_compute() {  return getBlock().getNumStmt() > 0 && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof ConstCase;  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean lastStmtCanCompleteNormally() {
      ASTNode$State state = state();
    boolean lastStmtCanCompleteNormally_value = lastStmtCanCompleteNormally_compute();
    return lastStmtCanCompleteNormally_value;
  }
  /**
   * @apilvl internal
   */
  private boolean lastStmtCanCompleteNormally_compute() {  return getBlock().canCompleteNormally();  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:65
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean noStmts() {
      ASTNode$State state = state();
    boolean noStmts_value = noStmts_compute();
    return noStmts_value;
  }
  /**
   * @apilvl internal
   */
  private boolean noStmts_compute() {
    for(int i = 0; i < getBlock().getNumStmt(); i++)
      if(!(getBlock().getStmt(i) instanceof Case))
        return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean noStmtsAfterLastLabel() {
      ASTNode$State state = state();
    boolean noStmtsAfterLastLabel_value = noStmtsAfterLastLabel_compute();
    return noStmtsAfterLastLabel_value;
  }
  /**
   * @apilvl internal
   */
  private boolean noStmtsAfterLastLabel_compute() {  return getBlock().getNumStmt() > 0 && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof Case;  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:75
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean noDefaultLabel() {
      ASTNode$State state = state();
    boolean noDefaultLabel_value = noDefaultLabel_compute();
    return noDefaultLabel_value;
  }
  /**
   * @apilvl internal
   */
  private boolean noDefaultLabel_compute() {
    for(int i = 0; i < getBlock().getNumStmt(); i++)
      if(getBlock().getStmt(i) instanceof DefaultCase)
        return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:82
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
  private boolean canCompleteNormally_compute() {  return lastStmtCanCompleteNormally() || noStmts() || noStmtsAfterLastLabel() || noDefaultLabel() || reachableBreak();  }
  /**
   * @apilvl internal
   */
  protected boolean defaultCase_computed = false;
  /**
   * @apilvl internal
   */
  protected DefaultCase defaultCase_value;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1793
   */
  @SuppressWarnings({"unchecked", "cast"})
  public DefaultCase defaultCase() {
    if(defaultCase_computed) {
      return defaultCase_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    defaultCase_value = defaultCase_compute();
if(isFinal && num == state().boundariesCrossed) defaultCase_computed = true;
    return defaultCase_value;
  }
  /**
   * @apilvl internal
   */
  private DefaultCase defaultCase_compute() {
    for(int i= 0; i < getBlock().getNumStmt(); i++) {
      if(getBlock().getStmt(i) instanceof DefaultCase)
        return (DefaultCase)getBlock().getStmt(i);
    }
    return null;
  }
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
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1801
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
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1872
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int numCase() {
      ASTNode$State state = state();
    int numCase_value = numCase_compute();
    return numCase_value;
  }
  /**
   * @apilvl internal
   */
  private int numCase_compute() {
    int result = 0;
    for(int i = 0; i < getBlock().getNumStmt(); i++)
      if(getBlock().getStmt(i) instanceof Case)
        result++;
    return result;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1987
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
   * @apilvl internal
   */
  protected boolean typeInt_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeInt_value;
  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:62
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeInt() {
    if(typeInt_computed) {
      return typeInt_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeInt_value = getParent().Define_TypeDecl_typeInt(this, null);
if(isFinal && num == state().boundariesCrossed) typeInt_computed = true;
    return typeInt_value;
  }
  /**
   * @apilvl internal
   */
  protected boolean typeLong_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl typeLong_value;
  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:64
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeLong() {
    if(typeLong_computed) {
      return typeLong_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeLong_value = getParent().Define_TypeDecl_typeLong(this, null);
if(isFinal && num == state().boundariesCrossed) typeLong_computed = true;
    return typeLong_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:569
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getBlockNoTransform()) {
      return getExpr().isDAafter(v);
    }
    if(caller == getExprNoTransform()){
    if(((ASTNode)v).isDescendantTo(this))
      return false;
    boolean result = isDAbefore(v);
    return result;
  }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:1027
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getBlockNoTransform()) {
      return getExpr().isDUafter(v);
    }
    if(caller == getExprNoTransform()) {
      return isDUbefore(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:481
   * @apilvl internal
   */
  public boolean Define_boolean_insideSwitch(ASTNode caller, ASTNode child) {
    if(caller == getBlockNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_insideSwitch(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:522
   * @apilvl internal
   */
  public Case Define_Case_bind(ASTNode caller, ASTNode child, Case c) {
    if(caller == getBlockNoTransform()){
    Block b = getBlock();
    for(int i = 0; i < b.getNumStmt(); i++)
      if(b.getStmt(i) instanceof Case && ((Case)b.getStmt(i)).constValue(c))
        return (Case)b.getStmt(i);
    return null;
  }
    return getParent().Define_Case_bind(this, caller, c);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:357
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_switchType(ASTNode caller, ASTNode child) {
    if(caller == getBlockNoTransform()) {
      return getExpr().type();
    }
    return getParent().Define_TypeDecl_switchType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:85
   * @apilvl internal
   */
  public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
    if(caller == getBlockNoTransform()) {
      return reachable();
    }
    return getParent().Define_boolean_reachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:159
   * @apilvl internal
   */
  public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
    if(caller == getBlockNoTransform()) {
      return reachable();
    }
    return getParent().Define_boolean_reportUnreachable(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
