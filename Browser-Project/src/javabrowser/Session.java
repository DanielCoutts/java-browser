package javabrowser;
/**
 * @author Daniel Coutts
 * 04/04/2015.
 */

import java.net.URL;
import java.util.Stack;

public class Session {

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
     * @param current   The current page at initialisation (should be the homepage).
     */
    public Session(URL current) {
        this.current = current;
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
        }
    }

    /**
     * Move backwards in the session history.
     */
    public void backward() {
        if(!previous.isEmpty()) {
            next.push(current);
            current = previous.pop();
        }
    }

    /**
     * Navigate to a new page from the current position in the session history
     *
     * @param url   the new page to add to the session history.
     */
    public void navigate(URL url) {
        // Add the current page to the session history.
        previous.push(current);
        current = url;

        // Empty the 'next' stack.
        clearNext();
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
