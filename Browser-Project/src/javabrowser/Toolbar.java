package javabrowser;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/**
 * @author Daniel Coutts
 */
public class Toolbar extends JPanel {

    /**
     * Reference to the Toolbar object's containing Browser object.
     */
    private Browser browser;

    private JTextField addressBar = new JTextField(30);
    JButton back = new JButton("Back");
    JButton forward = new JButton("Forward");
    JButton home = new JButton("Home");
    JButton reload = new JButton("Reload");

    JButton bookmarks = new JButton("Bookmarks");
    JButton history = new JButton("History");

    /**
     * Create a Toolbar object and add browser controls.
     *
     * @param browser The Browser object associated with this Pane object.
     */
    public Toolbar(Browser browser) {
        super();
        this.browser = browser;
        setLayout(new FlowLayout());
        add(back);
        add(forward);
        add(home);
        add(reload);
        add(addressBar);
        add(bookmarks);
        add(history);

        // Sets up all listeners for the Pane object.
        addListeners();
    }

    /**
     * @return returns the text in the address bar.
     */
    public JTextField getAddressBar() {
        return addressBar;
    }

    /**
     * Private method used to add listeners. It is called in the constructor.
     */
    private void addListeners() {

        // Home button action listener
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.setPage(Homepage.getHomepage());
                browser.getSession().navigate(Homepage.getHomepage());
            }
        });

        // Address bar action listener
        // (executes on return)
        addressBar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Create a URL from the current address bar text.
                URL url = Browser.makeUrl(addressBar.getText());
                // Navigate to this URL.
                browser.getSession().navigate(url);
            }
        });

        // Back button action listener
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.getSession().backward();
            }
        });

        // Forward button action listener
        forward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.getSession().forward();
            }
        });

        // Reload button action listener
        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.getPane().reload();
            }
        });
        bookmarks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        history.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.getHistory().setVisible(true);
            }
        });
    }
}