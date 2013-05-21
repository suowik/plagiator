package pl.edu.pk.zpi.plagiator.content;

import pl.edu.pk.zpi.plagiator.core.MatchedTextBlocks;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 21.05.13
 * Time: 14:37
 */
public class ResultDetailsTableModel extends AbstractTableModel {


    private final String file;
    private final List<MatchedTextBlocks> matchedTextBlocksList;

    public ResultDetailsTableModel(String file, List<MatchedTextBlocks> matchedTextBlocksList) {
        this.file = file;
        this.matchedTextBlocksList = matchedTextBlocksList;
    }

    @Override
    public int getRowCount() {
        return matchedTextBlocksList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        String result = "";
        switch (column) {
            case 0:
                result = "Tekst";
                break;
            case 1:
                result = "Indeks w pliku źródłowym";
                break;
            case 2:
                result = "Indeks w pliku wzorcowym";
                break;
        }
        return result;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String result = null;
        switch (columnIndex) {
            case 0:
                result = matchedTextBlocksList.get(rowIndex).getPatternFileText();
                break;
            case 1:
                result = String.valueOf(matchedTextBlocksList.get(rowIndex).getIndexInPatternFile());
                break;
            case 2:
                result = String.valueOf(matchedTextBlocksList.get(rowIndex).getIndexInSourceFile());
                break;
        }
        return result;
    }
}
