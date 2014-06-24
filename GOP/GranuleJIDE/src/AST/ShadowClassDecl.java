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
 * @declaredat ShadowClass.ast:3
 */
public class ShadowClassDecl extends ReferenceType implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    SortGranule_LinkedList_values = null;
    SuperShadowClass_computed = false;
    SuperShadowClass_value = null;
    memberFields_String_values = null;
    memberFieldsMap_computed = false;
    memberFieldsMap_value = null;
    methodsSignatureMap_visited = -1;
    methodsSignatureMap_computed = false;
    methodsSignatureMap_initialized = false;
    methodsSignatureMap_value = null;
    memberTypes_String_values = null;
    ancestorMethods_String_values = null;
    accessibleFrom_TypeDecl_values = null;
    hasSuperConstructorClass_computed = false;
    superConstructorClass_computed = false;
    superConstructorClass_value = null;
    hasFieldInit_computed = false;
    noConstructor_computed = false;
    isObject_computed = false;
    instanceOf_TypeDecl_values = null;
    typeDescriptor_computed = false;
    typeDescriptor_value = null;
    packageName_computed = false;
    packageName_value = null;
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
  public ShadowClassDecl clone() throws CloneNotSupportedException {
    ShadowClassDecl node = (ShadowClassDecl)super.clone();
    node.SortGranule_LinkedList_values = null;
    node.SuperShadowClass_computed = false;
    node.SuperShadowClass_value = null;
    node.memberFields_String_values = null;
    node.memberFieldsMap_computed = false;
    node.memberFieldsMap_value = null;
    node.methodsSignatureMap_visited = -1;
    node.methodsSignatureMap_computed = false;
    node.methodsSignatureMap_initialized = false;
    node.methodsSignatureMap_value = null;
    node.memberTypes_String_values = null;
    node.ancestorMethods_String_values = null;
    node.accessibleFrom_TypeDecl_values = null;
    node.hasSuperConstructorClass_computed = false;
    node.superConstructorClass_computed = false;
    node.superConstructorClass_value = null;
    node.hasFieldInit_computed = false;
    node.noConstructor_computed = false;
    node.isObject_computed = false;
    node.instanceOf_TypeDecl_values = null;
    node.typeDescriptor_computed = false;
    node.typeDescriptor_value = null;
    node.packageName_computed = false;
    node.packageName_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ShadowClassDecl copy() {
      try {
        ShadowClassDecl node = (ShadowClassDecl)clone();
        if(children != null) node.children = (ASTNode[])children.clone();
        return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
  }
  /**
   * @apilvl low-level
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ShadowClassDecl fullCopy() {
    ShadowClassDecl res = (ShadowClassDecl)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:24
   */
  public boolean hasSeedClass() {
	return seedClass()!=null;

  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:28
   */
  public boolean hasGranuleAccess(){
    return granuleAccess(((TypeAccess)getGranuleAccess()).name())!=null;
  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:50
   */
  public void registerToShadowClass(){	
	   if(hasGranuleAccess()){
			 TypeDecl granuleclass=getGranuleAccess().type();
			 if(granuleclass instanceof GranuleDecl)
			 {
	             if(!((GranuleDecl)granuleclass).shadowclasses.contains(this))		     
					((GranuleDecl)granuleclass).shadowclasses.add(this);
			 }			 
			 }	
	}
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:84
   */
  public void registerShadowClass(){
	   if(hasSeedClass()){
		TypeDecl seedclass=seedClass();
		 if(seedclass instanceof ClassDecl)
		 {
               if(!((ClassDecl)seedclass).shadowclasses.contains(this))			     
				((ClassDecl)seedclass).shadowclasses.add(this);
		 }			 
	   }
         //super.registerShadowClass();
	}
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:562
   */
  public boolean hasSuperShadowClass()
{
	return hasSeedClass();
}
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1258
   */
  public void typeCheck(){
	 super.typeCheck();
	 TypeDecl seedClass=seedClass();
	 TypeDecl granuleAccess =granuleAccess(((TypeAccess)getGranuleAccess()).name());
	 if(granuleAccess!=null&&seedClass!=null&&granuleAccess.isGranuleDecl()){
		   GranuleDecl granule=(GranuleDecl)granuleAccess;
	    	TypeDecl type=((TypeAccess)(granule.getRootClassAccess())).type();
	    	if(type!=null&&type.isClassDecl()){
	        ClassDecl classtype=(ClassDecl)type;
	    	if(!((ClassDecl)seedClass).instanceOf(classtype))        
	    	error("the shadow class  "+name()+ " does not appear in the granule "+granule.name()+" ! because its seed class "+((ClassDecl)seedClass).name()+" is not sub class of the granule's root class "+classtype.name());
	    	}	
	 }
}
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1274
   */
  public void nameCheck() {
	 super.nameCheck();
	TypeDecl seedClass=seedClass();
    if(seedClass==null) 
    error("No type named " + getID() + " exsit as the seedclass of shadowclass,make sure seedclass is declared before shadowclass");
    else if(!seedClass.isClassDecl())
    error("SeedClass can only be ClassDecl");
    TypeDecl granuleAccess =granuleAccess(((TypeAccess)getGranuleAccess()).name());
    if(granuleAccess==null)
    error("Can not find granule "+ ((TypeAccess)getGranuleAccess()).name());
}
  /**
   * @ast method 
   * @aspect GOPModifier
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1400
   */
  public void checkModifiers() {
   super.checkModifiers();
    // 8.1.1.2 final Classes
   TypeDecl typeDecl = hasSuperShadowClass() ? SuperShadowClass() : null;
   if(typeDecl != null && typeDecl.isFinal()) {
    error("class " + fullName() + " may not extend final class " + typeDecl.fullName());
    }
}
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:87
   */
  public void toString(StringBuffer s)
  {
    s.append(indent());
    getModifiers().toString(s);
    s.append("class " +getID());
    s.append(" within ");
    getGranuleAccess().toString(s);
    s.append(" {");
        for(int i=0; i < getNumBodyDecl(); i++) {
      getBodyDecl(i).toString(s);
    }
    s.append(indent() + "}");
  }
  /**
   * @ast method 
   * @aspect shadowClassCodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GOPCodeGeneration.jrag:7
   */
  public void generateClassfile() {
    super.generateClassfile();
    String fileName = destinationPath() + File.separator + constantPoolName() + ".class";
    if(options().verbose()) System.out.println("Writing class file to " + fileName);
    try {
      ConstantPool cp = constantPool();
      cp.addClass(constantPoolName());
     if(hasSuperShadowClass()) {
      cp.addClass(SuperShadowClass().constantPoolName());
      } 
      if(hasGranuleAccess()){
        cp.addClass(granuleAccess(((TypeAccess)getGranuleAccess()).name()).constantPoolName());
      }


       for(Iterator iter = bcFields().iterator(); iter.hasNext(); ) {
        FieldDeclaration field = (FieldDeclaration) iter.next();    
        cp.addUtf8(field.name());
        cp.addUtf8(field.type().typeDescriptor());
        field.attributes();
      }
      if(needsEnclosing()) {
        cp.addUtf8("this$0");
        cp.addUtf8(enclosing().typeDescriptor());
        cp.addUtf8("Synthetic");
      }

      for(Iterator iter = bcMethods().iterator(); iter.hasNext(); ) {
        BodyDecl decl = (BodyDecl)iter.next();
        decl.touchMethod(cp);
      }
      if(hasClinit()) {
        cp.addUtf8("<clinit>");
        cp.addUtf8("()V");
        clinit_attributes();
      }
      attributes();


      // Actual ClassFile generation
      File dest = new File(fileName);
      File parentFile = dest.getParentFile();
      if(parentFile != null)
        parentFile.mkdirs();
      FileOutputStream f = new FileOutputStream(fileName);
      DataOutputStream out = new DataOutputStream(new BufferedOutputStream(f));
      out.writeInt(magicHeader());
      out.writeChar(minorVersion());
      out.writeChar(majorVersion());
      cp.emit(out);
      int flags = flags();
      if(isNestedType())
        flags = mangledFlags(flags);
      flags |= Modifiers.ACC_SUPER;
      out.writeChar(flags);
      out.writeChar(cp.addClass(constantPoolName()));
////================================================
      out.writeChar(hasSuperShadowClass() ? cp.addClass(SuperShadowClass().constantPoolName()) : 0);
      //out.writeChar(hasSeedClass() ? cp.addClass(seedClass(getID()).constantPoolName()) : 0);
      //out.writeChar(hasGranuleAccess() ? cp.addClass(granuleAccess(((TypeAccess)getGranuleAccess()).name()).constantPoolName()) : 0);
      out.writeChar(0);


      Collection fields = bcFields();
      out.writeChar(fields.size() + (needsEnclosing() ? 1 : 0));
      for(Iterator iter = fields.iterator(); iter.hasNext(); ) {
        FieldDeclaration field = (FieldDeclaration) iter.next();
        out.writeChar(field.flags());
        out.writeChar(cp.addUtf8(field.name()));
        out.writeChar(cp.addUtf8(field.type().typeDescriptor()));
        out.writeChar(field.attributes().size());
        for(Iterator itera = field.attributes().iterator(); itera.hasNext();)
          ((Attribute)itera.next()).emit(out);
      }
      if(needsEnclosing()) {
        out.writeChar(0 /*Modifiers.ACC_PRIVATE*/);
        //System.out.println("da huang ye zi ti shi enclosing :"+enclosing().typeName());
        out.writeChar(cp.addUtf8("this$0"));
        out.writeChar(cp.addUtf8(enclosing().typeDescriptor()));
        out.writeChar(1);
        new SyntheticAttribute(cp).emit(out);

      }

      Collection methods = bcMethods();
      out.writeChar(methods.size() + (hasClinit() ? 1 : 0));
      for(Iterator iter = methods.iterator(); iter.hasNext(); ) {
        BodyDecl b = (BodyDecl)iter.next();
        b.generateMethod(out, cp);
      }
      if(hasClinit()) {
        out.writeChar(Modifiers.ACC_STATIC);
        out.writeChar(cp.addUtf8("<clinit>"));
        out.writeChar(cp.addUtf8("()V"));
        out.writeChar(clinit_attributes().size());
        for(Iterator itera = clinit_attributes().iterator(); itera.hasNext();)
          ((Attribute)itera.next()).emit(out);
      }
      out.writeChar(attributes().size());
      for(Iterator itera = attributes().iterator(); itera.hasNext();)
        ((Attribute)itera.next()).emit(out);

      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /**
   * @ast method 
   * @declaredat ShadowClass.ast:1
   */
  public ShadowClassDecl() {
    super();

    setChild(new List(), 2);

  }
  /**
   * @ast method 
   * @declaredat ShadowClass.ast:8
   */
  public ShadowClassDecl(Modifiers p0, String p1, Access p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @ast method 
   * @declaredat ShadowClass.ast:14
   */
  public ShadowClassDecl(Modifiers p0, beaver.Symbol p1, Access p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ShadowClass.ast:23
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat ShadowClass.ast:29
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ShadowClass.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**
   * @ast method 
   * @declaredat ShadowClass.ast:8
   */
  public void setID(beaver.Symbol symbol) {
    if(symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setID is only valid for String lexemes");
    tokenString_ID = (String)symbol.value;
    IDstart = symbol.getStart();
    IDend = symbol.getEnd();
  }
  /**
   * Getter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:19
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for GranuleAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:5
   */
  public void setGranuleAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for GranuleAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:12
   */
  public Access getGranuleAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ShadowClass.ast:18
   */
  public Access getGranuleAccessNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * Setter for BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:5
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 2);
  }
  /**
   * @return number of children in BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:12
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Getter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl)getBodyDeclList().getChild(i);
  }
  /**
   * Add element to list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:27
   */
  public void addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null || state == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ShadowClass.ast:34
   */
  public void addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:42
   */
  public void setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for BodyDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:50
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ShadowClass.ast:56
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * Getter for list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat ShadowClass.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclList() {
    List<BodyDecl> list = (List<BodyDecl>)getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat ShadowClass.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>)getChildNoTransform(2);
  }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:15
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String name() {
      ASTNode$State state = state();
    String name_value = name_compute();
    return name_value;
  }
  /**
   * @apilvl internal
   */
  private String name_compute() {  return ((TypeAccess)getGranuleAccess()).name()+"_"+getID();  }
  protected java.util.Map SortGranule_LinkedList_values;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:478
   */
  @SuppressWarnings({"unchecked", "cast"})
  public LinkedList SortGranule(LinkedList granuleset) {
    Object _parameters = granuleset;
    if(SortGranule_LinkedList_values == null) SortGranule_LinkedList_values = new java.util.HashMap(4);
    if(SortGranule_LinkedList_values.containsKey(_parameters)) {
      return (LinkedList)SortGranule_LinkedList_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    LinkedList SortGranule_LinkedList_value = SortGranule_compute(granuleset);
if(isFinal && num == state().boundariesCrossed) SortGranule_LinkedList_values.put(_parameters, SortGranule_LinkedList_value);
    return SortGranule_LinkedList_value;
  }
  /**
   * @apilvl internal
   */
  private LinkedList SortGranule_compute(LinkedList granuleset) {
	//CompilationUnit root=getCompilationUnit();
        Program root=getProgram();
	GranuleTree granuletree=root.CreateGranuleTree();
	LinkedList granulesetcopy=new LinkedList();
	if(granuletree!=null){
	for(Iterator iter=granuletree.toList().iterator();iter.hasNext();){
	  GranuleTreeNode granulenode=(GranuleTreeNode)iter.next();
	  if(granulenode.getGranulename().equals("g0"))
	  continue;
	  boolean found=false;		  
	  for(Iterator iter1=granuleset.iterator();iter1.hasNext()&&!found;)
	  {
		  ShadowClassDecl del=(ShadowClassDecl)iter1.next();
		  String granulename=((TypeAccess)del.getGranuleAccess()).name();
		  if(granulenode.getGranulename().equals(granulename))
		  {
		  granulesetcopy.add(del);
		  found=true;
		  }
	  }
   }
   }
	return granulesetcopy;
}
  /**
   * @apilvl internal
   */
  protected boolean SuperShadowClass_computed = false;
  /**
   * @apilvl internal
   */
  protected TypeDecl SuperShadowClass_value;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:536
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl SuperShadowClass() {
    if(SuperShadowClass_computed) {
      return SuperShadowClass_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SuperShadowClass_value = SuperShadowClass_compute();
if(isFinal && num == state().boundariesCrossed) SuperShadowClass_computed = true;
    return SuperShadowClass_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl SuperShadowClass_compute() {
	  if(hasSeedClass()){
		  TypeDecl seedclass=seedClass();
		  if(seedclass.isClassDecl()){
		  LinkedList mylist=new LinkedList(((ClassDecl)seedclass).shadowclasses());
		  if(mylist.size()==1)
		   return seedclass;
		  else {		
		   LinkedList mylistt=new LinkedList(SortGranule(mylist));         
		   ListIterator iterator=mylistt.listIterator(mylistt.size());
			 boolean found=false;
			  while(iterator.hasPrevious()&&!found){			  
				 ShadowClassDecl tyype=(ShadowClassDecl)iterator.previous();				
				  if(tyype==this)
					found=true;
			  }
			  if(iterator.hasPrevious())
			  return (ShadowClassDecl)iterator.previous();
                          else
			   return seedclass;
			 }
			}
		} 
	return null;
	//return (ClassDecl)typeObject();
}
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:621
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet memberFields(String name) {
    Object _parameters = name;
    if(memberFields_String_values == null) memberFields_String_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if(memberFields_String_values.containsKey(_parameters)) {
      Object _o = memberFields_String_values.get(_parameters);
      if(!(_o instanceof ASTNode$State.CircularValue)) {
        return (SimpleSet)_o;
      }
      else
        _value = (ASTNode$State.CircularValue)_o;
    }
    else {
      _value = new ASTNode$State.CircularValue();
      memberFields_String_values.put(_parameters, _value);
      _value.value = localFields(name);
    }
    ASTNode$State state = state();
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
      int num = state.boundariesCrossed;
      boolean isFinal = this.is$Final();
      SimpleSet new_memberFields_String_value;
      do {
        _value.visited = new Integer(state.CIRCLE_INDEX);
        state.CHANGE = false;
        new_memberFields_String_value = memberFields_compute(name);
        if ((new_memberFields_String_value==null && (SimpleSet)_value.value!=null) || (new_memberFields_String_value!=null && !new_memberFields_String_value.equals((SimpleSet)_value.value))) {
          state.CHANGE = true;
          _value.value = new_memberFields_String_value;
        }
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
        memberFields_String_values.put(_parameters, new_memberFields_String_value);
      }
      else {
        memberFields_String_values.remove(_parameters);
      state.RESET_CYCLE = true;
      memberFields_compute(name);
      state.RESET_CYCLE = false;
      }
      state.IN_CIRCLE = false; 
      return new_memberFields_String_value;
    }
    if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
      _value.visited = new Integer(state.CIRCLE_INDEX);
      SimpleSet new_memberFields_String_value = memberFields_compute(name);
      if (state.RESET_CYCLE) {
        memberFields_String_values.remove(_parameters);
      }
      else if ((new_memberFields_String_value==null && (SimpleSet)_value.value!=null) || (new_memberFields_String_value!=null && !new_memberFields_String_value.equals((SimpleSet)_value.value))) {
        state.CHANGE = true;
        _value.value = new_memberFields_String_value;
      }
      return new_memberFields_String_value;
    }
    return (SimpleSet)_value.value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet memberFields_compute(String name) {
    SimpleSet fields=localFields(name); 
    if(!fields.isEmpty())    	  
      return fields;  
    if(hasSuperShadowClass()){  
      for(Iterator iter=SuperShadowClass().memberFields(name).iterator(); iter.hasNext(); ) {
 //===============gop==========================
    	  Object decll=iter.next();    
          if(decll instanceof ContextVarDeclaration) {
        	 ContextVarDeclaration decl=(ContextVarDeclaration)decll;
        	 fields = fields.add(decl);
          }
          else {
          FieldDeclaration decl=(FieldDeclaration)decll;
    	  //FieldDeclaration decl=(FieldDeclaration)iter.next(); 
          if(!decl.isPrivate()&&decl.accessibleFrom(this))
           fields = fields.add(decl);
          }
        }   
     if(!fields.isEmpty())
     return fields;
  
}
    //TypeDecl seedclass=seedClass(getID());
    TypeDecl seedclass=seedClass();
	if(seedclass.isClassDecl()){
	  ClassDecl classseed=(ClassDecl)seedclass;
    if(classseed.hasSuperclass()){
        for(Iterator iter=classseed.superclass().memberFields(name).iterator(); iter.hasNext(); ) {
            FieldDeclaration decl=(FieldDeclaration)iter.next();
            fields = fields.add(decl);
         }
        for(Iterator iter1=fields.iterator();iter1.hasNext();)
        {
       	 FieldDeclaration decl=(FieldDeclaration)iter1.next();
        }
         if(!fields.isEmpty())
         return fields;
    }
	}
    TypeDecl granuleIn = granuleAccess(((TypeAccess)getGranuleAccess()).name());
    if(granuleIn!=null){
    for(Iterator iter=granuleIn.memberFields(name).iterator(); iter.hasNext(); ) {
    	    Object decll=iter.next();
	    if(decll instanceof ContextVarDeclaration){
 		ContextVarDeclaration decl=(ContextVarDeclaration)decll;
 		fields = fields.add(decl);
	   }
	    else if(decll instanceof FieldDeclaration)
	      {
				FieldDeclaration decl=(FieldDeclaration)decll;
				fields=fields.add(decl);
		  }  
     }
     if(!fields.isEmpty())
     return fields;
    }

   return fields;
  }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:681
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap memberFieldsMap() {
    if(memberFieldsMap_computed) {
      return memberFieldsMap_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    memberFieldsMap_value = memberFieldsMap_compute();
if(isFinal && num == state().boundariesCrossed) memberFieldsMap_computed = true;
    return memberFieldsMap_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap memberFieldsMap_compute() {
	    HashMap map = new HashMap(localFieldsMap());
	    if(hasSuperShadowClass()) {
	      for(Iterator iter=SuperShadowClass().fieldsIterator(); iter.hasNext(); ) {
	        FieldDeclaration decl= (FieldDeclaration)iter.next();
	        if(!decl.isPrivate() && decl.accessibleFrom(this) && !localFieldsMap().containsKey(decl.name()))
	          putSimpleSetElement(map, decl.name(), decl);
	      }
	    }	
	    return map;
 }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:694
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection memberMethods(String name) {
      ASTNode$State state = state();
    Collection memberMethods_String_value = memberMethods_compute(name);
    return memberMethods_String_value;
  }
  /**
   * @apilvl internal
   */
  private Collection memberMethods_compute(String name) {
    Collection c = (Collection)methodsNameMap().get(name);
    if(c != null) return c;
    return Collections.EMPTY_LIST;
  }
  /**
   * @apilvl internal
   */
  protected int methodsSignatureMap_visited = -1;
  /**
   * @apilvl internal
   */
  protected boolean methodsSignatureMap_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean methodsSignatureMap_initialized = false;
  /**
   * @apilvl internal
   */
  protected HashMap methodsSignatureMap_value;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:700
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap methodsSignatureMap() {
    if(methodsSignatureMap_computed) {
      return methodsSignatureMap_value;
    }
    ASTNode$State state = state();
    if (!methodsSignatureMap_initialized) {
      methodsSignatureMap_initialized = true;
      methodsSignatureMap_value = new HashMap();
    }
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
    int num = state.boundariesCrossed;
    boolean isFinal = this.is$Final();
      do {
        methodsSignatureMap_visited = state.CIRCLE_INDEX;
        state.CHANGE = false;
        HashMap new_methodsSignatureMap_value = methodsSignatureMap_compute();
        if ((new_methodsSignatureMap_value==null && methodsSignatureMap_value!=null) || (new_methodsSignatureMap_value!=null && !new_methodsSignatureMap_value.equals(methodsSignatureMap_value)))
          state.CHANGE = true;
        methodsSignatureMap_value = new_methodsSignatureMap_value; 
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
      methodsSignatureMap_computed = true;
      }
      else {
      state.RESET_CYCLE = true;
      methodsSignatureMap_compute();
      state.RESET_CYCLE = false;
        methodsSignatureMap_computed = false;
        methodsSignatureMap_initialized = false;
      }
      state.IN_CIRCLE = false; 
      return methodsSignatureMap_value;
    }
    if(methodsSignatureMap_visited != state.CIRCLE_INDEX) {
      methodsSignatureMap_visited = state.CIRCLE_INDEX;
      if (state.RESET_CYCLE) {
        methodsSignatureMap_computed = false;
        methodsSignatureMap_initialized = false;
        methodsSignatureMap_visited = -1;
        return methodsSignatureMap_value;
      }
      HashMap new_methodsSignatureMap_value = methodsSignatureMap_compute();
      if ((new_methodsSignatureMap_value==null && methodsSignatureMap_value!=null) || (new_methodsSignatureMap_value!=null && !new_methodsSignatureMap_value.equals(methodsSignatureMap_value)))
        state.CHANGE = true;
      methodsSignatureMap_value = new_methodsSignatureMap_value; 
      return methodsSignatureMap_value;
    }
    return methodsSignatureMap_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap methodsSignatureMap_compute() {
	 HashMap map = new HashMap(localMethodsSignatureMap());
	 if(hasSuperShadowClass()){
	    for(Iterator iter=SuperShadowClass().methodsIterator();iter.hasNext();){
	      MethodDecl m = (MethodDecl)iter.next();
	       if(!m.isPrivate() && m.accessibleFrom(this) && !localMethodsSignatureMap().containsKey(m.signature()))
	       putSimpleSetElement(map, m.signature(), m);
	     }
 }
	return map;
}
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:712
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet memberTypes(String name) {
    Object _parameters = name;
    if(memberTypes_String_values == null) memberTypes_String_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if(memberTypes_String_values.containsKey(_parameters)) {
      Object _o = memberTypes_String_values.get(_parameters);
      if(!(_o instanceof ASTNode$State.CircularValue)) {
        return (SimpleSet)_o;
      }
      else
        _value = (ASTNode$State.CircularValue)_o;
    }
    else {
      _value = new ASTNode$State.CircularValue();
      memberTypes_String_values.put(_parameters, _value);
      _value.value = localTypeDecls(name);
    }
    ASTNode$State state = state();
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
      int num = state.boundariesCrossed;
      boolean isFinal = this.is$Final();
      SimpleSet new_memberTypes_String_value;
      do {
        _value.visited = new Integer(state.CIRCLE_INDEX);
        state.CHANGE = false;
        new_memberTypes_String_value = memberTypes_compute(name);
        if ((new_memberTypes_String_value==null && (SimpleSet)_value.value!=null) || (new_memberTypes_String_value!=null && !new_memberTypes_String_value.equals((SimpleSet)_value.value))) {
          state.CHANGE = true;
          _value.value = new_memberTypes_String_value;
        }
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
        memberTypes_String_values.put(_parameters, new_memberTypes_String_value);
      }
      else {
        memberTypes_String_values.remove(_parameters);
      state.RESET_CYCLE = true;
      memberTypes_compute(name);
      state.RESET_CYCLE = false;
      }
      state.IN_CIRCLE = false; 
      return new_memberTypes_String_value;
    }
    if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
      _value.visited = new Integer(state.CIRCLE_INDEX);
      SimpleSet new_memberTypes_String_value = memberTypes_compute(name);
      if (state.RESET_CYCLE) {
        memberTypes_String_values.remove(_parameters);
      }
      else if ((new_memberTypes_String_value==null && (SimpleSet)_value.value!=null) || (new_memberTypes_String_value!=null && !new_memberTypes_String_value.equals((SimpleSet)_value.value))) {
        state.CHANGE = true;
        _value.value = new_memberTypes_String_value;
      }
      return new_memberTypes_String_value;
    }
    return (SimpleSet)_value.value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet memberTypes_compute(String name) {
   	    SimpleSet set=localTypeDecls(name);	
	    if(!set.isEmpty()) return set;
	    if(hasSuperShadowClass()){
	      for(Iterator iter =SuperShadowClass().memberTypes(name).iterator(); iter.hasNext(); ) {
	        TypeDecl decl = (TypeDecl)iter.next();	 
	        if(!decl.isPrivate() && decl.accessibleFrom(this)) {
	          set = set.add(decl);
	        }	      
	    }
	  }
	    return set;
}
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:727
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet ancestorMethods(String signature) {
    Object _parameters = signature;
    if(ancestorMethods_String_values == null) ancestorMethods_String_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if(ancestorMethods_String_values.containsKey(_parameters)) {
      Object _o = ancestorMethods_String_values.get(_parameters);
      if(!(_o instanceof ASTNode$State.CircularValue)) {
        return (SimpleSet)_o;
      }
      else
        _value = (ASTNode$State.CircularValue)_o;
    }
    else {
      _value = new ASTNode$State.CircularValue();
      ancestorMethods_String_values.put(_parameters, _value);
      _value.value = SimpleSet.emptySet;
    }
    ASTNode$State state = state();
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
      int num = state.boundariesCrossed;
      boolean isFinal = this.is$Final();
      SimpleSet new_ancestorMethods_String_value;
      do {
        _value.visited = new Integer(state.CIRCLE_INDEX);
        state.CHANGE = false;
        new_ancestorMethods_String_value = ancestorMethods_compute(signature);
        if ((new_ancestorMethods_String_value==null && (SimpleSet)_value.value!=null) || (new_ancestorMethods_String_value!=null && !new_ancestorMethods_String_value.equals((SimpleSet)_value.value))) {
          state.CHANGE = true;
          _value.value = new_ancestorMethods_String_value;
        }
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
        ancestorMethods_String_values.put(_parameters, new_ancestorMethods_String_value);
      }
      else {
        ancestorMethods_String_values.remove(_parameters);
      state.RESET_CYCLE = true;
      ancestorMethods_compute(signature);
      state.RESET_CYCLE = false;
      }
      state.IN_CIRCLE = false; 
      return new_ancestorMethods_String_value;
    }
    if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
      _value.visited = new Integer(state.CIRCLE_INDEX);
      SimpleSet new_ancestorMethods_String_value = ancestorMethods_compute(signature);
      if (state.RESET_CYCLE) {
        ancestorMethods_String_values.remove(_parameters);
      }
      else if ((new_ancestorMethods_String_value==null && (SimpleSet)_value.value!=null) || (new_ancestorMethods_String_value!=null && !new_ancestorMethods_String_value.equals((SimpleSet)_value.value))) {
        state.CHANGE = true;
        _value.value = new_ancestorMethods_String_value;
      }
      return new_ancestorMethods_String_value;
    }
    return (SimpleSet)_value.value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet ancestorMethods_compute(String signature) {	
    SimpleSet set = SimpleSet.emptySet;
    if(hasSuperShadowClass()){    	
     for(Iterator iter=SuperShadowClass().localMethodsSignature(signature).iterator(); iter.hasNext();) {
       MethodDecl m = (MethodDecl)iter.next();
        if(!m.isPrivate())
         set = set.add(m);
     }
    }
  if(hasSuperShadowClass()&&SuperShadowClass().isClassDecl()){
     if(set.size() != 1 || ((MethodDecl)set.iterator().next()).isAbstract()) { 
      for(Iterator iter =((ClassDecl)SuperShadowClass()).interfacesMethodsSignature(signature).iterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        set = set.add(m);
     }
  }
}
   if(!hasSuperShadowClass()) return set;
     if(set.size() == 1) {
      MethodDecl m = (MethodDecl)set.iterator().next();
      if(!m.isAbstract()) {
       boolean done = true;
        for(Iterator iter=SuperShadowClass().ancestorMethods(signature).iterator(); iter.hasNext(); ) {
         MethodDecl n = (MethodDecl)iter.next();
         if(n.isPrivate() || !n.accessibleFrom(m.hostType()))
          done = false;
        }
        if(done) return set;
      }
    }
    for(Iterator iter =SuperShadowClass().ancestorMethods(signature).iterator(); iter.hasNext();){
     MethodDecl m = (MethodDecl)iter.next();
      set = set.add(m);
    }
    return set;
  }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1035
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl granuleAccess(String name) {
      ASTNode$State state = state();
    TypeDecl granuleAccess_String_value = granuleAccess_compute(name);
    return granuleAccess_String_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl granuleAccess_compute(String name) {
       TypeDecl decl = lookupType(packageName(),name);
   if(decl!=null){
     if(!decl.isGranuleDecl())
     return null;
  else 
     return decl;
   }
   return null;
  }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1054
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ClassDecl seedClass() {
      ASTNode$State state = state();
    ClassDecl seedClass_value = seedClass_compute();
    return seedClass_value;
  }
  /**
   * @apilvl internal
   */
  private ClassDecl seedClass_compute() {
	 String name=getID();
	 String typename=name;
	 String pacg="";
	 ClassDecl classdecl=null;
	 if(name.contains(".")){ 
	 pacg=name.substring(0,name.lastIndexOf(".")); 
	 typename=name.substring(name.lastIndexOf(".")+1);
	 SimpleSet c=lookupType(pacg,getID());
	 if(!c.isEmpty())
	 classdecl=(ClassDecl)c.iterator().next();
	 else {
     System.err.println("shadow class " +name() + " can not be found seed class  "+getID());
	 }
	 }
	 else{
	 SimpleSet c=lookupType(getID());
	 if(c.size()>0)
	 classdecl=(ClassDecl)c.iterator().next();
	 else {
	 throw new Error("shadow class " +name() + " can not be found seed class "+getID());
	 }
	 } 	 
	 return classdecl;
 }
  /*syn TypeDecl GranuleDecl.superGranule(String name){
       TypeDecl decl = lookupType(packageName(),name);
       if(decl!=null){
       if(!decl.isGranuleDecl())
         return null;
       else 
         return decl;
       }
       return null;
  }* @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1107
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isShadowClassDecl() {
      ASTNode$State state = state();
    boolean isShadowClassDecl_value = isShadowClassDecl_compute();
    return isShadowClassDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isShadowClassDecl_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1414
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean accessibleFrom(TypeDecl type) {
    Object _parameters = type;
    if(accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
    if(accessibleFrom_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)accessibleFrom_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
if(isFinal && num == state().boundariesCrossed) accessibleFrom_TypeDecl_values.put(_parameters, Boolean.valueOf(accessibleFrom_TypeDecl_value));
    return accessibleFrom_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean accessibleFrom_compute(TypeDecl type) {  return true;  }
  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:27
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection lookupSuperConstructor() {
      ASTNode$State state = state();
    Collection lookupSuperConstructor_value = lookupSuperConstructor_compute();
    return lookupSuperConstructor_value;
  }
  /**
   * @apilvl internal
   */
  private Collection lookupSuperConstructor_compute() {  return hasSuperShadowClass()?SuperShadowClass().constructors():Collections.EMPTY_LIST;  }
  /**
   * @apilvl internal
   */
  protected boolean hasSuperConstructorClass_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean hasSuperConstructorClass_value;
  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:33
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasSuperConstructorClass() {
    if(hasSuperConstructorClass_computed) {
      return hasSuperConstructorClass_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    hasSuperConstructorClass_value = hasSuperConstructorClass_compute();
if(isFinal && num == state().boundariesCrossed) hasSuperConstructorClass_computed = true;
    return hasSuperConstructorClass_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasSuperConstructorClass_compute() {
	  return !isObject();
  }
  /**
   * @apilvl internal
   */
  protected boolean superConstructorClass_computed = false;
  /**
   * @apilvl internal
   */
  protected ClassDecl superConstructorClass_value;
  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ClassDecl superConstructorClass() {
    if(superConstructorClass_computed) {
      return superConstructorClass_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    superConstructorClass_value = superConstructorClass_compute();
if(isFinal && num == state().boundariesCrossed) superConstructorClass_computed = true;
    return superConstructorClass_value;
  }
  /**
   * @apilvl internal
   */
  private ClassDecl superConstructorClass_compute() {
	  if(isObject())
	  return null;
	  else
	  return (ClassDecl)typeObject();
  }
  /**
   * @apilvl internal
   */
  protected boolean hasFieldInit_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean hasFieldInit_value;
  /**
   * @attribute syn
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:354
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasFieldInit() {
    if(hasFieldInit_computed) {
      return hasFieldInit_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    hasFieldInit_value = hasFieldInit_compute();
if(isFinal && num == state().boundariesCrossed) hasFieldInit_computed = true;
    return hasFieldInit_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasFieldInit_compute() {
	  for(int i = 0; i < getNumBodyDecl(); i++) {
		  if(getBodyDecl(i) instanceof FieldDeclaration){
		   FieldDeclaration f=(FieldDeclaration)getBodyDecl(i);
		   if(!f.isStatic()&& f.hasInit())
		   return true;
		  }   
	  }
	  return false;
  }
  /**
   * @apilvl internal
   */
  protected boolean noConstructor_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean noConstructor_value;
  /**
   * @attribute syn
   * @aspect ImplicitConstructor
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:423
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean noConstructor() {
    if(noConstructor_computed) {
      return noConstructor_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    noConstructor_value = noConstructor_compute();
if(isFinal && num == state().boundariesCrossed) noConstructor_computed = true;
    return noConstructor_value;
  }
  /**
   * @apilvl internal
   */
  private boolean noConstructor_compute() {
	 for(int i = 0; i < getNumBodyDecl(); i++)
	    if(getBodyDecl(i) instanceof ConstructorDecl)
	       return false;
	    return true;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:230
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isObject() {
    if(isObject_computed) {
      return isObject_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isObject_value = isObject_compute();
if(isFinal && num == state().boundariesCrossed) isObject_computed = true;
    return isObject_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isObject_compute() {  return name().equals("Object")&&packageName().equals("java.lang");  }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:513
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean instanceOf(TypeDecl type) {
    Object _parameters = type;
    if(instanceOf_TypeDecl_values == null) instanceOf_TypeDecl_values = new java.util.HashMap(4);
    if(instanceOf_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)instanceOf_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean instanceOf_TypeDecl_value = instanceOf_compute(type);
if(isFinal && num == state().boundariesCrossed) instanceOf_TypeDecl_values.put(_parameters, Boolean.valueOf(instanceOf_TypeDecl_value));
    return instanceOf_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean instanceOf_compute(TypeDecl type) {
   if(type.isShadowClassDecl())
   return type.isSupertypeOfShadowClassDecl(this);	 
   else if(type.isClassDecl())
   {
	  if(SuperShadowClass().isClassDecl()){
		ClassDecl supershadowclass=(ClassDecl)SuperShadowClass();
		return type.isSupertypeOfClassDecl(supershadowclass);
	   }
	   else
	   {
		  return  SuperShadowClass().instanceOf(type);
	   }  
   }
   else return false;
  }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:552
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSupertypeOfShadowClassDecl(ShadowClassDecl type) {
      ASTNode$State state = state();
    boolean isSupertypeOfShadowClassDecl_ShadowClassDecl_value = isSupertypeOfShadowClassDecl_compute(type);
    return isSupertypeOfShadowClassDecl_ShadowClassDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSupertypeOfShadowClassDecl_compute(ShadowClassDecl type) {
		  if(super.isSupertypeOfShadowClassDecl(type))
		  return true;
		  return ((ShadowClassDecl)type).hasSuperShadowClass()&&((ShadowClassDecl)type).SuperShadowClass()!=null
		  &&((ShadowClassDecl)type).SuperShadowClass().instanceOf(this);
  }
  /**
   * @attribute syn
   * @aspect NestedTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:673
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isInnerClass() {
      ASTNode$State state = state();
    boolean isInnerClass_value = isInnerClass_compute();
    return isInnerClass_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isInnerClass_compute() {  return isNestedType() && !isStatic() && enclosingType().isGranuleDecl();  }
  /**
   * @attribute syn
   * @aspect shadowClassCodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GOPCodeGeneration.jrag:3
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String typeDescriptor() {
    if(typeDescriptor_computed) {
      return typeDescriptor_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    typeDescriptor_value = typeDescriptor_compute();
if(isFinal && num == state().boundariesCrossed) typeDescriptor_computed = true;
    return typeDescriptor_value;
  }
  /**
   * @apilvl internal
   */
  private String typeDescriptor_compute() {  return "L" + constantPoolName() + ";";  }
  /**
   * @attribute syn
   * @aspect shadowClassCodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GOPCodeGeneration.jrag:4
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String arrayTypeDescriptor() {
      ASTNode$State state = state();
    String arrayTypeDescriptor_value = arrayTypeDescriptor_compute();
    return arrayTypeDescriptor_value;
  }
  /**
   * @apilvl internal
   */
  private String arrayTypeDescriptor_compute() {  return constantPoolName();  }
  /**
   * @apilvl internal
   */
  protected boolean packageName_computed = false;
  /**
   * @apilvl internal
   */
  protected String packageName_value;
  /**
   * @attribute inh
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:17
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String packageName() {
    if(packageName_computed) {
      return packageName_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    packageName_value = getParent().Define_String_packageName(this, null);
if(isFinal && num == state().boundariesCrossed) packageName_computed = true;
    return packageName_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:21
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getGranuleAccessNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return super.Define_NameType_nameType(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:600
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getBodyDeclListNoTransform()) { 
   int i = caller.getIndexOfChild(child);
{   
    SimpleSet list = memberFields(name);
    if(!list.isEmpty()) return list;
    if(hasSeedClass()){
   //ClassDecl seedclass=(ClassDecl)seedClass(getID());
    ClassDecl seedclass=seedClass();
	while(seedclass.hasSuperclass())
    {
	  ClassDecl superclass=(ClassDecl)seedclass.superclass();
	  if(superclass.existShadowClass()){
	  list=superclass.UnionMemberFields(name);
	  if(!list.isEmpty()) return list;
	  }
	  seedclass=superclass;
	 }
    }
    list=lookupVariable(name);
    if(inStaticContext() || isStatic())
      list=removeInstanceVariables(list);
    return list;
  }
}
    return super.Define_SimpleSet_lookupVariable(caller, child, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1408
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return super.Define_boolean_mayBeFinal(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:654
   * @apilvl internal
   */
  public boolean Define_boolean_isNestedType(ASTNode caller, ASTNode child) {
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return super.Define_boolean_isNestedType(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:717
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
    if(caller == getGranuleAccessNoTransform()) {
      return hostType();
    }
    return super.Define_TypeDecl_hostType(caller, child);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
