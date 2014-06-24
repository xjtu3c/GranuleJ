package granulej.granule.client;

import granulej.util.Utility;

import java.io.File;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ResourceManager {

	public static void UpdateSimilarPathFile(HashMap<String, String> pathHash) {
		ClientUpGranulePathThread ugpt = new ClientUpGranulePathThread(pathHash);
		new Thread(ugpt).start();
	}

	public static void UpdateGranuleHashFile(HashMap<String, List<String>> similarGranuleHash) {
		ClientUpPathIndividualThread ught = new ClientUpPathIndividualThread(similarGranuleHash);
		new Thread(ught).start();
	}

	public static void updateLocalXML(List<Node> waitRegisList) {
		ClientRegisToLocalThread grlt = new ClientRegisToLocalThread(waitRegisList);
		new Thread(grlt).start();
	}

	public static Document loadLocalConfig(String clientDir) {
		String filePath = clientDir + "local.xml";
		try {
			if (!new File(filePath).exists()) {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = doc.createElement("local");
				root.setAttribute("name", InetAddress.getLocalHost().getHostAddress());
				doc.appendChild(root);
				Utility.writeToXML(doc, filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Utility.loadFromXML(filePath);
	}
	
	public static Document loadSimilarPath(String clientDir) {
		String filePath = clientDir + "granule_path.xml";
		if (!new File(filePath).exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = doc.createElement("g-path");
				doc.appendChild(root);
				Utility.writeToXML(doc, filePath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return Utility.loadFromXML(filePath);
	}
	
	public static Document loadSimilarGranule(String clientDir) {
		String filePath = clientDir + "path_granuleList.xml";
		if (!new File(filePath).exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = doc.createElement("g-hash");
				doc.appendChild(root);
				Utility.writeToXML(doc, filePath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return Utility.loadFromXML(filePath);
	}

}
