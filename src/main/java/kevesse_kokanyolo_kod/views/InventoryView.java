package kevesse_kokanyolo_kod.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
        //public String itemName="ItemName";
        public JLabel itemName;
        public String itemDescription = "";
        public JButton useButton;
        public JButton dropButton;

        public ItemPanel(){
            //TODO Borderek még nem jók ha jól látom
            this.setLayout(new BorderLayout());
            this.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
            
            itemName = new JLabel("ItemName");
            itemName.setBorder(new EmptyBorder(0, 0, 0, 60));
            itemName.setFont(itemName.getFont().deriveFont(20f));
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            useButton = new JButton("Use");
            useButton.setFont(useButton.getFont().deriveFont(18f));
            
            dropButton = new JButton("Drop");
            dropButton.setFont(dropButton.getFont().deriveFont(18f));
            
            
            /*
            useButton.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    itemName.setText("xd");;
                }
                
            });

            dropButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    itemName.setText("ItemName");;
                }
                
            });
            */
            
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
        for (int i = 0; i < person.getInventory().size(); i++) {
            IItem selectedItem = person.getInventory().get(i);
            ((ItemPanel)itemPanels.getComponents()[i]).itemName.setText(person.getInventory().get(i).getClass().getName()); //TODO leírások hozzáadása
            if(!selectedItem.isPassive()){
                ((ItemPanel)itemPanels.getComponents()[i]).useButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        person.useItem((Item)selectedItem); //TODO ez most jó?? nem, kell egy itemUsable();
                    }
                });
            }
            else{
                ((ItemPanel)itemPanels.getComponents()[i]).useButton.setEnabled(false);
            }

            ((ItemPanel)itemPanels.getComponents()[i]).dropButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    person.dropItem(selectedItem);
                }
            });
        }

        if(person.getInventory().size()<5) {
            for (int i = 0; i < 5-person.getInventory().size(); i++) {
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
            //add(itemPanel);
        }
        add(itemPanels);
    }
}
