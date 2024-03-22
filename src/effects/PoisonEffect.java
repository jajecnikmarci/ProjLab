package effects;

import player.Professor;
import player.Student;

/**
 *
 */
public class PoisonEffect extends RoomEffect {
    /**
     *
     */
    @Override
    public void activate() {
        System.out.println("PoisonEffect.activate()");
        Timer timer = new Timer();
        active = true;
        timer.start(30);
    }

    /**
     * @param professor
     */
    @Override
    public void affect(Professor professor) {

    }

    /**
     * @param student
     */
    @Override
    public void affect(Student student) {

    }
}
