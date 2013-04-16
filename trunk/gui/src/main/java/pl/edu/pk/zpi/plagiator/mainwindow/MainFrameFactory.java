package pl.edu.pk.zpi.plagiator.mainwindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.menu.MenuBarFactory;
import pl.edu.pk.zpi.plagiator.status.StatusBarFactory;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * User: msendyka
 * Date: 03.03.13
 * Time: 12:39
 */
@Component
public class MainFrameFactory {

    private JFrame mainFrame;
    @Autowired
    private Properties properties;
    @Autowired
    private MenuBarFactory menuBarFactory;
    @Autowired
    private StatusBarFactory statusBarFactory;

    @Value("${mainFrame.name}")
    private String windowName;
    private JPanel contentPanel;

    @PostConstruct
    public void init() {
        createMainFrame(windowName);
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private JFrame createMainFrame(String name) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(menuBarFactory.getMenuBar(), BorderLayout.PAGE_START);
        frame.getContentPane().add(contentPanel = new JPanel(), BorderLayout.CENTER);
        frame.getContentPane().add(statusBarFactory.getStatusBar(),BorderLayout.SOUTH);
        mainFrame = frame;
        return frame;

    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }
}
