package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

/**
 * Olyan hatás, ami megvédi a játékost a megöléstől.
 */
public class KillImmunity extends Effect {
    public KillImmunity(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    @Override
    public void activate() {
        Skeleton.startCall("KillImmunity.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(getDuration());
        Skeleton.endCall();
    }
}
