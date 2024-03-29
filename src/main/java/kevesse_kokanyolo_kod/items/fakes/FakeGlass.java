package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.Glass;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;

/**
 * Szent Sörös Pohár tárgyat reprezentáló osztály
 */
public class FakeGlass extends Glass implements FakeItem {
    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("FakeGlass.accept(Player)");
        player.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
