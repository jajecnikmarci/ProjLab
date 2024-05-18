package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.menus.LabyrinthBuilder;
import kevesse_kokanyolo_kod.views.IntPair;

public class DefaultLabyrinth {
    static final void create(Controller controller, LabyrinthBuilder labyrinthBuilder) {
        int[] roomLocations = { 
            840, 90,    // 0
            1190, 90,   // 1
            700, 220,   // 2
            990, 220,   // 3
            1330, 220,  // 4
            525, 340,   // 5
            700, 340,   // 6
            1190, 340,  // 7
            700, 460,   // 8
            990, 460,   // 9
            525, 590,   // 10
            840, 590,   // 11
            1190, 590   // 12
        };
        boolean poisonousRooms[] = new boolean[13];
        poisonousRooms[0] = true;
        poisonousRooms[9] = true;
        poisonousRooms[11] = true;

        for (int i = 0; i < roomLocations.length / 2; i++) {
            IntPair location = new IntPair(roomLocations[i * 2], roomLocations[i * 2 + 1]);
            controller.createRoom("room" + i, 4, poisonousRooms[i], location);
        }

        // Offset from center
        final IntPair topOffset = new IntPair(0, -1);
        final IntPair rightOffset = new IntPair(1, 0);
        final IntPair bottomOffset = new IntPair(0, 1);
        final IntPair leftOffset = new IntPair(-1, 0);

        controller.createDoor("room0", "room1", true, "door0", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door0", rightOffset, leftOffset);
        
        controller.createDoor("room0", "room2", true, "door1", true);
        // labyrinthBuilder.setDoorEndpointOffsets("door1", leftOffset, topOffset);
        
        controller.createDoor("room0", "room3", false, "door2", true);
        // labyrinthBuilder.setDoorEndpointOffsets("door2", rightOffset, topOffset);
        
        controller.createDoor("room2", "room5", true, "door3", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door3", leftOffset, rightOffset);

        controller.createDoor("room1", "room4", true, "door4", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door4", rightOffset, topOffset);
        
        controller.createDoor("room2", "room3", false, "door5", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door5", rightOffset, leftOffset);
        
        controller.createDoor("room7", "room1", false, "door6", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door6", topOffset, bottomOffset);
        
        controller.createDoor("room2", "room6", true, "door7", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door7", bottomOffset, topOffset);
        
        controller.createDoor("room8", "room3", false, "door8", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door8", rightOffset, bottomOffset );

        controller.createDoor("room4", "room7", false, "door9", true);
        // labyrinthBuilder.setDoorEndpointOffsets("door9", bottomOffset, rightOffset);
        
        controller.createDoor("room5", "room8", true, "door10", true);
        // labyrinthBuilder.setDoorEndpointOffsets("door10", rightOffset, leftOffset);
        
        controller.createDoor("room6", "room8", false, "door11", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door11", bottomOffset, topOffset);
        
        controller.createDoor("room8", "room9", true, "door12", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door12", rightOffset, leftOffset);
        
        controller.createDoor("room7", "room9", true, "door13", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door13", bottomOffset, rightOffset);

        controller.createDoor("room10", "room8", false, "door14", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door14", rightOffset, bottomOffset);
        
        controller.createDoor("room9", "room11", false, "door15", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door15", leftOffset, topOffset);
        
        controller.createDoor("room11", "room10", false, "door16", false);
        // labyrinthBuilder.setDoorEndpointOffsets("door16", leftOffset, rightOffset);
        
        controller.createDoor("room11", "room12", true, "door17", true);
        // labyrinthBuilder.setDoorEndpointOffsets("door17", rightOffset, leftOffset);

        controller.createStudent("room1", "S1");
        controller.createCleaner("room1", "C3");
        controller.createProfessor("room3", "P3");
            
        controller.createItem("room1", "FFP2", "FFP2");
        controller.createItem("room1", "Camembert", "Camembert");
       

        
    }
}
