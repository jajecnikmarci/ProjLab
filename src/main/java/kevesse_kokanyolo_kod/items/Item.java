package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.Effect;

import kevesse_kokanyolo_kod.items.fakes.*;

/**
 * Egy tárgyat reprezentáló osztály
 */
public abstract class Item implements IItem {
    protected Effect effect;
    @Override
    public void removeEffect() {
        effect = null;
    }

    public boolean interactItem(AirFreshener airFreshener){
        return false;
    }
    public boolean interactItem(Camembert camembert){
        return false;
    }
    public boolean interactItem(FFP2 ffp2){
        return false;
    }
    public boolean interactItem(Glass glass){
        return false;
    }
    public boolean interactItem(Rug rug){
        return false;
    }
    public boolean interactItem(SlideRule slideRule){
        return false;
    }
    public boolean interactItem(Transistor transistor){
        return false;
    }
    public boolean interactItem(TVSZ tvsz){
        return false;
    }
    public boolean interactItem(FakeFFP2 fakeFFP2){
        return false;
    }
    public boolean interactItem(FakeSlideRule fakeSlideRule){
        return false;
    }
    public boolean interactItem(FakeTVSZ fakeTVSZ){
        return false;
    }

}
