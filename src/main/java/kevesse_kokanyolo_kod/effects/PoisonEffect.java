package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;

/**
 * Mérgezés hatású effektum.
 */
public class PoisonEffect extends RoomEffect {

    public PoisonEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * Az oktatót megmérgezi.
     *
     * @param professor A hatást elszenvedő oktató
     */
    @Override
    public void affect(Professor professor) {
        SkeletonMenu.startCall("PoisonEffect.affect(Professor)");
        professor.poison();
        SkeletonMenu.endCall();
    }

    /**
     * A hallgatót megmérgezi.
     *
     * @param student A hatást elszenvedő hallgató
     */
    @Override
    public void affect(Student student) {
        SkeletonMenu.startCall("PoisonEffect.affect(Student)");
        student.poison();
        SkeletonMenu.endCall();
    }
}
