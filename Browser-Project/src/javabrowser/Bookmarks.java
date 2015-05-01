package javabrowser;

import javax.swing.*;
import java.net.URL;

/**
 * @author Daniel Coutts
 */
public class Bookmarks extends UrlMenu {

    public Bookmarks(Browser browser) {
        super(browser);
        setTitle("Bookmarks");
    }

    public void addBookmark(URL url) {
        String title = JOptionPane.showInputDialog("Please name this bookmark");
        title += " - " + url.toString();
        addListItem(title, url);
    }
}
