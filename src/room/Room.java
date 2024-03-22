package room;

import effects.RoomEffect;
import items.Item;
import player.Player;
import player.Professor;
import player.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class Room {
    /**
     * Megadja, hogy hány játékos tartózkodhat a szobában.
     */
    private int capacity;

    /**
     * A szobában tartózkodó játékosok listája.
     */
    private List<Player> players;

    /**
     * A szobához tartozó ajtók listája.
     */
    private List<Door> doors;

    /**
     * A szobában található tárgyak listája.
     */
    private List<Item> items;

    /**
     * A szobában található hatások listája.
     */
    private List<RoomEffect> effects;

    /**
     * Kitörli a megadott tárgyat a szoba tárgyai közül.
     * @param item a megadott tárgy
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Hozzáad egy tárgyat a szobában lévő tárgyak listájához.
     * @param item a hozzáadandó tárgy
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 
     */
    public void popItem(Player player) {

    }

    /**
     * Amikor egy oktató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait. 
     * Interakcióba hozza a szobában található játékosokkal.
     * @param professor a belépő oktató
     */
    public void onEnter(Professor professor) {
        for (RoomEffect effect : this.effects) 
            if(effect.isActive()) effect.affect(professor);
        
        for (Player player : this.players) {
            player.meet(professor);
        }
    }

    /**
     * Amikor egy hallgató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait.
     * Interakcióba hozza a szobában található játékosokkal.
     * @param student a belépő hallgató
     */
    public void onEnter(Student student) {
        for (RoomEffect effect : this.effects) 
            if(effect.isActive()) effect.affect(student);
        
        for (Player player : this.players) {
            player.meet(student);
        }
    }

    /**
     * Megmérgez minden játékost aki a szobában tartózkodik. 
     */
    public void poisonPlayers() {
        players.forEach(p -> p.poison());
    }

    /**
     * Kettéosztja ezt a szobát.
     * Az újonnan létrehozott szobák megosztoznak az eredeti szoba tulajdonságain.
     * A szoba szomszédai mostantól az új szobáknak csak egyikével lesznek szomszédosak.
     * A szobának legalább 4 kapacitásúnak kell lennie a szétosztáshoz.
     * Az újonnan létrehozott szobák egymással szomszédosak lesznek.
     */
    public void split() {
        if (capacity < 4) return;
        Room room = new Room();
        
        for (int i = 0; i < doors.size(); i += 2) {
            room.doors.add(doors.remove(i));
        }
        
        for (int i = 0; i < items.size(); i += 2) {
            room.items.add(items.remove(i));
        }

        for (int i = 0; i < effects.size(); i += 2) {
            room.effects.add(effects.remove(i));
        }

        Door door = new Door(this, room, true, true);
        doors.add(door);
        room.doors.add(door);
    }

    /**
     * Beleolvasztja akapott szobát ebbe a szobába.
     * A kapacitás a két szoba kapacitásának maximuma lesz.
     * A játékosok, tárgyak és hatások összeadódnak.
     * Az ajtók összegyűjtésekor azokat az ajtókat eldobjuk, amik a két szoba között vannak.
     * Az ajtók közül azokat, amik a kapott szobához vezetnek, átállítjuk, hogy erre a szobára vezessenek.
     * @param room a szoba amit beleolvasztunk ebbe a szobába
     */
    public void mergeWithRoom(Room room) {
        this.capacity = Math.max(this.capacity, room.capacity); 
        this.players.addAll(room.players);
        this.items.addAll(room.items);
        this.effects.addAll(room.effects);

        doors.addAll(room.doors);
        doors = doors.stream()
                        .filter(d -> !(d.getRoom1() == room && d.getRoom2() == this  
                                    || d.getRoom1() == this && d.getRoom2() == room))
                        .distinct()
                        .collect(Collectors.toList());
        doors.forEach(d -> {
            if(d.getRoom1() == room) d.setRoom1(this);
            if(d.getRoom2() == room) d.setRoom2(this);
        });
    }

    /**
     * @param player
     */
    void addPlayer(Player player) {
        this.players.add(player);

    }

    /**
     * @param player
     */
    void removePlayer(Player player) {
        this.players.remove(player);
    }

    /**
     * @param effect
     */
    void addEffect(RoomEffect effect) {
        this.effects.add(effect);
    }

    /**
     * @param effect
     */
    void removeEffect(RoomEffect effect) {
        this.effects.remove(effect);
    }
}
