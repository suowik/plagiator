package pl.edu.pk.zpi.plagiator.dao;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.XStream;
import pl.edu.pk.zpi.plagiator.domain.ComparisonResult;
import pl.edu.pk.zpi.plagiator.util.ShutdownUtil;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * User: msendyka
 * Date: 20.04.13
 * Time: 16:34
 */
@org.springframework.stereotype.Repository
public class ResultDao extends AbstractJcrDao implements Dao<ComparisonResult> {

    private static final String PATTERN = "yyyy-MM-dd HH-mm-ss";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(PATTERN);
    private static final XStream X_STREAM = new XStream();
    private final String PATH = "docs_results";


    @Override
    public List<ComparisonResult> findAll() {
        NodeIterator all = findAll(PATH);
        ArrayList<ComparisonResult> results = Lists.newArrayList();
        while (all.hasNext()) {
            Node next = (Node) all.next();
            Node jcrContent;
            InputStream content = null;
            String fileName = null;
            try {
                jcrContent = next.getNode("jcr:content");
                fileName = next.getName();
                content = jcrContent.getProperty("jcr:data").getBinary().getStream();
                results.add((ComparisonResult) X_STREAM.fromXML(content));

            } catch (RepositoryException e) {
                ShutdownUtil.shutdown();
            }
        }
        return results;
    }

    @Override
    public void save(ComparisonResult entity) {
        String marshalled = X_STREAM.toXML(entity);

        try {
            lazy();
            String mimeType = "application/octet-stream";
            Node rootNode = session.getRootNode();
            Node rootDocs = rootNode.getNode(PATH);
            Node fileNode = rootDocs.addNode(createResultName(entity), "nt:file");
            Node resNode = fileNode.addNode("jcr:content", "nt:resource");
            resNode.setProperty("jcr:mimeType", mimeType);
            resNode.setProperty("jcr:encoding", "");
            resNode.setProperty("jcr:data", new ByteArrayInputStream(marshalled.getBytes()));
            session.save();

        } catch (RepositoryException e) {
            ShutdownUtil.shutdown();
        }
    }

    private String createResultName(ComparisonResult entity) {
        return entity.getExaminedFileName() + "_" + SIMPLE_DATE_FORMAT.format(entity.getDate());
    }
}
