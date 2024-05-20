package kevesse_kokanyolo_kod.effects;

import java.util.TimerTask;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;

/**
 * Időzítő osztály. Elindítása után a megadott időtartamig várakozik, majd értesíti a hívó objektumot.
 */
public class Timer {

    /**
     * Az időzítőt birtokló Hatás.
     */
    private final Effect ownerEffect;

    /**
     * Az időzítést megvalósító java.util.Timer.
     */
    private java.util.Timer innerTimer;

    /**
     * A paraméterül kapott értékkel létrehozza a Timer-t
     */
    public Timer(Effect ownerEffect) {
        this.ownerEffect = ownerEffect;
    }

    /**
     * Az időzítő elindítását megvalósító metódus.
     * Amikor véget ér az időzítő, meghívja az ownerEffect.timeIsUp metódust, ezzel jelezve a hatás végét.
     * @param durationInSeconds Az időzítő időtartama.
     */
    public void start(int durationInSeconds) {
        SkeletonMenu.startCall("Timer.start(int)");
        innerTimer = new java.util.Timer();
        innerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeIsUp();
            }
        }, durationInSeconds *1000L); 
        SkeletonMenu.endCall();
    }
    protected void timeIsUp() {
        ownerEffect.timeIsUp();
    }

    /**
     * Az időzítő leállítását megvalósító metódus.
     */
    public void cancel() {
        SkeletonMenu.startCall("Timer.cancel()");
        if(innerTimer!=null) {
            innerTimer.cancel();
        }
        timeIsUp(); // Az időzítő leállításakor is jelezni kell a hatás végét.
        SkeletonMenu.endCall();
    }
}
