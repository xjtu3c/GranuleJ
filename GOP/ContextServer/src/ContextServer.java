import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ContextServer {
	private Document contextDoc;
	private static ContextServer cs;
	private static HashMap<String, String> contextHash = new HashMap<String, String>();
	// 上下文客户端ip列表
	private static List<String> clientList = new ArrayList<String>();
	// 上下文客户端ip-监听port列表
	private static Map<String, String> portMap = new HashMap<String, String>();
	// 客户端上下文-ipList列表
	private static Map<String, List<String>> contextMap = new HashMap<String, List<String>>();
	// 用于控制延迟批量处理，
	private static int count = 0;

	public static ContextServer getInstance() {
		if (cs == null) {
			cs = new ContextServer();
		}
		return cs;
	}

	private ContextServer() {

	}

	public void init() {
		String serverDir = Utility.getServerDir();
		File fileDir = new File(serverDir);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		loadContextDoc(serverDir);
	}

	public void loadContextDoc(String serverDir) {
		String filePath = Utility.getServerDir() + "context.xml";
		try {
			if (!new File(filePath).exists()) {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = doc.createElement("context-list");
				doc.appendChild(root);
				Utility.writeToXML(doc, filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		contextDoc = Utility.loadFromXML(filePath);
		loadContextDocToHash();
	}

	public void loadContextDocToHash() {
		NodeList nodeList = contextDoc.getElementsByTagName("context");
		Node cNode = null;
		NamedNodeMap attrMap = null;
		String cName = "";
		String cValue = "";
		int listSize = nodeList.getLength();
		for (int i = 0; i < listSize; i++) {
			cNode = nodeList.item(i);
			attrMap = cNode.getAttributes();
			cName = attrMap.getNamedItem("name").getNodeValue();
			cValue = attrMap.getNamedItem("value").getNodeValue();
			updateContextHash(cName, cValue);
		}
	}

	public void addContexts(String contextStr) {
		String[] contextArr = contextStr.split(";");
		int arrSize = contextArr.length;
		String[] context = null;
		String cName = "";
		String cValue = "";
		for (int i = 0; i < arrSize; i++) {
			context = contextArr[i].split(":");
			cName = context[0];
			cValue = context[1];
			updateContextHash(cName, cValue);
		}
		//批量、延迟写入到到文件
		synchronized (this) {
			count++;
			if (count == ConstantVariable.MAX_CONTEXT_UPDATE) {
				new Thread(new UpdateToContextFileThread(contextHash)).start();
				count = 0;
			}
		}

	}

	public void updateContexts(String contextStr) {
		Map<String, String> updateMap = new HashMap<String, String>();
		String contextUpdateStr = "";
		String[] contextArr = contextStr.split(";");
		int arrSize = contextArr.length;
		String[] context = null;
		String cName = "";
		String cValue = "";
		List<String> ipList = null;
		int ipListSize = 0;
		String ip = "";
		for (int i = 0; i < arrSize; i++) {
			context = contextArr[i].split(":");
			cName = context[0];
			cValue = context[1];
			ipList = contextMap.get(cName);
			if (ipList != null) {
				ipListSize = ipList.size();
			}
			// 将context以ip和contextStrs对的形式组织
			for (int j = 0; j < ipListSize; j++) {
				ip = ipList.get(j);
				contextUpdateStr = updateMap.get(ip);
				if (contextUpdateStr != null) {
					contextUpdateStr = contextUpdateStr + ";" + cName + ":" + cValue + ':' + ConstantVariable.CONTEXT_MODIFIER_PUB;
					updateMap.put(ip, contextUpdateStr);
				} else {
					updateMap.put(ip, cName + ":" + cValue + ':' + ConstantVariable.CONTEXT_MODIFIER_PUB);
				}

			}
			updateContextHash(cName, cValue);
		}
		new Thread(new UpdateBrocastThread(updateMap, portMap)).start();
		synchronized (this) {
			count++;
			if (count == ConstantVariable.MAX_CONTEXT_UPDATE) {
				new Thread(new UpdateToContextFileThread(contextHash)).start();
				count = 0;
			}
		}
	}

	// 1）client初始化的时候请求上下文来初始化和更新本地信息 2）client端程序运行时，请求上下文信息
	public String getContext(String contextStr) {
		boolean flag = false;
		String[] contextArr = contextStr.split(":");
		String feedback = "";
		int arrSize = contextArr.length;
		String cName = "";
		String cValue = "";
		for (int i = 0; i < arrSize; i++) {
			cName = contextArr[i];
			cValue = getContextHash(cName);
			if (cValue == null) {
				flag = true;
				break;
			}
			if (!feedback.isEmpty()) {
				feedback = feedback + ";" + cName + ":" + cValue;
			} else {
				feedback = feedback + cName + ":" + cValue;
			}
		}
		if (flag) {
			feedback = ConstantVariable.CONTEXT_GET_FAIL;
		}
		return feedback;
	}

	// 客户端信息注册
	public void handleClientRegis(String contextStr, String port, String ip) {
		updateToClientList(ip);
		updateToPortMap(ip, port);
		batchUpdateToContextMap(ip, contextStr);

	}

	public void updateToClientList(String ip) {
		synchronized (clientList) {
			if (!clientList.contains(ip)) {
				clientList.add(ip);
			}
		}
	}

	public void updateToPortMap(String ip, String port) {
		synchronized (portMap) {
			portMap.put(ip, port);
		}
	}

	public void batchUpdateToContextMap(String ip, String contextStr) {
		String[] contextArr = contextStr.split(":");
		int arrSize = contextArr.length;
		String cName = "";
		List<String> ipList = null;
		for (int i = 0; i < arrSize; i++) {
			cName = contextArr[i];
			ipList = getFromContextMap(cName);
			if (ipList != null) {
				if (!ipList.contains(ip)) {
					ipList.add(ip);
				}
				updateToContextMap(cName, ipList);
			} else {
				ipList = new ArrayList<String>();
				ipList.add(ip);
				updateToContextMap(cName, ipList);
			}
		}
	}

	public void removeClient(String ip) {
		removeFromClientList(ip);
		removeFromPortMap(ip);
		removeFromContextMap(ip);

	}

	public void removeFromClientList(String ip) {
		synchronized (clientList) {
			clientList.remove(ip);
		}
	}

	public void removeFromPortMap(String ip) {
		synchronized (portMap) {
			portMap.remove(ip);
		}
	}

	public void removeFromContextMap(String ip) {
		Iterator<Entry<String, List<String>>> iter = contextMap.entrySet().iterator();
		String cName = "";
		List<String> ipList = new ArrayList<String>();
		Entry<String, List<String>> entry = null;
		while (iter.hasNext()) {
			entry = (Entry<String, List<String>>) iter.next();
			cName = (String) entry.getKey();
			ipList = entry.getValue();
			if (ipList.contains(ip)) {
				ipList.remove(ip);
			}
			updateToContextMap(cName, ipList);
		}
	}

	public void updateContextHash(String key, String value) {
		synchronized (contextHash) {
			contextHash.put(key, value);
		}
	}

	public String getContextHash(String key) {
		synchronized (contextHash) {
			return contextHash.get(key);
		}
	}

	public void updateToContextMap(String cName, List<String> ipList) {
		synchronized (contextMap) {
			contextMap.put(cName, ipList);
		}
	}

	public List<String> getFromContextMap(String cName) {
		synchronized (contextMap) {
			return contextMap.get(cName);
		}
	}
}
