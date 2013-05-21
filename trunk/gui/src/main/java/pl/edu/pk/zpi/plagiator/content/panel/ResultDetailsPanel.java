package pl.edu.pk.zpi.plagiator.content.panel;

import pl.edu.pk.zpi.plagiator.content.ResultDetailsTableModel;
import pl.edu.pk.zpi.plagiator.core.MatchedTextBlocks;
import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.util.TableUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.05.13
 * Time: 12:30
 */
public class ResultDetailsPanel implements ContentPanel {

    private ComparisonResult comparisonResult;
    private ResultDetailsTableModel model;

    public ResultDetailsPanel(ComparisonResult comparisonResult) {
        this.comparisonResult = comparisonResult;
    }

    @Override
    public JPanel getContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel fileLabelPanel = new JPanel();
        fileLabelPanel.setLayout(new BorderLayout());
        fileLabelPanel.setBorder(new EmptyBorder(10,0,10,0));
        JLabel examinedFileLabel = new JLabel("Plik źródłowy: " + comparisonResult.getExaminedFileName());
        JLabel comparedFileLabel = new JLabel("Plik wzorcowy: " + comparisonResult.getComparedFileName());
        fileLabelPanel.add(examinedFileLabel, BorderLayout.NORTH);
        fileLabelPanel.add(comparedFileLabel, BorderLayout.SOUTH);
        panel.add(fileLabelPanel, BorderLayout.NORTH);
        model = new ResultDetailsTableModel(comparisonResult.getComparedFileName(), new ArrayList<MatchedTextBlocks>(comparisonResult.getMatchedTextBlocks()));
        JTable table = new JTable(model);
        TableUtil.setWidthAsPercentages(table,0.80,0.10,0.10);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}
