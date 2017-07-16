

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
 * 启动上下文服务器
 */

public class StartCServer extends JFrame implements ActionListener {		

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
	
	//选择模式
	//private Choice ip_txt,port_txt;

	
	/*
	 * 创建窗口
	 */
	public StartCServer() {		
		setLayout(new FlowLayout());
		init();
		setTitle("Context Server");
		setSize(300, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	/*
	 * 初始化窗口内组件
	 * 
	 */
	
	public void init(){
		
		start = new JButton("Start");
		end = new JButton("Close");
		ip = new JLabel("IP Address:");
		port = new JLabel("Port Number:");
		
		ip_txt = new JTextField(8);
		port_txt = new JTextField(8);
		
		//获取服务器运行本地的ip

		//公网ip
	//	ip_txt.setText("202.117.10.236");

		// 局域网的ip 不是公网ip
 		try {
			ip_txt.setText(InetAddress.getLocalHost().getHostAddress().toString());	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//端口为constant变量
		port_txt.setText(String.valueOf(ConstantVariable.CONTEXT_SERVER_PORT));

		//端口和ip为固定  不可编辑
		ip_txt.setEditable(false);	
		port_txt.setEditable(false);
		
		
/*		ip_txt = new Choice();
		port_txt = new Choice();
		
		try {
			ip_txt.add(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ip_txt.add((new InetSocketAddress(ConstantVariable.CONTEXT_SERVER_PORT)).toString());
		port_txt.add(String.valueOf(ConstantVariable.CONTEXT_CLIENT_PORT));
		port_txt.add(String.valueOf(ConstantVariable.CONTEXT_SERVER_PORT));*/
		
		
		//组件在窗口的设置
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
		
		//加按钮响应
		start.addActionListener(this);
		end.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start){
			new Thread(CServerConnection.getInstance()).start();
			JOptionPane.showMessageDialog(null, "Context Server is running !");
		}
		if(e.getSource() == end){
			JOptionPane.showMessageDialog(null, "Context Server is close !");
			System.exit(0);
			
		}
		
	}
	
	 
	
	public static void main(String[] args) {
		new StartCServer();
	}

}
