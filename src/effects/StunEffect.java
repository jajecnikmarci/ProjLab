package effects;

import items.Item;
import player.Professor;
import player.Student;

/**
 *
 */
public class StunEffect extends RoomEffect {
    public StunEffect(Item givenBy, int duration) {
        super(givenBy, duration);
    }

    /**
     *
     */
    @Override
    public void activate() {
        System.out.println("StunEffect.activate()");
        active = true;
    }

    /**
     * @param professor
     */
    @Override
    public void affect(Professor professor) {
        System.out.println("StunEffect.affect(Professor)");
        professor.stun(10);
    }

    /**
     * @param student
     */
    @Override
    public void affect(Student student) {
    }
}
