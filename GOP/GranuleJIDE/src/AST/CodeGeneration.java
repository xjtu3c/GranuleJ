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
public class CodeGeneration extends java.lang.Object {

    private ByteArray bytes = new ByteArray();


    private ConstantPool constantPool;



    public void clearCodeGeneration() {
      bytes = null;
      constantPool = null;
      variableScopeLabelAddress = null;
      variableScopeLabelUses = null;
      localVariableTable = null;
      lineNumberTable = null;
      exceptions = null;
      address = null;
      uses = null;
    }


    
    private boolean wideGoto = false;



    private boolean numberFormatError = false;


    public boolean numberFormatError() { return numberFormatError; }



    public CodeGeneration(ConstantPool constantPool) {
      this.constantPool = constantPool;
    }



    public CodeGeneration(ConstantPool constantPool, boolean wideGoto) {
      this.constantPool = constantPool;
      this.wideGoto = wideGoto;
    }



    public ConstantPool constantPool() {
      return constantPool;
    }


    
    private int variableScopeLabel = 1;


    public int variableScopeLabel() {
      return variableScopeLabel++;
    }


    public void addVariableScopeLabel(int label) {
      Integer label_object = new Integer(label);
      variableScopeLabelAddress.put(label_object, new Integer(pos()));
      // Update all reference to this label
      if(variableScopeLabelUses.containsKey(label_object)) {
        ArrayList array = (ArrayList)variableScopeLabelUses.get(label_object);
        for(Iterator iter = array.iterator(); iter.hasNext(); ) {
          LocalVariableEntry e = (LocalVariableEntry)iter.next();
          e.length = pos() - e.start_pc;
        }
      }
    }

 
    private HashMap variableScopeLabelAddress = new HashMap();


    private HashMap variableScopeLabelUses = new HashMap();



    class LocalVariableEntry {
      int start_pc;
      int length;
      int name_index;
      int descriptor_index;
      int index;
    }


    public Collection localVariableTable = new ArrayList();


    public void addLocalVariableEntryAtCurrentPC(String name, String typeDescriptor, int localNum, int variableScopeEndLabel) {
      LocalVariableEntry e = new LocalVariableEntry();
      e.start_pc = pos();
      e.length = 0;    
      e.name_index = constantPool().addUtf8(name);
      e.descriptor_index = constantPool().addUtf8(typeDescriptor);
      e.index = localNum;
      localVariableTable.add(e);
      Integer label_object = new Integer(variableScopeEndLabel);
      if(!variableScopeLabelUses.containsKey(label_object))
        variableScopeLabelUses.put(label_object, new ArrayList());
      Collection c = (Collection)variableScopeLabelUses.get(label_object);
      c.add(e);
    }



    // at each variable declaration and parameter declaration
    // inh int VariableDeclaration.variableScopeEndLabel(CodeGeneration gen); 
    // addLocalVariableEntryAtCurrentPC(this, variableScopeEndLabel());
    // syn lazy int Block.variableScopeEndLabel(CodeGeneration gen) = gen.variableScopeLabel();
    //  Block.createBCode() { ... gen.addLabel(variableScopeLabel());
    
    class LineNumberEntry {
      int start_pc;
      int line_number;
    }


    public Collection lineNumberTable = new ArrayList();


    public void addLineNumberEntryAtCurrentPC(ASTNode node) {
      LineNumberEntry e = new LineNumberEntry();
      e.start_pc = pos();
      e.line_number = node.sourceLineNumber();
      if(e.line_number != -1 && e.line_number != 65535)
        lineNumberTable.add(e);
    }


    
    public Collection exceptions = new ArrayList();


    public void addException(int start_pc, int end_pc, int handler_pc, int catch_type) {
      ExceptionEntry e = new ExceptionEntry();
      e.start_pc = start_pc;
      e.end_pc = end_pc;
      e.handler_pc = handler_pc;
      e.catch_type = catch_type;
      if(e.start_pc != e.end_pc)
        exceptions.add(e);
    }


    class ExceptionEntry {
      int start_pc;
      int end_pc;
      int handler_pc;
      int catch_type;
    }


    public void createExceptionTable(TryStmt tryStmt) {  
      for(int i = 0; i < tryStmt.getNumCatchClause(); i++) {
      
      tryStmt.getCatchClause(i).exceptionTableEntries(this, tryStmt);
      }
      if(tryStmt.hasFinally()) {
        addException(
            addressOf(tryStmt.label_begin()),
            addressOf(tryStmt.label_finally()),
            addressOf(tryStmt.label_exception_handler()),
            0
            );
      }
    }


    public void createExceptionTable(SynchronizedStmt stmt) {  
      addException(
          addressOf(stmt.label_begin()),
          addressOf(stmt.label_finally()),
          addressOf(stmt.label_exception_handler()),
          0
          );
    }



    public int maxLocals() {
      return maxLocals+1;
    }


    int maxLocals = 0;



    /*
    public int label() {
      return labelCounter++;
    }
    private static int labelCounter = 1;
    */
    private HashMap address = new HashMap();


    private HashMap uses = new HashMap();


    public void addLabel(int label) {
      Integer label_object = new Integer(label);
      /*
      if(pos() - 3 == bytes.lastGotoPos() && bytes.get(pos() - 3) == Bytecode.GOTO) {
        if(uses.containsKey(label_object)) {
          ArrayList array = (ArrayList)uses.get(label_object);
          for(int i = 0; i < array.size(); i++) {
            int p = ((Integer)array.get(i)).intValue();
            if(pos() - 3 == p) {
              //System.out.println("Found direct branch");
              array.remove(i);
              i--;
            }
          }
          bytes.setPos(pos() - 3);
        }
      }
      */
      address.put(label_object, new Integer(pos()));
      // Update all reference to this label
      if(uses.containsKey(label_object)) {
        ArrayList array = (ArrayList)uses.get(label_object);
        for(int i = 0; i < array.size(); i++) {
          int p = ((Integer)array.get(i)).intValue();
          if(bytes.get(p) == Bytecode.GOTO_W)
            setAddress32(p + 1, pos() - p);
          else
            setAddress(p + 1, pos() -  p);
        }
      }
    }

 
    public int addressOf(int label) {
      Integer label_object = new Integer(label);
      if(!address.containsKey(label_object))
        throw new Error("Can not compute address of unplaced label");
      return ((Integer)address.get(label_object)).intValue();
    }


    private int jump(int label) {
      Integer label_object = new Integer(label);
      if(!uses.containsKey(label_object))
        uses.put(label_object, new ArrayList());
      ArrayList a = (ArrayList)uses.get(label_object);
      a.add(new Integer(pos())); // position of the 16-bits reference
      Integer val = (Integer)address.get(label_object);
      if(val != null)
        return val.intValue() - pos();
      return 0; // a position of 0 means not calculated yet
    }


    private void setAddress(int position, int address) {
      if(address > Short.MAX_VALUE || address < Short.MIN_VALUE)
        numberFormatError = true;
      bytes.set(position + 0, (byte)((address&0xff00)>>8));
      bytes.set(position + 1, (byte)(address&0xff));
    }


    private void setAddress32(int position, int address) {
      bytes.set(position + 0, (byte)(address >> 24 & 0xff));
      bytes.set(position + 1, (byte)(address >> 16 & 0xff));
      bytes.set(position + 2, (byte)(address >> 8 & 0xff));
      bytes.set(position + 3, (byte)(address & 0xff));
    }




    public void emitStoreReference(int pos) {
      maxLocals = Math.max(maxLocals, pos+1);
      if(pos == 0) emit(Bytecode.ASTORE_0);
      else if(pos == 1) emit(Bytecode.ASTORE_1);
      else if(pos == 2) emit(Bytecode.ASTORE_2);
      else if(pos == 3) emit(Bytecode.ASTORE_3);
      else if(pos < 256) emit(Bytecode.ASTORE).add(pos);
      else emit(Bytecode.WIDE).emit(Bytecode.ASTORE).add2(pos);
    }


    public void emitLoadReference(int pos) {
      maxLocals = Math.max(maxLocals, pos+1);
      if(pos == 0) emit(Bytecode.ALOAD_0);
      else if(pos == 1) emit(Bytecode.ALOAD_1);
      else if(pos == 2) emit(Bytecode.ALOAD_2);
      else if(pos == 3) emit(Bytecode.ALOAD_3);
      else if(pos < 256) emit(Bytecode.ALOAD).add(pos);
      else emit(Bytecode.WIDE).emit(Bytecode.ALOAD).add2(pos);
    }



    public void emitReturn() {
      bytes.emit(Bytecode.RETURN);
    }

	

    public void emitThrow() {
      bytes.emit(Bytecode.ATHROW);
    }



    public void emitInstanceof(TypeDecl type) {
      int p = constantPool().addClass(type.isArrayDecl() ? type.typeDescriptor() : type.constantPoolName());
      bytes.emit(Bytecode.INSTANCEOF).add2(p);
    }


    public void emitCheckCast(TypeDecl type) {
      int p = constantPool().addClass(type.isArrayDecl() ? type.typeDescriptor() : type.constantPoolName());
      bytes.emit(Bytecode.CHECKCAST).add2(p);
    }



    public void emitDup() {
      bytes.emit(Bytecode.DUP);
    }


    public void emitDup2() {
      bytes.emit(Bytecode.DUP2);
    }



    public void emitPop() {
      bytes.emit(Bytecode.POP);
    }


    
    public void emitSwap() {
      bytes.emit(Bytecode.SWAP);
    }



    public void emitBranchNonNull(int label) {
      int p = jump(label);
      bytes.emit(Bytecode.IFNONNULL).add2(p);
    }



    public void emitGoto(int label) {
      int p = jump(label);
      if(wideGoto)
        bytes.emitGoto(Bytecode.GOTO_W).add4(p);
      else {
        if(p > Short.MAX_VALUE || p < Short.MIN_VALUE)
          numberFormatError = true;
        bytes.emitGoto(Bytecode.GOTO).add2(p);
      }
    }



    public void emitJsr(int label) {
      int p = jump(label);
      bytes.emit(Bytecode.JSR).add2(p);
    }



    public void emitCompare(byte bytecode, int label) {
      int p = jump(label);
      bytes.emit(bytecode).add2(p);
    }



    public String toString() {
      return bytes.toString();
    }


    public int size() {return bytes.size();}


    public int pos() {return bytes.pos();}


    public byte[] toArray() {return bytes.toArray();}


    CodeGeneration add(int i) { return add((byte)i); }


    CodeGeneration add(byte b) { bytes.add(b); return this; }


    CodeGeneration add2(int index) {
      bytes.add2(index);
      return this; 
    }


    CodeGeneration add4(int index) {
      bytes.add4(index);
      return this;
    }


    CodeGeneration emit(byte b) {
      bytes.emit(b);
      return this;
    }


    CodeGeneration emit(byte b, int stackChange) {
      bytes.emit(b, stackChange);
      return this;
    }


    public int maxStackDepth() {
      return bytes.maxStackDepth();
    }


    public int stackDepth() {
      return bytes.stackDepth();
    }


    public void changeStackDepth(int i) {
      bytes.changeStackDepth(i);
    }


}
