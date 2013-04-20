package pl.edu.pk.zpi.plagiator.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.domain.Document;

import java.util.List;

/**
 * User: msendyka
 * Date: 20.04.13
 * Time: 11:42
 */
@Component
public class ConcurrentRunner {

    @Autowired
    private Runner defaultRunner;

    public Runnable run(final Document document, final ExamineListener examineListener) {
        return new Runnable() {
            @Override
            public void run() {
                List<ComparisonResult> results = defaultRunner.examineDocument(document);
                examineListener.examineComplete(results);
            }
        };
    }
}
