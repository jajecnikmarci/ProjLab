package kevesse_kokanyolo_kod.room;

import java.util.ArrayList;
import java.util.List;

public class RoomObservable {
    private List<RoomObserver> observers = new ArrayList<>();

    public void addObserver(RoomObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RoomObserver observer) {
        observers.remove(observer);
    }

    public void notifyRoomSplit(Room room,Door door) {
        for (RoomObserver observer : observers) {
            observer.roomSplit(room, door);
        }
    }

    public void notifyRoomsMerged(Room room, Door door) {
        for (RoomObserver observer : observers) {
            observer.roomsMerged(room, door);
        }
    }
}
