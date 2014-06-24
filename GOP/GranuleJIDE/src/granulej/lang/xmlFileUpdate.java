package granulej.lang;

import granulej.util.FingerprintUtil;
import granulej.util.Utility;
import granulej.util.xmlUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlFileUpdate implements Runnable {

	private Document doc;

	private String gfname;

	private String type;

	private char granuleSeprator = '_';
	
	private int version;
	
	private CountDownLatch downLatch;  
	
	private CountDownLatch start;
	
	public xmlFileUpdate(Document doc, String type)
    {
	    this.doc=doc;
	    this.type=type;		
	}
	public xmlFileUpdate(CountDownLatch start,CountDownLatch downLatch,Document doc, String gfname, String type, int version) {
		this.start=start;
		this.downLatch = downLatch;  
		this.doc = doc;
		this.gfname = gfname;
		this.type = type;
		this.version=version;
	}
	
	public xmlFileUpdate(Document doc, String gfname, String type) {
		this.doc = doc;
		this.gfname = gfname;
		this.type = type;
	}
	public xmlFileUpdate(String gfname, String type) {
		this.gfname = gfname;
		this.type = type;
	}	
	public void run() {
		String fname = null;
		if (type.equals("gTree")) {
			try{
			start.await();
			fname = getFileName(gfname, "TestGranuleTree");
			updateTopNodeAttributes(doc, fname);
			saveXml(doc, fname);
			} catch (InterruptedException e){
			   e.printStackTrace();
			} finally{
			this.downLatch.countDown();
			}
		} else if (type.equals("cTree")) {
			String ctname=getClassTreeName(gfname);
			doc = xmlUtil.load(ctname);
			updateClassTreeXml(doc, gfname);
			fname = getClassFileName(ctname, "class_tree");
			saveXml(doc, fname);
		} else if (type.equals("path")) {
			try{
		    start.await();
			String pname=null;
			pname=getPathFileName(gfname,"GranuleSimiPath");
			generatorNewGranulePathXmlFile(doc,pname);
			}catch(InterruptedException e){
			  e.printStackTrace();
			} finally{
			this.downLatch.countDown();
			}
		}
	}

	//xml文件存储,无indent,统一格式是无缩进的
	public void saveXml(Document doc, String filename) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			pw.flush();
			pw.close();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	        
	
    //抽取TestGranuleTree的目录
	public StringBuffer extractDirFromFile(String filename)
	{
		StringBuffer sb = new StringBuffer();
		filename = filename.replace("/","\\");
		if (filename.endsWith(".xml")) {
			int index = filename.lastIndexOf(File.separator);
			sb.append(filename.substring(0, index+1));
		}
		return sb;	
	}
	
	//替换后的文件进行编号TestGranuleTree
	public String getFileName(String filename, String Suffix) {
		StringBuffer sb = new StringBuffer();
		if (filename.endsWith(".xml")) {
			int index = filename.lastIndexOf(Suffix);
			int len = Suffix.length();
			sb.append(filename.substring(0, index + len));
			sb.append(version);
			sb.append(".xml");
		}
		return sb.toString();
	}
	
	 //替换后的文件进行编号GranuleSimiPath
	public String getPathFileName(String filename,String Suffix) {
		    StringBuffer sb=extractDirFromFile(filename);
			sb.append(Suffix);
			sb.append(version);		
			sb.append(".xml");
		    return sb.toString();
	}
    
	//获取ClassTree的文件
	public String getClassTreeName(String filename) {
		 StringBuffer sb=extractDirFromFile(filename);
		  sb.append("class_tree");		
		  sb.append(".xml");
		  return sb.toString();
    }
	//获取最终class-tree的文件
	public String getClassFileName(String filename, String Suffix)
	{
		StringBuffer sb=extractDirFromFile(filename);
		  sb.append(Suffix);
		  sb.append(1);		
		  sb.append(".xml");
		  return sb.toString();		
	}
	
	//	更新新个体的信息	
	public void updateTopNodeAttributes(Document doc, String file) {
		  Element root = getTopNodeFromDom(doc);		
		   try {
				IndividualInfo.getInstance().setConfigfile(file);
				setNewIndividualFileName(root, file);
				setNewIndividualPoset(root);
				//setMapShadowClassFile(root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public void generatorNewGranulePathXmlFile(Document tree_doc, String file) {
		  String md = IndividualInfo.getInstance().getMd5();		
		try {
			Document pathDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = pathDoc.createElement("g-path");
			pathDoc.appendChild(root);
			root.setAttribute("md5", md);
			NodeList top = getTopNodeFromDom(tree_doc).getFirstChild().getChildNodes();
			treeTravelsPath(top, root, pathDoc, "");
			saveXml(pathDoc,file);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	//获取Dom的头结点
	public Element getTopNodeFromDom(Document doc) {
		Element root = doc.getDocumentElement();
		return root;
	}

	//设置文件名称
	public String setNewIndividualFileName(Element root, String file) {
		if (file != null && !"".equals(file)) {
			file = file.substring(file.lastIndexOf(File.separator) + 1);
			root.setAttribute("file", file);
		}
		return file;
	}

	//得到新个体的MD5
	public String setNewIndividualMd5Code(Element root) {
		Element sonNode = (Element) root.getFirstChild();
		String mdToString = FingerprintUtil.StringFingerprintWithSHA512(NodetoString(sonNode));
		root.setAttribute("md5", mdToString);
		return mdToString;
	}
	
	public String NodetoString(Element node) {
		Transformer transformer = null;
		String result = null;
		if (node == null)
			throw new IllegalArgumentException();
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		if (transformer != null) {
			try {
				StringWriter sw = new StringWriter();
				transformer.transform(new DOMSource(node), new StreamResult(sw));
				result = sw.toString();
				return result;
			} catch (TransformerException te) {
				throw new RuntimeException(te.getMessage());
			}
		}
		return result;
	}	

	//得到新个体的poset集
	public void setNewIndividualPoset(Element root) {
		StringBuffer poset = new StringBuffer();
		Node first = root.getFirstChild();
		String p_context = null;
		traversalTree(first, poset, p_context);
		root.setAttribute("poset", poset.toString());
	}

	public void traversalTree(Node node, StringBuffer poset, String p_context) {
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String context = node.getAttributes().getNamedItem("context").getNodeValue();
				if (p_context != null)
					poset.append("<" + p_context + "," + context + ">");
				p_context = context;
			}
			for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
				traversalTree(child, poset, p_context);
			}
		}
	}	

   //更新class-tree文件
	public void updateClassTreeXml(Document doc,String file){
	    try{
			Element top = doc.getDocumentElement();
			NodeList classList=top.getElementsByTagName("class");
			Node classNode=null;
			NamedNodeMap cAttrs=null;
			String cname="";
			for(int i=0,n=classList.getLength();i<n;i++){
				  classNode=classList.item(i);
				  if(classNode.getNodeType()==Node.ELEMENT_NODE){			  
					 cAttrs=classNode.getAttributes();
					 cname = cAttrs.getNamedItem("name").getNodeValue();
					 if(cname.equals("Object"))
					 continue;
					 ArrayList<GranuleNode> gset=GranuleTree.getInstance().getSameBaseGranules(cname);
				     int len=gset.size();
				     if(len>0){
				       while (cAttrs.getLength() > 0) {
			              cAttrs.removeNamedItem(cAttrs.item(0).getNodeName());
			            } 
						Element classElem=(Element)classNode;
						classElem.setAttribute("name",cname);
						int m=0;
						for(int j=0;j<len;j++){
						classElem.setAttribute("granule"+m, ((GranuleNode)gset.get(j)).getGranuleName());
						m++;
						}
						m=0;
						ArrayList<String> sset=getSameBaseShadowClasses(cname);
 					    for(int w=0,y=sset.size();w<y;w++){
					    classElem.setAttribute("shadow_class"+m, (String)(sset.get(w)));  
					    m++;
					    }					
				     }				 
				  }
			}				        
	      } catch(Exception e){
		     e.printStackTrace();
		 }
	 }

	//找到影子类
	private ArrayList<String> getSameBaseShadowClasses(String cname)
	{
		 HashMap<String, ArrayList<String>> c_shadows = XmlParser.getClassToShadowMap();
		 if(c_shadows.containsKey(cname))
		 return (ArrayList<String>)c_shadows.get(cname);
		 else
		 return new ArrayList<String>();
	}

	public String getClassNameFromShadowClass(String s_name) {
		int index = s_name.lastIndexOf(granuleSeprator);
		s_name = s_name.substring(index > 0 ? index + 1 : 0);
		return s_name;
	}

	public String getGranuleNameFromShadowClass(String s_name) {
		int index = s_name.lastIndexOf(granuleSeprator);
		s_name = s_name.substring(0, index > 0 ? index : 0);
		return s_name;
	}

	public void treeTravelsPath(NodeList nodelist, Element parentNode, Document doc, String path) {
		int size = nodelist.getLength();
		if (size == 0)
			return;
		String gname = null;
		String cname = null;
		String context = null;
		String p_app = null;
		String temp = null;
		for (int i = 0; i < size; i++) {
			temp = path;
			if (nodelist.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element curElement = (Element) nodelist.item(i);
				NamedNodeMap attr = curElement.getAttributes();
				gname = attr.getNamedItem("name").getNodeValue();
				cname = attr.getNamedItem("class").getNodeValue();
				context = attr.getNamedItem("context").getNodeValue();
				p_app = cname + ":" + context;
				if (!path.equals("")) {
					path = path + "," + p_app;
				} else {
					path = path + p_app;
				}
				Element nowNode = doc.createElement("path");
				nowNode.setAttribute("value", path);
				nowNode.setAttribute("name", gname);
				parentNode.appendChild(nowNode);
				treeTravelsPath(curElement.getChildNodes(), parentNode, doc, path);
			}
			path = temp;
		}
	}	

}
