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
     * Elhelyezi ezt a tárgyat a szobában, azaz hozzáad egy StunEffectet a szobához,
     * ez az effekt fél percig tart és kitörli a tárgyat a játékos tárgyai közül.
     *
     * @param room a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Rug.use(Room, Player)");
        StunEffect stunEffect = new StunEffect(this, 30, room);

        room.addStunEffect(stunEffect);
        effect = stunEffect;
        academicPerson.removeItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Rug.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
    
    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("effect", this.effect);  
        printer.endPrintObject();
    }

    @Override
    public boolean interactItem(Rug rug) {
        return true;
    }

    @Override
    public boolean interact(IItem item) {
        return  item.interactItem(this);
    }

}
