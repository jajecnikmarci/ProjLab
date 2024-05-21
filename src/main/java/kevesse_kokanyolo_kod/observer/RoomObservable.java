package kevesse_kokanyolo_kod.observer;

import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomObservable implements IRoomObservable {
    /**
     * Az Observer-ek listája
     */
    private List<RoomObserver> observers = new ArrayList<>();

    /**
     * Lehetővé teszi a megfigyelők hozzáadását a szoba objektumhoz.
     */
    public void addObserver(RoomObserver observer) {
        observers.add(observer);
    }

    /**
     * Értesíti a feliratkozott osztályokat a szobák osztódásáról.
     */
    public void notifyRoomSplit(Room room, Door door) {
        for (RoomObserver observer : observers) {
            observer.roomSplit(room, door);
        }
    }

    /**
     * Értesíti a feliratkozott osztályokat a szobák egyesüléséről.
     */
    public void notifyRoomsMerged(Room room, ArrayList<Door> doors) {
        for (RoomObserver observer : observers) {
            observer.roomsMerged(room, doors);
        }
    }
}
