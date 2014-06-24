package granulej.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import config.GUIConfig;

public class CleanAction implements ActionListener {
	private GUIConfig config;
	public CleanAction(GUIConfig config)
	{
		this.config=config;
	}
	public void actionPerformed(ActionEvent e) {
		String binPath = config.getProjectBinPath();
		File file = new File(binPath);
		deleteAllFiles(file);
	}
	
	public void deleteAllFiles(File dir)
	{
		if(dir.isDirectory())
		{
			File [] files = dir.listFiles();
			for(File file:files)
			{
				deleteAllFiles(file);
			}
		}
		
		dir.delete();
	}

}
