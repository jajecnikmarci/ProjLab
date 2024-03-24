package items;

import effects.PoisonEffect;
import player.Player;
import room.Room;
import skeleton.Skeleton;

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
        Skeleton.startCall("Camembert.use(Room, Player)");
        PoisonEffect camembertEffect = new PoisonEffect(this, 30);
        room.addEffect(camembertEffect);
        camembertEffect.activate();
        room.poisonPlayers();
        player.removeItem(this);
        Skeleton.endCall();
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        Skeleton.startCall("Camembert.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
