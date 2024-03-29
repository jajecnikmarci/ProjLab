package kevesse_kokanyolo_kod.effects;

/**
 * Az időzítőt figyelő interfész, mely observer design patternt valósít meg.
 */
public interface TimerObserver {
    /**
     * A timer lejárásakor meghívódó metódus
     */
    void timeIsUp();

    /**
     * A timer leállítása a lejárata előtt
     */
    void cancelTimer();
}
