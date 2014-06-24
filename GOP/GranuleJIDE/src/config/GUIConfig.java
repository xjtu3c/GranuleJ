package config;

import granulej.bean.FileStatus;
import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

public class GUIConfig {
	private String projectName = "";
	private String projectPath = "";
	private DefaultMutableTreeNode select = null;
	private Set<String> packages = new HashSet<String>();
	public Map<String, String> fileAndPath = new HashMap<String, String>();
	public Map<String, FileStatus> fileStatus = new HashMap<String, FileStatus>();
	private String gvmAddr = "";
	private String indAddr = "127.0.0.1";
	private String conAddr = "";
	private String mainFile = "";
	private String gvmPath = "/usr/local/jamvm/bin"; //本地
	
	public List<String> netMains = new ArrayList<String>();
	public List<String> netGVMIps = new ArrayList<String>();
	public List<String> netGVMPaths = new ArrayList<String>();
	
	private String classpath="";
	
	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public String getGvmPath() {
		return gvmPath;
	}

	public void setGvmPath(String gvmPath) {
		this.gvmPath = gvmPath;
	}

	public String getMainFile() {
		return mainFile;
	}

	public void setMainFile(String mainFile) {
		this.mainFile = mainFile;
	}

	public String getGvmAddr() {
		return gvmAddr;
	}

	public void setGvmAddr(String gvmAddr) {
		this.gvmAddr = gvmAddr;
	}

	public String getIndAddr() {
		return indAddr;
	}

	public void setIndAddr(String indAddr) {
		this.indAddr = indAddr;
	}

	public String getConAddr() {
		return conAddr;
	}

	public void setConAddr(String conAddr) {
		this.conAddr = conAddr;
	}
	
	public GUIConfig(){
	}
	
	public boolean AddPackage(String pack){
		return packages.add(pack);
	}
    	public boolean removePackage(String pack) {
		return packages.remove(pack);
    	}

	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectPath() {
		return projectPath;
	}
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	
	public String getProjectSrcPath()
	{
		return this.projectPath + File.separator+"src";
	}
	
	public String getProjectBinPath()
	{
		return this.projectPath+ File.separator +"bin";
	}
	
	public DefaultMutableTreeNode getSelect() {
		return select;
	}
	public void setSelect(DefaultMutableTreeNode select) {
		this.select = select;
	}
}
