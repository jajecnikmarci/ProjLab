package kevesse_kokanyolo_kod.observer;

import kevesse_kokanyolo_kod.people.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentObservable {
    /**
     * Az eseményekre feliratkozó objektumok listája.
     */
    private List<StudentObserver> observers = new ArrayList<>();

    /**
     * A megfigyelt hallgató.
     */
    Student observable;

    /**
     * Feliratkoztatja a megadott StudentObservert az objektumot figyelők listájára.
     */
    public void addObserver(StudentObserver observer) {
        observers.add(observer);
    }

    /**
     * Ha meghal a hallgató, ezt a metódust hívja meg, átadva magát paraméterként.
     * Értesíti a feliratkozókat.
     */
    public void notifyStudentKilled(Student student) {
        for (StudentObserver observer : observers) {
            observer.studentKilled(student);
        }
    }

    /**
     * Ha egy hallgató felvette a logarlécet, ezt a metódust hívja meg. Értesíti a feliratkozókat.
     */
    public void notifySlideRulePicked() {
        for (StudentObserver observer : observers) {
            observer.slideRulePicked();
        }
    }
}
