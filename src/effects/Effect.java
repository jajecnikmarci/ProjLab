package effects;

import items.Item;

/**
 *
 */
public abstract class Effect {
    /**
     *
     */
    boolean active;
    /**
     *
     */
    int duration;
    /**
     *
     */
    Item givenBy;

    /**
     *
     */
    abstract void activate();

    /**
     * @return
     */
    public boolean isActive() {
        return active;
    }
}
