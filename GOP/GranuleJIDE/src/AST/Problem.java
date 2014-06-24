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
 * @ast class
 * @declaredat :0
 */
public class Problem extends java.lang.Object implements Comparable {

    public int compareTo(Object o) {
      if(o instanceof Problem) {
        Problem other = (Problem)o;
        if(!fileName.equals(other.fileName))
          return fileName.compareTo(other.fileName);
        if(line != other.line)
          return line - other.line;
        return message.compareTo(other.message);
      }
      return 0;
    }


    public static class Severity {
      public static final Severity ERROR = new Severity();
      public static final Severity WARNING = new Severity();
      private Severity() { }
    }


    public static class Kind {
      public static final Kind OTHER = new Kind();
      public static final Kind LEXICAL = new Kind();
      public static final Kind SYNTACTIC = new Kind();
      public static final Kind SEMANTIC = new Kind();
      private Kind() { }
    }


    protected int line = -1;


    public int line() { return line; }


    protected int column = -1;


    public int column() { return column; }


    protected int endLine = -1;


    public int endLine() { return endLine; }


    protected int endColumn = -1;


    public int endColumn() { return endColumn; }


    protected String fileName;


    public String fileName() { return fileName; }


    public void setFileName(String fileName) { this.fileName = fileName; }


    protected String message;


    public String message() { return message; }


    protected Severity severity = Severity.ERROR;


    public Severity severity() { return severity; }


    protected Kind kind = Kind.OTHER;


    public Kind kind() { return kind; }


    public Problem(String fileName, String message) {
      this.fileName = fileName;
      this.message = message;
    }


    public Problem(String fileName, String message, int line) {
      this(fileName, message);
      this.line = line;
    }


    public Problem(String fileName, String message, int line, Severity severity) {
      this(fileName, message);
      this.line = line;
      this.severity = severity;
    }


    public Problem(String fileName, String message, int line, int column, Severity severity) {
      this(fileName, message);
      this.line = line;
      this.column = column;
      this.severity = severity;
    }


    public Problem(String fileName, String message, int line, Severity severity, Kind kind) {
      this(fileName, message);
      this.line = line;
      this.kind = kind;
      this.severity = severity;
    }


    public Problem(String fileName, String message, int line, int column, Severity severity, Kind kind) {
      this(fileName, message);
      this.line = line;
      this.column = column;
      this.kind = kind;
      this.severity = severity;
    }


    public Problem(String fileName, String message, int line, int column, int endLine, int endColumn, Severity severity, Kind kind) {
      this(fileName, message);
      this.line = line;
      this.column = column;
      this.endLine = endLine;
      this.endColumn = endColumn;
      this.kind = kind;
      this.severity = severity;
    }


    public String toString() {
      String location = "";
      if(line != -1 && column != -1)
        location = line + "," + column + ":";
      else if(line != -1)
        location = line + ":";
      String s = "";
      if(this.kind == Kind.LEXICAL)
        s = "Lexical Error: ";
      else if(this.kind == Kind.SYNTACTIC)
        s = "Syntactic Error: ";
      else if(this.kind == Kind.SEMANTIC)
        s = "Semantic Error: ";
      return fileName + ":" + location + "\n" + "  " + s + message;
    }


}
