package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.TVSZ;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;

/**
 * TVSZ Denevér Bőrre Nyomtatott Példánya tárgyat reprezentáló osztály
 */
public class FakeTVSZ extends TVSZ implements FakeItem {
    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("FakeTVSZ.accept(Player)");
        acamedicPerson.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
