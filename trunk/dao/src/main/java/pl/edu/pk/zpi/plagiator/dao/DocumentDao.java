package pl.edu.pk.zpi.plagiator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.domain.Document;
import sun.net.www.MimeTable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 11:20
 */
@Component
public class DocumentDao implements Dao<Document> {
    private final String PATH = "docs";

    @Autowired
    private Repository repository;
    private Session session;

    @PostConstruct
    public void init() throws RepositoryException {
        session = repository.login();
        save(new Document(new File("test.docx")));
    }

    @PreDestroy
    public void destroy() {
        session.logout();
    }

    @Override
    public List<Document> findAll() {
        try {
            Node rootNode = session.getRootNode();

        } catch (RepositoryException e) {
            //TODO logowac i system exit-1
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save(Document entity) {
        try {
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
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        } catch (RepositoryException e) {
            //TODO logowac i system exit-1
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
