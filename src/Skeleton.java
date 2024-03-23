import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import room.Room;
import player.Student;
import items.*;

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
=======
import player.Student;
import room.Room;

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
            test.run();
        }
    }

    List<Test> tests;

    public Skeleton() {
        tests = new ArrayList<>();
        /**
         * Itt történik a tesztek hozzáadása (2)
         */
        // tests.add(new Test("Test 1", this::test1));

        tests.add(new Test("Use Camembert", this::testUseCamembert));
        tests.add(new Test("Pair Transistors", this::testPairTransistors));
        tests.add(new Test("Drop Transistor", this::testDropTransistor));
        tests.add(new Test("Teleport With Transistors", this::testTeleportWithTransistors));
        tests.add(new Test("Split room", this::testSplitRoom));
        tests.add(new Test("Merge rooms", this::testMergeRooms));
    }

    private void print(String string) {
        System.out.println(string);
    }

    private void err(String string) {
        System.err.println(string);
    }

    /**
     * Segédfüggvény bekér ehy számot a felhasználótól.
     * @param msg kiírandó üzenet
     * @return
     * @throws NoSuchElementException
     */
    private int getInt(String msg) throws NoSuchElementException {
        print(msg);
        try {
            String str = scanner.nextLine();
            return Integer.parseInt(str);

        } catch(NumberFormatException numberFormat) {
            err("Nem számot adott meg!");
            return getInt(msg);
        }
    }
    /*
     * Bekér egy booleant a felhasználótól.
     * @param msg kiírandó üzenet
     */
    private boolean getBoolean(String msg) throws NoSuchElementException {
        print(msg + " (y/n)");;
        String str = scanner.nextLine();
        if(str.toLowerCase().equals("y")) return true;
        if(str.toLowerCase().equals("n")) return false;
        return getBoolean(msg);
    }
    /**
     * Kiírja a teszteket.
     */
    private void printTests() {
        System.out.println("Válasszon tesztet: (q - kilépés)");
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
                if (input.equals("q")) break;
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
            printTests();

        } while (scanner.hasNextLine());
        scanner.close();
    }

    /*
     * Példa teszt függvény. (1)
     */
    // private void test1() {
    // print("Test 1 executed");
    // }

    public void testUseCamembert(){

        Student student = new Student();
        Item camembert = new Camembert();
        student.addItem(camembert);
        Room room = new Room(10);
        room.addPlayer(student);

        student.useItem(camembert);
    }

    public void testPairTransistors(){

        Student student = new Student();
        Item transistor1 = new Transistor();
        Item transistor2 = new Transistor();

        student.addItem(transistor1);
        student.addItem(transistor2);

        student.useItem(transistor1);
    }

    public void testDropTransistor(){

        //ilyenkor is kiíródnak a függvények, de ilyenkor nem kellene
        Student student = new Student();
        Transistor transistor1 = new Transistor();
        Transistor transistor2 = new Transistor();

        student.addItem(transistor1);
        student.addItem(transistor2);

        transistor1.setPair(transistor2);
        transistor2.setPair(transistor1);

        student.useItem(transistor1);

    }

    public void testTeleportWithTransistors(){

        Student student = new Student();
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

    
    private void testSplitRoom() {
        int capacity = getInt("Mekkora szobát legyen a szoba kapacitása");
        
        boolean hasPlayer = getBoolean("Legyen játékos a szobában?");
        Room room = new Room(capacity);
        if (hasPlayer) {
            room.addPlayer(new Student());
        }
        room.split();
    }

    private void testMergeRooms() {
        int capacity1 = getInt("Mekkora legyen az 1. szoba kapacitása?");
        int capacity2 = getInt("Mekkora legyen az 2. szoba kapacitása?");
        boolean hasPlayer = getBoolean("Legyen játékos a szobákban?");
        Room room1 = new Room(capacity1);
        if (hasPlayer) {
            room1.addPlayer(new Student());
        }
        Room room2 = new Room(capacity2);
        room1.mergeWithRoom(room2);
    }
}
