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
import java.util.stream.Collectors;

/**
 *
 */
public class Room {


    public Room(int capacity) {
        this.capacity = capacity;
        this.players = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
        this.effects = new ArrayList<>();
    }
    
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
    private ArrayList<RoomEffect> effects;

    public Room(){
        players = new ArrayList<Player>();
        items = new ArrayList<Item>();
        effects = new ArrayList<RoomEffect>();
        doors = new ArrayList<Door>();
    }

    /**
     * Kitörli a megadott tárgyat a szoba tárgyai közül.
     * @param item a megadott tárgy
     */
    public void removeItem(Item item) {
        System.out.println("Room.removeItem(Item)");
        items.remove(item);
    }

    /**
     * Hozzáad egy tárgyat a szobában lévő tárgyak listájához.
     * @param item a hozzáadandó tárgy
     */
    public void addItem(Item item) {
        System.out.println("Room.addItem(Item)");
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
     * Amikor egy oktató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait. 
     * Interakcióba hozza a szobában található játékosokkal.
     * @param professor a belépő oktató
     */
    public void onEnter(Professor professor) {
        System.out.println("Room.onEnter(Professor)");
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
        System.out.println("Room.onEnter(Student)");
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
        System.out.println("Room.poisonPlayers()");
        players.forEach(p -> p.poison());
    }

    /**
     * Kettéosztja ezt a szobát, ha kapacitása 4 vagy nagyobb és nincs benne játékos.
     * Az újonnan létrehozott szobával megosztoznak az eredeti szoba tulajdonságain.
     * A szoba szomszédai mostantól ennek, vagy az új szobáknak csak egyikével lesznek szomszédosak.
     * Az újonnan létrehozott szoba szomszédos ezzel a szobával. 
     * A létrejövő szoba kapacitása alsó egészrésze az eredeti szoba kapacitásának felének.
     * 
     */
    public void split() {
        System.out.println("Room.split()");
        if (capacity < 4) return;
        if (!players.isEmpty()) return;
        
        Room room = new Room(capacity / 2);
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
     * Beleolvasztja a kapott szobát ebbe a szobába, ha nincs bennük játékos.
     * A kapacitás a két szoba kapacitásának maximuma lesz.
     * A játékosok, tárgyak és hatások összeadódnak.
     * Az ajtók összegyűjtésekor azokat az ajtókat eldobjuk, amik a két szoba között vannak.
     * Az ajtók közül azokat, amik a kapott szobához vezetnek, átállítjuk, hogy erre a szobára vezessenek.
     * A hívó dolga a beolvasztandó szoba megsemmisítése.
     * @param room a szoba amit beleolvasztunk ebbe a szobába
     */
    public void mergeWithRoom(Room room) {
        System.out.println("Room.mergeWithRoom(Room)");
        if (!players.isEmpty() && !room.players.isEmpty()) return;
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
        System.out.println("Room.addPlayer(Player)");
        this.players.add(player);
    }

    /**
     * Kitörli a játékost a szobából.
     * @param player a kitörölni kívánt játékos
     */
    public void removePlayer(Player player) {
        System.out.println("Room.removePlayer(Player)");
        this.players.remove(player);
    }

    /**
     * Hozzáadja a hatást a szobához.
     * @param effect a hozzáadandó hatás
     */
    public void addEffect(RoomEffect effect) {
        System.out.println("Room.addEffect(RoomEffect)");
        this.effects.add(effect);
    }

    /**
     * @param effect
     */
    public void removeEffect(RoomEffect effect) {
        System.out.println("Room.removeEffect(RoomEffect)");
        this.effects.remove(effect);
    }
}
