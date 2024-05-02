package kevesse_kokanyolo_kod.controllers;

import kevesse_kokanyolo_kod.people.Student;
import kevesse_kokanyolo_kod.views.StudentView;

public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
}
