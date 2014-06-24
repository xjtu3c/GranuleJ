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
 * @declaredat java.ast:207
 */
public class Block extends Stmt implements Cloneable, VariableScope {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    checkReturnDA_Variable_values = null;
    isDAafter_Variable_values = null;
    checkReturnDU_Variable_values = null;
    isDUafter_Variable_values = null;
    updateExprStmtSet_computed = false;
    updateExprStmtSet_value = null;
    localVariableDeclaration_String_values = null;
    canCompleteNormally_computed = false;
    variableScopeEndLabel_CodeGeneration_values = null;
    lookupType_String_values = null;
    lookupVariable_String_values = null;
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
  public Block clone() throws CloneNotSupportedException {
    Block node = (Block)super.clone();
    node.checkReturnDA_Variable_values = null;
    node.isDAafter_Variable_values = null;
    node.checkReturnDU_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.updateExprStmtSet_computed = false;
    node.updateExprStmtSet_value = null;
    node.localVariableDeclaration_String_values = null;
    node.canCompleteNormally_computed = false;
    node.variableScopeEndLabel_CodeGeneration_values = null;
    node.lookupType_String_values = null;
    node.lookupVariable_String_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Block copy() {
      try {
        Block node = (Block)clone();
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
  public Block fullCopy() {
    Block res = (Block)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect DeclareBeforeUse
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DeclareBeforeUse.jrag:21
   */
  public boolean declaredBeforeUse(Variable decl, ASTNode use) {
    int indexDecl = ((ASTNode)decl).varChildIndex(this);
    int indexUse = use.varChildIndex(this);
    return indexDecl <= indexUse;
  }
  /**
   * @ast method 
   * @aspect DeclareBeforeUse
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DeclareBeforeUse.jrag:26
   */
  public boolean declaredBeforeUse(Variable decl, int indexUse) {
    int indexDecl = ((ASTNode)decl).varChildIndex(this);
    return indexDecl <= indexUse;
  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:989
   */
  public void insertStmt(Stmt node, int i)
 {
	    List<Stmt> list = getStmtList();
        list.insertChild(node,i);
 }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:408
   */
  public boolean hasCheckFitness()
      {
    	if(getNumStmt()==1&&(getStmt(0) instanceof ReturnStmt))
        return true;
   		return false;
      }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:656
   */
  public void toString(StringBuffer s) {
    String indent = indent();
    s.append(shouldHaveIndent() ? indent : "");
    s.append("{");
    for(int i = 0; i < getNumStmt(); i++) {
      getStmt(i).toString(s);
    }
    s.append(shouldHaveIndent() ? indent : indent.substring(0, indent.length()-2));
    s.append("}");
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1760
   */
  public void createBCode(CodeGeneration gen) {
    //super.createBCode(gen);	
    for(int i = 0; i < getNumStmt(); i++) {
      try {
        getStmt(i).createBCode(gen);	  
      } catch (Exception e) {
        e.printStackTrace();
        throw new Error("Error generating code for " + errorPrefix() + " " + getStmt(i));
      }
    }
    gen.addVariableScopeLabel(variableScopeEndLabel(gen));
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public Block() {
    super();

    setChild(new List(), 0);

  }
  /**
   * @ast method 
   * @declaredat java.ast:8
   */
  public Block(List<Stmt> p0) {
    setChild(p0, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:14
   */
  protected int numChildren() {
    return 1;
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
   * Setter for StmtList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setStmtList(List<Stmt> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in StmtList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumStmt() {
    return getStmtList().getNumChild();
  }
  /**
   * Getter for child in list StmtList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Stmt getStmt(int i) {
    return (Stmt)getStmtList().getChild(i);
  }
  /**
   * Add element to list StmtList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addStmt(Stmt node) {
    List<Stmt> list = (parent == null || state == null) ? getStmtListNoTransform() : getStmtList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addStmtNoTransform(Stmt node) {
    List<Stmt> list = getStmtListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list StmtList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setStmt(Stmt node, int i) {
    List<Stmt> list = getStmtList();
    list.setChild(node, i);
  }
  /**
   * Getter for Stmt list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<Stmt> getStmts() {
    return getStmtList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<Stmt> getStmtsNoTransform() {
    return getStmtListNoTransform();
  }
  /**
   * Getter for list StmtList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Stmt> getStmtList() {
    List<Stmt> list = (List<Stmt>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Stmt> getStmtListNoTransform() {
    return (List<Stmt>)getChildNoTransform(0);
  }
  protected java.util.Map checkReturnDA_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:302
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean checkReturnDA(Variable v) {
    Object _parameters = v;
    if(checkReturnDA_Variable_values == null) checkReturnDA_Variable_values = new java.util.HashMap(4);
    if(checkReturnDA_Variable_values.containsKey(_parameters)) {
      return ((Boolean)checkReturnDA_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean checkReturnDA_Variable_value = checkReturnDA_compute(v);
if(isFinal && num == state().boundariesCrossed) checkReturnDA_Variable_values.put(_parameters, Boolean.valueOf(checkReturnDA_Variable_value));
    return checkReturnDA_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean checkReturnDA_compute(Variable v) {
    HashSet set = new HashSet();
    collectBranches(set);
    for(Iterator iter = set.iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof ReturnStmt) {
        ReturnStmt stmt = (ReturnStmt)o;
        if(!stmt.isDAafterReachedFinallyBlocks(v))
          return false;
      }
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:442
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
  private boolean isDAafter_compute(Variable v) {  return getNumStmt() == 0 ? isDAbefore(v) : getStmt(getNumStmt()-1).isDAafter(v);  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:448
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUeverywhere(Variable v) {
      ASTNode$State state = state();
    boolean isDUeverywhere_Variable_value = isDUeverywhere_compute(v);
    return isDUeverywhere_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUeverywhere_compute(Variable v) {  return isDUbefore(v) && checkDUeverywhere(v);  }
  protected java.util.Map checkReturnDU_Variable_values;
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:758
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean checkReturnDU(Variable v) {
    Object _parameters = v;
    if(checkReturnDU_Variable_values == null) checkReturnDU_Variable_values = new java.util.HashMap(4);
    if(checkReturnDU_Variable_values.containsKey(_parameters)) {
      return ((Boolean)checkReturnDU_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean checkReturnDU_Variable_value = checkReturnDU_compute(v);
if(isFinal && num == state().boundariesCrossed) checkReturnDU_Variable_values.put(_parameters, Boolean.valueOf(checkReturnDU_Variable_value));
    return checkReturnDU_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean checkReturnDU_compute(Variable v) {
    HashSet set = new HashSet();
    collectBranches(set);
    for(Iterator iter = set.iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof ReturnStmt) {
        ReturnStmt stmt = (ReturnStmt)o;
        if(!stmt.isDUafterReachedFinallyBlocks(v))
          return false;
      }
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:874
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
  private boolean isDUafter_compute(Variable v) {  return getNumStmt() == 0 ? isDUbefore(v) : getStmt(getNumStmt()-1).isDUafter(v);  }
  /**
   * @apilvl internal
   */
  protected boolean updateExprStmtSet_computed = false;
  /**
   * @apilvl internal
   */
  protected HashMap updateExprStmtSet_value;
  /**
   * @attribute syn
   * @aspect GranuleTree
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:91
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap updateExprStmtSet() {
    if(updateExprStmtSet_computed) {
      return updateExprStmtSet_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    updateExprStmtSet_value = updateExprStmtSet_compute();
if(isFinal && num == state().boundariesCrossed) updateExprStmtSet_computed = true;
    return updateExprStmtSet_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap updateExprStmtSet_compute() {
       HashMap<String,Object> map=new HashMap<String,Object>();
       if(hostType().isClassDecl()){
        for(int i=0;i<getNumStmt();i++)
        {
          if(getStmt(i) instanceof UpdateExprStmt)
          map.put(String.valueOf(i),getStmt(i)); 
        }
       }
       return map;
     }
  protected java.util.Map localVariableDeclaration_String_values;
  /**
   * @attribute syn
   * @aspect VariableScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:171
   */
  @SuppressWarnings({"unchecked", "cast"})
  public VariableDeclaration localVariableDeclaration(String name) {
    Object _parameters = name;
    if(localVariableDeclaration_String_values == null) localVariableDeclaration_String_values = new java.util.HashMap(4);
    if(localVariableDeclaration_String_values.containsKey(_parameters)) {
      return (VariableDeclaration)localVariableDeclaration_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    VariableDeclaration localVariableDeclaration_String_value = localVariableDeclaration_compute(name);
if(isFinal && num == state().boundariesCrossed) localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
    return localVariableDeclaration_String_value;
  }
  /**
   * @apilvl internal
   */
  private VariableDeclaration localVariableDeclaration_compute(String name) {	
    for(int i = 0; i < getNumStmt(); i++)
      if(getStmt(i).declaresVariable(name))
        return (VariableDeclaration)getStmt(i);
    return null;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:893
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean addsIndentationLevel() {
      ASTNode$State state = state();
    boolean addsIndentationLevel_value = addsIndentationLevel_compute();
    return addsIndentationLevel_value;
  }
  /**
   * @apilvl internal
   */
  private boolean addsIndentationLevel_compute() {  return shouldHaveIndent();  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:895
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean shouldHaveIndent() {
      ASTNode$State state = state();
    boolean shouldHaveIndent_value = shouldHaveIndent_compute();
    return shouldHaveIndent_value;
  }
  /**
   * @apilvl internal
   */
  private boolean shouldHaveIndent_compute() {  return getParent() instanceof List && getParent().getParent() instanceof Block;  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:40
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
  private boolean canCompleteNormally_compute() {  return getNumStmt() == 0 ? reachable() : getStmt(getNumStmt() - 1).canCompleteNormally();  }
  protected java.util.Map variableScopeEndLabel_CodeGeneration_values;
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:43
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int variableScopeEndLabel(CodeGeneration gen) {
    Object _parameters = gen;
    if(variableScopeEndLabel_CodeGeneration_values == null) variableScopeEndLabel_CodeGeneration_values = new java.util.HashMap(4);
    if(variableScopeEndLabel_CodeGeneration_values.containsKey(_parameters)) {
      return ((Integer)variableScopeEndLabel_CodeGeneration_values.get(_parameters)).intValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    int variableScopeEndLabel_CodeGeneration_value = variableScopeEndLabel_compute(gen);
if(isFinal && num == state().boundariesCrossed) variableScopeEndLabel_CodeGeneration_values.put(_parameters, Integer.valueOf(variableScopeEndLabel_CodeGeneration_value));
    return variableScopeEndLabel_CodeGeneration_value;
  }
  /**
   * @apilvl internal
   */
  private int variableScopeEndLabel_compute(CodeGeneration gen) {  return gen.variableScopeLabel();  }
  protected java.util.Map lookupType_String_values;
  /**
   * @attribute inh
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:188
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupType(String name) {
    Object _parameters = name;
    if(lookupType_String_values == null) lookupType_String_values = new java.util.HashMap(4);
    if(lookupType_String_values.containsKey(_parameters)) {
      return (SimpleSet)lookupType_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
if(isFinal && num == state().boundariesCrossed) lookupType_String_values.put(_parameters, lookupType_String_value);
    return lookupType_String_value;
  }
  protected java.util.Map lookupVariable_String_values;
  /**
   * @attribute inh
   * @aspect VariableScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:17
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupVariable(String name) {
    Object _parameters = name;
    if(lookupVariable_String_values == null) lookupVariable_String_values = new java.util.HashMap(4);
    if(lookupVariable_String_values.containsKey(_parameters)) {
      return (SimpleSet)lookupVariable_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
if(isFinal && num == state().boundariesCrossed) lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
    return lookupVariable_String_value;
  }
  /**
   * @attribute inh
   * @aspect UnreachableStatements
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:28
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean reachable() {
      ASTNode$State state = state();
    boolean reachable_value = getParent().Define_boolean_reachable(this, null);
    return reachable_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:52
   * @apilvl internal
   */
  public boolean Define_boolean_isIncOrDec(ASTNode caller, ASTNode child) {
    if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return getParent().Define_boolean_isIncOrDec(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:445
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getStmtListNoTransform()) {
      int index = caller.getIndexOfChild(child);
      return index == 0 ? isDAbefore(v) : getStmt(index - 1).isDAafter(v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:875
   * @apilvl internal
   */
  public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getStmtListNoTransform()) {
      int index = caller.getIndexOfChild(child);
      return index == 0 ? isDUbefore(v) : getStmt(index - 1).isDUafter(v);
    }
    return getParent().Define_boolean_isDUbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:331
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
    if(caller == getStmtListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
    SimpleSet c = SimpleSet.emptySet;
    for(int i = index; i >= 0 && !(getStmt(i) instanceof Case); i--) {
      if(getStmt(i) instanceof LocalClassDeclStmt) {
        TypeDecl t = ((LocalClassDeclStmt)getStmt(i)).getClassDecl();
        if(t.name().equals(name)) {
          c = c.add(t);
        }
      }
    }
    if(!c.isEmpty())
      return c;
    return lookupType(name);
  }
}
    return getParent().Define_SimpleSet_lookupType(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:123
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getStmtListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
    VariableDeclaration v = localVariableDeclaration(name);
    // declare before use and shadowing
    if(v != null && declaredBeforeUse(v, index))
      return v;
    return lookupVariable(name);
  }
}
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:400
   * @apilvl internal
   */
  public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
    if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return this;
    }
    return getParent().Define_VariableScope_outerScope(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:119
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.EXPRESSION_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:41
   * @apilvl internal
   */
  public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
    if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return childIndex == 0 ? reachable() : getStmt(childIndex-1).canCompleteNormally();
    }
    return getParent().Define_boolean_reachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\UnreachableStatements.jrag:149
   * @apilvl internal
   */
  public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
    if(caller == getStmtListNoTransform()) {
      int i = caller.getIndexOfChild(child);
      return i == 0 ? reachable() : getStmt(i-1).reachable();
    }
    return getParent().Define_boolean_reportUnreachable(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:44
   * @apilvl internal
   */
  public int Define_int_variableScopeEndLabel(ASTNode caller, ASTNode child, CodeGeneration gen) {
    if(caller == getStmtListNoTransform()) {
      int i = caller.getIndexOfChild(child);
      return variableScopeEndLabel(gen);
    }
    return getParent().Define_int_variableScopeEndLabel(this, caller, gen);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:131
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(caller == getStmtListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
    if(index == 0)
      return localNum();
    if(getStmt(index-1) instanceof VariableDeclaration)
      return getStmt(index-1).localNum() + ((VariableDeclaration)getStmt(index-1)).type().variableSize();
    return getStmt(index-1).localNum();
  }
}
    return getParent().Define_int_localNum(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag at line 50
    if(updateExprStmtSet().size()>0) {
      state().duringGranuleTree++;
      ASTNode result = rewriteRule0();
      state().duringGranuleTree--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GranuleTree.jrag:50
   * @apilvl internal
   */  private Block rewriteRule0() {
{
	    	 HashMap map=updateExprStmtSet();
	    	 /*for(Iterator iter1=map.values().iterator();iter1.hasNext();)
	    	 {
	    		 UpdateExprStmt stmt=(UpdateExprStmt)iter1.next();
	    		 UpdateExpr expr=(UpdateExpr)stmt.getUpdateExpr();
	    		 OupdateExpr oprand=(OupdateExpr)expr.getOupdateExpr();
	    		 CompilationUnit root=getCompilationUnit();
	    		 if(oprand instanceof TupdateExpr){	    		 
	    			root.hasTrueUpdateExpr=true;
	    		 }
	    		 else if(oprand instanceof FupdateExpr){ 
	    			root.hasFalseUpdateExpr=true;
	    		 }
	    		 break;	    		
	    	 }*/
	         for(Iterator iter=map.keySet().iterator();iter.hasNext();){
	        	 String key=(String)iter.next();
	        	 UpdateExprStmt expr=(UpdateExprStmt)map.get(key);
	        	 UpdateExpr dotstat=(UpdateExpr)expr.getUpdateExpr();
				 Expr dotleft=(Expr)dotstat.getLeft().fullCopy();
				 OupdateExpr dotexpr=(OupdateExpr)dotstat.getOupdateExpr().fullCopy();
				 Access dotright=(Access)dotexpr.getRight().fullCopy();
			     AbstractDot dd=new AbstractDot(dotleft,dotright);
			     int pos=Integer.parseInt(key);
				 setStmt(new ExprStmt(dd),pos);
				 String method=new String("fit");
				 ParseName p=new ParseName("fit");
				 List<Expr> lst=new List<Expr>();
				 MethodAccess m=new MethodAccess(method,lst);
				 AbstractDot d=new AbstractDot(dotleft,p);
				 d.replaceLast(m);	
				 insertStmt(new ExprStmt(d),pos);
				 dotexpr.getRightNoTransform().clearLocations();
				 dotstat.getLeftNoTransform().clearLocations();
				 dotstat.getOupdateExprNoTransform().clearLocations();			
	           }
	         return this;
	      }  }
}
