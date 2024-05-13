package kevesse_kokanyolo_kod.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Cleaner;

public class InventoryView extends JPanel {
    
    public InventoryView() {
        createInventoryInfo();
        
    }
    /**
     * Megjeleníti a kapott AcademicPerson tárgylistáját.
     */
    public void display(AcademicPerson person) {
    }

    /**
     * Megjeleníti a kapott Cleaner tárgylistáját.
     * @param person
     */
    public void display(Cleaner person) {
    }

    //Középen alul lévő Inventory információt tároló részt jeleníti meg
    private void createInventoryInfo() {
        JLabel inventoryInfo = new JLabel("Inventory");
        inventoryInfo.setBorder(new EmptyBorder(5, 0, 10, 0));
        inventoryInfo.setFont(inventoryInfo.getFont().deriveFont(20f));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(350, 300));
        add(inventoryInfo);

        for (int i = 0; i < 5; i++) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
            if(i < 2) {
                JLabel itemLabel = new JLabel("ItemName");
                itemLabel.setBorder(new EmptyBorder(0, 0, 0, 60));
                itemLabel.setFont(itemLabel.getFont().deriveFont(20f));
                itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                JButton useButton = new JButton("Use");
                useButton.setFont(useButton.getFont().deriveFont(18f));

                JButton dropButton = new JButton("Drop");
                dropButton.setFont(dropButton.getFont().deriveFont(18f));

                itemPanel.add(itemLabel);
                itemPanel.add(useButton);
                itemPanel.add(dropButton);
            }
            itemPanel.setPreferredSize(new Dimension(330, 40));
            add(itemPanel);
        }

    }
}
