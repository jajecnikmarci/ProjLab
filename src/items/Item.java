package items;

import player.Player;
import room.Room;

/**
 *
 */
public abstract class Item {
    /**
     * @param room
     * @param player
     */
    public abstract void use(Room room, Player player);

    /**
     * @param player
     */
    public abstract void accept(Player player);
}
