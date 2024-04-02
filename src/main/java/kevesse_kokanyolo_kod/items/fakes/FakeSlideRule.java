package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.SlideRule;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class FakeSlideRule extends SlideRule implements FakeItem {
    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("FakeSlideRule.accept(Player)");
        acamedicPerson.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
