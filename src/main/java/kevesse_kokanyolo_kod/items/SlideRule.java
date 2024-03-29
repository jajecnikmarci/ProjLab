package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class SlideRule extends Item {

    /**
     * A logarléc használata. Felvételekor véget ér a játék így nem felhasználható
     */
    @Override
    public void use(Room room, Player player) {
    }

    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("SlideRule.accept(Player)");
        player.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
