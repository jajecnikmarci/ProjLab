package kevesse_kokanyolo_kod.menus;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


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

    boolean randomness = false;
    boolean timerControl = false;
    LabyrinthBuilder labyrinthBuilder = null; //null, ha konfigurációs módban vagyunk

    List<Option> configOptions = new ArrayList<>(); //Konfigurációs parancsokat tartalmazza
    List<Option> initControlOptions = new ArrayList<>(); //Inicializálási és vezérlő parancsokat tartalmazza

    /**
     * Hozzáadja a parancsokat a 2 listához.
     */
    public void initProtoMenu() { // TODO: ez az egész lehetne static, csak viszonylag sok munka.
        configOptions.add(new Option("randomness", this::randomnessOption));
        configOptions.add(new Option("timercontrol", this::timerControlOption));
        configOptions.add(new Option("load", this::loadOption));
        configOptions.add(new Option("starttest", this::startTestOption));

        initControlOptions.add(new Option("printstate", this::printStateOption));
        initControlOptions.add(new Option("printall", this::printAllOption));
        initControlOptions.add(new Option("endtest", this::endTestOption));
        initControlOptions.add(new Option("add", t -> {
            try {
                addOption(t);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }));
        initControlOptions.add(new Option("pickup", this::pickupOption));
        initControlOptions.add(new Option("drop", this::dropOption));
        initControlOptions.add(new Option("use", this::useOption));
        initControlOptions.add(new Option("gotoroom", this::gotoroomOption));
        initControlOptions.add(new Option("shake", this::shakeOption));
    }

    private void runTest(String inputFileName, String expectedFileName) throws IOException {
        if (expectedFileName == null) expectedFileName = inputFileName + "_expected";
        expectedFileName += ".txt";

        printer = new Printer("input" + File.separatorChar + inputFileName + ".txt"); 
        String result = printer.getOutput();

        // TODO: compare result with contet of expectedFile

        // menu() is called in App.java
    }

    private void runTestMode(String inputFileName, String expectedFileName) throws IOException{
        if (inputFileName == null) {
            // TODO: go through files in input directory AND CALL RUNTEST FOR EACH
        } else {
            runTest(inputFileName, expectedFileName);
        }
    }

    private void runFile(String inputFileName, String outputFileName) throws IOException { 
        if (outputFileName == null) outputFileName = inputFileName + "_result";
        outputFileName += ".txt";

        printer = new Printer("input" + File.separatorChar + inputFileName + ".txt", 
                              "output" + File.separatorChar + outputFileName); 

        // menu() is called in App.java
    }

    private void runFileMode(String inputFileName, String outputFileName) throws IOException {
        if (inputFileName == null) {
            // TODO: go through files in input directory AND CALL RUNFile FOR EACH
        } else {
            runFile(inputFileName, outputFileName);
        }
    }

    public ProtoMenu(String mode, String inputFileName, String outputFileName) throws IOException {
        initProtoMenu();
        if(mode == null) return;
        if(mode.equals("-f")){
            runFileMode(inputFileName, outputFileName);
            return;
        }
        if(mode.equals("test")){
            runTestMode(inputFileName, inputFileName);
            return;
        }
        System.err.println("Hibás argumentum.");
        System.exit(1);

    }
    
    public ProtoMenu() { 
        initProtoMenu();
        printer = new Printer();
    }

    public void menu() {
        do {
            String input;
            try {
                input = Printer.scanner.nextLine();
                execute(input);

            } catch (NoSuchElementException e) {
                break;
            }

        } while (Printer.scanner.hasNextLine());
        try {
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    protected void execute(String line){

        String[] tokens = line.split(" ");

        if(tokens.length == 0){
            return;
        }
        boolean found = false;

        if(labyrinthBuilder==null){
            for (Option option : configOptions) {
                if (option.getName().equals(tokens[0])) {
                    option.run(tokens);
                    found = true;
                    break;
                }
            }
        } else {
            for (Option option : initControlOptions) {
                if (option.getName().equals(tokens[0])) {
                    option.run(tokens);
                    found = true;
                    break;
                }
            }
        }
        if(!found)
            printer.printError("Ismeretlen parancs.");
    }

    /**
     * A véletlenszerűség be- vagy kikapcsolása.
     * @param tokens a parancs szavai
     */
    private void randomnessOption(String[] tokens) {
        if (tokens.length < 2) {
            printer.printError("Hiányzó paraméter a 'randomness' parancshoz.");
            return;
        }

        if (tokens[1].equals("enable")) {
            randomness = true;
        } else if (tokens[1].equals("disable")) {
            randomness = false;
        } else {
            printer.printError("Érvénytelen paraméter a 'randomness' parancshoz.");
        }
    }


    /**
     * Az időzítők vezérlésének be- vagy kikapcsolása.
     * @param tokens a parancs szavai
     */
    private void timerControlOption(String[] tokens) {
        if (tokens.length < 2) {
            printer.printError("Hiányzó paraméter a 'timercontrol' parancshoz.");
            return;
        }

        if (tokens[1].equals("enable")) {
            timerControl = true;
        } else if (tokens[1].equals("disable")) {
            timerControl = false;
        } else {
            printer.printError("Érvénytelen paraméter a 'timercontrol' parancshoz.");
        }
    }

    /**
     * A konfigurációs fájl betöltése.
     * @param tokens a parancs szavai
     */
    private void loadOption(String[] tokens) {
        if (tokens.length < 2) {
            printer.printError("Hiányzó paraméter a 'load' parancshoz.");
            return;
        }
        //A konfiguráció betöltése
    }

    /**
     * A tesztelés kezdete.
     * @param tokens a parancs szavai
     */
    private void startTestOption(String[] tokens) {
        labyrinthBuilder = new LabyrinthBuilder();
    }

    /**
     * Az állapot kiírása.
     * @param tokens
     */
    private void printStateOption(String[] tokens) {
        if (tokens.length < 2) {
            printer.printError("Hiányzó paraméter a 'printstate' parancshoz.");
            return;
        }
        
        labyrinthBuilder.printState(tokens[1], printer);
    }

    /**
     * 
     * Az összes objektum állapotának kiírása.
     * @param tokens
     */
    private void printAllOption(String[] tokens) {
        
        labyrinthBuilder.printAll(printer);
    }

    /**
     * A tesztelés vége.
     * @param tokens a parancs szavai
     */
    private void endTestOption(String[] tokens) {
        labyrinthBuilder = null;
    }

    /**
     * Új elem hozzáadása a pályához.
     * @param tokens a parancs szavai
     */
    private void addOption(String[] tokens) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

        if (tokens.length < 4) {
            printer.printError("Hiányzó paraméter az 'add' parancshoz.");
            return;
        }
        switch (tokens[1]) {
            case "room":
                labyrinthBuilder.addRoom(tokens[2], Integer.parseInt(tokens[3]), printer);
                break;
            case "item":
                if (tokens.length < 5) {
                    printer.printError("Hiányzó paraméter az 'item' parancshoz.");
                    return;
                }
                labyrinthBuilder.addItem(tokens[2], tokens[3], tokens[4], printer);
                break;
            case "door":
                if (tokens.length < 4) {
                    printer.printError("Hiányzó paraméter a 'door' parancshoz.");
                    return;
                }
                boolean passable = true;
                boolean cursed = false;
                if(tokens[2].charAt(0) == '!'){
                    passable = false;
                    tokens[2] = tokens[2].substring(1);
                }
                if(tokens.length == 6 && tokens[5].equals("true")){
                    cursed = true;
                }
                labyrinthBuilder.addDoor(tokens[2], tokens[3], passable, tokens[4], cursed);
                break;
            case "person":
                if (tokens.length < 5) {
                    printer.printError("Hiányzó paraméter a 'person' parancshoz.");
                    return;
                }
                
                labyrinthBuilder.addPerson(tokens[2], tokens[3], tokens[4], printer);
                break;
            default:
                printer.printError("Ismeretlen 'add' parancs.");
        }
    }

    private void pickupOption(String[] tokens) {
        if (tokens.length < 2) {

            printer.printError("Hiányzó paraméter a 'pickup' parancshoz.");
            return;
        }
        labyrinthBuilder.pickup(tokens[1]);
    }

    private void dropOption(String[] tokens) {
        if (tokens.length < 3) {
            printer.printError("Hiányzó paraméter a 'drop' parancshoz.");
            return;
        }
        labyrinthBuilder.drop(tokens[1], tokens[2]);
    }

    private void useOption(String[] tokens) {
        if (tokens.length < 3) {
            printer.printError("Hiányzó paraméter a 'use' parancshoz.");
            return;
        }
        labyrinthBuilder.use(tokens[1], tokens[2]);
    }

    private void gotoroomOption(String[] tokens) {
        if (tokens.length < 3) {
            printer.printError("Hiányzó paraméter a 'gotoroom' parancshoz.");
            return;
        }
        labyrinthBuilder.gotoroom(tokens[1], tokens[2]);
    }

    private void shakeOption(String[] tokens) {
        labyrinthBuilder.shake(randomness);
    }

    class Option {
        private final String name;
        private final Consumer<String[]> option;

        public Option(String name, Consumer<String[]> option) {
            this.name = name;
            this.option = option;
        }

        public String getName() {
            return name;
        }

        public void run(String[] argument) {
            option.accept(argument);
        }
    }
}
