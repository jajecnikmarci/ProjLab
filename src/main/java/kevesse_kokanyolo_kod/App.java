package kevesse_kokanyolo_kod;

import kevesse_kokanyolo_kod.skeleton.Skeleton;

public class App {
    public static Skeleton skeleton;

    public static void main(String[] args) {
        skeleton = new Skeleton();
        boolean configGood = false;
        System.out.println("args: " + args.length);
        if (args.length == 5 && args[0].equals("-f")) {
            Skeleton.setFromAndToFile(true);
            System.out.println("File usage");
            if (args[1].equals("-i")) {
                skeleton.setInputFileName(args[2]);
                if (args[3].equals("-o")) {
                    skeleton.setOutputFileName(args[4]);
                    configGood = true;
                }
            }
        } else if (args.length == 0) {
            Skeleton.setFromAndToFile(false);
            System.out.println("Console usage");
            configGood = true;
        }
        if (!configGood) {
            System.out.println("Usage for file usage: running command + [-f -i <inputfile> -o <outputfile>]");
            System.exit(1);
        }

        skeleton.menu();
    }
}   