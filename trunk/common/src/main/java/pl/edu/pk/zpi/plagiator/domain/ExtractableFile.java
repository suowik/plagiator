package pl.edu.pk.zpi.plagiator.domain;

import java.io.InputStream;

/**
 * User: msendyka
 * Date: 16.04.13
 * Time: 22:16
 */
public interface ExtractableFile {
    InputStream getContent();

    DocumentType getDocumentType();
}
