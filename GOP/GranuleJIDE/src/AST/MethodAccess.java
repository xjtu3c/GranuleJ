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
 * @declaredat java.ast:17
 */
public class MethodAccess extends Access implements Cloneable {
  /**
   * @apilvl low-level
   */
  public void flushCache() {
    super.flushCache();
    computeDAbefore_int_Variable_values = null;
    exceptionCollection_computed = false;
    exceptionCollection_value = null;
    decll_computed = false;
    decll_value = null;
    decls_computed = false;
    decls_value = null;
    decl_computed = false;
    decl_value = null;
    isFilterClass_computed = false;
    type_computed = false;
    type_value = null;
    typeArguments_MethodDecl_values = null;
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
  public MethodAccess clone() throws CloneNotSupportedException {
    MethodAccess node = (MethodAccess)super.clone();
    node.computeDAbefore_int_Variable_values = null;
    node.exceptionCollection_computed = false;
    node.exceptionCollection_value = null;
    node.decll_computed = false;
    node.decll_value = null;
    node.decls_computed = false;
    node.decls_value = null;
    node.decl_computed = false;
    node.decl_value = null;
    node.isFilterClass_computed = false;
    node.type_computed = false;
    node.type_value = null;
    node.typeArguments_MethodDecl_values = null;
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /**
   * @apilvl internal
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodAccess copy() {
      try {
        MethodAccess node = (MethodAccess)clone();
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
  public MethodAccess fullCopy() {
    MethodAccess res = (MethodAccess)copy();
    for(int i = 0; i < getNumChildNoTransform(); i++) {
      ASTNode node = getChildNoTransform(i);
      if(node != null) node = node.fullCopy();
      res.setChild(node, i);
    }
    return res;
    }
  /**
   * @ast method 
   * @aspect AnonymousClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\AnonymousClasses.jrag:148
   */
  protected void collectExceptions(Collection c, ASTNode target) {
    super.collectExceptions(c, target);
    for(int i = 0; i < decl().getNumException(); i++)
      c.add(decl().getException(i).type());
  }
  /**
   * @ast method 
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:43
   */
  public void exceptionHandling() {
    for(Iterator iter = exceptionCollection().iterator(); iter.hasNext(); ) {
      TypeDecl exceptionType = (TypeDecl)iter.next();
      if(!handlesException(exceptionType))
        error("" + decl().hostType().fullName() + "." + this + " invoked in " + hostType().fullName() + " may throw uncaught exception " + exceptionType.fullName());
    }
  }
  /**
   * @ast method 
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:241
   */
  protected boolean reachedException(TypeDecl catchType) {
    for(Iterator iter = exceptionCollection().iterator(); iter.hasNext(); ) {
      TypeDecl exceptionType = (TypeDecl)iter.next();
      if(catchType.mayCatch(exceptionType))
        return true;
    }
    return super.reachedException(catchType);
  }
  /**
   * @ast method 
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:142
   */
  
   protected static HashMap auxiliarymethodaccess=new HashMap();
  /**
   * @ast method 
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:143
   */
  public boolean hasAuxiliaryMethodAccess()
   {
 	  if(!auxiliarymethodaccess.isEmpty())
 		 return true;
 	  return false;
   }
  /**
   * @ast method 
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:207
   */
  private static SimpleSet removeInstanceMethods(SimpleSet c) {
    SimpleSet set = SimpleSet.emptySet;
    for(Iterator iter = c.iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(m.isStatic())
        set = set.add(m);
    }
    return set;
  }
  /**
   * @ast method 
   * @aspect MethodDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:253
   */
  public boolean applicable(MethodDecl decl) {
    if(getNumArg() != decl.getNumParameter())
      return false;
    if(!name().equals(decl.name()))
      return false;      
    for(int i = 0; i < getNumArg(); i++){
      if(!getArg(i).type().instanceOf(decl.getParameter(i).type())){
    	//if(!getArg(i).type().methodInvocationConversionTo(decl.getParameter(i).type()))
    	return false;
        }
      }
    return true;
  }
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:526
   */
  public boolean isTransformMethod(){
    if(!isFilterClass()&&(!hasPrevExpr() || (hasPrevExpr() && prevExpr().isThisAccess()))){
	     MethodDecl md=decl();
         //return md.isOverridden();
		 return false;
	}
	return false;
	}
  /**
   * @ast method 
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:535
   */
  public String getReturnType(){
	   MethodDecl md=decl();
	   TypeDecl td=md.getTypeAccess().type();
	   if(td.isPrimitive()||td.isBoolean()) 
       return td.boxed().typeName();
       else    	
	   return td.typeName();
	}
  /**
   * @ast method 
   * @aspect NodeConstructors
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NodeConstructors.jrag:56
   */
  public MethodAccess(String name, List args, int start, int end) {
    this(name, args);
    setStart(start);
    setEnd(end);
  }
  /**
   * @ast method 
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:584
   */
  public void toString(StringBuffer s) {
    s.append(name());
    s.append("(");
    if(getNumArg() > 0) {
      getArg(0).toString(s);
      for(int i = 1; i < getNumArg(); i++) {
        s.append(", ");
        getArg(i).toString(s);
      }
    }
    s.append(")");
  }
  /**
   * @ast method 
   * @aspect TypeHierarchyCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:23
   */
  public void nameCheck() {
    if(isQualified() && qualifier().isPackageAccess() && !qualifier().isUnknown())
      error("The method " + decl().signature() + 
          " can not be qualified by a package name.");
    if(isQualified() && decl().isAbstract() && qualifier().isSuperAccess()) 
      error("may not access abstract methods in superclass");
//===================GOP=======================================
    if(isQualified()&&decl().isAbstract()&&qualifier().isSeedAccess())
      error("shadow class may not access abstract methods in seed class ");
    if(isQualified()&&decl().isAbstract()&&qualifier().isPreviousAccess())
        error("shadow class may not access abstract methods in seed class ");    
//===================END========================================
    if(decls().isEmpty() && (!isQualified() || !qualifier().isUnknown())) {
      StringBuffer s = new StringBuffer();
      s.append("no method named " + name());
      s.append("(");
      for(int i = 0; i < getNumArg(); i++) {
        if(i != 0)
          s.append(", ");
        s.append(getArg(i).type().typeName());
      }
      s.append(")" + " in " + methodHost() + " matches.");
      if(singleCandidateDecl() != null)
        s.append(" However, there is a method " + singleCandidateDecl().signature());
      error(s.toString());
    }
    if(decls().size() > 1) {
      boolean allAbstract = true;
      for(Iterator iter = decls().iterator(); iter.hasNext() && allAbstract; ) {
         MethodDecl m = (MethodDecl)iter.next();        
        if(!m.isAbstract() && !m.hostType().isObject())
          allAbstract = false;
      }
      if(!allAbstract && validArgs()){
        StringBuffer s = new StringBuffer();
        s.append("several most specific methods for " + this + "\n");
        for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
          MethodDecl m = (MethodDecl)iter.next();
          s.append("    " + m.signature() + " in " + m.hostType().typeName() + "\n");
        }
        error(s.toString());
      }       
    }
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:796
   */
  public boolean isProceedMethod()
  { 
	  MethodDecl m=decl();
	  if(m.hasAuxiliaryMethodDecl()){
		  for(Iterator iter=m.sortingAuxiliaryMethod().iterator();iter.hasNext();){
		    	MemberDecl memberdecl=(MemberDecl)iter.next();
		        if(memberdecl instanceof AuxiliaryMethodDecl)
		        { 
		         AuxiliaryMethodDecl decl=(AuxiliaryMethodDecl)memberdecl;
		         if(decl.hasBlock())
		 		{
		 			for(int i=0;i<decl.getBlock().getNumStmt();i++)
		 			{
		 				if(decl.getBlock().getStmt(i) instanceof ExprStmt){
		 					ExprStmt smt=(ExprStmt)decl.getBlock().getStmt(i);
		 					if(smt.getExpr() instanceof MethodAccess){
		 					MethodAccess method=(MethodAccess)smt.getExpr();
		 					if(method==this)
		 					return true;
		 					}
		 			     }			 
		 			 }		
		 		  }
		       }
		    }
	  }
	  return false;	  
  }
  /**
   * @ast method 
   * @aspect CreateBCode
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\CreateBCode.jrag:1067
   */
  protected void createLoadQualifier(CodeGeneration gen) {
    MethodDecl m = decl();
    if(hasPrevExpr()) {
      // load explicit qualifier
      prevExpr().createBCode(gen);
      // pop qualifier stack element for class variables
      // this qualifier must be computed to ensure side effects
      if(m.isStatic() && !prevExpr().isTypeAccess())
        prevExpr().type().emitPop(gen);
    }
    else if(!m.isStatic()) {
      // load implicit this qualifier
      emitThis(gen, methodQualifierType());
    }
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:114
   */
  private TypeDecl refined_InnerClasses_MethodAccess_methodQualifierType() { 
    if(hasPrevExpr()){
      return prevExpr().type();
    }
    TypeDecl typeDecl = hostType();
    while(typeDecl != null && !typeDecl.hasMethod(name()))
      typeDecl = typeDecl.enclosingType();
    /*if(typeDecl.isCompoundSubClass(decl().hostType()))
    {
      typeDecl=decl().hostType();
    }*/
    if(typeDecl.isCompoundSubClass(decl().hostType()))
    {
    	typeDecl=decl().hostType();
    	//System.out.println("qualifier type is "+typeDecl.typeName());
    }
    if(typeDecl != null)
      return typeDecl;
    return decl().hostType();
  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:189
   */
  public TypeDecl seedAccessorTarget() {   
		TypeDecl targetDecl = prevExpr().type();
	    TypeDecl enclosing = hostType();  
	    if(enclosing.isShadowClassDecl())
	     return enclosing;
	    do {
	      enclosing = enclosing.enclosingType();     
	    } while (!enclosing.instanceOf(targetDecl));  
	    return enclosing;
	  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:199
   */
  public TypeDecl previousAccessorTarget() {   
		TypeDecl targetDecl = prevExpr().type();
	    TypeDecl enclosing = hostType();  
	    if(enclosing.isShadowClassDecl())
	     return enclosing;
	    do {
	      enclosing = enclosing.enclosingType();     
	    } while (!enclosing.instanceOf(targetDecl));  
	    return enclosing;
	  }
  /**
   * @ast method 
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:210
   */
  public TypeDecl superAccessorTarget() {   
	TypeDecl targetDecl = prevExpr().type();
    TypeDecl enclosing = hostType();  
    if(enclosing.isShadowClassDecl())
     return enclosing;
    do {
      enclosing = enclosing.enclosingType();     
    } while (!enclosing.instanceOf(targetDecl));  
    return enclosing;
  }
  /**
   * @ast method 
   * @aspect Transformations
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\Transformations.jrag:69
   */
  public void refined_Transformations_MethodAccess_transformation() {
    MethodDecl m = decl(); 

    /*if(!isQualified() && !m.isStatic()) {
      TypeDecl typeDecl = hostType();
      while(typeDecl != null && !typeDecl.hasMethod(name()))
        typeDecl = typeDecl.enclosingType();
      ASTNode result = replace(this).with(typeDecl.createQualifiedAccess().qualifiesAccess(new ThisAccess("this")).qualifiesAccess(new MethodAccess(name(), getArgList())));
      result.transformation();
      return;
    }*/
    if(requiresAccessor()) {
      /* Access to private methods in enclosing types:
      The original MethodAccess is replaced with an access to an accessor method
      built by createAccessor(). This method is built lazily and differs from
      normal MethodDeclarations in the following ways:
      1) The method in the class file should always be static and the signature
         is thus changed to include a possible this reference as the first argument. 
      2) The method is always invoked using INVOKESTATIC
      3) The flags must indicate that the method is static and package private
      */
    
      super.transformation();
      replace(this).with(decl().createAccessor(methodQualifierType()).createBoundAccess(getArgList()));
      return;
    }
    else if(!m.isStatic() && isQualified() && prevExpr().isSuperAccess() &&!hostType().instanceOf(prevExpr().type())) {
    	decl().createSuperAccessor(superAccessorTarget());
    }
//===================GOP=======================================
    else if(!!m.isStatic() && isQualified() && prevExpr().isSeedAccess() &&!hostType().instanceOf(prevExpr().type())){
    	decl().createSeedAccessor(seedAccessorTarget());
    }
    else if(!!m.isStatic() && isQualified() && prevExpr().isPreviousAccess() &&!hostType().instanceOf(prevExpr().type())){
    	decl().createPreviousAccessor(previousAccessorTarget());
    }
//===================END========================================
    super.transformation();
  }
  /**
   * @ast method 
   * @aspect Annotations
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Annotations.jrag:336
   */
  public void checkModifiers() {
    if(decl().isDeprecated() &&
      !withinDeprecatedAnnotation() &&
      hostType().topLevelType() != decl().hostType().topLevelType() &&
      !withinSuppressWarnings("deprecation"))
        warning(decl().signature() + " in " + decl().hostType().typeName() + " has been deprecated");
  }
  /**
   * @ast method 
   * @aspect GenericMethodsInference
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericMethodsInference.jrag:46
   */
  public Collection computeConstraints(GenericMethodDecl decl) {
    Constraints c = new Constraints();
    // store type parameters
    for(int i = 0; i < decl.original().getNumTypeParameter(); i++)
      c.addTypeVariable(decl.original().getTypeParameter(i));
    
    // add initial constraints
    for(int i = 0; i < getNumArg(); i++) {
      TypeDecl A = getArg(i).type();
      int index = i >= decl.getNumParameter() ? decl.getNumParameter() - 1 : i;
      TypeDecl F = decl.getParameter(index).type();
      if(decl.getParameter(index) instanceof VariableArityParameterDeclaration 
         && (getNumArg() != decl.getNumParameter() || !A.isArrayDecl())) {
        F = F.componentType();
      }
      c.convertibleTo(A, F);
    }
    if(c.rawAccess)
      return new ArrayList();
    
    //c.printConstraints();
    //System.err.println("Resolving equality constraints");
    c.resolveEqualityConstraints();
    //c.printConstraints();

    //System.err.println("Resolving supertype constraints");
    c.resolveSupertypeConstraints();
    //c.printConstraints();

    //System.err.println("Resolving unresolved type arguments");
    //c.resolveBounds();
    //c.printConstraints();

    if(c.unresolvedTypeArguments()) {
      TypeDecl S = assignConvertedType();
      if(S.isUnboxedPrimitive())
        S = S.boxed();
      TypeDecl R = decl.type();
      // TODO: replace all uses of type variables in R with their inferred types
      TypeDecl Rprime = R;
      if(R.isVoid())
        R = typeObject();
      c.convertibleFrom(S, R);
      // TODO: additional constraints

      c.resolveEqualityConstraints();
      c.resolveSupertypeConstraints();
      //c.resolveBounds();

      c.resolveSubtypeConstraints();
    }

    return c.typeArguments();
  }
  /**
   * @ast method 
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:23
   */
  protected SimpleSet potentiallyApplicable(Collection candidates) {
    SimpleSet potentiallyApplicable = SimpleSet.emptySet;
    // select potentially applicable methods
    for(Iterator iter = candidates.iterator(); iter.hasNext(); ) {
      MethodDecl decl = (MethodDecl)iter.next();
      if(potentiallyApplicable(decl) && accessible(decl)) {
        if(decl instanceof GenericMethodDecl) {
          decl = ((GenericMethodDecl)decl).lookupParMethodDecl(typeArguments(decl));
        }
        potentiallyApplicable = potentiallyApplicable.add(decl);
      }
    }
    return potentiallyApplicable;
  }
  /**
   * @ast method 
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:38
   */
  protected SimpleSet applicableBySubtyping(SimpleSet potentiallyApplicable) {
    SimpleSet maxSpecific = SimpleSet.emptySet;
    for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
      MethodDecl decl = (MethodDecl)iter.next();
      if(applicableBySubtyping(decl))
        maxSpecific = mostSpecific(maxSpecific, decl);
    }
    return maxSpecific;
  }
  /**
   * @ast method 
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:48
   */
  protected SimpleSet applicableByMethodInvocationConversion(SimpleSet potentiallyApplicable, SimpleSet maxSpecific) {
    if(maxSpecific.isEmpty()) {
      for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
        MethodDecl decl = (MethodDecl)iter.next();
        if(applicableByMethodInvocationConversion(decl))
          maxSpecific = mostSpecific(maxSpecific, decl);
      }
    }
    return maxSpecific;
  }
  /**
   * @ast method 
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:59
   */
  protected SimpleSet applicableVariableArity(SimpleSet potentiallyApplicable, SimpleSet maxSpecific) {
    if(maxSpecific.isEmpty()) {
      for(Iterator iter = potentiallyApplicable.iterator(); iter.hasNext(); ) {
        MethodDecl decl = (MethodDecl)iter.next();
        if(decl.isVariableArity() && applicableVariableArity(decl))
          maxSpecific = mostSpecific(maxSpecific, decl);
      }
    }
    return maxSpecific;
  }
  /**
   * @ast method 
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:140
   */
  private static SimpleSet mostSpecific(SimpleSet maxSpecific, MethodDecl decl) {
    if(maxSpecific.isEmpty())
      maxSpecific = maxSpecific.add(decl);
    else {
      if(decl.moreSpecificThan((MethodDecl)maxSpecific.iterator().next()))
        maxSpecific = SimpleSet.emptySet.add(decl);
      else if(!((MethodDecl)maxSpecific.iterator().next()).moreSpecificThan(decl))
        maxSpecific = maxSpecific.add(decl);
    }
    return maxSpecific;
  }
  /**
   * @ast method 
   * @declaredat java.ast:1
   */
  public MethodAccess() {
    super();

    setChild(new List(), 0);

  }
  /**
   * @ast method 
   * @declaredat java.ast:8
   */
  public MethodAccess(String p0, List<Expr> p1) {
    setID(p0);
    setChild(p1, 0);
  }
  /**
   * @ast method 
   * @declaredat java.ast:12
   */
  public MethodAccess(beaver.Symbol p0, List<Expr> p1) {
    setID(p0);
    setChild(p1, 0);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:19
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilvl internal
   * @ast method 
   * @declaredat java.ast:25
   */
  public boolean mayHaveRewrite() {
    return false;
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
   * Setter for ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:5
   */
  public void setArgList(List<Expr> list) {
    setChild(list, 0);
  }
  /**
   * @return number of children in ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:12
   */
  public int getNumArg() {
    return getArgList().getNumChild();
  }
  /**
   * Getter for child in list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Expr getArg(int i) {
    return (Expr)getArgList().getChild(i);
  }
  /**
   * Add element to list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:27
   */
  public void addArg(Expr node) {
    List<Expr> list = (parent == null || state == null) ? getArgListNoTransform() : getArgList();
    list.addChild(node);
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:34
   */
  public void addArgNoTransform(Expr node) {
    List<Expr> list = getArgListNoTransform();
    list.addChild(node);
  }
  /**
   * Setter for child in list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:42
   */
  public void setArg(Expr node, int i) {
    List<Expr> list = getArgList();
    list.setChild(node, i);
  }
  /**
   * Getter for Arg list.
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:50
   */
  public List<Expr> getArgs() {
    return getArgList();
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:56
   */
  public List<Expr> getArgsNoTransform() {
    return getArgListNoTransform();
  }
  /**
   * Getter for list ArgList
   * @apilvl high-level
   * @ast method 
   * @declaredat java.ast:63
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expr> getArgList() {
    List<Expr> list = (List<Expr>)getChild(0);
    list.getNumChild();
    return list;
  }
  /**
   * @apilvl low-level
   * @ast method 
   * @declaredat java.ast:72
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Expr> getArgListNoTransform() {
    return (List<Expr>)getChildNoTransform(0);
  }
  /**
   * @ast method 
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:11
   */
    protected SimpleSet maxSpecific(Collection candidates) {
    SimpleSet potentiallyApplicable = potentiallyApplicable(candidates);
    // first phase
    SimpleSet maxSpecific = applicableBySubtyping(potentiallyApplicable);
    // second phase
    maxSpecific = applicableByMethodInvocationConversion(potentiallyApplicable,
        maxSpecific);
    // third phase
    maxSpecific = applicableVariableArity(potentiallyApplicable, maxSpecific);
    return maxSpecific;
  }
  /**
   * @ast method 
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:331
   */
    public void typeCheck() {
    if(isQualified() && decl().isAbstract() && qualifier().isSuperAccess())
      error("may not access abstract methods in superclass");
    if(!decl().isVariableArity() || invokesVariableArityAsArray()) {
      for(int i = 0; i < decl().getNumParameter(); i++) {
        TypeDecl exprType = getArg(i).type();
        TypeDecl parmType = decl().getParameter(i).type();
        if(!exprType.methodInvocationConversionTo(parmType) && !exprType.isUnknown() && !parmType.isUnknown()) {
          error("#The type " + exprType.typeName() + " of expr " +
            getArg(i) + " is not compatible with the method parameter " +
            decl().getParameter(i));
        }
      }
    }
  }
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:112
   */
    public void createBCode(CodeGeneration gen) {
    MethodDecl decl = decl().erasedMethod();
    createLoadQualifier(gen);

    if(decl.type().isUnknown()) {
      System.err.println("Could not bind " + this);
      for (int i = 0; i < getNumArg(); ++i) {
        System.err.println("Argument " + getArg(i) + " is of type " + getArg(i).type().typeName());
        if(getArg(i).varDecl() != null) System.err.println(getArg(i).varDecl() + " in " + getArg(i).varDecl().hostType().typeName());
      }
      if(isQualified())
        System.err.println("Qualifier " + qualifier() + " is of type " + qualifier().type().typeName());
      throw new Error("Could not bind " + this);
    }
    if(decl.getNumParameter() != getNumArg()) {
      System.out.println(this + " does not have the same number of arguments as " + decl);
    }

    for (int i = 0; i < getNumArg(); ++i) {
      getArg(i).createBCode(gen);
      // the cast or boxing/unboxing operation must know the bound rather than the erased type
      getArg(i).type().emitCastTo(gen, decl().getParameter(i).type()); // MethodInvocationConversion
    }
    if(!decl.isStatic() && isQualified() && prevExpr().isSuperAccess()) {
      if(!hostType().instanceOf(prevExpr().type()))
        decl().createSuperAccessor(superAccessorTarget()).emitInvokeMethod(gen, superAccessorTarget());
      else
        decl.emitInvokeSpecialMethod(gen, methodQualifierType());
    }
    else if(!decl().isStatic()&&isQualified()&&prevExpr().isSeedAccess()){
         if(!hostType().instanceOf(prevExpr().type()))
         decl().createSeedAccessor(seedAccessorTarget()).emitInvokeMethod(gen, seedAccessorTarget());
         else
         decl().emitInvokeSpecialMethod(gen, methodQualifierType(),false);
	 }
    else if(!decl().isStatic()&&isQualified()&&prevExpr().isPreviousAccess()){
       if(!hostType().instanceOf(prevExpr().type()))
       decl().createPreviousAccessor(previousAccessorTarget()).emitInvokeMethod(gen, previousAccessorTarget());
       else 
       decl().emitInvokeSpecialMethod(gen, methodQualifierType(),true);
	 }
    else if(decl().isPrivate()&&hostType().hasMethod(name()))
	  {
	     decl().emitInvokeSpecialMethod(gen,methodQualifierType());	    
	   }
	     else if(hostType().isCompoundSubClass(methodQualifierType()))
	      {
	      if(hostType().existsSuperClass()&&hostType().isShadowClassDecl())
	      {
	        		ShadowClassDecl shadow=(ShadowClassDecl)hostType();
	        		ClassDecl classdecl=shadow.seedClass();
	        	  decl().emitInvokeMethod(gen,classdecl);
	       }
	    }
    else
      decl.emitInvokeMethod(gen, methodQualifierType());

    if(decl.type() != decl().type())
      gen.emitCheckCast(decl().type());
  }
  /**
   * @ast method 
   * @aspect GenericsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\GenericsCodegen.jrag:173
   */
    protected TypeDecl refined_GenericsCodegen_MethodAccess_methodQualifierType() {
    TypeDecl typeDecl = refined_InnerClasses_MethodAccess_methodQualifierType();
    if(typeDecl == null)
     return null;
    if(!typeDecl.isShadowClassDecl()&&!typeDecl.isGranuleDecl()){
    typeDecl = typeDecl.erasure();
    }
    MethodDecl m = decl().sourceMethodDecl();  
    Collection methods = typeDecl.memberMethods(m.name());    
    if(!methods.contains(decl()) && !methods.contains(m))
      return m.hostType();
    if(typeDecl.isShadowClassDecl()||typeDecl.isGranuleDecl())
    return typeDecl;
    return typeDecl.erasure();
  }
  /**
   * @ast method 
   * @aspect VariableArityParametersCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\VariableArityParametersCodegen.jrag:16
   */
    public void transformation() {
    if(decl().isVariableArity() && !invokesVariableArityAsArray()) {
      // arguments to normal parameters
      List list = new List();
      for(int i = 0; i < decl().getNumParameter() - 1; i++)
        list.add(getArg(i).fullCopy());
      // arguments to variable arity parameters
      List last = new List();
      for(int i = decl().getNumParameter() - 1; i < getNumArg(); i++)
        last.add(getArg(i).fullCopy());
      // build an array holding arguments
      Access typeAccess = decl().lastParameter().type().elementType().createQualifiedAccess();
      for(int i = 0; i < decl().lastParameter().type().dimension(); i++)
        typeAccess = new ArrayTypeAccess(typeAccess);
      list.add(new ArrayCreationExpr(typeAccess, new Opt(new ArrayInit(last))));
      // replace argument list with augemented argument list
      setArgList(list);
    }
    refined_Transformations_MethodAccess_transformation();
  }
  /**
   * @ast method 
   * @aspect StaticImportsCodegen
   * @declaredat D:\zhh\JastAddJ\Java1.5Backend\StaticImportsCodegen.jrag:18
   */
    protected TypeDecl methodQualifierType() {
    TypeDecl typeDecl = refined_GenericsCodegen_MethodAccess_methodQualifierType();
    if(typeDecl != null)
      return typeDecl;
    return decl().hostType();
  }
  /**
   * @ast method 
   * @aspect TypeAnalysis
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeAnalysis.jrag:292
   */
  private TypeDecl refined_TypeAnalysis_MethodAccess_type()
{ return decl().type(); }
  protected java.util.Map computeDAbefore_int_Variable_values;
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:414
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean computeDAbefore(int i, Variable v) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(Integer.valueOf(i));
    _parameters.add(v);
    if(computeDAbefore_int_Variable_values == null) computeDAbefore_int_Variable_values = new java.util.HashMap(4);
    if(computeDAbefore_int_Variable_values.containsKey(_parameters)) {
      return ((Boolean)computeDAbefore_int_Variable_values.get(_parameters)).booleanValue();
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    boolean computeDAbefore_int_Variable_value = computeDAbefore_compute(i, v);
if(isFinal && num == state().boundariesCrossed) computeDAbefore_int_Variable_values.put(_parameters, Boolean.valueOf(computeDAbefore_int_Variable_value));
    return computeDAbefore_int_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean computeDAbefore_compute(int i, Variable v) {  return i == 0 ? isDAbefore(v) : getArg(i-1).isDAafter(v);  }
  /**
   * @attribute syn
   * @aspect DA
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:416
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isDAafter(Variable v) {
      ASTNode$State state = state();
    boolean isDAafter_Variable_value = isDAafter_compute(v);
    return isDAafter_Variable_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isDAafter_compute(Variable v) {  return getNumArg() == 0 ? isDAbefore(v) : getArg(getNumArg()-1).isDAafter(v);  }
  /**
   * @apilvl internal
   */
  protected boolean exceptionCollection_computed = false;
  /**
   * @apilvl internal
   */
  protected Collection exceptionCollection_value;
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:51
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Collection exceptionCollection() {
    if(exceptionCollection_computed) {
      return exceptionCollection_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    exceptionCollection_value = exceptionCollection_compute();
if(isFinal && num == state().boundariesCrossed) exceptionCollection_computed = true;
    return exceptionCollection_value;
  }
  /**
   * @apilvl internal
   */
  private Collection exceptionCollection_compute() {
    //System.out.println("Computing exceptionCollection for " + name());
    HashSet set = new HashSet();
    Iterator iter = decls().iterator();
    if(!iter.hasNext())
      return set;

    MethodDecl m = (MethodDecl)iter.next();
    //System.out.println("Processing first found method " + m.signature() + " in " + m.hostType().fullName());

    for(int i = 0; i < m.getNumException(); i++) {
      TypeDecl exceptionType = m.getException(i).type();
      set.add(exceptionType);
    }
    while(iter.hasNext()) {
      HashSet first = new HashSet();
      first.addAll(set);
      HashSet second = new HashSet();
      m = (MethodDecl)iter.next();
      //System.out.println("Processing the next method " + m.signature() + " in " + m.hostType().fullName());
      for(int i = 0; i < m.getNumException(); i++) {
        TypeDecl exceptionType = m.getException(i).type();
        second.add(exceptionType);
      }
      set = new HashSet();
      for(Iterator i1 = first.iterator(); i1.hasNext(); ) {
        TypeDecl firstType = (TypeDecl)i1.next(); 
        for(Iterator i2 = second.iterator(); i2.hasNext(); ) {
          TypeDecl secondType = (TypeDecl)i2.next();
          if(firstType.instanceOf(secondType)) {
            set.add(firstType);
          }
          else if(secondType.instanceOf(firstType)) {
            set.add(secondType);
          }
        }
      }
    }
    return set;
  }
  /**
   * @attribute syn
   * @aspect ContextVar
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExternalVar.jrag:327
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int FitAccessCount() {
      ASTNode$State state = state();
    int FitAccessCount_value = FitAccessCount_compute();
    return FitAccessCount_value;
  }
  /**
   * @apilvl internal
   */
  private int FitAccessCount_compute() {
	 return super.FitAccessCount()+(getID().equals("fit")&&getNumArg()==0?1:0);
 }
  /**
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:118
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl singleCandidateDecl() {
      ASTNode$State state = state();
    MethodDecl singleCandidateDecl_value = singleCandidateDecl_compute();
    return singleCandidateDecl_value;
  }
  /**
   * @apilvl internal
   */
  private MethodDecl singleCandidateDecl_compute() {
    MethodDecl result = null;
    for(Iterator iter = lookupMethod(name()).iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(result == null)
        result = m;
      else if(m.getNumParameter() == getNumArg() && result.getNumParameter() != getNumArg())
        result = m;
    }
    return result;
  }
  /**
   * @apilvl internal
   */
  protected boolean decll_computed = false;
  /**
   * @apilvl internal
   */
  protected AuxiliaryMethodDecl decll_value;
  /**
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:130
   */
  @SuppressWarnings({"unchecked", "cast"})
  public AuxiliaryMethodDecl decll() {
    if(decll_computed) {
      return decll_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    decll_value = decll_compute();
if(isFinal && num == state().boundariesCrossed) decll_computed = true;
    return decll_value;
  }
  /**
   * @apilvl internal
   */
  private AuxiliaryMethodDecl decll_compute() {
 	    if(hasAuxiliaryMethodAccess()){
 	     if(auxiliarymethodaccess.containsKey(name())){
 	      AuxiliaryMethodDecl decll=(AuxiliaryMethodDecl)auxiliarymethodaccess.get(name());
 	      return decll;
 	     }
 	      else 
 	    	return null;
    }
 	    return null;
  }
  /**
   * @apilvl internal
   */
  protected boolean decls_computed = false;
  /**
   * @apilvl internal
   */
  protected SimpleSet decls_value;
  /**
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:150
   */
  @SuppressWarnings({"unchecked", "cast"})
  public SimpleSet decls() {
    if(decls_computed) {
      return decls_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    decls_value = decls_compute();
if(isFinal && num == state().boundariesCrossed) decls_computed = true;
    return decls_value;
  }
  /**
   * @apilvl internal
   */
  private SimpleSet decls_compute() {	
    SimpleSet maxSpecific = SimpleSet.emptySet;	
    for(Iterator iter = lookupMethod(name()).iterator(); iter.hasNext();) {
      MethodDecl decl = (MethodDecl)iter.next();	  
      if(applicable(decl) && accessible(decl)){ 
        if(maxSpecific.isEmpty()){
          maxSpecific = maxSpecific.add(decl);      
        }
        else {        	
          if(decl.moreSpecificThan((MethodDecl)maxSpecific.iterator().next()))
            maxSpecific=SimpleSet.emptySet.add(decl);
          else if(!((MethodDecl)maxSpecific.iterator().next()).moreSpecificThan(decl))
            maxSpecific = maxSpecific.add(decl);
        }    
      }  
    }
    if(isQualified() ? qualifier().staticContextQualifier() : inStaticContext()){
      maxSpecific = removeInstanceMethods(maxSpecific); 
    }
    return maxSpecific;
  }
  /**
   * @apilvl internal
   */
  protected boolean decl_computed = false;
  /**
   * @apilvl internal
   */
  protected MethodDecl decl_value;
  /**
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:189
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl decl() {
    if(decl_computed) {
      return decl_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    decl_value = decl_compute();
if(isFinal && num == state().boundariesCrossed) decl_computed = true;
    return decl_value;
  }
  /**
   * @apilvl internal
   */
  private MethodDecl decl_compute() {
	SimpleSet decls = decls();
    if(decls.size() == 1)
      return (MethodDecl)decls.iterator().next();

    // 8.4.6.4 - only return the first method in case of multply inherited abstract methods
    boolean allAbstract = true;
    for(Iterator iter = decls.iterator(); iter.hasNext() && allAbstract; ) {
      MethodDecl m = (MethodDecl)iter.next();
	  if(name().equals("valueOf"))
	  System.out.println("da hua hua ti shi "+m + !m.isAbstract() + !m.hostType().isObject()+ decls.size());
      if(!m.isAbstract() && !m.hostType().isObject())
        allAbstract = false;
      }
    if(decls.size() > 1 && allAbstract)
      return (MethodDecl)decls.iterator().next();
    return unknownMethod();
  }
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:267
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean accessible(MethodDecl m) {
      ASTNode$State state = state();
    boolean accessible_MethodDecl_value = accessible_compute(m);
    return accessible_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean accessible_compute(MethodDecl m) {
    if(!isQualified())
      return true;
    if(!m.accessibleFrom(hostType()))
      return false;
    // the method is not accessible if the type is not accessible
    if(!qualifier().type().accessibleFrom(hostType()))
      return false;
    // 6.6.2.1 -  include qualifier type for protected access
    if(m.isProtected() && !m.hostPackage().equals(hostPackage()) && !m.isStatic() && !qualifier().isSuperAccess()) {
      TypeDecl C = m.hostType();
      TypeDecl S = hostType().subclassWithinBody(C);
      TypeDecl Q = qualifier().type();
      if(S == null || !Q.instanceOf(S))
        return false;
    }
    return true;
  }
  /**
   * @apilvl internal
   */
  protected boolean isFilterClass_computed = false;
  /**
   * @apilvl internal
   */
  protected boolean isFilterClass_value;
  /* rewrite MethodAccess{
    when(isTransformMethod())
	to Expr {
	     String methodname=new String("invokeAgent");
	     TypeAccess ta= new TypeAccess("InvokeAgent");
	     List list=new List();		 
		 int len=getNumArg();      
              
		 for(int i = 0; i < len; i++) {     		 
		 list.add(getArg(i).fullCopy());
		 };   
			
		Access typeAccess=new TypeAccess("Object");
        
		List<Expr> l =new List<Expr>();	
        ClassInstanceExpr cse=new ClassInstanceExpr(typeAccess,new List<Expr>(), new Opt<TypeDecl>());  
		l.add(cse);

        StringLiteral para1=new StringLiteral(getID());
        l.add(para1);			 
             
	    StringLiteral para2=new StringLiteral(decl().signature());
		l.add(para2);	
	    
		typeAccess=new ArrayTypeAccess(typeAccess);
	    if(len==0){		
        l.add(new ArrayCreationExpr(typeAccess,new Opt(new ArrayInit(list))));			 
		}
	    else {		 	     
		l.add(new ArrayCreationExpr(typeAccess,new Opt(new ArrayInit(list))));
        }			 

	    String methodReturnType=getReturnType();
	    if(methodReturnType.equals("void")){
			 methodname=new String("invokeVoidAgent");
        }	 
	   
	    MethodAccess m=new MethodAccess(methodname,l);
		
	   ParseName pn= new ParseName(methodname);
	   AbstractDot d = new AbstractDot(ta,pn);
	   d.replaceLast(m);		
			 
	   Expr p;
	   if(!methodReturnType.equals("void")){
		   p=new CastExpr(new TypeAccess(methodReturnType),(Expr)d);
        }
        else{
		   p=(Expr)d;
		}
        return p;
    } 
  }* @attribute syn
   * @aspect MethodTransform
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\MethodTransform.jrag:516
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isFilterClass() {
    if(isFilterClass_computed) {
      return isFilterClass_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    isFilterClass_value = isFilterClass_compute();
if(isFinal && num == state().boundariesCrossed) isFilterClass_computed = true;
    return isFilterClass_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isFilterClass_compute() {
	  String qname=hostType().fullName();
	  if(qname!=null && !"".equals(qname)){
    	if(qname.startsWith("granulej.")|| qname.startsWith("java.") || qname.startsWith("org.") || qname.startsWith("javax."))
    	return true;
       }
	   return false;
	}
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\NameCheck.jrag:60
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean validArgs() {
      ASTNode$State state = state();
    boolean validArgs_value = validArgs_compute();
    return validArgs_value;
  }
  /**
   * @apilvl internal
   */
  private boolean validArgs_compute() {
    for(int i = 0; i < getNumArg(); i++)
      if(getArg(i).type().isUnknown())
        return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrint
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\PrettyPrint.jadd:933
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
   * @aspect Names
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\QualifiedNames.jrag:18
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
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ResolveAmbiguousNames.jrag:19
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean isMethodAccess() {
      ASTNode$State state = state();
    boolean isMethodAccess_value = isMethodAccess_compute();
    return isMethodAccess_value;
  }
  /**
   * @apilvl internal
   */
  private boolean isMethodAccess_compute() {  return true;  }
  /**
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:116
   */
  @SuppressWarnings({"unchecked", "cast"})
  public NameType predNameType() {
      ASTNode$State state = state();
    NameType predNameType_value = predNameType_compute();
    return predNameType_value;
  }
  /**
   * @apilvl internal
   */
  private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }
  /**
   * @attribute syn
   * @aspect Generics
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\Generics.jrag:12
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl type() {
    if(type_computed) {
      return type_value;
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    type_value = type_compute();
if(isFinal && num == state().boundariesCrossed) type_computed = true;
    return type_value;
  }
  /**
   * @apilvl internal
   */
  private TypeDecl type_compute() {
    if(getNumArg() == 0 && name().equals("getClass") && decl().hostType().isObject()) {
      TypeDecl bound = isQualified() ? qualifier().type() : hostType();
      ArrayList args = new ArrayList();
      args.add(bound.erasure().asWildcardExtends());
      return ((GenericClassDecl)lookupType("java.lang", "Class")).lookupParTypeDecl(args);
    }
    else
      return refined_TypeAnalysis_MethodAccess_type();
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat D:\zhh\JastAddJ\Java1.4Backend\InnerClasses.jrag:535
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean requiresAccessor() {
      ASTNode$State state = state();
    boolean requiresAccessor_value = requiresAccessor_compute();
    return requiresAccessor_value;
  }
  /**
   * @apilvl internal
   */
  private boolean requiresAccessor_compute() {
    MethodDecl m = decl();
    if(m.isPrivate() && m.hostType() != hostType())
      return true;
    if(m.isProtected() && !m.hostPackage().equals(hostPackage()) && !hostType().hasMethod(m.name()))
    return true;
    return false;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:181
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean applicableBySubtyping(MethodDecl m) {
      ASTNode$State state = state();
    boolean applicableBySubtyping_MethodDecl_value = applicableBySubtyping_compute(m);
    return applicableBySubtyping_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean applicableBySubtyping_compute(MethodDecl m) {
    if(m.getNumParameter() != getNumArg())
      return false;
    for(int i = 0; i < m.getNumParameter(); i++)
      if(!getArg(i).type().instanceOf(m.getParameter(i).type()))
        return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:201
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean applicableByMethodInvocationConversion(MethodDecl m) {
      ASTNode$State state = state();
    boolean applicableByMethodInvocationConversion_MethodDecl_value = applicableByMethodInvocationConversion_compute(m);
    return applicableByMethodInvocationConversion_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean applicableByMethodInvocationConversion_compute(MethodDecl m) {
    if(m.getNumParameter() != getNumArg())
      return false;
    for(int i = 0; i < m.getNumParameter(); i++)
      if(!getArg(i).type().methodInvocationConversionTo(m.getParameter(i).type()))
        return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:221
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean applicableVariableArity(MethodDecl m) {
      ASTNode$State state = state();
    boolean applicableVariableArity_MethodDecl_value = applicableVariableArity_compute(m);
    return applicableVariableArity_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean applicableVariableArity_compute(MethodDecl m) {
    for(int i = 0; i < m.getNumParameter() - 1; i++)
      if(!getArg(i).type().methodInvocationConversionTo(m.getParameter(i).type()))
        return false;
    for(int i = m.getNumParameter() - 1; i < getNumArg(); i++)
      if(!getArg(i).type().methodInvocationConversionTo(m.lastParameter().type().componentType()))
        return false;
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:262
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean potentiallyApplicable(MethodDecl m) {
      ASTNode$State state = state();
    boolean potentiallyApplicable_MethodDecl_value = potentiallyApplicable_compute(m);
    return potentiallyApplicable_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private boolean potentiallyApplicable_compute(MethodDecl m) {
    if(!m.name().equals(name()))
      return false;
    if(!m.accessibleFrom(hostType()))
      return false;
    if(m.isVariableArity() && !(arity() >= m.arity()-1))
      return false;
    if(!m.isVariableArity() && !(m.arity() == arity()))
      return false;
    if(m instanceof GenericMethodDecl) {
      GenericMethodDecl gm = (GenericMethodDecl)m;
      ArrayList list = typeArguments(m);
      if(list.size() != 0) {
        if(gm.getNumTypeParameter() != list.size())
          return false;
        for(int i = 0; i < gm.getNumTypeParameter(); i++)
          if(!((TypeDecl)list.get(i)).subtype(gm.original().getTypeParameter(i)))
            return false;
      }
    }
    return true;
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:285
   */
  @SuppressWarnings({"unchecked", "cast"})
  public int arity() {
      ASTNode$State state = state();
    int arity_value = arity_compute();
    return arity_value;
  }
  /**
   * @apilvl internal
   */
  private int arity_compute() {  return getNumArg();  }
  protected java.util.Map typeArguments_MethodDecl_values;
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\MethodSignature.jrag:287
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ArrayList typeArguments(MethodDecl m) {
    Object _parameters = m;
    if(typeArguments_MethodDecl_values == null) typeArguments_MethodDecl_values = new java.util.HashMap(4);
    if(typeArguments_MethodDecl_values.containsKey(_parameters)) {
      return (ArrayList)typeArguments_MethodDecl_values.get(_parameters);
    }
      ASTNode$State state = state();
  int num = state.boundariesCrossed;
  boolean isFinal = this.is$Final();
    ArrayList typeArguments_MethodDecl_value = typeArguments_compute(m);
if(isFinal && num == state().boundariesCrossed) typeArguments_MethodDecl_values.put(_parameters, typeArguments_MethodDecl_value);
    return typeArguments_MethodDecl_value;
  }
  /**
   * @apilvl internal
   */
  private ArrayList typeArguments_compute(MethodDecl m) {
    ArrayList typeArguments = new ArrayList();
    if(m instanceof GenericMethodDecl) {
      GenericMethodDecl g = (GenericMethodDecl)m;
      Collection arguments = computeConstraints(g);
      if(arguments.isEmpty())
        return typeArguments;
      int i = 0;
      for(Iterator iter = arguments.iterator(); iter.hasNext(); i++) {
        TypeDecl typeDecl = (TypeDecl)iter.next();
        if(typeDecl == null) {
          TypeVariable v = g.original().getTypeParameter(i);
          if(v.getNumTypeBound() == 0)
            typeDecl = typeObject();
          else if(v.getNumTypeBound() == 1)
            typeDecl = v.getTypeBound(0).type();
          else
            typeDecl = v.lubType();
        }
        typeArguments.add(typeDecl);
      }
    }
    return typeArguments;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\VariableArityParameters.jrag:40
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean invokesVariableArityAsArray() {
      ASTNode$State state = state();
    boolean invokesVariableArityAsArray_value = invokesVariableArityAsArray_compute();
    return invokesVariableArityAsArray_value;
  }
  /**
   * @apilvl internal
   */
  private boolean invokesVariableArityAsArray_compute() {
    if(!decl().isVariableArity())
      return false;
    if(arity() != decl().arity())
      return false;
    return getArg(getNumArg()-1).type().methodInvocationConversionTo(decl().lastParameter().type());
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\ExceptionHandling.jrag:29
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean handlesException(TypeDecl exceptionType) {
      ASTNode$State state = state();
    boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
    return handlesException_TypeDecl_value;
  }
  /**
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:15
   */
  @SuppressWarnings({"unchecked", "cast"})
  public MethodDecl unknownMethod() {
      ASTNode$State state = state();
    MethodDecl unknownMethod_value = getParent().Define_MethodDecl_unknownMethod(this, null);
    return unknownMethod_value;
  }
  /**
   * @attribute inh
   * @aspect TypeHierarchyCheck
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:159
   */
  @SuppressWarnings({"unchecked", "cast"})
  public boolean inExplicitConstructorInvocation() {
      ASTNode$State state = state();
    boolean inExplicitConstructorInvocation_value = getParent().Define_boolean_inExplicitConstructorInvocation(this, null);
    return inExplicitConstructorInvocation_value;
  }
  /**
   * @attribute inh
   * @aspect GenericMethodsInference
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericMethodsInference.jrag:43
   */
  @SuppressWarnings({"unchecked", "cast"})
  public TypeDecl typeObject() {
      ASTNode$State state = state();
    TypeDecl typeObject_value = getParent().Define_TypeDecl_typeObject(this, null);
    return typeObject_value;
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\DefiniteAssignment.jrag:413
   * @apilvl internal
   */
  public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
    if(caller == getArgListNoTransform()) {
      int i = caller.getIndexOfChild(child);
      return computeDAbefore(i, v);
    }
    return getParent().Define_boolean_isDAbefore(this, caller, v);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupMethod.jrag:30
   * @apilvl internal
   */
  public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
    if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return unqualifiedScope().lookupMethod(name);
    }
    return getParent().Define_Collection_lookupMethod(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:90
   * @apilvl internal
   */
  public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
    if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return unqualifiedScope().hasPackage(packageName);
    }
    return getParent().Define_boolean_hasPackage(this, caller, packageName);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupType.jrag:177
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
    if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return unqualifiedScope().lookupType(name);
    }
    return getParent().Define_SimpleSet_lookupType(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\LookupVariable.jrag:187
   * @apilvl internal
   */
  public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
    if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return unqualifiedScope().lookupVariable(name);
    }
    return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\SyntacticClassification.jrag:124
   * @apilvl internal
   */
  public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
    if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return NameType.EXPRESSION_NAME;
    }
    return getParent().Define_NameType_nameType(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.4Frontend\TypeHierarchyCheck.jrag:17
   * @apilvl internal
   */
  public String Define_String_methodHost(ASTNode caller, ASTNode child) {
    if(true) {
      int childIndex = this.getIndexOfChild(caller);
      return unqualifiedScope().methodHost();
    }
    return getParent().Define_String_methodHost(this, caller);
  }
  /**
   * @declaredat D:\zhh\JastAddJ\Java1.5Frontend\GenericMethodsInference.jrag:41
   * @apilvl internal
   */
  public TypeDecl Define_TypeDecl_assignConvertedType(ASTNode caller, ASTNode child) {
    if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
      return typeObject();
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
