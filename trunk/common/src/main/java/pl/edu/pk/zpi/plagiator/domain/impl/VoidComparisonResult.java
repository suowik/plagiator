package pl.edu.pk.zpi.plagiator.domain.impl;

import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.domain.ComparisonStatus;

import static pl.edu.pk.zpi.plagiator.domain.ComparisonStatus.OK;

/**
 * User: msendyka
 * Date: 16.03.13
 * Time: 12:25
 */
public class VoidComparisonResult implements ComparisonResult {

    @Override
    public ComparisonStatus getStatus() {
        return OK;
    }
}
