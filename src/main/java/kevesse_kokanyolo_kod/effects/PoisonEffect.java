package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * A szoba mérgezés hatású effektuma.
 */
public class PoisonEffect extends RoomEffect {
    /**
     * Létrehozza a PoisonEffect-et a paraméterül kapott értékekkel.
     */
    public PoisonEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * A mérgezés effekt mérgezés hatást gyakorol a paraméterül kapott
     * játékosra.
     * @param academicPerson a játékos, akit érint az effektum
     */
    @Override
    public void affect(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("PoisonEffect.affect(Professor)");
        academicPerson.poison();
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject("PoisonEffect");
        printer.printField("givenBy", builder.getInstanceName(this.givenBy));
        printer.printField("duration", this.duration);        
        printer.printField("active", this.active);  
        printer.endPrintObject();      
    }
}
