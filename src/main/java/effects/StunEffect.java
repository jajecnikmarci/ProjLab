package main.java.effects;

import main.java.items.Item;
import main.java.player.Professor;
import main.java.player.Student;
import main.java.skeleton.Skeleton;

/**
 * Egy játékos sokkolását elvégző effekt
 */
public class StunEffect extends RoomEffect {
    public StunEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    @Override
    public void activate() {
        Skeleton.startCall("StunEffect.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(getDuration());
        Skeleton.endCall();
    }

    /**
     * Lebénít egy oktatót
     * @param professor Sokkolandó oktató
     */
    @Override
    public void affect(Professor professor) {
        Skeleton.startCall("StunEffect.affect(Professor)");
        professor.stun(10);
        Skeleton.endCall();
    }

    /**
     * Lebénít egy hallgatót
     * @param student Sokkolandó hallgató
     */
    @Override
    public void affect(Student student) {
        Skeleton.startCall("StunEffect.affect(Professor)");
        student.stun(10);
        Skeleton.endCall();
    }
}
