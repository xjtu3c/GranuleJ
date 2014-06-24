package granulej.gui.graphics;

import granulej.gui.datastructure.NamedXY;
import granulej.gui.datastructure.TreeNode;
import granulej.util.Utility;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class AnalysisGranuleTree {
	private Document granuleTree;
	private TreeNode root;
	private int leftX = 0;
	private int rightX = 100;
	private int topY = 0;
	private int bottomY = 100;
	private double absDepth = 0.2;
	private int maxdepth;
	private List<String> sortGranule = new LinkedList<String>();

	public TreeNode getRoot() {
		return root;
	}

	public AnalysisGranuleTree(String granuleT) {
		granuleTree = Utility.loadFromXML(granuleT);
		root=buildGranuleTree(granuleTree.getDocumentElement().getFirstChild(),root);		
		maxdepth = constructDeepth(root);
	}

	public void setArea(int lx, int rx, int ty, int by) {
		leftX = lx;
		rightX = rx;
		topY = ty;
		bottomY = by;
	}

	public void locateXY() {
		locateXY(root, leftX, rightX);
	}

	public List<String> sortGranule(TreeNode node) {
		for (int i = 0; i < node.getGranulesNum(); i++)
			sortGranule.add(node.getGranuleAt(i).getName());
		for (int i = 0; i < node.getChildNum(); i++)
			sortGranule(node.childAt(i));
		return sortGranule;
	}

	public void locateXY(TreeNode node, int left, int right) {
	}

	public void preorderTravel(TreeNode node) {
		System.out.println(node.getNodeName() + " X:" + node.getX() + " Y:"
				+ node.getY() + " Depth：" + node.getDeepth());
		for (int i = 0; i < node.getGranulesNum(); i++) {
			NamedXY ele = node.getGranuleAt(i);
			System.out.println("Granule" + i + " name " + ele.getName() + " X:"
					+ ele.getX() + " Y:" + ele.getY());
		}
		for (int i = 0; i < node.getShadowsNum(); i++) {
			NamedXY ele = node.getShadowAt(i);
			System.out.println("Shadow class" + i + "name" + ele.getName() + " X:"
					+ ele.getX() + " Y:" + ele.getY());
		}

		for (int i = 0; i < node.getChildNum(); i++)
			preorderTravel(node.childAt(i));
	}

	public int constructDeepth(TreeNode node) {
		if (node.getFather() == null)
			node.setDeepth(0);
		else
			node.setDeepth(node.getFather().getDeepth() + 1);
		for (int i = 0; i < node.getGranulesNum(); i++) {
			NamedXY ele = node.getGranuleAt(i);
			if (node.getShadowsNum() == 0)
				ele.setDepth(node.getDeepth());
			else
				ele.setDepth(node.getDeepth() - absDepth);
		}
		for (int i = 0; i < node.getShadowsNum(); i++) {
			NamedXY ele = node.getShadowAt(i);
			if (node.getGranulesNum() == 0)
				ele.setDepth(node.getDeepth());
			else
				ele.setDepth(node.getDeepth() + absDepth);
		}
		int maxdepth = node.getDeepth();
		for (int i = 0; i < node.getChildNum(); i++) {
			int temp = constructDeepth(node.childAt(i));
			if (temp > maxdepth)
				maxdepth = temp;
		}
		return maxdepth;
	}

	public TreeNode search(String name, TreeNode node){
		if(node.getNodeName().equals(name))
			return node;
		for (int i = 0; i < node.getChildNum(); i++) {
			TreeNode n = search(name, node.childAt(i));
			if(n != null)
				return n;
		}
		return null;
	}
	public TreeNode buildGranuleTree(Node node,TreeNode treeNode) {
		NamedNodeMap startAttr = node.getAttributes();
		String granuleName = startAttr.getNamedItem("name").getNodeValue();
		treeNode = new TreeNode(granuleName);
		for (int i = 0; i < startAttr.getLength(); i++) {
			String attrname = node.getAttributes().item(i).getNodeName();
			String attrval = node.getAttributes().item(i).getNodeValue();
			if (attrname.startsWith("name")) {
				treeNode.addGranule(attrval);
			}
			if (attrname.startsWith("shadow")) {
				attrval = attrval.substring(0, attrval.indexOf(DrawIndividual.granuleSeprator));
				treeNode.addShadow(attrval);
			}
		}
		int i = 0;
		for (Node child = node.getFirstChild(); child != null; child = child
				.getNextSibling(), i++) {
			if (child.getAttributes() == null
					|| child.getAttributes().getNamedItem("name") == null)
				continue;
			TreeNode temp = buildGranuleTree(child, treeNode.childAt(i));
			temp.setFather(treeNode);
			treeNode.addChild(temp);
		}
		
		return treeNode;
	}
	
//	public static void main(String[] args) {
//		AnalysisClassTree tree = new AnalysisClassTree(
//				"E:/logger1/g_FileLogger_class.xml");
//		tree.locateXY();
//		tree.preorderTravel(tree.getRoot());
//	}
}
