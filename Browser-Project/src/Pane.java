/**
 * @author  Daniel Coutts
 */

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

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
     * @param browser The Browser object associated with this Pane object.
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
        addListeners();
    }

    /**
     * Create a content Pane containing a specified web page.
     *
     * @param browser The Browser object associated with this Pane object.
     * @param url     The URL of the web page to be displayed.
     */
    public Pane(Browser browser, String url) throws IOException {
        this(browser);
        setPage(url);
    }

    /**
     * Change the page displayed in the browser viewport.
     *
     * @param url   The url of the web page to be displayed.
     */
    public void setPage(String url) throws IOException {
        // Sets the page
        viewport.setPage(url);
        browser.getToolbar().updateAddressBar(url);
    }

    /**
     * Reloads the web page in the pane.
     */
    public void reload() {
        try {
            // Store the current page in a variable.
            String page = viewport.getPage().toString();

            // Refresh the page.
            viewport.getDocument().putProperty(Document.StreamDescriptionProperty, null);
            setPage(page);
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
        viewport.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent event) {
                // If a hyperlink is clicked, navigate to that page.
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        setPage(event.getURL().toString());
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