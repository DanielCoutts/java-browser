import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class Pane extends JPanel {
	
	private JEditorPane viewport;
	
	public Pane() {
		super();
		viewport = new JEditorPane();
		viewport.setEditable(false);
		viewport.setContentType("text/html");
		
		add(new JScrollPane(viewport));
	}
	
	public Pane(String url) {
		this();
		setPage(url);
	}
	
	public void setPage(String url) {
		try {
			viewport.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
