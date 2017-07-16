package granulej.gui.action;
/*
 * 上下文服务器的响应
 */

import granulej.gui.MainFrame;
import gui.constant.ContextConstant;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ContextGranuleJAction implements ActionListener {
	
	private MainFrame main;
	//用来布局的
	private Box basebox,box1,box2,box3;

	
	
	public ContextGranuleJAction(MainFrame main) {
		
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final JFrame iframe = new JFrame("Save GServer Address Info");
		iframe.setSize(350, 200);
		iframe.setLocationRelativeTo(null);
		iframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		iframe.setVisible(true);
		iframe.setResizable(false);
	
		iframe.getContentPane().setLayout(new GridBagLayout());
		
		
		
		JLabel ip = new JLabel("IP Address：");

		final JTextField ip_txt = new JTextField(ContextConstant.CONTEXT_SERVER_IP,20);
		
		JLabel port = new JLabel("Port Number：");

		final JTextField port_txt = new JTextField(String.valueOf(ContextConstant.CONTEXT_SERVER_PORT),20);
		port_txt.setEditable(false);
		
		//组件在窗口的设置
		box1 = Box.createHorizontalBox();
		box2 = Box.createHorizontalBox();
		box3 = Box.createHorizontalBox();
		
		box1.add(ip);
		box1.add(Box.createHorizontalStrut(15));
		box1.add(ip_txt);
		
		box2.add(port);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(port_txt);
		

		/*
		 * 保存ip和port操作
		 */
		
		//确认保存按钮
		JButton btnConfirm = new JButton("Save");	
		box3.add(btnConfirm);
		box3.add(Box.createHorizontalStrut(20));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//int n = JOptionPane.showConfirmDialog(null, "Do You Want To Save The CServer Address Info?", "CServer Info Confirm Dialog", JOptionPane.YES_NO_OPTION);
				//确定保存
		       // if (n == JOptionPane.YES_OPTION) { 	
		        	//地址不为空
		        	if(!ip_txt.getText().equals("") && !port_txt.getText().equals("")){
			        	//保存ip地址和端口号到文件
			        	try {	
			        		if(!isboolIp(ip_txt.getText()))
			        			JOptionPane.showMessageDialog(null, "IP Address Format：0~255:0~255:0~255:0~255","IP Erroe",JOptionPane.ERROR_MESSAGE);
			        		//注册端口的范围从1024到49151 
			        		//动态端口49151~65535
			        		else if(1024 >Integer.valueOf(port_txt.getText()) || Integer.valueOf(port_txt.getText()) >65535)		        		
			        			JOptionPane.showMessageDialog(null, "Port Number ：1024~65535","Port Error",JOptionPane.ERROR_MESSAGE);
			        		else{
/*				        		String data = "public static final String CONTEXT_SERVER_IP = "+"\"" +ip_txt.getText()+"\" ;\n"
				        				+"public static final int CONTEXT_SERVER_PORT = " + port_txt.getText()+" ;\n";*/
				        		//File file =new File("./src/gui/constant/ContextAddress.txt");
			        			File file =new File("./src/gui/constant/ContextConstant.java");
					        	 //文件不存在 则创建
					        	 if(!file.exists()){
					        		 file.createNewFile();
					        	 }				        	 
			        			 //读文件
			        			 List<String> list= new ArrayList<String>();
			        			 BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			        			 String temp =  null;
			        			 while((temp=in.readLine()) != null){
			        				 list.add(temp+"\n");
			        			 }
			        			
			        			 //获取第一个变量的位置，如果没有服务器地址变量则直接保存
			        			 int position = 0;
			        			 for(int i = 0;i<list.size();i++){
			        				 String str = list.get(i);
			        				 if(str.contains("public")){
			        					 position = i;			        					
			        					 break;
			        				 }
			        					 
			        			 }
			        			 
			        			 /*
			        			  * 端口号固定
			        			  */
/*			        			 //判断constant文件的端口号，设置为新的值，没有则直接创建一个			        	
			        			 for(int i = 0;i<list.size();i++){
			        				 String str = list.get(i);
			        				 if(str.contains("public static final int CONTEXT_CLIENT_PORT = ")){
			        					 str = "	public static final int CONTEXT_CLIENT_PORT = " + port_txt.getText()+" ;\n";	
			        					 list.remove(i);	
			        					 list.add(i, str);
			        						break;
			        					}
			        				 else{
			        					 if(i == list.size()-1){
			        						 list.add(position, "	public static final int CONTEXT_CLIENT_PORT = " + port_txt.getText()+" ;\n\n");
			        						 break;
			        					 }
			        				 }
			        			 }*/
			        			 
			        			 //判断constant文件里面的服务器地址，修改地址
			        			 for(int i = 0;i<list.size();i++){
			        				 String str = list.get(i);
			        				 //已经有地址，修改地址
			        				 if(str.contains("public static final String CONTEXT_SERVER_IP")){
			        					str =  "	public static final String CONTEXT_SERVER_IP = "+"\"" +ip_txt.getText()+"\" ;\n";	
			        					list.remove(i);
			        					list.add(i, str);	
			        					break;
			        					}
			        				 //没有地址，保存地址
			        				 else{
			        					 if(i == list.size()-1){
			        						 list.add(position, "	public static final String CONTEXT_SERVER_IP = "+"\"" +ip_txt.getText()+"\" ;\n\n");
			        						 break;
			        					 }
			        				 }
			        			 }
			        			 

			        			 
			        			 
			        			 //true 追加文件  false 覆盖文件
			        			 FileWriter fileWritter = new FileWriter(file);
			        			 BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			        			 //String data = null;
			        			 for(int i = 0;i<list.size();i++){
			        				 bufferWritter.write(list.get(i));
			        				// System.out.println(list.get(i));
			        			 }
			        			 in.close();
			        			 bufferWritter.close();
			        			 fileWritter.close();

			        			 JOptionPane.showMessageDialog(null, "Successful Action,Close the Dialog!");
			        		}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
		        
		        	}
		        	else if(ip_txt.getText().equals("") && !port_txt.getText().equals(""))
		        		 JOptionPane.showMessageDialog(null, "Error：IP can't be null","IP Error",JOptionPane.ERROR_MESSAGE);
		        	else if(!ip_txt.getText().equals("") && port_txt.getText().equals(""))
		        		 JOptionPane.showMessageDialog(null, "Error：Port can't be null","Port Error",JOptionPane.ERROR_MESSAGE);
		        	else{
		        		JOptionPane.showMessageDialog(null, "Error：Address can't be null","Address Error",JOptionPane.ERROR_MESSAGE);
		        	}

		      //  }
		        //否则.....
		       // else if (n == JOptionPane.NO_OPTION) {  
		            // ......  
		       // }
			}
		});	

		
		//关闭保存按钮
		JButton btnConcel = new JButton("Close");
		box3.add(btnConcel);
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showConfirmDialog(null, "Quit the Dialog?", "CServer Info Comfirm Dialog", JOptionPane.YES_NO_OPTION);  
		        if (n == JOptionPane.YES_OPTION) {  
		        	iframe.dispose(); 
		        } else if (n == JOptionPane.NO_OPTION) {  
		            // ......  
		        }
				//iframe.dispose();
			}
		});
		
		
		
		//使用box进行页面布局
		basebox = Box.createVerticalBox();
		basebox.add(Box.createVerticalStrut(5));
		basebox.add(box1);
		basebox.add(Box.createVerticalStrut(10));
		basebox.add(box2);
		basebox.add(Box.createVerticalStrut(20));
		basebox.add(box3);
		basebox.add(Box.createVerticalStrut(10));
		
		iframe.add(basebox);

	}
	
	
	/*
	 * ip地址的格式是否正确	
	 */
		public boolean isboolIp(String ipAddress)  
		{  
		       String ip ="\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
		       		+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
		       		+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
		       		+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b"; 
		       
		       Pattern pattern = Pattern.compile(ip);   
		       Matcher matcher = pattern.matcher(ipAddress);   
		       return matcher.matches();   
		} 


}
