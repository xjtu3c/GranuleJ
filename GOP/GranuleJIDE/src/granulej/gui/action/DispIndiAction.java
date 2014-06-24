package granulej.gui.action;

import granulej.gui.ConstString;
import granulej.gui.graphics.DrawIndividual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import config.GUIConfig;

public class DispIndiAction implements ActionListener {
	private GUIConfig config;

	public DispIndiAction(GUIConfig c) {
		config = c;
	}
	public String getXML(File dirFile) {
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (files[i].getName().endsWith("class_tree.xml"))
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
		JFrame iframe = new JFrame("Individual Hierarchy");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(450, 550);
		iframe.setLocation((screensize.width - 450) / 2,
				(screensize.height - 550) / 2);

		if(config.getProjectPath() == null || config.getProjectPath().trim().length() == 0){
			JOptionPane.showMessageDialog(null, "Please create a project !","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String path = getXML(new File(config.getProjectBinPath()));
		if(path == null || path.trim().length() == 0){
			JOptionPane.showMessageDialog(null, "Individual hierarchy is currently not generated !","Message",JOptionPane.PLAIN_MESSAGE);
			return;
		}

		DrawIndividual draw = new DrawIndividual(config,450,550);
		draw.setPreferredSize(new Dimension(200,100));
		
		JScrollPane scrollPane = new JScrollPane(draw);
		draw.setBounds(100, 100, 100, 300);
		iframe.add(draw);
		iframe.setVisible(true);
		
		//添加编辑菜单
		JMenuBar mBar = new JMenuBar();
		JMenu editMenu = new JMenu(ConstString.menus[1]);

		JMenuItem editMenuItem = new JMenuItem(ConstString.editItems[7]);
		editMenuItem.addActionListener(new EditIndividualColorAction(draw));
		
		editMenu.add(editMenuItem);
		mBar.add(editMenu);
		
		iframe.setJMenuBar(mBar);
	}
}