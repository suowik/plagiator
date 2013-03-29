package pl.edu.pk.zpi.plagiator.core.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * @author b4rt3k
 * 
 */
public class DocxExtractor implements Extractor {
	private static final Logger logger = Logger.getLogger(DocxExtractor.class);

	/**
	 * Method read bytes from docx input stream and saves it to given output
	 * stream
	 * 
	 * @throws IOException
	 */
	@Override
	public void extract(InputStream is, OutputStream os) throws IOException {
		logger.info("Extraction process started.");
		try {
			XWPFWordExtractor extractor = new XWPFWordExtractor(new XWPFDocument(is));
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
