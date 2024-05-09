package kevesse_kokanyolo_kod.observer;

import java.util.List;

public class StateChangedObservable<T> {
    /**
     * A feliratkozók listája.
     */
    List<StateChangedObserver<T>> observers;

    /**
     * A vizsgált objektum.
     */
    T observable;

    /**
     * Feliratkoztatja a megadott observert.
     * @param observer amit feliratkoztat
     */
    public void addObserver(StateChangedObserver<T> observer) {
    }

    /**
     * Értesíti az összes feliratkozót, hogy a vizsgált objektum állapota megváltozott és
     * átadja nekik paraméterként a vizsgált objektumot (observable).
     */
    protected void notifyStateChanged() {
    }
}