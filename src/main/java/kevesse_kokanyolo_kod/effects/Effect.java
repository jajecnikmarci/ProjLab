package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

/**
 * Effektek megvalósítására szolgáló osztály. Az effektumokat a játékosokra és szobákra lehet alkalmazni.
 */
public abstract class Effect implements TimerObserver {
    protected Timer timer;

    /**
     * Ha egy effekt aktiválása került már akkorr igaz
     */
    protected boolean active;
    /*
     * Az effektet figyelő osztály
     */
    EffectConsumedObserver observer;
    /**
     * Az effektum időtartama, ha van neki. Ha nincs akkor 0
     */
    private final int duration;
    /**
     * Az az item aminek a hatására az effekt létrejött
     */
    private final Item givenBy;

    protected Effect(Item givenBy, int duration, EffectConsumedObserver observer) {
        this.givenBy = givenBy;
        this.duration = duration;
        this.observer = observer;
    }

    /**
     * A már létrehozott effekt aktiválása, adott esetben ez hívja meg a tárgy use függvényét
     */
    public abstract void activate();

    /**
     * Az effekt aktívságának lekérő függvénye
     *
     * @return az effekt aktívságát adja vissza
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Az effekt hosszát lekérő függvény
     *
     * @return az effekt hosszát adja vissza
     */
    public int getDuration() {
        return duration;
    }

    public void timeIsUp() {
        Skeleton.startCall("PoisonImmunity.timeIsUp()");
        observer.effectConsumed(this);
        Skeleton.endCall();
    }

    public void cancelTimer() {
        Skeleton.startCall("Effect.cancelTimer()");
        timer.cancel();
        active = false;
        Skeleton.endCall();
    }

    public Item getItem() {
        return givenBy;
    }
}
