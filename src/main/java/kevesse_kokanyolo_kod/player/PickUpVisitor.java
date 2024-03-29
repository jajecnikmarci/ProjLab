package kevesse_kokanyolo_kod.player;

import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.items.fakes.FakeItem;

/**
 * A játékos által felvihető tárgyakat reprezentáló interfész
 * Visitor design pattern implementációja
 */
public interface PickUpVisitor {
    /** Hamis tárgy felvétele
     * @param fakeItem a hamis tárgy
     */
    void acceptItem(FakeItem fakeItem);

    /**
     * @param ffp2
     */
    void acceptItem(FFP2 ffp2);

    /**
     * @param camembert
     */
    void acceptItem(Camembert camembert);

    /**
     * @param transistor
     */
    void acceptItem(Transistor transistor);

    /**
     * @param slideRule
     */
    void acceptItem(SlideRule slideRule);

    /**
     * @param tvsz
     */
    void acceptItem(TVSZ tvsz);

    /**
     * @param glass
     */
    void acceptItem(Glass glass);

    /**
     * @param rug
     */
    void acceptItem(Rug rug);

}
