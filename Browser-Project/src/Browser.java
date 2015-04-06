/**
 * @author  Daniel Coutts
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
            pane = new Pane(this, Bookmarks.getHomepage());
            session = new Session(/*this, */Bookmarks.getHomepage());
        }
        catch (IOException ioe) {
            // If the url is not valid, the user is notified with a popup.
            JOptionPane.showMessageDialog(this, "That is not a valid web address.");
        }

        //a toolbar and display pane are added and positioned.
        add(pane, BorderLayout.CENTER);
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

    public static void main(String[] args) {
        Browser window = new Browser(new Dimension(1000, 1000));
    }
}