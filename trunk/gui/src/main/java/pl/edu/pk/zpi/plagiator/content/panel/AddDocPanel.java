package pl.edu.pk.zpi.plagiator.content.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.content.Content;
import pl.edu.pk.zpi.plagiator.content.ContentManager;
import pl.edu.pk.zpi.plagiator.content.ContentPanelFactory;
import pl.edu.pk.zpi.plagiator.dao.SavingDao;
import pl.edu.pk.zpi.plagiator.domain.Document;
import pl.edu.pk.zpi.plagiator.domain.StoredDocument;
import pl.edu.pk.zpi.plagiator.runner.Runner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: mhl
 * Date: 16.03.13
 * Time: 15:41
 */
@Component
public class AddDocPanel implements ContentPanel, ActionListener {

    @Autowired
    private Runner runner;
    @Autowired
    private SavingDao<Document> savingDao;
    @Autowired
    private ContentManager contentManager;
    @Autowired
    private ContentPanelFactory contentPanelFactory;
    private JButton addBtn;
    private JButton cancelBtn;
    private JButton selectFileBtn;
    private JLabel selectFileLabel;
    private JTextField selectFile;
    private File selectedFile;
    private JCheckBox processAfterAddCheckBox;

    public AddDocPanel() {
    }

    @Override
    public JPanel getContent() {
        return createPanel();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        selectFileLabel = new JLabel("Wybierz plik");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.insets.left = 10;
        c.insets.right = 10;
        c.insets.top = 10;
        panel.add(selectFileLabel, c);
        selectFile = new JTextField();
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.6;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets.top = 10;
        panel.add(selectFile, c);
        selectFileBtn = new JButton("^");
        selectFileBtn.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.1;
        c.fill = GridBagConstraints.NONE;
        c.insets.right = 10;
        c.insets.top = 10;
        panel.add(selectFileBtn, c);
        JLabel processAfterAddLabel = new JLabel("Przeskanować po dodaniu?");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.insets.left = 10;
        c.insets.right = 10;
        c.insets.top = 10;
        panel.add(processAfterAddLabel, c);
        processAfterAddCheckBox = new JCheckBox();
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets.right = 10;
        c.insets.top = 10;
        panel.add(processAfterAddCheckBox, c);
        addBtn = new JButton("Dodaj");
        addBtn.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.insets.left = 10;
        c.insets.right = 10;
        c.insets.top = 10;
        panel.add(addBtn, c);
        cancelBtn = new JButton("Anuluj");
        cancelBtn.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets.right = 10;
        c.insets.top = 10;
        panel.add(cancelBtn, c);
        panel.setBorder(new EmptyBorder(0, 200, 0, 200));
        return panel;
    }

    private void createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Microsoft Documents (*.doc, *.docx)", "doc", "docx"));
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            //This is where a real application would open the file.
            selectFile.setText(selectedFile.getName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(selectFileBtn)) {
            createFileChooser();
        }
        if (e.getSource().equals(addBtn)) {
            Document document = new Document(selectedFile);
            if(processAfterAddCheckBox.isSelected()) {
                runner.examineDocument(document);
            }
            savingDao.save(document);
        }
        if (e.getSource().equals(cancelBtn)) {
            contentManager.setContent(contentPanelFactory.createContent(Content.RESULT).getContent());
        }
    }
}
