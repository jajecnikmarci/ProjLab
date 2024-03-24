package items;

import effects.KillImmunity;
import player.Player;
import room.Room;
import skeleton.Skeleton;

/**
 * TVSZ Denevér Bőrre Nyomtatott Példánya tárgyat reprezentáló osztály
 */
public class TVSZ extends Item {
    /**
     * A tárgy hátralévő immunitást adásainak száma
     */
    private int timesImmune;

    public TVSZ() {
        timesImmune = 3;
    }

    @Override
    public void use(Room room, Player player) {
        Skeleton.startCall("TVSZ.use(Room, Player)");
        KillImmunity killImmunity = new KillImmunity(this,0, player);
        player.addKillImmunity(killImmunity);
        timesImmune--;

        if (timesImmune == 0) {
            player.removeItem(this);
        }
        Skeleton.endCall();   
    }

    public void accept(Player player) {
        Skeleton.startCall("TVSZ.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
