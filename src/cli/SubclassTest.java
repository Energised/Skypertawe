/**
 * SubclassTest.java
 * @author Dan Woolsey
 *
 * Testing LoginWindow.java and HomeWindow.java
 *
 * -> Am now sure this is a feasable method for dealing with multiple Windows wo/
 *    messy exit issues and will hopefully make a cleaner code base
 * NB: Must now rewrite Main.java and fully get Home.java working
 *
 */

package src.cli;

import src.obj.*;
import src.cli.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.gui2.*;

import java.io.IOException;
import java.util.ArrayList;

public class SubclassTest
{

    public static DefaultTerminalFactory dtf = null;
    public static Terminal t = null;
    public static Screen s = null;

    public static void main(String[] args)
    {
        try
        {
            dtf = new DefaultTerminalFactory();
            t = dtf.createTerminal();
            s = new TerminalScreen(t);
            s.startScreen();

            WindowBasedTextGUI w = new MultiWindowTextGUI(s);

            ArrayList<Account> accs = new ArrayList<Account>();
            Account ac1 = new Account("energised", "Dan", "Woolsey", "07523050753", "17/01", "Swansea", 0, null, "profile-img.jpg");
            accs.add(ac1);

            LoginWindow l = new LoginWindow(s, accs);
            w.addWindowAndWait(l);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(s != null)
            {
                try
                {
                    s.stopScreen();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
