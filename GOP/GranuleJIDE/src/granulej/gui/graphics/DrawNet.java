package granulej.gui.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

//public class DrawNet extends JPanel implements ComponentListener {
public class DrawNet extends JPanel {
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;
	private int num = 0;
	private int[] x;
	private int[] y;
	private int radis;
	private String[] ip;
	private String[] name;
	

	public DrawNet(String s) {
		addComponentListener(this);
		System.out.println(s);
		StringTokenizer st = new StringTokenizer(s, ":;");
		num = st.countTokens() / 2;
		ip = new String[num];
		name = new String[num];
		int j = 0;
		while (st.hasMoreTokens()) {
			String ipaddr = st.nextToken();
			String file = st.nextToken();
			ip[j] = ipaddr;
			name[j] = file;
			j++;
		}

		x = new int[num];
		y = new int[num];

		int height = 450;
		int width = 550;
		int thisx = 0;
		int thisy = 0;
		radis = width / (num + 1) / 2;
		int space = num == 1 ? 0 : width / (num + 1) / (num - 1);
		for (int i = 0; i < num; i++) {
			x[i] = thisx + (2 * i + 1) * radis + i * space;
			if (num == 1)
				x[i] += (width - 2 * radis) / 2;
			y[i] = thisy + height / 2;
		}
	}

	public void componentHidden(ComponentEvent arg0) {
	}

	public void componentMoved(ComponentEvent arg0) {
	}*/

//	public void disp(Graphics g) {
//
//		Graphics2D g2 = (Graphics2D) g;
//		for (int i = 0; i < num; i++) {
//			g2.setPaint(new Color(randRGB(), randRGB(), randRGB()));
//			g2.fillOval(x[i] - radis, y[i] - radis, 2 * radis, 2 * radis);
//			g2.setPaint(Color.black);
//			g2.drawString(name[i], x[i], y[i] - radis);
//			g2.drawString(ip[i], x[i], y[i] + radis);
//		}
//	}


	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		disp(g);
//	}

//	private int randRGB() {
//		return (int) (Math.random() * 256);
//	}
//
//	public void componentResized(ComponentEvent e) {
//		int width = e.getComponent().getWidth();
//		int height = e.getComponent().getHeight();
//		int leftx = e.getComponent().getX();
//		int topy = e.getComponent().getY();
//		radis = width / (num + 1) / 2;
//		int space = num == 1 ? 0 : width / (num + 1) / (num - 1);
//		for (int i = 0; i < num; i++) {
//			x[i] = leftx + (2 * i + 1) * radis + i * space;
//			if (num == 1)
//				x[i] += (width - 2 * radis) / 2;
//			y[i] = topy + height / 2;
//		}
//	}
//
//	public void componentShown(ComponentEvent arg0) {
//	}
    BufferedImage image;
    
    public DrawNet(BufferedImage image) {
        this.image = image;
    }
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - image.getWidth())/2;
        int y = (getHeight() - image.getHeight())/2;
        g.drawImage(image, x, y, this);
    }

}
