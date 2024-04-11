package kevesse_kokanyolo_kod.items;


import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * TVSZ Denevér Bőrre Nyomtatott Példánya tárgyat reprezentáló osztály
 */
public class TVSZ extends Item {
    /**
     * A tárgy hátralévő immunitást adásainak száma
     */
    private int timesImmune;

    /**
     * Létrehozza a TVSZ tárgyat
     */
    public TVSZ() {
        timesImmune = 3;
    }

    /**
     * A TVSZ használata. A játékosnak ad egy KillImmunity-t, ami 0 időtartamú, nem aktív. (Akkor aktiválódik ,ha a játékosst megpróbálják megölni)
     * Csökkenti a timesImmune-t eggyel.
     * Ha a csökkentés után a timesImmune elérte a 0-t, törölteti a tárgyat a játékossal.
     *
     * @param room a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("TVSZ.use(Room, Player)");
        KillImmunity killImmunity = new KillImmunity(this, 0, academicPerson);
        academicPerson.addKillImmunity(killImmunity);
        timesImmune--;

        if (timesImmune == 0) {
            academicPerson.removeItem(this);
        }
        SkeletonMenu.endCall();
    }

    /**
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt. 
     * Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("TVSZ.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Felülírja az IItem interfész TVSZ interactItem metódusát,
     * felügyeli, hogy ne kerülhessen két TVSZ tárgy az AcademicPerson-höz
     * 
     * @param tvsz a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    @Override
    public boolean interactItem(TVSZ tvsz) {
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

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("timesImmune", this.timesImmune);
        printer.printField("effect", this.effect);
        printer.endPrintObject();
    }
}
