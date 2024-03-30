package kevesse_kokanyolo_kod.player;

import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.room.Room;

public class Cleaner extends Person{
    protected Cleaner(Room r) {
        super(r);
    }

    @Override
    public void meet(Student student) {

    }

    @Override
    public void meet(Professor professor, Room room) {

    }

    @Override
    public void poison() {}


}
