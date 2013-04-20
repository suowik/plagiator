package pl.edu.pk.zpi.plagiator.dao;

import org.springframework.stereotype.Repository;
import pl.edu.pk.zpi.plagiator.domain.Document;
import pl.edu.pk.zpi.plagiator.domain.StoredDocument;
import pl.edu.pk.zpi.plagiator.domain.StoredDocuments;
import pl.edu.pk.zpi.plagiator.util.ShutdownUtil;
import sun.net.www.MimeTable;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 11:20
 */
@Repository
public class DocumentDaoImpl extends AbstractJcrDao implements DocumentsDao {
    private final String PATH = "docs";

    @Override
    public StoredDocuments findAll() {
        NodeIterator all = findAll(PATH);
        if (all != null) {
            return new StoredDocuments(all);
        }
        return null;
    }

    @Override
    public StoredDocument findByName(String fileName) {
        try {
            lazy();
            Node rootNode = session.getRootNode();
            Node rootDocs = rootNode.getNode(PATH);
            Node node;

            if (rootDocs.hasNode(fileName)) {
                node = rootDocs.getNode(fileName);
            } else {
                return null;
            }
            Node jcrContent = node.getNode("jcr:content");
            InputStream content = jcrContent.getProperty("jcr:data").getBinary().getStream();
            return new StoredDocument(content, node.getName());
        } catch (RepositoryException e) {
            //TODO logowac i system exit-1
            e.printStackTrace();
        }
        ShutdownUtil.shutdown();
        return null;
    }

    @Override
    public void save(Document entity) {
        try {
            lazy();
            if (findByName(entity.getFile().getName()) == null) {
                MimeTable mt = MimeTable.getDefaultTable();
                String mimeType = mt.getContentTypeFor(entity.getFile().getName());
                if (mimeType == null) mimeType = "application/octet-stream";
                Node rootNode = session.getRootNode();
                Node rootDocs = rootNode.getNode(PATH);
                Node fileNode = rootDocs.addNode(entity.getFile().getName(), "nt:file");
                Node resNode = fileNode.addNode("jcr:content", "nt:resource");
                resNode.setProperty("jcr:mimeType", mimeType);
                resNode.setProperty("jcr:encoding", "");
                try {
                    resNode.setProperty("jcr:data", new FileInputStream(entity.getFile()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                session.save();
            }

        } catch (RepositoryException e) {
            ShutdownUtil.shutdown();
        }
    }

}
