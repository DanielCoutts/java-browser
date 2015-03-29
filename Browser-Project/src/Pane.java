import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Pane extends JPanel {
	
	/**
	 * Declares a JEditorPane to display webpages.
	 */
	private JEditorPane viewport;
	
	/**
	 * Reference to the Pane object's containing Browser object.
	 */
	private Browser browser;
	
	/**
	 * Create an empty content Pane.
	 * 
	 * @param browser	The Browser object associated with this Pane object.
	 */
	public Pane(Browser browser) {
		
		// Call the JPanel constructor.
		super();
		
		this.browser = browser;
		
		// Set up the viewport to display HTML.
		viewport = new JEditorPane();
		viewport.setEditable(false);
		viewport.setContentType("text/html");
		
		// Add the viewport to a JScrollPane, then add to this Pane.
		add(new JScrollPane(viewport));
		
		// Set an appropriate Layout for the content Pane.
		setLayout(new GridLayout());
		
		// Sets up all listeners for the Pane object.
		createListeners();
	}
	
	/**
	 * Create a content Pane containing a specified URL.
	 * 
	 * @param browser	The Browser object associated with this Pane object.
	 * @param url		The URL of the webpage to be displayed
	 */
	public Pane(Browser browser, String url) {
		this(browser);
		setPage(url);
	}
	
	public void setPage(String url) {
		try {
			viewport.setPage(url);
			browser.getToolbar().updateAddressBar(url);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
		}
		
	}
	
	private void createListeners() {
		viewport.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					setPage(event.getURL().toString());
				}
			}
		});
	}	
}
