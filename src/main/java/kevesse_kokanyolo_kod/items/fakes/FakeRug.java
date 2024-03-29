package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.Rug;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;

/**
 * Nedves Táblatörlő Rongy tárgyat reprezentáló osztály
 */
public class FakeRug extends Rug implements FakeItem {
    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("FakeRug.accept(Player)");
        player.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
