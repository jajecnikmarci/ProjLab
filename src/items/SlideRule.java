package items;

import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class SlideRule extends Item {
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
        System.out.println("SlideRule.accept(Player)");
        player.acceptItem(this);
    }

    /**
     * @param student
     * @return
     */
    @Override
    public boolean canPickUp(Student student) {
        return true;
    }

    /**
     * @param professor
     * @return
     */
    @Override
    public boolean canPickUp(Professor professor) {
        return false;
    }
}
