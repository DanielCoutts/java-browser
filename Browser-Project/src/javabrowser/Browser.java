package javabrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Daniel Coutts
 */
public class Browser extends JFrame {

    private static final String BOOKMARKS_FILEPATH = "bookmarks.ser";
    private static final String HISTORY_FILEPATH = "history.ser";

    /**
     * A reference to the Browser's associated Toolbar class.
     */
    private Toolbar toolbar;

    /**
     * A reference to the Browser's associated Pane class.
     */
    private Pane pane;

    /**
     * A reference to the Browser's associated Session class.
     */
    private Session session;

    /**
     * A reference to the Browser's associated Bookmarks class.
     */
    private Bookmarks bookmarks;

    /**
     * A reference to the Browser's associated History class.
     */
    private History history;

    /**
     * Sets up the browser correctly and initialises objects.
     *
     * @param size The default dimensions of the window.
     */
    public Browser(Dimension size) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // The browser object is passed to these objects.
        // This allows them to communicate through this class.
        bookmarks = new Bookmarks(this);
        history = new History(this);
        toolbar = new Toolbar(this);
        pane = new Pane(this);
        session = new Session(this);

        // Attempt to load history and bookmarks data from files
        loadBookmarks(BOOKMARKS_FILEPATH);
        loadHistory(HISTORY_FILEPATH);

        // Initially navigate to the homepage
        session.navigate(Homepag.getHomepage());

        // A toolbar and display pane are added and positioned.
        add(new JScrollPane(pane), BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        // Set the default and minimum dimensions.
        setSize(size);
        setMinimumSize(new Dimension(1100, 400));

        addListeners();

        setVisible(true);
    }

    /**
     * @return The associated Toolbar object.
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * @return The associated Pane object.
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * @return The associated Session object.
     */
    public Session getSession() {
        return session;
    }

    public Bookmarks getBookmarks() {
        return bookmarks;
    }

    public History getHistory() {
        return history;
    }

    /**
     * Change the page displayed in the browser viewport.
     *
     * @param url The url of the web page to be displayed.
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
            JOptionPane.showMessageDialog(this, "That is not a valid web address.");
        }
    }

    /**
     * Create a url. Add http:// if it is missing.
     *
     * @param string the string to attempt to convert into a url.
     * @return the completed url object (or null if invalid).
     */
    public static URL makeUrl(String string) {
        if (!string.startsWith("http://")) {
            string = "http://" + string;
        }
        try {
            return new URL(string);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "That is not a valid URL.");
            return null;
        }
    }

    /**
     * Saves the Bookmarks object to file.
     *
     * @param file The file to write the object to.
     * @throws IOException when the file cannot be written.
     */
    public void saveBookmarks(String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(bookmarks);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Your bookmarks could not be saved.");
        }
    }

    /**
     * Saves the History object to file.
     *
     * @param file The file to write the object to.
     * @throws IOException when the file cannot be written.
     */
    public void saveHistory(String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(history);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Your history could not be saved.");
        }
    }

    /**
     * Loads the Bookmarks object from file.
     *
     * @param file The file to load from.
     */
    public void loadBookmarks(String file) {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            bookmarks = (Bookmarks) in.readObject();
        }
        // No catch bodies are required, as a notification is not needed when there
        // are no previous bookmarks.
        catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * Loads the History object from file.
     *
     * @param file The file to load from.
     */
    public void loadHistory(String file) {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            history = (History) in.readObject();
        }
        // No catch bodies are required, as a notification is not needed when there
        // is no previous history.
        catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * Private method used to add listeners. It is called in the constructor.
     */
    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveBookmarks(BOOKMARKS_FILEPATH);
                saveHistory(HISTORY_FILEPATH);
            }
        });
    }

    /**
     * A main method that creates a browser object.
     */
    public static void main(String[] args) {
        Browser window = new Browser(new Dimension(1000, 1000));
    }
}