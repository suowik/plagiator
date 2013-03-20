package pl.edu.pk.zpi.plagiator.content.panel;

import pl.edu.pk.zpi.plagiator.content.ContentManager;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.03.13
 * Time: 15:42
 */
public class ResultsPanel implements ContentPanel {

    private ContentManager contentManager;

    public ResultsPanel(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    @Override
    public JPanel getContent() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("RESULTS");
        panel.add(label);
        return panel;
    }
}
