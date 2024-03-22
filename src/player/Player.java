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

    Room location;

    /**
     * @param item
     */
    public void addItem(Item item) {

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
        item.accept(this);
        location.popItem();
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
    public void addPoisonImmunity(PoisonImmunity poisonImmunity) {

    }

    /**
     * @param poisonImmunity
     */
    public void removePoisonImmunity(PoisonImmunity poisonImmunity) {

    }

    /**
     *
     */
    void stun() {

    }

    /**
     *
     */
    void poison() {

    }

    /**
     * @param room
     */
    void goToRoom(Room room) {

    }

}
