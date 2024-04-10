package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * Mérgezés hatású effektum.
 */
public class PoisonEffect extends RoomEffect {

    public PoisonEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * 
     * @param academicPerson a játékos, akit érint az effektum
     */
    @Override
    public void affect(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("PoisonEffect.affect(Professor)");
        academicPerson.poison();
        SkeletonMenu.endCall();
    }


    


    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("PoisonEffect");
        printer.printField("active", this.active);  
        printer.endPrintObject();      
    }
}
