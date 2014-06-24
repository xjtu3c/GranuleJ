package granulej.gui.action;

import granulej.gui.graphics.DrawHistory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import config.GUIConfig;

public class DispHistoryAction implements ActionListener {
	private GUIConfig config;

	public DispHistoryAction(GUIConfig c) {
		config = c;
	}

	public String getXML(File dirFile) {
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (files[i].getName().endsWith("GranuleTree.xml"))
					return files[i].getAbsolutePath();
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				return getXML(files[i]);
			}
		}
		return null;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame iframe = new JFrame("Individual Envolution History");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(900, 550);
		iframe.setLocation((screensize.width - 450) / 2,
				(screensize.height - 550) / 2);

		if(config.getProjectPath() == null || config.getProjectPath().trim().length() == 0){
			JOptionPane.showMessageDialog(null, "Please create a project !","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String binPath = config.getProjectPath()+File.separator+"bin";
		String path = getXML(new File(binPath));
		if(path == null || path.trim().length() == 0){
			JOptionPane.showMessageDialog(null, "Individaul hierarchy is currently not generated !","Message",JOptionPane.PLAIN_MESSAGE);
			return;
		}
		String b_path = config.getProjectBinPath() + File.separator ;
		if(!new File(b_path+"TestGranuleTree1.xml").exists() ||! new File(b_path+"class_tree1.xml").exists())
		{
		   JOptionPane.showMessageDialog(null, "There is no evolutionary history !","Message",JOptionPane.INFORMATION_MESSAGE);
	       return ;				
		}	
		DrawHistory draw = new DrawHistory(config,900,550);
		draw.setBounds(100, 100, 300, 300);
		iframe.add(draw);
		iframe.setVisible(true);
	}
}