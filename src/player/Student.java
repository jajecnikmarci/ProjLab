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
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param ffp2 a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(FFP2 ffp2) {
        System.out.println("Student.acceptItem(FFP2)");
        this.addItem(ffp2);
        this.addPoisonImmunity(new PoisonImmunity());
        location.removeItem(ffp2);
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param camembert a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Camembert camembert) {
        System.out.println("Student.acceptItem(Camembert)");
        this.addItem(camembert);
        location.removeItem(camembert);
    }

    /**     *
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param transistor a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Transistor transistor) {
        System.out.println("Student.acceptItem(Transistor)");
        this.addItem(transistor);
        location.removeItem(transistor);
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param slideRule a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        System.out.println("Student.acceptItem(SlideRule)");
        this.addItem(slideRule);
        location.removeItem(slideRule);
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.s
     * @param tvsz a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        System.out.println("Student.acceptItem(TVSZ)");
        this.addItem(tvsz);
        this.addKillImmunity(new KillImmunity());
        location.removeItem(tvsz);
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param glass a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Glass glass) {
        System.out.println("Student.acceptItem(Glass)");
        this.addItem(glass);
        this.addKillImmunity(new KillImmunity());
        location.removeItem(glass);
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param rug a hozzáadandó tárgy
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
