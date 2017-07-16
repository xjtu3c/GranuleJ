package granulej.gui.action;
/*
 * 粒服务器的响应
 * 粒服务器的ip地址和端口号的保存
 */

import granulej.gui.MainFrame;
import gui.constant.GranuleConstant;

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

public class GranueGranuleJAction implements ActionListener {
	
	private MainFrame main;
	//用来布局的
	private Box basebox,box1,box2,box3;

	
	
	public GranueGranuleJAction(MainFrame main) {
		
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//窗口设置
		final JFrame iframe = new JFrame("Save GServer Address Info");
		iframe.setSize(350, 200);
		iframe.setLocationRelativeTo(null);
		iframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		iframe.setVisible(true);
		iframe.setResizable(false);
		
		//窗口布局
		iframe.getContentPane().setLayout(new GridBagLayout());
		
		
		JLabel ip = new JLabel("IP Address：");
		final JTextField ip_txt = new JTextField(GranuleConstant.GRANULE_SERVER_IP,20);
		
		JLabel port = new JLabel("Port Number：");
		final JTextField port_txt = new JTextField(String.valueOf(GranuleConstant.LOCAL_AS_CLIENT_PORT),20);
		port_txt.setEditable(false);
		//ip.setFont(new Font("",1,14));
		//port.setFont(new Font("",1,14));	

		

		/*
		 * 保存ip和port操作
		 */
		
		//确认保存按钮
		JButton btnConfirm = new JButton("Save");	

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//int n = JOptionPane.showConfirmDialog(null, "Do You Want To Save The GServer Address Info?", "GServer Info Confirm Dialog", JOptionPane.YES_NO_OPTION);  
		       // if (n == JOptionPane.YES_OPTION) { 	
		        	//地址不为空
				
		        	if(!ip_txt.getText().equals("") && !port_txt.getText().equals("")){
			        	//保存ip地址和端口号到文件
			        	try {
			        		//判断地址的格式是否正确
			        		if(!isboolIp(ip_txt.getText()))
			        			JOptionPane.showMessageDialog(null, "IP Address Format：0~255:0~255:0~255:0~255","IP Erroe",JOptionPane.ERROR_MESSAGE);
			        		//注册端口的范围从1024到49151 
			        		//动态端口49151~65535
			        		else if(1024 >Integer.valueOf(port_txt.getText()) || Integer.valueOf(port_txt.getText()) >65535)		        		
			        			JOptionPane.showMessageDialog(null, "Port Number ：1024~65535","Port Error",JOptionPane.ERROR_MESSAGE);
			        		else{
			        			// String data = "public static final String GRANULE_SERVER_IP = "+"\"" +ip_txt.getText()+"\" ;\n"
				        		//			+"public static final int LOCAL_AS_SERVER_PORT = " + port_txt.getText()+" ;\n";	
			        
			        			 //写入文件 
			        			
			        			//测试文件
			        			 //File file =new File("./src/gui/constant/GranuleAddress.txt");
			        			 
			        			//正式文件
			        			File file =new File("./src/gui/constant/GranuleConstant.java");
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
/*			        			 //constant文件中找到端口变量，服务器端口的改变
			        			 for(int i = 0;i<list.size();i++){
			        				 String str = list.get(i);
			        				 if(str.contains("public static final int LOCAL_AS_CLIENT_PORT = ")){
			        					 str = "	public static final int LOCAL_AS_CLIENT_PORT = " + port_txt.getText()+" ;\n";	
			        					 list.remove(i);	
			        					 list.add(i, str);
			        					break;
			        					}
			        				 //文件中没有这个变量 ，没有就直接保存
			        				 else{
			        					 if(i == list.size()-1){
			        						  list.add(position, "	public static final int LOCAL_AS_CLIENT_PORT = " + port_txt.getText()+" ;\n\n");
			        						  break;
			        					 }
			        					
			        				 }
			        			 }*/
			        			 
			        			 //ip地址
			        			 for(int i = 0;i<list.size();i++){
			        				 String str = list.get(i);			        					
			        				 if(str.contains("public static final String GRANULE_SERVER_IP = ")){
			        					str =  "	public static final String GRANULE_SERVER_IP = "+"\"" +ip_txt.getText()+"\" ;\n";				        				
			        					list.remove(i);
			        					list.add(i, str);

			        					break;
			        					}
			        				 //没有地址，保存地址
			        				 else{
			        					 if(i == list.size()-1){
			        						list.add(position, "	public static final String GRANULE_SERVER_IP = "+"\"" +ip_txt.getText()+"\" ;\n");
			        						
			        						break; 
			        					 }
			        					 
			        				 }
			        			 }
			        			 
			        			 
			        			 //true 追加文件  false 覆盖文件
			        			 FileWriter fileWritter = new FileWriter(file);
			        			 BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			        			 for(int i = 0;i<list.size();i++){
			        				 bufferWritter.write(list.get(i));
			        				// System.out.println(list.get(i));
			        			 }
			        			 in.close();
			        			 bufferWritter.close();
			        			 fileWritter.close();

			        			 JOptionPane.showMessageDialog(null, "Save Successful,Close the Dialog!");
			        			 
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
		        //选择取消保存
		       // else if (n == JOptionPane.NO_OPTION) {  
		            // ......  
		      //  }

			}
		});	

		
		//取消保存按钮
		JButton btnConcel = new JButton("Close");
	
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showConfirmDialog(null, "Quit the Dialog?", "GServer Info Comfirm Dialog", JOptionPane.YES_NO_OPTION); 
		        if (n == JOptionPane.YES_OPTION) {  
		        	iframe.dispose(); 
		        } else if (n == JOptionPane.NO_OPTION) {  
		            // ......  
		        }
				///iframe.dispose();				
			}
		});
		
		
		//组件在窗口的设置
		box1 = Box.createHorizontalBox();
		box2 = Box.createHorizontalBox();
		box3 = Box.createHorizontalBox();
		
		box1.add(ip);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(ip_txt);

		
		box2.add(port);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(port_txt);
		
		box3.add(btnConfirm);
		box3.add(Box.createHorizontalStrut(20));
		box3.add(btnConcel);
		
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
