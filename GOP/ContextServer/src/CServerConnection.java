
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CServerConnection implements Runnable {
	private static CServerConnection csc;

	private static boolean flag = true;

	private ContextServer contextServer;

	private static String currenIp;
	private static String currenPort;
	
	public static CServerConnection getInstance() {
		if (csc == null) {
			csc = new CServerConnection();
			//System.out.println("new a cserver!");
		}
		return csc;
	}

	private CServerConnection() {
		contextServer = ContextServer.getInstance();
		contextServer.init();
		//System.out.println("init cserver!");
	}

	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(ConstantVariable.CONTEXT_SERVER_PORT));
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new HandleSocket(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class HandleSocket implements Runnable {
		private Socket socket;

		public HandleSocket(Socket sock) {
			this.socket = sock;
		}

		public void run() {
			try {
				OutputStream outputToSocket = socket.getOutputStream();
				InputStream inputFromSocket = socket.getInputStream();
				String action = Utility.getInputFromSocket(inputFromSocket);
				String contextStr = null;
				System.out.println("上下文服务器连接：action is :"+ action);
				System.out.println("上下文服务器连接 ：inputFromSocket is :"+inputFromSocket);
				if (ConstantVariable.CONTEXT_REG.equals(action)) {
					// register context to server
					System.out.println("上下文服务器连接：ConstantVariable.CONTEXT_REG is :"+ConstantVariable.CONTEXT_REG);
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					handleRegAction(contextStr);
				} else if (ConstantVariable.CONTEXT_UPDATE.equals(action)) {
					// update context to server
					System.out.println("上下文服务器连接：ConstantVariable.CONTEXT_UPDATE is :"+ConstantVariable.CONTEXT_UPDATE);
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					handleUpdateAction(contextStr);
				} else if (ConstantVariable.CONTEXT_CLIENT_INT.equals(action)) {
					System.out.println("上下文服务器连接：ConstantVariable.CONTEXT_CLIENT_INT is :"+ConstantVariable.CONTEXT_CLIENT_INT);
					// client request to init its context info
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					String port = Utility.getInputFromSocket(inputFromSocket);
					String ip = socket.getInetAddress().getHostAddress();
					currenIp = ip;
					currenPort = port;
					handleClientRegis(contextStr, port, ip);
					handleInitAction(contextStr, outputToSocket);
				} else if (ConstantVariable.CONTEXT_GET.equals(action)) {
					System.out.println("上下文服务器连接：ConstantVariable.CONTEXT_GET is :"+ConstantVariable.CONTEXT_GET);
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					handleGetAction(contextStr, outputToSocket);
				} else if (ConstantVariable.CONTEXT_CLIENT_DES.equals(action)) {
					System.out.println("上下文服务器连接：ConstantVariable.CONTEXT_CLIENT_DES is :"+ConstantVariable.CONTEXT_CLIENT_DES);
					String ip = Utility.getInputFromSocket(inputFromSocket);
					handleClientRemoved(ip);
				}
				inputFromSocket.close();
				outputToSocket.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void handleRegAction(String contextStr) {
			contextServer.addContexts(contextStr);
		}

		public void handleUpdateAction(String contextStr) {
			contextServer.updateContexts(contextStr);
		}

		public void handleClientRegis(String contextStr, String port, String ip) {
			contextServer.handleClientRegis(contextStr, port, ip);
		}

		public void handleClientRemoved(String ip) {
			contextServer.removeClient(ip);
		}

		public void handleInitAction(String contextStr, OutputStream outputToSocket) {
			String feedback = contextServer.getContext(contextStr);
			handleFeedback(feedback, outputToSocket);
		}

		public void handleGetAction(String contextStr, OutputStream outputToSocket) {
			String feedback = contextServer.getContext(contextStr);
			handleFeedback(feedback, outputToSocket);
		}

		public void handleFeedback(String feedback, OutputStream outputToSocket) {
			PrintWriter pWriter = new PrintWriter(outputToSocket);
			pWriter.print(feedback);
			pWriter.flush();
			pWriter.close();
		}
	}
	
	public static String getPort(){
		return currenPort;
	}
	public static String getIp(){
		return currenIp;
	}

/*	public static void main(String[] args) {
		System.out.println("CServerConnection is running!!");
		new Thread(CServerConnection.getInstance()).start();
	}*/
}
