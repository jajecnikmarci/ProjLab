package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.PoisonImmunity;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;
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
    public void use(Room room, AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("FFP2.use(Room, Player)");
        if (immunityLength <= 0) {
            acamedicPerson.removeItem(this);
            //TODO Mérgezni a player-t ha elhasználódott a maszk
            SkeletonMenu.endCall("A tárgy elhasználódott");
            return;
        }
        PoisonImmunity poisonImmunity = new PoisonImmunity(this, immunityLength, acamedicPerson);
        acamedicPerson.addPoisonImmunity(poisonImmunity);
        effect = poisonImmunity;
        immunityLength -= 2;
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("FFP2.accept(Player)");
        acamedicPerson.acceptItem(this);
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
    public void printState(Printer printer) {
        printer.startPrintObject("FFP2");
        printer.printField("immunityLength", this.immunityLength);
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }

}
