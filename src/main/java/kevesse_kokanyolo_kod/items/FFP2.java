package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.PoisonImmunity;
import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

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
        if (immunityLength <= 0) {
            player.removeItem(this);
            //TODO Mérgezni a player-t ha elhasználódott a maszk
            Skeleton.endCall("A tárgy elhasználódott");
            return;
        }
        PoisonImmunity poisonImmunity = new PoisonImmunity(this, immunityLength, player);
        player.addPoisonImmunity(poisonImmunity);
        effect = poisonImmunity;
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
     *
     * @return az immunitás hossza
     */
    public int getImmunityLength() {
        return immunityLength;
    }
}
