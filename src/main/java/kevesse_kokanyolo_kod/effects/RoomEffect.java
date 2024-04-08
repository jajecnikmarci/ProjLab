package kevesse_kokanyolo_kod.effects;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * Egy hatás, ami egy szobában lévő játékosokra hat.
 */
public abstract class RoomEffect extends Effect {
    protected RoomEffect(Item givenBy, int duration, EffectConsumedObserver observer) {
        super(givenBy, duration, observer);
    }

    
    /**
     * 
     * @param academicPerson
     */
    public abstract void affect(AcademicPerson academicPerson);

    @Override
    public void activate() {
        SkeletonMenu.startCall("RoomEffect.activate()");
        timer = new Timer(this);
        active = true;
        timer.start(getDuration());
        SkeletonMenu.endCall();
    }
}
