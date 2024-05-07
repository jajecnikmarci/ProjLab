package kevesse_kokanyolo_kod.observer;

import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

public interface IRoomObservable {
    void notifyRoomSplit(Room room, Door door);
    void notifyRoomsMerged(Room room, Door door);
    void addObserver(RoomObserver observer);
}
