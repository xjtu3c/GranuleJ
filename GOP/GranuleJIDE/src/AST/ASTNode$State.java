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
 * @apilvl internal
 * @ast class
 * @declaredat :0
 */
public class ASTNode$State extends java.lang.Object {

/**
 * @apilvl internal
 */
  public boolean IN_CIRCLE = false;


/**
 * @apilvl internal
 */
  public int CIRCLE_INDEX = 1;


/**
 * @apilvl internal
 */
  public boolean CHANGE = false;


/**
 * @apilvl internal
 */
  public boolean RESET_CYCLE = false;


  /**
   * @apilvl internal
   */
  static public class CircularValue {
    Object value;
    int visited = -1;
  }


  public static final int REWRITE_CHANGE = 1;


  public static final int REWRITE_NOCHANGE = 2;


  public static final int REWRITE_INTERRUPT = 3;


  public int boundariesCrossed = 0;



  private int[] stack;


  private int pos;


  public ASTNode$State() {
      stack = new int[64];
      pos = 0;
  }


  private void ensureSize(int size) {
      if(size < stack.length)
        return;
      int[] newStack = new int[stack.length * 2];
      System.arraycopy(stack, 0, newStack, 0, stack.length);
      stack = newStack;
  }


  public void push(int i) {
    ensureSize(pos+1);
    stack[pos++] = i;
  }


  public int pop() {
    return stack[--pos];
  }


  public int peek() {
    return stack[pos-1];
  }


  /**
   * @apilvl internal
   */
  static class IdentityHashSet extends java.util.AbstractSet implements java.util.Set {
    public IdentityHashSet(int initialCapacity) {
      map = new java.util.IdentityHashMap(initialCapacity);
      }
    private java.util.IdentityHashMap map;
    private static final Object PRESENT = new Object();
    public java.util.Iterator iterator() { return map.keySet().iterator(); }
    public int size() { return map.size(); }
    public boolean isEmpty() { return map.isEmpty(); }
    public boolean contains(Object o) { return map.containsKey(o); }
    public boolean add(Object o) { return map.put(o, PRESENT)==null; }
    public boolean remove(Object o) { return map.remove(o)==PRESENT; }
    public void clear() { map.clear(); }
  }


  public Options options = new Options();


  public int replacePos = 0;


  protected int duringBoundNames = 0;


  protected int duringExternalVar = 0;


  protected int duringGOP = 0;


  protected int duringMethodTransform = 0;


  protected int duringResolveAmbiguousNames = 0;


  protected int duringSyntacticClassification = 0;


  protected int duringAnonymousClasses = 0;


  protected int duringLookupConstructor = 0;


  protected int duringVariableDeclaration = 0;


  protected int duringConstantExpression = 0;


  protected int duringGranuleTree = 0;


  protected int duringDefiniteAssignment = 0;


  protected int duringAnnotations = 0;


  protected int duringEnums = 0;


  protected int duringGenericTypeVariables = 0;

public void reset() {
    IN_CIRCLE = false;
    CIRCLE_INDEX = 1;
    CHANGE = false;
    boundariesCrossed = 0;
    if(duringBoundNames != 0) {
      System.out.println("Warning: resetting duringBoundNames");
      duringBoundNames = 0;
    }
    if(duringExternalVar != 0) {
      System.out.println("Warning: resetting duringExternalVar");
      duringExternalVar = 0;
    }
    if(duringGOP != 0) {
      System.out.println("Warning: resetting duringGOP");
      duringGOP = 0;
    }
    if(duringMethodTransform != 0) {
      System.out.println("Warning: resetting duringMethodTransform");
      duringMethodTransform = 0;
    }
    if(duringResolveAmbiguousNames != 0) {
      System.out.println("Warning: resetting duringResolveAmbiguousNames");
      duringResolveAmbiguousNames = 0;
    }
    if(duringSyntacticClassification != 0) {
      System.out.println("Warning: resetting duringSyntacticClassification");
      duringSyntacticClassification = 0;
    }
    if(duringAnonymousClasses != 0) {
      System.out.println("Warning: resetting duringAnonymousClasses");
      duringAnonymousClasses = 0;
    }
    if(duringLookupConstructor != 0) {
      System.out.println("Warning: resetting duringLookupConstructor");
      duringLookupConstructor = 0;
    }
    if(duringVariableDeclaration != 0) {
      System.out.println("Warning: resetting duringVariableDeclaration");
      duringVariableDeclaration = 0;
    }
    if(duringConstantExpression != 0) {
      System.out.println("Warning: resetting duringConstantExpression");
      duringConstantExpression = 0;
    }
    if(duringGranuleTree != 0) {
      System.out.println("Warning: resetting duringGranuleTree");
      duringGranuleTree = 0;
    }
    if(duringDefiniteAssignment != 0) {
      System.out.println("Warning: resetting duringDefiniteAssignment");
      duringDefiniteAssignment = 0;
    }
    if(duringAnnotations != 0) {
      System.out.println("Warning: resetting duringAnnotations");
      duringAnnotations = 0;
    }
    if(duringEnums != 0) {
      System.out.println("Warning: resetting duringEnums");
      duringEnums = 0;
    }
    if(duringGenericTypeVariables != 0) {
      System.out.println("Warning: resetting duringGenericTypeVariables");
      duringGenericTypeVariables = 0;
    }
  }


}
