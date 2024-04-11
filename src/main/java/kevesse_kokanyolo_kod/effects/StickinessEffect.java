package kevesse_kokanyolo_kod.effects;


import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * A ragadós hatású tárgyakat reprezentáló osztály.
 * A hatás 5 látogatás után érvényesül.
 */
public class StickinessEffect extends RoomEffect{
    /**
     * A hatás érvényesüléséig hátralévő látogatások számát tárolja.
     */
    private int visitCount;

    /**
     * Létrehozza a StickinessEffect-et, az ősosztály konstruktorával.
     */
    public StickinessEffect() {
        super(null, 0, null);
    }

    /**
     * Amikor egy játékos belép a szobába, eggyel kevesebb játékosnak
     * kell a szobába lépnie, hogy a szoba ragacsossá váljon.
     * @param academicPerson a játékos, akit érint a szoba hatása
     */
    @Override
    public void affect(AcademicPerson academicPerson) {
        visitCount--;
    }

    /**
     * Visszaadja, hogy ragacsosság megakadályozza-e a tárgy felvételét.
     * @return true, ha ragacsosság megakadályozza a tárgy felvételét, egyébként false
     */
    public boolean isSticky() {
        return visitCount <= 0;
    }

    /**
     * Takaításkor a ragacsosságig hátralévő látogatások számát visszaállítja alapértékre, ami 5
     */
    public void clean() {
        visitCount = 5;
    }

    /**
     * Hibát dob, mivel a ragacsosság hatás nem aktiválandó, ezzel jelzi a programozónak a hibát.
     */
    @Override
    public void activate() {
        throw new UnsupportedOperationException("StickinessEffect Should not be activated.");    
    }
    
    @Override
    public void printState(Printer printer, LabyrinthBuilder builder) {
        printer.startPrintObject("StickinessEffect");
        printer.printField("active", this.active);  
        printer.printField("visitCount", this.visitCount);
        printer.printField("givenBy", builder.getInstanceName(this.givenBy));
        printer.printField("duration", this.duration);        
        printer.endPrintObject();      
    }
}
