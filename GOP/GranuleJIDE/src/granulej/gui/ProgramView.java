package granulej.gui;

import granulej.bo.Display;
import granulej.bo.GranuleDescriptor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;

import granulej.util.OpertionSystem;
import granulej.util.TempFile;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import AST.ContextVarDeclaration;
import AST.FieldDeclaration;
import AST.MethodDecl;
import AST.ShadowClassDecl;
import AST.TypeAccess;
import AST.TypeDecl;
import AST.BooleanType;
import AST.Access;
import config.GUIConfig;


public class ProgramView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane programPane = new JTabbedPane();

	private JTabbedPane granulePane = new JTabbedPane();

	private Display disp;

	private MainFrame main;

	private GUIConfig config;

	private Console console;
	
	private ImageIcon icon,icon_in, icon_cv;
	
	private List<ContextVarDeclaration> c_list=new LinkedList<ContextVarDeclaration>();
	
	private boolean isRetrieve=false;
	
	private JTable cjt;
	
	private static IndividualView newContentPane;
	
	public static boolean isReplace=false;
	
	public static ArrayList<String> list=new ArrayList<String>();


	public JTable getContextTable() {
		return cjt;
	}

	public void setContextTable(JTable cjt) {
		this.cjt = cjt;
	}

	public boolean isRetrieve() {
		return isRetrieve;
	}

	public void setRetrieve(boolean isRetrieve) {
		this.isRetrieve = isRetrieve;
	}

	public ProgramView(final MainFrame m, final GUIConfig conf, Console c) {
		main = m;
		config = conf;
		console = c;
		setLayout(new BorderLayout());
		icon = new ImageIcon("./image/outline.gif");
		programPane.addTab("Outline", icon, new JPanel());

		/*
		 * Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		 * programPane.setPreferredSize(new Dimension(screensize.width / 7,
		 * screensize.height *2/ 5));
		 */
		icon_in = new ImageIcon("./image/show_categories.gif");
		granulePane.addTab("Individual",icon_in,new JPanel());

		JSplitPane splitPaneProgram = new JSplitPane(JSplitPane.VERTICAL_SPLIT, programPane, granulePane);
		add(splitPaneProgram);
		setVisible(true);
		splitPaneProgram.setResizeWeight(0.1);
		splitPaneProgram.setOneTouchExpandable(false);
		splitPaneProgram.setDividerLocation((int) (MainFrame.screenHeight * 0.6)); // 设置分隔条的位置
	}

	public void init() {
		console.clearIO();
		String curDir=System.getProperty("java.class.path");
		String[] args = new String[5];
		args[0] = "-classpath";
		if(OpertionSystem.getSystemState().equals("windows"))
		args[1] = ".;" + curDir + config.getClasspath();
		else
	    args[1] = ".:" + curDir + config.getClasspath();
		args[2] = "-d";
		args[3] = config.getProjectBinPath();
		args[4] = config.getProjectSrcPath();

		disp = new Display(config);
		try {
			disp.compile(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setView(String cname) {
		init();
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		String[] names = new String[] { "Name", "Return Type", "Visibility" };
		List<Object> flist = disp.getMethods(cname);
		String[][] contents = new String[flist.size()][3];
		int fieldnum = 0;
		for (int i = 0; i < flist.size(); i++) {
			Object obj = flist.get(i);
			if (obj instanceof FieldDeclaration) {
				fieldnum++;
				FieldDeclaration fdecl = (FieldDeclaration) obj;
				contents[i][0] = fdecl.getID();
				Access acc=fdecl.getTypeAccess();
				/*if(acc instanceof ParTypeAccess){
				ParTypeAccess ptype=(ParTypeAccess)acc;
				contents[i][1]=((TypeAccess)ptype.getTypeAccess()).getID();
				}
				else{*/
				contents[i][1] = ((TypeAccess)acc).getID();
				//}
				contents[i][2] = fdecl.getModifiers().toString();
			}
			if (obj instanceof MethodDecl || obj instanceof ShadowClassDecl) {
				MethodDecl mdecl = (MethodDecl) obj;
				contents[i][0] = mdecl.signature();
				contents[i][1] = ((TypeAccess) mdecl.getTypeAccess()).getID();
				contents[i][2] = mdecl.getModifiers().toString();
			}
		}

		JTable jt = new JTable(contents, names);
		int len = jt.getColumnCount();
		for (int j = 0; j < len; j++) {
			TableColumn tc = jt.getColumnModel().getColumn(j);
			tc.setCellRenderer(renderer);
		}
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.setColumnSelectionAllowed(false);
		jt.setEnabled(false);
		if (fieldnum > 0) {
			jt.setRowSelectionInterval(0, fieldnum - 1);
			jt.setSelectionBackground(Color.lightGray);
		}
		jt.setGridColor(Color.GRAY);
		JScrollPane jp = new JScrollPane(jt);
		programPane.removeTabAt(0);
		programPane.addTab("Outline",icon,jp);

		String[] cnames = new String[] { "Name", "Type", "Value" };
		c_list = disp.getContext();
		isRetrieve=true;
		int length=c_list.size();
		String[][] contexts = new String[length][3];
		//从临时文件获取
		String[][] values=null;
		String[] tvalue=null;
		String[] twalue=null;
		String temp=new TempFile().readFile().trim();		
		int l=0;
		if(!"".equals(temp)){
		tvalue=temp.split(";");
		twalue=new String[2];
		l=tvalue.length;
		}
		if(l>0){		
		values=new String[l][2];
		}
		for(int t=0; t<l;t++){
			twalue=tvalue[t].trim().split(":");		
			values[t][0]=twalue[0];
			values[t][1]=twalue[1];
		 }
		
		for (int i = 0; i < length; i++) {
			ContextVarDeclaration obj = c_list.get(i);
			contexts[i][0] = obj.getID();
			contexts[i][1] = ((TypeAccess) obj.getTypeAccess()).getID();
			TypeDecl type = obj.type();
			if("".equals(temp)){		
			if (type.isPrimitive()) {
				if (type instanceof BooleanType) {
					contexts[i][2] = "false";
				} else
					contexts[i][2] = "0";
			} else{
				contexts[i][2] = "null";
			 }
		    }
			else {
			    int f=0;
			    boolean flag=false;
				for(;f<l;f++)
				{
					if(values[f][0].equals(contexts[i][0])){
					flag=true;
					break;
					}
				}
			   if(flag){				
				contexts[i][2]=values[f][1];
			   }
			   else{
				if (type.isPrimitive()) {
				   if (type instanceof BooleanType) {
						contexts[i][2] = "false";
					  } else
						contexts[i][2] = "0";
			    } else
						contexts[i][2] = "null";
			   }
		    }
		 }
		cjt = new JTable(contexts, cnames);
		int size = cjt.getColumnCount();
		for (int i = 0; i < size; i++) {
			TableColumn tc = cjt.getColumnModel().getColumn(i);
			tc.setCellRenderer(renderer);
		}
		cjt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		cjt.setColumnSelectionAllowed(false);
		cjt.setEnabled(false);
		cjt.setGridColor(Color.GRAY);
		JScrollPane cjp = new JScrollPane(cjt);
		main.getPackageTree().getContextPane().removeTabAt(0);
		icon_cv=new ImageIcon("./image/members.gif");
		main.getPackageTree().getContextPane().addTab("Context Variable",icon_cv,cjp);  
       
		List<GranuleDescriptor> gdlist = disp.getGranules();
		newContentPane = new IndividualView(gdlist);
		newContentPane.setOpaque(true);
		newContentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
		granulePane.removeTabAt(0);
		granulePane.addTab("Individual",icon_in,newContentPane);
	}


	public MainFrame getMain() {
		return main;
	}

	public void setMain(MainFrame main) {
		this.main = main;
	}

	public List<ContextVarDeclaration> getContextList() {
		return c_list;
	}

	public void setContextList(List<ContextVarDeclaration> c_list) {
		this.c_list = c_list;
	}

	public static IndividualView getNewContentPane() {
		return newContentPane;
	}

	public static void setNewContentPane(IndividualView newContentPane) {
		ProgramView.newContentPane = newContentPane;
	}	
	
	
  }