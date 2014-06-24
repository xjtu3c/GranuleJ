package granulej.context.client;

import granulej.util.Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UpdateToContextFileThread implements Runnable {

	private HashMap<String, String> contextHash;

	private HashMap<String, String> contextTypeHash;

	public UpdateToContextFileThread(HashMap<String, String> cHash, HashMap<String, String> cTypeHash) {
		this.contextHash = cHash;
		this.contextTypeHash = cTypeHash;
	}

	public void run() {
		// 更新到粒hash
		String filePath = Utility.getClientDir() + "context.xml";
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = doc.createElement("context-list");
			doc.appendChild(root);
			Iterator<Entry<String, String>> iter = contextHash.entrySet().iterator();
			String cName = "";
			String cValue = "";
			String cType = "";
			Entry<String, String> entry = null;
			while (iter.hasNext()) {
				entry = (Entry<String, String>) iter.next();
				cName = (String) entry.getKey();
				cValue = (String) entry.getValue();
				cType = contextTypeHash.get(cName);
				Element elem = doc.createElement("context");
				elem.setAttribute("name", cName);
				elem.setAttribute("type", cType);
				elem.setAttribute("value", cValue);
				root.appendChild(elem);
			}
			synchronized (this) {
				Utility.writeToXML(doc, filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
