package player;

import effects.PoisonImmunity;
import items.Item;
import room.Door;
import room.Room;

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
     * A Játékos aktuális szobája
     */
    Room room=new Room(10);

    /**
     *
     */
    List<PoisonImmunity> poisonImmunities;

    /**
     * @param item
     */
    public void addItem(Item item) {
        System.out.println("Player.addItem(Item)");
    }

    /**
     * Tárgy eltávolítása a játékostól
     * @param item
     */
    public void removeItem(Item item) {
        System.out.println("Player.removeItem(Item)");
    }

    /**
     * @param item
     */
    void pickUpItem(Item item) {

    }

    /**
     * @param item
     */
    void dropItem(Item item) {

    }

    /**
     * @param item
     */
    public void useItem(Item item) {
        item.use(room, this);
    }

    /**
     * @param poisonImmunity
     */
    void addPoisonImmunity(PoisonImmunity poisonImmunity) {

    }

    /**
     * @param poisonImmunity
     */
    void removePoisonImmunity(PoisonImmunity poisonImmunity) {

    }

    /**
     *
     */
    void stun() {

    }

    /**
     *
     */
    public void poison() {

    }

    /**
     * @param room
     */
    void goToRoom(Room room) {
    }

    public void meet(Student student) {
        
    }
    public void meet(Professor professor) {

    }

}
