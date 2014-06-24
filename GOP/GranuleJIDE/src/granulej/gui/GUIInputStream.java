package granulej.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.StringBufferInputStream;

import javax.swing.JTextPane;

public class GUIInputStream extends StringBufferInputStream
{
	private JTextPane text;
	private GUIInputStream pointer;
	private String data = "";
	
	public GUIInputStream(JTextPane t){
		super("");
		pointer = this;
		text = t;
		text.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
					data = text.getText().trim();
					String line = "";
					if(data.lastIndexOf('\n') != -1)
						data = data.substring(data.lastIndexOf('\n')+1);
					buffer = data;
					notif();
				}
			}
		});
	}
	public synchronized void notif(){
		notify();
	}
	
	public synchronized int read(byte[] b) {
		try {
			if(data == null || data == "")
				wait();
			if(b.length >= data.length()){
				for(int i=0; i<data.length(); i++){
					b[i] = (byte)data.charAt(i);
				}
				int result = data.length();
				data = "";
				buffer = "";
				return result;
			}
			else{
				for(int i=0; i<b.length; i++){
					b[i] = (byte)data.charAt(i);
				}
				data = data.substring(b.length);
				buffer = data;
				return b.length;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
