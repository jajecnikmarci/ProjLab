package kevesse_kokanyolo_kod.room;

public interface RoomObserver {
    /**
     * Amikor egy szoba osztódik, ez a függvény hívódik meg.
     */
    void roomSplit(Room room, Door door);

    /**
     * Amikor egy szoba egyesül, ez a függvény hívódik meg.
     */
    void roomsMerged(Room room, Door door);
}
