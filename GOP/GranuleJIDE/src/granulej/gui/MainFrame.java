package granulej.gui;

import granulej.bean.FileStatus;
import granulej.bean.JClosableTabbedPane;
import granulej.context.client.CClientConnection;
import granulej.granule.client.GClientConnection;
import granulej.gui.action.AboutGranuleJAction;
import granulej.gui.action.BuildAction;
import granulej.gui.action.CleanAction;
import granulej.gui.action.CloseAction;
import granulej.gui.action.ConfigAction;
import granulej.gui.action.ContextGranuleJAction;
import granulej.gui.action.CopyAction;
import granulej.gui.action.CutAction;
import granulej.gui.action.DeleteTextAction;
import granulej.gui.action.DispContextAction;
import granulej.gui.action.DispGranuleBaseAction;
import granulej.gui.action.DispHistoryAction;
import granulej.gui.action.DispIndiAction;
import granulej.gui.action.DispNetSimilarAction;
import granulej.gui.action.FindAndReplaceAction;
import granulej.gui.action.GVMConfigAction;
import granulej.gui.action.GranueGranuleJAction;
import granulej.gui.action.NewClassAction;
import granulej.gui.action.NewGranuleAction;
import granulej.gui.action.NewInterfaceAction;
import granulej.gui.action.NewPackageAction;
import granulej.gui.action.NewProjectAction;
import granulej.gui.action.NewShadowAction;
import granulej.gui.action.PasteAction;
import granulej.gui.action.RunAction;
import granulej.gui.action.SimulateConfigAction;
import granulej.gui.action.StopAction;
import granulej.gui.action.SwitchAction;
import granulej.util.LookAndFeelUtility;
import granulej.util.TempFile;
import granulej.util.xmlUtil;
import gui.constant.ContextConstant;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import config.GUIConfig;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GUIConfig config = new GUIConfig();

	// ====================================IDE环境组成部分=============================
	private Editor editor;

	private PackageTree packageTree;

	private Console console;

	private ProgramView program;

	private static GClientConnection gc;

	private static CClientConnection cc;

	private JButton save;

	private JButton saveAll;

	private JButton start;

	private static MainFrame mf;

	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	public MainFrame() {
		super("GranuleJ IDE");

		setLookAndFeel();
		init();

		editor = new Editor(this, config); // editor和console形成中间一栏
		packageTree = new PackageTree(this, config, editor); // 最左边一栏
		console = Console.getInstance();
		program = new ProgramView(this, config, console); // 最右边一栏
		addMenu();
		addTool();

		// 将editor和console塞到一个Jpanel中（中间一栏）
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		JSplitPane splitPaneContent = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editor, console);
		content.add(splitPaneContent);
		content.setVisible(true);
		splitPaneContent.setOneTouchExpandable(true);
		splitPaneContent.setDividerLocation((int) (screenHeight * 0.6)); // 设置分隔条的位置

		save.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) {
					JClosableTabbedPane jtp = editor.getTabbedPane();
					setSingleFileStatus(jtp.getTitleAt(jtp.getSelectedIndex()), FileStatus.SAVED);
				}
			}
		});
		saveAll.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) {
					setMultiFileStatus(FileStatus.SAVED);
				}
			}
		});
		JSplitPane splitPane_PaC = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, packageTree, content);
		splitPane_PaC.setDividerLocation((int) (screenWidth * 0.2));

		JSplitPane splitPaneC_PR = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane_PaC, program);
		getContentPane().add(splitPaneC_PR);

		Image icon = Toolkit.getDefaultToolkit().getImage("./image/granulej.gif");
		setIconImage(icon);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				writeProjectPath();
			}
		});
		loadExistedProject();
		setVisible(true);
		splitPane_PaC.setResizeWeight(0.1);
		splitPane_PaC.setOneTouchExpandable(false);
		splitPane_PaC.setDividerLocation(0.15);

		splitPaneC_PR.setResizeWeight(0.1);
		splitPaneC_PR.setOneTouchExpandable(false);
		splitPaneC_PR.setDividerLocation(0.7);

		// 本地个体库初始化
		gc = GClientConnection.getInstance();
		new Thread(gc).start();

		// 本地上下文库初始化
		cc = CClientConnection.getInstance();
		new Thread(cc).start();

		
		

	}

	public void start() {
		start.setEnabled(true);
		start.setToolTipText("Terminate");
		start.setIcon(new ImageIcon("./image/start.gif"));
	}

	public void stop() {
		start.setEnabled(false);
		start.setIcon(new ImageIcon("./image/stop.gif"));
	}

	public void writeProjectPath() {
		if (config.getProjectPath() == null || config.getProjectPath().length() == 0)
			return;
		File file = new File(System.getProperty("user.dir") + "/project.txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(config.getProjectPath() + "\n");
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void serachFiles(String dir) {
		File root = new File(dir);
		File[] filesOrDirs = root.listFiles();
		boolean hasDefault = false;
		if (filesOrDirs != null) {
			for (int i = 0; i < filesOrDirs.length; i++) {
				if (filesOrDirs[i].isDirectory()) {
					if (filesOrDirs[i].listFiles().length == 0) {
						String name = filesOrDirs[i].getAbsolutePath();
						String pckName = name.substring(name.indexOf(config.getProjectSrcPath()) + config.getProjectSrcPath().length() + 1);
						if (pckName.lastIndexOf(File.separator) != -1)
							pckName = pckName.replace(File.separator, ".");
						config.setSelect(packageTree.newPackage(pckName, false));
					} else
						serachFiles(filesOrDirs[i].getAbsolutePath());
				} else {
					String name = filesOrDirs[i].getAbsolutePath();
					if (name.endsWith(".java")) {
						config.fileAndPath.put(filesOrDirs[i].getName(), filesOrDirs[i].getAbsolutePath());
						config.fileStatus.put(filesOrDirs[i].getName(), FileStatus.SAVED);
						String pckName = name.substring(name.indexOf(config.getProjectSrcPath()) + config.getProjectSrcPath().length() + 1);

						if (pckName.lastIndexOf(File.separator) != -1) {
							pckName = pckName.substring(0, pckName.lastIndexOf(File.separator));
							pckName = pckName.replace(File.separator, ".");
						} else {
							pckName = "(default package)";
							hasDefault = true;
						}
						config.setSelect(packageTree.newPackage(pckName, false));
						packageTree.newClass(filesOrDirs[i].getName());
					}
				}
			}
		}
		if (!hasDefault) {
			packageTree.newPackage("(default package)", false);
		}
	}

	void loadExistedProject() {
		try {
			File file = new File("./project.txt");
			if (!file.exists())
				return;
			BufferedReader bf = new BufferedReader(new FileReader("./project.txt"));
			String ppath = bf.readLine();
			File pfile = new File(ppath);
			if (!pfile.exists()) {
				// 不存在就创建目录，窗口也启动
				pfile.mkdirs();
				// bf.close();
				file.delete();
				return ;
			}

			config.setProjectName(ppath.substring(ppath.lastIndexOf(File.separator) + 1));
			config.setProjectPath(ppath);
			packageTree.newProject(config.getProjectName());
			serachFiles(ppath + File.separator + "src");
			config.setClasspath(loadExistedClassPath());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String loadExistedClassPath()
	{
		String filename=config.getProjectPath() + File.separator + ".classpath";	
		String class_path="";
		if(!new File(filename).exists())
		return class_path;
		Document doc=xmlUtil.load(filename);
		Element rootElement = doc.getDocumentElement();		
		NodeList nodes = rootElement.getChildNodes();
		String attribute="";
		int len=nodes.getLength();
		for (int i = 0; i < len; i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {
				Element child = (Element) node;
				attribute = child.getAttribute("path");
				class_path=class_path+";"+attribute;
			}
		}
      return class_path;	
	}

	void setLookAndFeel() {
		try {
			if (LookAndFeelUtility.getLookAndFeel() != null) {
				// 所使用观感
				UIManager.setLookAndFeel(LookAndFeelUtility.getLookAndFeel());
				// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			    //String lookAndFeel=UIManager.getSystemLookAndFeelClassName();
				//UIManager.setLookAndFeel(lookAndFeel);
				SwingUtilities.updateComponentTreeUI(this);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screensize.width, screensize.height);
		UIDefaults defaults = UIManager.getDefaults();
		defaults.remove("SplitPane.border");
		defaults.remove("SplitPaneDivider.border");
	}

	void addMenu() {
		JMenuBar mBar = new JMenuBar();
		// ========================="文件"菜单部分=========================
		JMenu fileMenu = new JMenu(ConstString.menus[0]);

		// 以下是"新建"子菜单
		JMenu fileSubMenu = new JMenu(ConstString.fileItems[0]);
		JMenuItem fileItemsNewProject = new JMenuItem(ConstString.fileSubItems[0], new ImageIcon("./image/newjprj_wiz.gif"));
		JMenuItem fileItemsNewPackage = new JMenuItem(ConstString.fileSubItems[1], new ImageIcon("./image/newpack_wiz.gif"));
		JMenuItem fileItemsNewClass = new JMenuItem(ConstString.fileSubItems[2], new ImageIcon("./image/newclass_wiz.gif"));
		JMenuItem fileItemsNewInterface = new JMenuItem(ConstString.fileSubItems[3], new ImageIcon("./image/newint_wiz.gif"));
		JMenuItem fileItemsNewGranule = new JMenuItem(ConstString.fileSubItems[4], new ImageIcon("./image/granule.gif"));
		JMenuItem fileItemsNewShadow = new JMenuItem(ConstString.fileSubItems[5], new ImageIcon("./image/shadow.gif"));

		fileItemsNewProject.addActionListener(new NewProjectAction(this, config));
		fileItemsNewPackage.addActionListener(new NewPackageAction(this, config));
		fileItemsNewClass.addActionListener(new NewClassAction(this, config));
		fileItemsNewInterface.addActionListener(new NewInterfaceAction(this, config));
		fileItemsNewGranule.addActionListener(new NewGranuleAction(this, config));
		fileItemsNewShadow.addActionListener(new NewShadowAction(this, config));

		fileSubMenu.add(fileItemsNewProject);
		fileSubMenu.addSeparator();
		fileSubMenu.add(fileItemsNewPackage);
		fileSubMenu.add(fileItemsNewClass);
		fileSubMenu.add(fileItemsNewInterface);
		fileSubMenu.add(fileItemsNewGranule);
		fileSubMenu.add(fileItemsNewShadow);

		fileMenu.add(fileSubMenu);
		fileMenu.addSeparator();

		// 以下是"保存"菜单项
		JMenuItem fileItemsSave = new JMenuItem(ConstString.fileItems[1], new ImageIcon("./image/to_saveall_edit.gif"));
		fileItemsSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JClosableTabbedPane jtp = editor.getTabbedPane();
				for (int i = 0; i < jtp.getTabCount(); i++)
					setSingleFileStatus(jtp.getTitleAt(i), FileStatus.SAVED);
			}
		});

		fileMenu.add(fileItemsSave);

		JMenuItem fileItemsSwitch = new JMenuItem(ConstString.fileItems[2]);
		fileItemsSwitch.addActionListener(new SwitchAction(this, config));
		fileMenu.add(fileItemsSwitch);

		JMenuItem fileItemsClose = new JMenuItem(ConstString.fileItems[3]);
		fileItemsClose.addActionListener(new CloseAction(this, config));
		fileMenu.add(fileItemsClose);

		mBar.add(fileMenu);
		// ========================"编辑"菜单部分============================
		JMenu editMenu = new JMenu(ConstString.menus[1]);
		final JMenuItem editItemsUndo = new JMenuItem(ConstString.editItems[0], new ImageIcon("./image/undo_edit.gif"));
		final JMenuItem editItemsRedo = new JMenuItem(ConstString.editItems[1], new ImageIcon("./image/redo_edit.gif"));
		editItemsUndo.setEnabled(false);
		editItemsRedo.setEnabled(false);
		
		//重做和撤销
		final UndoManager undoManager=new UndoManager();
		
		JClosableTabbedPane jtp=editor.getTabbedPane();
		 jtp.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent changeEvent) {
	          JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
	          int index = sourceTabbedPane.getSelectedIndex();
	          if(index==-1)
	          return ;
	          JScrollPane aa=(JScrollPane)sourceTabbedPane.getComponent(index);
	          JTextPane jp=(JTextPane)(aa.getViewport().getView());	 
	          jp.getDocument().addUndoableEditListener(undoManager);
	          undoManager.addEdit(new AbstractUndoableEdit(){
	                public void redo() throws javax.swing.undo.CannotRedoException
	                {
	                   super.redo();
	                   editItemsUndo.setEnabled(undoManager.canUndo());
	                   editItemsRedo.setEnabled(undoManager.canRedo());
	                }  
	                public void undo() throws javax.swing.undo.CannotUndoException
	                {
	                   super.undo();
	                   editItemsUndo.setEnabled(undoManager.canUndo());
	                   editItemsRedo.setEnabled(undoManager.canRedo());
	                }   
	          });	     
             editItemsUndo.setEnabled(undoManager.canUndo());
             editItemsRedo.setEnabled(undoManager.canRedo());          
	        }
		 });
		
		editItemsUndo.addActionListener(new ActionListener()
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	            if (undoManager.canUndo())
	            {
	               undoManager.undo();
	            }
	            editItemsUndo.setEnabled(undoManager.canUndo());
	            editItemsRedo.setEnabled(undoManager.canRedo());
	         }
	      });
	    editItemsRedo.addActionListener(new ActionListener()
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	            if (undoManager.canRedo())
	            {
	               undoManager.redo();
	            }	            editItemsUndo.setEnabled(undoManager.canUndo());
	            editItemsRedo.setEnabled(undoManager.canRedo());
	         }
	      });
    
		JMenuItem editItemsCut = new JMenuItem(ConstString.editItems[2], new ImageIcon("./image/cut_edit.gif"));
		editItemsCut.addActionListener(new CutAction(editor));
		JMenuItem editItemsCopy = new JMenuItem(ConstString.editItems[3], new ImageIcon("./image/copy_edit.gif"));
		editItemsCopy.addActionListener(new CopyAction(editor));
		JMenuItem editItemsPaste = new JMenuItem(ConstString.editItems[4], new ImageIcon("./image/paste_edit.gif"));
		editItemsPaste.addActionListener(new PasteAction(editor));
		JMenuItem editItemsDelete = new JMenuItem(ConstString.editItems[5], new ImageIcon("./image/delete_edit.gif"));
		editItemsDelete.addActionListener(new DeleteTextAction(editor));
		JMenuItem editItemsFindAndReplace = new JMenuItem(ConstString.editItems[6]);
		editItemsFindAndReplace.addActionListener(new FindAndReplaceAction(editor));
		editMenu.add(editItemsUndo);
		editMenu.add(editItemsRedo);
		editMenu.add(editItemsCut);
		editMenu.add(editItemsCopy);
		editMenu.add(editItemsPaste);
		editMenu.add(editItemsDelete);
		editMenu.add(editItemsFindAndReplace);
		mBar.add(editMenu);

		// ========================"工程"菜单部分============================
		// 编译
		JMenu projectMenu = new JMenu(ConstString.menus[2]);
		JMenuItem projectItemsCompile = new JMenuItem(ConstString.projectItems[0], new ImageIcon("./image/build_exec.gif"));
		projectItemsCompile.addActionListener(new BuildAction(config));
		// 清理
		JMenuItem projectItemsClean = new JMenuItem(ConstString.projectItems[1]);
		projectItemsClean.addActionListener(new CleanAction(config));

		JMenuItem projectItemsSettings = new JMenuItem(ConstString.projectItems[2]);
		projectItemsSettings.addActionListener(new ConfigAction(this, config));
		JMenuItem projectItemsSimulate = new JMenuItem(ConstString.projectItems[3]);
		projectItemsSimulate.addActionListener(new SimulateConfigAction(this, config));

		projectMenu.add(projectItemsCompile);
		projectMenu.add(projectItemsClean);
		projectMenu.add(projectItemsSettings);
		projectMenu.add(projectItemsSimulate);
		mBar.add(projectMenu);
		// ========================"运行"菜单部分============================
		JMenu runMenu = new JMenu(ConstString.menus[3]);
		JMenuItem runItemsRun = new JMenuItem(ConstString.runItems[0], new ImageIcon("./image/runlast_co.gif"));
		JMenuItem runItemsDebug = new JMenuItem(ConstString.runItems[1], new ImageIcon("./image/debuglast_co.gif"));
		JMenuItem runItemsSet = new JMenuItem(ConstString.runItems[2]);

		runItemsRun.addActionListener(new RunAction(this, config, console));
		runItemsSet.addActionListener(new GVMConfigAction(this, config));
		runMenu.add(runItemsRun);
		runMenu.add(runItemsDebug);
		runMenu.add(runItemsSet);
		mBar.add(runMenu);
		// ========================"窗口"菜单部分============================
		JMenu windowMenu = new JMenu(ConstString.menus[4]);
		JMenuItem windowItemsContext = new JMenuItem(ConstString.windowItems[0], new ImageIcon("./image/context.gif"));
		windowItemsContext.addActionListener(new DispContextAction(program));

		JMenuItem windowItemsEvoHistroy = new JMenuItem(ConstString.windowItems[1], new ImageIcon("./image/history_list.gif"));
		windowItemsEvoHistroy.addActionListener(new DispHistoryAction(config));

		JMenuItem windowItemsIndHierarchy = new JMenuItem(ConstString.windowItems[2], new ImageIcon("./image/tree_obj.gif"));
		windowItemsIndHierarchy.addActionListener(new DispIndiAction(config));

		JMenuItem windowItemsNetGranule = new JMenuItem(ConstString.windowItems[3], new ImageIcon("./image/granule_base.gif"));
		windowItemsNetGranule.addActionListener(new DispNetSimilarAction(config));

		JMenuItem windowItemsGranuleBase = new JMenuItem(ConstString.windowItems[4], new ImageIcon("./image/repository_rep.gif"));
		windowItemsGranuleBase.addActionListener(new DispGranuleBaseAction(config));

		windowMenu.add(windowItemsContext);
		windowMenu.add(windowItemsEvoHistroy);
		windowMenu.add(windowItemsIndHierarchy);
		windowMenu.add(windowItemsNetGranule);
		windowMenu.add(windowItemsGranuleBase);
		mBar.add(windowMenu);
		// ========================"帮助"菜单部分============================
		JMenu helpMenu = new JMenu(ConstString.menus[5]);
		JMenuItem helpItemsAbout = new JMenuItem(ConstString.helpItems[0], new ImageIcon("./image/about.gif"));
		helpItemsAbout.addActionListener(new AboutGranuleJAction(this));
		JMenuItem helpItemsWebsite = new JMenuItem(ConstString.helpItems[1], new ImageIcon("./image/web.png"));
		helpItemsWebsite.addActionListener(new AboutGranuleJAction(this));
		helpMenu.add(helpItemsAbout);
		helpMenu.add(helpItemsWebsite);
		mBar.add(helpMenu);
		setJMenuBar(mBar);
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~来自孙丽玉的波浪线~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//======================“服务器”部分====================================
		//~~~~~~~~~~~~~~~~~~~~~~~~~来自孙丽玉的波浪线~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		/*
		 * 在ConstString文件中增加了服务器的信息
		 * 在action文件夹中增加上下文服务器和粒服务器的响应文件
		 * GranueGranuleJAction 粒服务器
		 * ContextGranuleJAction上下文服务器
		 */
		JMenu serverMenu = new JMenu(ConstString.menus[6]);
		
		JMenuItem serverItemscontext = new JMenuItem(ConstString.serverItems[0]);
		serverItemscontext.addActionListener(new ContextGranuleJAction(this));
		
		JMenuItem serverItemsgranue = new JMenuItem(ConstString.serverItems[1]);
		serverItemsgranue.addActionListener(new GranueGranuleJAction(this));
		
		serverMenu.add(serverItemscontext);
		serverMenu.add(serverItemsgranue);
		mBar.add(serverMenu);
		setJMenuBar(mBar);
		
	}

	public void changeStatus(String name) {
		FileStatus status = config.fileStatus.get(name);
		if (status == FileStatus.SAVED) {
			save.setEnabled(false);
			save.setIcon(new ImageIcon("./image/save_edit.gif"));
		} else {
			save.setEnabled(true);
			save.setIcon(new ImageIcon("./image/to_save_edit.gif"));
		}
	}

	public void changeAllStatus() {
		boolean isAll = true;
		Iterator<String> iter = config.fileStatus.keySet().iterator();
		while (iter.hasNext()) {
			FileStatus value = config.fileStatus.get(iter.next());
			if (value == FileStatus.UNSAVED) {
				isAll = false;
				break;
			}
		}
		if (isAll) {
			saveAll.setEnabled(false);
			saveAll.setIcon(new ImageIcon("./image/saveall_edit.gif"));
		} else {
			saveAll.setEnabled(true);
			saveAll.setIcon(new ImageIcon("./image/to_saveall_edit.gif"));
		}
	}

	public void setSingleFileStatus(String name, FileStatus status) {
		FileStatus old = config.fileStatus.get(name);
		if (old != status) {
			if (status == FileStatus.SAVED) {
				save.setEnabled(false);
				save.setIcon(new ImageIcon("./image/save_edit.gif"));
				JClosableTabbedPane jtp = editor.getTabbedPane();
				String filePath = config.fileAndPath.get(name);
				int tabNumber = -1;
				for (int i = 0; i < jtp.getTabCount(); i++) {
					if (jtp.getTitleAt(i).equals(name))
						tabNumber = i;
				}
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new FileWriter(new File(filePath)));
					JScrollPane jp = (JScrollPane) jtp.getComponentAt(tabNumber);
					JTextPane textpane = (JTextPane) (jp.getViewport().getView());
					String context = textpane.getText();
					bw.write(context);
					bw.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				save.setEnabled(false);
				save.setIcon(new ImageIcon("./image/save_edit.gif"));
				config.fileStatus.put(name, FileStatus.SAVED);
				changeAllStatus();
			}
			if (status == FileStatus.UNSAVED) {
				save.setEnabled(true);
				save.setIcon(new ImageIcon("./image/to_save_edit.gif"));
				config.fileStatus.put(name, FileStatus.UNSAVED);
				setMultiFileStatus(FileStatus.UNSAVED);
			}
		}
	}

	public void setMultiFileStatus(FileStatus status) {
		if (status == FileStatus.SAVED) {
			JClosableTabbedPane jtp = editor.getTabbedPane();
			for (int i = 0; i < jtp.getTabCount(); i++)
				setSingleFileStatus(jtp.getTitleAt(i), FileStatus.SAVED);
			saveAll.setEnabled(false);
			saveAll.setIcon(new ImageIcon("./image/saveall_edit.gif"));

		}
		if (status == FileStatus.UNSAVED) {
			saveAll.setEnabled(true);
			saveAll.setIcon(new ImageIcon("./image/to_saveall_edit.gif"));
		}
	}

	void addTool() {
		JToolBar toolBar = new JToolBar();

		JButton newProject = new JButton(new ImageIcon("./image/newjprj_wiz.gif"));
		newProject.setToolTipText("New GranuleJ Project");
		newProject.addActionListener(new NewProjectAction(this, config));

		save = new JButton(new ImageIcon("./image/save_edit.gif"));
		save.setToolTipText("Save");
		save.setDisabledIcon(new ImageIcon("./image/save_edit.gif"));
		save.setEnabled(false);
		save.registerKeyboardAction(new SaveListener(), KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		save.registerKeyboardAction(new SaveListener(), KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

		saveAll = new JButton(new ImageIcon("./image/saveall_edit.gif"));
		saveAll.setToolTipText("Save All");
		saveAll.setDisabledIcon(new ImageIcon("./image/saveall_edit.gif"));
		saveAll.setEnabled(false);

		JButton build = new JButton(new ImageIcon("./image/build_exec.gif"));
		build.setToolTipText("Build");
		build.addActionListener(new BuildAction(config));

		JButton debug = new JButton(new ImageIcon("./image/debug_exc.gif"));
		debug.setToolTipText("Debug");

		JButton run = new JButton(new ImageIcon("./image/run_exc.gif"));
		run.setToolTipText("Run");
		run.addActionListener(new RunAction(this, config, console));

		JButton newPackage = new JButton(new ImageIcon("./image/newpack_wiz.gif"));
		newPackage.setToolTipText("New Package");
		newPackage.addActionListener(new NewPackageAction(this, config));

		JButton newClass = new JButton(new ImageIcon("./image/newclass_wiz.gif"));
		newClass.setToolTipText("New Class");
		newClass.addActionListener(new NewClassAction(this, config));

		JButton newInterface = new JButton(new ImageIcon("./image/newint_wiz.gif"));
		newInterface.setToolTipText("New Interface");
		newInterface.addActionListener(new NewInterfaceAction(this, config));

		JButton newGranule = new JButton(new ImageIcon("./image/granule.gif"));
		newGranule.setToolTipText("New Granule");
		newGranule.addActionListener(new NewGranuleAction(this, config));

		JButton newShadow = new JButton(new ImageIcon("./image/shadow.gif"));
		newShadow.setToolTipText("New Shadow Class");
		newShadow.addActionListener(new NewShadowAction(this, config));

		start = new JButton(new ImageIcon("./image/stop.gif"));
		start.addActionListener(new StopAction(this));
		start.setEnabled(false);
		start.setToolTipText("Program State");

		toolBar.add(newProject);
		toolBar.add(save);
		toolBar.add(saveAll);
		toolBar.addSeparator();
		toolBar.add(build);
		toolBar.add(debug);
		toolBar.add(run);
		toolBar.addSeparator();
		toolBar.add(newPackage);
		toolBar.add(newClass);
		toolBar.add(newInterface);
		toolBar.add(newGranule);
		toolBar.add(newShadow);
		toolBar.addSeparator();
		toolBar.add(start);
		getContentPane().add(toolBar, BorderLayout.NORTH);
	}

	public PackageTree getPackageTree() {
		return packageTree;
	}

	public Editor getEditor() {
		return editor;
	}

	public ProgramView getProgramView() {
		return program;
	}

	public void restart(boolean write) {
		// mf.processWindowEvent(new WindowEvent(this,
		// WindowEvent.WINDOW_CLOSING));
		if (write) {
			mf.writeProjectPath();
		}
		mf.dispose();
		if (write) {
			gc.setFlag(true);
			cc.setFlag(true);
			mf = new MainFrame();
		}
	}

	public static void main(String[] args) {
		mf = new MainFrame();
		mf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mf.disconnectWithCS();
				mf.clearTempFile();
			}
		});
	}

	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JClosableTabbedPane jtp = editor.getTabbedPane();
			setSingleFileStatus(jtp.getTitleAt(jtp.getSelectedIndex()), FileStatus.SAVED);
		}
	}

	// @Override
	public void dispose() {
		super.dispose();
		disconnectWithCS();
	}

	public void disconnectWithCS() {
		try {
			Socket sock = new Socket(ContextConstant.CONTEXT_SERVER_IP, ContextConstant.CONTEXT_SERVER_PORT);
			OutputStream socketOutput = sock.getOutputStream();
			PrintWriter socketWriter = new PrintWriter(socketOutput);
			socketWriter.println(ContextConstant.CONTEXT_CLIENT_DES);
			socketWriter.println(ContextConstant.CONTEXT_CLIENT_PORT);
			socketWriter.flush();
			socketWriter.close();
			sock.close();
			// 停止端口的监听

			gc.setFlag(false);
			cc.setFlag(false);

		} catch (IOException e1) {
			System.out.println("Context server is not started !");
			// e1.printStackTrace();
		}
	}

	public void clearTempFile() {
		File file = new File("./project.txt");
		if (!file.exists())
		 new TempFile().deleteFile();
	}
}