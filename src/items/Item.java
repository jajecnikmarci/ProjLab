package items;

import player.Player;
import room.Room;

/**
 * Egy tárgyat reprezentáló osztály
 */
public abstract class Item {
    /**
     *  A tárgy használata
     * @param room a szoba, ahol a tárgyat használják
     * @param player a játékos, aki használja a tárgyat
     */
    public abstract void use(Room room, Player player);

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt. Visitor design pattern része
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    public abstract void accept(Player player);
}
