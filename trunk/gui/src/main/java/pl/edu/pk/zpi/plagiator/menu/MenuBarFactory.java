package pl.edu.pk.zpi.plagiator.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.content.Content;
import pl.edu.pk.zpi.plagiator.content.ContentManager;
import pl.edu.pk.zpi.plagiator.content.ContentPanelFactory;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.03.13
 * Time: 13:59
 */
@Component
public class MenuBarFactory implements ActionListener{

    private JToolBar menuBar;
    private JButton addDocument;
    private JButton resultsTable;
    @Autowired
    private Properties properties;
    @Autowired
    private ContentManager contentManager;

    @PostConstruct
    public void init() {
        createMenuBar();
    }

    private void createMenuBar() {
        menuBar = new JToolBar();
        addDocument = new JButton(properties.getProperty("menuBar.addDocument"));
        addDocument.addActionListener(this);
        resultsTable = new JButton(properties.getProperty("menuBar.showResults"));
        resultsTable.addActionListener(this);
        menuBar.add(addDocument);
        menuBar.addSeparator();
        menuBar.add(resultsTable);
    }

    public JToolBar getMenuBar() {
        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addDocument)) {
            contentManager.setContent(ContentPanelFactory.createContent(Content.ADD_DOC, contentManager).getContent());
        }
        if(e.getSource().equals(resultsTable)) {
            contentManager.setContent(ContentPanelFactory.createContent(Content.RESULT, contentManager).getContent());
        }
    }
}
