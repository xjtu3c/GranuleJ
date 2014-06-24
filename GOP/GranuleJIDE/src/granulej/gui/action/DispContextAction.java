package granulej.gui.action;

import granulej.gui.MainFrame;
import granulej.gui.ProgramView;
import granulej.util.TempFile;
import gui.constant.ContextConstant;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import AST.BooleanType;
import AST.ContextVarDeclaration;
import AST.TypeAccess;
import AST.TypeDecl;

import config.GUIConfig;

public class DispContextAction implements ActionListener {
	private MainFrame main;

	private GUIConfig config;

	private ProgramView view;

	public DispContextAction(ProgramView view) {
		this.view = view;
	}

	public void actionPerformed(ActionEvent arg0) {
		final JFrame iframe = new JFrame("Context Variable");

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		iframe.setSize(680, 350);
		iframe.setLocation((screensize.width - 350) / 2, (screensize.height - 250) / 2);
		iframe.setVisible(true);
		iframe.setResizable(true);

		iframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				view.getMain().setEnabled(true);
			}
		});

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		iframe.getContentPane().setLayout(gridbag);

		JLabel tname = new JLabel("Context variable value can be modified.");
		addComponent(iframe, gridbag, c, tname, 0, 0, 4, 1);

		JLabel name, type, value;
		final int len;
		int n;
		LinkedList<ContextVarDeclaration> clist = (LinkedList<ContextVarDeclaration>) view.getContextList();
		if (!view.isRetrieve()) {
			len = 1;
		} else {
			len = clist.size();
		}
		final JTextField[] name_set = new JTextField[len];
		final JTextField[] type_set = new JTextField[len];
		final JTextField[] value_set = new JTextField[len];
		String[][] val_set = null;
		String[] tvalue=null;
		String[] twalue=null;
		String temp = getTempFileContent();
		int l=0;
		if (!"".equals(temp)) {
			tvalue=temp.split(";");
			twalue=new String[2];
			l=tvalue.length;
		}
		if(l>0){
		 val_set=new String[l][2];	
		}
		for(int t=0; t<l;t++){
			twalue=tvalue[t].split(":");
			val_set[t][0]=twalue[0];
			val_set[t][1]=twalue[1];
		}
		
		for (n = 1; n <= len; n++) {
			String na = "";
			String ty = "";
			String vl = "";
			if (clist.size() > 0) {
				ContextVarDeclaration obj = clist.get(n - 1);
				na = obj.getID();
				ty = ((TypeAccess) obj.getTypeAccess()).getID();
				TypeDecl o_type = obj.type();
				if ("".equals(temp)) {				
					if (o_type.isPrimitive()) {
						if (o_type instanceof BooleanType)
							vl = "false";
						else
							vl = "0";
					} else {
						vl = "null";
					}
				} else {
					 int f=0;
					    boolean flag=false;
						for(;f<l;f++)
						{
							if(val_set[f][0].equals(na)){
							flag=true;
							break;
							}
						}
					   if(flag){				
						   vl=val_set[f][1];
					   }
					   else{
						if (o_type.isPrimitive()) {
						   if (o_type instanceof BooleanType) {
								vl="false";
							  } else
								vl="0";
					       } else
								vl="null";
					    }
				    }
			}
			name = new JLabel("Name:");
			addComponent(iframe, gridbag, c, name, n, 0, 1, 1);

			name_set[n - 1] = new JTextField(25);
			name_set[n - 1].setEditable(false);
			name_set[n - 1].setText(na);
			addComponent(iframe, gridbag, c, name_set[n - 1], n, 1, 1, 1);

			type = new JLabel("Type:");
			addComponent(iframe, gridbag, c, type, n, 2, 1, 1);

			type_set[n - 1] = new JTextField(25);
			type_set[n - 1].setEditable(false);
			type_set[n - 1].setText(ty);
			addComponent(iframe, gridbag, c, type_set[n - 1], n, 3, 1, 1);

			value = new JLabel("Value:");
			addComponent(iframe, gridbag, c, value, n, 4, 1, 1);

			value_set[n - 1] = new JTextField(25);
			value_set[n - 1].setEditable(true);
			value_set[n - 1].setText(vl);
			addComponent(iframe, gridbag, c, value_set[n - 1], n, 5, 1, 1);

		}

		JLabel ename = new JLabel(" ");
		addComponent(iframe, gridbag, c, ename, n + 1, 0, 1, 1);

		JButton btnConfirm = new JButton("Update");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String c_seq = "";
				String new_value;
				JTable ct = view.getContextTable();
				for (int j = 0; j < len; j++) {
					new_value = value_set[j].getText().trim();
					if (new_value == null && new_value.length() == 0) {
						JOptionPane.showMessageDialog(null, "Please input context value !", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//数据验证
					else {
						String type_v = type_set[j].getText().trim();
//						if (type_v.equals("String")) {
//							Pattern p = Pattern.compile("[a-zA-Z]*");
//							Matcher m = p.matcher(new_value);
//							if (!m.matches()) {
//								JOptionPane.showMessageDialog(null, "Value'" + new_value + "' of " + "Type '" + type_v + "' is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
//								return;
//							}
//						}
						if (type_v.equals("boolean")) {
							if (!new_value.equals("true") && !new_value.equals("false")) {
								JOptionPane.showMessageDialog(null, "Value'" + new_value + "' of " + "Type '" + type_v + "' is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						} else {
							try {
								if (type_v.equals("int"))
									Integer.parseInt(new_value);
								else if (type_v.equals("double"))
									Double.parseDouble(new_value);
								else if (type_v.equals("byte"))
									Byte.parseByte(new_value);
								else if (type_v.equals("short"))
									Short.parseShort(new_value);
								else if (type_v.equals("long"))
									Long.parseLong(new_value);
								else if (type_v.equals("float"))
									Float.parseFloat("float");
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Value'" + new_value + "' of " + "Type '" + type_v + "' is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
					c_seq = c_seq + name_set[j].getText().trim() + ":" + new_value;
					System.out.println("Context sequence is :"+c_seq);
					if (j < len - 1) {
						c_seq = c_seq + ";";
					}
					//更新ProgramView组件JTable的值	
					ct.setValueAt(new_value, j, 2);
				}
				iframe.dispose();
				
				//更新到临时文件
				updateTempFile(c_seq);
				//通信更新
				new HandleUpdate(c_seq).start();
			}
		});

		addComponent(iframe, gridbag, c, btnConfirm, n + 2, 3, 1, 1);

		JButton btnConcel = new JButton("Cancel");
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iframe.dispose();
				view.getMain().setEnabled(true);
			}
		});
		addComponent(iframe, gridbag, c, btnConcel, n + 2, 5, 1, 1);
	}	
	
	public void updateTempFile(String newValue) {
		TempFile tf = new TempFile();
		tf.writeFile(newValue);
	}

	public String getTempFileContent() {
		TempFile temp = new TempFile();
		return temp.readFile();
	}

	public class HandleUpdate extends Thread {
		private String c_seq;

		public HandleUpdate(String seq) {
			this.c_seq = seq;
		}

		public void run() {
			if("".equals(c_seq))
			return ;
			Socket socket = null;
			OutputStream socketPut;
			PrintWriter socketWriter;
			System.out.println("Updating ...");
			try {
				socket = new Socket(InetAddress.getLocalHost(), ContextConstant.CONTEXT_CLIENT_PORT);
				socketPut = socket.getOutputStream();
				socketWriter = new PrintWriter(socketPut);
				socketWriter.println(ContextConstant.CONTEXT_UPDATE);
				socketWriter.println(ContextConstant.CONTEXT_UPDATE_FROM_LOCAL);
				System.out.println("update sequence is : "+ c_seq);
				socketWriter.println(c_seq);
				socketWriter.flush();
				System.out.println("Context value update information is sending ...");
				socketPut.close();
				socketWriter.close();
				socket.close();
			} catch (BindException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void addComponent(Container container, GridBagLayout layout, GridBagConstraints constraints, Component componentToAdd, int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;

		constraints.gridwidth = width; // 组件宽度
		constraints.gridheight = height; // 组件高度

		constraints.weightx = 0; // 组件在水平方向的拉伸能力
		constraints.weighty = 0; // 组件在垂直方向的拉伸能力

		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;

		layout.setConstraints(componentToAdd, constraints); // 设置目标组件的约束
		container.add(componentToAdd); // 在容器中添加目标组件
	}

	public GUIConfig getConfig() {
		return config;
	}

	public void setConfig(GUIConfig config) {
		this.config = config;
	}

	public MainFrame getMain() {
		return main;
	}

	public void setMain(MainFrame main) {
		this.main = main;
	}

	public ProgramView getView() {
		return view;
	}

	public void setView(ProgramView view) {
		this.view = view;
	}

}
