package pl.edu.pk.zpi.plagiator.domain;

import java.io.InputStream;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 17:42
 */
public class StoredDocument {

    private InputStream content;
    private String fileName;

    protected StoredDocument(InputStream content, String fileName) {
        this.content = content;
        this.fileName = fileName;
    }

    public InputStream getContent() {
        return content;
    }

    public String getFileName() {
        return fileName;
    }
}
