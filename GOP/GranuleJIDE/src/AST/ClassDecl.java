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
 * @declaredat java.ast:64
 */
public class ClassDecl extends ReferenceType implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    hasGranuleDecl_computed = false;
    hasShadowClass_computed = false;
    isExistFit_computed = false;
    isAuxiliaryClassDecl_computed = false;
    hasShadowAsDes_computed = false;
    noConstructor_computed = false;
    classInstances_visited = -1;
    classInstances_computed = false;
    classInstances_initialized = false;
    classInstances_value = null;
    UnionMethodsName_computed = false;
    UnionMethodsName_value = null;
    auxiliarymethodsSignatureMap_computed = false;
    auxiliarymethodsSignatureMap_value = null;
    interfacesMethodsSignatureMap_computed = false;
    interfacesMethodsSignatureMap_value = null;
    methodsSignatureMap_computed = false;
    methodsSignatureMap_value = null;
    ancestorMethods_String_values = null;
    memberTypes_String_values = null;
    shadowClassSet_computed = false;
    shadowClassSet_value = null;
    memberFieldsMap_computed = false;
    memberFieldsMap_value = null;
    UnionMemberFields_computed = false;
    UnionMemberFields_value = null;
    UnionMemberFields_String_values = null;
    memberFields_String_values = null;
    getShadowSet_computed = false;
    getShadowSet_value = null;
    getShadowclasses_computed = false;
    getShadowclasses_value = null;
    unimplementedMethods_computed = false;
    unimplementedMethods_value = null;
    hasAbstract_computed = false;
    castingConversionTo_TypeDecl_values = null;
    isString_computed = false;
    isObject_computed = false;
    instanceOf_TypeDecl_values = null;
    isCircular_visited = -1;
    isCircular_computed = false;
    isCircular_initialized = false;
    typeDescriptor_computed = false;
    typeDescriptor_value = null;
    implementedInterfaces_computed = false;
    implementedInterfaces_value = null;
    subtype_TypeDecl_values = null;
    needsSignatureAttribute_computed = false;
    classSignature_computed = false;
    classSignature_value = null;
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
  public ClassDecl clone() throws CloneNotSupportedException {
    ClassDecl node = (ClassDecl)super.clone();
    node.hasGranuleDecl_computed = false;
    node.hasShadowClass_computed = false;
    node.isExistFit_computed = false;
    node.isAuxiliaryClassDecl_computed = false;
    node.hasShadowAsDes_computed = false;
    node.noConstructor_computed = false;
    node.classInstances_visited = -1;
    node.classInstances_computed = false;
    node.classInstances_initialized = false;
    node.classInstances_value = null;
    node.UnionMethodsName_computed = false;
    node.UnionMethodsName_value = null;
    node.auxiliarymethodsSignatureMap_computed = false;
    node.auxiliarymethodsSignatureMap_value = null;
    node.interfacesMethodsSignatureMap_computed = false;
    node.interfacesMethodsSignatureMap_value = null;
    node.methodsSignatureMap_computed = false;
    node.methodsSignatureMap_value = null;
    node.ancestorMethods_String_values = null;
    node.memberTypes_String_values = null;
    node.shadowClassSet_computed = false;
    node.shadowClassSet_value = null;
    node.memberFieldsMap_computed = false;
    node.memberFieldsMap_value = null;
    node.UnionMemberFields_computed = false;
    node.UnionMemberFields_value = null;
    node.UnionMemberFields_String_values = null;
    node.memberFields_String_values = null;
    node.getShadowSet_computed = false;
    node.getShadowSet_value = null;
    node.getShadowclasses_computed = false;
    node.getShadowclasses_value = null;
    node.unimplementedMethods_computed = false;
    node.unimplementedMethods_value = null;
    node.hasAbstract_computed = false;
    node.castingConversionTo_TypeDecl_values = null;
    node.isString_computed = false;
    node.isObject_computed = false;
    node.instanceOf_TypeDecl_values = null;
    node.isCircular_visited = -1;
    node.isCircular_computed = false;
    node.isCircular_initialized = false;
    node.typeDescriptor_computed = false;
    node.typeDescriptor_value = null;
    node.implementedInterfaces_computed = false;
    node.implementedInterfaces_value = null;
    node.subtype_TypeDecl_values = null;
    node.needsSignatureAttribute_computed = false;
    node.classSignature_computed = false;
    node.classSignature_value = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ClassDecl copy() {
      try {
        ClassDecl node = (ClassDecl)clone();
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
  public ClassDecl fullCopy() {
    ClassDecl res = (ClassDecl)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AccessControl.jrag:150
   */
  public void accessControl() {
    super.accessControl();
    
    // 8.1.1.2 final Classes
    TypeDecl typeDecl = hasSuperclass() ? superclass() : null;
    if(typeDecl != null && !typeDecl.accessibleFromExtend(this))
    //if(typeDecl != null && !isCircular() && !typeDecl.accessibleFrom(this))
      error("class " + fullName() + " may not extend non accessible type " + typeDecl.fullName());

    if(hasSuperclass() && !superclass().accessibleFrom(this))
      error("a superclass must be accessible which " + superclass().name() + " is not");

    // 8.1.4
    for(int i = 0; i < getNumImplements(); i++) {
      TypeDecl decl = getImplements(i).type();
      if(!decl.isCircular() && !decl.accessibleFrom(this))
        error("class " + fullName() + " can not implement non accessible type " + decl.fullName());
    }
  }
  /**
   * @ast method 
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:92
   */
  public void exceptionHandling() {
    constructors();
    super.exceptionHandling();
  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:70
   */
  
  protected LinkedList shadowclasses=new LinkedList();
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:71
   */
  public LinkedList shadowclasses(){
        Program root=getProgram();
  		if(!root.isRegisterShadowClass) {  		
  		  root.registerShadowClass(); 
  		root.isRegisterShadowClass=true;
  	    }
  		return shadowclasses;
    }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:110
   */
  
  protected boolean hasShadowclass=false;
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:114
   */
  boolean hasGranuleClass()
   {
	  if(!granuleclasses.isEmpty())
		  return true;
	   return false;
   }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:120
   */
  
   protected LinkedList granuleclasses=new LinkedList();
  /**
   * @ast method 
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1439
   */
  public HashMap searchPrivateField()
{   
    HashMap map=new HashMap();   
	for(Iterator iter=fieldsIterator();iter.hasNext();)
	{
		FieldDeclaration field=(FieldDeclaration)iter.next();
		if(field.getModifiers().isPrivate())
		map.put(field.name(),field);
	}
    return map;
}
  /**
   * @ast method 
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1450
   */
  public boolean isEraseFieldModifiers(){
	if(hasGranuleDecl()&&searchPrivateField().size()>0)
	 return true;
	else 
     return false;	
}
  /**
   * @ast method 
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1901
   */
  public boolean hasGranuleSet()
 {
    CompilationUnit root=getCompilationUnit();
    if(root.GranuleCounts()>=1)
    return true;
    return false;
 }
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:128
   */
  public Collection subclasses() {
		Program root = getProgram();
		if (!root.subclassFilled) {
		   root.SubclassFilledes();
		   root.subclassFilled = true;
		}
		return Subclasses;
	}
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:137
   */
  
	
	private LinkedList Subclasses = new LinkedList();
  /**
   * @ast method 
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:147
   */
  void SubclassFilledes() {
		if (hasSuperClassAccess()) {
			TypeDecl superClass = getSuperClassAccess().type();
			if (superClass instanceof ClassDecl) {
				((ClassDecl) superClass).Subclasses.add(this);
			}
		}
		super.SubclassFilledes();
	}
  /**
   * @ast method 
   * @aspect ImplicitConstructor
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:472
   */
    
protected LinkedList classInstances=new LinkedList();
  /**
   * @ast method 
   * @aspect MemberMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:503
   */
  public Iterator interfacesMethodsIterator() {
    return new Iterator() {
      private Iterator outer = interfacesMethodsSignatureMap().values().iterator();
      private Iterator inner = null;
      public boolean hasNext() {
        if((inner == null || !inner.hasNext()) && outer.hasNext())
          inner = ((SimpleSet)outer.next()).iterator();
        return inner == null ? false : inner.hasNext();
      }
      public Object next() {
        return inner.next();
      }
      public void remove() { throw new UnsupportedOperationException(); }
    };
  }
  /**
   * @ast method 
   * @aspect VariableScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:49
   */
  
//===================GOP=========================================
  private LinkedList shadowclassset=new LinkedList();
  /**
   * @ast method 
   * @aspect VariableScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:84
   */
  public boolean existShadowClass()
  {
	  if(!shadowClassSet().isEmpty())
	  return true;
	  return false;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:781
   */
  void getDirectSubclass(){
	 for(Iterator iter = interfacesIterator(); iter.hasNext();){
         ((InterfaceDecl)iter.next()).directClass.add(this); 
      }
	 super.getDirectSubclass();
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:845
   */
  public LinkedList getSubclasses(){
	  LinkedList s=new LinkedList();
	  Program root=getProgram();
	  for(Iterator iter = root.compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit)iter.next();
      if(cu.fromSource())
		s.addAll(cu.getSubclasses(this));
       }
		return s;
	}
  /**
   * @ast method 
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:94
   */
  public void checkModifiers() {
    super.checkModifiers();
    // 8.1.1.2 final Classes
    TypeDecl typeDecl = hasSuperclass() ? superclass() : null;
    if(typeDecl != null && typeDecl.isFinal()) {
      error("class " + fullName() + " may not extend final class " + typeDecl.fullName());
    }

  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:69
   */
  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    s.append("class " + name());
    if(hasSuperClassAccess()) {
      s.append(" extends ");
      getSuperClassAccess().toString(s);
    }
    if(getNumImplements() > 0) {
      s.append(" implements ");
      getImplements(0).toString(s);
      for(int i = 1; i < getNumImplements(); i++) {
        s.append(", ");
        getImplements(i).toString(s);
      }
    }
  ppBodyDecls(s);
  }
  /**
   * @ast method 
   * @aspect SuperClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:739
   */
  public boolean hasSuperclass() {
    return !isObject();
  }
  /**
   * @ast method 
   * @aspect SuperClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:743
   */
  public ClassDecl superclass() {
    if(isObject())
      return null;
    if(hasSuperClassAccess() && !isCircular() && getSuperClassAccess().type().isClassDecl())
      return (ClassDecl)getSuperClassAccess().type();
    return (ClassDecl)typeObject();
  }
  /**
   * @ast method 
   * @aspect SuperClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:758
   */
  public Iterator interfacesIterator() {
    return new Iterator() {
      public boolean hasNext() {
        computeNextCurrent();
        return current != null;
      }
      public Object next() {
        return current;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
      private int index = 0;
      private TypeDecl current = null;
      private void computeNextCurrent() {
        current = null;
        if(isObject() || isCircular())
          return;
        while(index < getNumImplements()) {
          TypeDecl typeDecl = getImplements(index++).type();
          if(!typeDecl.isCircular() && typeDecl.isInterfaceDecl()) {
            current = typeDecl;
            return;
          }
        }
      }
    };
  }
  /**
   * @ast method 
   * @aspect TypeHierarchyCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:281
   */
  public void nameCheck() {
   // System.out.println("class name is checked :"+name());
    super.nameCheck();
    //System.out.println("class name is checked first  :"+getSuperClassAccess().type().isClassDecl());
    if(hasSuperClassAccess() && !getSuperClassAccess().type().isClassDecl())
      error("class may only inherit a class and not " + getSuperClassAccess().type().typeName());
    if(isObject() && hasSuperClassAccess())
      error("class Object may not have superclass");
    if(isObject() && getNumImplements() != 0)
      error("class Object may not implement interfaces");
    
    // 8.1.3
    if(isCircular())
      error("circular inheritance dependency in " + typeName()); 
      
    // 8.1.4
    HashSet set = new HashSet();
    for(int i = 0; i < getNumImplements(); i++) {
      TypeDecl decl = getImplements(i).type();
      if(!decl.isInterfaceDecl() && !decl.isUnknown())
        error("type " + fullName() + " tries to implement non interface type " + decl.fullName());
      if(set.contains(decl))
        error("type " + decl.fullName() + " mentionened multiple times in implements clause");
      set.add(decl);
    }

    for(Iterator iter = interfacesMethodsIterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(localMethodsSignature(m.signature()).isEmpty()) {
        SimpleSet s = superclass().methodsSignature(m.signature());
        for(Iterator i2 = s.iterator(); i2.hasNext(); ) {
          MethodDecl n = (MethodDecl)i2.next();
          if(n.accessibleFrom(this)) {
            interfaceMethodCompatibleWithInherited(m, n);
          }
        }
        if(s.isEmpty()) {
          for(Iterator i2 = interfacesMethodsSignature(m.signature()).iterator(); i2.hasNext(); ) {
            MethodDecl n = (MethodDecl)i2.next();
            if(!n.mayOverrideReturn(m) && !m.mayOverrideReturn(n))
              error("Xthe return type of method " + m.signature() + " in " + m.hostType().typeName() + 
                  " does not match the return type of method " + n.signature() + " in " + 
                  n.hostType().typeName() + " and may thus not be overriden");
          }
        }
      }
    }
  }
  /**
   * @ast method 
   * @aspect TypeHierarchyCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:330
   */
  private void interfaceMethodCompatibleWithInherited(MethodDecl m, MethodDecl n) {
    if(n.isStatic())
      error("Xa static method may not hide an instance method");
    if(!n.isAbstract() && !n.isPublic())
      error("Xoverriding access modifier error for " + m.signature() + " in " + m.hostType().typeName() + " and " + n.hostType().typeName());
    if(!n.mayOverrideReturn(m) && !m.mayOverrideReturn(m))
      error("Xthe return type of method " + m.signature() + " in " + m.hostType().typeName() + 
            " does not match the return type of method " + n.signature() + " in " + 
            n.hostType().typeName() + " and may thus not be overriden");
    if(!n.isAbstract()) {
      // n implements and overrides method m in the interface
      // may not throw more checked exceptions
      for(int i = 0; i < n.getNumException(); i++) {
        Access e = n.getException(i);
        boolean found = false;
        for(int j = 0; !found && j < m.getNumException(); j++) {
          if(e.type().instanceOf(m.getException(j).type()))
            found = true;
        }
        if(!found && e.type().isUncheckedException())
          error("X" + n.signature() + " in " + n.hostType().typeName() + " may not throw more checked exceptions than overridden method " +
           m.signature() + " in " + m.hostType().typeName());
      }
    }
  }
  /**
   * @ast method 
   * @aspect GenerateClassfile
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GenerateClassfile.jrag:69
   */
  public void generateClassfile() {
    super.generateClassfile();
    String fileName = destinationPath() + File.separator + constantPoolName() + ".class";
    if(options().verbose()) System.out.println("Writing class file to " + fileName);
    try {
      ConstantPool cp = constantPool();
      // force building of constant pool
      cp.addClass(constantPoolName());
      if(hasSuperclass()) {
        cp.addClass(superclass().constantPoolName());
      }
      int numInterfaces = 0;
      for(Iterator iter = interfacesIterator(); iter.hasNext(); numInterfaces++)
        cp.addClass(((TypeDecl)iter.next()).constantPoolName());
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
      out.writeChar(hasSuperclass() ? cp.addClass(superclass().constantPoolName()) : 0);
      out.writeChar(numInterfaces);
      for(Iterator iter = interfacesIterator(); iter.hasNext(); )
        out.writeChar(cp.addClass(((TypeDecl)iter.next()).constantPoolName()));
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
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:504
   */
  public LinkedList shadowSet()
   {
       LinkedList<String> list=new LinkedList<String>();
       if(hasShadowClass())
 		 {
			for(Iterator iter1=shadowclasses().iterator();iter1.hasNext();){
 			ShadowClassDecl shadowdecl=(ShadowClassDecl)iter1.next(); 		
 			if(shadowdecl.SuperShadowClass() instanceof ClassDecl)
 			list.addFirst(shadowdecl.name());
 			else
 			{
 				Stack<String> stack=new Stack();
 				stack.push(shadowdecl.name());
 				while(!(shadowdecl.SuperShadowClass() instanceof ClassDecl)&&!list.contains(((ShadowClassDecl)(shadowdecl.SuperShadowClass())).name())){ 				
 				ShadowClassDecl sshadow=(ShadowClassDecl)(shadowdecl.SuperShadowClass());
 				stack.push(sshadow.name());
 				shadowdecl=sshadow;
 				}
 				for(String s:stack){
 				 if(!list.contains(s))
 				 list.add(s);
 				}
 			}
			}
	   }
	   return list;
   }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:531
   */
  public LinkedList<ShadowClassDecl> shadowSets()
    {
      LinkedList<ShadowClassDecl> list=new LinkedList<ShadowClassDecl>();
      if(hasShadowClass())
 		{
		   for(Iterator iter1=shadowclasses().iterator();iter1.hasNext();){
 		   ShadowClassDecl shadowdecl=(ShadowClassDecl)iter1.next(); 		
 		   if(shadowdecl.SuperShadowClass() instanceof ClassDecl)
 		   list.addFirst(shadowdecl);
 			else
 			{
 				Stack<ShadowClassDecl> stack=new Stack<ShadowClassDecl>();
 				stack.push(shadowdecl);
 				while(!(shadowdecl.SuperShadowClass() instanceof ClassDecl)&&!list.contains(((ShadowClassDecl)(shadowdecl.SuperShadowClass())).name())){ 				
 				ShadowClassDecl sshadow=(ShadowClassDecl)(shadowdecl.SuperShadowClass());
 				stack.push(sshadow);
 				shadowdecl=sshadow;
 				}
 				for(ShadowClassDecl s:stack){
 				 if(!list.contains(s))
 				 list.add(s);
 				}
 			}
			}
	   }
	   return list;
   }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:558
   */
  public HashMap<String,ArrayList<String>> methodMapShadowes()
    {
        HashMap<String, ArrayList<String>> hs=new HashMap<String, ArrayList<String>>();
        LinkedList<ShadowClassDecl> list=shadowSets();  
        for(Iterator iter=bcMethods().iterator();iter.hasNext();)
        {
         Object obj=iter.next();
         if(obj instanceof MethodDecl){
         MethodDecl md=(MethodDecl)obj;
         ArrayList<String> array=new ArrayList<String>();
         for(int i=0;i<list.size();i++)
         {
          ShadowClassDecl s=(ShadowClassDecl)list.get(i);      
          for(Iterator iter0=s.bcMethods().iterator();iter0.hasNext();)
          {
           Object obj0=iter0.next();
           if(obj0 instanceof MethodDecl){
           MethodDecl md0=(MethodDecl)obj0;        
           if(md0.overrides(md)&&!array.contains(s.name())){
           array.add(s.name());
           break;
            }
           }
          }
        }
         if(array.size()>0)
         hs.put(md.name(),array); 
        }    
       }
      return hs;   
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:603
   */
  public void classList()
    {
    
        HashMap<String, ArrayList<String>> hs=new HashMap<String, ArrayList<String>>();
        LinkedList<ShadowClassDecl> list=shadowSets();  
        for(Iterator iter=bcMethods().iterator();iter.hasNext();)
        {
         Object obj=iter.next();
         if(obj instanceof MethodDecl){
         MethodDecl md=(MethodDecl)obj;
         ArrayList<String> array=new ArrayList<String>();
         for(int i=0;i<list.size();i++)
         {
          ShadowClassDecl s=(ShadowClassDecl)list.get(i);      
          for(Iterator iter0=s.bcMethods().iterator();iter0.hasNext();)
          {
           Object obj0=iter0.next();
           if(obj0 instanceof MethodDecl){
           MethodDecl md0=(MethodDecl)obj0;        
           if(md0.overrides(md)&&!array.contains(s.name())){
           array.add(s.name());
           break;
           }
          }
          }
         }
         hs.put(md.name(),array); 
        }    
       }
          Iterator iterator=hs.entrySet().iterator();   
          while(iterator.hasNext()){   
          Entry entry = (Entry)iterator.next();         
          entry.getKey();
          entry.getValue();
          //System.out.println("da hua hua ti shi second :"+(String)entry.getKey());
          ArrayList<String> ls=(ArrayList<String>)entry.getValue();
          for(int n=0;n<ls.size();n++)
          System.out.println("da hua hua ti shi third :"+ls.get(n));      
        }    
    }
  /**
   * @ast method 
   * @aspect GranuleSeedXmlStructureGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GranuleSeedXml.jrag:685
   */
  public HashMap<String,ArrayList<String>> shadowesRewrite()
    {
        HashMap<String, ArrayList<String>> hs=new HashMap<String, ArrayList<String>>();
        LinkedList<ShadowClassDecl> list=shadowSets();  
        for(Iterator iter=bcMethods().iterator();iter.hasNext();)
        {
         Object obj=iter.next();
         if(obj instanceof MethodDecl){
         MethodDecl md=(MethodDecl)obj;
         ArrayList<String> array=new ArrayList<String>();
         for(int i=0;i<list.size();i++)
         {
          ShadowClassDecl s=(ShadowClassDecl)list.get(i);      
          for(Iterator iter0=s.bcMethods().iterator();iter0.hasNext();)
          {
           Object obj0=iter0.next();
           if(obj0 instanceof MethodDecl){
           MethodDecl md0=(MethodDecl)obj0;        
           if(md0.overrides(md)&&!array.contains(s.name())){
           array.add(s.name());
           break;
            }
           }
          }
        }
         if(array.size()>0){
         String sig=md.signature()+"#"+name();
         hs.put(sig,array); 
         }
        }    
       }
      return hs;   
    }
  /**
   * @ast method 
   * @aspect Generics
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:165
   */
  public TypeDecl makeGeneric(Signatures.ClassSignature s) {
    if(s.hasFormalTypeParameters()) {
      ASTNode node = getParent();
      int index = node.getIndexOfChild(this);
      node.setChild(
          new GenericClassDecl(
            getModifiersNoTransform(),
            getID(),
            s.hasSuperclassSignature() ? new Opt(s.superclassSignature()) : getSuperClassAccessOptNoTransform(),
            s.hasSuperinterfaceSignature() ? s.superinterfaceSignature() : getImplementsListNoTransform(),
            getBodyDeclListNoTransform(),
            s.typeParameters()
          ),
          index
      );
      return (TypeDecl)node.getChildNoTransform(index);
    }
    else {
      if(s.hasSuperclassSignature())
        setSuperClassAccessOpt(new Opt(s.superclassSignature()));
      if(s.hasSuperinterfaceSignature())
        setImplementsList(s.superinterfaceSignature());
      return this;
    }
  }
  /**
   * @ast method 
   * @aspect LookupParTypeDecl
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:1076
   */
  public ClassDecl p(Parameterization parTypeDecl) {
    ClassDecl c = new ClassDeclSubstituted(
      (Modifiers)getModifiers().fullCopy(),
      getID(),
      hasSuperClassAccess() ? new Opt(getSuperClassAccess().type().substitute(parTypeDecl)) : new Opt(),
      getImplementsList().substitute(parTypeDecl),
      new List(), 
      this
    );
    return c;
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public ClassDecl() {
    super();

    setChild(new Opt(), 1);
    setChild(new List(), 2);
    setChild(new List(), 3);

  }
  /**
   * @ast method 
   * @declaredat java.ast:10
   */
  public ClassDecl(Modifiers p0, String p1, Opt<Access> p2, List<Access> p3, List<BodyDecl> p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /**
   * @ast method 
   * @declaredat java.ast:17
   */
  public ClassDecl(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<Access> p3, List<BodyDecl> p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:27
   */
  protected int numChildren() {
    return 4;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:33
   */
  public boolean mayHaveRewrite() {
    return true;
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
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**
   * @ast method 
   * @declaredat java.ast:8
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
   * @declaredat java.ast:19
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for SuperClassAccessOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setSuperClassAccessOpt(Opt<Access> opt) {
    setChild(opt, 1);
  }
  /**
   * Does this node have a SuperClassAccess child?
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public boolean hasSuperClassAccess() {
    return getSuperClassAccessOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child SuperClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getSuperClassAccess() {
    return (Access)getSuperClassAccessOpt().getChild(0);
  }
  /**
   * Setter for optional child SuperClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void setSuperClassAccess(Access node) {
    getSuperClassAccessOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Access> getSuperClassAccessOpt() {
    return (Opt<Access>)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Access> getSuperClassAccessOptNoTransform() {
    return (Opt<Access>)getChildNoTransform(1);
  }
  /**
   * Setter for ImplementsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setImplementsList(List<Access> list) {
    setChild(list, 2);
  }
  /**
   * @return number of children in ImplementsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumImplements() {
    return getImplementsList().getNumChild();
  }
  /**
   * Getter for child in list ImplementsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getImplements(int i) {
    return (Access)getImplementsList().getChild(i);
  }
  /**
   * Add element to list ImplementsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addImplements(Access node) {
    List<Access> list = (parent == null || state == null) ? getImplementsListNoTransform() : getImplementsList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addImplementsNoTransform(Access node) {
    List<Access> list = getImplementsListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ImplementsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setImplements(Access node, int i) {
    List<Access> list = getImplementsList();
    list.setChild(node, i);
  }
  /**
   * Getter for Implements list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<Access> getImplementss() {
    return getImplementsList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<Access> getImplementssNoTransform() {
    return getImplementsListNoTransform();
  }
  /**
   * Getter for list ImplementsList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getImplementsList() {
    List<Access> list = (List<Access>)getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getImplementsListNoTransform() {
    return (List<Access>)getChildNoTransform(2);
  }
  /**
   * Setter for BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 3);
  }
  /**
   * @return number of children in BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Getter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl)getBodyDeclList().getChild(i);
  }
  /**
   * Add element to list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null || state == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for BodyDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * Getter for list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclList() {
    List<BodyDecl> list = (List<BodyDecl>)getChild(3);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>)getChildNoTransform(3);
  }
  /**
   * @ast method 
   * @aspect TypeConversion
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:84
   */
  private boolean refined_TypeConversion_ClassDecl_castingConversionTo_TypeDecl(TypeDecl type)
{
    if(type.isArrayDecl()) {
      return isObject();
    }
    else if(type.isClassDecl()) {
      return this == type || instanceOf(type) || type.instanceOf(this);
    }
    else if(type.isInterfaceDecl()) {
      return !isFinal() || instanceOf(type);
    }
    else return super.castingConversionTo(type);
  }
  /**
   * @ast method 
   * @aspect Generics
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:37
   */
  private boolean refined_Generics_ClassDecl_castingConversionTo_TypeDecl(TypeDecl type)
{
    TypeDecl S = this;
    TypeDecl T = type;
    if(T instanceof TypeVariable) {
      TypeVariable t = (TypeVariable)T;
      if(t.getNumTypeBound() == 0) return true;
      for(int i = 0; i < t.getNumTypeBound(); i++)
        if(castingConversionTo(t.getTypeBound(i).type()))
          return true;
      return false;
    }
    if(T.isClassDecl() && (S.erasure() != S || T.erasure() != T))
        return S.erasure().castingConversionTo(T.erasure());
    return refined_TypeConversion_ClassDecl_castingConversionTo_TypeDecl(type);
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:318
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant cast(Constant c) {
      ASTNode$State state = state();
    Constant cast_Constant_value = cast_compute(c);
    return cast_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant cast_compute(Constant c) {  return Constant.create(c.stringValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:380
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant add(Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant add_Constant_Constant_value = add_compute(c1, c2);
    return add_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant add_compute(Constant c1, Constant c2) {  return Constant.create(c1.stringValue() + c2.stringValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:445
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Constant questionColon(Constant cond, Constant c1, Constant c2) {
      ASTNode$State state = state();
    Constant questionColon_Constant_Constant_Constant_value = questionColon_compute(cond, c1, c2);
    return questionColon_Constant_Constant_Constant_value;
  }
  /**
   * @apilvl internal
   */
  private Constant questionColon_compute(Constant cond, Constant c1, Constant c2) {  return Constant.create(cond.booleanValue() ? c1.stringValue() : c2.stringValue());  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ConstantExpression.jrag:549
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean eqIsTrue(Expr left, Expr right) {
      ASTNode$State state = state();
    boolean eqIsTrue_Expr_Expr_value = eqIsTrue_compute(left, right);
    return eqIsTrue_Expr_Expr_value;
  }
  /**
   * @apilvl internal
   */
  private boolean eqIsTrue_compute(Expr left, Expr right) {  return isString() && left.constant().stringValue().equals(right.constant().stringValue());  }
  /**
   * @attribute syn
   * @aspect ErrorCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ErrorCheck.jrag:30
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int lineNumber() {
      ASTNode$State state = state();
    int lineNumber_value = lineNumber_compute();
    return lineNumber_value;
  }
  /**
   * @apilvl internal
   */
  private int lineNumber_compute() {  return getLine(IDstart);  }
  /**
   * @apilvl internal
   */
  protected boolean hasGranuleDecl_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean hasGranuleDecl_value;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:96
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasGranuleDecl() {
    if(hasGranuleDecl_computed) {
      return hasGranuleDecl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    hasGranuleDecl_value = hasGranuleDecl_compute();
if(isFinal && num == state().boundariesCrossed) hasGranuleDecl_computed = true;
    return hasGranuleDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasGranuleDecl_compute() {
	  if(!shadowclasses().isEmpty())
 		  return true;
 	      return false;    	
	 }
  /**
   * @apilvl internal
   */
  protected boolean hasShadowClass_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean hasShadowClass_value;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:102
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasShadowClass() {
    if(hasShadowClass_computed) {
      return hasShadowClass_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    hasShadowClass_value = hasShadowClass_compute();
if(isFinal && num == state().boundariesCrossed) hasShadowClass_computed = true;
    return hasShadowClass_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasShadowClass_compute() {
	  if(!hasShadowclass){
	  if(!shadowclasses().isEmpty())
       hasShadowclass=true;
	  }
	  return hasShadowclass;    	
  }
  /**
   * @apilvl internal
   */
  protected boolean isExistFit_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isExistFit_value;
  /**
   * @attribute syn
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1928
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isExistFit() {
    if(isExistFit_computed) {
      return isExistFit_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isExistFit_value = isExistFit_compute();
if(isFinal && num == state().boundariesCrossed) isExistFit_computed = true;
    return isExistFit_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isExistFit_compute() {
	 boolean found=false;
	 for(int i=0;i<getNumBodyDecl()&&!found;i++)
	 {
		 if(getBodyDecl(i) instanceof MethodDecl){
		  MethodDecl method=(MethodDecl)getBodyDecl(i);
		    if(method.name().equals("fit")&&method.getNumParameter()==0)
		    found=true;
	  }
	 }
	 return found;
 }
  /**
   * @apilvl internal
   */
  protected boolean isAuxiliaryClassDecl_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isAuxiliaryClassDecl_value;
  /**
   * @attribute syn
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1941
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isAuxiliaryClassDecl() {
    if(isAuxiliaryClassDecl_computed) {
      return isAuxiliaryClassDecl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isAuxiliaryClassDecl_value = isAuxiliaryClassDecl_compute();
if(isFinal && num == state().boundariesCrossed) isAuxiliaryClassDecl_computed = true;
    return isAuxiliaryClassDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isAuxiliaryClassDecl_compute() {
	 String classname=getID();
	 if(classname.equals("GopContext")||classname.equals("fit%fit")||classname.lastIndexOf("$publish$")!=0)
	 return true;
	 return false;	 
 }
  /**
   * @attribute syn
   * @aspect ConstructScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:22
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
  private Collection lookupSuperConstructor_compute() {  return hasSuperclass() ? superclass().constructors() : Collections.EMPTY_LIST;  }
  /**
   * @apilvl internal
   */
  protected boolean hasShadowAsDes_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean hasShadowAsDes_value;
  /**
   * @attribute syn
   * @aspect ConstructorLookup
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:112
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasShadowAsDes() {
    if(hasShadowAsDes_computed) {
      return hasShadowAsDes_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    hasShadowAsDes_value = hasShadowAsDes_compute();
if(isFinal && num == state().boundariesCrossed) hasShadowAsDes_computed = true;
    return hasShadowAsDes_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasShadowAsDes_compute() {
          if(!shadowclasses().isEmpty())
	  return true;
	  boolean found=false;
	  for(Iterator iter=subclasses().iterator();iter.hasNext()&&!found;)
	  {
		  ClassDecl subclass=(ClassDecl)iter.next();
		  if((subclass.instanceOf(this))&&subclass.shadowclasses().size()>=1){
		  found=true;
		  }
	  }
	  if(found) return true;
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:416
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
   * @apilvl internal
   */
  protected int classInstances_visited = -1;
  /**
   * @apilvl internal
   */
  protected boolean classInstances_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean classInstances_initialized = false;
  /**
   * @apilvl internal
   */
  protected LinkedList classInstances_value;
  /**
   * @attribute syn
   * @aspect ImplicitConstructor
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupConstructor.jrag:473
   */
  @SuppressWarnings({"unchecked", "cast"})
  public LinkedList classInstances() {
    if(classInstances_computed) {
      return classInstances_value;
    }
    ASTNode$State state = state();
    if (!classInstances_initialized) {
      classInstances_initialized = true;
      classInstances_value = new LinkedList();
    }
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
    int num = state.boundariesCrossed;
    boolean isFinal = this.is$Final();
      do {
        classInstances_visited = state.CIRCLE_INDEX;
        state.CHANGE = false;
        LinkedList new_classInstances_value = classInstances_compute();
        if ((new_classInstances_value==null && classInstances_value!=null) || (new_classInstances_value!=null && !new_classInstances_value.equals(classInstances_value)))
          state.CHANGE = true;
        classInstances_value = new_classInstances_value; 
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
      classInstances_computed = true;
      }
      else {
      state.RESET_CYCLE = true;
      classInstances_compute();
      state.RESET_CYCLE = false;
        classInstances_computed = false;
        classInstances_initialized = false;
      }
      state.IN_CIRCLE = false; 
      return classInstances_value;
    }
    if(classInstances_visited != state.CIRCLE_INDEX) {
      classInstances_visited = state.CIRCLE_INDEX;
      if (state.RESET_CYCLE) {
        classInstances_computed = false;
        classInstances_initialized = false;
        classInstances_visited = -1;
        return classInstances_value;
      }
      LinkedList new_classInstances_value = classInstances_compute();
      if ((new_classInstances_value==null && classInstances_value!=null) || (new_classInstances_value!=null && !new_classInstances_value.equals(classInstances_value)))
        state.CHANGE = true;
      classInstances_value = new_classInstances_value; 
      return classInstances_value;
    }
    return classInstances_value;
  }
  /**
   * @apilvl internal
   */
  private LinkedList classInstances_compute() {
	Program root=getProgram();
	if(!root.isRegisterToClassInstance)
	{
		root.registerClass();
		root.isRegisterToClassInstance=true;
	}
	return classInstances;
}
  /**
   * @apilvl internal
   */
  protected boolean UnionMethodsName_computed = false;
  /**
   * @apilvl internal
   */
  protected HashMap UnionMethodsName_value;
  /*eq GranuleDecl.methodsNameMap(){
	HashMap map = new HashMap();
	for(Iterator iter=methodsIterator(); iter.hasNext(); ) {
	MethodDecl m = (MethodDecl)iter.next();
	ArrayList list = (ArrayList)map.get(m.name());
	if(list == null) {
	list = new ArrayList(4);
	map.put(m.name(), list);
	}
	list.add(m);
	}
	for(Iterator iter =auxiliarymethodsIterator(); iter.hasNext(); ) {
		AuxiliaryMethodDecl m = (AuxiliaryMethodDecl)iter.next();
		ArrayList list=(ArrayList)map.get(m.name());
		if(list == null) {
		list = new ArrayList(4);
		map.put(m.name(), list);
		}
	 list.add(m);
	}
	return map;
   }* @attribute syn
   * @aspect MemberMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:338
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap UnionMethodsName() {
    if(UnionMethodsName_computed) {
      return UnionMethodsName_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    UnionMethodsName_value = UnionMethodsName_compute();
if(isFinal && num == state().boundariesCrossed) UnionMethodsName_computed = true;
    return UnionMethodsName_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap UnionMethodsName_compute() {
     LinkedList methodset=new LinkedList();
	 for(Iterator iter=methodsIterator(); iter.hasNext();) {
	 MethodDecl m=(MethodDecl)iter.next();
	 methodset.add(m);
	 }
	 if(hasGranuleDecl())
	 {
		 LinkedList shadowclass=new LinkedList(shadowclasses());
		 for(Iterator iter=shadowclass.iterator();iter.hasNext();)
		 {
			 ShadowClassDecl shadowtype=(ShadowClassDecl)iter.next();
			 for(Iterator iter1=shadowtype.methodsIterator();iter1.hasNext();) {
				 MethodDecl mm=(MethodDecl)iter1.next();
				 methodset.add(mm);
		       }
		 }
	 }	 
	
		 for(int i=0;i<methodset.size();i++){
			 MethodDecl decl=(MethodDecl)methodset.get(i);
		    for(int j=i+1;j<methodset.size();j++)
		    {
			   MethodDecl decll=(MethodDecl)methodset.get(j);
			   if(decl.signature().equals(decll.signature()))
			   methodset.remove(j);
		    }
		 }
		 HashMap map=new HashMap();	 
		 for(Iterator iter1=methodset.iterator();iter1.hasNext();)
		 {
			 MethodDecl decl=(MethodDecl)iter1.next();
             ArrayList list=(ArrayList)map.get(decl.name());
			 if(list == null) {
			   list = new ArrayList(4);
			   map.put(decl.name(), list);
			  }
			  list.add(decl);
			 }
         return map;
	 }
  /**
   * @attribute syn
   * @aspect MemberMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:380
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection UnionMemberMethods(String name) {
      ASTNode$State state = state();
    Collection UnionMemberMethods_String_value = UnionMemberMethods_compute(name);
    return UnionMemberMethods_String_value;
  }
  /**
   * @apilvl internal
   */
  private Collection UnionMemberMethods_compute(String name) {
	    Collection c=(Collection)UnionMethodsName().get(name);
	    if(c != null) return c;
	    return Collections.EMPTY_LIST;
 }
  /**
   * @attribute syn
   * @aspect MemberMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:437
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap auxiliarymethodsSignatureMap() {
    if(auxiliarymethodsSignatureMap_computed) {
      return auxiliarymethodsSignatureMap_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    auxiliarymethodsSignatureMap_value = auxiliarymethodsSignatureMap_compute();
if(isFinal && num == state().boundariesCrossed) auxiliarymethodsSignatureMap_computed = true;
    return auxiliarymethodsSignatureMap_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap auxiliarymethodsSignatureMap_compute() {
        HashMap map=new HashMap(localAuxiliaryMethodsSignatureMap());
        return map;
      }
  /**
   * @attribute syn
   * @aspect MemberMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:518
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet interfacesMethodsSignature(String signature) {
      ASTNode$State state = state();
    SimpleSet interfacesMethodsSignature_String_value = interfacesMethodsSignature_compute(signature);
    return interfacesMethodsSignature_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet interfacesMethodsSignature_compute(String signature) {
    SimpleSet set = (SimpleSet)interfacesMethodsSignatureMap().get(signature);
    if(set != null) return set;
    return SimpleSet.emptySet;
  }
  /**
   * @apilvl internal
   */
  protected boolean interfacesMethodsSignatureMap_computed = false;
  /**
   * @apilvl internal
   */
  protected HashMap interfacesMethodsSignatureMap_value;
  /**
   * @attribute syn
   * @aspect MemberMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:524
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap interfacesMethodsSignatureMap() {
    if(interfacesMethodsSignatureMap_computed) {
      return interfacesMethodsSignatureMap_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    interfacesMethodsSignatureMap_value = interfacesMethodsSignatureMap_compute();
if(isFinal && num == state().boundariesCrossed) interfacesMethodsSignatureMap_computed = true;
    return interfacesMethodsSignatureMap_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap interfacesMethodsSignatureMap_compute() {
    HashMap map = new HashMap();
    for(Iterator iter = interfacesIterator(); iter.hasNext(); ) {
      TypeDecl typeDecl = (InterfaceDecl)iter.next();
      for(Iterator i2 = typeDecl.methodsIterator(); i2.hasNext(); ) {
        MethodDecl m = (MethodDecl)i2.next();
        putSimpleSetElement(map, m.signature(), m);
      }
    }
    return map;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:359
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap methodsSignatureMap() {
    if(methodsSignatureMap_computed) {
      return methodsSignatureMap_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    methodsSignatureMap_value = methodsSignatureMap_compute();
if(isFinal && num == state().boundariesCrossed) methodsSignatureMap_computed = true;
    return methodsSignatureMap_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap methodsSignatureMap_compute() {
    HashMap map = new HashMap(localMethodsSignatureMap());
    if(hasSuperclass()) {
      for(Iterator iter = superclass().methodsIterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        if(!m.isPrivate() && m.accessibleFrom(this) && !localMethodsSignatureMap().containsKey(m.signature())) {
          if(!(m instanceof MethodDeclSubstituted) || !localMethodsSignatureMap().containsKey(m.sourceMethodDecl().signature()))
            putSimpleSetElement(map, m.signature(), m);
        }
      }
    }
    for(Iterator outerIter = interfacesIterator(); outerIter.hasNext(); ) {
      TypeDecl typeDecl = (TypeDecl)outerIter.next();
      for(Iterator iter = typeDecl.methodsIterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        if(!m.isPrivate() && m.accessibleFrom(this) && !localMethodsSignatureMap().containsKey(m.signature())) {
          if(!(m instanceof MethodDeclSubstituted) || !localMethodsSignatureMap().containsKey(m.sourceMethodDecl().signature())) {
            if(allMethodsAbstract((SimpleSet)map.get(m.signature())) &&
              (!(m instanceof MethodDeclSubstituted) ||
               allMethodsAbstract((SimpleSet)map.get(m.sourceMethodDecl().signature()))              )
            )
              putSimpleSetElement(map, m.signature(), m);
          }
        }
      }
    }
    return map;
  }
  /**
   * @attribute syn
   * @aspect AncestorMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:618
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet ancestorMethods(String signature) {
    Object _parameters = signature;
    if(ancestorMethods_String_values == null) ancestorMethods_String_values = new java.util.HashMap(4);
    if(ancestorMethods_String_values.containsKey(_parameters)) {
      return (SimpleSet)ancestorMethods_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet ancestorMethods_String_value = ancestorMethods_compute(signature);
if(isFinal && num == state().boundariesCrossed) ancestorMethods_String_values.put(_parameters, ancestorMethods_String_value);
    return ancestorMethods_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet ancestorMethods_compute(String signature) {
    SimpleSet set = SimpleSet.emptySet;
    if(hasSuperclass()) {
      for(Iterator iter = superclass().localMethodsSignature(signature).iterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        if(!m.isPrivate())
          set = set.add(m);
      }
    }
    if(set.size() != 1 || ((MethodDecl)set.iterator().next()).isAbstract()) { 
      for(Iterator iter = interfacesMethodsSignature(signature).iterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        set = set.add(m);
      }
    }
    if(!hasSuperclass()) return set;
    if(set.size() == 1) {
      MethodDecl m = (MethodDecl)set.iterator().next();
      if(!m.isAbstract()) {
        boolean done = true;
        for(Iterator iter = superclass().ancestorMethods(signature).iterator(); iter.hasNext(); ) {
          MethodDecl n = (MethodDecl)iter.next();
          if(n.isPrivate() || !n.accessibleFrom(m.hostType()))
            done = false;
        }
        if(done) return set;
      }
    }
    for(Iterator iter = superclass().ancestorMethods(signature).iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      set = set.add(m);
    }
    return set;
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:450
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet memberTypes(String name) {
    Object _parameters = name;
    if(memberTypes_String_values == null) memberTypes_String_values = new java.util.HashMap(4);
    if(memberTypes_String_values.containsKey(_parameters)) {
      return (SimpleSet)memberTypes_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet memberTypes_String_value = memberTypes_compute(name);
if(isFinal && num == state().boundariesCrossed) memberTypes_String_values.put(_parameters, memberTypes_String_value);
    return memberTypes_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet memberTypes_compute(String name) {
    SimpleSet set = localTypeDecls(name);
    if(!set.isEmpty()) return set;
    for(Iterator outerIter = interfacesIterator(); outerIter.hasNext(); ) {
      TypeDecl type = (TypeDecl)outerIter.next();
      for(Iterator iter = type.memberTypes(name).iterator(); iter.hasNext(); ) {
        TypeDecl decl = (TypeDecl)iter.next();
        if(!decl.isPrivate() && decl.accessibleFrom(this))
          set = set.add(decl);
      }
    }
    if(hasSuperclass()) {
      for(Iterator iter = superclass().memberTypes(name).iterator(); iter.hasNext(); ) {
        TypeDecl decl = (TypeDecl)iter.next();
        if(!decl.isPrivate() && decl.accessibleFrom(this)) {
          set = set.add(decl);
        }
      }
    }
    return set;
  }
  /**
   * @apilvl internal
   */
  protected boolean shadowClassSet_computed = false;
  /**
   * @apilvl internal
   */
  protected LinkedList shadowClassSet_value;
  /**
   * @attribute syn
   * @aspect VariableScope
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:50
   */
  @SuppressWarnings({"unchecked", "cast"})
  public LinkedList shadowClassSet() {
    if(shadowClassSet_computed) {
      return shadowClassSet_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    shadowClassSet_value = shadowClassSet_compute();
if(isFinal && num == state().boundariesCrossed) shadowClassSet_computed = true;
    return shadowClassSet_value;
  }
  /**
   * @apilvl internal
   */
  private LinkedList shadowClassSet_compute() {
	  CompilationUnit root=getCompilationUnit();
	   if(root.fromSource()){
	      for(int i=0;i<root.getNumTypeDecl();i++){
	    	  if(root.getTypeDecl(i) instanceof GranuleDecl) {
	    		  GranuleDecl granuledecl=(GranuleDecl)root.getTypeDecl(i);
	    		  ClassDecl rootclass=(ClassDecl)(granuledecl.getRootClassAccess().type());
	    		  if(this.instanceOf(rootclass)){
	    		  for(int j= 0; j<granuledecl.getNumBodyDecl();j++) {
	    		  if(granuledecl.getBodyDecl(j) instanceof MemberShadowClassDecl){
	    		   MemberShadowClassDecl membershadowclass=(MemberShadowClassDecl)granuledecl.getBodyDecl(j);
				   ShadowClassDecl shadowclass=(ShadowClassDecl)membershadowclass.getShadowClassDecl();
				   //if(shadowclass.seedClass(shadowclass.getID())==this){
				   if(shadowclass.seedClass()==this){
					 if(!shadowclassset.contains(shadowclass))
				     shadowclassset.add(shadowclass);
				   }	    					    
	    		  }
	    		 }
	    	   }
	    	  }	    		  
	    	  else if(root.getTypeDecl(i) instanceof ShadowClassDecl){
	    		  ShadowClassDecl shadowclass=(ShadowClassDecl)root.getTypeDecl(i);
	    		  if(shadowclass.seedClass()==this){
	    		 // if(shadowclass.seedClass(shadowclass.getID())==this){
	    		   if(!shadowclassset.contains(shadowclass))
	    			   shadowclassset.add(shadowclass);
	    	  }
	      }
     }
	}
	  return shadowclassset;
  }
  /**
   * @attribute syn
   * @aspect Fields
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:347
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
    if(hasSuperclass()) {
      for(Iterator iter = superclass().fieldsIterator(); iter.hasNext(); ) {
        FieldDeclaration decl = (FieldDeclaration)iter.next();
        if(!decl.isPrivate() && decl.accessibleFrom(this) && !localFieldsMap().containsKey(decl.name()))
          putSimpleSetElement(map, decl.name(), decl);
      }
    }
    for(Iterator outerIter = interfacesIterator(); outerIter.hasNext(); ) {
      TypeDecl type = (TypeDecl)outerIter.next();
      for(Iterator iter = type.fieldsIterator(); iter.hasNext(); ) {
        FieldDeclaration decl = (FieldDeclaration)iter.next();
        if(!decl.isPrivate() && decl.accessibleFrom(this) && !localFieldsMap().containsKey(decl.name()))
          putSimpleSetElement(map, decl.name(), decl);
      }
    }
    return map;
  }
  /**
   * @apilvl internal
   */
  protected boolean UnionMemberFields_computed = false;
  /**
   * @apilvl internal
   */
  protected HashMap UnionMemberFields_value;
  /**
   * @attribute syn
   * @aspect Fields
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:380
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap UnionMemberFields() {
    if(UnionMemberFields_computed) {
      return UnionMemberFields_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    UnionMemberFields_value = UnionMemberFields_compute();
if(isFinal && num == state().boundariesCrossed) UnionMemberFields_computed = true;
    return UnionMemberFields_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap UnionMemberFields_compute() {
	     HashMap map=new HashMap(localFieldsMap());	 
		 if(existShadowClass())
		  {
			 LinkedList shadowclass=new LinkedList(shadowClassSet());
			 for(Iterator iter=shadowclass.iterator();iter.hasNext();)
			 {
				 ShadowClassDecl shadowtype=(ShadowClassDecl)iter.next();				
				 for(Iterator iter1=shadowtype.localFieldsMap().values().iterator();iter1.hasNext();) {
					 FieldDeclaration f=(FieldDeclaration)iter1.next();
					 if(!f.isPrivate() && f.accessibleFrom(this) && !localFieldsMap().containsKey(f.name())){
				     putSimpleSetElement(map,f.name(),f);
					 }
				 }
			 }
		}	
	  return map;
  }
  protected java.util.Map UnionMemberFields_String_values;
  /**
   * @attribute syn
   * @aspect Fields
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:416
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet UnionMemberFields(String name) {
    Object _parameters = name;
    if(UnionMemberFields_String_values == null) UnionMemberFields_String_values = new java.util.HashMap(4);
    if(UnionMemberFields_String_values.containsKey(_parameters)) {
      return (SimpleSet)UnionMemberFields_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet UnionMemberFields_String_value = UnionMemberFields_compute(name);
if(isFinal && num == state().boundariesCrossed) UnionMemberFields_String_values.put(_parameters, UnionMemberFields_String_value);
    return UnionMemberFields_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet UnionMemberFields_compute(String name) {  return UnionMemberFields().containsKey(name) ? (SimpleSet)UnionMemberFields().get(name) : SimpleSet.emptySet;  }
  /**
   * @attribute syn
   * @aspect Fields
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:421
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet memberFields(String name) {
    Object _parameters = name;
    if(memberFields_String_values == null) memberFields_String_values = new java.util.HashMap(4);
    if(memberFields_String_values.containsKey(_parameters)) {
      return (SimpleSet)memberFields_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet memberFields_String_value = memberFields_compute(name);
if(isFinal && num == state().boundariesCrossed) memberFields_String_values.put(_parameters, memberFields_String_value);
    return memberFields_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet memberFields_compute(String name) {	
    SimpleSet fields = localFields(name);
    if(!fields.isEmpty())
      return fields; // this causes hiding of fields in superclass and interfaces
    if(hasSuperclass()) {
      for(Iterator iter = superclass().memberFields(name).iterator(); iter.hasNext(); ) {
        FieldDeclaration decl = (FieldDeclaration)iter.next();
        if(!decl.isPrivate() && decl.accessibleFrom(this))
          fields = fields.add(decl);
      }
    }
    for(Iterator outerIter = interfacesIterator(); outerIter.hasNext(); ) {
      TypeDecl type = (TypeDecl)outerIter.next();
      for(Iterator iter = type.memberFields(name).iterator(); iter.hasNext(); ) {
        FieldDeclaration decl = (FieldDeclaration)iter.next();
        if(!decl.isPrivate() && decl.accessibleFrom(this))
          fields = fields.add(decl);
      }
    }
    return fields;
  }
  /**
   * @apilvl internal
   */
  protected boolean getShadowSet_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection getShadowSet_value;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:977
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection getShadowSet() {
    if(getShadowSet_computed) {
      return getShadowSet_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    getShadowSet_value = getShadowSet_compute();
if(isFinal && num == state().boundariesCrossed) getShadowSet_computed = true;
    return getShadowSet_value;
  }
  /**
   * @apilvl internal
   */
  private Collection getShadowSet_compute() {
	 Program root=getProgram();
	 root.collectClassMapping();
	 return root.getSpecificShadowSet(this);	
	}
  /**
   * @apilvl internal
   */
  protected boolean getShadowclasses_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection getShadowclasses_value;
  /**
   * @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:983
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection getShadowclasses() {
    if(getShadowclasses_computed) {
      return getShadowclasses_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    getShadowclasses_value = getShadowclasses_compute();
if(isFinal && num == state().boundariesCrossed) getShadowclasses_computed = true;
    return getShadowclasses_value;
  }
  /**
   * @apilvl internal
   */
  private Collection getShadowclasses_compute() {
	  Collection s=new LinkedList();
	  Program root=getProgram();
	  if(root==null) return s;
	  for(Iterator iter = root.compilationUnitIterator(); iter.hasNext(); ) {
      CompilationUnit cu = (CompilationUnit)iter.next();
      if(cu.fromSource())
		s.addAll(cu.getShadowclasses(this));
       }
		return s;
	}
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:17
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection unimplementedMethods() {
    if(unimplementedMethods_computed) {
      return unimplementedMethods_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    unimplementedMethods_value = unimplementedMethods_compute();
if(isFinal && num == state().boundariesCrossed) unimplementedMethods_computed = true;
    return unimplementedMethods_value;
  }
  /**
   * @apilvl internal
   */
  private Collection unimplementedMethods_compute() {
    Collection c = new ArrayList();
    for(Iterator iter = interfacesMethodsIterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      boolean implemented = false;
      SimpleSet set = (SimpleSet)localMethodsSignature(m.signature());
      if(set.size() == 1) {
        MethodDecl n = (MethodDecl)set.iterator().next();
        if(!n.isAbstract())
          implemented = true;
      }
      if(!implemented) {
        set = (SimpleSet)ancestorMethods(m.signature());
        for(Iterator i2 = set.iterator(); i2.hasNext(); ) {
          MethodDecl n = (MethodDecl)i2.next();
          if(!n.isAbstract())
            implemented = true;
        }
      }
      if(!implemented) {
        c.add(m);
      }
    }

    if(hasSuperclass()) {
      for(Iterator iter = superclass().unimplementedMethods().iterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        SimpleSet set = (SimpleSet)localMethodsSignature(m.signature());
        if(set.size() == 1) {
          MethodDecl n = (MethodDecl)set.iterator().next();
          if(n.isAbstract() || !n.overrides(m))
            c.add(m);
        }
        else
          c.add(m);
      }
    }

    for(Iterator iter = localMethodsIterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(m.isAbstract()) {
        c.add(m);
      }
    }
    return c;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:64
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean hasAbstract() {
    if(hasAbstract_computed) {
      return hasAbstract_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    hasAbstract_value = hasAbstract_compute();
if(isFinal && num == state().boundariesCrossed) hasAbstract_computed = true;
    return hasAbstract_value;
  }
  /**
   * @apilvl internal
   */
  private boolean hasAbstract_compute() {  return !unimplementedMethods().isEmpty();  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\AutoBoxing.jrag:134
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean castingConversionTo(TypeDecl type) {
    Object _parameters = type;
    if(castingConversionTo_TypeDecl_values == null) castingConversionTo_TypeDecl_values = new java.util.HashMap(4);
    if(castingConversionTo_TypeDecl_values.containsKey(_parameters)) {
      return ((Boolean)castingConversionTo_TypeDecl_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean castingConversionTo_TypeDecl_value = castingConversionTo_compute(type);
if(isFinal && num == state().boundariesCrossed) castingConversionTo_TypeDecl_values.put(_parameters, Boolean.valueOf(castingConversionTo_TypeDecl_value));
    return castingConversionTo_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean castingConversionTo_compute(TypeDecl type) {
    if(refined_Generics_ClassDecl_castingConversionTo_TypeDecl(type))
      return true;
    boolean canUnboxThis = !unboxed().isUnknown();
    boolean canUnboxType = !type.unboxed().isUnknown();
    if(canUnboxThis && !canUnboxType)
      return unboxed().wideningConversionTo(type);
    return false;
    /*
    else if(unboxingConversionTo(type))
      return true;
    return false;
    */
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:210
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isClassDecl() {
      ASTNode$State state = state();
    boolean isClassDecl_value = isClassDecl_compute();
    return isClassDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isClassDecl_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:225
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isString() {
    if(isString_computed) {
      return isString_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isString_value = isString_compute();
if(isFinal && num == state().boundariesCrossed) isString_computed = true;
    return isString_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isString_compute() {  return fullName().equals("java.lang.String");  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:228
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
  private boolean isObject_compute() {  return name().equals("Object") && packageName().equals("java.lang");  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsSubtype.jrag:387
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
  private boolean instanceOf_compute(TypeDecl type) { return subtype(type); }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:545
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSupertypeOfClassDecl(ClassDecl type) {
      ASTNode$State state = state();
    boolean isSupertypeOfClassDecl_ClassDecl_value = isSupertypeOfClassDecl_compute(type);
    return isSupertypeOfClassDecl_ClassDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSupertypeOfClassDecl_compute(ClassDecl type) {
    if(super.isSupertypeOfClassDecl(type)){
      return true;
    }    
    return type.hasSuperclass() && type.superclass() != null && type.superclass().instanceOf(this);
  }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:572
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSupertypeOfInterfaceDecl(InterfaceDecl type) {
      ASTNode$State state = state();
    boolean isSupertypeOfInterfaceDecl_InterfaceDecl_value = isSupertypeOfInterfaceDecl_compute(type);
    return isSupertypeOfInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSupertypeOfInterfaceDecl_compute(InterfaceDecl type) {  return isObject();  }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:585
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isSupertypeOfArrayDecl(ArrayDecl type) {
      ASTNode$State state = state();
    boolean isSupertypeOfArrayDecl_ArrayDecl_value = isSupertypeOfArrayDecl_compute(type);
    return isSupertypeOfArrayDecl_ArrayDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isSupertypeOfArrayDecl_compute(ArrayDecl type) {
    if(super.isSupertypeOfArrayDecl(type))
      return true;
    return type.hasSuperclass() && type.superclass() != null && type.superclass().instanceOf(this);
  }
  /**
   * @attribute syn
   * @aspect NestedTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:672
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
  private boolean isInnerClass_compute() {  return isNestedType() && !isStatic() && enclosingType().isClassDecl();  }
  /**
   * @attribute syn
   * @aspect Circularity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:820
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isCircular() {
    if(isCircular_computed) {
      return isCircular_value;
    }
    ASTNode$State state = state();
    if (!isCircular_initialized) {
      isCircular_initialized = true;
      isCircular_value = true;
    }
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
    int num = state.boundariesCrossed;
    boolean isFinal = this.is$Final();
      do {
        isCircular_visited = state.CIRCLE_INDEX;
        state.CHANGE = false;
        boolean new_isCircular_value = isCircular_compute();
        if (new_isCircular_value!=isCircular_value)
          state.CHANGE = true;
        isCircular_value = new_isCircular_value; 
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
      isCircular_computed = true;
      }
      else {
      state.RESET_CYCLE = true;
      isCircular_compute();
      state.RESET_CYCLE = false;
        isCircular_computed = false;
        isCircular_initialized = false;
      }
      state.IN_CIRCLE = false; 
      return isCircular_value;
    }
    if(isCircular_visited != state.CIRCLE_INDEX) {
      isCircular_visited = state.CIRCLE_INDEX;
      if (state.RESET_CYCLE) {
        isCircular_computed = false;
        isCircular_initialized = false;
        isCircular_visited = -1;
        return isCircular_value;
      }
      boolean new_isCircular_value = isCircular_compute();
      if (new_isCircular_value!=isCircular_value)
        state.CHANGE = true;
      isCircular_value = new_isCircular_value; 
      return isCircular_value;
    }
    return isCircular_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isCircular_compute() {
    if(hasSuperClassAccess()) {
      Access a = getSuperClassAccess().lastAccess();
      while(a != null) {
        if(a.type().isCircular())
          return true;
        a = (a.isQualified() && a.qualifier().isTypeAccess()) ? (Access)a.qualifier() : null;
      }
    }
    for(int i = 0; i < getNumImplements(); i++) {
      Access a = getImplements(i).lastAccess();
      while(a != null) {
        if(a.type().isCircular())
          return true;
        a = (a.isQualified() && a.qualifier().isTypeAccess()) ? (Access)a.qualifier() : null;
      }
    }
    return false;
  }
  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\ConstantPoolNames.jrag:15
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
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1404
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
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:11
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl superEnclosing() {
      ASTNode$State state = state();
    TypeDecl superEnclosing_value = superEnclosing_compute();
    return superEnclosing_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl superEnclosing_compute() {
     return superclass().erasure().enclosing();
   }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:228
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Annotation annotation(TypeDecl typeDecl) {
      ASTNode$State state = state();
    Annotation annotation_TypeDecl_value = annotation_compute(typeDecl);
    return annotation_TypeDecl_value;
  }
  /**
   * @apilvl internal
   */
  private Annotation annotation_compute(TypeDecl typeDecl) {
    Annotation a = super.annotation(typeDecl);
    if(a != null) return a;
    if(hasSuperclass()) {
      // If the queried annotation is itself annotation with @Inherited then
      // delegate the query to the superclass
      if(typeDecl.annotation(lookupType("java.lang.annotation", "Inherited")) != null)
        return superclass().annotation(typeDecl);
    }
    return null;
  }
  /**
   * @attribute syn
   * @aspect GenericsTypeCheck
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:371
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashSet implementedInterfaces() {
    if(implementedInterfaces_computed) {
      return implementedInterfaces_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    implementedInterfaces_value = implementedInterfaces_compute();
if(isFinal && num == state().boundariesCrossed) implementedInterfaces_computed = true;
    return implementedInterfaces_value;
  }
  /**
   * @apilvl internal
   */
  private HashSet implementedInterfaces_compute() {
    HashSet set = new HashSet();
    if(hasSuperclass())
      set.addAll(superclass().implementedInterfaces());
    for(Iterator iter = interfacesIterator(); iter.hasNext(); ) {
      InterfaceDecl decl = (InterfaceDecl)iter.next();
      set.add(decl);
      set.addAll(decl.implementedInterfaces());
    }
    return set;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsSubtype.jrag:407
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean subtype(TypeDecl type) {
    Object _parameters = type;
    if(subtype_TypeDecl_values == null) subtype_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if(subtype_TypeDecl_values.containsKey(_parameters)) {
      Object _o = subtype_TypeDecl_values.get(_parameters);
      if(!(_o instanceof ASTNode$State.CircularValue)) {
        return ((Boolean)_o).booleanValue();
      }
      else
        _value = (ASTNode$State.CircularValue)_o;
    }
    else {
      _value = new ASTNode$State.CircularValue();
      subtype_TypeDecl_values.put(_parameters, _value);
      _value.value = Boolean.valueOf(true);
    }
    ASTNode$State state = state();
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
      int num = state.boundariesCrossed;
      boolean isFinal = this.is$Final();
      boolean new_subtype_TypeDecl_value;
      do {
        _value.visited = new Integer(state.CIRCLE_INDEX);
        state.CHANGE = false;
        new_subtype_TypeDecl_value = subtype_compute(type);
        if (new_subtype_TypeDecl_value!=((Boolean)_value.value).booleanValue()) {
          state.CHANGE = true;
          _value.value = Boolean.valueOf(new_subtype_TypeDecl_value);
        }
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
        subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);
      }
      else {
        subtype_TypeDecl_values.remove(_parameters);
      state.RESET_CYCLE = true;
      subtype_compute(type);
      state.RESET_CYCLE = false;
      }
      state.IN_CIRCLE = false; 
      return new_subtype_TypeDecl_value;
    }
    if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
      _value.visited = new Integer(state.CIRCLE_INDEX);
      boolean new_subtype_TypeDecl_value = subtype_compute(type);
      if (state.RESET_CYCLE) {
        subtype_TypeDecl_values.remove(_parameters);
      }
      else if (new_subtype_TypeDecl_value!=((Boolean)_value.value).booleanValue()) {
        state.CHANGE = true;
        _value.value = new_subtype_TypeDecl_value;
      }
      return new_subtype_TypeDecl_value;
    }
    return ((Boolean)_value.value).booleanValue();
  }
  /**
   * @apilvl internal
   */
  private boolean subtype_compute(TypeDecl type) {  return type.supertypeClassDecl(this);  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsSubtype.jrag:422
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean supertypeClassDecl(ClassDecl type) {
      ASTNode$State state = state();
    boolean supertypeClassDecl_ClassDecl_value = supertypeClassDecl_compute(type);
    return supertypeClassDecl_ClassDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean supertypeClassDecl_compute(ClassDecl type) {  return super.supertypeClassDecl(type) || 
    type.hasSuperclass() && type.superclass() != null && type.superclass().subtype(this);  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsSubtype.jrag:438
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean supertypeInterfaceDecl(InterfaceDecl type) {
      ASTNode$State state = state();
    boolean supertypeInterfaceDecl_InterfaceDecl_value = supertypeInterfaceDecl_compute(type);
    return supertypeInterfaceDecl_InterfaceDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean supertypeInterfaceDecl_compute(InterfaceDecl type) {  return isObject();  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericsSubtype.jrag:451
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean supertypeArrayDecl(ArrayDecl type) {
      ASTNode$State state = state();
    boolean supertypeArrayDecl_ArrayDecl_value = supertypeArrayDecl_compute(type);
    return supertypeArrayDecl_ArrayDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean supertypeArrayDecl_compute(ArrayDecl type) {
    if(super.supertypeArrayDecl(type))
      return true;
    return type.hasSuperclass() && type.superclass() != null && type.superclass().subtype(this);
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:258
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet bridgeCandidates(String signature) {
      ASTNode$State state = state();
    SimpleSet bridgeCandidates_String_value = bridgeCandidates_compute(signature);
    return bridgeCandidates_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet bridgeCandidates_compute(String signature) {
    SimpleSet set = ancestorMethods(signature);
    for(Iterator iter = interfacesMethodsSignature(signature).iterator(); iter.hasNext(); )
      set = set.add(iter.next());
    return set;
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:376
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean needsSignatureAttribute() {
    if(needsSignatureAttribute_computed) {
      return needsSignatureAttribute_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    needsSignatureAttribute_value = needsSignatureAttribute_compute();
if(isFinal && num == state().boundariesCrossed) needsSignatureAttribute_computed = true;
    return needsSignatureAttribute_value;
  }
  /**
   * @apilvl internal
   */
  private boolean needsSignatureAttribute_compute() {
    if(hasSuperclass() && superclass().needsSignatureAttribute())
      return true;
    for(Iterator iter = interfacesIterator(); iter.hasNext(); )
      if(((TypeDecl)iter.next()).needsSignatureAttribute())
        return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:414
   */
  @SuppressWarnings({"unchecked", "cast"})
  public String classSignature() {
    if(classSignature_computed) {
      return classSignature_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    classSignature_value = classSignature_compute();
if(isFinal && num == state().boundariesCrossed) classSignature_computed = true;
    return classSignature_value;
  }
  /**
   * @apilvl internal
   */
  private String classSignature_compute() {
    StringBuffer buf = new StringBuffer();
    // SuperclassSignature
    if(hasSuperclass())
      buf.append(superclass().classTypeSignature());
    // SuperinterfaceSignature*
    for(Iterator iter = interfacesIterator(); iter.hasNext(); )
      buf.append(((TypeDecl)iter.next()).classTypeSignature());
    return buf.toString();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\Modifiers.jrag:289
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    return super.Define_boolean_mayBeFinal(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:74
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getImplementsListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.TYPE_NAME;
    }
    if(caller == getSuperClassAccessOptNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return super.Define_NameType_nameType(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:719
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
    if(caller == getImplementsListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return hostType();
    }
    if(caller == getSuperClassAccessOptNoTransform()) {
      return hostType();
    }
    return super.Define_TypeDecl_hostType(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:276
   * @apilvl internal
   */
  public boolean Define_boolean_withinSuppressWarnings(ASTNode caller, ASTNode child, String s) {
    if(caller == getImplementsListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return hasAnnotationSuppressWarnings(s) || withinSuppressWarnings(s);
    }
    if(caller == getSuperClassAccessOptNoTransform()) {
      return hasAnnotationSuppressWarnings(s) || withinSuppressWarnings(s);
    }
    return super.Define_boolean_withinSuppressWarnings(caller, child, s);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:377
   * @apilvl internal
   */
  public boolean Define_boolean_withinDeprecatedAnnotation(ASTNode caller, ASTNode child) {
    if(caller == getImplementsListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return isDeprecated() || withinDeprecatedAnnotation();
    }
    if(caller == getSuperClassAccessOptNoTransform()) {
      return isDeprecated() || withinDeprecatedAnnotation();
    }
    return super.Define_boolean_withinDeprecatedAnnotation(caller, child);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 1872
    if(!hasSuperClassAccess()&&!isObject()&&!isUnknown()&&!isExistFit()&&!isAuxiliaryClassDecl()) {
      state().duringGOP++;
      ASTNode result = rewriteRule0();
      state().duringGOP--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1872
   * @apilvl internal
   */  private ClassDecl rewriteRule0() {
{
		  List<Modifier> list=new List<Modifier>();
		  list.add(new Modifier("public"));
		  Modifiers m=new Modifiers(list);
		  String s="void";
		  Access access=new TypeAccess(s);
		  List<ParameterDeclaration> list1=new List<ParameterDeclaration>();
		  List<Access> list2=new List<Access>();
		  Block bl=new Block();
		  Opt<Block> opt=new Opt<Block>(bl);
		  MethodDecl method=new MethodDecl(m,access,"fit",list1,list2,opt);	
		  addBodyDecl(method); 
		 /*CompilationUnit root=getCompilationUnit();
		  if(root.hasTrueUpdateExpr||root.hasFalseUpdateExpr){
		  List<Modifier> lst=new List<Modifier>();
	      Modifiers mss=new Modifiers(lst);
	      Access sa=new TypeAccess("int");
	      String fieldname="state";
	      Expr value=new IntegerLiteral("1");
		  if(root.hasFalseUpdateExpr)
		  value=new IntegerLiteral("2");
	      Opt<Expr> opt1=new Opt<Expr>(value);	 
	      VariableDeclaration ff=new VariableDeclaration(mss,sa,fieldname,opt1);
	      bl.addStmt(ff);
		  }*/
		  return this;
	  }  }
}
