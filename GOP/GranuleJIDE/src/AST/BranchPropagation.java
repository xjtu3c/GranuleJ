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
 * @ast interface
 * @declaredat :0
 */
public interface BranchPropagation {
public void collectBranches(Collection c);

public Stmt branchTarget(Stmt branchStmt);

public void collectFinally(Stmt branchStmt, ArrayList list);

  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:33
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection targetContinues();
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:34
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection targetBreaks();
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:35
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection targetBranches();
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:36
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection escapedBranches();
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection branches();
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:40
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean targetOf(ContinueStmt stmt);
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BranchTarget.jrag:41
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean targetOf(BreakStmt stmt);
}
