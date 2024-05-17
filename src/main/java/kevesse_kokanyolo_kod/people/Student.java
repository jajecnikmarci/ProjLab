package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.observer.IStudentObservable;
import kevesse_kokanyolo_kod.observer.StudentObservable;
import kevesse_kokanyolo_kod.observer.StudentObserver;
import kevesse_kokanyolo_kod.room.Room;

import java.util.*;

/**
 * A Hallgató viselkedését megvalósító osztály.
 */
public class Student extends AcademicPerson implements IStudentObservable {
    /**
     * A hallgató lelkeinek számát tárolja.
     */
    private int souls;

    /**
     * Nyilvántartja, a hallgató immunitásait a halálra.
     */
    private List<Effect> killImmunities;

    /**
     * Ha egy hallgatót elbocsájtanak, vagy felveszi a logarlécet,
     * a programnak kezelnie kell a további történéseket. Ha az utolsó hallgatót is
     * elbocsájtották, a játéknak vége, az oktatók nyertek. Ha egy hallgató felvette
     * a logarlécet, akkor a hallgatók nyerték a játékot.
     */
    private StudentObservable studentObservable;


    /**
     * Létrehozza a hallgatót
     * 
     * @param room a szoba, ahova a hallgató kerül
     */
    public Student(Room room) {
        super(room);
        souls = 3;
        killImmunities = new ArrayList<>();
        studentObservable = new StudentObservable();
    }

    public int getSouls(){
        return souls;
    }

    /**
     * Megpróbálja elszipolyozni a hallgató lelkét. 
     * Ha van neki aktív immunitása, akkor az megvédi-
     * Ha van neki nem aktív immunitása, akkor azt aktiválja, ez megvédi.
     * Különben csökkenti a hallgató lelkeinek számát 1-el. 
     * Ha meghal a hallgató (lelkeinek száma=0), jelez a vezérlő felé.
     */
    public void kill() {
        SkeletonMenu.startCall("Student.kill()");
        if (killImmunities.isEmpty()) {
            souls--;
            if (souls == 0) {
                dropAll();
                studentObservable.notifyStudentKilled(this);
                SkeletonMenu.endCall(" A játékos meghalt");
                return;
            }
            SkeletonMenu.endCall("A hallgató lelkét elszipolyozták.");
            return;
        }
        for (Effect killImmunity : killImmunities) {
            if (killImmunity.isActive()) {
                SkeletonMenu.endCall("A hallgatót megvédte egy már aktív tárgya.");
                return;
            }
        }
        Effect killImmunity = killImmunities.get(0);
        killImmunity.activate();
        SkeletonMenu.endCall("A hallgatót megvédte egy most aktiválódott tárgya.");
    }

    /**
     * Eldobja a hallgató egyik tárgyát véletlenszerűen.
     */
    @Override
    public void dropRandomItem() {
        SkeletonMenu.startCall("Student.dropRandomItem()");
        int rand = (int) (Math.random() * inventory.size()); // TODO: randomness disable
        IItem item = inventory.get(rand);
        dropItem(item);
        SkeletonMenu.endCall();
    }

     /**
     * A hallgatónál legföljebb 5 tárgy lehet egyszerre.
     * @returns 5
     */
    @Override
    public int getMaxItemCount() {
        return 5;
    }

    /**
     * Hozzáadja a killImmunities-hez a paraméterként kapott immuntitást.
     *
     * @param killImmunity a hozzáadadndó killImmunity
     */
    @Override
    public void addKillImmunity(Effect killImmunity) {
        SkeletonMenu.startCall("Student.addKillImmunity(KillImmunity)");
        killImmunities.add(killImmunity);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a killImmunities-ből vagy a poisonimmunities-ből a kapott effectet, ha benne van valamelyikben.
     *
     * @param effect a hatás ami az immunitást adja
     */
    @Override
    public void removeEffect(Effect effect) {
        super.removeEffect(effect); // Ha poisoneffect kitörli
        killImmunities.remove(effect);
    }

    /**
     * A paraméterként kapott Transistor tárgyat hozzáadja a Student tárgyaihoz.
     * Beállítja a transzisztor tulajdonosát a Student-re.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param transistor a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Transistor transistor) {
        SkeletonMenu.startCall("Student.acceptItem(Transistor)");
        this.addItem(transistor);
        transistor.setOwner(this);
        location.removeItem(transistor);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott SlideRule tárgyat hozzáadja a Student tárgyaihoz.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * A hallgatók nyerték a játékot, jelez a vezérlőnek a slideRulePicked metódus meghívásával.
     *
     * @param slideRule a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        SkeletonMenu.startCall("Student.acceptItem(SlideRule)");
        this.addItem(slideRule);
        location.removeItem(slideRule);

        studentObservable.notifySlideRulePicked();
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott TVSZ tárgyat hozzáadja a Student tárgyaihoz.
     * A tárgy felvételét követően a játékos megkapja az TVSZ tárgyhoz tartozó hatást.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * 
     * @param tvsz a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        SkeletonMenu.startCall("Student.acceptItem(TVSZ)");
        this.addItem(tvsz);
        this.addKillImmunity(tvsz.getEffect()); 
        location.removeItem(tvsz);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott Glass tárgyat hozzáadja a Student tárgyaihoz.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param glass a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Glass glass) {
        SkeletonMenu.startCall("Student.acceptItem(Glass)");
        this.addItem(glass);
        location.removeItem(glass);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott Rug tárgyat hozzáadja a Student tárgyaihoz.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
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
     * Interakcióba lép egy oktatóval, elszipolyozódik a lelke.
     *
     * @param professor a hallgatóval interakcióba lépő oktató
     */
    @Override
    public void meet(Professor professor) {
        SkeletonMenu.startCall("Student.meet(Professor, Room)");
        kill();
        SkeletonMenu.endCall();
    }

    /**
     * Interakcióba lép egy másik hallgatóval, nem csinál semmit.
     *
     * @param student a hallgatü, akivel találkozik
     */
    @Override
    public void meet(Student student) {
        SkeletonMenu.startCall("Student.meet(Student)");
        SkeletonMenu.endCall();
    }

    /**
     * Meghívja a szoba onEnter metódusát átadva magát paraméterként, mint Student. 
     * @param room a szoba, ahova a hallgató érkezik
     */
    @Override
    protected void callOnEnter(Room room) {
        room.onEnter(this);
    }

    /**
     * Felülírja az AcademicPerson effectConsumed metódusát, hogy a hallgatót megfelelően kezelje.
     * felhasználja az eredeti metódust, hogy a PoisonImmunityket kezelje.
     * 
     * Ha lejárt egy killimmunity effect akkor a játékos törli az effektet a killimmunity listájából, 
     * valamint az effektet adó tárgy use metódusát meghívja, ha a tárgy még a játékosnál van, 
     * ezzel jelezve neki a használódást.
     * 
     */
    @Override
    public void effectConsumed(Effect effect) {
        SkeletonMenu.startCall("Student.effectConsumed()");
        super.effectConsumed(effect); // Kezeli a mérgezés lejártát, ha mérgezés volt az effect
        
        Item item = effect.getItem();
        item.removeEffect();
        for (Effect e: killImmunities) {
            if (e == effect) {
                removeEffect(effect); // Előbb töröljük az effectet
                item.removeEffect(); 
                if (inventory.contains(item)) { // Majd jelezzük a tárgynak, hogy használódotzt
                    item.use(location, this);
                }
                break;
            }
        }
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder){
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("location", builder.getInstanceName(this.location));
        printer.printField("stunned", this.stunned);
        printer.printFields("inventory", this.inventory, builder);
        printer.printFields("killImmunities", "KillImmunity", this.killImmunities.size());
        printer.printFields("poisonImmunities", "PoisonImmunity", this.poisonImmunities.size());
        printer.printField("souls", this.souls);
        printer.endPrintObject();
    }

    @Override
    public void notifyStudentKilled(Student student) {
        studentObservable.notifyStudentKilled(student);
    }

    @Override
    public void notifySlideRulePicked() {
        studentObservable.notifySlideRulePicked();
    }

    @Override
    public void addObserver(StudentObserver observer) {
        studentObservable.addObserver(observer);
    }
}
