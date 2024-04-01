package kevesse_kokanyolo_kod.player;

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
    }
