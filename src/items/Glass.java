package items;

import effects.KillImmunity;
import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class Glass extends Item {

    /**
     * A Glass használatánál (csak hallgató használhatja) a hallgató védett
     * állapotba kerül az oktatók támadásaival szemben a Glass használatától
     * kezdve 10 másodpercig. A védettség egy KillImmunity, ami aktiválódik
     * amint aktiválja a hallgató a tárgyat.
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        System.out.println("Glass.use(Room, Player)");
        KillImmunity killImmunity = new KillImmunity(this, 10);
        killImmunity.activate();
        player.removeItem(this);
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        System.out.println("Glass.accept(Player)");
        player.acceptItem(this);
    }
}
