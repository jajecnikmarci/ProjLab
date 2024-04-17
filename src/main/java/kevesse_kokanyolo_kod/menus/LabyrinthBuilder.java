package kevesse_kokanyolo_kod.menus;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.items.fakes.*;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Cleaner;
import kevesse_kokanyolo_kod.effects.Timer;

public class LabyrinthBuilder {

    Map<String, Room> rooms = new HashMap<>();
    Map<String, Item> items = new HashMap<>();
    Map<String, Door> doors = new HashMap<>();
    Map<String, Cleaner> cleaners = new HashMap<>();
    Map<String, AcademicPerson> academicPeople = new HashMap<>();

    static Map<String, Timer> timers = new HashMap<>(); 

    Map<String, Class<?>> ItemclassMap = new HashMap<>();

    /**
     * Csak feltölti a nevekhez a megfelelő osztályokat
     */
    public LabyrinthBuilder() {

        ItemclassMap.put("FakeFFP2", FakeFFP2.class);
        ItemclassMap.put("FakeSlideRule", FakeSlideRule.class);
        ItemclassMap.put("FakeTVSZ", FakeTVSZ.class);
        ItemclassMap.put("AirFreshener", AirFreshener.class);
        ItemclassMap.put("Camembert", Camembert.class);
        ItemclassMap.put("FFP2", FFP2.class);
        ItemclassMap.put("Glass", Glass.class);
        ItemclassMap.put("Rug", Rug.class);
        ItemclassMap.put("SlideRule", SlideRule.class);
        ItemclassMap.put("Transistor", Transistor.class);
        ItemclassMap.put("TVSZ", TVSZ.class);
}

    /**
     * Először ellenőrzi, hogy a szoba neve már szerepel-e a listában, ha nem akkor
     * hozzáadja
     * 
     * @param name     Szoba neve
     * @param capacity Szoba kapacitása
     * @param printer  Printer objektum
     */
    public void addRoom(String name, int capacity, boolean isPoisonous, Printer printer) {

        for (String key : rooms.keySet()) {
            if (rooms.get(key).equals(name)) {
                printer.printError("A szoba már szerepel a listában!");
                return;
            }
        }

        rooms.put(name, new Room(capacity, isPoisonous));
    }

    /**
     * Ellenőrzi, hogy a tárgy neve már szerepel-e a listában
     * Ha nem, akkor hozzáadja a megfelelő osztályt a megfelelő szobához
     * A konstuktorban feltöltött ItemclassMap segítségével tudja, hogy melyik
     * osztályt kell példányosítani
     * 
     * @param roomName szoba neve
     * @param itemType tárgy típusa
     * @param itemName tárgy neve
     * @param printer  Printer objektum
     */
    public void addItem(String roomName, String itemType, String itemName, Printer printer)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {

        for (String key : items.keySet()) {
            if (items.get(key).equals(itemName)) {
                printer.printError("A tárgy már szerepel a listában!");
                return;
            }
        }

        Class<?> classType = ItemclassMap.get(itemType);
        Item item = (Item) classType.getConstructor().newInstance();

        items.put(itemName, item);
        rooms.get(roomName).addItem(item);
    }

    /**
     * Szoba hozzáadása
     * 
     * @param roomname1 Első szoba neve
     * @param roomname2 Második szoba neve
     * @param passable  Átjárható-e az első szoba felől
     * @param cursed    Átkos-e az ajtó
     */
    public void addDoor(String roomname1, String roomname2, boolean passable, String doorName, boolean cursed) {
        if (!rooms.containsKey(roomname1) || !rooms.containsKey(roomname2)) {
            System.err.println("Nincs ilyen nevű szoba.");
            return;
        }
        Room room1 = rooms.get(roomname1);
        Room room2 = rooms.get(roomname2);

        Door door = new Door(room1, room2, true, passable, true, cursed);
        doors.put(doorName, door);
    }

    /**
     * Játékos hozzáadása
     * Itt is először ellenőrzi, hogy a játékos neve már szerepel-e a listában
     * 
     * @param roomName   szoba neve
     * @param personType Játékos típusa
     * @param personName Játékos neve
     * @param printer    Printer objektum
     */
    public void addPerson(String roomName, String personType, String personName, Printer printer) {
        for (String key : cleaners.keySet()) {
            if (cleaners.get(key).equals(personName)) {
                printer.printError("A játékos már szerepel a listában!");
                return;
            }
        }
        for (String key : academicPeople.keySet()) {
            if (academicPeople.get(key).equals(personName)) {
                printer.printError("A játékos már szerepel a listában!");
                return;
            }
        }

        if (personType.equals("Cleaner")) {
            Cleaner cleaner = new Cleaner(rooms.get(roomName));
            cleaners.put(personName, cleaner);
            rooms.get(roomName).addPlayer(cleaner);

        } else if (personType.equals("Student")) {
            Student student = new Student(rooms.get(roomName));
            academicPeople.put(personName, student);
            rooms.get(roomName).addPlayer(student);

        } else if (personType.equals("Professor")) {
            Professor professor = new Professor(rooms.get(roomName));
            academicPeople.put(personName, professor);
            rooms.get(roomName).addPlayer(professor);
        }
    }

    /**
     * Tárgy felvétele a szobából
     * 
     * @param academicName Játékos neve
     */
    public void pickup(String academicName) {
        if (!academicPeople.containsKey(academicName)) {
            System.err.println("Nincs ilyen nevű játékos.");
            return;
        }
        academicPeople.get(academicName).pickUpItem();
    }

    /**
     * A játékosok tárgyának eldobása
     * 
     * @param academicName Játékos neve
     * @param itemName     Tárgy neve
     */
    public void drop(String academicName, String itemName) {
        if (!academicPeople.containsKey(academicName)) {
            System.err.println("Nincs ilyen nevű játékos.");
            return;
        }
        if (!items.containsKey(itemName)) {
            System.err.println("Nincs ilyen nevű tárgy.");
            return;
        }
        academicPeople.get(academicName).dropItem(items.get(itemName));
    }

    /**
     * A játékosok tárgyának használata
     * 
     * @param academicName Játékos neve
     * @param itemName     Tárgy neve
     */
    public void use(String academicName, String itemName) {
        if (!academicPeople.containsKey(academicName)) {
            System.err.println("Nincs ilyen nevű játékos.");
            return;
        }
        if (!items.containsKey(itemName)) {
            System.err.println("Nincs ilyen nevű tárgy.");
            return;
        }
        academicPeople.get(academicName).useItem(items.get(itemName));
    }

    /**
     * A játékosok szobába mozgatása
     * 
     * @param personName Játékos neve
     * @param roomName   Szoba neve
     */
    public void gotoroom(String personName, String roomName) {
        if (!academicPeople.containsKey(personName) && !cleaners.containsKey(personName)) {
            System.err.println("Nincs ilyen nevű játékos.");
            return;
        }
        if (!rooms.containsKey(roomName)) {
            System.err.println("Nincs ilyen nevű szoba.");
            return;
        }

        if(academicPeople.containsKey(personName))
            academicPeople.get(personName).goToRoom(rooms.get(roomName));

        if(cleaners.containsKey(personName))
            cleaners.get(personName).goToRoom(rooms.get(roomName));
    }


    public String newRoomName, newDoorName;
    public void shake() {
        boolean isRandom = ProtoMenu.getRandomness();
        Room roomToMergeInto, roomToMerge, roomToSplit;
        if(!isRandom) {
            while (true) {
                String input[] = ProtoMenu.readString("Melyik szoba osztódjon, mi legyen a neve? <RoomToSplit> <NewRoomName> <NewDoorName>").split(" ");
                if (input.length < 3) System.err.println("Nem adott meg paramétert.");
                if(rooms.containsKey(input[0])) {
                    roomToSplit = rooms.get(input[0]);
                    newRoomName = input[1];
                    newDoorName = input[2];
                    break;
                } else {
                    System.err.println("Nincs ilyen nevű szoba.");
                }
                System.err.println("Nincs ilyen nevű szoba.");
            }
            while ((roomToMergeInto = rooms.get(ProtoMenu.readString("Melyik szobába olvadjon bele a másik?"))) == null) {
                System.err.println("Nincs ilyen nevű szoba.");
            }
            while ((roomToMerge = rooms.get(ProtoMenu.readString("Melyik szoba olvadjon bele a másikba?"))) == null) {
                System.err.println("Nincs ilyen nevű szoba.");
            }
            List<Room> roms = rooms.values().stream().collect(Collectors.toList());
            for(int i = 0; i < roms.size(); i++) {
                roms.get(i).onShake(roomToSplit, roomToMergeInto, roomToMerge);
            }
            //roomToSplit.split();
            //roomToMergeInto.mergeWithRoom(roomToMerge);
            
        } else {

        }
        doors.values().forEach(door -> door.onShake());
        
    }


    public String getInstanceName(Item item) {
        return items.keySet()
            .stream()
            .filter(key -> items.get(key)
            .equals(item))
            .findFirst()
            .orElse("");
    }
    public String getInstanceName(Room room) {
        return rooms.keySet()
            .stream()
            .filter(key -> rooms.get(key)
            .equals(room))
            .findFirst()
            .orElse("");
    }
    public String getInstanceName(AcademicPerson person) {
        return academicPeople.keySet()
            .stream()
            .filter(key -> academicPeople.get(key)
            .equals(person))
            .findFirst()
            .orElse("");
    }
    public String getInstanceName(Cleaner cleaner) {
        return cleaners.keySet()
            .stream()
            .filter(key -> cleaners.get(key)
            .equals(cleaner))
            .findFirst()
            .orElse("");
    }
    public String getInstanceName(Door door) {
        return doors.keySet()
            .stream()
            .filter(key -> doors.get(key)
            .equals(door))
            .findFirst()
            .orElse("");
    }

    /**
     * A kiválasztott objektum állapotának kiírása
     * 
     * @param name    Kiválasztott objektum neve
     * @param printer Printer objektum
     */
    public void printState(String name, Printer printer) {  
        if (rooms.containsKey(name)) rooms.get(name).printState(printer, this);
        if (doors.containsKey(name)) doors.get(name).printState(printer, this);
        if (items.containsKey(name)) items.get(name).printState(printer, this);
        if (cleaners.containsKey(name)) cleaners.get(name).printState(printer, this);
        if (academicPeople.containsKey(name)) academicPeople.get(name).printState(printer, this);
    }

    /**
     * Az összes szoba, ajtó, tárgy, takarító és játékos állapotának kiírása
     * 
     * @param printer Printer objektum
     */
    public void printAll(Printer printer) {
        for (String key : rooms.keySet()) {
            rooms.get(key).printState(printer, this);
        }
        for (String key : doors.keySet()) {
            doors.get(key).printState(printer, this);
        }
        for (String key : items.keySet()) {
            items.get(key).printState(printer, this);
        }
        for (String key : cleaners.keySet()) {
            cleaners.get(key).printState(printer, this);
        }
        for (String key : academicPeople.keySet()) {
            academicPeople.get(key).printState(printer, this);
        }
    }

    static public void addTimer(Timer timer){
        int num = timers.size()+1;
        String name = "timer" + num;
        timers.put(name, timer);
    }
    public void endTimer(String timerName) {
        timers.get(timerName).cancel();
    }
}
