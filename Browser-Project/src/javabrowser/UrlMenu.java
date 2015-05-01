package javabrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Daniel Coutts
 */
public class UrlMenu extends JFrame {

    /**
     * Reference to the containing Browser object.
     */
    Browser browser;

    /**
     * The GUI list where the titles of any URLs added will be displayed.
     */
    private JList<String> list;

    /**
     * The JPanel where any buttons will be added.
     */
    private JPanel buttons;

    /**
     * Vector object to store the URL titles.
     */
    private Vector<String> titles;

    /**
     * List to store the URLs.
     */
    private List<URL> urls;

    private JButton goTo;
    private JButton remove;
    private JButton removeAll;

    /**
     * Creates a menu of URLs with buttons for navigating to and removing items.
     *
     * @param browser the associated Browser object.
     */
    public UrlMenu(Browser browser) {
        super();

        this.browser = browser;
        this.setLayout(new BorderLayout());

        titles = new Vector<String>();
        urls = new LinkedList<URL>();

        // Initialise the JList and tell it to display the contents of titles.
        list = new JList<String>(titles);

        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the List and button pane in the correct positions.
        add(buttons, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);

        setMinimumSize(new Dimension(600, 400));

        goTo = new JButton("Go to");
        remove = new JButton("Remove");
        removeAll = new JButton("Remove All");

        // Add the buttons to the button pane.
        buttons.add(goTo);
        buttons.add(remove);
        buttons.add(removeAll);

        addListeners();
    }

    /**
     * Saves the data to file.
     *
     * @param file The file to load from.
     */
    public void save(String file) {
        try {
            File listFile = new File(file);
            PrintWriter list = new PrintWriter(listFile);
            for (int i = 0; i < titles.size(); i++) {
                list.println(titles.get(i));
                list.println(urls.get(i));
            }
            list.close();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "A config file is missing and cannot be created.");
        }
    }

    /**
     * Loads the data from file.
     *
     * @param file The file to load from.
     */
    public void load(String file) {
        try {
            FileReader list = new FileReader(file);
            Scanner source = new Scanner(list);

            while (source.hasNextLine()) {
                this.addListItem(source.nextLine(), Browser.makeUrl(source.nextLine()));
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "A config file is missing and cannot be created.");
        }
    }

    /**
     * Add a URL to the list.
     *
     * @param title The title of the list item.
     * @param url   The associated url.
     */
    void addListItem(String title, URL url) {
        titles.add(title);
        urls.add(url);
        list.setListData(titles);
        list.revalidate();
        list.repaint();
    }

    /**
     * Remove an entry from the list by index.
     *
     * @param index The index of the entry to remove.
     */
    private void delete(int index) {
        titles.remove(index);
        urls.remove(index);
        list.setListData(titles);
        list.revalidate();
        list.repaint();
    }

    /**
     * Clear the JList.
     */
    private void deleteAll() {
        titles = new Vector<String>();
        urls = new LinkedList<URL>();
        list.setListData(titles);
        list.revalidate();
        list.repaint();
    }

    /**
     * Add listeners for all of the buttons.
     */
    private void addListeners() {

        goTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index >= 0 && index < titles.size()) {
                    browser.getSession().navigate(urls.get(index));
                }
            }
        });

        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index >= 0 && index < titles.size()) {
                    delete(index);
                }
            }
        });

        removeAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAll();
            }
        });
    }
}