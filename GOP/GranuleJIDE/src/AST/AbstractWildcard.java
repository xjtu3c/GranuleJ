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
 * @declaredat Generics.ast:17
 */
public abstract class AbstractWildcard extends Access implements Cloneable {
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
  public AbstractWildcard clone() throws CloneNotSupportedException {
    AbstractWildcard node = (AbstractWildcard)super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @ast method 
   * @declaredat Generics.ast:1
   */
  public AbstractWildcard() {
    super();


  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Generics.ast:10
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Generics.ast:16
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
