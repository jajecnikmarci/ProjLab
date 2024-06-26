# ProjLab
2024 kevesse_kokanyolo_kod Szoftver Projekt Laboratórium

# Feature hozzáadásának a menete
`git branch <feature-neve>`

`git add .`

`git commit -m "<commit leírása>"`
## Merge conflict feloldása
`git pull origin main`

`git merge main` - fontos, hogy a `<feature-neve>` branchen legyél

`git push --set-upstream origin <feature-neve>`


# Fordíttás és futtatás 
- A fordításhoz JDK 17 szükséges
Compile and run on linux
javac -d bin src/main/**/*.java
jar cfm labyrinth.jar manifest.mf -C bin/ .     
~~java -jar labyrinth.jar -f -i src/test/resources/input.txt  -o src/test/tstOutput/output.txt~~

# Fordítás windowson
1. Parancsorral lépjünk a `src\main\java\kevesse_kokanyolo_kod` mappába
2. Futtassuk a következő parancsot: `javac -d ../../../../bin *.java effects/*.java menus/*.java people/*.java room/*.java items/*.java items/fakes/*.java`
3. Navigáljunk a létrejött bin mappába (a projekt gyökérkönnyvtárában lesz)!
4. Ezután hozzuk létre a jar file-t az alábbi paranccsal `jar cfm ../Labyrinth.jar ../manifest.mf kevesse_kokanyolo_kod/*.class kevesse_kokanyolo_kod/effects/*.class kevesse_kokanyolo_kod/menus/*.class kevesse_kokanyolo_kod/people/*.class kevesse_kokanyolo_kod/room/*.class kevesse_kokanyolo_kod/items/*.class kevesse_kokanyolo_kod/items/fakes/*.class`
5. Létrejött A Labyrinth.jar a gyökérkönyvtárban. Odanavigálva futtathatjuk a `java -jar Labyrinth.jar` segítségével.



# Grafikus verzió fordítás és futtatás
1. Powershellel lépjünk a `src\main\java\kevesse_kokanyolo_kod` mappába
2. Futtassuk a következő parancsokat: `$javaFiles = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }`
`javac -d ..\..\..\..\bin $javaFiles`
3. Navigáljunk a létrejött bin mappába (a projekt gyökérkönnyvtárában lesz)!
4. Futtassuk a következő parancsokat: `$classes = Get-ChildItem -Recurse -Filter *.class | ForEach-Object { $_.FullName.Replace((Get-Location).Path + "\", "") }`
`jar cfm ..\Labyrinth.jar ..\manifest.mf $classes`
5. Létrejött A Labyrinth.jar a gyökérkönyvtárban. Odanavigálva futtathatjuk a `java -jar Labyrinth.jar` segítségével.



# File lista generálása
```
Get-ChildItem -Recurse -Filter *.java | ForEach-Object {
    $file = $_
    $fileName = $file.Name
    $fileSize = $file.Length
    $creationDate = $file.CreationTime
    "$fileName, $fileSize, $creationDate, Description"
} | Out-File -FilePath java_files_list.csv
```
A java_files_list.csv-ben jeleninek meg a fileok
