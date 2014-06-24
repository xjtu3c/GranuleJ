package granulej.bean;

import granulej.gui.MainFrame;
import granulej.util.xmlUtil;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import config.GUIConfig;



public class PackageLibrary extends JPanel implements ActionListener {
	
	   private static String ADD_COM = "add";
	   private static String REMOVE_COM = "remove";
	   private static String OK_COM="Ok";
	     
	   private LibraryTree treePanel;
	   private GUIConfig config;
	   private MainFrame main;
	   private JFrame iframe;
	   

	
	  public PackageLibrary(GUIConfig config,MainFrame main, JFrame iframe){  
		    super(new BorderLayout());	
		    this.config=config;
		    this.iframe=iframe;
		    this.main=main;
	        treePanel = new LibraryTree();
	        populateTree(treePanel);
	 
	        JButton addButton = new JButton("Add External JARs or Class Folder");        
	        addButton.setActionCommand(ADD_COM);
	        addButton.addActionListener(this);
	         
	        JButton removeButton = new JButton("Remove");
	        removeButton.setActionCommand(REMOVE_COM);
	        removeButton.addActionListener(this);
	        
	        JButton okButton = new JButton("Ok");
		    okButton.setActionCommand(OK_COM);
		    okButton.addActionListener(this);

	        treePanel.setPreferredSize(new Dimension(300, 150));
	        add(treePanel, BorderLayout.CENTER);   
	     
	        JPanel panel = new JPanel(new GridLayout(0,3));
	        panel.add(addButton);
	        panel.add(removeButton);
	        panel.add(okButton);
	        add(panel, BorderLayout.SOUTH); 
	        
	    }
	   public void populateTree(LibraryTree treePanel) {
		    if(config.getClasspath()!=null &&!"".equals(config.getClasspath())){
	        String[] path=config.getClasspath().split(";");
	        for(int i=0;i<path.length;i++){
	        	treePanel.addObject(null,path[i]);	        	
	        }
		    }
	    }
	  
	   
	    public void actionPerformed(ActionEvent e) {
	        String command = e.getActionCommand();
	         
	        if (ADD_COM.equals(command)) {
	        	String node_name="";
	        	JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("JAR or Class Folder Selection ");
				chooser.setAcceptAllFileFilterUsed(true);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					node_name=chooser.getSelectedFile().toString();
				}
				if(!"".equals(node_name)){
                //更新到classpath
		        updateClassPathNode(config.getProjectPath(),node_name);
		        config.setClasspath(config.getClasspath()+";"+node_name);
				node_name=splitName(node_name);
	            treePanel.addObject(null,node_name);	        
				}
				else {
			    return ;
				}
				
	        } else if (REMOVE_COM.equals(command)) {	        	
	            treePanel.removeCurrentNode();
	        } else if(OK_COM.equals(command))
	        {
	        	iframe.dispose();
				main.setEnabled(true);	
	        }
	    }  
	   
	    public String splitName(String node)
	    {
	       File name=new File(node);
	       String path=name.getPath();
	       return node=name.getName()+"-"+ path.substring(0, path.lastIndexOf(name.getName())-1);    	
	    }
	    public String compositeName(String node)
	    {
	    	String node_name="";
	    	if(node!=null &&!"".equals(node)){
	    	 if(node.contains("-")){
	    	 String[] ns=node.split("-");
	    	 node_name=ns[1]+File.separator+ns[0];
	    	 }
             }
	    	return node_name;
	    }
	    
	    
	    public void updateClassPathNode(String path,String node)
	    {
	    	String filename=path+File.separator+".classpath";
	    	Document doc=xmlUtil.load(filename); 
	    	Element rootElement = doc.getDocumentElement();
	    	Element child = doc.createElement("classpathentry");
			child.setAttribute("path", node);
			rootElement.appendChild(child);
            xmlUtil.saveXml(doc, filename);
	    } 
	    public void deleteClassPathNode(String path, String node)
	    {
            String filename=path+File.separator+".classpath";
	    	Document doc=xmlUtil.load(filename); 
	    	Element rootElement = doc.getDocumentElement();
	    	NodeList nodes = rootElement.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node nd = nodes.item(i);
				if (nd instanceof Element) {
					Element child = (Element) nd;
					String attribute = child.getAttribute("path");
					if (attribute.equals(node)) {
					  child.getParentNode().removeChild(child);
					}
				}
			}

	    }
	   
	    class LibraryTree extends JPanel {
		  protected DefaultMutableTreeNode rootNode;
		  protected DefaultTreeModel treeModel;
		  protected JTree tree;
		  private Toolkit toolkit = Toolkit.getDefaultToolkit();

		  public LibraryTree() {
			super(new GridLayout(1, 0));
		    rootNode = new DefaultMutableTreeNode("Jars and class folders");
		    treeModel = new DefaultTreeModel(rootNode);

		    tree = new JTree(treeModel);
		    tree.setEditable(false);
		    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		    tree.setShowsRootHandles(true);
		    JPanel jp=new JPanel();
		    JScrollPane scrollPane = new JScrollPane(tree);
		    add(scrollPane);
		  }
		  
		  public void clear() {
		    rootNode.removeAllChildren();
		    treeModel.reload();
		  }

		  public void removeCurrentNode() {
		    TreePath currentSelection = tree.getSelectionPath();
		    if (currentSelection != null) {
		      DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
		      String curname=currentNode.getUserObject().toString();
		      curname=compositeName(curname);
		      deleteClassPathNode(config.getProjectPath(), curname);
		      config.setClasspath(config.getClasspath().replace("",";"+curname));
		      MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
		      if (parent != null) {
		        treeModel.removeNodeFromParent(currentNode);
		        return;
		      }
		    }
		    toolkit.beep();
		  }
		  public DefaultMutableTreeNode addObject(Object child) {
		    DefaultMutableTreeNode parentNode = null;
		    TreePath parentPath = tree.getSelectionPath();

		    if (parentPath == null) {
		      parentNode = rootNode;
		    } else {
		      parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		    }

		    return addObject(parentNode, child, true);
		  }

		  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
		      Object child) {
		    return addObject(parent, child, false);
		  }

		  public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
		      Object child, boolean shouldBeVisible) {
		    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		    if (parent == null) {
		      parent = rootNode;
		    }

		    // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
		    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		    // Make sure the user can see the lovely new node.
		    if (shouldBeVisible) {
		      tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		    }
		    return childNode;
		  }

		  class MyTreeModelListener implements TreeModelListener {
		    public void treeNodesChanged(TreeModelEvent e) {
		      DefaultMutableTreeNode node;
		      node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
		      int index = e.getChildIndices()[0];
		      node = (DefaultMutableTreeNode) (node.getChildAt(index));
		    }

		    public void treeNodesInserted(TreeModelEvent e) {
		    }

		    public void treeNodesRemoved(TreeModelEvent e) {
		    }

		    public void treeStructureChanged(TreeModelEvent e) {
		    }
		  }
		}
	  
	  class MyTreeModelListener implements TreeModelListener {
		    public void treeNodesChanged(TreeModelEvent e) {
		      DefaultMutableTreeNode node;
		      node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

		      int index = e.getChildIndices()[0];
		      node = (DefaultMutableTreeNode) (node.getChildAt(index));

		    }

		    public void treeNodesInserted(TreeModelEvent e) {
		    }

		    public void treeNodesRemoved(TreeModelEvent e) {
		    }

		    public void treeStructureChanged(TreeModelEvent e) {
		    }
		  }
	  
		}