package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.Camembert;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;

/**
 * Hamis Dobozolt Káposztás Camembert-t reprezentáló osztály
 */
public class FakeCamembert extends Camembert implements FakeItem {
    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("FakeCamembert.accept(Player)");
        player.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
