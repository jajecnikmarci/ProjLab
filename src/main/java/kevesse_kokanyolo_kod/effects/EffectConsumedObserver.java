package kevesse_kokanyolo_kod.effects;

/**
 * Az effektumok lejártát figyelő interfész, mely observer design patternt valósít meg.
 */
public interface EffectConsumedObserver {
    /**
     * A hatás lejártakor kezeli a végrehajtandó eseményeket.
     */
    void effectConsumed(Effect effect);
}
