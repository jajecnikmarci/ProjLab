package items;

import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public abstract class Item {
    /**
     * @param room
     * @param player
     */
    public abstract void use(Room room, Player player);

    /**
     * @param player
     */
    abstract void accept(Player player);

    /**
     * @param student
     * @return
     */
    abstract boolean canPickUp(Student student);

    /**
     * @param professor
     * @return
     */
    abstract boolean canPickUp(Professor professor);
}
