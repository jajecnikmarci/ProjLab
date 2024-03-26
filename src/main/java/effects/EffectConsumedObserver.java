package main.java.effects;

/**
 * Az effektumok lejártát figyelő interfész, mely observer design patternt valósít meg.
 */
public interface EffectConsumedObserver {
    /**
     * Az effektum lejártakor meghívódó metódus
     */
    public void effectConsumed(Effect effect);
}
