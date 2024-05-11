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
    private JPanel labyrinthPanel;
    private List<RoomPanel> roomPanels = new ArrayList<>();

    public GameWindow() {
        frame = new JFrame("Labyrinth");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        createLines();
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

    //Egy szobát tárol, ami fel van osztva 4 sorra, 3 oszlopra, ezekben a részekben jelennek meg a játékosok, szoba kapacitása
    public class RoomPanel {
        public JPanel room;
        private List<JPanel> roomInfoPanels = new ArrayList<>();

        public RoomPanel(JPanel room) {
            this.room = room;
        }
    }

    //Szobák megjelenítése
    private void createLabyrinth() {
        //Szobák helyének meghatározása, IntPair osztályt emiatt hoztam létre
        List<IntPair> roomsLocation = new ArrayList<>();
        int[] roomsLocations = { 840, 90, 1190, 90, 700, 220, 990, 220, 1330, 220, 525,
                340, 700, 340, 1190, 340, 700, 460, 990, 460, 525, 590, 840, 590, 1190, 590 };

        for (int i = 0; i < roomsLocations.length; i++) if(i % 2 == 1) roomsLocation.add(new IntPair(roomsLocations[i-1], roomsLocations[i]));

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

    //A bal alsó sarokban lévő játékos információt tároló részt jeleníti meg
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

    //Középen alul lévő Inventory információt tároló részt jeleníti meg
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

    //Jobb alul lévő szobában lévő tárgyakat tároló részt jeleníti meg
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

    //A szobák közötti vonalakat, nyilakat rajzolja ki
    private void createLines() {
        labyrinthPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));

                //Fekete vonalak pozíciói
                int[] blackRoomLines = { 840, 130, 1190, 130, 1270, 130, 1370, 220, 1230, 170, 1230, 340, 700, 260, 605,
                        380, 740, 300, 740, 340, 780, 260, 990, 260, 1030, 300, 780, 500, 740, 420, 740, 460, 1230,
                        420, 1070, 500, 780, 500, 990, 500, 740, 540, 605, 630, 990, 500, 880, 590, 605, 630, 840, 630 };

                //Fekete vonalak kirajzolása
                for (int i = 1; i < blackRoomLines.length + 1; i++) if(i % 4 == 0) g2d.drawLine(blackRoomLines[i-4], blackRoomLines[i-3], blackRoomLines[i-2], blackRoomLines[i-1]);

                int[] arrows = { 1230, 340, 1220, 330, 1240, 330, 780, 260, 790, 250, 790, 270, 780,
                        500, 794, 501, 782, 485, 740, 420, 730, 430, 750, 430, 605, 630, 619, 631, 607,
                        615, 990, 500, 976, 501, 988, 516, 840, 630, 830, 620, 830, 640 };

                //Fekete nyilak feje (háromszögek) kirajzolása
                for (int i = 1; i < arrows.length + 1; i++) {
                    if(i % 6 == 0) {
                        Polygon arrow = new Polygon();
                        arrow.addPoint(arrows[i-6], arrows[i-5]);
                        arrow.addPoint(arrows[i-4], arrows[i-3]);
                        arrow.addPoint(arrows[i-2], arrows[i-1]);
                        g2d.fill(arrow);
                    }
                }

                g2d.setColor(new Color(102,0,153));
                int[] purpleRoomLines = { 840, 130, 740, 220, 920, 130, 1030, 220, 1370,
                        300, 1270, 380, 605, 380, 700, 500, 920, 630, 1190, 630 };

                //Lila vonalak kirajzolása
                for (int i = 1; i < purpleRoomLines.length + 1; i++) if(i % 4 == 0) g2d.drawLine(purpleRoomLines[i-4], purpleRoomLines[i-3], purpleRoomLines[i-2], purpleRoomLines[i-1]);

                //Lila nyilak feje (háromszögek) kirajzolása
                int[] purpleArrows = { 920, 130, 934, 131, 922, 145, 1370, 300, 1356, 301, 1368, 315 };
                for (int i = 1; i < purpleArrows.length + 1; i++) {
                    if(i % 6 == 0) {
                        Polygon arrow = new Polygon();
                        arrow.addPoint(purpleArrows[i-6], purpleArrows[i-5]);
                        arrow.addPoint(purpleArrows[i-4], purpleArrows[i-3]);
                        arrow.addPoint(purpleArrows[i-2], purpleArrows[i-1]);
                        g2d.fill(arrow);
                    }
                }
            }
        };
    }
}
