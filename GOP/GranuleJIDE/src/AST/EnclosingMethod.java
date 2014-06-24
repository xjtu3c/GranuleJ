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
public class EnclosingMethod extends Attribute {

    public EnclosingMethod(ConstantPool cp, TypeDecl typeDecl) {
      super(cp, "EnclosingMethod");
      u2(cp.addClass(typeDecl.enclosingType().constantPoolName()));
      BodyDecl b = typeDecl.enclosingBodyDecl();
      if(b instanceof MethodDecl) {
        MethodDecl m = (MethodDecl)b;
        u2(cp.addNameAndType(m.name(), m.descName()));
      }
      else if(b instanceof ConstructorDecl) {
        ConstructorDecl m = (ConstructorDecl)b;
        u2(cp.addNameAndType(m.name(), m.descName()));
      }
      else {
        u2(0);
      }
    }


}
