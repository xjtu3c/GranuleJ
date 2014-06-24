import javax.swing.JFrame;

public class TimingDetection extends Thread {
	private JFrame frame;
	private Site site = null;
	// private int i = 0;
	// private int index = i;
	private static int oldTypeSelected = 0;

	public TimingDetection(JFrame frame) {
		this.frame = frame;
	}
	public int getTypeIndex() {
		int i = -1;
		for (int k = Util.Menus.length - 1; k >= 0; k--) {
			if (Util.GpsInfo.get(Util.Menus[k]) == null || ((String)Util.GpsInfo.get(Util.Menus[k])).length() == 0)
				continue;
			i = k;
			break;
		}
		return i;
	}
	public void clearTypeInfo(int i) {
		for (int j = i + 1; j < Util.Menus.length; j++) {
			if (j < Util.Menus.length && j >= 0) {
				Util.GpsInfo.put(Util.Menus[j], "");
				Util.GpsInfo.put(Util.Menus[j] + "_old", "");
			}
		}
	}

	public boolean isSameAsOld(int i) {
		if (i == -1) {
			return true;
		}
		clearTypeInfo(i);
		System.out.println("Before return : " + i + " , #" + Util.GpsInfo.get(Util.Menus[i]) + "# , #" + Util.GpsInfo.get(Util.Menus[i] + "_old") + "#");
		return Util.GpsInfo.get(Util.Menus[i]).equals(Util.GpsInfo.get(Util.Menus[i] + "_old")) && (oldTypeSelected == i);
	}

	public void run() {

		// if (Util.GpsInfo.get("city") != null &&
		// Util.GpsInfo.get("city").length() == 0) {
		if (Util.GpsInfo.get("city") != null && ((String)Util.GpsInfo.get("city")).length() != 0) {
			site = new City();
			site.setName((String)Util.GpsInfo.get("city"));
			site.setFrame(frame);
			site.enter();
			site.createUI();
		}

		while (true) {
			int j = getTypeIndex();
			if (!isSameAsOld(j)) {
			     // try {
				// Thread.sleep(2500);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				// } else {
				if (site != null) {
					site.exit();
				}
				oldTypeSelected = j;
				switch (j) {
				case 0:
					site = new City();
					site.setName((String)(Util.GpsInfo.get("city")));
					break;
				case 1:
					site = new Park();
					site.setName((String)(Util.GpsInfo.get("park")));
					break;
				case 2:
					site = new Scene();
					site.setName((String)Util.GpsInfo.get("sence"));
					break;
				case 3:
					site = new Museum();
					site.setName((String)Util.GpsInfo.get("museum"));
					break;
				case 4:
					site = new Room();
					site.setName((String)Util.GpsInfo.get("room"));
					break;
				default:
					site = new City();
					site.setName((String)Util.GpsInfo.get("city"));
					oldTypeSelected = 0;
				}
				site.setFrame(frame);
				site.enter();
				site.createUI();
	
				for (String ele : Util.Menus) {
					Util.GpsInfo.put(ele + "_old", Util.GpsInfo.get(ele));
				}
			}
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
