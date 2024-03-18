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
    abstract void affect(Professor professor);

    /**
     * @param student
     */
    abstract void affect(Student student);

}
