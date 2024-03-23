package room;

import player.Player;

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
     * Amikor egy játékos megpróbál átmenni az ajtón, itt ellenőrzi az ajtó,
     * hogy átmehet-e. Az elégséges feltétele, hogy átmehessen a játékos az
     * ajtón: átmehet az ajtón abból a szobából, amelyikben a játékos van és
     * van még legalább 1 hely a szobában, amelyikbe menni próbál a játékos.
     * @param player
     */
    public void goThrough(Player player) {
        System.out.println("Door.goThrough(Player)");
        if(player.getLocation() == room1 && room2Open && room2.canPlayerGoIn()) {
            room1.removePlayer(player);
            room2.addPlayer(player);
            player.setLocation(room2);
        }
        else if(player.getLocation() == room2 && room1Open && room1.canPlayerGoIn()) {
            room2.removePlayer(player);
            room1.addPlayer(player);
            player.setLocation(room1);
        }
        //else { } //Ha játékos nem tud a másik szobába menni
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
