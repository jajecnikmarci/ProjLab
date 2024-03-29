package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.SlideRule;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class FakeSlideRule extends SlideRule implements FakeItem {
    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("FakeSlideRule.accept(Player)");
        player.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
