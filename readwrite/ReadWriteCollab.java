/**
* ReadWriteCollab.java
* @author Dan Woolsey
*
* Handles reading information from a text file containing the coordinates of
* lines and particles on the screen to be redisplayed by CollabDrawGUI
*
*/

import java.util.ArrayList;

public class ReadWriteCollab extends ReadWrite<ArrayList<String>>
{
    public ReadWriteCollab(String dbname, String filename)
    {
        super(dbname, filename);
    }

    public String read(String query)
    {
        // read in collab draw info plus sender and recipient
        // get info from tom
        // do stuff
    }

    public void write(ArrayList<String> data)
    {
        // do stuff here
    }
}
