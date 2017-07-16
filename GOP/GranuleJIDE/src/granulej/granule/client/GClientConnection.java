package granulej.granule.client;

import granulej.gui.Console;
import granulej.util.Utility;
import gui.constant.GranuleConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//客户端接收模块
public class GClientConnection implements Runnable {

	private GranuleClient granuleClient;

	private static GClientConnection gcc;

	private static boolean flag = true;

	public static GClientConnection getInstance() {
		if (gcc == null) {
			gcc = new GClientConnection();
		}
		return gcc;
	}

	private GClientConnection() {
		granuleClient = GranuleClient.getInstance();
		granuleClient.init();
		// 以后再考虑活动个体的情况
		// new Thread(new ActGranuleManagement(granuleClient)).start();
	}

	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(GranuleConstant.LOCAL_AS_SERVER_PORT));
			while (flag) {
				Socket socket = serverSocket.accept();
				new Thread(new HandleSocket(socket)).start();
			}
		} catch (BindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class HandleSocket extends Thread {
		private Socket socket;

		public HandleSocket(Socket sock) {
			this.socket = sock;
		}

		public void run() {
			try {
				OutputStream outputToSocket = socket.getOutputStream();
				InputStream inputFromSocket = socket.getInputStream();
				String action = null;
				boolean flag = true;
				while (flag) {
					action = Utility.getInputFromSocket(inputFromSocket);
					if (action != null && !action.isEmpty()) {
						flag = false;
					}
				}
				String filePath = "";
				
				//测试 liyu 通信服务器
				
				//System.out.println("粒服务器服务器action is "+action);
				
				if (action.equals(GranuleConstant.GRANULE_GET)) {
					// granuleServer get file from client
					filePath = Utility.getInputFromSocket(inputFromSocket);
					handleGetAction(filePath, outputToSocket);
				} else if (action.equals(GranuleConstant.GRANULE_PUT)) {
					// granuleServer sent file to client
					filePath = Utility.getInputFromSocket(inputFromSocket);
					handlePutAction(filePath, inputFromSocket);
				} else if (action.equals(GranuleConstant.GRANULE_REGISTER)) {
					String md5 = Utility.getInputFromSocket(inputFromSocket);
					String projectPath = Utility.getInputFromSocket(inputFromSocket);
					String testGranuleName = Utility.getInputFromSocket(inputFromSocket);
					String testGranulePathName = Utility.getInputFromSocket(inputFromSocket);
					handleRegisterAction(md5, projectPath, testGranuleName, testGranulePathName);
				} else if (action.equals(GranuleConstant.GRANULE_SEARCH)) {
					String md5Code = Utility.getInputFromSocket(inputFromSocket);
					String granuleName = Utility.getInputFromSocket(inputFromSocket);
					String retryInfo = Utility.getInputFromSocket(inputFromSocket);
					String projectPath =  Utility.getInputFromSocket(inputFromSocket);
					int position = Integer.valueOf(Utility.getInputFromSocket(inputFromSocket));
					// Date date0 = new Date();
					// Timestamp t1 = new Timestamp(date0.getTime());
					// System.out.println(t1);
					String[] result = null;
					// for (int j = 0; j < 1; j++) {
					result = handleSearchAction(projectPath,md5Code, granuleName, retryInfo, position);
					// }
					// Date date1 = new Date();
					// Timestamp t2 = new Timestamp(date1.getTime());
					// System.out.println(t2);
					String feedback = null;
					if (result != null) {
						feedback = result[0] + ";" + result[1] + ";" + result[2] + ";" + result[3];
					} else {
						feedback = GranuleConstant.GRANULE_SEARCH_FAIL;
					}
					handleFeedback(feedback, outputToSocket);
				} else {
					Console.getInstance().append("Client has given a error request: " + action + "\n");
				}
				inputFromSocket.close();
				outputToSocket.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void handleGetAction(String filePath, OutputStream outputToSocket) {
			try {
				InputStream fi = new FileInputStream(new File(filePath));
				byte[] buffer = new byte[1024];
				int bytesum = 0;
				int byteread = 0;
				while ((byteread = fi.read(buffer)) != -1) {
					bytesum += byteread;
					outputToSocket.write(buffer, 0, byteread);
				}
				fi.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void handlePutAction(String filePath, InputStream inputFromSocket) {
			try {
				byte[] buffer = new byte[1024];
				int byteread = 0;
				int bytesum = 0;
				FileOutputStream fs = new FileOutputStream(filePath);
				while ((byteread = inputFromSocket.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				fs.close();
			} catch (IOException e) {
			}
		}

		public void handleRegisterAction(String md5, String projectPath, String testGranuleName, String testGranulePathName) {
			granuleClient.regisGranuleTree(md5, projectPath, testGranuleName, testGranulePathName);
		}

		public String[] handleSearchAction(String projectPath, String md5Code, String granuleName, String retryInfo, int position) {
			return granuleClient.getSimilarGranule(projectPath,md5Code, granuleName, retryInfo, position);
		}

		public void handleFeedback(String feedback, OutputStream outputToSocket) {
			PrintWriter pWriter = new PrintWriter(outputToSocket);
			pWriter.print(feedback);
			pWriter.flush();
			pWriter.close();
		}
	}

	public void setFlag(boolean gflag) {
		flag = gflag;
	}

	public static void main(String[] args) {
		new Thread(new GClientConnection()).start();
	}
}
