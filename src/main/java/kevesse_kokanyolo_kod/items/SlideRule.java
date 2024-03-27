package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

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
        Skeleton.startCall("SlideRule.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
