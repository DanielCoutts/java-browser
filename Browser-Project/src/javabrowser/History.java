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
     * @param url The associated url.
     */
    public void addHistoryItem(URL url) {
        Date now = new Date(System.currentTimeMillis());
        String title = now.toString() + " - " + url.toString();
        addListItem(title, url);
    }
}
