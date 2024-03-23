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

    public Effect(Item givenBy, int duration) {
        this.givenBy = givenBy; 
        this.duration = duration;
    }

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
