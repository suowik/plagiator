package pl.edu.pk.zpi.plagiator.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.mainwindow.MainFrameFactory;

import javax.jcr.RepositoryException;
import javax.swing.*;
import java.awt.*;

/**
 * User: msendyka
 * Date: 02.03.13
 * Time: 20:06
 */
@Component
public class Starter {

    @Autowired
    private MainFrameFactory mainFrameFactory;

    public static void main(String[] args) throws RepositoryException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        Starter p = context.getBean(Starter.class);
        JFrame mainFrame = p.mainFrameFactory.getMainFrame();
        mainFrame.setSize(new Dimension(1024, 768));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        //TODO zamykanie kontekstu przy zamknieciu okna gui i przy wywlowaniu ShutdownUtil


    }
}
