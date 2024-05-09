# A felület működési elve
*push*: Ha valamilyen objektum állapota megváltozik, értesíti a felületet (Éehetőség szerint a változást közvetlen kiváltó metódus értesít).

*pull:* A felület dolga ilyenkor frissíteni a menük, és a megjelenő labirintus állapotát és a változásokat megjeleníteni a képernyőn. Ezt az objektumok állapotának lekérdezésével teszi. 


# Események és az ezeket kiváltó metódusok
## Person
- StateChanged Kiváltó metódusok: [addItem, removeItem, addKillImmunity, addPoisonImmunity, stun, kill]


## Room
- StateChanged - Kiváltó metódusok: [addItem, removeItem, addPoisonEffect, addStunEffect, clearPoisonEffects, addPlayer, removePlayer, effectConsumed]
- RoomSplit - Kiváltó metódus: split
- RoomsMerged - Kiváltó metódus: merge

## Door
- StateChanged - Kiváltó metódus: onShake

## Item
- StateChanged - Kiváltó metódus: use

# Felület feladata az események hatására
## Person
- StateChanged: Ha ez a kijelölt személy, Inventory, Player menük frissítése, bénulás jelölése, ha meghalt a játékos törlése, ha mindenki meghalt, a játék befejezése

## Room 
- StateChanged: Labirintus újrarajzolása, ha ebben a szobában van a kijelölt játékos, az Items in Room menü frissítése
- RoomSplit: Labirintus újrarajzolása, létrejött szoba és ajtó tárolása, feliratkozás az eseményeikre
- RoomsMerged: Megszűnt ajtó, szoba törlése, labirintus újrarajzolás
  
## Door
- StateChanged: Labirintus újrarajzolása

## Item 
- StateChanged: Ha a kijelölt játékosnál van a tárgy, az Inventory menü frissítése