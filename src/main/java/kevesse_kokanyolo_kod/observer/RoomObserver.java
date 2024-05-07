package kevesse_kokanyolo_kod.observer;

import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

public interface RoomObserver {
    /**
     * Meghatározza a műveleteket, amelyeket a megfigyelőnek végre kell hajtania, amikor egy
     * szoba kettéosztódik, az interfészből származó osztálynak kell megvalósítani.
     */
    void roomSplit(Room room, Door door);

    /**
     * Meghatározza a műveleteket, amelyeket a megfigyelőnek végre kell hajtania, amikor két
     * szoba összeolvad, az interfészből származó osztálynak kell megvalósítani.
     */
    void roomsMerged(Room room, Door door);
}
