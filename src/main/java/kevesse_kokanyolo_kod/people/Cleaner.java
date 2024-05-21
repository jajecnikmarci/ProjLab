package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.room.Room;

public class Cleaner extends Person {
    /**
     * Létrehozza a takarítót.
     * 
     * @param room a szoba, ahova a takarító kerül
     */
    public Cleaner(Room room) {
        super(room);
    }

    /**
     * Ha a takarító találkozik egy hallgatóval, aki nem ájult, a hallgatót kitessékeli a szobából.
     * 
     * @param student a hallgató, aki találkozik a takarítóval
     */
    @Override
    public void meet(Student student) {
        if (!student.isStunned()) {
            student.leaveRoom();
        }
    }

    /**
     * Ha a takarító találkozik egy professzorral, aki nem ájult, a professzort kitessékeli a szobából.
     * 
     * @param professor a professzor, aki találkozik a takarítóval
     */
    @Override
    public void meet(Professor professor) {
        if (!professor.isStunned()) {
            professor.leaveRoom();
        }
    }

    /**
     * Ha a takarító találkozik egy másik takarítóval, kiküldi a szobából.
     * 
     * @param cleaner a takarító, aki találkozik a takarítóval
     */
    @Override
    public void meet(Cleaner cleaner) {
        cleaner.leaveRoom();
    }

    /**
     * A takarító nem mérgeződhet meg (Amint egy mérgező szobába lép, megtisztítja a szobát)
     * Ez a metódus üres.
     */
    @Override
    public void poison() {}

    /**
     * Meghívja a szoba onEnter metódusát a takarítóval, mint paraméterrel..
     * 
     * @param room a szoba, ahova belép a takarító
     */
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
