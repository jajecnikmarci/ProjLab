package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.views.IntPair;

public class DefaultLabyrinth {
    static final void create(Controller controller, LabyrinthBuilder labyrinthBuilder) {
        int[] roomLocations = { 
            840, 90, 
            1190, 90, 
            700, 220, 
            990, 220, 
            1330, 220, 
            525, 340, 
            700, 340, 
            1190, 340, 
            700, 460, 
            990, 460, 
            525, 590, 
            840, 590, 
            1190, 590 };
        boolean poisonousRooms[] = new boolean[13];
        poisonousRooms[0] = true;
        poisonousRooms[9] = true;
        poisonousRooms[11] = true;

        for (int i = 0; i < roomLocations.length / 2; i++) {
            IntPair location = new IntPair(roomLocations[i * 2], roomLocations[i * 2 + 1]);
            controller.createRoom("room" + i, 4, poisonousRooms[i], location);
        }

        final IntPair topOffset = new IntPair(0, -1);
        final IntPair rightOffset = new IntPair(1, 0);
        final IntPair bottomOffset = new IntPair(0, 1);
        final IntPair leftOffset = new IntPair(-1, 0);

        controller.createDoor("room0", "room1", true, "door0", false);
        controller.createDoor("room0", "room2", true, "door1", true);
        controller.createDoor("room0", "room3", false, "door2", true);
        controller.createDoor("room2", "room5", true, "door3", false);
        controller.createDoor("room1", "room4", true, "door4", false);
        controller.createDoor("room2", "room3", false, "door5", false);
        controller.createDoor("room7", "room1", false, "door6", false);
        controller.createDoor("room2", "room6", true, "door7", false);
        controller.createDoor("room8", "room3", false, "door8", false);
        controller.createDoor("room4", "room7", false, "door9", true);
        controller.createDoor("room5", "room8", true, "door10", true);
        controller.createDoor("room6", "room8", false, "door11", false);
        controller.createDoor("room8", "room9", true, "door12", false);
        controller.createDoor("room7", "room9", true, "door13", false);
        controller.createDoor("room10", "room8", false, "door14", false);
        controller.createDoor("room9", "room11", false, "door15", false);
        controller.createDoor("room11", "room10", false, "door16", false);
        controller.createDoor("room11", "room12", true, "door17", true);

        controller.createStudent("room1", "S1");
        controller.createCleaner("room1", "C3");
        controller.createProfessor("room3", "P3");

        controller.createItem("room1", "FFP2", "FFP2");
        controller.createItem("room1", "Camembert", "Camembert");

        controller.createItem("room1", "FakeSlideRule", "FakeSlideRule");
        controller.createItem("room12", "SlideRule", "SlideRule");

        controller.createItem("room4", "Transistor", "Transistor1");
        controller.createItem("room5", "Transistor", "Transistor2");
    }
}
