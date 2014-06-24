package granulej.gui;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class GUIPrintStream extends PrintStream {
	private JTextComponent text;
	private StringBuffer sb = new StringBuffer();

	public GUIPrintStream(OutputStream out, JTextComponent text) {
		super(out);
		this.text = text;
	}

	public void clear() {
		sb.delete(0, sb.length());
	}

	/**
	 * 重写write()方法，将输出信息填充到GUI组件。
	 * 
	 * @param buf
	 * @param off
	 * @param len
	 */
	@Override
	public synchronized void write(byte[] buf, int off, int len) {
		final String message = new String(buf, off, len);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				sb.append(message);
				text.setText(sb.toString());
			}
		});
	}

}
