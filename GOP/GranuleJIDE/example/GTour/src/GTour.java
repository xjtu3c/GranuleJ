import javax.swing.JFrame;

external String city_c;
external String park_c;
external String scene_c;
external String museum_c;
external String room_c;
external String net_c;

public class GTour {
	
	public static void EnterTour(JFrame frame){
		for(String ele:Util.Menus){
			Util.GpsInfo.put(ele,"");
			Util.GpsInfo.put(ele+"_old","");
		}
		
		GpsThread gps_thread=new GpsThread();
		gps_thread.start();
		
		TimingDetection timing_detection=new TimingDetection(frame);
		timing_detection.start();
		
	}
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(236, 447);
		frame.setResizable(false);
		frame.setVisible(true);
		GTour.EnterTour(frame);
	}
}

