import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class HandRegisExceptionSaveThread implements Runnable {
	private List<Node> waitRegisList;

	public HandRegisExceptionSaveThread(List<Node> nodeList) {
		this.waitRegisList = nodeList;
	}

	public void run() {
		String redoFilePath = "src\\redoUpdate.xml";
		File redoFile = new File(redoFilePath);
		Document regisDoc = null;
		List<String> md5List = new ArrayList<String>();
		if (redoFile.exists()) {
			regisDoc = Utility.loadFromXML(redoFilePath);
		}
		if (regisDoc != null) {
			md5List = Utility.getMd5ListFromRegisDoc(regisDoc);
		}
		if (regisDoc != null) {
			Node root = regisDoc.getElementsByTagName("update_node_list").item(0);
			int waitRegisListSize = waitRegisList.size();
			Node updateNode = null;
			String md5 = "";
			NamedNodeMap attrMap = null;
			for (int i = 0; i < waitRegisListSize; i = i + 1) {
				// copy nodeDoc
				updateNode = waitRegisList.get(i);
				attrMap = updateNode.getAttributes();
				md5 = attrMap.getNamedItem("md5").getNodeValue();
				if (!md5List.contains(md5)) {
					Element childNode = regisDoc.createElement(updateNode.getNodeName());
					childNode.setAttribute("md5", md5);
					childNode.setAttribute("feature", attrMap.getNamedItem("feature").getNodeValue());
					childNode.setAttribute("ip", "202.117.15.192");
					childNode.setAttribute("location", "E:\\");
					root.appendChild(childNode);
					for (Node tripNode = updateNode.getFirstChild(); tripNode != null; tripNode = tripNode.getNextSibling()) {
						Utility.updateNodeTrip(regisDoc, tripNode, childNode);
					}
				}
			}
			Utility.writeToXML(regisDoc, redoFilePath);
			waitRegisList = new ArrayList<Node>();

		} else {
			try {
				Utility.writeNodeListToXML(waitRegisList, redoFilePath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
	}
}
