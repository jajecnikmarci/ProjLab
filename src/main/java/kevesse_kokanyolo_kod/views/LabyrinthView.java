package kevesse_kokanyolo_kod.views;

import kevesse_kokanyolo_kod.controllers.Controller;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabyrinthView extends JPanel {
    private List<RoomPanel> roomPanels = new ArrayList<>();
    Controller controller;

    public LabyrinthView(Controller controller) {
        setLayout(null); // Kell a setBounds() használatához
        this.controller = controller;
        createLabyrinth();
    }

    /**
     * kirajzolja a teljes labirintust
     * 
     * @param labyrinth
     */
    public void display(LabyrinthBuilder labyrinthBuilder) {

    }

    /**
     * Kirajzolja a magadott szobát a megadott helyre
     * 
     * @param room     a szoba, amit kirajzol
     * @param position a pozíció, ahova kirajzolja
     */
    private void createRoomPanel(Room room, IntPair position) {
        RoomPanel roomPanel = new RoomPanel();
        roomPanel.setLayout(new GridLayout(4, 3));
        roomPanel.setBounds(position.x, position.y, 80, 80);

        // Mergező szobákat kékre színezzük TODO: kék? és a többi hatás?
        if (room.isPoisonous())
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        else{
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        }
        
        for (int j = 0; j < 12; j++) {
            roomPanel.slots.add(new JPanel());
            if (j == 11) {
                JLabel capacityLabel = new JLabel(String.valueOf(room.getCapacity()));
                capacityLabel.setFont(new Font("Arial", Font.BOLD, 12));
                roomPanel.slots.get(j).add(capacityLabel);
            }
            roomPanel.add(roomPanel.slots.get(j));
        }
        add(roomPanel);
    }

    /**
     * Kirajzolja az ajtót a két végpontja közé.
     * 
     * @param door a kirajzolandó ajtó
     */
    private void drawDoor(Door door, Graphics2D g2d) {
        if (!door.isVisible()) return;
        Color savedColor = g2d.getColor();
        var roomLocations = controller.getLabyrinthBuilder().getRoomLocations();
        IntPair room1Location = roomLocations.get(door.getRoom1());
        IntPair room2Location = roomLocations.get(door.getRoom2());
        IntPair offsets[] = controller.getLabyrinthBuilder().getDoorOffsets(door);
        IntPair room1Offset = offsets[0];
        IntPair room2Offset = offsets[1];

        // scale offset 
        room1Offset.x *= roomWidth / 2; room1Offset.y *= roomHeight / 2;
        room2Offset.x *= roomWidth / 2; room2Offset.y *= roomHeight / 2;
        // offset is given from room center
        room1Offset.x += roomWidth / 2; room1Offset.y += roomHeight / 2;
        room2Offset.x += roomWidth / 2; room2Offset.y += roomHeight / 2;

        if(door.isCursed()) { 
            g2d.setColor(Color.MAGENTA);
        } else {
            g2d.setColor(Color.BLACK);
        }

        g2d.drawLine(room1Location.x + room1Offset.x, room1Location.y + room1Offset.y,
                room2Location.x + room2Offset.x, room2Location.y + room2Offset.y);

        // Calculate arrow
        
        IntPair r2 = new IntPair(room2Location.x + room2Offset.x, room2Location.y + room2Offset.y);
        IntPair r1 = new IntPair(room1Location.x + room1Offset.x, room1Location.y + room1Offset.y);
            
        IntPair v = r1.sub(r2); // r1 - r2 
        double arrowSize = 10;
        v = v.scale(arrowSize);
        IntPair n1 = new IntPair(v.y, -v.x); 
        IntPair n2 = new IntPair(-v.y, v.x); 

        if(!door.isRoom1Open()) { // r1 --> r2
            var p1 = r1.sub(v).add(n1);
            var p2 = r1.sub(v).add(n2);
            Polygon arrow = new Polygon();
            arrow.addPoint(r1.x, r1.y);
            arrow.addPoint(p1.x, p1.y); // r2 - v + n1
            arrow.addPoint(p2.x, p2.y); // r2 - v + n2 
            g2d.fillPolygon(arrow);
            
        } else if(!door.isRoom2Open()) { // r2 --> r1
            v = new IntPair(-v.x, -v.y);
            var p1 = r2.sub(v).add(n1);
            var p2 = r2.sub(v).add(n2);
            Polygon arrow = new Polygon();
            arrow.addPoint(r2.x, r2.y);
            arrow.addPoint(p1.x, p1.y); // r1 - v + n1
            arrow.addPoint(p2.x, p2.y); // r1 - v + n2 
            g2d.fillPolygon(arrow);
        }

        g2d.setColor(savedColor);
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
        private List<JPanel> slots = new ArrayList<>();

        public RoomPanel() {
        }
    }

    private final int roomWidth = 80;
    private final int roomHeight = 80;
    // Szobák megjelenítése
    private void createLabyrinth() {
        //ez már lackó féle
        for (var roomEntry : controller.getRooms().entrySet()) {
            IntPair position = controller.getRoomLocations().get(roomEntry.getValue());
            createRoomPanel(roomEntry.getValue(), position);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        for (var doorEntry : controller.getLabyrinthBuilder().getDoors().entrySet()) {
            drawDoor(doorEntry.getValue(), g2d);
        }
    }
}
