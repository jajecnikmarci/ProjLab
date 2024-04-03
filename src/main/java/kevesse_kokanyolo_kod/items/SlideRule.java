package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class SlideRule extends Item {

    /**
     * A logarléc használata. Felvételekor véget ér a játék így nem felhasználható
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
    }

    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("SlideRule.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("SlideRule");
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }
}
