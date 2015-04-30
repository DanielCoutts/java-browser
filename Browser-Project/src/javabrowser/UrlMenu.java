package javabrowser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Daniel Coutts
 */
public class UrlMenu extends JFrame implements Serializable {

    Browser browser;

    private JList<String> list;

    private JPanel buttons;

    private List<String> titles;

    private List<URL> urls;

    public DefaultListModel model;

    public UrlMenu(Browser browser) {
        super();
        this.browser = browser;

        this.setLayout(new BorderLayout());

        titles = new LinkedList<String>();
        urls = new LinkedList<URL>();

        list = new JList<String>();
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(buttons, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);

        setMinimumSize(new Dimension(300, 400));

        addListeners();
    }

    public void addButton(JButton button) {
        buttons.add(button);
    }


    public void addListItem(String title, URL clickUrl) {
        titles.add(title);
        urls.add(clickUrl);

        String[] elements = titles.toArray(new String[titles.size()]);
        list = new JList<String>(elements);
    }

    public void deleteAll() {
        titles = new LinkedList<String>();
        urls = new LinkedList<URL>();
        list = new JList<String>();
    }

    private void addListeners() {
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index = list.getSelectedIndex();
                browser.getSession().navigate(urls.get(index));
            }
        });
    }
}