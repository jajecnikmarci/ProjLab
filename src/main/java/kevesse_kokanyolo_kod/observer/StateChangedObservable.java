package kevesse_kokanyolo_kod.observer;

import java.util.ArrayList;
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
    
    public StateChangedObservable(T observable) {
        this.observers = new ArrayList<>();
        this.observable = observable;
    }

    /**
     * Feliratkoztatja a megadott observert.
     * @param observer amit feliratkoztat
     */
    public void addObserver(StateChangedObserver<T> observer) {
        observers.add(observer);
    }

    /**
     * Értesíti az összes feliratkozót, hogy a vizsgált objektum állapota megváltozott és
     * átadja nekik paraméterként a vizsgált objektumot (observable).
     */
    public void notifyStateChanged() {
        for (StateChangedObserver<T> observer : observers) {
            observer.onStateChanged(observable);
        }
    }
}
