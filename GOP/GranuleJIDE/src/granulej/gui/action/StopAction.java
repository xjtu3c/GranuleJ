package granulej.gui.action;

import granulej.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopAction implements ActionListener {		
	public MainFrame main;
	public StopAction(MainFrame m) {
		main = m;
	}
	
	public void runShell(String shStr) {
		try {
			//Runtime.getRuntime().exec(
					//new String[] { "/bin/sh", "-c", shStr }, null, null);
			Runtime.getRuntime().exec(
					new String[]{"cmd.exe","-c",shStr},null,null);
			main.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		runShell("killall jamvm");
	}
}