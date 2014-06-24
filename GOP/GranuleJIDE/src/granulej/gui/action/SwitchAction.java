package granulej.gui.action;

import granulej.gui.MainFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import config.GUIConfig;

public class SwitchAction implements ActionListener {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private MainFrame main;
	private GUIConfig config;

	public SwitchAction(MainFrame main, GUIConfig config) {
		this.main = main;
		this.config = config;
	}

	public void actionPerformed(ActionEvent arg0) {
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		JFileChooser filechooser = new JFileChooser();// 创建文件选择器
		filechooser.setSize(550, 400);
		filechooser.setLocation((screensize.width - 550) / 2,
				(screensize.height - 400) / 2);
		filechooser.setVisible(true);
		
		filechooser.setCurrentDirectory(new File("."));// 设置当前目录
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		filechooser.setDialogTitle("Open Project"); //設置標題
		
		
		// 显示JAVA源文件
		filechooser.setFileFilter(new FileFilter() {
			public boolean accept(File f) { // 设定可用的文件的后缀名
				if(f!=null){
				if (f.getName().endsWith(".java") || f.isDirectory()) {
					return true;
				}
                /*if(f.isDirectory()){
				  return true;
				}
			    String extension=getExtension(f);
				if(f!=null && extension.equalsIgnoreCase("java")){
				return true;
			    }*/
				}
				return false;
			}
            
			public String getDescription() {
				return "JAVA Project Directory ";
			}
			
		    public String getExtension(File f)
			{
			   if(f!=null){
		           String filename=f.getName();
			       int i=filename.lastIndexOf('.');
			       if(i>= 0 && i<filename.length()-1){
			         return filename.substring(i+1).toLowerCase();
			       }
			   }
			    return null;
			  }
			
		});
		 
		 int result=filechooser.showOpenDialog(null);
         if (result == JFileChooser.APPROVE_OPTION)// if approve (yes,    
         {   
             File file = filechooser.getSelectedFile(); 
             config.setProjectPath(file.getAbsolutePath()); 
             main.setVisible(true);
             main.restart(true);
         }
	}
}