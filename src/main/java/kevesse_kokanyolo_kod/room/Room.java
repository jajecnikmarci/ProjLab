package kevesse_kokanyolo_kod.room;

import kevesse_kokanyolo_kod.effects.*;
import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.items.iItem;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.*;

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
    private final List<Person> people;
    /**
     * A szobához tartozó ajtók listája.
     */
    private List<Door> doors;
    /**
     * A szobában található tárgyak listája.
     */
    private List<iItem> items;
    /**
     * A szobában található hatások listája.
     */
    private ArrayList<PoisonEffect> poisonEffects;
    private ArrayList<StunEffect> stunEffects;

    private StickinessEffect lastCleaning;

    public Room(int capacity) {
        this.capacity = capacity;
        this.people = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
        this.poisonEffects = new ArrayList<>();
        this.stunEffects = new ArrayList<>();

        lastCleaning = null;
    }

    public Room() {
        people = new ArrayList<>();
        items = new ArrayList<>();
        this.poisonEffects = new ArrayList<>();
        this.stunEffects = new ArrayList<>();
        doors = new ArrayList<>();
        lastCleaning = null;
    }

    public List<Door> getDoors() {
        return doors;
    }

    /**
     * Kitörli a megadott tárgyat a szoba tárgyai közül.
     *
     * @param item a megadott tárgy
     */
    public void removeItem(iItem item) {
        SkeletonMenu.startCall("Room.removeItem(Item)");
        items.remove(item);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáad egy tárgyat a szobában lévő tárgyak listájához.
     *
     * @param item a hozzáadandó tárgy
     */
    public void addItem(iItem item) {
        SkeletonMenu.startCall("Room.addItem(Item)");
        items.add(item);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáad a szobában lévő táryak közül a legfelső tárgyat a játékos tárgyaihoz.
     *
     * @param acamedicPerson A játékos, aki a tárgyat fel akarja venni
     */
    public void popItem(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Room.popItem(Player)");
        if (items.isEmpty()) {
            SkeletonMenu.endCall("Nincs több tárgy a szobában.");
            return;
        } else if (lastCleaning.isSticky()) {
            SkeletonMenu.endCall("Túl rég volt takarítás a szobában így a tárgyak ragacsosak lettek");
            return;
        }
        items.get(items.size() - 1).accept(acamedicPerson);
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
        for (PoisonEffect poisonEffect : this.poisonEffects) {
            if (poisonEffect.isActive()) {
                poisonEffect.affect(professor);
                break;
            }
        }
        for (StunEffect stunEffect : this.stunEffects) {
            if (stunEffect.isActive()) {
                stunEffect.affect(professor);
                break;
            }
        }
        for (Person person : this.people) {
            person.meet(professor, this);
        }
        lastCleaning.affect(professor);
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
        for (PoisonEffect effect : this.poisonEffects) {
            if (effect.isActive()) {
                effect.affect(student);
                break;
            }
        }
        lastCleaning.affect(student);
        people.forEach(person -> person.meet(student));
        SkeletonMenu.endCall();
    }

    public void onEnter(Cleaner cleaner) {
        SkeletonMenu.startCall("Room.onEnter(Cleaner)");
        for (Person person : this.people) person.meet(cleaner, this);
        this.poisonEffects.clear();
        lastCleaning.clean();
        SkeletonMenu.endCall();
    }

    /**
     * Megmérgez minden játékost aki a szobában tartózkodik.
     */
    public void poisonPlayers() {
        SkeletonMenu.startCall("Room.poisonPlayers()");
        people.forEach(Person::poison);
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
        if (!people.isEmpty()) {
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

        for (int i = 0; i < poisonEffects.size(); i += 2) {
            room.poisonEffects.add(poisonEffects.remove(i));
        }
        for (int i = 0; i < stunEffects.size(); i += 2) {
            room.stunEffects.add(stunEffects.remove(i));
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
        if (!people.isEmpty() || !room.people.isEmpty()) {
            SkeletonMenu.endCall("A szobák nem olvadtak össze, mert volt bennük játékos.");
            return;
        }
        this.capacity = Math.max(this.capacity, room.capacity);
        this.people.addAll(room.people);
        this.items.addAll(room.items);
        this.poisonEffects.addAll(room.poisonEffects);
        this.stunEffects.addAll(room.stunEffects);

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
     * @param acamedicPerson a hozzáadandó player
     */
    public void addPlayer(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Room.addPlayer(Player)");
        this.people.add(acamedicPerson);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a játékost a szobából.
     *
     * @param acamedicPerson a kitörölni kívánt játékos
     */
    public void removePlayer(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Room.removePlayer(Player)");
        this.people.remove(acamedicPerson);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a hatást a szobához.
     *
     * @param effect a hozzáadandó hatás
     */
    public void addPoisonEffect(PoisonEffect effect) {
        SkeletonMenu.startCall("Room.addEffect(RoomEffect)");
        this.poisonEffects.add(effect);
        SkeletonMenu.endCall();
    }

    /**
     * @param effect
     */
    public void removePoisonEffect(PoisonEffect effect) {
        SkeletonMenu.startCall("Room.removeEffect(RoomEffect)");
        this.poisonEffects.remove(effect);
        SkeletonMenu.endCall();
    }/**
     * Hozzáadja a hatást a szobához.
     *
     * @param effect a hozzáadandó hatás
     */
    public void addStunEffect(StunEffect effect) {
        SkeletonMenu.startCall("Room.addEffect(RoomEffect)");
        this.stunEffects.add(effect);
        SkeletonMenu.endCall();
    }

    /**
     * @param effect
     */
    public void removeStunEffect(StunEffect effect) {
        SkeletonMenu.startCall("Room.removeEffect(RoomEffect)");
        this.stunEffects.remove(effect);
        SkeletonMenu.endCall();
    }

    /**
     * Ha egy játékos befér még a szobába, igaz értékkel tér vissza.
     */
    public boolean canPlayerEnter() {
        return capacity > people.size();
    }

    /**
     * Megkeresi a paraméterként kapott tárgyhoz tartozó KillImmunity-t a killImmunities listában.
     *
     * @param item a tárgy, amelyhez tartozó KillImmunity-t keresi
     */
    private void deleteRoomEffectByItem(Item item) {
        stunEffects.removeIf(stunEffect -> stunEffect.getItem().equals(item));
        poisonEffects.removeIf(poisonEffect -> poisonEffect.getItem().equals(item));
    }

    public void effectConsumed(Effect effect) {
        SkeletonMenu.startCall("Room.effectConsumed()");
        Item item = effect.getItem();
        deleteRoomEffectByItem(item);
        item.removeEffect();
        SkeletonMenu.endCall();
    }

    public void clearPoisonEffects() {
        SkeletonMenu.startCall("Room.clearPoisonEffects()");
        poisonEffects.clear();
        SkeletonMenu.endCall();
    }
}
