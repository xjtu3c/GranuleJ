package granulej.util;

import granulej.granule.client.HandRegisExceptionSaveThread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import org.xml.sax.SAXException;

public class Utility {
	public static Document loadFromXML(String filename) {
		try {
			File file = new File(filename);
			Document document = null;
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = builder.parse(file);
			document.normalize();
			return document;
		} catch(IOException e){
		   e.printStackTrace();
		} catch(ParserConfigurationException e){
		   e.printStackTrace();
		} catch(SAXException se){
		   se.printStackTrace();
		}

		return null;
	}

	public static void writeToXML(Document doc, String path) {
		try {
			File f = new File(path);
			// 内容一次写入文件
			boolean flag = true;
			// while (flag) {
			// if (f.canWrite()) {
			// flag = false;
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			transformer.transform(source, result);
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getInputFromSocket(InputStream inputStream) {
		String path = "";
		char c;
		try {
			DataInputStream ds = new DataInputStream(inputStream);
			while (ds.available() > 0 && (c = (char) ds.readUnsignedByte()) != '\n') {
				if (c != '\r')
					path = path + c;
			}
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getAllInputFromSocket(InputStream inputStream) {
		try {
			StringBuffer sb = new StringBuffer();
			char c;
			DataInputStream ds = new DataInputStream(inputStream);
			while (ds.available() > 0) {
				c = (char) ds.readUnsignedByte();
				sb.append(c);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取客户器端目录
	public static String getClientDir() {
		return System.getProperty("user.dir") + File.separator + "client" + File.separator;
	}

	public static List<String> stringArrayToList(String[] stringArray) {
		List<String> returnList = new ArrayList<String>();
		int arraySize = stringArray.length;
		for (int i = 0; i < arraySize; i++) {
			returnList.add(stringArray[i]);
		}
		return returnList;
	}

	public static String getIp() {
		String localip = null;// ??IP,????????IP????
		String netip = null;// ??IP
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// ??????IP
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> addresses = ni.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = addresses.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// ??IP
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// ??IP
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	public static String getFile(String path) {
		try {
			String ret = "", temp = "";
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((temp = br.readLine()) != null) {
				ret = ret + temp.trim();
			}
			return ret;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String translate(String name, int type) {
		if (type == 0) { // ????????
			name = name.substring(1, name.length() - 1);
			name = name.replace('/', '.');
			return name;
		} else {
			name = name.replace('.', '/');
			name = "/" + name + "/";
			return name;
		}
	}

	public static void Copy(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getMd5ListFromRegisDoc(Document doc) {
		List<String> md5List = new ArrayList<String>();
		Node redoNode = null;
		String md5 = "";
		NodeList nodeList = doc.getElementsByTagName("g-tree");
		int length = nodeList.getLength();
		for (int i = 0; i < length; i++) {
			redoNode = nodeList.item(i);
			md5 = redoNode.getAttributes().getNamedItem("md5").getNodeValue();
			md5List.add(md5);
		}
		return md5List;
	}

	public static Socket createGranuleConnectionToServer(String serverIp, int port, List<Node> waitRegisList) {
		Socket sock = null;
		try {
			sock = new Socket(serverIp, port);
		} catch (Exception e) {
			System.out.println("The granule server is not started,and it will be register later!");
			// 相似粒注册通信异常的处理,存储信息
			HandRegisExceptionSaveThread hrct = new HandRegisExceptionSaveThread(waitRegisList);
			new Thread(hrct).start();
		}
		return sock;
	}

	public static Socket createContextConnectionToServer(String serverIp, int port) {
		Socket sock = null;
		try {
			sock = new Socket(serverIp, port);
		} catch (Exception e) {
			System.out.println("The context server is not started,and it will be register later!");
			// 相似粒注册通信异常的处理,存储信息
		}
		return sock;
	}

	public static String getIndividualPath(Node individual) {
		String target_source = "";
		String file_name = "";
		if (individual != null) {
			target_source = individual.getAttributes().getNamedItem("location").getNodeValue();
			file_name = individual.getAttributes().getNamedItem("file").getNodeValue();
			return target_source + file_name;
		}
		return null;
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

	public static void writeNodeListToXML(List<Node> waitRegisList, String path) throws ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = doc.createElement("update_node_list");
		root.setAttribute("ip", Utility.getIp());
		int waitRegisListSize = waitRegisList.size();
		Node updateNode = null;
		Node updateNodePath = null;
		NamedNodeMap attrMap = null;
		for (int i = 0; i < waitRegisListSize; i = i + 2) {
			// copy nodeDoc
			updateNode = waitRegisList.get(i);
			attrMap = updateNode.getAttributes();
			Element childNode = doc.createElement(updateNode.getNodeName());
			childNode.setAttribute("md5", attrMap.getNamedItem("md5").getNodeValue());
			childNode.setAttribute("raceId", attrMap.getNamedItem("raceId").getNodeValue());
			childNode.setAttribute("location", attrMap.getNamedItem("location").getNodeValue());
			childNode.setAttribute("file", attrMap.getNamedItem("file").getNodeValue());
			//childNode.setAttribute("mmap", attrMap.getNamedItem("mmap").getNodeValue());
			root.appendChild(childNode);
			for (Node tripNode = updateNode.getFirstChild(); tripNode != null; tripNode = tripNode.getNextSibling()) {
				updateNodeTrip(doc, tripNode, childNode);
			}
			// copy pathDoc
			updateNodePath = waitRegisList.get(i + 1);
			Element childNodePath = doc.createElement(updateNodePath.getNodeName());
			childNodePath.setAttribute("md5", updateNodePath.getAttributes().getNamedItem("md5").getNodeValue());
			root.appendChild(childNodePath);
			for (Node tripNodePath = updateNodePath.getFirstChild(); tripNodePath != null; tripNodePath = tripNodePath.getNextSibling()) {
				updateNodePathTrip(doc, tripNodePath, childNodePath);
			}
		}
		doc.appendChild(root);
		writeToXML(doc, path);
		waitRegisList = null;
	}

	// 生成更新文件update.xml的个体信息部门
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

	// 生成更新文件update.xml的个体路径部门
	public static void updateNodePathTrip(Document doc, Node fromNode, Node descNode) {
		Element elm = null;
		if (fromNode.getNodeType() == Node.ELEMENT_NODE) {
			String name = fromNode.getNodeName();
			if ("path".equals(name)) {
				NamedNodeMap startAttr = fromNode.getAttributes();
				elm = doc.createElement(name);
				elm.setAttribute("name", startAttr.getNamedItem("name").getNodeValue());
				elm.setAttribute("value", startAttr.getNamedItem("value").getNodeValue());
				descNode.appendChild(elm);
			}
		}
		for (Node child = fromNode.getFirstChild(); child != null; child = child.getNextSibling()) {
			updateNodePathTrip(doc, child, elm);
		}
	}

	// 更新local.xml的个体信息，添加新的个体
//	public static void addNewNodeTrip(Document doc, Node fromNode, Node descNode) {
//		Element elm = null;
//		if (fromNode.getNodeType() == Node.ELEMENT_NODE) {
//			String name = fromNode.getNodeName();
//			if ("granule".equals(name)) {
//				NamedNodeMap startAttr = fromNode.getAttributes();
//				String granuleName = startAttr.getNamedItem("name").getNodeValue();
//				elm = doc.createElement(name);
//				elm.setAttribute("name", granuleName);
//				elm.setAttribute("context", startAttr.getNamedItem("context").getNodeValue());
//				elm.setAttribute("class", startAttr.getNamedItem("class").getNodeValue());
//
//				String shawdowName = "";
//				int length = startAttr.getLength();
//				for (int i = 3; i < length; i++) {
//					shawdowName = startAttr.item(i).getNodeValue();
//					elm.setAttribute("shadow_class" + (i - 3), shawdowName);
//				}
//				descNode.appendChild(elm);
//			}
//		}
//
//		for (Node child = fromNode.getFirstChild(); child != null; child = child.getNextSibling()) {
//			addNewNodeTrip(doc, child, elm);
//		}
//	}
	
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
}
