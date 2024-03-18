package player;

import effects.KillImmunity;
import items.*;

import java.util.List;

/**
 *
 */
public class Student extends Player {
    /**
     *
     */
    private int souls;

    /**
     *
     */
    List<KillImmunity> killImmunities;

    /**
     *
     */
    void kill() {

    }

    /**
     * @param killImmunity
     */
    void addKillImmunity(KillImmunity killImmunity) {

    }

    /**
     * @param killImmunity
     */
    void removeKillImmunity(KillImmunity killImmunity) {

    }

    /**
     * @param ffp2
     * @return
     */
    @Override
    public boolean acceptItem(FFP2 ffp2) {
        return false;
    }

    /**
     * @param camembert
     * @return
     */
    @Override
    public boolean acceptItem(Camembert camembert) {
        return false;
    }

    /**
     * @param transistor
     * @return
     */
    @Override
    public boolean acceptItem(Transistor transistor) {
        return false;
    }

    /**
     * @param slideRule
     * @return
     */
    @Override
    public boolean acceptItem(SlideRule slideRule) {
        return false;
    }

    /**
     * @param tvsz
     * @return
     */
    @Override
    public boolean acceptItem(TVSZ tvsz) {
        return false;
    }

    /**
     * @param glass
     * @return
     */
    @Override
    public boolean acceptItem(Glass glass) {
        return false;
    }

    /**
     * @param rug
     * @return
     */
    @Override
    public boolean acceptItem(Rug rug) {
        return false;
    }
}
