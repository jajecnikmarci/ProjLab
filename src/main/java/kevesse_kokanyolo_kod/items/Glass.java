package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Szent Sörös Pohár tárgyat reprezentáló osztály
 */
public class Glass extends Item {
    /**
     * Ennyi ideig ad védettséget a tárgy
     */
    private static final int killImmunityLength = 10;
    /**
     * Megmondja, hogy a tárgy használt-e, igaz, hamis ha nem volt használva,
     * ellenkező esetben igaz.
     */
    private boolean hasBeenUsed=false;
    
    /**
     * A Glass használata (csak hallgató használhatja).
     * Az AcademicPerson-höz hozzáad egy 10 másodpercers KillImmunity effektet, valamint aktiválja azt.
     * A hallgató védett állapotba kerül az oktatók támadásaival szemben,
     * a Glass használatát követő 10 másodpercig.
     * Beállítja a tárgyhoz tartozó hatást a létrehozott hatásra.
     * 
     * Ha a hallgató már használta a tárgyat és újra használni próbálja, 
     * akkor el kell dobnia az egyik tárgyát véletlenszerűen. 
     * 
     * Az effekt lejártakor a szoba meghívja a removeEffect metódust, 
     * ezzel jelezve, hogy a hatás lejárt, majd meg kell hívnia ezt a függvényt, jelezve az elhasználódást
     * ami törölteti ezt a tárgyat a játékossal.
     * 
     * @param room a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Glass.use(Room, Player)");
        if(hasBeenUsed) {
            if (effect != null) {
                academicPerson.dropRandomItem();
                notifyStateChanged();
                SkeletonMenu.endCall("A tárgyat úgy próbálták használni, hogy már egyszer ezt megtették");
                return;
            } else {
                academicPerson.removeItem(this);
                SkeletonMenu.endCall("A tárgy már elhasználódott ezért törlésre került");
                return;
            }
        }
        KillImmunity killImmunity = new KillImmunity(this, killImmunityLength, academicPerson);
        academicPerson.addKillImmunity(killImmunity);
        killImmunity.activate();
        effect = killImmunity;
        hasBeenUsed=true;
        SkeletonMenu.endCall();
    }

    /**
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt. 
     * Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Glass.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Felülírja az IItem interfész Glass interactItem metódusát,
     * felügyeli, hogy ne kerülhessen két Glass tárgy az AcademicPerson-höz
     * 
     * @param glass a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    @Override
    public boolean interactItem(Glass glass) {
        return true;
    }

    /**
     * Visitor design pattern része. 
     * @param item a tárgy, amivel interakcióba lép.
     */
    @Override
    public boolean interact(IItem item) {
        return  item.interactItem(this);
    }

    /**
     * Ha a tárgyat eldobták, és már aktiválva volt,
     * akkor a hatása megmarad, de a tárgy megszűnik.
     */
    @Override
    public void onDrop(AcademicPerson person) {
        SkeletonMenu.startCall("Glass.onDrop(AcademicPerson)");
        if(effect.isActive()) {
            person.getLocation().removeItem(this);
        }
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("hasBeenUsed", this.hasBeenUsed);
        printer.printField("effect", this.effect);
        printer.endPrintObject();
    }
}
