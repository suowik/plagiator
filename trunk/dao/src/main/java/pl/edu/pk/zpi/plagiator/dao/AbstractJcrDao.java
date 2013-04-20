package pl.edu.pk.zpi.plagiator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.edu.pk.zpi.plagiator.util.ShutdownUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jcr.*;

/**
 * User: msendyka
 * Date: 20.04.13
 * Time: 16:36
 */
public class AbstractJcrDao {
    public static final String ADMIN = "admin";


    public NodeIterator findAll(String path) {
        try {
            lazy();
            Node rootNode = session.getRootNode();
            Node rootDocs = rootNode.getNode(path);
            NodeIterator nodes = rootDocs.getNodes();
            return nodes;
        } catch (RepositoryException e) {
            //TODO logowac i system exit-1
            e.printStackTrace();
        }
        ShutdownUtil.shutdown();
        return null;
    }

    @Qualifier("repository")
    @Autowired
    private Repository repository;
    protected Session session;

    @PostConstruct
    public void init() throws RepositoryException {
    }

    @PreDestroy
    public void destroy() {
        if (session != null) {
            session.logout();
        }
    }

    protected void lazy() throws RepositoryException {
        if (session == null) {
            session = repository.login(new SimpleCredentials(ADMIN, new char[]{}));
        }
    }
}
