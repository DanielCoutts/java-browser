import java.awt.*;

import javax.swing.*;


public class Toolbar extends JPanel {
	
	// constructor adds button and address bar
	public Toolbar() {
		super();
		setLayout(new FlowLayout());
		add(new JButton("Back"));
		add(new JButton("Forward"));
		add(new JButton("Home"));
		add(new JTextField(27));
		//hist&favs now
		
	}
}
