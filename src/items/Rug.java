package items;

import effects.StunEffect;
import player.Player;
import player.Professor;
import player.Student;
import room.Room;

/**
 *
 */
public class Rug extends Item {
    /**
     * A hallgató használja a Rug tárgyat és a hallgató tartózkodási szobájához hozzáadódik a rongy effektje.
     * 
     * @param room
     * @param player
     */
    @Override
    public void use(Room room, Player player) {
        System.out.println("Rug.use(Room, Player)");
        room.addEffect(new StunEffect());
        player.removeItem(this);
    }

    /**
     * @param player
     */
    @Override
    public void accept(Player player) {
        System.out.println("Rug.accept(Player)");
        player.acceptItem(this);
    }

    /**
     * @param student
     * @return
     */
    @Override
    public boolean canPickUp(Student student) {
        return true;
    }

    /**
     * @param professor
     * @return
     */
    @Override
    public boolean canPickUp(Professor professor) {
        return false;
    }
}
