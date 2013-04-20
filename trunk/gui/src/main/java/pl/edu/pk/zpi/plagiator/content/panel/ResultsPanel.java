package pl.edu.pk.zpi.plagiator.content.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.content.ContentManager;
import pl.edu.pk.zpi.plagiator.content.ResultsTableModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

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
    private ResultsTableModel model;

    public ResultsPanel() {
    }

    @Override
    public JPanel getContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        model = new ResultsTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollpane = new JScrollPane(table);
        panel.add(scrollpane, BorderLayout.CENTER);
        return panel;
    }

    public void refreshModel() {

    }
}
