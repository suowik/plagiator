package pl.edu.pk.zpi.plagiator.core.extractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.domain.DocumentType;
import pl.edu.pk.zpi.plagiator.domain.ExtractableFile;

import java.io.OutputStream;

/**
 * User: msendyka
 * Date: 16.04.13
 * Time: 21:51
 */
@Component
public class ExtractorFacade {

    @Autowired
    private Extractor docExtractor;

    @Autowired
    private Extractor docxExtractor;

    public void extract(ExtractableFile toExtract, OutputStream output) {
        try {
            if (toExtract.getDocumentType() == DocumentType.DOC) {
                extractToGiven(docExtractor, toExtract, output);
            } else if (toExtract.getDocumentType() == DocumentType.DOCX) {
                extractToGiven(docxExtractor, toExtract, output);
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void extractToGiven(Extractor extractor, ExtractableFile toExtract, OutputStream output) throws Exception {
        extractor.extract(toExtract.getContent(), output);
    }

}
