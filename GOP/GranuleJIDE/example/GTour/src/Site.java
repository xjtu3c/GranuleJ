import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Site {
	private String name;
	private ServerInfo server;
	private JFrame frame;
	private JPanel show_panel=new JPanel();
	private ArrayList<Site> sites=new ArrayList<Site>();
	
	public ArrayList<Site> getSites(){
		return sites;
	}
	public void addSite(Site site){
		sites.add(site);
	}
	public ServerInfo getServerInfo(){
		return server;
	}
	public void setServerInfo(ServerInfo serve){
		server=serve;
	}
	public String getName(){
		return name==null?"":name;
	}
	public void setName(String nam){
		name=nam;
	}
	public JPanel getShowPanel(){
		return show_panel;
	}
	public void setShowPanel(JPanel p){
		show_panel=p;
	}
	public JFrame getFrame(){
		return frame;
	}
	public void setFrame(JFrame fram){
		frame=fram;
	}
	public void enter(){}
	public void createUI(){}
	public void exit(){}
}
