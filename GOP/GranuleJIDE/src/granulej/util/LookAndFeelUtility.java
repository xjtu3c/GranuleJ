package granulej.util;

import java.util.Properties;

public class LookAndFeelUtility {
	public static  String getLookAndFeel() {
		Properties props = System.getProperties(); // 
		String osName = props.getProperty("os.name");
		String lnfName = null;
		if (osName.contains("Windows")) {
			lnfName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		} else if (osName.contains("Mac")) {
			lnfName = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
		} else if (osName.contains("Metal")) {
			lnfName = "javax.swing.plaf.metal.MetalLookAndFeel";
		} else if (osName.contains("Motif")) {
			lnfName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		}
		return lnfName;
	}
}
