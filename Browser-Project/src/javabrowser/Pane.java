package javabrowser;

import java.net.URL;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

/**
 * @author Daniel Coutts
 */
public class Pane extends JEditorPane {

    /**
     * Reference to the Pane object's containing Browser object.
     */
    private Browser browser;

    /**
     * Create an empty content Pane.
     *
     * @param browser The Browser object associated with this Pane object.
     */
    public Pane(Browser browser) {

        // Call the JEditorPane constructor.
        super();

        this.browser = browser;

        // Set up the viewport to display HTML.
        setEditable(false);
        setContentType("text/html");

        // Sets up all listeners for the Pane object.
        addListeners();
    }

    /**
     * Reloads the web page in the pane.
     */
    public void reload() {

        // Store the current page in a variable.
        URL page = getPage();

        // Refresh the page.
        getDocument().putProperty(Document.StreamDescriptionProperty, null);
        browser.setPage(page);
    }

    /**
     * Private method used to add listeners. It is called in the constructor.
     */
    private void addListeners() {
        addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent event) {
                // If a hyperlink is clicked, navigate to that page.
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    browser.getSession().navigate(event.getURL());
                }
            }
        });
    }
}