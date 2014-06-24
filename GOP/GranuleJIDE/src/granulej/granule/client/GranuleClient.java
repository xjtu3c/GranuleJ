package granulej.granule.client;

import granulej.gui.Console;
import granulej.util.Utility;
import gui.constant.GranuleConstant;
import java.io.File;
import java.net.InetAddress;
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

//请求处理模块
public class GranuleClient {

	private static GranuleClient gc;

	private Document testGranuleDoc;

	private Document testGranulePathDoc;

	private Document localDoc;

	private Document pathDoc;

	private Document similarDoc;

	private static String serverIp = GranuleConstant.GRANULE_SERVER_IP;

	// 粒名和相似粒路径hash
	private static HashMap<String, String> pathHash = new HashMap<String, String>();

	// 个体hash
	private static HashMap<String, String> granuleHash = new HashMap<String, String>();

	// 种群hash
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

	// 待注册列表
	private static List<Node> waitRegisList = new ArrayList<Node>();

	public static GranuleClient getInstance() {
		if (gc == null) {
			gc = new GranuleClient();
		}
		return gc;
	}

	public void init() {
		String clientDir = Utility.getClientDir();
		File fileDir = new File(clientDir);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		localDoc = ResourceManager.loadLocalConfig(clientDir); // raceHash,granuleHash
		loadLocalDocToHash();
		
		pathDoc = ResourceManager.loadSimilarPath(clientDir);  //pathHash
		loadPathDocToHash();
		
		similarDoc = ResourceManager.loadSimilarGranule(clientDir);  //similarGranuleHash
		loadSimilarDocToHash();
		// 多线程处理异常的注册请求
		// handRegisRedoProcess();
	}

	public void regisGranuleTree(String md5, String projectPath, String testGranuleName, String testGranulePathName) {
		String location = granuleHash.get(md5);
		String individualPath = projectPath + File.separator + testGranuleName;
		String gruanulePath = projectPath + File.separator + testGranulePathName;
		location = null;
		if (location != null) {
			Console.getInstance().append("The individual is already existed!" + "\n");
			// 已经注册，不做任何加载和处理动作
			return;
		} else {
			// 没有注册
			testGranuleDoc = Utility.loadFromXML(individualPath);
			testGranulePathDoc = Utility.loadFromXML(gruanulePath);
			// for testGranuleDoc
			modifyToHashAndCache(md5, individualPath);
		
			// for waitRegisList
			regisGranule();
		}
	}

	public String[] getSimilarGranule(String projectPath,String md5Code, String granuleName, String retryInfo, int position) {
		String similarPath = pathHash.get(granuleName);
		String[] result = null;
		// make sure the two belong to the same race but not the same individual
		result = findSimilarGranule(projectPath,md5Code, similarPath, retryInfo, position);
		return result;
	}

	public void loadLocalDocToHash() {
		NodeList raceList = localDoc.getElementsByTagName("race");
		int raceListLength = 0;
		if (raceList != null) {
			raceListLength = raceList.getLength();
		}
		// for race
		Element raceNode = null;
		NamedNodeMap raceAttr = null;
		String id = "";
		// for individual
		NodeList nodeList = null;
		Node node = null;
		NamedNodeMap nodeAttr = null;

		String md5 = "";
		String location = "";
		String file = "";
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
				location = nodeAttr.getNamedItem("location").getNodeValue();
				file = nodeAttr.getNamedItem("file").getNodeValue();
				granuleHash.put(md5, location + file);
				raceHash.put(md5, id);
			}
		}
	}

	public void loadPathDocToHash() {
		NodeList nodeList = pathDoc.getElementsByTagName("granule");
		Node node = null;
		NamedNodeMap nodeAttr = null;
		String name = "";
		String path = "";
		int nodeListLength = 0;
		if (nodeList != null) {
			nodeListLength = nodeList.getLength();
		}
		for (int i = 0; i < nodeListLength; i++) {
			node = nodeList.item(i);
			nodeAttr = node.getAttributes();
			name = nodeAttr.getNamedItem("name").getNodeValue();
			path = nodeAttr.getNamedItem("path").getNodeValue();
			pathHash.put(name, path);
		}
	}

	public void loadSimilarDocToHash() {
		NodeList nodeList = similarDoc.getElementsByTagName("hash");
		Node node = null;
		NamedNodeMap nodeAttr = null;
		String path = "";
		String md5String = "";
		int nodeListLength = nodeList.getLength();
		if (nodeList != null) {
			nodeListLength = nodeList.getLength();
		}
		List<String> md5List = new ArrayList<String>();
		for (int i = 0; i < nodeListLength; i++) {

			node = nodeList.item(i);
			nodeAttr = node.getAttributes();
			path = nodeAttr.getNamedItem("path").getNodeValue();
			md5String = nodeAttr.getNamedItem("md5_list").getNodeValue();
			md5List = Utility.stringArrayToList(md5String.split(","));
			similarGranuleHash.put(path, md5List);
		}
	}

	// 注册内容写入内存
	public void modifyToHashAndCache(String md5, String individualPath) {
		// 放入个体种群hash
		Node regisNode = testGranuleDoc.getElementsByTagName("g-tree").item(0);
		String raceId = regisNode.getAttributes().getNamedItem("raceId").getNodeValue();
		modifyRaceHash(md5, raceId);
		// 放入到粒hash
		modifyGranuleHash(md5, individualPath);

		NodeList granuleNodeList = testGranulePathDoc.getElementsByTagName("path");
		int listSize = granuleNodeList.getLength();
		String name = "";
		String path = "";
		Node node = null;
		NamedNodeMap nodeAt = null;
		for (int i = 0; i < listSize; i++) {
			node = granuleNodeList.item(i);
			nodeAt = node.getAttributes();
			name = nodeAt.getNamedItem("name").getNodeValue(); // 获取粒名
			path = nodeAt.getNamedItem("value").getNodeValue();// 上下文基类偏序对
			// 放入到路径hash
			if (pathHash.get(name) == null) {
				modifyPathHash(name, path);
			}
			// 放入相似粒hash
			modifySimilarGranuleHash(path, md5);
		}
		// 根据相似路径-个体对的多少，动态调整cache的大小，
		similarGranuleCache.resize(similarGranuleHash, true);
		// 放入相似粒缓存
		// modifySimilarGranuleCache(path, md5);
		List<String> md5List = new ArrayList<String>();
		md5List.add(md5);
		updateCache(path, md5List);

	}

	// 放入种群hash中
	public void modifyRaceHash(String md5, String raceId) {
		synchronized (raceHash) {
			if (raceHash.get(md5) == null) {
				raceHash.put(md5, raceId);
			}
		}
	}

	// 放入个体hash中
	public void modifyGranuleHash(String md5, String indPath) {
		synchronized (granuleHash) {
			if (granuleHash.get(md5) == null) {
				granuleHash.put(md5, indPath);
			}
		}
	}

	// 放入路径hash中
	public void modifyPathHash(String granuleName, String similarPath) {
		synchronized (pathHash) {
			if (pathHash.get(granuleName) == null) {
				pathHash.put(granuleName, similarPath);
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

	// 注册内容写入文件
	public void regisGranule() {
		Node regisNode = testGranuleDoc.getElementsByTagName("g-tree").item(0);
		Node regisNodePath = testGranulePathDoc.getElementsByTagName("g-path").item(0);
		if (!waitRegisList.contains(testGranuleDoc)) {
			addRegisNodeList(regisNode, regisNodePath);
		}
		synchronized (waitRegisList) {
			int waitRegisListSize = waitRegisList.size();
			// 需要再增加一种时间机制来控制个体的注册
			if (waitRegisListSize >= GranuleConstant.REG_BATCH_SIZE) {
				// 写入本地local.xml和远程server.xml
				mutiThreadRegisGranule();
				// empty waitRegisList
				emptyRegisNodeList();
				// 更新更新粒-相似路径文件，
				ResourceManager.UpdateSimilarPathFile(pathHash);
				// 更新相似路径-相似粒列表文件，
				ResourceManager.UpdateGranuleHashFile(similarGranuleHash);
			}
		}
	}

	public void addRegisNodeList(Node node, Node pathNode) {
		synchronized (waitRegisList) {
			if (!waitRegisList.contains(node)) {
				waitRegisList.add(node);
				waitRegisList.add(pathNode);
			}
		}
	}

	// 个体注册
	public void mutiThreadRegisGranule() {
		// 向本地注册,内容写入local.xml
		ResourceManager.updateLocalXML(waitRegisList);
		// 向远程转发注册请求
		GClientRedirect.redirectRegister(serverIp, GranuleConstant.LOCAL_AS_CLIENT_PORT, waitRegisList);
	}

	public void emptyRegisNodeList() {
		synchronized (waitRegisList) {
			waitRegisList = new ArrayList<Node>();
		}
	}

	public String[] findSimilarGranule(String proPath,String md5Code, String similarPath, String retryInfo, int position) {
		String projectPath = granuleHash.get(md5Code);
		if(projectPath==null){
	       projectPath=proPath;
		}
		String[] result = new String[4];
		String indPath = null;
		// Node similarGranule = null;
		boolean flag = false; // 有没有找到相似粒
		List<String> md5List = new ArrayList<String>();
		List<String> md5CacheList = new ArrayList<String>();
		String md5 = "";
		// String target_source = "";
		int md5ArrLength = 0;
		if (GranuleConstant.RETRY_NULL.equals(retryInfo)) {
			// 从本地Cache中查找
			md5List = similarGranuleCache.get(similarPath);
			if (md5List != null) {
				md5ArrLength = md5List.size();
			}
			if (md5List != null && md5ArrLength > 0) {
				for (int i = 0; i < md5ArrLength; i++) {
					md5 = md5List.get(i);
					indPath = granuleHash.get(md5);
					if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
						// 更新缓存被使用信息，
						modifyCacheExpired(similarPath);
						retryInfo = GranuleConstant.RETRY_LOCAL_CACHE;
						position = i + 1;
						flag = true;
						result[0] = indPath;
						result[1] = similarPath;
						result[2] = retryInfo;
						result[3] = String.valueOf(position);
						break;
					}
				}
			}
			// 从本地个体库中查找
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
						if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
							md5CacheList = getCacheList(md5Code, md5, i, md5List);
							updateCache(similarPath, md5CacheList);
							retryInfo = GranuleConstant.RETRY_LOCAL_STORE;
							position = i + 1;
							// modifySimilarGranuleCache(similarPath, md5);
							flag = true;
							result[0] = indPath;
							result[1] = similarPath;
							result[2] = retryInfo;
							result[3] = String.valueOf(position);
							break;
						}
					}
				}

			}
			// 向远程服务器发出相似粒查找请求
			if (!flag) {
				result = GClientRedirect.redirectSearch(serverIp, GranuleConstant.LOCAL_AS_CLIENT_PORT, projectPath, md5Code, similarPath, retryInfo, position);
			}

		} else {
			// 解决二次查找问题
			result = researchSimilarGranule(projectPath, md5Code, similarPath, retryInfo, position);
		}
		return result;
	}

	public String[] researchSimilarGranule(String projectPath, String md5Code, String similarPath, String retryInfo, int position) {
		String[] result = new String[4];
		String indPath = null;
		// Node similarGranule = null;
		boolean flag = false;
		List<String> md5List = new ArrayList<String>();
		List<String> md5CacheList = new ArrayList<String>();
		String md5 = "";
		int md5ArrLength = 0;
		if (GranuleConstant.RETRY_LOCAL_CACHE.equals(retryInfo)) {
			md5List = similarGranuleCache.get(similarPath);
			if (md5List != null) {
				md5ArrLength = md5List.size();
			}
			for (int i = position; i < md5ArrLength; i++) {
				md5 = md5List.get(i);
				indPath = granuleHash.get(md5);
				if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
					// 更新缓存被使用信息，
					modifyCacheExpired(similarPath);
					retryInfo = GranuleConstant.RETRY_LOCAL_CACHE;
					position = i + 1;
					flag = true;
					result[0] = indPath;
					result[1] = similarPath;
					result[2] = retryInfo;
					result[3] = String.valueOf(position);
					break;
				}
			}

			// 从本地个体库中查找
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
						if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
							md5CacheList = getCacheList(md5Code, md5, i, md5List);
							updateCache(similarPath, md5CacheList);
							retryInfo = GranuleConstant.RETRY_LOCAL_STORE;
							position = i + 1;
							flag = true;
							// modifySimilarGranuleCache(similarPath, md5);
							result[0] = indPath;
							result[1] = similarPath;
							result[2] = retryInfo;
							result[3] = String.valueOf(position);
							break;
						}
					}
				}
			}

			// 向远程服务器发出请求
			if (!flag) {
				position = 0;
				result = GClientRedirect.redirectSearch(serverIp, GranuleConstant.LOCAL_AS_CLIENT_PORT, projectPath, md5Code, similarPath, retryInfo, position);
			}
		} else if (GranuleConstant.RETRY_LOCAL_STORE.equals(retryInfo)) {
			md5List = similarGranuleHash.get(similarPath);
			if (md5List != null) {
				md5ArrLength = md5List.size();
			}
			for (int j = position; j < md5ArrLength; j++) {
				md5 = md5List.get(j);
				indPath = granuleHash.get(md5);
				if (!md5.equals(md5Code) && indPath != null && raceHash.get(md5).equals(raceHash.get(md5Code))) {
					md5CacheList = getCacheList(md5Code, md5, j, md5List);
					updateCache(similarPath, md5CacheList);
					retryInfo = GranuleConstant.RETRY_LOCAL_STORE;
					position = j + 1;
					// modifySimilarGranuleCache(similarPath, md5);
					flag = true;
					result[0] = indPath;
					result[1] = similarPath;
					result[2] = retryInfo;
					result[3] = String.valueOf(position);
					break;
				}
			}
			// 向远程服务器发出请求
			if (!flag) {
				position = 0;
				result = GClientRedirect.redirectSearch(serverIp, GranuleConstant.LOCAL_AS_CLIENT_PORT, projectPath, md5Code, similarPath, retryInfo, position);
			}
		} else {
			result = GClientRedirect.redirectSearch(serverIp, GranuleConstant.LOCAL_AS_CLIENT_PORT, projectPath, md5Code, similarPath, retryInfo, position);
		}
		return result;
	}

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

	// public static void main (String[]args){
	// GranuleClient gc = GranuleClient.getInstance();
	// gc.init();
	// Date d1 = new Date();
	// Timestamp ts1 = new Timestamp(d1.getTime());
	// System.out.println(ts1);
	// for(int i=0;i<30;i++){
	// gc.regisGranuleTree("abcd", null, null, null);
	// gc.regisGranuleTree("abcd", null, null, null);
	// gc.regisGranuleTree("abcd", null, null, null);
	// gc.regisGranuleTree("abcd", null, null, null);
	// gc.regisGranuleTree("abcd", null, null, null);
	// }
	// Date d2 = new Date();
	// Timestamp ts2 = new Timestamp(d2.getTime());
	// System.out.println(ts2);
	//	}
}
