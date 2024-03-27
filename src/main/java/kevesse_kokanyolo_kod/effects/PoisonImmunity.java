package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

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
        Skeleton.startCall("PoisonImmunity.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(getDuration());

        Skeleton.endCall();
    }
}
