package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.StunEffect;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Nedves Táblatörlő Rongy tárgyat reprezentáló osztály
 */
public class Rug extends Item {
    /**
     * Ennyi ideig nem szárad ki a rongy.
     */
    private static final int lifeTime = 30;
    
    /**
     * A Rug használata.
     * A szobához hozzáad egy StunEffectet, lifeTime időtartammal (30 s). 
     * Aktiválja az effectet.
     * 
     * Beállítja a tárgyhoz tartozó hatást a létrehozott hatásra.
     * Törölteti ezt a tárgyat a játékossal.
     *
     * @param room a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Rug.use(Room, Player)");
        StunEffect stunEffect = new StunEffect(this, lifeTime, room);

        room.addStunEffect(stunEffect);
        stunEffect.activate();
        effect = stunEffect;
        academicPerson.removeItem(this);
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
        SkeletonMenu.startCall("Rug.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Felülírja az IItem interfész Rug interactItem metódusát,
     * felügyeli, hogy ne kerülhessen két Rug tárgy az AcademicPerson-höz
     * 
     * @param rug a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    @Override
    public boolean interactItem(Rug rug) {
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
