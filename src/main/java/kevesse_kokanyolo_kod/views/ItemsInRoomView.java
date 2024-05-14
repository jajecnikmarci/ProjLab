package kevesse_kokanyolo_kod.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import kevesse_kokanyolo_kod.controllers.Controller;
import kevesse_kokanyolo_kod.items.FFP2; //TODO torlendo
import kevesse_kokanyolo_kod.items.TVSZ; //TODO torlendo
import kevesse_kokanyolo_kod.room.Room;

public class ItemsInRoomView extends JPanel{
    JPanel itemPanels;

    public ItemsInRoomView() {
        createRoomInfo();
    }
    /**
     * Megjeleníti a kapott szoba tárgyait egy görgethető listában.
     * @param room a szoba, amelyiknek a tárgyait meg kell jeleníteni
     */
    public void display(Room room) {
        itemPanels.removeAll();
        for (int i = 0; i < room.getItems().size(); i++) {
            ItemPanel itemPanel = new ItemPanel();
            String nameString = room.getItems().get(i).getClass().getName();
            itemPanel.itemName.setText(nameString.substring(nameString.lastIndexOf(".")+1)); 
            itemPanels.add(itemPanel);
        }
    }

    private class ItemPanel extends JPanel{
        JLabel itemName;
        
        public ItemPanel(){
            //borderek nem jók
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
            
            itemName = new JLabel("ItemName");
            itemName.setBorder(new EmptyBorder(0, 0, 0, 0));
            itemName.setFont(itemName.getFont().deriveFont(20f));
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            this.add(itemName);
            
            this.setPreferredSize(new Dimension(180, 40));
        }
    }

    //Jobb alul lévő szobában lévő tárgyakat tároló részt jeleníti meg
    private void createRoomInfo() {

        JLabel roomInfo = new JLabel("Items in room");
        roomInfo.setBorder(new EmptyBorder(5, 0, 10, 0));
        roomInfo.setFont(roomInfo.getFont().deriveFont(20f));
        add(roomInfo);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(200, 300));

        itemPanels = new JPanel();
        itemPanels.setLayout(new BoxLayout(itemPanels,BoxLayout.Y_AXIS));

        
        
        for (int i = 0; i < 10; i++) {
            ItemPanel itemPanel = new ItemPanel();
            itemPanels.add(itemPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(itemPanels);
        scrollPane.setPreferredSize(new Dimension(200,248));
        itemPanels.setAutoscrolls(true);
        add(scrollPane);

/*

Room room = new Room(4);
room.addItem(new FFP2());
room.addItem(new TVSZ());
room.addItem(new FFP2());


System.out.println(room.getItems().get(0).getClass().getName());
display(room);
*/
    }
}
