package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.effects.PoisonImmunity;
import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.room.Room;

import java.util.*;

/**
 * A Hallgató viselkedését megvalósító osztály.
 */
public class Student extends AcademicPerson {
    /**
     * A hallgató lelkeinek számát tárolja.
     */
    private int souls;

    /**
     * Nyilvántartja, hogy melyik tárgyak által immunis a halálra.
     */
    private List<KillImmunity> killImmunities;

    public Student(Room room) {
        super(room);
        souls = 3;
        killImmunities = new ArrayList<>();
    }


    /**
     * Megpróbálja elszipolyozni a hallgató lelkét. Ha van neki immunitása, akkor nem sikerül.
     */
    public void kill() {
        SkeletonMenu.startCall("Student.kill()");
        if (killImmunities.isEmpty()) {
            souls--;
            if (souls == 0) {
                //TODO: Meghal a hallgató
                SkeletonMenu.endCall(" A játékos meghalt");
                return;
            }
            SkeletonMenu.endCall("A hallgató lelkét elszipolyozták.");
            return;
        }
        for (KillImmunity killImmunity : killImmunities) {
            if (killImmunity.isActive()) {
                SkeletonMenu.endCall("A hallgatót megvédte egy már aktív tárgya.");
                return;
            }
        }
        KillImmunity killImmunity = killImmunities.get(0);
        killImmunity.activate();
        SkeletonMenu.endCall("A hallgatót megvédte egy most aktiválódott tárgya.");
    }

    /**
     * Eldobja a hallgató egyik tárgyát.
     */
    @Override
    public void dropRandomItem() {
        SkeletonMenu.startCall("Student.dropRandItem()");
        int rand = (int) (Math.random() * inventory.size());
        IItem item = inventory.get(rand);
        inventory.remove(item);
        location.addItem(item);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a killImmunities-hez a paraméterként kapott immuntitást.
     *
     * @param killImmunity A hozzáadadndó killImmunity
     */
    @Override
    public void addKillImmunity(KillImmunity killImmunity) {
        SkeletonMenu.startCall("Student.addKillImmunity(KillImmunity)");
        killImmunities.add(killImmunity);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a killImmunities-ből a paraméterként kapott tárgyhoz tartozó immunitást.
     *
     * @param item A tárgy ami az immunitást adja
     */
    @Override
    public void removeKillImmunity(Item item) {
        SkeletonMenu.startCall("Student.removeKillImmunity(KillImmunity)");
        KillImmunity killImmunityToRemove = findKillImmunityByItem(item);
        killImmunities.remove(killImmunityToRemove);
        SkeletonMenu.endCall();
    }

    /**
     * Összepárosítja a két kapott tranzisztort. Ez egy új függvény, a tervben nem szerepelt.
     *
     * @param transistor1 Az egyik tranzisztor
     * @param transistor2 A másik tranzisztor
     */
    public void pairTransistors(Transistor transistor1, Transistor transistor2) {
        SkeletonMenu.startCall("Student.pairTransistors(transistor1, transistor2)");
        transistor1.setPair(transistor2);
        transistor2.setPair(transistor1);
        transistor1.setOwner(this);
        transistor2.setOwner(this);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param transistor a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Transistor transistor) {
        SkeletonMenu.startCall("Student.acceptItem(Transistor)");
        this.addItem(transistor);
        location.removeItem(transistor);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param slideRule a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        SkeletonMenu.startCall("Student.acceptItem(SlideRule)");
        this.addItem(slideRule);
        location.removeItem(slideRule);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.s
     *
     * @param tvsz a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        SkeletonMenu.startCall("Student.acceptItem(TVSZ)");
        this.addItem(tvsz);
        KillImmunity killImmunity = new KillImmunity(tvsz, 10, this);
        killImmunity.activate();
        this.addKillImmunity(killImmunity);
        location.removeItem(tvsz);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param glass a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Glass glass) {
        SkeletonMenu.startCall("Student.acceptItem(Glass)");
        this.addItem(glass);
        this.addKillImmunity(new KillImmunity(glass, 10, this));
        location.removeItem(glass);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param rug a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Rug rug) {
        SkeletonMenu.startCall("Student.acceptItem(Rug)");
        this.addItem(rug);
        location.removeItem(rug);
        SkeletonMenu.endCall();
    }

    /**
     * Interakcióba lép egy Oktatóval, elszipolyozódik a lelke.
     *
     * @param professor A hallgatóval interakcióba lépő oktató
     * @param room      A szoba, ahol az interakció történik
     */
    @Override
    public void meet(Professor professor, Room room) {
        SkeletonMenu.startCall("Student.meet(Professor, Room)");
        kill();
        SkeletonMenu.endCall();
    }

    /**
     * Interakcióba lép egy másik hallgatóval, azaz nem csinál semmit.
     *
     * @param student
     */
    @Override
    public void meet(Student student) {
        SkeletonMenu.startCall("Student.meet(Student)");
        SkeletonMenu.endCall();
    }

    
    @Override
    protected void callOnEnter(Room room) {
        room.onEnter(this);
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
        SkeletonMenu.startCall("Student.effectConsumed()");
        super.effectConsumed(effect);
        Item item = effect.getItem();
        KillImmunity killImmunity = findKillImmunityByItem(item);
        if (killImmunity != null) {
            killImmunities.remove(killImmunity);
            item.removeEffect();
            if (inventory.contains(item)) {
                item.use(location, this);
            }
        }
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer){
        printer.startPrintObject("Student");
        printer.printField("location", this.location);
        printer.printField("stunned", this.stunned);
        printer.printFields("inventory", this.inventory);
        printer.printFields("killImmunities", this.killImmunities);
        printer.printFields("poisonImmunities", this.poisonImmunities);
        printer.printField("souls", this.souls);
        printer.endPrintObject();
    }
}
