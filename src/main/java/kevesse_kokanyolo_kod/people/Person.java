package kevesse_kokanyolo_kod.people;

import java.util.Optional;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.room.Room;

public abstract class Person {
    Person(Room r) {
        location = r;
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
     * Professzorral való találkozás esetén a játékos
     *
     * @param professor a professzor, akivel találkozik
     * @param room      a szoba, ahol a találkozás történik
     */
    public abstract void meet(Professor professor, Room room);

    public abstract void meet(Cleaner cleaner, Room room);

    public abstract void poison();

    public void leaveRoom() {
        //TODO
    }
    
    // Meghívja a szoba onEnter metódusát, a megfelelő paraméterrel. 
    // Azért szükséges felülítni, mert az onEntert a megfelelő típusú paraméterrel kell meghívni. (Professor, Student, Cleaner)
    protected abstract void callOnEnter(Room room);

    /**
     * Belép a szobába, ha tud és közli a szobával, hogy belépett a játékos.
     * @param room a szoba, amibe a játékos belép
     */
    public void goToRoom(Room room) {
        SkeletonMenu.startCall("Person.goToRoom(Room)");
        Optional<Door> door = location.getDoors()
                .stream()
                .filter(d -> d.isBetween(location, room))
                .findFirst();

        if (door.isPresent()) {
            door.get().goThrough(this);
            callOnEnter(room);
            SkeletonMenu.endCall("A személy átment a szobába.");
            return;
        }
        SkeletonMenu.endCall("A személy nem ment át a szobába.");
    }
}
