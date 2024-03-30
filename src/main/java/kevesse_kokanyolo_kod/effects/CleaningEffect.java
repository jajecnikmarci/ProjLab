package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.player.Professor;
import kevesse_kokanyolo_kod.player.Student;

public class CleaningEffect extends RoomEffect{
    private int visitsBeforeEffect;

    protected CleaningEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
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

    public boolean cleaningDeniesItemPickup() {
        if (visitsBeforeEffect <= 0) return true;
        else return false;
    }

    public void resetVisitsBeforeEffect() {
        visitsBeforeEffect = 5;
    }
}
