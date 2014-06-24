package granulej.gui.graphics;

import granulej.gui.datastructure.NamedXY;
import granulej.gui.datastructure.TreeNode;
import granulej.util.Utility;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class AnalysisClassTree {
	private Document classTree;
	private TreeNode root;
	private int leftX = 0;
	private int rightX = 100;
	private int topY = 0;
	private int bottomY = 100;
	private double absDepth = 0.2;
	private int granuleInterval = 20;
	private int textHeight=20;
	private int textWidth=4;
	private int classInterval=20;
	private boolean isup = true;
	private int maxdepth;
	private List<String> sortGranule = new LinkedList<String>();
	
	//表示这棵树是多棵树中的一支，方面画图的时候确定起始坐标
	private int count=0;
	
	//记录所有粒的位置
	private HashMap<String,NamedXY> graLocs = new HashMap<String,NamedXY>();

	public TreeNode getRoot() {
		return root;
	} 

	public AnalysisClassTree(String path,int width,int height,int num) {
		classTree = Utility.loadFromXML(path);
		root = buildTree(classTree.getDocumentElement(), root);
		maxdepth = constructDeepth(root);
		count=num;
		if(maxdepth == 0)
			return;
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		leftX = (screensize.width - width) / 2;
		rightX = leftX + width;
		topY = (screensize.height - height) / 2;
		bottomY = topY = height;
	}

	public void setArea(int lx, int rx, int ty, int by) {
		leftX = lx;
		rightX = rx;
		topY = ty;
		bottomY = by;
	}

	//num表示要画几个类图，而index表示当前计算的是第几个类图的坐标
	public void locateXY(int index) {
		int left= leftX+index*(rightX-leftX)/count;
		int right = leftX+(index+1)*(rightX-leftX)/count;
		locateXY(root, left, right);
	}

	public List<String> sortGranule(TreeNode node) {
		for (int i = 0; i < node.getGranulesNum(); i++)
			sortGranule.add(node.getGranuleAt(i).getName());
		for (int i = 0; i < node.getChildNum(); i++)
			sortGranule(node.childAt(i));
		return sortGranule;
	}

	//计算一颗树所有结点的位置
	private void locateXY(TreeNode root, int left, int right) {
		int maxdep=0;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.addLast(root);
		int upy = 20;
		//用bfs按照层次遍历所有类结点
		while(!queue.isEmpty())
		{
			LinkedList<TreeNode> childs = new LinkedList<TreeNode>();
			for(int i=0;i<queue.size();++i)
			{
				TreeNode node = queue.get(i);
				for(int j=0;j<node.getChildNum();++j)
				{
					childs.addLast(node.childAt(j));
				}
			}
			
			//先确定每一层第一个结点的位置
			TreeNode firstNode = queue.pollFirst();
			int distance = (right - left) / (queue.size()+2);
			Point topLeft = new Point();
			topLeft.x=left+distance;
			topLeft.y=upy;
			Point curClassLoc=calTreeNodeXY(topLeft,firstNode);
			//记录左边类的最右边位置，避免交叉
			right=curClassLoc.x;
			if(upy<curClassLoc.y)
			{
				upy=curClassLoc.y;
			}
			curClassLoc.y=topLeft.y;
			while(!queue.isEmpty())
			{
				TreeNode n=queue.pollFirst();
				curClassLoc = calTreeNodeXY(curClassLoc,n);
				if(upy<curClassLoc.y)
				{
					upy=curClassLoc.y;
				}
				curClassLoc.y = topLeft.y;
			}
			
			//遍历下一层
			queue.clear();
			queue=null;
			queue = childs;
		}
	}
	
	//计算类的位置，以及附属的粒、影子类的位置
	public Point calTreeNodeXY(Point topLeft,TreeNode n)
	{
		Point res = new Point();
		res.x=0;
		res.y=0;
		int textw = textWidth*(n.getNodeName().length()+3);
		n.setX(topLeft.x);
		n.setY(topLeft.y);
		
		int right = topLeft.x+textw;
		int bottom = topLeft.y+textHeight;
		
		if(isup&&n.getDeepth()==1&&n.getChildNum()==0)
		{
			n.setX(topLeft.x-granuleInterval);
			n.setY(topLeft.y-granuleInterval);
			right = topLeft.x;
			isup=false;
		}
		else
		{
			isup=true;
		}
		
		int shadowLeft=topLeft.x+granuleInterval;
		for (int i = 0; i < n.getShadowsNum(); i++) {
			NamedXY ele = n.getShadowAt(i);
			ele.setX(shadowLeft);
			ele.setY(topLeft.y+textHeight+(granuleInterval+textHeight/2)*(i+1));
			if(bottom<shadowLeft)
				bottom=bottom+(granuleInterval+textHeight/2)*n.getShadowsNum();
		}
//		if(right<shadowLeft+textHeight/2)
//			right=shadowLeft+textHeight/2;
		
		int granuleLeft = shadowLeft+textHeight/2+15;
		for (int i = 0; i < n.getGranulesNum(); i++) {
			NamedXY ele = n.getGranuleAt(i);
			textw=textWidth*(ele.getName().length()+2);
			ele.setX(granuleLeft);
			ele.setY(topLeft.y+textHeight+(granuleInterval+textHeight/2)*(i+1)-textHeight/2);
//			if(right<ele.getX()+textw)
//				right=ele.getX()+textw;
			if(bottom<topLeft.y+textHeight+(granuleInterval+textHeight/2)*(i+1))
				bottom=topLeft.y+textHeight+(granuleInterval+textHeight/2)*(i+1);
			
			//记录下粒的位置
			graLocs.put(ele.getName(), ele);
		}
		
		res.x=right+15;
		res.y=bottom+2*granuleInterval;
		
		return res;
	}

	public void preorderTravel(TreeNode node) {
		System.out.println(node.getNodeName() + " X:" + node.getX() + " Y:"
				+ node.getY() + " Depth：" + node.getDeepth());
		for (int i = 0; i < node.getGranulesNum(); i++) {
			NamedXY ele = node.getGranuleAt(i);
			System.out.println("Granule" + i + " Name " + ele.getName() + " X:"
					+ ele.getX() + " Y:" + ele.getY());
		}
		for (int i = 0; i < node.getShadowsNum(); i++) {
			NamedXY ele = node.getShadowAt(i);
			System.out.println("Shadow Class" + i + " Nmae " + ele.getName() + " X:"
					+ ele.getX() + " Y:" + ele.getY());
		}

		for (int i = 0; i < node.getChildNum(); i++)
			preorderTravel(node.childAt(i));
	}

	public int constructDeepth(TreeNode node) {
		if(node == null)
			return 0;
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

	public TreeNode buildTree(Node node, TreeNode treeNode) {
		NamedNodeMap startAttr = node.getAttributes();
//		if(startAttr.getLength() == 0)
//		{
//			System.out.println("xml file doesn't have granules");
//			return null;
//		}
		String className = startAttr.getNamedItem("name").getNodeValue();
		treeNode = new TreeNode(className);
		for (int i = 0; i < startAttr.getLength(); i++) {
			String attrname = node.getAttributes().item(i).getNodeName();
			String attrval = node.getAttributes().item(i).getNodeValue();
			if (attrname.startsWith("granule")) {
				treeNode.addGranule(attrval);
			}
			if (attrname.startsWith("shadow")) {
	//			attrval = attrval.substring(0, attrval.indexOf('_'));
				treeNode.addShadow(attrval);
			}
		}
		int i = 0;
		for (Node child = node.getFirstChild(); child != null; child = child
				.getNextSibling(), i++) {
			if (child.getAttributes() == null
					|| child.getAttributes().getNamedItem("name") == null)
				continue;
			TreeNode temp = buildTree(child, treeNode.childAt(i));
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
	
	public HashMap<String,NamedXY> getGranuleLocs()
	{
		return graLocs;
	}
}
