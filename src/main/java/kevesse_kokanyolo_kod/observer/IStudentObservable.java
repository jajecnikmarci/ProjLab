package kevesse_kokanyolo_kod.observer;

import kevesse_kokanyolo_kod.people.Student;

public interface IStudentObservable {
    /**
     * Ha meghal a hallgató, ezt a metódust hívja meg, átadva magát paraméterként.
     * Értesíti a feliratkozókat.
     * @param student a hallgató, akit elbocsájtottak
     */
    public void notifyStudentKilled(Student student);

    /**
     * Ha egy hallgató felvette a logarlécet, ezt a metódust hívja meg. Értesíti a feliratkozókat.
     */
    public void notifySlideRulePicked();

    /**
     * Feliratkoztatja a megadott StudentObservert az objektumot figyelők listájára.
     * @param observer amire feliratkoztatja
     */
    public void addObserver(StudentObserver observer);
}
