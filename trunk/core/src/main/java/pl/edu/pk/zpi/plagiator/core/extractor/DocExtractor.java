package pl.edu.pk.zpi.plagiator.core.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

/**
 * @author b4rt3k
 * 
 */
public class DocExtractor implements Extractor {

	/**
	 * Method read bytes from doc input stream and saves it to given output
	 * stream
	 * 
	 * @throws IOException
	 */
	@Override
	public void extract(InputStream is, OutputStream os) throws IOException {
		try {
			WordExtractor extractor = new WordExtractor(new HWPFDocument(is));
			os.write(extractor.getText().getBytes(Charset.forName("UTF-8")));
		} finally {
			if (os != null) {
				os.close();
			}
			if(is!=null){
				is.close();
			}
		}

	}
}
