package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.room.Room;

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
        SkeletonMenu.startCall("TVSZ.use(Room, Player)");
        KillImmunity killImmunity = new KillImmunity(this, Integer.MAX_VALUE, player);
        player.addKillImmunity(killImmunity);
        timesImmune--;

        if (timesImmune == 0) {
            player.removeItem(this);
        }
        SkeletonMenu.endCall();
    }

    public void accept(Player player) {
        SkeletonMenu.startCall("TVSZ.accept(Player)");
        player.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
