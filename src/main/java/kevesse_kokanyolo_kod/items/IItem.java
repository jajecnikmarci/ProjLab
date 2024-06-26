package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.items.fakes.FakeFFP2;
import kevesse_kokanyolo_kod.items.fakes.FakeSlideRule;
import kevesse_kokanyolo_kod.items.fakes.FakeTVSZ;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * A tárgyak közös interfésze. 
 * 
 * Visitor Patternt valósít meg, a tárgyak interakcióba léptetéséhez. 
 * (Egy tárgyból nem vehet fel egynél többet a játékos, kivéve tranzisztorok. Ezeknek a párosítását intézi.)
 * Az összes interactItem függvvénynek alapértelmezett implementációt ad (ami hamissal tér vissza), megengedi a tárgy felvételét.
 * Minden tárgynak a hozzá tartozó interactItem metódust felül kell írnia, ha nem akarja, hogy a játékos felvegyen belőle kettőt (vagy tranzisztor esete).
 */
public interface IItem {
    /**
     * A tárgy használata 
     *
     * @param room   a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    public void use(Room room, AcademicPerson academicPerson);

    /**
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt. 
     * Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    public void accept(AcademicPerson academicPerson);

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
     * Minden tárgynak felül kell írnia és meg kell hívnia a kapott tárgyra  az interactItem függvényt átadva magát a saját típusaként.
     * 
     * @param item a tárgy, amivel interakcióba lép
     * @return igaz, ha a 2 tárgy azonos fajta, egyébként hamis
     */
    public boolean interact(IItem item);

    /**
     * Visszaadja, hogy a tárgy manuálisan használható-e a játékosnak. Pl.: true -> nem használható manuálisan (játékos direkt parancsára)
     * 
     * @return passzív-e a tárgy
     */
    public default boolean isPassive(){
        return false;
    }
    
    /**
     * Visszaadja a tárgy leírását (ha van)
     * 
     * @return ha van leírás akkor a leírás (String), ha nincs null
     */
    public default String getDescription(){
        return null;
    }

    /**
     * Törli a tárgyhoz tartozó Effect-et
     */
    public void removeEffect();

    /**
     * Visszaadja a tárgyhoz tartozó effektet.
     */
    public Effect getEffect();

    /**
     * Ha egy tárgy eldobásra kerül, akkor ez a függvény hívódik meg.
     * Az implementációkban felül kell írni, ha a tárgyhoz tartozik valamilyen speciális viselkedés
     * @param person
     */
    public default void onDrop(AcademicPerson person) {} 


    public void printState(Printer printer, LabyrinthBuilder builder);

}
