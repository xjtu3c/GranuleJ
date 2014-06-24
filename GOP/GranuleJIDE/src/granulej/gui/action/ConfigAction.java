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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import config.GUIConfig;

public class ConfigAction implements ActionListener {
	private MainFrame main;
	private GUIConfig config;

	public ConfigAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
	}

	public void actionPerformed(ActionEvent arg0) {
		main.setEnabled(false);

		final JFrame iframe = new JFrame("Configure network environment");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(500, 250);
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
		JLabel name = new JLabel("GranuleJ Virtual Machine Address:");
		addComponent(iframe, gridbag, c, name, 0, 0, 1, 1, 5, 5);
		final JTextField vtext = new JTextField(35);
		if (!config.getGvmAddr().equals(""))
			vtext.setText(config.getGvmAddr());
		else
			vtext.setText("localhost(default)");
		addComponent(iframe, gridbag, c, vtext, 0, 1, 1, 3, 5, 5);
		JLabel gName = new JLabel("Individual Library Server Address:");
		addComponent(iframe, gridbag, c, gName, 1, 0, 1, 1, 5, 5);
		final JTextField gtext = new JTextField(35);
		if (!config.getIndAddr().equals(""))
			gtext.setText(config.getIndAddr());
		addComponent(iframe, gridbag, c, gtext, 1, 1, 1, 2, 5, 5);

		JLabel cName = new JLabel("Context Variables Server Address:");
		addComponent(iframe, gridbag, c, cName, 2, 0, 1, 1, 5, 5);
		final JTextField ctext = new JTextField(35);
		if (!config.getConAddr().equals(""))
			ctext.setText(config.getConAddr());
		addComponent(iframe, gridbag, c, ctext, 2, 1, 1, 2, 5, 5);
		
		JLabel tname = new JLabel("  ");
		addComponent(iframe, gridbag, c,tname,3,0,1,1,3,3);
		
		JButton btnConfirm = new JButton("Ok");

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vname = vtext.getText();
				String gname = gtext.getText();
				String cname = ctext.getText();
				if (vname.trim().length() == 0 || gname.trim().length() == 0
						|| cname.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Address is null!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Pattern p = Pattern.compile("([0-9])+([0-9.])*([0-9])");
				Matcher m = p.matcher(vname);
				if (!m.matches() && !vname.contains("localhost")) {
					JOptionPane.showMessageDialog(null, "GranuleJ virtual machine address is not a valid name !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				m = p.matcher(gname);
				if (!m.matches()) {
					JOptionPane.showMessageDialog(null," Failed to connect to individual library server because of unknown host "+ gname +"!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				m = p.matcher(cname);
				if (!m.matches()) {
					JOptionPane.showMessageDialog(null,"Failed to connect to context variable server because of unknown host "+cname+"!","Error",JOptionPane.ERROR_MESSAGE);
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

		addComponent(iframe, gridbag, c, btnConfirm, 4, 1, 1, 1, 5, 5);
		JButton btnConcel = new JButton("Cancel");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				main.setVisible(true);
				main.setEnabled(true);
				main.validate();
			}
		});
		addComponent(iframe, gridbag, c, btnConcel, 4, 2, 1, 1, 5, 5);
	}
    
	protected void addComponent(Container container, GridBagLayout layout,
			GridBagConstraints constraints, Component componentToAdd, int row,
			int column, int height, int width, int x, int y) {
		constraints.gridx = column;
		constraints.gridy = row; 

		constraints.gridwidth = width; 
		constraints.gridheight = height; 

		constraints.weightx = 0; 
		constraints.weighty = 0; 
		
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;

		layout.setConstraints(componentToAdd, constraints); // 设置目标组件的约束
		container.add(componentToAdd); // 在容器中添加目标组件
	}
    
}