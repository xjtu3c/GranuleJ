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
public class Attribute extends java.lang.Object {

    int attribute_name_index;


    ByteArrayOutputStream buf = new ByteArrayOutputStream();


    DataOutputStream output = new DataOutputStream(buf);



    public Attribute(ConstantPool cp, String name) {   
      attribute_name_index = cp.addUtf8(name);
    }



    public void emit(DataOutputStream out) throws IOException {
      out.writeChar(attribute_name_index);
      out.writeInt(buf.size());
      buf.writeTo(out);
      output.close();
      buf.close();
    }


    public int size() { return buf.size(); }


    public void u1(int v) { try { output.writeByte(v); } catch(IOException e) {} }


    public void u2(int v) { try { output.writeChar(v); } catch(IOException e) {} }


    public void u4(int v) { try { output.writeInt(v); } catch(IOException e) {} }


    public void append(byte[] data) { try { output.write(data, 0, data.length); } catch(IOException e) {} }


    public void append(Attribute attribute) { try { attribute.emit(output); } catch(IOException e) {} }


}
