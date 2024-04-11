package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Student;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Tranzisztor tárgyat reprezentáló osztály
 */
public class Transistor extends Item {
    /**
     * A tranzisztor párja mellyel összekapcsolásra kerül
     */
    private Transistor pair;

    /**
     * A szoba ahol a tranzisztor található eldobás után
     */
    private Room room;

    /**
     * A tranzisztor tulajdonosa
     */
    private Student owner;

    /**
     * A tranzisztor párjának megadása
     *
     * @param transistor a tranzisztor párja
     */
    public void setPair(Transistor transistor) {
        SkeletonMenu.startCall("Transistor.setPair(Transistor)");
        this.pair = transistor;
        SkeletonMenu.endCall();
    }

    /**
     * Beállítja atranzisztor tulajdonosát a megadott hallgatóra.
     *
     * @param student a megadott halglató
     */
    public void setOwner(Student student) {
        this.owner = student;
    }

    /**
     * A transzisztor használatánál, ha a játékosnál 2 párosított tranzisztor van,
     * akkor a tranzisztort a szobához kapcsolja és eltávolítja a játékos tárgyai közül.
     * ha a játékosnál 1 párosított tranzisztor van, akkor megpróbálja a teleportálást
     * ha ez lehetséges, akkor a játékost a másik szobába teleportálja és felcseréli a tranzisztorokat
     * (eredeti eltávolítása, szoba beállítása, másik tranzisztor hozzáadása)
     * különben nem történik semmi
     *
     * @param room   a szoba, ahol a tranzisztor használva lett
     * @param academicPerson a játékos aki használja a tranzisztort
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Transistor.use(Room, Player)");

        if(this.pair==null){
            academicPerson.checkHasItem(this);

            if(this.pair!=null)
                SkeletonMenu.endCall("A tranzisztor párosítva lett egy másikkal.");

        }else if (this.pair != null && this.pair.room == null) {

            this.setRoom(room);
            academicPerson.removeItem(this);
            SkeletonMenu.endCall("A tranzisztor a szobához lett hozzáadva.");

        } else if (this.pair != null && this.pair.room.canPlayerEnter()) {
            room.removePlayer(academicPerson);

            this.pair.room.addPlayer(academicPerson);
            this.pair.room.onEnter(owner);

            this.setRoom(room);
            this.pair.room = null;

            academicPerson.removeItem(this);
            academicPerson.addItem(this.pair);
            SkeletonMenu.endCall("A játékos át lett teleportálva a másik szobába.");
        } else SkeletonMenu.endCall("A játékos nem lett elteleportálva a másik szobába.");
    }

    /**
     * Beállítja a tranzisztor szobáját ahol eldobták
     *
     * @param room a szoba ahol a tranzisztor található
     */
    public void setRoom(Room room) {
        SkeletonMenu.startCall("Transistor.setRoom(Room)");
        this.room = room;
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
        SkeletonMenu.startCall("Transistor.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
    
    /**
     * Működés: Ha a tranzisztornak van párja, akkor nem lehet még egy tranzisztort hozzáadni a játékoshoz, 
     * ezért tér vissza igazzal.
     * Ha a thisnek (új tranziszornak) nincs tulajdonosa, vagyis még nincs tranzisztor a játékosnál, 
     * akkor hamis értékkel tér vissza, fel lehet venni a tárgyat.
     * 
     * Különben megtörténik a tranzisztorok összekapcsolása (egymás párját bellítják a másiknak) 
     * és hamis értékkel térünk vissza, hogy fel legyen véve a tárgy. 
     * Ez a use függvény hatására mehet csak végbe, ilyenkor nincs mit felvenni.
     * 
     * @param transistor a tranzisztor, ami már a játékosnál van
     * @return igaz, ha a tárgy a játékoshoz nem adható hozzá
     */
    @Override
    public boolean interactItem(Transistor transistor) {
        //this: ezt akarjuk hozzáadni
        if(transistor.pair != null){
            return true;
        }else if(this.owner == null){
            return false;
        }

        this.pair = transistor;
        transistor.pair = this;

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
        printer.printField("pair", this.pair);
        printer.printField("room", this.room);
        printer.printField("owner", this.owner);
        printer.endPrintObject();
    }
}
