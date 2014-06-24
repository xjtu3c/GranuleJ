package granulej.gui;

import granulej.bo.GranuleDescriptor;
import granulej.gui.datastructure.NodeData;
import granulej.gui.datastructure.NodeType;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;
import java.awt.Component;
import java.util.*;

import AST.BodyDecl;
import AST.ContextVarDeclaration;
import AST.MethodDecl;
import AST.FieldDeclaration;
import AST.ShadowClassDecl;

public class IndividualView extends JPanel {

	private JTree tree;

	protected DefaultMutableTreeNode top;

	protected DefaultTreeModel treeModel;

	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	public IndividualView(HashMap<String, ArrayList<String>> glist) {
		super(new GridLayout(1, 0));
		top = new DefaultMutableTreeNode(new NodeData(NodeType.ROOT, "granule declarations"));
		createNodes0(top, glist);

		treeModel = new DefaultTreeModel(top);
		tree = new JTree(treeModel);
		tree.setEditable(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.setCellRenderer(new MyRenderer());

		DefaultMutableTreeNode last = null;
		if (top.getChildCount() >= 1) {
			last = (DefaultMutableTreeNode) top.getChildAt(top.getChildCount() - 1);
		} else
			last = top;

		expandDefaultRootNode(tree, new TreePath(last.getPath()));

		treeModel.addTreeModelListener(new MyTreeModelListener());

		JScrollPane treeView = new JScrollPane(tree);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(treeView);

		Dimension minimumSize = new Dimension(100, 50);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(100);
		treeView.setPreferredSize(new Dimension(100, 100));
		add(splitPane);
	}

	public IndividualView(List<GranuleDescriptor> glist) {
		super(new GridLayout(1, 0));
		top = new DefaultMutableTreeNode(new NodeData(NodeType.ROOT, "granule declarations"));
		createNodes(top, glist);

		treeModel = new DefaultTreeModel(top);
		tree = new JTree(treeModel);
		tree.setEditable(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.setCellRenderer(new MyRenderer());

		DefaultMutableTreeNode last = null;
		if (top.getChildCount() >= 1) {
			last = (DefaultMutableTreeNode) top.getChildAt(top.getChildCount() - 1);
		} else
			last = top;

		expandDefaultRootNode(tree, new TreePath(last.getPath()));

		treeModel.addTreeModelListener(new MyTreeModelListener());

		JScrollPane treeView = new JScrollPane(tree);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(treeView);

		Dimension minimumSize = new Dimension(100, 50);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(100);
		treeView.setPreferredSize(new Dimension(100, 100));
		add(splitPane);
	}

	class MyTreeModelListener implements TreeModelListener {
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
			try {
				int index = e.getChildIndices()[0];
				node = (DefaultMutableTreeNode) (node.getChildAt(index));
			} catch (NullPointerException exc) {
			}

		}

		public void treeNodesInserted(TreeModelEvent e) {
		}

		public void treeNodesRemoved(TreeModelEvent e) {
		}

		public void treeStructureChanged(TreeModelEvent e) {
		}
	}

	public DefaultMutableTreeNode getLast() {
		DefaultMutableTreeNode last = null;
		if (top.getChildCount() >= 1) {
			last = (DefaultMutableTreeNode) top.getChildAt(top.getChildCount() - 1);
		} else
			last = top;
		return last;
	}

	/** Remove all nodes except the root node. */
	public void clear() {
		if(top==null)
	    return ;
		if(top.getChildCount()>=1){
		top.removeAllChildren();
		}
		treeModel.reload();
	}

	/** Remove the currently selected node. */
	public void removeCurrentNode() {
		TreePath currentSelection = tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
			if (parent != null) {
				treeModel.removeNodeFromParent(currentNode);
				return;
			}
		}
		toolkit.beep();
	}

	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentPath == null) {
			parentNode = top;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		NodeData node = null;
		if (parent == null) {
			node = new NodeData(NodeType.GRANULE, (String) child);
		} else {
			  if (((String) child).equals("fitness()") || ((String) child).equals("fitnessAction()") || ((String) child).equals("checkFitness()")) {
				node = new NodeData(NodeType.METHPRI, (String) child);
			} else 
				node = new NodeData(NodeType.SHADOWCLASS, (String) child);
		}
		return addObject(parent, node, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
		if (parent == null) {
			parent = top;
		}
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	class MyRenderer extends DefaultTreeCellRenderer {

		ImageIcon rootIcon = new ImageIcon("./image/flatLayout(1).gif");

		ImageIcon granuleIcon = new ImageIcon("./image/granule_class.gif");

		ImageIcon shadowIcon = new ImageIcon("./image/shadowclass.gif");

		ImageIcon methpubIcon = new ImageIcon("./image/methpub_obj.gif");

		ImageIcon methproIcon = new ImageIcon("./image/methpro_obj.gif");

		ImageIcon methpriIcon = new ImageIcon("./image/methpri_obj.gif");

		ImageIcon fieldpubIcon = new ImageIcon("./image/field_public_obj.gif");

		ImageIcon fieldproIcon = new ImageIcon("./image/field_protected_obj.gif");

		ImageIcon fieldpriIcon = new ImageIcon("./image/field_private_obj.gif");

		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			NodeData data = (NodeData) node.getUserObject();
			ImageIcon icon = null;
			switch (data.nodeType) {
			case NodeType.ROOT:
				icon = rootIcon;
				break;
			case NodeType.GRANULE:
				icon = granuleIcon;
				break;
			case NodeType.SHADOWCLASS:
				icon = shadowIcon;
				break;
			case NodeType.METHPUB:
				icon = methpubIcon;
				break;
			case NodeType.METHPRO:
				icon = methproIcon;
				break;
			case NodeType.METHPRI:
				icon = methpriIcon;
				break;
			case NodeType.FIELDPUB:
				icon = fieldpubIcon;
				break;
			case NodeType.FIELDPRO:
				icon = fieldpriIcon;
				break;
			case NodeType.FIELDPRI:
				icon = fieldpriIcon;
				break;

			}
			this.setIcon(icon);
			return this;
		}
	}

	public void expandDefaultRootNode(JTree tree, TreePath parent) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandDefaultRootNode(tree, path);
			}
		}
		tree.expandPath(parent);
	}

	private void createNodes0(DefaultMutableTreeNode top, HashMap<String, ArrayList<String>> map) {

		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode sonNode = null;
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			category = new DefaultMutableTreeNode(new NodeData(NodeType.GRANULE, key));
			top.add(category);

			node = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRI, "fitness()"));
			category.add(node);
			node = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRI, "fitnessAction()"));
			category.add(node);
			node = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRI, "checkFitness()"));
		    category.add(node);

			ArrayList<String> value = (ArrayList<String>) (entry.getValue());
			int v_size = value.size();
			for (int i = 0; i < v_size; i++) {
				node = new DefaultMutableTreeNode(new NodeData(NodeType.SHADOWCLASS, value.get(i)));
				category.add(node);
			}
		}
	}

	private void createNodes(DefaultMutableTreeNode top, List<GranuleDescriptor> glist) {

		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode sonNode = null;
		for (Iterator iter = glist.iterator(); iter.hasNext();) {
			GranuleDescriptor gdecl = (GranuleDescriptor) iter.next();
			category = new DefaultMutableTreeNode(new NodeData(NodeType.GRANULE, gdecl.granuleToString()));
			top.add(category);
			for (Iterator iter0 = gdecl.getContextVars().iterator(); iter0.hasNext();) {
				ContextVarDeclaration cvd = (ContextVarDeclaration) iter0.next();
				if (cvd.getModifiers().equals("public")) {
					node = new DefaultMutableTreeNode(new NodeData(NodeType.FIELDPUB, cvd.getID() + ":" + cvd.getTypeAccess()));
				} else {
					node = new DefaultMutableTreeNode(new NodeData(NodeType.FIELDPRI, cvd.getID() + ":" + cvd.getTypeAccess()));
				}
				category.add(node);
			}
			for (Iterator iter1 = gdecl.getFieldVars().iterator(); iter1.hasNext();) {
				FieldDeclaration fd = (FieldDeclaration) iter1.next();
				if (!fd.name().equals("checkOn")) {
					node = new DefaultMutableTreeNode(new NodeData(NodeType.FIELDPUB, fd.name() + ":" + fd.getTypeAccess()));
					category.add(node);
				}
			}

			node = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRI, "fitness()"));
			category.add(node);
			node = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRI, "fitnessAction()"));
			category.add(node);
			node = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRI, "checkFitness()"));
			category.add(node);
			for (Iterator iter3 = gdecl.getShadowClasses().iterator(); iter3.hasNext();) {
				ShadowClassDecl scd = (ShadowClassDecl) iter3.next();
				node = new DefaultMutableTreeNode(new NodeData(NodeType.SHADOWCLASS, scd.getID()));
				category.add(node);
				for (int j = 0; j < scd.getNumBodyDecl(); j++) {
					BodyDecl bdecl = scd.getBodyDecl(j);
					if (bdecl instanceof FieldDeclaration) {
						FieldDeclaration fd = (FieldDeclaration) bdecl;
						if (fd.getModifiers().isPublic())
							sonNode = new DefaultMutableTreeNode(new NodeData(NodeType.FIELDPUB, fd.getID() + ":" + fd.getTypeAccess()));
						else if (fd.getModifiers().isProtected())
							sonNode = new DefaultMutableTreeNode(new NodeData(NodeType.FIELDPRO, fd.getID() + ":" + fd.getTypeAccess()));
						else
							sonNode = new DefaultMutableTreeNode(new NodeData(NodeType.FIELDPRI, fd.getID() + ":" + fd.getTypeAccess()));
						node.add(sonNode);
					} else if (bdecl instanceof MethodDecl) {
						MethodDecl md = (MethodDecl) bdecl;
						if (md.getModifiers().isPublic())
							sonNode = new DefaultMutableTreeNode(new NodeData(NodeType.METHPUB, md.signature()));
						else if (md.getModifiers().isProtected())
							sonNode = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRO, md.signature()));
						else
							sonNode = new DefaultMutableTreeNode(new NodeData(NodeType.METHPRI, md.signature()));
						node.add(sonNode);
					}
				}
			}
		}
	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}
}
