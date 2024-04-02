package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.player.Student;

/**
 * Egy tárgyat reprezentáló osztály
 */
public abstract class Item implements iItem {
    protected Effect effect;

    public void hasToDropItem(Student student) {}

    @Override
    public void removeEffect() {
        effect = null;
    }
}
