package kevesse_kokanyolo_kod.views;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

import java.awt.*;

public class LabyrinthView {
    /**
     * kirajzolja a teljes labirintust
     * @param labyrinth
     */
    public void display(LabyrinthBuilder labyrinth) {
    }

    /**
     * Kirajzolja a magadott szobát a megadott helyre
     * @param room a szoba, amit kirajzol
     * @param position a pozíció, ahova kirajzolja
     */
    private void drawRoom(Room room, Point position) {
    }

    /**
     * Kirajzolja az ajtót a két végpontja közé.
     * @param door a kirajzolandó ajtó
     */
    private void drawDoor(Door door) {
    }

    /**
     *     Egy játékosra kattintva kijelölt állapotba helyezi a kattintott
     *     játékost, megjelennek a játékoshoz tartozó információk, az elérhető cselekvések,
     *     amiket a játékossal csinálhatunk (tárgy használata, elejtése, tárgy felvétele). Ha ki van
     *     jelölve egy játékos és egy szobára kattintunk a programban, a játékost megpróbálja
     *     átküldeni a kattintott szobába, ha nincs kijelölve játékos, nem történik semmi.
     */
    private void handleClick() {
    }
}
