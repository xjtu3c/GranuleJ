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
 * @declaredat java.ast:78
 */
public class FieldDeclaration extends MemberDecl implements Cloneable, SimpleSet, Iterator, Variable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    accessibleFrom_TypeDecl_values = null;
    exceptions_computed = false;
    exceptions_value = null;
    isDAafter_Variable_values = null;
    isDUafter_Variable_values = null;
    fieldToVarMap_visited = -1;
    fieldToVarMap_computed = false;
    fieldToVarMap_initialized = false;
    fieldToVarMap_value = null;
    constant_computed = false;
    constant_value = null;
    attributes_computed = false;
    attributes_value = null;
    flags_computed = false;
    usesTypeVariable_computed = false;
    sourceVariableDecl_computed = false;
    sourceVariableDecl_value = null;
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
  public FieldDeclaration clone() throws CloneNotSupportedException {
    FieldDeclaration node = (FieldDeclaration)super.clone();
    node.accessibleFrom_TypeDecl_values = null;
    node.exceptions_computed = false;
    node.exceptions_value = null;
    node.isDAafter_Variable_values = null;
    node.isDUafter_Variable_values = null;
    node.fieldToVarMap_visited = -1;
    node.fieldToVarMap_computed = false;
    node.fieldToVarMap_initialized = false;
    node.fieldToVarMap_value = null;
    node.constant_computed = false;
    node.constant_value = null;
    node.attributes_computed = false;
    node.attributes_value = null;
    node.flags_computed = false;
    node.usesTypeVariable_computed = false;
    node.sourceVariableDecl_computed = false;
    node.sourceVariableDecl_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public FieldDeclaration copy() {
      try {
        FieldDeclaration node = (FieldDeclaration)clone();
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
  public FieldDeclaration fullCopy() {
    FieldDeclaration res = (FieldDeclaration)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect BoundNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BoundNames.jrag:11
   */
  public Access createQualifiedBoundAccess() {
    if(isStatic())
      return hostType().createQualifiedAccess().qualifiesAccess(new BoundFieldAccess(this));
    else
      return new ThisAccess("this").qualifiesAccess(
        new BoundFieldAccess(this));
  }
  /**
   * @ast method 
   * @aspect BoundNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BoundNames.jrag:100
   */
  public Access createBoundFieldAccess() {
    return createQualifiedBoundAccess();
  }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:81
   */
  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:85
   */
  public boolean isSingleton() { return true; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:86
   */
  public boolean isSingleton(Object o) { return contains(o); }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:93
   */
  
  private FieldDeclaration iterElem;
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:94
   */
  public Iterator iterator() { iterElem = this; return this; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:95
   */
  public boolean hasNext() { return iterElem != null; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:96
   */
  public Object next() { Object o = iterElem; iterElem = null; return o; }
  /**
   * @ast method 
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:97
   */
  public void remove() { throw new UnsupportedOperationException(); }
  /**
   * @ast method 
   * @aspect DefiniteAssignment
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:179
   */
  public void definiteAssignment() {
    super.definiteAssignment();
    if(isBlank() && isFinal() && isClassVariable()) {
      boolean found = false;
      TypeDecl typeDecl = hostType();
      for(int i = 0; i < typeDecl.getNumBodyDecl(); i++) {
        if(typeDecl.getBodyDecl(i) instanceof StaticInitializer) {
          StaticInitializer s = (StaticInitializer)typeDecl.getBodyDecl(i);
          if(s.isDAafter(this))
            found = true;
        }
        
        else if(typeDecl.getBodyDecl(i) instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)typeDecl.getBodyDecl(i);
          if(f.isStatic() && f.isDAafter(this))
            found = true;
        }
        
      }
      if(!found)
        error("blank final class variable " + name() + " in " + hostType().typeName() + " is not definitely assigned in static initializer");

    }
    if(isBlank() && isFinal() && isInstanceVariable()) {
      TypeDecl typeDecl = hostType();
      boolean found = false;
      for(int i = 0; !found && i < typeDecl.getNumBodyDecl(); i++) {
        if(typeDecl.getBodyDecl(i) instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)typeDecl.getBodyDecl(i);
          if(!f.isStatic() && f.isDAafter(this))
            found = true;
        }
        else if(typeDecl.getBodyDecl(i) instanceof InstanceInitializer) {
          InstanceInitializer ii = (InstanceInitializer)typeDecl.getBodyDecl(i);
          if(ii.getBlock().isDAafter(this))
            found = true;
        }
      }
      for(Iterator iter = typeDecl.constructors().iterator(); !found && iter.hasNext(); ) {
        ConstructorDecl c = (ConstructorDecl)iter.next();
        if(!c.isDAafter(this)) {
          error("blank final instance variable " + name() + " in " + hostType().typeName() + " is not definitely assigned after " + c.signature());
          }
      }
    }
    if(isBlank() && hostType().isInterfaceDecl()) {
            error("variable  " + name() + " in " + hostType().typeName() + " which is an interface must have an initializer");
    }

  }
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1732
   */
  
  protected LinkedList fieldToVar=new LinkedList();
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1770
   */
  
  

		
/*rewrite FieldDeclaration
{
 when(!variableRef.isEmpty())
 to FieldDeclaration {
	 if(!variableRef.isEmpty())
	 {
	 FieldDeclaration ff=(FieldDeclaration)variableRef.get(0);
	 String typename=getID()+"%"+ff.getTypeAccess();
	 setID(typename);
	 }
	 return this;
 }
}*/
protected static ArrayList variableRef=new ArrayList();
  /**
   * @ast method 
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:111
   */
  public void checkModifiers() {
    super.checkModifiers();
    if(hostType().isInterfaceDecl()) {
      if(isProtected())
        error("an interface field may not be protected");
      if(isPrivate())
        error("an interface field may not be private");
      if(isTransient())
        error("an interface field may not be transient");
      if(isVolatile())
        error("an interface field may not be volatile");
    }
    if(hostType().isGranuleDecl()&&!isPrivate()&&!isPublic()&&!isProtected())
		error("local variable declaration in Granule is only private or public or protected"); 
  }
  /**
   * @ast method 
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:344
   */
  public void nameCheck() {
    super.nameCheck();
    // 8.3
//==================GOP==========================================
    if(hostType().isClassDecl()||hostType().isGranuleDecl()||hostType().isShadowClassDecl())
    {
    	Program root=getProgram();
    	for(Iterator iter=root.contextVariable(name()).iterator();iter.hasNext();)
    	{
    		Variable v=(Variable)iter.next();    		
    		if(v.name().equals(name()))
            error("field named " +name()+" has been already declared context variable !");
    	}
    }
//==================end==========================================
    for(Iterator iter = hostType().memberFields(name()).iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      if(v != this&& v.hostType() == hostType())
        error("field named " + name() + " is multiply declared in type " + hostType().typeName());
  //=========================GOP==================================      
      if(hostType().isShadowClassDecl())
     {
    	 ShadowClassDecl shadowclass=(ShadowClassDecl)hostType();
    	 if(shadowclass.hasSuperShadowClass()){
    	 while(!shadowclass.SuperShadowClass().isClassDecl())
    	 {
    		 ShadowClassDecl superclass=(ShadowClassDecl)(shadowclass.SuperShadowClass());
			 for(Iterator iter1=superclass.localFieldsMap().values().iterator();iter1.hasNext();)
	    		{
	    	      FieldDeclaration vv=(FieldDeclaration)iter1.next();
	    		  //if(vv!=this&&getTypeAccess().type()==vv.getTypeAccess().type()&&name().equals(vv.name()))
	    	      if(vv!=this&&!vv.getTypeAccess().type().instanceOf(getTypeAccess().type())&&name().equals(vv.name()))
	    		  error(" field named "+ name()+ " is not declared  in type" +hostType().typeName()+ "; it has areadly declared in shadow class " +superclass.name());    	    			
	    		}
		   shadowclass=superclass;
		 }
        if(shadowclass.SuperShadowClass().isClassDecl())
        {  
        	 ClassDecl classdecl=(ClassDecl)shadowclass.SuperShadowClass();
		  	  for(Iterator iter2=classdecl.memberFields(name()).iterator();iter2.hasNext();)
	           {   
	    		  FieldDeclaration ff=(FieldDeclaration)iter2.next();
	    		  //if(ff!=this&&getTypeAccess().type()==ff.getTypeAccess().type()&&name().equals(ff.name()))
	    		  if(ff!=this&&!ff.getTypeAccess().type().instanceOf(getTypeAccess().type())&&name().equals(ff.name()))
	    		  error(" field named "+ name()+ " is not declared  in type" +hostType().typeName()+ "; it has areadly declared in its seed class " +classdecl.name());
	    	   }
        }
     }
    }
  //==========================END==================================      
    }
  }
  /**
   * @ast method 
   * @aspect NodeConstructors
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NodeConstructors.jrag:86
   */
  public FieldDeclaration(Modifiers m, Access type, String name) {
    this(m, type, name, new Opt());
  }
  /**
   * @ast method 
   * @aspect NodeConstructors
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NodeConstructors.jrag:90
   */
  public FieldDeclaration(Modifiers m, Access type, String name, Expr init) {
    this(m, type, name, new Opt(init));
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:207
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name());
    if(hasInit()) {
      s.append(" = ");
      getInit().toString(s);
    }
    s.append(";");
  }
  /**
   * @ast method 
   * @aspect TypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeCheck.jrag:32
   */
  public void typeCheck() {
    if(hasInit()) {
      TypeDecl source = getInit().type();
      TypeDecl dest = type();
      if(!source.assignConversionTo(dest, getInit()))
        error("can not assign " + name() + " of type " + dest.typeName() +
              " a value of type " + source.typeName());
    }
  }
  /**
   * @ast method 
   * @aspect VariableDeclarationTransformation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:117
   */
  
  // when splitting a FieldDecl into multiple FieldDeclarations, provide every FieldDeclaration with a reference
  // to the original FieldDecl; if only a single FieldDeclaration results, no reference is stored
  private FieldDecl fieldDecl = null;
  /**
   * @ast method 
   * @aspect VariableDeclarationTransformation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:118
   */
  public FieldDecl getFieldDecl() {
	  return fieldDecl;
  }
  /**
   * @ast method 
   * @aspect VariableDeclarationTransformation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:121
   */
  public void setFieldDecl(FieldDecl fieldDecl) {
	  this.fieldDecl = fieldDecl;
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:679
   */
  public void emitLoadField(CodeGeneration gen, TypeDecl typeDecl) {
    if(hostType().isArrayDecl() && name().equals("length")) {
      gen.emit(Bytecode.ARRAYLENGTH);
      return;
    }
    String classname = typeDecl.constantPoolName();
    String      desc = type().typeDescriptor();
    String      name = name();
    int index = gen.constantPool().addFieldref(classname, name, desc);
    if(isStatic())
      gen.emit(Bytecode.GETSTATIC, type().variableSize()).add2(index);
    else
      gen.emit(Bytecode.GETFIELD, type().variableSize() - 1).add2(index);
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:754
   */
  public void emitStoreField(CodeGeneration gen, TypeDecl typeDecl) {
    String classname = typeDecl.constantPoolName();
    String      desc = type().typeDescriptor();
    String      name = name();
    int index = gen.constantPool().addFieldref(classname, name, desc);   
    if(isStatic())
      gen.emit(Bytecode.PUTSTATIC, -type().variableSize()).add2(index);
    else
      gen.emit(Bytecode.PUTFIELD, -type().variableSize() - 1).add2(index);
  }
  /**
   * @ast method 
   * @aspect CodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CodeGeneration.jrag:766
   */
  public void emitStoreField(CodeGeneration gen, TypeDecl typeDecl,Boolean previous) {
    String classname=null;
    /*if(previous)
    classname="previous%"+typeDecl.constantPoolName();
    else 
    classname="seed%"+typeDecl.constantPoolName();*/
    classname = typeDecl.constantPoolName();
    String      desc = type().typeDescriptor();
    String      name = name();
    int index = gen.constantPool().addFieldref(classname, name, desc);   
    if(isStatic())
      gen.emit(Bytecode.PUTSTATIC, -type().variableSize()).add2(index);
    else
      gen.emit(Bytecode.PUTFIELD, -type().variableSize() - 1).add2(index);
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:462
   */
  public boolean clear() {
    return false;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:440
   */
  public MethodDecl createAccessor(TypeDecl fieldQualifier) {	
    MethodDecl m = (MethodDecl)fieldQualifier.getAccessor(this, "field_read");
    if(m != null) return m;
    
    int accessorIndex = fieldQualifier.accessorCounter++;
    Modifiers modifiers = new Modifiers(new List());
    modifiers.addModifier(new Modifier("static"));
    modifiers.addModifier(new Modifier("synthetic"));
    modifiers.addModifier(new Modifier("public"));

    List parameters = new List();
    if(!isStatic())
      parameters.add(new ParameterDeclaration(fieldQualifier.createQualifiedAccess(), "that"));

    m = new MethodDecl(
      modifiers,
      type().createQualifiedAccess(),
      "get$" + name() + "$access$" + accessorIndex,
      parameters,
      new List(),
      new Opt(
        new Block(
          new List().add(
            new ReturnStmt(createAccess())
          )
        )
      )
    );
    m = fieldQualifier.addMemberMethod(m);
    fieldQualifier.addAccessor(this, "field_read", m);
    return m;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:473
   */
  public MethodDecl createAccessorWrite(TypeDecl fieldQualifier) {
    MethodDecl m = (MethodDecl)fieldQualifier.getAccessor(this, "field_write");
    if(m != null) return m;

    int accessorIndex = fieldQualifier.accessorCounter++;
    Modifiers modifiers = new Modifiers(new List());
    modifiers.addModifier(new Modifier("static"));
    modifiers.addModifier(new Modifier("synthetic"));
    modifiers.addModifier(new Modifier("public"));

    List parameters = new List();
    if(!isStatic())
      parameters.add(new ParameterDeclaration(fieldQualifier.createQualifiedAccess(), "that"));
    parameters.add(new ParameterDeclaration(type().createQualifiedAccess(), "value"));

    m = new MethodDecl(
      modifiers,
      type().createQualifiedAccess(),
      "set$" + name() + "$access$" + accessorIndex,
      parameters,
      new List(),
      new Opt(
        new Block(
          new List().add(
            new ExprStmt(
              new AssignSimpleExpr(
                createAccess(),
                new VarAccess("value")
              )
            )
          ).add(
            new ReturnStmt(
              new Opt(
                new VarAccess("value")
              )
            )
          )
        )
      )
    );
    m = fieldQualifier.addMemberMethod(m);
    fieldQualifier.addAccessor(this, "field_write", m);
    return m;
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:518
   */
  private Access createAccess() {
    Access fieldAccess = new BoundFieldAccess(this);
    return isStatic() ? fieldAccess : new VarAccess("that").qualifiesAccess(fieldAccess);
  }
  /**
   * @ast method 
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1058
   */
  public BodyDecl p(Parameterization parTypeDecl) {
    FieldDeclaration f = new FieldDeclarationSubstituted(
      (Modifiers)getModifiers().fullCopy(),
      getTypeAccess().type().substituteReturnType(parTypeDecl),
      getID(),
      new Opt(),
      this
    );
    return f;
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public FieldDeclaration() {
    super();

    setChild(new Opt(), 2);

  }
  /**
   * @ast method 
   * @declaredat java.ast:8
   */
  public FieldDeclaration(Modifiers p0, Access p1, String p2, Opt<Expr> p3) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
  }
  /**
   * @ast method 
   * @declaredat java.ast:14
   */
  public FieldDeclaration(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:23
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:29
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Getter for TypeAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public Access getTypeAccess() {
    return (Access)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:18
   */
  public Access getTypeAccessNoTransform() {
    return (Access)getChildNoTransform(1);
  }
  /**
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**   * @apilvl internal   * @ast method 
   * @declaredat java.ast:8
   */
  
  /**   * @apilvl internal   */  protected String tokenString_ID;
  /**
   * @ast method 
   * @declaredat java.ast:9
   */
  
  public int IDstart;
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  
  public int IDend;
  /**
   * @ast method 
   * @declaredat java.ast:11
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
   * @declaredat java.ast:22
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for InitOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setInitOpt(Opt<Expr> opt) {
    setChild(opt, 2);
  }
  /**
   * Does this node have a Init child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasInit() {
    return getInitOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr getInit() {
    return (Expr)getInitOpt().getChild(0);
  }
  /**
   * Setter for optional child Init
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setInit(Expr node) {
    getInitOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOpt() {
    return (Opt<Expr>)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Expr> getInitOptNoTransform() {
    return (Opt<Expr>)getChildNoTransform(2);
  }
  /**
   * @ast method 
   * @aspect Attributes
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Attributes.jrag:224
   */
  private Collection refined_Attributes_FieldDeclaration_attributes()
{
    ArrayList l = new ArrayList();
    if(isStatic() && isFinal() && isConstant() && (type().isPrimitive() || type().isString()))
      l.add(new ConstantValueAttribute(hostType().constantPool(), this));
    return l;
  }
  /**
   * @ast method 
   * @aspect AnnotationsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\AnnotationsCodegen.jrag:17
   */
  private Collection refined_AnnotationsCodegen_FieldDeclaration_attributes()
{
    Collection c = refined_Attributes_FieldDeclaration_attributes();
    getModifiers().addRuntimeVisibleAnnotationsAttribute(c);
    getModifiers().addRuntimeInvisibleAnnotationsAttribute(c);
    return c;
  }
  protected java.util.Map accessibleFrom_TypeDecl_values;
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AccessControl.jrag:109
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
  private boolean accessibleFrom_compute(TypeDecl type) {
    if(type.isGranuleDecl() && ((GranuleDecl)(type)).getRootClassAccess().type()==(this.hostType()))
    return true;
    if(isPublic())
      return true;
    else if(isProtected()) {
      if(hostPackage().equals(type.hostPackage()))
        return true;
      if(type.withinBodyThatSubclasses(hostType()) != null)
        return true;
      return false;
    }
    else if(isPrivate())
      return hostType().topLevelType() == type.topLevelType();
    else
      return hostPackage().equals(type.hostPackage());
  }
  /**
   * @apilvl internal
   */
  protected boolean exceptions_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection exceptions_value;
  /**
   * @attribute syn
   * @aspect AnonymousClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AnonymousClasses.jrag:111
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection exceptions() {
    if(exceptions_computed) {
      return exceptions_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    exceptions_value = exceptions_compute();
if(isFinal && num == state().boundariesCrossed) exceptions_computed = true;
    return exceptions_value;
  }
  /**
   * @apilvl internal
   */
  private Collection exceptions_compute() {
    HashSet set = new HashSet();
    if(isInstanceVariable() && hasInit()) {
      collectExceptions(set, this);
      for(Iterator iter = set.iterator(); iter.hasNext(); ) {
        TypeDecl typeDecl = (TypeDecl)iter.next();
        if(!getInit().reachedException(typeDecl))
          iter.remove();
      }
    }
    return set;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:479
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstant() {
      ASTNode$State state = state();
    boolean isConstant_value = isConstant_compute();
    return isConstant_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstant_compute() {  return isFinal() && hasInit() && getInit().isConstant() && (type() instanceof PrimitiveType || type().isString());  }
  /**
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:79
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int size() {
      ASTNode$State state = state();
    int size_value = size_compute();
    return size_value;
  }
  /**
   * @apilvl internal
   */
  private int size_compute() {  return 1;  }
  /**
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:80
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isEmpty() {
      ASTNode$State state = state();
    boolean isEmpty_value = isEmpty_compute();
    return isEmpty_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isEmpty_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect DataStructures
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DataStructures.jrag:84
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean contains(Object o) {
      ASTNode$State state = state();
    boolean contains_Object_value = contains_compute(o);
    return contains_Object_value;
  }
  /**
   * @apilvl internal
   */
  private boolean contains_compute(Object o) {  return this == o;  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:316
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafter(Variable v) {
    Object _parameters = v;
    if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
    if(isDAafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDAafter_Variable_value = isDAafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
    return isDAafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafter_compute(Variable v) {
    if(v == this)
      return hasInit();
    return hasInit() ? getInit().isDAafter(v) : isDAbefore(v);
  }
  /**
   * @attribute syn
   * @aspect DU
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:772
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDUafter(Variable v) {
    Object _parameters = v;
    if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
    if(isDUafter_Variable_values.containsKey(_parameters)) {
      return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean isDUafter_Variable_value = isDUafter_compute(v);
if(isFinal && num == state().boundariesCrossed) isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
    return isDUafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDUafter_compute(Variable v) {
    if(v == this)
      return !hasInit();
    return hasInit() ? getInit().isDUafter(v) : isDUbefore(v);
  }
  /**
   * @apilvl internal
   */
  protected int fieldToVarMap_visited = -1;
  /**
   * @apilvl internal
   */
  protected boolean fieldToVarMap_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean fieldToVarMap_initialized = false;
  /**
   * @apilvl internal
   */
  protected LinkedList fieldToVarMap_value;
  /**
   * @attribute syn
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1733
   */
  @SuppressWarnings({"unchecked", "cast"})
  public LinkedList fieldToVarMap() {
    if(fieldToVarMap_computed) {
      return fieldToVarMap_value;
    }
    ASTNode$State state = state();
    if (!fieldToVarMap_initialized) {
      fieldToVarMap_initialized = true;
      fieldToVarMap_value = new LinkedList();
    }
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
    int num = state.boundariesCrossed;
    boolean isFinal = this.is$Final();
      do {
        fieldToVarMap_visited = state.CIRCLE_INDEX;
        state.CHANGE = false;
        LinkedList new_fieldToVarMap_value = fieldToVarMap_compute();
        if ((new_fieldToVarMap_value==null && fieldToVarMap_value!=null) || (new_fieldToVarMap_value!=null && !new_fieldToVarMap_value.equals(fieldToVarMap_value)))
          state.CHANGE = true;
        fieldToVarMap_value = new_fieldToVarMap_value; 
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
      fieldToVarMap_computed = true;
      }
      else {
      state.RESET_CYCLE = true;
      fieldToVarMap_compute();
      state.RESET_CYCLE = false;
        fieldToVarMap_computed = false;
        fieldToVarMap_initialized = false;
      }
      state.IN_CIRCLE = false; 
      return fieldToVarMap_value;
    }
    if(fieldToVarMap_visited != state.CIRCLE_INDEX) {
      fieldToVarMap_visited = state.CIRCLE_INDEX;
      if (state.RESET_CYCLE) {
        fieldToVarMap_computed = false;
        fieldToVarMap_initialized = false;
        fieldToVarMap_visited = -1;
        return fieldToVarMap_value;
      }
      LinkedList new_fieldToVarMap_value = fieldToVarMap_compute();
      if ((new_fieldToVarMap_value==null && fieldToVarMap_value!=null) || (new_fieldToVarMap_value!=null && !new_fieldToVarMap_value.equals(fieldToVarMap_value)))
        state.CHANGE = true;
      fieldToVarMap_value = new_fieldToVarMap_value; 
      return fieldToVarMap_value;
    }
    return fieldToVarMap_value;
  }
  /**
   * @apilvl internal
   */
  private LinkedList fieldToVarMap_compute() {
	  Program root=getProgram();
	  if(!root.isMapSet){
		root.registerVariables();
		root.isMapSet=true;
      }
		return fieldToVar;
  }
  /**
   * @attribute syn
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1771
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasSameField() {
      ASTNode$State state = state();
    boolean hasSameField_value = hasSameField_compute();
    return hasSameField_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasSameField_compute() {
  if(hostType().isShadowClassDecl())
  {
    ShadowClassDecl shadowclass=(ShadowClassDecl)hostType();
    while(!shadowclass.SuperShadowClass().isClassDecl())
    {
      ShadowClassDecl superclass=(ShadowClassDecl)(shadowclass.SuperShadowClass());
      for(Iterator iter1=superclass.localFieldsMap().values().iterator();iter1.hasNext();)
      {
    	  FieldDeclaration vv=(FieldDeclaration)iter1.next();
    	  if(name().equals(vv.name())){
    	  if(!variableRef.contains(vv))
    	  variableRef.add(vv);
    	  return true;
    	  }
      }
      shadowclass=superclass;
    }
    if(shadowclass.SuperShadowClass().isClassDecl())
    {
     ClassDecl classdecl=(ClassDecl)shadowclass.SuperShadowClass();
     for(Iterator iter2=classdecl.memberFields(name()).iterator();iter2.hasNext();)
     {
        FieldDeclaration ff=(FieldDeclaration)iter2.next();
        if(name().equals(ff.name()))
        {
        if(!variableRef.contains(ff))
        variableRef.add(ff);
        return true;
        }
     }
    }
    return false;
  }
  return false;
}
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:225
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSynthetic() {
      ASTNode$State state = state();
    boolean isSynthetic_value = isSynthetic_compute();
    return isSynthetic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSynthetic_compute() {  return getModifiers().isSynthetic();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:267
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPublic() {
      ASTNode$State state = state();
    boolean isPublic_value = isPublic_compute();
    return isPublic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPublic_compute() {  return getModifiers().isPublic() || hostType().isInterfaceDecl();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:268
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isPrivate() {
      ASTNode$State state = state();
    boolean isPrivate_value = isPrivate_compute();
    return isPrivate_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isPrivate_compute() {  return getModifiers().isPrivate();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:269
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isProtected() {
      ASTNode$State state = state();
    boolean isProtected_value = isProtected_compute();
    return isProtected_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isProtected_compute() {  return getModifiers().isProtected();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:270
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isStatic() {
      ASTNode$State state = state();
    boolean isStatic_value = isStatic_compute();
    return isStatic_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isStatic_compute() {  return getModifiers().isStatic() || hostType().isInterfaceDecl();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:272
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFinal() {
      ASTNode$State state = state();
    boolean isFinal_value = isFinal_compute();
    return isFinal_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFinal_compute() {  return getModifiers().isFinal() || hostType().isInterfaceDecl();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:273
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isTransient() {
      ASTNode$State state = state();
    boolean isTransient_value = isTransient_compute();
    return isTransient_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isTransient_compute() {  return getModifiers().isTransient();  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:274
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVolatile() {
      ASTNode$State state = state();
    boolean isVolatile_value = isVolatile_compute();
    return isVolatile_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVolatile_compute() {  return getModifiers().isVolatile();  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:941
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String dumpString() {
      ASTNode$State state = state();
    String dumpString_value = dumpString_compute();
    return dumpString_value;
  }
  /**
   * @apilvl internal
   */
  private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:253
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl type() {
      ASTNode$State state = state();
    TypeDecl type_value = type_compute();
    return type_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl type_compute() {  return getTypeAccess().type();  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:275
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isVoid() {
      ASTNode$State state = state();
    boolean isVoid_value = isVoid_compute();
    return isVoid_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isVoid_compute() {  return type().isVoid();  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:55
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isParameter() {
      ASTNode$State state = state();
    boolean isParameter_value = isParameter_compute();
    return isParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isParameter_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:57
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isClassVariable() {
      ASTNode$State state = state();
    boolean isClassVariable_value = isClassVariable_compute();
    return isClassVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isClassVariable_compute() {  return isStatic() || hostType().isInterfaceDecl();  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:59
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isInstanceVariable() {
      ASTNode$State state = state();
    boolean isInstanceVariable_value = isInstanceVariable_compute();
    return isInstanceVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isInstanceVariable_compute() {  return (hostType().isClassDecl() || hostType().isAnonymous()||hostType().isShadowClassDecl())&& !isStatic();  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:60
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isMethodParameter() {
      ASTNode$State state = state();
    boolean isMethodParameter_value = isMethodParameter_compute();
    return isMethodParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isMethodParameter_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:61
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isConstructorParameter() {
      ASTNode$State state = state();
    boolean isConstructorParameter_value = isConstructorParameter_compute();
    return isConstructorParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isConstructorParameter_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:62
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isExceptionHandlerParameter() {
      ASTNode$State state = state();
    boolean isExceptionHandlerParameter_value = isExceptionHandlerParameter_compute();
    return isExceptionHandlerParameter_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isExceptionHandlerParameter_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isLocalVariable() {
      ASTNode$State state = state();
    boolean isLocalVariable_value = isLocalVariable_compute();
    return isLocalVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isLocalVariable_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:65
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isBlank() {
      ASTNode$State state = state();
    boolean isBlank_value = isBlank_compute();
    return isBlank_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isBlank_compute() {  return !hasInit();  }
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:67
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
  private String name_compute() {  return getID();  }
  /**
   * @apilvl internal
   */
  protected boolean constant_computed = false;
  /**
   * @apilvl internal
   */
  protected Constant constant_value;
  /**
   * @attribute syn
   * @aspect Variables
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\VariableDeclaration.jrag:68
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant constant() {
    if(constant_computed) {
      return constant_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    constant_value = constant_compute();
if(isFinal && num == state().boundariesCrossed) constant_computed = true;
    return constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant constant_compute() {  return type().cast(getInit().constant());  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:353
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection attributes() {
    if(attributes_computed) {
      return attributes_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    attributes_value = attributes_compute();
if(isFinal && num == state().boundariesCrossed) attributes_computed = true;
    return attributes_value;
  }
  /**
   * @apilvl internal
   */
  private Collection attributes_compute() {
    Collection c = refined_AnnotationsCodegen_FieldDeclaration_attributes();
    if(needsSignatureAttribute())
      c.add(new SignatureAttribute(hostType().constantPool(), type().fieldTypeSignature()));
    return c;
  }
  /**
   * @apilvl internal
   */
  protected boolean flags_computed = false;
  /**
   * @apilvl internal
   */
  protected int flags_value;
  /**
   * @attribute syn
   * @aspect Flags
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Flags.jrag:99
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int flags() {
    if(flags_computed) {
      return flags_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    flags_value = flags_compute();
if(isFinal && num == state().boundariesCrossed) flags_computed = true;
    return flags_value;
  }
  /**
   * @apilvl internal
   */
  private int flags_compute() {
    int res = 0;
    if(isPublic()) res |= Modifiers.ACC_PUBLIC;
    if(isPrivate()) res |= Modifiers.ACC_PRIVATE;
    if(isProtected()) res |= Modifiers.ACC_PROTECTED;
    if(isStatic()) res |= Modifiers.ACC_STATIC;
    if(isFinal()) res |= Modifiers.ACC_FINAL;
    if(isVolatile()) res |= Modifiers.ACC_VOLATILE;
    if(isTransient()) res |= Modifiers.ACC_TRANSIENT;
  //GOP  
    //if(isExternal())  res |= Modifiers.ACC_EXTERNAL;
    return res;
  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:378
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isBytecodeField() {
      ASTNode$State state = state();
    boolean isBytecodeField_value = isBytecodeField_compute();
    return isBytecodeField_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isBytecodeField_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:422
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean flush() {
      ASTNode$State state = state();
    boolean flush_value = flush_compute();
    return flush_value;
  }
  /**
   * @apilvl internal
   */
  private boolean flush_compute() {  return false;  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:287
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasAnnotationSuppressWarnings(String s) {
      ASTNode$State state = state();
    boolean hasAnnotationSuppressWarnings_String_value = hasAnnotationSuppressWarnings_compute(s);
    return hasAnnotationSuppressWarnings_String_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasAnnotationSuppressWarnings_compute(String s) {  return getModifiers().hasAnnotationSuppressWarnings(s);  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:325
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDeprecated() {
      ASTNode$State state = state();
    boolean isDeprecated_value = isDeprecated_compute();
    return isDeprecated_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDeprecated_compute() {  return getModifiers().hasDeprecatedAnnotation();  }
  /**
   * @apilvl internal
   */
  protected boolean usesTypeVariable_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean usesTypeVariable_value;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:910
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean usesTypeVariable() {
    if(usesTypeVariable_computed) {
      return usesTypeVariable_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    usesTypeVariable_value = usesTypeVariable_compute();
if(isFinal && num == state().boundariesCrossed) usesTypeVariable_computed = true;
    return usesTypeVariable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean usesTypeVariable_compute() {  return getTypeAccess().usesTypeVariable();  }
  /**
   * @apilvl internal
   */
  protected boolean sourceVariableDecl_computed = false;
  /**
   * @apilvl internal
   */
  protected Variable sourceVariableDecl_value;
  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1285
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Variable sourceVariableDecl() {
    if(sourceVariableDecl_computed) {
      return sourceVariableDecl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    sourceVariableDecl_value = sourceVariableDecl_compute();
if(isFinal && num == state().boundariesCrossed) sourceVariableDecl_computed = true;
    return sourceVariableDecl_value;
  }
  /**
   * @apilvl internal
   */
  private Variable sourceVariableDecl_compute() {  return this;  }
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsParTypeDecl.jrag:67
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean visibleTypeParameters() {
      ASTNode$State state = state();
    boolean visibleTypeParameters_value = visibleTypeParameters_compute();
    return visibleTypeParameters_value;
  }
  /**
   * @apilvl internal
   */
  private boolean visibleTypeParameters_compute() {  return !isStatic();  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:53
   */
  @SuppressWarnings({"unchecked", "cast"})
  public FieldDeclaration erasedField() {
      ASTNode$State state = state();
    FieldDeclaration erasedField_value = erasedField_compute();
    return erasedField_value;
  }
  /**
   * @apilvl internal
   */
  private FieldDeclaration erasedField_compute() {  return this;  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:410
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean needsSignatureAttribute() {
      ASTNode$State state = state();
    boolean needsSignatureAttribute_value = needsSignatureAttribute_compute();
    return needsSignatureAttribute_value;
  }
  /**
   * @apilvl internal
   */
  private boolean needsSignatureAttribute_compute() {  return type().needsSignatureAttribute();  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:34
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean handlesException(TypeDecl exceptionType) {
      ASTNode$State state = state();
    boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
    return handlesException_TypeDecl_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:39
   * @apilvl internal
   */
  public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
    if(caller == getInitOptNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_isSource(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:322
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getInitOptNoTransform()){
    return isDAbefore(v);
  }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:152
   * @apilvl internal
   */
  public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
    if(caller == getInitOptNoTransform()){
    if(hostType().isAnonymous())
      return true;
    if(!exceptionType.isUncheckedException())
      return true;
    for(Iterator iter = hostType().constructors().iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(!decl.throwsException(exceptionType))
        return false;
    }
    return true;
  }
    return getParent().Define_boolean_handlesException(this, caller, exceptionType);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:314
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePublic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:315
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeProtected(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:316
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBePrivate(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:317
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeStatic(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:318
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeFinal(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:319
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeTransient(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeTransient(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:320
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeVolatile(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_mayBeVolatile(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:78
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getTypeAccessNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:262
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
    if(caller == getInitOptNoTransform()) {
      return type();
    }
    return getParent().Define_TypeDecl_declType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:179
   * @apilvl internal
   */
  public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
    if(caller == getInitOptNoTransform()) {
      return isStatic() || hostType().isInterfaceDecl();
    }
    return getParent().Define_boolean_inStaticContext(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:143
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_expectedType(ASTNode caller, ASTNode child) {
    if(caller == getInitOptNoTransform()) {
      return type().componentType();
    }
    return getParent().Define_TypeDecl_expectedType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:80
   * @apilvl internal
   */
  public boolean Define_boolean_mayUseAnnotationTarget(ASTNode caller, ASTNode child, String name) {
    if(caller == getModifiersNoTransform()) {
      return name.equals("FIELD");
    }
    return getParent().Define_boolean_mayUseAnnotationTarget(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericMethodsInference.jrag:35
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
    if(caller == getInitOptNoTransform()) {
      return type();
    }
    return getParent().Define_TypeDecl_assignConvertedType(this, caller);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}
