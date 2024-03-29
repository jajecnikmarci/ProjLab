package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.player.Professor;
import kevesse_kokanyolo_kod.player.Student;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

/**
 *
 */
public class PoisonEffect extends RoomEffect {

    public PoisonEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * Az oktatót megmérgezi.
     *
     * @param professor
     */
    @Override
    public void affect(Professor professor) {
        Skeleton.startCall("PoisonEffect.affect(Professor)");
        professor.poison();
        Skeleton.endCall();
    }

    /**
     * A hallgatót megmérgezi.
     *
     * @param student
     */
    @Override
    public void affect(Student student) {
        Skeleton.startCall("PoisonEffect.affect(Student)");
        student.poison();
        Skeleton.endCall();
    }
}
