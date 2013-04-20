package pl.edu.pk.zpi.plagiator.domain.impl;

import pl.edu.pk.zpi.plagiator.core.MatchedTextBlocks;
import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.domain.ComparisonStatus;

import java.util.Collection;
import java.util.Date;

/**
 * User: msendyka
 * Date: 20.04.13
 * Time: 11:10
 */
public class DefaultComparisonResult implements ComparisonResult {
    private ComparisonStatus status;
    private String examinedFileName;
    private String comparedFileName;
    private Date date;
    private Collection<MatchedTextBlocks> matchedTextBlocksList;

    public DefaultComparisonResult(ComparisonStatus status, String examinedFileName, String comparedFileName, Date date, Collection<MatchedTextBlocks> matchedTextBlocksList) {
        this.status = status;
        this.examinedFileName = examinedFileName;
        this.comparedFileName = comparedFileName;
        this.date = date;
        this.matchedTextBlocksList = matchedTextBlocksList;
    }

    @Override
    public ComparisonStatus getStatus() {
        return status;
    }

    @Override
    public String getExaminedFileName() {
        return examinedFileName;
    }

    @Override
    public String getComparedFileName() {
        return comparedFileName;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Collection<MatchedTextBlocks> getMatchedTextBlocks() {
        return matchedTextBlocksList;
    }
}
