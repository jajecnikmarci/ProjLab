package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.PoisonImmunity;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * FFP2-es maszkot reprezentáló osztály. Ezt a tárgy automatikusan használódik, 
 * a játékos kézileg nem tudja használni.
 * Használódáskor a Playerre PoisonImmunity-t rak, ami immunityLength ideig védelmet ad poison ellen.
 */
public class FFP2 extends Item {
    /**
     * Megmondja, hogy a tárgy keletkezésekor, hány másodpercig tart a tárgy hatása
     */
    private static final int firstImmunityLength = 10;

    /**
     * Tárolja, hogy hány másodperccel csökkel a tárgy hatása minden használat után
     */
    private static final int immunityLengthDecrease = 2;
    
    /**
     * A maszkhoz tartozó következő védettség hossza.
     */
    private int immunityLength = firstImmunityLength;
    
    /**
     * Az FFP2 használata. A tárgyat felvevő academicPerson-nek
     * immunitást ad a mérgezés hatással szemben, a használattól
     * kezdve. Ha a tárgy teljesen elhasználódott, a tárgy megsemmisül.
     * 
     * Ez a függvény akkor hívódik, ha a tárgyhoz tartozó hatás lejárt. 
     * (Az első hatás a tárgy felvételekor adódik a játékoshoz)
     * Ha a tárgy elhasználódott (immunityLength = 0) akkor törölteti a tárgyat a játékostól.
     * Egyébként csökkenti 2 másodperccel az immunityLength-et.
     * Létrehoz egy PoisonImmunity hatást immunityLength hosszal és hozzáadja a játékoshoz.
     * Beállítja a tárgy effect attribútumát a létrehozott hatásra. 
     * (Nem aktiválja a létrehozott hatást, azt majd csak akkor kell, ha mérgeződik.)
     * 
     * Ha a védettség lejárt és a szoba továbbra is mérgező, 
     * a játékos úőjbóli mérgezését a szoba felelőssége kezelni.
     *
     * @param room a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("FFP2.use(Room, Player)");
        immunityLength -= immunityLengthDecrease;
        if (immunityLength <= 0) {
            academicPerson.removeItem(this);
            SkeletonMenu.endCall("A tárgy elhasználódott");
            return;
        }
        PoisonImmunity poisonImmunity = new PoisonImmunity(this, immunityLength, academicPerson);
        academicPerson.addPoisonImmunity(poisonImmunity);
        effect = poisonImmunity;
        notifyStateChanged();
        SkeletonMenu.endCall();
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
     * Létrehoz egy PoisonImmunityt firstImmunityLength hosszal. Beállítja a hatását erre.
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt. 
     * Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("FFP2.accept(Player)");
        this.effect = new PoisonImmunity(this, firstImmunityLength, academicPerson);
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

  
    /**
     * Felülírja az IItem interfész FFP2 interactItem metódusát,
     * felügyeli, hogy ne kerülhessen két FFP2 tárgy az AcademicPerson-höz
     * 
     * @param ffp2 a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    @Override
    public boolean interactItem(FFP2 ffp2) {
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

    /**
     * Ha a játékos eldobja az FFP2-t, akkor a játékosról leveszi az immunitást.
     */
    @Override
    public void onDrop(AcademicPerson person) {
        SkeletonMenu.startCall("FFP2.onDrop(AcademicPerson)");
        person.removeEffect(this.effect);
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("immunityLength", this.immunityLength);
        printer.printFields("effect", "PoisonImmunity", 1);  
        printer.endPrintObject();      
    }
}
