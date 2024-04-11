package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.SlideRule;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * Logarléc tárgyat reprezentáló osztály
 */
public class FakeSlideRule extends SlideRule implements FakeItem {
    /**
     * Meghívja a paraméterként kapott AcademicPerson-re a tárgyhoz tartozó acceptItem függvényt, 
     * FakeItemként átadva magát. 
     * Visitor design pattern része
     *
     * @param academicPerson a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("FakeSlideRule.accept(Player)");
        academicPerson.acceptItem((FakeItem) this);
        SkeletonMenu.endCall();
    }
}
