package granulej.gui.action;

import granulej.util.Utility;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import config.GUIConfig;

public class DispGranuleBaseAction implements ActionListener {
	private GUIConfig config;

	public DispGranuleBaseAction(GUIConfig c) {
		config = c;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame iframe = new JFrame("Granule Library Structure");
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(800, 700);
		iframe.setLocation((screensize.width - 800) / 2, (screensize.height - 700) / 2);
		try {
//			Socket client = new Socket(config.getIndAddr(), 1218);
//			InputStream socketGet = client.getInputStream();
//			PrintWriter socketWriter = new PrintWriter(client.getOutputStream());
//
//			socketWriter.println("base");
//			socketWriter.flush();

//			byte[] buffer = new byte[1212];
//			int byteread = 0;
//			int bytesum = 0;
//			FileOutputStream fs = new FileOutputStream(config.getProjectPath() + File.separator + "granule_base.xml");
//			while ((byteread = socketGet.read(buffer)) != -1) {
//				bytesum += byteread;
//				fs.write(buffer, 0, byteread);
//			}
			String file = Utility.getFile(System.getProperty("user.dir") + File.separator +"client"+File.separator+ "local.xml");
			int pos = 0;
			StringBuffer bf = new StringBuffer(file);
			for (int i = 0; i < bf.length() - 1; i++) {
				if (bf.charAt(i) == '<') {

					bf.insert(i++, '\r');
					for (int j = 0; j < pos * 2; j++)
						bf.insert(i++, ' ');
					if (bf.charAt(i + 1) == '/')
						pos--;
					else
						pos++;
					i++;
					continue;
				}
				if (i > 1 && bf.charAt(i) == '>' && bf.charAt(i - 1) == '/') {
					pos--;
				}
			}
			JTextPane text = new JTextPane();
			text.setText(bf.toString());
			text.setEditable(false);
			JScrollPane areaScrollPane = new JScrollPane(text);
			areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			iframe.add(areaScrollPane);
			iframe.setVisible(true);
//			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}