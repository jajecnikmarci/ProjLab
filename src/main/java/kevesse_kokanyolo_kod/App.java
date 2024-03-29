package kevesse_kokanyolo_kod;
import kevesse_kokanyolo_kod.skeleton.Skeleton;

import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    public static Skeleton skeleton;
    public static void main(String[] args) {
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