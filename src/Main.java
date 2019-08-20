/**
 * Main.java
 * @author Dan Woolsey
 *
 * Linking together data and CLIs
 *
 * (1) Populate BST with information from Database (via RWA)
 * (2) Pull ordered ArrayList of all Accounts to give to Login
 */

package src;

import src.cli.*;
//import src.obj.*;
import src.obj.Account;
import src.obj.BST;
import src.obj.Graph;

import java.util.ArrayList;

public class Main
{

    public static Login l;

    public static BST tree;
    public static Graph graph;

    public static void main(String[] args)
    {
        try
        {
            tree = new BST();
            tree.populateTree();
            ArrayList<Account> accs = tree.inorderAccountWalk(tree.getRoot());

            graph = new Graph();
            graph.populateVertices();
            graph.populateFriendshipEdges();
            graph.populateRequestEdges();

            l = new Login(accs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
