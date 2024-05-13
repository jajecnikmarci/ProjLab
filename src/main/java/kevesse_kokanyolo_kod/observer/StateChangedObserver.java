package kevesse_kokanyolo_kod.observer;

import java.util.function.Consumer;

public class StateChangedObserver<T> {
    /**
     * Ez a függvény hívódik meg, ha az osztály értesítést kap. (A
     * Consumer egy funkcionális interfész egy darab T típusú paramétert kap és nem ad
     * vissza semmilyen értéket)
     */
    private Consumer<T> callback;
    
    StateChangedObserver(Consumer<T> callback) {
        this.callback = callback;
    }

    /**
     * Ezt a metódust hívja meg az Observable átadva magát (kontextusként).
     * Ez a metódus meghívja a konstruktorban beállított callback függvényt.
     */
    public void onStateChanged(T observable) {
        callback.accept(observable);
    }
}
