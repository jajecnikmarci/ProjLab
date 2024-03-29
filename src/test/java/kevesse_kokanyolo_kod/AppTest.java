package kevesse_kokanyolo_kod;

import junit.framework.TestCase;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AppTest extends TestCase {
    public Skeleton skeleton;
    public void testMainTest() {
        try {
            skeleton = new Skeleton();
            skeleton.setFromAndToFile(true);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        skeleton.menu();
    }
}