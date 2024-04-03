package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.room.Room;

public class Cleaner extends Person{
    public Cleaner(Room r) {
        super(r);
    }

    @Override
    public void meet(Student student) {
        if (!student.isStunned()) {
            student.leaveRoom();
        }
    }

    @Override
    public void meet(Professor professor, Room room) {
        if (!professor.isStunned()) {
            professor.leaveRoom();
        }
    }

    @Override
    public void meet(Cleaner cleaner, Room room) {
        cleaner.leaveRoom();
    }

    @Override
    public void poison() {}


}
