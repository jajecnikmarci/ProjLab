package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.player.Professor;
import kevesse_kokanyolo_kod.player.Student;

/**
 * Egy hatás, ami egy szobában lévő játékosokra hat.
 */
public abstract class RoomEffect extends Effect {
    public RoomEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * Hatást hajt végre a szobában lévő professzorra.
     * @param professor A hatást elszenvedő professzor
     */
    public abstract void affect(Professor professor);

    /**
     * Hatást hajt végre a szobában lévő hallgatóra.
     * @param student A hatást elszenvedő hallgató
     */
    public abstract void affect(Student student);
}
