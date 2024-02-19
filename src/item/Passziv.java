package item;

import human.Ember;
import labyrinth.Szoba;

public abstract class Passziv extends Targy {
    public void use(Szoba szoba, Ember user) {
        onUse(szoba, user);
    }

    public abstract void onUse(Szoba szoba, Ember user);
}
