package pl.edu.pk.zpi.plagiator.domain;

import pl.edu.pk.zpi.plagiator.util.ShutdownUtil;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.io.InputStream;

/**
 * User: msendyka
 * Date: 09.03.13
 * Time: 17:37
 */
public class StoredDocuments implements Iterable<StoredDocument> {

    private NodeIterator nodeIterator;

    public StoredDocuments(NodeIterator nodeIterator) {
        this.nodeIterator = nodeIterator;
    }


    @Override
    public java.util.Iterator<StoredDocument> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<StoredDocument> {

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public StoredDocument next() {
            Node next = (Node) nodeIterator.next();
            Node jcrContent;
            InputStream content = null;
            String fileName = null;
            try {
                jcrContent = next.getNode("jcr:content");
                fileName = next.getName();
                content = jcrContent.getProperty("jcr:data").getBinary().getStream();
            } catch (RepositoryException e) {
                ShutdownUtil.shutdown();
            }
            return new StoredDocument(content, fileName);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }
    }
}
