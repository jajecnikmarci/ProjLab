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
public class ProtoMenu {
    private static Printer printer;
    public ProtoMenu(String inputFileName, String outputFileName) throws IOException {
        printer = new Printer(inputFileName, outputFileName);
    }

    // minden objektum kap egy nevet: instanceName
    // I. INICIALIZÁLÁS: minden teszteset ezzel indul, ez egy olyan valós állapot, amiből a játék kiindulhat.
    // pálya létrehozása: 
    // 1. Szobák létrehozása
    //  1.1 Tárgyak hozzáadása a szobákhoz.
    // 2. Ajtók létrehozása
    // 3. Személyek létrehozása, szobához adása.
    // 4. endinit: printState(Printer) minden osztályra
    // II. CONTROL
    // Parancsok:
    // pickup <Academic>
    // drop <Academic> <Item>
    // use <Academic> <Item>
    // gotoroom <Person> <Door>
    // pairtransstors <Student> <Transistor> <Transistor>
    // split <Room>
    // merge <Room> <Room>


    // Megjegyzések: 
    // A <> közötti szavak példányneveket jelölnek.
    // Academic: egy hallgató, vagy oktató példány neve
    // A proto nyelv értelmezője hibát dob ha helytelen a parancs.
    // PersonInstance: egy hallgató, oktató, takarító példány neve
    // - new <ClassName> <instanceName> {Door, Room, Student, Professor, Cleaner, Items, FakeItems}
    // TODO: A Student, Professor, Cleaner osztályokból érdemes lenne azokat a metódusokat egy-egy interfészbe kiszervezni, amik az irányításhoz szükségesek.
}
