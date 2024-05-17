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
import kevesse_kokanyolo_kod.windows.GameWindow;
import kevesse_kokanyolo_kod.observer.RoomObserver;

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

    // private MenuWindow menuWindow;
    private GameWindow gameWindow;
    Printer printer;
    
    public Controller() {
        printer = new Printer();
        labyrinthBuilder = new LabyrinthBuilder(printer);
        personStateChangedObserver = new StateChangedObserver<Person>(person->{
            System.out.println("Player changed");
            String name = labyrinthBuilder.getSelectedPerson();
            redisplayLabyrinth(); //TODO bénulás jelölés. játékos törlés + kill nem máshol van?
            if(labyrinthBuilder.getPerson(name) == person){
                redisplayPlayerInfo(person);
                redisplayInventory(person);
                redisplayItemsInRoom(person.getLocation());
            }
        } );
        itemStateChangedObserver = new StateChangedObserver<Item>(i->{
            System.out.println("Item changed");
            String name = labyrinthBuilder.getSelectedPerson();

            if(labyrinthBuilder.getStudents().get(name) != null){
                if(labyrinthBuilder.getStudents().get(name).getInventory().contains(i)) redisplayInventory(labyrinthBuilder.getStudents().get(name));
            }
            else if(labyrinthBuilder.getProfessors().get(name) != null){
                if(labyrinthBuilder.getProfessors().get(name).getInventory().contains(i)) redisplayInventory(labyrinthBuilder.getProfessors().get(name));
            }
            else if (labyrinthBuilder.getCleaners().get(name) != null) {
                redisplayInventory(labyrinthBuilder.getCleaners().get(name));
            }
            else{
                System.out.println("Nem talált xd");
            }
        } 
        );
        
        doorStateChangedObserver = new StateChangedObserver<Door>(d->{
            System.out.println("Door changed");
            redisplayLabyrinth();
        } 
        );
        roomStateChangedObserver = new StateChangedObserver<Room>(room->{
                System.out.println("Room changed");
                System.out.println("poisonous: " +room.isPoisonous());
                redisplayLabyrinth();
                String name = labyrinthBuilder.getSelectedPerson();
                if(name!=null){
                    if(labyrinthBuilder.getPerson(name).getLocation() == room){
                        redisplayItemsInRoom(room);
                    }
                }
            }
        );



        // innentől kezdve eseményvezérelt a programunk!
        //SwingUtilities.invokeLater(() ->  {
            gameWindow = new GameWindow(this);
            labyrinthView = gameWindow.labyrinthView;
            inventoryView = gameWindow.inventoryView;
            itemsInRoomView = gameWindow.itemsInRoomView;
            playerInfoView = gameWindow.playerInfoView;
        //});
    }

    public void init(){
        initGame();
    }

    private void initGame() {
        int[] roomLocations = { 
            840, 90,    // 0
            1190, 90,   // 1
            700, 220,   // 2
            990, 220,   // 3
            1330, 220,  // 4
            525, 340,   // 5
            700, 340,   // 6
            1190, 340,  // 7
            700, 460,   // 8
            990, 460,   // 9
            525, 590,   // 10
            840, 590,   // 11
            1190, 590   // 12
        };
        boolean poisonousRooms[] = new boolean[13];
        poisonousRooms[0] = true;
        poisonousRooms[9] = true;
        poisonousRooms[11] = true;

        for (int i = 0; i < roomLocations.length / 2; i++) {
            IntPair location = new IntPair(roomLocations[i * 2], roomLocations[i * 2 + 1]);
            createRoom("room" + i, 4, poisonousRooms[i], location);
        }

        // Offset from center
        final IntPair topOffset = new IntPair(0, -1);
        final IntPair rightOffset = new IntPair(1, 0);
        final IntPair bottomOffset = new IntPair(0, 1);
        final IntPair leftOffset = new IntPair(-1, 0);

        createDoor("room0", "room1", true, "door0", false);
        labyrinthBuilder.setDoorEndpointOffsets("door0", rightOffset, leftOffset);
        
        createDoor("room0", "room2", true, "door1", true);
        labyrinthBuilder.setDoorEndpointOffsets("door1", leftOffset, topOffset);
        
        createDoor("room0", "room3", false, "door2", true);
        labyrinthBuilder.setDoorEndpointOffsets("door2", rightOffset, topOffset);
        
        createDoor("room2", "room5", true, "door3", false);
        labyrinthBuilder.setDoorEndpointOffsets("door3", leftOffset, rightOffset);

        createDoor("room1", "room4", true, "door4", false);
        labyrinthBuilder.setDoorEndpointOffsets("door4", rightOffset, topOffset);
        
        createDoor("room2", "room3", false, "door5", false);
        labyrinthBuilder.setDoorEndpointOffsets("door5", rightOffset, leftOffset);
        
        createDoor("room7", "room1", false, "door6", false);
        labyrinthBuilder.setDoorEndpointOffsets("door6", topOffset, bottomOffset);
        
        createDoor("room2", "room6", true, "door7", false);
        labyrinthBuilder.setDoorEndpointOffsets("door7", bottomOffset, topOffset);
        
        createDoor("room8", "room3", false, "door8", false);
        labyrinthBuilder.setDoorEndpointOffsets("door8", rightOffset, bottomOffset );

        createDoor("room4", "room7", false, "door9", true);
        labyrinthBuilder.setDoorEndpointOffsets("door9", bottomOffset, rightOffset);
        
        createDoor("room5", "room8", true, "door10", true);
        labyrinthBuilder.setDoorEndpointOffsets("door10", rightOffset, leftOffset);
        
        createDoor("room6", "room8", false, "door11", false);
        labyrinthBuilder.setDoorEndpointOffsets("door11", bottomOffset, topOffset);
        
        createDoor("room8", "room9", true, "door12", false);
        labyrinthBuilder.setDoorEndpointOffsets("door12", rightOffset, leftOffset);
        
        createDoor("room7", "room9", true, "door13", false);
        labyrinthBuilder.setDoorEndpointOffsets("door13", bottomOffset, rightOffset);

        createDoor("room10", "room8", false, "door14", false);
        labyrinthBuilder.setDoorEndpointOffsets("door14", rightOffset, bottomOffset);
        
        createDoor("room9", "room11", false, "door15", false);
        labyrinthBuilder.setDoorEndpointOffsets("door15", leftOffset, topOffset);
        
        createDoor("room11", "room10", false, "door16", false);
        labyrinthBuilder.setDoorEndpointOffsets("door16", leftOffset, rightOffset);
        
        createDoor("room11", "room12", true, "door17", true);
        labyrinthBuilder.setDoorEndpointOffsets("door17", rightOffset, leftOffset);

        createStudent("room1", "S1");
        createCleaner("room1", "C3");
        createProfessor("room3", "P3");
            
        createItem("room1", "FFP2", "FFP2");
        createItem("room1", "Camembert", "Camembert");
       

    }

    private void redisplayLabyrinth() {
        labyrinthView.redisplay(labyrinthBuilder);
    }

    private void redisplayInventory(Person person){
        if(labyrinthBuilder.getStudents().get(labyrinthBuilder.getPersonName(person)) !=null || labyrinthBuilder.getProfessors().get(labyrinthBuilder.getPersonName(person)) != null) {
            AcademicPerson academicPerson = (AcademicPerson) person;
            inventoryView.display(academicPerson);
        }
        else if(labyrinthBuilder.getCleaners().get(labyrinthBuilder.getPersonName(person))!=null){
            Cleaner cleaner = (Cleaner) person;
            inventoryView.display(cleaner);
        }
    }

    private void redisplayItemsInRoom(Room room){
        itemsInRoomView.display(room);
    }

    private void redisplayPlayerInfo(Person person){
        if(labyrinthBuilder.getStudents().get(labyrinthBuilder.getPersonName(person))!=null || labyrinthBuilder.getProfessors().get(labyrinthBuilder.getPersonName(person))!=null) {
            AcademicPerson academicPerson = (AcademicPerson) person;
            playerInfoView.display(academicPerson);
        }
        else if(labyrinthBuilder.getCleaners().get(labyrinthBuilder.getPersonName(person))!=null){
            Cleaner cleaner = (Cleaner) person;
            playerInfoView.display(cleaner);
        }
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
     * roomName paraméterhez tartozó objektumba, szobába
     * @param roomName a szoba, ahova a játékos megy
     */
    public void goToRoom(String roomName) {
        if(!labyrinthBuilder.getSelectedPerson().isEmpty())
            labyrinthBuilder.gotoroom(labyrinthBuilder.getSelectedPerson(), roomName);
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
     * @param location a szoba elhelyezkedése a képernyőn
     */
    public void createRoom(String name, int capacity, boolean isPoisonous, IntPair location) {
        Room room = labyrinthBuilder.addRoom(name, capacity, isPoisonous);
        labyrinthBuilder.setRoomLocation(room, location);
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
    public void createItem(String roomName, String itemType, String itemName) {
        Item item = null;
        try {
            item = labyrinthBuilder.addItem(roomName, itemType, itemName);
        } catch(Exception e) {
            e.printStackTrace();
        }

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
            ((Student)person).addObserver(this);
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
        redisplayLabyrinth();
        redisplayItemsInRoom(labyrinthBuilder.getPerson(personName).getLocation());
        redisplayInventory(labyrinthBuilder.getPerson(personName));
        redisplayPlayerInfo(labyrinthBuilder.getPerson(personName));
    }


    // TODO
    @Override
    public void studentKilled(Student student) {
        student.getLocation().removePlayer(student);
        labyrinthBuilder.getStudents().remove(labyrinthBuilder.getPersonName(student));
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
