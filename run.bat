@echo off

setlocal EnableDelayedExpansion

javac -cp "lib/*" "@compile.list" -d ./bin

REM Vérifier si la compilation a réussi
if !ERRORLEVEL! EQU 0 (
    java -cp "./bin;lib/*" plusCoursChemin.Controleur
) else (
    echo Erreur de compilation!
)