package pl.edu.pk.zpi.plagiator.content.panel;

import pl.edu.pk.zpi.plagiator.domain.ComparisonStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.05.13
 * Time: 15:32
 */
public class StatusRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Object val = table.getValueAt(row, 3);
        if(val instanceof ComparisonStatus) {
            switch ((ComparisonStatus)val) {
                case OK: c.setBackground(Color.GREEN);
                    c.setForeground(Color.BLACK);
                    break;
                case SUSPICIOUS: c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE);
                    break;
            }
        }
        return c;
    }
}
