package kevesse_kokanyolo_kod.people;

import java.util.ArrayList;
import java.util.List;

public class StudentObservable {
    /**
     * Az Observer-ek listája
     */
    private List<StudentObserver> observers = new ArrayList<>();

    /**
     * Metódusok az Observer-ek regisztrálásához és eltávolításához
     */
    public void addObserver(StudentObserver observer) {
        observers.add(observer);
    }

    /**
     * A játék menete közben figyeli, hogy hány hallgatót bocsájtottak el.
     * Ha az összes hallgatót elbocsájtották, a professzorok nyertek.
     */
    public void notifyStudentKilled(Student student) {
        for (StudentObserver observer : observers) {
            observer.studentKilled(student);
        }
    }

    /**
     * Ez a függvény hívódik meg, ha egy hallgató felveszi a logarlécet.
     */
    public void notifySlideRulePicked() {
        for (StudentObserver observer : observers) {
            observer.slideRulePicked();
        }
    }
}
