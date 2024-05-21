package kevesse_kokanyolo_kod.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kevesse_kokanyolo_kod.items.IItem;
import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Cleaner;

public class InventoryView extends JPanel {
    JPanel itemPanels;
    public InventoryView() {
        createInventoryInfo();
        
    }

    private class ItemPanel extends JPanel{
        public JLabel itemName;
        public JButton useButton;
        public JButton dropButton;

        public ItemPanel(){
            this.setLayout(new BorderLayout());
            this.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
            
            itemName = new JLabel("");
            itemName.setBorder(new EmptyBorder(0, 0, 0, 60));
            itemName.setFont(itemName.getFont().deriveFont(20f));
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            useButton = new JButton("Use");
            useButton.setFont(useButton.getFont().deriveFont(18f));
            
            dropButton = new JButton("Drop");
            dropButton.setFont(dropButton.getFont().deriveFont(18f));
            
            this.add(itemName,BorderLayout.WEST);
            buttonPanel.add(useButton);
            buttonPanel.add(dropButton);
            this.add(buttonPanel,BorderLayout.EAST);
            this.setPreferredSize(new Dimension(330, 40));
        }
    }

    /**
     * Megjeleníti a kapott AcademicPerson tárgylistáját.
     */
    public void display(AcademicPerson person) {
        if(person == null) {
            for (int i = 0; i < 5; i++) {
                ((ItemPanel)itemPanels.getComponents()[i]).setVisible(false);
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            if(i<person.getInventory().size()){
                IItem selectedItem = person.getInventory().get(i);
                String nameString = selectedItem.getClass().getName();
                String fullString = nameString.substring(nameString.lastIndexOf(".")+1);
                if(selectedItem.getDescription() != null) fullString = fullString + selectedItem.getDescription();
                ((ItemPanel)itemPanels.getComponents()[i]).setVisible(true);

                ((ItemPanel)itemPanels.getComponents()[i]).itemName.setText(fullString); 
                JButton useShort = ((ItemPanel)itemPanels.getComponents()[i]).useButton;
                if(useShort.getActionListeners().length !=0) useShort.removeActionListener(useShort.getActionListeners()[0]);
                if(!selectedItem.isPassive()){
                    ((ItemPanel)itemPanels.getComponents()[i]).useButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            person.useItem((Item)selectedItem);
                        }
                    });
                    ((ItemPanel)itemPanels.getComponents()[i]).useButton.setEnabled(true);
                }
                else{
                    ((ItemPanel)itemPanels.getComponents()[i]).useButton.setEnabled(false);
                }

                JButton dropShort = ((ItemPanel)itemPanels.getComponents()[i]).dropButton;
                if(dropShort.getActionListeners().length !=0) dropShort.removeActionListener(dropShort.getActionListeners()[0]);
                ((ItemPanel)itemPanels.getComponents()[i]).dropButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        person.dropItem(selectedItem); 
                    }
                });
            }
            else {
                ((ItemPanel)itemPanels.getComponents()[i]).setVisible(false);

            }
        }
    }

    /**
     * Megjeleníti a kapott Cleaner tárgylistáját.
     * @param person
     */
    public void display(Cleaner person) {
        for (var panel : itemPanels.getComponents()) {
            panel.setVisible(false);
        }
    }

    //Középen alul lévő Inventory információt tároló részt jeleníti meg
    private void createInventoryInfo() {
        JLabel inventoryInfo = new JLabel("Inventory");
        inventoryInfo.setBorder(new EmptyBorder(5, 0, 10, 0));
        inventoryInfo.setFont(inventoryInfo.getFont().deriveFont(20f));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(350, 300));
        add(inventoryInfo);
        itemPanels = new JPanel();
        itemPanels.setLayout(new BoxLayout(itemPanels,BoxLayout.Y_AXIS));
        for (int i = 0; i < 5; i++) {
            ItemPanel itemPanel = new ItemPanel();
            itemPanels.add(itemPanel);
            itemPanel.setVisible(false);
        }
        add(itemPanels);
    }
}
