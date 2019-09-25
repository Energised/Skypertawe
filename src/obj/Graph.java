/**
 * Graph.java
 * @author Dan Woolsey
 *
 * Graph class storing HashMap (Vertices) and ArrayList (Edges)
 * for friendship/request data for use by GUI
 *
 *  Graph()
 *      VERTEX FUNCTIONS
 *          getVertices()
 *          addNewVertex(Account acc)
 *          getVertex(Account acc)
 *      EDGE FUNCTIONS
 *          addNewEdge(Vertex v1, Vertex v2, int weight)
 *          getEdges()
 *      BUILDING + MANAGING GRAPH
 *          populateVertices()
 *          populateFriendshipEdges()
 *          populateRequestEdges()
 *          addRecord(ArrayList<Account> toAdd)
 *          emptyGraph()
 *          update() -> only function for outside calling to update Graph
 *      QUERYING GRAPH
 *          getFriends(Account acc)
 *          getFriendNames(Account acc) // NB: used in UserSearchBox
 *          getRequests(Account acc)
 */

package src.obj;

import src.*;
import src.obj.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph
{
    private HashMap<String, Vertex> vertices;
    private ArrayList<Edge> edges;

    public Graph()
    {
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new ArrayList<Edge>();
    }


    /**
     * VERTEX FUNCTIONS
     */

    public HashMap<String, Vertex> getVertices()
    {
        return this.vertices;
    }

    public void addNewVertex(Account acc)
    {
        this.getVertices().put(acc.getUsername(), new Vertex(acc));
    }

    public Vertex getVertex(Account acc)
    {
        return this.getVertices().get(acc.getUsername());
    }

    /**
     * EDGE FUNCTIONS
     */

    public void addNewEdge(Vertex v1, Vertex v2, int weight)
    {
        Edge e = new Edge(v1, v2, weight);
        v1.addNewEdge(e);
        v2.addNewEdge(e);
        this.edges.add(e);
    }

    public ArrayList<Edge> getEdges()
    {
        return this.edges;
    }


    /**
     * BUILDING AND MANAGING THE GRAPH
     */

    public void populateVertices() throws Exception
    {
        ReadWriteAccount rwa = new ReadWriteAccount("data.db");
        ArrayList<Account> accs = rwa.ReadAllAccounts();
        for(Account a : accs)
        {
            this.addNewVertex(a);
        }
        rwa.close();
    }

    public void populateFriendshipEdges() throws Exception
    {
        ReadWriteFriends rwf = new ReadWriteFriends("data.db");
        ArrayList<Account> friendships = rwf.getAllFriendships();
        for(int x=0; x<friendships.size(); x+=2)
        {
            //g.edges.add(new Edge(g.getVertex(a1), g.getVertex(a2), 2));
            this.addNewEdge(this.getVertex(friendships.get(x)),
                            this.getVertex(friendships.get(x+1)), 2);
        }
        rwf.close();
    }

    public void populateRequestEdges() throws Exception
    {
        ReadWriteFriends rwf = new ReadWriteFriends("data.db");
        ArrayList<Account> requests = rwf.getAllRequests();
        for(int x=0; x<requests.size(); x+=2)
        {
            this.addNewEdge(this.getVertex(requests.get(x)),
                            this.getVertex(requests.get(x+1)), 1);
        }
        rwf.close();
    }

    // returns 1 if success, 0 if failure
    public int addRecord(ArrayList<Account> toAdd) throws Exception
    {
        try
        {
            ReadWriteFriends rwf = new ReadWriteFriends("data.db");
            rwf.setFriendshipRecord(toAdd);
            rwf.close();
            return 1;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public void emptyGraph()
    {
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new ArrayList<Edge>();
    }

    public void update() throws Exception
    {
        this.emptyGraph();
        this.populateVertices();
        this.populateFriendshipEdges();
        this.populateRequestEdges();
    }

    /**
     * QUERYING GRAPH
     */

    public ArrayList<Account> getFriends(Account acc)
    {
        ArrayList<Edge> friendships = this.getVertex(acc).getEdges();
        ArrayList<Account> friends = new ArrayList<Account>();
        for(Edge e : friendships)
        {
            if(e.getWeight() == 2)
            {
                //System.out.println(e);
                if(e.getVertex1() == this.getVertex(acc))
                {
                    friends.add(e.getVertex2().getAccount());
                }
                else
                {
                    friends.add(e.getVertex1().getAccount());
                }
            }
        }
        return friends;
    }

    public ArrayList<String> getFriendNames(Account acc)
    {
        ArrayList<Edge> friendships = this.getVertex(acc).getEdges();
        ArrayList<String> friendNames = new ArrayList<String>();
        for(Edge e : friendships)
        {
            if(e.getWeight() == 2)
            {
                //System.out.println(e);
                if(e.getVertex1() == this.getVertex(acc))
                {
                    friendNames.add(e.getVertex2().getAccount().getUsername());
                }
                else
                {
                    friendNames.add(e.getVertex1().getAccount().getUsername());
                }
            }
        }
        return friendNames;
    }

    public ArrayList<Account> getRequests(Account acc)
    {
        ArrayList<Edge> requests = this.getVertex(acc).getEdges();
        ArrayList<Account> userReqs = new ArrayList<Account>();
        for(Edge e : requests)
        {
            if(e.getWeight() == 1)
            {
                //System.out.println(e);
                if(e.getVertex1() == this.getVertex(acc))
                {
                    userReqs.add(e.getVertex2().getAccount());
                }
                else
                {
                    userReqs.add(e.getVertex1().getAccount());
                }
            }
        }
        return userReqs;
    }

    public static void main(String[] args)
    {
        try
        {
            // how to setup graph
            Graph g = new Graph();
            g.populateVertices();
            g.populateFriendshipEdges();
            g.populateRequestEdges();

            ArrayList<Account> toAdd = new ArrayList<Account>();
            Account ac1 = new Account("energised", "Dan", "Woolsey", "07523050753", "17/01", "Swansea", 0, null, "profile-img.jpg");
            Account ac2 = new Account("gman", "Gary", "Waho", "07649752134", "7/12", "Bradford", 0, null, "profile-img.jpg");
            Account ac3 = new Account("bobby", "Bob", "Cobb", "07432257152", "9/11", "Ramsgate", 0, null, "profile-img.jpg");
            Account ac4 = new Account("zapdos", "Ash", "Smash", "07477957152", "12/05", "Basildon", 0, null, "profile-img.jpg");
            Account ac5 = new Account("coleslaw", "Jack", "Cross", "07432217152", "22/11", "Hackney", 0, null, "profile-img.jpg");
            Account ac6 = new Account("smokeweed4lyf", "Adam", "Fladam", "07342257192", "01/01", "York", 0, null, "profile-img.jpg");
            toAdd.add(ac4);
            toAdd.add(ac1);
            //g.addRecord(toAdd);
            //g.update();

            //for(Edge e : g.getEdges())
            //{
            //    System.out.println(e);
            //}
            // test for friends
            ArrayList<Account> f1 = g.getFriends(ac5);
            for(Account a : f1)
            {
                System.out.println(a.getUsername());
            }
            // test for requests
            ArrayList<Account> r1 = g.getRequests(ac6);
            for(Account a : r1)
            {
                System.out.println(a.getUsername());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
