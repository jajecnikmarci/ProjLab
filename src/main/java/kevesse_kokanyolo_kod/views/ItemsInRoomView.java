package kevesse_kokanyolo_kod.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kevesse_kokanyolo_kod.room.Room;

public class ItemsInRoomView extends JPanel{
    public ItemsInRoomView() {
        createRoomInfo();
    }
    /**
     * Megjeleníti a kapott szoba tárgyait egy görgethető listában.
     * @param room a szoba, amelyiknek a tárgyait meg kell jeleníteni
     */
    public void display(Room room) {
    }

    //Jobb alul lévő szobában lévő tárgyakat tároló részt jeleníti meg
    private void createRoomInfo() {
        JLabel roomInfo = new JLabel("Items in room");
        roomInfo.setBorder(new EmptyBorder(5, 0, 10, 0));
        roomInfo.setFont(roomInfo.getFont().deriveFont(20f));
        add(roomInfo);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(200, 300));

        for (int i = 0; i < 5; i++) {
            JPanel roomItemPanel = new JPanel();
            roomItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            roomItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
            if(i < 2) {
                JLabel roomItemLabel = new JLabel("ItemName");
                roomItemLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
                roomItemLabel.setFont(roomItemLabel.getFont().deriveFont(20f));
                roomItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                roomItemPanel.add(roomItemLabel);
            }
            roomItemPanel.setPreferredSize(new Dimension(180, 40));
            add(roomItemPanel);
        }

    }
}
