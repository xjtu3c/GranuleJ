package granulej.granule.client;

import granulej.util.Utility;

import java.io.File;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ClientRegisToLocalThread implements Runnable {
	private List<Node> waitRegisList;

	public ClientRegisToLocalThread(List<Node> regisList) {
		this.waitRegisList = regisList;
	}

	public void run() {

		String individualFilePath = Utility.getClientDir() + "local.xml";
		Document localDoc = Utility.loadFromXML(individualFilePath);
		Node localRoot = localDoc.getElementsByTagName("local").item(0);

		int waitRegisListSize = waitRegisList.size();
		Node regisDocRoot = null;
//		Node regisDocNode = null;
		Element nodeElem = null;
		Element raceElem = null;
		NamedNodeMap attrMap = null;
		String location = "";
		String file = "";
		// String ip = "";
		String md5 = "";
		//String mmap = "";
		Node raceRoot = null;
		NodeList raceList = null;
		int raceListSize = 0;
		Node raceNode = null;
		for (int i = 0; i < waitRegisListSize; i = i + 2) {
			regisDocRoot = waitRegisList.get(i);
			String raceId = regisDocRoot.getAttributes().getNamedItem("raceId").getNodeValue();
			raceList = localDoc.getElementsByTagName("race");
			raceListSize = raceList.getLength();
			for (int j = 0; j < raceListSize; j++) {
				raceNode = raceList.item(j);
				if (raceNode.getAttributes().getNamedItem("id").getNodeValue().equals(raceId)) {
					raceRoot = raceNode;
					break;
				}
			}
			// 没有这个种群的个体存在
			if (raceRoot == null) {
				raceElem = localDoc.createElement("race");
				raceElem.setAttribute("id", raceId);
				localRoot.appendChild(raceElem);
				raceRoot = (Node) raceElem;
			}
//			regisDocNode = null;
//			for (Node node = regisDocRoot.getFirstChild(); node != null; node = node.getNextSibling()) {
//				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("granule")) {
//					regisDocNode = node;
//					break;
//				}
//			}
			// regisDocNode = regisDoc.getElementsByTagName("granule").item(0);
			attrMap = regisDocRoot.getAttributes();
			location = attrMap.getNamedItem("location").getNodeValue();
			file = attrMap.getNamedItem("file").getNodeValue();
			md5 = attrMap.getNamedItem("md5").getNodeValue();
			//mmap = attrMap.getNamedItem("mmap").getNodeValue();
			
			//=====此处是qhz添加的代码================
			if(!lookupNode(raceRoot,md5)){

			nodeElem = localDoc.createElement("gvm");
			nodeElem.setAttribute("location", location);
			nodeElem.setAttribute("file", file);
			nodeElem.setAttribute("md5", md5);
			//nodeElem.setAttribute("mmap", mmap);
			raceRoot.appendChild(nodeElem);
			}
//			Utility.addNewNodeTrip(localDoc, regisDocNode, nodeElem);
		}
		//System.out.println("granulej.granule.client : ClientRegisToLocalThread"+Thread.currentThread().getName()+": pid"+Thread.currentThread().getId()+"is running !");
		
		// 内容一次写入文件
		synchronized (this) {
			/*
			 * ceshi 阻塞在哪里发生
			 */
		//	System.out.println("本地同步!");
			
			Utility.writeToXML(localDoc, individualFilePath);
		//	System.out.println("同步 granulej.granule.client : ClientRegisToLocalThread"+Thread.currentThread().getName()+": pid"+Thread.currentThread().getId()+"is running !");
			
		}
		
		
	}
	
	private boolean lookupNode(Node root, String index)
	{
	  NodeList list=root.getChildNodes();
	  Node cnode=null;
	  for(int i=0,len=list.getLength();i<len;i++){
		  cnode = list.item(i);
		  if(cnode.getNodeType()==Node.ELEMENT_NODE){
			if(cnode.getAttributes().getNamedItem("md5").getNodeValue().equals(index)){
				return true;
			}			  
		  }  
	  }	
	  return false;	
	}	
}
