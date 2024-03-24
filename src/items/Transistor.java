package items;

import player.Player;
import player.Student;
import room.Room;
import skeleton.Skeleton;

/**
 *
 */
public class Transistor extends Item {
    /**
     *
     */
    private Transistor pair;

    /**
     *
     */
    private Room room;

    private Student owner;
    /**
     * @param room
     */
    public void setRoom(Room room) {
        Skeleton.startCall("Transistor.setRoom(Room)");
        this.room = room;
        Skeleton.endCall();
    }

    /**
     * A tranzisztor párának megadása
     * @param transistor
     */
    public void setPair(Transistor transistor) {
        Skeleton.startCall("Transistor.setPair(Transistor)");
        this.pair = transistor;
        Skeleton.endCall();
    }

    /**
     * Beállítja atranzisztor tulajdonosát a megadott hallgatóra.
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
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        Skeleton.startCall("Transistor.use(Room, Player)");
        if(this.pair != null && this.pair.room == null) {

            this.setRoom(room);
            player.removeItem(this);
            Skeleton.endCall("A tranzisztor a szobához lett hozzáadva.");
        
        }else if(this.pair != null && this.pair.room.canPlayerEnter()) {
                room.removePlayer(player);

                this.pair.room.addPlayer(player);
                this.pair.room.onEnter(owner);

                this.setRoom(room);
                this.pair.room= null;

                player.removeItem(this);
                player.addItem(this.pair);
                Skeleton.endCall("A játékos át lett teleportálva a másik szobába.");
        } else Skeleton.endCall("A játékos nem lett elteleportálva a másik szobába.");
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        Skeleton.startCall("Transistor.accept(Player)");
        player.acceptItem(this);
        Skeleton.endCall();
    }
}
