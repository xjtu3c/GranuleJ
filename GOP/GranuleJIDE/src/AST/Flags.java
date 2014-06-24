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
public interface Flags {

     
    public int ACC_PUBLIC = 0x0001;

     
    public int ACC_PRIVATE = 0x0002;

     
    public int ACC_PROTECTED = 0x0004;

     
    public int ACC_STATIC = 0x0008;

     
    public int ACC_FINAL = 0x0010;

     
    public int ACC_SUPER = 0x0020;

     
    public int ACC_SYNCHRONIZED = 0x0020;

     
    public int ACC_VOLATILE = 0x0040;

     
    public int ACC_BRIDGE = 0x0040;

     
    public int ACC_TRANSIENT = 0x0080;

     
    public int ACC_VARARGS = 0x0080;

     
    public int ACC_NATIVE = 0x0100;

     
    public int ACC_INTERFACE = 0x0200;

     
    public int ACC_ABSTRACT = 0x0400;

     
    public int ACC_STRICT = 0x0800;

     
    public int ACC_SYNTHETIC = 0x1000;

     
    public int ACC_ANNOTATION = 0x2000;

     
    public int ACC_ENUM = 0x4000;
}
