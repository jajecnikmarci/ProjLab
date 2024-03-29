package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;

/**
 * Tranzisztor tárgyat reprezentáló osztály
 */
public class FakeTransistor extends kevesse_kokanyolo_kod.items.Transistor implements FakeItem {
    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("FakeTransistor.accept(Player)");
        player.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
