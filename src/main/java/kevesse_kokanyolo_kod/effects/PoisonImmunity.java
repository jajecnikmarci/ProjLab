package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;

/**
 * Az AcademicPlyer-ek számára méreg elleni védettséget biztosító hatás, ha aktív állapotban van.
 */
public class PoisonImmunity extends Effect {
    /**
     * Létrehozza a PoisonImmunity-t a paraméterül kapott értékekkel.
     */
    public PoisonImmunity(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }
    
    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject("PoisonImmunity");
        printer.printField("givenBy", builder.getInstanceName(this.givenBy));
        printer.printField("duration", this.duration);
        printer.printField("active", this.active);  
        printer.endPrintObject();      
    }
}
