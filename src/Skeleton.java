import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import room.Room;



/**
 * Szkeleton osztály. Tárolja a teszteseteket. 
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
    // private void test1() {
    //     print("Test 1 executed");
    // }


    private void split() {
        Room room = new Room();
        print("room.split()");
        
        room.split();

    }  
    
}
