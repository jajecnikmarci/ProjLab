package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;

/**
 * Effektek megvalósítására szolgáló osztály. Az effektumokat a játékosokra és szobákra lehet alkalmazni.
 */
public abstract class Effect implements TimerObserver {
    /**
     * Megmondja, hogy meddig/még mennyi ideig aktív az effekt.
     */
    protected Timer timer;

    /**
     * Megmondja, hogy aktív-e a hatás.
     */
    protected boolean active;

    /*
     * Az effektet figyelő osztály, az effekt lejártakor meg kell hívni az effectConsumed() metódusát.
     */
    private final EffectConsumedObserver observer;

    /**
     * Az effekt időtartama, ha van neki. Ha nincs akkor az értéke 0.
     */
    protected final int duration;

    /**
     * Azt az Item-et tárolja, amelyik létrehozta az effektet.
     */
    protected final Item givenBy;

    /**
     * Létrehozza az Effect-et a paraméterül kapott értékekkel.
     */
    protected Effect(Item givenBy, int duration, EffectConsumedObserver observer) {
        this.givenBy = givenBy;
        this.duration = duration;
        this.observer = observer;
    }

    /**
     * Aktiválja az effectet, létrehoz egy időzítőt és elindítja a hatás időtartamáig.
     */
    public void activate() {
        SkeletonMenu.startCall("Effect.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(duration);
        SkeletonMenu.endCall();

        LabyrinthBuilder.addTimer(timer);
    }

    /**
     * Az effekt aktívságának lekérő függvénye
     * @return az effekt aktívságát adja vissza
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Az effekt hosszát lekérő függvény
     * @return az effekt hosszát adja vissza
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Jelzi a hatást figyelő osztálynak (observer), hogy az effekt elhasználódott. 
     * A hatás lejártakor az időzítő meghívja ezt a függvént. 
     */
    public void timeIsUp() {
        SkeletonMenu.startCall("Effect.timeIsUp()");
        observer.effectConsumed(this);
        SkeletonMenu.endCall();
    }
    
    /**
     * Leállítja az időzítőt és inaktívvá teszi a hatást.
     */
    public void cancelTimer() {
        SkeletonMenu.startCall("Effect.cancelTimer()");
        timer.cancel();
        active = false;
        SkeletonMenu.endCall();
    }
    
    /**
     * Visszaadja, hogy melyik tárgy hozta létre az effektet
     * @return a tárgy, amelyik létrehozta az effektet
     */
    public Item getItem() {
        return givenBy;
    }

    /**
     * Kiiratja a hatás belső állapotát a printer segítségével. 
     * A builder segítségével kérdezi le a hatás példányához tartozó nevét.  
     * @param printer  
     * @param builder
     */
    public abstract void printState(Printer printer, LabyrinthBuilder builder);
}
