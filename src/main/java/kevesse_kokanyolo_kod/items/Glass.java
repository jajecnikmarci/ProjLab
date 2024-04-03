package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Szent Sörös Pohár tárgyat reprezentáló osztály
 */
public class Glass extends Item {
    private boolean hasBeenUsed=false;
    /**
     * A Glass használatánál (csak hallgató használhatja) a hallgató védett
     * állapotba kerül az oktatók támadásaival szemben a Glass használatától
     * kezdve 10 másodpercig. A védettség egy KillImmunity, ami aktiválódik
     * amint aktiválja a hallgató a tárgyat.
     *
     * @param room   a szoba, ahol a tárgyat használják
     * @param academicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Glass.use(Room, Player)");
        if(hasBeenUsed) {
            if (effect != null) {
                academicPerson.dropRandomItem();
                SkeletonMenu.endCall("A tárgyat úgy próbálták használni, hogy már egyszer ezt megtették");
                return;
            } else {
                academicPerson.removeKillImmunity(this);
                SkeletonMenu.endCall("A tárgy már elhasználódott ezért törlésre került");
                return;
            }
        }
        KillImmunity killImmunity = new KillImmunity(this, 10, academicPerson);
        academicPerson.addKillImmunity(killImmunity);
        killImmunity.activate();
        effect = killImmunity;
        hasBeenUsed=true;
        SkeletonMenu.endCall();
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Glass.accept(Player)");
        academicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("Glass");
        printer.printField("hasBeenUsed", this.hasBeenUsed);
        printer.printField("effect", this.effect);
        printer.endPrintObject();
    }
}
