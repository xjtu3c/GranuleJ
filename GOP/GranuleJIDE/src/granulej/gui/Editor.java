package granulej.gui;

import granulej.bean.FileStatus;
import granulej.bean.JClosableTabbedPane;
import granulej.bean.MyTreeNode;
import granulej.gui.action.SyntaxHighlighter;
import granulej.gui.action.UndoWrapper;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import javax.swing.tree.DefaultMutableTreeNode;

import config.GUIConfig;

public class Editor extends JPanel {

	private GUIConfig config;
	private MainFrame main;
	private JClosableTabbedPane jtp;
	private JPopupMenu popMenu;
	private JTextPane textpane;

	public JTextPane getTextpane() {
		return textpane;
	}

	public void setTextpane(JTextPane textpane) {
		this.textpane = textpane;
	}

	public JClosableTabbedPane getTabbedPane() {
		return jtp;
	}

	public MainFrame getMain() {
		return main;
	}

	public void setMain(MainFrame main) {
		this.main = main;
	}

	public Editor(MainFrame main, GUIConfig conf) {
		this.main = main;
		config = conf;		
		jtp = new JClosableTabbedPane(main, config);	
		setLayout(new BorderLayout());
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		jtp.setPreferredSize(new Dimension(screensize.width,screensize.height));
		add(jtp, BorderLayout.CENTER);
	}

	public void newTab(String name) {
		JTextPane text = new JTextPane();
		text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_S) {
			    	Console.getInstance().append("hehe!"+"\n");
				}
				if (e.getKeyChar() == KeyEvent.VK_S && e.getModifiers() == KeyEvent.CTRL_MASK) {
					Console.getInstance().append("Ctrl+S print! save successfully"+"\n");
				} else {
				String name = jtp.getTitleAt(jtp.getSelectedIndex());
				main.setSingleFileStatus(name, FileStatus.UNSAVED);
                }
			}
		});
		JScrollPane areaScrollPane = new JScrollPane(text);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		areaScrollPane.setPreferredSize(new Dimension(screensize.width / 7 * 5,screensize.height / 5 * 3));
		if (jtp.indexOfTab(name) != -1) {
			jtp.setSelectedIndex(jtp.indexOfTab(name));
		} else {
			ImageIcon icon=new ImageIcon("./image/jcu_obj.gif");
			jtp.addTab(name,areaScrollPane,icon);
			int num = jtp.indexOfTab(name);		
			jtp.setSelectedIndex(num);
			String path = config.getProjectSrcPath();
			DefaultMutableTreeNode tmp = (DefaultMutableTreeNode) config.getSelect().getParent();
			MyTreeNode sel = (MyTreeNode) tmp.getUserObject();

			String pkg = sel.getName();
			if (pkg.equals("(default package)"))
				path = path + File.separator + name;
			else {
				pkg = pkg.replace(".", File.separator);
				path = path + File.separator + pkg + File.separator + name;
			}
			config.fileAndPath.put(name, path);

			try {
				BufferedReader bf = new BufferedReader(new FileReader(path));
				String temp = "";
				String line = bf.readLine();
				while (line != null) {
					temp += line + "\n";
					line = bf.readLine();
				}
				JScrollPane jp = (JScrollPane) jtp.getComponentAt(num);
				textpane=(JTextPane) (jp.getViewport().getView());	
                // 设置缩进20
				Style style = textpane.getLogicalStyle();
				StyleConstants.setLeftIndent(style, 20);
				StyleConstants.setFontSize(style, 12);
				StyleConstants.setFontFamily(style,"Consolas");  
				textpane.getDocument().addDocumentListener(new SyntaxHighlighter(textpane));

				final UndoWrapper undo=new UndoWrapper(textpane);
				
				textpane.setText(temp);
                popMenu = new JPopupMenu();
                
        		JMenuItem undoItem = new JMenuItem("Undo");
				undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
				undoItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					 undo.undoManager.undo();
					}
				}); 
            
                JMenuItem copyItem =new JMenuItem("Copy");
				copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
				copyItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textpane.copy();
					}
				});
				JMenuItem plasteItem = new JMenuItem("Paste");
				plasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
				plasteItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textpane.paste();
					}
				});
				JMenuItem cutItem = new JMenuItem("Cut");
				cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
				cutItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textpane.cut();
					}
				});			
				popMenu.add(undoItem);
				popMenu.add(copyItem);
				popMenu.add(plasteItem);
				popMenu.add(cutItem);
				textpane.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
							popMenu.show(jtp, e.getX(), e.getY());
						}
					}

					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							popMenu.setVisible(false);
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
