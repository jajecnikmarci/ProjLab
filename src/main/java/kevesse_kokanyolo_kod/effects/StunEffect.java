package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * Egy AcademicPerson lebénulását elvégző effekt.
 */
public class StunEffect extends RoomEffect {
    /**
     * A paraméterül kapott értékekkel létrehozza a StunEffect-et
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
     * Lebénítja a kapott hallgatót/oktatót
     * @param a bénítandó hallgató vagy oktató
     */
    @Override
    public void affect(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("StunEffect.affect(AcademicPerson)");
        academicPerson.stun(stunDuration);
        SkeletonMenu.endCall();
    }
    
    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject("StunEffect");
        printer.printField("active", this.active);
        printer.printField("duration", this.duration);
        printer.printField("givenBy", builder.getInstanceName(this.givenBy));
        printer.endPrintObject();      
    }
}
