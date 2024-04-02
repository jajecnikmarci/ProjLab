package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;
import kevesse_kokanyolo_kod.player.Student;
import kevesse_kokanyolo_kod.room.Room;

/**
 * Tranzisztor tárgyat reprezentáló osztály
 */
public class Transistor extends Item {
    /**
     * A tranzisztor párja mellyel összekapcsolásra kerül
     */
    private Transistor pair;

    /**
     * A szoba ahol a tranzisztor található eldobás után
     */
    private Room room;

    /**
     * A tranzisztor tulajdonosa
     */
    private Student owner;

    /**
     * A tranzisztor párának megadása
     *
     * @param transistor a tranzisztor párja
     */
    public void setPair(Transistor transistor) {
        SkeletonMenu.startCall("Transistor.setPair(Transistor)");
        this.pair = transistor;
        SkeletonMenu.endCall();
    }

    /**
     * Beállítja atranzisztor tulajdonosát a megadott hallgatóra.
     *
     * @param student a megadott halglató
     */
    public void setOwner(Student student) {
        this.owner = student;
    }

    /**
     * A transzisztor használatánál, ha a játékosnál 2 párosított tranzisztor van,
     * akkor a tranzisztort a szobához kapcsolja és eltávolítja a játékos tárgyai közül.
     * ha a játékosnál 1 párosított tranzisztor van, akkor megpróbálja a teleportálást
     * ha ez lehetséges, akkor a játékost a másik szobába teleportálja és felcseréli a tranzisztorokat
     * (eredeti eltávolítása, szoba beállítása, másik tranzisztor hozzáadása)
     * különben nem történik semmi
     *
     * @param room   a szoba, ahol a tranzisztor használva lett
     * @param player a játékos aki használja a tranzisztort
     */
    @Override
    public void use(Room room, Player player) {
        SkeletonMenu.startCall("Transistor.use(Room, Player)");
        if (this.pair != null && this.pair.room == null) {

            this.setRoom(room);
            player.removeItem(this);
            SkeletonMenu.endCall("A tranzisztor a szobához lett hozzáadva.");

        } else if (this.pair != null && this.pair.room.canPlayerEnter()) {
            room.removePlayer(player);

            this.pair.room.addPlayer(player);
            this.pair.room.onEnter(owner);

            this.setRoom(room);
            this.pair.room.removeItem(this.pair); //TODO: ez nem biztos hogy így kéne
            this.pair.room = null;

            player.removeItem(this);
            player.addItem(this.pair);
            SkeletonMenu.endCall("A játékos át lett teleportálva a másik szobába.");
        } else SkeletonMenu.endCall("A játékos nem lett elteleportálva a másik szobába.");
    }

    /**
     * Beállítja a tranzisztor szobáját ahol eldobták
     *
     * @param room a szoba ahol a tranzisztor található
     */
    public void setRoom(Room room) {
        SkeletonMenu.startCall("Transistor.setRoom(Room)");
        this.room = room;
        SkeletonMenu.endCall();
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     *
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("Transistor.accept(Player)");
        player.acceptItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("Transistor");
        printer.printField("pair", this.pair);
        printer.printField("room", this.room);
        printer.printField("owner", this.owner);
        printer.endPrintObject();
    }
}
