package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.AirFreshener;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;

public class FakeAirFreshener extends AirFreshener implements FakeItem{
    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("FakeAirFreshener.accept(Player)");
        player.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
