package pl.edu.pk.zpi.plagiator.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.04.13
 * Time: 20:48
 */
@Component
public class StatusBarFactory {

    @Autowired
    private Properties properties;
    private JLabel statusBar;

    @PostConstruct
    public void init() {
         createStatusBar();
    }

    private void createStatusBar() {
        statusBar = new JLabel(properties.getProperty("statusBar.default"));
        statusBar.setFont(new Font("Serif", Font.BOLD, 20));
        statusBar.setForeground(Color.RED);
    }

    public JLabel getStatusBar() {
        return statusBar;
    }

    public void setText(String text) {
        statusBar.setText(text);
    }
}
