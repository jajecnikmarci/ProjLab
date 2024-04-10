package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;

/**
 * Olyan hatás, ami megvédi a Student-et a Professor-ok támadásától (lélekszipolyozásától), ha aktiválva van.
 */
public class KillImmunity extends Effect {
    /**
     * Létrehozza a KillImmunity-t a paraméterül kapott értékekkel.
     */
    public KillImmunity(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject("KillImmunity");
        printer.printField("givenBy", builder.getInstanceName(this.givenBy));
        printer.printField("duration", builder.getInstanceName(this.givenBy));
        printer.printField("active", this.active);  
        printer.endPrintObject();
    }
}
