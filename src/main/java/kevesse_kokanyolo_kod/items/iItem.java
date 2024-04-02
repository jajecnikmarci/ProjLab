package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.room.Room;

public interface IItem {

    /**
     * A tárgy használata
     *
     * @param room   a szoba, ahol a tárgyat használják
     * @param player a játékos, aki használja a tárgyat
     */
    void use(Room room, Player player);

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt. Visitor design pattern része
     *
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    void accept(Player player);

    /**
     * Null-ra állítja a tárgyhoz tartozó Effect-et
     */
    void removeEffect();

}
