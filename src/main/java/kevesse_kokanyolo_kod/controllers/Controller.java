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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;


public class Controller implements StudentObserver, RoomObserver {

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
            redisplayLabyrinth();
            if(labyrinthBuilder.getPerson(name) == person){
                redisplayPlayerInfo(person);
                redisplayInventory(person);
                redisplayItemsInRoom(person.getLocation());
            }
        } );
        itemStateChangedObserver = new StateChangedObserver<Item>(i -> {
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
                redisplayLabyrinth();
                String name = labyrinthBuilder.getSelectedPerson();
                if(name!=null){
                    if(labyrinthBuilder.getPerson(name).getLocation() == room){
                        redisplayItemsInRoom(room);
                    }
                }
            }
        );


        gameWindow = new GameWindow(this);
        labyrinthView = gameWindow.labyrinthView;
        inventoryView = gameWindow.inventoryView;
        itemsInRoomView = gameWindow.itemsInRoomView;
        playerInfoView = gameWindow.playerInfoView;

        TimerTask shakeTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("SHAKE");
                shake();
            }
        };
        Timer shakeTimer = new Timer();
        shakeTimer.scheduleAtFixedRate(shakeTask, 10000, 30000);

    }

    public void init() {
        DefaultLabyrinth.create(this, labyrinthBuilder);
    }

    private void redisplayLabyrinth() {
        labyrinthView.redisplay(labyrinthBuilder);
    }

    private void redisplayInventory(Person person){
        if(person == null){
            AcademicPerson nullPerson = null;
            inventoryView.display(nullPerson);
        }
        else if(labyrinthBuilder.getStudents().get(labyrinthBuilder.getPersonName(person)) !=null || labyrinthBuilder.getProfessors().get(labyrinthBuilder.getPersonName(person)) != null) {
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
        if(person == null){
            AcademicPerson nullPerson = null;
            playerInfoView.display(nullPerson);
        }
        else if(labyrinthBuilder.getStudents().get(labyrinthBuilder.getPersonName(person))!=null || labyrinthBuilder.getProfessors().get(labyrinthBuilder.getPersonName(person))!=null) {
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
        if(labyrinthBuilder.getSelectedPerson()!=null)
            labyrinthBuilder.gotoroom(labyrinthBuilder.getSelectedPerson(), roomName);
    }

    /**
     * A labirintus tér-idő rengését valósítja meg.
     */
    public void shake() {
        labyrinthBuilder.shake();
        gameWindow.infoView.addMessage("SHAKE");
    }

    /**
     * Egy hallgató elbocsátása miatt az életben maradt hallgatók
     * száma csökken. (StudentObserver implementációja)
     */
    public void studentKilled() {
        labyrinthBuilder.studentDied();
        if(labyrinthBuilder.getStudentCount()==0) {
            int result = JOptionPane.showConfirmDialog(gameWindow, "Az oktatók megnyerték a játékot, mert eltanácsolták az összes hallgatót!","Message",JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.OK_OPTION){
                System.exit(0);
            }
        }
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
     * Automatiokusan beállítja az ajtó végpontjainak elhelyezkedését.
     * @param roomname1 a szoba, amit a roomname2 szobával köt össze
     * @param roomname2 a szoba, amit a roomname1 szobával köt össze
     * @param passable az ajtó átjárhatósága
     * @param doorName az ajtó neve
     * @param cursed az ajtó elátkozottsága
     */
    public void createDoor(String roomname1, String roomname2, boolean passable, String doorName, boolean cursed) {
        Door door = labyrinthBuilder.addDoor(roomname1, roomname2, passable, doorName, cursed);
        if(door != null) {
            Room r1 = door.getRoom1();
            Room r2 = door.getRoom2();
            IntPair l1 = labyrinthBuilder.getRoomLocations().get(r1);
            IntPair l2 = labyrinthBuilder.getRoomLocations().get(r2);
            var offsets = findMinOffset(l1, l2);
            labyrinthBuilder.setDoorEndpointOffsets(doorName, offsets[0], offsets[1]);

            door.addObserver(doorStateChangedObserver);
        }
    }

    private IntPair[] findMinOffset(IntPair pos1, IntPair pos2) {
        // Offset from center
        final IntPair topOffset = new IntPair(0, -1);
        final IntPair rightOffset = new IntPair(1, 0);
        final IntPair bottomOffset = new IntPair(0, 1);
        final IntPair leftOffset = new IntPair(-1, 0);

        IntPair[] offsets = {topOffset, rightOffset, bottomOffset, leftOffset};

        double minDistance = Double.MAX_VALUE;
        IntPair[] minOffsets = new IntPair[2];
        for (int i = 0; i < offsets.length; i++) {
            for (int j = 0; j < offsets.length; j++) {
                // Does not matter that offsets are not scaled
                double distance = IntPair.distance(pos1.add(offsets[i]), pos2.add(offsets[j]));
                if (distance < minDistance) {
                    minDistance = distance;
                    minOffsets = new IntPair[]{offsets[i], offsets[j]};
                }
            }
        }
        return minOffsets;
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

    public void deselectPerson(){
        labyrinthBuilder.setSelectedPerson(null);
        redisplayLabyrinth();
        redisplayItemsInRoom(null);
        redisplayInventory(null);
        redisplayPlayerInfo(null);
    }

    @Override
    public void studentKilled(Student student) {
        student.getLocation().removePlayer(student);
        labyrinthBuilder.getStudents().remove(labyrinthBuilder.getPersonName(student));
    }

    // TODO
    @Override
    public void slideRulePicked() {
        int result = JOptionPane.showConfirmDialog(gameWindow, "A hallgatók megnyerték a játékot!","Message",JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION){
            System.exit(0);
        }
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
        String doorName = "door"+String.valueOf(labyrinthBuilder.getNextDoorId());
        String roomName = "room"+String.valueOf(labyrinthBuilder.getNextRoomId());
        labyrinthBuilder.addRoom(roomName, newRoom);
        labyrinthBuilder.addDoor(doorName, newDoor);
        Room oldRoom = newDoor.getRoom1() == newRoom ? newDoor.getRoom2() : newDoor.getRoom1();
        IntPair oldLocation = labyrinthBuilder.getRoomLocations().get(oldRoom);
        labyrinthBuilder.setRoomLocation(newRoom, 
        oldLocation.add(new IntPair(LabyrinthView.roomWidth, 0)));
        oldLocation.set(oldLocation.x()- 20 -LabyrinthView.roomWidth, oldLocation.y()- 20);
        rearrangeLabyrinth();
        labyrinthView.redisplay(labyrinthBuilder);
        String originalRoomName = (newDoor.getRoom1() == newRoom) ? labyrinthBuilder.getRoomName(newDoor.getRoom2()) : labyrinthBuilder.getRoomName(newDoor.getRoom1());
        String newRoomName = labyrinthBuilder.getRoomName(newRoom);
        String newDoorName = labyrinthBuilder.getDoorName(newDoor);
        gameWindow.infoView.addMessage("A " + originalRoomName + " szoba osztódott, létrejött a " + newRoomName + " szoba és");
        gameWindow.infoView.addMessage("létrejött a " + newDoorName + " ajtó.");
    }

    /**
     * Áthelyezi a szobákat, ha kell, 
     * úgy, hogy ne fedjék egymást és legyen köztük legalább 20px távolság,
     * valamint az ajtókat a szobák között úgy helyezi el, hogy a lehető legkisebb legyen a hoszuk.
     */
    private void rearrangeLabyrinth() {
        Map<Room, IntPair> roomLocations = labyrinthBuilder.getRoomLocations();
        for (Room room : roomLocations.keySet()) {
            IntPair location = roomLocations.get(room);
            for (Room otherRoom : roomLocations.keySet()) {
                if (room == otherRoom) continue;
                
                IntPair otherLocation = roomLocations.get(otherRoom);
                // Supposing rooms are square
                if(IntPair.distance(otherLocation, location) < LabyrinthView.roomHeight + 20) { 
                    // Apply  repulsive force
                    IntPair direction = otherLocation.sub(location).scale(LabyrinthView.roomHeight + 20);
                    location = location.sub(direction);
                    otherLocation = otherLocation.add(direction);
                }
                
            }
        }
        for (var doorEntry: labyrinthBuilder.getDoors().entrySet()) {
            Door door = doorEntry.getValue();
            Room r1 = door.getRoom1();
            Room r2 = door.getRoom2();
            IntPair l1 = labyrinthBuilder.getRoomLocations().get(r1);
            IntPair l2 = labyrinthBuilder.getRoomLocations().get(r2);
            var offsets = findMinOffset(l1, l2);
            labyrinthBuilder.setDoorEndpointOffsets(doorEntry.getKey(), offsets[0], offsets[1]);
        }

    }

    /**
     * Paraméterként kapja a beolvadt szobát és ajtót, majd ezeket ténylegesen törli a LabyrinthBuilder-ből.
     * @param mergedRoom a szoba, ami egyesült
     * @param mergedDoor az ajtó, ami egyesült
     */
    @Override
    public void roomsMerged(Room mergedRoom, Door mergedDoor) {
        String mergedRoomName = labyrinthBuilder.getRoomName(mergedRoom);
        String roomName = (mergedDoor.getRoom1() == mergedRoom) ? labyrinthBuilder.getRoomName(mergedDoor.getRoom2()) : labyrinthBuilder.getRoomName(mergedDoor.getRoom1());

        labyrinthBuilder.removeDoor(mergedDoor);
        labyrinthBuilder.removeRoom(mergedRoom);

        IntPair r1l = labyrinthBuilder.getRoomLocations().get(mergedDoor.getRoom1());
        IntPair r2l = labyrinthBuilder.getRoomLocations().get(mergedDoor.getRoom2());

        IntPair newLocation = r1l.add(r2l).mult(0.5);

        Room changedRoom = mergedDoor.getRoom1() == mergedRoom ? mergedDoor.getRoom2() : mergedDoor.getRoom1();
        labyrinthBuilder.setRoomLocation(changedRoom, newLocation);
        // Remove door endpoint offsets from the labyrinthBuilder
        labyrinthBuilder.getDoors()
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() == mergedDoor)
            .findFirst()
            .ifPresent(
                entry-> labyrinthBuilder.removeDoorEndpointOffsets(entry.getKey())
            );

        rearrangeLabyrinth();
        redisplayLabyrinth();

        gameWindow.infoView.addMessage("A " + mergedRoomName + " szoba beleolvadt a " + roomName + " szobába.");
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
