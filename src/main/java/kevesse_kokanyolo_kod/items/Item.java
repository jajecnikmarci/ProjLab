package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.observer.IStateChangedObservable;
import kevesse_kokanyolo_kod.observer.StateChangedObservable;
import kevesse_kokanyolo_kod.observer.StateChangedObserver;

/**
 * Egy tárgyat reprezentáló osztály
 */
public abstract class Item implements IItem, IStateChangedObservable<Item> {

    private StateChangedObservable<Item> stateChangedObservable;
    Item() {
        stateChangedObservable = new StateChangedObservable<>(this);
    }
    /**
     * A tárgyhoz tartozó effektet tárolja, hogy meg lehessen szüntetni eldobáskor, 
     * vagy felvételkor a játékoshoz lehessen adni.
     */
    protected Effect effect;

    /**
     * Megszünteti a tárgyhoz tartozó effektet
     */
    @Override
    public void removeEffect() {
        effect = null;
    }

    /**
     * Visszaadja a tárgyhoz tartozó effektet
     */
    @Override 
    public Effect getEffect() {
        return effect;
    }

    @Override
    public void addObserver(StateChangedObserver<Item> observer) {
        stateChangedObservable.addObserver(observer);
        
    }

    @Override
    public void notifyStateChanged() {
        stateChangedObservable.notifyStateChanged();
        
    }
}
