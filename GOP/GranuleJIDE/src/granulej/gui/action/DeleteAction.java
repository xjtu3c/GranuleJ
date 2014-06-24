package granulej.gui.action;

import granulej.bean.JClosableTabbedPane;
import granulej.bean.MyTreeNode;
import granulej.bean.TreeNodeType;
import granulej.gui.Editor;
import granulej.gui.MainFrame;
import granulej.util.FileUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import config.GUIConfig;

public class DeleteAction implements ActionListener {

	private MainFrame main;

	private GUIConfig config;

	private JTree jtree;

	private Editor editor;

	public DeleteAction(MainFrame main, GUIConfig config, JTree jtree, Editor editor) {
		this.main = main;
		this.config = config;
		this.jtree = jtree;
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) {
		main.setEnabled(false);

		DefaultMutableTreeNode snode = config.getSelect();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
		MyTreeNode mnode = null;
		if (snode != null) {
			mnode = (MyTreeNode) snode.getUserObject();
		} else {
			mnode = (MyTreeNode) node.getUserObject();
		}
		String frameStr = "";
		if (mnode.getType() == TreeNodeType.CLASS) {
			frameStr = "Are you sure you want to delete file '" + mnode.getName() + "'?";
		} else if (mnode.getType() == TreeNodeType.PACKAGE) {
			frameStr = "Are you sure you want to delete package '" + mnode.getName() + "'?";
		} else if (mnode.getType() == TreeNodeType.PROJECT) {
			frameStr = "Are you sure you want to delete project '" + mnode.getName() + "'?";
		}

		int response = JOptionPane.showConfirmDialog(null, frameStr, "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (response == JOptionPane.YES_OPTION) {
			String fileName = "";
			mnode = (MyTreeNode) node.getUserObject();

			if (mnode.getType() == TreeNodeType.CLASS) {
				fileName = config.fileAndPath.get(mnode.getName());
			}
			// 对于默认包删除的操作
			boolean recursiveDel = true;
			boolean deleteCheck = false;
			if (mnode.getType() == TreeNodeType.PACKAGE) {
				String pname = mnode.getName();
				config.removePackage(pname);
				// String projectPath = config.getProjectPath();
				String projectPath = config.getProjectSrcPath();
				if (mnode.getName().equals("(default package)")) {
					// 默认包信息删除
					fileName = projectPath;
					recursiveDel = false;
				} else {
					if (pname.contains(".")) {
						fileName = projectPath + File.separator + pname.replace(".", "/");
						deleteCheck = true;
					} else {
						// 其它包信息删除，如果包只有一层，则直接删除
						fileName = projectPath + File.separator + pname;
					}
				}
			}

			if (mnode.getType() == TreeNodeType.PROJECT) {
				fileName = config.getProjectPath();
			}

			JClosableTabbedPane jtp = editor.getTabbedPane();
			FileUtil.jtp = jtp;
			FileUtil.config = config;

			main.setVisible(true);
			main.setEnabled(true);
			main.validate();

			if (FileUtil.delete(fileName, recursiveDel)) {
				// 对于包的进一步删除
				if (deleteCheck && mnode.getType() == TreeNodeType.PACKAGE) {
					String projectPath = config.getProjectPath();
					File file = null;
					while (fileName != projectPath) {
						fileName = fileName.substring(0, fileName.lastIndexOf("/"));
						file = new File(fileName);
						if (file.exists() && file.listFiles().length == 0) {
							file.delete();
						} else {
							break;
						}
					}
				}
				if (mnode.getType() == TreeNodeType.PROJECT) {
					config.setProjectName("");
					config.setProjectPath("");
					config.setSelect(null);
					DefaultMutableTreeNode root = new DefaultMutableTreeNode(new MyTreeNode(null, "", TreeNodeType.PROJECT));
					DefaultTreeModel treeModel = new DefaultTreeModel(root);
					jtree.setModel(treeModel);
					((DefaultTreeModel) jtree.getModel()).reload();

				} else {
					DefaultMutableTreeNode parentnode=null;
					if (mnode.getName().equals("(default package)")) {
						node.removeAllChildren();
					} else {
						parentnode= (DefaultMutableTreeNode) node.getParent();
					    ((DefaultTreeModel) jtree.getModel()).removeNodeFromParent(node);						
					}
				    if(parentnode!=null)
					jtree.scrollPathToVisible(new TreePath(parentnode.getPath()));
					((DefaultTreeModel) jtree.getModel()).reload();				
				}
			} else {
				JOptionPane.showMessageDialog(null, "Try deleted file failed !", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (response == JOptionPane.NO_OPTION) {
			main.setVisible(true);
			main.setEnabled(true);
		}
	}

}
