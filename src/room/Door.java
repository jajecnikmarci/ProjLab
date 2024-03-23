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
    Door(Room room1, Room room2, boolean room1Open, boolean room2Open) {
    }

    /**
     * @param player
     */
    void goThrough(Player player) {
        System.out.println("Door.goThrough(Player)");
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
