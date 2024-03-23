package items;

import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class FFP2 extends Item {
    /**
     *
     */
    private int immunityLength = 10;
    

    /**
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        immunityLength -= 2;
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
