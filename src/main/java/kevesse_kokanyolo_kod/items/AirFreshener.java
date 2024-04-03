package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

public class AirFreshener extends Item{
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("AirFreshener.use(Room, Player)");
        room.clearPoisonEffects();
        academicPerson.removeItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("AirFreshener.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("AirFreshener");
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }
}
