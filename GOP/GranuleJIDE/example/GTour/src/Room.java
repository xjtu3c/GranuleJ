import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Room extends Site {
	private String introduction = "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! "
			+ "Welcome to " + getName() + " ! " + "Welcome to "
			+ getName() + " ! " + "Welcome to " + getName() + " ! ";

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String s) {
		introduction = s;
	}

	public void createUI() {
		getFrame().setTitle("Welcome to " + getName());
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout());

		JTextArea text_area = new JTextArea(20, 19);
		text_area.setEditable(false);
		text_area.setLineWrap(true);
		text_area.setText(getIntroduction());
		JScrollPane scroll_pane = new JScrollPane(text_area);

		getShowPanel().removeAll();
		getShowPanel().add(scroll_pane);

		JPanel function_panel = new JPanel();
		function_panel.setLayout(new GridLayout(4, 2));
		String[] button_menu = { "introduction", "voice", "video", "pictures",
				"history", "recommendation", "snack", "query" };
		RoomActionListener room_action_listener = new RoomActionListener(
				getShowPanel(), getServerInfo(), this);
		for (String menu : button_menu) {
			JButton button = new JButton(menu);
			button.addActionListener(room_action_listener);
			function_panel.add(button);
		}

		main_panel.add(getShowPanel(), BorderLayout.CENTER);
		main_panel.add(function_panel, BorderLayout.SOUTH);

		getFrame().add(main_panel);
		getFrame().validate();

	}

	public void enter() {

	}

	public void exit() {

	}
}
