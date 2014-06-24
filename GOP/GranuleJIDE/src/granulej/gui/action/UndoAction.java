package granulej.gui.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.undo.CannotUndoException;


public class UndoAction  implements ActionListener {
	private UndoAndRedoAction manager;
 
	public UndoAction(UndoAndRedoAction manager)
	{
	   this.manager=manager;
	}	  

	public void actionPerformed(ActionEvent event) {		
	    try {
	    	manager.undo.undoManager.undo();
	     } catch (CannotUndoException e) {
		      Toolkit.getDefaultToolkit().beep();
	      }
	    }
}
