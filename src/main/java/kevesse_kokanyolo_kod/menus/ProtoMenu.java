package kevesse_kokanyolo_kod.menus;


import java.io.IOException;

/**
 * A prototípus menü osztálya.
 * A tesztelő nyelv értelmezését végzi.
 * 
 * Minden osztályunknak meg kell valósítania a printState(Printer) metódust, 
 * és ezzel ki kell iratnia a belső állapotát. Fontos, hogy látszódjon, ami változhatott.
 * Formátum: 
 * <Osztálynév>: 
 *  <változó1>: <érték1> 
 *  <változó2>: 
 *  ... 
 * 
 */
public class ProtoMenu extends Printer {
    public ProtoMenu() {
        super();
    }
    public ProtoMenu(String inputFileName, String outputFileName) throws IOException {
        super(inputFileName, outputFileName);
    }

    public void printState() {
        println("ProtoMenu:");
        printWithIndents(" ");
        println("Nincs belső állapot.");
    }
}
