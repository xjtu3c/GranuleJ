package granulej.gui.graphics;

import granulej.gui.datastructure.NamedXY;
import granulej.gui.datastructure.TreeNode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;

import config.GUIConfig;

/**
 * 定义一个JPanel对象用于保存绘制的图形的设置
 */
public class DrawHistory extends JPanel implements ComponentListener {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private AnalysisClassTree class_tree_pre;
	private AnalysisGranuleTree granule_tree_pre;
	private TreeNode class_root_pre;
	private TreeNode granule_root_pre;
	
	private AnalysisClassTree class_tree_cur;
	private AnalysisGranuleTree granule_tree_cur;
	private TreeNode class_root_cur;
	private TreeNode granule_root_cur;
	
	//演化之前的文件 
	public static String classTreePreName = "class_tree.xml"; 
	public static String granuleTreePreName = "TestGranuleTree.xml";
	
	//演化之后的文件
	//public static String classTreeCurName = "class_tree1.xml";
	//public static String granuleTreeCurName = "TestGranuleTree1.xml";
	public static String classTreeCurName;
	public static String granuleTreeCurName;
	
	//记录所有粒的位置
	private HashMap<String,NamedXY> graLocs_pre = null;
	private HashMap<String,NamedXY> graLocs_cur = null;
	
	private HashMap<String,Object> newGranules = null;
	
	public static char granuleSeprator = '_';
	
	private GUIConfig config;

	public DrawHistory(GUIConfig config,int width,int height) {
		addComponentListener(this);
		this.config = config;
		
		int index=findMaxNumberFileName();
		classTreeCurName="class_tree"+1+".xml";
		granuleTreeCurName="TestGranuleTree"+index+".xml";
		
		class_tree_pre = new AnalysisClassTree(getXML(new File(config.getProjectBinPath()),classTreePreName),
				width,height,2);
		granule_tree_pre = new AnalysisGranuleTree(getXML(new File(config.getProjectBinPath()),granuleTreePreName));
		class_root_pre = class_tree_pre.getRoot();
		granule_root_pre = granule_tree_pre.getRoot();
		
		class_tree_pre.locateXY(0);
		graLocs_pre = class_tree_pre.getGranuleLocs();
		
		class_tree_cur = new AnalysisClassTree(getXML(new File(config.getProjectBinPath()),classTreeCurName),
				width,height,2);
		granule_tree_cur = new AnalysisGranuleTree(getXML(new File(config.getProjectBinPath()),granuleTreeCurName));
		class_root_cur = class_tree_cur.getRoot();
		granule_root_cur = granule_tree_cur.getRoot();
		
		class_tree_cur.locateXY(1);
		graLocs_cur = class_tree_cur.getGranuleLocs();
		
		checkNewGranules();
	}

	public String getXML(File dirFile,String suffix) {
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (files[i].getName().endsWith(suffix))
					return files[i].getAbsolutePath();
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				return getXML(files[i],suffix);
			}
		}
		return null;
	}
	//寻找最新的一个历史
	private int findMaxNumberFileName(){
		int num=0;
		for(int i=1;i<Integer.MAX_VALUE;i++){
		  if(new File(config.getProjectBinPath()+File.separator+"TestGranuleTree"+i+".xml").exists()){
			  num++;
		  }else{
			  break;
		  }
		}
		 return num;	
		}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		recursivePaint(granule_root_pre,class_root_pre,g,graLocs_pre);
		recursivePaint(granule_root_cur,class_root_cur,g,graLocs_cur);
		
	}

	public void recursivePaint(TreeNode graTreeRoot,TreeNode classTreeRoot,Graphics g,HashMap<String,NamedXY> graLocs) {
		drawGranuleTree(graTreeRoot,g,graLocs);
		drawClassTree(classTreeRoot,g,graLocs);
	}
	
	public void drawGranuleTree(TreeNode node, Graphics g,HashMap<String,NamedXY> graLocs)
	{
		Graphics2D g2 = (Graphics2D) g;
		NamedXY father = graLocs.get(node.getNodeName());
		for(int i=0;i<node.getChildNum();++i)
		{
			TreeNode ele = node.childAt(i);
			NamedXY child = graLocs.get(ele.getNodeName());
			if(child==null)
				continue;
			Line2D line = new Line2D.Double(child.getX(),child.getY(),father.getX(),father.getY());
			g2.setPaint(Color.green);
			g2.draw(line);
			
			drawGranuleTree(ele,g,graLocs);
		}
	}

	public void drawClassTree(TreeNode node, Graphics g,HashMap<String,NamedXY> graLocs)
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
			g2.setPaint(Color.black);
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
			
			//画代表影子类的椭圆
			if (ele.getName() == null || ele.getName().trim().length() == 0)
				continue;
			Rectangle2D rect = new Rectangle2D.Double(ele.getX() - radis,
					ele.getY() - radis, radis * 2, 1 * radis);// 创建矩形对象
			Ellipse2D ellipse = new Ellipse2D.Double();// 创建椭圆对象
			ellipse.setFrame(rect);

			if(newGranules.containsKey(graName))
				g2.setPaint(Color.blue);
			else
			    g2.setPaint(new Color(85, 150, 210));
			g2.fill(ellipse);
		}
		//画粒的名字和矩形框
		for (int i = 0; i < node.getGranulesNum(); i++) {
			NamedXY ele = node.getGranuleAt(i);
			if (ele.getName() == null || ele.getName().trim().length() == 0)
				continue;
			Rectangle2D rect = new Rectangle2D.Double(ele.getX() - radis,
					ele.getY() - radis, 2 * radis, 2 * radis);
			
			//如果粒是被替换以后的新粒，则用其他颜色画出来
			if(newGranules.containsKey(ele.getName()))
				g2.setPaint(Color.blue);
			else
				g2.setPaint(Color.green);
			g2.fill(rect);
			g2.setPaint(Color.black);
			g2.drawString(ele.getName(), ele.getX() + radis, ele.getY());
		}
		
		//画类的圆
		Ellipse2D circle = new Ellipse2D.Double();

		circle.setFrameFromCenter(node.getX(), node.getY(),
				node.getX() + radis, node.getY() + radis);
		g2.setPaint(new Color(85, 150, 210));
		g2.fill(circle);
		g2.setPaint(Color.black);
		g2.drawString(node.getNodeName(), node.getX()-4*radis, node.getY()-radis);
		for (int i = 0; i < node.getChildNum(); i++) {
			TreeNode child = node.childAt(i);
			drawClassTree(child, g,graLocs);
		}
	}
	
	
	public void componentResized(ComponentEvent e) {
		int dis = 20;
		int witdh = e.getComponent().getWidth();
		int height = e.getComponent().getHeight();
		int leftx = e.getComponent().getX();
		int topy = e.getComponent().getY();
		int bottomy = e.getComponent().getY();

		class_tree_pre.setArea(leftx, witdh, topy, bottomy);
		class_tree_cur.setArea(leftx, witdh, topy, bottomy);
		class_tree_pre.locateXY(0);
		class_tree_cur.locateXY(1);
	}
	
	public void checkNewGranules()
	{
		newGranules = new HashMap<String,Object>();
		Iterator it=graLocs_cur.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String)it.next();
			if(!graLocs_pre.containsKey(name))
			{
				newGranules.put(name, true);
			}
		}
	}

	public void componentHidden(ComponentEvent arg0) {
	}

	public void componentMoved(ComponentEvent arg0) {
	}

	public void componentShown(ComponentEvent arg0) {
	}
}