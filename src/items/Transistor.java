package items;

import player.Player;
import player.Professor;
import player.Student;
import room.Room;

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
        System.out.println("Transistor.setRoom(Room)");
        this.room = room;
    }

    /**
     * A tranzisztor párának megadása
     * @param transistor
     */
    public void setPair(Transistor transistor) {
        System.out.println("Transistor.setPair(Transistor)");
        this.pair = transistor;
    }

    /**
     * Beállítja atranzisztor tulajdonosát a megadott hallgatóra.
     * @param student a megadott halglató
     */
    public void setOwner(Student student) {
        System.out.println("Transistor.setOwner(Student)");
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
        System.out.println("Transistor.use(Room, Player)");

        if(this.pair != null && this.pair.room == null) {

            this.setRoom(room);
            player.removeItem(this);
        
        }else if(this.pair != null){

            if(this.pair.room.canPlayerGoIn()) {
                room.removePlayer(player);

                this.pair.room.addPlayer(player);
                this.pair.room.onEnter(owner);

                this.setRoom(room);
                this.pair.room= null;

                player.removeItem(this);
                player.addItem(this.pair);
            }

        }
    }

    /**
     * Meghívja a paraméterként kapott playerre a tárgyhoz tartozó acceptItem függvényt.
     * @param player a játékos aki próbálja felvenni a tárgyat
     */
    @Override
    public void accept(Player player) {
        System.out.println("Transistor.accept(Player)");
        player.acceptItem(this);
    }
}
