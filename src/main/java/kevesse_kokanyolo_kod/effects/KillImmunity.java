package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;

/**
 * Olyan hatás, ami megvédi a játékost a megöléstől.
 */
public class KillImmunity extends Effect {
    public KillImmunity(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    @Override
    public void activate() {
        SkeletonMenu.startCall("KillImmunity.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(getDuration());
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("KillImmunity");
        printer.printField("active", this.active);  
        printer.endPrintObject();      
    }
}
