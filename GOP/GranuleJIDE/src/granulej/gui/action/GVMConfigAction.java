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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import config.GUIConfig;

public class GVMConfigAction implements ActionListener {
	private MainFrame main;
	private GUIConfig config;

	public GVMConfigAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
	}

	public void actionPerformed(ActionEvent arg0) {
		main.setEnabled(false);

		// main.set
		final JFrame iframe = new JFrame("Configure Local Virtual Machine ");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(600, 200);
		iframe.setLocation((screensize.width - 350) / 2,
				(screensize.height - 150) / 2);
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
		
		JLabel tname = new JLabel("The configuration is for jamvm.");
		addComponent(iframe, gridbag, c,tname, 0, 0, 1, 2, 5, 5);
		
		
		JLabel name = new JLabel("GVM Directory :");
		addComponent(iframe, gridbag, c, name, 1, 0, 1, 1, 5, 5);
		final JTextField ppath = new JTextField(35);
		ppath.setText("/user/local/jamvm/bin");
		ppath.setEditable(false);
		addComponent(iframe, gridbag, c, ppath, 1, 1, 1, 2, 5, 5);
		JButton btnBrose = new JButton("Browse...");
		btnBrose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse GVM directory ");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					ppath.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		addComponent(iframe, gridbag, c, btnBrose, 1, 3, 1, 1, 5, 5);
		
		JLabel tn = new JLabel("  ");
		addComponent(iframe, gridbag, c,tn, 2, 0, 1, 1, 5, 5);

		JButton btnConfirm = new JButton("Ok");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				config.setGvmPath(ppath.getText());
				main.setEnabled(true);
				
			}
		});

		addComponent(iframe, gridbag, c, btnConfirm, 3, 1, 1, 1, 5, 5);
		JButton btnConcel = new JButton("Cancel");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				main.setVisible(true);
				main.setEnabled(true);
			}
		});
		addComponent(iframe, gridbag, c, btnConcel, 3, 2, 1, 1, 5, 5);
	}

	protected void addComponent(Container container, GridBagLayout layout,
			GridBagConstraints constraints, Component componentToAdd, int row,
			int column, int height, int width, int x, int y) {
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