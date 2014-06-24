package granulej.gui.datastructure;
import granulej.gui.graphics.DrawIndividual;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;

public class TreeNode {
	private String name;
	private List<TreeNode> children;
	private TreeNode father;
	private int deepth;
	private List<NamedXY> granules;
	private List<NamedXY> shadows;
	private int x;
	private int y;
	public  void ChangeColorAction ()
	{    
		JRadioButtonMenuItem items[]; //菜单项
		Color[] colors={Color.blue,Color.pink,Color.yellow,Color.red,Color.orange}; //颜色数组
		 String[] str = {"Blue","Pink","Yellow","Red","Orange"}; //菜单项名称
	      ButtonGroup colorGroup=new ButtonGroup(); 
	      items=new JRadioButtonMenuItem[5]; //初始化数组
	      for (int i=0;i<items.length;i++) { 
	         items[i]=new JRadioButtonMenuItem(str[i]); //实例化菜单项
	         colorGroup.add(items[i]); //增加菜单项到按钮组
	        items[i].addActionListener(null); }//菜单项事件处理
	}
	public NamedXY getGranuleAt(int i) {
		return granules.get(i);
	}

	public NamedXY getShadowAt(int i) {
		return shadows.get(i);
	}

	public int getGranulesNum() {
		return granules.size();
	}

	public int getShadowsNum() {
		return shadows.size();
	}

	public TreeNode() {
		children = null;
		father = null;
		children = new LinkedList<TreeNode>();
		granules = new LinkedList<NamedXY>();
		shadows = new LinkedList<NamedXY>();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public TreeNode(String val) {
		name = val;
		children = new LinkedList<TreeNode>();
		father = null;
		granules = new LinkedList<NamedXY>();
		shadows = new LinkedList<NamedXY>();
	}

	public void addGranule(String name) {
		granules.add(new NamedXY(name));
	}

	public void addShadow(String gname) {
		shadows.add(new NamedXY(gname));
	}

	public int getDeepth() {
		return deepth;
	}

	public void setDeepth(int i) {
		deepth = i;
	}

	public void setFather(TreeNode father) {
		this.father = father;
	}

	public TreeNode getFather() {
		return father;
	}

	public void addChild(TreeNode node) {
		children.add(node);
	}

	public TreeNode childAt(int index) {
		if (index < children.size() && index > -1)
			return children.get(index);
		return null;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNodeName() {
		return name;
	}

	public int getChildNum() {
		return children.size();
	}
	public boolean isInNode(int x,int y)
	{
		double dis = Math.sqrt(Math.pow(this.x-x, 2.0)+Math.pow(this.y-y,2.0));
		if(dis<=DrawIndividual.radis)
			return true;
		else
		    return false;
	}
}
