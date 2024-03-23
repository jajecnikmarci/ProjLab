package player;

import effects.PoisonImmunity;
import items.*;
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
     * @param student
     */
    @Override
    public void meet(Student student) {
        student.kill();
    }

    /**
     * @param professor
     */
    @Override
    public void meet(Professor professor) {
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
        this.addPoisonImmunity(new PoisonImmunity());
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
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param transistor
     */
    @Override
    public void acceptItem(Transistor transistor) {
        return;
    }

    /**
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param slideRule
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        return;
    }

    /**
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param tvsz
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        return;
    }

    /**
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param glass
     */
    @Override
    public void acceptItem(Glass glass) {
        return;
    }

    /**
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param rug
     */
    @Override
    public void acceptItem(Rug rug) {
        return;
    }
}
