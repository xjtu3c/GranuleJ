package granulej.context.client;

import granulej.util.Utility;
import gui.constant.ContextConstant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CClientConnection implements Runnable {

	private ContextClient contextClient;

	private static CClientConnection ccc;

	private static boolean flag = true;

	public static CClientConnection getInstance() {
		if (ccc == null) {
			ccc = new CClientConnection();
		}
		return ccc;
	}

	private CClientConnection() {
		contextClient = ContextClient.getInstance();
		contextClient.init();
	}

	public void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(ContextConstant.CONTEXT_CLIENT_PORT));
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
				String contextStr = "";
				String contextFrom = "";
				String port = "";
				
				//ceshi 通信 服务器				
				//System.out.println("上下文服务器action is "+action);
				
				if (ContextConstant.CONTEXT_UPDATE.equals(action)) {
					contextFrom = Utility.getInputFromSocket(inputFromSocket);
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					handleUpdateAction(contextStr, contextFrom);
					//测试
					//上下文正确，但是端口这个问题不知道 handleUpdateAction
					//System.out.println("CClientConnection==== client context connection to server is "+contextStr+ "and the port is "+ port);
					
				} else if (ContextConstant.CONTEXT_PROGRAM_INIT.equals(action)) {
					// program init to get context
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					port = Utility.getInputFromSocket(inputFromSocket);
					handleInitAction(contextStr, outputToSocket, port);
					//System.out.println("CClientConnection==== client context init to program is "+contextStr + "and the port is "+ port);
				} else if (ContextConstant.CONTEXT_GET.equals(action)) {
					// program get context when running
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					port = Utility.getInputFromSocket(inputFromSocket);
					handleGetAction(contextStr, outputToSocket, port);
				} else if (ContextConstant.CONTEXT_PROGRAM_DES.equals(action)) {
					port = Utility.getInputFromSocket(inputFromSocket);
					handleDestoryAction(port);
				}
				inputFromSocket.close();
				outputToSocket.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void handleUpdateAction(String contextStr, String contextFrom) {
			contextClient.updateContexts(contextStr, contextFrom);
		}

		public void handleInitAction(String contextStr, OutputStream outputToSocket, String port) {
			String feedback = contextClient.getInitContext(contextStr, port);
			if (feedback == null || feedback.isEmpty()) {
				feedback = ContextConstant.CONTEXT_GET_FAIL;
			}
			HandleFeedback(feedback, outputToSocket);
		}

		public void handleGetAction(String contextStr, OutputStream outputToSocket, String port) {
			String feedback = contextClient.getContext(contextStr, port);
			HandleFeedback(feedback, outputToSocket);
		}

		public void handleDestoryAction(String port) {
			contextClient.cleanListenMap(port);
		}

		public void HandleFeedback(String feedback, OutputStream outputToSocket) {
			PrintWriter pWriter = new PrintWriter(outputToSocket);
			pWriter.println(feedback);
			pWriter.flush();
			pWriter.close();
		}
	}

	public void setFlag(boolean gflag) {
		flag = gflag;
	}
}
