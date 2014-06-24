package granulej.bean;

import javax.swing.Icon;

public class MyTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon icon;
	private String name;
	private TreeNodeType type;
	private FileStatus status;

	public FileStatus getStatus() {
		return status;
	}

	public void setStatus(FileStatus status) {
		this.status = status;
	}

	public MyTreeNode(Icon icon, String name, TreeNodeType type) {
		this.icon = icon;
		this.name = name;
		this.type = type;
	}

	public TreeNodeType getType() {
		return type;
	}

	public void setType(TreeNodeType type) {
		this.type = type;
	}

	public MyTreeNode() {
		super();
	}

	/**
	 * @return the icon
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}