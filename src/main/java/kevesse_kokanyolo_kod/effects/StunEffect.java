package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;

/**
 * Egy játékos sokkolását elvégző effekt
 */
public class StunEffect extends RoomEffect {
    public StunEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * Lebénít egy oktatót
     *
     * @param professor Sokkolandó oktató
     */
    @Override
    public void affect(Professor professor) {
        SkeletonMenu.startCall("StunEffect.affect(Professor)");
        professor.stun(10);
        SkeletonMenu.endCall();
    }

    /**
     * Lebénít egy hallgatót
     *
     * @param student Sokkolandó hallgató
     */
    @Override
    public void affect(Student student) {
        SkeletonMenu.startCall("StunEffect.affect(Professor)");
        student.stun(10);
        SkeletonMenu.endCall();
    }
}
