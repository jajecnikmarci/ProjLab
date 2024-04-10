package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.room.Room;

public class Cleaner extends Person{
    public Cleaner(Room room) {
        super(room);
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

    @Override
    protected void callOnEnter(Room room) {
        room.onEnter(this);
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder){
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("location", builder.getInstanceName(this.location));
        printer.endPrintObject();
    }
}
