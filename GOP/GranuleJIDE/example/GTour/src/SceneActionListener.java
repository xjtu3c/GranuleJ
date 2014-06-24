import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class SceneActionListener extends SiteActionListener implements
		ActionListener {
	private JPanel panel;
	private ServerInfo server;
	private Scene scene;
	public SceneActionListener(JPanel p,ServerInfo s, Scene scen){
		panel=p;
		server=s;
		scene=scen;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
