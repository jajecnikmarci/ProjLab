package kevesse_kokanyolo_kod;

import java.io.IOException;

import kevesse_kokanyolo_kod.menus.ProtoMenu;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;

public class App {
    public static SkeletonMenu skeleton;
    static {
        skeleton = new SkeletonMenu();
    }
    
    public static ProtoMenu proto;
    static {
        proto = new ProtoMenu();
    }

    public static void main(String[] args) {
        
        if(args.length == 0){
            System.out.println("Nincs megadva argumentum");
            System.exit(1);
        }
        else if(args.length == 1){
            if(args[0].equals("skeleton")) skeleton.menu();
            if(args[0].equals("proto")) proto.menu();
        }
        else if(args.length == 2){
            if(args[1].equals("-f") || args[1].equals("test")){
                try {
                    proto = new ProtoMenu(args[1], null, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Hibás argumentum");
                System.exit(1);
            }
        }
        else if(args.length == 3){
            if(args[1].equals("-f") || args[1].equals("test")){
                try {
                    proto = new ProtoMenu(args[1], args[2], null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Hibás argumentum");
                System.exit(1);
            }
        }
        else if(args.length == 4){
            if(args[1].equals("-f") || args[1].equals("test")){
                try {
                    proto = new ProtoMenu(args[1], args[2], args[3]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Hibás argumentum");
                System.exit(1);
            }
        }
        


        if (args.length == 4 && args[0].equals("-f")) {
            
            System.out.println("File usage");
            try {
                skeleton = new SkeletonMenu(args[1],args [2] );
            } catch (IOException e) {
                System.out.println("Usage for file usage: running command + [-f <inputfile> <outputfile>]");
                System.exit(1);
            }
        } else if (args.length == 1) {
            skeleton = new SkeletonMenu();
            
            System.out.println("Console usage");
        }
        proto.menu();


    }
}   