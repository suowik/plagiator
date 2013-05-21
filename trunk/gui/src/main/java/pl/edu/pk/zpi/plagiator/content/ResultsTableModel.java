package pl.edu.pk.zpi.plagiator.content;

import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 20.04.13
 * Time: 17:20
 */
public class ResultsTableModel extends AbstractTableModel {

    private List<ComparisonResult> comparisonResults;

    public ResultsTableModel(List<ComparisonResult> comparisonResults) {
        this.comparisonResults = comparisonResults;
    }

    @Override
    public int getRowCount() {
        return comparisonResults.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object object = getValueAt(0, columnIndex);
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public String getColumnName(int column) {
        String result = "";
        switch (column) {
            case 0:
                result = "Plik źródłowy";
                break;
            case 1:
                result = "Plik wzorca";
                break;
            case 2:
                result = "Data skanowania";
                break;
            case 3:
                result = "Status";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ComparisonResult comparisonResult = comparisonResults.get(rowIndex);
        String result = null;
        switch (columnIndex) {
            case 0:
                result = comparisonResult.getExaminedFileName();
                break;
            case 1:
                result = comparisonResult.getComparedFileName();
                break;
            case 2:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss");
                result = simpleDateFormat.format(comparisonResult.getDate());
                break;
            case 3:
                return comparisonResult.getStatus();
        }
        return result;
    }

    public ComparisonResult getComparisonResult(int index) {
        return comparisonResults.get(index);
    }
}
