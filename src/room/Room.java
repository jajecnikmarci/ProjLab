package room;

import effects.Effect;
import effects.RoomEffect;
import items.Item;
import player.Player;
import player.Professor;
import player.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private ArrayList<RoomEffect> effects;

    public Room(){
        players = new ArrayList<Player>();
        items = new ArrayList<Item>();
        effects = new ArrayList<RoomEffect>();
        doors = new ArrayList<Door>();
    }

    /**
     * Kitöröl egy tárgyat a szoba tárgyai közül.
     * @param item a kitörlendő item
     */
    public void removeItem(Item item) {
        System.out.println("Room.removeItem(Item)");
        items.remove(item);
    }

    /**
     * Hozzáad egy tárgyat a szoba tárgyai közé.
     * @param item a hozzáadandó item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 
     * @param player 
     */
    public void popItem(Player player) {
        System.out.println("Room.popItem(Player)");
        if(items.isEmpty()) return;
        items.get(items.size()-1).accept(player);
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
     * Kezeli a szobába belépő hallgató eseményeit. 
     * 
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
     * Hozzáad egy Player-t a szobában tartózkodó Player-ek közé
     * @param player a hozzáadandó player
     */
    public void addPlayer(Player player) {
        players.add(player);
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
    public void addEffect(RoomEffect effect) {
        System.out.println("Rug.addEffect(Effect)");
        this.effects.add(effect);
    }

    /**
     * @param effect
     */
    void removeEffect(RoomEffect effect) {
        this.effects.remove(effect);
    }
}
