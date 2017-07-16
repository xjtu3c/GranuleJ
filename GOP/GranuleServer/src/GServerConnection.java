import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class GServerConnection implements Runnable {

	private static GServerConnection gsc;

	private GranuleServer granuleServer;

	public static GServerConnection getInstance() {
		if (gsc == null) {
			gsc = new GServerConnection();
		}
		return gsc;
	}

	private GServerConnection() {
		granuleServer = GranuleServer.getInstance();
		granuleServer.init();
		// 以后再考虑活动个体的情况
		// new Thread(new ActGranuleManagement(granuleServer)).start();
	}

	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(ConstantVariable.REMOTE_AS_SERVER_PORT));
			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				new Thread(new HandleSocket(socket)).start();
			}
		} catch (Exception e) {
			System.out.println("Granule server can not be connected at port:" + ConstantVariable.REMOTE_AS_SERVER_PORT);
		}
	}

	public class HandleSocket extends Thread {
		private Socket socket = null;

		public HandleSocket(Socket sock) {
			this.socket = sock;
		}

		public synchronized void run() {
			try {
				OutputStream outputToSocket = socket.getOutputStream();
				InputStream inputFromSocket = socket.getInputStream();
				String action = Utility.getInputFromSocket(inputFromSocket);

				String filePath = "";
				
				//liyu ceshi 服务器的连通
				System.out.println("粒服务器连接：action is :"+ action);
				System.out.println("粒服务器连接 ：inputFromSocket is :"+inputFromSocket);
				
				if (action.equals(ConstantVariable.ACTION_GET)) {
					// local get file from server
					System.out.println("粒服务器连接 ：ConstantVariable.ACTION_GET is :"+ConstantVariable.ACTION_GET);
					filePath = Utility.getInputFromSocket(inputFromSocket);
					handleGetAction(filePath, outputToSocket);
				} else if (action.equals(ConstantVariable.ACTION_PUT)) {
					// local sent class file to server
					System.out.println("粒服务器连接 ：ConstantVariable.ACTION_PUT is :"+ConstantVariable.ACTION_PUT);
					filePath = Utility.getInputFromSocket(inputFromSocket);
					handlerPutAction(filePath, inputFromSocket);
				} else if (action.equals(ConstantVariable.ACTION_REGISTER)) {
					// local sent update xml config file to server
					System.out.println("粒服务器连接 ：ConstantVariable.ACTION_REGISTER is :"+ConstantVariable.ACTION_REGISTER);
					filePath = Utility.getInputFromSocket(inputFromSocket);
					handleRegisAction(filePath, inputFromSocket, socket);
				} else if (action.equals(ConstantVariable.ACTION_SEARCH)) {
					// find the similar granule
					System.out.println("粒服务器连接 ：ConstantVariable.ACTION_SEARCH is :"+ConstantVariable.ACTION_SEARCH);
					String projectPath = Utility.getInputFromSocket(inputFromSocket);
					String md5Code = Utility.getInputFromSocket(inputFromSocket);
					String similarPath = Utility.getInputFromSocket(inputFromSocket);
					String retryInfo = Utility.getInputFromSocket(inputFromSocket);
					int position = Integer.valueOf(Utility.getInputFromSocket(inputFromSocket));
					handleSearchAction(md5Code, similarPath, socket, retryInfo, position, projectPath);
				} else if (action.equals(ConstantVariable.ACTION_ACTIVE)) {
					System.out.println("粒服务器连接 ：ConstantVariable.ACTION_ACTIVE is :"+ConstantVariable.ACTION_ACTIVE);
					// get all active client
					PrintWriter socketWriter = new PrintWriter(outputToSocket);
					// socketWriter.println(Utility.activeToString(granuleServer.getActive()));
					socketWriter.close();
				} else if (action.equals(ConstantVariable.ACTION_BASE)) {
					System.out.println("粒服务器连接 ：ConstantVariable.ACTION_BASE is :"+ConstantVariable.ACTION_BASE);
					// sent server config file to client
					handleBaseAction(outputToSocket);
				} else {
					System.out.println("Server send an error request: " + action);
				}
				outputToSocket.close();
				inputFromSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void handleGetAction(String filePath, OutputStream outputToSocket) {
			try {
				// sent server file to client
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

		public void handlerPutAction(String filePath, InputStream inputFromSocket) {
			try {
				// put client file to server
				byte[] buffer = new byte[1024];
				int byteread = 0;
				int bytesum = 0;
				FileOutputStream fs = new FileOutputStream(Utility.getServerDir() + filePath);
				while ((byteread = inputFromSocket.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				fs.close();
			} catch (IOException e) {
				System.out.println("Error reading from file \"" + filePath.substring(filePath.lastIndexOf("/") + 1) + "\"");
			}
		}

		public void handleRegisAction(String filePath, InputStream inputFromSocket, Socket socket) {
			try {
				// update new granule to server
				byte[] buffer = new byte[1024];
				int byteread = 0;
				int bytesum = 0;
				String tempPath = Utility.getServerDir() + "temp.xml";
				File temp = new File(tempPath);
				if (temp.exists()) {
					temp.delete();
				}
				temp = new File(tempPath);
				FileOutputStream fs = new FileOutputStream(temp);
				while ((byteread = inputFromSocket.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				Document updateNodesDoc = Utility.loadFromXML(tempPath);
				Node node = updateNodesDoc.getElementsByTagName("update_node_list").item(0);
				String ip = node.getAttributes().getNamedItem("ip").getNodeValue();
				List<Node> updateList = new ArrayList<Node>();
				Node updateNode = null;
				for (updateNode = node.getFirstChild(); updateNode != null; updateNode = updateNode.getNextSibling()) {
					if (updateNode.getNodeType() == Node.ELEMENT_NODE) {
						updateList.add(updateNode);
					}
				}
				granuleServer.registerToServer(updateList, ip);
				temp.delete();
				fs.close();
				// 是否添加活动个体
				// String fileName =
				// filePath.substring(filePath.lastIndexOf("/") +
				// 1);
				// handleIndActive(socket, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void handleSearchAction(String md5Code, String similarPath, Socket socket, String retryInfo, int position, String projectPath) {
			granuleServer.getSimilarGranule(md5Code, similarPath, socket, socket.getInetAddress().getHostAddress(), retryInfo, position, projectPath);
			
			//System.out.println("gs ip : "+socket.getInetAddress().getHostAddress());
			// 暂时不考虑
			// handleIndActive(socket, md5Code);
		}

		public void handleBaseAction(OutputStream outputToSocket
				) {
			try {
				InputStream fi = new FileInputStream(new File(Utility.getServerFileConfigPath()));
				byte[] buffer = new byte[1212];
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

		public void handleIndActive(Socket socket, String fileName) {
			// String ip = socket.getInetAddress().getHostAddress();
			// // ActiveIndividual individual = granuleServer.getActive().get(ip
			// +
			// ":" + fileName);
			// if (individual != null) {
			// individual.setStartTime(new Date());
			// } else {
			// ActiveIndividual indNew = new ActiveIndividual();
			// indNew.setIp(ip);
			// indNew.setStartTime(new Date());
			// indNew.setFile(fileName);
			// // granuleServer.getActive().put(ip + ":" + fileName, indNew);
			// }
		}
	}

/*	public static void main(String[] args) {
		System.out.println("GServerConnection is running!!");
		new Thread(GServerConnection.getInstance()).start();
	}*/
}
