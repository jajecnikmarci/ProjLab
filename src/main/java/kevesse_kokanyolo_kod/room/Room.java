package kevesse_kokanyolo_kod.room;

import kevesse_kokanyolo_kod.effects.*;
import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.items.IItem;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.observer.IRoomObservable;
import kevesse_kokanyolo_kod.observer.IStateChangedObservable;
import kevesse_kokanyolo_kod.observer.RoomObservable;
import kevesse_kokanyolo_kod.observer.RoomObserver;
import kevesse_kokanyolo_kod.observer.StateChangedObservable;
import kevesse_kokanyolo_kod.observer.StateChangedObserver;
import kevesse_kokanyolo_kod.people.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A játékban szereplő szobákat reprezentáló osztály. Kezeli a benne találjató hatásokat. 
 * Interakcióba lépteti a benne lévő játékosokat a belépő játékossal. Tárolja a benne lévő tárgyakat.
 */
public class Room implements EffectConsumedObserver, IRoomObservable, IStateChangedObservable<Room> {
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
     * Amikor egy szoba osztódik, vagy szobák egyesülnek, jelezzük az
     * eseményre feliratkozott osztályoknak. Azért nem implementálja az
     * osztály a RoomObserver interfészt, mert ez az implementáció
     * nem a szoba felelőssége. Emiatt a szoba konstruktora is módosul.
     */
    private RoomObservable roomObservable;

    private StateChangedObservable<Room> stateChangedObservable;

    /**
     * Létrehozza a szobát, a paraméterül kapott szoba kapacitásával és
     * a feliratkozók értesítéséhez szükséges observable-el.
     * 
     * @param capacity a szoba kapacitása
     * @param observable a feliratkozók értesítése
     */
    public Room(int capacity) {
        this.capacity = capacity;
        this.people = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
        this.poisonEffects = new ArrayList<>();
        this.stunEffects = new ArrayList<>();

        stickiness = null;

        roomObservable = new RoomObservable();
        stateChangedObservable = new StateChangedObservable<>(this);
    }
    public Room(int capacity, boolean poisonous) {
        this(capacity);
        if(poisonous){
            PoisonEffect poisonEffect = new PoisonEffect(null, Integer.MAX_VALUE, this);
            poisonEffects.add(poisonEffect);
            poisonEffect.activate();
        }
    }

    public boolean isPoisonous() {
        return !poisonEffects.isEmpty(); 
    }

    public int getCapacity() {
        return capacity;
    }

    public List<IItem> getItems() {
        return items;
    }

    /**
     * Hozzáadja a szoba ajtóihoz a kapott ajtót.
     * @param door a kapott ajtó
     */
    public void addDoor(Door door) {
        doors.add(door);
    }
    /**
     * Visszaadja a szoba ajatajainak listáját.
     * @return
     */
    public List<Door> getDoors() {
        return doors;
    }

    public List<Person> getPeople() {
        return people;
    }

    /**
     * Kitörli a megadott tárgyat a szoba tárgyai közül.
     *
     * @param item a megadott tárgy
     */
    public void removeItem(IItem item) {
        SkeletonMenu.startCall("Room.removeItem(Item)");
        items.remove(item);
        stateChangedObservable.notifyStateChanged();
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
        stateChangedObservable.notifyStateChanged();
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
     * akkor az AcademicPerson tárgyaival interakcióba lépteti a szoba tárgyai közül a legfelsőt. 
     * A Visitor Patternt alkalmazza a legfölső tárgynak átadja az AcademicPerson-t, 
     * a tárgy áthelyezése a személyhez innentől nem a szoba felelőssége.
     *
     * @param academicPerson a játékos, aki a tárgyat fel akarja venni
     */
    public void popItem(AcademicPerson academicPerson) {
        SkeletonMenu.startCall("Room.popItem(Player)");
        if (items.isEmpty()) {
            SkeletonMenu.endCall("Nincs több tárgy a szobában.");
            return;
        }
        IItem topItem = items.get(items.size() - 1);
        if (stickiness != null && stickiness.isSticky()) {
            SkeletonMenu.endCall("Túl rég volt takarítás a szobában így a tárgyak ragacsosak lettek");
            return;
        } else if(academicPerson.checkHasItem(topItem)){  
            // Fontos, hogy előbb megnézzük, hogy létezik-e a tárgy, valamint, hogy más nem akadályozza-e a felvételt. 
            // Csak utána lépjenek interakcióba a tárgyak. 
            SkeletonMenu.endCall("A játékosnak már van ilyen tárgya.");
            return;
        }
        topItem.accept(academicPerson);
        SkeletonMenu.endCall();
    }

    /**
     * Akkor hívódik, amikor egy oktató belép a szobába, ez a függvény gyakorolja rá a szoba hatásait
     * Megpróbálja megmérgezni az oktatót, ha mérgező a szoba.
     * Megpróbálja bénítani az oktatót, ha van aktív bénító hatás a szobában.
     * Interakcióba hozza a professzort az összes szobában található személlyel. saját magán kívűl.
     * Ha a szobában van stickinessEffect, jelzi az effektnek, hogy újabb személy lépett a szobába.
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
        for (int i = 0; i < people.size(); i++)  { // Nem használható forEach, mert a meet módosíthatja a person objektumot.
            Person person = people.get(i);
            if(person != professor) person.meet(professor);
        };
        // people.forEach(person -> person.meet(professor));
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
        for (int i = 0; i < this.people.size(); i++) {
            Person person = this.people.get(i);
            if(person != student) person.meet(student);
        }
        SkeletonMenu.endCall();
    }

    /**
     * Amikor egy takarító belép a szobába, ez a függvény gyakorolja rá a szoba hatásait.
     * Interakcióba hozza a szobában található játékosokkal.
     * A takarító:
     * - kiszellőzteti a szobát (a szoba mérgező hatásait törli)
     * - kitakarítja a szobát, ha a szoba ragacsos, ha nem, 
     *   a takarító belépését követően, az ötödik látogató után a szoba újra ragacsossá fog válni. (Létrehoz egy StickinessEffectet a szobában.)
     * A beléptető objektum felelőssége meghívni ezt a függvényt.
     *
     * @param cleaner a belépő takarító
     */
    public void onEnter(Cleaner cleaner) {
        SkeletonMenu.startCall("Room.onEnter(Cleaner)");
        for (int i = 0; i < this.people.size(); i++) {
            Person person = this.people.get(i);
            if(person != cleaner) person.meet(cleaner);
        }
        clearPoisonEffects();
       
        SkeletonMenu.endCall();
    }
    /**
     * Ha van aktív mérgező hatás a szobában, akkor az első aktív mérgező hatást alkalmazza a személyre.
     * @param academicPerson a személy, akire a mérgező hatást gyakorolja a szoba
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

        Room newRoom = new Room(capacity / 2);
        // Az ajtók felét átkötjük az új szobába.
        for (int i = 0; i < doors.size(); i += 2) {
            Door door = doors.remove(i);
            if(door.getRoom1() == this) {
                door.setRoom1(newRoom);
                // A Room2 marad
            } else {
                door.setRoom2(newRoom);
                // A Room1 marad
            }
            newRoom.addDoor(door);
        }

        
        for (int i = 0; i < items.size(); i += 2) {
            newRoom.items.add(items.remove(i));
        }

        for (int i = 0; i < poisonEffects.size(); i += 2) {
            newRoom.poisonEffects.add(poisonEffects.remove(i));
        }
        for (int i = 0; i < stunEffects.size(); i += 2) {
            newRoom.stunEffects.add(stunEffects.remove(i));
        }

        // Jöhetne létre elátkozott ajtó...
        Door newDoor = new Door(this, newRoom, true, true, true, false);
        roomObservable.notifyRoomSplit(newRoom, newDoor);

        SkeletonMenu.endCall("A szoba osztódott.");
    }

    /**
     * Beleolvasztja a kapott szobát ebbe a szobába, ha nincs bennük játékos.
     * A kapacitás a két szoba kapacitásának maximuma lesz.
     * A játékosok, tárgyak és hatások összeadódnak. (lásd split)
     * Az ajtók összegyűjtésekor azokat az ajtókat eldobjuk, amik a két szoba között vannak.
     * Az ajtók közül azokat, amik a kapott szobához vezetnek, átállítjuk, hogy erre a szobára vezessenek.
     * Jelzi a roomsMergedEvent-nek, hogy melyik ajtót és szobát kell megszüntetni.
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

        //Eltávolítandó ajtók listája
        ArrayList<Door> doorsToRemove = new ArrayList<>();

        for (int i =0; i< doors.size(); i++) {

            //A két szoba közötti ajtókat eldobjuk
            if(doors.get(i).isBetween(this, room)){
                doorsToRemove.add(0,doors.get(i));
            }else{

                for(int k =0; k< room.doors.size(); k++){

                    //A két szoba közötti ajtókat eldobjuk
                    if(room.doors.get(k).isBetween(this, room)){
                        doorsToRemove.add(room.doors.get(k));
                    }
                    else{
                        if(doors.get(i).getRoom1() == room.doors.get(k).getRoom1() || doors.get(i).getRoom1() == room.doors.get(k).getRoom2() 
                          || doors.get(i).getRoom2() == room.doors.get(k).getRoom1() || doors.get(i).getRoom2() == room.doors.get(k).getRoom2()){
                        
                            //Itt doors.get(i) és a room.doors.get(k) párhuzamos ajtók

                            //Azt összevont ajtó tulajdonságainak beállítása
                            boolean cursed = doors.get(i).isCursed() || room.doors.get(k).isCursed();
                            boolean room1Open = doors.get(i).isRoom1Open() || room.doors.get(k).isRoom1Open();
                            boolean room2Open = doors.get(i).isRoom2Open() || room.doors.get(k).isRoom2Open();

                            //A tulajdonságok beállítása a doors.get(i) ajtóra
                            doors.get(i).setDoor(room, room, room1Open, room2Open, room2Open, cursed);

                            //A másik ajtót el kell távolítani
                            doorsToRemove.add(room.doors.get(k));

                            //Az ajtó másik szobájából is törölni kell az ajtót
                            if(room.doors.get(k).getRoom1() == room)
                                room.doors.get(k).getRoom2().doors.remove(room.doors.get(k));
                            else
                                room.doors.get(k).getRoom1().doors.remove(room.doors.get(k));

                            break;
                        }
                    }
                }
            
            }
        }

        room.doors.removeAll(doorsToRemove);
        doors.removeAll(doorsToRemove);
        doors.addAll(room.doors); //Ajtók összesítése

        //duplikált ajtók eltávolítása
        doors = doors.stream()
                .distinct()
                .collect(Collectors.toList());

        notifyRoomsMerged(room, doorsToRemove);

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
        stateChangedObservable.notifyStateChanged();
        SkeletonMenu.endCall();
    }

    public void addNewPlayer(Person person) {
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
        stateChangedObservable.notifyStateChanged();
        SkeletonMenu.endCall();
    }

    /**
     * Hozzáadja a mérgező hatást a szobához.
     * @param effect a hozzáadandó hatás
     */
    public void addPoisonEffect(PoisonEffect effect) {
        SkeletonMenu.startCall("Room.addEffect(RoomEffect)");
        this.poisonEffects.add(effect);
        stateChangedObservable.notifyStateChanged();
        SkeletonMenu.endCall();
    }
       
    /**
     * Hozzáadja a bénító hatást a szobához.
     * @param effect a hozzáadandó hatás
     */
    public void addStunEffect(StunEffect effect) {
        SkeletonMenu.startCall("Room.addEffect(RoomEffect)");
        this.stunEffects.add(effect);
        stateChangedObservable.notifyStateChanged();
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
        stunEffects.removeIf(stunEffect -> ( stunEffect.getItem() != null && stunEffect.getItem().equals(item)));
        poisonEffects.removeIf(poisonEffect -> (poisonEffect.getItem() != null &&poisonEffect.getItem().equals(item)));
    }

    /**
     * Téridőrengéskor ha ez a szoba, amelyiknek ketté kell osztódnia, akkor meghívja a split() függvényt.
     * Ha ez az a szoba, amelyikbe bele kell olvadnia a másiknak, akkor megpróbálja beolvasztani magába a másikat.
     * @param roomToSplit
     * @param roomToMergeInto
     * @param roomToMerge
     */
    public void onShake(Room roomToSplit, Room roomToMergeInto, Room roomToMerge) {
        if(this == roomToSplit) {
            this.split();
        }
        if(this == roomToMergeInto) {
            this.mergeWithRoom(roomToMerge);
        }
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
        if(item!= null) {
            deleteRoomEffectByItem(item);
            item.removeEffect();
            stateChangedObservable.notifyStateChanged();
        }
        SkeletonMenu.endCall();
    }
    
    /**
     * Törli a mérgező hatásokat a szobából.
     */
    public void clearPoisonEffects() {
        SkeletonMenu.startCall("Room.clearPoisonEffects()");
        poisonEffects.clear();
        if (stickiness!=null) stickiness.clean();
        else stickiness=new StickinessEffect();
        
        stateChangedObservable.notifyStateChanged();
        SkeletonMenu.endCall();
    }

    public void printState(Printer printer, LabyrinthBuilder builder){
        printer.startPrintObject(builder.getInstanceName(this));
        printer.printField("capacity", this.capacity);
        printer.printFields("people", this.people, builder);
        printer.printFields("doors", this.doors, builder);
        printer.printFields("items", this.items, builder);
        printer.printFields("poisonEffects", "poisonEffects", this.poisonEffects.size());
        printer.printFields("stunEffects", "stunEffects", this.stunEffects.size());
        printer.printField("lastCleaning", stickiness == null? "null" : "StickinessEffect");

        printer.endPrintObject();
    }

    @Override
    public void notifyRoomSplit(Room room, Door door) {
        roomObservable.notifyRoomSplit(room, door);
    }

    @Override
    public void notifyRoomsMerged(Room room, ArrayList<Door> doors) {
        roomObservable.notifyRoomsMerged(room, doors);
    }

    @Override
    public void addObserver(RoomObserver observer) {
        roomObservable.addObserver(observer);
    }

    @Override
    public void addObserver(StateChangedObserver<Room> observer) {
        stateChangedObservable.addObserver(observer);
    }
    
    @Override
    public void notifyStateChanged() {
        stateChangedObservable.notifyStateChanged();
    }

    public boolean isSticky() {
        return stickiness != null && stickiness.isSticky();
    }
    public boolean hasStunEffect() {
        return !stunEffects.isEmpty();
    }
}
