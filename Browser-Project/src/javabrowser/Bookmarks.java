package javabrowser;
/**
 * @author  Daniel Coutts
 */

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Bookmarks {

    /**
     * Retrieves the homepage from the homepage.cfg file and returns it.
     *
     * @return  the browser's homepage.
     */
    public static URL getHomepage() throws MalformedURLException {
        try {
            FileReader home = new FileReader("homepage.cfg");
            Scanner source = new Scanner(home);
            return new URL(source.nextLine());
        }
        catch (NoSuchElementException nsee) {
            String homeUrl = JOptionPane.showInputDialog("Please set a homepage:");
            setHomepage(new URL(homeUrl));
            return getHomepage();
        }
        catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null , "A config file is missing and cannot be created.");
            throw new MalformedURLException("The homepage cannot be set.");
        }
    }

    /**
     * Sets the browser homepage.
     *
     * @param url   url to set the homepage to.
     */
    public static void setHomepage(URL url) {
        try {
            PrintWriter home = new PrintWriter("homepage.cfg");
            home.println(url);
        }
        catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null , "A config file is missing and cannot be created.");
        }
    }
}