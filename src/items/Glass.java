package items;

import effects.KillImmunity;
import player.Player;
import room.Room;
import skeleton.Skeleton;

/**
 * Szent Sörös Pohár tárgyat reprezentáló osztály
 */
public class Glass extends Item {

    /**
     * A Glass használatánál (csak hallgató használhatja) a hallgató védett
     * állapotba kerül az oktatók támadásaival szemben a Glass használatától
     * kezdve 10 másodpercig. A védettség egy KillImmunity, ami aktiválódik
     * amint aktiválja a hallgató a tárgyat.
     * @param room a szoba, ahol a tárgyat használják
     * @param player a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, Player player) {
        Skeleton.startCall("Glass.use(Room, Player)");
        KillImmunity killImmunity = new KillImmunity(this, 10, player);
        killImmunity.activate();
        player.removeItem(this);
        Skeleton.endCall();
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        Skeleton.startCall("Glass.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
