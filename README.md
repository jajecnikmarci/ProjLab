# ProjLab
2024 kevesse_kokanyolo_kod Szoftver Projekt Laboratórium


# JDK 17 

# Feature hozzaadasanak a menete

`git branch feature-neve`
`git add .`
`git commit`

`git pull`
`git push`

Compile and run on linux
javac -d bin src/main/**/*.java
jar cfm labyrinth.jar manifest.mf -C bin/ .     
java -jar labyrinth.jar -f -i src/test/resources/input.txt  -o src/test/tstOutput/output.txt
