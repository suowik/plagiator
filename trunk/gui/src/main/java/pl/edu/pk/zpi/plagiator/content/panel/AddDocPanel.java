package pl.edu.pk.zpi.plagiator.content.panel;

import pl.edu.pk.zpi.plagiator.content.Content;
import pl.edu.pk.zpi.plagiator.content.ContentManager;
import pl.edu.pk.zpi.plagiator.content.ContentPanelFactory;
import pl.edu.pk.zpi.plagiator.domain.StoredDocument;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
public class AddDocPanel implements ContentPanel, ActionListener {

    private ContentManager contentManager;
    private JButton addBtn;
    private JButton cancelBtn;
    private JButton selectFileBtn;
    private JLabel selectFileLabel;
    private JTextField selectFile;
    private File selectedFile;

    public AddDocPanel(ContentManager contentManager) {
        this.contentManager = contentManager;
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
        c.gridx=0;
        c.gridy=0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.insets.left = 10; c.insets.right = 10; c.insets.top = 10;
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
        c.insets.right = 10; c.insets.top = 10;
        panel.add(selectFileBtn, c);
        JLabel processAfterAddLabel = new JLabel("Przeskanować po dodaniu?");
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.insets.left = 10; c.insets.right = 10; c.insets.top = 10;
        panel.add(processAfterAddLabel, c);
        JCheckBox processAfterAddCheckBox = new JCheckBox();
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets.right = 10; c.insets.top = 10;
        panel.add(processAfterAddCheckBox, c);
        addBtn = new JButton("Dodaj");
        addBtn.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=3;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.insets.left = 10; c.insets.right = 10; c.insets.top = 10;
        panel.add(addBtn, c);
        cancelBtn = new JButton("Anuluj");
        cancelBtn.addActionListener(this);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets.right = 10; c.insets.top = 10;
        panel.add(cancelBtn, c);
        panel.setBorder(new EmptyBorder(0,200,0,200));
        return panel;
    }

    private void createFileChooser() {
                       JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            //This is where a real application would open the file.
            selectFile.setText(selectedFile.getName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(selectFileBtn)) {
             createFileChooser();
        }
        if(e.getSource().equals(addBtn)) {
             contentManager.saveFile(selectedFile);
            Iterator<StoredDocument> iterator = contentManager.findAll().iterator();
             while(iterator.hasNext()) {
                 StoredDocument document = iterator.next();
                 System.out.println(document.getFileName());
             }
        }
        if(e.getSource().equals(cancelBtn)) {
            contentManager.setContent(ContentPanelFactory.createContent(Content.RESULT, contentManager).getContent());
        }
    }
}
