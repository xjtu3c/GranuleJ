import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utility {

	public static Document loadFromXML(String filename) {
		try {
			File file = new File(filename);
			Document document = null;
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//			boolean flag = true;
//			while (flag) {
//				if (file.canRead()) {
//					flag = false;
					document = builder.parse(file);
//				}
//			}
			return document;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void writeToXML(Document doc, String path) {
		try {
			File file = new File(path);
//			boolean flag = true;
//			while (flag) {
//				if (file.canWrite()) {
//					flag = false;
					Transformer transformer = TransformerFactory.newInstance().newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(file);
					transformer.transform(source, result);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取服务器端目录
	public static String getServerDir() {
		return System.getProperty("user.dir") + File.separator + "server" + File.separator;
	}

	// 获取服务器粒库描述文件server.xml绝对路径
	public static String getServerFileConfigPath() {
		return getServerDir() + "server.xml";
	}

	// 获取某个客户端在服务器的粒库文件目录
	public static String getClientFileRoot(String ip, String md5Code) {
		return getServerDir() + ip + File.separator + md5Code + File.separator;
	}

	public static List<String> stringArrayToList(String[] stringArray) {
		List<String> returnList = new ArrayList<String>();
		int arraySize = stringArray.length;
		for (int i = 0; i < arraySize; i++) {
			returnList.add(stringArray[i]);
		}
		return returnList;
	}

	// 更新local.xml的个体信息，添加新的个体
	public static void addNewNodeTrip(Document doc, Node fromNode, Node descNode) {
		Element elm = null;
		if (fromNode.getNodeType() == Node.ELEMENT_NODE) {
			String name = fromNode.getNodeName();
			if ("granule".equals(name)) {
				NamedNodeMap startAttr = fromNode.getAttributes();
				String granuleName = startAttr.getNamedItem("name").getNodeValue();
				elm = doc.createElement(name);
				elm.setAttribute("name", granuleName);
				elm.setAttribute("context", startAttr.getNamedItem("context").getNodeValue());
				elm.setAttribute("class", startAttr.getNamedItem("class").getNodeValue());

				String shawdowName = "";
				for (int i = 3; i < startAttr.getLength(); i++) {
					shawdowName = startAttr.item(i).getNodeValue();
					elm.setAttribute("shadow_class" + (i - 3), shawdowName);
				}
				descNode.appendChild(elm);
			}
		}

		for (Node child = fromNode.getFirstChild(); child != null; child = child.getNextSibling()) {
			addNewNodeTrip(doc, child, elm);
		}
	}

	public static void getXMLConifgFileFromClient(String ip, String proClientDir, String proServerDir, String fileName) {
		String clientXmlFile = proClientDir + fileName;
		String serverXmlFile = proServerDir + fileName;
		try {
			Socket sock = new Socket(ip, ConstantVariable.REMOTE_AS_CLIENT_PORT);

			PrintWriter socketWriter = new PrintWriter(sock.getOutputStream());
			socketWriter.println(ConstantVariable.ACTION_GET);
			socketWriter.println(clientXmlFile);
			socketWriter.flush();
			InputStream socketGet = sock.getInputStream();
			byte[] buffer = new byte[1024];
			int byteread = 0;
			int bytesum = 0;
			FileOutputStream fs = new FileOutputStream(serverXmlFile);
			while ((byteread = socketGet.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void getClassFileFromClient(Node node, String ip, String proClientDir, String proServerDir) throws IOException {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			String name = node.getNodeName();
			if ("granule".equals(name)) {
				NamedNodeMap startAttr = node.getAttributes();
				String granuleName = startAttr.getNamedItem("name").getNodeValue();
				String shawdowName = "";
				if (!"g0".equals(granuleName)) {
					getFileFromClientToLib(granuleName, ip, proClientDir, proServerDir);
					for (int i = 3; i < startAttr.getLength(); i++) {
						shawdowName = startAttr.item(i).getNodeValue();
						getFileFromClientToLib(shawdowName, ip, proClientDir, proServerDir);
					}
				}
			}
		}
		for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
			getClassFileFromClient(child, ip, proClientDir, proServerDir);
		}
	}

	public static void getFileFromClientToLib(String className, String ip, String proClientDir, String proServerDir) {
		String clientFile = proClientDir + className + ".class";
		String serverFile = proServerDir + className + ".class";
		try {
			Socket sock = new Socket(ip, ConstantVariable.REMOTE_AS_CLIENT_PORT);

			PrintWriter socketWriter = new PrintWriter(sock.getOutputStream());
			socketWriter.println(ConstantVariable.ACTION_GET);
			socketWriter.println(clientFile);
			socketWriter.flush();
			InputStream socketGet = sock.getInputStream();
			byte[] buffer = new byte[1024];
			int byteread = 0;
			int bytesum = 0;
			FileOutputStream fs = new FileOutputStream(serverFile);
			while ((byteread = socketGet.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sentXmlFileToClient(String indPath, String clientIp, String projectPath, boolean iConfig) {
		String proServerDir = indPath;
		String proClientDir = null;
		if (iConfig) {
			proClientDir = projectPath + "server_back.xml";
		} else {
			proClientDir = projectPath + "m_map.xml";
		}

		try {
			Socket sock = new Socket(clientIp, ConstantVariable.REMOTE_AS_CLIENT_PORT);
			OutputStream socketPut = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketPut);
			socketWriter.println(ConstantVariable.ACTION_PUT);
			socketWriter.println(proClientDir);
			socketWriter.flush();
			InputStream fi = new FileInputStream(new File(proServerDir));
			byte[] buffer = new byte[1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = fi.read(buffer)) != -1) {
				bytesum += byteread;
				socketPut.write(buffer, 0, byteread);
			}
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sentGranuleFileToClient(String indPath, Node similarGranule, String clientIp, String projectPath) {
		if (similarGranule.getNodeType() == Node.ELEMENT_NODE) {
			String name = similarGranule.getNodeName();
			String className = "";
			if ("granule".equals(name)) {
				className = similarGranule.getAttributes().getNamedItem("name").getNodeValue();
				sendClassFileToClient(indPath, projectPath, className, clientIp);
				String shadowName = "";
				for (int i = 3; i < similarGranule.getAttributes().getLength(); i++) {
					shadowName = similarGranule.getAttributes().item(i).getNodeValue();
					sendClassFileToClient(indPath, projectPath, shadowName, clientIp);
				}
			}
		}

		for (Node child = similarGranule.getFirstChild(); child != null; child = child.getNextSibling()) {
			sentGranuleFileToClient(indPath, child, clientIp, projectPath);
		}
	}

	public static synchronized void sendClassFileToClient(String indPath, String projectPath, String className, String clientIp) {
		String proServerDir = indPath.substring(0, indPath.lastIndexOf(File.separator)) + File.separator + className + ".class";
		String proClientDir = projectPath + className + ".class";
		try {
			Socket sock = new Socket(clientIp, ConstantVariable.REMOTE_AS_CLIENT_PORT);
			OutputStream socketPut = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketPut);
			socketWriter.println(ConstantVariable.ACTION_PUT);
			socketWriter.println(proClientDir);
			socketWriter.flush();
			InputStream fi = new FileInputStream(new File(proServerDir));
			byte[] buffer = new byte[1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = fi.read(buffer)) != -1) {
				bytesum += byteread;
				socketPut.write(buffer, 0, byteread);
			}
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Node> fiterPathNodeFromNodeList(List<Node> nodeList) {
		int listSize = nodeList.size();
		List<Node> list = new ArrayList<Node>();
		for (int i = 0; i < listSize; i = i + 2) {
			list.add(nodeList.get(i));
		}
		return list;
	}

	public static String listToString(List<String> list) {
		String str = "";
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			if (i == listSize - 1) {
				str += list.get(i);
			} else {
				str += list.get(i) + ",";
			}
		}
		return str;
	}

	public static List<String> getMd5ListFromRegisDoc(Document doc) {
		List<String> md5List = new ArrayList<String>();
		Node redoNode = null;
		String md5 = "";
		NodeList nodeList = doc.getElementsByTagName("g-tree");
		for (int i = 0; i < nodeList.getLength(); i++) {
			redoNode = nodeList.item(i);
			md5 = redoNode.getAttributes().getNamedItem("md5").getNodeValue();
			md5List.add(md5);
		}
		return md5List;
	}

	public static void writeNodeListToXML(List<Node> waitRegisList, String path) throws ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = doc.createElement("update_node_list");
		int waitRegisListSize = waitRegisList.size();
		Node updateNode = null;
		NamedNodeMap attrMap = null;
		for (int i = 0; i < waitRegisListSize; i = i + 1) {
			// copy nodeDoc
			updateNode = waitRegisList.get(i);
			attrMap = updateNode.getAttributes();
			Element childNode = doc.createElement(updateNode.getNodeName());
			childNode.setAttribute("md5", attrMap.getNamedItem("md5").getNodeValue());
			childNode.setAttribute("feature", attrMap.getNamedItem("feature").getNodeValue());
			childNode.setAttribute("ip", "202.117.15.192");
			childNode.setAttribute("location", "E:\\");
			childNode.setAttribute("id", "1");// 种群
			root.appendChild(childNode);
			for (Node tripNode = updateNode.getFirstChild(); tripNode != null; tripNode = tripNode.getNextSibling()) {
				updateNodeTrip(doc, tripNode, childNode);
			}
		}
		doc.appendChild(root);
		writeToXML(doc, path);
		waitRegisList = null;
	}

	public static void updateNodeTrip(Document doc, Node fromNode, Node descNode) {
		Element elm = null;
		if (fromNode.getNodeType() == Node.ELEMENT_NODE) {
			String name = fromNode.getNodeName();
			if ("granule".equals(name)) {
				NamedNodeMap startAttr = fromNode.getAttributes();
				String granuleName = startAttr.getNamedItem("name").getNodeValue();
				elm = doc.createElement(name);
				elm.setAttribute("name", granuleName);
				elm.setAttribute("context", startAttr.getNamedItem("context").getNodeValue());
				elm.setAttribute("class", startAttr.getNamedItem("class").getNodeValue());
				String shawdowName = "";
				for (int i = 3; i < startAttr.getLength(); i++) {
					shawdowName = startAttr.item(i).getNodeValue();
					elm.setAttribute("shadow_class" + (i - 3), shawdowName);
				}
				descNode.appendChild(elm);
			}
		}

		for (Node child = fromNode.getFirstChild(); child != null; child = child.getNextSibling()) {
			updateNodeTrip(doc, child, elm);
		}
	}

	public static List<Node> getListNodeFromNodeList(NodeList nodeList) {
		int size = nodeList.getLength();
		List<Node> list = new ArrayList<Node>();
		for (int i = 0; i < size; i++) {
			list.add(nodeList.item(i));
		}
		return list;
	}

	public static String getInputFromSocket(InputStream inputStream) {
		String path = "";
		char c;
		try {
			DataInputStream ds = new DataInputStream(inputStream);
			while ((c = (char) ds.readUnsignedByte()) != '\n') {
				if (c != '\r')
					path = path + c;
			}
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void handleFeedback(Socket socket, String feedback) {
		try {
			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream());
			socketWriter.println(feedback);
			socketWriter.flush();
			socketWriter.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Node guidToGranuleNode(String indPath, String similarPath) {
		Document simiDoc = Utility.loadFromXML(indPath);
		Node returnGranule = simiDoc.getElementsByTagName("granule").item(0);
		String[] objectArray = similarPath.split(",");
		int arraySize = objectArray.length;
		String className = "";
		String contextName = "";
		Node node = null;
		for (int i = 0; i < arraySize; i++) {
			className = objectArray[i].split(":")[0];
			contextName = objectArray[i].split(":")[1];
			for (node = returnGranule.getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getAttributes().getNamedItem("class").getNodeValue().equals(className) && node.getAttributes().getNamedItem("context").getNodeValue().equals(contextName)) {
						returnGranule = node;
						break;
					}
				}
			}
		}
		return returnGranule;
	}
	
	// 获取最久未使用项
	public static String getOldestItem(HashMap<String, Date> map) {
		Iterator<Entry<String, Date>> iter = map.entrySet().iterator();
		String md5 = "";
		Date date = null;
		Entry<String, Date> entry = null;
		Date cDate = new Date();
		long max_gap = 0l;
		String remove_item = null;
		long item_gap = 0l;
		while (iter.hasNext()) {
			entry = (Entry<String, Date>) iter.next();
			md5 = (String) entry.getKey();
			date = (Date) entry.getValue();
			item_gap = cDate.getTime() - date.getTime();
			if (item_gap >= max_gap) {
				remove_item = md5;
			}
		}
		return remove_item;
	}

	// 获取超过时间阀值2小时未使用项
	public static List<String> getIneffectiveItems(HashMap<String, Date> map) {
		Iterator<Entry<String, Date>> iter = map.entrySet().iterator();
		String md5 = "";
		Date date = null;
		Entry<String, Date> entry = null;
		Date cDate = new Date();
		long item_gap = 0l;
		List<String> items = new ArrayList<String>();
		while (iter.hasNext()) {
			entry = (Entry<String, Date>) iter.next();
			md5 = (String) entry.getKey();
			date = (Date) entry.getValue();
			item_gap = cDate.getTime() - date.getTime();
			if (item_gap > 7200000) {
				items.add(md5);
			}
		}
		return items;
	}

	// public static String activeToString(Map<String, ActiveIndividual> active)
	// {
	// String activeString = "";
	// for (String o : active.keySet()) {
	// activeString = activeString + o + ";";
	// }
	// return activeString;
	// }

	// public static String getIp() {
	// String localip = null;
	// String netip = null;
	// try {
	// Enumeration<NetworkInterface> netInterfaces =
	// NetworkInterface.getNetworkInterfaces();
	// InetAddress ip = null;
	// boolean finded = false;
	// NetworkInterface ni = null;
	// Enumeration<InetAddress> address = null;
	// while (netInterfaces.hasMoreElements() && !finded) {
	// ni = netInterfaces.nextElement();
	// address = ni.getInetAddresses();
	// while (address.hasMoreElements()) {
	// ip = address.nextElement();
	// if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() &&
	// ip.getHostAddress().indexOf(":") == -1) {// ??IP
	// netip = ip.getHostAddress();
	// finded = true;
	// break;
	// } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() &&
	// ip.getHostAddress().indexOf(":") == -1) {// ??IP
	// localip = ip.getHostAddress();
	// }
	// }
	// }
	// } catch (SocketException e) {
	// e.printStackTrace();
	// }
	// if (netip != null && !"".equals(netip)) {
	// return netip;
	// } else {
	// return localip;
	// }
	// }
}
