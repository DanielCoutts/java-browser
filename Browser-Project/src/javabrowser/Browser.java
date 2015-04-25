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

        try {
            // The browser object is passes to the Toolbar and Pane objects.
            // This allows the toolbar to access the display pane and vice versa.
            toolbar = new Toolbar(this);
            pane = new Pane(this);
            setPage(Bookmarks.getHomepage());
            session = new Session(Bookmarks.getHomepage());
        }
        catch (MalformedURLException mue) {
            // If the url is not valid, the user is notified with a popup.
            JOptionPane.showMessageDialog(this, "That is not a valid web address.");
        }

        //a toolbar and display pane are added and positioned.
        add(new JScrollPane(pane), BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        setSize(size);
        setMinimumSize(new Dimension(800, 400));
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
    public void setPage(URL url) throws MalformedURLException {
        // Sets the page
        try {
            pane.setPage(url);
            toolbar.updateAddressBar(url);
        }
        catch (IOException ioe) {
            // If the url is not valid, the user is notified with a popup.
            JOptionPane.showMessageDialog(this , "That is not a valid web address.");
        }
    }


    public static void main(String[] args) {
        Browser window = new Browser(new Dimension(1000, 1000));
    }
}