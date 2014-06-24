package granulej.gui.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * UndoWrapper is responsible for adding undo and redo support to text
 * components.
 * 
 * @author Antonio Vieiro (antonio@antonioshome.net), $Author: $
 * @version $Revision: $
 */
public class UndoWrapper implements UndoableEditListener {
    public  UndoManager undoManager;

    private UndoAction undoAction;

    private RedoAction redoAction;

    private JTextPane textComponent;

    /**
     * Creates a new instance of UndoWrapper
     */
    public UndoWrapper(JTextPane aComponent) {
        textComponent = aComponent;
        undoManager = new UndoManager();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        textComponent.getDocument().addUndoableEditListener(this);
        textComponent.getInputMap().put((KeyStroke) undoAction.getValue(Action.ACCELERATOR_KEY),"undo");
        textComponent.getInputMap().put((KeyStroke) redoAction.getValue(Action.ACCELERATOR_KEY),"redo");
        textComponent.getActionMap().put("undo", undoAction);
        textComponent.getActionMap().put("redo", redoAction);
    }

    public void undoableEditHappened(UndoableEditEvent e) {
        undoManager.addEdit(e.getEdit());
        undoAction.updateUndoState();
        redoAction.updateRedoState();
    }

    /**
     * UndoAction is the Action responsible for handling the undo operation.
     */
    class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Cannot undo"); 
            setEnabled(false);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl+Z"));
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undoManager.undo();
            } catch (CannotUndoException cue) {
                cue.printStackTrace(System.err);
            }
            updateUndoState();
            redoAction.updateRedoState();
        }

        void updateUndoState() {
            if (undoManager.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, "Undo"); 
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Cannot undo"); 
            }
        }
    }

    /**
     * RedoAction is the Action responsible for handling the redo operation.
     */
    class RedoAction extends AbstractAction {
        public RedoAction() {
            super("Cannot redo");
            setEnabled(false);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl+Y"));
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undoManager.redo();
            } catch (CannotRedoException cre) {
                cre.printStackTrace(System.err);
            }
            updateRedoState();
            undoAction.updateUndoState();
        }

        void updateRedoState() {
            if (undoManager.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, "Redo"); 
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Cannot redo");
            }
        }
    }

    UndoAction getUndoAction() {
        return undoAction;
    }

    RedoAction getRedoAction() {
        return redoAction;
    }
}