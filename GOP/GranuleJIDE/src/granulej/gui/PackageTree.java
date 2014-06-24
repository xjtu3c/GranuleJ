package granulej.gui;

import granulej.bean.JClosableTabbedPane;
import granulej.bean.MyTreeCellRenderer;
import granulej.bean.MyTreeNode;
import granulej.bean.TreeNodeType;
import granulej.gui.action.BuildPathAction;
import granulej.gui.action.DeleteAction;
import granulej.gui.action.NewClassAction;
import granulej.gui.action.NewGranuleAction;
import granulej.gui.action.NewInterfaceAction;
import granulej.gui.action.NewPackageAction;
import granulej.gui.action.NewProjectAction;
import granulej.gui.action.NewShadowAction;
import granulej.util.FileUtil;
import granulej.util.LookAndFeelUtility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import config.GUIConfig;

public class PackageTree extends JPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTree tree;

	private DefaultTreeModel treeModel;

	private GUIConfig config;

	private MainFrame main;

	private Editor editor;

	private JTabbedPane contextPane;

	private JTabbedPane projectPane;

	private JPopupMenu popMenu = new JPopupMenu();

	private JMenuItem buildpItem;

	private JMenuItem delItem;
	
	private JMenuItem refreshItem;

	private JMenuItem editItem;

	private JMenuItem addProjectItem;

	private JMenuItem addPackageItem;

	private JMenuItem addClassItem;

	private JMenuItem addInterfaceItem;

	private JMenuItem addGranuleItem;

	private JMenuItem addShadowItem;

	private ImageIcon icon, icon_cv;

	public JTabbedPane getContextPane() {
		return contextPane;
	}

	public void newProject(String name) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		// DefaultTreeCellRenderer dtcr = (DefaultTreeCellRenderer) tree
		// .getCellRenderer();
		// dtcr.setLeafIcon(new ImageIcon("./image/prj_obj.gif"));
		root.setUserObject(new MyTreeNode(new ImageIcon("./image/prj_obj.gif"), name, TreeNodeType.PROJECT));

		treeModel.reload();
	}

	public DefaultMutableTreeNode newPackage(String name, boolean isDefault) {
		if (!config.AddPackage(name)) {
			return config.getSelect();
		}
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new MyTreeNode(new ImageIcon("./image/jperspective.gif"), name, TreeNodeType.PACKAGE));
		treeModel.insertNodeInto(newNode, root, root.getChildCount());
		treeModel.reload();
		return newNode;
	}

	public void newClass(String name) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new MyTreeNode(new ImageIcon("./image/jcu_obj.gif"), name, TreeNodeType.CLASS));
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		if (config.getSelect() == root) {
			for (int i = 0; i < root.getChildCount(); i++) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
				MyTreeNode mnode = (MyTreeNode) node.getUserObject();
				if (mnode.getName().equals("(default package)"))
					treeModel.insertNodeInto(newNode, node, node.getChildCount());
			}
		} else {
			DefaultMutableTreeNode selectNode = config.getSelect();
			MyTreeNode sel = (MyTreeNode) selectNode.getUserObject();
			if (sel.getType() == TreeNodeType.CLASS) {
				DefaultMutableTreeNode selectParent = (DefaultMutableTreeNode) selectNode.getParent();
				treeModel.insertNodeInto(newNode, selectParent, selectParent.getChildCount());
			} else {
				treeModel.insertNodeInto(newNode, config.getSelect(), config.getSelect().getChildCount());
			}
		}
		treeModel.reload();
	}

	public PackageTree(final MainFrame m, final GUIConfig conf, final Editor e) {
		main = m;
		editor = e;
		config = conf;
		//setLookAndFeel();
		setLayout(new BorderLayout());
		popMenu = new JPopupMenu();
		addProjectItem = new JMenuItem("Project", new ImageIcon("./image/newjprj_wiz.gif"));
		addPackageItem = new JMenuItem("Package", new ImageIcon("./image/newpack_wiz.gif"));
		addClassItem = new JMenuItem("Class", new ImageIcon("./image/newclass_wiz.gif"));
		addInterfaceItem = new JMenuItem("Interface", new ImageIcon("./image/newint_wiz.gif"));
		addGranuleItem = new JMenuItem("Granule", new ImageIcon("./image/granule.gif"));
		addShadowItem = new JMenuItem("Shadow Class", new ImageIcon("./image/shadow.gif"));
		JMenu newMenu = new JMenu("New");
		newMenu.add(addProjectItem);
		newMenu.add(addPackageItem);
		newMenu.add(addClassItem);
		newMenu.add(addInterfaceItem);
		newMenu.add(addGranuleItem);
		newMenu.add(addShadowItem);
		delItem = new JMenuItem("Delete", new ImageIcon("./image/delete_edit.gif"));
		buildpItem = new JMenuItem("Build Path");
		editItem = new JMenuItem("Rename");
		refreshItem=new JMenuItem("Refresh", new ImageIcon("./image/nav_refresh.gif"));
		addProjectItem.addActionListener(new NewProjectAction(main, config));
		addPackageItem.addActionListener(new NewPackageAction(main, config));
		addClassItem.addActionListener(new NewClassAction(main, config));
		addInterfaceItem.addActionListener(new NewInterfaceAction(main, config));
		addGranuleItem.addActionListener(new NewGranuleAction(main, config));
		addShadowItem.addActionListener(new NewShadowAction(main, config));
		buildpItem.addActionListener(new BuildPathAction(main, config));
		editItem.addActionListener(this);
		popMenu.add(newMenu);
		popMenu.add(delItem);
		popMenu.add(buildpItem);
		popMenu.add(editItem);
		popMenu.add(refreshItem);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new MyTreeNode(null, "", TreeNodeType.PROJECT));
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setCellRenderer(new MyTreeCellRenderer());

		delItem.addActionListener(new DeleteAction(main, config, tree, editor));
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				config.setSelect(node);
			}
		});	
		
		refreshItem.addActionListener(new ActionListener()
	      {
	         public void actionPerformed(ActionEvent event)
	         {	
	        	 newProject(config.getProjectName());
	         }
	      });		
	
		tree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				TreePath path = tree.getPathForLocation(e.getX(), e.getY());
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					if (e.getClickCount() == 2 && e.getButton() == 1) {
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
						String fileName = ((MyTreeNode) node.getUserObject()).getName();
						if (fileName.endsWith(".java")) {
							main.getEditor().newTab(fileName);
							main.getProgramView().setView(fileName.substring(0, fileName.length() - 5));
						}
					}
					if (e.getButton() == 3) {
						tree.setSelectionPath(path);
						popMenu.show(tree, e.getX(), e.getY());
					}
				}
			}
		});

		DefaultTreeCellRenderer dtcr = (DefaultTreeCellRenderer) tree.getCellRenderer();

		dtcr.setLeafIcon(null);
		treeModel.reload();

		JScrollPane jp = new JScrollPane(tree);
		projectPane = new JTabbedPane();
		icon = new ImageIcon("./image/package.gif");
		projectPane.addTab("Project", icon, jp);
		add(projectPane, BorderLayout.NORTH);

		contextPane = new JTabbedPane();
		icon_cv = new ImageIcon("./image/members.gif");
		contextPane.addTab("Context Variable", icon_cv, new JPanel());
		setBorder(new EtchedBorder());
		JSplitPane splitPanePackage = new JSplitPane(JSplitPane.VERTICAL_SPLIT, projectPane, contextPane);
		add(splitPanePackage);
		setVisible(true);
		splitPanePackage.setResizeWeight(0.1);
		splitPanePackage.setOneTouchExpandable(false);
		splitPanePackage.setDividerLocation((int) (MainFrame.screenHeight * 0.6)); // 设置分隔条的位置
	}

	void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(LookAndFeelUtility.getLookAndFeel());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (e.getSource() == delItem) {
			if (node.isRoot()) {
				return;
			}
			String fileName = "";
			MyTreeNode mnode = (MyTreeNode) node.getUserObject();
			if (mnode.getName().equals("(default package)")) {
				JOptionPane.showMessageDialog(null, " Not allowed to delete the package !", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (mnode.getType() == TreeNodeType.PACKAGE) {
				String pname = mnode.getName();
				if (pname.contains("."))
					fileName = config.getProjectSrcPath() + File.separator + pname.substring(0, pname.indexOf('.'));
				else
					fileName = config.getProjectSrcPath() + File.separator + pname;
			} else
				fileName = config.fileAndPath.get(((MyTreeNode) node.getUserObject()).getName());

			JClosableTabbedPane jtp = editor.getTabbedPane();
			FileUtil.jtp = jtp;
			FileUtil.config = config;
			config.fileAndPath.remove(((MyTreeNode) node.getUserObject()).getName());
			if (FileUtil.delete(fileName, true))
				((DefaultTreeModel) tree.getModel()).removeNodeFromParent(node);
		} else if (e.getSource() == editItem) {
			MyTreeNode mnode = (MyTreeNode) node.getUserObject();
			if (mnode.getName().equals("(default package)")) {
				JOptionPane.showMessageDialog(null, "Default package does not allow renaming!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String oldName = config.fileAndPath.get(mnode.getName());

			if (mnode.getType() == TreeNodeType.PACKAGE || mnode.getType() == TreeNodeType.PROJECT) {
				JOptionPane.showMessageDialog(null, "It does not support renaming project name or package name currently !", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String inputValue = JOptionPane.showInputDialog("Please input new name:");
			if (inputValue == null || inputValue.trim().equals(""))
				return;
			Pattern p = Pattern.compile("(_|[a-zA-Z])+([a-zA-Z0-9_%])*");
			Matcher m = p.matcher(inputValue);
			if (!m.matches()) {
				JOptionPane.showMessageDialog(null, "The name is not valid !");
				return;
			}
			if (inputValue.endsWith(".java")) {
				inputValue = inputValue.substring(0, inputValue.lastIndexOf(".java"));
			}
			inputValue = inputValue + ".java";
			config.fileAndPath.remove(mnode.getName());
			config.fileAndPath.put(inputValue, oldName);
			mnode.setName(inputValue);
			File oldF = new File(oldName);
			File newF = new File(oldF.getParent() + File.separator + inputValue);
			oldF.renameTo(newF);
		}
   
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
}
