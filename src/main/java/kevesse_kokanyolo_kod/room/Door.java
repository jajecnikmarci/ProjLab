package kevesse_kokanyolo_kod.room;

import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

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
     * @param player a játékos, aki átmegy az ajtón
     * @return true, ha a játékos átment az ajtón, false egyébként (VÁLTOZÁS: void -> boolean)
     */
    public boolean goThrough(Player player) {
        Skeleton.startCall("Door.goThrough(Player)");
        if (player.getLocation() == room1 && room2Open && room2.canPlayerEnter()) {
            room1.removePlayer(player);
            room2.addPlayer(player);
            player.setLocation(room2);
            Skeleton.endCall("A játékos átment az ajtón.");
            return true;
        } else if (player.getLocation() == room2 && room1Open && room1.canPlayerEnter()) {
            room2.removePlayer(player);
            room1.addPlayer(player);
            player.setLocation(room1);
            Skeleton.endCall("A játékos átment az ajtón.");
            return true;
        }
        Skeleton.endCall("A játékos nem tudott átmenni az ajtón.");
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
