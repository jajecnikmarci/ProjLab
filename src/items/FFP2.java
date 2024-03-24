package items;

import effects.PoisonImmunity;
import player.Player;
import player.Professor;
import player.Student;
import room.Room;
import skeleton.Skeleton;

/**
 * FFP2-es maszkot reprezentáló osztály.
 * Használatakor a Playerre PoisonImmunity-t rak, ami 10 másodpercig ad védelmet poison ellen.
 */
public class FFP2 extends Item {
    /**
     * 10 másodpercig tartó immunitás a poison hatásával szemben.
     */
    private int immunityLength = 10;

    @Override
    public void use(Room room, Player player) {
        Skeleton.startCall("FFP2.use(Room, Player)");
        immunityLength -= 2;
        Skeleton.endCall();
    }

    @Override
    public void accept(Player player) {
        Skeleton.startCall("FFP2.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
    
     /**
      * Visszaadja az immunitás hosszát.
      * @return az immunitás hossza
      */
    public int getImmunityLength() {
        return immunityLength;
    }
}
