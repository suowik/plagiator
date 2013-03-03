package pl.edu.pk.zpi.plagiator.mainwindow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * User: msendyka
 * Date: 03.03.13
 * Time: 12:39
 */
@Component
public class MainFrameFactory {

    private JFrame mainFrame;

    @Value("${mainFrame.name}")
    private String windowName;

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
        mainFrame = frame;
        return frame;

    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
