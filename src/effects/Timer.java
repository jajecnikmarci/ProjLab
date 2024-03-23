package effects;

/**
 * Időzítő osztály. Elindítása után a megadott időtartamig várakozik, majd értesíti a hívó objektumot.
 */
public class Timer {

    /**
     * @param duration
     */
    void start(int duration) {
        System.out.println("Timer.start(int)");
    }

    /**
     *
     */
    void Notify() {
    }
}
