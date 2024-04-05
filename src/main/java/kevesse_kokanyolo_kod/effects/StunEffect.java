package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;

/**
 * Egy játékos sokkolását elvégző effekt
 */
public class StunEffect extends RoomEffect {
    /**
     * @param givenBy A hatást kiváltó tárgy
     * @param duration A hatás élettartama. Ennyi idő után törlődik a szobából a hatás.
     * @param observer Az hatást figyelő osztály
     */
    public StunEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    /**
     * A bénítás időtartama
     */
    private static final int stunDuration = 10;

    /**
     * Lebénít egy oktatót
     *
     * @param professor Sokkolandó oktató
     */
    @Override
    public void affect(Professor professor) {
        SkeletonMenu.startCall("StunEffect.affect(Professor)");
        professor.stun(stunDuration);
        SkeletonMenu.endCall();
    }
    /**
     * Lebénít egy hallgatót
     *
     * @param student Sokkolandó hallgató
     */
    @Override
    public void affect(Student student) {
        SkeletonMenu.startCall("StunEffect.affect(Professor)");
        student.stun(stunDuration);
        SkeletonMenu.endCall();
    }
    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("StunEffect");
        printer.printField("active", this.active);  
        printer.endPrintObject();      
    }
}
