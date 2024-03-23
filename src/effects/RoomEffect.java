package effects;

import items.Item;
import player.Professor;
import player.Student;

/**
 *
 */
public abstract class RoomEffect extends Effect {
    public RoomEffect(Item givenBy, int duration) {
        super(givenBy, duration);
    }

    /**
     * @param professor
     */
    public abstract void affect(Professor professor);

    /**
     * @param student
     */
    public abstract void affect(Student student);

}
