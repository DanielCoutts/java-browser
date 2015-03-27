import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Pane extends JPanel {
	
	private JEditorPane viewport;
	private Toolbar matchingToolbar;
	
	public Pane(Toolbar matchingToolbar) {
		super();
		this.matchingToolbar = matchingToolbar;
		viewport = new JEditorPane();
		viewport.setEditable(false);
		viewport.setContentType("text/html");
		
		add(new JScrollPane(viewport));
		setLayout(new GridLayout());
		createListeners();
	}
	
	public Pane(Toolbar matchingToolbar, String url) {
		this(matchingToolbar);
		setPage(url);
	}
	
	public void setPage(String url) {
		try {
			viewport.setPage(url);
			matchingToolbar.updateAddressBar(url);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(matchingToolbar, "That is not a valid web address.");
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
