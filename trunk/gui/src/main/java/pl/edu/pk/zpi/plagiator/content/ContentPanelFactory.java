package pl.edu.pk.zpi.plagiator.content;

import pl.edu.pk.zpi.plagiator.content.panel.AddDocPanel;
import pl.edu.pk.zpi.plagiator.content.panel.ContentPanel;
import pl.edu.pk.zpi.plagiator.content.panel.ResultsPanel;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.03.13
 * Time: 13:50
 */
public class ContentPanelFactory {

    public static ContentPanel createContent(Content content, ContentManager contentManager) {

        switch (content) {
            case ADD_DOC:
                return new AddDocPanel(contentManager);
            case RESULT:
                return new ResultsPanel(contentManager);
            default:
                throw new IllegalArgumentException("");
        }
    }

}
