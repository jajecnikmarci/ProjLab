package kevesse_kokanyolo_kod;

import kevesse_kokanyolo_kod.skeleton.Skeleton;

import java.io.IOException;

public class App {
    public static Skeleton skeleton;

    public static void main(String[] args) {

        if (args.length == 3 && args[0].equals("-f")) {
            Skeleton.setFromAndToFile(true);
            System.out.println("File usage");
            try {
                skeleton = new Skeleton(args[1],args [2] );
            } catch (IOException e) {
                System.out.println("Usage for file usage: running command + [-f <inputfile> <outputfile>]");
                System.exit(1);
            }
        } else if (args.length == 0) {
            Skeleton.setFromAndToFile(false);
            System.out.println("Console usage");
        }
        skeleton.menu();
    }
}   