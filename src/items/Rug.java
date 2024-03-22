package items;

import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class Rug extends Item {
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
    public void accept(Player player) {
        if(player.acceptItem(this)){
            player.addItem(this);
        }
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
