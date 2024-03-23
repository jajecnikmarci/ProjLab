import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

        tests.add(new Test("Split room", this::split));
        tests.add(new Test("Merge rooms", this::merge));
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

    private boolean getBoolean(String msg) throws NoSuchElementException {
        print(msg + " (y/n)");;
        String str = scanner.nextLine();
        if(str.toLowerCase().equals("y")) return true;
        if(str.toLowerCase().equals("n")) return false;
        return getBoolean(msg);
    }

    private void printTests() {
        System.out.println("Válasszon tesztet: (q - kilépés)");
        for (int i = 0; i < tests.size(); i++) {
            System.out.println(i + 1 + ". " + tests.get(i).getTitle());
        }
        System.out.println();
    }
    Scanner scanner;
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

    private void split() {
        int capacity = getInt("Mekkora szobát legyen a szoba kapacitása");
        
        boolean hasPlayer = getBoolean("Legyen játékos a szobában?");
        Room room = new Room(capacity);
        if (hasPlayer) {
            room.addPlayer(new Student());
        }
        room.split();
    }

    private void merge() {
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
