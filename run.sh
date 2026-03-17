#!/bin/bash

# Compile the Java files
javac -cp "lib/*" @compile.list -d ./bin

# Check if compilation succeeded
if [ $? -eq 0 ]; then
    java -cp "./bin:lib/*" plusCoursChemin.Controleur
else
    echo "Erreur de compilation!"
fi