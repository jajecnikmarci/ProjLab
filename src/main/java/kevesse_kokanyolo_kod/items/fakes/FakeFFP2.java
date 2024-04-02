package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;

/**
 * FFP2-es maszkot reprezentáló osztály.
 * Használatakor a Playerre PoisonImmunity-t rak, ami 10 másodpercig ad védelmet poison ellen.
 */
public class FakeFFP2 extends kevesse_kokanyolo_kod.items.FFP2 implements FakeItem {
    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("FakeFFP2.accept(Player)");
        acamedicPerson.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
