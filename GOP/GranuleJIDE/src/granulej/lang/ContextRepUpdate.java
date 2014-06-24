package granulej.lang;

import granulej.util.Utility;
import gui.constant.ContextConstant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;

public class ContextRepUpdate implements Runnable {

	private String con_seq;

	private int port;

	private IndividualInfo in_info;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ContextRepUpdate(String con_seq) {
		this.con_seq = con_seq;
		this.in_info = IndividualInfo.getInstance();
	}

	public void run() {
		if("".equals(con_seq))
		return ;
		Socket socket = null;
		OutputStream socketPut;
		PrintWriter socketWriter;
		try {
			port=in_info.getPort();
			socket = new Socket(InetAddress.getLocalHost(), ContextConstant.CONTEXT_CLIENT_PORT);
			socketPut = socket.getOutputStream();
			InputStream inputFromSocket = socket.getInputStream();
			socketWriter = new PrintWriter(socketPut);
			socketWriter.println(ContextConstant.CONTEXT_GET);
			socketWriter.println(con_seq);
			socketWriter.println(port);
			socketWriter.flush();
			boolean flag = true;
			String constr = null;
			while (flag) {
				constr = Utility.getInputFromSocket(inputFromSocket);
				if (!constr.isEmpty()) {
					flag = false;
				}
			}
			updateContext(constr);
			socketPut.close();
			socketWriter.close();
			socket.close();
		} catch (BindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCon_seq() {
		return con_seq;
	}

	public void setCon_seq(String con_seq) {
		this.con_seq = con_seq;
	}

	public void updateContext(String constr) {
		if (ContextConstant.CONTEXT_GET_FAIL.equals(constr.trim())) {
			return;
		}
		if (constr != null && !constr.equals("")) {
			String[] contexts = constr.split(";");
			String[] context = new String[2];
			for (int i = 0; i < contexts.length; i++) {
				if(contexts[i].endsWith(":")){
				 context[0]=contexts[i];
				 context[1]="";					
			     }
			    else {
				  context = contexts[i].split(":");
			    }        
				GopContext.addContext(context[0], context[1]);
			}
		}
	}
}
