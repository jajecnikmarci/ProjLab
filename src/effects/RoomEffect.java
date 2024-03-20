package effects;

import player.Professor;
import player.Student;

/**
 *
 */
public abstract class RoomEffect extends Effect {
    /**
     * @param professor
     */
    public abstract void affect(Professor professor);

    /**
     * @param student
     */
    public abstract void affect(Student student);

}
