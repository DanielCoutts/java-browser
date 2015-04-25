package javabrowser;
/**
 * @author Daniel Coutts
 */

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;


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

    // Constructor that adds button and and address bar.
    public Toolbar(Browser browser) {
        super();
        this.browser = browser;
        setLayout(new FlowLayout());
        add(back);
        add(forward);
        add(home);
        add(reload);
        add(addressBar);
        //hist&favs now

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
     * Method to set the address bar text. Useful for updating the address
     * bar when clicking on hyperlinks.
     *
     * @param url String to set the addressBar text to.
     */
    public void updateAddressBar(URL url) {
        addressBar.setText(url.toString());
    }

    /**
     * Private method used to add listeners. It is called in the constructor.
     */
    private void addListeners() {

        // Home button action listener
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    browser.setPage(Bookmarks.getHomepage());
                    browser.getSession().navigate(Bookmarks.getHomepage());
                }
                catch (MalformedURLException mue) {
                    // If the url is not valid, the user is notified with a popup.
                    JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                }
            }
        });

        // Address bar action listener
        addressBar.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) { }

            public void keyReleased(KeyEvent e) { }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    try {
                        URL url;

                        // This block attempts to work out if the user input can be read as a valid url.
                        try {
                            // The current url in the address bar is placed into a variable.
                            url = new URL(addressBar.getText());
                        } catch (MalformedURLException mue) {
                            // Try adding http:// to the start of the url.
                            url = new URL("http://" + addressBar.getText());
                        }

                        // The display pane displays the url in the address bar
                        browser.setPage(url);
                        // The address bar is updated with the full page url.
                        updateAddressBar(url);
                        // Add this url to the session history at the current position.
                        browser.getSession().navigate(url);
                    }
                    catch (MalformedURLException mue) {
                        // If the url is not valid, the user is notified with a popup.
                        JOptionPane.showMessageDialog(browser , "That is not a valid web address.");
                    }
                }
            }
        });

        // Back button action listener
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    browser.getSession().backward();
                    URL current = browser.getSession().getCurrent();
                    browser.setPage(current);
                } catch (MalformedURLException mue) {
                    // If the url is not valid, the user is notified with a popup.
                    JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                }
            }
        });

        // Forward button action listener
        forward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    browser.getSession().forward();
                    URL current = browser.getSession().getCurrent();
                    browser.setPage(current);
                }
                catch (MalformedURLException mue) {
                    // If the url is not valid, the user is notified with a popup.
                    JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                }
            }
        });

        // Reload button action listener
        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.getPane().reload();
            }
        });
    }
}