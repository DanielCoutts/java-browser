import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.LinkedList;

import javax.swing.*;


public class Toolbar extends JPanel {
	
	private Browser browser;
	
	private List<String> session = new LinkedList<String>();
	
	private JTextField addressBar = new JTextField(30);
	JButton back = new JButton("Back");
	JButton forward = new JButton("Forward");
	JButton home = new JButton("Home");
	JButton reload = new JButton("Reload");
	
	// constructor adds button and address bar
	public Toolbar(Browser browser) {
		super();
		this.browser = browser;
		setLayout(new FlowLayout());
		add(back);
		add(forward);
		add(home);
		add(reload);
		add(addressBar);
		//hist&favs now
		
		createListeners();
	}

	public JTextField getAddressBar() {
		return addressBar;
	}

	public void updateAddressBar(String text) {
		addressBar.setText(text);
	}

	private void createListeners() {
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browser.getPane().setPage(Bookmarks.getHomepage());
			}
		});
		
		addressBar.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) { }
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					browser.getPane().setPage(addressBar.getText());
				}
			}
		});
	}
	
	
}
