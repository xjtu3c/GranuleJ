package granulej.lang;
import gui.constant.GranuleConstant;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.Socket;
import java.net.InetAddress; 

public class GranuleRegister implements Runnable {

	private String md5;
	private String projectPath;
	private String testGranuleName;
	private String testGranulePathName;
	
	
	public GranuleRegister(String md5,String projectPath,String testGranuleName,String testGranulePathName){
	  this.md5=md5;
	  this.projectPath=projectPath;
	  this.testGranuleName=testGranuleName;
	  this.testGranulePathName=testGranulePathName;		
	}	
    public void run() {
    	Socket socket=null;
    	OutputStream socketPut;
    	PrintWriter socketWriter;
		try {	
			socket=new Socket(InetAddress.getLocalHost(),GranuleConstant.LOCAL_AS_SERVER_PORT);
	        socketPut=socket.getOutputStream();
	        socketWriter=new PrintWriter(socketPut);
	        socketWriter.println(GranuleConstant.GRANULE_REGISTER);
	        socketWriter.println(md5);
	        socketWriter.println(projectPath);
	        socketWriter.println(testGranuleName);
	        socketWriter.println(testGranulePathName);
	        System.out.println("Individual registration request starts..."); 
			socketWriter.flush();          
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

