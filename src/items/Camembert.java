package items;

import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class Camembert extends Item {
    /**
     * @param room
     * @param player
     */
    @Override
    void use(Room room, Player player) {

    }

    /**
     * @param player
     */
    @Override
    void accept(Player player) {

    }

    /**
     * @param student
     * @return
     */
    @Override
    boolean canPickUp(Student student) {
        return false;
    }

    /**
     * @param professor
     * @return
     */
    @Override
    boolean canPickUp(Professor professor) {
        return false;
    }
}
