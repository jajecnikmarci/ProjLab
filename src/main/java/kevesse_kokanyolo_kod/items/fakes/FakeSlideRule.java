package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.SlideRule;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class FakeSlideRule extends SlideRule implements FakeItem {
    /**
     * Hozzáadja a paraméterül kapott AcademicPerson-höz a tárgyat.
     * @param academicPerson az a játékos, aki felveszi a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("FakeSlideRule.accept(Player)");
        academicPerson.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
