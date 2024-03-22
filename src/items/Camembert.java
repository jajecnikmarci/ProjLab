package items;

import effects.PoisonEffect;
import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class Camembert extends Item {
    /**
     * A Camembert használata. Eltávolítja a játékos tárgyai közül a Camembert-et, 
     * és a szobához hozzáad egy PoisonEffect-et, és azt aktiválni kell.
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {

        System.out.println("Camembert.use(Room, Player)");

        player.removeItem(this);

        PoisonEffect camembertEffect = new PoisonEffect();

        room.addEffect(camembertEffect);

        camembertEffect.activate();

        room.poisonPlayers();
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
