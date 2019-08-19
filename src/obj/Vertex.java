/**
 * Vertex.java
 * @author Dan Woolsey
 *
 * Vertex class for use with Edge and Graph classes
 */

package src.obj;

import java.util.ArrayList;

class Vertex
{
    private Account account;
    private ArrayList<Edge> edges;

    public Vertex(Account acc)
    {
        this.account = acc;
        this.edges = new ArrayList<Edge>();
    }

    public void addNewEdge(Edge e)
    {
        edges.add(e);
    }

    public Account getAccount()
    {
        return this.account;
    }

    public ArrayList<Edge> getEdges()
    {
        return this.edges;
    }
}
