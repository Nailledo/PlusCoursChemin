@echo off

setlocal EnableDelayedExpansion

javac "@compile.list" -d ./bin

REM Vérifier si la compilation a réussi
if !ERRORLEVEL! EQU 0 (
    java -cp ./bin plusCoursChemin.Controleur
) else (
    echo Erreur de compilation!
)
pause
