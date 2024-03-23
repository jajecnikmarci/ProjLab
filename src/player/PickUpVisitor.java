package player;

import items.*;

/**
 *
 */
public interface PickUpVisitor {
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
