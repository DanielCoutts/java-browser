import javax.swing.*;
import java.awt.*;

public class Browser extends JFrame {
	
	private Toolbar toolbar;
	private Pane pane;
	
	public Browser(Dimension size) {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		toolbar = new Toolbar(pane);
		pane = new Pane(toolbar, Bookmarks.getHomepage());
		
		add(pane, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		
		setSize(size);
		setMinimumSize(new Dimension(800,400));
		setVisible(true);
	}
	
	// MAIN METHOD FOR TESTING
	public static void main(String[] args) {
		Browser window = new Browser(new Dimension(1000, 1000));
	}
}
