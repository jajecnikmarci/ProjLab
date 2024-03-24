import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import items.*;
import player.*;
import room.*;

class Test {
    private String title;
    private Runnable test;

    public Test(String name, Runnable test) {
        this.title = name;
        this.test = test;
    }

    public String getTitle() {
        return title;
    }

    public void run() {
        test.run();
    }
}

/**
 * Szkeleton osztály. Tárolja a teszteseteket. Kiírja a menüt.
 * Teszt hozzáadaása:
 * (1) Teszt függvény megírása az ostályon belül (lásd test 1)
 * (2) konstruktorban hozzáadás a tests listához.
 * 
 */
public class Skeleton {
    class Test {
        private String title;
        private Runnable test;

        public Test(String name, Runnable test) {
            this.title = name;
            this.test = test;
        }

        public String getTitle() {
            return title;
        }

        public void run() {
            print("Running Test: " + title);
            test.run();
            print("End of test: " + title + "\n");
        }
    }

    List<Test> tests;

    public Skeleton() {
        tests = new ArrayList<>();
        /**
         * Itt történik a tesztek hozzáadása (2)
         */
        // tests.add(new Test("Test 1", this::test1));
        tests.add(new Test("Pickup FFP2", this::testPickUpFFP2));
        tests.add(new Test("Pickup Camembert", this::testPickUpCamembert));
        tests.add(new Test("Pickup Glass", this::testPickUpGlass));
        tests.add(new Test("Pickup Rug", this::testPickUpRug));
        tests.add(new Test("Pickup Sliderule", this::testPickUpSlideRule));
        tests.add(new Test("Pickup Transistor", this::testPickUpTransistor));
        tests.add(new Test("Pickup TVSZ", this::testPickUpTVSZ));
        tests.add(new Test("Use Rug", this::testUseRug));
        tests.add(new Test("Use Camembert", this::testUseCamembert));
        tests.add(new Test("Pair Transistors", this::testPairTransistors));
        tests.add(new Test("Drop Transistor", this::testDropTransistor));
        tests.add(new Test("Teleport with Transistors", this::testTeleportWithTransistors));
        tests.add(new Test("Split Room", this::testSplitRoom));
        tests.add(new Test("Merge Rooms", this::testMergeRooms));
        tests.add(new Test("Move to Room", this::testMoveToRoom));
        tests.add(new Test("Use Glass", this::testUseGlass));
        tests.add(new Test("Player Gets poisoned", this::testPlayerGetsPoisoned));
        tests.add(new Test("Student Gets Defended from Professor with Glass",
                this::testStudentGetsDefendedFromProfessorWithGlass));
        tests.add(new Test("Player Gets Defended from Poison", this::testPlayerGetsDefendedFromPoison));
    }

    private void print(String string) {
        System.out.println(string);
    }

    private void err(String string) {
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
        print(msg);
        try {
            String str = scanner.nextLine();
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
        print(msg + " (y/n)");
        ;
        String str = scanner.nextLine();
        if (str.toLowerCase().equals("y"))
            return true;
        if (str.toLowerCase().equals("n"))
            return false;
        return getBoolean(msg);
    }

    /**
     * Kiírja a teszteket.
     */
    private void printTests() {
        System.out.println("m - Menü");
        System.out.println("q - Kilépés");
        System.out.println("Válasszon tesztet: ");
        for (int i = 0; i < tests.size(); i++) {
            System.out.println(i + 1 + ". " + tests.get(i).getTitle());
        }
        System.out.println();
    }

    Scanner scanner;

    /**
     * Elindítja a Skeleton menüt.
     */
    public void menu() {
        scanner = new Scanner(System.in);
        printTests();
        do {
            String input;
            try {
                input = scanner.nextLine();
                if (input.equals("q"))
                    break;
                else if (input.equals("m")) {
                    printTests();
                    continue;
                }
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < tests.size())
                    tests.get(index).run();
                else
                    print("Nincs ilyen teszt!");
            } catch (NumberFormatException e) {
                err("Adjon meg számot!");
            } catch (NoSuchElementException e) {
                break;
            }

        } while (scanner.hasNextLine());
        scanner.close();
    }

    /*
     * Példa teszt függvény. (1)
     */
    // private void test1() {
    // print("Test 1 executed");
    // }

    public void testUseCamembert() {

        Student student = new Student(null);
        Item camembert = new Camembert();
        student.addItem(camembert);
        Room room = new Room(10);
        room.addPlayer(student);

        student.useItem(camembert);
    }

    public void testPairTransistors() {
        Student student = new Student(null);
        Item transistor1 = new Transistor();
        Item transistor2 = new Transistor();

        student.addItem(transistor1);
        student.addItem(transistor2);

        student.useItem(transistor1);
    }

    public void testDropTransistor() {
        // ilyenkor is kiíródnak a függvények, de ilyenkor nem kellene
        Room room = new Room(1);
        Student student = new Student(room);
        Transistor transistor1 = new Transistor();
        transistor1.setRoom(room);
        Transistor transistor2 = new Transistor();
        transistor2.setRoom(room);

        student.addItem(transistor1);
        student.addItem(transistor2);

        transistor1.setPair(transistor2);
        transistor2.setPair(transistor1);

        student.useItem(transistor1);
    }

    public void testTeleportWithTransistors() {
        Student student = new Student(null);
        Transistor transistor1 = new Transistor();
        Transistor transistor2 = new Transistor();

        student.addItem(transistor1);

        transistor1.setPair(transistor2);
        transistor2.setPair(transistor1);

        Room room1 = new Room(10);
        Room room2 = new Room(10);

        transistor2.setRoom(room2);
        room1.addPlayer(student);

        student.useItem(transistor1);

    }

    public void testPickUpFFP2() {
        Room room = new Room();
        Student student = new Student(room);
        FFP2 item = new FFP2();
        room.addPlayer(student);
        room.addItem(item);
        student.pickUpItem();
    }

    public void testPickUpCamembert() {
        Room r = new Room();
        Student s = new Student(r);
        Camembert i = new Camembert();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();
    }

    public void testPickUpGlass() {
        Room r = new Room();
        Student s = new Student(r);
        Glass i = new Glass();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();
    }

    public void testPickUpTVSZ() {
        Room r = new Room();
        Student s = new Student(r);
        TVSZ i = new TVSZ();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();
    }

    public void testPickUpTransistor() {
        Room r = new Room();
        Student s = new Student(r);
        Transistor i = new Transistor();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();
    }

    public void testPickUpRug() {
        Room r = new Room();
        Student s = new Student(r);
        Rug i = new Rug();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();
    }

    public void testPickUpSlideRule() {
        Room r = new Room();
        Student s = new Student(r);
        SlideRule i = new SlideRule();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();
    }

    public void testUseRug() {
        Room r = new Room();
        Student s = new Student(r);
        Rug i = new Rug();
        r.addPlayer(s);
        s.addItem(i);

        i.use(r, s);
    }

    private void testSplitRoom() {
        int capacity = getInt("Mekkora szobát legyen a szoba kapacitása");

        boolean hasPlayer = getBoolean("Legyen játékos a szobában?");
        Room room = new Room(capacity);
        if (hasPlayer) {
            room.addPlayer(new Student(null));
        }
        room.split();
    }

    private void testMergeRooms() {
        int capacity1 = getInt("Mekkora legyen az 1. szoba kapacitása?");
        int capacity2 = getInt("Mekkora legyen az 2. szoba kapacitása?");
        boolean hasPlayer = getBoolean("Legyen játékos a szobákban?");
        Room room1 = new Room(capacity1);
        if (hasPlayer) {
            room1.addPlayer(new Student(null));
        }
        Room room2 = new Room(capacity2);
        room1.mergeWithRoom(room2);
    }

    private void testMoveToRoom() {
        Room room1 = new Room(4);
        Room room2 = new Room(4);
        Door door = new Door(room1, room2, true, true);
        Student student = new Student(room1);
        room1.addPlayer(student);
        door.goThrough(student);
    }

    private void testUseGlass() {
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        Glass glass = new Glass();
        student.addItem(glass);
        student.useItem(glass);
    }

    private void testPlayerGetsPoisoned() {
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        Rug rug = new Rug();
        Glass glass = new Glass();
        student.addItem(rug);
        student.addItem(glass);
        student.poison();
    }

    private void testStudentGetsDefendedFromProfessorWithGlass() {
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        Glass glass = new Glass();
        student.addItem(glass);
        student.useItem(glass);
        Professor professor = new Professor(room);
        room.addPlayer(professor);
    }

    private void testPlayerGetsDefendedFromPoison() {
        Room room = new Room();
        Student student = new Student(room);
        room.addPlayer(student);
        TVSZ tvsz = new TVSZ();
        student.addItem(tvsz);
        student.useItem(tvsz);
        student.poison();
    }
}
