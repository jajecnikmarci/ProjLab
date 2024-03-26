package main.java.items;

import main.java.player.Player;
import main.java.room.Room;
import main.java.skeleton.Skeleton;

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
