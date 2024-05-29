package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class SlideRule extends Item {
    /**
     * A logarléc használata. Felvételekor véget ér a játék így nem csinál semmit.
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
    }

    /**
     * Visszaadja, hogy a tárgy manuálisan használható-e a játékosnak. Pl.: true -> nem használható manuálisan (játékos direkt parancsára)
     * 
     * @return passzív-e a tárgy
     */
    public boolean isPassive(){
        return true;
    }

    /**
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt. 
     * Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("SlideRule.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Felülírja az IItem interfész SlideRule interactItem metódusát,
     * felügyeli, hogy ne kerülhessen két SlideRule tárgy az AcademicPerson-höz
     * 
     * @param slideRule a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    @Override
    public boolean interactItem(SlideRule slideRule) {
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
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }
}
