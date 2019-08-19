/**
 * Graph.java
 * @author Dan Woolsey
 *
 * Graph class storing ArrayLists of Vertices and Edges for friendship/request data
 */

package src.obj;

import src.*;

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

    public void addNewVertex(Account acc)
    {
        this.vertices.put(acc.getUsername(), new Vertex(acc));
    }

    public void addNewEdge(Vertex v1, Vertex v2, int weight)
    {
        Edge e = new Edge(v1, v2, weight);
        v1.addNewEdge(e);
        v2.addNewEdge(e);
    }

    public Vertex getVertex(Account acc)
    {
        return this.vertices.get(acc.getUsername());
    }

    public static void main(String[] args)
    {
        try
        {
            ReadWriteAccount rwa = new ReadWriteAccount("data.db");
            // pull all accounts for vertices
            ArrayList<Account> accs = rwa.ReadAllAccounts();
            Graph g = new Graph();
            for(Account a : accs)
            {
                g.addNewVertex(a);
            }

            //Vertex v = g.getVertex(accs.get(3));
            //System.out.println("VERTEX TEST AT POS 3: " + v.getAccount().getUsername());
            rwa.close();

            ReadWriteFriends rwf = new ReadWriteFriends("data.db");
            // pull all friendships and requests for edges

            ArrayList<Account> friendships = rwf.getAllFriendships();
            Account a1 = null;
            Account a2 = null;
            for(int x=0; x<friendships.size(); x+=2)
            {
                a1 = friendships.get(x);
                a2 = friendships.get(x+1);
                g.edges.add(new Edge(g.getVertex(a1), g.getVertex(a2), 2));
                g.addNewEdge(g.getVertex(a1), g.getVertex(a2), 2);
            }

            for(Edge e : g.edges)
            {
                System.out.println(e);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
