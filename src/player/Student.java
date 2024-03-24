package player;

import effects.Effect;
import effects.KillImmunity;
import effects.PoisonImmunity;
import items.*;

import room.*;
import skeleton.Skeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A Hallgató viselkedését megvalósító osztály. 
 */
public class Student extends Player {
    /**
     * A hallgató lelkeinek számát tárolja. 
     */
    private int souls = 3;

    /**
     * Nyilvántartja, hogy melyik tárgyak által immunis a halálra.
     */
    private List<KillImmunity> killImmunities;

    public Student(Room r){
        super(r);
        souls = 3;
        killImmunities=new ArrayList<KillImmunity>();
    }


    /**
     * Megpróbálja elszipolyozni a hallgató lelkét. Ha van neki immunitása, akkor nem sikerül.
     */
    public void kill() {
        Skeleton.startCall("Student.kill()");
        if (killImmunities.isEmpty()) {
            souls--;
            if (souls == 0) {
                Skeleton.endCall(" A játékos meghalt");
                return;
            }
            Skeleton.endCall("A hallgató lelkét elszipolyozták.");
            return;
        }
        for (KillImmunity killImmunity : killImmunities) {
            if (killImmunity.isActive()) {
                Skeleton.endCall("A hallgatót megvédte egy már aktív tárgya.");
                return;
            }
        }
        killImmunities.get(0).activate();
        Skeleton.endCall("A hallgatót megvédte egy most aktiválódott tárgya.");

    }

    /**
     * Hozzáadja a killImmunities-hez a paraméterként kapott immuntitást.
     * @param killImmunity
     */
    public void addKillImmunity(KillImmunity killImmunity) {
        Skeleton.startCall("Student.addKillImmunity(KillImmunity)");
        killImmunities.add(killImmunity);
        Skeleton.endCall();
    }

    /**
     * Kitörli a killImmunities-ből a paraméterként kapott tárgyhoz tartozó immunitást.
     * @param item A tárgy ami az immunitást adja
     */
    public void removeKillImmunity(Item item) {
        Skeleton.startCall("Student.removeKillImmunity(KillImmunity)");
        KillImmunity killImmunityToRemove = findKillImmunityByItem(item);
        killImmunities.remove(killImmunityToRemove);
        Skeleton.endCall();
    }

    /**
     * Összepárosítja a két kapott tranzisztort. Ez egy új függvény, a tervben nem szerepelt.
     * @param transistor1
     * @param transistor2
     */
    public void pairTransistors(Transistor transistor1, Transistor transistor2) {
        Skeleton.startCall("Student.pairTransistors(transistor1, transistor2)");
        transistor1.setPair(transistor2);
        transistor2.setPair(transistor1);
        transistor1.setOwner(this);
        transistor2.setOwner(this);
        Skeleton.endCall();
    }

    
    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param ffp2 a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(FFP2 ffp2) {
        Skeleton.startCall("Student.acceptItem(FFP2)");
        this.addItem(ffp2);
        this.addPoisonImmunity(new PoisonImmunity(ffp2,ffp2.getImmunityLength(),this));
        location.removeItem(ffp2);
        Skeleton.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param camembert a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Camembert camembert) {
        Skeleton.startCall("Student.acceptItem(Camembert)");
        this.addItem(camembert);
        location.removeItem(camembert);
        Skeleton.endCall();
    }


    /**   
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param transistor a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Transistor transistor) {
        Skeleton.startCall("Student.acceptItem(Transistor)");
        this.addItem(transistor);
        location.removeItem(transistor);
        Skeleton.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param slideRule a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        Skeleton.startCall("Student.acceptItem(SlideRule)");
        this.addItem(slideRule);
        location.removeItem(slideRule);
        Skeleton.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.s
     * @param tvsz a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        Skeleton.startCall("Student.acceptItem(TVSZ)");
        this.addItem(tvsz);
        KillImmunity killImmunity = new KillImmunity(tvsz, 10,this);
        killImmunity.activate();
        this.addKillImmunity(killImmunity);
        location.removeItem(tvsz);
        Skeleton.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param glass a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Glass glass) {
        Skeleton.startCall("Student.acceptItem(Glass)");
        this.addItem(glass);
        this.addKillImmunity(new KillImmunity(glass,10,this));
        location.removeItem(glass);
        Skeleton.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param rug a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Rug rug) {
        Skeleton.startCall("Student.acceptItem(Rug)");
        this.addItem(rug);
        location.removeItem(rug);
        Skeleton.endCall();
    }

    /**
     * Interakcióba lép egy Oktatóval, elszipolyozódik a lelke. 
     * @param professor
     * @param room
     */
    @Override
    public void meet(Professor professor, Room room) {
        Skeleton.startCall("Student.meet(Professor, Room)");
        kill();
        Skeleton.endCall();
    }

    /**
     * Interakcióba lép egy másik hallgatóval, azaz nem csinál semmit.
     * @param student 
     */
    @Override
    public void meet(Student student) {
    }

    public void goToRoom(Room room) {
        Skeleton.startCall("Student.goToRoom(Room)");
        Optional<Door> door = location.getDoors()
                .stream()
                .filter(d -> d.isBetween(location, room))
                .findFirst();

        if(door.isPresent()) {
            door.get().goThrough(this);
            room.onEnter(this);
            Skeleton.endCall("A hallgató átment a szobába."); 
            return;
        }
        Skeleton.endCall("A hallgató nem ment át a szobába.");
    }
    public KillImmunity findKillImmunityByItem(Item item) {
        for (KillImmunity killImmunity : killImmunities) {
            if (killImmunity.getItem().equals(item)) {
                return killImmunity;
            }
        }
        return null;
    }
    @Override
    public void effectConsumed(Effect effect) {
        Skeleton.startCall("Student.effectConsumed()");


    }
}
