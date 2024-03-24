package items;

import player.Player;
import room.Room;
import skeleton.Skeleton;

/**
 *
 */
public class TVSZ extends Item {
    private int timesImmune = 3;
    /**
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        Skeleton.startCall("TVSZ.use(Room, Player)"); 
        timesImmune--;    
        Skeleton.endCall();   
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    public void accept(Player player) {
        Skeleton.startCall("TVSZ.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
