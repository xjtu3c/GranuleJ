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

/*************************************************************
   * Auxiliary class
   ************************************************************* @ast class
 * @declaredat :0
 */
public class ByteArray extends java.lang.Object {

    private int stackDepth = 0;


    private int maxStackDepth = 0;


    private int size = 64;


    private byte[] bytes = new byte[size];


    private int pos = 0;


    private int lastGotoPos = 0;


    ByteArray add(int i) {return add((byte)i);}


    ByteArray add(byte b) {
      if(pos >= size) {
        byte[] ba = new byte[size * 2];
        System.arraycopy(bytes, 0, ba, 0, size);
        size *= 2;
        bytes = ba;
      }
      bytes[pos++] = b;
      return this;
    }


    ByteArray add4(int i) { 
      add(i >> 24 & 0xff);
      add(i >> 16 & 0xff);
      add(i >> 8 & 0xff);
      add(i & 0xff);
      return this;
    }


    ByteArray add2(int index) {
      add(index >> 8 & 0xff);
      add(index & 0xff);
      return this;
    }


    ByteArray emit(byte b) {
      changeStackDepth(BytecodeDebug.stackChange(b));
      add(b);
      return this;
    }


    ByteArray emitGoto(byte b) {
      changeStackDepth(BytecodeDebug.stackChange(b));
      lastGotoPos = pos;
      add(b);
      return this;
    }


    ByteArray emit(byte b, int stackChange) {
      changeStackDepth(stackChange);
      add(b);
      return this;
    }


    
    public int maxStackDepth() {
      return maxStackDepth;
    }


    public int stackDepth() {
      return stackDepth;
    }


    public void changeStackDepth(int i) {
      stackDepth += i;
      if(stackDepth > maxStackDepth)
        maxStackDepth = stackDepth;
    }


    
    public int pos() {return pos;}


    public int lastGotoPos() {return lastGotoPos;}


    public void setPos(int index) { pos = index; }


    public int size() {return pos;}


    public byte get(int index) {return bytes[index];}


    public void set(int index, byte value) {bytes[index] = value;}


    public String toString() {
      StringBuffer b = new StringBuffer();
      for(int i = 0; i < pos; i++) b.append(" " + bytes[i]);
      return b.toString();
    }


    public byte[] toArray() {
      byte[] b = new byte[pos];
      System.arraycopy(bytes, 0, b, 0, pos);
      return b;
    }


}
