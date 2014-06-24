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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import granulej.util.xmlUtil;

import config.GUIConfig;

public class NewProjectAction implements ActionListener {
	private MainFrame main;
	private GUIConfig config;

	public NewProjectAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
	}

	public void actionPerformed(ActionEvent arg0) {
		main.setEnabled(false);

		final JFrame iframe = new JFrame("New Project");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(500,250);
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
		JLabel name = new JLabel("Project name:");
		addComponent(iframe, gridbag, c, name, 0, 0, 1, 1, 5, 5);
		final JTextField pname=new JTextField(40);
		addComponent(iframe, gridbag, c, pname, 0, 1, 1, 3, 5, 5);
		JLabel path = new JLabel("Directory:");
		addComponent(iframe, gridbag, c, path, 1, 0, 1, 1, 5, 5);
		final JTextField ppath=new JTextField(30);
		ppath.setEditable(false);
		addComponent(iframe, gridbag, c, ppath, 1, 1, 1, 2, 5, 5);
		JButton btnBrose = new JButton("Browse...");
		btnBrose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse project's root directory");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					ppath.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		addComponent(iframe, gridbag, c, btnBrose, 1, 3, 1, 1, 5, 5);

		JButton btnConfirm = new JButton("Finish");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pname.getText() == null
						|| pname.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please input project name!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (ppath.getText() == null
						|| ppath.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please choose the directory!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Pattern p = Pattern.compile("(_|[a-zA-Z])+([a-zA-Z0-9_])*");
				Matcher m = p.matcher(pname.getText());
				if (!m.matches()) {
					JOptionPane.showMessageDialog(null, "Project name is not valid !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (config.getProjectName() != "") {
					JOptionPane.showMessageDialog(null, "Currently the development environment is only allowed to open a project !","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				iframe.dispose();
				config.setProjectName(pname.getText());
				config.setProjectPath(ppath.getText() + File.separator
						+ pname.getText());
				main.setVisible(true);
				main.setEnabled(true);
				File dir = new File(config.getProjectSrcPath() + File.separator);
				if (!dir.mkdirs()) {
					JOptionPane.showMessageDialog(null, "Failed to creat the directory !","Error",JOptionPane.ERROR_MESSAGE);
					config.setProjectName("");
					config.setProjectPath("");
					return;
				}
				main.getPackageTree().newProject(pname.getText());
				main.getPackageTree().newPackage("(default package)", true);
				createClassPath();
			}
		});

		addComponent(iframe, gridbag, c, btnConfirm, 2, 1, 1, 1, 5, 5);
		JButton btnConcel = new JButton("Cancel");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				main.setVisible(true);
				main.setEnabled(true);
				main.validate();
			}
		});
		addComponent(iframe, gridbag, c, btnConcel, 2, 2, 1, 1, 5, 5);
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
	
	public void createClassPath()
	{
		String filename=config.getProjectPath()+File.separator+".classpath";
		try{
		if (!new File(filename).exists()) {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = doc.createElement("classpath");
			doc.appendChild(root);
			xmlUtil.saveXml(doc,filename);
		}
		}catch(ParserConfigurationException e){
		    e.printStackTrace();
	    }
	}	
}
