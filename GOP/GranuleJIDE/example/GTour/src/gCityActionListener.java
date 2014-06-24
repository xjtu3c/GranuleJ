import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


granule gCityActionListener(CityActionListener){
	external String city_c;
	{
		return city_c.equals("Xian");
	}
	
}

class CityActionListener within gCityActionListener{
	
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command=e.getActionCommand();
			System.out.println("ActionListener:"+command);
			CityService.exitService();
			if(command.equals("introduction")){
				JTextArea text_area=new JTextArea(20,18);
				text_area.setEditable(false);
				text_area.setLineWrap(true);
				text_area.setText(city.getIntroduction());
				JScrollPane scroll_pane =new JScrollPane(text_area);
				panel.removeAll();
				panel.add(scroll_pane);
				panel.validate();
			}else if(command.equals("voice")){
				AudioInputStream audioInputStream;
				AudioFormat audioFormat;
				SourceDataLine sourceDataLine;
				File file = new File("city.mp3");
				try{
					audioInputStream = AudioSystem.getAudioInputStream(file);
					audioFormat = audioInputStream.getFormat();
					if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
						audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,audioFormat.getSampleRate(), 16, audioFormat.getChannels(), audioFormat.getChannels() * 2,audioFormat.getSampleRate(), false);
						audioInputStream = AudioSystem.getAudioInputStream(audioFormat,audioInputStream);
					}
					DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat,AudioSystem.NOT_SPECIFIED);
					sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
					sourceDataLine.open(audioFormat);
					sourceDataLine.start();
					CityService city_service=CityService.CreateCityService(panel, city, command);
					city_service.preparedVoice(audioInputStream, sourceDataLine);
					city_service.start();
				}catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if (command.equals("history")){
				JTextArea text_area=new JTextArea(20,18);
				text_area.setEditable(false);
				text_area.setLineWrap(true);
				text_area.setText("history history history history history history history history history history history history history history history history");
				JScrollPane scroll_pane =new JScrollPane(text_area);
				panel.removeAll();
				panel.add(scroll_pane);
				panel.validate();
			}else if(command.equals("snack")){
				JTextArea text_area=new JTextArea(20,18);
				text_area.setEditable(false);
				text_area.setLineWrap(true);
				text_area.setText("snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack snack");
				JScrollPane scroll_pane =new JScrollPane(text_area);
				panel.removeAll();
				panel.add(scroll_pane);
				panel.validate();
			}
		}
	}
