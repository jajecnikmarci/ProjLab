package player;

import java.util.Optional;

import effects.PoisonImmunity;
import items.*;
import room.Door;
import room.Room;

/**
 *
 */
public class Professor extends Player {
    
    public Professor(Room r){
        super(r);
        location=r;
    }
    
    
    /**
     *
     * @param student
     */
    @Override
    public void meet(Student student) {
        System.out.println("Professor.meet(Student)");
        student.kill();
    }

    /**
     * @param professor
     */
    @Override
    public void meet(Professor professor, Room room) {
        System.out.println("Professor.meet(Professor, Room)");
        // TODO: Leave room
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param ffp2 a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(FFP2 ffp2) {
        System.out.println("Professor.acceptItem(FFP2)");
        this.addItem(ffp2);
        this.addPoisonImmunity(new PoisonImmunity(ffp2,ffp2.getImmunityLength()));
        location.removeItem(ffp2);
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * @param camembert a hozzáadandó tárgy
     */
    @Override
    public void acceptItem(Camembert camembert) {
        System.out.println("Professor.acceptItem(Camembert)");
        this.addItem(camembert);
        location.removeItem(camembert);
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param transistor
     */
    @Override
    public void acceptItem(Transistor transistor) {
        return;
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param slideRule
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        return;
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param tvsz
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        return;
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param glass
     */
    @Override
    public void acceptItem(Glass glass) {
        return;
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param rug
     */
    @Override
    public void acceptItem(Rug rug) {
        return;
    }

    /**
     * Belép a megadott szobába, ha a szoba befogadja a játékost.
     * @param room a szoba, amibe a játékos be kíván lépni
     */
    @Override
    public void goToRoom(Room room) {
        Optional<Door> door = location.getDoors()
                .stream()
                .filter(d -> d.isBetween(location, room))
                .findFirst();

        if(door.isPresent()) {
            door.get().goThrough(this);
            room.onEnter(this);
        }
    }
}
