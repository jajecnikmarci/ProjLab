package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * Egy hatás, ami egy szobában lévő játékosokra hat.
 */
public abstract class RoomEffect extends Effect {
    /**
     * Létrehozza a RoomEffect-et a paraméterül kapott értékekkel.
     */
    protected RoomEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * A szoba hatást gyakorol a paraméterül kapott játékosra.
     * @param academicPerson a játékos, akit érint a szoba hatása
     */
    public abstract void affect(AcademicPerson academicPerson);
}
