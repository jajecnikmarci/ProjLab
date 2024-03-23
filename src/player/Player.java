package player;

import effects.Effect;
import effects.PoisonImmunity;
import items.Item;
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
        inventory.add(item);
    }

    /**
     * @param item
     */
    public void removeItem(Item item) {
        System.out.println("Player.removeItem(Item)");
        inventory.remove(item);
    }

    /**
     * A jelenlegi szobából felveszi a legfelső tárgyat.
     * @param item
     */
    public void pickUpItem() {
        System.out.println("Player.pickUpItem()");
        // if (item.canPickUp(this)) 
        location.popItem(this);
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
