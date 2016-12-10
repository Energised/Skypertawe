/**
* Main.java
* @author Dan Woolsey
*
* Builds and populates BST and Graph structures with info from ReadWrite
*
* USAGE:
*
*   java ".:sqlite-jdbc-3.15.1.jar" Main
*          ^^ .: on Mac, .; on Windows
*/

import java.util.ArrayList;
import java.util.Set;

public class Main
{
    BST tree;
    Graph graph;
    ReadWriteAccount rwa;
    ReadWriteFriends rwf;

    final String DATABASE = "data.db";

    public Main() throws Exception
    {
        this.tree = new BST();
        this.rwa = new ReadWriteAccount(DATABASE);
        ArrayList<Account> accounts = this.rwa.read_all_accounts();
        this.tree.addAccountsFromArrayList(accounts);
        //this.tree.printAlphabetical(this.tree.getRoot());
        this.graph = new Graph();
        Vertex v1;
        boolean test;
        for(Account acc : accounts)
        {
            test = this.graph.addVertex(new Vertex(acc.getUsername()),false);
            //System.out.println(test);
        }
        //boolean ans = this.graph.containsVertex(new Vertex(accounts.get(0).getUsername()));
        //System.out.println(ans);

        this.rwf = new ReadWriteFriends(DATABASE);
        ArrayList<Account> friends = this.rwf.get_all_friends();

        int count = 0;
        Account a1;
        Account a2;

        while(count < friends.size())
        {
            a1 = friends.get(count);
            a2 = friends.get(count+1);
            this.graph.addEdge(this.graph.getVertex(a1.getUsername()),
                                this.graph.getVertex(a2.getUsername()));
            count += 2;
        }

        Set<Edge> edges = this.graph.getEdges();
        for(Edge x : edges)
        {
            System.out.println(x);
        }

        ArrayList<Account> search = this.tree.searchBeginningWith("e");
        for(Account x : search)
        {
            System.out.println(x.getUsername());
        }
    }

    public static void main(String[] args) throws Exception
    {
        Main m = new Main();
    }
}
