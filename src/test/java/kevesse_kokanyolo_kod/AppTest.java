package kevesse_kokanyolo_kod;

import junit.framework.TestCase;
import kevesse_kokanyolo_kod.skeleton.SkeletonTest;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AppTest extends TestCase {
    public SkeletonTest skeletonTest;
    public void testMainTest() {
        try {
            skeletonTest = new SkeletonTest();
            skeletonTest.setFromAndToFile(true);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String next=null;
        skeletonTest.menu();
    }
}