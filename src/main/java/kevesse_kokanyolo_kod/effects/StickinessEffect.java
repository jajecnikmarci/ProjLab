package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;

/**
 * A ragadós hatású tárgyakat reprezentáló osztály.
 * A hatás 5 látogatás után érvényesül.
 */
public class StickinessEffect extends RoomEffect{
    /**
     * A hatás érvényesüléséig hátralévő látogatások száma.
     */
    private int visitsBeforeEffect;

    protected StickinessEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(null, 0, null);
    }

    @Override
    public void affect(Professor professor) {
        visitsBeforeEffect--;
        }

    @Override
    public void affect(Student student) {
        visitsBeforeEffect--;
    }

    /**
     * Visszaadja, hogy ragacsosság megakadályozza-e a tárgy felvételét.
     * @return true, ha ragacsosság megakadályozza a tárgy felvételét, egyébként false
     */
    public boolean doesCleaningDenyItemPickup() {
        return visitsBeforeEffect <= 0;
    }

    /**
     * Takaításkor a ragacsosságig hátralévő látogatások számát visszaállítja alapértékre, ami 5
     */
    public void resetVisitsBeforeEffect() {
        visitsBeforeEffect = 5;
    }
}
