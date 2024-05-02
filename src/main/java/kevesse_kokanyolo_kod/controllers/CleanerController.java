package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.people.Cleaner;
import kevesse_kokanyolo_kod.views.CleanerView;

public class CleanerController {
    private Cleaner model;
    private CleanerView view;

    public CleanerController(Cleaner model, CleanerView view) {
        this.model = model;
        this.view = view;
    }
}
