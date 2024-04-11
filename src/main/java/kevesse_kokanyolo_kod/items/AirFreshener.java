package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

public class AirFreshener extends Item{
    /**
     * Az AirFreshener használata. Megtisztítja a szobát a mérgezőségtől, ahol a tárgyat használták,
     * a szoba megszűnik mérgezőnek lenni. A tárgy törölteti magát a felhasználójával.
     *
     * @param room   a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("AirFreshener.use(Room, Player)");
        room.clearPoisonEffects();
        academicPerson.removeItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt. 
     * Visitor design pattern része
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("AirFreshener.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Felülírja az IItem interfész AirFreshener interactItem metódusát,
     * felügyeli, hogy ne kerülhessen két AirFreshener tárgy az AcademicPerson-höz
     * 
     * @param airFreshener a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    @Override
    public boolean interactItem(AirFreshener airFreshener) {
        return true;
    }

    /**
     * Visitor design pattern része. 
     * @param item a tárgy, amivel interakcióba lép.
     */
    @Override
    public boolean interact(IItem item) {
        return item.interactItem(this);
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }
}
