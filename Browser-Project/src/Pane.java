/**
 * @author  Daniel Coutts
 */

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

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
        try {
            // Store the current page in a variable.
            String page = getPage().toString();

            // Refresh the page.
            getDocument().putProperty(Document.StreamDescriptionProperty, null);
            browser.setPage(page);
        }
        catch (IOException ioe) {
            // If the url is not valid, the user is notified with a popup.
            JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
        }
    }

    /**
     * Private method used to add listeners. It is called in the constructor.
     */
    private void addListeners() {
        addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent event) {
                // If a hyperlink is clicked, navigate to that page.
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        browser.setPage(event.getURL().toString());
                        browser.getSession().navigate(event.getURL().toString());
                    }
                    catch (IOException ioe) {
                        // If the url is not valid, the user is notified with a popup.
                        JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                    }
                }
            }
        });
    }
}