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
 * @declaredat Granule.ast:1
 */
public class GranuleDecl extends ReferenceType implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    shadowclasses_visited = -1;
    shadowclasses_computed = false;
    shadowclasses_initialized = false;
    shadowclasses_value = null;
    memberFields_String_values = null;
    localFields_String_values = null;
    localFieldsMap_computed = false;
    localFieldsMap_value = null;
    accessibleFrom_TypeDecl_values = null;
    auxiliarymethodsSignatureMap_computed = false;
    auxiliarymethodsSignatureMap_value = null;
    ancestorMethods_String_values = null;
    methodsSignatureMap_computed = false;
    methodsSignatureMap_value = null;
    isCircular_visited = -1;
    isCircular_computed = false;
    isCircular_initialized = false;
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
  public GranuleDecl clone() throws CloneNotSupportedException {
    GranuleDecl node = (GranuleDecl)super.clone();
    node.shadowclasses_visited = -1;
    node.shadowclasses_computed = false;
    node.shadowclasses_initialized = false;
    node.shadowclasses_value = null;
    node.memberFields_String_values = null;
    node.localFields_String_values = null;
    node.localFieldsMap_computed = false;
    node.localFieldsMap_value = null;
    node.accessibleFrom_TypeDecl_values = null;
    node.auxiliarymethodsSignatureMap_computed = false;
    node.auxiliarymethodsSignatureMap_value = null;
    node.ancestorMethods_String_values = null;
    node.methodsSignatureMap_computed = false;
    node.methodsSignatureMap_value = null;
    node.isCircular_visited = -1;
    node.isCircular_computed = false;
    node.isCircular_initialized = false;
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
  public GranuleDecl copy() {
      try {
        GranuleDecl node = (GranuleDecl)clone();
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
  public GranuleDecl fullCopy() {
    GranuleDecl res = (GranuleDecl)copy();
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\BoundNames.jrag:59
   */
  public ContextVar addMemberContextVar(ContextVar c)
  {
	  addBodyDecl(new MemberContextVarDecl(c));
	  return ((MemberContextVarDecl)getBodyDecl(getNumBodyDecl()-1)).getContextVar();
  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:11
   */
  public boolean hasRootClass(){
    	  return rootClass()!=null;
  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:36
   */
  
   protected LinkedList shadowclasses=new LinkedList();
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:61
   */
  boolean hasShadowClass()
   {
 	  if(!shadowclasses().isEmpty())
 		  return true;
 	      return false;    	
	 }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:505
   */
  public boolean isExpandsGranule(TypeDecl typedecl)
{
	//CompilationUnit root=getCompilationUnit();
	Program root=getProgram();
	GranuleTree granuletree=root.CreateGranuleTree();
	if(granuletree!=null)
	{
	    boolean found=false;
		Iterator iter=granuletree.toList().iterator();
		int i=0;
		while(iter.hasNext()&&!found)
		{
			GranuleTreeNode granulenode=(GranuleTreeNode)iter.next();
			 if(granulenode.getGranuledecl()==this){
			 found=true;
			 i=granuletree.toList().indexOf(granulenode);
			 }
		}
	   if(i>1){
	   GranuleTreeNode granuleprevious=(GranuleTreeNode)granuletree.toList().get(i-1);
	   if(granuleprevious.getGranuledecl()==typedecl)
	   return true;
	   }
	   else if(i<granuletree.toList().size()-1){
	   GranuleTreeNode granulenext=(GranuleTreeNode)granuletree.toList().get(i+1);
	   if(granulenext.getGranuledecl()==typedecl)
	   return true;
	   }	   
	}
    return false;		
}
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:909
   */
  public boolean hasMemberShadowClassDecl()
	{
	  for(int i=0;i<getNumBodyDecl();i++)
	  {
		 if(getBodyDecl(i) instanceof MemberShadowClassDecl)
		 return true;
	  }
	  return false;
  }
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1181
   */
  public void typeCheck(){ 
   super.typeCheck();
   int cnt=0;
   for(int i = 0; i < getNumBodyDecl(); i++) {
      if(cnt>3)
      error(" granule can only have one GranuleBody exsit");
      BodyDecl bodyDecl = getBodyDecl(i);
      if( bodyDecl instanceof StaticInitializer || bodyDecl instanceof ConstructorDecl )
        error(" granule can not have method or initializer or constructor memeber.");
      if((bodyDecl instanceof MethodDecl)||(bodyDecl instanceof AuxiliaryMethodDecl))
         cnt++;
    }
    if (cnt==0)
       error(" granule declaration must have a fitness method!");
    if(cnt>3)
    	error(" granule declaration must have at most three fitness methods!");
    if(hasSuperGranuleSpec() && !getSuperGranuleSpec().getSuperGranuleAccess().type().isGranuleDecl())
      error("A granule may only inherit a granule and not " + getSuperGranuleSpec().getSuperGranuleAccess().type().typeName() +" type ");  
    if(hasSuperGranuleSpec()&&getSuperGranuleSpec().getSuperGranuleAccess().type().isGranuleDecl())
    {
 	   if(getSuperGranuleSpec() instanceof ExpandsGranuleSpec)
 	   {
 		   ExpandsGranuleSpec expands=(ExpandsGranuleSpec)getSuperGranuleSpec();
 		   TypeDecl typedecl=superGranule();
 		   if(typedecl!=null){
 		   GranuleDecl typedecll=(GranuleDecl)typedecl;
 		   TypeDecl supertypedecl=typedecll.getRootClassAccess().type();
 		   TypeDecl rootclass=getRootClassAccess().type();
 		   ClassDecl classroot=(ClassDecl)rootclass;
 		   if(supertypedecl.isClassDecl())
 		   {
 			   ClassDecl classdecl=(ClassDecl)supertypedecl;
 			   if(!rootclass.instanceOf(classdecl) && !this.isExpandsGranule(typedecll))
 			     error(" the keyword expands is used wrong in the granule declaration  "+name());
 		   }
 		  }
 	   }
 	   if(getSuperGranuleSpec() instanceof ExtendsGranuleSpec)
 	   {
 		   ExtendsGranuleSpec extend=(ExtendsGranuleSpec)getSuperGranuleSpec();
 		   TypeDecl typedecl=superGranule();
 		   if(typedecl!=null){
 		    GranuleDecl typedecll=(GranuleDecl)typedecl;
 		   TypeDecl supertypedecl=(TypeDecl)(typedecll.getRootClassAccess().type());
 		   TypeDecl rootclass=getRootClassAccess().type();
 		   ClassDecl classroot=(ClassDecl)rootclass;
 		   if(!typedecl.isGranuleDecl()&&!supertypedecl.isClassDecl())
 		   error("the super granule  "+typedecl.name()+ " of  the granule " +getID()+ "is wrong !");
 		   else
 		   {
 			  ClassDecl classdecl=(ClassDecl)supertypedecl;
 			  if(rootclass!=classdecl)
 		      error("the super granule "+typedecl.name()+" and the granule "+getID()+ " attached to class is not same! ");    		
 			  HashMap localcontext=localFieldsMap();
 			  HashMap superlocalcontext=typedecl.localFieldsMap();
 			  int i=0,j=0;
 			  for(Iterator iter0=localcontext.values().iterator();iter0.hasNext();){
 				  Object del=iter0.next();
 				  if(del instanceof ContextVarDeclaration)
 			       i++;
 			  }
 			  for(Iterator iter1=localcontext.values().iterator();iter1.hasNext();){
 			  Object dell=iter1.next();
 			  if(dell instanceof ContextVarDeclaration)
 			  {
 				 ContextVarDeclaration decll=(ContextVarDeclaration)dell;
 				 if(superlocalcontext.containsKey(decll.name()))
 				 j++;
 			  }
 			  }
 			  if(i!=j)
 			  error(" the granule " +getID()+"  and super granule "+typedecl.name()+" can not use keyword extends !");
       }
 	 }  
  }
 }
}
  /**
   * @ast method 
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1286
   */
  public void nameCheck() {
	 super.nameCheck();
	 if(!getRootClassAccess().type().isClassDecl())
	 error("Root class of the granule may only be a class and not " + getRootClassAccess().type().typeName());	
    //if(!isTopLevelType())
       //error("Granule can only be topLevelType");
    //TypeDecl rootClass = rootClass(((TypeAccess)getRootClassAccess()).name());
    ClassDecl rootClass=rootClass();
    if(rootClass==null)
     error("In the granule " +name()+",its root class must be a class  "+((TypeAccess)getRootClassAccess()).name()+" that exsits");
    if(hasSuperGranuleSpec())
    {
    	if(getSuperGranuleSpec() instanceof ExpandsGranuleSpec)
    	{
    	ExpandsGranuleSpec expands=(ExpandsGranuleSpec)getSuperGranuleSpec();
    	//TypeDecl typedecl=superGranule(((TypeAccess)expands.getSuperGranuleAccess()).name());
    	TypeDecl typedecl=superGranule();
    	if(typedecl==null)
        error("The super granule  "+expands.getSuperGranuleAccess()+" of the granule "+name()+ "  does not exist! ");
    	}
    	else if(getSuperGranuleSpec() instanceof ExtendsGranuleSpec)
    	{
    	   ExtendsGranuleSpec extend=(ExtendsGranuleSpec)getSuperGranuleSpec();
   		  // TypeDecl typedecl=superGranule(((TypeAccess)extend.getSuperGranuleAccess()).name());
    	  TypeDecl typedecl=superGranule();
   		   if(typedecl==null)
   		   error("The super granule  "+extend.getSuperGranuleAccess()+" of the granule "+name()+ "  does not exist! ");
    	}
    }
    if(isCircular())
        error("circular inheritance dependency in " + typeName()); 
}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:360
   */
  
    public static boolean checkDone=false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:361
   */
  
    private boolean checkFlag=false;
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:362
   */
  public void setCheckFlag(boolean flag)
    {
    	this.checkFlag=flag;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:366
   */
  public boolean getCheckFlag()
    {
    	return checkFlag;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:370
   */
  public boolean hasCheckFitM()
    {
    	for(int i=0;i<getNumBodyDecl();i++)
    	{
    		BodyDecl by=(BodyDecl)getBodyDecl(i);
    		if(by instanceof MethodDecl)
    		{
    			MethodDecl md=(MethodDecl)by;
    			if(md.name().equals("checkfitness"))
    			{
    			   if(md.getBlock().getStmt(0) instanceof ReturnStmt){
    			   ReturnStmt rm=(ReturnStmt)(md.getBlock().getStmt(0));
    			   if(rm.getResult() instanceof BooleanLiteral){
    			   BooleanLiteral bl=(BooleanLiteral)rm.getResult();
    			   if(bl.constant().equals("true"))
    			   return true;	
    			}
    		   }   
    		}
    	}
    	}
      return false;
    }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:393
   */
  public void addFieldDecl(boolean flag)
    {
   	 List<Modifier> lst=new List<Modifier>();
   	 lst.add(new Modifier("private"));
   	 lst.add(new Modifier("static"));
   	 Modifiers mss=new Modifiers(lst);
     Access sa=new PrimitiveTypeAccess("boolean");
   	 String fieldname="checkOn";
   	 String result=Boolean.toString(flag);
   	 Expr value=new BooleanLiteral(result);
   	 Opt<Expr> opt=new Opt<Expr>(value);
   	 FieldDeclaration ff=new FieldDeclaration(mss,sa,fieldname,opt);
   	 addBodyDecl(ff);
   	 checkDone=true;
    }
  /**
   * @ast method 
   * @aspect GranuleCodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GOPCodeGeneration.jrag:123
   */
  public void generateClassfile() {
    super.generateClassfile();
    String fileName = destinationPath() + File.separator + constantPoolName() + ".class";
    if(options().verbose()) System.out.println("Writing class file to " + fileName);
    try {
      ConstantPool cp = constantPool();

      // force building of constant pool
      cp.addClass(constantPoolName());
      if(hasRootClass()) {
        //cp.addClass(rootClass(((TypeAccess)getRootClassAccess()).name()).constantPoolName());
    	  cp.addClass(rootClass().constantPoolName());
      }
      int numInterfaces = 0;
      for(Iterator iter = bcFields().iterator(); iter.hasNext();){
     	 Object fieldnode=iter.next();
         if(fieldnode instanceof ContextVarDeclaration){
          ContextVarDeclaration field=(ContextVarDeclaration)fieldnode;
          cp.addUtf8(field.name());
          cp.addUtf8(field.type().typeDescriptor());
          field.attributes();    	
         }
         if(fieldnode instanceof FieldDeclaration){
          FieldDeclaration field=(FieldDeclaration)fieldnode; 
          cp.addUtf8(field.name());
          cp.addUtf8(field.type().typeDescriptor());
          field.attributes();    	
         }    	
       } 
      if(needsEnclosing()){    
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
      //out.writeChar(hasRootClass() ? cp.addClass(rootClass(((TypeAccess)getRootClassAccess()).name()).constantPoolName()) : 0);
      out.writeChar(hasRootClass()?cp.addClass(rootClass().constantPoolName()):0);
      out.writeChar(numInterfaces);
      Collection fields = bcFields();
      for(Iterator iter1 = fields.iterator(); iter1.hasNext(); )
      {
    	  Object field=iter1.next();
    	  if(field instanceof ContextVarDeclaration)
    	  {
    		  iter1.remove();
    		  fields.remove(field);    		  
    	  }
      }
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
      /*for(Iterator iter = fields.iterator(); iter.hasNext(); ) {
      	Object fieldnode=iter.next();
      	if(fieldnode instanceof ContextVarDeclaration){
          ContextVarDeclaration field =(ContextVarDeclaration)fieldnode;
          out.writeChar(field.flags());
          out.writeChar(cp.addUtf8(field.name()));
          out.writeChar(cp.addUtf8(field.type().typeDescriptor()));
          out.writeChar(field.attributes().size());
          for(Iterator itera = field.attributes().iterator(); itera.hasNext();)
            ((Attribute)itera.next()).emit(out);
      	}
      	 if(fieldnode instanceof FieldDeclaration)
      	{
      		    FieldDeclaration field = (FieldDeclaration)fieldnode;
      	        out.writeChar(field.flags());
      	        out.writeChar(cp.addUtf8(field.name()));
      	        out.writeChar(cp.addUtf8(field.type().typeDescriptor()));
      	        out.writeChar(field.attributes().size());
      	        for(Iterator itera = field.attributes().iterator(); itera.hasNext();)
      	          ((Attribute)itera.next()).emit(out);
      	}
        }*/
      if(needsEnclosing()) {
    	out.writeChar(0);
        //out.writeChar(0 /*Modifiers.ACC_PRIVATE*/);
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
   * @declaredat Granule.ast:1
   */
  public GranuleDecl() {
    super();

    setChild(new Opt(), 1);
    setChild(new List(), 3);

  }
  /**
   * @ast method 
   * @declaredat Granule.ast:9
   */
  public GranuleDecl(Modifiers p0, String p1, Opt<SuperGranuleSpec> p2, Access p3, List<BodyDecl> p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /**
   * @ast method 
   * @declaredat Granule.ast:16
   */
  public GranuleDecl(Modifiers p0, beaver.Symbol p1, Opt<SuperGranuleSpec> p2, Access p3, List<BodyDecl> p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setChild(p4, 3);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:26
   */
  protected int numChildren() {
    return 4;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat Granule.ast:32
   */
  public boolean mayHaveRewrite() {
    return true;
  }
  /**
   * Setter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Getter for Modifiers
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public Modifiers getModifiers() {
    return (Modifiers)getChild(0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:18
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers)getChildNoTransform(0);
  }
  /**
   * Setter for lexeme ID
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**
   * @ast method 
   * @declaredat Granule.ast:8
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
   * @declaredat Granule.ast:19
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Setter for SuperGranuleSpecOpt
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setSuperGranuleSpecOpt(Opt<SuperGranuleSpec> opt) {
    setChild(opt, 1);
  }
  /**
   * Does this node have a SuperGranuleSpec child?
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public boolean hasSuperGranuleSpec() {
    return getSuperGranuleSpecOpt().getNumChild() != 0;
  }
  /**
   * Getter for optional child SuperGranuleSpec
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SuperGranuleSpec getSuperGranuleSpec() {
    return (SuperGranuleSpec)getSuperGranuleSpecOpt().getChild(0);
  }
  /**
   * Setter for optional child SuperGranuleSpec
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:27
   */
  public void setSuperGranuleSpec(SuperGranuleSpec node) {
    getSuperGranuleSpecOpt().setChild(node, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<SuperGranuleSpec> getSuperGranuleSpecOpt() {
    return (Opt<SuperGranuleSpec>)getChild(1);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:44
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<SuperGranuleSpec> getSuperGranuleSpecOptNoTransform() {
    return (Opt<SuperGranuleSpec>)getChildNoTransform(1);
  }
  /**
   * Setter for RootClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setRootClassAccess(Access node) {
    setChild(node, 2);
  }
  /**
   * Getter for RootClassAccess
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public Access getRootClassAccess() {
    return (Access)getChild(2);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:18
   */
  public Access getRootClassAccessNoTransform() {
    return (Access)getChildNoTransform(2);
  }
  /**
   * Setter for BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:5
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 3);
  }
  /**
   * @return number of children in BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:12
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Getter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl)getBodyDeclList().getChild(i);
  }
  /**
   * Add element to list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:27
   */
  public void addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null || state == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:34
   */
  public void addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:42
   */
  public void setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
  }
  /**
   * Getter for BodyDecl list.
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:50
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat Granule.ast:56
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * Getter for list BodyDeclList
   * @apilvl high-level
   * @ast method 
   * @declaredat Granule.ast:63
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
   * @declaredat Granule.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>)getChildNoTransform(3);
  }
  /**
   * @apilvl internal
   */
  protected int shadowclasses_visited = -1;
  /**
   * @apilvl internal
   */
  protected boolean shadowclasses_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean shadowclasses_initialized = false;
  /**
   * @apilvl internal
   */
  protected LinkedList shadowclasses_value;
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:37
   */
  @SuppressWarnings({"unchecked", "cast"})
  public LinkedList shadowclasses() {
    if(shadowclasses_computed) {
      return shadowclasses_value;
    }
    ASTNode$State state = state();
    if (!shadowclasses_initialized) {
      shadowclasses_initialized = true;
      shadowclasses_value = new LinkedList();
    }
    if (!state.IN_CIRCLE) {
      state.IN_CIRCLE = true;
    int num = state.boundariesCrossed;
    boolean isFinal = this.is$Final();
      do {
        shadowclasses_visited = state.CIRCLE_INDEX;
        state.CHANGE = false;
        LinkedList new_shadowclasses_value = shadowclasses_compute();
        if ((new_shadowclasses_value==null && shadowclasses_value!=null) || (new_shadowclasses_value!=null && !new_shadowclasses_value.equals(shadowclasses_value)))
          state.CHANGE = true;
        shadowclasses_value = new_shadowclasses_value; 
        state.CIRCLE_INDEX++;
      } while (state.CHANGE);
      if(isFinal && num == state().boundariesCrossed) {
      shadowclasses_computed = true;
      }
      else {
      state.RESET_CYCLE = true;
      shadowclasses_compute();
      state.RESET_CYCLE = false;
        shadowclasses_computed = false;
        shadowclasses_initialized = false;
      }
      state.IN_CIRCLE = false; 
      return shadowclasses_value;
    }
    if(shadowclasses_visited != state.CIRCLE_INDEX) {
      shadowclasses_visited = state.CIRCLE_INDEX;
      if (state.RESET_CYCLE) {
        shadowclasses_computed = false;
        shadowclasses_initialized = false;
        shadowclasses_visited = -1;
        return shadowclasses_value;
      }
      LinkedList new_shadowclasses_value = shadowclasses_compute();
      if ((new_shadowclasses_value==null && shadowclasses_value!=null) || (new_shadowclasses_value!=null && !new_shadowclasses_value.equals(shadowclasses_value)))
        state.CHANGE = true;
      shadowclasses_value = new_shadowclasses_value; 
      return shadowclasses_value;
    }
    return shadowclasses_value;
  }
  /**
   * @apilvl internal
   */
  private LinkedList shadowclasses_compute() {
		Program root=getProgram();
		if(!root.isRegisterToShadowClass) {
		   root.registerToShadowClass();
		  root.isRegisterToShadowClass=true;
	}
		return shadowclasses;
	}
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:764
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
      return fields; 
      
    //TypeDecl rootClass = rootClass(((TypeAccess)getRootClassAccess()).name());
    ClassDecl rootClass=rootClass();
    if(rootClass!=null){
       for(Iterator iter = rootClass.memberFields(name).iterator(); iter.hasNext(); ) {
         FieldDeclaration decl = (FieldDeclaration)iter.next();
          if(!decl.isPrivate() && decl.accessibleFrom(this))
         fields = fields.add(decl);
       }
       if(!fields.isEmpty())
         return fields;  
    }
    if(hasSuperGranuleSpec()){
    	TypeAccess typeaccess=(TypeAccess)(getSuperGranuleSpec().getSuperGranuleAccess());
    	//TypeDecl superGranule=superGranule(typeaccess.name());
    	TypeDecl superGranule=superGranule();
    	if(superGranule!=null){
       		for(Iterator iter = superGranule.memberFields(name).iterator(); iter.hasNext(); ) {
         		FieldDeclaration decl = (FieldDeclaration)iter.next();
                        if(!decl.isPrivate() && decl.accessibleFrom(this))
         		fields = fields.add(decl);
       		}
        	if(!fields.isEmpty())
         	return fields;  
   		 }
    }
    if(hasSuperGranuleSpec()){
    	TypeAccess typeaccess=(TypeAccess)(getSuperGranuleSpec().getSuperGranuleAccess());
    	//TypeDecl superGranule = superGranule(typeaccess.name());
    	TypeDecl superGranule=superGranule();
    	if(superGranule!=null){
       		for(Iterator iter = superGranule.memberFields(name).iterator(); iter.hasNext(); ) {
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
    }
    return fields;
  }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:818
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet localFields(String name) {
    Object _parameters = name;
    if(localFields_String_values == null) localFields_String_values = new java.util.HashMap(4);
    if(localFields_String_values.containsKey(_parameters)) {
      return (SimpleSet)localFields_String_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    SimpleSet localFields_String_value = localFields_compute(name);
if(isFinal && num == state().boundariesCrossed) localFields_String_values.put(_parameters, localFields_String_value);
    return localFields_String_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet localFields_compute(String name) {  return localFieldsMap().containsKey(name) ? (SimpleSet)localFieldsMap().get(name) : SimpleSet.emptySet;  }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:820
   */
  @SuppressWarnings({"unchecked", "cast"})
  public HashMap localFieldsMap() {
    if(localFieldsMap_computed) {
      return localFieldsMap_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    localFieldsMap_value = localFieldsMap_compute();
if(isFinal && num == state().boundariesCrossed) localFieldsMap_computed = true;
    return localFieldsMap_value;
  }
  /**
   * @apilvl internal
   */
  private HashMap localFieldsMap_compute() {
		   HashMap map = new HashMap();
		   for(int i = 0; i < getNumBodyDecl(); i++) {
		   if(getBodyDecl(i) instanceof MemberContextVarDecl){
			   MemberContextVarDecl decll=(MemberContextVarDecl)getBodyDecl(i);
		       //for(int j=0; j< decll.getNumContextVar();j++){
		        ASTNode node=decll.getContextVar();
		        if(node instanceof ContextVarDeclaration){  
		        ContextVarDeclaration decl=(ContextVarDeclaration)node; 		   
	            SimpleSet fields=(SimpleSet)map.get(decl.name());
	 	          if(fields == null)  fields=SimpleSet.emptySet;
	 	          fields = fields.add(decl);
	 	          map.put(decl.name(), fields);
		        }
			    else if (node instanceof List)
			    {
			        List listvar=(List)node;
			        for(Iterator iter = listvar.iterator(); iter.hasNext(); ) {
			        VariableDeclaration decl=(VariableDeclaration)iter.next();
			        SimpleSet fields=(SimpleSet)map.get(decl.name());
				    if(fields == null) fields = SimpleSet.emptySet;
				    fields = fields.add(decl);
				    map.put(decl.name(), fields);
			        }
			      }
		   }
		   else if(getBodyDecl(i) instanceof FieldDeclaration)
		        {
		         FieldDeclaration decl = (FieldDeclaration)getBodyDecl(i); 		        
			     SimpleSet fields=(SimpleSet)map.get(decl.name());
			 	 if(fields == null)  fields=SimpleSet.emptySet;
			 	 fields = fields.add(decl);
			 	  map.put(decl.name(), fields);
		        }
		        }
		    return map;
		    }
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1046
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ClassDecl rootClass() {
      ASTNode$State state = state();
    ClassDecl rootClass_value = rootClass_compute();
    return rootClass_value;
  }
  /**
   * @apilvl internal
   */
  private ClassDecl rootClass_compute() {
	  if(isObject())
	  return null;
	  if(!isCircular()&&getRootClassAccess().type().isClassDecl())
		  return (ClassDecl)getRootClassAccess().type();
	  return (ClassDecl)typeObject();
}
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1090
   */
  @SuppressWarnings({"unchecked", "cast"})
  public GranuleDecl superGranule() {
      ASTNode$State state = state();
    GranuleDecl superGranule_value = superGranule_compute();
    return superGranule_value;
  }
  /**
   * @apilvl internal
   */
  private GranuleDecl superGranule_compute() {
	if(hasSuperGranuleSpec()&&!isCircular()&&getSuperGranuleSpec().getSuperGranuleAccess().type().isGranuleDecl())
	return (GranuleDecl)getSuperGranuleSpec().getSuperGranuleAccess().type();
	return null;
}
  /**
   * @attribute syn
   * @aspect GOP
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1110
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isGranuleDecl() {
      ASTNode$State state = state();
    boolean isGranuleDecl_value = isGranuleDecl_compute();
    return isGranuleDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isGranuleDecl_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1413
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
  private boolean accessibleFrom_compute(TypeDecl type) {  return false;  }
  /**
   * @attribute syn
   * @aspect ExternalVars
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1923
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int GranuleCount() {
      ASTNode$State state = state();
    int GranuleCount_value = GranuleCount_compute();
    return GranuleCount_value;
  }
  /**
   * @apilvl internal
   */
  private int GranuleCount_compute() {
	 return super.GranuleCount()+1;
 }
  /**
   * @attribute syn
   * @aspect MemberMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:441
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
   * @aspect AncestorMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:671
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
	  SimpleSet set=SimpleSet.emptySet;
	  TypeDecl classdecl=getRootClassAccess().type();
	  for(Iterator iter=classdecl.localMethodsSignature(signature).iterator();iter.hasNext();){
		  MethodDecl m=(MethodDecl)iter.next();
		  if(!m.isPrivate())
		   set=set.add(m);
		  }
	    if(set.size() == 1) {
	      MethodDecl m = (MethodDecl)set.iterator().next();
	      if(!m.isAbstract()) {
	        boolean done = true;
	        for(Iterator iter =classdecl.ancestorMethods(signature).iterator(); iter.hasNext(); ) {
	          MethodDecl n = (MethodDecl)iter.next();
	          if(n.isPrivate() || !n.accessibleFrom(m.hostType()))
	            done = false;
	        }
	        if(done) return set;
	      }
	    }
	    for(Iterator iter =classdecl.ancestorMethods(signature).iterator(); iter.hasNext(); ) {
	      MethodDecl m = (MethodDecl)iter.next();
	      set = set.add(m);
	    }
	    return set;	   
  }
  /**
   * @attribute syn
   * @aspect AncestorMethods
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:698
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
	  TypeDecl classdecl=getRootClassAccess().type();
	      for(Iterator iter =classdecl.methodsIterator(); iter.hasNext(); ) {
	        MethodDecl m = (MethodDecl)iter.next();
	        if(!m.isPrivate() && m.accessibleFrom(this) && !localMethodsSignatureMap().containsKey(m.signature()))
	          putSimpleSetElement(map, m.signature(), m);
	      }
	    return map;  
  }
  /**
   * @attribute syn
   * @aspect Circularity
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:840
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
	  if(hasSuperGranuleSpec())
	    {
		 Access a=getSuperGranuleSpec().getSuperGranuleAccess().lastAccess();
		 while(a!=null){
		   if(a.type().isCircular())
		   return true;
		   a=(a.isQualified() && a.qualifier().isTypeAccess())?(Access)a.qualifier():null;
		 }
		 }
       return false;
  }
  /**
   * @attribute syn
   * @aspect GranuleCodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GOPCodeGeneration.jrag:120
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
   * @aspect GranuleCodeGeneration
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\GOPCodeGeneration.jrag:121
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:18
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
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:8
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getRootClassAccessNoTransform()) {
      return NameType.TYPE_NAME;
    }
    return super.Define_NameType_nameType(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1392
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return true;
    }
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return true;
    }
    return super.Define_boolean_mayBePrivate(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1393
   * @apilvl internal
   */
  public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return true;
    }
    return super.Define_boolean_mayBePublic(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1394
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return true;
    }
    return super.Define_boolean_mayBeProtected(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1395
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return super.Define_boolean_mayBeAbstract(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1396
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return super.Define_boolean_mayBeStatic(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1397
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return super.Define_boolean_mayBeStrictfp(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:1398
   * @apilvl internal
   */
  public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
    if(caller == getModifiersNoTransform()) {
      return false;
    }
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return false;
    }
    return super.Define_boolean_mayBeFinal(caller, child);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:655
   * @apilvl internal
   */
  public boolean Define_boolean_isNestedType(ASTNode caller, ASTNode child) {
    if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return true;
    }
    return super.Define_boolean_isNestedType(caller, child);
  }
  /**
   * @apilvl internal
   */
  public ASTNode rewriteTo() {
    // Declared in D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag at line 894
    if(hasMemberShadowClassDecl()) {
      state().duringGOP++;
      ASTNode result = rewriteRule0();
      state().duringGOP--;
      return result;
    }

    return super.rewriteTo();
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\GOP.jrag:894
   * @apilvl internal
   */  private GranuleDecl rewriteRule0() {
{
	    for(int i=0;i<getNumBodyDecl();i++)
	    {
	    	if(getBodyDecl(i) instanceof MemberShadowClassDecl){
	        ShadowClassDecl scd=((MemberShadowClassDecl)getBodyDecl(i)).getShadowClassDecl(); 
	        CompilationUnit root=getCompilationUnit();
	        root.addTypeDecl(scd);
	        getBodyDeclsNoTransform().removeChild(i);
	    	}
	    }
	    return this;
	 }  }
}
