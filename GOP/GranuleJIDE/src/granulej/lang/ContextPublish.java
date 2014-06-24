package granulej.lang;

import gui.constant.ContextConstant;
import granulej.util.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;

public class ContextPublish {
	private String con_seq;

	private int port;

    public ContextPublish(String con_seq)
    {
    	this.con_seq=con_seq;
    }	
    
    public void sendThread()
    {
    	ContextUpdate cp=new ContextUpdate(this);
    }

	public void run() {
		if("".equals(con_seq))
		return ;
		Socket socket = null;
		OutputStream socketPut;
		PrintWriter socketWriter;
		try {
			socket = new Socket(InetAddress.getLocalHost(), ContextConstant.CONTEXT_CLIENT_PORT);
			socketPut = socket.getOutputStream();
			InputStream inputFromSocket = socket.getInputStream();
			socketWriter = new PrintWriter(socketPut);
			socketWriter.println(ContextConstant.CONTEXT_PROGRAM_INIT);
			socketWriter.println(con_seq);
			socketWriter.println(port);
			socketWriter.flush();
			boolean flag = true;
			String constr = null;
			while (flag) {
				constr = Utility.getAllInputFromSocket(inputFromSocket);
				if (!constr.isEmpty()) {
					flag = false;
				}
			}
			updateContextInitValue(constr);
			socketPut.close();
			socketWriter.close();
			socket.close();
		} catch (BindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}

	public void updateContextInitValue(String constr) {
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
			    GopContext.modifyContext(context[0], context[1]);
			}
		}
	}
	

	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;

	} 
}
