package effects;

import items.Item;

/**
 *
 */
public class PoisonImmunity extends Effect {

    public PoisonImmunity(Item givenBy, int duration) {
        super(givenBy, duration);
    }

    /**
     * A méreg immunitás aktiválásakor, egy időzítő indul el,
     * az időzítő lejártáig a játékosnak mérgezés elleni
     * védettséget nyújt.
     */
    @Override
    public void activate() {
        System.out.println("PoisonImmunity.activate()");
        Timer timer = new Timer();
        active = true;
        timer.start(getDuration());
        //TODO notify metódus feliratkozás majd active = false
    }
}
