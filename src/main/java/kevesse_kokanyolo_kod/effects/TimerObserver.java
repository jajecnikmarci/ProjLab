package kevesse_kokanyolo_kod.effects;

/**
 * Az időzítőt figyelő interfész, mely observer design patternt valósít meg.
 */
public interface TimerObserver {
    /**
     * A timer lejárásakor meghívódó metódus
     */
    public void timeIsUp();

    /**
     * A timer leállítása a lejárata előtt
     */
    public void cancelTimer();
}
