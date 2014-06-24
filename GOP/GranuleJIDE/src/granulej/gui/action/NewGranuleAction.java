package granulej.gui.action;

import granulej.bean.MyTreeNode;
import granulej.bean.TreeNodeType;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import config.GUIConfig;

public class NewGranuleAction implements ActionListener {
	private MainFrame main;
	private GUIConfig config;

	public NewGranuleAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
	}

	public void actionPerformed(ActionEvent arg0) {
		// System.out.println(arg0.getActionCommand());
		main.setEnabled(false);

		// main.set
		final JFrame iframe = new JFrame("New Granule");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(500, 250);
		iframe.setLocation((screensize.width - 350) / 2,
				(screensize.height - 250) / 2);
		iframe.setVisible(true);
		iframe.setResizable(false);

		iframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				iframe.dispose();
				main.setEnabled(true);
			}
		});

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		iframe.getContentPane().setLayout(gridbag);
		JLabel name = new JLabel("Project name:");
		addComponent(iframe, gridbag, c, name, 0, 0, 1, 1);
		final JTextField pname = new JTextField(35);
		pname.setEditable(false);
		pname.setText(config.getProjectName());
		addComponent(iframe, gridbag, c, pname, 0, 1, 1, 3);
		JLabel pkgName = new JLabel("Package name:");
		addComponent(iframe, gridbag, c, pkgName, 1, 0, 1, 1);
		final JTextField pkg = new JTextField(35);
		addComponent(iframe, gridbag, c, pkg, 1, 1, 1, 2);
		JLabel clsName = new JLabel("Granule name:");
		addComponent(iframe, gridbag, c, clsName, 2, 0, 1, 1);
		final JTextField cls = new JTextField(35);
		addComponent(iframe, gridbag, c, cls, 2, 1, 1, 2);
		
		JLabel tname = new JLabel("  ");
		addComponent(iframe, gridbag, c, tname, 3, 0, 1, 1);

		JButton btnConfirm = new JButton("Finish");
		DefaultMutableTreeNode tnode = config.getSelect();
		MyTreeNode sel = tnode == null ? null : (MyTreeNode) tnode
				.getUserObject();
		if (sel != null && sel.getType() == TreeNodeType.PACKAGE) {
			pkg.setText(sel.getName());
		}
		pkg.setEditable(false);

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = cls.getText();
				if (name.endsWith(".java")) {
					name = name.substring(0, name.lastIndexOf(".java"));
				}

				String pkgText = pkg.getText().trim();
				String pnameText = pname.getText().trim();
				if (pnameText == null || pnameText.length() == 0) {
					JOptionPane.showMessageDialog(null, "Please create a project !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (pkgText == null || pkgText.length() == 0) {
					JOptionPane.showMessageDialog(null, "Please choose a package !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					Pattern p = Pattern.compile("(_|[a-zA-Z])+([a-zA-Z0-9_])*");
					Matcher m = p.matcher(name);
					if (!m.matches()) {
						JOptionPane.showMessageDialog(null, "Granule name is not valid !","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				iframe.dispose();
				config.setProjectName(pnameText);
				main.setVisible(true);
				main.setEnabled(true);

				String temp = "";
				if (pkgText.equals("(default package)"))
					temp = config.getProjectSrcPath() + File.separator + name 
							+ ".java";
				else
					temp = config.getProjectSrcPath() + File.separator
							+ pkgText.replace(".", File.separator)
							+ File.separator + name + ".java";
				File dir = new File(temp);
				try {
					if (!dir.createNewFile()) {
						JOptionPane.showMessageDialog(null, "Failed to create granule file !","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				config.fileAndPath.put(name + ".java", temp);
				main.getPackageTree().newClass(name + ".java");
			}
		});

		addComponent(iframe, gridbag, c, btnConfirm, 4, 1, 1, 1);
		JButton btnConcel = new JButton("Cancel");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				main.setVisible(true);
				main.setEnabled(true);
			}
		});
		addComponent(iframe, gridbag, c, btnConcel, 4, 2, 1, 1);
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
		constraints.fill = GridBagConstraints.BOTH;

		layout.setConstraints(componentToAdd, constraints); // 设置目标组件的约束
		container.add(componentToAdd); // 在容器中添加目标组件
	}
}