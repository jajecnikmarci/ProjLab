package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.Effect;

/**
 * Egy tárgyat reprezentáló osztály
 */
public abstract class Item implements IItem {
    /**
     * A tárgyhoz tartozó effektet tárolja
     */
    protected Effect effect;

    /**
     * Megszünteti a tárgyhoz tartozó effektet
     */
    @Override
    public void removeEffect() {
        effect = null;
    }
}
