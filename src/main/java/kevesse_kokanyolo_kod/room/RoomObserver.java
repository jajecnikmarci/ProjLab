package kevesse_kokanyolo_kod.room;

public interface RoomObserver {
    void roomSplit(Room room, Door door);
    void roomsMerged(Room room, Door door);
}
