package granulej.net.gvm.client;

import granulej.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import config.GUIConfig;

public class GVMClient {

	private static final long serialVersionUID = 1L;
	private String serverIp;
	private String clientIp;

	protected int hostPort = 1212;
	protected InputStream socketGet;
	protected OutputStream socketPut;
	protected PrintWriter socketWriter;
	protected Socket server;
	private GUIConfig config;

	protected GVMClient(String ip, GUIConfig c) {
		serverIp = ip;
		config = c;
		clientIp = Utility.getIp();
	}

	public String getServerIp() {
		return serverIp;
	}

	private void setUpConnection() {
		try {
			server = new Socket(serverIp, hostPort);

			socketGet = server.getInputStream();
			socketPut = server.getOutputStream();
			socketWriter = new PrintWriter(server.getOutputStream());
		} catch (UnknownHostException e) {
			System.out
					.println("Error setting up socket connection: unknown host at "
							+ serverIp + ":" + hostPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void tearDownConnection() {
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("Error tearing down socket connection: " + e);
		}
	}

	private synchronized void sendFileClient(String clsName) {
		try {
			setUpConnection();
			socketWriter.println("put");
			socketWriter.println(clientIp);
			socketWriter.println(config.getProjectPath());
			socketWriter.println(clsName);
			socketWriter.flush();
			System.out.println("Client send file " + clsName + " to "
					+ serverIp);
			InputStream fi = new FileInputStream(new File(clsName));
			byte[] buffer = new byte[1212];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = fi.read(buffer)) != -1) {
				bytesum += byteread;
				socketPut.write(buffer, 0, byteread);
			}
			System.out.println("Client sent " + bytesum + " byte");
			fi.close();
			tearDownConnection();
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("Error sending from file "
							+ clsName.substring(clsName
									.lastIndexOf(File.separator) + 1));
		}
	}

	
	public void sendFile(String name){
		if(!name.endsWith(".class") && !name.endsWith("tree.xml"))
			return;
		sendFileClient(name);
	}
	
	public void sendDirectory(String path){
		File dirFile = new File(path);
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				sendFile(files[i].getAbsolutePath());
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				sendDirectory(files[i].getAbsolutePath());
			}
		}
	}
	public void recuriveSendFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		} else {
			if (file.isFile()) {
				sendFile(path);
			} else {
				sendDirectory(path);
			}
		}
	}

	public static void main(String args[]) {
		GUIConfig c = new GUIConfig();
		c.setProjectPath("E:\\logger");
		new GVMClient("202.117.10.241", c).recuriveSendFile("E:\\logger");
	}
}
