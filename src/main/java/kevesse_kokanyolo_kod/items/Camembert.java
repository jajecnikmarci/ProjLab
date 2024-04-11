package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.PoisonEffect;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * A Dobozolt Káposztás Camembert-t reprezentáló osztály
 * Használatakor a Player-re PoisonEffect-et rak, ami 30 másodpercig tart.
 */
public class Camembert extends Item {
    /**
     * A Camembert használata. 
     * Eltávolítja a játékos tárgyai közül a Camembert-et,
     * és a szobához hozzáad egy 30 másodpercers PoisonEffect-et, valamint aktiválja azt.
     * Megmérgezteti a szobával a már benne tartózkodó játékosokat.
     * Beállítja a tárgyhoz tartozó hatást a létrehozott hatásra.
     *  
     * @param room a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Camembert.use(Room, Player)");
        PoisonEffect camembertEffect = new PoisonEffect(this, 30, room);
        room.addPoisonEffect(camembertEffect);
        camembertEffect.activate();
        effect = camembertEffect;
        room.poisonPlayers(); //Azért itt mert itt ismerjük a szobát
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
        SkeletonMenu.startCall("Camembert.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Felülírja az IItem interfész Camembert interactItem metódusát,
     * felügyeli, hogy ne kerülhessen két Camembert tárgy az AcademicPerson-höz
     * 
     * @param camembert a tárgy, amit az AcademicPerson megpróbál felvenni
     */
    @Override
    public boolean interactItem(Camembert camembert) {
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
