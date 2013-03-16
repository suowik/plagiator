package pl.edu.pk.zpi.plagiator.core.extractor;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author b4rt3k
 * 
 */
public interface Extractor {
	void extract(InputStream is, OutputStream os) throws Exception;
}
