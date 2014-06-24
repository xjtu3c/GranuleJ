package granulej.lang;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import granulej.util.FingerprintUtil;

import java.io.File;
import java.io.StringWriter;
import java.lang.InterruptedException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class GranuleTreeUpdate {
   
	private Document doc;
	private String file;	
	//信号量
	private boolean flag = true;
	
	private static int number;
	
	protected GranuleTreeUpdate(Document doc,String file)
	{
	  this.doc=doc;
	  this.file=file;
	}
	
	public void setTreeTopNode(){
		synchronized(this){
		   try{
			   while(flag){
				 wait();
			   }
			   flag = true?(flag=false):(flag=true);
			   updateTreeTopNode();
			   notify();		   
		      }catch(InterruptedException e){
			  e.printStackTrace();			   
		   }
		}		
	}
	
	public void getTreeTopNode()
	{
	   synchronized(this){
		  try{
			  while(!flag){
				  wait();
			  }
			  flag = true?(flag=false):(flag=true);
			  notify();
		  }catch(InterruptedException e)
		  {
			 e.printStackTrace();
		  }
	   }
	}
	
 
	public void updateTreeTopNode() {
		try {
			Element root= doc.getDocumentElement();        
			String md = setNewIndividualMd5Code(root);
			IndividualInfo.getInstance().setMd5(md);
			String fileName= setNewIndividualFileName(root, file);
			IndividualInfo.getInstance().setConfigfile(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	获取Dom的头结点
	public Element getTopNodeFromDom(Document doc) {
		Element root = doc.getDocumentElement();
		return root;
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

	public String extractNumFromConfigFile(String file,String suffix){
	    String number="";
	    if(file!=null && !file.equals("")){
	    	if (file.endsWith(".xml")) {
				int index = file.lastIndexOf(suffix);
				int len = suffix.length();
	            number=file.substring(index+len,file.indexOf(".xml"));
	    }
	    }
	    return number;
	}
	
	//设置文件名称
	public String setNewIndividualFileName(Element root, String file) {
		if (file != null && !"".equals(file)) {
			file = file.substring(file.lastIndexOf(File.separator) + 1);
			root.setAttribute("file", file);
		}
		return file;
	}	
}
