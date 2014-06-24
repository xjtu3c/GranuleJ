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
 * @declaredat java.ast:200
 */
public class Modifiers extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    isExternal_computed = false;
    isCompile_computed = false;
    isLoad_computed = false;
    isRuntime_computed = false;
    isBase_computed = false;
    isPublic_computed = false;
    isPrivate_computed = false;
    isProtected_computed = false;
    isStatic_computed = false;
    isFinal_computed = false;
    isAbstract_computed = false;
    isVolatile_computed = false;
    isTransient_computed = false;
    isStrictfp_computed = false;
    isSynchronized_computed = false;
    isNative_computed = false;
    isSynthetic_computed = false;
    numModifier_String_values = null;
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
  public Modifiers clone() throws CloneNotSupportedException {
    Modifiers node = (Modifiers)super.clone();
    node.isExternal_computed = false;
    node.isCompile_computed = false;
    node.isLoad_computed = false;
    node.isRuntime_computed = false;
    node.isBase_computed = false;
    node.isPublic_computed = false;
    node.isPrivate_computed = false;
    node.isProtected_computed = false;
    node.isStatic_computed = false;
    node.isFinal_computed = false;
    node.isAbstract_computed = false;
    node.isVolatile_computed = false;
    node.isTransient_computed = false;
    node.isStrictfp_computed = false;
    node.isSynchronized_computed = false;
    node.isNative_computed = false;
    node.isSynthetic_computed = false;
    node.numModifier_String_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Modifiers copy() {
      try {
        Modifiers node = (Modifiers)clone();
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
  public Modifiers fullCopy() {
    Modifiers res = (Modifiers)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:377
   */
  public void checkModifiers() {
    super.checkModifiers();
    if(numProtectionModifiers() > 1)
      error("only one public, protected, private allowed");
    if(numModifier("static") > 1)
      error("only one static allowed");
    // 8.4.3.1
    // 8.4.3.2
    // 8.1.1.2
    if(numCompletenessModifiers() > 1)
      error("only one of final, abstract, volatile allowed");
    if(numModifier("synchronized") > 1)
      error("only one synchronized allowed");
    if(numModifier("transient") > 1)
      error("only one transient allowed");
    if(numModifier("native") > 1)
      error("only one native allowed");
    if(numModifier("strictfp") > 1)
      error("only one strictfp allowed");
//=================GOP=======================================
    if(numModifier("compile")>1)
    error("only one compile allowed");
    if(numModifier("load")>1)
    error("only one load allowed");
    if(numModifier("runtime")>1)
    error("only one runtime allowed");  
    if(numModifier("base")>1)
    error("only one base allowed");
    if(isCompile()&&!mayBeCompile())
     error("modifier compile not allowed in this context");
    if(isLoad()&&!mayBeLoad())
        error("modifier load not allowed in this context");
    if(isRuntime()&&!mayBeRuntime())
        error("modifier runtime not allowed in this context");
    if(isBase()&&!mayBeBase())
    error("modifier base not allowed in this context");
//=================END=======================================    
    if(isPublic() && !mayBePublic())
      error("modifier public not allowed in this context");
    if(isPrivate() && !mayBePrivate())
      error("modifier private not allowed in this context");
    if(isProtected() && !mayBeProtected())
      error("modifier protected not allowed in this context");
    if(isStatic() && !mayBeStatic())
      error("modifier static not allowed in this context");
    if(isFinal() && !mayBeFinal())
      error("modifier final not allowed in this context");
    if(isAbstract() && !mayBeAbstract())
      error("modifier abstract not allowed in this context");
    if(isVolatile() && !mayBeVolatile())
      error("modifier volatile not allowed in this context");
    if(isTransient() && !mayBeTransient())
      error("modifier transient not allowed in this context");
    if(isStrictfp() && !mayBeStrictfp())
      error("modifier strictfp not allowed in this context");
    if(isSynchronized() && !mayBeSynchronized())
      error("modifier synchronized not allowed in this context");
    if(isNative() && !mayBeNative())
      error("modifier native not allowed in this context");
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:562
   */
  public void toString(StringBuffer s) {
    for(int i = 0; i < getNumModifier(); i++) {
      getModifier(i).toString(s);
      s.append(" ");
    }
  }
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:11
   */
  
  public static final int ACC_PUBLIC       = 0x0001;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:12
   */
   // class field method
  public static final int ACC_PRIVATE      = 0x0002;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:13
   */
   //       field method
  public static final int ACC_PROTECTED    = 0x0004;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:14
   */
   //       field method
  public static final int ACC_STATIC       = 0x0008;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:15
   */
   //       field method
  public static final int ACC_FINAL        = 0x0010;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:16
   */
   // class field method
  public static final int ACC_SYNCHRONIZED = 0x0020;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:17
   */
   //             method
  public static final int ACC_SUPER        = 0x0020;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:18
   */
   // class
  public static final int ACC_VOLATILE     = 0x0040;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:19
   */
   //       field
  public static final int ACC_TRANSIENT    = 0x0080;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:20
   */
   //       field
  public static final int ACC_NATIVE       = 0x0100;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:21
   */
   //             method
  public static final int ACC_INTERFACE    = 0x0200;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:22
   */
   // class
  public static final int ACC_ABSTRACT     = 0x0400;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:23
   */
   // class       method
  public static final int ACC_SYNTHETIC    = 0x1000;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:24
   */
   //       field method
  public static final int ACC_STRICT       = 0x0800;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:26
   */
   //             method
  
  public static final int ACC_COMPILE       =0x0100;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:27
   */
  
  public static final int ACC_LOAD          =0x0120;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:28
   */
  
  public static final int ACC_RUN           =0x0140;
  /**
   * @ast method 
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:29
   */
  
  public static final int ACC_BASE          =0x0180;
  /**
   * @ast method 
   * @aspect modifierExternal
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GOPCodeGeneration.jrag:273
   */
  
  public static final int ACC_EXTERNAL=0x2000;
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:39
   */
  public void addRuntimeVisibleAnnotationsAttribute(Collection c) {
    ConstantPool cp = hostType().constantPool();
    Collection annotations = runtimeVisibleAnnotations();
    if(!annotations.isEmpty())
      c.add(new AnnotationsAttribute(cp, annotations, "RuntimeVisibleAnnotations"));
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:47
   */
  public void addRuntimeInvisibleAnnotationsAttribute(Collection c) {
    ConstantPool cp = hostType().constantPool();
    Collection annotations = runtimeInvisibleAnnotations();
    if(!annotations.isEmpty())
      c.add(new AnnotationsAttribute(cp, annotations, "RuntimeInvisibleAnnotations"));
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:96
   */
  public Collection runtimeVisibleAnnotations() {
    Collection annotations = new ArrayList();
    for(int i = 0; i < getNumModifier(); i++)
      if(getModifier(i).isRuntimeVisible())
        annotations.add(getModifier(i));
    return annotations;
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:117
   */
  public Collection runtimeInvisibleAnnotations() {
    Collection annotations = new ArrayList();
    for(int i = 0; i < getNumModifier(); i++)
      if(getModifier(i).isRuntimeInvisible())
        annotations.add(getModifier(i));
    return annotations;
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:140
   */
  

  // Add ACC_ANNOTATION flag to generated class file
  public static final int ACC_ANNOTATION = 0x2000;
  /**
   * @ast method 
   * @aspect EnumsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\EnumsCodegen.jrag:12
   */
  
    // add flags to enums
  public static final int ACC_ENUM = 0x4000;
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:247
   */
  

  public static final int ACC_BRIDGE = 0x0040;
  /**
   * @ast method 
   * @aspect VariableArityParametersCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\VariableArityParametersCodegen.jrag:79
   */
  

  public static final int ACC_VARARGS = 0x0080;
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public Modifiers() {
    super();

    setChild(new List(), 0);

  }
  /**
   * @ast method 
   * @declaredat java.ast:8
   */
  public Modifiers(List<Modifier> p0) {
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
    return false;
  }
  /**
   * Setter for ModifierList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setModifierList(List<Modifier> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ModifierList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumModifier() {
    return getModifierList().getNumChild();
  }
  /**
   * Getter for child in list ModifierList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Modifier getModifier(int i) {
    return (Modifier)getModifierList().getChild(i);
  }
  /**
   * Add element to list ModifierList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addModifier(Modifier node) {
    List<Modifier> list = (parent == null || state == null) ? getModifierListNoTransform() : getModifierList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addModifierNoTransform(Modifier node) {
    List<Modifier> list = getModifierListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ModifierList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setModifier(Modifier node, int i) {
    List<Modifier> list = getModifierList();
    list.setChild(node, i);
  }
  /**
   * Getter for Modifier list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<Modifier> getModifiers() {
    return getModifierList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<Modifier> getModifiersNoTransform() {
    return getModifierListNoTransform();
  }
  /**
   * Getter for list ModifierList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Modifier> getModifierList() {
    List<Modifier> list = (List<Modifier>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Modifier> getModifierListNoTransform() {
    return (List<Modifier>)getChildNoTransform(0);
  }
  /**
   * @apilvl internal
   */
  protected boolean isExternal_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isExternal_value;
  /**
   * @attribute syn
   * @aspect GOPModifier
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1362
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isExternal() {
    if(isExternal_computed) {
      return isExternal_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isExternal_value = isExternal_compute();
if(isFinal && num == state().boundariesCrossed) isExternal_computed = true;
    return isExternal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isExternal_compute() {  return numModifier("external") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isCompile_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isCompile_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:455
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isCompile() {
    if(isCompile_computed) {
      return isCompile_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isCompile_value = isCompile_compute();
if(isFinal && num == state().boundariesCrossed) isCompile_computed = true;
    return isCompile_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isCompile_compute() {  return numModifier("compile")!=0;  }
  /**
   * @apilvl internal
   */
  protected boolean isLoad_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isLoad_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:456
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isLoad() {
    if(isLoad_computed) {
      return isLoad_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isLoad_value = isLoad_compute();
if(isFinal && num == state().boundariesCrossed) isLoad_computed = true;
    return isLoad_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isLoad_compute() {  return numModifier("load")!=0;  }
  /**
   * @apilvl internal
   */
  protected boolean isRuntime_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isRuntime_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:457
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isRuntime() {
    if(isRuntime_computed) {
      return isRuntime_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isRuntime_value = isRuntime_compute();
if(isFinal && num == state().boundariesCrossed) isRuntime_computed = true;
    return isRuntime_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isRuntime_compute() {  return numModifier("runtime")!=0;  }
  /**
   * @apilvl internal
   */
  protected boolean isBase_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isBase_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:458
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isBase() {
    if(isBase_computed) {
      return isBase_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isBase_value = isBase_compute();
if(isFinal && num == state().boundariesCrossed) isBase_computed = true;
    return isBase_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isBase_compute() {  return numModifier("base")!=0;  }
  /**
   * @apilvl internal
   */
  protected boolean isPublic_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isPublic_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:460
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPublic() {
    if(isPublic_computed) {
      return isPublic_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isPublic_value = isPublic_compute();
if(isFinal && num == state().boundariesCrossed) isPublic_computed = true;
    return isPublic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPublic_compute() {  return numModifier("public") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isPrivate_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isPrivate_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:461
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPrivate() {
    if(isPrivate_computed) {
      return isPrivate_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isPrivate_value = isPrivate_compute();
if(isFinal && num == state().boundariesCrossed) isPrivate_computed = true;
    return isPrivate_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPrivate_compute() {  return numModifier("private") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isProtected_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isProtected_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:462
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isProtected() {
    if(isProtected_computed) {
      return isProtected_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isProtected_value = isProtected_compute();
if(isFinal && num == state().boundariesCrossed) isProtected_computed = true;
    return isProtected_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isProtected_compute() {  return numModifier("protected") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isStatic_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isStatic_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:463
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isStatic() {
    if(isStatic_computed) {
      return isStatic_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isStatic_value = isStatic_compute();
if(isFinal && num == state().boundariesCrossed) isStatic_computed = true;
    return isStatic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isStatic_compute() {  return numModifier("static") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isFinal_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isFinal_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:464
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFinal() {
    if(isFinal_computed) {
      return isFinal_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isFinal_value = isFinal_compute();
if(isFinal && num == state().boundariesCrossed) isFinal_computed = true;
    return isFinal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFinal_compute() {  return numModifier("final") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isAbstract_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isAbstract_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:465
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isAbstract() {
    if(isAbstract_computed) {
      return isAbstract_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isAbstract_value = isAbstract_compute();
if(isFinal && num == state().boundariesCrossed) isAbstract_computed = true;
    return isAbstract_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isAbstract_compute() {  return numModifier("abstract") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isVolatile_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isVolatile_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:466
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVolatile() {
    if(isVolatile_computed) {
      return isVolatile_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isVolatile_value = isVolatile_compute();
if(isFinal && num == state().boundariesCrossed) isVolatile_computed = true;
    return isVolatile_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVolatile_compute() {  return numModifier("volatile") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isTransient_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isTransient_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:467
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isTransient() {
    if(isTransient_computed) {
      return isTransient_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isTransient_value = isTransient_compute();
if(isFinal && num == state().boundariesCrossed) isTransient_computed = true;
    return isTransient_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isTransient_compute() {  return numModifier("transient") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isStrictfp_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isStrictfp_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:468
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isStrictfp() {
    if(isStrictfp_computed) {
      return isStrictfp_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isStrictfp_value = isStrictfp_compute();
if(isFinal && num == state().boundariesCrossed) isStrictfp_computed = true;
    return isStrictfp_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isStrictfp_compute() {  return numModifier("strictfp") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isSynchronized_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isSynchronized_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:469
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSynchronized() {
    if(isSynchronized_computed) {
      return isSynchronized_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isSynchronized_value = isSynchronized_compute();
if(isFinal && num == state().boundariesCrossed) isSynchronized_computed = true;
    return isSynchronized_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSynchronized_compute() {  return numModifier("synchronized") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isNative_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isNative_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:470
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isNative() {
    if(isNative_computed) {
      return isNative_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isNative_value = isNative_compute();
if(isFinal && num == state().boundariesCrossed) isNative_computed = true;
    return isNative_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isNative_compute() {  return numModifier("native") != 0;  }
  /**
   * @apilvl internal
   */
  protected boolean isSynthetic_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isSynthetic_value;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:472
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSynthetic() {
    if(isSynthetic_computed) {
      return isSynthetic_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isSynthetic_value = isSynthetic_compute();
if(isFinal && num == state().boundariesCrossed) isSynthetic_computed = true;
    return isSynthetic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSynthetic_compute() {  return numModifier("synthetic") != 0;  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:474
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int numProtectionModifiers() {
      ASTNode$State state = state();
    int numProtectionModifiers_value = numProtectionModifiers_compute();
    return numProtectionModifiers_value;
  }
  /**
   * @apilvl internal
   */
  private int numProtectionModifiers_compute() {  return numModifier("public") + numModifier("protected") + numModifier("private");  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:477
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int numCompletenessModifiers() {
      ASTNode$State state = state();
    int numCompletenessModifiers_value = numCompletenessModifiers_compute();
    return numCompletenessModifiers_value;
  }
  /**
   * @apilvl internal
   */
  private int numCompletenessModifiers_compute() {  return numModifier("abstract") + numModifier("final") + numModifier("volatile");  }
  protected java.util.Map numModifier_String_values;
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:480
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int numModifier(String name) {
    Object _parameters = name;
    if(numModifier_String_values == null) numModifier_String_values = new java.util.HashMap(4);
    if(numModifier_String_values.containsKey(_parameters)) {
      return ((Integer)numModifier_String_values.get(_parameters)).intValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    int numModifier_String_value = numModifier_compute(name);
if(isFinal && num == state().boundariesCrossed) numModifier_String_values.put(_parameters, Integer.valueOf(numModifier_String_value));
    return numModifier_String_value;
  }
  /**
   * @apilvl internal
   */
  private int numModifier_compute(String name) {
    int n = 0;
    for(int i = 0; i < getNumModifier(); i++) {
      String s = getModifier(i).getID();
      if(s.equals(name))
        n++;
    }
    return n;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:214
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Annotation annotation(TypeDecl typeDecl) {
      ASTNode$State state = state();
    Annotation annotation_TypeDecl_value = annotation_compute(typeDecl);
    return annotation_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private Annotation annotation_compute(TypeDecl typeDecl) {
    for(int i = 0; i < getNumModifier(); i++) {
      if(getModifier(i) instanceof Annotation) {
        Annotation a = (Annotation)getModifier(i);
        if(a.type() == typeDecl)
          return a;
      }
    }
    return null;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:289
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasAnnotationSuppressWarnings(String s) {
      ASTNode$State state = state();
    boolean hasAnnotationSuppressWarnings_String_value = hasAnnotationSuppressWarnings_compute(s);
    return hasAnnotationSuppressWarnings_String_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasAnnotationSuppressWarnings_compute(String s) {
    Annotation a = annotation(lookupType("java.lang", "SuppressWarnings"));
    if(a != null && a.getNumElementValuePair() == 1 && a.getElementValuePair(0).getName().equals("value"))
      return a.getElementValuePair(0).getElementValue().hasValue(s);
    return false;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:319
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasDeprecatedAnnotation() {
      ASTNode$State state = state();
    boolean hasDeprecatedAnnotation_value = hasDeprecatedAnnotation_compute();
    return hasDeprecatedAnnotation_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasDeprecatedAnnotation_compute() {  return annotation(lookupType("java.lang", "Deprecated")) != null;  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:437
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl hostType() {
      ASTNode$State state = state();
    TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
    return hostType_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:439
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBePublic() {
      ASTNode$State state = state();
    boolean mayBePublic_value = getParent().Define_boolean_mayBePublic(this, null);
    return mayBePublic_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:440
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBePrivate() {
      ASTNode$State state = state();
    boolean mayBePrivate_value = getParent().Define_boolean_mayBePrivate(this, null);
    return mayBePrivate_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:441
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeProtected() {
      ASTNode$State state = state();
    boolean mayBeProtected_value = getParent().Define_boolean_mayBeProtected(this, null);
    return mayBeProtected_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:442
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeStatic() {
      ASTNode$State state = state();
    boolean mayBeStatic_value = getParent().Define_boolean_mayBeStatic(this, null);
    return mayBeStatic_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:443
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeFinal() {
      ASTNode$State state = state();
    boolean mayBeFinal_value = getParent().Define_boolean_mayBeFinal(this, null);
    return mayBeFinal_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:444
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeAbstract() {
      ASTNode$State state = state();
    boolean mayBeAbstract_value = getParent().Define_boolean_mayBeAbstract(this, null);
    return mayBeAbstract_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:445
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeVolatile() {
      ASTNode$State state = state();
    boolean mayBeVolatile_value = getParent().Define_boolean_mayBeVolatile(this, null);
    return mayBeVolatile_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:446
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeTransient() {
      ASTNode$State state = state();
    boolean mayBeTransient_value = getParent().Define_boolean_mayBeTransient(this, null);
    return mayBeTransient_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:447
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeStrictfp() {
      ASTNode$State state = state();
    boolean mayBeStrictfp_value = getParent().Define_boolean_mayBeStrictfp(this, null);
    return mayBeStrictfp_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:448
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeSynchronized() {
      ASTNode$State state = state();
    boolean mayBeSynchronized_value = getParent().Define_boolean_mayBeSynchronized(this, null);
    return mayBeSynchronized_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:449
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeNative() {
      ASTNode$State state = state();
    boolean mayBeNative_value = getParent().Define_boolean_mayBeNative(this, null);
    return mayBeNative_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:451
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeCompile() {
      ASTNode$State state = state();
    boolean mayBeCompile_value = getParent().Define_boolean_mayBeCompile(this, null);
    return mayBeCompile_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:452
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeLoad() {
      ASTNode$State state = state();
    boolean mayBeLoad_value = getParent().Define_boolean_mayBeLoad(this, null);
    return mayBeLoad_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:453
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeRuntime() {
      ASTNode$State state = state();
    boolean mayBeRuntime_value = getParent().Define_boolean_mayBeRuntime(this, null);
    return mayBeRuntime_value;
  }
  /**
   * @attribute inh
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:454
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean mayBeBase() {
      ASTNode$State state = state();
    boolean mayBeBase_value = getParent().Define_boolean_mayBeBase(this, null);
    return mayBeBase_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:56
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl lookupType(String packageName, String typeName) {
      ASTNode$State state = state();
    TypeDecl lookupType_String_String_value = getParent().Define_TypeDecl_lookupType(this, null, packageName, typeName);
    return lookupType_String_String_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:424
   * @apilvl internal
   */
  public Annotation Define_Annotation_lookupAnnotation(ASTNode caller, ASTNode child, TypeDecl typeDecl) {
    if(caller == getModifierListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
    return annotation(typeDecl);
  }
}
    return getParent().Define_Annotation_lookupAnnotation(this, caller, typeDecl);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
