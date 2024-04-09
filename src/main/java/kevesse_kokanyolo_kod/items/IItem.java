package kevesse_kokanyolo_kod.items;


import kevesse_kokanyolo_kod.items.fakes.FakeFFP2;
import kevesse_kokanyolo_kod.items.fakes.FakeSlideRule;
import kevesse_kokanyolo_kod.items.fakes.FakeTVSZ;
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

    public default boolean interactItem(AirFreshener airFreshener){
        return false;
    }
    public default boolean interactItem(Camembert camembert){
        return false;
    }
    public default boolean interactItem(FFP2 ffp2){
        return false;
    }
    public default boolean interactItem(Glass glass){
        return false;
    }
    public default boolean interactItem(Rug rug){
        return false;
    }
    public default boolean interactItem(SlideRule slideRule){
        return false;
    }
    public default boolean interactItem(Transistor transistor){
        return false;
    }
    public default boolean interactItem(TVSZ tvsz){
        return false;
    }
    public default boolean interactItem(FakeFFP2 fakeFFP2){
        return false;
    }
    public default boolean interactItem(FakeSlideRule fakeSlideRule){
        return false;
    }
    public default boolean interactItem(FakeTVSZ fakeTVSZ){
        return false;
    }

    /**
     * Egy interakció során ez hívódik meg, ez még az általános IItemet fogadja
     * Ez az implementációkban meghívja már az adott tárgyra vonatkozó interactItem függvényt
     * Az interactItem alapból (lásd fentebb) mindig false-sal tér vissza, azaz a tárgyak nem azonosak
     * Viszont minden tárgy a saját típusával valót felülírja, és true-val tér vissza, vagyis azonosak a tárgyak
     * @param item a tárgy, amivel interakcióba lép
     * @return igaz, ha a 2 tárgy azonos fajta, egyébként hamis
     */
    boolean interact(IItem item);

    /**
     * Null-ra állítja a tárgyhoz tartozó Effect-et
     */
    void removeEffect();

    public void printState(Printer printer);

}
