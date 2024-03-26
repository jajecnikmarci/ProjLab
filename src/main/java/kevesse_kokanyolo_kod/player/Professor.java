package kevesse_kokanyolo_kod.player;

import java.util.Optional;

import kevesse_kokanyolo_kod.effects.PoisonImmunity;
import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.skeleton.Skeleton;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

/**
 * A professzort reprezentáló osztály
 */
public class Professor extends Player {
    
    public Professor(Room r){
        super(r);
        location=r;
    }
    
    
    /**
     * Professzor találkozik egy diákkal, akit megöl.
     * @param student a diák, aki meg fog halni
     */
    @Override
    public void meet(Student student) {
        Skeleton.startCall("Professor.meet(Student)");
        student.kill();
        Skeleton.endCall();
    }

    /**
     * Professzor találkozik egy professzorral, akivel találkozik
     * @param professor a professzor, akivel találkozik
     */
    @Override
    public void meet(Professor professor, Room room) {
        Skeleton.startCall("Professor.meet(Professor, Room)");
        // TODO: Leave room
        Skeleton.endCall("A professzor elhagyta a szobát.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param transistor a felvevendő tárgy
     */
    @Override
    public void acceptItem(Transistor transistor) {
        Skeleton.startCall("Professor.acceptItem(Transistor)");
        Skeleton.endCall("A professzor nem tudja felvenni a tranzisztort.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param slideRule a felvevendő tárgy
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        Skeleton.startCall("Professor.acceptItem(SlideRule)");
        Skeleton.endCall("A professzor nem tudja felvenni a logarlécet.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param tvsz a felvevendő tárgy
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        Skeleton.startCall("Professor.acceptItem(TVSZ)");
        Skeleton.endCall("A professzor nem tudja felvenni a TVSZ-t.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param glass a felvevendő tárgy
     */
    @Override
    public void acceptItem(Glass glass) {
        Skeleton.startCall("Professor.acceptItem(Glass)");
        Skeleton.endCall("A professzor nem tudja felvenni a Szent Söröspoharat.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz, 
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     * @param rug a felvevendő tárgy
     */
    @Override
    public void acceptItem(Rug rug) {
        Skeleton.startCall("Professor.acceptItem(Rug)");
        Skeleton.endCall("A professzor nem tudja felvenni a Nedves Táblatörlő Rongyot.");
    }

    /**
     * Belép a megadott szobába, ha a szoba befogadja a játékost.
     * @param room a szoba, amibe a játékos be kíván lépni
     */
    @Override
    public void goToRoom(Room room) {
        Skeleton.startCall("Professor.goToRoom(Room)");
        Optional<Door> door = location.getDoors()
                .stream()
                .filter(d -> d.isBetween(location, room))
                .findFirst();

        if(door.isPresent()) {
            door.get().goThrough(this);
            room.onEnter(this);
            Skeleton.endCall("A professzor belépett a szobába."); 
            return;
        }
        Skeleton.endCall("A professzor nem lépett be a szobába.");
    }
}
