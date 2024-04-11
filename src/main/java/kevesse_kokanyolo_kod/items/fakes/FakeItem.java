package kevesse_kokanyolo_kod.items.fakes;

import kevesse_kokanyolo_kod.items.IItem;
import kevesse_kokanyolo_kod.people.AcademicPerson;

/**
 * A hamis tárgyakat összefogó interfész. 
 * Ennek a megvalósítása által a hamis tárgyak felvételét egységesen lehet kezelni.
 * Az interfész implementálóinak nem szükséges felülírni az interactItem és interact metódusokat, 
 * mert a hamis tárgyak felvételkor törlődnek. 
 */
public interface FakeItem extends IItem {
    /**
     * A hamis tárgyaknak felül kell írni a visitor design patternből eredő accept metódust, 
     * úgy hogy FakeItemként adják át önmagukat a felvevőnek és ne az örökölt típusuk szerint.
     */
    @Override
    void accept(AcademicPerson academicPerson);
}
