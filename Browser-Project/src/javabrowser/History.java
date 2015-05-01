package javabrowser;

import java.net.URL;
import java.util.Date;

/**
 * @author Daniel Coutts
 */
public class History extends UrlMenu {

    /**
     * Creates a menu of URLs with buttons for navigating to and removing items.
     *
     * @param browser the associated Browser object.
     */
    public History(Browser browser) {
        super(browser);
        // Sets the window title to "History".
        setTitle("History");
    }

    /**
     * Add a URL to the history.
     *
     * @param title The title of the list item.
     * @param url   The associated url.
     */
    public void addListItem(String title, URL url) {
        Date now = new Date(System.currentTimeMillis());
        title = now.toString() + " - " + title;
        super.addListItem(title, url);
    }
}
