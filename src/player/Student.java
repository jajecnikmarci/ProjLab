package player;

import effects.KillImmunity;
import effects.PoisonImmunity;
import items.*;
import room.*;

import java.util.ArrayList;
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

    public Student(Room r){
        super(r);
        souls = 3;
        killImmunities=new ArrayList<KillImmunity>();
    }


    /**
     *
     */
    void kill() {

    }

    /**
     * Hozzáadja a killImmunities-hez a paraméterként kapott immuntitást.
     * @param killImmunity
     */
    void addKillImmunity(KillImmunity killImmunity) {
        System.out.println("Student.addKillImmunity(KillImmunity)");
        killImmunities.add(killImmunity);
    }

    /**
     * Kitörli a killImmunities-ből a paraméterként kapott immuntitást.
     * @param killImmunity
     */
    void removeKillImmunity(KillImmunity killImmunity) {
        killImmunities.remove(killImmunity);
    }

    /**
     * @param ffp2
     */
    @Override
    public void acceptItem(FFP2 ffp2) {
        System.out.println("Student.acceptItem(FFP2)");
        this.addItem(ffp2);
        this.addPoisonImmunity(new PoisonImmunity());
        location.removeItem(ffp2);
    }

    /**
     * @param camembert
     */
    @Override
    public void acceptItem(Camembert camembert) {
        System.out.println("Student.acceptItem(Camembert)");
        this.addItem(camembert);
        location.removeItem(camembert);
    }

    /**
     * @param transistor
     */
    @Override
    public void acceptItem(Transistor transistor) {
        System.out.println("Student.acceptItem(Transistor)");
        this.addItem(transistor);
        location.removeItem(transistor);
    }

    /**
     * @param slideRule
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        System.out.println("Student.acceptItem(SlideRule)");
        this.addItem(slideRule);
        location.removeItem(slideRule);
    }

    /**
     * @param tvsz
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        System.out.println("Student.acceptItem(TVSZ)");
        this.addItem(tvsz);
        this.addKillImmunity(new KillImmunity());
        location.removeItem(tvsz);
    }

    /**
     * @param glass
     */
    @Override
    public void acceptItem(Glass glass) {
        System.out.println("Student.acceptItem(Glass)");
        this.addItem(glass);
        this.addKillImmunity(new KillImmunity());
        location.removeItem(glass);
    }

    /**
     * @param rug
     */
    @Override
    public void acceptItem(Rug rug) {
        System.out.println("Student.acceptItem(Rug)");
        this.addItem(rug);
        location.removeItem(rug);
    }

    
    @Override
    public void meet(Professor professor) {
        kill();
    }

    @Override
    public void meet(Student student) {
    }
}
