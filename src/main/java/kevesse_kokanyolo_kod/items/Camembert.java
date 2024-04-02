package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.PoisonEffect;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.people.AcamedicPerson;
import kevesse_kokanyolo_kod.room.Room;

/**
 * A Dobozolt Káposztás Camembert-t reprezentáló osztály
 * Használatakor a Player-re PoisonEffect-et rak, ami 30 másodpercig tart.
 */
public class Camembert extends Item {
    /**
     * A Camembert használata. Eltávolítja a játékos tárgyai közül a Camembert-et,
     * és a szobához hozzáad egy PoisonEffect-et, valamint aktiválja azt.
     *
     * @param room   a szoba, ahol a tárgyat használják
     * @param acamedicPerson a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Camembert.use(Room, Player)");
        PoisonEffect camembertEffect = new PoisonEffect(this, 30, room);
        room.addPoisonEffect(camembertEffect);
        camembertEffect.activate();
        effect = camembertEffect;
        room.poisonPlayers(); //Azért itt mert itt ismerjük a szobát
        acamedicPerson.removeItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(AcamedicPerson acamedicPerson) {
        SkeletonMenu.startCall("Camembert.accept(Player)");
        acamedicPerson.acceptItem(this);
        SkeletonMenu.endCall();
    }
}
