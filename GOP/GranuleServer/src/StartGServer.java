
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * 启动粒服务器
 */

public class StartGServer extends JFrame implements ActionListener {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用来布局的
	private Box basebox,box1,box2,box3;
	//服务器的开启和关闭
	private JButton start,end;
	// ip和端口的标签和显示 
	private JLabel ip,port;
	private JTextField ip_txt,port_txt;
	//private Choice ip_txt,port_txt;

	
	/*
	 * 创建窗口
	 */
	public StartGServer() {		
		setLayout(new FlowLayout());
		init();
		setTitle("Granule Server");
		setSize(300, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	
	/*
	 * 初始化各个组件
	 */
	public void init(){
		start = new JButton("Start");
		end = new JButton("Close");
		ip = new JLabel("IP Address:");
		port = new JLabel("Port Number:");
		

		ip_txt = new JTextField(8);
		port_txt = new JTextField(8);
		//获取本地的ip
//		ip_txt.setText("202.117.10.236");
		try {
			ip_txt.setText(InetAddress.getLocalHost().getHostAddress().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//constant文件里面设置的端口号
		port_txt.setText(String.valueOf(ConstantVariable.REMOTE_AS_SERVER_PORT));
		
		//服务器的ip和端口是固定的  不可编辑
		ip_txt.setEditable(false);	
		port_txt.setEditable(false);
/*		ip_txt = new Choice();
		port_txt = new Choice();
		
		try {
			ip_txt.add(InetAddress.getLocalHost().getHostAddress().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ip_txt.add("127.0.0.1");
		port_txt.add("port1");
		port_txt.add("port2");*/
		
		//布局设计
		box1 = Box.createHorizontalBox();
		box2 = Box.createHorizontalBox();
		box3 = Box.createHorizontalBox();

		box1.add(ip);
		box1.add(Box.createHorizontalStrut(14));
		box1.add(ip_txt);
		
		box2.add(port);
		box2.add(Box.createHorizontalStrut(8));
		box2.add(port_txt);
		
		box3.add(start);
		box3.add(Box.createHorizontalStrut(20));
		box3.add(end);
		
		basebox = Box.createVerticalBox();
		basebox.add(Box.createVerticalStrut(5));
		basebox.add(box1);
		basebox.add(Box.createVerticalStrut(10));
		basebox.add(box2);
		basebox.add(Box.createVerticalStrut(15));
		basebox.add(box3);
		basebox.add(Box.createVerticalStrut(10));
		
		add(basebox);
		
		//按钮响应
		start.addActionListener(this);
		end.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start){
			new Thread(GServerConnection.getInstance()).start();
			JOptionPane.showMessageDialog(null, "Granule Server is running !");
		}
		if(e.getSource() == end){
			JOptionPane.showMessageDialog(null, "Granule Server is close !");
			System.exit(0);
			
		}
		
	}
		
	public static void main(String[] args) {
		new StartGServer();
	}

}
