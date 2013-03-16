package pl.edu.pk.zpi.plagiator.executable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jcr.*;

/**
 * User: msendyka
 * Date: 16.03.13
 * Time: 11:29
 */
public class JcrInstaller {

    public static void main(String[] args) throws RepositoryException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:context.xml");
        Session session = openSession(context);
        Node rootNode = session.getRootNode();

        //po dodaniu jakis nowych galezi do jcra dodajemy tutaj tworzenie ich
        rootNode.addNode("docs");

        cleanup(session);
    }

    private static Session openSession(ApplicationContext context) throws RepositoryException {
        Repository repository = context.getBean(Repository.class);
        return repository.login(new SimpleCredentials("admin", new char[]{}));
    }

    private static void cleanup(Session session) throws RepositoryException {
        session.save();
        session.logout();
    }
}
