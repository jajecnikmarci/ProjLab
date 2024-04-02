package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class SlideRule extends Item {

    /**
     * A logarléc használata. Felvételekor véget ér a játék így nem felhasználható
     */
    @Override
    public void use(Room room, AcamedicPerson acamedicPerson) {
    }

    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("SlideRule.accept(Player)");
        acamedicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
