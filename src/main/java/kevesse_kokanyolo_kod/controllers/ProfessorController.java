package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.people.Professor;
import kevesse_kokanyolo_kod.views.ProfessorView;

public class ProfessorController {
    private Professor model;
    private ProfessorView view;

    public ProfessorController(Professor model, ProfessorView view) {
        this.model = model;
        this.view = view;
    }
}
