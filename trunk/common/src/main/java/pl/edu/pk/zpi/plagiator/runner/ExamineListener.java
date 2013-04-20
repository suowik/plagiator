package pl.edu.pk.zpi.plagiator.runner;

import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;

import java.util.List;

/**
 * User: msendyka
 * Date: 20.04.13
 * Time: 11:41
 */
public interface ExamineListener {

    public void examineComplete(List<ComparisonResult> results);
}
