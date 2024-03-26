package main.java.effects;

import main.java.items.Item;
import main.java.player.Professor;
import main.java.player.Student;
import main.java.skeleton.Skeleton;

/**
 *
 */
public class PoisonEffect extends RoomEffect {

    public PoisonEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     *
     */
    @Override
    public void activate() {
        Skeleton.startCall("PoisonEffect.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(getDuration());
        Skeleton.endCall();
    }

    /**
     * Az oktatót megmérgezi.
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
     * @param student
     */
    @Override
    public void affect(Student student) {
        Skeleton.startCall("PoisonEffect.affect(Student)");
        student.poison();
        Skeleton.endCall();
    }
}
