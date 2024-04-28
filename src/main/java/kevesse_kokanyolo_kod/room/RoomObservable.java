package kevesse_kokanyolo_kod.room;

import java.util.ArrayList;
import java.util.List;

public class RoomObservable {
    /**
     * Az Observer-ek listája
     */
    private List<RoomObserver> observers = new ArrayList<>();

    /**
     * Metódusok az Observer-ek regisztrálásához és eltávolításához
     */
    public void addObserver(RoomObserver observer) {
        observers.add(observer);
    }

    /**
     * A játék menete közben figyeli, a szoba osztódott-e.
     */
    public void notifyRoomSplit(Room room,Door door) {
        for (RoomObserver observer : observers) {
            observer.roomSplit(room, door);
        }
    }

    /**
     * A játék menete közben figyeli, a szobák egyesültek-e.
     */
    public void notifyRoomsMerged(Room room, Door door) {
        for (RoomObserver observer : observers) {
            observer.roomsMerged(room, door);
        }
    }
}
