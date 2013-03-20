package pl.edu.pk.zpi.plagiator.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.dao.DocumentsDao;
import pl.edu.pk.zpi.plagiator.domain.Document;
import pl.edu.pk.zpi.plagiator.domain.StoredDocuments;
import pl.edu.pk.zpi.plagiator.mainwindow.MainFrameFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.03.13
 * Time: 16:53
 */
@Component
public class ContentManager {

    @Autowired
    private MainFrameFactory mainFrameFactory;
    @Autowired
    private DocumentsDao documentsDao;

    public void setContent(JPanel panel) {
        getContentPane().remove(mainFrameFactory.getContentPanel());
        mainFrameFactory.setContentPanel(panel);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private Container getContentPane() {
        return mainFrameFactory.getMainFrame().getContentPane();
    }

    public void saveFile(File file) {
        Document document = new Document(file);
        documentsDao.save(document);
    }

    public StoredDocuments findAll() {
        return documentsDao.findAll();
    }

}
