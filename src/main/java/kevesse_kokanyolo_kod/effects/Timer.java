package kevesse_kokanyolo_kod.effects;

import java.util.TimerTask;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;

/**
 * Időzítő osztály. Elindítása után a megadott időtartamig várakozik, majd értesíti a hívó objektumot.
 */
public class Timer {

    /**
     * Az időzítőt tulajdonló Item.
     */
    private final Effect ownerEffect;

    /**
     * Az időzítést megvalósító java.util.Timer.
     */
    private java.util.Timer innerTimer;

    public Timer(Effect ownerEffect) {
        this.ownerEffect = ownerEffect;
    }

    /**
     * Az időzítő elindítását megvalósító metódus.
     *
     * @param durationInSeconds Az időzítő időtartama.
     */
    void start(int durationInSeconds) {
        SkeletonMenu.startCall("Timer.start(int)");
        innerTimer = new java.util.Timer();
        innerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ownerEffect.timeIsUp();
            }
        }, durationInSeconds /*1000L*/); //TODO *1000L élesben
        SkeletonMenu.endCall();
    }

    /**
     * Az időzítő leállítását megvalósító metódus.
     */
    public void cancel() {
        SkeletonMenu.startCall("Timer.cancel()");
        innerTimer.cancel();
        SkeletonMenu.endCall();
    }
}
