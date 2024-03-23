package effects;

import items.Item;

/**
 *
 */
public abstract class Effect {
    Effect(Item givenBy) {
        this.givenBy = givenBy;
    }

    /**
     *
     */
    private boolean active;
    /**
     *
     */
    private int duration;
    /**
     *
     */
    private Item givenBy;

    /**
     *
     */
    public abstract void activate();

    /**
     * @return
     */
    public boolean isActive() {
        return active;
    }
}
