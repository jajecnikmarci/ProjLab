package kevesse_kokanyolo_kod.items;

import junit.framework.TestCase;
import kevesse_kokanyolo_kod.player.Student;
import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.skeleton.Skeleton;
import org.junit.Test;

public class CamembertTest extends TestCase {

    @Test
    public void use() {
        Skeleton.setVerbose(false);
        Room room = new Room(10);
        Student student = new Student(room);
        Item camembert = new Camembert();
        student.addItem(camembert);
        room.addPlayer(student);
        Skeleton.setVerbose( true);
        student.useItem(camembert);
        

    }

    @Test
    public void accept() {
    }
}