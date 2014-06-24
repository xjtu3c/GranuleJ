import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;


granule gCityService(CityService){
	external String city_c;
	{
		return city_c.equals("Xian");
	}
	
}
class CityService within gCityService {
		private AudioInputStream audioInputStream;
		private SourceDataLine sourceDataLine;
		private byte tempBuffer[] = new byte[320];
		
		public void preparedVoice(AudioInputStream audInputStream,SourceDataLine souDataLine){
			audioInputStream=audInputStream;
			sourceDataLine=souDataLine;
		}
		
		public static void exitService(){
			if(city_service!=null){
				if(city_service.isAlive()){
					city_service.stop();
					}
				city_service=null;
			}
		}
		
		public void run(){
			if(command.equals("voice")){
				try {
					int cnt;
					while ((cnt = audioInputStream.read(tempBuffer, 0,tempBuffer.length)) != -1) {
						if (cnt > 0) {
							sourceDataLine.write(tempBuffer, 0, cnt);
							}
						}
					sourceDataLine.drain();
					sourceDataLine.close();
				}catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	}
