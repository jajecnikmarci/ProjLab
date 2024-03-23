package effects;

import items.Item;
import player.Professor;
import player.Student;

/**
 *
 */
public class PoisonEffect extends RoomEffect {

    public PoisonEffect(Item givenBy, int duration) {
        super(givenBy, duration);
    }

    /**
     *
     */
    @Override
    public void activate() {
        System.out.println("PoisonEffect.activate()");
        Timer timer = new Timer();
        active = true;
        timer.start(duration);
        //TODO notify metódus feliratkozás majd active = false
    }

    /**
     * Az oktatót megmérgezi.
     * @param professor
     */
    @Override
    public void affect(Professor professor) {
        professor.poison();
    }

    /**
     * A hallgatót megmérgezi.
     * @param student
     */
    @Override
    public void affect(Student student) {
        student.poison();
    }
}
