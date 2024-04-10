package kevesse_kokanyolo_kod.items;


import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

public interface IItem {

    /**
     * A tárgy használata
     *
     * @param room   a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    void use(Room room, AcademicPerson academicPerson);

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt. Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    void accept(AcademicPerson academicPerson);

    /**
     * Null-ra állítja a tárgyhoz tartozó Effect-et
     */
    void removeEffect();

    public void printState(Printer printer, LabyrinthBuilder builder);

}
