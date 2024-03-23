package items;

import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class TVSZ extends Item {
    /**
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {

    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    public void accept(Player player) {
        System.out.println("TVSZ.accept(Player)");
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
