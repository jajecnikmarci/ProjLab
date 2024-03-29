package kevesse_kokanyolo_kod.room;

import kevesse_kokanyolo_kod.effects.Effect;
import kevesse_kokanyolo_kod.effects.EffectConsumedObserver;
import kevesse_kokanyolo_kod.effects.RoomEffect;
import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.player.Professor;
import kevesse_kokanyolo_kod.player.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A játékban szereplő szobákat reprezentáló osztály.
 */
public class Room implements EffectConsumedObserver {


    /**
     * Megadja, hogy hány játékos tartózkodhat a szobában.
     */
    private int capacity;
    /**
     * A szobában tartózkodó játékosok listája.
     */
    private final List<Player> players;
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

    public Room(int capacity) {
        this.capacity = capacity;
        this.players = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
        this.effects = new ArrayList<>();
    }

    public Room() {
        players = new ArrayList<>();
        items = new ArrayList<>();
        effects = new ArrayList<>();
        doors = new ArrayList<>();
    }

    public List<Door> getDoors() {
        return doors;
    }

    /**
     * Kitörli a megadott tárgyat a szoba tárgyai közül.
     *
     * @param item a megadott tárgy
     */
    public void removeItem(Item item) {
        SkeletonMenu.startCall("Room.removeItem(Item)");
        items.remove(item);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáad egy tárgyat a szobában lévő tárgyak listájához.
     *
     * @param item a hozzáadandó tárgy
     */
    public void addItem(Item item) {
        SkeletonMenu.startCall("Room.addItem(Item)");
        items.add(item);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáad a szobában lévő táryak közül a legfelső tárgyat a játékos tárgyaihoz.
     *
     * @param player A játékos, aki a tárgyat fel akarja venni
     */
    public void popItem(Player player) {
        SkeletonMenu.startCall("Room.popItem(Player)");
        if (items.isEmpty()) {
            SkeletonMenu.endCall("Nincs több tárgy a szobában.");
            return;
        }
        items.get(items.size() - 1).accept(player);
        SkeletonMenu.endCall();
    }

    /**
     * Amikor egy oktató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait.
     * Interakcióba hozza a szobában található játékosokkal.
     * A beléptető objektum felelőssége meghívni ezt a függvényt.
     *
     * @param professor a belépő oktató
     */
    public void onEnter(Professor professor) {
        SkeletonMenu.startCall("Room.onEnter(Professor)");
        for (RoomEffect effect : this.effects) {
            if (effect.isActive()) effect.affect(professor);
        }
        for (Player player : this.players) {
            player.meet(professor, this);
        }
        SkeletonMenu.endCall();
    }

    /**
     * Amikor egy hallgató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait.
     * Interakcióba hozza a szobában található játékosokkal.
     * A beléptető objektum felelőssége meghívni ezt a függvényt.
     *
     * @param student a belépő hallgató
     */
    public void onEnter(Student student) {
        SkeletonMenu.startCall("Room.onEnter(Student)");
        for (RoomEffect effect : this.effects) {
            if (effect.isActive()) effect.affect(student);
        }
        players.forEach(player -> player.meet(student));
        SkeletonMenu.endCall();
    }

    /**
     * Megmérgez minden játékost aki a szobában tartózkodik.
     */
    public void poisonPlayers() {
        SkeletonMenu.startCall("Room.poisonPlayers()");
        players.forEach(Player::poison);
        SkeletonMenu.endCall();
    }

    /**
     * Kettéosztja ezt a szobát, ha kapacitása 4 vagy nagyobb és nincs benne játékos.
     * Az újonnan létrehozott szobával megosztoznak az eredeti szoba tulajdonságain.
     * A szoba szomszédai mostantól ennek, vagy az új szobáknak csak egyikével lesznek szomszédosak.
     * Az újonnan létrehozott szoba szomszédos ezzel a szobával.
     * A létrejövő szoba kapacitása alsó egészrésze az eredeti szoba kapacitásának felének.
     */
    public void split() {
        SkeletonMenu.startCall("Room.split()");
        if (capacity < 4) {
            SkeletonMenu.endCall("A szoba nem osztódott, mert kapacitása 4-nél kisebb volt.");
            return;
        }
        if (!players.isEmpty()) {
            SkeletonMenu.endCall("A szoba nem osztódott, mert volt benne játékos.");
            return;
        }

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
        SkeletonMenu.endCall("A szoba osztódott.");
    }

    /**
     * Beleolvasztja a kapott szobát ebbe a szobába, ha nincs bennük játékos.
     * A kapacitás a két szoba kapacitásának maximuma lesz.
     * A játékosok, tárgyak és hatások összeadódnak.
     * Az ajtók összegyűjtésekor azokat az ajtókat eldobjuk, amik a két szoba között vannak.
     * Az ajtók közül azokat, amik a kapott szobához vezetnek, átállítjuk, hogy erre a szobára vezessenek.
     * A hívó dolga a beolvasztandó szoba megsemmisítése.
     *
     * @param room a szoba amit beleolvasztunk ebbe a szobába
     */
    public void mergeWithRoom(Room room) {
        SkeletonMenu.startCall("Room.mergeWithRoom(Room)");
        if (!players.isEmpty() || !room.players.isEmpty()) {
            SkeletonMenu.endCall("A szobák nem olvadtak össze, mert volt bennük játékos.");
            return;
        }
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
            if (d.getRoom1() == room) d.setRoom1(this);
            if (d.getRoom2() == room) d.setRoom2(this);
        });
        SkeletonMenu.endCall("A szobák összeolvadtak.");
    }

    /**
     * Hozzáad egy Player-t a szobában tartózkodó Player-ek közé
     *
     * @param player a hozzáadandó player
     */
    public void addPlayer(Player player) {
        SkeletonMenu.startCall("Room.addPlayer(Player)");
        this.players.add(player);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a játékost a szobából.
     *
     * @param player a kitörölni kívánt játékos
     */
    public void removePlayer(Player player) {
        SkeletonMenu.startCall("Room.removePlayer(Player)");
        this.players.remove(player);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a hatást a szobához.
     *
     * @param effect a hozzáadandó hatás
     */
    public void addEffect(RoomEffect effect) {
        SkeletonMenu.startCall("Room.addEffect(RoomEffect)");
        this.effects.add(effect);
        SkeletonMenu.endCall();
    }

    /**
     * @param effect
     */
    public void removeEffect(RoomEffect effect) {
        SkeletonMenu.startCall("Room.removeEffect(RoomEffect)");
        this.effects.remove(effect);
        SkeletonMenu.endCall();
    }

    /**
     * Ha egy játékos befér még a szobába, igaz értékkel tér vissza.
     */
    public boolean canPlayerEnter() {
        return capacity > players.size();
    }

    /**
     * Megkeresi a paraméterként kapott tárgyhoz tartozó KillImmunity-t a killImmunities listában.
     *
     * @param item a tárgy, amelyhez tartozó KillImmunity-t keresi
     */
    private RoomEffect findRoomEffectByItem(Item item) {
        for (RoomEffect effect : effects) {
            if (effect.getItem().equals(item)) {
                return effect;
            }
        }
        return null;
    }

    public void effectConsumed(Effect effect) {
        SkeletonMenu.startCall("Room.effectConsumed()");
        Item item = effect.getItem();
        effects.remove(findRoomEffectByItem(item));
        item.removeEffect();
        SkeletonMenu.endCall();
    }
}
