import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class ProcessGps implements Runnable {
	private String cityInfo;
	private String parkInfo;
	private String sceneInfo;
	private String museumInfo;
	private String roomInfo;
	private String netInfo;
	private Socket socket;

	public ProcessGps(String city, String park, String scene, String museum, String room, String net, Socket s) {
		this.cityInfo = city;
		this.parkInfo = park;
		this.sceneInfo = scene;
		this.museumInfo = museum;
		this.roomInfo = room;
		this.netInfo = net;
		this.socket = s;
	}

	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			String line = br.readLine();
			if (line != null && line.equals("whereami")) {
				pw.println(this.cityInfo + "," + this.parkInfo + "," + this.sceneInfo + "," + this.museumInfo + "," + this.roomInfo + "," + this.netInfo);
			} else {
				pw.println("error");
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}