package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.StunEffect;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.room.Room;

public class AirFreshener extends Item{
    @Override
    public void use(Room room, Player player) {
        SkeletonMenu.startCall("AirFreshener.use(Room, Player)");
        room.clearPoisonEffects();
        player.removeItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("AirFreshener.accept(Player)");
        player.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
