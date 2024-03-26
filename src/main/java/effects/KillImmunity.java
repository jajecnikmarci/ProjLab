package main.java.effects;

import main.java.items.Item;

import main.java.skeleton.Skeleton;

/**
 * Olyan hatás, ami megvédi a játékost a megöléstől.
 */
public class KillImmunity extends Effect {
    public KillImmunity(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration,observer);
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
