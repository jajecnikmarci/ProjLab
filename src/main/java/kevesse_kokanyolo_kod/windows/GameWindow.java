package kevesse_kokanyolo_kod.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    private JFrame frame;
    private JPanel playerPanel;
    private JPanel bottomPanel;
    private JPanel inventoryPanel;
    private JPanel roomPanel;

    public GameWindow() {
        frame = new JFrame("Labyrinth");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        createLabyrinth();
        createPlayerInfo();
        createInventoryInfo();
        createRoomInfo();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public class IntPair {
        public int x;
        public int y;

        public IntPair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void createLabyrinth() {
        List<IntPair> roomsLocation = new ArrayList<>();
        roomsLocation.add(new IntPair(840, 90));
        roomsLocation.add(new IntPair(1190, 90));
        roomsLocation.add(new IntPair(700, 220));
        roomsLocation.add(new IntPair(990, 220));
        roomsLocation.add(new IntPair(1330, 220));
        roomsLocation.add(new IntPair(525, 340));
        roomsLocation.add(new IntPair(700, 340));
        roomsLocation.add(new IntPair(1190, 340));
        roomsLocation.add(new IntPair(700, 460));
        roomsLocation.add(new IntPair(990, 460));
        roomsLocation.add(new IntPair(525, 590));
        roomsLocation.add(new IntPair(840, 590));
        roomsLocation.add(new IntPair(1190, 590));

        JPanel panel = new JPanel();
        panel.setLayout(null); // Kell a setBounds() használatához

        for (int i = 0; i < roomsLocation.size(); i++) {
            JPanel roomPanel = new JPanel();
            roomPanel.setLayout(new GridLayout(4, 3));
            roomPanel.setBounds(roomsLocation.get(i).x, roomsLocation.get(i).y, 80, 80);
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            for (int j = 0; j < 12; j++) {
                JPanel smallPanel = new JPanel();
                if (j == 11) {
                    JLabel label = new JLabel("4");
                    label.setFont(new Font("Arial", Font.BOLD, 12));
                    smallPanel.add(label);
                }
                roomPanel.add(smallPanel);
            }
            frame.add(roomPanel);
            panel.add(roomPanel);
        }
        frame.add(panel);
    }

    private void createPlayerInfo() {
        playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerPanel.setPreferredSize(new Dimension(350, 300));
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        JLabel playerInfo = new JLabel("Player");
        playerInfo.setBorder(new EmptyBorder(5, 20, 40, 0));
        playerInfo.setFont(playerInfo.getFont().deriveFont(20f));

        JLabel playerName = new JLabel("Name: ");
        playerName.setBorder(new EmptyBorder(0, 20, 0, 0));
        playerName.setFont(playerInfo.getFont().deriveFont(18f));

        JLabel playerSouls = new JLabel("Souls: ");
        playerSouls.setBorder(new EmptyBorder(20, 20, 50, 0));
        playerSouls.setFont(playerInfo.getFont().deriveFont(18f));

        JButton pickUpButton = new JButton("Pick up item");
        pickUpButton.setFont(playerInfo.getFont().deriveFont(18f));
        pickUpButton.setPreferredSize(new Dimension(225, 50));

        playerPanel.add(playerInfo);
        playerPanel.add(playerName);
        playerPanel.add(playerSouls);
        playerPanel.add(pickUpButton);

        bottomPanel.add(playerPanel);
    }

    private void createInventoryInfo() {
        inventoryPanel = new JPanel();
        JLabel inventoryInfo = new JLabel("Inventory");
        inventoryInfo.setBorder(new EmptyBorder(5, 0, 10, 0));
        inventoryInfo.setFont(inventoryInfo.getFont().deriveFont(20f));
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        inventoryPanel.setPreferredSize(new Dimension(350, 300));
        inventoryPanel.add(inventoryInfo);

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
            inventoryPanel.add(itemPanel);
        }

        bottomPanel.add(inventoryPanel);
    }

    private void createRoomInfo() {
        roomPanel = new JPanel();
        JLabel roomInfo = new JLabel("Items in room");
        roomInfo.setBorder(new EmptyBorder(5, 0, 10, 0));
        roomInfo.setFont(roomInfo.getFont().deriveFont(20f));
        roomPanel.add(roomInfo);
        roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        roomPanel.setPreferredSize(new Dimension(200, 300));

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
            roomPanel.add(roomItemPanel);
        }

        bottomPanel.add(roomPanel);
    }
}
