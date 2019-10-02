# Skypertawe - Assignment 3 - CS-230

All files working together will be stored in the directory readwrite/

Currently rebuilding this application inside the src/ directory using the
Lanterna 3.0.1 CLI library while upgrading each section for usability since
the final program was heavily rushed into a working condition.

## Unfinished Features for readwrite/:  

   * MessageGUI2 only displays users who've previously sent you a message
   * Saving a Line in CollabDrawGUI will throw an error when you try and reload the file

## Unfinished Features for src/:

   * Complete listener override for UserCheckBoxList.java
   * Build Edit window
   * Message system works, but only updates on program exit, work out a fix
   * Also gman and energised are duplicated in message menu for some reason

# USAGE:

## For files in readwrite/

    $ javac *.java;

### On Windows:  

    $ java -cp ".;sqlite-jdbc-3.15.1.jar" Main;

### On OSX/Linux:

     $ java -cp ".:sqlite-jdbc-3.15.1.jar" Main;

## For files in src/

    $ javac -cp .:src/lib/* src/*;

    $ java -cp .:src/lib/* src.Main;
