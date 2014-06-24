package granulej.context.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import granulej.util.Utility;
import gui.constant.ContextConstant;

public class UpdateToServerThread implements Runnable {
	// 更新到服务器端
	private String contextStr;

	private String operationType;

	public UpdateToServerThread(String cStr, String opType) {
		this.contextStr = cStr;
		this.operationType = opType;
	}

	public void run() {
		try {
			Socket sock = Utility.createContextConnectionToServer(ContextConstant.CONTEXT_SERVER_IP, ContextConstant.CONTEXT_SERVER_PORT);
			OutputStream socketOutput = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketOutput);
			socketWriter.println(operationType);
			socketWriter.println(contextStr);
			socketWriter.flush();
			socketWriter.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
