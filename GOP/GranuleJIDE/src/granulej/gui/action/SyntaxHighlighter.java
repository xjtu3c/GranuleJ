package granulej.gui.action;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class SyntaxHighlighter implements DocumentListener {
	private Set<String> keywords;

	private Style keywordStyle;

	private Style normalStyle;

	private Style numberStyle;

	private Style commentStyle;

	public SyntaxHighlighter(JTextPane editor) {
		// 准备着色使用的样式
		keywordStyle = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
		normalStyle = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
		numberStyle = ((StyledDocument) editor.getDocument()).addStyle("Number_Style", null);
		commentStyle = ((StyledDocument) editor.getDocument()).addStyle("Comment_Style", null);

		//StyleConstants.setForeground(keywordStyle, Color.red);
		StyleConstants.setForeground(keywordStyle, new Color(127, 0, 116));
		StyleConstants.setForeground(normalStyle, Color.BLACK);
		StyleConstants.setBold(keywordStyle, true);

		StyleConstants.setForeground(normalStyle, Color.BLACK);
		StyleConstants.setBold(normalStyle, false);

		//StyleConstants.setForeground(numberStyle, Color.MAGENTA);
		StyleConstants.setForeground(numberStyle, new Color(127, 0, 116));
		StyleConstants.setBold(numberStyle, false);

		StyleConstants.setForeground(commentStyle, new Color(13, 120, 101));
		StyleConstants.setBold(commentStyle, false);

		// 准备关键字
		keywords = new HashSet<String>();
		keywords.add("import");
		keywords.add("public");
		keywords.add("protected");
		keywords.add("private");
		keywords.add("byte");
		keywords.add("short");
		keywords.add("int");
		keywords.add("long");
		keywords.add("char");
		keywords.add("float");
		keywords.add("double");
		keywords.add("static");
		keywords.add("granule");
		keywords.add("class");
		keywords.add("interface");
		keywords.add("abstract");
		keywords.add("extends");
		keywords.add("implements");
		keywords.add("void");
		keywords.add("external");
		keywords.add("within");
		keywords.add("seed");
		keywords.add("expands");
		keywords.add("return");
		keywords.add("this");
		keywords.add("previous");
		keywords.add("super");
		keywords.add("if");
		keywords.add("else");
		keywords.add("void");
		keywords.add("new");
		keywords.add("try");
		keywords.add("catch");
	}

	public boolean isComment(StyledDocument doc, int pos) throws BadLocationException {
		String txt = doc.getText(0, doc.getLength());
		int singleStart, multiStart;
		int commentStart, commentEnd = 0;
		boolean isThisCommentSingle = true;
		do {
			singleStart = txt.indexOf("//", commentEnd);
			multiStart = txt.indexOf("/*", commentEnd);
			if (singleStart == -1 && multiStart == -1) {
				return false;
			} else if (singleStart == -1 && multiStart != -1 || multiStart != -1 && multiStart < singleStart) {
				commentStart = multiStart;
				commentEnd = txt.indexOf("*/", commentStart + 2) == -1 ? txt.length() : txt.indexOf("*/", commentStart + 2) + 2;
				isThisCommentSingle = false;
			} else {
				commentStart = singleStart;
				commentEnd = txt.indexOf("\n", commentStart + 2) == -1 ? txt.length() : txt.indexOf("\n", commentStart + 2) + 1;
				isThisCommentSingle = true;
			}
			if (pos >= commentStart && pos < commentEnd)
				return true;
		} while (true);
	}

	public char getCharAt(Document doc, int pos) throws BadLocationException {
		return doc.getText(pos, 1).charAt(0);
	}

	public int CharacterType(Document doc, int pos) throws BadLocationException {
		char ch = getCharAt(doc, pos);
		if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_')
			return 0;
		else if (ch == '/')
			return 1;
		else if (ch == '\n')
			return 2;
		else
			return 3;
	}

	public int indexOfWordStart(Document doc, int pos) throws BadLocationException {
		int initialPos = pos;
		for (; pos > 0 && CharacterType(doc, pos - 1) == CharacterType(doc, initialPos); --pos)
			;
		return pos;
	}

	public int indexOfWordEnd(Document doc, int pos) throws BadLocationException {
		int initialPos = pos;
		for (; pos < doc.getLength() && CharacterType(doc, pos) == CharacterType(doc, initialPos); ++pos)
			;
		return pos;
	}

	public boolean isKeyWord(String word) {
		if (keywords.contains(word.toLowerCase()))
			return true;
		return false;
	}

	public void coloringWord(StyledDocument doc, int pos) throws BadLocationException {
		if (isComment(doc, pos)) {
			SwingUtilities.invokeLater(new ColouringTask(doc, pos, 1, commentStyle));
		} else {
			int wordEnd = indexOfWordEnd(doc, pos);
			int wordStart = indexOfWordStart(doc, pos);
			String word = doc.getText(wordStart, wordEnd - wordStart);
			try {
				Integer.parseInt(word);
				SwingUtilities.invokeLater(new ColouringTask(doc, wordStart, wordEnd - wordStart, numberStyle));
			} catch (NumberFormatException e) {
				if (isKeyWord(word)) {
					SwingUtilities.invokeLater(new ColouringTask(doc, wordStart, wordEnd - wordStart, keywordStyle));
				} else
					SwingUtilities.invokeLater(new ColouringTask(doc, wordStart, wordEnd - wordStart, normalStyle));
			}
		}
	}

	public void changedUpdate(DocumentEvent e) {

	}

	public void insertUpdate(DocumentEvent e) {
		try {
			Document doc = e.getDocument();
			int processTo = Math.max(doc.getLength(), Math.max(doc.getText(0, doc.getLength()).indexOf("\n", e.getOffset()) + 1, doc.getText(0, doc.getLength()).indexOf("*/",
					e.getOffset()) + 2));
			for (int i = e.getOffset(); i < processTo; i++)
				coloringWord((StyledDocument) doc, i);
			if (e.getOffset() + e.getLength() < e.getDocument().getLength())
				coloringWord((StyledDocument) doc, e.getOffset() + e.getLength());
			if (e.getOffset() - 1 >= 0)
				coloringWord((StyledDocument) doc, e.getOffset() - 1);
		} catch (Exception excp) {
		}
	}

	public void removeUpdate(DocumentEvent e) {
		try {
			int length = e.getLength();
			Document doc = e.getDocument();
			int processTo = Math.max(doc.getLength(), Math.max(doc.getText(0, doc.getLength()).indexOf("\n", e.getOffset()) + 1, doc.getText(0, doc.getLength()).indexOf("*/",
					e.getOffset()) + 2));
			for (int i = e.getOffset(); i < processTo; i++)
				coloringWord((StyledDocument) doc, i);
			if (e.getOffset() + e.getLength() < e.getDocument().getLength())
				coloringWord((StyledDocument) doc, e.getOffset() + e.getLength());
			if (e.getOffset() - 1 >= 0)
				coloringWord((StyledDocument) doc, e.getOffset() - 1);
		} catch (Exception excp) {
		}
	}

	private class ColouringTask implements Runnable {
		private StyledDocument doc;

		private Style style;

		private int pos;

		private int len;

		public ColouringTask(StyledDocument doc, int pos, int len, Style style) {
			this.doc = doc;
			this.pos = pos;
			this.len = len;
			this.style = style;
		}

		public void run() {

			try {
				doc.setCharacterAttributes(pos, len, style, true);
			} catch (Exception e) {
			}
		}
	}

}
