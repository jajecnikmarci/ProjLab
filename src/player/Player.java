package player;

import effects.PoisonImmunity;
import items.Item;
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
     *
     */
    List<PoisonImmunity> poisonImmunities;

    /**
     * @param item
     */
    void addItem(Item item) {

    }

    /**
     * @param item
     */
    void removeItem(Item item) {

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
    void useItem(Item item) {

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

}
