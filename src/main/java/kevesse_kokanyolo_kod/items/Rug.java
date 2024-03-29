package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.StunEffect;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Nedves Táblatörlő Rongy tárgyat reprezentáló osztály
 */
public class Rug extends Item {
    @Override
    public void use(Room room, Player player) {
        SkeletonMenu.startCall("Rug.use(Room, Player)");
        StunEffect stunEffect = new StunEffect(this, 30, room);

        room.addEffect(stunEffect);
        effect = stunEffect;
        player.removeItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("Rug.accept(Player)");
        player.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
