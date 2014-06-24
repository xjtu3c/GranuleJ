package granulej.gui.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;

public class RedoAction implements ActionListener{
	private UndoAndRedoAction manager;
	
	public RedoAction(UndoAndRedoAction manager)
	{
		this.manager=manager;
	}
	public void actionPerformed(ActionEvent e) {          
		  try {
		    	manager.undo.undoManager.redo();
		      } catch (CannotRedoException ex) {
		        Toolkit.getDefaultToolkit().beep();
		      }
		    }
	}

