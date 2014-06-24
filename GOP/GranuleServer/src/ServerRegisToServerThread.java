import java.io.File;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ServerRegisToServerThread implements Runnable {
	private List<Node> waitRegisList;
	private String ipAddr;

	public ServerRegisToServerThread(List<Node> regisList, String ip) {
		this.waitRegisList = regisList;
		this.ipAddr = ip;

	}

	public void run() {
		String individualFilePath = Utility.getServerDir() + "server.xml";
		Document serverDoc = Utility.loadFromXML(individualFilePath);
		Node localRoot = serverDoc.getElementsByTagName("global").item(0);

		int waitRegisListSize = waitRegisList.size();
		Node regisDocRoot = null;
//		Node regisDocNode = null;
		Element nodeElem = null;
		Element raceElem = null;
		NamedNodeMap attrMap = null;
		String loc_client = "";
		String loc_server = "";
		String file = "";
		String md5 = "";
		//String mmap = "";
		Node raceRoot = null;
		NodeList raceList = null;
		int raceListSize = 0;
		Node raceNode = null;
		for (int i = 0; i < waitRegisListSize; i = i + 2) {
			regisDocRoot = waitRegisList.get(i);
			String raceId = regisDocRoot.getAttributes().getNamedItem("raceId").getNodeValue();
			raceList = serverDoc.getElementsByTagName("race");
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
				raceElem = serverDoc.createElement("race");
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
			loc_client = attrMap.getNamedItem("location").getNodeValue();
			file = attrMap.getNamedItem("file").getNodeValue();
			md5 = attrMap.getNamedItem("md5").getNodeValue();
			loc_server = Utility.getServerDir() + ipAddr + File.separator + md5 + File.separator;
			//mmap = attrMap.getNamedItem("mmap").getNodeValue();

			nodeElem = serverDoc.createElement("gvm");
			nodeElem.setAttribute("md5", md5);
			nodeElem.setAttribute("loc_client", loc_client);
			nodeElem.setAttribute("loc_server", loc_server);
			nodeElem.setAttribute("file", file);
			nodeElem.setAttribute("ip", ipAddr);
			//nodeElem.setAttribute("mmap", mmap);
			raceRoot.appendChild(nodeElem);
//			Utility.addNewNodeTrip(serverDoc, regisDocNode, nodeElem);
		}
		synchronized (this) {
			Utility.writeToXML(serverDoc, individualFilePath);
		}
	}
}
