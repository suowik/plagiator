package pl.edu.pk.zpi.plagiator.domain;

import java.io.InputStream;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 17:42
 */
public class StoredDocument implements ExtractableFile {

    private InputStream content;
    private String fileName;
    private DocumentType documentType;

    public StoredDocument(InputStream content, String fileName) {
        this.content = content;
        this.fileName = fileName;
        documentType = getDocumentType(fileName);
    }

    private DocumentType getDocumentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return DocumentType.valueOf(extension.toUpperCase());
    }

    @Override
    public DocumentType getDocumentType() {
        return documentType;
    }

    @Override
    public InputStream getContent() {
        return content;
    }

    public String getFileName() {
        return fileName;
    }
}
