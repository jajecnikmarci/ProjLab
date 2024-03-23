package player;

import effects.PoisonImmunity;
import items.*;
import room.Room;

/**
 *
 */
public class Professor extends Player {
    
    public Professor(Room r){
        super(r);
        location=r;
    }
    
    
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
     */
    @Override
    public void acceptItem(FFP2 ffp2) {
        System.out.println("Professor.acceptItem(FFP2)");
        this.addItem(ffp2);
        this.addPoisonImmunity(new PoisonImmunity());
        location.removeItem(ffp2);
    }

    /**
     * @param camembert
     */
    @Override
    public void acceptItem(Camembert camembert) {
        System.out.println("Professor.acceptItem(Camembert)");
        this.addItem(camembert);
        location.removeItem(camembert);
    }

    /**
     * @param transistor
     * @return
     */
    @Override
    public void acceptItem(Transistor transistor) {
        return;
    }

    /**
     * @param slideRule
     * @return
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        return;
    }

    /**
     * @param tvsz
     * @return
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        return;
    }

    /**
     * @param glass
     * @return
     */
    @Override
    public void acceptItem(Glass glass) {
        return;
    }

    /**
     * @param rug
     * @return
     */
    @Override
    public void acceptItem(Rug rug) {
        return;
    }
}
