package granulej.bo;

import granulej.error.CompilerException;
import granulej.gui.action.JavaCompiler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import AST.BodyDecl;
import AST.BytecodeParser;
import AST.ClassDecl;
import AST.CompilationUnit;
import AST.ContextVar;
import AST.ContextVarDeclaration;
import AST.FieldDecl;
import AST.FieldDeclaration;
import AST.Frontend;
import AST.GranuleDecl;
import AST.JavaParser;
import AST.MemberContextVarDecl;
import AST.MethodDecl;
import AST.ShadowClassDecl;
import AST.SimpleSet;
import AST.TypeAccess;
import AST.TypeDecl;
import AST.ContextCode;
import AST.ContextCodeDec;
import AST.ShadowClassDecl;
import AST.MemberShadowClassDecl;
import config.GUIConfig;

public class Display extends Frontend {

	private GUIConfig config;
	public static void main(String args[]) {
		//for (int i = 0; i < args.length; i++)
		//	System.out.println(args[i]);
		//Display display = new Display();
		//display.compile(args);
		//display.getMethods("");
		//display.getContext();
	}

	public Display(GUIConfig conf){
		config = conf;
	}
	/*public List<ContextVarDeclaration> getContext() {
		List<ContextVarDeclaration> clist = new LinkedList<ContextVarDeclaration>();
		List<String> nameList = new LinkedList<String>();
		for (Iterator iter = program.compilationUnitIterator(); iter.hasNext();) {
			CompilationUnit unit = (CompilationUnit) iter.next();
			if (!unit.fromSource())
				continue;
			for (int i = 0; i < unit.getNumTypeDecl(); i++) {
				TypeDecl decl = unit.getTypeDecl(i);
				if (!(decl instanceof GranuleDecl))
					continue;
				GranuleDecl gdecl = (GranuleDecl) decl;
				for (int j = 0; j < gdecl.getNumBodyDecl(); j++) {
					BodyDecl bdecl = gdecl.getBodyDecl(j);
					if (!(bdecl instanceof MemberContextVarDecl))
						continue;
					MemberContextVarDecl vdecl=(MemberContextVarDecl) bdecl;
					ContextVar cv=vdecl.getContextVar();
						if (cv instanceof ContextVarDeclaration) {
							ContextVarDeclaration cvd = (ContextVarDeclaration) cv;
							if(!nameList.contains(cvd.getID()))
								{
									clist.add(cvd);
									nameList.add(cvd.getID());
								}
						}
				}
			}
		}
		return clist;
	}*/
	
	public List<ContextVarDeclaration> getContext() {
	List<ContextVarDeclaration> clist = new LinkedList<ContextVarDeclaration>();
	List<String> nameList = new LinkedList<String>();
	HashMap map=program.contextVariableMap();
	Iterator iter = map.entrySet().iterator();
	while (iter.hasNext()) {
	    Map.Entry entry = (Map.Entry) iter.next();
	    SimpleSet val =(SimpleSet)entry.getValue();
	    for(Iterator it=val.iterator();it.hasNext();)
	    {
             Object element=it.next();
             if(element instanceof ContextVarDeclaration){
            	ContextVarDeclaration cval=(ContextVarDeclaration)element;
            	if(!clist.contains(cval))
            	  clist.add(cval);
             }             
	    }
	} 
	return clist;
}

	public List<Object> getMethods(String cname) {
		List<Object> flist = new LinkedList<Object>();
		for (Iterator iter = program.compilationUnitIterator(); iter.hasNext();) {
			CompilationUnit unit = (CompilationUnit) iter.next();
			if (!unit.fromSource())
				continue;
			for (int i = 0; i < unit.getNumTypeDecl(); i++) {
				TypeDecl decl = unit.getTypeDecl(i);
				if (decl instanceof ClassDecl) {
					ClassDecl cdecl = (ClassDecl) decl;
					if (!cname.equals(cdecl.getID()))
						continue;
					for (int j = 0; j < cdecl.getNumBodyDecl(); j++) {
						BodyDecl bdecl = cdecl.getBodyDecl(j);
						if ((bdecl instanceof FieldDecl)
								|| (bdecl instanceof FieldDeclaration)) {
							flist.add(0, bdecl);
						}
						if (bdecl instanceof MethodDecl) {
							MethodDecl mdecl = (MethodDecl) bdecl;
							if(mdecl.getID().equals("main")){
								config.setMainFile(cname+".java");
							}
							flist.add(mdecl);
						}
					}
				}
				if(decl instanceof ShadowClassDecl){
					ShadowClassDecl cdecl = (ShadowClassDecl) decl;
					if (!cname.equals(cdecl.name()))
						continue;
					for (int j = 0; j < cdecl.getNumBodyDecl(); j++) {
						BodyDecl bdecl = cdecl.getBodyDecl(j);
						if ((bdecl instanceof FieldDecl)
								|| (bdecl instanceof FieldDeclaration)) {
							flist.add(0, bdecl);
						}
						if (bdecl instanceof MethodDecl) {
							MethodDecl mdecl = (MethodDecl) bdecl;
							flist.add(mdecl);
						}
					}
				}
			}
		}
		return flist;
	}
	
    public List<GranuleDescriptor> getGranules(){
        List<GranuleDescriptor> gdlist = new LinkedList<GranuleDescriptor>();
    	HashMap<GranuleDecl,LinkedList<ShadowClassDecl>> hm=new HashMap<GranuleDecl,LinkedList<ShadowClassDecl>>();
		for(Iterator iter = program.compilationUnitIterator(); iter.hasNext();) {
			CompilationUnit unit = (CompilationUnit) iter.next();
			if (!unit.fromSource())
				continue;
			for(int i = 0; i < unit.getNumTypeDecl(); i++) {
				TypeDecl decl = unit.getTypeDecl(i);				
				if (decl instanceof GranuleDecl) {
    	          GranuleDecl gd=(GranuleDecl)decl;
    	          GranuleDescriptor gdp=new GranuleDescriptor(gd);
    	          for(int j=0;j<gd.getNumBodyDecl(); j++){
				    BodyDecl bdecl=gd.getBodyDecl(j);
				    if(bdecl instanceof MemberContextVarDecl)
				    {
				     ContextVar cv=((MemberContextVarDecl)bdecl).getContextVar();
				 	 if(cv instanceof ContextVarDeclaration)
				 	 gdp.getContextVars().add((ContextVarDeclaration)cv);
				    }
				    else if(bdecl instanceof FieldDeclaration)
				    {
				      gdp.getFieldVars().add((FieldDeclaration)bdecl);
				    }   
    	          }
    	          if(!gdlist.contains(gdp))
    	          gdlist.add(gdp);
				}
				else if(decl instanceof ShadowClassDecl){
				   ShadowClassDecl cdecl = (ShadowClassDecl) decl;
				   GranuleDecl gd=(GranuleDecl)(cdecl.getGranuleAccess().type());
				   LinkedList<ShadowClassDecl> list;
				   if(!hm.containsKey(gd)){
					list=new LinkedList<ShadowClassDecl>();
					list.add(cdecl);
				   }
				   else 
				   {
					 list=(LinkedList<ShadowClassDecl>)hm.get(gd);
					 if(!list.contains(cdecl))
					 list.add(cdecl);					
				   }
				   hm.put(gd,list);
				}
			   }
			for(Iterator it=gdlist.iterator();it.hasNext();)
			{
				 GranuleDescriptor gdc=(GranuleDescriptor)it.next();
				 if(hm.containsKey(gdc.getGranuleDecl())){
				 List<ShadowClassDecl> ls=hm.get(gdc.getGranuleDecl());
				 gdc.setShadowClasses(ls);
				 }
			}			
		}		    	
        return gdlist;
    }

	public boolean compile(String args[]) throws CompilerException{
		return process(args, new BytecodeParser(), new JavaParser() {
			parser.JavaParser parser = new parser.JavaParser();

			public CompilationUnit parse(java.io.InputStream is, String fileName)
			throws java.io.IOException, beaver.Parser.Exception {
			return parser.parse(is, fileName);
			}
			});
	}
}
