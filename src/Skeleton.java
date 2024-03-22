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

        tests.add(new Test("use Camembert", this::useCamembert));
        tests.add(new Test("pair Transistors", this::pairTransistors));
        tests.add(new Test("drop Transistor", this::dropTransistor));
        tests.add(new Test("teleport with Transistors", this::teleportWithTransistors));
        
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

    public void useCamembert(){

        Student student = new Student();
        Item camembert = new Camembert();
        student.addItem(camembert);
        Room room = new Room(10);
        room.addPlayer(student);

        student.useItem(camembert);
    }

    public void pairTransistors(){

        Student student = new Student();
        Item transistor1 = new Transistor();
        Item transistor2 = new Transistor();

        student.addItem(transistor1);
        student.addItem(transistor2);

        student.useItem(transistor1);
    }

    public void dropTransistor(){

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

    public void teleportWithTransistors(){

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

    
}
