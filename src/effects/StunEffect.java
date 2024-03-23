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

    }

    /**
     * @param professor
     */
    @Override
    public void affect(Professor professor) {

    }

    /**
     * @param student
     */
    @Override
    public void affect(Student student) {

    }
}
