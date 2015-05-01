package javabrowser;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @author Daniel Coutts
 */
public class Homepage {

    /**
     * Retrieves the homepage from the homepage.cfg file and returns it.
     *
     * @return the browser's homepage (or null if the file cannot be found/created).
     */
    public static URL getHomepage() {
        try {
            // creating a FileReader and putting it inside a scanner
            FileReader home = new FileReader("homepage.cfg");
            Scanner source = new Scanner(home);

            URL url;
            // If the config file contains a string, attempt to convert it into a url.
            if (source.hasNextLine()) {
                url = Browser.makeUrl(source.nextLine());

                // If the url conversion was successful, return the url.
                if (url != null) {
                    return url;
                }
            }
            source.close();

            // The homepage url is missing or invalid, so call the ask the user to set
            // a homepage and try again.
            setHomepage();
            return getHomepage();
        } catch (FileNotFoundException fnfe) {
//            missingFile();
            return null;
        }
    }

    /**
     * Sets the browser homepage.
     *
     * @param url   url to set the homepage to.
     */
    public static void setHomepage(URL url) {
        try {
            // Write whatever is passed to this method to the homepage config file.
            PrintWriter home = new PrintWriter("homepage.cfg");
            home.println(url);
            home.close();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "A config file is missing and cannot be created.");
        }
    }

    /**
     * Asks the user to set the homepage using an input dialog.
     */
    public static void setHomepage() {
        URL url = null;

        // ask the user for a url until a valid one is given.
        while (url == null) {
            String homeUrl = JOptionPane.showInputDialog("Please set a homepage:");
            url = Browser.makeUrl(homeUrl);
        }

        // Set the homepage to the user's choice.
        setHomepage(url);
    }
}