#!/bin/bash

# Compile the Java files
javac -d bin $(find src -name '*.java')

# Run the main Java class
java -cp bin plusCoursChemin.Controleur