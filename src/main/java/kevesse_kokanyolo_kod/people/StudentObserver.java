package kevesse_kokanyolo_kod.people;

public interface StudentObserver {
    /**
     * Amikor egy játékos meghal, ez a függvény hívódik meg.
     */
    void studentKilled(Student student);

    /**
     * Amikor egy játékos felveszi a logarlécet, ez a függvény hívódik meg.
     */
    void slideRulePicked();
}
