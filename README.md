# Skypertawe - Assignment 3 - CS-230

All files working together will be stored in the directory readwrite/

Currently rebuilding this application inside the src/ directory using the
Lanterna 3.0.1 CLI library while upgrading each section for usability since
the final program was heavily rushed into a working condition.

Messaging system is complete, will continue to test with multiple users and
try to setup the database on an S3 bucket so all program data can be pulled
from any computer

Collaborative Drawing environment will only be a part of the GUI system, in the
future I might rebuilt the whole GUI system but I can't be bothered right now

## Unfinished Features for readwrite/:  

   * MessageGUI2 only displays users who've previously sent you a message
   * Saving a Line in CollabDrawGUI will throw an error when you try and reload the file

## Unfinished Features for src/:

   * Complete listener override for UserCheckBoxList.java
   * Edits work on reset
   * Empty database wont allow for account registration (?) must hardcode an
     account in the db first
   * Once a request is accepted, a user must log out and back in before
     UserCheckBoxList and MessageWindow update
   * Replace stack trace prints with a logging system (should have done this ages ago)

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
