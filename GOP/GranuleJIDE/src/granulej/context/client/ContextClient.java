package granulej.context.client;

import granulej.gui.Console;
import granulej.util.Utility;
import gui.constant.ContextConstant;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ContextClient {

	private static ContextClient cc;

	private Document contextDoc;

	private static HashMap<String, String> contextHash = new HashMap<String, String>();

	private static HashMap<String, String> contextTypeHash = new HashMap<String, String>();

	private static List<String> portList = new ArrayList<String>();

	private static Map<String, List<String>> listenMap = new HashMap<String, List<String>>();

	private static int count = 0;

	public static ContextClient getInstance() {
		if (cc == null) {
			cc = new ContextClient();
		}
		return cc;
	}

	public void init() {
		String dirPath = Utility.getClientDir();
		File fileDir = new File(dirPath);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		loadContextDoc(dirPath);
	}

	public void loadContextDoc(String dirPath) {
		String filePath = dirPath + "context.xml";
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
		String contextStr = loadContextDocToHash();
		if (contextStr != null && !"".equals(contextStr)) {
			loadServerSideContext(contextStr);
		}
	}

	public String loadContextDocToHash() {
		NodeList nodeList = contextDoc.getElementsByTagName("context");
		Node cNode = null;
		String cName = "";
		String cValue = "";
		String cType = "";
		NamedNodeMap attrMap = null;
		String contextStr = "";
		int size = 0;
		if (nodeList != null) {
			size = nodeList.getLength();
		}
		for (int i = 0; i < size; i++) {
			cNode = nodeList.item(i);
			attrMap = cNode.getAttributes();
			cName = attrMap.getNamedItem("name").getNodeValue();
			cType = attrMap.getNamedItem("type").getNodeValue();
			if (ContextConstant.CONTEXT_MODIFIER_PUB.equals(cType)) {
				if (contextStr.isEmpty()) {
					contextStr = contextStr + cName;
				} else {
					contextStr = contextStr + ":" + cName;
				}
			}
			cValue = attrMap.getNamedItem("value").getNodeValue();
			updateContextHash(cName, cValue);
			updateContextTypeHash(cName, cType);
		}
		return contextStr;
	}

	// 从上下文服务器获取上下文初始信息
	public void loadServerSideContext(String contextStr) {
		try {
			String feedback = "";
			boolean flag = true;
			Socket sock = new Socket(ContextConstant.CONTEXT_SERVER_IP, ContextConstant.CONTEXT_SERVER_PORT);
			OutputStream socketOutput = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketOutput);
			socketWriter.println(ContextConstant.CONTEXT_CLIENT_INT);
			socketWriter.println(contextStr);
			socketWriter.println(ContextConstant.CONTEXT_CLIENT_PORT);
			socketWriter.flush();
			while (flag) {
				feedback = Utility.getAllInputFromSocket(sock.getInputStream());
				if (feedback != null && !feedback.isEmpty()) {
					flag = false;
				}
			}
			// 上下文client初始化时，成功从服务器获取更新信息
			if (!ContextConstant.CONTEXT_GET_FAIL.equals(feedback.trim())) {
				updateContextHash(feedback);
			}
			// 失败则不做任何处理
		} catch (Exception e) {
			Console.getInstance().append("The individual is already existed!");
		}
	}

	public String getContext(String contextStr, String port) {
		List<String> newContextList = new ArrayList<String>();
		List<String> oldContextList = listenMap.get(port);
		String[] contextArr = contextStr.split(":");
		int arrSize = contextArr.length;
		String cName = "";
		String cValue = "";
		String modifier = "";
		String feedback = "";
		String sFeedback = "";
		boolean flag = true;
		String contextFromServer = "";
		for (int i = 0; i < arrSize; i++) {
			cName = contextArr[i];
			modifier = getContextTypeHash(cName);
			if (modifier == null) {
				// 如果本地没有发布过该上下文，则一定是远程个体发布的
				modifier = ContextConstant.CONTEXT_MODIFIER_PUB;
			}
			if (oldContextList == null || !oldContextList.contains(cName)) {
				newContextList.add(cName);
			}
			cValue = getContextHash(cName);
			if (cValue != null) {
				if (feedback.isEmpty()) {
					feedback = feedback + cName + ":" + cValue;
				} else {
					feedback = feedback + ";" + cName + ":" + cValue;
				}
			} else if (cValue == null && ContextConstant.CONTEXT_MODIFIER_PUB.equals(modifier)) {
				if (contextFromServer.isEmpty()) {
					contextFromServer = contextFromServer + cName;
				} else {
					contextFromServer = contextFromServer + ":" + cName;
				}
			} else {
				// 本地查找私有上下文失败
				flag = false;
				break;
			}
		}
		if (flag && contextFromServer != null && !contextFromServer.isEmpty()) {
			// 从服务器获取上下文值
			sFeedback = requestContextFromServer(contextFromServer);
			if (sFeedback != null && !sFeedback.isEmpty()) {
				if (feedback.isEmpty()) {
					feedback = sFeedback;
				} else {
					feedback = feedback + ";" + sFeedback;
				}
			} else {
				// 查找上下文失败
				flag = false;
			}
		}
		// 更新观察者观察的内容
		if (port != null && newContextList.size() > 0) {
			addTolistenMap(port, newContextList);
		}
		if (!flag) {
			feedback = ContextConstant.CONTEXT_GET_FAIL;
		}
		return feedback;
	}

	// addContexts的执行阶段
	public String getInitContext(String contextStr, String port) {
		String[] contextArr = contextStr.split(";");
		int arrSize = contextArr.length;
		String[] context = new String[3];
		String cName = "";
		String cValue = "";
		String oldValue = "";
		String modifier = "";
		String contextToServer = "";
		// 返回给程序的初始值
		String feedback = "";
		// String newContextStr = "";
		List<String> newContextList = new ArrayList<String>();
		List<String> oldContextList = listenMap.get(port);
		// 增加一个观察者
		addToPortList(port);
		for (int i = 0; i < arrSize; i++) {
			context = contextArr[i].split(":");
			cName = context[0];
			cValue = context[1];
			modifier = context[2];

			oldValue = getContextHash(cName);
			// 1）可以通过本地获取上下文信息
			if (oldValue != null) {
				if (!feedback.isEmpty()) {
					feedback = feedback + ";" + cName + ":" + oldValue;
				} else {
					feedback = feedback + cName + ":" + oldValue;
				}
			} else {
				// 下列上下文本地不存在
				if (!feedback.isEmpty()) {
					feedback = feedback + ";" + cName + ":" + cValue;
				} else {
					feedback = feedback + cName + ":" + cValue;
				}
			}

			// 更新port监听的内容
			if (oldContextList == null || !oldContextList.contains(cName)) {
				newContextList.add(cName);
			}

			// 更新到服务器的内容
			if (ContextConstant.CONTEXT_MODIFIER_PUB.equals(modifier)) {
				if (!"".equals(contextToServer)) {
					contextToServer = contextToServer + ";" + cName + ":" + cValue;
				} else {
					contextToServer = contextToServer + cName + ":" + cValue;
				}
			}
			// 本地更新
			if (oldValue == null) {
				updateContextHash(cName, cValue);
				updateContextTypeHash(cName, modifier);
			}
		}
		// 记录观察者观察的内容
		addTolistenMap(port, newContextList);
		// 更新到本地context文件
		synchronized (this) {
			count++;
			if (count == ContextConstant.MAX_CONTEXT_UPDATE) {
				new Thread(new UpdateToContextFileThread(contextHash, contextTypeHash)).start();
				count = 0;
			}
		}
		// 更新到服务器
		if (!contextToServer.isEmpty()) {
			new Thread(new UpdateToServerThread(contextToServer, ContextConstant.CONTEXT_REG)).start();
		}
		return feedback;
	}

	// 1）上下文client初始化时，从服务器获取更新信息 2）程序运行时向服务器请求上下文
	public void updateContextHash(String contextStr) {
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
			updateContextTypeHash(cName, ContextConstant.CONTEXT_MODIFIER_PUB);
		}
		
		synchronized (this) {
			count++;
			if (count == ContextConstant.MAX_CONTEXT_UPDATE) {
				new Thread(new UpdateToContextFileThread(contextHash, contextTypeHash)).start();
				count = 0;
			}
		}
	}

	// 程序执行过程中更新上下文信息
	public void updateContexts(String contextStr, String contextFrom) {
		String[] contextArr = contextStr.split(";");
		int arrSize = contextArr.length;
		String[] context = null;
		String cName = "";
		String cValue = "";
		String modifier = "";
		String contextToServer = "";
		for (int i = 0; i < arrSize; i++) {
			context = contextArr[i].split(":");
			cName = context[0];
			cValue = context[1];
			modifier = contextTypeHash.get(cName);
			if (ContextConstant.CONTEXT_MODIFIER_PUB.equals(modifier)) {
				if (!"".equals(contextToServer)) {
					contextToServer = contextToServer + ";" + cName + ":" + cValue;
				} else {
					contextToServer = contextToServer + cName + ":" + cValue;
				}
			}
			updateContextHash(cName, cValue);
			updateContextTypeHash(cName, modifier);
		}
		// 更新到活动程序之中
		updateToListenPort(contextArr);
		// 更新到本地上下文文件
		synchronized (this) {
			count++;
			if (count == ContextConstant.MAX_CONTEXT_UPDATE) {
				new Thread(new UpdateToContextFileThread(contextHash, contextTypeHash)).start();
				count = 0;
			}
		}
		// 更新到集中管理服务器
		if (!ContextConstant.CONTEXT_UPDATE_FROM_SERVER.equals(contextFrom)) {
			new Thread(new UpdateToServerThread(contextToServer, ContextConstant.CONTEXT_UPDATE)).start();
		}
	}

	// 更新最新的内容到程序端
	public void updateToListenPort(String[] contextArr) {
		int portSize = portList.size();
		String contextStr = null;
		String port = "";
		for (int i = 0; i < portSize; i++) {
			port = portList.get(i);
			contextStr = getUpdateContextForPort(contextArr, listenMap.get(port));
			new Thread(new UpdateToProgramThread(port, contextStr)).start();
		}
	}

	public String getUpdateContextForPort(String[] contextArr, List<String> contextList) {
		String contextStr = null;
		String[] context = null;
		String cName = "";
		String cValue = "";
		int arrSize = contextArr.length;
		for (int i = 0; i < arrSize; i++) {
			context = contextArr[i].split(":");
			cName = context[0];
			cValue = context[1];
			if (contextList.contains(cName)) {
				if (contextStr != null) {
					contextStr = contextStr + ";" + cName + ":" + cValue;
				} else {
					contextStr = contextStr + cName + ":" + cValue;
				}

			}
		}
		return contextStr;
	}

	public String requestContextFromServer(String newContextStr) {
		String sFeedback = "";
		try {
			boolean flag = true;
			Socket sock = Utility.createContextConnectionToServer(ContextConstant.CONTEXT_SERVER_IP, ContextConstant.CONTEXT_SERVER_PORT);
			OutputStream socketOutput = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketOutput);
			socketWriter.println(ContextConstant.CONTEXT_GET);
			socketWriter.println(newContextStr);
			socketWriter.flush();

			while (flag) {
				sFeedback = Utility.getAllInputFromSocket(sock.getInputStream());
				if (!sFeedback.isEmpty()) {
					flag = false;
				}
			}
			// 程序本地请求上下文不到时，从服务器获取更新信息
			if (ContextConstant.CONTEXT_GET_FAIL.equals(sFeedback.trim())) {
				sFeedback = null;
			}
			if (sFeedback != null && !sFeedback.isEmpty()) {
				updateContextHash(sFeedback);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sFeedback;
	}

	public void updateContextHash(String key, String value) {
		synchronized (contextHash) {
			contextHash.put(key, value);
		}
	}

	public void updateContextTypeHash(String key, String type) {
		synchronized (contextTypeHash) {
			contextTypeHash.put(key, type);
		}
	}

	public String getContextHash(String key) {
		synchronized (contextHash) {
			return contextHash.get(key);
		}
	}

	public String getContextTypeHash(String key) {
		synchronized (contextTypeHash) {
			return contextTypeHash.get(key);
		}
	}

	public void addToPortList(String port) {
		synchronized (portList) {
			if (!portList.contains(port)) {
				portList.add(port);
			}
		}
	}

	public void addTolistenMap(String port, List<String> cList) {
		List<String> contextList = null;
		synchronized (listenMap) {
			contextList = listenMap.get(port);
			if (contextList != null) {
				contextList.addAll(cList);
				listenMap.put(port, contextList);
			} else {
				listenMap.put(port, cList);
			}
		}
	}

	public void cleanListenMap(String port) {
		removeFromPortList(port);
		removeFromlistenMap(port);
	}

	public void removeFromPortList(String port) {
		synchronized (portList) {
			portList.remove(port);
		}
	}

	public void removeFromlistenMap(String port) {
		synchronized (listenMap) {
			listenMap.remove(port);
		}
	}
}
