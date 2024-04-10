package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.effects.EffectConsumedObserver;
import kevesse_kokanyolo_kod.effects.KillImmunity;
import kevesse_kokanyolo_kod.effects.PoisonImmunity;
import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.items.fakes.FakeItem;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.room.Room;

import java.util.*;

/**
 * A játékost reprezentáló osztály
 */
public abstract class AcademicPerson extends Person implements PickUpVisitor, EffectConsumedObserver {
    /**
     * A játékos tárgylistája.
     */
    List<IItem> inventory;

    /**
     * A játékos által használható mérgezés elleni immunitások listája.
     */
    List<PoisonImmunity> poisonImmunities;



    public boolean isStunned() {
        return stunned;
    }

    protected boolean stunned;

    protected AcademicPerson(Room r) {
        super(r);
        inventory = new ArrayList<>();
        poisonImmunities = new ArrayList<>();
    }

    /**
     * Hozzáadja a player tárgylistájához a paraméterként kapott tárgyat.
     *
     * @param item a hozzáadandó tárgy
     */
    public void addItem(Item item) {
        SkeletonMenu.startCall("Player.addItem(Item)");
        inventory.add(item);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a player tárgylistájából a paraméterként kapott tárgyat.
     *
     * @param item a kitörlendő tárgy
     */
    public void removeItem(Item item) {
        SkeletonMenu.startCall("Player.removeItem(Item)");
        inventory.remove(item);
        SkeletonMenu.endCall();
    }

    /**
     * A jelenlegi szobára meghívja a popItem függvényt.
     * Ezzel elindítja a Visitor működést, mely végén felveszi a szoba tárgylistájának legfelső tárgyát.
     */
    public void pickUpItem() {
        SkeletonMenu.startCall("Player.pickUpItem()");
        location.popItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Megnézi, hogy a játékosnak van-e a paraméterként kapott tárgya. 
     * Bővebben megnézi, hogy a játékos tűárgyai tiltják-e a paraméterként kapott tárgy felvételét.
     * @param item a keresett tárgy
     * @return true, ha a játékosnak van a tárgya, false egyébként
     */
    public boolean checkHasItem(IItem item){
        SkeletonMenu.startCall("Player.pickUpItem()");
        for(IItem i : inventory){
            if(i != item && i.interact(item)){
                SkeletonMenu.endCall();
                return true;
            }
        }
        SkeletonMenu.endCall();
        return false;
    }

    /**
     * A paraméterül kapott item-et eldobja a játékos, nem
     * tartozik többé a játékoshoz az item.
     *
     * @param item
     */
    public void dropItem(Item item) {
        SkeletonMenu.startCall("Player.dropItem(Item)");
        inventory.remove(item);
        location.addItem(item);
        SkeletonMenu.endCall();
    }

    /**
     * A játékos elejti az összes nála lévő item-et, egy
     * item sem fog a játékoshoz tartozni.
     */
    public void dropAll() {
        SkeletonMenu.startCall("Player.dropAll()");
        inventory.forEach(item -> location.addItem(item));
        inventory.clear();
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott tárgyat használja a játékos.
     *
     * @param item a használandó tárgy
     */
    public void useItem(Item item) {
        SkeletonMenu.startCall("Player.useItem(Item)");
        item.use(location, this);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a poisonImmunities-hez a paraméterként kapott immuntitást.
     *
     * @param poisonImmunity
     */
    public void addPoisonImmunity(PoisonImmunity poisonImmunity) {
        SkeletonMenu.startCall("Player.addPoisonImmunity(PoisonImmunity)");
        poisonImmunities.add(poisonImmunity);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a poisonImmunities-ból a paraméterként kapott tárgyhoz tartozó immunitást.
     *
     * @param item A tárgy ami az immunitást adja
     */
    public void removePoisonImmunity(Item item) {
        SkeletonMenu.startCall("Player.removePoisonImmunity(Item)");
        Effect effectToRemove = findPoisonImmunityByItem(item);
        poisonImmunities.remove(effectToRemove);
        SkeletonMenu.endCall();
    }

    @Override
    public void meet(Cleaner cleaner, Room room) {
        SkeletonMenu.startCall("Player.meet(Cleaner, Room)");
        if (!stunned) {
            this.leaveRoom();
            SkeletonMenu.endCall("A játékos elhagyta a szobát.");
        }
        else {
            SkeletonMenu.endCall("A játékos nem tudott elhagyni a szobát, mert bénult.");
        }
    }

    /**
     * A játékos duration ideig nem tud mozogni.
     */
    public void stun(int duration) {
        SkeletonMenu.startCall("Player.stun(Duration)");
        Timer timer = new Timer();
        stunned=true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stunned = false;
                SkeletonMenu.endCall("Játékos már nem bénult");
            }
        }, duration * 1000L);
        SkeletonMenu.endCall("A játékos lebénult.");
    }

    /**
     * A játékost ha mérgezés éri és nincs védettsége a méreg
     * ellen a játékosnak, mozgásképtelenné válik a játékos 5
     * másodpercre és elejti az összes nála lévő tárgyat. Ha
     * a játékos tud védekezni a méreg ellen, a játékos nem
     * válik mozgásképtelenni és nem ejti el a nála lévő
     * tárgyakat.
     */
    @Override
    public void poison() {
        SkeletonMenu.startCall("Player.poison()");
        if (poisonImmunities.isEmpty()) {
            this.dropAll();
            this.stun(5);
            SkeletonMenu.endCall("A játékos megmérgeződött.");
            return;
        }
        if (poisonImmunities.stream().anyMatch(PoisonImmunity::isActive)) {
            SkeletonMenu.endCall("A játékos nem mérgeződött meg, mert volt aktív immunitása.");
            return;
        }
        PoisonImmunity poisonImmunity = poisonImmunities.get(0);
        poisonImmunity.activate();
        SkeletonMenu.endCall("A játékos nem mérgeződött meg, mert egy tárgy megvédte.");
    }




    /**
     * Megkeresi a paraméterként kapott tárgyhoz tartozó immunitást a poisonImmunities-ból.
     *
     * @param item a tárgy, aminek az immunitását keresi
     * @return a tárgyhoz tartozó immunitás
     */
    protected PoisonImmunity findPoisonImmunityByItem(Item item) {
        for (PoisonImmunity poisonImmunity : poisonImmunities) {
            if (poisonImmunity.getItem().equals(item)) {
                return poisonImmunity;
            }
        }
        return null;
    }

    /**
     * Hozzáad egy KillImmunity-t a játékoshoz.
     * Professor nem overide-olja mivel számára nem szükséges
     *
     * @param killImmunity a hozzáadandó immunitás
     */
    public void addKillImmunity(KillImmunity killImmunity) {
    }

    /**
     * Kitörli a paraméterként kapott tárgyhoz tartozó KillImmunity-t a játékosból.
     * Professor nem overide-olja mivel számára nem szükséges
     *
     * @param item a tárgy, aminek az immunitását törli
     */
    public void removeKillImmunity(Item item) {
    }




    /**
     * Ha elhasználódott egy effekt akkor a játékosnak törölnie kell az effektet a listájából.
     *
     * @param effect az elhasznált effekt
     */
    public void effectConsumed(Effect effect) {
        SkeletonMenu.startCall("Player.effectConsumed(Effect)");
        Item item = effect.getItem();
        PoisonImmunity poisonImmunity = findPoisonImmunityByItem(item);
        if (poisonImmunity != null) {
            poisonImmunities.remove(poisonImmunity);
            item.removeEffect();
            if (inventory.contains(item)) {
                item.use(location, this);
            }
        }
        SkeletonMenu.endCall();
    }

    public void dropRandomItem(){};

        /**
         * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
         * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
         *
         * @param ffp2 a hozzáadandó tárgy
         */
    @Override
    public void acceptItem(FFP2 ffp2) {
        SkeletonMenu.startCall("Player.acceptItem(FFP2)");
        this.addItem(ffp2);
        this.addPoisonImmunity(new PoisonImmunity(ffp2, ffp2.getImmunityLength(), this));
        location.removeItem(ffp2);
        SkeletonMenu.endCall();
    }


    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param camembert a felvevendő tárgy
     */
    @Override
    public void acceptItem(Camembert camembert) {
        SkeletonMenu.startCall("Player.acceptItem(Camembert)");
        this.addItem(camembert);
        location.removeItem(camembert);
        SkeletonMenu.endCall();
    }


    /**
     * A hamis tárgyak (FakeItem) felvételkor megsemmisülnek. A tárgyak közös interfésze az IItem interfészben definiált.
     * A hamis tárgyak az igazi verziójukat öröklik és implementálják az IItemből származtatott FakeItem interfészt.
     *
     * @param fakeItem a nem felvevendő tárgy
     */
    @Override
    public void acceptItem(FakeItem fakeItem) {
        SkeletonMenu.startCall("Player.acceptItem(Fake)");
        location.removeItem(fakeItem);
        SkeletonMenu.endCall();
    }

    @Override
    public void acceptItem(AirFreshener airFreshener) {
        SkeletonMenu.startCall("Player.acceptItem(AirFreshener)");
        this.addItem(airFreshener);
        location.removeItem(airFreshener);
        SkeletonMenu.endCall();
    }

}