package granulej.bean;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		MyTreeNode jtreeNode = (MyTreeNode) node.getUserObject();

		this.setIcon(jtreeNode.getIcon());
		this.setText(jtreeNode.getName());
		this.setOpaque(true);
		if (selected) {
			this.setBackground(new Color(178, 180, 191));
		} else {
			this.setBackground(new Color(255, 255, 255));
		}
		return this;
	}
}
