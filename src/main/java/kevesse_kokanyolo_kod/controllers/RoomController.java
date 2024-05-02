package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.room.Room;
import kevesse_kokanyolo_kod.views.RoomView;

public class RoomController {
    private Room model;
    private RoomView view;

    public RoomController(Room model, RoomView view) {
        this.model = model;
        this.view = view;
    }
}
