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
     * @param item
     */
    void popItem(Item item) {
    }

    /**
     * @param professor
     */
    public void onEnter(Professor professor) {
        for (RoomEffect effect : this.effects) 
            if(effect.isActive()) effect.affect(professor);
        
        for (Player player : this.players) {
            player.meet(professor);
        }
    }

    /**
     * @param student
     */
    public void onEnter(Student student) {
        for (RoomEffect effect : this.effects) 
            if(effect.isActive()) effect.affect(student);
        
        for (Player player : this.players) {
            player.meet(student);
        }
    }

    /**
     *
     */
    void poisonPlayers() {
        players.forEach(p -> p.poison());
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
