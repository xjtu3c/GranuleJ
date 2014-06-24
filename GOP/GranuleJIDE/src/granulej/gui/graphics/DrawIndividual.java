package granulej.gui.graphics;

import granulej.gui.ConstString;
import granulej.gui.action.EditIndividualColorAction;
import granulej.gui.datastructure.NamedXY;
import granulej.gui.datastructure.TreeNode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import config.GUIConfig;
/**
 * 定义一个JPanel对象用于保存绘制的图形的设置
 */
public class DrawIndividual extends JPanel implements ComponentListener,MouseListener,MouseMotionListener{
	/**
     * 
     */
	
	public static int dis = 20;
	public static int radis = (int) (dis * 0.3);
	private static final long serialVersionUID = 1L;
	private AnalysisClassTree class_tree;
	private AnalysisGranuleTree granule_tree;
	private TreeNode class_root;
	private TreeNode granule_root;
	
	public static String classTreeName = "class_tree.xml"; 
	public static String granuleTreeName = "TestGranuleTree.xml";
	
	//记录所有粒的位置
	private HashMap<String,NamedXY> graLocs = null;
	
	public static char granuleSeprator = '_';
	public int isTreeNode = 0;
	public NamedXY clickNamedXY=null;
	public TreeNode clickTreeNode = null;
	public boolean isMove = false;
	public boolean ii=false;
	public boolean isLocated=false;
	
	private Color classColor = new Color(85, 150, 210);
	private Color granuleColor = new Color(0,191,0);
	private Color shadowClassColor = new Color(0, 150, 210);
	
	
	public DrawIndividual(GUIConfig config,int width,int height) {
		addComponentListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		class_tree = new AnalysisClassTree(getXML(new File(config.getProjectBinPath()),classTreeName),
				                           width,height,1);
		granule_tree = new AnalysisGranuleTree(getXML(new File(config.getProjectBinPath()),granuleTreeName));
		class_root = class_tree.getRoot();
		granule_root = granule_tree.getRoot();
		
		class_tree.locateXY(0);
		graLocs = class_tree.getGranuleLocs();
	}

	public String getXML(File dirFile,String suffix) {
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (files[i].getName().endsWith(suffix))
					return files[i].getAbsolutePath();
			}
			// 删除子目录
			else if (files[i].isDirectory() && !files[i].getName().equals("granulej")) {
				return getXML(files[i],suffix);
			}
		}
		return null;
	}

	public void recursivePaint(Graphics g) {
		drawGranuleTree(granule_root,g);
		drawClassTree(class_root,g);
	}
	
	public void drawGranuleTree(TreeNode node, Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		NamedXY father = graLocs.get(node.getNodeName());
		for(int i=0;i<node.getChildNum();++i)
		{
			TreeNode ele = node.childAt(i);
			NamedXY child = graLocs.get(ele.getNodeName());
			Line2D line = new Line2D.Double(child.getX(),child.getY(),father.getX(),father.getY());
			g2.setPaint(getGranuleColor());
			g2.draw(line);
			
			drawGranuleTree(ele,g);
		}
	}

	public void drawClassTree(TreeNode node, Graphics g)
	{
		int dis = 20;
		int radis = (int) (dis * 0.3);
		Graphics2D g2 = (Graphics2D) g;
		//设置字体
		Font font = new Font("SansSerif", Font.BOLD,10);
		g2.setFont(font);
		//画类关系的线
		for (int i = 0; i < node.getChildNum(); i++) {
			TreeNode child = node.childAt(i);
			Line2D line = new Line2D.Double(node.getX(), node.getY(),
					child.getX(), child.getY());
			
			g2.setPaint(Color.black);
			g2.draw(line);
		}
		//画粒之间的线
		for (int i = 0; i < node.getGranulesNum(); i++) {
			NamedXY ele = node.getGranuleAt(i);
			if (ele.getName() == null || ele.getName().trim().length() == 0)
				continue;
			Line2D line;
			if (i == 0) {
				line = new Line2D.Double(node.getX(), node.getY(), ele.getX(),
						ele.getY());
			} else {
				line = new Line2D.Double(node.getGranuleAt(i - 1).getX(), node
						.getGranuleAt(i - 1).getY(), ele.getX(), ele.getY());
			}
			//画虚线
			float dash1[] = {2.0f,2.0f};
			BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
            g2.setStroke(dashed);
			g2.setPaint(Color.blue);
			g2.draw(line);
			g2.setStroke(new BasicStroke());
		}

		//画影子类之间的线
		for (int i = 0; i < node.getShadowsNum(); i++) {
			NamedXY ele = node.getShadowAt(i);
			Line2D line;
			if (i == 0) {
				line = new Line2D.Double(node.getX(), node.getY(), ele.getX(),
						ele.getY());
			} else {
				line = new Line2D.Double(node.getShadowAt(i - 1).getX(), node
						.getShadowAt(i - 1).getY(), ele.getX(), ele.getY());
			}
			float dash1[] = {2.0f,2.0f};
			BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
            g2.setStroke(dashed);
			g2.setPaint(Color.black);
			g2.draw(line);
			g2.setStroke(new BasicStroke());
		}
		//画影子类的圆和到粒之间的线
		for (int i = 0; i < node.getShadowsNum(); i++) {
			NamedXY ele = node.getShadowAt(i);
			
			//到粒之间的线
			int ind = ele.getName().lastIndexOf(DrawIndividual.granuleSeprator);
			String graName = ele.getName().substring(0, ind>0?ind:0);
			NamedXY gra = null;
			gra = graLocs.get(graName);
			if(gra!=null)
			{
				Line2D line = new Line2D.Double(gra.getX(),gra.getY(),ele.getX(),ele.getY());
				float dash1[] = {2.0f,2.0f};
				BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
	            g2.setStroke(dashed);
				g2.setPaint(Color.black);
				g2.draw(line);
				g2.setStroke(new BasicStroke());
			}
			
			drawShadowClassNode(ele,g2);
		}
		//画粒的名字和矩形框
		for (int i = 0; i < node.getGranulesNum(); i++) {
			NamedXY ele = node.getGranuleAt(i);
			drawGranuleNode(ele,g2);
		}
		
		//画类的形状
		drawClassNode(node,g2);
		for (int i = 0; i < node.getChildNum(); i++) {
			TreeNode child = node.childAt(i);
			drawClassTree(child, g);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		recursivePaint(g);
	}
	
	public void drawGranuleNode(NamedXY ele,Graphics2D graphics)
	{
		if (ele.getName() == null || ele.getName().trim().length() == 0)
			return;
		Rectangle2D rect = new Rectangle2D.Double(ele.getX() - radis,
				ele.getY() - radis, 2 * radis, 2 * radis);

		graphics.setPaint(getGranuleColor());
		graphics.fill(rect);
		graphics.setPaint(Color.black);
		graphics.drawString(ele.getName(), ele.getX() + radis/2, ele.getY());
	}
	
	public void drawClassNode(TreeNode node,Graphics2D graphics)
	{
		//画类的圆
		Ellipse2D circle = new Ellipse2D.Double();

		circle.setFrameFromCenter(node.getX(), node.getY(),
				node.getX() + radis, node.getY() + radis);
		graphics.setPaint(getClassColor());
		graphics.fill(circle);
		graphics.setPaint(Color.black);
		graphics.drawString(node.getNodeName(), node.getX()-4*radis, node.getY()-radis);
	}
	
	public void drawShadowClassNode(NamedXY ele,Graphics2D graphics)
	{
		//画影子类的矩形框
		if (ele.getName() == null || ele.getName().trim().length() == 0)
			return;
		Rectangle2D rect = new Rectangle2D.Double(ele.getX() - radis,
				ele.getY() - radis, radis * 2, 1 * radis);// 创建矩形对象
		Ellipse2D ellipse = new Ellipse2D.Double();// 创建椭圆对象
		ellipse.setFrame(rect);

		graphics.setPaint(getShadowClassColor());
		graphics.fill(ellipse);
	}

	public void componentResized(ComponentEvent e) {
		int dis = 20;
		int witdh = e.getComponent().getWidth();
		int height = e.getComponent().getHeight();
		int leftx = e.getComponent().getX();
		int topy = e.getComponent().getY() + (int) (dis * 0.4);
		int bottomy = e.getComponent().getY() + height - (int) (dis * 0.4);

		if(!isLocated)
		{
			class_tree.setArea(leftx, witdh, topy, bottomy);
			class_tree.locateXY(0);
			isLocated=true;
		}
	}
	public boolean isClickedNode(int x,int y,TreeNode root)
	{
		for(int i=0;i<root.getChildNum();++i)
		{
			TreeNode node = root.childAt(i);
			if(node.isInNode(x, y))
			{
				isTreeNode = 1;
				clickTreeNode = node;
				return true;
			}
		}
		
		for(int i=0;i<root.getGranulesNum();++i)
		{
			NamedXY g = root.getGranuleAt(i);
			if(g.isInNode(x, y))
			{
				isTreeNode = 2;
				clickNamedXY = g;
				return true;
			}
		}
		
		for(int i=0;i<root.getShadowsNum();++i)
		{
			NamedXY s = root.getShadowAt(i);
			if(s.isInNode(x, y))
			{
				isTreeNode = 2;
				clickNamedXY = s;
				return true;
			}
		}
		
		for(int i=0;i<root.getChildNum();++i)
		{
			TreeNode node = root.childAt(i);
			if(isClickedNode(x,y,node))
				return true;
		}
		
		return false;
		
	}
	//@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Dragged鼠标位置X"+e.getX());
//		System.out.println("Dragged鼠标位置Y:"+e.getY());
		if(!isMove)
			return;
		
		if(isTreeNode==1)
		{
			clickTreeNode.setX(e.getX());
			clickTreeNode.setY(e.getY());
		}
		else
		{
			clickNamedXY.setX(e.getX());
			clickNamedXY.setY(e.getY());
		}
		
		
		repaint();
	}
	
	
	 
	   
	//@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub		
		//判断是否在节点内		
		if(isClickedNode(e.getX(),e.getY(),class_root))
			
				
			isMove=true;
			
	     
	    if(e.getClickCount() == 2)
	    {       ii=true;
	            if(isTreeNode==1)
	      		{
	      			clickTreeNode.setName(JOptionPane.showInputDialog(null,"更换的名字"));
	      			
	      		}
	      		else
	      		{
	      			clickNamedXY.setName(JOptionPane.showInputDialog(null,"更换的名字"));
	      			
	      		}
	            
	    		repaint();
    	} 
	    if(e.getClickCount() == 1)
	    {
	    	 if(isTreeNode==1)
	      		{
	      			clickTreeNode.ChangeColorAction ();
	      			
	      		}
	    	 else
	      		{
	      			clickNamedXY.ChangeColorAction ();
	      			
	      		}
	    }
	     
	}
	private JPanel getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isMove=false;
		 if(e.getClickCount() == 2)
			 ii=false;
		
	}
	//@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("鼠标位置X："+e.getX());
//		System.out.println("鼠标位置Y:"+e.getY());
	    
		
	}
	//@Override
	public void mouseEntered(MouseEvent e) {
	}
	//@Override
	public void mouseExited(MouseEvent e) {	}
	
	

	public void componentHidden(ComponentEvent arg0) {
	}

	public void componentMoved(ComponentEvent arg0) {
	}

	public void componentShown(ComponentEvent arg0) {
	}
	
	public Color getClassColor()
	{
		return classColor;
	}
	
	public void setClassColor(Color color)
	{
		this.classColor=color;
	}
	
	public Color getGranuleColor()
	{
		return granuleColor;
	}
	
	public void setGranuleColor(Color color)
	{
		this.granuleColor=color;
	}
	
	public Color getShadowClassColor()
	{
		return shadowClassColor;
	}
	
	public void setShadowClassColor(Color color)
	{
		this.shadowClassColor=color;
	}
}