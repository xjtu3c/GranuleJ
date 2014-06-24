package granulej.gui.action;

import granulej.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import config.GUIConfig;

public class CloseAction implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame main;
	private GUIConfig config;

	public CloseAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
	}

	public void actionPerformed(ActionEvent arg0) {
		int result=JOptionPane.showConfirmDialog(null,"Exit GranuleJ IDE ?","Confirm Exit",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.YES_OPTION){
		File file = new File("./project.txt");
		if (file.exists())
			file.delete();
		config.setProjectPath(null);
		main.restart(false);
		}
	}
}