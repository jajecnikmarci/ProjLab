package effects;

import items.Item;
import player.Professor;
import player.Student;

/**
 *
 */
public abstract class RoomEffect extends Effect {
    RoomEffect(Item givenBy) {
        super(givenBy);
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
