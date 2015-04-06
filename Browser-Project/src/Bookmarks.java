/**
 * @author  Daniel Coutts
 */

import java.io.*;
import java.util.*;

public class Bookmarks {

    /**
     * Retrieves the homepage from the homepage.cfg file and returns it.
     *
     * @return  the browser's homepage.
     */
    public static String getHomepage() {
        try {
            FileReader home = new FileReader("homepage.cfg");
            Scanner source = new Scanner(home);
            return source.nextLine();
        }
        catch (FileNotFoundException fnfe) {
            return "http://nufc.co.uk/";
        }
    }

    /**
     * Sets the browser homepage.
     *
     * @param url   url to set the homepage to.
     */
    public static void setHomepage(String url) {
        PrintWriter home = null;
        try {
            home = new PrintWriter("homepage.cfg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        home.println(url);
    }
}