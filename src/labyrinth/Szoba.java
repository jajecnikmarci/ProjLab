package labyrinth;

import human.Ember;
import human.Hallgato;
import human.Oktato;

import java.util.List;

public class Szoba {
    public List<Hallgato> getStudents() {
        return null;
    }

    public List<Oktato> getTeachers() {
        return null;
    }

    public int getCapacity() {
        return 0;
    }

    public boolean isPoisonous() {
        return true;
    }

    public void setPoisonous(boolean poisonous) {
    }

    public Szoba[] split() { //returns two rooms
        return null;
    }

    public Szoba mergeWith(Szoba szoba) {
        return null;
    }

    public void update() {
        //check if any Hallgato is near an Oktato
    }

    public List<Ajto> getAjtok() {
        return null;
    }

    public void stepIn(Ember ember) {
    }

    public void stepOut(Ember ember) {
    }

    //tries to move player in direction
    public void movePlayer(Ember ember, int dirX, int dirY) {
    }
}
