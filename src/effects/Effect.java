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
