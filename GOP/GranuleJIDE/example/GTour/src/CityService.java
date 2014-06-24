import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JPanel;

public class CityService extends SiteService {
	private JPanel panel;
	private City city;
	protected String command;

	public CityService(JPanel pane, City cit, String comman) {
		panel = pane;
		city = cit;
		command = comman;
	}

	protected static CityService city_service = null;

	public static CityService CreateCityService(JPanel panel, City city, String command) {
		if (city_service != null) {
			if (city_service.isAlive()) {
				city_service.stop();
			}
			city_service = null;
		}
		city_service = new CityService(panel, city, command);
		return city_service;
	}

	public void preparedVoice(AudioInputStream audioInputStream, SourceDataLine sourceDataLine) {
	}

	public static void exitService() {
	}

	public void run() {

	}
}
