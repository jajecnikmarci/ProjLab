package effects;

import items.Item;

/**
 *
 */
public class KillImmunity extends Effect {
    public KillImmunity(Item givenBy, int duration) {
        super(givenBy, duration);
    }

    /**
     * @param givenBy
     */
    KillImmunity(Item givenBy) {
        super(givenBy);
    }

    /**
     *
     */
    @Override
    public void activate() {

    }
}
