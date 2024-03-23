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
     * és a szobához hozzáad egy PoisonEffect-et, valamint aktiválja azt.
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        System.out.println("Camembert.use(Room, Player)");
        PoisonEffect camembertEffect = new PoisonEffect(this, 30);
        room.addEffect(camembertEffect);
        camembertEffect.activate();
        room.poisonPlayers();
        player.removeItem(this);
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        System.out.println("Camembert.accept(Player)");
        player.acceptItem(this);
    }
}
