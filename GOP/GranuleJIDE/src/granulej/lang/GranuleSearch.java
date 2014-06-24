package granulej.lang;

import granulej.util.Utility;
import gui.constant.GranuleConstant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;

public class GranuleSearch {

	private SendPacket send_packet;

	private String result = null;

	public GranuleSearch(SendPacket send_packet) {
		this.send_packet = send_packet;
	}

	public void SimilarLookup() {
		Socket socket = null;
		OutputStream socketPut;
		PrintWriter socketWriter;
		try {
			socket = new Socket(InetAddress.getLocalHost(), GranuleConstant.LOCAL_AS_SERVER_PORT);
			socketPut = socket.getOutputStream();
			String result = null;	
			boolean flag=true;
			socketWriter = new PrintWriter(socketPut);
			InputStream inputFromSocket = socket.getInputStream();
			socketWriter.println(GranuleConstant.GRANULE_SEARCH);
			socketWriter.println(send_packet.getMd5Code());
			socketWriter.println(send_packet.getGranuleName());
			socketWriter.println(send_packet.getRetryInfo());
			socketWriter.println(IndividualInfo.getInstance().getWorkDirectory());
			socketWriter.println(send_packet.getPosition());			
            System.out.println("Granule searching for unfit granule:" + send_packet.getGranuleName() + " of individual:" + send_packet.getMd5Code());
			socketWriter.flush();
			while (flag) {
				result = Utility.getAllInputFromSocket(inputFromSocket);
				if (!result.isEmpty()) {
					flag = false;
				}
			}
			setLookupResult(result);
			System.out.println("Result is :"+result);
			inputFromSocket.close();
			socketPut.close();
			socketWriter.close();
			socket.close();
		} catch (BindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//接收到的字符串
	public String getLookupResult() {
		return result;
	}

	public void setLookupResult(String res) {
		this.result = res;
	}

}
