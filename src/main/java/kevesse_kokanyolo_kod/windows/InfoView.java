package kevesse_kokanyolo_kod.windows;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class InfoView extends JPanel {
    DefaultListModel<String> listModel;
    private JList<String> messageList;
    public InfoView() {
        setPreferredSize(new Dimension(350, 200));  
        setLayout(new BorderLayout());
        // Create a scrollable list of messages
        listModel = new DefaultListModel<>();
        messageList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(messageList);
        add(scrollPane, BorderLayout.CENTER);


    }

    public void addMessage(String message) {
        listModel.addElement(message);

        // Scroll to the bottom
        if (listModel.getSize() > 0) {
            messageList.ensureIndexIsVisible(listModel.getSize() - 1);
        }
    }
}
