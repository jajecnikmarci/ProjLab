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
     * A transzisztor használata
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        System.out.println("Transistor.use(Room, Player)");

        //A másik tranzisztor megtalálása
        //Itt egyenlőre gondban állok, mert megkéne kapni a másik tranzisztort
        Transistor transistor2=null;

        //1 eset: A játékosnál 2 párosítatlan tranzisztor van: párosítás
        if(this.pair == null && transistor2!=null) {
            this.setPair(transistor2);
            transistor2.setPair(this);
        }

        //2 eset: A játékosnál 2 párosított tranzisztor van: az egyik tranzisztor a szobhoz kapcsolóik
        else if(this.pair != null && transistor2!=null) {

            this.setRoom(room);
            player.removeItem(this);
        
        //3 eset: A játékosnál 1 tranzisztor van: megtörténik a teleportálás
        }else if(this.pair != null && transistor2==null){
            
            //felhasználó megkérdezése, hogy van-e hely room2-ben, ezt majd még megcsinálom


            room.removePlayer(player); //Játékos eltávolítása a szobából

            this.pair.room.addPlayer(player); //Játékos hozzáadása a másik szobához

            //A tranzisztorok szobájának beállítása
            this.setRoom(room);
            this.pair.room= null;

            //Egyik transzisztor eltávolítása, másik hozzáadása
            player.removeItem(this);
            player.addItem(this.pair);
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
