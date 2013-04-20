package pl.edu.pk.zpi.plagiator.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 11:17
 */
public class Document implements ExtractableFile {

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

    @Override
    public DocumentType getDocumentType() {
        return documentType;
    }

    public File getFile() {
        return file;
    }

    @Override
    public InputStream getContent() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
