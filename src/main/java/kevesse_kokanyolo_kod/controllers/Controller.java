package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.observer.StateChangedObserver;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Cleaner;
import kevesse_kokanyolo_kod.people.Person;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;
import kevesse_kokanyolo_kod.observer.StudentObserver;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.views.*;
import kevesse_kokanyolo_kod.windows.MenuWindow;
import kevesse_kokanyolo_kod.observer.RoomObserver;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Controller implements StudentObserver, RoomObserver {
    /**
     * Az életben maradt hallgatók számát tárolja.
     */
    //private int studentCount; //Ez benne van a LabyrinthBuilderben

    private StateChangedObserver<Person> personStateChangedObserver;
    private StateChangedObserver<Item> itemStateChangedObserver;
    private StateChangedObserver<Door> doorStateChangedObserver;
    private StateChangedObserver<Room> roomStateChangedObserver;
    /**
     * A controller tartalmazza a pályát.
     */
    private LabyrinthBuilder labyrinthBuilder;


    private InventoryView inventoryView;
    private ItemsInRoomView itemsInRoomView;
    private LabyrinthView labyrinthView;
    private PlayerInfoView playerInfoView;

    private MenuWindow menuWindow;
    Printer printer;
    
    public Controller() {
        menuWindow = new MenuWindow(this);
        printer = new Printer();
        labyrinthBuilder = new LabyrinthBuilder(printer);
        // TODO: EZEK itt példák az eseménykezelésre.  
        // personStateChangedObserver = new StateChangedObserver<Person>((Person p) -> redisplay(p));
        // itemStateChangedObserver = new StateChangedObserver<Item>(i -> redisplay(i));
        // doorStateChangedObserver = new StateChangedObserver<Door>(redisplay);
        // roomStateChangedObserver = new StateChangedObserver<Room>(redisplay);
        // labyrinthBuilder = new LabyrinthBuilder(new Printer());

        //view-ket meg kell kapni
        initGame();
    }

    private void initGame() {
        int[] roomLocations = { 840, 90, 1190, 90, 700, 220, 990, 220, 1330, 220, 525, 340, 700, 340, 1190, 340, 700,
                460, 990, 460, 525, 590, 840, 590, 1190, 590 };
        for (int i = 0; i < roomLocations.length; i++) {
            if (i % 2 == 1) {
                Room room;
                if(i == 1 || i == 10 || i == 12) room = new Room(4, true);
                else room = new Room(4);
                labyrinthBuilder.addRoom("room" + (i - 1)/2, room);
                labyrinthBuilder.setRoomLocation(room, new IntPair(roomLocations[i - 1], roomLocations[i]));
            }
        }
        // Offset from center
        IntPair topOffset = new IntPair(0, -1);
        IntPair rightOffset = new IntPair(1, 0);
        IntPair bottomOffset = new IntPair(0, 1);
        IntPair leftOffset = new IntPair(-1, 0);

        labyrinthBuilder.addDoor("room1", "room2", false, "door1", true);
        labyrinthBuilder.setDoorEndpointOffsets("door1", leftOffset,rightOffset);
    }


    private void redisplay(Person p) {
        labyrinthView.display(labyrinthBuilder);
        //többi view frissítése
    }

    /**
     * Egy hallgató vagy professzor tárgy felvételét kezeli, a tárgy a hallgatóhoz
     * vagy professzorhoz kerül, a tárgyhoz tartozó hatásokat megkapja a játékos.
     * A PlayerInfoView Pick up Item gombjára kattintva hívódik meg.
     * @param academicName a hallgató vagy oktató, akihez a tárgy kerül
     */
    public void pickUp(String academicName) {
        labyrinthBuilder.pickup(academicName);
    }

    /**
     * Egy hallgató vagy professzor tárgy elejtését kezeli, a tárgy nem
     * tartozik elejtés után a játékoshoz, a szoba legfelső tárgya lesz az elejtett tárgy.
     * @param academicName a hallgató vagy professzor, aki elejti a tárgyat
     * @param itemName a tárgy neve, amit elejt a hallgató vagy professzor
     */
    public void drop(String academicName, String itemName) {
        labyrinthBuilder.drop(academicName, itemName);
    }

    /**
     * Egy hallgató vagy professzor tárgy használatát kezeli, a tárgy használatához
     * tartozó hatásokat megkapja a játékos, ha elhasználódik a tárgy, a tárgy megsemmisül.
     * @param academicName a hallgató vagy professzor, aki megkapja a hatást
     * @param itemName a tárgy, ami a hatást adja
     */
    public void use(String academicName, String itemName) {
        labyrinthBuilder.use(academicName, itemName);
    }

    /**
     * A játékosok szobák közötti mozgását kezeli, a paraméterként kapott personName-hez
     * tartozó objektumot lekérdezi a LabyrinthBuilder-től, majd a játékost átküldi a
     * roomName paraméterhez tartozó objektumba, szobába.
     * @param personName a játékos, aki más szobába megy
     * @param roomName a szoba, ahova a játékos megy
     */
    public void goToRoom(String personName, String roomName) {
        labyrinthBuilder.gotoroom(personName, roomName);
    }

    /**
     * A labirintus tér-idő rengését valósítja meg.
     */
    public void shake() {
        labyrinthBuilder.shake();
    }

    /**
     * Egy hallgató elbocsátása miatt az életben maradt hallgatók
     * száma csökken. (StudentObserver implementációja)
     */
    public void studentKilled() {
        labyrinthBuilder.studentDied();
    }

    /**
     * Hozzáad egy szobát a labyrinthBuilderhez, valamint feliratkoztatja a
     * roomStateChangedObservert a szoba StateChanged eseményeire, és feliratkoztatja
     * magát RoomObserverként a roomSplit és roomsMerged eseményekre
     * @param name a szoba neve
     * @param capacity a szoba kapacitása
     * @param isPoisonous a szoba mérgezőssége
     * @param point a szoba elhelyezkedése a képernyőn
     */
    public void createRoom(String name, int capacity, boolean isPoisonous, Point point) {
        Room room = labyrinthBuilder.addRoom(name, capacity, isPoisonous);
        if(room != null) {
            room.addObserver(roomStateChangedObserver);
            room.addObserver(this);
        }
    }

    /**
     * Hozzáad egy ajtót a labyrinthBuilderhez, valamint feliratkoztatja a
     * doorStateChangedObservert a ajtó StateChanged eseményeire.
     * @param roomname1 a szoba, amit a roomname2 szobával köt össze
     * @param roomname2 a szoba, amit a roomname1 szobával köt össze
     * @param passable az ajtó átjárhatósága
     * @param doorName az ajtó neve
     * @param cursed az ajtó elátkozottsága
     */
    public void createDoor(String roomname1, String roomname2, boolean passable, String doorName, boolean cursed) {
        Door door = labyrinthBuilder.addDoor(roomname1, roomname2, passable, doorName, cursed);
        if(door != null) {
            door.addObserver(doorStateChangedObserver);
        }
    }

    /**
     * Hozzáad egy tárgyat a labyrinthBuilderhez, valamint feliratkoztatja a
     * itemStateChangedObservert a tárgy StateChanged eseményeire.
     * @param roomName a szoba, ahol a tárgy található
     * @param itemType a tárgy típusa
     * @param itemName a tárgy neve
     */
    public void createItem(String roomName, String itemType, String itemName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException{

        Item item = labyrinthBuilder.addItem(roomName, itemType, itemName);
        if(item != null) {
            item.addObserver(itemStateChangedObserver);
        }
    }

    /**
     * Hozzáad egy hallgatót a labyrinthBuilderhez, valamint feliratkoztatja a
     * personStateChangedObservert a hallgató StateChanged eseményeire.
     * @param roomName a szoba, ahova a hallgatót helyezi
     * @param personName a hallgató neve
     */
    public void createStudent(String roomName, String personName) {
        Person person = labyrinthBuilder.addPerson(roomName, "Student", personName);
        if(person != null) {
            person.addObserver(personStateChangedObserver);
        }
    }

    /**
     * Hozzáad egy oktatót a labyrinthBuilderhez, valamint feliratkoztatja a
     * personStateChangedObservert az oktató StateChanged eseményeire.
     * @param roomName a szoba, ahova a hallgatót vagy professzort helyezi
     * @param personName a hallgató vagy professzor neve
     */
    public void createProfessor(String roomName, String personName) {
        Person person = labyrinthBuilder.addPerson(roomName, "Professor", personName);
        if(person != null) {
            person.addObserver(personStateChangedObserver);
        }
    }

    /**
     * Hozzáad egy takarítót a labyrinthBuilderhez, valamint feliratkoztatja a
     * personStateChangedObservert a takarító StateChanged eseményeire.
     * @param roomName a szoba, ahova a takarítót helyezi
     * @param personName a takarító neve
     */
    public void createCleaner(String roomName, String personName) {
        Person person = labyrinthBuilder.addPerson(roomName, "Cleaner", personName);
        if(person != null) {
            person.addObserver(personStateChangedObserver);
        }
    }

    /**
     * Kiválasztja az adott játékost.
     * @param personName a játékos, akit kiválaszt
     */
    public void selectPerson(String personName) {
        labyrinthBuilder.setSelectedPerson(personName);
    }


    // TODO
    @Override
    public void studentKilled(Student student) {
    }

    // TODO 
    @Override
    public void slideRulePicked() {
    }

    /**
     * Amikor egy szoba osztódik, meghívja ezt a függvényt (RoomObserver implementációja),
     * a létrejött szobát és ajtót el kell tárolni, fel kell iratkozni eseményeikre
     * @param newRoom a szoba, ami osztódik
     * @param newDoor az ajtó, ami a két szobát összeköti
     */
    @Override
    public void roomSplit(Room newRoom, Door newDoor) {
        newRoom.addObserver(this);
        newRoom.addObserver(roomStateChangedObserver);
        newDoor.addObserver(doorStateChangedObserver);
        //TODO nevekre hátha lesz jobb
        String roomName = "room"+String.valueOf(labyrinthBuilder.getRoomMapSize()+1);
        String doorName = "door"+String.valueOf(labyrinthBuilder.getDoorMapSize()+1);
        labyrinthBuilder.addRoom(roomName, newRoom);
        labyrinthBuilder.addDoor(doorName, newDoor);
    }

    /**
     * Paraméterként kapja a beolvadt szobát és ajtót, majd ezeket ténylegesen törli a LabyrinthBuilder-ből.
     * @param mergedRoom a szoba, ami egyesült
     * @param mergedDoor az ajtó, ami egyesült
     */
    @Override
    public void roomsMerged(Room mergedRoom, Door mergedDoor) {
        labyrinthBuilder.removeRoom(mergedRoom);
        labyrinthBuilder.removeDoor(mergedDoor);
    }

    public LabyrinthBuilder getLabyrinthBuilder() {
        return labyrinthBuilder;
    }

    public Map<String, Student> getStudents() {
        return labyrinthBuilder.getStudents();
    }
    
    public Map<String, Professor> getProfessors() {
        return labyrinthBuilder.getProfessors();
    }

    public Map<String, Cleaner> getCleaners() {
        return labyrinthBuilder.getCleaners();
    }

    public Map<String, Room> getRooms() {
        return labyrinthBuilder.getRooms();
    }

    public Map<Room, IntPair> getRoomLocations() {
        return labyrinthBuilder.getRoomLocations();
    }
}
