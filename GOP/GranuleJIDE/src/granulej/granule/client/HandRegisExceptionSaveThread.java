package granulej.granule.client;

import granulej.util.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class HandRegisExceptionSaveThread implements Runnable {
	private List<Node> redoReigsList;

	public HandRegisExceptionSaveThread(List<Node> regisList) {
		this.redoReigsList = regisList;
	}

	public void run() {
		String filePath = Utility.getClientDir() + "update.xml";
		String redoFilePath = Utility.getClientDir() + "redoUpdate.xml";
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
			int waitRegisListSize = redoReigsList.size();
			Node updateNode = null;
			Node updateNodePath = null;
			String md5 = "";
			NamedNodeMap nAttr = null;
			for (int i = 0; i < waitRegisListSize; i = i + 2) {
				// copy nodeDoc
				updateNode = redoReigsList.get(i);
				nAttr = updateNode.getAttributes();
				md5 = nAttr.getNamedItem("md5").getNodeValue();
				if (!md5List.contains(md5)) {
					Element childNode = regisDoc.createElement(updateNode.getNodeName());
					childNode.setAttribute("md5", md5);
					//childNode.setAttribute("mmap", nAttr.getNamedItem("mmap").getNodeValue());
					childNode.setAttribute("raceId", nAttr.getNamedItem("raceId").getNodeValue());
					childNode.setAttribute("file", nAttr.getNamedItem("file").getNodeValue());
					childNode.setAttribute("location", nAttr.getNamedItem("location").getNodeValue());
					root.appendChild(childNode);
					for (Node tripNode = updateNode.getFirstChild(); tripNode != null; tripNode = tripNode.getNextSibling()) {
						Utility.updateNodeTrip(regisDoc, tripNode, childNode);
					}
					// copy pathDoc
					updateNodePath = redoReigsList.get(i + 1);
					Element childNodePath = regisDoc.createElement(updateNodePath.getNodeName());
					childNodePath.setAttribute("md5", updateNodePath.getAttributes().getNamedItem("md5").getNodeValue());
					root.appendChild(childNodePath);
					for (Node tripNodePath = updateNodePath.getFirstChild(); tripNodePath != null; tripNodePath = tripNodePath.getNextSibling()) {
						Utility.updateNodePathTrip(regisDoc, tripNodePath, childNodePath);
					}
				}
			}
			Utility.writeToXML(regisDoc, redoFilePath);
			redoReigsList = new ArrayList<Node>();

		} else {
			Utility.Copy(filePath, redoFilePath);
		}
	}
}
