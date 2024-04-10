package kevesse_kokanyolo_kod.items;

import java.util.function.Consumer;
import java.util.function.Function;

import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * TVSZ Denevér Bőrre Nyomtatott Példánya tárgyat reprezentáló osztály
 */
public class TVSZ extends Item {
    /**
     * A tárgy hátralévő immunitást adásainak száma
     */
    private int timesImmune;

    public TVSZ() {
        timesImmune = 3;
    }

    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("TVSZ.use(Room, Player)");
        KillImmunity killImmunity = new KillImmunity(this, Integer.MAX_VALUE, academicPerson);
        academicPerson.addKillImmunity(killImmunity);
        timesImmune--;

        if (timesImmune == 0) {
            academicPerson.removeItem(this);
        }
        SkeletonMenu.endCall();
    }

    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("TVSZ.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("timesImmune", this.timesImmune);
        printer.printField("effect", this.effect);
        printer.endPrintObject();
    }
}
