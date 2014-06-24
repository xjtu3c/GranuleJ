package granulej.bo;

import java.util.LinkedList;
import java.util.List;

import AST.ContextCode;
import AST.ContextVarDeclaration;
import AST.FieldDeclaration;
import AST.GranuleDecl;
import AST.ShadowClassDecl;
import AST.TypeAccess;

public class GranuleDescriptor {
	private GranuleDecl granuleDecl;

	private List<ContextVarDeclaration> convars;

	private List<FieldDeclaration> fvars;

	private List<ShadowClassDecl> shadowclasses;

	public GranuleDescriptor(GranuleDecl gdecl) {
		granuleDecl = gdecl;
		convars = new LinkedList<ContextVarDeclaration>();
		fvars = new LinkedList<FieldDeclaration>();
		shadowclasses = new LinkedList<ShadowClassDecl>();
	}

	public GranuleDecl getGranuleDecl() {
		return granuleDecl;
	}

	public void setGranuleDecl(GranuleDecl gd) {
		this.granuleDecl = gd;
	}

	public void setContextVars(List<ContextVarDeclaration> convars) {
		this.convars = convars;
	}

	public List<ContextVarDeclaration> getContextVars() {
		return convars;
	}

	public void setFieldVars(List<FieldDeclaration> fvars) {
		this.fvars = fvars;
	}

	public List<FieldDeclaration> getFieldVars() {
		return fvars;
	}

	public void setShadowClasses(List<ShadowClassDecl> shadowclasses) {
		this.shadowclasses = shadowclasses;
	}

	public List<ShadowClassDecl> getShadowClasses() {
		return shadowclasses;
	}

	public String granuleToString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getGranuleDecl().getID());
		sb.append("(");
		sb.append(((TypeAccess) (getGranuleDecl().getRootClassAccess())).getID());
		sb.append(")");
		return sb.toString();
	}

}
