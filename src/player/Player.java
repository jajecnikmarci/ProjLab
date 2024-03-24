package player;

import effects.PoisonImmunity;
import items.Item;
import room.Room;
import skeleton.Skeleton;

import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public abstract class Player implements PickUpVisitor {
    /**
     *
     */
    List<Item> inventory;

    /**
     *
     */
    List<PoisonImmunity> poisonImmunities;
    
    /**
     * A szoba amelyben a player jelenleg tartózkodik.
     */
    Room location;

    public Player(Room r){
        location=r;
        inventory = new ArrayList<Item>();
        poisonImmunities=new ArrayList<PoisonImmunity>();
    }
    
    /**
     * Hozzáadja a player tárgylistájához a paraméterként kapott tárgyat.
     * @param item a hozzáadandó tárgy
     */
    public void addItem(Item item) {
        Skeleton.startCall("Player.addItem(Item)");
        inventory.add(item);
        Skeleton.endCall();
    }

    /**
     * Kitörli a player tárgylistájából a paraméterként kapott tárgyat.
     * @param item a kitörlendő tárgy
     */
    public void removeItem(Item item) {
        Skeleton.startCall("Player.removeItem(Item)");
        inventory.remove(item);
        Skeleton.endCall();
    }

    /**
     * A jelenlegi szobára meghívja a popItem függvényt. 
     * Ezzel elindítja a Visitor működést, mely végén felveszi a szoba tárgylistájának legfelső tárgyát.
     */
    public void pickUpItem() {
        Skeleton.startCall("Player.pickUpItem()");
        // if (item.canPickUp(this)) 
        location.popItem(this);
        Skeleton.endCall();
    }

    /**
     * A paraméterül kapott item-et eldobja a játékos, nem
     * tartozik többé a játékoshoz az item.
     * @param item
     */
    public void dropItem(Item item) {
        Skeleton.startCall("Player.dropItem(Item)");
        inventory.remove(item);
        location.addItem(item);
        Skeleton.endCall();
    }

    /**
     * A játékos elejti az összes nála lévő item-et, egy
     * item sem fog a játékoshoz tartozni.
     */
    public void dropAll() {
        Skeleton.startCall("Player.dropAll()");
        inventory.forEach(item -> location.addItem(item));
        inventory.clear();
        Skeleton.endCall();
    }

    /**
     * @param item
     */
    public void useItem(Item item) {
        Skeleton.startCall("Player.useItem(Item)");
        item.use(location, this);
        Skeleton.endCall();
    }

    /**
     * Hozzáadja a poisonImmunities-hez a paraméterként kapott immuntitást.
     * @param poisonImmunity
     */
    public void addPoisonImmunity(PoisonImmunity poisonImmunity) {
        Skeleton.startCall("Player.addPoisonImmunity(PoisonImmunity)");
        poisonImmunities.add(poisonImmunity);
        Skeleton.endCall();
    }

    /**
     * Kitörli a poisonImmunities-ból a paraméterként kapott immuntitást.
     * @param poisonImmunity
     */
    public void removePoisonImmunity(PoisonImmunity poisonImmunity) {
        Skeleton.startCall("Player.removePoisonImmunity(PoisonImmunity)");
        poisonImmunities.remove(poisonImmunity);
        Skeleton.endCall();
    }

    /**
     * A játékos duration ideig nem tud mozogni.
     */
    public void stun(int duration) {
        Skeleton.startCall("Player.stun(Duration)");
        // TODO: implement
        Skeleton.endCall("A játékos lebénult.");
    }

    /**
     * A játékost ha mérgezés éri és nincs védettsége a méreg
     * ellen a játékosnak, mozgásképtelenné válik a játékos 5
     * másodpercre és elejti az összes nála lévő tárgyat. Ha
     * a játékos tud védekezni a méreg ellen, a játékos nem
     * válik mozgásképtelenni és nem ejti el a nála lévő
     * tárgyakat.
     */
    public void poison() {
        Skeleton.startCall("Player.poison()");
        if (poisonImmunities.isEmpty()) {
            this.dropAll();
            this.stun(5);
            Skeleton.endCall("A játékos megmérgeződött.");
            return;
        }
        if (poisonImmunities.stream().anyMatch(PoisonImmunity::isActive)) {
            Skeleton.endCall("A játékos nem mérgeződött meg, mert volt aktív immunitása.");
            return;
        }
        poisonImmunities.get(0).activate();
        Skeleton.endCall("A játékos nem mérgeződött meg, mert egy tárgy megvédte.");
    }

    /**
     * Belép a szobába, ha tud és közli a szobával, hogy belépett a játékos.
     * @param room 
     */
    public abstract void goToRoom(Room room);

    /**
     * @param student
     */
    public void meet(Student student) {
    }

    /**
     * @param professor
     * @param room
     */
    public void meet(Professor professor, Room room) {

    }

    /**
     * Visszaadja, hogy melyik szobában van a játékos.
     */
    public Room getLocation() {
        return this.location;
    }

    /**
     * Beállítja, hogy a játékos melyik szobában lesz (szobaváltásnál).
     * @param room
     */
    public void setLocation(Room room) {
        this.location = room;
    }
}
