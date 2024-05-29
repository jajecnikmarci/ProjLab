package kevesse_kokanyolo_kod.observer;

import kevesse_kokanyolo_kod.people.Student;

public interface StudentObserver {
    /**
     * Ha meghal egy hallgató, ebben a metódusban kell kezelni, hogy mi történjen a hatására.
     */
    void studentKilled(Student student);

    /**
     * Ha felveszi egy hallgató a logarlécet, ebben a metódusban kell kezelni, hogy mi történjen a hatására.
     */
    void slideRulePicked();
}
