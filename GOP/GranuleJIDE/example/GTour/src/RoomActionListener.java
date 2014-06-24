import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class RoomActionListener extends SiteActionListener implements
		ActionListener {
	private JPanel panel;
	private ServerInfo server;
	private Room room;
	public RoomActionListener(JPanel p,ServerInfo s, Room r){
		panel=p;
		server=s;
		room=r;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
