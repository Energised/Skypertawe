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

    public static LoginGUI login;
    public static MessageGUI2 msg;
    public static CreateAccountGUI create;
    public static HomeGUI home;
    public static ProfileGUI profile;
    public static CollabDrawGUI draw;

    Account a;

    final String DATABASE = "data.db";

    public Main() throws Exception
    {
        this.rwa = new ReadWriteAccount(DATABASE);
        this.rwf = new ReadWriteFriends(DATABASE);
        ArrayList<Account> accounts = this.rwa.read_all_accounts();
        this.tree = populate_tree(new BST(), accounts);
        this.graph = populate_graph(new Graph(), accounts);
        this.a = new Account("","","","","","",0,"","");
        //set_login();
    }

    public static LoginGUI get_login() throws Exception
    {
        return login;
    }

    public static MessageGUI2 get_message() throws Exception
    {
        return msg;
    }

    public static CreateAccountGUI get_create() throws Exception
    {
        return create;
    }

    public static HomeGUI get_home() throws Exception
    {
        return home;
    }

    public static ProfileGUI get_profile() throws Exception
    {
        return profile;
    }

    public static CollabDrawGUI get_draw() throws Exception
    {
        return draw;
    }

    public static void set_login() throws Exception
    {
        login = new LoginGUI();
    }

    public static void set_message(Account acc) throws Exception
    {
        msg = new MessageGUI2(acc);
    }

    public static void set_create() throws Exception
    {
        create = new CreateAccountGUI();
    }

    public static void set_home(Account acc) throws Exception
    {
        home = new HomeGUI(acc);
    }

    public static void set_profile(Account acc) throws Exception
    {
        profile = new ProfileGUI(acc);
    }

    public static void set_draw(Account acc1, Account acc2) throws Exception
    {
        draw = new CollabDrawGUI(acc1, acc2);
    }

    /**
    * populates a BST with accounts from the database
    */

    public BST populate_tree(BST tree, ArrayList<Account> acc) throws Exception
    {
        tree.addAccountsFromArrayList(acc);
        //tree.printAlphabetical(this.tree.getRoot()); // display tree
        return tree;
    }

    public Graph populate_graph(Graph graph, ArrayList<Account> acc) throws Exception
    {
        Vertex v1;
        boolean test;
        for(Account a : acc)
        {
            test = graph.addVertex(new Vertex(a.getUsername()),false);
            //System.out.println(test);
        }
        //boolean ans = this.graph.containsVertex(new Vertex(accounts.get(0).getUsername()));
        //System.out.println(ans);
        // get a list of all friendships between accounts
        ArrayList<Account> friends = this.rwf.get_all_friends();
        int count = 0;
        Account a1;
        Account a2;
        while(count < friends.size())
        {
            a1 = friends.get(count);
            a2 = friends.get(count+1);
            graph.addEdge(graph.getVertex(a1.getUsername()),
                          graph.getVertex(a2.getUsername()));
            count += 2;
        }
        return graph;
    }

    public Graph get_graph()
    {
        return this.graph;
    }

    public BST get_tree()
    {
        return this.tree;
    }

    public static void main(String[] args) throws Exception
    {
        Main m = new Main();
        set_login();
        //Set<Edge> edges = m.get_graph().getEdges();
        //for(Edge x : edges)
        //{
        //    System.out.println(x);
        //}
        //
        //ArrayList<Account> search = m.get_tree().searchBeginningWith("g");
        //for(Account x : search)
        //{
        //    System.out.println(x.getUsername());
        //}
    }
}
