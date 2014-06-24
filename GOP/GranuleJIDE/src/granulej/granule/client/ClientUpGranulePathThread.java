package granulej.granule.client;

import granulej.util.Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ClientUpGranulePathThread implements Runnable {
	private static HashMap<String, String> granulePathHash;

	public ClientUpGranulePathThread(HashMap<String, String> granPathHash) {
		this.granulePathHash = granPathHash;
	}

	public void run() {
		// 更新到粒hash
		String filePath = Utility.getClientDir() + "granule_path.xml";
		new File(filePath).delete();
		if (!new File(filePath).exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = doc.createElement("g-path");
				doc.appendChild(root);
				Iterator<Entry<String, String>> iter = granulePathHash.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, String> entry = (Entry<String, String>) iter.next();
					String granuleName = (String) entry.getKey();
					String path = (String) entry.getValue();
					Element elem = doc.createElement("granule");
					elem.setAttribute("name", granuleName);
					elem.setAttribute("path", path);
					root.appendChild(elem);
				}
				// 内容一次写入文件
				synchronized (this) {
					Utility.writeToXML(doc, filePath);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
