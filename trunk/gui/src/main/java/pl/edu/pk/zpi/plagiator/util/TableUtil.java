package pl.edu.pk.zpi.plagiator.util;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.05.13
 * Time: 16:21
 */
public class TableUtil {

    private TableUtil() {

    }

    public static void setWidthAsPercentages(JTable table,
                                              double... percentages) {
        final double factor = 10000;

        TableColumnModel model = table.getColumnModel();
        for (int columnIndex = 0; columnIndex < percentages.length; columnIndex++) {
            TableColumn column = model.getColumn(columnIndex);
            column.setPreferredWidth((int) (percentages[columnIndex] * factor));
        }
    }
}
