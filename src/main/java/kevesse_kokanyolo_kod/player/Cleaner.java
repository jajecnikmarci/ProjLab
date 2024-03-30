package kevesse_kokanyolo_kod.player;

import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.room.Room;

public class Cleaner extends Person{
    protected Cleaner(Room r) {
        super(r);
    }

    @Override
    public void meet(Student student) {
        if (!student.isStunned()) {
            student.hasToLeaveRoom();
        }
    }

    @Override
    public void meet(Professor professor, Room room) {
        if (!professor.isStunned()) {
            professor.hasToLeaveRoom();
        }
    }

    @Override
    public void meet(Cleaner cleaner, Room room) {
        cleaner.hasToLeaveRoom();
    }

    @Override
    public void poison() {}


}
