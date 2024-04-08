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


    
    boolean interactItem(AirFreshener airFreshener);
    boolean interactItem(Camembert camembert);
    boolean interactItem(FFP2 ffp2);
    boolean interactItem(Glass glass);
    boolean interactItem(Rug rug);
    boolean interactItem(SlideRule slideRule);
    boolean interactItem(Transistor transistor);
    boolean interactItem(TVSZ tvsz);
    boolean interactItem(FakeFFP2 fakeFFP2);
    boolean interactItem(FakeSlideRule fakeSlideRule);
    boolean interactItem(FakeTVSZ fakeTVSZ);

    /**
     * 
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
