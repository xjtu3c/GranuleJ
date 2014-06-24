package granulej.gui.action;

import granulej.gui.MainFrame;
import granulej.util.Utility;

import java.awt.BorderLayout;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import config.GUIConfig;

public class SimulateConfigAction implements ActionListener {
	private MainFrame main;
	private GUIConfig config;
	private JFrame iframe = new JFrame("Configure similation network environment");
	private JTabbedPane jtp = new JTabbedPane();
	public static int GVMNUM = 1;

	public SimulateConfigAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
		jtp.addTab("Local Machine ",new ImageIcon("./image/pc_icon.png"),getPanel());
		iframe.add(jtp, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel jp = new JPanel();
		jp.setLayout(gridbag);
		JLabel name = new JLabel("GVM IP：");
		addComponent(jp, gridbag, c, name, 0, 0, 1, 1, 5, 5);
		final JTextField vtext = new JTextField(35);
		vtext.setText(Utility.getIp());
		addComponent(jp, gridbag, c, vtext, 0, 1, 1, 2, 5, 5);
		JLabel gName = new JLabel("Individual library server address:");
		addComponent(jp, gridbag, c, gName, 1, 0, 1, 1, 5, 5);
		final JTextField gtext = new JTextField(35);
		if (!config.getIndAddr().equals(""))
			gtext.setText(config.getIndAddr());
		addComponent(jp, gridbag, c, gtext, 1, 1, 1, 2, 5, 5);

		JLabel cName = new JLabel("Context variables server address:");
		addComponent(jp, gridbag, c, cName, 2, 0, 1, 1, 5, 5);
		final JTextField ctext = new JTextField(35);
		if (!config.getConAddr().equals(""))
			ctext.setText(config.getConAddr());
		addComponent(jp, gridbag, c, ctext, 2, 1, 1, 2, 5, 5);

		JLabel gvmname = new JLabel("Remote GranuleJ VM address:");
		addComponent(jp, gridbag, c, gvmname, 3, 0, 1, 1, 5, 5);
		final JTextField gvmtext = new JTextField(35);
		gvmtext.setText("/usr/local/jamvm/bin");
		addComponent(jp, gridbag, c, gvmtext, 3, 1, 1, 2, 5, 5);

		JLabel mainname = new JLabel("Main class：");
		addComponent(jp, gridbag, c, mainname, 4, 0, 1, 1, 5, 5);
		final JTextField maintext = new JTextField(35);

		addComponent(jp, gridbag, c, maintext, 4, 1, 1, 2, 5, 5);
		JButton btnBrose = new JButton("Browse...");
		btnBrose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse main class ");
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					maintext.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		addComponent(jp, gridbag, c, btnBrose, 4, 3, 1, 1, 5, 5);
		
		

		JButton btnConfirm = new JButton("Ok");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vname = vtext.getText();
				String gname = gtext.getText();
				String cname = ctext.getText();
				if (vname.trim().length() == 0 || gname.trim().length() == 0 || cname.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Address name is null !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Pattern p = Pattern.compile("([0-9])+([0-9.])*([0-9])");
				Matcher m = p.matcher(vname);
				if (!m.matches()) {
					JOptionPane.showMessageDialog(null, "Failed to connect to GranuleJ virtual machine because of unknown host "+vname+"!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				m = p.matcher(gname);
				if (!m.matches()) {
					JOptionPane.showMessageDialog(null, "Failed to connect to individual library server because of unknown host"+gname+"!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				m = p.matcher(cname);
				if (!m.matches()) {
					JOptionPane.showMessageDialog(null, "Failed to connect to context variable server because of unknown host "+cname+"!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				config.setGvmAddr(vname);
				config.setIndAddr(gname);
				config.setConAddr(cname);

				iframe.dispose();
				main.setVisible(true);
				main.setEnabled(true);
			}
		});

		addComponent(jp, gridbag, c, btnConfirm, 5, 1, 1, 1, 5, 5);
		JButton btnConcel = new JButton("Add");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// iframe.dispose();
				String vname = vtext.getText();
				String gname = gtext.getText();
				String cname = ctext.getText();
				config.setGvmAddr(vname);
				config.setIndAddr(gname);
				config.setConAddr(cname);
				addConfig();
				main.setEnabled(false);
			}
		});
		addComponent(jp, gridbag, c, btnConcel, 5, 2, 1, 1, 5, 5);
		return jp;
	}

	public void actionPerformed(ActionEvent arg0) {
		// System.out.println(arg0.getActionCommand());
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
		jtp.addTab("GVM" + GVMNUM, getPanel());
		GVMNUM++;
		iframe.add(jtp, BorderLayout.CENTER);
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