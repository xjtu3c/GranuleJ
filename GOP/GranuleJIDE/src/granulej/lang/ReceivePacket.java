package granulej.lang;

import granulej.util.Entry;
import granulej.util.LRUCache;
import granulej.util.xmlUtil;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ReceivePacket extends GranulePacket {
	private String simi_path;

	private static ReceivePacket rpacket = null;

	private Node simi_node = null;

	private Document simi_doc = null;
	
	private ArrayList<String> parents=new ArrayList<String>();
	
	private LRUCache<String,Document> cache;
	
	private int capacity=10;


	public ArrayList<String> getParents() {
		return parents;
	}

	public void setParents(ArrayList<String> parents) {
		this.parents = parents;
	}

	public static synchronized ReceivePacket getInstance() {
		if (rpacket == null)
			rpacket = new ReceivePacket();
		return rpacket;
	}
	
	private ReceivePacket(){
		  cache=new LRUCache<String,Document>(capacity);		
    }

	public String getSimilarPath() {
		return simi_path;
	}

	public void setSimilarPath(String simi_path) {
		this.simi_path = simi_path;
	}

	public String getSimilarGranuleFilePath() {
		String filename = getConfigFile();
		if (filename != null && !"".equals(filename)) {
			int index = filename.lastIndexOf(File.separator);
			filename = filename.substring(0, index > 0 ? index : 0);
		}
		return filename;
	}

	public String getSimilarGranuleName() {
		String gname = null;
		String conf = getConfigFile();
		Node simi_node = null;
		if (conf != null && !conf.equals("") && simi_path != null && !simi_path.equals("")) {
			simi_node = getSimilarGranuleNodeFromFile(conf, simi_path);
			if(simi_node!=null){
			gname = simi_node.getAttributes().getNamedItem("name").getNodeValue();
			}
		}
		return gname;
	}

	public Node getSimilarGranuleNodeFromFile(String configpath, String similarPath) {
		if(!new File(configpath).exists())
		return null;
		//先从缓存取		
		Entry<String, Document> entr=cache.getElement(configpath);
		if(entr==null){
		  Document similar_doc= xmlUtil.load(configpath);
		  simi_doc=similar_doc;
		  cache.addElement(configpath, similar_doc);		  
		}
		else 
		{
		  simi_doc=(Document)entr.getValue();
		}	
		    	
		Node returnGranule = simi_doc.getElementsByTagName("granule").item(0);
		String[] objectArray = similarPath.split(",");
		int arraySize = objectArray.length;
		String className = "";
		String contextName = "";
		String[] cla_con = null;
		Node node = null;
		parents.clear();
		for (int i = 0; i < arraySize; i++) {
			cla_con = objectArray[i].split(":");
			className = cla_con[0];
			if(cla_con.length>1)
				contextName = cla_con[1];
			for (node = returnGranule.getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String pgranule=node.getAttributes().getNamedItem("name").getNodeValue();
					if(!parents.contains(pgranule)){
						parents.add(pgranule);
					}
					if (node.getAttributes().getNamedItem("class").getNodeValue().equals(className)
							&& node.getAttributes().getNamedItem("context").getNodeValue().equals(contextName)) {
						returnGranule = node;
						break;
					}
				}
			}
		}
		simi_node = returnGranule;
		return returnGranule;
	}

	public Node getSimilarGranuleNode() {
		return simi_node;
	}

	public void setSimilarGranuleNode(Node node) {
		simi_node = node;
	}

	public Document getSimilarFileDom() {
		return simi_doc;
	}

	public void setSimilarFileDom(Document doc) {
		simi_doc = doc;
	}
}
