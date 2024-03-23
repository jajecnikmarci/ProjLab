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
 * Szkeleton osztály. Tárolja a teszteseteket. 
 * Teszt hozzáadaása:
 * (1) Teszt függvény megírása az ostályon belül (lásd test 1)
 * (2) konstruktorban hozzáadás a tests listához.  
 */
public class Skeleton {
    List<Test> tests;
  
    public Skeleton() {
        tests = new ArrayList<>();
        /**
         * Itt történik a tesztek hozzáadása (2)
         */
        // tests.add(new Test("Test 1", this::test1));
        tests.add(new Test("pickup ffp2",this::testPickUpFFP2));
        tests.add(new Test("pickup camembert",this::testPickUpCamembert));
        tests.add(new Test("pickup glass",this::testPickUpGlass));
        tests.add(new Test("pickup Rug",this::testPickUpRug));
        tests.add(new Test("pickup sliderule",this::testPickUpSlideRule));
        tests.add(new Test("pickup transistor",this::testPickUpTransistor));
        tests.add(new Test("pickup tvsz",this::testPickUpTVSZ));
        tests.add(new Test("use rug",this::testUseRug));

    }
    private void print(String string) {
        System.out.println(string);
    }
    private void err(String string) {
        System.err.println(string);
    }
    
    private void printTests() {
        System.out.println("Válasszon tesztet: (q - kilépés)");
        for (int i = 0; i < tests.size(); i++) {
            System.out.println(i + 1 + ". " + tests.get(i).getTitle());
        }
    }
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        do  {
            printTests();
            String input; 
            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            if (input.equals("q")) {
                break;
            }
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < tests.size())
                    tests.get(index).run();
                else
                    print("Nincs ilyen teszt!");

            } catch (NumberFormatException e) {
                err("Adjon meg számot!");
            }
        } while (scanner.hasNextLine());
        
        scanner.close();
    }

    /*
     * Példa teszt függvény. (1) 
     */
    // public void test1() {
    //     print("Test 1 executed");
    // }
    
    public void testPickUpFFP2() {
        Room room = new Room();
        Student student = new Student(room);
        FFP2 item = new FFP2();
        room.addPlayer(student);
        room.addItem(item);

        student.pickUpItem();

        print("Test ffp2 pick up executed");
    }

    public void testPickUpCamembert() {
        Room r = new Room();
        Student s = new Student(r);
        Camembert i = new Camembert();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();

        print("Test camembert pick up executed");
    }
    
    public void testPickUpGlass() {
        Room r = new Room();
        Student s = new Student(r);
        Glass i = new Glass();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();

        print("Test glass pick up executed");
    }

    public void testPickUpTVSZ() {
        Room r = new Room();
        Student s = new Student(r);
        TVSZ i = new TVSZ();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();

        print("Test tvsz pick up executed");
    }

    public void testPickUpTransistor() {
        Room r = new Room();
        Student s = new Student(r);
        Transistor i = new Transistor();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();

        print("Test transistor pick up executed");
    }

    public void testPickUpRug() {
        Room r = new Room();
        Student s = new Student(r);
        Rug i = new Rug();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();

        print("Test rug pick up executed");
    }

    public void testPickUpSlideRule() {
        Room r = new Room();
        Student s = new Student(r);
        SlideRule i = new SlideRule();
        r.addPlayer(s);
        r.addItem(i);

        s.pickUpItem();

        print("Test sliderule pick up executed");
    }

    public void testUseRug() {
        Room r = new Room();
        Student s = new Student(r);
        Rug i = new Rug();
        r.addPlayer(s);
        s.addItem(i);

        i.use(r,s);

        print("Test use rug executed");
    }
    
}
