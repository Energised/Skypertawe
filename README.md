# Skypertawe - Assignment 3 - CS-230

All files working together will be stored in the directory readwrite/

Currently rebuilding this application inside the src/ directory using the
Lanterna 3.0.1 CLI library while upgrading each section for usability since
the final program was heavily rushed into a working condition.

## Unfinished Features for readwrite/:  

   * MessageGUI2 only displays users who've previously sent you a message
   * Saving a Line in CollabDrawGUI will throw an error when you try and reload the file

## Unfinished Features for src/:

   * Message updating system using triggers via SQLite db
   * Complete listener override for UserCheckBoxList.java
   * Build Register, Edit and Messaging windows
   * Deal with no-echo on exit that happens occasionally for some reason (~)
   * Find workaround for parent component length in Homes topRightPanel
   * Refactoring windows for real this time, edits will be made and pre existing
     classes Login and Home will be replaced w/ LoginWindow and HomeWindow

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
