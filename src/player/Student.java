package player;

import effects.KillImmunity;
import items.*;
import room.Room;

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
    private List<KillImmunity> killImmunities;

    /**
     * Egy professzor hívja meg ha egy szobába került egy diákkal
     */
    public void kill() {
        System.out.println("Student.kill()");
        if (killImmunities.isEmpty()) {
            souls--;
            return;
        }
        for (KillImmunity killImmunity : killImmunities) {
            if (killImmunity.isActive()) {
                return;
            }
        }
        killImmunities.get(0).activate();

    }

    /**
     * @param killImmunity
     */
    public void addKillImmunity(KillImmunity killImmunity) {

    }

    /**
     * @param killImmunity
     */
    public void removeKillImmunity(KillImmunity killImmunity) {

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

    /**
     * @param professor
     * @param room
     */
    @Override
    public void meet(Professor professor, Room room) {
        System.out.println("Student.meet(Professor, Room)");
        kill();
    }

    /**
     * @param student
     */
    @Override
    public void meet(Student student) {
    }
}
