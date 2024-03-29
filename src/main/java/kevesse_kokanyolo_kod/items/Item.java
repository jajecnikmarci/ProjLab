package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.Effect;

/**
 * Egy tárgyat reprezentáló osztály
 */
public abstract class Item implements iItem {
    protected Effect effect;


    @Override
    public void removeEffect() {
        effect = null;
    }
}
