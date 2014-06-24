package granulej.gui.datastructure;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;



import granulej.gui.graphics.DrawIndividual;
public class NamedXY {
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private String name;
	private int x;
	private int y;
	private double depth;
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
	public  void Color(){
		Color[] colors={Color.blue,Color.pink,Color.yellow,Color.red,Color.orange};
		
	}
	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public NamedXY(String name) {
		this.name = name;
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
