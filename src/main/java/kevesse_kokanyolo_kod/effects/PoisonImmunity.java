package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;

/**
 * A méreg elleni védettséget biztosító hatás.
 */
public class PoisonImmunity extends Effect {

    public PoisonImmunity(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * A méreg immunitás aktiválásakor, egy időzítő indul el,
     * az időzítő lejártáig a játékosnak mérgezés elleni
     * védettséget nyújt.
     */
    @Override
    public void activate() {
        SkeletonMenu.startCall("PoisonImmunity.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(getDuration());

        SkeletonMenu.endCall();
    }
    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("PoisonImmunity");
        printer.printField("active", this.active);  
        printer.endPrintObject();      
    }
}
