/**
 * @author Daniel Coutts
 */

import com.sun.codemodel.internal.JOp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
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
     * @param text String to set the addressBar text to.
     */
    public void updateAddressBar(String text) {
        addressBar.setText(text);
    }

    /**
     * Private method used to add listeners. It is called in the constructor.
     */
    private void addListeners() {
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    browser.getPane().setPage(Bookmarks.getHomepage());
                    browser.getSession().navigate(Bookmarks.getHomepage());
                }
                catch (IOException ioe) {
                    // If the url is not valid, the user is notified with a popup.
                    JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                }
            }
        });

        addressBar.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    // The current url in the address bar is placed into a variable.
                    String url = addressBar.getText();
                    try {
                        // The display pane displays the url in the address bar
                        browser.getPane().setPage(url);

                        browser.getSession().navigate(url);
                    } catch (IOException ioe) {
                        // If the url is not valid, the user is notified with a popup.
                        JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                    }
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    browser.getSession().backward();
                    String current = browser.getSession().getCurrent();
                    browser.getPane().setPage(current);
                } catch (IOException ioe) {
                    // If the url is not valid, the user is notified with a popup.
                    JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                }
            }
        });

        forward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    browser.getSession().forward();
                    String current = browser.getSession().getCurrent();
                    browser.getPane().setPage(current);
                }
                catch (IOException ioe) {
                    // If the url is not valid, the user is notified with a popup.
                    JOptionPane.showMessageDialog(browser, "That is not a valid web address.");
                }
            }
        });

        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browser.getPane().reload();
            }
        });
    }
}