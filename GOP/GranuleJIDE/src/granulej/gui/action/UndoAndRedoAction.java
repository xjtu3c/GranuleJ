package granulej.gui.action;

import granulej.bean.JClosableTabbedPane;
import granulej.gui.ConstString;
import granulej.gui.Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;



public class UndoAndRedoAction implements ActionListener {
	
	public  UndoWrapper undo;
	private Editor editor;
    private JTextPane textpane;    
    private JClosableTabbedPane jtp;
     
    public UndoAndRedoAction(Editor editor){
        this.editor = editor;  
    } 
    
    public void actionPerformed(ActionEvent e0) {
         //由editor获取textpane;
    	jtp = editor.getTabbedPane();
		JScrollPane jp = (JScrollPane) jtp.getSelectedComponent();
	    if(jp==null)
	    return ;
		textpane = (JTextPane) (jp.getViewport().getView());
        undo=new UndoWrapper(textpane);
        
    }

}