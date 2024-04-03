package kevesse_kokanyolo_kod.people;

import kevesse_kokanyolo_kod.items.*;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.room.Room;


/**
 * A professzort reprezentáló osztály
 */
public class Professor extends AcademicPerson {

    public Professor(Room room) {
        super(room);
    }


    /**
     * Professzor találkozik egy diákkal, akit megöl.
     *
     * @param student a diák, aki meg fog halni
     */
    @Override
    public void meet(Student student) {
        SkeletonMenu.startCall("Professor.meet(Student)");
        student.kill();
        SkeletonMenu.endCall();
    }

    /**
     * Professzor találkozik egy professzorral, akivel találkozik
     *
     * @param professor a professzor, akivel találkozik
     */
    @Override
    public void meet(Professor professor, Room room) {
        SkeletonMenu.startCall("Professor.meet(Professor, Room)");
        leaveRoom();
        SkeletonMenu.endCall("A professzor elhagyta a szobát.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     *
     * @param transistor a felvevendő tárgy
     */
    @Override
    public void acceptItem(Transistor transistor) {
        SkeletonMenu.startCall("Professor.acceptItem(Transistor)");
        SkeletonMenu.endCall("A professzor nem tudja felvenni a tranzisztort.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     *
     * @param slideRule a felvevendő tárgy
     */
    @Override
    public void acceptItem(SlideRule slideRule) {
        SkeletonMenu.startCall("Professor.acceptItem(SlideRule)");
        SkeletonMenu.endCall("A professzor nem tudja felvenni a logarlécet.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     *
     * @param tvsz a felvevendő tárgy
     */
    @Override
    public void acceptItem(TVSZ tvsz) {
        SkeletonMenu.startCall("Professor.acceptItem(TVSZ)");
        SkeletonMenu.endCall("A professzor nem tudja felvenni a TVSZ-t.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     *
     * @param glass a felvevendő tárgy
     */
    @Override
    public void acceptItem(Glass glass) {
        SkeletonMenu.startCall("Professor.acceptItem(Glass)");
        SkeletonMenu.endCall("A professzor nem tudja felvenni a Szent Söröspoharat.");
    }

    /**
     * A paraméterként kapott tárgyat hozzáadja a Player tárgyaihoz, illetve ha kell akkor Effectet ad a játékoshoz,
     * majd kitörli a tárgyat a jelenlegi szoba tárgylistájából.
     * Ez a visitor miatt van, ilyen tárgyat nem vehet fel okató, ezért nem csinál semmit.
     *
     * @param rug a felvevendő tárgy
     */
    @Override
    public void acceptItem(Rug rug) {
        SkeletonMenu.startCall("Professor.acceptItem(Rug)");
        SkeletonMenu.endCall("A professzor nem tudja felvenni a Nedves Táblatörlő Rongyot.");
    }

    
    @Override
    public void callOnEnter(Room room) {
        room.onEnter(this);
    }
}
