package kevesse_kokanyolo_kod.room;

import kevesse_kokanyolo_kod.effects.*;
import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.items.IItem;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
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
     * Megadja, hogy legfeljebb hány játékos tartózkodhat egyszerre a szobában.
     */
    private int capacity;

    /**
     * A szobában tartózkodó játékosok listája.
     */
    private List<Person> people;
    /**
     * A szobához tartozó ajtók listája.
     */
    private List<Door> doors;
    /**
     * A szobában található tárgyak listája.
     */
    private List<IItem> items;
    /**
     * A szobában található mérgező hatások listája.
     */
    private ArrayList<PoisonEffect> poisonEffects;
    /**
     * A szobában található bénító hatások listája.
     */
    private ArrayList<StunEffect> stunEffects;

    /** 
     * Kezeli a szoba ragacsosságát.
     */
    private StickinessEffect stickiness;

    /**
     * Létrehozza a szobát, a paraméterül kapott szoba kapacitásával
     * 
     * @param capacity a szoba kapacitása
     */
    public Room(int capacity) {
        this.capacity = capacity;
        this.people = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
        this.poisonEffects = new ArrayList<>();
        this.stunEffects = new ArrayList<>();

        stickiness = null;
    }

    /**
     * Visszaadja a szoba ajatajainak listáját.
     * @return
     */
    public List<Door> getDoors() {
        return doors;
    }

    /**
     * Kitörli a megadott tárgyat a szoba tárgyai közül.
     *
     * @param item a megadott tárgy
     */
    public void removeItem(IItem item) {
        SkeletonMenu.startCall("Room.removeItem(Item)");
        items.remove(item);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a tárgyat a szobában lévő tárgyak listájához.
     *
     * @param item a hozzáadandó tárgy
     */
    public void addItem(IItem item) {
        SkeletonMenu.startCall("Room.addItem(Item)");
        items.add(item);
        SkeletonMenu.endCall();
    }

    /**
     * Megpróbálja átadni a szoba tárgyai közül a legfelsőt a paraméterül kapott
     * AcademicPerson-nak.
     * 
     * Ha nincs a szobában tárgy, a metódus véget ér és az AcademicPerson nem vett fel egy tárgyat sem.
     * Csak akkor tud az AcademicPerson tárgyat felvenni a szobából:
     * - ha a szoba nem ragacsos,
     * - a szobában van tárgy és még nincs az AcademicPerson-nél olyan típusú tárgy (kivétel a transistor tárgy),
     * amit megpróbál felvenni a szobából.
     * 
     * Ha ezek a feltételek teljesülnek, 
     * akkor az AcademicPerson megpróbálja felvenni a szoba tárgyai közül a legfelsőt. 
     * A Visitor Patternt alkalmazza a tárgynak átadja az AcademicPerson-t, 
     * a tárgy áthelyezése a személyhez innentől nem a szoba felelőssége.
     *
     * @param academicPerson a játékos, aki a tárgyat fel akarja venni
     */
    public void popItem(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Room.popItem(Player)");
        IItem topItem = items.get(items.size() - 1);
         if (items.isEmpty()) {
            SkeletonMenu.endCall("Nincs több tárgy a szobában.");
            return;
        } else if (stickiness != null && stickiness.isSticky()) {
            SkeletonMenu.endCall("Túl rég volt takarítás a szobában így a tárgyak ragacsosak lettek");
            return;
        } else if(academicPerson.checkHasItem(topItem)){  
            // Fontos, hogy előbb megnézzük, hogy létezik-e a tárgy, valamint, hogy más nem akadályozza-e a felvételt. 
            // Csak utána lépjenek interakcióba a tárgyak. 
            SkeletonMenu.endCall("A játékosnak már van ilyen tárgya.");
            return;
        } else if(academicPerson.getInventorySize()>4){
            //A játékos nem vehet fel több tárgyat (max 5 tárgy lehet nála)
            return;
        }
        topItem.accept(academicPerson);
        SkeletonMenu.endCall();
    }

    /**
     * Akkor hívódik, amikor egy oktató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait
     * Megpróbálja megmérgezni az oktatót, ha mérgező a szoba.
     * Megpróbálja bénítani az oktatót, ha van aktív bénító hatás a szobában.
     * Interakcióba hozza a professzort az összes szobában található személlyel.
     * Ha a szobában van stickinessEffect, jelzi neki, hogy újabb személy lépett a szobába.
     * 
     * @param professor a belépő oktató
     */
    public void onEnter(Professor professor) {
        SkeletonMenu.startCall("Room.onEnter(Professor)");
        tryPoison(professor);
        for (StunEffect stunEffect : this.stunEffects) {
            if (stunEffect.isActive()) {
                stunEffect.affect(professor);
                break;
            }
        }
        people.forEach(person -> person.meet(professor));
        if (stickiness!=null) stickiness.affect(professor);
        SkeletonMenu.endCall();
    }

    /**
     * Amikor egy hallgató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait.
     * Megpróbálja megmérgezni a hallgatót, ha mérgező a szoba.
     * Interakcióba hozza a szobában található játékosokkal.
     * A beléptető objektum felelőssége meghívni ezt a függvényt.
     *
     * @param student a belépő hallgató
     */
    public void onEnter(Student student) {
        SkeletonMenu.startCall("Room.onEnter(Student)");
        tryPoison(student);
        if (stickiness!=null) stickiness.affect(student);
        people.forEach(person -> person.meet(student));
        SkeletonMenu.endCall();
    }

    /**
     * Amikor egy takarító belép a szobába, ez a függvény gyakorolja rá a szoba hatásait.
     * Interakcióba hozza a szobában található játékosokkal.
     * A takarító:
     * - kiszellőzteti a szobát (a szoba mérgező hatásait lenulázza)
     * - kitakarítja a szobát, ha a szoba ragacsos
     * A takarítást követően, az ötödik látogató után a szoba újra ragacsossá fog válni.
     * A beléptető objektum felelőssége meghívni ezt a függvényt.
     *
     * @param cleaner a belépő takarító
     */
    public void onEnter(Cleaner cleaner) {
        SkeletonMenu.startCall("Room.onEnter(Cleaner)");
        for (Person person : this.people) person.meet(cleaner);
        this.poisonEffects.clear();
        if (stickiness!=null) stickiness.clean();
        else {
            stickiness=new StickinessEffect();
        }
        SkeletonMenu.endCall();
    }
    /**
     * Ha van aktív mérgező hatás a szobában, akkor az első aktív mérgező hatást alkalmazza a személyre.
     * 
     * @param academicPerson a játékos, akire a mérgező hatást gyakorolja a szoba
     */
    public void tryPoison(AcademicPerson academicPerson) {
        for (PoisonEffect effect : this.poisonEffects) {
            if (effect.isActive()) {
                effect.affect(academicPerson);
                break;
            }
        }
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
     * Az újonnan létrehozott szobával megosztoznak az eredeti szoba tulajdonságain a következő módon:
     * a páros indexű dolgok átkerülnek a létrejövő szobához, a páratlan indexűek maradnak.
     * (A szétosztandó dolgok: ajtók, tárgyak, poisonEffect-ek, stunEffectek)  
     * A szoba szomszédai mostantól ennek, vagy az új szobáknak csak egyikével lesznek szomszédosak.
     * Az újonnan létrehozott szoba szomszédos ezzel a szobával, az összekötő ajtó nem átkozott, látható és kétirányú.
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

        // Jöhetne létre elátkozott ajtó...
        Door door = new Door(this, room, true, true, true, false); 
        doors.add(door);
        room.doors.add(door);
        SkeletonMenu.endCall("A szoba osztódott.");
    }

    /**
     * Beleolvasztja a kapott szobát ebbe a szobába, ha nincs bennük játékos.
     * A kapacitás a két szoba kapacitásának maximuma lesz.
     * A játékosok, tárgyak és hatások összeadódnak. (lásd split)
     * Az ajtók összegyűjtésekor azokat az ajtókat eldobjuk, amik a két szoba között vannak.
     * Az ajtók közül azokat, amik a kapott szobához vezetnek, átállítjuk, hogy erre a szobára vezessenek.
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
     * Hozzáad egy személyt a szobában tartózkodó személyek listájához.
     *
     * @param person a hozzáadandó személy
     */
    public void addPlayer(Person person) {
        SkeletonMenu.startCall("Room.addPlayer(Player)");
        this.people.add(person);
        SkeletonMenu.endCall();
    }

    /**
     * Kitörli a játékost a szobából.
     *
     * @param person a kitörölni kívánt játékos
     */
    public void removePlayer(Person person) {
        SkeletonMenu.startCall("Room.removePlayer(Player)");
        this.people.remove(person);
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a mérgező hatást a szobához.
     * @param effect a hozzáadandó hatás
     */
    public void addPoisonEffect(PoisonEffect effect) {
        SkeletonMenu.startCall("Room.addEffect(RoomEffect)");
        this.poisonEffects.add(effect);
        SkeletonMenu.endCall();
    }

    /**
     * Törli a megadott hatást a szobából
     * @param effect a hatás, amit eltávolítunk a szoba hatásai közül
     */
    public void removePoisonEffect(PoisonEffect effect) {
        SkeletonMenu.startCall("Room.removeEffect(RoomEffect)");
        this.poisonEffects.remove(effect);
        SkeletonMenu.endCall();
    }
    
    /**
     * Hozzáadja a bénító hatást a szobához.
     * @param effect a hozzáadandó hatás
     */
    public void addStunEffect(StunEffect effect) {
        SkeletonMenu.startCall("Room.addEffect(RoomEffect)");
        this.stunEffects.add(effect);
        SkeletonMenu.endCall();
    }
    
    /**
     * Törli a hatást a szobából.
     * @param effect a törlendő hatás
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
     * Megkeresi a paraméterként kapott tárgyhoz tartozó hatást a StunEffect vagy a PoisonEffect listákban, és kitörli. 
     *
     * @param item a tárgy, amelyhez a tartozó hatást keresi
     */
    private void deleteRoomEffectByItem(Item item) {
        stunEffects.removeIf(stunEffect -> stunEffect.getItem().equals(item));
        poisonEffects.removeIf(poisonEffect -> poisonEffect.getItem().equals(item));
    }
    
    /**
     * Egy hatás lejártát kezeli.
     * A tárgy által adott hatást eltávolítja a szoba hatásai közül.
     * Megszünteti a tárgyhoz tartozó hatást.
     * 
     * @param effect a hatás, ami lejár
     */
    public void effectConsumed(Effect effect) {
        SkeletonMenu.startCall("Room.effectConsumed()");
        Item item = effect.getItem();
        deleteRoomEffectByItem(item);
        item.removeEffect();
        SkeletonMenu.endCall();
    }
    
    /**
     * Törli a mérgező hatásokat a szobából.
     */
    public void clearPoisonEffects() {
        SkeletonMenu.startCall("Room.clearPoisonEffects()");
        poisonEffects.clear();
        SkeletonMenu.endCall();
    }

    public void printState(Printer printer, LabyrinthBuilder builder){
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("capacity", this.capacity);
        printer.printFields("people", this.people, builder);
        printer.printFields("doors", this.doors, builder);
        printer.printFields("items", this.items, builder);
        printer.printFields("poisonEffects", this.poisonEffects, builder);
        printer.printFields("stunEffects", this.stunEffects, builder);
        printer.printField("lastCleaning", this.stickiness);
        printer.endPrintObject();
    }
}
