package pl.edu.pk.zpi.plagiator.content.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.content.ContentManager;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.03.13
 * Time: 15:42
 */
@Component
public class ResultsPanel implements ContentPanel {

    @Autowired
    private ContentManager contentManager;

    public ResultsPanel() {
    }

    @Override
    public JPanel getContent() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("RESULTS");
        panel.add(label);
        return panel;
    }
}
