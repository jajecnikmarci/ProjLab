package kevesse_kokanyolo_kod.menus;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.items.fakes.*;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.views.IntPair;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Cleaner;
import kevesse_kokanyolo_kod.people.Person;
import kevesse_kokanyolo_kod.effects.Timer;

public class LabyrinthBuilder {

    Map<String, Room> rooms = new HashMap<>();
    Map<String, Item> items = new HashMap<>();
    Map<String, Door> doors = new HashMap<>();
    Map<String, Cleaner> cleaners = new HashMap<>();
    Map<String, AcademicPerson> academicPeople = new HashMap<>();
    Map<String, Student> students = new HashMap<>();
    Map<String, Professor> professors = new HashMap<>();

    Map<Room, IntPair> roomLocations = new HashMap<>();
    Map<Door, IntPair[]> doorOffsets = new HashMap<>(); // maps doorName to first and second endpoint offset

    private int studentCount = 0; 
    static Map<String, Timer> timers = new HashMap<>(); 

    Map<String, Class<?>> ItemclassMap = new HashMap<>();

    Printer printer;

    private String selectedPerson;
    /**
     * Csak feltölti a nevekhez a megfelelő osztályokat
     */
    public LabyrinthBuilder(Printer printer) {

        this.printer=printer;
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


    public void setSelectedPerson(String selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
    public String getSelectedPerson() {
        return selectedPerson;
    }

    public Map<String, Door> getDoors() {
        return doors;
    }

    public Map<String, Student> getStudents() {
        return students;
    }
    
    public Map<String, Professor> getProfessors() {
        return professors;
    }

    public Map<String, Cleaner> getCleaners() {
        return cleaners;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public Map<Room, IntPair> getRoomLocations() {
        return roomLocations;
    }

    public IntPair[] getDoorOffsets(Door door) {
        return doorOffsets.get(door);
    }

    public void setRoomLocation(Room room, IntPair location){
        roomLocations.put(room, location);
    }

    public void setRoomLocation(Room room, int x, int y){
        roomLocations.put(room, new IntPair(x,y));
    }

    public void setDoorEndpointOffsets(String doorName, IntPair startOffset, IntPair endOffset){
        doorOffsets.put(doors.get(doorName), new IntPair [] {startOffset, endOffset});
    }
    public void removeDoorEndpointOffsets(String doorName){
        doorOffsets.remove(doors.get(doorName));
    }

    public Person getPerson(String personName){
        if(students.get(personName) != null){
            return students.get(personName);
        }

        if(professors.get(personName) != null){
            return professors.get(personName);
        }

        if(cleaners.get(personName) != null){
            return cleaners.get(personName);
        }

        return null;
    }

    public String getPersonName(Person person){
        for (var studentEntry : students.entrySet()) {
            if(studentEntry.getValue()==person) return studentEntry.getKey();
        }

        for (var professorEntry : professors.entrySet()) {
            if(professorEntry.getValue()==person) return professorEntry.getKey();
        }

        for (var cleanerEntry : cleaners.entrySet()) {
            if(cleanerEntry.getValue()==person) return cleanerEntry.getKey();
        }

        return null;
    }

    public String getRoomName(Room room){
        for (var roomEntry : rooms.entrySet()) {
            if(roomEntry.getValue() == room) return roomEntry.getKey();
        }

        return null;
    }

    public String getDoorName(Door door){
        for (var doorEntry : doors.entrySet()) {
            if(doorEntry.getValue() == door) return doorEntry.getKey();
        }

        return null;
    }

    /** 
     * Nyilvántartjuk a kiosztott ajtók és szobák számait, 
     * hogy ne legyen ütközés merge utáni split esetén. 
     */
    private int lastRoomId = 0;
    private int lastDoorId = 0;
    public int getNextDoorId() {
        return lastDoorId+1;
    }
    public int getNextRoomId() {
        return lastRoomId+1;
    }
    /**
     * Először ellenőrzi, hogy a szoba neve már szerepel-e a listában, ha nem akkor
     * létrehozza és hozzáadja
     * 
     * @param name     Szoba neve
     * @param capacity Szoba kapacitása
     * @param printer  Printer objektum
     */
    public Room addRoom(String name, int capacity, boolean isPoisonous) {
        if (rooms.keySet().contains(name)) {
            printer.printError("A szoba már szerepel a listában!");
            return null;
        }
        Room room = new Room(capacity, isPoisonous);
        addRoom(name, room);
        return room;
    }

    /**
     * Először ellenőrzi, hogy a szoba neve már szerepel-e a listában, ha nem akkor
     * hozzáadja
     * 
     * @param name     Szoba neve
     * @param Room     Kész Szoba 
     */
    public void addRoom(String name, Room room) {
        if (rooms.keySet().contains(name)) {
            printer.printError("A szoba már szerepel a listában!");
            return;
        }

        rooms.put(name, room);
        lastRoomId++;
    }

    /**
     * Kitörli a kapott szobát a tárolt szobákból
     * 
     * @param room kitörlendő szoba
     */
    public void removeRoom(Room room){
        for (var roomEntry : rooms.entrySet()) {
            if(roomEntry.getValue().equals(room)) {
                rooms.remove(roomEntry.getKey());
                break;
            }
        }
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
    public Item addItem(String roomName, String itemType, String itemName)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {

        for (String key : items.keySet()) {
            if (items.get(key).equals(itemName)) {
                printer.printError("A tárgy már szerepel a listában!");
                return null;
            }
        }

        Class<?> classType = ItemclassMap.get(itemType);
        Item item = (Item) classType.getConstructor().newInstance();

        items.put(itemName, item);
        rooms.get(roomName).addItem(item);

        return item;
    }

    /**
     * Ajtó létrehozása és hozzáadása
     * 
     * @param roomname1 Első szoba neve
     * @param roomname2 Második szoba neve
     * @param passable  Átjárható-e az első szoba felől
     * @param cursed    Átkos-e az ajtó
     */
    public Door addDoor(String roomname1, String roomname2, boolean passable, String doorName, boolean cursed) {
        if (!rooms.containsKey(roomname1) || !rooms.containsKey(roomname2)) {
            printer.printError("Nincs ilyen nevű szoba.");
            return null;
        }
        Room room1 = rooms.get(roomname1);
        Room room2 = rooms.get(roomname2);

        Door door = new Door(room1, room2, true, passable, true, cursed);
        doors.put(doorName, door);
        lastDoorId++;
        return door;
    }

    /**
     * Ajtó hozzáadása
     * 
     * @param doorName  Ajtó neve
     * @param door      Kész ajtó
     */
    public void addDoor(String doorName, Door door) {
        doors.put(doorName, door);
        lastDoorId++;
    }

    /**
     * Kitörli a kapott ajtót a tárolt ajtókból
     * 
     * @param room kitörlendő ajtó
     */
    public void removeDoor(Door door){
        for (var doorEntry : doors.entrySet()) {
            if(doorEntry.getValue().equals(door)) {
                doors.remove(doorEntry.getKey());
                break;
            }
        }
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
    public Person addPerson(String roomName, String personType, String personName) {
        for (String key : cleaners.keySet()) {
            if (cleaners.get(key).equals(personName)) {
                printer.printError("A játékos már szerepel a listában!");
                return null;
            }
        }
        for (String key : academicPeople.keySet()) {
            if (academicPeople.get(key).equals(personName)) {
                printer.printError("A játékos már szerepel a listában!");
                return null;
            }
        }

        Person person = null;

        if (personType.equals("Cleaner")) {
            Cleaner cleaner = new Cleaner(rooms.get(roomName));
            person = cleaner;
            cleaners.put(personName, cleaner);
            rooms.get(roomName).addNewPlayer(cleaner);

        } else if (personType.equals("Student")) {
            Student student = new Student(rooms.get(roomName));
            person = student;
            studentCount++;
            academicPeople.put(personName, student);
            rooms.get(roomName).addNewPlayer(student);
            students.put(personName, student);

        } else if (personType.equals("Professor")) {
            Professor professor = new Professor(rooms.get(roomName));
            person = professor;
            academicPeople.put(personName, professor);
            rooms.get(roomName).addNewPlayer(professor);
            professors.put(personName, professor);
        }

        return person;
    }

    /**
     * Tárgy felvétele a szobából
     * 
     * @param academicName Játékos neve
     */
    public void pickup(String academicName) {
        if (!academicPeople.containsKey(academicName)) {
            printer.printError("Nincs ilyen nevű játékos.");
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
            printer.printError("Nincs ilyen nevű játékos.");
            return;
        }
        if (!items.containsKey(itemName)) {
            printer.printError("Nincs ilyen nevű tárgy.");
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
            printer.printError("Nincs ilyen nevű játékos.");
            return;
        }
        if (!items.containsKey(itemName)) {
            printer.printError("Nincs ilyen nevű tárgy.");
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
            printer.printError("Nincs ilyen nevű játékos.");
            return;
        }
        if (!rooms.containsKey(roomName)) {
            printer.printError("Nincs ilyen nevű szoba.");
            return;
        }

        if(academicPeople.containsKey(personName))
            academicPeople.get(personName).goToRoom(rooms.get(roomName));

        if(cleaners.containsKey(personName))
            cleaners.get(personName).goToRoom(rooms.get(roomName));
    }

    /**
     * Ez a függvény feltételezi, hogy vannak ajtók a labirintusban
     */
    private Door chooseRandomDoor() {
        List<Door> doorList = new ArrayList<>(doors.values());
        Random random = new Random();
        return doorList.get(random.nextInt(doorList.size()));
    }

    /**
     * Ez a függvény feltételezi, hogy vannak szobák a labirintusban
     */
    private Room chooseRandomRoom() {
        List<Room> roomList = new ArrayList<>(rooms.values());
        Random random = new Random();
        return roomList.get(random.nextInt(roomList.size()));
    }

    public String newRoomName, newDoorName;
    public void shake() {
        Door randomDoor = chooseRandomDoor();

        Room roomToMergeInto, roomToMerge, roomToSplit;
        roomToMergeInto = randomDoor.getRoom1();
        roomToMerge = randomDoor.getRoom2();
        roomToSplit = chooseRandomRoom();
        List<Room> roomList = new ArrayList<>(rooms.values());
        for (int i = 0; i < roomList.size(); i++) {
            var room = roomList.get(i);
            room.onShake(roomToSplit, roomToMergeInto, roomToMerge);
        }
        doors.values().forEach(door -> door.onShake());
    }

    public String getInstanceName(Item item) {
        return items.keySet()
            .stream()
            .filter(key -> items.get(key)
            .equals(item))
            .findFirst()
            .orElse(null);
    }
    public String getInstanceName(Room room) {
        return rooms.keySet()
            .stream()
            .filter(key -> rooms.get(key)
            .equals(room))
            .findFirst()
            .orElse(null);
    }
    public String getInstanceName(AcademicPerson person) {
        return academicPeople.keySet()
            .stream()
            .filter(key -> academicPeople.get(key)
            .equals(person))
            .findFirst()
            .orElse(null);
    }
    public String getInstanceName(Cleaner cleaner) {
        return cleaners.keySet()
            .stream()
            .filter(key -> cleaners.get(key)
            .equals(cleaner))
            .findFirst()
            .orElse(null);
    }
    public String getInstanceName(Door door) {
        return doors.keySet()
            .stream()
            .filter(key -> doors.get(key)
            .equals(door))
            .findFirst()
            .orElse(null);
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
    public void clearTimers() {
        timers.clear();
    
    }
    public int getStudentCount() {
        return studentCount;
    }

    public void studentDied() {
        studentCount--;
    }
}
