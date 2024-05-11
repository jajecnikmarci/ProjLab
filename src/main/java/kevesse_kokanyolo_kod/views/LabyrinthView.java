package kevesse_kokanyolo_kod.views;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabyrinthView extends JPanel {
    private List<RoomPanel> roomPanels = new ArrayList<>();

    public LabyrinthView() {
        setLayout(null); // Kell a setBounds() használatához
        createLabyrinth();
    }

    /**
     * kirajzolja a teljes labirintust
     * 
     * @param labyrinth
     */
    public void display(LabyrinthBuilder labyrinth) {

    }

    /**
     * Kirajzolja a magadott szobát a megadott helyre
     * 
     * @param room     a szoba, amit kirajzol
     * @param position a pozíció, ahova kirajzolja
     */
    private void drawRoom(Room room, Point position) {
    }

    /**
     * Kirajzolja az ajtót a két végpontja közé.
     * 
     * @param door a kirajzolandó ajtó
     */
    private void drawDoor(Door door) {
    }

    /**
     * Egy játékosra kattintva kijelölt állapotba helyezi a kattintott játékost,
     * megjelennek a játékoshoz tartozó információk, az elérhető cselekvések, amiket
     * a játékossal csinálhatunk (tárgy használata, elejtése, tárgy felvétele). Ha
     * ki van jelölve egy játékos és egy szobára kattintunk a programban, a játékost
     * megpróbálja átküldeni a kattintott szobába, ha nincs kijelölve játékos, nem
     * történik semmi.
     */
    private void handleClick() {
    }

    // Egy szobát tárol, ami fel van osztva 4 sorra, 3 oszlopra, ezekben a részekben
    // jelennek meg a játékosok, szoba kapacitása
    public class RoomPanel extends JPanel { 
        public JPanel room;
        private List<JPanel> slots = new ArrayList<>();

        public RoomPanel(JPanel room) {
            this.room = room;
        }
    }
    
    public class IntPair {
        public int x;
        public int y;

        public IntPair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Szobák megjelenítése
    private void createLabyrinth() {
        // Szobák helyének meghatározása, IntPair osztályt emiatt hoztam létre
        List<IntPair> roomsLocation = new ArrayList<>();
        int[] roomsLocations = { 840, 90, 1190, 90, 700, 220, 990, 220, 1330, 220, 525, 340, 700, 340, 1190, 340, 700,
                460, 990, 460, 525, 590, 840, 590, 1190, 590 };

        for (int i = 0; i < roomsLocations.length; i++)
            if (i % 2 == 1)
                roomsLocation.add(new IntPair(roomsLocations[i - 1], roomsLocations[i]));

        for (int i = 0; i < roomsLocation.size(); i++) {
            // Minden roomPanel fel lesz osztva 4 sorra, 3 oszlopra, ezeket eltároljuk, hogy
            // később a szobákban lévő játékosokat,
            // szobák kapacitását könnyen változtathassuk.
            RoomPanel roomPanel = new RoomPanel(new JPanel());
            roomPanel.room.setLayout(new GridLayout(4, 3));
            roomPanel.room.setBounds(roomsLocation.get(i).x, roomsLocation.get(i).y, 80, 80);

            // Mergező szobákat kékre színezzük
            if (i == 1 || i == 10 || i == 12)
                roomPanel.room.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            else
                roomPanel.room.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // A szobát 12 részre osztva, mindegyik részhez hozzáadunk egy JPanelt, amibe
            // szöveget lehet majd rakni,
            // vagy a szélét színezni.
            // TODO: --> RoomPanel
            for (int j = 0; j < 12; j++) {
                roomPanel.slots.add(new JPanel());
                if (j == 11) {
                    JLabel capacityLabel = new JLabel("4");
                    capacityLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    roomPanel.slots.get(j).add(capacityLabel);
                }
                roomPanel.room.add(roomPanel.slots.get(j));
            }
            roomPanels.add(roomPanel);
            add(roomPanel.room);
        }

        // Példa egy játékos hozzáadására, kijelölésének megjelenítését:
        roomPanels.get(0).slots.get(0).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        JLabel playerLabel = new JLabel("S1");
        playerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        roomPanels.get(0).slots.get(0).add(playerLabel);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));

        // Fekete vonalak pozíciói
        int[] blackRoomLines = { 840, 130, 1190, 130, 1270, 130, 1370, 220, 1230, 170, 1230, 340, 700, 260, 605, 380,
                740, 300, 740, 340, 780, 260, 990, 260, 1030, 300, 780, 500, 740, 420, 740, 460, 1230, 420, 1070, 500,
                780, 500, 990, 500, 740, 540, 605, 630, 990, 500, 880, 590, 605, 630, 840, 630 };

        // Fekete vonalak kirajzolása
        for (int i = 1; i < blackRoomLines.length + 1; i++)
            if (i % 4 == 0)
                g2d.drawLine(blackRoomLines[i - 4], blackRoomLines[i - 3], blackRoomLines[i - 2],
                        blackRoomLines[i - 1]);

        int[] arrows = { 1230, 340, 1220, 330, 1240, 330, 780, 260, 790, 250, 790, 270, 780, 500, 794, 501, 782, 485,
                740, 420, 730, 430, 750, 430, 605, 630, 619, 631, 607, 615, 990, 500, 976, 501, 988, 516, 840, 630, 830,
                620, 830, 640 };

        // Fekete nyilak feje (háromszögek) kirajzolása
        for (int i = 1; i < arrows.length + 1; i++) {
            if (i % 6 == 0) {
                Polygon arrow = new Polygon();
                arrow.addPoint(arrows[i - 6], arrows[i - 5]);
                arrow.addPoint(arrows[i - 4], arrows[i - 3]);
                arrow.addPoint(arrows[i - 2], arrows[i - 1]);
                g2d.fill(arrow);
            }
        }

        g2d.setColor(new Color(102, 0, 153));
        int[] purpleRoomLines = { 840, 130, 740, 220, 920, 130, 1030, 220, 1370, 300, 1270, 380, 605, 380, 700, 500,
                920, 630, 1190, 630 };

        // Lila vonalak kirajzolása
        for (int i = 1; i < purpleRoomLines.length + 1; i++)
            if (i % 4 == 0)
                g2d.drawLine(purpleRoomLines[i - 4], purpleRoomLines[i - 3], purpleRoomLines[i - 2],
                        purpleRoomLines[i - 1]);

        // Lila nyilak feje (háromszögek) kirajzolása
        int[] purpleArrows = { 920, 130, 934, 131, 922, 145, 1370, 300, 1356, 301, 1368, 315 };
        for (int i = 1; i < purpleArrows.length + 1; i++) {
            if (i % 6 == 0) {
                Polygon arrow = new Polygon();
                arrow.addPoint(purpleArrows[i - 6], purpleArrows[i - 5]);
                arrow.addPoint(purpleArrows[i - 4], purpleArrows[i - 3]);
                arrow.addPoint(purpleArrows[i - 2], purpleArrows[i - 1]);
                g2d.fill(arrow);
            }
        }
    }
}
