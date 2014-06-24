import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class ParkActionListener extends SiteActionListener implements ActionListener {
	private JPanel panel;
	private ServerInfo server;
	private Park park;
	public ParkActionListener(JPanel p,ServerInfo s, Park pa){
		panel=p;
		server=s;
		park=pa;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
