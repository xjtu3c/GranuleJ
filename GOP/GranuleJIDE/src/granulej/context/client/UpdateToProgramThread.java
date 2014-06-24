package granulej.context.client;

import gui.constant.ContextConstant;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class UpdateToProgramThread implements Runnable {
	// 更新到程序端
	private String port;

	private String contextStr;

	public UpdateToProgramThread(String portNo, String contextString) {
		this.port = portNo;
		this.contextStr = contextString;
	}

	public void run() {
		try {
			Socket sock = new Socket(ContextConstant.CONTEXT_SERVER_IP, Integer.valueOf(port));
			OutputStream socketOutput = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketOutput);
			socketWriter.println(ContextConstant.CONTEXT_UPDATE);
			socketWriter.println(contextStr);
			socketWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
