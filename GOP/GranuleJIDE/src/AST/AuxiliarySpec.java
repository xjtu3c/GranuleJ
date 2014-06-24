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
 * @declaredat Granule.ast:18
 */
public abstract class AuxiliarySpec extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    parameterDeclaration_String_values = null;
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
  public AuxiliarySpec clone() throws CloneNotSupportedException {
    AuxiliarySpec node = (AuxiliarySpec)super.clone();
    node.parameterDeclaration_String_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:284
   */
  public void toString(StringBuffer s)
  {
    s.append(indent());
    s.append(" " + kind() + "(");
    if(getNumParameter() > 0) {
      getParameter(0).toString(s);
      for(int i = 1; i < getNumParameter(); i++) {
        s.append(", ");
        getParameter(i).toString(s);
      }
    }
    s.append(")");
  }
  /**
   * @ast method 
   * @declaredat Granule.ast:1
   */
  public AuxiliarySpec() {
    super();

    setChild(new List(), 0);

  }
  /**
   * @ast method 
   * @declaredat Granule.ast:8
   */
  public AuxiliarySpec(List<ParameterDeclaration> p0) {
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
    return false;
  }
  /**
   * Setter for ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public int getNumParameter() {
    return getParameterList().getNumChild();
  }
  /**
   * Getter for child in list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ParameterDeclaration getParameter(int i) {
    return (ParameterDeclaration)getParameterList().getChild(i);
  }
  /**
   * Add element to list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:27
   */
  public void addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list = (parent == null || state == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:34
   */
  public void addParameterNoTransform(ParameterDeclaration node) {
    List<ParameterDeclaration> list = getParameterListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:42
   */
  public void setParameter(ParameterDeclaration node, int i) {
    List<ParameterDeclaration> list = getParameterList();
    list.setChild(node, i);
  }
  /**
   * Getter for Parameter list.
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:50
   */
  public List<ParameterDeclaration> getParameters() {
    return getParameterList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:56
   */
  public List<ParameterDeclaration> getParametersNoTransform() {
    return getParameterListNoTransform();
  }
  /**
   * Getter for list ParameterList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ParameterDeclaration> getParameterList() {
    List<ParameterDeclaration> list = (List<ParameterDeclaration>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>)getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:3
   */
  @SuppressWarnings({"unchecked", "cast"})
  public abstract String kind();
  protected java.util.Map parameterDeclaration_String_values;
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:65
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet parameterDeclaration(String name) {
    Object _parameters = name;
    if(parameterDeclaration_String_values == null) parameterDeclaration_String_values = new java.util.HashMap(4);
    if(parameterDeclaration_String_values.containsKey(_parameters)) {
      return (SimpleSet)parameterDeclaration_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet parameterDeclaration_String_value = parameterDeclaration_compute(name);
if(isFinal && num == state().boundariesCrossed) parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
    return parameterDeclaration_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet parameterDeclaration_compute(String name) {
  	    for(int i = 0; i <getNumParameter(); i++)
  	      if(getParameter(i).name().equals(name))
  	        return (ParameterDeclaration)getParameter(i);
  	    return SimpleSet.emptySet;
  	  }
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:174
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
    	    for(int i = 0; i < getNumParameter(); i++)
    	      if(getParameter(i).name().equals(name))
    	        return SimpleSet.emptySet.add(getParameter(i));
    	    //return localLookupImplicitVariable(name);
    	    return SimpleSet.emptySet;
    	  }
  /**
   * @attribute syn
   * @aspect AuxiliaryMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:181
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet lookupAfterVariable(String name) {
      ASTNode$State state = state();
    SimpleSet lookupAfterVariable_String_value = lookupAfterVariable_compute(name);
    return lookupAfterVariable_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet lookupAfterVariable_compute(String name) {  return SimpleSet.emptySet;  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AuxiliaryMethod.jrag:62
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) {
      int i = caller.getIndexOfChild(child);
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\LocalNum.jrag:27
   * @apilvl internal
   */
  public int Define_int_localNum(ASTNode caller, ASTNode child) {
    if(caller == getParameterListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
	 if(index==0)
	  return 1;
	  return getParameter(index-1).localNum()+getParameter(index-1).type().variableSize();
   }
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
