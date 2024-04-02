package kevesse_kokanyolo_kod.room;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;

/**
 * Az ajtókat reprezentáló osztály.
 */
public class Door {
    /**
     * Az ajtó egyik szobája.
     */
    private Room room1;

    /**
     * Az ajtó másik szobája.
     */
    private final Room room2;

    /**
     * Az ajtó első szobából a másodikba nyitva van-e.
     */
    private final boolean room1Open;

    /**
     * Az ajtó második szobából az elsőbe nyitva van-e.
     */
    private final boolean room2Open;

    /**
     * Az ajtó látható-e.
     */
    private boolean visible;

    /**
     * Az ajtó el van-e átkozva.
     */
    private boolean cursed;

    public Door(Room room1, Room room2, boolean room1Open, boolean room2Open) {
        this.room1 = room1;
        this.room2 = room2;
        this.room1Open = room1Open;
        this.room2Open = room2Open;
    }

    /**
     * Segédfüggvény. Megmondja, hogy az ajtó room1 és room2 között van-e.
     *
     * @param room1 az egyik szoba
     * @param room2 a másik szoba
     */
    public boolean isBetween(Room room1, Room room2) {
        return (this.room1 == room1 && this.room2 == room2) || (this.room1 == room2 && this.room2 == room1);
    }

    /**
     * Amikor egy játékos megpróbál átmenni az ajtón, itt ellenőrzi az ajtó,
     * hogy átmehet-e. Az elégséges feltétele, hogy átmehessen a játékos az
     * ajtón: átmehet az ajtón abból a szobából, amelyikben a játékos van és
     * van még legalább 1 hely a szobában, amelyikbe menni próbál a játékos.
     *
     * @param acamedicPerson a játékos, aki átmegy az ajtón
     * @return true, ha a játékos átment az ajtón, false egyébként (VÁLTOZÁS: void -> boolean)
     */
    public boolean goThrough(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Door.goThrough(Player)");
        if (acamedicPerson.getLocation() == room1 && room2Open && room2.canPlayerEnter()) {
            room1.removePlayer(acamedicPerson);
            room2.addPlayer(acamedicPerson);
            acamedicPerson.setLocation(room2);
            SkeletonMenu.endCall("A játékos átment az ajtón.");
            return true;
        } else if (acamedicPerson.getLocation() == room2 && room1Open && room1.canPlayerEnter()) {
            room2.removePlayer(acamedicPerson);
            room1.addPlayer(acamedicPerson);
            acamedicPerson.setLocation(room1);
            SkeletonMenu.endCall("A játékos átment az ajtón.");
            return true;
        }
        SkeletonMenu.endCall("A játékos nem tudott átmenni az ajtón.");
        return false;
    }

    public Room getRoom1() {
        return room1;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public void setRoom2(Room room2) {
    }
}
