package granulej.granule.client;

import granulej.util.Utility;
import gui.constant.GranuleConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.w3c.dom.Node;

public class ClientRegisToServerThread implements Runnable {
	private List<Node> waitRegisList;

	private Socket sock;

	public ClientRegisToServerThread(List<Node> waitRegisList, Socket socket) {
		this.waitRegisList = waitRegisList;
		this.sock = socket;
	}

	public void run() {
		// 完成本地的注册工作
		String filePath = Utility.getClientDir() + "update.xml";
		File temp = new File(filePath);
		if (temp.exists()) {
			temp.delete();
		}
		try {
			synchronized (this) {
				Utility.writeNodeListToXML(waitRegisList, filePath);
			}
			OutputStream socketOutput = null;
			PrintWriter socketWriter = null;
			try {
				socketOutput = sock.getOutputStream();
				socketWriter = new PrintWriter(socketOutput);
				socketWriter.println(GranuleConstant.GRANULE_REGISTER);
				socketWriter.println(filePath);
				socketWriter.flush();
				InputStream fi = new FileInputStream(new File(filePath));
				byte[] buffer = new byte[1024];
				int bytesum = 0;
				int byteread = 0;
				while ((byteread = fi.read(buffer)) != -1) {
					bytesum += byteread;
					socketOutput.write(buffer, 0, byteread);
				}
				fi.close();
				socketOutput.close();
				socketWriter.close();
				sock.close();
			} catch (Exception e) {
				// 相似粒注册通信异常的处理,存储信息
				HandRegisExceptionSaveThread hrct = new HandRegisExceptionSaveThread(waitRegisList);
				new Thread(hrct).start();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
}
