package room;

import player.Player;
import skeleton.Skeleton;

/**
 *
 */
public class Door {
    /**
     *
     */
    private Room room1;

    /**
     *
     */
    private Room room2;

    /**
     *
     */
    private boolean room1Open;

    /**
     *
     */
    private boolean room2Open;

    /**
     *
     */
    private boolean visible;

    /**
     *
     */
    private boolean cursed;

    /**
     * @param room1
     * @param room2
     * @param room1Open
     * @param room2Open
     */
    public Door(Room room1, Room room2, boolean room1Open, boolean room2Open) {
        this.room1 = room1;
        this.room2 = room2;
        this.room1Open = room1Open;
        this.room2Open = room2Open;
    }

    /**
     * Segédfüggvény. Megmondja, hogy az ajtó room1 és room2 között van-e.
     */
    public boolean isBetween(Room room1, Room room2) {
        return (this.room1 == room1 && this.room2 == room2) || (this.room1 == room2 && this.room2 == room1);
    }

    /**
     * Amikor egy játékos megpróbál átmenni az ajtón, itt ellenőrzi az ajtó,
     * hogy átmehet-e. Az elégséges feltétele, hogy átmehessen a játékos az
     * ajtón: átmehet az ajtón abból a szobából, amelyikben a játékos van és
     * van még legalább 1 hely a szobában, amelyikbe menni próbál a játékos.
     * @param player
     * @return true, ha a játékos átment az ajtón, false egyébként (VÁLTOZÁS: void -> boolean)
     */
    public boolean goThrough(Player player) {
        Skeleton.startCall("Door.goThrough(Player)");
        if(player.getLocation() == room1 && room2Open && room2.canPlayerEnter()) {
            room1.removePlayer(player);
            room2.addPlayer(player);
            player.setLocation(room2);
            Skeleton.endCall("A játékos átment az ajtón.");
            return true;
        }
        else if(player.getLocation() == room2 && room1Open && room1.canPlayerEnter()) {
            room2.removePlayer(player);
            room1.addPlayer(player);
            player.setLocation(room1);
            Skeleton.endCall("A játékos átment az ajtón.");
            return true;
        }
        Skeleton.endCall("A játékos nem tudott átmenni az ajtón.");
        return false;
    }

    /**
     * @return
     */
    public Room getRoom1() {
        return room1;
    }

    /**
     * @param room1
     */
    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    /**
     * @return
     */
    public Room getRoom2() {
        return room2;
    }

    /**
     * @param room2
     */
    public void setRoom2(Room room2) {
    }
}
