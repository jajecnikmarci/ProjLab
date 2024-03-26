package main.resources.effects;

import main.resources.skeleton.Skeleton;

import java.util.TimerTask;

/**
 * Időzítő osztály. Elindítása után a megadott időtartamig várakozik, majd értesíti a hívó objektumot.
 */
public class Timer {

    /**
     * Az időzítőt tulajdonló Item.
     */
    private Effect ownerEffect;

    /**
     * Az időzítést megvalósító java.util.Timer.
     */
    private java.util.Timer timer;

    public Timer(Effect ownerEffect) {
        this.ownerEffect = ownerEffect;
    }
    /**
     * Az időzítő elindítását megvalósító metódus.
     *
     * @param durationInSeconds Az időzítő időtartama.
     */
    void start(int durationInSeconds) {
        Skeleton.startCall("Timer.start(int)");
        timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ownerEffect.timeIsUp();
            }
        }, durationInSeconds /*1000L*/); //TODO *1000L élesben
        Skeleton.endCall();
    }
    /**
     * Az időzítő leállítását megvalósító metódus.
     */
    public void cancel() {
        Skeleton.startCall("Timer.cancel()");
        timer.cancel();
        Skeleton.endCall();
    }
}
