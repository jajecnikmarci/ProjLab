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
    private List<RoomPanel> roomPanels = new ArrayList<>();

    public GameWindow() {
        frame = new JFrame("Labyrinth");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        createLabyrinth();
        createPlayerInfo();
        createInventoryInfo();
        createRoomInfo();
    }

    public class IntPair {
        public int x;
        public int y;

        public IntPair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class RoomPanel {
        public JPanel room;
        private List<JPanel> roomInfoPanels = new ArrayList<>();

        public RoomPanel(JPanel room) {
            this.room = room;
        }
    }

    private void createLabyrinth() {
        //Szobák helyének meghatározása, IntPair osztályt emiatt hoztam létre
        List<IntPair> roomsLocation = new ArrayList<>();
        roomsLocation.add(new IntPair(840, 90)); //1. szoba
        roomsLocation.add(new IntPair(1190, 90)); //2. szoba
        roomsLocation.add(new IntPair(700, 220)); //3. szoba
        roomsLocation.add(new IntPair(990, 220)); //4. szoba
        roomsLocation.add(new IntPair(1330, 220)); //5. szoba
        roomsLocation.add(new IntPair(525, 340)); //6. szoba
        roomsLocation.add(new IntPair(700, 340)); //7. szoba
        roomsLocation.add(new IntPair(1190, 340)); //8. szoba
        roomsLocation.add(new IntPair(700, 460)); //9. szoba
        roomsLocation.add(new IntPair(990, 460)); //10. szoba
        roomsLocation.add(new IntPair(525, 590)); //11. szoba
        roomsLocation.add(new IntPair(840, 590)); //12. szoba
        roomsLocation.add(new IntPair(1190, 590)); //13. szoba

        //Vonalak, nyilak kirajzolása a szobák között
        JPanel labyrinthPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));
                
                //1. szoba
                g2d.drawLine(840, 130, 1190, 130);
                //2. szoba
                g2d.drawLine(1270, 130, 1370, 220);
                g2d.drawLine(1230, 170, 1230, 340);
                //3. szoba
                g2d.drawLine(700, 260, 605, 380);
                g2d.drawLine(740, 300, 740, 340);
                g2d.drawLine(780, 260, 990, 260);
                //4. szoba
                g2d.drawLine(1030, 300, 780, 500);
                //7. szoba
                g2d.drawLine(740, 420, 740, 460);
                //8. szoba
                g2d.drawLine(1230, 420, 1070, 500);
                //9. szoba
                g2d.drawLine(780, 500, 990, 500);
                g2d.drawLine(740, 540, 605, 630);
                //10. szoba
                g2d.drawLine(990, 500, 880, 590);
                //11. szoba
                g2d.drawLine(605, 630, 840, 630);

                //Nyilak feje (háromszögek) kirajzolása
                Polygon secondRoomArrow = new Polygon();
                secondRoomArrow.addPoint(1230, 340);
                secondRoomArrow.addPoint(1220, 330);
                secondRoomArrow.addPoint(1240, 330);
                g2d.fill(secondRoomArrow);
                Polygon thirdRoomArrow = new Polygon();
                thirdRoomArrow.addPoint(780, 260);
                thirdRoomArrow.addPoint(790, 250);
                thirdRoomArrow.addPoint(790, 270);
                g2d.fill(thirdRoomArrow);
                Polygon fourthRoomArrow = new Polygon();
                fourthRoomArrow.addPoint(780, 500);
                fourthRoomArrow.addPoint(794, 501);
                fourthRoomArrow.addPoint(782, 485);
                g2d.fill(fourthRoomArrow);
                Polygon seventhRoomArrow = new Polygon();
                seventhRoomArrow.addPoint(740, 420);
                seventhRoomArrow.addPoint(730, 430);
                seventhRoomArrow.addPoint(750, 430);
                g2d.fill(seventhRoomArrow);
                Polygon ninthRoomArrow = new Polygon();
                ninthRoomArrow.addPoint(605, 630);
                ninthRoomArrow.addPoint(619, 631);
                ninthRoomArrow.addPoint(607, 615);
                g2d.fill(ninthRoomArrow);
                Polygon tenthRoomArrow = new Polygon();
                tenthRoomArrow.addPoint(990, 500);
                tenthRoomArrow.addPoint(976, 501);
                tenthRoomArrow.addPoint(988, 516);
                g2d.fill(tenthRoomArrow);
                Polygon eleventhRoomArrow = new Polygon();
                eleventhRoomArrow.addPoint(840, 630);
                eleventhRoomArrow.addPoint(830, 620);
                eleventhRoomArrow.addPoint(830, 640);
                g2d.fill(eleventhRoomArrow);

                //Lila vonalak, nyilak hozzáadása
                g2d.setColor(new Color(102,0,153));
                //1. szoba
                g2d.drawLine(840, 130, 740, 220);
                g2d.drawLine(920, 130, 1030, 220);
                //5. szoba
                g2d.drawLine(1370, 300, 1270, 380);
                //6. szoba
                g2d.drawLine(605, 380, 700, 500);
                //12. szoba
                g2d.drawLine(920, 630, 1190, 630);

                Polygon firstRoomArrow = new Polygon();
                firstRoomArrow.addPoint(920, 130);
                firstRoomArrow.addPoint(934, 131);
                firstRoomArrow.addPoint(922, 145);
                g2d.fill(firstRoomArrow);
                Polygon fifthRoomArrow = new Polygon();
                fifthRoomArrow.addPoint(1370, 300);
                fifthRoomArrow.addPoint(1356, 301);
                fifthRoomArrow.addPoint(1368, 315);
                g2d.fill(fifthRoomArrow);
            }
        };

        labyrinthPanel.setLayout(null); // Kell a setBounds() használatához
        for (int i = 0; i < roomsLocation.size(); i++) {
            //Minden roomPanel fel lesz osztva 4 sorra, 3 oszlopra, ezeket eltároljuk, hogy később a szobákban lévő játékosokat,
            //szobák kapacitását könnyen változtathassuk.
            RoomPanel roomPanel = new RoomPanel(new JPanel());
            roomPanel.room.setLayout(new GridLayout(4, 3));
            roomPanel.room.setBounds(roomsLocation.get(i).x, roomsLocation.get(i).y, 80, 80);

            //Mergező szobákat kékre színezzük
            if (i == 1 || i == 10 || i == 12) roomPanel.room.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            else roomPanel.room.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            //A szobát 12 részre osztva, mindegyik részhez hozzáadunk egy JPanelt, amibe szöveget lehet majd rakni,
            //vagy a szélét színezni.
            for (int j = 0; j < 12; j++) {
                roomPanel.roomInfoPanels.add(new JPanel());
                if (j == 11) {
                    JLabel capacityLabel = new JLabel("4");
                    capacityLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    roomPanel.roomInfoPanels.get(j).add(capacityLabel);
                }
                roomPanel.room.add(roomPanel.roomInfoPanels.get(j));
            }
            roomPanels.add(roomPanel);
            frame.add(roomPanel.room);
            labyrinthPanel.add(roomPanel.room);
        }

        //Példa egy játékos hozzáadására, kijelölésének megjelenítését:
        roomPanels.get(0).roomInfoPanels.get(0).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        JLabel playerLabel = new JLabel("S1");
        playerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        roomPanels.get(0).roomInfoPanels.get(0).add(playerLabel);

        frame.add(labyrinthPanel);
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
