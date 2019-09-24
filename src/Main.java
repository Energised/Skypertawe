/**
 * Main.java
 * @author Dan Woolsey
 *
 * Linking together data and CLIs
 *
 *
 * (1) Setup terminal and screen for lanterna
 * (2) Populate BST with information from Database (via RWA)
 * (3) Pull ordered ArrayList of all Accounts to give to Login
 */

package src;

import src.cli.*;
//import src.obj.*;
import src.obj.Account;
import src.obj.BST;
import src.obj.Graph;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.gui2.*;

import java.util.ArrayList;

public class Main
{

    public static DefaultTerminalFactory dtf = null;
    public static Terminal t = null;
    public static Screen s = null;

    public static BST tree;
    public static Graph graph;

    public static void main(String[] args)
    {
        try
        {
            // setup terminal and screen for lanterna
            dtf = new DefaultTerminalFactory();
            t = dtf.createTerminal();
            s = new TerminalScreen(t);
            s.startScreen();

            WindowBasedTextGUI w = new MultiWindowTextGUI(s);

            // setup information from ReadWrite sections
            tree = new BST();
            tree.populateTree();
            ArrayList<Account> accs = tree.inorderAccountWalk(tree.getRoot());

            graph = new Graph();
            graph.populateVertices();
            graph.populateFriendshipEdges();
            graph.populateRequestEdges();

            LoginWindow l = new LoginWindow(s, accs);
            w.addWindowAndWait(l);


        }
        catch(Exception e)
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
