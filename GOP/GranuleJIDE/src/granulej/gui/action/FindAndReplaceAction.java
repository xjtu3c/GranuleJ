package granulej.gui.action;

import granulej.bean.JClosableTabbedPane;
import granulej.gui.Editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

public class FindAndReplaceAction implements ActionListener {
	private Editor editor;

	private JTextPane textpane;

	private JClosableTabbedPane jtp;

	private JRadioButton forw;

	private JRadioButton back;

	private JRadioButton all;

	private JRadioButton selectline;

	private JCheckBox case_s;

	private JCheckBox whole_s;

	private JCheckBox wrap_s;

	private JTextField find_str;

	private JTextField rep_str;

	public FindAndReplaceAction(Editor editor) {
		this.editor = editor;
		this.jtp = editor.getTabbedPane();
	}

	public void actionPerformed(ActionEvent e) {

		final JFrame iframe = new JFrame("Find/Replace");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(450, 400);
		iframe.setLocation((screensize.width - 350) / 2, (screensize.height - 300) / 2);
		iframe.setVisible(true);
		iframe.setResizable(false);

		iframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				editor.getMain().setEnabled(true);
			}
		});

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		iframe.getContentPane().setLayout(gridbag);
		JLabel find = new JLabel("Find :");
		addComponent(iframe, gridbag, c, find, 0, 0, 1, 1, GridBagConstraints.WEST);

		find_str = new JTextField(30);
		find_str.setEditable(true);
		addComponent(iframe, gridbag, c, find_str, 0, 1, 1, 2, GridBagConstraints.WEST);

		JLabel rep = new JLabel("Replace with : ");
		addComponent(iframe, gridbag, c, rep, 1, 0, 1, 1, GridBagConstraints.WEST);

		rep_str = new JTextField(30);
		rep_str.setEditable(true);
		addComponent(iframe, gridbag, c, rep_str, 1, 1, 1, 2, GridBagConstraints.WEST);

		Box styleBox = Box.createHorizontalBox();
		ButtonGroup styleGroup = new ButtonGroup();
		forw = new JRadioButton("Forward");
		forw.setSelected(true);
		back = new JRadioButton("Backward");
		styleGroup.add(forw);
		styleGroup.add(back);
		styleBox.add(forw);
		styleBox.add(back);
		styleBox.setBorder(BorderFactory.createTitledBorder("Direction"));
		addComponent(iframe, gridbag, c, styleBox, 2, 0, 1, 3, GridBagConstraints.EAST);

		Box scopeBox = Box.createHorizontalBox();
		ButtonGroup scopeGroup = new ButtonGroup();
		all = new JRadioButton("All");
		all.setSelected(true);
		selectline = new JRadioButton("Selected Lines");
		scopeGroup.add(all);
		scopeGroup.add(selectline);
		scopeBox.add(all);
		scopeBox.add(selectline);
		scopeBox.setBorder(BorderFactory.createTitledBorder("Scope"));
		addComponent(iframe, gridbag, c, scopeBox, 3, 0, 1, 3, GridBagConstraints.EAST);

		Box topBox = Box.createHorizontalBox();
		ButtonGroup topGroup = new ButtonGroup();
		case_s = new JCheckBox("Case Sensitive");
		whole_s = new JCheckBox("Whole Word");
		wrap_s = new JCheckBox("Wrap Search");
		topGroup.add(case_s);
		topGroup.add(whole_s);
		topGroup.add(wrap_s);
		topBox.add(case_s);
		topBox.add(whole_s);
		topBox.add(wrap_s);
		topBox.setBorder(BorderFactory.createTitledBorder("Options"));
		addComponent(iframe, gridbag, c, topBox, 4, 0, 1, 3, GridBagConstraints.EAST);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new FindAction());
		addComponent(iframe, gridbag, c, btnFind, 5, 1, 1, 1, GridBagConstraints.WEST);

		JButton btnReplace = new JButton("Replace");
		btnReplace.addActionListener(new ReplaceAction());

		addComponent(iframe, gridbag, c, btnReplace, 5, 2, 1, 1, GridBagConstraints.WEST);

		JButton btnReplaceAll = new JButton("Replace All");
		btnReplaceAll.addActionListener(new ReplaceAllAction());

		addComponent(iframe, gridbag, c, btnReplaceAll, 6, 1, 1, 1, GridBagConstraints.WEST);

		JButton btnConcel = new JButton("Close");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				editor.getMain().setEnabled(true);
			}
		});
		addComponent(iframe, gridbag, c, btnConcel, 6, 2, 1, 1, GridBagConstraints.WEST);

	}

	public class FindAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String last_item = "";

		private int last_position = -1;

		public void actionPerformed(ActionEvent e) {
			JScrollPane jp = (JScrollPane) jtp.getSelectedComponent();
			if (jp == null)
				return;
			textpane = (JTextPane) (jp.getViewport().getView());
			String r_str = find_str.getText().trim();
			if (r_str == null && "".equals(r_str)) {
				JOptionPane.showMessageDialog(null, "Please input the content !", "Error", JOptionPane.ERROR_MESSAGE);
			}

			// 光標位置			
			int pos = getStartPosition();
			int lastpos = getEndPosition();

			//获取编辑器的文本
			String text = textpane.getText();

			if (pos == text.length() && !wrap_s.isSelected()) {
				return;
			}
			//大小写不敏感，文本全转成小写
			if (!case_s.isSelected()) {
				r_str = r_str.toLowerCase();
				text = text.toLowerCase();
			}

			if (pos == last_position && r_str.equals(last_item))
				if (forw.isSelected())
					pos += r_str.length() + 1;
				else
					pos -= r_str.length() - 1;

			//下一处位置
			int inext = findNext(pos, text, r_str);

			last_item = r_str;
			last_position = inext;

			if (inext != -1) {
				if (selectline.isSelected()) {
					textpane.select(inext, lastpos);
				} else {
					textpane.select(inext, inext + r_str.length());
				}
			} else {
				JOptionPane.showMessageDialog(null, "String '" + r_str + "' not found !", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}

	public class ReplaceAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String last_item = "";

		private int last_position = -1;

		public void actionPerformed(ActionEvent e) {
			JScrollPane jp = (JScrollPane) jtp.getSelectedComponent();
			if (jp == null)
				return;
			textpane = (JTextPane) (jp.getViewport().getView());

			int pos = getStartPosition();
			int lastpos = getEndPosition();

			String text = textpane.getText();

			String value = find_str.getText().trim();
			if (value.length() == 0 || text.length() == 0)
				return;

			String newValue = rep_str.getText().trim();

			if (!case_s.isSelected()) {
				if (newValue.equalsIgnoreCase(value))
					return;
				value = value.toLowerCase();
				text = text.toLowerCase();
			} else if (newValue.equals(value))
				return;
			
			if (pos == last_position && value.equals(last_item))
				if (forw.isSelected())
					pos += value.length() + 1;
				else
					pos -= value.length() - 1;

			int ix = findNext(pos, text, value);
			
			last_item = value;
			last_position = ix;

			int firstIx = ix;
			if (ix != -1) {

				textpane.select(ix, ix + value.length());
				//替换新值
				textpane.replaceSelection(newValue);
				
				if (selectline.isSelected()) {
					textpane.select(ix, lastpos);
				} else {
					textpane.select(ix, ix + newValue.length());
				}
				if (ix < firstIx)
					firstIx += newValue.length() - value.length();
				
                //改变的文本重新获取
				text = textpane.getText();
				
				if (!case_s.isSelected()) {
					text = text.toLowerCase();
				}

				if (forw.isSelected()) {
					ix = findNext(ix + newValue.length(), text, value);
				} else {
					ix = findNext(ix - 1, text, value);
				}
			} else {
				JOptionPane.showMessageDialog(null, "String '" + value + "' not found !", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}

	public class ReplaceAllAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			JScrollPane jp = (JScrollPane) jtp.getSelectedComponent();
			if (jp == null)
				return;
			textpane = (JTextPane) (jp.getViewport().getView());
			
			int pos = getStartPosition();
			
			String text = textpane.getText();
			String value = find_str.getText().trim();
			if (value.length() == 0 || text.length() == 0)
				return;

			String newValue = rep_str.getText().trim();
			if (!case_s.isSelected()) {
				if (newValue.equalsIgnoreCase(value))
					return;
				value = value.toLowerCase();
				text = text.toLowerCase();
			} else if (newValue.equals(value))
				return;

			int ix = findNext(pos, text, value);
			if (ix >= 0) {

				int firstIx = ix;
				int valueInNewValueIx = !case_s.isSelected() ? newValue.toLowerCase().indexOf(value) : newValue.indexOf(value);
                
				while (ix != -1) {
					textpane.select(ix, ix + value.length());
					textpane.replaceSelection(newValue);
					textpane.select(ix, ix + newValue.length());
					if (ix < firstIx)
						firstIx += newValue.length() - value.length();

					text = textpane.getText();
					if (!case_s.isSelected()) {
						text = text.toLowerCase();
					}

					if (forw.isSelected()) {
						ix = findNext(ix + newValue.length(), text, value);
					} else {
						ix = findNext(ix - 1, text, value);
					}
					if (wrap_s.isSelected() && valueInNewValueIx != -1 && ix == firstIx + valueInNewValueIx) {
						break;
					}
				}
				 int res=forw.isSelected()?1:0;
				 textpane.setCaretPosition(res);
				// textpane.flushSBText();
				// textpane.setReplaceAll( false );
		
			} else {
				JOptionPane.showMessageDialog(null, "String '" + value + "' not found !", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}

	private int findNext(int pos, String txt, String value) {
		int ix = forw.isSelected() ? txt.indexOf(value, pos) : txt.lastIndexOf(value, pos);
		if (selectline.isSelected() && (ix < textpane.getSelectionStart() || ix > textpane.getSelectionEnd()))
			ix = -1;
		if (whole_s.isSelected()) {
			while (ix != -1
					&& ((ix > 0 && Character.isLetterOrDigit(txt.charAt(ix - 1))) || (ix < txt.length() - value.length() - 1 && Character.isLetterOrDigit(txt.charAt(ix
							+ value.length()))))) {
				ix = forw.isSelected() ? ++ix : --ix;
				ix = forw.isSelected() ? txt.indexOf(value, ix) : txt.lastIndexOf(value, ix);
			}
		}

		if (ix == -1 && wrap_s.isSelected()) {
			if (forw.isSelected() && pos > 0)
				return findNext(0, txt, value);
			else if (back.isSelected() && pos < txt.length() - 1)
				return findNext(txt.length() - 1, txt, value);
		}
		return ix;
	}

	private int getStartPosition() {
		int pos = textpane.getCaretPosition();
		if (selectline.isSelected()) {
			if (forw.isSelected()) {
				int selstart = textpane.getSelectionStart();
				if (selstart < pos && selstart != -1)
					pos = selstart;
			} else {
				int selend = textpane.getSelectionEnd();
				if (selend > pos && selend != -1)
					pos = selend;
			}
		} else {
			int selstart = textpane.getSelectionStart();
			if (selstart < pos && selstart != -1)
				pos = selstart;
		}
		return pos;
	}

	private int getEndPosition() {
		return forw.isSelected() ? textpane.getSelectionEnd() : textpane.getSelectionStart();
	}

	protected void addComponent(Container container, GridBagLayout layout, GridBagConstraints constraints, Component componentToAdd, int row, int column, int height, int width,
			int align) {
		constraints.gridx = column; // 组件所在列
		constraints.gridy = row; // 组件所在行

		constraints.gridwidth = width; // 组件宽度
		constraints.gridheight = height; // 组件高度

		constraints.weightx = 0; // 组件在水平方向的拉伸能力
		constraints.weighty = 0; // 组件在垂直方向的拉伸能力

		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = align;
		constraints.fill = GridBagConstraints.BOTH;

		layout.setConstraints(componentToAdd, constraints); // 设置目标组件的约束
		container.add(componentToAdd); // 在容器中添加目标组件
	}
}
