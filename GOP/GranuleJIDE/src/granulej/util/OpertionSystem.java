package granulej.util;

import java.util.Properties;

public class OpertionSystem {
	public static  String getSystemState() {
	Properties props = System.getProperties(); // 
	String osName = props.getProperty("os.name");
	String lnfName = null;
	if(osName.startsWith("win")||osName.startsWith("Win")) {
		lnfName = "windows";
	}
	else{
		lnfName="linux";
	}
	return lnfName;
   }
}
