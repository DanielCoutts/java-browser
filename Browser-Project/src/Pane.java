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
    public Pane(Browser browser, String url) {
        this(browser);
        setPage(url);
    }

    /**
     * Change the page displayed in the browser viewport.
     *
     * @param url   The url of the web page to be displayed.
     */
    public void setPage(String url) {
        try {
            // Sets the page
            viewport.setPage(url);
            browser.getToolbar().updateAddressBar(url);
        } catch (IOException e) {
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
                    setPage(event.getURL().toString());
                }
            }
        });
    }
}