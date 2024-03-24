package items;

import effects.StunEffect;
import player.Player;
import room.Room;
import skeleton.Skeleton;

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
        Skeleton.startCall("Rug.use(Room, Player)");
        room.addEffect(new StunEffect(this,30   ));
        player.removeItem(this);
        Skeleton.endCall();
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        Skeleton.startCall("Rug.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
