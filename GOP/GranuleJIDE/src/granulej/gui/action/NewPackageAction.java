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
import java.io.File;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import config.GUIConfig;

public class NewPackageAction implements ActionListener {
	private MainFrame main;
	private GUIConfig config;

	public NewPackageAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
	}

	public void actionPerformed(ActionEvent arg0) {
		main.setEnabled(false);

		// main.set
		final JFrame iframe = new JFrame("New Package");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(500, 250);
		iframe.setLocation((screensize.width - 350) / 2,
				(screensize.height - 200) / 2);
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
		JLabel name = new JLabel("Project name：");
		addComponent(iframe, gridbag, c, name, 0, 0, 1, 1, 5, 5);
		final JTextField pname = new JTextField(35);
		pname.setEditable(false);
		pname.setText(config.getProjectName());
		addComponent(iframe, gridbag, c, pname, 0, 1, 1, 3, 5, 5);
		JLabel pkgName = new JLabel("Package name：");
		addComponent(iframe, gridbag, c, pkgName, 1, 0, 1, 1, 5, 5);
		final JTextField pkg = new JTextField(35);
		addComponent(iframe, gridbag, c, pkg, 1, 1, 1, 2, 5, 5);
		
		JLabel tname = new JLabel("   ");
		addComponent(iframe, gridbag, c,tname,2,0,1,1,5,5);

		JButton btnConfirm = new JButton("Finish");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pkgText = pkg.getText().trim();
				String pnameText = pname.getText().trim();
				if (pnameText == null || pnameText.length() == 0) {
					JOptionPane.showMessageDialog(null, "Please create a project !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (pkgText == null || pkgText.length() == 0) {
					JOptionPane.showMessageDialog(null, "It is not recommended to use the default package !","Error",JOptionPane.ERROR_MESSAGE);
				} else {
					boolean isValid = true;
					if (pkg.getText().endsWith("."))
						isValid = false;
					Pattern p = Pattern.compile("(_|[a-zA-Z])+([a-zA-Z0-9_])*");
					StringTokenizer st = new StringTokenizer(pkgText, ".");
					while (st.hasMoreTokens()) {
						Matcher m = p.matcher(st.nextToken());
						if (!m.matches())
							isValid = false;
					}
					if (!isValid) {
						JOptionPane.showMessageDialog(null, "Invalid package name !","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				iframe.dispose();
				config.setProjectName(pnameText);
				main.setVisible(true);
				main.setEnabled(true);
				File dir = new File(config.getProjectSrcPath() + File.separator
						+ pkgText.replace(".", File.separator));
				if (!dir.mkdirs()) {
					JOptionPane.showMessageDialog(null, "Failed to create package directory !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				main.getPackageTree().newPackage(pkgText, false);
			}
		});

		addComponent(iframe, gridbag, c, btnConfirm, 3, 1, 1, 1, 5, 5);
		JButton btnConcel = new JButton("Cancel");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				main.setVisible(true);
				main.setEnabled(true);
				main.validate();
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