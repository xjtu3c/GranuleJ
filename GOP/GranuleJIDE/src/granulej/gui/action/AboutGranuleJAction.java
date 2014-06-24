package granulej.gui.action;

import granulej.gui.MainFrame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import config.GUIConfig;

public class AboutGranuleJAction implements ActionListener {
	private MainFrame main;

	public AboutGranuleJAction(MainFrame frame) {
		this.main=frame;
	}

	public void actionPerformed(ActionEvent arg){
		final JFrame iframe = new JFrame("About GranuleJ IDE ");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(600, 350);
		iframe.setLocation((screensize.width - 350) / 2,
				(screensize.height - 250) / 2);
		iframe.setVisible(true);
		iframe.setResizable(false);

		iframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				main.setEnabled(true);
			}
		});
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		iframe.getContentPane().setLayout(gridbag);
		JLabel name = new JLabel("GranuleJ IDE ");
		addComponent(iframe, gridbag, c, name, 0, 0, 1, 4);	
		
		JLabel vname = new JLabel("Version: 1.0.0");
		addComponent(iframe, gridbag, c, vname, 1, 0, 1,4);
		
		JLabel lname = new JLabel("This product includes software developed by the website http://www.granulej.org/");
		addComponent(iframe, gridbag, c, lname, 2, 0, 1,4);
		
		JLabel cname = new JLabel("All rights reserved.Visit http://www.granulej.org/");
		addComponent(iframe, gridbag, c, cname, 3, 0, 1,4);	

		JButton btnConcel = new JButton("Ok");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				main.setEnabled(true);
			}
		});
		addComponent(iframe, gridbag, c, btnConcel, 4,4,1,1);
	}
	 
     protected void addComponent(Container container, GridBagLayout layout,GridBagConstraints constraints,
				Component componentToAdd, int row, int column, int height, int width)
		{
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
	
