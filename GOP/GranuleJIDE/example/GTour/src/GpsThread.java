import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class GpsThread extends Thread{

	public GpsThread(){
	}
	public void GetContextByTime(){
		try {
			Socket socket=new Socket("127.0.0.1",7890);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			pw.println("whereami"+"\n\n");
			pw.flush();
			String line=br.readLine();
			String[] temp=line.split(",");
			String[] nums=new String[6];
			for(int i=0;i<nums.length;i++){
				nums[i]="";
			}
			for(int i=0;i<temp.length;i++){
				nums[i]=temp[i].equals("null")?"":temp[i];
			}
			for(int i=0;i<nums.length;i++){
				nums[i]=((nums[i]==null)||(nums[i].equals("null")))?"":nums[i];
			}
			
			Util.GpsInfo.put("city", nums[0]==null?"":nums[0]);
			Util.GpsInfo.put("park", nums[1]==null?"":nums[1]);
			Util.GpsInfo.put("scene", nums[2]==null?"":nums[2]);
			Util.GpsInfo.put("museum", nums[3]==null?"":nums[3]);
			Util.GpsInfo.put("room", nums[4]==null?"":nums[4]);
			Util.GpsInfo.put("net", nums[5]==null?"":nums[5]);
			
//			city_c=nums[0]==null?"":nums[0];
//			park_c=nums[1]==null?"":nums[1];
//			scene_c=nums[2]==null?"":nums[2];
//			museum_c=nums[3]==null?"":nums[3];
//			room_c=nums[4]==null?"":nums[4];
//			net_c=nums[5]==null?"":nums[5];
			
			for(String ele:Util.Menus){
				System.out.println(ele+" : "+Util.GpsInfo.get(ele)+" , "+Util.GpsInfo.get(ele+"_old"));
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			GetContextByTime();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
