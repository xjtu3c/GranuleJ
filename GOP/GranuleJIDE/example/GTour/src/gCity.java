import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


granule gCity(City) {
	external String city_c;
	{
              return city_c.equals("Xian");
	}	
}

class City within gCity{
		private String introduction="";
		public String getIntroduction(){
			return introduction;
		}
		public void setIntroduction(String s){
			introduction=s;
		}
		public void createUI(){
			setName(city_c);
			introduction="Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! "+
			"Welcome to "+getName()+" ! "+"Welcome to "+getName()+" ! ";
			getFrame().setTitle("Welcome to Xian");
			JPanel main_panel=new JPanel();
			main_panel.setLayout(new BorderLayout());
			
			JTextArea text_area=new JTextArea(20,19);
			text_area.setEditable(false);
			text_area.setLineWrap(true);
			text_area.setText(getIntroduction());
			JScrollPane scroll_pane =new JScrollPane(text_area);
			
			getShowPanel().removeAll();
			getShowPanel().add(scroll_pane);
			
			JPanel function_panel=new JPanel();
			function_panel.setLayout(new GridLayout(4,2));
			String[] button_menu={"introduction","voice","video","pictures","history","recommendation","snack","query"};
			CityActionListener city_action_listener=new CityActionListener(getShowPanel(),getServerInfo(),this);
			for(String menu:button_menu){
				JButton button=new JButton(menu);
				button.addActionListener(city_action_listener);
				function_panel.add(button);
			}
			
			main_panel.add(getShowPanel(),BorderLayout.CENTER);
			main_panel.add(function_panel,BorderLayout.SOUTH);
			
			getFrame().add(main_panel);
			getFrame().validate();
			
		}
		public void enter(){
			
		}
		public void exit(){
			
		}
	}
