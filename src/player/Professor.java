package player;

import items.*;

/**
 *
 */
public class Professor extends Player {
    /**
     * @param student
     */
    @Override
    public void meet(Student student) {
        student.kill();
    }

    /**
     * @param professor
     */
    @Override
    public void meet(Professor professor) {
        // TODO: Leave room
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
