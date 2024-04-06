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

