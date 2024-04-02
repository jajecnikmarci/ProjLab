package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;
import kevesse_kokanyolo_kod.room.Room;

public class AirFreshener extends Item{
    @Override
    public void use(Room room, AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("AirFreshener.use(Room, Player)");
        room.clearPoisonEffects();
        acamedicPerson.removeItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("AirFreshener.accept(Player)");
        acamedicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("AirFreshener");
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }
}
