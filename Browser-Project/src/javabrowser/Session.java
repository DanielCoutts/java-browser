package javabrowser;
/**
 * @author Daniel Coutts
 */

import java.net.URL;
import java.util.Stack;

public class Session {

    /**
     * Reference to the Session object's containing Browser object.
     */
    Browser browser;

    /**
     * Stack to store the session history before the current page.
     */
    private Stack<URL> previous;

    /**
     * Stack to store the session history after the current page.
     */
    private Stack<URL> next;

    /**
     * The current page.
     */
    private URL current;

    /**
     * Creates a Session object to track the pages or the current Pane
     *
     * @param browser   The Browser object associated with this Pane object.
     */
    public Session(Browser browser) {
        this.browser = browser;

        previous = new Stack<URL>();
        next = new Stack<URL>();
    }

    /**
     * Move forward in the session history.
     */
    public void forward() {
        if(!next.isEmpty()) {
            previous.push(current);
            current = next.pop();
            browser.setPage(current);
        }
    }

    /**
     * Move backwards in the session history.
     */
    public void backward() {
        if(!previous.isEmpty()) {
            next.push(current);
            current = previous.pop();
            browser.setPage(current);
        }
    }

    /**
     * Navigate to a new page from the current position in the session history
     *
     * @param url   the new page to navigate to and add to the session history.
     */
    public void navigate(URL url) {
        if (current != null) {
            // Add the current page to the session history.
            previous.push(current);

            // Empty the 'next' stack.
            clearNext();
        }

        // Set 'current' to the specified url.
        current = url;

        // Display the webpage.
        browser.setPage(current);
    }

    /**
     * @return  The current page.
     */
    public URL getCurrent() {
        return current;
    }

    /**
     * Private method that empties the 'next' stack.
     */
    private void clearNext() {
        next = new Stack<URL>();
    }

}
