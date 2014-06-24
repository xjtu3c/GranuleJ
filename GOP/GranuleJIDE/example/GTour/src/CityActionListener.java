import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class CityActionListener extends SiteActionListener implements ActionListener {
	protected JPanel panel;
	protected ServerInfo server;
	protected City city;
	public CityActionListener(JPanel p,ServerInfo s, City c){
		panel=p;
		server=s;
		city=c;
	}
	public void actionPerformed(ActionEvent e) {
	}

}
