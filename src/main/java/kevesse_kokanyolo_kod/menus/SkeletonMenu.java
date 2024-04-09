package kevesse_kokanyolo_kod.menus;

import kevesse_kokanyolo_kod.effects.StunEffect;
import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.items.fakes.FakeSlideRule;
import kevesse_kokanyolo_kod.people.Cleaner;
import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.people.Student;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Szkeleton osztály. Tárolja a teszteseteket. Kiírja a menüt.
 * Teszt hozzáadaása:
 * (1) Teszt függvény megírása az ostályon belül (lásd test 1)
 * (2) konstruktorban hozzáadás a tests listához.
 * <p>
 * Az összes lényeges függvénynek meg kell hívnia a startCall és endCall függvényeket.
 * A startCall-nak a függvény fejlécét kell átadni.
 * Az endCallnak a függvény visszatérési értékét kell átadni, vagy hogy mi történt,
 * amiért visszatért a függvény. (Ha nem egyértelmű)
 * Az összes függvény minden lehetséges visszatérése előtt meg kell hívni az endCall függvényt.
 * <p>
 * A verbose változóval lehet ki-be kapcsolni a kiírást. Ha igaz, akkor kiírja a függvényeket, ha hamis, akkor nem.
 * A teszt függvényekben a verbose változót ki kell kapcsolni az iniciálásnál, mert ott is hívódhatnak függvények, de
 * ott azokat nem kell kiírni. Az iniciálás után igazra kell állítani a verbose változót.
 */
public class SkeletonMenu {
    static boolean disablePrint = false;
    static boolean fromAndToFile;
    static FileWriter fileWriter;
    static Scanner scanner;
    private static int indentCounter = 0;
    private static boolean verbose = true;
    private void initializeTest(){
        tests = new ArrayList<>();
        /**
         * Itt történik a tesztek hozzáadása (2)
         */
        // tests.add(new Test("Test 1", this::test1));
        tests.add(new Test("Move To Room", this::testMoveToRoom));
        tests.add(new Test("Pickup FFP2", this::testPickUpFFP2));
        tests.add(new Test("Pickup Camembert", this::testPickUpCamembert));
        tests.add(new Test("Pickup Glass", this::testPickUpGlass));
        tests.add(new Test("Pickup Rug", this::testPickUpRug));
        tests.add(new Test("Pickup Sliderule", this::testPickUpSlideRule));
        tests.add(new Test("Pickup Transistor", this::testPickUpTransistor));
        tests.add(new Test("Pickup TVSZ", this::testPickUpTVSZ));
        tests.add(new Test("Pickup AirFreshener", this::testPickUpAirFreshener));
        tests.add(new Test("Pickup fake SlideRule", this::testPickUpFakeSlideRule));
        tests.add(new Test("Pickup Camembert in sticky room", this::testPickUpCamembertInStickyRoom));

        tests.add(new Test("Use Camembert", this::testUseCamembert));
        tests.add(new Test("Use Glass", this::testUseGlass));
        tests.add(new Test("Use Rug", this::testUseRug));
        tests.add(new Test("Use AirFreshener", this::testUseAirFreshener));
        tests.add(new Test("Room poisons Academic", this::testRoomPoisonsAcademic));

        tests.add(new Test("Pair Transistors", this::testPairTransistors));
        tests.add(new Test("Drop Transistor", this::testDropTransistor));
        tests.add(new Test("Teleport with Transistor", this::testTeleportWithTransistor));
        tests.add(new Test("Split Room", this::testSplitRoom));
        tests.add(new Test("Merge Rooms", this::testMergeRooms));

        tests.add(new Test("TVSZ defends Student", this::testTVSZdefendsStudent));
        tests.add(new Test("Glass defends Student", this::testGlassdefendsStudent));
        tests.add(new Test("FFP2 defends Academic", this::testFFP2defendsAcademic));
        tests.add(new Test("Professor meets Student", this::testProfessorMeetsStudent));
        tests.add(new Test("Professor meets Professor", this::testProfessorMeetsProfessor));
        tests.add(new Test("Cleaner meets Academic", this::testCleanerMeetsAcademic));
        tests.add(new Test("Rug stuns professor", this::testRugStunsProfessor));
    }

    List<Test> tests;
    public SkeletonMenu(String inputFileName,String outputFileName) throws IOException {
        initializeTest();
        fileWriter = new FileWriter(outputFileName);
        scanner = new Scanner(new File(inputFileName));
    }
    public SkeletonMenu() {
        initializeTest();
        scanner = new Scanner(System.in);
    }

    public static void setFromAndToFile(boolean fromAndToFile) {
        SkeletonMenu.fromAndToFile = fromAndToFile;
    }

    public static void startCall(String methodHeader) {

        if (verbose) {
            printLn(methodHeader);
            indentCounter++;
        }
    }

    public static void endCall(String result) {

        if (verbose) {
            indentCounter--;
            printLn("<--" + result);
        }

    }

    public static void endCall() {

        if (verbose) {
            indentCounter--;
            printLn("<-- void");
        }
    }

    public static void printLn(String string) {
        if (disablePrint) return;
        try {
            for (int i = 0; i < indentCounter - 1; i++) {
                if (fromAndToFile) fileWriter.write("\t");
                else System.out.print("\t");
            }
            if (fromAndToFile) fileWriter.write(" " + string + "\n");
            else System.out.println(string);
        } catch (IOException e) {
            System.out.println("Nem lehet a fájlba írni!");
            System.exit(1);
        }
    }

    public static void printLn() {
        if (fromAndToFile) {
            try {
                fileWriter.write("\n");
            } catch (IOException e) {
                System.out.println("Nem lehet a fájlba írni!");
                System.exit(1);
            }
        } else System.out.println();
    }

    public static void err(String string) {
        System.err.println(string);
    }

    /**
     * Segédfüggvény bekér ehy számot a felhasználótól.
     *
     * @param msg kiírandó üzenet
     * @return
     * @throws NoSuchElementException
     */
    private int getInt(String msg) throws NoSuchElementException {
        printLn(msg);
        try {
            String str;
            str = scanner.nextLine();
            return Integer.parseInt(str);

        } catch (NumberFormatException numberFormat) {
            err("Nem számot adott meg!");
            return getInt(msg);
        }
    }

    /*
     * Bekér egy booleant a felhasználótól.
     *
     * @param msg kiírandó üzenet
     */
    private boolean getBoolean(String msg) throws NoSuchElementException {
        printLn(msg + " (y/n)");

        String str = scanner.nextLine();
        if (str.equalsIgnoreCase("y"))
            return true;
        if (str.equalsIgnoreCase("n"))
            return false;
        return getBoolean(msg);
    }

    /**
     * Kiírja a teszteket.
     */
    private void printTests() {
        printLn("m - Menü");
        printLn("q - Kilépés");
        printLn("Válasszon tesztet: ");
        for (int i = 0; i < tests.size(); i++) {
            printLn(i + 1 + ". " + tests.get(i).getTitle());
        }
        printLn();
    }

    public void executeAllTests() {
        for (Test test : tests) {

            test.run();

        }
    }

    public void executeTest(String input) {
        int index = Integer.parseInt(input) - 1;
        if (index >= 0 && index < tests.size()) // run test
        {

            tests.get(index).run();

        } else {
            printLn("Nincs ilyen teszt!");
        }
    }

    /**
     * Elindítja a Skeleton menüt.
     */
    public void menu() {

        printTests();
        do {
            String input;
            try {
                input = scanner.nextLine();
                if (fromAndToFile) printLn(input);
                if (input.equals("q"))
                    break;
                else if (input.equals("m")) {
                    printTests();
                    continue;
                }
                executeTest(input);
            } catch (NumberFormatException e) {
                err("Adjon meg számot!");
            } catch (NoSuchElementException e) {
                break;
            }

        } while (scanner.hasNextLine());

        scanner.close();
        if (fromAndToFile){
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void testUseCamembert() {

        verbose = false;
        Room room = new Room(10);
        Student student = new Student(room);
        Item camembert = new Camembert();
        room.addItem(camembert);
        student.pickUpItem();
        room.addPlayer(student);

        verbose = true;
        student.useItem(camembert);
    }

    /*
     * Példa teszt függvény. (1)
     */
    // private void test1() {
    // print("Test 1 executed");
    // }

    public void testPairTransistors() {

        verbose = false;
        Room room = new Room(10);
        Student student = new Student(room);
        Transistor transistor1 = new Transistor();
        Transistor transistor2 = new Transistor();

        room.addItem(transistor1);
        room.addItem(transistor2);
        student.pickUpItem();
        student.pickUpItem();

        verbose = true;
        student.useItem(transistor1);     
    }

    public void testDropTransistor() {

        verbose = false;
        Room room1 = new Room(10);

        Student student = new Student(room1);
        room1.addPlayer(student);
        Transistor transistor1 = new Transistor();
        Transistor transistor2 = new Transistor();


        room1.addItem(transistor1);
        room1.addItem(transistor2);
        student.pickUpItem();
        student.pickUpItem();

        transistor1.setPair(transistor2);
        transistor2.setPair(transistor1);

        verbose = true;
        student.useItem(transistor1);

    }

    public void testTeleportWithTransistor() {

        verbose = false;
        Room room1 = new Room(10);
        Room room2;

        boolean hasPlace = false;

        hasPlace = getBoolean("Van hely a másik szobában?");

        if (hasPlace) {
            room2 = new Room(10);
        } else {
            room2 = new Room(0);
        }

        Student student = new Student(room1);
        room1.addPlayer(student);
        Transistor transistor1 = new Transistor();
        Transistor transistor2 = new Transistor();

        room1.addItem(transistor1);
        student.pickUpItem();

        transistor1.setPair(transistor2);
        transistor2.setPair(transistor1);

        transistor2.setRoom(room2);

        verbose = true;
        student.useItem(transistor1);
    }

    public void testPickUpFFP2() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        FFP2 item = new FFP2();
        room.addPlayer(student);
        room.addItem(item);
        verbose = true;
        student.pickUpItem();
    }

    public void testPickUpCamembert() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        Camembert camembert = new Camembert();
        room.addPlayer(student);
        room.addItem(camembert);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpGlass() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        Glass glass = new Glass();
        room.addPlayer(student);
        room.addItem(glass);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpTVSZ() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        TVSZ tvsz = new TVSZ();
        room.addPlayer(student);
        room.addItem(tvsz);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpAirFreshener() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        AirFreshener airFreshener = new AirFreshener();
        room.addPlayer(student);
        room.addItem(airFreshener);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpFakeSlideRule() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        FakeSlideRule fakeSlideRule = new FakeSlideRule();
        room.addPlayer(student);
        room.addItem(fakeSlideRule);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpCamembertInStickyRoom() {
        verbose = false;
        Room room = new Room();
        Cleaner cleaner = new Cleaner(room);
        room.addPlayer(cleaner);
        room.onEnter(cleaner);
        room.removePlayer(cleaner);
        Student student = new Student(room);
        room.addPlayer(student);
        //Szimuláljuk, hogy bejön 5 hallgató
        room.onEnter(student);
        room.onEnter(student);
        room.onEnter(student);
        room.onEnter(student);
        room.onEnter(student);
        Camembert camembert = new Camembert();
        room.addItem(camembert);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpTransistor() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        Transistor transistor = new Transistor();
        room.addPlayer(student);
        room.addItem(transistor);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpRug() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        Rug rug = new Rug();
        room.addPlayer(student);
        room.addItem(rug);
        verbose = true;

        student.pickUpItem();
    }

    public void testPickUpSlideRule() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        SlideRule slideRule = new SlideRule();
        room.addPlayer(student);
        room.addItem(slideRule);
        verbose = true;

        student.pickUpItem();
    }

    public void testUseRug() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        Rug rug = new Rug();
        room.addPlayer(student);
        student.addItem(rug);

        verbose = true;
        rug.use(room, student);
    }

    public void testUseAirFreshener() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        AirFreshener airFreshener = new AirFreshener();
        room.addPlayer(student);
        student.addItem(airFreshener);

        verbose = true;
        airFreshener.use(room, student);
    }

    /**
     * A tárgyak elvétele a játékostól nem íródik ki, mert a tárgyak listáján a clear metódus lett meghívva.
     */
    public void testRoomPoisonsAcademic() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        Rug rug = new Rug();
        Glass glass = new Glass();
        student.addItem(rug);
        student.addItem(glass);

        verbose = true;
        student.poison();
    }

    private void testSplitRoom() {

        int capacity = getInt("Mekkora legyen a szoba kapacitása");

        boolean hasPlayer = getBoolean("Legyen játékos a szobában?");

        verbose = false;
        Room room = new Room(capacity);
        if (hasPlayer) {
            room.addPlayer(new Student(null));
        }
        verbose = true;
        room.split();
    }

    private void testMergeRooms() {
        int capacity1 = getInt("Mekkora legyen az 1. szoba kapacitása?");
        int capacity2 = getInt("Mekkora legyen az 2. szoba kapacitása?");
        boolean hasPlayer = getBoolean("Legyen játékos a szobákban?");
        verbose = false;
        Room room1 = new Room(capacity1);
        if (hasPlayer) {
            room1.addPlayer(new Student(null));
        }
        Room room2 = new Room(capacity2);
        verbose = true;

        room1.mergeWithRoom(room2);
    }

    private void testMoveToRoom() {
        verbose = false;
        Room room1 = new Room(4);
        Room room2 = new Room(4);
        Door door = new Door(room1, room2, true, true);
        Student student = new Student(room1);
        room1.addPlayer(student);
        verbose = true;
        door.goThrough(student);
    }

    private void testUseGlass() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        Glass glass = new Glass();
        student.addItem(glass);
        verbose = true;
        student.useItem(glass);
    }

    private void testTVSZdefendsStudent() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        TVSZ tvsz = new TVSZ();
        room.addItem(tvsz);
        verbose = true;
        student.pickUpItem();
        student.kill();
    }

    private void testGlassdefendsStudent() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        Glass glass = new Glass();
        room.addItem(glass);
        student.pickUpItem();
        verbose = true;
        student.useItem(glass);
        Professor professor = new Professor(room);
        room.addPlayer(professor);
        room.onEnter(professor);
    }

    private void testFFP2defendsAcademic() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        FFP2 ffp2 = new FFP2();
        room.addItem(ffp2);
        verbose = true;
        student.pickUpItem();
        student.poison();
    }

    private void testProfessorMeetsStudent() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        verbose = true;
        Professor professor = new Professor(room);
        room.addPlayer(professor);
        room.onEnter(professor);
    }

    private void testProfessorMeetsProfessor() {
        verbose = false;
        Room room = new Room();
        Professor professor1 = new Professor(room);
        room.addPlayer(professor1);
        verbose = true;
        Professor professor2 = new Professor(room);
        room.addPlayer(professor2);
        room.onEnter(professor2);
    }

    private void testCleanerMeetsAcademic() {
        verbose = false;
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        verbose = true;
        Cleaner cleaner = new Cleaner(room);
        room.addPlayer(cleaner);
        room.onEnter(cleaner);
    }

    private void testRugStunsProfessor() {
        verbose = false;
        Room room = new Room();
        Room room2 = new Room();
        Professor professor = new Professor(room2);
        Rug rug = new Rug();
        StunEffect stunEffect = new StunEffect(rug, 30, room);
        room.addStunEffect(stunEffect);
        stunEffect.activate();
        verbose = true;
        room.onEnter(professor);
    }

    class Test {
        private final String title;
        private final Runnable test;

        public Test(String name, Runnable test) {
            this.title = name;
            this.test = test;
        }

        public String getTitle() {
            return title;
        }

        public void run() {
            printLn("Running Test: " + title);
            indentCounter++;
            test.run();
            indentCounter--;
            printLn("End of test: " + title + "\n");
        }
    }
}
