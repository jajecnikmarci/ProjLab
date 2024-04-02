package kevesse_kokanyolo_kod;

import java.io.IOException;

import kevesse_kokanyolo_kod.menus.SkeletonMenu;

public class App {
    public static SkeletonMenu skeleton;

    public static void main(String[] args) {
        // TODO: Implement proto mode (disable skeleton menu)
        if (args.length == 3 && args[0].equals("-f")) {
            SkeletonMenu.setFromAndToFile(true);
            System.out.println("File usage");
            try {
                skeleton = new SkeletonMenu(args[1],args [2] );
            } catch (IOException e) {
                System.out.println("Usage for file usage: running command + [-f <inputfile> <outputfile>]");
                System.exit(1);
            }
        } else if (args.length == 0) {
            skeleton = new SkeletonMenu();
            SkeletonMenu.setFromAndToFile(false);
            System.out.println("Console usage");
        }
        skeleton.menu();
    }
}   