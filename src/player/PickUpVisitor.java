package player;

import items.*;

/**
 *
 */
public interface PickUpVisitor {
    /**
     * @param ffp2
     * @return
     */
    boolean acceptItem(FFP2 ffp2);

    /**
     * @param camembert
     * @return
     */
    boolean acceptItem(Camembert camembert);

    /**
     * @param transistor
     * @return
     */
    boolean acceptItem(Transistor transistor);

    /**
     * @param slideRule
     * @return
     */
    boolean acceptItem(SlideRule slideRule);

    /**
     * @param tvsz
     * @return
     */
    boolean acceptItem(TVSZ tvsz);

    /**
     * @param glass
     * @return
     */
    boolean acceptItem(Glass glass);

    /**
     * @param rug
     * @return
     */
    boolean acceptItem(Rug rug);

}
