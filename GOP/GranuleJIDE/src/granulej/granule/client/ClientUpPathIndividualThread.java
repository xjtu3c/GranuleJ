package granulej.granule.client;

import granulej.util.Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ClientUpPathIndividualThread implements Runnable {
	private static HashMap<String, List<String>> similarGranuleHash;

	public ClientUpPathIndividualThread(HashMap<String, List<String>> simiGranuleHash) {
		this.similarGranuleHash = simiGranuleHash;
	}

	public void run() {
		// 更新到粒hash
		String filePath = Utility.getClientDir() + "path_granuleList.xml";
		new File(filePath).delete();
		if (!new File(filePath).exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = doc.createElement("g-hash");
				doc.appendChild(root);
				Iterator<Entry<String, List<String>>> iter = similarGranuleHash.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, List<String>> entry = (Entry<String, List<String>>) iter.next();
					String path = (String) entry.getKey();
					List<String> md5List = (List<String>) entry.getValue();
					Element elem = doc.createElement("hash");
					elem.setAttribute("path", path);
					elem.setAttribute("md5_list", Utility.listToString(md5List));
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
