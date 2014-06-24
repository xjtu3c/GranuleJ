package granulej.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;



public class Console extends JPanel implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPopupMenu popMenu;
	private JTextPane jt;
	private GUIPrintStream guiPrintStream;
	private ImageIcon icon;
	
	public static Console _instance=null;
	
	public static Console getInstance()
	{
		if(_instance==null)
			_instance = new Console();
		
		return _instance;
	}

	public Console() {
		setLayout(new BorderLayout());
		JTabbedPane consolePane = new JTabbedPane();
		popMenu = new JPopupMenu();
		JMenuItem delItem =new JMenuItem("Clean");
		delItem.addActionListener(this);
		popMenu.add(delItem);

		jt = new JTextPane();
		jt.setEditable(false);
		jt.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 3) {
					popMenu.show(jt, e.getX(), e.getY());
				}

			}
		});

		setIO();
		JScrollPane jp = new JScrollPane(jt);

		icon = new ImageIcon("./image/console_view.gif");
		consolePane.addTab("Console",icon,jp);
		consolePane.setBorder(new EtchedBorder());
		add(consolePane, BorderLayout.CENTER);
	}

	public void setIO(){
		guiPrintStream = new GUIPrintStream(System.out, jt);
		System.setErr(guiPrintStream);
		System.setOut(guiPrintStream);
	}
	
	public void setOutput(String out){
		jt.setText(out);
	}
	
	public void append(String out){
		Document doc = jt.getDocument();      
		try {
			doc.insertString(doc.getLength(), out, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	public String getText(){
		return jt.getText(); 
	}
	public void setOutput(OutputStream o){
		guiPrintStream = new GUIPrintStream(o, jt);
		System.setErr(guiPrintStream);
		System.setOut(guiPrintStream);
	}
	
	public void clearIO(){
		System.setErr(System.err);
		System.setOut(System.out);
		guiPrintStream.clear();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		jt.setText("");
		guiPrintStream.clear();
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
}
