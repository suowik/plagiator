package pl.edu.pk.zpi.plagiator.domain;

import pl.edu.pk.zpi.plagiator.core.MatchedTextBlocks;

import java.util.Collection;
import java.util.Date;

/**
 * User: msendyka
 * Date: 16.03.13
 * Time: 12:16
 */
public interface ComparisonResult {

    ComparisonStatus getStatus();

    String getExaminedFileName();

    String getComparedFileName();

    Date getDate();

    Collection<MatchedTextBlocks> getMatchedTextBlocks();
}
