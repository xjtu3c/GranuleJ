package granulej.gui.action;

import granulej.bean.PackageLibrary;
import granulej.gui.MainFrame;
import granulej.util.Utility;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;


import config.GUIConfig;

public class BuildPathAction extends JPanel implements ActionListener {
	private MainFrame main;
	private GUIConfig config;
	private JFrame iframe;
	private JTabbedPane jtp;


	public BuildPathAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
		iframe = new JFrame("Configure Build Path");
		jtp = new JTabbedPane();
		jtp.addTab(" Libraries ", new ImageIcon("./image/library_obj.gif"),getPanel());
	    jtp.setOpaque(true);
		iframe.add(jtp, BorderLayout.CENTER);	
	}

	public JPanel getPanel() {    

		PackageLibrary jp=new PackageLibrary(config,main,iframe);
		return jp;

	}
	
    public void actionPerformed(ActionEvent arg0) {
		main.setEnabled(false);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(600, 300);
		iframe.setLocation((screensize.width - 350) / 2, (screensize.height - 250) / 2);
		iframe.setVisible(true);
		iframe.setResizable(false);

		iframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				main.setEnabled(true);
			}
		});
	 }
	  
	public void addConfig() {
		jtp.addTab("GVM", getPanel());
		iframe.add(jtp, BorderLayout.CENTER);
	}
	protected void addComponent(Container container, GridBagLayout layout,
			GridBagConstraints constraints, Component componentToAdd, int row,
			int column, int height, int width) {
		constraints.gridx = column; // 组件所在列
		constraints.gridy = row; // 组件所在行

		constraints.gridwidth = width; // 组件宽度
		constraints.gridheight = height; // 组件高度

		constraints.weightx = 0; // 组件在水平方向的拉伸能力
		constraints.weighty = 0; // 组件在垂直方向的拉伸能力
		
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;

		layout.setConstraints(componentToAdd, constraints); // 设置目标组件的约束
		container.add(componentToAdd); // 在容器中添加目标组件
	}
	
 }