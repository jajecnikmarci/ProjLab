package kevesse_kokanyolo_kod.people;

import java.util.Optional;

import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;

/**
 * A személyeket reprezentáló absztrakt osztály.
 */
public abstract class Person {
    Person(Room room) {
        location = room;
    }
    /**
     * A szoba amelyben a player jelenleg tartózkodik.
     */
    protected Room location;

    /**
     * Visszaadja, hogy melyik szobában van a játékos.
     */
    public Room getLocation() {
        return this.location;
    }

    /**
     * Beállítja, hogy a játékos melyik szobában lesz (szobaváltásnál).
     *
     * @param room
     */
    public void setLocation(Room room) {
        this.location = room;
    }

    /**
     * A játékos találkozik egy hallgatóval.
     *
     * @param student a hallgató, akivel találkozik
     */
    public abstract void meet(Student student);

    /**
     * Professzorral találkozik a játékos
     *
     * @param professor a professzor, akivel találkozik
     */
    public abstract void meet(Professor professor);

    /**
     * Takarítóval találkozik a játékos
     *
     * @param cleaner a takarító, akivel találkozik
     */
    public abstract void meet(Cleaner cleaner);

    /**
     * Megmérgezi a játékost.
     */
    public abstract void poison();

    /**
     * A játékos megpróbálja elhagyni a szobát, addig próbál
     * a szoba ajtajain kimenni, amíg sikerrel nem jár, vagy
     * megpróbált az összes ajtón átmenni.
     */
    public void leaveRoom() {
        SkeletonMenu.startCall("Person.leaveRoom()");
        for(Door door: location.getDoors()) if(door.goThrough(this)) break;
        SkeletonMenu.endCall();
    }
    
    /**
     * Meghívja a szoba onEnter metódusát, a megfelelő paraméterrel. 
     * Azért szükséges felülírni, mert az onEntert a megfelelő típusú paraméterrel kell meghívni. (Professor, Student, Cleaner)
     */
    protected abstract void callOnEnter(Room room);

    /**
     * Belép a szobába, ha tud és közli a szobával, hogy belépett a játékos.
     * 
     * Megnézi, hogy a szobának van-e olyan ajtaja, amelyiken át tud menni a megadott szobába. (Tehát a két szoba között van és látható.)
     * Ha van ilyen ajtó, megpróbál átmenni rajta, 
     * ha sikerül közli a szobával, hogy belépett a megfelelő típusú játékos (callOnEnter felülírt verzióját meghívja)
     * @param room a szoba, amibe a játékos belépni kíván
     */
    public void goToRoom(Room room) {
        SkeletonMenu.startCall("Person.goToRoom(Room)");
        Optional<Door> door = location.getDoors()
                .stream()
                .filter(d -> d.isBetween(location, room))
                .findFirst();

        if (door.isPresent() && door.get().goThrough(this)) {
            callOnEnter(room);
            SkeletonMenu.endCall("A személy átment a szobába.");
            return;
        }
        SkeletonMenu.endCall("A személy nem ment át a szobába.");
    }
    public abstract void printState(Printer printer, LabyrinthBuilder builder);
}
