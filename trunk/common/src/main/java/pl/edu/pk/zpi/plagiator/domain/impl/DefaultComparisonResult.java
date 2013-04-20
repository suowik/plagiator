package pl.edu.pk.zpi.plagiator.domain.impl;

import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.domain.ComparisonStatus;

/**
 * User: msendyka
 * Date: 20.04.13
 * Time: 11:10
 */
public class DefaultComparisonResult implements ComparisonResult {
    private ComparisonStatus status;
    private String examinedFileName;
    private String comparedFileName;

    public DefaultComparisonResult(ComparisonStatus status, String examinedFileName, String comparedFileName) {
        this.status = status;
        this.examinedFileName = examinedFileName;
        this.comparedFileName = comparedFileName;
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
}
