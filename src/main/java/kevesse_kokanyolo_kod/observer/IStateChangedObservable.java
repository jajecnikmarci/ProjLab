package kevesse_kokanyolo_kod.observer;

public interface IStateChangedObservable<T> {
    /**
     * Feliratkoztatja a megadott observert, származtatott osztálynak kell megvalósítania.
     * @param observer amit feliratkoztat
     */
    void addObserver(StateChangedObserver<T> observer);

    /**
     * Értesíti az összes feliratkozót, hogy a vizsgált objektum állapota megváltozott és
     * átadja nekik paraméterként a vizsgált objektumot (observable).
     */
    void notifyStateChanged();
}
