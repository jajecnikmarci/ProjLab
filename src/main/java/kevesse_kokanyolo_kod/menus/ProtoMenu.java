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

    // CONFIG:
    // randomness <enable|disable> - véletlenszerűség be- vagy kikapcsolása, 
    //   bármi ami véletlenszerű viselkedést kér, a ProtoMenüvel kiválasztatja az eredményt, ha a randomness ki van kapcsolva.
    // timercontrol <enable|disable> - Ha be van kapcsolva, a felhasználó átveszi az időzítők vezérlését a programtól, ezzel aktiválja a timer parancsot.   
    // load <CONFIG_FILE_PATH> - konfigurációs fájl betöltése (a proto nyelvén írt)
    // printstate <InstanceName> - A megadott objektum állapotának kiírása 
    // printall - Az összes objektum állapotának kiírása

    // I. INICIALIZÁLÁS: minden teszteset ezzel indul, ez egy olyan valós állapot, amiből a játék kiindulhat.
    // pálya létrehozása. 
    // Minden objektum kap egy nevet: instanceName
    // Ajánlott inicializálási sorrend: 
    // 1. Szobák létrehozása (Adja meg a szoba nevét: <RoomName> )
    // add room <RoomName>

    // 2. Tárgyak hozzáadása a szobákhoz. (Adjon tárgyt a szobához: <RoomName> <ItemType> <ItemName>)
    // add item <RoomName> <ItemType> <ItemName> 

    // 3. Ajtók létrehozása (Adjon hozzá ajtókat a szobához: [!]<RoomName> <RoomName> [?cursed] Tegyen felkiáltójelet az 1. szoba neve elé, ha nem átjárható abból az irányból. A cursed opcionális true/false értéket lehet megadni, default = false.) 
    // add door [!]<RoomName> <RoomName>  [?cursed]

    // 4. Személyek létrehozása, szobához adása. (Adjon hozzá személyeket a szobához: <RoomName> <PersonType> <PersonName>) 
    // add person <RoomName> <PersonType> <PersonName>

    // II. CONTROL
    // Parancsok:
    // pickup <Academic> - tárgy felvétele a szobából
    // drop <Academic> <Item> - tárgy eldobása
    // use <Academic> <Item> - tárgy használata
    // gotoroom <Person> <Room> - átmegy egy személy egyik szobából a másikba
    // shake - Tér-Idő rengést hajt végre
    // endtimer <TimerName> - Leállítja az időzítőt
    // Ctrl+C - kilépés


    // Megjegyzések: 
    // - A <> közötti szavak kötelező paramétereket jelölnek.
    // - A [] közötti szavak opcionális paramétereket jelölnek.
    // - Ha a timercontrol be van kapcsolva, az időzítők indulásukkor kiírják a nevüket a felhasználónak, és a felhasználó beírhatja az endtimer <TimerName> parancsot, hogy leállítsa az időzítőt.
    // - Academic: egy hallgató, vagy oktató példány neve
    // - Person: egy hallgató, oktató, takarító példány neve
    // - A proto nyelv értelmezője hibát dob ha helytelen a parancs.
}
