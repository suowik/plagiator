package pl.edu.pk.zpi.plagiator.domain;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 11:55
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentTest {

    private Document underTest;
    @Mock
    private File file;

    @Test
    public void shouldSetDocDocumentType() throws Exception {
        Mockito.when(file.getName()).thenReturn("test.doc");
        underTest = new Document(file);

        DocumentType documentType = underTest.getDocumentType();

        Assert.assertTrue(documentType == DocumentType.DOC);
    }

    @Test
    public void shouldSetDocxDocumentType() throws Exception {
        Mockito.when(file.getName()).thenReturn("C:/abc/def/test.docx");
        underTest = new Document(file);

        DocumentType documentType = underTest.getDocumentType();

        Assert.assertTrue(documentType == DocumentType.DOCX);
    }
}
