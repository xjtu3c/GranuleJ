package granulej.gui.action;

import granulej.gui.Console;
import granulej.util.OpertionSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import config.GUIConfig;

public class BuildAction implements ActionListener {
	private GUIConfig config;

	public BuildAction(GUIConfig c) {
		config = c;
	}
	
	public void actionPerformed(ActionEvent arg0) {	
		String curDir = System.getProperty("java.class.path");	
		String[] args = new String[6];
	    args[0]="-classpath";
	    if(OpertionSystem.getSystemState().equals("windows"))
	    args[1]=".;"+curDir+config.getClasspath();
	    else 
	    args[1]=".:"+curDir+config.getClasspath();
	    args[2]="-g";
		args[3]="-d";
		args[4]=config.getProjectBinPath();
		args[5]=config.getProjectSrcPath();		
		try{
			String binPath = config.getProjectBinPath();
			File file = new File(binPath);
			deleteAllFiles(file);
		    if(JavaCompiler.compile(args)){		       
			   Console.getInstance().append("build successfully!\n");
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
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
