package granulej.lang;

import granulej.util.Utility;
import gui.constant.ContextConstant;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ContextUpdate extends Thread {
	private boolean isShutdown;

	private ServerSocket serverSocket = null;

	private Socket socket = null;

	private static Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

	private static int map_length = 10;

	private int port;

	private ContextPublish cp;
	
	private IndividualInfo in_info;

	static {
		for (int i = 0; i < map_length; i++) {
			map.put(new Integer(ContextConstant.LOCAL_AS_CONTEXT_PORT + i), false);
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	//	取出哈希表中空闲的端口号
	public int getIdlePort() {
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Boolean val = (Boolean) entry.getValue();
			if (val.equals(Boolean.FALSE)) {
				return ((Integer) entry.getKey()).intValue();
			}
		}
		return 0;
	}

	//设置忙标志
	public void setBusyMark(Integer port) {
		if (map.containsKey(port)) {
			map.put(port, true);
		}
	}

	//设置空闲标志
	public void setIdleMark(Integer port) {
		if (map.containsKey(port))
			map.put(port, false);
	}

	public ContextUpdate(ContextPublish cp) {
		this.cp = cp;
		in_info=IndividualInfo.getInstance();
		GVMInit.getInstance().setContextUp(this);
		isShutdown = true;
		setDaemon(true);
		getVailablePort();
		start();
	}
 
	public void getVailablePort()
	{
		port = getIdlePort();
		setBusyMark(port);
		cp.setPort(port);
		in_info.putPort(port);
	}
	
	//程序结束时，断开侦听
	public void shutDown() {
		Thread t=new HandleTerminate(port);
		t.start();
		isShutdown = false;
		setIdleMark(port);	
	}

	//初始启动ServerSocket,并一直监听上下文的变化
	public void run() {
		try {
			serverSocket = new ServerSocket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(port));
			System.out.println("Context information is sending for server side ...");
			while (isShutdown) {
				socket = serverSocket.accept();
				new Thread(new HandleSocket(socket)).start();
			}
		} catch (BindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//变化的上下文更新内存中的列表
	public void updateContextList(String result) {
		try {
			String[] res = result.split(";");
			int len = res.length;
			String[] con = new String[2];
			for (int i = 0; i < len; i++) {
				con = res[i].split(":");
				updateContextValue(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateContextValue(String[] context) {
		if (context == null || context.length < 2)
			return;
		String name = context[0];
		HashMap<String, Context> c_list = GopContext.getContexts();
		if (c_list.containsKey(name)) {
			Context ct = (Context) c_list.get(name);
			ct.setValue(context[1]);
			c_list.put(name, ct);
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
				String action = Utility.getInputFromSocket(inputFromSocket);
				String contextStr = "";
				if (ContextConstant.CONTEXT_UPDATE.equals(action)) {
					contextStr = Utility.getInputFromSocket(inputFromSocket);
					handleUpdateAction(contextStr);
				}
				inputFromSocket.close();
				outputToSocket.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void handleUpdateAction(String contextStr) {
			updateContextList(contextStr);
		}
	}

	  public class HandleTerminate extends Thread {
		private int portp;

		public HandleTerminate(int port) {
			this.portp = port;
		}

		public void run() {
			Socket socket = null;
			OutputStream socketPut;
			PrintWriter socketWriter;
			//System.out.println("Shutting down ...");
			try {
				socket = new Socket(InetAddress.getLocalHost(), ContextConstant.CONTEXT_CLIENT_PORT);
				socketPut = socket.getOutputStream();
				socketWriter = new PrintWriter(socketPut);
				socketWriter.println(ContextConstant.CONTEXT_PROGRAM_DES);
				socketWriter.println(portp);
				socketWriter.flush();
				//System.out.println("Context information is not listening ...");
				socketPut.close();
				socketWriter.close();
				socket.close();
			} catch (BindException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		try {
			for (int i = 0; i < 10; i++) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}