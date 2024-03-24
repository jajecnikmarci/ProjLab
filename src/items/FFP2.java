package items;

import effects.PoisonImmunity;
import player.Player;
import player.Professor;
import player.Student;
import room.Room;
import skeleton.Skeleton;

/**
 *
 */
public class FFP2 extends Item {
    /**
     *
     */
    private int immunityLength = 10;
    

    /**
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        Skeleton.startCall("FFP2.use(Room, Player)");
        immunityLength -= 2;
        Skeleton.endCall();
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        Skeleton.startCall("FFP2.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
    
     /**
      * @return
      */
    public int getImmunityLength() {
        return immunityLength;
    }
}
