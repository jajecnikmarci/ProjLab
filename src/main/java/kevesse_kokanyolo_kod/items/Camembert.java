package kevesse_kokanyolo_kod.items;

import kevesse_kokanyolo_kod.effects.PoisonEffect;
import kevesse_kokanyolo_kod.menus.Printer;
import kevesse_kokanyolo_kod.menus.SkeletonMenu;
import kevesse_kokanyolo_kod.player.Player;
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
     * @param player a játékos, aki használja a tárgyat
     */
    @Override
    public void use(Room room, Player player) {
        SkeletonMenu.startCall("Camembert.use(Room, Player)");
        PoisonEffect camembertEffect = new PoisonEffect(this, 30, room);
        room.addEffect(camembertEffect);
        camembertEffect.activate();
        effect = camembertEffect;
        room.poisonPlayers(); //Azért itt mert itt ismerjük a szobát
        player.removeItem(this);
        SkeletonMenu.endCall();
    }

    @Override
    public void accept(Player player) {
        SkeletonMenu.startCall("Camembert.accept(Player)");
        player.acceptItem(this);
        SkeletonMenu.endCall();
    }
    @Override
    public void printState(Printer printer) {
        printer.startPrintObject("Camembert");
        printer.printField("effect", this.effect);  
        printer.endPrintObject();      
    }

}
