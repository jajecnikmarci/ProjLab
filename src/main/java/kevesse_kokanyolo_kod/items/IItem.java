package kevesse_kokanyolo_kod.items;


import kevesse_kokanyolo_kod.items.fakes.FakeFFP2;
import kevesse_kokanyolo_kod.items.fakes.FakeSlideRule;
import kevesse_kokanyolo_kod.items.fakes.FakeTVSZ;
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
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt. 
     * Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    void accept(AcademicPerson academicPerson);

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * Az AirFreshener-nek felül kell írnia.
     * 
     * @param airFreshener a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(AirFreshener airFreshener){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * A Camembert-nek felül kell írnia.
     * 
     * @param camembert a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(Camembert camembert){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * Az FFP2-nek felül kell írnia.
     * 
     * @param ffp2 a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(FFP2 ffp2){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * A Glass-nak felül kell írnia.
     * 
     * @param glass a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(Glass glass){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * A Rug-nak felül kell írnia.
     * 
     * @param rug a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(Rug rug){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * A SlideRule-nak felül kell írnia.
     * 
     * @param slideRule a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(SlideRule slideRule){
        return false;
    }

    /**
     * Ezt a tranzisztor osztálynak felül kell írnia 
     * és interakcióba kell léptetni a felveendő tranzisztort a már birtokolt Transistorral, ha van. 
     * A Transistor a többi tárggyal nem lép interakcióba.
     * 
     * @param transistor a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(Transistor transistor){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * A TVSZ-nek felül kell írnia.
     * 
     * @param tvsz a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(TVSZ tvsz){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     *
     * @param fakeFFP2 a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(FakeFFP2 fakeFFP2){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * 
     * @param fakeSlideRule a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(FakeSlideRule fakeSlideRule){
        return false;
    }

    /**
     * Amikor az AcademicPerson felvesz egy tárgyat, 
     * ha már van ilyen típusú tárgy az AcademicPerson-nél,
     * igaz értékkel tér vissza és nem veheti fel a játékos a tárgyat,
     * ellenkező esetben a játékos felveheti.
     * A fakeTVSZ-nek felül kell írnia.
     * 
     * @param fakeTVSZ a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    public default boolean interactItem(FakeTVSZ fakeTVSZ){
        return false;
    }

    /**
     * Egy interakció során ez hívódik meg, ez még az általános IItemet fogadja
     * Ez az implementációkban meghívja már az adott tárgyra vonatkozó interactItem függvényt
     * Az interactItem alapból (lásd fentebb) mindig false-sal tér vissza, azaz a tárgyak nem azonosak
     * Viszont minden tárgy a saját típusával valót felülírja, és true-val tér vissza, vagyis azonosak a tárgyak
     * 
     * @param item a tárgy, amivel interakcióba lép
     * @return igaz, ha a 2 tárgy azonos fajta, egyébként hamis
     */
    boolean interact(IItem item);

    /**
     * Törli a tárgyhoz tartozó Effect-et
     */
    void removeEffect();

    public void printState(Printer printer, LabyrinthBuilder builder);

}
