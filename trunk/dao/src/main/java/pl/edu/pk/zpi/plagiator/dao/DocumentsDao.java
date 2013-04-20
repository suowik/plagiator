package pl.edu.pk.zpi.plagiator.dao;

import pl.edu.pk.zpi.plagiator.domain.Document;
import pl.edu.pk.zpi.plagiator.domain.StoredDocument;
import pl.edu.pk.zpi.plagiator.domain.StoredDocuments;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 18:17
 */
public interface DocumentsDao extends SavingDao<Document> {
    StoredDocuments findAll();

    StoredDocument findByName(String fileName);
}
