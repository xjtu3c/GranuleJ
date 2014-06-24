package granulej.granule.client;

import granulej.gui.Console;
import granulej.util.Utility;
import gui.constant.GranuleConstant;

import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import org.w3c.dom.Node;

//请求转发模块
public class GClientRedirect {
	public static void redirectRegister(String serIp, int port, List<Node> waitRegisList) {
		Socket socket = Utility.createGranuleConnectionToServer(serIp, port, waitRegisList);
		if (socket != null) {
			ClientRegisToServerThread grst = new ClientRegisToServerThread(waitRegisList, socket);
			new Thread(grst).start();
		}
	}

	public static String[] redirectSearch(String serIp, int port, String projectPath, String md5Code, String similarPath, String retryInfo, int position) {
		String[] result = null;
		Socket sock = null;
		PrintWriter socketWriter = null;
		boolean flag = true;
		String feedback = "";
		int end = projectPath.lastIndexOf(File.separator);
		projectPath = projectPath.substring(0, end) + File.separator;
		try {
			sock = new Socket(serIp, port);
			socketWriter = new PrintWriter(sock.getOutputStream());
			socketWriter.println(GranuleConstant.GRANULE_SEARCH);
			socketWriter.println(projectPath);
			socketWriter.println(md5Code);
			socketWriter.println(similarPath);
			if (retryInfo != null) {
				socketWriter.println(retryInfo);
				socketWriter.println(position);
			}
			socketWriter.flush();
			while (flag) {
				feedback = Utility.getAllInputFromSocket(sock.getInputStream());
				if (feedback != null && !feedback.isEmpty()) {
					flag = false;
				}
			}
			// 远程查找失败
			if (GranuleConstant.GRANULE_SEARCH_FAIL.equals(feedback.trim())) {
				result = null;
			} else {
				// 远程查找成功
				result = feedback.split(";");
			}
			socketWriter.close();
			sock.close();
		} catch (Exception e) {
			// e.printStackTrace();
			Console.getInstance().append("The granule server is not started !" + "\n");
		}
		return result;
	}

}
