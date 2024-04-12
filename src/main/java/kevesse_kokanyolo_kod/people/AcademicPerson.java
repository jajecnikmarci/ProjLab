package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.effects.EffectConsumedObserver;
import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.items.fakes.FakeItem;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.room.Room;

import java.util.*;

/**
 * A Hallgatókat és oktatókat összefoglaló osztály reprezentáló osztály
 */
public abstract class AcademicPerson extends Person implements PickUpVisitor, EffectConsumedObserver {
    /**
     * A játékos tárgylistája.
     */
    List<IItem> inventory;

    /**
     * A játékos által használható mérgezés elleni immunitások listája.
     */
    List<Effect> poisonImmunities;

    /**
     * Megmondja, hogy mérgezett-e a játékos.
     */
    public boolean isStunned() {
        return stunned;
    }
      
    /**
     * Megmondja, hogy mérgezett-e a játékos.
     */
    protected boolean stunned;

    /**
     * Létrehozza a játékost
     * 
     * @param room a szoba, ahova a játékos kerül
     */
    protected AcademicPerson(Room room) {
        super(room);
        inventory = new ArrayList<>();
        poisonImmunities = new ArrayList<>();
    }
    /**
     * Megnézi, hogy a játékos bénult-e. Ha igen, akkor nem tud mozogni. 
     * Felülírja a Person goToRoom függvényét. 
     */
    @Override 
    public void goToRoom(Room room) {
        if(!stunned)
            super.goToRoom(room);
    }

    /**
     * Hozzáadja az AcademicPerson tárgylistájához a paraméterként kapott tárgyat.
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
     * Ha a játékos nincs lebénulva a van a jelenlegi szobára meghívja a popItem függvényt.
     * Ezzel elindítja a Visitor működést, 
     * mely végén felveszi a szoba tárgylistájának legfelső tárgyát, ha felveheti.
     * A Professornak és Studentnek felül kell írni és ellenőrizni, hogy van-e hely a tárgylistájában.
     */
    public void pickUpItem() {
        SkeletonMenu.startCall("Player.pickUpItem()");
        if(!stunned)
            location.popItem(this);
        SkeletonMenu.endCall();
    }

    /**
     * Megnézi, hogy a játékosnak van-e a paraméterként kapott tárgya. 
     * Bővebben megnézi, hogy a játékos tárgyai tiltják-e a paraméterként kapott tárgy felvételét. 
     * 
     * Ilyenkor természetesen a tárgyak interakcióba is lépnek egymással, ami egyelőre csak a tranzisztor párosítása során lényeges.
     * (Az interakció gondoskodik Tranzisztorok párosításáról. Ezt a metódust hívja a tranzisztor use metódusa, amikor párosításra van szükség.)
     * 
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
     * A paraméterül kapott item-et eldobja a játékos, ha nincs lebénulva.
     * Az item a szobához kerül, nem tartozik többé a játékoshoz az item.
     *
     * @param item a tárgy, amit eldob a játékos
     */
    public void dropItem(IItem item) {
        SkeletonMenu.startCall("Player.dropItem(Item)");
        if(!stunned) {
            inventory.remove(item);
            location.addItem(item);
            item.onDrop(this);
        }
        SkeletonMenu.endCall();
    }

    /**
     * A játékos eldobja az összes nála lévő tárgyat-et,
     * mindegyik, a játékosnál lévő item a szobához kerül,
     * egy item sem fog a játékoshoz tartozni.
     */
    public void dropAll() {
        SkeletonMenu.startCall("Player.dropAll()");
        inventory.forEach(item -> this.dropItem(item));
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott tárgyat használja a játékos, ha nincs lebénulva.
     * Csak olyan tárgyat szabad átadni ennek a függvénynek, 
     * aminek a játékos meghívhatja a use függvényét.
     * 
     * @param item a használandó tárgy
     */
    public void useItem(Item item) {
        SkeletonMenu.startCall("Player.useItem(Item)");
        if(!stunned)
            item.use(location, this);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a poisonImmunities-hez a paraméterként kapott immuntitást.
     * @param poisonImmunity az immunitás, ami hozzáadódik az immunitásokhoz
     */
    public void addPoisonImmunity(Effect poisonImmunity) {
        SkeletonMenu.startCall("Player.addPoisonImmunity(PoisonImmunity)");
        poisonImmunities.add(poisonImmunity);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a paraméterként kapott immunitást a poisonImmunities-ból.
     * A Hallgatóknak felül kell írni, hogy a KillImmunity-ket is töröljék, ha azt kaptak.
     * @param a törlendő hatás
     */
    public void removeEffect(Effect effect) {
        SkeletonMenu.startCall("Player.removeEffect(Effect)");
        poisonImmunities.remove(effect);
        SkeletonMenu.endCall();
    }

    /**
     * A játékos találkozik egy takarítóval, amikor a takarító a szobába lép
     * és a játékos tud mozogni, a játékos elhagyja a szobát.
     *
     * @param cleaner a takarító, akivel a játékos találkozik
     */
    @Override
    public void meet(Cleaner cleaner) {
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
     * A játékos lebénulását valósítja meg. 
     * Beállítja a stunned jelzőt igazta
     * Elindít egy időzítőt, ez duration másodperc  múlva visszaállítja a stunned jelzőt hamisra.
     *
     * @param duration az idő, ameddig a játékos lebénulva lesz
     */
    public void stun(int duration) {
        SkeletonMenu.startCall("Player.stun(Duration)");
        // TODO: timercontrol
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
     * másodpercre és elejti az összes nála lévő tárgyat. 
     * Ha ajátékosnak van aktív PoisonImmunityje, az megvédi a mérgezéssel szemben.
     * Ha van nem aktív PoisonImmunity-je, azt aktiválja és ez megvédi a mérgeződéssel szemben.
     */
    public void poison() {
        SkeletonMenu.startCall("Player.poison()");
        if (poisonImmunities.isEmpty()) {
            this.dropAll();
            this.stun(5);
            SkeletonMenu.endCall("A játékos megmérgeződött.");
            return;
        }
        if (poisonImmunities.stream().anyMatch(Effect::isActive)) {
            SkeletonMenu.endCall("A játékos nem mérgeződött meg, mert volt aktív immunitása.");
            return;
        }
        Effect poisonImmunity = poisonImmunities.get(0);
        poisonImmunity.activate();
        SkeletonMenu.endCall("A játékos nem mérgeződött meg, mert egy tárgy megvédte.");
    }

  

    /**
     * Hozzáad egy KillImmunity-t a játékoshoz.
     * Professor nem írja fölül mivel számára nem szükséges
     * A Student osztályban felül kell írni.
     *
     * @param killImmunity a hozzáadandó immunitás
     */
    public void addKillImmunity(Effect killImmunity) {
    }

   
    /**
     * EffectConsumedObserver interfész implementációja.
     * Ha lejárt egy effekt akkor a játékos törli az effektet a poisonimmunity listájából, 
     * valamint az effektet adó tárgy use metódusát meghívja, ha a tárgy még a játékosnál van, 
     * ezzel jelezve neki a használódást. 
     * 
     * Ebben az osztályban a PoisonImmunity effekteket kell csak kezelni, 
     * a Student osztályban a KillImmunityket is kezelni kell.
     * 
     * Ha lejár egy PoisonImmunity effect, 
     * a szobával meg kell mérgeztetni ezt a játékost, ha továbbra is mérgező. (tryPoison)
     *
     * @param effect az elhasznált effekt
     */
    public void effectConsumed(Effect effect) {
        SkeletonMenu.startCall("Player.effectConsumed(Effect)");
        Item item = effect.getItem();
        item.removeEffect();
        for (Effect e : poisonImmunities) {
            if (e == effect) {
                if (inventory.contains(item)) {
                    item.use(location, this);
                }
                location.tryPoison(this);
                removeEffect(effect);
                break;
            
            }
        }
        SkeletonMenu.endCall();
    }

    /**
     * A játékos elejt egy nála lévő, véletlenszerűen kiválasztott tárgyat.
     */
    public void dropRandomItem() {
        SkeletonMenu.startCall("AcademicPerson.dropRandomItem()");
        dropItem(inventory.get(new Random().nextInt(inventory.size())));
        SkeletonMenu.endCall();
    };

    /**
     * A paraméterként kapott FFP2 tárgyat hozzáadja a Player tárgyaihoz.
     * A tárgy felvételét követően a játékos megkapja az FFP2 tárgyhoz tartozó hatást.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param ffp2 a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(FFP2 ffp2) {
        SkeletonMenu.startCall("Player.acceptItem(FFP2)");
        this.addItem(ffp2);
        this.addPoisonImmunity(ffp2.getEffect());
        location.removeItem(ffp2);
        SkeletonMenu.endCall();
    }

    /**
     * A paraméterként kapott Camembert tárgyat hozzáadja a Player tárgyaihoz.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
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
     * A hamis tárgy felvételét követően a hamis tárgy törlődik, megszűnik létezni.
     * 
     * @param fakeItem a nem felvevendő tárgy
     */
    @Override
    public void acceptItem(FakeItem fakeItem) {
        SkeletonMenu.startCall("Player.acceptItem(Fake)");
        location.removeItem(fakeItem);
        SkeletonMenu.endCall();
    }

    /**
     * Az AirFreshener tárgyat hozzáadja a Player tárgyaihoz.
     * Majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     *
     * @param airFreshener a felvevendő tárgy
     */
    @Override
    public void acceptItem(AirFreshener airFreshener) {
        SkeletonMenu.startCall("Player.acceptItem(AirFreshener)");
        this.addItem(airFreshener);
        location.removeItem(airFreshener);
        SkeletonMenu.endCall();
    }

}
