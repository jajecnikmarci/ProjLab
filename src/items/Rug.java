package items;

import effects.StunEffect;
import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class Rug extends Item {
    /**
     * A hallgató használja a Rug tárgyat és a hallgató tartózkodási szobájához hozzáadódik a rongy effektje.
     * @param room a szoba amihez az effektet kell adni
     * @param player játékos aki használta az itemet 
     */
    @Override
    public void use(Room room, Player player) {
        System.out.println("Rug.use(Room, Player)");
        room.addEffect(new StunEffect());
        player.removeItem(this);

    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        System.out.println("Rug.accept(Player)");
        player.acceptItem(this);
    }
}
