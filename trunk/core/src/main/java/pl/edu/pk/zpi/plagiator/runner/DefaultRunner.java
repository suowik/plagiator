package pl.edu.pk.zpi.plagiator.runner;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.core.Analyzer;
import pl.edu.pk.zpi.plagiator.core.MatchedTextBlocks;
import pl.edu.pk.zpi.plagiator.core.extractor.ExtractorFacade;
import pl.edu.pk.zpi.plagiator.dao.DocumentsDao;
import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.domain.ComparisonStatus;
import pl.edu.pk.zpi.plagiator.domain.Document;
import pl.edu.pk.zpi.plagiator.domain.StoredDocument;
import pl.edu.pk.zpi.plagiator.domain.impl.DefaultComparisonResult;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: msendyka
 * Date: 16.03.13
 * Time: 12:26
 */
@Component
public class DefaultRunner implements Runner {

    @Autowired
    DocumentsDao documentsDao;

    @Autowired
    private Analyzer analyzer;

    @Autowired
    private ExtractorFacade extractorFacade;

    @Override
    public List<ComparisonResult> examineDocument(Document document) {
        ArrayList<ComparisonResult> comparisonResults = Lists.newArrayList();
        for (Object o : documentsDao.findAll()) {
            try {
                comparisonResults.add(compare(document, (StoredDocument) o));
            } catch (FileNotFoundException ignored) {
            }
        }
        return comparisonResults;
    }

    private ComparisonResult compare(Document document, StoredDocument next) throws FileNotFoundException {
        if (document.getFile().getName().equals(next.getFileName())) {
            return null;
        }
        ByteArrayOutputStream input = new ByteArrayOutputStream();
        ByteArrayOutputStream pattern = new ByteArrayOutputStream();
        extractorFacade.extract(document, input);
        extractorFacade.extract(next, pattern);
        Collection<MatchedTextBlocks> analyze = analyzer.analyze(input.toString(), pattern.toString(), 5);
        ComparisonStatus status;
        status = analyze.isEmpty() ? ComparisonStatus.OK : ComparisonStatus.SUSPICIOUS;
        return new DefaultComparisonResult(status, document.getFile().getName(), next.getFileName());
    }
}
