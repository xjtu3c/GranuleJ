import java.io.File;
import java.util.List;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ServerGetUpFileByClientThread implements Runnable {
	private List<Node> waitRegisList;
	private String ipAddr;

	public ServerGetUpFileByClientThread(List<Node> nodeList, String ip) {
		this.waitRegisList = nodeList;
		this.ipAddr = ip;
	}

	public void run() {
		int waitRegisListSize = waitRegisList.size();
		Node updateNode = null;
		NamedNodeMap attrMap = null;
		try {
			String proClientDir = null;
			String proServerDir = null;
			String md5 = "";
			String fileName = "";
			//String mfile="";
			for (int i = 0; i < waitRegisListSize; i = i + 2) {
				// copy nodeDoc
				updateNode = waitRegisList.get(i);
				attrMap = updateNode.getAttributes();
				md5 = attrMap.getNamedItem("md5").getNodeValue();
				fileName = attrMap.getNamedItem("file").getNodeValue();
				//mfile = attrMap.getNamedItem("mmap").getNodeValue();
				proClientDir = attrMap.getNamedItem("location").getNodeValue() + File.separator;
				proServerDir = Utility.getClientFileRoot(ipAddr, md5);
				File projectDir = new File(proServerDir);
				if (!projectDir.exists()) {
					projectDir.mkdirs();
				}
				//Utility.getXMLConifgFileFromClient(ipAddr,proClientDir,proServerDir,mfile);
				Utility.getXMLConifgFileFromClient(ipAddr,proClientDir,proServerDir,fileName);
				for (Node tripNode = updateNode.getFirstChild(); tripNode != null; tripNode = tripNode.getNextSibling()) {
					Utility.getClassFileFromClient(tripNode, ipAddr, proClientDir, proServerDir);
				}
			}
		} catch (Exception e) {
			// 将未传送的字节码结构写入文件，待下次重传
			HandRegisExceptionSaveThread hrst = new HandRegisExceptionSaveThread(waitRegisList);
			new Thread(hrst).start();
		}
	}
}
