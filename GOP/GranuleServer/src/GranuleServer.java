import java.io.File;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GranuleServer {

	private static GranuleServer gs;
	// private static Map<String, ActiveIndividual> active;
	private Document serverDoc;
	private Document similarDoc;
	private static HashMap<String, String> granuleHash = new HashMap<String, String>();
	private static HashMap<String, String> mMapHash = new HashMap<String, String>();
	private static HashMap<String, String> raceHash = new HashMap<String, String>();
	// 个体缓存，用于相似粒查找
	// private static HashMap<String, List<String>> similarGranuleCache = new
	// HashMap<String, List<String>>();
	// 个体缓存，用于相似粒查找
	private static GopHashMap<String, List<String>> similarGranuleCache = GopHashMap.getInstance();
	// 个体缓存时间戳
	private static HashMap<String, Date> cacheExpired = new HashMap<String, Date>();
	// 相似粒Hash，用于相似粒查找
	private static HashMap<String, List<String>> similarGranuleHash = new HashMap<String, List<String>>();
	// 用于控制批量写个体到文件
	private static List<Node> waitRegisList = new ArrayList<Node>();

	public static GranuleServer getInstance() {
		if (gs == null) {
			gs = new GranuleServer();
		}
		return gs;
	}

	private GranuleServer() {

	}

	public void init() {
		// active = new HashMap<String, ActiveIndividual>();
		String serverDir = Utility.getServerDir();
		File fileDir = new File(serverDir);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		loadServerConfig(serverDir);
		loadSimilarGranule(serverDir);
		// 多线程处理异常的注册请求
		// handRegisRedoProcess();
	}

	public void registerToServer(List<Node> regisList, String ip) {
		int waitRegisListSize = regisList.size();
		if (waitRegisListSize >= 1) {
			Node regisNode = null;
			Node regisNodePath = null;
			NamedNodeMap nodeAttr = null;
			String md5 = "";
			String raceId = "";
			String file = "";
			//String mmap = "";
			String individual = null;
			// List<Node> unRegisList = new ArrayList<Node>();
			for (int i = 0; i < waitRegisListSize; i = i + 2) {
				regisNode = regisList.get(i);
				regisNodePath = regisList.get(i + 1);
				nodeAttr = regisNode.getAttributes();
				raceId = nodeAttr.getNamedItem("raceId").getNodeValue();
				md5 = nodeAttr.getNamedItem("md5").getNodeValue();
				file = nodeAttr.getNamedItem("file").getNodeValue();
				//mmap = nodeAttr.getNamedItem("mmap").getNodeValue();
				individual = granuleHash.get("md5");
				if (individual == null) {
					waitRegisList.add(regisNode);
					waitRegisList.add(regisNodePath);
					// 更新种群hash
					modifyRaceHash(md5, raceId);
					// 放入到个体hash
					String indPath = Utility.getServerDir() + ip + File.separator + md5 + File.separator + file;
					//String mmapPath = Utility.getServerDir() + ip + File.separator + md5 + File.separator + mmap;
					modifyGranuleHash(md5, indPath);
					// for testGranuleDoc
					modifyToHashAndCache(md5, regisNodePath);
					// 更新mmaphash
					//modifyMMapHash(md5, mmapPath);
				}
			}
			// 延迟注册到服务器
			if (waitRegisList != null && waitRegisList.size() == ConstantVariable.REG_BATCH_SIZE) {
				mutiThreadRegisToServer(waitRegisList, ip);
				waitRegisList = new ArrayList<Node>();
			}
		}
	}

	public void getSimilarGranule(String md5Code, String similarPath, Socket socket, String clientIp, String retryInfo, int position, String projectPath) {
		// make sure the two belong to the same race but not the same individual
		findSimilarGranule(md5Code, similarPath, socket, clientIp, retryInfo, position, projectPath);
	}

	public void loadServerConfig(String serverDir) {
		String filePath = serverDir + "server.xml";
		if (!new File(filePath).exists()) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = doc.createElement("global");
				root.setAttribute("name", InetAddress.getLocalHost().getHostAddress());
				doc.appendChild(root);
				Utility.writeToXML(doc, filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		serverDoc = Utility.loadFromXML(filePath);
		loadServerDocToHash();
	}

	public void loadSimilarGranule(String serverDir) {
		String filePath = serverDir + "path_granuleList.xml";
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
		similarDoc = Utility.loadFromXML(filePath);
		loadSimilarDocToHash();
	}

	public void loadServerDocToHash() {
		NodeList raceList = serverDoc.getElementsByTagName("race");
		int raceListLength = raceList.getLength();
		// for race
		Element raceNode = null;
		NamedNodeMap raceAttr = null;
		String id = "";
		// for individual
		NodeList nodeList = null;
		Node node = null;
		NamedNodeMap nodeAttr = null;
		String file = "";
		String location = "";
		String md5 = "";
		//String mmap = "";
		int nodeListLength = 0;
		for (int i = 0; i < raceListLength; i++) {
			raceNode = (Element) raceList.item(i);
			raceAttr = raceNode.getAttributes();
			id = raceAttr.getNamedItem("id").getNodeValue();
			nodeList = raceNode.getElementsByTagName("gvm");
			nodeListLength = nodeList.getLength();
			for (int j = 0; j < nodeListLength; j++) {
				node = nodeList.item(j);
				nodeAttr = node.getAttributes();
				md5 = nodeAttr.getNamedItem("md5").getNodeValue();
				location = nodeAttr.getNamedItem("loc_server").getNodeValue();
				file = nodeAttr.getNamedItem("file").getNodeValue();
				//mmap = nodeAttr.getNamedItem("mmap").getNodeValue();
				granuleHash.put(md5, location + file);
				//mMapHash.put(md5, location + mmap);
				raceHash.put(md5, id);
			}

		}
	}

	public void loadSimilarDocToHash() {
		NodeList nodeList = similarDoc.getElementsByTagName("hash");
		Node node = null;
		NamedNodeMap nodeAttr = null;
		String path = "";
		String value = "";
		List<String> md5List = new ArrayList<String>();
		int nodeListLength = nodeList.getLength();
		for (int i = 0; i < nodeListLength; i++) {
			node = nodeList.item(i);
			nodeAttr = node.getAttributes();
			path = nodeAttr.getNamedItem("path").getNodeValue();
			value = nodeAttr.getNamedItem("md5_list").getNodeValue();
			md5List = Utility.stringArrayToList(value.split(","));
			similarGranuleHash.put(path, md5List);
		}
	}

	public void mutiThreadRegisToServer(List<Node> regisList, String ip) {
		// 更新文件内容到server.xml
		ServerRegisToServerThread srst = new ServerRegisToServerThread(regisList, ip);
		new Thread(srst).start();

		// 更新文件内容到granuleHash.xml
		ServerUpPathGranuleThread sugt = new ServerUpPathGranuleThread(similarGranuleHash);
		new Thread(sugt).start();

		// 更新文件字节码到服务器
		List<Node> nodeList = Utility.fiterPathNodeFromNodeList(regisList);
		ServerGetUpFileByClientThread sgfct = new ServerGetUpFileByClientThread(nodeList, ip);
		new Thread(sgfct).start();
	}

	public void findSimilarGranule(String md5Code, String similarPath, Socket socket, String clientIp, String retryInfo, int position, String projectPath) {
		String indPath = null;
		//String mMapPath = null;
		boolean flag = false;
		boolean rflag = false;
		// find from cache
		List<String> md5Array = similarGranuleCache.get(similarPath);
		String md5 = "";
		List<String> md5CacheList = new ArrayList<String>();
		String feedback = "";
		Node similarGranule = null;
		int md5ArrLength = 0;
		if (md5Array != null) {
			md5ArrLength = md5Array.size();
		}
		if (ConstantVariable.RETRY_NULL.equals(retryInfo) || ConstantVariable.RETRY_LOCAL_CACHE.equals(retryInfo) || ConstantVariable.RETRY_LOCAL_STORE.equals(retryInfo)) {
			if (md5Array != null && md5ArrLength > 0) {
				for (int i = 0; i < md5ArrLength; i++) {
					md5 = md5Array.get(i);
					indPath = granuleHash.get(md5);
					//mMapPath = mMapHash.get(md5);
					if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
						// 更新缓存被使用信息，
						modifyCacheExpired(similarPath);
						// 更新二次查询信息
						retryInfo = ConstantVariable.RETRY_REMOTE_CACHE;
						position = i + 1;
						// modifySimilarGranuleCache(similarPath, md5);
						flag = true;
						feedback = projectPath + "server_back.xml" + ";" + similarPath + ";" + retryInfo + ";" + position;
						break;
					}
				}
			}
			// else find from individual store
			if (!flag) {
				md5Array = similarGranuleHash.get(similarPath);
				md5ArrLength = 0;
				if (md5Array != null) {
					md5ArrLength = md5Array.size();
				}

				if (md5Array != null && md5ArrLength > 0) {
					for (int i = 0; i < md5ArrLength; i++) {
						md5 = md5Array.get(i);
						indPath = granuleHash.get(md5);
						//mMapPath = mMapHash.get(md5);
						if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
							// 更新二次查询信息
							md5CacheList = getCacheList(md5Code, md5, i, md5Array);
							retryInfo = ConstantVariable.RETRY_REMOTE_STORE;
							position = i + 1;
							// modifySimilarGranuleCache(similarPath, md5);
							flag = true;
							feedback = projectPath + "server_back.xml" + ";" + similarPath + ";" + retryInfo + ";" + position;
							break;
						}
					}
				}
			}
		} else {
			// 解决二次查找问题
			rflag = true;
			researchSimilarGranule(md5Code, similarPath, socket, clientIp, retryInfo, position, projectPath);
		}
		if (flag) {
			Utility.handleFeedback(socket, feedback);
			similarGranule = Utility.guidToGranuleNode(indPath, similarPath);
			//Utility.sentXmlFileToClient(mMapPath, clientIp, projectPath, false);
			Utility.sentXmlFileToClient(indPath, clientIp, projectPath, true);
			Utility.sentGranuleFileToClient(indPath, similarGranule, clientIp, projectPath);
			// 放入相似粒缓存，
			// modifySimilarGranuleCache(similarPath, md5);
			updateCache(similarPath, md5CacheList);
			// if (similarGranuleCache.size() ==
			// similarGranuleCache.getMaxSize()) {
			// String removeItem = Utility.getOldestItem(cacheExpired);
			// similarGranuleCache.remove(removeItem, true);
			// cacheExpired.remove(removeItem);
			// }
			// similarGranuleCache.put(similarPath, md5);
			// modifyCacheExpired(similarPath);

		} else if (!rflag) {
			feedback = ConstantVariable.GRANULE_SEARCH_FAIL;
			Utility.handleFeedback(socket, feedback);
		}
	}

	public void researchSimilarGranule(String md5Code, String similarPath, Socket socket, String clientIp, String retryInfo, int position, String projectPath) {
		String indPath = null;
		//String mMapPath = null;
		boolean flag = false;
		List<String> md5List = new ArrayList<String>();
		List<String> md5CacheList = new ArrayList<String>();
		String md5 = "";
		int md5ArrLength = 0;
		String feedback = "";
		Node similarGranule = null;
		if (ConstantVariable.RETRY_REMOTE_CACHE.equals(retryInfo)) {
			md5List = similarGranuleCache.get(similarPath);
			if (md5List != null) {
				md5ArrLength = md5List.size();
			}
			for (int i = position; i < md5ArrLength; i++) {
				md5 = md5List.get(i);
				indPath = granuleHash.get(md5);
				//mMapPath = mMapHash.get(md5);
				if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
					// 更新缓存被使用信息，
					modifyCacheExpired(similarPath);
					// 更新二次查询信息
					retryInfo = ConstantVariable.RETRY_REMOTE_CACHE;
					position = i + 1;
					// modifySimilarGranuleCache(similarPath, md5);
					flag = true;
					// 远程返回目标个体的配置文件hardcode为server_back.xml
					feedback = projectPath + "server_back.xml" + ";" + similarPath + ";" + retryInfo + ";" + position;
					break;
				}
			}
			if (!flag) {
				md5List = similarGranuleHash.get(similarPath);
				md5ArrLength = 0;
				if (md5List != null) {
					md5ArrLength = md5List.size();
				}

				if (md5List != null && md5ArrLength > 0) {
					for (int i = 0; i < md5ArrLength; i++) {
						md5 = md5List.get(i);
						indPath = granuleHash.get(md5);
						//mMapPath = mMapHash.get(md5);
						if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
							md5CacheList = getCacheList(md5Code, md5, i, md5List);
							// 更新二次查询信息
							retryInfo = ConstantVariable.RETRY_REMOTE_STORE;
							position = i + 1;
							// modifySimilarGranuleCache(similarPath, md5);
							flag = true;
							feedback = projectPath + "server_back.xml" + ";" + similarPath + ";" + retryInfo + ";" + position;
							break;
						}
					}
				}
			}
		} else {
			md5List = similarGranuleHash.get(similarPath);
			if (md5List != null) {
				md5ArrLength = md5List.size();
			}
			for (int i = position; i < md5ArrLength; i++) {
				md5 = md5List.get(i);
				indPath = granuleHash.get(md5);
				//mMapPath = mMapHash.get(md5);
				if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
					md5CacheList = getCacheList(md5Code, md5, i, md5List);
					// 更新二次查询信息
					retryInfo = ConstantVariable.RETRY_REMOTE_STORE;
					position = i + 1;
					// modifySimilarGranuleCache(similarPath, md5);
					flag = true;
					feedback = projectPath + "server_back.xml" + ";" + similarPath + ";" + retryInfo + ";" + position;
					break;
				}
			}
		}
		if (flag) {
			Utility.handleFeedback(socket, feedback);
			similarGranule = Utility.guidToGranuleNode(indPath, similarPath);
			//Utility.sentXmlFileToClient(mMapPath, clientIp, projectPath, false);
			Utility.sentXmlFileToClient(indPath, clientIp, projectPath, true);
			Utility.sentGranuleFileToClient(indPath, similarGranule, clientIp, projectPath);
			// 放入相似粒缓存，
			// modifySimilarGranuleCache(similarPath, md5);
			updateCache(similarPath, md5CacheList);
			// if (similarGranuleCache.size() ==
			// similarGranuleCache.getMaxSize()) {
			// String removeItem = Utility.getOldestItem(cacheExpired);
			// similarGranuleCache.remove(removeItem, true);
			// cacheExpired.remove(removeItem);
			// }
			// similarGranuleCache.put(similarPath, md5);
			// modifyCacheExpired(similarPath);
		} else {
			feedback = ConstantVariable.GRANULE_SEARCH_FAIL;
			Utility.handleFeedback(socket, feedback);
		}
	}

	public void modifyToHashAndCache(String md5, Node regisNodePath) {

		String path = "";
		Node nodePath = null;
		for (nodePath = regisNodePath.getFirstChild(); nodePath != null; nodePath = nodePath.getNextSibling()) {
			if (nodePath.getNodeType() == Node.ELEMENT_NODE) {
				path = nodePath.getAttributes().getNamedItem("value").getNodeValue();
				// 放入相似粒hash
				modifySimilarGranuleHash(path, md5);
			}
		}
		// 根据相似路径-个体对的多少，动态调整cache的大小，
		similarGranuleCache.resize(similarGranuleHash,true);
	}

	public void modifyRaceHash(String md5, String raceId) {
		synchronized (raceHash) {
			if (raceHash.get(md5) == null) {
				raceHash.put(md5, raceId);
			}
		}
	}

//	public void modifyMMapHash(String md5, String mmap) {
//		synchronized (mMapHash) {
//			if (mMapHash.get(md5) == null) {
//				mMapHash.put(md5, mmap);
//			}
//		}
//	}

	// 放入个体hash中
	public void modifyGranuleHash(String md5, String indPath) {
		synchronized (granuleHash) {
			if (granuleHash.get(md5) == null) {
				granuleHash.put(md5, indPath);
			}
		}
	}

	// 放入相似粒hash
	public void modifySimilarGranuleHash(String similarPath, String md5) {
		List<String> md5HashList = similarGranuleHash.get(similarPath);
		synchronized (similarGranuleHash) {
			if (md5HashList == null) {
				List<String> newMd5HashList = new ArrayList<String>();
				newMd5HashList.add(md5);
				similarGranuleHash.put(similarPath, newMd5HashList);
			} else {
				if (!md5HashList.contains(md5)) {
					md5HashList.add(md5);
				}
				similarGranuleHash.put(similarPath, md5HashList);
			}
		}
	}

	// 放入相似粒缓存
	// public void modifySimilarGranuleCache(String similarPath, String md5) {
	// List<String> md5CacheList = similarGranuleCache.get(similarPath);
	// if (md5CacheList == null) {
	// List<String> newMd5CacheList = new ArrayList<String>();
	// newMd5CacheList.add(md5);
	// similarGranuleCache.put(similarPath, newMd5CacheList);
	// } else {
	// if (!md5CacheList.contains(md5)) {
	// md5CacheList.add(md5);
	// }
	// similarGranuleCache.put(similarPath, md5CacheList);
	// }
	//
	// }

	public void updateCache(String path, List<String> md5List) {
		// cache被装满时，
		synchronized (similarGranuleCache) {
			if (similarGranuleCache.size() == similarGranuleCache.getMaxSize()) {
				String removeItem = Utility.getOldestItem(cacheExpired);
				similarGranuleCache.remove(removeItem, true);
				cacheExpired.remove(removeItem);
			}
			int size = md5List.size();
			String md5 = "";
			for (int i = 0; i < size; i++) {
				md5 = md5List.get(i);
				similarGranuleCache.put(path, md5);
			}
			modifyCacheExpired(path);
		}
	}

	public void modifyCacheExpired(String similarPath) {
		synchronized (cacheExpired) {
			cacheExpired.put(similarPath, new Date());
		}
	}

	// 获取需要装入cache的内容，一般为该数据及其下一条可用数据，
	public List<String> getCacheList(String md5Code, String md5, int i, List<String> md5Array) {
		List<String> md5CacheList = new ArrayList<String>();
		md5CacheList.add(md5);
		int size = md5Array.size();
		String nextMd5 = "";
		for (int j = i + 1; j < size; j++) {
			nextMd5 = md5Array.get(j);
			if (!nextMd5.equals(md5Code)) {
				md5CacheList.add(nextMd5);
				break;
			}
		}
		return md5CacheList;
	}

	// public Map<String, ActiveIndividual> getActive() {
	// return active;
	// }

	// 处理获取文件失败的注册个体
	public void handRegisRedoProcess() {
		Document doc = Utility.loadFromXML("src\\redoUpdate.xml");
		NodeList nodeList = doc.getElementsByTagName("g-tree");
		List<Node> list = Utility.getListNodeFromNodeList(nodeList);
		// test
		ServerGetUpFileByClientThread sgft = new ServerGetUpFileByClientThread(list, "");
		new Thread(sgft).start();
	}

//	public static void main(String[] args) {
//		List<String> hm = new ArrayList<String>();
//		for (int i = 0; i < 70; i++) {
//			hm.add("a" + i);
//		}
//		List<String> cm = new ArrayList<String>();
//		for (int j = 30; j < 41; j++) {
//			cm.add("a" + j);
//		}
//		Date d1 = new Date();
//		Timestamp ts1 = new Timestamp(d1.getTime());
//		System.out.println(ts1);
//		for (int m = 0; m < 10000000; m++) {
//			for (int i = 0; i < 100; i++) {
//				if (hm.get(i).equals("a35")) {
//					break;
//				}
//			}
//		}
//		Date d2 = new Date();
//		Timestamp ts2 = new Timestamp(d2.getTime());
//		System.out.println(ts2);
//		for (int n = 0; n < 10000000; n++) {
//			for (int j = 0; j < 11; j++) {
//				if (cm.get(j).equals("a35")) {
//					break;
//				}
//			}
//		}
//		Date d3 = new Date();
//		Timestamp ts3 = new Timestamp(d3.getTime());
//		System.out.println(ts3);
//	}
}
