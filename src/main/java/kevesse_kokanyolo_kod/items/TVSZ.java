package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;
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
    public void use(Room room, AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("TVSZ.use(Room, Player)");
        KillImmunity killImmunity = new KillImmunity(this, Integer.MAX_VALUE, acamedicPerson);
        acamedicPerson.addKillImmunity(killImmunity);
        timesImmune--;

        if (timesImmune == 0) {
            acamedicPerson.removeItem(this);
        }
        SkeletonMenu.endCall();
    }

    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("TVSZ.accept(Player)");
        acamedicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
