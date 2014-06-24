package granulej.bean;

import granulej.gui.MainFrame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolTip;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import config.GUIConfig;

public class JClosableTabbedPane extends JTabbedPane implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double scaleRatio = 0.3;
	private HashMap<String, Component> maps = new HashMap<String, Component>();
	private GUIConfig config;
	private MainFrame main;

	public JClosableTabbedPane(final MainFrame m, GUIConfig conf) {
		super();
		main = m;
		config = conf;
		addMouseListener(this);
		addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index = getSelectedIndex();
				if (index != -1)
					main.changeStatus(getTitleAt(index));
			}
		});
	}

	public void addTab(String title, Component component) {
		this.addTab(title, component, null);
	}

	public void addTab(String title, Component component, Icon extraIcon) {
		super.addTab(title, new CloseTabIcon(extraIcon), component);
	}

	public void insertTab(String title, Icon icon, Component component,
			String tip, int index) {
		tip = "tab" + component.hashCode();
		maps.put(tip, component);
		super.insertTab(title, icon, component, tip, index);
	}

	public void removeTabAt(int index) {
		Component component = getComponentAt(index);
		maps.remove("tab" + component.hashCode());
		super.removeTabAt(index);
	}

	public JToolTip createToolTip() {
		ImageToolTip tooltip = new ImageToolTip();
		tooltip.setComponent(this);
		return tooltip;
	}

	class ImageToolTip extends JToolTip {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Dimension getPreferredSize() {
			String tip = getTipText();
			Component component = maps.get(tip);
			if (component != null) {
				return new Dimension(
						(int) (getScaleRatio() * component.getWidth()),
						(int) (getScaleRatio() * component.getHeight()));
			} else {
				return super.getPreferredSize();
			}
		}

		public void paintComponent(Graphics g) {
			String tip = getTipText();
			Component component = maps.get(tip);
			if (component instanceof JComponent) {
				JComponent jcomponent = (JComponent) component;
				Graphics2D g2d = (Graphics2D) g;
				AffineTransform at = g2d.getTransform();
				g2d.transform(AffineTransform.getScaleInstance(getScaleRatio(),
						getScaleRatio()));
				ArrayList<JComponent> dbcomponents = new ArrayList<JComponent>();
				updateDoubleBuffered(jcomponent, dbcomponents);
				jcomponent.paint(g);
				resetDoubleBuffered(dbcomponents);
				g2d.setTransform(at);
			}
		}

		private void updateDoubleBuffered(JComponent component,
				ArrayList<JComponent> dbcomponents) {
			if (component.isDoubleBuffered()) {
				dbcomponents.add(component);
				component.setDoubleBuffered(false);
			}
			for (int i = 0; i < component.getComponentCount(); i++) {
				Component c = component.getComponent(i);
				if (c instanceof JComponent) {
					updateDoubleBuffered((JComponent) c, dbcomponents);
				}
			}
		}

		private void resetDoubleBuffered(ArrayList<JComponent> dbcomponents) {
			for (JComponent component : dbcomponents) {
				component.setDoubleBuffered(true);
			}
		}
	}

	public double getScaleRatio() {
		return scaleRatio;
	}

	public void setScaleRatio(double scaleRatio) {
		this.scaleRatio = scaleRatio;
	}

	public void mouseClicked(MouseEvent e) {
		int tabNumber = getUI().tabForCoordinate(this, e.getX(), e.getY());
		if (tabNumber < 0) {
			return;
		}
		Rectangle rect = ((CloseTabIcon) getIconAt(tabNumber)).getBounds();
		if (rect.contains(e.getX(), e.getY())) {
			// the tab is being closed
			// System.out.println(getTitleAt(tabNumber));
			// System.out.println(config.fileAndPath.get(getTitleAt(tabNumber)));
			String fileName = getTitleAt(tabNumber);
			String filePath = config.fileAndPath.get(fileName);
			FileStatus status = config.fileStatus.get(fileName);
			if (status == FileStatus.SAVED) {
				this.removeTabAt(tabNumber);
				return;
			}
			int ifadd = JOptionPane.showConfirmDialog(null, "The file has been modified. Save changes?", "Save Resource",
					JOptionPane.YES_NO_OPTION);
			if (ifadd != JOptionPane.YES_OPTION) {
				config.fileStatus.put(fileName, FileStatus.SAVED);
				main.changeAllStatus();
				this.removeTabAt(tabNumber);
				return;
			}
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(new File(filePath)));
				JScrollPane jp = (JScrollPane) getComponentAt(tabNumber);
				JTextPane textpane = (JTextPane) (jp.getViewport().getView());
				String context = textpane.getText();

				bw.write(context);
				bw.close();
				main.setSingleFileStatus(fileName, FileStatus.SAVED);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			this.removeTabAt(tabNumber);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
