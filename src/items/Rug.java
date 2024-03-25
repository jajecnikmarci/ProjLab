package items;

import effects.StunEffect;
import player.Player;
import room.Room;
import skeleton.Skeleton;

/**
 * Nedves Táblatörlő Rongy tárgyat reprezentáló osztály
 */
public class Rug extends Item {
    @Override
    public void use(Room room, Player player) {
        Skeleton.startCall("Rug.use(Room, Player)");
        StunEffect stunEffect = new StunEffect(this,30   ,room);

        room.addEffect(stunEffect);
        effect = stunEffect;
        player.removeItem(this);
        Skeleton.endCall();
    }
    @Override
    public void accept(Player player) {
        Skeleton.startCall("Rug.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
