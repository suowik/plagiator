package pl.edu.pk.zpi.plagiator.core.extractor;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @author b4rt3k
 */
@Component
public class DocExtractor implements Extractor {
    private static final Logger logger = Logger.getLogger(DocExtractor.class);

    /**
     * Method read bytes from doc input stream and saves it to given output
     * stream
     *
     * @throws IOException
     */
    @Override
    public void extract(InputStream is, OutputStream os) throws IOException {
        logger.info("Extraction process started.");
        try {
            WordExtractor extractor = new WordExtractor(new HWPFDocument(is));
            os.write(extractor.getText().getBytes(Charset.forName("UTF-8")));
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
        logger.info("Extraction completed.");
    }
}
