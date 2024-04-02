package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.StunEffect;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Nedves Táblatörlő Rongy tárgyat reprezentáló osztály
 */
public class Rug extends Item {
    @Override
    public void use(Room room, AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Rug.use(Room, Player)");
        StunEffect stunEffect = new StunEffect(this, 30, room);

        room.addStunEffect(stunEffect);
        effect = stunEffect;
        acamedicPerson.removeItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Rug.accept(Player)");
        acamedicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
