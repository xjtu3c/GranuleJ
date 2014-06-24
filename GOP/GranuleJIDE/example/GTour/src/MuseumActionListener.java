import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class MuseumActionListener extends SiteActionListener implements
		ActionListener {
	private JPanel panel;
	private ServerInfo server;
	private Museum museum;
	public MuseumActionListener(JPanel p,ServerInfo s, Museum m){
		panel=p;
		server=s;
		museum=m;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
