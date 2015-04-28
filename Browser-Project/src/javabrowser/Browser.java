package javabrowser;
/**
 * @author  Daniel Coutts
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser extends JFrame {

    /**
     * A reference to the Browser's associated Toolbar.
     */
    private Toolbar toolbar;

    /**
     * A reference to the Browser's associated Pane.
     */
    private Pane pane;

    /**
     * Session object.
     */
    private Session session;

    /**
     * A constructor that sets up the browser correctly.
     *
     * @param size  The default dimensions of the window.
     */
    public Browser(Dimension size) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // The browser object is passes to the Toolbar, Pane, and Session objects.
        // This allows them to communicate through this class.
        toolbar = new Toolbar(this);
        pane = new Pane(this);
        session = new Session(this);

        // Initially navigate to the homepage
        session.navigate(Bookmarks.getHomepage());

        // A toolbar and display pane are added and positioned.
        add(new JScrollPane(pane), BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        // Set the default and minimum dimensions.
        setSize(size);
        setMinimumSize(new Dimension(1000, 400));
        setVisible(true);
    }

    /**
     * @return  The associated Toolbar object.
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * @return  The associated Pane object.
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * @return  The associated Session object.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Change the page displayed in the browser viewport.
     *
     * @param url   The url of the web page to be displayed.
     */
    public void setPage(URL url) {
        // Sets the page
        try {
            // Set the pane to display the specified URL.
            pane.setPage(url);

            // Set the address bar to show the specified URL.
            toolbar.getAddressBar().setText(url.toString());
        }
        // The setPage method throws an IO exception. This needs to be caught but shouldn't
        // be a problem under normal usage.
        catch (IOException ioe) {
            // If the url is not valid, the user is notified with a popup.
            JOptionPane.showMessageDialog(this , "That is not a valid web address.");
        }
    }

    /**
     * Create a url. Add http:// if it is missing.
     *
     * @param string  the string to attempt to convert into a url.
     * @return  the completed url object (or null if invalid).
     */
    public static URL makeUrl(String string) {
        if(!string.startsWith("http://")) {
            string = "http://" + string;
        }
        try {
            return new URL(string);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "That is not a valid URL.");
            return null;
        }
    }

    public static void main(String[] args) {
        Browser window = new Browser(new Dimension(1000, 1000));
    }
}