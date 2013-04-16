package pl.edu.pk.zpi.plagiator.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.content.panel.AddDocPanel;
import pl.edu.pk.zpi.plagiator.content.panel.ContentPanel;
import pl.edu.pk.zpi.plagiator.content.panel.ResultsPanel;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.03.13
 * Time: 13:50
 */
@Component
public class ContentPanelFactory {

    @Autowired
    private AddDocPanel addDocPanel;
    @Autowired
    private ResultsPanel resultsPanel;

    public ContentPanel createContent(Content content) {

        switch (content) {
            case ADD_DOC:
                return addDocPanel;
            case RESULT:
                return resultsPanel;
            default:
                throw new IllegalArgumentException("");
        }
    }

}
