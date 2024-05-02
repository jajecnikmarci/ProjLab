package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.room.Door;
import kevesse_kokanyolo_kod.views.DoorView;

public class DoorController {
    private Door model;
    private DoorView view;

    public DoorController(Door model, DoorView view) {
        this.model = model;
        this.view = view;
    }
}
