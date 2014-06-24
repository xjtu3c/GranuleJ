package granulej.lang;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import granulej.util.xmlUtil;

public class GranuleUpdate{
	
	public HashMap<String, ArrayList<String>> update(String path){
      HashMap<String, ArrayList<String>> tree=new HashMap<String, ArrayList<String>>();
	  Document doc=xmlUtil.load(path+"TestGranuleTree.xml");
	  Element root=doc.getDocumentElement();
	  traversalGranuleTree(root.getFirstChild(),tree);	
	  return tree;
	}
	
	public void traversalGranuleTree(Node node,HashMap<String, ArrayList<String>> tree) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
					String name = node.getNodeName();
					if (name.equals("granule")) {
						NamedNodeMap nodeMap = node.getAttributes();
						String gName = nodeMap.getNamedItem("name").getNodeValue();
						String cName="";
						String conName="";
						String key="";
						if (!gName.equals("g0")) {
						  cName=nodeMap.getNamedItem("class").getNodeValue();
						  conName=nodeMap.getNamedItem("context").getNodeValue();
						  key=gName+"("+ cName+ ")"+conName;
						  ArrayList<String> list=new ArrayList<String>();			
						  int length = nodeMap.getLength();
						  for (int i = 0; i < length; i++) {
								String nodeName = nodeMap.item(i).getNodeName();
								if (nodeName.startsWith("shadow_class")){
									 list.add(nodeMap.item(i).getNodeValue());
						     }
                           }
						   tree.put(key, list);						
						 }
					  }
				}
				
			for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
				traversalGranuleTree(child,tree);
			}
		}
 }
