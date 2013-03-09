package pl.edu.pk.zpi.plagiator.domain;

import java.io.File;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 11:17
 */
public class Document {

    private DocumentType documentType;
    private File file;

    public Document(File file) {
        this.file = file;
        documentType = getDocumentType(file);
    }

    private DocumentType getDocumentType(File file) {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        return DocumentType.valueOf(extension.toUpperCase());
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public File getFile() {
        return file;
    }
}
