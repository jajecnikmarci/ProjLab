package items;

import effects.PoisonImmunity;
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
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        System.out.println("FFP2.accept(Player)");
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
        return true;
    }
}
