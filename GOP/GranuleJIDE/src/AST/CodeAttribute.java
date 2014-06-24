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
public class CodeAttribute extends Attribute {

    public CodeAttribute(CodeGeneration codeGen, MethodDecl m) {
      super(codeGen.constantPool(), "Code");
      u2(codeGen.maxStackDepth());
      u2(codeGen.maxLocals());
      u4(codeGen.pos()); // code_length
      append(codeGen.toArray());
      u2(codeGen.exceptions.size());
      for(Iterator iter = codeGen.exceptions.iterator(); iter.hasNext(); ) {
        CodeGeneration.ExceptionEntry e = (CodeGeneration.ExceptionEntry)iter.next();
        u2(e.start_pc);
        u2(e.end_pc);
        u2(e.handler_pc);
        u2(e.catch_type);
      }

      if(m == null || !m.getModifiers().isSynthetic()) {
        u2(2); // Attribute count
        append(new LineNumberTableAttribute(codeGen));
        append(new LocalVariableTableAttribute(codeGen));
      }
      else {
        u2(0); // Attribute count
      }
    }


    public CodeAttribute(CodeGeneration codeGen, AuxiliaryMethodDecl a,boolean dummy) {
        super(codeGen.constantPool(), "Code");
        u2(codeGen.maxStackDepth());
        u2(codeGen.maxLocals());
        u4(codeGen.pos()); // code_length
        append(codeGen.toArray());
        u2(codeGen.exceptions.size());
         for(Iterator iter = codeGen.exceptions.iterator(); iter.hasNext(); ) {
         CodeGeneration.ExceptionEntry e = (CodeGeneration.ExceptionEntry)iter.next();
         u2(e.start_pc);
         u2(e.end_pc);
         u2(e.handler_pc);
         u2(e.catch_type);
        }

        if(a == null || !a.getModifiers().isSynthetic()) {
          u2(2); // Attribute count
          append(new LineNumberTableAttribute(codeGen));
          append(new LocalVariableTableAttribute(codeGen));
        }
        else {
          u2(0); // Attribute count
        }
   }


}
