package pl.edu.pk.zpi.plagiator.runner;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.dao.DocumentsDao;
import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.domain.Document;
import pl.edu.pk.zpi.plagiator.domain.StoredDocument;
import pl.edu.pk.zpi.plagiator.domain.impl.VoidComparisonResult;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public List<ComparisonResult> examineDocument(Document document) {
        ArrayList<ComparisonResult> comparisonResults = Lists.newArrayList();
        Iterator<StoredDocument> iterator = documentsDao.findAll().iterator();
        while (iterator.hasNext()) {
            comparisonResults.add(compare(document, iterator.next()));
        }
        return comparisonResults;
    }

    private ComparisonResult compare(Document document, StoredDocument next) {
        return new VoidComparisonResult();
    }
}
