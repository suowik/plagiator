package pl.edu.pk.zpi.plagiator.domain;

/**
 * User: msendyka
 * Date: 16.03.13
 * Time: 12:16
 */
public interface ComparisonResult {

    ComparisonStatus getStatus();

    String getExaminedFileName();

    String getComparedFileName();
}
