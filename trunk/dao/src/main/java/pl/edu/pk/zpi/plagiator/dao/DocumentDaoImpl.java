package pl.edu.pk.zpi.plagiator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.domain.Document;
import pl.edu.pk.zpi.plagiator.domain.StoredDocuments;
import pl.edu.pk.zpi.plagiator.util.ShutdownUtil;
import sun.net.www.MimeTable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jcr.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 11:20
 */
@Component
public class DocumentDaoImpl implements DocumentsDao {
    public static final String ADMIN = "admin";
    private final String PATH = "docs";

    @Autowired
    private Repository repository;
    private Session session;

    @PostConstruct
    public void init() throws RepositoryException {
    }

    @PreDestroy
    public void destroy() {
        if (session != null) {
            session.logout();
        }
    }

    @Override
    public StoredDocuments findAll() {
        try {
            lazy();
            Node rootNode = session.getRootNode();
            Node rootDocs = rootNode.getNode(PATH);
            NodeIterator nodes = rootDocs.getNodes();
            return new StoredDocuments(nodes);
        } catch (RepositoryException e) {
            //TODO logowac i system exit-1
            e.printStackTrace();
        }
        ShutdownUtil.shutdown();
        return null;
    }

    private void lazy() throws RepositoryException {
        if (session == null) {
            session = repository.login(new SimpleCredentials(ADMIN, new char[]{}));
        }
    }

    @Override
    public void save(Document entity) {
        try {
            lazy();
            MimeTable mt = MimeTable.getDefaultTable();
            String mimeType = mt.getContentTypeFor(entity.getFile().getName());
            if (mimeType == null) mimeType = "application/octet-stream";
            Node rootNode = session.getRootNode();
            Node rootDocs = rootNode.addNode(PATH);
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

        } catch (RepositoryException e) {
            ShutdownUtil.shutdown();
        }
    }

}
