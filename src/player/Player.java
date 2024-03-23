package player;

import effects.Effect;
import effects.KillImmunity;
import effects.PoisonImmunity;
import items.Item;
import room.Door;
import room.Room;

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

    Room location;

    public Player(Room r){
        location=r;
        inventory = new ArrayList<Item>();
        poisonImmunities=new ArrayList<PoisonImmunity>();
    }
     /**
     * @param item
     */
    public void addItem(Item item) {
        System.out.println("Player.addItem(Item)");
    }

    
    /**
     * Hozzáadja a player tárgylistájához a paraméterként kapott tárgyat.
     * @param item a hozzáadandó tárgy
     */
    public void addItem(Item item) {
        System.out.println("Player.addItem(Item)");
        inventory.add(item);

    }

    /**
     * Kitörli a player tárgylistájából a paraméterként kapott tárgyat.
     * @param item a kitörlendő tárgy
     */
    public void removeItem(Item item) {
        System.out.println("Player.removeItem(Item)");
        inventory.remove(item);
    }

    /**
     * A jelenlegi szobára meghívja a popItem függvényt. 
     * Ezzel elindítja a Visitor működést, mely végén felveszi a szoba tárgylistájának legfelső tárgyát.
     */
    public void pickUpItem() {
        System.out.println("Player.pickUpItem()");
        // if (item.canPickUp(this)) 
        location.popItem(this);
    }

    /**
     * A paraméterül kapott item-et eldobja a játékos, nem
     * tartozik többé a játékoshoz az item.
     * @param item
     */
    void dropItem(Item item) {
        inventory.remove(item);
    }

    /**
     * A játékos elejti az összes nála lévő item-et, egy
     * item sem fog a játékoshoz tartozni.
     */
    void dropAll() {
        inventory.forEach(item -> location.addItem(item));
        inventory.clear();
    }

    /**
     * @param item
     */
    public void useItem(Item item) {
        item.use(location, this);
    }

    /**
     * Hozzáadja a poisonImmunities-hez a paraméterként kapott immuntitást.
     * @param poisonImmunity
     */
    public void addPoisonImmunity(PoisonImmunity poisonImmunity) {
        System.out.println("Player.addPoisonImmunity(PoisonImmunity)");
        poisonImmunities.add(poisonImmunity);
    }

    /**
     * Kitörli a poisonImmunities-ból a paraméterként kapott immuntitást.
     * @param poisonImmunity
     */
    public void removePoisonImmunity(PoisonImmunity poisonImmunity) {
        poisonImmunities.remove(poisonImmunity);
    }

    /**
     * A játékos duration ideig nem tud mozogni.
     */
    void stun(int duration) {

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
        System.out.println("Player.poison()");
        if (poisonImmunities.isEmpty()) {
            this.dropAll();
            this.stun(5);
            return;
        }
        if (poisonImmunities.stream().anyMatch(PoisonImmunity::isActive)) return;
        poisonImmunities.get(0).activate();
    }

    /**
     * @param room
     */
    void goToRoom(Room room) {
    }

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
