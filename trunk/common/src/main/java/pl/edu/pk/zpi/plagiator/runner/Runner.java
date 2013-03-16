package pl.edu.pk.zpi.plagiator.runner;

import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.domain.Document;

import java.util.List;

/**
 * User: msendyka
 * Date: 16.03.13
 * Time: 12:14
 */
public interface Runner {

    List<ComparisonResult> examineDocument(Document document);
}
