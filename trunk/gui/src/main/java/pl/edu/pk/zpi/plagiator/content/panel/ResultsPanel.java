package pl.edu.pk.zpi.plagiator.content.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.content.ContentManager;
import pl.edu.pk.zpi.plagiator.content.ResultsTableModel;
import pl.edu.pk.zpi.plagiator.dao.ResultDao;
import pl.edu.pk.zpi.plagiator.domain.ComparisonStatus;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    @Autowired
    private ResultDao resultDao;
    private ResultsTableModel model;

    public ResultsPanel() {
    }

    @Override
    public JPanel getContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        model = new ResultsTableModel(resultDao.findAll());
        JTable table = new JTable(model);
        table.setDefaultRenderer(ComparisonStatus.class, new StatusRenderer());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    contentManager.setContent(new ResultDetailsPanel(model.getComparisonResult(row)).getContent());
                }
            }
        });
        JScrollPane scrollpane = new JScrollPane(table);
        panel.add(scrollpane, BorderLayout.CENTER);
        return panel;
    }

    public void refreshModel() {

    }
}
