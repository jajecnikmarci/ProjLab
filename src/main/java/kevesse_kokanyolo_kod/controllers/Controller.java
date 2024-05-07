package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.items.Item;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.observer.StateChangedObserver;
import kevesse_kokanyolo_kod.people.Person;
import kevesse_kokanyolo_kod.people.Student;
import kevesse_kokanyolo_kod.observer.StudentObserver;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.observer.RoomObserver;

import java.awt.*;

public class Controller implements StudentObserver, RoomObserver {
    /**
     * Az életben maradt hallgatók számát tárolja.
     */
    private int studentCount;
    private StateChangedObserver<Person> personStateChangedObserver;
    private StateChangedObserver<Item> itemStateChangedObserver;
    private StateChangedObserver<Door> doorStateChangedObserver;
    private StateChangedObserver<RoomObserver> roomStateChangedObserver;
    /**
     * A controller tartalmazza a pályát.
     */
    private LabyrinthBuilder labyrinthBuilder;

    /**
     * Egy hallgató vagy professzor tárgy felvételét kezeli, a tárgy a hallgatóhoz
     * vagy professzorhoz kerül, a tárgyhoz tartozó hatásokat megkapja a játékos.
     * @param academicName a hallgató vagy oktató, akihez a tárgy kerül
     */
    public void pickUp(String academicName) {
    }

    /**
     * Egy hallgató vagy professzor tárgy elejtését kezeli, a tárgy nem
     * tartozik elejtés után a játékoshoz, a szoba legfelső tárgya lesz az elejtett tárgy.
     * @param academicName a hallgató vagy professzor, aki elejti a tárgyat
     * @param itemName a tárgy neve, amit elejt a hallgató vagy professzor
     */
    public void drop(String academicName, String itemName) {
    }

    /**
     * Egy hallgató vagy professzor tárgy használatát kezeli, a tárgy használatához
     * tartozó hatásokat megkapja a játékos, ha elhasználódik a tárgy, a tárgy megsemmisül.
     * @param academicName a hallgató vagy professzor, aki megkapja a hatást
     * @param itemName a tárgy, ami a hatást adja
     */
    public void use(String academicName, String itemName) {
    }

    /**
     * A játékosok szobák közötti mozgását kezeli, a paraméterként kapott personName-hez
     * tartozó objektumot lekérdezi a LabyrinthBuilder-től, majd a játékost átküldi a
     * roomName paraméterhez tartozó objektumba, szobába.
     * @param personName a játékos, aki más szobába megy
     * @param roomName a szoba, ahova a játékos megy
     */
    public void goToRoom(String personName, String roomName) {
    }

    /**
     * A labirintus tér-idő rengését valósítja meg.
     */
    public void shake() {
    }

    /**
     * Egy hallgató elbocsátása miatt az életben maradt hallgatók
     * száma csökken. (StudentObserver implementációja)
     */
    public void studentKilled() {
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
    }

    /**
     * Hozzáad egy tárgyat a labyrinthBuilderhez, valamint feliratkoztatja a
     * itemStateChangedObservert a tárgy StateChanged eseményeire.
     * @param roomName a szoba, ahol a tárgy található
     * @param itemType a tárgy típusa
     * @param itemName a tárgy neve
     */
    public void createItem(String roomName, String itemType, String itemName) {
    }

    /**
     * Hozzáad egy hallgatót a labyrinthBuilderhez, valamint feliratkoztatja a
     * personStateChangedObservert a hallgató StateChanged eseményeire.
     * @param roomName a szoba, ahova a hallgatót helyezi
     * @param personName a hallgató neve
     */
    public void createStudent(String roomName, String personName) {
    }

    /**
     * Hozzáad egy oktatót a labyrinthBuilderhez, valamint feliratkoztatja a
     * personStateChangedObservert az oktató StateChanged eseményeire.
     * @param roomName a szoba, ahova a hallgatót vagy professzort helyezi
     * @param personName a hallgató vagy professzor neve
     */
    public void createProfessor(String roomName, String personName) {
    }

    /**
     * Hozzáad egy takarítót a labyrinthBuilderhez, valamint feliratkoztatja a
     * personStateChangedObservert a takarító StateChanged eseményeire.
     * @param roomName a szoba, ahova a takarítót helyezi
     * @param personName a takarító neve
     */
    public void createCleaner(String roomName, String personName) {
    }

    /**
     * Kiválasztja az adott játékost.
     * @param personName a játékos, akit kiválaszt
     */
    public void selectPerson(String personName) {
    }

    @Override
    public void studentKilled(Student student) {
    }

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
    }

    /**
     * Paraméterként kapja a beolvadt szobát és ajtót, majd ezeket ténylegesen törli a LabyrinthBuilder-ből.
     * @param mergedRoom a szoba, ami egyesült
     * @param mergedDoor az ajtó, ami egyesült
     */
    @Override
    public void roomsMerged(Room mergedRoom, Door mergedDoor) {
    }
}
