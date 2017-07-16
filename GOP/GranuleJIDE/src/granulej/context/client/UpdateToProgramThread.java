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
			
//			Socket sock = new Socket(ContextConstant.CONTEXT_SERVER_IP, ContextConstant.CONTEXT_SERVER_PORT);
			System.out.println("UpdateToProgramThread ****  the update to program context is : "+contextStr +"and the port is  "+port);
			OutputStream socketOutput = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketOutput);
			socketWriter.println(ContextConstant.CONTEXT_UPDATE);
			socketWriter.println(contextStr);
			//测试
			System.out.println("UpdateToProgramThread ****  the update to program context is : "+contextStr +"and the port is  "+port);
			socketWriter.flush();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
