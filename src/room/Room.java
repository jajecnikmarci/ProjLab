package room;

import effects.RoomEffect;
import items.Item;
import player.Player;
import player.Professor;
import player.Student;

import java.util.List;

/**
 *
 */
public class Room {
    /**
     *
     */
    private int capacity;

    /**
     *
     */
    private List<Player> players;

    /**
     *
     */
    private List<Door> doors;

    /**
     *
     */
    private List<Item> items;

    /**
     *
     */
    private List<RoomEffect> effects;

    /**
     * @param item
     */
    void removeItem(Item item) {
    }

    /**
     * @param item
     */
    void addItem(Item item) {
    }

    /**
     * 
     * @param item
     */
    public void popItem() {

    }

    /**
     * @param professor
     */
    void onEnter(Professor professor) {
    }

    /**
     * @param student
     */
    void onEnter(Student student) {
    }

    /**
     *
     */
    void poisonPlayers() {
    }

    /**
     *
     */
    void split() {

    }

    /**
     * @param room
     */
    void mergeWithRoom(Room room) {

    }

    /**
     * @param player
     */
    void addPlayer(Player player) {

    }

    /**
     * @param player
     */
    void removePlayer(Player player) {

    }

    /**
     * @param effect
     */
    void addEffect(RoomEffect effect) {

    }

    /**
     * @param effect
     */
    void removeEffect(RoomEffect effect) {

    }
}
