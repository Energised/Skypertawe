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

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            BST tree = new BST();
            tree.populateTree();
            ArrayList<Account> accs = tree.inorderAccountWalk(tree.getRoot());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
