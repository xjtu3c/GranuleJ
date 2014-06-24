package granulej.gui.action;

import granulej.gui.graphics.DrawNet;
import gui.constant.GranuleConstant;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import config.GUIConfig;

public class DispNetSimilarAction implements ActionListener {
	
	private GUIConfig config;

	public DispNetSimilarAction(GUIConfig c) {
		config = c;
	}

	public String getXML(File dirFile) {
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (files[i].getName().endsWith("Class.xml"))
					return files[i].getAbsolutePath();
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				return getXML(files[i]);
			}
		}
		return null;
	}

	public String getAct() {
		String active = "";
		try {
			Socket client = new Socket(InetAddress.getLocalHost(), GranuleConstant.LOCAL_AS_SERVER_PORT);
			InputStream socketGet = client.getInputStream();
			PrintWriter socketWriter = new PrintWriter(client.getOutputStream());

			socketWriter.println("active");
			socketWriter.flush();
			byte[] buffer = new byte[2048];
			socketGet.read(buffer);
			active = new String(buffer).trim();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return active;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFrame iframe = new JFrame("Network Individual Distribution");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(400, 300);
		iframe.setLocation((screensize.width - 450) / 2, (screensize.height - 550) / 2);

		JLabel tname = new JLabel(" The function is not currently supported.");

		String path = "./image/pc.png";
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		DrawNet contentPane = new DrawNet(image);
		contentPane.setOpaque(true);
		contentPane.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		for (int j = 0; j < 8; j++) {
			gbc.gridwidth = ((j + 1) % 2 == 0) ? GridBagConstraints.REMAINDER : GridBagConstraints.RELATIVE;
		}
		contentPane.add(tname);
		iframe.setContentPane(contentPane);
		iframe.setVisible(true);
		iframe.setResizable(false);
	}
}