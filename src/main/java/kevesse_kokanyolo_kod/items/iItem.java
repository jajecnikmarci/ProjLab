package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.people.AcamedicPerson;
import kevesse_kokanyolo_kod.room.Room;

public interface iItem {

    /**
     * A tárgy használata
     *
     * @param room   a szoba, ahol a tárgyat használják
     * @param acamedicPerson a játékos, aki használja a tárgyat
     */
    void use(Room room, AcamedicPerson acamedicPerson);

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt. Visitor design pattern része
     *
     * @param acamedicPerson a játékos aki próbálja felvenni a tárgyat
     */
    void accept(AcamedicPerson acamedicPerson);

    /**
     * Null-ra állítja a tárgyhoz tartozó Effect-et
     */
    void removeEffect();

}
