package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.PoisonImmunity;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * FFP2-es maszkot reprezentáló osztály.
 * Használatakor a Playerre PoisonImmunity-t rak, ami 10 másodpercig ad védelmet poison ellen.
 */
public class FFP2 extends Item {
    /**
     * 10 másodpercig tartó immunitás a poison hatásával szemben.
     */
    private int immunityLength = 10;

    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("FFP2.use(Room, Player)");
        if (immunityLength <= 0) {
            academicPerson.removeItem(this);
            //TODO Mérgezni a player-t ha elhasználódott a maszk
            SkeletonMenu.endCall("A tárgy elhasználódott");
            return;
        }
        PoisonImmunity poisonImmunity = new PoisonImmunity(this, immunityLength, academicPerson);
        academicPerson.addPoisonImmunity(poisonImmunity);
        effect = poisonImmunity;
        immunityLength -= 2;
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("FFP2.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Visszaadja az immunitás hosszát.
     *
     * @return az immunitás hossza
     */
    public int getImmunityLength() {
        return immunityLength;
    }
    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("immunityLength", this.immunityLength);
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }

    @Override
    public boolean interactItem(FFP2 ffp2) {
        return true;
    }

    @Override
    public boolean interact(IItem item) {
        return  item.interactItem(this);
    }

}
