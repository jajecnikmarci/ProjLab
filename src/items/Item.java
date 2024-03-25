package items;

import effects.Effect;
import player.Player;
import room.Room;

/**
 * Egy tárgyat reprezentáló osztály
 */
public abstract class Item {
    protected Effect effect;

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

    /**
     * Null-ra állítja a tárgyhoz tartozó Effect-et
     */
    public void removeEffect() {
        effect = null;
    }
}
