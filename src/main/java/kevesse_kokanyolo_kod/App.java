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

    public static void main(String[] args) {  // TODO: proto menu usage kiírása 
        if(args.length == 0) {
            System.out.println("Nincs megadva argumentum");
            System.exit(1);
        }
        else if(args.length >= 1){
            if(args[0].equals("skeleton")) skeleton.menu();
            if(args[0].equals("proto")) {
                skeleton.mute();
                String arg[] = new String[]{null, null, null, null};
                for(int i = 0; i < args.length; i++) {
                    arg[i] = args[i];
                }
                try {
                    proto = new ProtoMenu(arg[1], arg[2], arg[3]);
                    proto.menu();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                
            }
        }
    }
}   