package pl.edu.pk.zpi.plagiator.core;

public class MatchedTextBlocks {
    /**
     * Class holds block indexes in pattern and source file (index mean word
     * number from zero)
     *
     * @author b4rt3k
     */
    private int indexInPatternFile;
    private String patternFileText;
    private int indexInSourceFile;
    private String sourceFileText;

    public int getIndexInPatternFile() {
        return indexInPatternFile;
    }

    public void setIndexInPatternFile(int indexInPatternFile) {
        this.indexInPatternFile = indexInPatternFile;
    }

    public int getIndexInSourceFile() {
        return indexInSourceFile;
    }

    public void setIndexInSourceFile(int indexInSourceFile) {
        this.indexInSourceFile = indexInSourceFile;
    }

    public MatchedTextBlocks(int indexInPatternFile, String patternFileText, int indexInSourceFile, String sourceFileText) {
        this.indexInPatternFile = indexInPatternFile;
        this.patternFileText = patternFileText;
        this.indexInSourceFile = indexInSourceFile;
        this.sourceFileText = sourceFileText;
    }

    public String getPatternFileText() {
        return patternFileText;
    }

    public void setPatternFileText(String patternFileText) {
        this.patternFileText = patternFileText;
    }

    public String getSourceFileText() {
        return sourceFileText;
    }

    public void setSourceFileText(String sourceFileText) {
        this.sourceFileText = sourceFileText;
    }

    public String toString() {
        return "{" + indexInPatternFile + ", " + indexInSourceFile + "}";
    }
}